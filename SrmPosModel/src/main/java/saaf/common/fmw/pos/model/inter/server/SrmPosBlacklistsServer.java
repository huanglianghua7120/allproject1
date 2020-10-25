package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.SrmPosBlacklistsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierInfoEntity_HI;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosBlacklistsEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IBlacklists;

import java.util.*;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商黑名单
 * <p>
 * Update History
 * ===========================================================================
 * Version    Date           Updated By     Description
 * -------    -----------    -----------    ---------------
 * V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
@Component("srmPosBlacklistsServer")
public class SrmPosBlacklistsServer implements IBlacklists {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosBlacklistsServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPosBlacklistsEntity_HI_RO> srmPosBlacklistsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosBlacklistsEntity_HI> srmPosBlacklistsDAO_HI;

    @Autowired
    private ViewObject<SrmPosReasonsEntity_HI> srmPosReasonsDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    /**
     * Description：提交黑名单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public Pagination<SrmPosBlacklistsEntity_HI_RO> findBlacklistsList(
            JSONObject parameters, Integer pageIndex, Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosBlacklistsEntity_HI_RO.QUERY_BLACK_SQL);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "b.blacklist_number", "blacklistNumber", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "u.user_full_name", "userFullName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "b.blacklist_status", "blacklistStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "i.supplier_name ", "supplierName", queryString, map, "LIKE");
            String creationDateFrom = parameters.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(b.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = parameters.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(b.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY b.creation_date DESC");
            Pagination<SrmPosBlacklistsEntity_HI_RO> frozenList =
                    srmPosBlacklistsDAO_HI_RO.findPagination(queryString, countSql, map, pageIndex, pageRows);
            return frozenList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 清除黑名单Job
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public void updateBlacklistsJob(JSONObject parameters) throws Exception {
        List<SrmPosBlacklistsEntity_HI> blackList = srmPosBlacklistsDAO_HI.findList("FROM SrmPosBlacklistsEntity_HI WHERE blacklistStatus = 'APPROVED' AND " +
                "relieveDate < SYSDATE AND ( permanentFlag <> 'Y' OR permanentFlag is null");
        List<SrmPosBlacklistsEntity_HI> updateBlackList = new ArrayList<>();
        for (SrmPosBlacklistsEntity_HI blackRow : blackList) {
            blackRow.setBlacklistStatus("UNEFFICACY");
            updateBlackList.add(blackRow);
        }
        srmPosBlacklistsDAO_HI.saveAll(updateBlackList);
    }


    /**
     * Description：保存黑名单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveBlacklists(JSONObject params) throws Exception {
        LOGGER.info("保存冻结信息，参数：" + params.toString());
        SrmPosBlacklistsEntity_HI blackRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            if (params.get("supplierId") == null) {
                JSONObject result = SToolUtils.convertResultJSONObj
                        ("E", "供应商为空，无法保存", 0, null);
                return result;
            }
            // 保存信息判断
            if (null == params.getInteger("blacklistId")) {
                blackRow = new SrmPosBlacklistsEntity_HI();
                String blacklistNumber = saafSequencesUtil.getDocSequences("SRM_POS_BLACKLISTS", "HMD-", dateFromate, 4);
                blackRow.setSupplierId(params.getInteger("supplierId"));
                blackRow.setBlacklistNumber(blacklistNumber);
            } else {
                // 判断存在就是修改
                blackRow = srmPosBlacklistsDAO_HI.findByProperty("blacklistId", params.getInteger("blacklistId")).get(0);
            }

            blackRow.setBlacklistFileId(params.getInteger("blacklistFileId"));
            blackRow.setDescription(params.getString("description"));
            blackRow.setPermanentFlag(params.getString("permanentFlag"));
            blackRow.setRelieveDate(params.getDate("relieveDate"));
            blackRow.setBlacklistStatus("DRAFT");
            blackRow.setOperatorUserId(operatorUserId);
            srmPosBlacklistsDAO_HI.saveOrUpdate(blackRow);

            LOGGER.info("getBlacklistId：" + blackRow.getBlacklistId());

            List<SrmPosReasonsEntity_HI> reasonList = new ArrayList<SrmPosReasonsEntity_HI>();
            JSONArray valuesArray = params.getJSONArray("lineData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                SrmPosReasonsEntity_HI reasonRow = null;
                // 保存原因判断
                if (valuesJson.getInteger("reasonsId") == null) {
                    reasonRow = new SrmPosReasonsEntity_HI();
                    reasonRow.setDocId(blackRow.getBlacklistId());
                } else {
                    // 判断存在就是修改
                    reasonRow = srmPosReasonsDAO_HI.getById(valuesJson.getInteger("reasonsId"));
                }

                reasonRow.setReasonCode(SToolUtils.object2String(valuesJson.get("reasonCode")));
                reasonRow.setReasonDescription(SToolUtils.object2String(valuesJson.get("reasonDescription")));
                reasonRow.setSelectedFlag(SToolUtils.object2String(valuesJson.get("selectedFlag")));
                reasonRow.setDocId(blackRow.getBlacklistId());
                reasonRow.setDocTable("SRM_POS_BLACKLISTS");
                reasonRow.setOperatorUserId(operatorUserId);
                reasonList.add(reasonRow);
            }
            srmPosReasonsDAO_HI.saveOrUpdateAll(reasonList);

            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, blackRow);
            result.put("getBlacklistId", blackRow.getBlacklistId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 删除黑名单
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteBlacklists(JSONObject params) throws Exception {
        LOGGER.info("删除参数：" + params.toString());
        SrmPosBlacklistsEntity_HI blackRow = null;
        try {
            List<SrmPosBlacklistsEntity_HI> blackList = srmPosBlacklistsDAO_HI.findByProperty("blacklistId", params.getInteger("blacklistId"));
            if ("DRAFT".equals(blackList.get(0).getBlacklistStatus()) || "REJECTED".equals(blackList.get(0).getBlacklistStatus())) {
                if (blackList != null && blackList.size() > 0) {
                    blackRow = blackList.get(0);
                    srmPosBlacklistsDAO_HI.delete(blackRow);
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("blacklistId") + "不存在", 0,
                            null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败,只能删除拟定和驳回的单据!", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 根据id查询黑名单
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosBlacklistsEntity_HI_RO> findBlacklists(Integer blacklistId) throws Exception {
        LOGGER.info("根据id查询信息，参数：" + blacklistId);
        try {
            String sql = SrmPosBlacklistsEntity_HI_RO.QUERY_BLACK_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            queryString.append(" AND b.blacklist_id  = :blacklistId");
            map.put("blacklistId", blacklistId);
            List<SrmPosBlacklistsEntity_HI_RO> list = srmPosBlacklistsDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：提交黑名单
     * <p>
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * orgId  业务实体ID  NUMBER  N
     * organizationId  库存组织ID  NUMBER  N
     * rejectReason  审批意见  VARCHAR2  N
     * blacklistId  黑名单ID  NUMBER  Y
     * blacklistNumber  黑名单单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:SRM_POS_SUPPLIER_INFO  NUMBER  Y
     * blacklistStatus  黑名单单据状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * permanentFlag  永久黑名单标识(Y/N)  VARCHAR2  N
     * relieveDate  解除时间  DATE  N
     * blacklistFileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * approvedDate  审批时间  DATE  N
     * <p>
     * Update History
     * =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveApprovalBlacklists(JSONObject params) throws Exception {
        LOGGER.info("保存冻结信息，参数：" + params.toString());
        SrmPosBlacklistsEntity_HI blackRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            // 保存信息判断
            if (null == params.getInteger("blacklistId")) {
                blackRow = new SrmPosBlacklistsEntity_HI();
                String blacklistNumber = saafSequencesUtil.getDocSequences("SRM_POS_BLACKLISTS", "HMD-", dateFromate, 4);
                blackRow.setSupplierId(params.getInteger("supplierId"));
                blackRow.setBlacklistNumber(blacklistNumber);
            } else {
                // 判断存在就是修改
                blackRow = srmPosBlacklistsDAO_HI.findByProperty("blacklistId", params.getInteger("blacklistId")).get(0);
            }

            blackRow.setBlacklistFileId(params.getInteger("blacklistFileId"));
            blackRow.setDescription(params.getString("description"));
            blackRow.setPermanentFlag(params.getString("permanentFlag"));
            blackRow.setRelieveDate(params.getDate("relieveDate"));
            blackRow.setBlacklistStatus("SUBMITTED");
            blackRow.setOperatorUserId(operatorUserId);
            srmPosBlacklistsDAO_HI.saveOrUpdate(blackRow);

            LOGGER.info("getBlacklistId：" + blackRow.getBlacklistId());

            List<SrmPosReasonsEntity_HI> reasonList = new ArrayList<SrmPosReasonsEntity_HI>();
            JSONArray valuesArray = params.getJSONArray("lineData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                SrmPosReasonsEntity_HI reasonRow = null;
                // 保存原因判断
                if (valuesJson.getInteger("reasonsId") == null) {
                    reasonRow = new SrmPosReasonsEntity_HI();
                    reasonRow.setDocId(blackRow.getBlacklistId());
                } else {
                    // 判断存在就是修改
                    reasonRow = srmPosReasonsDAO_HI.getById(valuesJson.getInteger("reasonsId"));
                }
                reasonRow.setReasonCode(SToolUtils.object2String(valuesJson.get("reasonCode")));
                reasonRow.setReasonDescription(SToolUtils.object2String(valuesJson.get("reasonDescription")));
                reasonRow.setSelectedFlag(SToolUtils.object2String(valuesJson.get("selectedFlag")));
                reasonRow.setDocId(blackRow.getBlacklistId());
                reasonRow.setDocTable("SRM_POS_BLACKLISTS");
                reasonRow.setOperatorUserId(operatorUserId);
                reasonList.add(reasonRow);
            }
            srmPosReasonsDAO_HI.saveOrUpdateAll(reasonList);

            JSONObject result = SToolUtils.convertResultJSONObj("S", "提交成功", 1, blackRow);
            result.put("getBlacklistId", blackRow.getBlacklistId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 审批黑名单
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApprovalBlacklists(JSONObject params)
            throws Exception {
        try {
            Integer blacklistId = params.getInteger("blacklistId");
            Integer operatorUserId = params.getInteger("varUserId");

            if (blacklistId == null) {
                return SToolUtils.convertResultJSONObj("E", "审批失败，参数无效！", 0, null);
            }
            SrmPosBlacklistsEntity_HI blackRow = srmPosBlacklistsDAO_HI.getById(blacklistId);
            if (blackRow != null) {
                //审批通过后,更改供应商信息的可采购和可付款标识为N和更改供应商黑名单标识为Y
                SrmPosSupplierInfoEntity_HI supplierInfoRow = srmPosSupplierInfoDAO_HI.getById(blackRow.getSupplierId());
                supplierInfoRow.setPaymentFlag("N");
                supplierInfoRow.setPurchaseFlag("N");
                supplierInfoRow.setSupplierStatus("QUIT");
                supplierInfoRow.setBlacklistFlag("Y");
                supplierInfoRow.setOperatorUserId(operatorUserId);
                srmPosSupplierInfoDAO_HI.update(supplierInfoRow);

                //审批通过后,更改供应商地点的可采购和可付款标识为N
                SrmPosSupplierSitesEntity_HI sitesRow = null;
                List<SrmPosSupplierSitesEntity_HI> sitesList = srmPosSupplierSitesDAO_HI.findByProperty("supplierId", blackRow.getSupplierId());
                List<SrmPosSupplierSitesEntity_HI> updateSitesList = new ArrayList<>();
                if (sitesList != null && sitesList.size() > 0) {
                    sitesRow = sitesList.get(0);
                    sitesRow.setPaymentFlag("N");
                    sitesRow.setPurchaseFlag("N");
                    sitesRow.setOperatorUserId(operatorUserId);
                    updateSitesList.add(sitesRow);
                }
                srmPosSupplierSitesDAO_HI.updateAll(updateSitesList);

                //更新单据状态
                blackRow.setBlacklistStatus("APPROVED");
                blackRow.setOperatorUserId(operatorUserId);
                srmPosBlacklistsDAO_HI.update(blackRow);
                return SToolUtils.convertResultJSONObj("S", "审批成功", 1, blackRow);
            } else {
                return SToolUtils.convertResultJSONObj("E", "审批失败，单据不存在！", 0, null);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 驳回黑名单
     * Update History
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateRejectBlacklists(JSONObject params)
            throws Exception {
        try {
            Integer blacklistId = params.getInteger("blacklistId");
            if (blacklistId == null) {
                return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数blacklistId", 0, null);
            }
            SrmPosBlacklistsEntity_HI blackRow = srmPosBlacklistsDAO_HI.getById(Integer.parseInt(blacklistId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            blackRow.setBlacklistStatus("REJECTED");
            blackRow.setOperatorUserId(operatorUserId);
            srmPosBlacklistsDAO_HI.update(blackRow);
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, blackRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
