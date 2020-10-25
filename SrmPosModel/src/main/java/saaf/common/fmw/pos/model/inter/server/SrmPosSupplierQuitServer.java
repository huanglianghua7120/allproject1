package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierQuitEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosSupplierQuit;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;
import saaf.common.fmw.utils.SrmUtils;

import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.MSG;
import static saaf.common.fmw.services.CommonAbstractServices.STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商退出头层
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2020-04-15     hgq             创建
 * ==============================================================================
 */
@Component("srmPosSupplierQuitServer")
public class SrmPosSupplierQuitServer implements ISrmPosSupplierQuit {

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPosSupplierQuitEntity_HI_RO> srmPosSupplierQuitDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierSitesEntity_HI_RO> srmPosSupplierSitesEntity_HI_RO;

    @Autowired
    private ViewObject<SrmPosReasonsEntity_HI> SrmPosReasonsDao_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierQuitEntity_HI> srmPosSupplierQuitDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierQuitSitesEntity_HI> srmPosSupplierQuitSiteDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> SrmPosSupplierCategoriesDAO_HI;


    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ISupplierInfo iSupplierInfo;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    /**
     * 查询供应商Lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierQuitEntity_HI_RO> findSupplierInfoLov(JSONObject jsonParams, Integer pageIndex,
                                                                          Integer pageRows) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(SrmPosSupplierQuitEntity_HI_RO.QUERY_SUPPLIER_LOV);
        Map<String, Object> map = new HashMap<String, Object>();
        String functionType = jsonParams.getString("functionType");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_name ", "varSupplierName", sql, map, "like");
        SaafToolUtils.parperParam(jsonParams, "t.supplier_number ", "varSupplierNumber", sql, map, "like");
        if ("QUIT".equals(functionType)) {
            sql.append(" AND t.supplier_status IN( 'QUALIFIED','FROZEN' ) ");
        }
        String supplierStatus = jsonParams.getString("supplierStatus");
        if ("QUIT".equals(supplierStatus)) {
            sql.append(" AND t.supplier_status IN ('INTRODUCING','EFFETIVE' )\r\n");
        }
        sql.append(" AND t.Supplier_Type IN (" + getSupplierType(jsonParams.getInteger("varUserId")) +")");
        String countSql = "select count(1) from (" + sql + ")";
        Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitDAO_HI_RO.findPagination(sql,countSql, map, pageIndex, pageRows);
        return rowSet;

    }


    /**
     * 查询冻结单号Lov
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierQuitEntity_HI_RO> findFrozenLov(JSONObject jsonParams, Integer pageIndex, Integer pageRows) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(SrmPosSupplierQuitEntity_HI_RO.QUERY_FROZEN_LOV);
        Map<String, Object> map = new HashMap<String, Object>();
        SaafToolUtils.parperParam(jsonParams, " t.supplier_id ", "supplierId", sql, map, "=");
        sql.append(" AND t.freeze_status ='APPROVED' ");
        String countSql = "select count(1) from (" + sql + ")";
        sql.append(" ORDER BY t.CREATION_DATE DESC ");
        Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitDAO_HI_RO.findPagination(sql,countSql, map, pageIndex, pageRows);
        return rowSet;
    }

    /**
     * 查询供应商退出
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Pagination<SrmPosSupplierQuitEntity_HI_RO> findSupplierQuitInfo(JSONObject jsonParams, Integer pageIndex,
                                                                           Integer pageRows) throws Exception {

        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        sb.append(SrmPosSupplierQuitEntity_HI_RO.QUERY_SUPPLIER_QUIT);
        SaafToolUtils.parperParam(jsonParams, " psq.supplier_quit_id ", "supplierQuitId", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, " psi.supplier_name ", "varSupplierName", sb, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, " su.user_full_name ", "varUserFullName", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, " psq.document_number ", "varDocumentNumber", sb, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(jsonParams, " psq.document_status ", "varDocumentStatus", sb, queryParamMap, "=");
        String varCreationDateF = jsonParams.getString("varCreationDateF");
        if (varCreationDateF != null && !"".equals(varCreationDateF.trim())) {
            sb.append(" AND trunc(psq.creation_date) >= to_date(:varCreationDateF, 'yyyy-mm-dd')\n");
            queryParamMap.put("varCreationDateF", varCreationDateF);
        }
        String varCreationDateE = jsonParams.getString("varCreationDateE");
        if (varCreationDateE != null && !"".equals(varCreationDateE.trim())) {
            sb.append(" AND trunc(psq.creation_date) <= to_date(:varCreationDateE, 'yyyy-mm-dd')\n");
            queryParamMap.put("varCreationDateE", varCreationDateE);
        }

        //供应商引入退出报表
        if (null != jsonParams.getString("supplierCurrentYear") && !"".equals(jsonParams.getString("supplierCurrentYear"))) {//三年的供应商引入退出
            Date supplierCurrentYearDate = DateUtil.str2Date(jsonParams.getString("supplierCurrentYear"), "yyyy");
            String supplierCurrentYear = DateUtil.date2Str(supplierCurrentYearDate, "yyyy-MM-dd");
            if (null != jsonParams.getString("supplierQuitName") && !"".equals(jsonParams.getString("supplierQuitName"))) {//供应商退出
                //验证字符串是否含有SQL关键字及字符，有则返回NULL
                if (SrmUtils.isContainSQL(supplierCurrentYear)) {
                    return null;
                }
                sb.append(      "AND EXISTS\n" +
                                "(SELECT\n" +
                                "  spsq.supplier_quit_id\n" +
                                "FROM\n" +
                                "  srm_pos_supplier_quit spsq\n" +
                                "WHERE spsq.supplier_quit_id = psq.supplier_quit_id\n" +
                                "  AND spsq.document_status = 'APPROVED'\n" +
                                "  AND to_char(spsq.last_update_date, 'yyyy') = '" + supplierCurrentYear.substring(0, 4) + "')");
            }
        }

        sb.append(" AND psq.Supplier_Id IN\n" +
                " (SELECT Psi.Supplier_Id\n" +
                "          FROM Srm_Pos_Supplier_Info Psi\n" +
                "         WHERE Psi.Supplier_Status IN ('APPROVED'\n" +
                "                                      ,'EFFETIVE'\n" +
                "                                      ,'INTRODUCING'\n" +
                "                                      ,'QUIT')\n" +
                "           AND Psi.Supplier_Type IN (" + getSupplierType(jsonParams.getInteger("varUserId")) +"))");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY psq.creation_date DESC ");
        Pagination<SrmPosSupplierQuitEntity_HI_RO> rowSet = srmPosSupplierQuitDAO_HI_RO.findPagination(sb,countSql, queryParamMap, pageIndex, pageRows);
        return rowSet;
    }

    private String getSupplierType(Integer userId){
        JSONObject json=new JSONObject();
        json.put("userId",userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList=srmBaseUserCategories.findUserCategories(json);
        Map map = new HashMap();
        map.put("lookupType", "POS_SUPPLIER_TYPE");
        map.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
        List  categoryCode=new ArrayList();
        for(SaafLookupValuesEntity_HI vo:lookupValues){
            for(SrmBaseUserCategoriesEntity_HI_RO ro:userCategoriesList){
                if(ro.getCategoryCode().equals(vo.getLookupCode())){
                    categoryCode.add(ro.getCategoryCode());
                }
            }
        }
        String supplierType= String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining("','")));
        supplierType="'"+supplierType+"'";
        return supplierType;
    }

    /**
     * Description：保存数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * supplierQuitId  供应商退出单ID  NUMBER  Y
     * documentNumber  退出单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  （废弃）组织ID  NUMBER  N
     * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
     * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
     * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * rejectReason  驳回原因  VARCHAR2  N
     * quitType  退出类型  VARCHAR2  N
     * supplierQuitId  供应商退出单ID  NUMBER  Y
     * documentNumber  退出单号  VARCHAR2  N
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  （废弃）组织ID  NUMBER  N
     * frozenId  （废弃）冻结单ID,关联表:srm_pos_frozen_info  NUMBER  N
     * documentStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * inventoryCleanupFlag  是否完成库存清理(Y/N)  VARCHAR2  N
     * paymentSettleFlag  是否完成尾款结算(Y/N)  VARCHAR2  N
     * fileId  附件ID  NUMBER  N
     * description  说明  VARCHAR2  N
     * quitDate  退出日期  DATE  N
     * quitForeverFlag  是否永久退出(Y/N)  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveData(JSONObject jsonParams) throws Exception {
        boolean repeatFlag = true;
        repeatFlag = findSupplierQuit(jsonParams);
        if (repeatFlag) {
            return SToolUtils.convertResultJSONObj("E", "该供应商存在未退出成功的单据，请先完成前面的单据！", 0, null);
        }

        Integer supplierQuitId = jsonParams.getInteger("supplierQuitId");
        Integer supplierAddressId = jsonParams.getInteger("supplierAddressId");
        Integer supplierId = jsonParams.getInteger("supplierId");
        Integer frozenId = jsonParams.getInteger("frozenId");
        Integer varUserId = jsonParams.getInteger("varUserId");
        String action = jsonParams.getString("action");
        String status = null;
        SrmPosSupplierQuitEntity_HI row = null;
        List<SrmPosSupplierQuitSitesEntity_HI> quitSitesRow = null;

        if ("SAVE".equals(action)) {
            status = "DRAFT";
        } else if ("SUBMIT".equals(action)) {
            status = "SUBMITTED";
        } else if ("APPROVED".equals(action)) {
            // 审批通过
            row = srmPosSupplierQuitDAO_HI.findByProperty("supplier_quit_id", supplierQuitId).get(0);
            if (!"SUBMITTED".equals(row.getDocumentStatus())) {
                return SToolUtils.convertResultJSONObj("E", "状态不为待审核，不能审批！", 0, null);
            }
            row.setDocumentStatus("APPROVED");
            row.setOperatorUserId(varUserId);
            srmPosSupplierQuitDAO_HI.saveOrUpdate(row);
            //this.updateSupplierInfo(supplierId, varUserId, supplierQuitId);
            JSONObject json = this.updateSupplierInfo(row.getSupplierId(), varUserId, supplierQuitId);
            if(null == json || null == json.getString(STATUS) || !SUCCESS_STATUS.equals(json.getString(STATUS))){
                throw new Exception(json.getString(MSG));
            }
            //发送邮件-联系人
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", row.getSupplierId());
            if (contactsList != null && contactsList.size() > 0) {
                String title = "供应商退出结果通知";
                String content = "<p>您好！</p><br/>贵公司已经退出，请在公司档案中查看！";
                sendEmailToContacts(contactsList, title, content);
            }
            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", row.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("供应商退出", "您好！贵公司已经退出，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_supplier_quit", row.getSupplierQuitId(), "supplierQuitId", "home.supplierQuitDetail", varUserId);
            }
            return SToolUtils.convertResultJSONObj("S", "单据审批通过！", 1, row);
        } else if ("REJECTED".equals(action)) {
            // 拒绝
            row = srmPosSupplierQuitDAO_HI.findByProperty("supplier_quit_id", supplierQuitId).get(0);
            if (!"SUBMITTED".equals(row.getDocumentStatus())) {
                return SToolUtils.convertResultJSONObj("E", "状态不为待审核，不能审批！", 0, null);
            }
            row.setDocumentStatus("REJECTED");
            row.setOperatorUserId(varUserId);
            srmPosSupplierQuitDAO_HI.saveOrUpdate(row);
            //插入通知
            iSrmBaseNotifications.insertSrmBaseNotifications("供应商退出", "您好！您所提交的供应商的退出资料，单号：" + row.getDocumentNumber() + "；审批被驳回，请查看处理，谢谢！"
                    , row.getCreatedBy(), "srm_pos_supplier_quit", row.getSupplierQuitId(), "supplierQuitId", "home.supplierQuitDetail", varUserId);
            return SToolUtils.convertResultJSONObj("S", "单据已拒绝！", 1, row);
        } else {
            return SToolUtils.convertResultJSONObj("E", "参数异常,请联系管理员！", 0, null);
        }
        if (supplierQuitId == null || "".equals(supplierQuitId)) {
            row = new SrmPosSupplierQuitEntity_HI();
            String CurrentDateStr = SrmUtils.getDateStr(new Date(), "yyyyMMdd");
            String documentNumber = saafSequencesUtil.getDocSequences("srm_pos_supplier_quit".toUpperCase(), "GFTC-" + CurrentDateStr, 4);
            row.setDocumentNumber(documentNumber);
        } else {
            row = srmPosSupplierQuitDAO_HI.findByProperty("supplier_quit_id", supplierQuitId).get(0);
        }
        row.setSupplierId(supplierId);
        row.setDocumentStatus(status);
        row.setFrozenId(frozenId);
        row.setOperatorUserId(varUserId);
        row.setPaymentSettleFlag(jsonParams.getString("paymentSettleFlag"));
        row.setInventoryCleanupFlag(jsonParams.getString("inventoryCleanupFlag"));
        row.setFileId(jsonParams.getInteger("fileId"));
        row.setDescription(jsonParams.getString("description"));
        row.setQuitDate(jsonParams.getDate("quitDate"));
        row.setQuitForeverFlag(jsonParams.getString("quitForeverFlag"));
        srmPosSupplierQuitDAO_HI.saveOrUpdate(row);
        //取消退出原因
/*        JSONArray valuesArray = jsonParams.getJSONArray("lineData");
        SrmPosReasonsEntity_HI lineRow = null;
        for (int i = 0; i < valuesArray.size(); i++) {
            JSONObject lineJson = valuesArray.getJSONObject(i);
            Integer reasonsId = lineJson.getInteger("reasonsId");
            if (reasonsId == null || "".equals(reasonsId)) {
                lineRow = new SrmPosReasonsEntity_HI();
            } else {
                lineRow = SrmPosReasonsDao_HI.findByProperty("reasons_id", reasonsId).get(0);
            }
            lineRow.setReasonCode(lineJson.getString("reasonCode"));
            lineRow.setReasonDescription(lineJson.getString("reasonDescription"));
            lineRow.setSelectedFlag(lineJson.getString("selectedFlag"));
            lineRow.setDocId(row.getSupplierQuitId());
            lineRow.setDocTable("SRM_POS_SUPPLIER_QUIT");
            lineRow.setOperatorUserId(varUserId);
            SrmPosReasonsDao_HI.saveOrUpdate(lineRow);
        }*/

        JSONArray siteArray = jsonParams.getJSONArray("sitesData");
        if (null != supplierQuitId && null!= supplierAddressId){
            Map querySites = new HashMap();
            querySites.put("supplier_quit_id", supplierQuitId);
            querySites.put("supplier_address_id", supplierAddressId);
            quitSitesRow = srmPosSupplierQuitSiteDAO_HI.findByProperty(querySites);
            if (quitSitesRow.size() > 0) {
                srmPosSupplierQuitSiteDAO_HI.delete(quitSitesRow);
            }
        }

        if (siteArray.size() > 0) {
            for (int j = 0; j < siteArray.size(); j++) {
                JSONObject siteJson = siteArray.getJSONObject(j);
                SrmPosSupplierQuitSitesEntity_HI newQuitSiteRow = new SrmPosSupplierQuitSitesEntity_HI();
                newQuitSiteRow.setSupplierQuitId(row.getSupplierQuitId());
                newQuitSiteRow.setSupplierSiteId(siteJson.getInteger("supplierSiteId"));
                newQuitSiteRow.setSupplierAddressId(siteJson.getInteger("supplierAddressId"));
                newQuitSiteRow.setSiteName(siteJson.getString("siteName"));
                newQuitSiteRow.setOrgId(siteJson.getInteger("orgId"));
                if ("QUIT".equals(siteJson.getString("siteStatus"))) {
                    continue;
                } else {
                    newQuitSiteRow.setSiteStatus(siteJson.getString("siteStatus"));
                }
                newQuitSiteRow.setPurchaseFlag(siteJson.getString("purchaseFlag"));
                newQuitSiteRow.setPaymentFlag(siteJson.getString("paymentFlag"));
                newQuitSiteRow.setFrozeFlag(siteJson.getString("frozeFlag"));
                newQuitSiteRow.setUnfrozeDate(siteJson.getDate("unfrozeDate"));
                newQuitSiteRow.setQualifiedDate(siteJson.getDate("qualifiedDate"));
                newQuitSiteRow.setInvalidDate(siteJson.getDate("invalidDate"));
                newQuitSiteRow.setTemporarySiteFlag(siteJson.getString("temporarySiteFlag"));
                newQuitSiteRow.setSelectedFlag("Y");
                newQuitSiteRow.setVersionNum(siteJson.getInteger("versionNum"));
                newQuitSiteRow.setOperatorUserId(varUserId);
                srmPosSupplierQuitSiteDAO_HI.saveOrUpdate(newQuitSiteRow);
            }
        }
        return SToolUtils.convertResultJSONObj("S", "数据保存成功", 1, row);
    }


    private void sendEmailToContacts(final List<SrmPosSupplierContactsEntity_HI> contactList, final String title, final String content) {
        ArrayList<String> emailAddressList = new ArrayList<>();
        for (SrmPosSupplierContactsEntity_HI contact : contactList) {
            String emailAddress = contact.getEmailAddress();
            //如果邮箱不为空
            if (emailAddress != null && !"".equals(emailAddress)) {
                //如果当前联系人没有失效
                if (contact.getFailureDate() == null || contact.getFailureDate().after(new Date())) {
                    emailAddressList.add(emailAddress);
                }
            }
        }
        String[] emailAddress = new String[emailAddressList.size()];
        emailAddress = emailAddressList.toArray(emailAddress);
        sendMailUtil.doSendHtmlEmail(title, content, emailAddress);
    }

    /**
     * 审批后更新供应商状态
     *
     * @param supplierId
     * @param varUserId
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    /*public String updateSupplierInfo(Integer supplierId, Integer varUserId, Integer supplierQuitId) throws Exception {
        List<SrmPosSupplierQuitSitesEntity_HI> quitSites = srmPosSupplierQuitSiteDAO_HI.findByProperty("supplier_quit_id", supplierQuitId);
        SrmPosSupplierSitesEntity_HI site = null;
        for (SrmPosSupplierQuitSitesEntity_HI quitSite : quitSites) {
            site = srmPosSupplierSitesDAO_HI.getById(quitSite.getSupplierSiteId());
            site.setSiteStatus("QUIT");
            site.setPurchaseFlag("N");
            site.setPaymentFlag("N");
            site.setInvalidDate(new Date());
            site.setOperatorUserId(varUserId);
            srmPosSupplierSitesDAO_HI.saveOrUpdate(site);
            quitSite.setSiteStatus("QUIT");
            srmPosSupplierQuitSiteDAO_HI.saveOrUpdate(quitSite);
        }

        Map<String, Object> siteMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("select b from SrmPosSupplierSitesEntity_HI b where b.supplierId =" + supplierId);
        siteMap.put("supplier_id", supplierId);
        sb.append(" and b.siteStatus <> 'QUIT' ");
        siteMap.put("site_status", "QUIT");
        List<SrmPosSupplierSitesEntity_HI> countSite = srmPosSupplierSitesDAO_HI.findList(sb.toString(), siteMap);
        SrmPosSupplierInfoEntity_HI supplierRow = srmPosSupplierInfoDAO_HI.findByProperty("supplier_id", supplierId).get(0);
        if (countSite.size() == 0) {
            supplierRow.setSupplierStatus("QUIT");
            //supplierRow.setPassU9Flag(SrmUtils.getUpadteU9Flag(supplierRow.getPassU9Flag()));
            supplierRow.setApprovalDate(null);
            supplierRow.setOperatorUserId(varUserId);
            srmPosSupplierInfoDAO_HI.saveOrUpdate(supplierRow);
        }
        Map<String, Object> cateMap = new HashMap<String, Object>();
        cateMap.put("supplier_id", supplierId);
        cateMap.put("status", "ACT");
        List<SrmPosSupplierCategoriesEntity_HI> cateRow = SrmPosSupplierCategoriesDAO_HI.findByProperty(cateMap);
        SrmPosSupplierCategoriesEntity_HI row = null;
        if (cateRow != null && cateRow.size() > 0) {
            for (int i = 0; i < cateRow.size(); i++) {
                row = cateRow.get(0);
                row.setStatus("INACT");
                row.setInvalidDate(new Date());
                row.setOperatorUserId(varUserId);
                SrmPosSupplierCategoriesDAO_HI.saveOrUpdate(row);
            }
        }
        return "S";
    }*/

    /**
     * 审批后更新供应商状态
     * updated by xuwen at 2018/12/19
     *
     * @param supplierId     供应商id
     * @param varUserId      操作用户id
     * @param supplierQuitId 供应商退出id
     * @return 返回操作结果
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    private JSONObject updateSupplierInfo(Integer supplierId, Integer varUserId, Integer supplierQuitId) throws Exception{
        List<SrmPosSupplierQuitSitesEntity_HI> quitSites = srmPosSupplierQuitSiteDAO_HI.findByProperty("supplier_quit_id", supplierQuitId);
        SrmPosSupplierSitesEntity_HI site = null;
        for (SrmPosSupplierQuitSitesEntity_HI quitSite : quitSites) {
            site = srmPosSupplierSitesDAO_HI.getById(quitSite.getSupplierSiteId());
            site.setSiteStatus("QUIT");
            site.setPurchaseFlag("N");
            site.setPaymentFlag("N");
            site.setInvalidDate(new Date());
            site.setOperatorUserId(varUserId);
            srmPosSupplierSitesDAO_HI.saveOrUpdate(site);
            srmPosSupplierSitesDAO_HI.fluch();
            quitSite.setSiteStatus("QUIT");
            srmPosSupplierQuitSiteDAO_HI.saveOrUpdate(quitSite);
            srmPosSupplierQuitSiteDAO_HI.fluch();
        }

        Map<String, Object> siteMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("select b from SrmPosSupplierSitesEntity_HI b where b.supplierId =" + supplierId);
        siteMap.put("supplier_id", supplierId);
        sb.append(" and b.siteStatus <> 'QUIT' ");
        siteMap.put("site_status", "QUIT");
        List<SrmPosSupplierSitesEntity_HI> countSite = srmPosSupplierSitesDAO_HI.findList(sb.toString(), siteMap);
        SrmPosSupplierInfoEntity_HI supplierRow = srmPosSupplierInfoDAO_HI.findByProperty("supplier_id", supplierId).get(0);
        if (countSite.size() == 0) {
            supplierRow.setSupplierStatus("QUIT");
            //supplierRow.setPassU9Flag(SrmUtils.getUpadteU9Flag(supplierRow.getPassU9Flag()));
            supplierRow.setApprovalDate(null);
            supplierRow.setOperatorUserId(varUserId);
            srmPosSupplierInfoDAO_HI.saveOrUpdate(supplierRow);
            srmPosSupplierInfoDAO_HI.fluch();
        }
        Map<String, Object> cateMap = new HashMap<String, Object>();
        cateMap.put("supplier_id", supplierId);
        cateMap.put("status", "ACT");
        List<SrmPosSupplierCategoriesEntity_HI> cateRow = SrmPosSupplierCategoriesDAO_HI.findByProperty(cateMap);
        SrmPosSupplierCategoriesEntity_HI row = null;
        if (cateRow != null && cateRow.size() > 0) {
            for (int i = 0; i < cateRow.size(); i++) {
                row = cateRow.get(0);
                row.setStatus("INACT");
                row.setInvalidDate(new Date());
                row.setOperatorUserId(varUserId);
                SrmPosSupplierCategoriesDAO_HI.saveOrUpdate(row);
                SrmPosSupplierCategoriesDAO_HI.fluch();
            }
        }
        //供应商退出不同步至EBS
        /*List<SrmPosSupplierSitesEntity_HI> sitesEntityList = srmPosSupplierSitesDAO_HI.findByProperty("supplierId",supplierId);
        if(null != sitesEntityList && sitesEntityList.size()>0 && (null != supplierRow.getSupplierEbsNumber() && !"".equals(supplierRow.getSupplierEbsNumber()))){
            //将供应商档案信息发送到EBS系统——同时存在地点和供应商EBS编码
            JSONObject json = iSupplierInfo.updateSrmPosSupplierInfoSendToEBSInfo(supplierId,"NORMAL","N",null,varUserId);
            return json;
        }*/
        return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "操作成功", 0, null);
    }

    /**
     * 删除数据
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
    public JSONObject deleteQuitRow(JSONObject jsonParams) throws Exception {
        Integer supplierQuitId = jsonParams.getInteger("supplierQuitId");
        SrmPosSupplierQuitEntity_HI row = null;
        row = srmPosSupplierQuitDAO_HI.findByProperty("supplier_quit_id", supplierQuitId).get(0);
        if (row == null) {
            return SToolUtils.convertResultJSONObj("E", "数据不存在" + supplierQuitId, 0, null);
        }
        if ("DRAFT".equals(row.getDocumentStatus()) || "REJECTED".equals(row.getDocumentStatus())) {
            /* 根据退出单号删除退出地点表的相关信息 */
            List<SrmPosSupplierQuitSitesEntity_HI> quitSitesRow = null;
            Map querySites = new HashMap();
            querySites.put("supplier_quit_id", supplierQuitId);
            quitSitesRow = srmPosSupplierQuitSiteDAO_HI.findByProperty(querySites);
            if (quitSitesRow.size() > 0) {
                srmPosSupplierQuitSiteDAO_HI.delete(quitSitesRow);
            }
            srmPosSupplierQuitDAO_HI.delete(row);
        } else {
            return SToolUtils.convertResultJSONObj("E", "只能删除拟定、驳回状态的数据！", 1, null);
        }
        return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
    }

    /**
     * 查询理由
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosReasonsEntity_HI> findReasonInfo(JSONObject jsonParams) throws Exception {
        String docId = jsonParams.getString("docId");
        String docTable = jsonParams.getString("docTable");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("doc_id", docId);
        map.put("doc_table", docTable);
        List<SrmPosReasonsEntity_HI> list = SrmPosReasonsDao_HI.findByProperty(map);
        return list;
    }

    /**
     * 供应商退出数量计算——按照年份（供应商引入退出报表）
     *
     * @param currentYear
     * @param i
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Integer findSupplierQuitCount(String currentYear, Integer i) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(currentYear)) {
            return null;
        }
        Integer count = 0;
        HashSet<Integer> supplierIdSize = new HashSet<>();
        StringBuilder sb = new StringBuilder("SELECT t.supplier_id FROM srm_pos_supplier_quit t WHERE t.document_status='APPROVED' ");
        sb.append("AND YEAR(t.last_update_date)=YEAR(date_sub('" + currentYear + "',interval " + i + " year)) ");
        sb.append(" GROUP BY t.supplier_id");
        List<SrmPosSupplierQuitEntity_HI_RO> quitList = srmPosSupplierQuitDAO_HI_RO.findList(sb.toString());
        for (SrmPosSupplierQuitEntity_HI_RO k : quitList) {
            supplierIdSize.add(k.getSupplierId());
        }
        if (null != supplierIdSize && supplierIdSize.size() > 0) {
            count = supplierIdSize.size();
        }
        return count;
    }

    /**
     * 检查该供应商所有地点是否已退出，如果全已退出，更新所有品类为失效状态
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
    public JSONObject updateSupplierCategories(JSONObject jsonParams) throws Exception {
        Integer supplierId = jsonParams.getInteger("supplierId");
        Integer operatorUserId = jsonParams.getInteger("operatorUserId");
        Map querySiteMap = new HashMap();
        querySiteMap.put("supplierId", supplierId);
        List<SrmPosSupplierSitesEntity_HI> querySites = srmPosSupplierSitesDAO_HI.findByProperty(querySiteMap);
        int countSite = 0;
        if (querySites.size() > 0) {
            for (SrmPosSupplierSitesEntity_HI querySite : querySites) {
                if (!"QUIT".equals(querySite.getSiteStatus())) {
                    countSite++;
                }
            }
            if (countSite == 0) {
                List<SrmPosSupplierCategoriesEntity_HI> queryCategories = SrmPosSupplierCategoriesDAO_HI.findByProperty(querySiteMap);
                if (queryCategories.size() > 0) {
                    for (SrmPosSupplierCategoriesEntity_HI queryCategorie : queryCategories) {
                        queryCategorie.setStatus("DISABLED");
                        queryCategorie.setOperatorUserId(operatorUserId);
                    }
                    SrmPosSupplierCategoriesDAO_HI.saveOrUpdateAll(queryCategories);
                }
            }
        }
        return SToolUtils.convertResultJSONObj("S", "数据保存成功", 1, null);
    }

    /**
     * 同一供应商已批准状态单据
     *
     * @param parameters
     * @return
     * @throws Exception
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean findSupplierQuit(JSONObject parameters) throws Exception {
            boolean flag = false;
            StringBuffer queryString = new StringBuffer(SrmPosSupplierQuitEntity_HI_RO.QUERY_SUPPLIER_QUIT);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
        SaafToolUtils.parperParam(parameters, " psq.supplier_id ", "supplierId", queryString, map, "=");
        SaafToolUtils.parperParam(parameters, " psi.supplier_name ", "supplierName", queryString, map, "=");
        queryString.append(" AND psq.document_status NOT IN ('APPROVED','UNEFFICACY') ");
        if(!ObjectUtils.isEmpty(parameters.getInteger("supplierQuitId"))){
            queryString.append(" AND psq.supplier_quit_id != "+ parameters.getInteger("supplierQuitId")+" ");
        }
            List<SrmPosSupplierQuitEntity_HI_RO> qualificationList = srmPosSupplierQuitDAO_HI_RO.findList(queryString, map);
            if (qualificationList!=null&&qualificationList.size() > 0) {
                flag = true;
            }
            return flag;
    }

}
