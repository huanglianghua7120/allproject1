package saaf.common.fmw.spm.model.inter.server;

import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceEvaluateEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceEvaluateEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceEvaluate;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceIndicators;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

@Component("srmSpmPerformanceIndicatorsServer")
public class SrmSpmPerformanceIndicatorsServer implements ISrmSpmPerformanceIndicators{

	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceIndicatorsServer.class);

	@Autowired
	private ViewObject<SrmSpmPerformanceIndicatorsEntity_HI> srmSpmPerformanceIndicatorsDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceLinesEntity_HI> srmSpmPerformanceLinesDAO_HI;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceIndicatorsEntity_HI_RO> srmSpmPerformanceIndicatorsDAO_HI_RO;

	@Autowired
	private ISrmSpmPerformanceEvaluate srmSpmPerformanceEvaluate;

	public SrmSpmPerformanceIndicatorsServer() {
		super();
	}


    /**
     * 查询绩效的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public List<SrmSpmPerformanceIndicatorsEntity_HI_RO> queryPerformanceIndicators(JSONObject jsonParam) {

		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryString = new StringBuffer(SrmSpmPerformanceIndicatorsEntity_HI_RO.QUERY_PERFORMANCE_INDICATORS);
		SaafToolUtils.parperParam(jsonParam, "Spi.Performance_Id", "performanceId", queryString, queryParamMap, "=");
		List<SrmSpmPerformanceIndicatorsEntity_HI_RO> list = srmSpmPerformanceIndicatorsDAO_HI_RO.findList(queryString, queryParamMap);
		return list;
	}

    /**
     * 查询绩效的指标信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Map<String, Object> findPerformanceIndicatorsList(JSONObject jsonParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryString = new StringBuffer(SrmSpmPerformanceIndicatorsEntity_HI_RO.QUERY_PERFORMANCE_INDICATORS_LIST);
		Integer performanceId = jsonParam.getInteger("performanceId");
		queryString.append(" AND Spi.Performance_Id = " + performanceId);
		if ("SHOW".equals(jsonParam.getString("searchMode"))){
			queryString.append("AND Spi.Performance_Line_Id = (SELECT Spl.Performance_Line_Id FROM Srm_Spm_Performance_Lines Spl WHERE Spl.Performance_Id = " + performanceId + " AND ROWNUM = 1)");
		}else if ("SUPPLIER".equals(jsonParam.getString("searchMode"))){
			queryString.append("AND Spi.Performance_Line_Id = " + jsonParam.getInteger("performanceLineId"));
		}
		queryString.append(" ORDER BY Std.Evaluate_Dimension,Spi.Indicator_Code");
		List<SrmSpmPerformanceIndicatorsEntity_HI_RO> list = srmSpmPerformanceIndicatorsDAO_HI_RO.findList(queryString, queryParamMap);
		//拼接加减分信息
		if ("SUPPLIER".equals(jsonParam.getString("searchMode"))){
			SrmSpmPerformanceLinesEntity_HI linesEntity = srmSpmPerformanceLinesDAO_HI.getById(jsonParam.getInteger("performanceLineId"));
			if (null != linesEntity) {
				String manualScoreIdStr = linesEntity.getManualScoreIdStr();
				if (null != manualScoreIdStr && !"".equals(manualScoreIdStr)){
					List<SrmSpmPerformanceIndicatorsEntity_HI_RO> manualList = findManualScoreList(linesEntity.getSupplierId(), null, null, manualScoreIdStr);
				    if (null != manualList && manualList.size() > 0){
						for (int i = 0; i < manualList.size(); i ++){
							list.add(manualList.get(i));
						}
					}
				}
			}
		}
		if (!list.isEmpty()) {
			map.put("list", list);
		}
		return map;
	}


    /**
     * 查询绩效的指标信息-excel导出
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public List<SrmSpmPerformanceIndicatorsEntity_HI_RO> findPerformanceIndicatorsForExport(JSONObject jsonParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer queryString = new StringBuffer(SrmSpmPerformanceIndicatorsEntity_HI_RO.QUERY_PERFORMANCE_INDICATORS_FOR_EXPORT);
		Integer performanceId = jsonParam.getInteger("performanceId");
		queryString.append(" AND Spi.Performance_Id = " + performanceId);
		queryString.append("AND Spi.Performance_Line_Id = " + jsonParam.getInteger("performanceLineId"));
		queryString.append(" ORDER BY Std.Evaluate_Dimension,Spi.Indicator_Code");
		List<SrmSpmPerformanceIndicatorsEntity_HI_RO> list = srmSpmPerformanceIndicatorsDAO_HI_RO.findList(queryString, queryParamMap);

		//只保留第一行的供应商信息
		for (int i = 0; i < list.size(); i ++){
			if (i > 0){
				SrmSpmPerformanceIndicatorsEntity_HI_RO entity = list.get(i);
				entity.setSupplierNumber(null);
				entity.setSupplierName(null);
				entity.setScore(null);
				entity.setRank(null);
			}
		}
		//拼接加减分信息
		SrmSpmPerformanceLinesEntity_HI linesEntity = srmSpmPerformanceLinesDAO_HI.getById(jsonParam.getInteger("performanceLineId"));
		if (null != linesEntity) {
			String manualScoreIdStr = linesEntity.getManualScoreIdStr();
			if (null != manualScoreIdStr && !"".equals(manualScoreIdStr)){
				List<SrmSpmPerformanceIndicatorsEntity_HI_RO> manualList = findManualScoreList(linesEntity.getSupplierId(), null, null, manualScoreIdStr);
				if (null != manualList && manualList.size() > 0){
					for (int i = 0; i < manualList.size(); i ++){
						list.add(manualList.get(i));
					}
				}
			}
		}

		return list;
	}


    /**
     * 计算指标得分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public void saveCalculateIndicatorScore(JSONObject jsonParams) throws Exception {
		try {
			Integer performanceId = jsonParams.getInteger("performanceId");
			Integer userId = jsonParams.getInteger("varUserId");
			if (null == performanceId || userId == null){
				throw new UtilsException("参数错误");
			}
			BigDecimal divideNum = new BigDecimal("100");
			//查找所有指标行
			List<SrmSpmPerformanceIndicatorsEntity_HI_RO> indicatorList = queryPerformanceIndicators(jsonParams);
			for (SrmSpmPerformanceIndicatorsEntity_HI_RO indicatorEntity : indicatorList){
				//查找每个指标行的评分信息
				Map<String, Object> evaluateParamsMap = new HashMap<>();
				evaluateParamsMap.put("performanceId", indicatorEntity.getPerformanceId());
				evaluateParamsMap.put("performanceLineId", indicatorEntity.getPerformanceLineId());
				evaluateParamsMap.put("performanceIndicatorId", indicatorEntity.getPerformanceIndicatorId());
                List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList = srmSpmPerformanceEvaluate.queryPerformanceEvaluateList(evaluateParamsMap);
				//List<SrmSpmPerformanceEvaluateEntity_HI> evaluateList = srmSpmPerformanceEvaluateDAO_HI.findByProperty(evaluateParamsMap);
				BigDecimal indicatorScore = new BigDecimal("0");
				//指标得分=评分之和÷评分人数x指标权重
                for (int i = 0; i < evaluateList.size(); i ++){
					indicatorScore = indicatorScore.add(evaluateList.get(i).getScore());
				}
				indicatorScore = indicatorScore.divide(new BigDecimal(evaluateList.size()), 3,BigDecimal.ROUND_HALF_UP);
				indicatorScore = indicatorScore.multiply(indicatorEntity.getIndicatorWeight()).divide(divideNum);

				SrmSpmPerformanceIndicatorsEntity_HI updateEntity = srmSpmPerformanceIndicatorsDAO_HI.getById(indicatorEntity.getPerformanceIndicatorId());
				updateEntity.setIndicatorScore(indicatorScore);
				updateEntity.setOperatorUserId(userId);
				srmSpmPerformanceIndicatorsDAO_HI.saveOrUpdate(updateEntity);
			}
		} catch (Exception e) {
			//throw new RuntimeException("计算指标得分时发生错误:" + e.getCause().getMessage());
            throw new UtilsException("计算指标得分时发生错误:" + e.getCause().getMessage());
		}
	}
    /**
     * 查询指标得分
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public List<SrmSpmPerformanceIndicatorsEntity_HI_RO> findManualScoreList(Integer supplierId, String happenedDateFrom, String happenedDateTo, String manualScoreIdStr) {
		Map<String, Object> queryParamMap = new HashMap();
		StringBuffer queryParam = new StringBuffer();
		queryParam.append(SrmSpmPerformanceIndicatorsEntity_HI_RO.QUERY_MANUAL_SCORE_LIST);
		queryParam.append("  AND Sms.Supplier_Id = " + supplierId);
		if (null != manualScoreIdStr && !"".equals(manualScoreIdStr)){
			queryParam.append("  AND Sms.manual_score_id in (" + manualScoreIdStr + ")");
		}
		if (happenedDateFrom != null && !"".equals(happenedDateFrom.trim())) {
			queryParam.append("  AND TO_DATE(to_char(Sms.Happened_Date,'yyyy-mm'),'yyyy-mm') >= TO_DATE('" + happenedDateFrom + "','yyyy-mm')");
		}
		if (happenedDateTo != null && !"".equals(happenedDateTo.trim())) {
			queryParam.append("  AND TO_DATE(to_char(Sms.Happened_Date,'yyyy-mm'),'yyyy-mm') <= TO_DATE('" + happenedDateTo + "','yyyy-mm')");
		}
		return srmSpmPerformanceIndicatorsDAO_HI_RO.findList(queryParam, queryParamMap);
	}

}

