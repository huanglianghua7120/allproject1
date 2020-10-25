package saaf.common.fmw.spm.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceHeadersEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceHeadersEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceLinesEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceLines;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmPerformanceLinesServer")
public class SrmSpmPerformanceLinesServer implements ISrmSpmPerformanceLines{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceLinesServer.class);

	@Autowired
	private ViewObject<SrmSpmPerformanceHeadersEntity_HI> srmSpmPerformanceHeadersDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceLinesEntity_HI> srmSpmPerformanceLinesDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceIndicatorsEntity_HI> srmSpmPerformanceIndicatorsDAO_HI;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceLinesEntity_HI_RO> srmSpmPerformanceLinesEntityHIRO;

	public SrmSpmPerformanceLinesServer() {
		super();
	}


    /**
     * 查询绩效行信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Map<String, Object> findPerformanceLinesById(Integer performanceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(SrmSpmPerformanceLinesEntity_HI_RO.QUERY_PERFORMANCE_LINES_LIST);
		sb.append(" AND Spl.Performance_Id = :performanceId");
		queryParamMap.put("performanceId", performanceId);
		sb.append(" ORDER BY Psi.Supplier_Number");

		List<SrmSpmPerformanceLinesEntity_HI_RO> row = srmSpmPerformanceLinesEntityHIRO.findList(sb.toString(), queryParamMap);
		map.put("row", row);
		return map;
	}


    /**
     * 保存绩效行信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject savePerformanceLine(JSONObject jsonParams) throws Exception {
		try {
			Integer performanceId = jsonParams.getInteger("performanceId");
			Integer performanceLineId = jsonParams.getInteger("performanceLineId");
			String action = jsonParams.getString("ACTION");
			if (null == performanceId || null == performanceLineId){
				return SToolUtils.convertResultJSONObj("E", "参数错误", 1, null);
			}
			Integer userId = jsonParams.getInteger("varUserId");
			SrmSpmPerformanceLinesEntity_HI linesEntity = srmSpmPerformanceLinesDAO_HI.getById(performanceLineId);
			if (null != linesEntity){
				if ("SUBMIT".equals(action)){
					linesEntity.setConfirmFlag("Y");
					linesEntity.setConfirmDate(new Date());
				}
				linesEntity.setImprovementPlan(jsonParams.getString("improvementPlan"));
				linesEntity.setOperatorUserId(userId);
				srmSpmPerformanceLinesDAO_HI.saveOrUpdate(linesEntity);

				if ("SUBMIT".equals(action)){
					//如果同一绩效下所有供应商都已确认，则更新头状态为“已完成”
					List<SrmSpmPerformanceLinesEntity_HI> lineList = srmSpmPerformanceLinesDAO_HI.findByProperty("performanceId", performanceId);
					Boolean completeFlag = true;
					if (!lineList.isEmpty()){
						for (SrmSpmPerformanceLinesEntity_HI entity : lineList){
							if (null == entity.getConfirmFlag() || "".equals(entity.getConfirmFlag())){
								completeFlag = false;
							}
						}
						if (completeFlag) {
							SrmSpmPerformanceHeadersEntity_HI headersEntity = srmSpmPerformanceHeadersDAO_HI.getById(performanceId);
							if (null == headersEntity) {
								//throw new RuntimeException("更新绩效状态时发生错误：找不到绩效头信息");
                                throw new UtilsException("更新绩效状态时发生错误：找不到绩效头信息");
							} else {
								headersEntity.setStatus("COMPLETE");
								headersEntity.setOperatorUserId(userId);
								srmSpmPerformanceHeadersDAO_HI.saveOrUpdate(headersEntity);
							}
						}
					}
				}
			}else {
				return SToolUtils.convertResultJSONObj("E", "参数错误", 1, null);
			}
			return SToolUtils.convertResultJSONObj("S", "保存成功", 1, linesEntity);
		} catch (Exception e) {
			//throw new RuntimeException(e.getCause().getMessage());
            throw new UtilsException("保存失败"+e.getCause().getMessage());
		}
	}



}
