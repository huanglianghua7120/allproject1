package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.paging.Pagination;
import java.util.List;
import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewSitesEntity_HI;
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
public interface ISrmPosLocaleReviewSites {
	/**
	 *
	 * @param queryParamJSON
	 * @return
	 * ==============================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2020-04-15     hgq             创建
	 * ==============================================================================
	 */
	List<SrmPosLocaleReviewSitesEntity_HI> findSrmPosLocaleReviewSitesInfo(JSONObject queryParamJSON);

	Object saveSrmPosLocaleReviewSitesInfo(JSONObject queryParamJSON);

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
	public JSONObject saveLocaleReviewsSites(JSONObject params) throws Exception;

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
	public JSONObject saveLocaleReviewsNewSites(JSONObject params) throws Exception;
}
