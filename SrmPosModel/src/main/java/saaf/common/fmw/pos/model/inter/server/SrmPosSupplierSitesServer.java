package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafInstitution;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosFrozenSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierSites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商地点
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierSitesServer")
public class SrmPosSupplierSitesServer implements ISrmPosSupplierSites {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosSupplierSitesServer.class);

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierSitesEntity_HI_RO> srmPosSupplierSitesDAO_HI_RO;

    @Autowired
    private ISaafInstitution iSaafInstitution;//组织

    public SrmPosSupplierSitesServer() {
        super();
    }

    /**
     * 保存供应商的地点——供应商接口（数据输入）
     *
     * @param supplierSitesList
     * @param supplierAddressId
     * @param userId
     * @param supplierId
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void saveSupplierSitesExternal(List<SrmPosSupplierSitesEntity_HI> supplierSitesList, Integer supplierAddressId, Integer userId, Integer supplierId) {
        List<SrmPosSupplierSitesEntity_HI> newSupplierSitesList = new ArrayList<>();
        if (null != supplierSitesList && supplierSitesList.size() > 0) {
            for (SrmPosSupplierSitesEntity_HI k : supplierSitesList) {
                SrmPosSupplierSitesEntity_HI findRow = null;
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("sourceCode", k.getSourceCode());
                jsonParams.put("sourceId", k.getSourceId());
                jsonParams.put("supplierId", supplierId);
                jsonParams.put("supplierAddressId", supplierAddressId);
                List<SrmPosSupplierSitesEntity_HI_RO> listRO = findSupplierSites(jsonParams);
                if (null != listRO && listRO.size() > 0) {
                    SrmPosSupplierSitesEntity_HI_RO findRO = listRO.get(0);
                    findRow = srmPosSupplierSitesDAO_HI.getById(findRO.getSupplierSiteId());
                }
                if (null != findRow) {
                    findRow.setSiteName(k.getSiteName());
                    findRow.setInstName(k.getInstName());
                    findRow.setSiteStatus(k.getSiteStatus());
                    findRow.setPurchaseFlag(k.getPurchaseFlag());
                    findRow.setPaymentFlag(k.getPaymentFlag());
                    findRow.setFrozeFlag(k.getFrozeFlag());
                    findRow.setUnfrozeDate(k.getUnfrozeDate());
                    findRow.setQualifiedDate(k.getQualifiedDate());
                    findRow.setInvalidDate(k.getInvalidDate());
                    findRow.setTemporarySiteFlag(k.getTemporarySiteFlag());
                    findRow.setOrgId(k.getOrgId());
                    if (!(k.getInstName() == null || "".equals(k.getInstName()))) {
                        JSONObject jsonParamsInstName = new JSONObject();
                        jsonParamsInstName.put("inst_Name", k.getInstName());
                        jsonParamsInstName.put("instType", "ORG");
                        Pagination<SaafInstitutionEntity_HI_RO> institutionEntityListPagination = null;
                        try {
                            institutionEntityListPagination = iSaafInstitution.findInstitutionListLov(jsonParamsInstName, 1, 9999);
                        } catch (Exception e) {
                            LOGGER.error("业务实体查询出错！");
                        }
                        if (null != institutionEntityListPagination) {
                            List<SaafInstitutionEntity_HI_RO> institutionEntityList = institutionEntityListPagination.getData();
                            if (null != institutionEntityList && institutionEntityList.size() > 0) {
                                SaafInstitutionEntity_HI_RO entityHi = institutionEntityList.get(0);
                                findRow.setOrgId(entityHi.getInstId());  //业务实体Id
                            }
                        }
                    }
                    findRow.setSupplierId(supplierId);
                    findRow.setSupplierAddressId(supplierAddressId);
                    findRow.setOperatorUserId(userId);
                    newSupplierSitesList.add(findRow);
                } else {
                    if (!(k.getInstName() == null || "".equals(k.getInstName()))) {
                        JSONObject jsonParamsInstName = new JSONObject();
                        jsonParamsInstName.put("inst_Name", k.getInstName());
                        jsonParamsInstName.put("instType", "ORG");
                        Pagination<SaafInstitutionEntity_HI_RO> institutionEntityListPagination = null;
                        try {
                            institutionEntityListPagination = iSaafInstitution.findInstitutionListLov(jsonParamsInstName, 1, 9999);
                        } catch (Exception e) {
                            LOGGER.error("业务实体查询出错！");
                        }
                        if (null != institutionEntityListPagination) {
                            List<SaafInstitutionEntity_HI_RO> institutionEntityList = institutionEntityListPagination.getData();
                            if (null != institutionEntityList && institutionEntityList.size() > 0) {
                                SaafInstitutionEntity_HI_RO entityHi = institutionEntityList.get(0);
                                findRow.setOrgId(entityHi.getInstId());  //业务实体Id
                            }
                        }
                    }
                    k.setSupplierId(supplierId);
                    k.setSupplierAddressId(supplierAddressId);
                    k.setOperatorUserId(userId);
                    newSupplierSitesList.add(k);
                }
            }
            srmPosSupplierSitesDAO_HI.saveOrUpdateAll(newSupplierSitesList);
        }
    }

    /**
     * 校验供应商的地点必填项——供应商接口（数据输入）
     *
     * @param supplierSitesList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public String judgeSpaceSupplierSites(List<SrmPosSupplierSitesEntity_HI> supplierSitesList) {
        String result = "";
        if (null == supplierSitesList || supplierSitesList.size() <= 0) {
            return result;
        }
        Integer index = 0;
        for (SrmPosSupplierSitesEntity_HI k : supplierSitesList) {
            index++;
            if (null == k.getSourceId() || "".equals(k.getSourceId())) {
                result += "地点的" + "第" + index + "行的数据来源Id——sourceId";
                return result;
            }
            if (null == k.getSourceCode() || "".equals(k.getSourceCode())) {
                result += "地点的" + "第" + index + "行的数据来源类型Code——sourceCode";
                return result;
            }
            if (null == k.getSiteName() || "".equals(k.getSiteName())) {
                result += "地点的" + "第" + index + "行的必填项——地点名称";
                return result;
            }
            if (null == k.getInstName() || "".equals(k.getInstName())) {
                result += "地点的" + "第" + index + "行的必填项——业务实体";
                return result;
            }
        }
        return result;
    }

    /**
     * 查询配置文件行
     *
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSites(JSONObject jsonParam) {
        Map<String, Object> parameterMap = SToolUtils.fastJsonObj2Map(jsonParam);
        String addressId = String.valueOf(parameterMap.get("supplierAddressId"));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES);
        SaafToolUtils.parperParam(jsonParam, "A.supplier_id", "supplier_id", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParam, "A.supplier_id", "supplierId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParam, "A.source_code", "sourceCode", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParam, "A.source_id", "sourceId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParam, "A.site_status", "siteStatus", sb, queryParamMap, "=");
        /*if (addressId != null && !"".equals(addressId.trim())) {
            Integer addId = Integer.parseInt(addressId);
            sb.append(" AND A.supplier_address_id = " + addId);
        }*/
        if (addressId != null && !"".equals(addressId.trim()) && !"null".equals(addressId.trim())) {
            sb.append(" AND A.supplier_address_id = " + addressId + " ");
        }
        List<SrmPosSupplierSitesEntity_HI_RO> result = srmPosSupplierSitesDAO_HI_RO.findList(sb.toString(), queryParamMap);
        return result;
    }
    /**
     * 查询供应商地点
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSrmPosSupplierSites(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        Map<String, Object> frozenMap = new HashMap();
        StringBuffer supperlierSite = new StringBuffer();
        StringBuffer frozenSite = new StringBuffer();
        String siteFlag = jsonParams.getString("siteFlag");
        List<SrmPosSupplierSitesEntity_HI_RO> rowSet = null;
        frozenSite.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_FROZEN_SITES_LIST);
        SaafToolUtils.parperParam(jsonParams, "a.frozen_id", "frozenId", frozenSite, frozenMap, "=");
        SaafToolUtils.parperParam(jsonParams, "d.supplier_address_id", "supplierAddressId", frozenSite, frozenMap, "=");
        //
        supperlierSite.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES_LIST);
        SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", supperlierSite, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.supplier_address_id", "supplierAddressId", supperlierSite, queryParamMap, "=");
        if ("add".equals(siteFlag)) {
            supperlierSite.append(" AND (a.froze_flag IS NULL || a.froze_flag = :frozeFlag)");
            queryParamMap.put("frozeFlag", "N");
            rowSet = srmPosSupplierSitesDAO_HI_RO.findList(supperlierSite.toString(), queryParamMap);
        } else {
            rowSet = srmPosSupplierSitesDAO_HI_RO.findList(frozenSite.toString(), frozenMap);
            /**冻结地点：有取冻结表数据，没有取供应商地点数据*/
            if (0 == rowSet.size()) {
                rowSet = srmPosSupplierSitesDAO_HI_RO.findList(supperlierSite.toString(), queryParamMap);
            }
        }
        return rowSet;
    }
    /**
     * 查询供应商地点
     *
     * @param params
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSites(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer supperlierSite = new StringBuffer();
        String siteFlag = jsonParams.getString("siteFlag");
        List<SrmPosSupplierSitesEntity_HI_RO> rowSet = null;
        supperlierSite.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES_LIST);
        SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", supperlierSite, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.supplier_address_id", "supplierAddressId", supperlierSite, queryParamMap, "=");
        /**查询判断*/
        StringBuffer addSite = new StringBuffer();
        Map<String, Object> addMap = new HashMap<String, Object>();
        addSite.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES_LIST);
        SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", addSite, addMap, "=");
        SaafToolUtils.parperParam(jsonParams, "a.supplier_address_id", "supplierAddressId", addSite, addMap, "=");
        addSite.append(" AND check_org_f(" + jsonParams.getInteger("varUserId") + ", a.org_id) = 'Y'");    //过滤业务实体
        if (jsonParams.getString("flag") != null && "recovery".equals(jsonParams.getString("flag"))) {
            addSite.append(" AND (a.site_status ='EFFECTIVE') AND a.froze_flag = 'Y'\n");
        }else{
            //冻结单据只显示未冻结地点
            addSite.append(" AND (a.site_status ='EFFECTIVE') AND (a.froze_flag = 'N' OR a.froze_flag IS NULL)\n");
        }
        if ("add".equals(siteFlag)) {
            //只能操作合格的供应商地点
            //addSite.append(" AND (a.site_status = 'EFFECTIVE' OR a.site_status = 'INTRODUCING')\n");
            addSite.append(" AND (a.site_status = 'EFFECTIVE')\n");
            addSite.append(" AND (a.froze_flag IS NULL OR a.froze_flag = '' OR a.froze_flag = :frozeFlag)\n");
            addMap.put("frozeFlag", "Y");
        }
        rowSet = srmPosSupplierSitesDAO_HI_RO.findList(addSite.toString(), addMap);
        //if(!"add".equals(siteFlag)){
        if (jsonParams.getInteger("frozenId") != null && jsonParams.getInteger("frozenId") > 0) {
            StringBuffer hqlStr = new StringBuffer();
            Map<String, Object> aaa = new HashMap();
            hqlStr.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES_LIST_EDIT);
            SaafToolUtils.parperParam(jsonParams, "d.frozen_id", "frozenId", hqlStr, aaa, "=");
            SaafToolUtils.parperParam(jsonParams, "a.supplier_address_id", "supplierAddressId", hqlStr, aaa, "=");
            //冻结只冻结合格状态的地点
            hqlStr.append(" AND (a.site_status = 'EFFECTIVE')\r\n ");
            //过滤业务实体
            hqlStr.append(" AND check_org_f(" + jsonParams.getInteger("varUserId") + ", a.org_id) = 'Y'\n");
//            hqlStr.append(" \t GROUP BY a.supplier_site_id \r\n ");
            List<SrmPosSupplierSitesEntity_HI_RO> list = srmPosSupplierSitesDAO_HI_RO.findList(hqlStr, aaa);
            rowSet = list;
        }
        return rowSet;
    }
    /**
     * 根据供应商ID，查询有效的供应商地点
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findQuitSites(JSONObject jsonParams) {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_QUIT_SITES);
        SaafToolUtils.parperParam(jsonParams, "b.supplier_id", "supplierId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "b.supplier_address_id", "supplierAddressId", queryString, queryParamMap, "=");
        queryString.append("   AND check_org_f(" + jsonParams.getInteger("varUserId") + ", b.org_id) = 'Y'");    //过滤业务实体
        List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

    /**
     * 根据供应商ID，查询有效的供应商地点
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSiteListByEffective(JSONObject jsonParams) {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
//        Integer userId = jsonParams.getInteger("varUserId");
//        if (userId == null) {
//            userId = -1;
//        }
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SITES_BY_STATUS);
//        if (userId != null) {
//            queryString.append(" AND check_org_f (" + userId + ", pss.org_id) = 'Y' ");
//        }
        SaafToolUtils.parperParam(jsonParams, "pss.supplier_id", "supplierId", queryString, queryParamMap, "=");
        List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

    /**
     * 根据供应商ID查询地点LOV
     *
     * @param
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SITES_BY_STATUS);
        SaafToolUtils.parperParam(jsonParams, "pss.supplier_id", "supplierId", queryString, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "pss.site_name", "siteName", queryString, queryParamMap, "like");
        String countSql = "select count(1) from (" + queryString + ")";
        Pagination<SrmPosSupplierSitesEntity_HI_RO> rowSet =
                srmPosSupplierSitesDAO_HI_RO.findPagination(queryString.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return rowSet;
    }

    /**
     * 根据供应商ID，查询可新增的地点
     *
     * @param jsonParams
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSiteListByNew(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Integer userId = jsonParams.getInteger("varUserId");
        if (userId == null) {
            userId = -1;
        }
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryString = new StringBuffer();
        queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SITES_BY_NEW);
        if (userId != null) {
            queryString.append(" AND check_org_f (" + userId + ", pst.org_id) = 'Y' ");
        }
        SaafToolUtils.parperParam(jsonParams, "pst.supplier_id", "supplierId", queryString, queryParamMap, "=");
        List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return rowSet;
    }

    /**
     * 查询供应商地点,本方法属于现场考察
     *
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteOfReview(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SITES_OF_REVIEW);
            SaafToolUtils.parperParam(jsonParams, "qs.qualification_id", "qualificationId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t1.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "t1.supplier_address_id", "supplierAddressId", queryString, queryParamMap, "=");
            queryParamMap.put("localeReviewId", jsonParams.getInteger("localeReviewId"));
            queryString.append(" AND check_org_f(" + jsonParams.getInteger("varUserId") + ", t1.org_id) = 'Y'");    //过滤业务实体
            //queryString.append(" GROUP BY t1.supplier_site_id \n");
            List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 送货通知查询供应商地点
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierSiteForNotice(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SITES_OF_NOTICE);
            SaafToolUtils.parperParam(jsonParams, "a.supplier_id", "supplierId", queryString, queryParamMap, "=");
            SaafToolUtils.parperParam(jsonParams, "a.org_id", "orgId", queryString, queryParamMap, "=");
            List<SrmPosSupplierSitesEntity_HI_RO> rowSet = srmPosSupplierSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查找计划考察的地点
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosSupplierSitesEntity_HI_RO> findSupplierPlanSites(JSONObject jsonParam) {
        Map<String, Object> parameterMap = SToolUtils.fastJsonObj2Map(jsonParam);
        String addressId = String.valueOf(parameterMap.get("supplierAddressId"));
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_PLAN_SITES);
        SaafToolUtils.parperParam(jsonParam, "t1.supplier_id", "supplierId", sb, queryParamMap, "=");//如果是供应商查询
        SaafToolUtils.parperParam(jsonParam, "ips.investigation_plan_lines_id", "investigationPlanLinesId", sb, queryParamMap, "=");//如果是供应商查询

        if (addressId != null && !"".equals(addressId.trim())) {
            Integer addId = Integer.parseInt(addressId);
            sb.append("\n\t AND t1.supplier_address_id = " + addId + " \n");
        }
        sb.append(" \tGROUP BY t1.supplier_site_id \n");
        List<SrmPosSupplierSitesEntity_HI_RO> result = srmPosSupplierSitesDAO_HI_RO.findList(sb.toString(), queryParamMap);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setSelectedFlag("Y");
        }
        return result;
    }

}
