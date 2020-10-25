package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;

import com.yhg.hibernate.core.paging.Pagination;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemDeliversEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.base.model.inter.ISrmBaseItemDelivers;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：银行
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseItemDeliversService")
@Path("/srmBaseItemDeliversService")
public class SrmBaseItemDeliversService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseItemDeliversService.class);
	@Autowired
	private ISrmBaseItemDelivers srmBaseItemDeliversServer;
	public SrmBaseItemDeliversService() {
		super();
	}

	/**
	 * 查询物料回货方式
	 * @param params
	 * @param pageIndex
	 * @param pageRows
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findCategoryReturn")
	public String findCategoryReturn(@FormParam("params")String params, @FormParam("pageIndex")Integer pageIndex, @FormParam("pageRows")Integer pageRows) {
		try {
			JSONObject jsonParams = this.parseObject(params);
			Pagination<SrmBaseItemDeliversEntity_HI_RO> pag = srmBaseItemDeliversServer.findCategoryReturn(jsonParams, pageIndex, pageRows);
			LOGGER.info("-->>参数：" + params + "查询成功！");
			System.out.println("----------------:"+JSON.toJSONString(pag));
			return JSON.toJSONString(pag);
		} catch (Exception e) {
			LOGGER.error("查询失败" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * Description：保存物料回货方式
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * itemDeliverId  回货方式ID  NUMBER  Y
	 * categoryId  采购分类ID  NUMBER  Y
	 * itemId  物料ID  NUMBER  N
	 * deliverType  回货类型  VARCHAR2  Y
	 * deliverStatus  状态  VARCHAR2  N
	 * itemDeliverId  回货方式ID  NUMBER  Y
	 * categoryId  采购分类ID  NUMBER  Y
	 * itemId  物料ID  NUMBER  N
	 * deliverType  回货类型  VARCHAR2  Y
	 * deliverStatus  状态  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveCategoryReturn")
	@POST
	public String saveCategoryReturn(@FormParam("params")String params) {
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();
		try {
			JSONArray jsonParam = JSONArray.parseArray(params);
			JSONObject posJson = srmBaseItemDeliversServer.saveCategoryReturn(jsonParam,userId);
			return CommonAbstractServices.convertResultJSONObj(posJson.getString(STATUS),posJson.getString(MSG),posJson.getInteger(COUNT),posJson.getString(DATA));
		}catch (Exception e) {
			LOGGER.error("保存品分工失败！" + e,e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "保存品分工失败!" + e, 0, null);
		}
	}

	/**
	 * 批量删除物料回货方式
	 * @param params
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("deleteCategoryReturn")
	public String deleteCategoryReturn(@FormParam(PARAMS) String params){
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONArray jsonArray = jsonParams.getJSONArray("array");
			JSONObject object = srmBaseItemDeliversServer.deleteCategoryReturn(jsonArray);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
//			//e.printStackTrace();
			LOGGER.error("删除失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
		}
	}

    /**
     * 批量生效
     * @param params
     * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    @POST
    @Path("updateEffectCategoryReturn")
    @Produces("application/json")
    public String updateEffectCategoryReturn(@FormParam(PARAMS)String params){
        LOGGER.info("生效品类分工信息，参数为："+params.toString());
        if(StringUtils.isBlank(params)){
            return CommonAbstractServices.convertResultJSONObj(ERROR_STATUS,"未知错误",0,null);
        }
        try{
            JSONObject jsonParams = this.parseObject(params);
            JSONObject jsondata = srmBaseItemDeliversServer.updateEffectCategoryReturn(jsonParams);
            return CommonAbstractServices.convertResultJSONObj(jsondata.getString(STATUS),jsondata.getString(MSG),jsondata.getInteger(COUNT),jsondata.getString(DATA));
        }catch (Exception e){
//            //e.printStackTrace();
            LOGGER.error("------------------->生效物料回货方式失败！参数："+params.toString()+"，异常："+e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
            return convertResultJSONObj(ERROR_STATUS,"生效物料回货方式失败！",0,null);
        }
    }
    /**
     * 批量失效
     * @param params
     * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
    @POST
    @Path("updateInvalidCategoryReturn")
    public String updateInvalidCategoryReturn(@FormParam(PARAMS) String params){
        UserInfoSessionBean sessionBean = null;
        sessionBean = getUserSessionBean();
        int userId = sessionBean.getUserId();
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONArray jsonArray = jsonParams.getJSONArray("array");
            JSONObject object = srmBaseItemDeliversServer.updateInvalidCategoryReturn(jsonArray,userId);
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
//            //e.printStackTrace();
            LOGGER.error("失效物料回货方式失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
            return CommonAbstractServices.convertResultJSONObj("E",  "未知错误", 0, null);
        }
    }

	/**
	 * Description：导入物料回货方式
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * consignmentFlag  是否寄售(Y/N)  VARCHAR2  N
	 * itemId  物料ID  NUMBER  Y
	 * itemCode  物料编码  VARCHAR2  Y
	 * itemName  物料名称  VARCHAR2  Y
	 * itemDescription  物料说明  VARCHAR2  N
	 * globalFlag  全局标识  VARCHAR2  N
	 * enabledAsl  启用ASL  VARCHAR2  N
	 * organizationId  库存组织ID  NUMBER  Y
	 * uomCode  计量单位  VARCHAR2  N
	 * itemStatus  物料状态  VARCHAR2  N
	 * purchasingFlag  可采购标识  VARCHAR2  N
	 * agentId  采购员ID  NUMBER  N
	 * categoryId  采购分类ID  NUMBER  N
	 * purchaseCycle  采购周期  VARCHAR2  N
	 * purchasingLeadTime  采购提前期  NUMBER  N
	 * minPacking  最小包装量  NUMBER  N
	 * benchmarkPrice  基准价  NUMBER  N
	 * imageId  图片ID  NUMBER  N
	 * invalidDate  失效时间  DATE  N
	 * sourceCode  数据来源  VARCHAR2  N
	 * sourceId  数据来源ID  VARCHAR2  N
	 * minPoQuantity  最小采购量  NUMBER  N
	 * purchaseStatus    VARCHAR2  N
	 * modelCode  型号编码  VARCHAR2  N
	 * modelName  物料型号  VARCHAR2  N
	 * modelStorageDuration    DATE  N
	 * temperatureUpper    NUMBER  N
	 * temperatureLower    NUMBER  N
	 * humidityUpper    NUMBER  N
	 * humidityLower    NUMBER  N
	 * ulType    VARCHAR2  N
	 * copper    VARCHAR2  N
	 * sizec    NUMBER  N
	 * ppLength    NUMBER  N
	 * realitySize    NUMBER  N
	 * moqc    NUMBER  N
	 * itemRank    VARCHAR2  N
	 * conversionRatio  转换比例  VARCHAR2  N
	 * inventoryCode  库存单位  VARCHAR2  N
	 * isTest  是否检验  VARCHAR2  N
	 * isTighten  是否加严  VARCHAR2  N
	 * itemPlanWay  物料计划方式  VARCHAR2  N
	 * copperFoilType  铜箔类型  VARCHAR2  N
	 * standardSize  标准尺寸  VARCHAR2  N
	 * moqOrderQuantity  MOQ（起订量）  NUMBER  N
	 * itemId  物料ID  NUMBER  Y
	 * itemCode  物料编码  VARCHAR2  Y
	 * itemName  物料名称  VARCHAR2  Y
	 * itemDescription  物料说明  VARCHAR2  N
	 * globalFlag  全局标识  VARCHAR2  N
	 * enabledAsl  启用ASL  VARCHAR2  N
	 * organizationId  库存组织ID  NUMBER  Y
	 * uomCode  计量单位  VARCHAR2  N
	 * itemStatus  物料状态  VARCHAR2  N
	 * purchasingFlag  可采购标识  VARCHAR2  N
	 * agentId  采购员ID  NUMBER  N
	 * categoryId  采购分类ID  NUMBER  N
	 * purchaseCycle  采购周期  VARCHAR2  N
	 * purchasingLeadTime  采购提前期  NUMBER  N
	 * minPacking  最小包装量  NUMBER  N
	 * benchmarkPrice  基准价  NUMBER  N
	 * imageId  图片ID  NUMBER  N
	 * invalidDate  失效时间  DATE  N
	 * sourceCode  数据来源  VARCHAR2  N
	 * sourceId  数据来源ID  VARCHAR2  N
	 * cost  成本  NUMBER  N
	 * specification  规格型号  VARCHAR2  N
	 * region  组织区域  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@POST
	@Path("batchImportCategoryReturn")
	public String saveBatchImportCategoryReturn(@FormParam(PARAMS) String params){
		UserInfoSessionBean sessionBean = null;
		sessionBean = getUserSessionBean();
		int userId = sessionBean.getUserId();
		try {
			JSONObject jsonParams = this.parseObject(params);
			System.out.print(jsonParams.toJSONString());
			JSONArray jsonArray = jsonParams.getJSONArray("data");
			JSONObject object = srmBaseItemDeliversServer.saveBatchImportCategoryReturn(jsonArray,userId);
			return JSONObject.toJSONString(object);
		} catch (Exception e) {
//			//e.printStackTrace();
			LOGGER.error("导入失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}
}
