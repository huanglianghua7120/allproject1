package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceEvaluateEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceEvaluate;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@Component("srmSpmPerformanceEvaluateService")
@Path("/srmSpmPerformanceEvaluateService")
public class SrmSpmPerformanceEvaluateService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceEvaluateService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";

	@Autowired
	private ISrmSpmPerformanceEvaluate srmSpmPerformanceEvaluateServer;

	public SrmSpmPerformanceEvaluateService() {
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
	@Path("findPerformanceEvaluateById")
	public String findPerformanceEvaluateById(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);

			Map<String,Object> performanceEvaluateInfo = srmSpmPerformanceEvaluateServer.findPerformanceEvaluateById(jsonParam);
			jsonParam.put("data", performanceEvaluateInfo);
			jsonParam.put("status", "S");
			jsonParam.put("msg", "查询成功");
			return jsonParam.toJSONString();

		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			LOGGER.error("查询失败！", e);
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

    /**
     * 查询绩效详细评分信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findPerformanceEvaluateDetail")
	public String findPerformanceEvaluateDetail(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);

			Map<String,Object> performanceEvaluateInfo = srmSpmPerformanceEvaluateServer.findPerformanceEvaluateDetail(jsonParam);
			jsonParam.put("data", performanceEvaluateInfo);
			jsonParam.put("status", "S");
			jsonParam.put("msg", "查询成功");
			return jsonParam.toJSONString();

		}catch (Exception e) {
			LOGGER.error("查询失败！", e);
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}


    /**
     * 查询绩效的某一评分人的评分信息-excel导出
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findPerformanceEvaluateExport")
	public String findPerformanceEvaluateExport(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			List<SrmSpmPerformanceEvaluateEntity_HI_RO> list = srmSpmPerformanceEvaluateServer.findPerformanceEvaluateExport(paramJSON);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}


    /**
     * 保存绩效评分信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("saveEvaluate")
	@POST
	public String saveEvaluate(@FormParam("params")String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmPerformanceEvaluateServer.saveEvaluate(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("保存绩效评分信息失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存绩效评分信息失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>保存绩效评分信息失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存绩效评分信息失败!", 0, null);
		}
	}


    /**
     * 查询绩效的监控评分信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("findPerformanceEvaluateMonitorById")
	public String findPerformanceEvaluateMonitorById(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			Integer performanceId = jsonParam.getInteger("performanceId");
			if(performanceId != null) {
				Map<String,Object> performanceEvaluateInfo = srmSpmPerformanceEvaluateServer.findPerformanceEvaluateMonitorById(performanceId);
				jsonParam.put("data", performanceEvaluateInfo);
				jsonParam.put("status", "S");
				jsonParam.put("msg", "查询成功");
				return jsonParam.toJSONString();
			}else {
				return convertResultJSONObj("E", "请检查参数", 0, null);
			}
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			LOGGER.error("查询失败！", e);
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}


    /**
     * 批量导入——绩效评分
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @POST
    @Path("saveBatchImportEvaluateScore")
    public String saveBatchImportEvaluateScore(@FormParam(PARAMS) String params){
        LOGGER.info("参数："+params.toString());
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmSpmPerformanceEvaluateServer.saveBatchImportEvaluateScore(jsonParams);
            return JSONObject.toJSONString(jsondata);
        }catch (UtilsException e){
            LOGGER.error("导入失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "导入失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("导入失败，" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E",   "导入失败，"+e.getMessage(), 0, null);
        }
    }

}
