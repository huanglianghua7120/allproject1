package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosQualificationSites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.SrmPosQualificationSitesEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("srmPosQualificationSitesServer")
public class SrmPosQualificationSitesServer implements ISrmPosQualificationSites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosQualificationSitesServer.class);

    @Autowired
    private ViewObject<SrmPosQualificationSitesEntity_HI> srmPosQualificationSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosQualificationSitesEntity_HI_RO> lines2Entity_HI_RO;

    public SrmPosQualificationSitesServer() {
        super();
    }

    /**
     * 根据地址ID和资质审查ID，查询资质审查单下面有效的供应商地点
     *
     * @param jsonParams
     * @return
     * @throws Exception
     */
    @Override
    public List<SrmPosQualificationSitesEntity_HI_RO> findQualificationSiteListByA(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosQualificationSitesEntity_HI_RO.QUERY_SITE_BY_A);
        SaafToolUtils.parperParam(jsonParams, "pqs.supplier_address_id", "supplierAddressId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pqs.qualification_id", "qualificationId", queryString, queryParamMap, "=");
        //过滤业务实体
//        queryString.append(" AND srm_sys_check_access_f(" + jsonParams.getInteger("varUserId") + ", pqs.org_id, null, null, null, null, null, null) = 'Y'");
        List<SrmPosQualificationSitesEntity_HI_RO> rowSet = lines2Entity_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

    /**
     * 根据地址ID和资质审查ID，查询资质审查单下面新增的供应商地点
     *
     * @param jsonParams
     * @return
     * @throws Exception
     */
    @Override
    public List<SrmPosQualificationSitesEntity_HI_RO> findQualificationSiteListByNotA(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosQualificationSitesEntity_HI_RO.QUERY_SITE_BY_NOT_A);
//        if (null != jsonParams.getInteger("sceneType") && 20 != jsonParams.getInteger("sceneType")) {
            queryString.append(" AND nvl(pqs.add_flag, 'N') != 'A'\n");
//        }
        SaafToolUtils.parperParam(jsonParams, "pqs.supplier_address_id", "supplierAddressId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pqs.qualification_id", "qualificationId", queryString, queryParamMap, "=");
//        queryString.append(" AND srm_sys_check_access_f(" + jsonParams.getInteger("varUserId") + ", pqs.org_id, null, null, null, null, null, null) = 'Y'");
        List<SrmPosQualificationSitesEntity_HI_RO> rowSet = lines2Entity_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

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

    @Override
    public JSONObject saveQualificationSites(JSONObject params) throws Exception {
        LOGGER.info("保存资质审查信息，参数：" + params.toString());
        Integer operatorUserId = params.getInteger("varUserId");
        Integer qualificationId = params.getInteger("qualificationId");
        //保存新引入的供应商地点
        JSONArray newSiteArray = params.getJSONArray("newSiteData");
        if (newSiteArray != null) {
            List<SrmPosQualificationSitesEntity_HI> newSiteList1 = JSON.parseArray(newSiteArray.toJSONString(), SrmPosQualificationSitesEntity_HI.class);
            for (SrmPosQualificationSitesEntity_HI effSite1 : newSiteList1) {
                effSite1.setQualificationId(qualificationId);
                effSite1.setOperatorUserId(operatorUserId);
            }
            srmPosQualificationSitesDAO_HI.saveOrUpdateAll(newSiteList1);
        }
        return SToolUtils.convertResultJSONObj("S", "保存资质审查的地点成功", 1, null);
    }

}
