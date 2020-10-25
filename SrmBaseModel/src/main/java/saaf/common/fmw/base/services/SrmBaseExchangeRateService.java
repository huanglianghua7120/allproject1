package saaf.common.fmw.base.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseExchangeRateEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseExchangeRate;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.bean.UserInfoSessionBean;
import saaf.common.fmw.services.CommonAbstractServices;

import java.util.List;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：汇率
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmBaseExchangeRateService")
@Path("/srmBaseExchangeRateService")
public class SrmBaseExchangeRateService extends CommonAbstractServices{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmBaseExchangeRateService.class);
	@Autowired
	private ISrmBaseExchangeRate srmBaseExchangeRateServer;
	public SrmBaseExchangeRateService() {
		super();
	}

	/**
	 * 查询汇率
	 * @param params
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findBaseExchangeRate")
	@POST
	public String findBaseExchangeRate(@FormParam("params") String params,
			@FormParam("pageIndex") Integer pageIndex,
			@FormParam("pageRows") Integer pageRows) {
		try {
			JSONObject jsonParam = JSONObject.parseObject(params);
			Pagination<SrmBaseExchangeRateEntity_HI_RO> noticesList = srmBaseExchangeRateServer.findBaseExchangeRate(jsonParam, pageIndex, pageRows);
			return JSON.toJSONString(noticesList);
		} catch (Exception e) {
			LOGGER.error("查询失败！", e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * 查询汇率类型
	 * @param
	 * @return
	 * Update History
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("findDefaulRateType")
	@POST
	public String findDefaulRateType() {
		try {
			SaafLookupValuesEntity_HI rateTypeEntity = srmBaseExchangeRateServer.findDefaulRateType();
			return JSON.toJSONString(rateTypeEntity);
		} catch (Exception e) {
			LOGGER.error("查询失败！", e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return convertResultJSONObj("E", "查询失败!" + e, 0, null);
		}
	}

	/**
	 * Description：保存汇率
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * exchangeRateId  汇率表ID  NUMBER  Y
	 * exchangeRate  汇率  NUMBER  N
	 * rateType  汇率类型  VARCHAR2  N
	 * originalCurrency  原币种  VARCHAR2  N
	 * targetCurrency    VARCHAR2  N
	 * rateDate    DATE  N
	 * effectiveStartDate    DATE  N
	 * effectiveEndDate  有效结束日期  DATE  N
	 * sourceCode  数据来源  VARCHAR2  N
	 * sourceId  数据来源ID  VARCHAR2  N
	 * exchangeRateId  汇率ID  NUMBER  Y
	 * exchangeRate  汇率  NUMBER  N
	 * rateType  汇率类型  VARCHAR2  N
	 * originalCurrency  原币种  VARCHAR2  N
	 * targetCurrency  目标币种  VARCHAR2  N
	 * effectiveStartDate  有效开始日期  DATE  N
	 * effectiveEndDate  有效结束日期  DATE  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

	@Path("saveBaseExchangeRate")
	@POST
	@Produces("application/json")
	public String saveBaseExchangeRate(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmBaseExchangeRateServer.saveBaseExchangeRate(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(
					posJson.getString("status"), posJson.getString("msg"),
					posJson.getInteger("count"), posJson.get("data"));
		} catch (Exception e) {
			LOGGER.error("保存失败！" + e, e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E",
					"未知错误", 0, null);
		}
	}

	/**
	 * 删除汇率
	 * @param params
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@Path("deleteExchangeRate")
	@POST
	public String deleteExchangeRate(@FormParam("params") String params) {
		LOGGER.info("删除汇率，参数：" + params.toString());
		try {
			JSONObject jsonParams = this.parseObject(params);
			JSONObject jsondata = srmBaseExchangeRateServer.deleteExchangeRate(jsonParams);
			return CommonAbstractServices.convertResultJSONObj(jsondata.getString("status"), jsondata.getString("msg"), jsondata.getInteger("count"), jsondata.get("data"));
		}catch (Exception e) {
			LOGGER.error("--->>删除汇率失败！参数：" + params + ",异常：" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
		}
	}

	/**
	 * Description：批量导入
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * exchangeRateId  汇率表ID  NUMBER  Y
	 * exchangeRate  汇率  NUMBER  N
	 * rateType  汇率类型  VARCHAR2  N
	 * originalCurrency  原币种  VARCHAR2  N
	 * targetCurrency    VARCHAR2  N
	 * rateDate    DATE  N
	 * effectiveStartDate    DATE  N
	 * effectiveEndDate  有效结束日期  DATE  N
	 * sourceCode  数据来源  VARCHAR2  N
	 * sourceId  数据来源ID  VARCHAR2  N
	 * exchangeRateId  汇率ID  NUMBER  Y
	 * exchangeRate  汇率  NUMBER  N
	 * rateType  汇率类型  VARCHAR2  N
	 * originalCurrency  原币种  VARCHAR2  N
	 * targetCurrency  目标币种  VARCHAR2  N
	 * effectiveStartDate  有效开始日期  DATE  N
	 * effectiveEndDate  有效结束日期  DATE  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */

    @POST
    @Path("saveImportExchangeRate")
    public String saveImportExchangeRate(@FormParam(PARAMS) String params){
        UserInfoSessionBean sessionBean = null;
        sessionBean = getUserSessionBean();
        int userId = sessionBean.getUserId();
        try {
            JSONObject jsonParams = this.parseObject(params);
            JSONObject object = srmBaseExchangeRateServer.saveImportExchangeRate(jsonParams,userId);
            return JSONObject.toJSONString(object);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("导入失败" + e);
			if (e instanceof UtilsException) {
				return convertResultJSONObj(ERROR_STATUS, e.getMessage(), 0, null);
			}
            return CommonAbstractServices.convertResultJSONObj("E", "未知错误", 0, null);
        }
    }

}
