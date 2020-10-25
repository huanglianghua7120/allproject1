package saaf.common.fmw.pos.model.inter;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationSitesEntity_HI_RO;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商资质
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     zhj             创建
 * ===========================================================================
 */
public interface ISrmPosQualificationSites {

    /**
     * 根据地址ID和资质审查ID，查询资质审查单下面有效的供应商地点
     * @param jsonParams
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    List<SrmPosQualificationSitesEntity_HI_RO> findQualificationSiteListByA(JSONObject jsonParams) throws Exception;

    /**
     * 根据地址ID和资质审查ID，查询资质审查单下面新增的供应商地点
     * @param jsonParams
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     zhj             创建
     * ===========================================================================
     */
    List<SrmPosQualificationSitesEntity_HI_RO> findQualificationSiteListByNotA(JSONObject jsonParams) throws Exception;

    /**
     * Description：保存资质审查的地点信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * qualificationSiteId  资质审查地点ID  NUMBER  Y
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  Y
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
     * addFlag  新增标识(Y/N)  VARCHAR2  N
     * qualificationSiteId  资质审查地点ID  NUMBER  Y
     * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  Y
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
     * addFlag  新增标识(Y/N)  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    JSONObject saveQualificationSites(JSONObject params) throws Exception;

}
