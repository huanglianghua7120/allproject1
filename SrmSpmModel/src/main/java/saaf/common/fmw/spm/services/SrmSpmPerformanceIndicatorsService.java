package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceIndicators;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@Component("srmSpmPerformanceIndicatorsService")
@Path("/srmSpmPerformanceIndicatorsService")
public class SrmSpmPerformanceIndicatorsService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceIndicatorsService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";

	@Autowired
	private ISrmSpmPerformanceIndicators srmSpmPerformanceIndicatorsServer;

	public SrmSpmPerformanceIndicatorsService() {
		super();
	}


    /**
     * 查询绩效的指标信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findPerformanceIndicatorsList")
	public String findPerformanceIndicatorsList(@FormParam(PARAMS) String params) {
		try {
			LOGGER.info(params);
			JSONObject jsonParam = this.parseObject(params);
			Map<String, Object> performanceIndicatorsList = srmSpmPerformanceIndicatorsServer.findPerformanceIndicatorsList(jsonParam);
			jsonParam.put("data", performanceIndicatorsList);
			jsonParam.put("status", "S");
			jsonParam.put("msg", "查询成功");
			return jsonParam.toJSONString();
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

    /**
     * 查询绩效的指标信息-excel导出
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findPerformanceIndicatorsForExport")
	public String findPerformanceIndicatorsForExport(@FormParam(PARAMS) String params) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			List<SrmSpmPerformanceIndicatorsEntity_HI_RO> list = srmSpmPerformanceIndicatorsServer.findPerformanceIndicatorsForExport(paramJSON);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

}
