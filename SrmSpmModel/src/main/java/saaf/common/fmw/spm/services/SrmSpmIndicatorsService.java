package saaf.common.fmw.spm.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.adf.common.utils.SToolUtils;
import com.yhg.hibernate.core.paging.Pagination;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.exception.NotLoginException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmIndicators;

@Component("srmSpmIndicatorsService")
@Path("/srmSpmIndicatorsService")
public class SrmSpmIndicatorsService extends CommonAbstractServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorsService.class);
	private static final String PARAMS = "params";
	private static final String PAGE_INDEX = "pageIndex";
	private static final String PAGE_ROWS = "pageRows";

	@Autowired
	private ISrmSpmIndicators srmSpmIndicatorsServer;

	public SrmSpmIndicatorsService() {
		super();
	}

    /**
     * Description：查询绩效指标
     * @param params 绩效指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("findSrmSpmIndicatorsInfo")
	public String findSrmSpmIndicatorsInfo(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = parseObject(params);
			Pagination<SrmSpmIndicatorsEntity_HI_RO> page = srmSpmIndicatorsServer.SpmIndicatorsInfo(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		}catch (UtilsException e){
            return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null));
        }catch (Exception e) {
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e.getMessage(), 0, null));
		}
	}
	

    /**
     * Description：指标行表删除
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("deleteContact")
	@POST
	public String deleteContact(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmIndicatorsServer.deleteContact(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
		}
	}

    /**
     * Description：数据删除
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("deleteIndicatorsList")
	@POST
	public String deleteIndicatorsList(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmIndicatorsServer.deleteIndicatorsList(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
		} catch (UtilsException e){
            LOGGER.error("删除失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "删除失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("删除失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "删除失败!" + e, 0, null);
		}
	}
	

    /**
     * Description：查询指标信息
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("findIndicators")
    @POST
    public String findIndicators(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer indicatorId = jsonParam.getInteger("indicatorId");
			if(indicatorId!=null) {
			   Map<String,Object> frozenInfo =srmSpmIndicatorsServer.findIndicators(indicatorId);
			   jsonParam.put("frozenInfo", frozenInfo);
		       jsonParam.put("status", "S");
		       jsonParam.put("msg", "查询成功");
		       return jsonParam.toJSONString();
			}else {
				 return convertResultJSONObj("E", "请检查indicatorId参数", 0, null);
			}
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
            LOGGER.error("查询失败！", e);
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
	}
	
	
	

    /**
     * Description：指标數據保存
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("saveIndicators")
	@POST
	public String saveIndicators(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmSpmIndicatorsServer.saveIndicators(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
			/*JSONObject jsonParams = this.parseObject(params);
			boolean flag =srmSpmIndicatorsServer.countIndicator(jsonParams);
			if(flag) {
				JSONObject jsondata = srmSpmIndicatorsServer.saveIndicators(jsonParams);
				return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
			}else {
				return CommonAbstractServices.convertResultJSONObj("E", "此指标已经存在无法保存!", 0, null);
			}*/



		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("保存指标失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "保存指标失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>指标失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "保存指标失败!", 0, null);
		}
	}

    /**
     * Description：生效绩效指标
     * @param params 绩效指标生效所需参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("updateApproveIndicators")
	@POST
	public String updateApproveIndicators(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			/*boolean flag =srmSpmIndicatorsServer.countIndicator(jsonParams);
			if(flag) {*/
				JSONObject jsondata = srmSpmIndicatorsServer.updateApproveIndicators(jsonParams);
				return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
			/*}else {
				return CommonAbstractServices.convertResultJSONObj("E", "此指标已经存在无法生效!", 0, null);
			}*/
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		} catch (UtilsException e){
            LOGGER.error("指标生效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标生效失败:" + e.getMessage(), 0, null);
        }catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>指标失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标生效失败!", 0, null);
		}
	}

    /**
     * Description：指标失效
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("updateRejectedIndicator")
	@POST
	public String updateRejectedIndicator(@FormParam("params") String params) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			/*boolean flag =srmSpmIndicatorsServer.countRejectedIndicator(jsonParams);
			if(flag) {*/
				JSONObject jsondata = srmSpmIndicatorsServer.updateRejectedIndicator(jsonParams);
				return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"),jsondata.getInteger("count"), jsondata.get("data"));
			/*}else {
				return CommonAbstractServices.convertResultJSONObj("E", "指标模板在引用，无法失效，如果要是失效，请先失效引用模板!", 0, null);
			}*/
		} catch (NotLoginException e) {
			return CommonAbstractServices.convertResultJSONObj("NOTLOGIN", e.getMessage(), 0, null);
		}catch (UtilsException e){
            LOGGER.error("指标失效失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "指标失效失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error("--->>指标失败！参数：" + params + ",异常：" + e);
			return CommonAbstractServices.convertResultJSONObj("E", "指标失效失败!", 0, null);
		}
	}

    /**
     * Description：更新指标状态
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@Path("updateStatusIndicators")
    @POST
    public String updateStatusIndicators(@FormParam("params")String params) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Integer indicatorId = jsonParam.getInteger("indicatorId");
			String type = jsonParam.getString("type");
			if(indicatorId!=null) {
			   boolean flag =srmSpmIndicatorsServer.updateStatusIndicators(indicatorId,type);
			   if(flag) {
				   jsonParam.put("status", "S");
			       jsonParam.put("msg", "数据修改成功！"); 
			   }else {
				   jsonParam.put("status", "E");
			       jsonParam.put("msg", "生效失败，已有有效版本的指标，请先将原指标失效，再生效此指标!");  
			   }
		       return jsonParam.toJSONString();
			}else {
				 return convertResultJSONObj("E", "请检查indicatorId参数", 0, null);
			}
		}catch (Exception e) {
            LOGGER.error("查询失败！", e);
            return convertResultJSONObj("E", "查询失败!" + e, 0, null);
        }
	}


	@POST
	@Path("queryIndicatorsItemList")
	public String queryIndicatorsItemList(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			Pagination<SrmSpmIndicatorItemsEntity_HI_RO> page = srmSpmIndicatorsServer.queryIndicatorsItemList(paramJSON,pageIndex, pageRows);
			return JSON.toJSONString(page);
		} catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}

    /**
     * Description：指标导出
     * @param params
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
	@POST
	@Path("queryIndicatorsExport")
	public String queryIndicatorsExport(@FormParam(PARAMS) String params, @FormParam(PAGE_INDEX) Integer pageIndex,@FormParam(PAGE_ROWS) Integer pageRows) {
		LOGGER.info(params);
		try {
			JSONObject paramJSON = JSON.parseObject(params);
			List<SrmSpmIndicatorsEntity_HI_RO> list =srmSpmIndicatorsServer.queryIndicatorsExport(paramJSON);
			return CommonAbstractServices.convertResultJSONObj("S", "查询成功", list.size(), list);
		}catch (UtilsException e){
            LOGGER.error("查询失败：" + e.getMessage());
            return CommonAbstractServices.convertResultJSONObj("E", "查询失败:" + e.getMessage(), 0, null);
        } catch (Exception e) {
			//e.printStackTrace();
			return JSON.toJSONString(SToolUtils.convertResultJSONObj("E", "查询失败!" + e, 0, null));
		}
	}
	
	

}
