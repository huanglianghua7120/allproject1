package saaf.common.fmw.pos.model.inter.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.ViewObject;

import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosQualificationSitesEntity_HI;
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
@Component("srmPosLocaleReviewSitesServer")
public class SrmPosLocaleReviewSitesServer implements ISrmPosLocaleReviewSites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewSitesServer.class);

    @Autowired
    private ViewObject<SrmPosLocaleReviewSitesEntity_HI> srmPosLocaleReviewSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosQualificationSitesEntity_HI> srmPosQualificationSitesDAO_HI;

    public SrmPosLocaleReviewSitesServer() {
        super();
    }
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
    public List<SrmPosLocaleReviewSitesEntity_HI> findSrmPosLocaleReviewSitesInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmPosLocaleReviewSitesEntity_HI> findListResult = srmPosLocaleReviewSitesDAO_HI.findList("from SrmPosLocaleReviewSitesEntity_HI", queryParamMap);
        return findListResult;
    }

    public Object saveSrmPosLocaleReviewSitesInfo(JSONObject queryParamJSON) {
        SrmPosLocaleReviewSitesEntity_HI srmPosLocaleReviewSitesEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmPosLocaleReviewSitesEntity_HI.class);
        Object resultData = srmPosLocaleReviewSitesDAO_HI.save(srmPosLocaleReviewSitesEntity_HI);
        return resultData;
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
    public JSONObject saveLocaleReviewsSites(JSONObject params) throws Exception {
        LOGGER.info("保存现场考察地点信息，参数：" + params.toString());
        Integer operatorUserId = params.getInteger("varUserId");
        Integer localeReviewId = params.getInteger("localeReviewId");
        //保存新引入的供应商地点
        JSONArray siteArray = params.getJSONArray("siteData");
        if (siteArray != null) {
            List<SrmPosLocaleReviewSitesEntity_HI> newSiteList = new ArrayList<>();
            for (int i = 0; i < siteArray.size(); i++) {
                SrmPosLocaleReviewSitesEntity_HI newSite = srmPosLocaleReviewSitesDAO_HI.getById(siteArray.getJSONObject(i).getInteger("localeReviewSiteId"));
                if (null == newSite) {
                    newSite = new SrmPosLocaleReviewSitesEntity_HI();
                }
                newSite.setLocaleReviewId(localeReviewId);
                newSite.setSupplierSiteId(siteArray.getJSONObject(i).getInteger("supplierSiteId"));
                newSite.setSupplierAddressId(siteArray.getJSONObject(i).getInteger("supplierAddressId"));
                newSite.setSiteName(siteArray.getJSONObject(i).getString("siteName"));
                newSite.setOrgId(siteArray.getJSONObject(i).getInteger("orgId"));
                newSite.setSiteStatus(siteArray.getJSONObject(i).getString("siteStatus"));
                newSite.setSelectedFlag(siteArray.getJSONObject(i).getString("selectedFlag"));
                newSite.setOperatorUserId(operatorUserId);
                newSiteList.add(newSite);
            }
            srmPosLocaleReviewSitesDAO_HI.saveOrUpdateAll(newSiteList);
        }
        return SToolUtils.convertResultJSONObj("S", "保存现场考察地点信息", 1, null);
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
    public JSONObject saveLocaleReviewsNewSites(JSONObject params) throws Exception {
        LOGGER.info("保存现场考察地点信息，参数：" + params.toString());
        Integer operatorUserId = params.getInteger("varUserId");
        Integer localeReviewId = params.getInteger("localeReviewId");
        //保存新引入的供应商地点
        List<SrmPosLocaleReviewSitesEntity_HI> checkSites = srmPosLocaleReviewSitesDAO_HI.findByProperty("localeReviewId", localeReviewId);
        if (checkSites.size() == 0) {
            Map<String, Object> querySitesMap = new HashMap<>();
            querySitesMap.put("qualificationId", params.getInteger("qualificationId"));
            querySitesMap.put("addFlag", "Y");
            List<SrmPosQualificationSitesEntity_HI> qualificationSites = srmPosQualificationSitesDAO_HI.findByProperty(querySitesMap);
            if (qualificationSites.size() > 0) {
                List<SrmPosLocaleReviewSitesEntity_HI> newSiteList = new ArrayList<>();
                for (SrmPosQualificationSitesEntity_HI qualificationSite : qualificationSites) {
                    SrmPosLocaleReviewSitesEntity_HI newSite = new SrmPosLocaleReviewSitesEntity_HI();
                    newSite.setLocaleReviewId(localeReviewId);
                    newSite.setSupplierSiteId(qualificationSite.getSupplierSiteId());
                    newSite.setSupplierAddressId(qualificationSite.getSupplierAddressId());
                    newSite.setSiteName(qualificationSite.getSiteName());
                    newSite.setOrgId(qualificationSite.getOrgId());
                    newSite.setSiteStatus(qualificationSite.getSiteStatus());
                    newSite.setOperatorUserId(operatorUserId);
                    newSiteList.add(newSite);
                }
                srmPosLocaleReviewSitesDAO_HI.saveOrUpdateAll(newSiteList);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "保存现场考察地点信息", 1, null);
    }
}
