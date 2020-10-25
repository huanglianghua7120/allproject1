package saaf.common.fmw.pos.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.services.CommonAbstractServices;
import saaf.common.fmw.pos.model.inter.ISrmPosLocaleReviewSites;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：现场考察地点
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
@Component("srmPosLocaleReviewSitesService")
@Path("/srmPosLocaleReviewSitesService")
public class SrmPosLocaleReviewSitesService extends CommonAbstractServices{
private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewSitesService.class);
	@Autowired
	private ISrmPosLocaleReviewSites srmPosLocaleReviewSitesServer;
	public SrmPosLocaleReviewSitesService() {
		super();
	}

	/**
	 * 
	 * @param params
	 * @param curIndex
	 * @param pageSize
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	@POST
	@Path("findSrmPosLocaleReviewSitesInfo")
	//	//srmPosLocaleReviewSitesService/findSrmPosLocaleReviewSitesInfo
	public String findSrmPosLocaleReviewSitesInfo(@FormParam("params")
        String params, @FormParam("pageIndex")
        @DefaultValue("1")
        Integer curIndex, @FormParam("pageRows")
        @DefaultValue("10")
        Integer pageSize) throws Exception {
		LOGGER.info(params);
		JSONObject paramJSON = this.parseObject(params);
		String resultStr = JSONObject.toJSONString(srmPosLocaleReviewSitesServer.findSrmPosLocaleReviewSitesInfo(paramJSON));
		LOGGER.info(resultStr);
		return resultStr;
	}

	/**
	 * Description：保存现场考察地点
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * localeReviewSiteId  现场考察地点ID  NUMBER  Y
	 * localeReviewId  现场考察ID，关联表:srm_pos_locale_reviews  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * selectedFlag  勾选标识(Y/N)  VARCHAR2  N
	 * localeReviewSiteId  现场考察地点ID  NUMBER  Y
	 * localeReviewId  现场考察ID，关联表:srm_pos_locale_reviews  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * selectedFlag  勾选标识(Y/N)  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
	@Path("saveLocaleReviewsSites")
	@POST
	public String saveLocaleReviewsSites(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosLocaleReviewSitesServer.saveLocaleReviewsSites(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
		}catch (Exception e) {
			LOGGER.error("保存资质审查的地点信息失败！" + e,e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "保存资质审查的地点信息失败!" + e, 0, null);
		}
	}

	/**
	 * Description：保存现场考察地点
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * localeReviewSiteId  现场考察地点ID  NUMBER  Y
	 * localeReviewId  现场考察ID，关联表:srm_pos_locale_reviews  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * selectedFlag  勾选标识(Y/N)  VARCHAR2  N
	 * localeReviewSiteId  现场考察地点ID  NUMBER  Y
	 * localeReviewId  现场考察ID，关联表:srm_pos_locale_reviews  NUMBER  Y
	 * supplierSiteId  供应商地点ID  NUMBER  N
	 * supplierAddressId  供应商地址ID  NUMBER  N
	 * siteName  地点名称  VARCHAR2  N
	 * orgId  业务实体ID(关联saaf_institution)  NUMBER  N
	 * siteStatus  地点状态(POS_SUPPLIER_SITE_STATUS)  VARCHAR2  N
	 * purchaseFlag  可采购(Y/N)  VARCHAR2  N
	 * paymentFlag  可付款(Y/N)  VARCHAR2  N
	 * frozeFlag  已冻结(Y/N)  VARCHAR2  N
	 * unfrozeDate  解冻时间  DATE  N
	 * qualifiedDate  合格时间  DATE  N
	 * invalidDate  失效时间  DATE  N
	 * temporarySiteFlag  临时地点标识  VARCHAR2  N
	 * selectedFlag  勾选标识(Y/N)  VARCHAR2  N
	 *
	 * Update History
	 * =======================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-06-16     HLH             创建
	 * =======================================================================
	 */
	@Path("saveLocaleReviewsNewSites")
	@POST
	public String saveLocaleReviewsNewSites(@FormParam("params") String params) {
		try {
			JSONObject jsonParam = this.parseObject(params);
			JSONObject posJson = srmPosLocaleReviewSitesServer.saveLocaleReviewsNewSites(jsonParam);
			return CommonAbstractServices.convertResultJSONObj(posJson.getString("status"), posJson.getString("msg"), posJson.getInteger("count"), posJson.get("data"));
		}catch (Exception e) {
			LOGGER.error("保存资质审查的地点信息失败！" + e,e);
			if (e instanceof UtilsException) {
				return CommonAbstractServices.convertResultJSONObj("E",e.getMessage(), 0, null);
			}
			return CommonAbstractServices.convertResultJSONObj("E", "保存资质审查的地点信息失败!" + e, 0, null);
		}
	}
}
