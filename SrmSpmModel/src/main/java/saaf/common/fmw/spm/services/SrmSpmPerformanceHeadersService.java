package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceHeadersEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceHeaders;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceIndicators;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

@Component("srmSpmPerformanceHeadersService")
@Path("/srmSpmPerformanceHeadersService")
public class SrmSpmPerformanceHeadersService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceHeadersService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";

	@Autowired
	private ISrmSpmPerformanceHeaders srmSpmPerformanceHeadersServer;

	@Autowired
	private ISrmSpmPerformanceIndicators srmSpmPerformanceIndicators;

	public SrmSpmPerformanceHeadersService() {
		super();
	}


    /**
     * 查询绩效列表（分页）
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("queryPerformanceHeadersList")
	public String queryPerformanceHeadersList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> page = srmSpmPerformanceHeadersServer.queryPerformanceHeadersList(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		} catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

    /**
     * 供应商-查询绩效列表（分页）
     * @param params
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@POST
	@Path("queryPerformanceResultList")
	public String queryPerformanceResultList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = this.parseObject(params);
			Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> page = srmSpmPerformanceHeadersServer.queryPerformanceResultList(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		} catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}


    /**
     * 查询绩效头信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("findPerformanceHeaderById")
	@POST
	public String findPerformanceHeaderById(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer performanceId = jsonParam.getInteger("performanceId");
			if(performanceId != null) {
				Map<String,Object> performanceInfo = srmSpmPerformanceHeadersServer.findPerformanceHeaderById(performanceId);
				jsonParam.put("data", performanceInfo);
				jsonParam.put("status", "S");
				jsonParam.put("msg", "查询成功");
				return jsonParam.toJSONString();
			}else {
				return convertResultJSONObj("E", "请检查performanceId参数", 0, null);
			}
		}catch (Exception e) {
			LOGGER.error("查询失败！", e);
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

    /**
     * 保存绩效信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("savePerformance")
	@POST
	public String savePerformance(@FormParam("params")String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmPerformanceHeadersServer.savePerformance(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		} catch (UtilsException e){
            LOGGER.error("保存绩效信息失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存绩效信息失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>保存绩效信息失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存绩效信息失败!", 0, null);
		}
	}


    /**
     * 计算绩效行得分
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("calculateScore")
	@POST
	public String saveCalculateScore(@FormParam("params")String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmPerformanceHeadersServer.saveCalculateScore(jsonParams);
			//srmSpmPerformanceIndicators.saveCalculateIndicatorScore(jsonParams);
			//return null;
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("计算得分失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "计算得分失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>计算得分失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "计算得分失败!", 0, null);
		}
	}


    /**
     * 保存绩效信息
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Path("deletePerformance")
	@POST
	public String deletePerformance(@FormParam("params")String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmPerformanceHeadersServer.deletePerformance(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("删除绩效信息失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除绩效信息失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>删除绩效信息失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除绩效信息失败!", 0, null);
		}
	}

}
