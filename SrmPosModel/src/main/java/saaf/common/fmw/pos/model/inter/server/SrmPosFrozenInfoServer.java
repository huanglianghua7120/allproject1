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

import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosFrozenInfoEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierSitesEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.IFrozenInfo;
import saaf.common.fmw.utils.SrmUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：供应商冻结、恢复
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     hgq             创建
 * ===========================================================================
 */
@Component("srmPosFrozenInfoServer")
public class SrmPosFrozenInfoServer implements IFrozenInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosFrozenInfoServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPosFrozenInfoEntity_HI_RO> srmPosFrozenInfoDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosFrozenInfoEntity_HI> srmPosFrozenInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosReasonsEntity_HI> srmPosReasonsDAO_HI;

    @Autowired
    private ViewObject<SrmPosFrozenCategoriesEntity_HI> srmPosFrozenCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosFrozenSitesEntity_HI> srmPosFrozenSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSupplierSitesEntity_HI_RO> srmPosSupplierSitesDAO_HI_RO;

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值
    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    /**
     * 查询冻结、恢复列表信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosFrozenInfoEntity_HI_RO> findFrozenInfoList(JSONObject parameters,
                                                                       Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosFrozenInfoEntity_HI_RO.QUERY_FROZEN_SQL);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "fi.freeze_status", "freezeStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "si.supplier_name ", "supplierName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "su.user_full_name", "userFullName", queryString, map, "LIKE");
            String creationDateFrom = parameters.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(fi.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = parameters.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(fi.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            if (parameters.getString("freezeNumber") != null && !"".equals(parameters.getString("freezeNumber").trim())) {
                queryString.append(" AND fi.freeze_number LIKE '%");
                queryString.append(parameters.getString("freezeNumber"));
                queryString.append("%'");
            }
            SaafToolUtils.parperParam(parameters, "fi.freeze_type ", "freezeType", queryString, map, "=");
            queryString.append(" AND fi.Supplier_Id IN\n" +
                    " (SELECT Psi.Supplier_Id\n" +
                    "          FROM Srm_Pos_Supplier_Info Psi\n" +
                    "         WHERE Psi.Supplier_Status IN ('APPROVED'\n" +
                    "                                      ,'EFFETIVE'\n" +
                    "                                      ,'INTRODUCING'\n" +
                    "                                      ,'QUIT')\n" +
                    "           AND Psi.Supplier_Type IN (" + getSupplierType(parameters.getInteger("varUserId")) +"))");
            // 排序
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY fi.creation_date DESC");
            Pagination<SrmPosFrozenInfoEntity_HI_RO> frozenList =
                    srmPosFrozenInfoDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return frozenList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 获取供应商类型
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
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
     * 保存冻结、恢复信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject saveFrozenInfo(JSONObject params) throws Exception {
        LOGGER.info("保存冻结信息，参数：" + params.toString());
        SrmPosFrozenInfoEntity_HI frozenRow = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            String freezeType = params.getString("freezeType");
            String forbidPurchaseFlag = params.getString("forbidPurchaseFlag");
            String forbidPaymentFlag = params.getString("forbidPaymentFlag");
            Date date = new Date(System.currentTimeMillis());
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            String freezeNumber = null;

            // 保存冻结信息判断
            if (null == params.getInteger("frozenId")) {
                frozenRow = new SrmPosFrozenInfoEntity_HI();
                if (freezeType.equals("FROZEN")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFDJ-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                } else if (freezeType.equals("RECOVERY")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFHF-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                }
                frozenRow.setSupplierId(params.getInteger("supplierId"));
            } else {
                // 判断存在就是修改
                frozenRow = srmPosFrozenInfoDAO_HI.findByProperty("frozenId", params.getInteger("frozenId")).get(0);
                freezeNumber = frozenRow.getFreezeNumber();
            }

            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(freezeNumber)) {
                return null;
            }

            frozenRow.setForbidPaymentFlag(forbidPaymentFlag);
            frozenRow.setForbidPurchaseFlag(forbidPurchaseFlag);
            frozenRow.setFrozenFileId(params.getInteger("frozenFileId"));
            frozenRow.setDescription(params.getString("description"));
            frozenRow.setFreezeType(params.getString("freezeType"));
            frozenRow.setUnfrozenDate(params.getDate("unfrozenDate"));
            frozenRow.setFreezeStatus("DRAFT");
            frozenRow.setApproveBy(operatorUserId);
            frozenRow.setApproveDate(date);
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.saveOrUpdate(frozenRow);

            LOGGER.info("getFrozenId：" + frozenRow.getFrozenId());

            //取消原因
  /*          List<SrmPosReasonsEntity_HI> reasonList = new ArrayList<SrmPosReasonsEntity_HI>();
            JSONArray valuesArray = params.getJSONArray("lineData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                SrmPosReasonsEntity_HI reasonRow = null;
                // 保存冻结原因判断
                if (valuesJson.getInteger("reasonsId") == null) {
                    reasonRow = new SrmPosReasonsEntity_HI();
                    reasonRow.setDocId(frozenRow.getFrozenId());
                } else {
                    // 判断存在就是修改
                    reasonRow = srmPosReasonsDAO_HI.getById(valuesJson.getInteger("reasonsId"));
                }

                reasonRow.setReasonCode(SToolUtils.object2String(valuesJson.get("reasonCode")));
                reasonRow.setReasonDescription(SToolUtils.object2String(valuesJson.get("reasonDescription")));
                reasonRow.setSelectedFlag(SToolUtils.object2String(valuesJson.get("selectedFlag")));
                reasonRow.setDocId(frozenRow.getFrozenId());
                reasonRow.setDocTable("SRM_POS_FROZEN_INFO");
                reasonRow.setOperatorUserId(operatorUserId);
                reasonList.add(reasonRow);
            }
            srmPosReasonsDAO_HI.saveOrUpdateAll(reasonList);*/
            StringBuffer hql = new StringBuffer();
            hql.append("from SrmPosFrozenInfoEntity_HI h where h.freezeNumber =");
            hql.append("'");
            hql.append(freezeNumber);
            hql.append("'");
            SrmPosFrozenInfoEntity_HI entity = srmPosFrozenInfoDAO_HI.get(hql, new HashMap<String, Object>());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("dataType", "CLASS");
            paraMap.put("freezeType", freezeType);
            paraMap.put("operatorUserId", operatorUserId + "");
            paraMap.put("forbidPurchaseFlag", forbidPurchaseFlag);
            paraMap.put("frozenId", entity.getFrozenId().toString());
            //
            //取消品类
/*            JSONArray classArray = params.getJSONArray("classData");
            List<?> classList = getDataHandler(classArray, paraMap);
            srmPosFrozenCategoriesDAO_HI.saveOrUpdateAll(classList);*/

            JSONArray siteArray = params.getJSONArray("siteData");
            paraMap.put("dataType", "SITES");
            List<?> siteList = getDataHandler(siteArray, paraMap);
            srmPosFrozenSitesDAO_HI.saveOrUpdateAll(siteList);

            /**冻结地点列表*/
            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, frozenRow);
            result.put("getFrozenId", frozenRow.getFrozenId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 数据加工处理
     *
     * @param jsonArray
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> getDataHandler(JSONArray jsonArray, Map<String, String> paraMap) {
        String dataType = paraMap.get("dataType");
        Integer frozenId = Integer.valueOf(paraMap.get("frozenId"));
        Integer operatorUserId = Integer.parseInt(paraMap.get("operatorUserId"));
        List<SrmPosFrozenCategoriesEntity_HI> classList = new ArrayList<SrmPosFrozenCategoriesEntity_HI>();
        List<SrmPosFrozenSitesEntity_HI> siteList = new ArrayList<SrmPosFrozenSitesEntity_HI>();
        JSONObject jsonobj = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonobj = jsonArray.getJSONObject(i);
            if ("CLASS".equals(dataType)) {
                SrmPosFrozenCategoriesEntity_HI classRow = null;
                Integer frozenCategoryId = jsonobj.getInteger("frozenCategoryId");
                //保存判断
                if (frozenCategoryId == null) {
                    classRow = new SrmPosFrozenCategoriesEntity_HI();
                } else {
                    classRow = srmPosFrozenCategoriesDAO_HI.getById(frozenCategoryId);
                }
                classRow.setSupplierCategoryId(jsonobj.getInteger("supplierCategoryId"));
                classRow.setCategoryId(jsonobj.getInteger("categoryId"));
                classRow.setCategoryStatus(jsonobj.getString("statusType"));
                classRow.setSelectedFlag(jsonobj.getString("selectedFlag"));
                classRow.setFrozenId(frozenId);
                classRow.setOperatorUserId(operatorUserId);
                classList.add(classRow);
            }
            if ("SITES".equals(dataType)) {
                SrmPosFrozenSitesEntity_HI siteRow = null;
                Integer frozenSiteId = jsonobj.getInteger("frozenSiteId");
                if (frozenSiteId == null) {
                    siteRow = new SrmPosFrozenSitesEntity_HI();
                } else {
                    siteRow = srmPosFrozenSitesDAO_HI.getById(frozenSiteId);
                }
                siteRow.setSupplierSiteId(jsonobj.getInteger("supplierSiteId"));
                siteRow.setSiteStatus(jsonobj.getString("siteStatus"));
                siteRow.setTemporarySiteFlag(jsonobj.getString("temporarySiteFlag"));
                siteRow.setSelectedFlag(jsonobj.getString("selectedFlag"));
                siteRow.setActionPurchaseFlag(jsonobj.getString("actionPurchaseFlag"));
                siteRow.setActionPaymentFlag(jsonobj.getString("actionPaymentFlag"));
                siteRow.setFrozenId(frozenId);
                siteRow.setOperatorUserId(operatorUserId);
                siteList.add(siteRow);
            }
        }
        if ("CLASS".equals(dataType)) {
            return classList;
        } else {
            return siteList;
        }
    }

    /**
     * 删除供应商冻结、恢复信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteFrozenInfo(JSONObject params) throws Exception {
        LOGGER.info("删除供应商冻结、恢复信息，参数：" + params.toString());
        SrmPosFrozenInfoEntity_HI frozenRow = null;
        Integer frozenId = params.getInteger("frozenId");
        try {
            if (frozenId == null) {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + params.getString("frozenId") + "不存在", 0, null);
            }
            List<SrmPosFrozenInfoEntity_HI> frozenList = srmPosFrozenInfoDAO_HI.findByProperty("frozenId", frozenId);
            if ("DRAFT".equals(frozenList.get(0).getFreezeStatus()) || "REJECT".equals(frozenList.get(0).getFreezeStatus())) {
                if (frozenList != null && frozenList.size() > 0) {
                    frozenRow = frozenList.get(0);
                    srmPosFrozenInfoDAO_HI.delete(frozenRow);
                }

                List<SrmPosFrozenSitesEntity_HI> frozenSiteList = srmPosFrozenSitesDAO_HI.findByProperty("frozenId", frozenId);
                if (frozenSiteList != null && frozenSiteList.size() > 0) {
                    srmPosFrozenSitesDAO_HI.deleteAll(frozenSiteList);
                }
                List<SrmPosFrozenCategoriesEntity_HI> frozenCategoriesList = srmPosFrozenCategoriesDAO_HI.findByProperty("frozenId", frozenId);
                if (frozenCategoriesList != null && frozenCategoriesList.size() > 0) {
                    srmPosFrozenSitesDAO_HI.deleteAll(frozenCategoriesList);
                }
                List<SrmPosReasonsEntity_HI> frozenReasonsList = srmPosReasonsDAO_HI.findByProperty("docId", frozenId);
                if (frozenReasonsList != null && frozenReasonsList.size() > 0) {
                    srmPosFrozenSitesDAO_HI.deleteAll(frozenReasonsList);
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
     * 根据id查询冻结信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosFrozenInfoEntity_HI_RO> findFrozenInfo(Integer frozenId) throws Exception {
        LOGGER.info("根据id查询信息，参数：" + frozenId);
        try {
            String sql = SrmPosFrozenInfoEntity_HI_RO.QUERY_FROZEN_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            queryString.append(" and fi.frozen_id  = :frozenId");
            map.put("frozenId", frozenId);
            List<SrmPosFrozenInfoEntity_HI_RO> list = srmPosFrozenInfoDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：提交供应商冻结、恢复信息
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeNumber  冻结单据号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * freezeItem  冻结的条目（SNBC_TYPE：地点或者品类）  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * freezeType  区分冻结和恢复单据  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeType  类型(POS_FREEZE_TYPE  冻结或解冻)  VARCHAR2  N
     * freezeNumber  冻结单号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveApprovalFrozenInfo(JSONObject params)
            throws Exception {
        LOGGER.info("提交信息，参数：" + params.toString());
        SrmPosFrozenInfoEntity_HI frozenRow = null;
        String freezeNumber = null;
        try {
            Integer operatorUserId = params.getInteger("varUserId");
            String freezeType = params.getString("freezeType");
            String forbidPurchaseFlag = params.getString("forbidPurchaseFlag");
            String forbidPaymentFlag = params.getString("forbidPaymentFlag");
            Date date = new Date(System.currentTimeMillis());
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");

            // 保存冻结信息判断
            if (null == params.getInteger("frozenId")) {
                boolean flag = false;
                flag = findExistRecovery(params.get("supplierId"), params.get("frozenId"), freezeType);
                // 检查是否存在该供应商且状态不等于已核准的恢复单据
                if (flag) {
                    if ("RECOVERY".equals(freezeType)) {
                        return SToolUtils.convertResultJSONObj("E", "该供应商有未处理完的恢复单据,请先处理完成!", 0, null);
                    } else if ("FROZEN".equals(freezeType)) {
                        return SToolUtils.convertResultJSONObj("E", "该供应商有未处理完的冻结单据,请先处理完成!", 0, null);
                    }
                }

                frozenRow = new SrmPosFrozenInfoEntity_HI();
                if (freezeType.equals("FROZEN")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFDJ-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                } else if (freezeType.equals("RECOVERY")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFHF-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                }
                frozenRow.setSupplierId(params.getInteger("supplierId"));
            } else {
                // 判断存在就是修改
                frozenRow = srmPosFrozenInfoDAO_HI.findByProperty("frozenId", params.getInteger("frozenId")).get(0);
                freezeNumber = frozenRow.getFreezeNumber();
            }

            //验证字符串是否含有SQL关键字及字符，有则返回NULL
            if (SrmUtils.isContainSQL(freezeNumber)) {
                return null;
            }

            frozenRow.setForbidPaymentFlag(params.getString("forbidPaymentFlag"));
            frozenRow.setForbidPurchaseFlag(params.getString("forbidPurchaseFlag"));
            frozenRow.setFrozenFileId(params.getInteger("frozenFileId"));
            frozenRow.setDescription(params.getString("description"));
            frozenRow.setFreezeType(params.getString("freezeType"));
            frozenRow.setUnfrozenDate(params.getDate("unfrozenDate"));
            frozenRow.setFreezeStatus("APPROVING");
            frozenRow.setApproveBy(operatorUserId);
            frozenRow.setApproveDate(date);
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.saveOrUpdate(frozenRow);

            LOGGER.info("getFrozenId：" + frozenRow.getFrozenId());

            //取消原因
/*            List<SrmPosReasonsEntity_HI> reasonList = new ArrayList<SrmPosReasonsEntity_HI>();
            JSONArray valuesArray = params.getJSONArray("lineData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                SrmPosReasonsEntity_HI reasonRow = null;
                // 提交冻结原因判断
                if (valuesJson.getInteger("reasonsId") == null) {
                    reasonRow = new SrmPosReasonsEntity_HI();
                    reasonRow.setDocId(frozenRow.getFrozenId());
                } else {
                    // 判断存在就是修改
                    reasonRow = srmPosReasonsDAO_HI.getById(valuesJson.getInteger("reasonsId"));
                }

                reasonRow.setReasonCode(SToolUtils.object2String(valuesJson.get("reasonCode")));
                reasonRow.setReasonDescription(SToolUtils.object2String(valuesJson.get("reasonDescription")));
                reasonRow.setSelectedFlag(SToolUtils.object2String(valuesJson.get("selectedFlag")));
                reasonRow.setDocId(frozenRow.getFrozenId());
                reasonRow.setDocTable("SRM_POS_FROZEN_INFO");
                reasonRow.setOperatorUserId(operatorUserId);
                reasonList.add(reasonRow);
            }
            srmPosReasonsDAO_HI.saveOrUpdateAll(reasonList);*/
            StringBuffer hql = new StringBuffer();
            hql.append("from SrmPosFrozenInfoEntity_HI h where h.freezeNumber =");
            hql.append("'");
            hql.append(freezeNumber);
            hql.append("'");
            SrmPosFrozenInfoEntity_HI entity = srmPosFrozenInfoDAO_HI.get(hql, new HashMap<String, Object>());
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("dataType", "CLASS");
            paraMap.put("freezeType", freezeType);
            paraMap.put("operatorUserId", operatorUserId + "");
            paraMap.put("forbidPurchaseFlag", forbidPurchaseFlag);
            paraMap.put("forbidPaymentFlag", forbidPaymentFlag);
            paraMap.put("frozenId", entity.getFrozenId().toString());
            //
            //取消品类
/*            JSONArray classArray = params.getJSONArray("classData");
            List<?> classList = getDataHandler(classArray, paraMap);
            srmPosFrozenCategoriesDAO_HI.saveOrUpdateAll(classList);*/
            JSONArray siteArray = params.getJSONArray("siteData");
            paraMap.put("dataType", "SITES");
            List<?> siteList = getDataHandler(siteArray, paraMap);
            srmPosFrozenSitesDAO_HI.saveOrUpdateAll(siteList);
            JSONObject result = SToolUtils.convertResultJSONObj("S", "提交成功", 1, frozenRow);
            result.put("getFrozenId", frozenRow.getFrozenId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 审批供应商冻结信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApprovalFrozenInfo(JSONObject params) throws Exception {
        try {
            Integer frozenId = params.getInteger("frozenId");
            SrmPosFrozenInfoEntity_HI frozenRow = srmPosFrozenInfoDAO_HI.getById(Integer.parseInt(frozenId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            frozenRow.setFreezeStatus("APPROVED");
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.update(frozenRow);

            LOGGER.info("禁止付款:" + frozenRow.getForbidPaymentFlag());
            LOGGER.info("禁止采购:" + frozenRow.getForbidPurchaseFlag());

            String purchaseFlag = frozenRow.getForbidPaymentFlag();
            String paymentFlag = frozenRow.getForbidPurchaseFlag();
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("purchaseFlag", purchaseFlag);
            paraMap.put("paymentFlag", paymentFlag);
            paraMap.put("flag", "FROZEN");
            paraMap.put("frozenId", frozenId.toString());
            paraMap.put("operatorUserId", operatorUserId.toString());
            /**审批冻结品类*/
            //取消冻结品类
/*            JSONArray classArray = params.getJSONArray("classData");
            List<?> classList = updateDataHandler(classArray, "CLASS", paraMap);
            srmPosSupplierCategoriesDAO_HI.updateAll((List<?>) classList.get(0));
            srmPosFrozenCategoriesDAO_HI.updateAll((List<?>) classList.get(1));*/

            /**审批冻结地点*/
            JSONArray siteArray = params.getJSONArray("siteData");
            List<?> siteList = updateDataHandler(siteArray, "SITES", paraMap);
            srmPosFrozenSitesDAO_HI.saveOrUpdateAll(siteList);
            /**冻结供应商地点*/
            List<SrmPosSupplierSitesEntity_HI> list = updateDataHandlerSupperlierSite(siteArray, paraMap, operatorUserId);
            srmPosSupplierSitesDAO_HI.saveOrUpdateAll(list);
            //发送邮件-联系人
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", frozenRow.getSupplierId());
            if (contactsList != null && contactsList.size() > 0) {
                String title = "供应商冻结结果通知";
                String content = "<p>您好！</p><br/>贵公司的部分组织已经冻结，请在公司档案中查看！！";
                sendEmailToContacts(contactsList, title, content);
            }

            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", frozenRow.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                /*iSrmBaseNotifications.insertSrmBaseNotifications("组织/品类冻结", "您好！贵公司的部分品类和组织已经冻结，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.frozenInfoDetail", operatorUserId);*/
                iSrmBaseNotifications.insertSrmBaseNotifications("组织冻结", "您好！贵公司的部分组织已经冻结，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.frozenInfoDetail", operatorUserId);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, frozenRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 发送邮件
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
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
     * 审批冻结品类、冻结地点
     *
     * @param jsonArray
     * @param dataType
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> updateDataHandler(JSONArray jsonArray, String dataType, Map<String, String> paraMap) {
        List<SrmPosSupplierCategoriesEntity_HI> classList = new ArrayList<SrmPosSupplierCategoriesEntity_HI>();
        List<SrmPosFrozenCategoriesEntity_HI> frozenCategoriesList = new ArrayList<>();
        List<SrmPosFrozenSitesEntity_HI> siteList = new ArrayList<SrmPosFrozenSitesEntity_HI>();
        Integer operatorUserId = Integer.valueOf(paraMap.get("operatorUserId"));
        Integer frozenId = Integer.valueOf(paraMap.get("frozenId"));
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            if ("CLASS".equals(dataType)) {
                if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                    SrmPosSupplierCategoriesEntity_HI classRow = null;
                    Integer supplierCategoryId = jsonobj.getInteger("supplierCategoryId");
                    classRow = srmPosSupplierCategoriesDAO_HI.getById(supplierCategoryId);
                    classRow.setStatus("DISABLED");
                    classRow.setApprovedDate(null);
                    classRow.setInvalidDate(date);
                    classRow.setOperatorUserId(operatorUserId);
                    classList.add(classRow);

                    SrmPosFrozenCategoriesEntity_HI frozenCategories = null;
                    Integer frozenCategoryId = jsonobj.getInteger("frozenCategoryId");
                    frozenCategories = srmPosFrozenCategoriesDAO_HI.getById(frozenCategoryId);
                    if (frozenCategories == null) {
                        continue;
                    }
                    frozenCategories.setCategoryStatus("DISABLED");
                    frozenCategories.setOperatorUserId(operatorUserId);
                    frozenCategoriesList.add(frozenCategories);
                }
            }
            if ("SITES".equals(dataType)) {
                if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                    Integer supplierSiteId = jsonobj.getInteger("supplierSiteId");
                    SrmPosFrozenSitesEntity_HI siteRow = null;
                    StringBuffer hql = new StringBuffer();
                    hql.append("from SrmPosFrozenSitesEntity_HI h where h.frozenId = ");
                    hql.append(frozenId);
                    hql.append("and h.supplierSiteId = ");
                    hql.append(supplierSiteId);
                    siteRow = srmPosFrozenSitesDAO_HI.get(hql, new HashMap<String, Object>());
                    siteRow.setFrozeFlag("Y");
                    //冻结日期默认为当前时间
                    //siteRow.setUnfrozeDate(jsonobj.getDate("unfrozenDate"));
                    siteRow.setUnfrozeDate(date);
                    siteRow.setOperatorUserId(operatorUserId);
                    siteList.add(siteRow);
                }
            }
        }
        if ("CLASS".equals(dataType)) {
            List retList = new ArrayList<>();
            retList.add(classList);
            retList.add(frozenCategoriesList);
            return retList;
        } else {
            return siteList;
        }
    }

    /**
     * 更新供应商地点表
     *
     * @param jsonArray
     * @param
     * @param operatorUserId
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmPosSupplierSitesEntity_HI> updateDataHandlerSupperlierSite(JSONArray jsonArray, Map<String, String> paraMap, Integer operatorUserId) {
        List<SrmPosSupplierSitesEntity_HI> supperlierSiteList = new ArrayList<SrmPosSupplierSitesEntity_HI>();
        String flag = paraMap.get("flag");
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                SrmPosSupplierSitesEntity_HI supperlierSite = null;
                Integer supplierSiteId = jsonobj.getInteger("supplierSiteId");
                String actionPurchaseFlag = jsonobj.getString("actionPurchaseFlag");
                String actionPaymentFlag = jsonobj.getString("actionPaymentFlag");
                supperlierSite = srmPosSupplierSitesDAO_HI.getById(supplierSiteId);
                //冻结日期默认为当前时间
                //supperlierSite.setUnfrozeDate("FROZEN".equals(flag) ? jsonobj.getDate("unfrozenDate") : null);
                supperlierSite.setUnfrozeDate("FROZEN".equals(flag) ? date : null);
                supperlierSite.setFrozeFlag("FROZEN".equals(flag) ? "Y" : "N");
                //冻结单据：Y表示冻结采购=用户冻结地点的采购标志N
                if ("Y".equals(actionPurchaseFlag)) {
                    supperlierSite.setPurchaseFlag("N");
                }
                //冻结单据：Y表示冻结付款=用户冻结地点的付款标志N
                if ("Y".equals(actionPaymentFlag)) {
                    supperlierSite.setPaymentFlag("N");
                }
                supperlierSite.setOperatorUserId(operatorUserId);
                supperlierSiteList.add(supperlierSite);
            }
        }
        return supperlierSiteList;
    }

    /**
     * 供应商恢复数据处理
     *
     * @param jsonArray
     * @param
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> recoverDataHandler(JSONArray jsonArray, Map<String, String> paraMap) {
        String dataType = paraMap.get("dataType");
        String operatorUserId = paraMap.get("operatorUserId");
        String forbidPurchaseFlag = paraMap.get("forbidPurchaseFlag");
        String forbidPaymentFlag = paraMap.get("forbidPaymentFlag");

        List<SrmPosSupplierCategoriesEntity_HI> classList = new ArrayList<SrmPosSupplierCategoriesEntity_HI>();
        List<SrmPosFrozenCategoriesEntity_HI> frozenCategoriesList = new ArrayList<SrmPosFrozenCategoriesEntity_HI>();
        List<SrmPosFrozenSitesEntity_HI> siteList = new ArrayList<SrmPosFrozenSitesEntity_HI>();
        JSONObject jsonobj = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonobj = jsonArray.getJSONObject(i);
            if (jsonobj != null && jsonobj.getString("selectedFlag") != null && "Y".equals(jsonobj.getString("selectedFlag"))) {
                if ("CLASS".equals(dataType)) {
                    SrmPosSupplierCategoriesEntity_HI classRow = null;
                    Integer supplierCategoryId = jsonobj.getInteger("supplierCategoryId");
                    classRow = srmPosSupplierCategoriesDAO_HI.getById(supplierCategoryId);
                    classRow.setStatus("EFFECTIVE");
                    classRow.setInvalidDate(null);
                    classRow.setApprovedDate(new Date());
                    classRow.setOperatorUserId(Integer.valueOf(operatorUserId));
                    classList.add(classRow);

                    SrmPosFrozenCategoriesEntity_HI frozenCategories = null;
                    Integer frozenCategoryId = jsonobj.getInteger("frozenCategoryId");
                    frozenCategories = srmPosFrozenCategoriesDAO_HI.getById(frozenCategoryId);
                    frozenCategories.setCategoryStatus("EFFECTIVE");
                    frozenCategories.setOperatorUserId(Integer.valueOf(operatorUserId));
                    frozenCategoriesList.add(frozenCategories);
                }
                if ("SITES".equals(dataType)) {
                    SrmPosFrozenSitesEntity_HI siteRow = null;
                    Integer frozenSiteId = jsonobj.getInteger("frozenSiteId");
                    siteRow = srmPosFrozenSitesDAO_HI.getById(frozenSiteId);
                    siteRow.setFrozeFlag("N");
                    siteRow.setUnfrozeDate(null);
                    if (null != forbidPurchaseFlag && forbidPurchaseFlag.equals("Y")) {
                        siteRow.setActionPurchaseFlag("Y");
                    }
                    if (null != forbidPaymentFlag && forbidPaymentFlag.equals("Y")) {
                        siteRow.setActionPaymentFlag("Y");
                    }
                    siteRow.setOperatorUserId(Integer.valueOf(operatorUserId));
                    siteList.add(siteRow);
                }
            }
        }
        if ("CLASS".equals(dataType)) {
            List retList = new ArrayList();
            retList.add(classList);
            retList.add(frozenCategoriesList);
            return retList;
        } else {
            return siteList;
        }
    }

    /**
     * 恢复供应商标记
     *
     * @param jsonArray
     * @param paraMap
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> recoverSupplierSiet(JSONArray jsonArray, Map<String, String> paraMap) {
        List<SrmPosSupplierSitesEntity_HI> supplierList = new ArrayList<SrmPosSupplierSitesEntity_HI>();
        String operatorUserId = paraMap.get("operatorUserId");
        String forbidPurchaseFlag = paraMap.get("forbidPurchaseFlag");
        String forbidPaymentFlag = paraMap.get("forbidPaymentFlag");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                SrmPosSupplierSitesEntity_HI supplierRow = null;
                Integer supplierSiteId = jsonobj.getInteger("supplierSiteId");
                String actionPurchaseFlag = jsonobj.getString("actionPurchaseFlag");
                String actionPaymentFlag = jsonobj.getString("actionPaymentFlag");
                supplierRow = srmPosSupplierSitesDAO_HI.getById(supplierSiteId);
                supplierRow.setFrozeFlag(null);
                supplierRow.setUnfrozeDate(null);
                supplierRow.setSiteStatus("EFFECTIVE");//合格
                //恢复逻辑与冻结一致
/*                if ("Y".equals(forbidPurchaseFlag)) {
                    supplierRow.setPurchaseFlag("Y");
                }
                if ("Y".equals(forbidPaymentFlag)) {
                    supplierRow.setPaymentFlag("Y");
                }*/
                if ("Y".equals(actionPurchaseFlag)) {
                    supplierRow.setPurchaseFlag("Y");
                }
                if ("Y".equals(actionPaymentFlag)) {
                    supplierRow.setPaymentFlag("Y");
                }
                supplierRow.setOperatorUserId(Integer.valueOf(operatorUserId));
                supplierList.add(supplierRow);
            }
        }
        return supplierList;
    }

    /**
     * 恢复冻结单标记
     *
     * @param jsonArray
     * @param paraMap
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> recoverFrozenSiet(JSONArray jsonArray, Map<String, String> paraMap) {
        List<SrmPosFrozenSitesEntity_HI> frozenList = new ArrayList<SrmPosFrozenSitesEntity_HI>();
        String operatorUserId = paraMap.get("operatorUserId");
        String forbidPurchaseFlag = paraMap.get("forbidPurchaseFlag");
        String forbidPaymentFlag = paraMap.get("forbidPaymentFlag");
        Integer frozenId = Integer.valueOf(paraMap.get("frozenId"));
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            SrmPosFrozenSitesEntity_HI frozenRow = null;
            Integer supplierSiteId = jsonobj.getInteger("supplierSiteId");
            StringBuffer hql = new StringBuffer();
            hql.append("from SrmPosFrozenSitesEntity_HI h where h.frozenId =");
            hql.append(frozenId);
            hql.append(" and h.supplierSiteId =");
            hql.append(supplierSiteId);
            frozenRow = srmPosFrozenSitesDAO_HI.get(hql, new HashMap<String, Object>());
            frozenRow.setFrozeFlag("N");
            frozenRow.setUnfrozeDate(null);
            frozenRow.setSiteStatus("QUALIFIED");//合格
            if (null != forbidPurchaseFlag && forbidPurchaseFlag.equals("Y")) {
                frozenRow.setActionPurchaseFlag(forbidPurchaseFlag);
            }
            if (null != forbidPaymentFlag && forbidPaymentFlag.equals("Y")) {
                frozenRow.setActionPaymentFlag(forbidPaymentFlag);
            }
            frozenRow.setOperatorUserId(Integer.valueOf(operatorUserId));
            frozenList.add(frozenRow);
        }
        return frozenList;
    }

    /**
     * 驳回供应商冻结、恢复信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateRejectFrozenInfo(JSONObject params) throws Exception {
        try {
            Integer frozenId = params.getInteger("frozenId");
            if (frozenId == null) {
                return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数frozenId", 0, null);
            }
            SrmPosFrozenInfoEntity_HI frozenRow = srmPosFrozenInfoDAO_HI.getById(Integer.parseInt(frozenId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            frozenRow.setFreezeStatus("REJECT");
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.update(frozenRow);
            //插入通知
            //判断是冻结还是恢复驳回
            if (null != params.getString("recoveryFlag") && "Y".equals(params.getString("recoveryFlag"))) {//恢复
                /*iSrmBaseNotifications.insertSrmBaseNotifications("组织/品类恢复", "您好！您所提交的供应商的恢复资料，单号：" + frozenRow.getFreezeNumber() + "；审批被驳回，请查看处理，谢谢！"
                        , frozenRow.getCreatedBy(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.recoveryInfoDetail", operatorUserId);*/
                iSrmBaseNotifications.insertSrmBaseNotifications("组织恢复", "您好！您所提交的供应商的恢复资料，单号：" + frozenRow.getFreezeNumber() + "；审批被驳回，请查看处理，谢谢！"
                        , frozenRow.getCreatedBy(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.recoveryInfoDetail", operatorUserId);
            } else {//冻结
                /*iSrmBaseNotifications.insertSrmBaseNotifications("组织/品类冻结", "您好！ 您所提交的供应商的冻结资料，单号：" + frozenRow.getFreezeNumber() + "；审批被驳回，请查看处理，谢谢！"
                        , frozenRow.getCreatedBy(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.frozenInfoDetail", operatorUserId);*/
                iSrmBaseNotifications.insertSrmBaseNotifications("组织冻结", "您好！ 您所提交的供应商的冻结资料，单号：" + frozenRow.getFreezeNumber() + "；审批被驳回，请查看处理，谢谢！"
                        , frozenRow.getCreatedBy(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.frozenInfoDetail", operatorUserId);
            }
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, frozenRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 审批供应商恢复信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApprovalRecoveryInfo(JSONObject params) throws Exception {
        // 若冻结采购标识为Y,则不允许提交;
        try {
            Integer frozenId = params.getInteger("frozenId");
            if (frozenId == null) {
                return SToolUtils.convertResultJSONObj("E",
                        "审批失败,请传入参数frozenId", 0, null);
            }
            SrmPosFrozenInfoEntity_HI frozenRow = srmPosFrozenInfoDAO_HI.getById(Integer.parseInt(frozenId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            String forbidPurchaseFlag = params.getString("forbidPurchaseFlag");
            String forbidPaymentFlag = params.getString("forbidPaymentFlag");
            frozenRow.setFreezeStatus("APPROVED");
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.update(frozenRow);

            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("dataType", "CLASS");
            paraMap.put("operatorUserId", operatorUserId + "");
            paraMap.put("forbidPurchaseFlag", forbidPurchaseFlag);
            paraMap.put("forbidPaymentFlag", forbidPaymentFlag);
            /**审批冻结品类*/
            //取消品类
/*            JSONArray classArray = params.getJSONArray("classData");
            List<?> classList = recoverDataHandler(classArray, paraMap);
            srmPosSupplierCategoriesDAO_HI.updateAll((List<?>) classList.get(0));
            srmPosFrozenCategoriesDAO_HI.updateAll((List<?>) classList.get(1));*/
            /**审批冻结地点*/
            JSONArray siteArray = params.getJSONArray("siteData");
            /**恢复供应商标记*/
            List<?> supplierList = recoverSupplierSiet(siteArray, paraMap);
            srmPosSupplierSitesDAO_HI.saveOrUpdateAll(supplierList);
            //发送邮件-联系人
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", frozenRow.getSupplierId());
            if (contactsList != null && contactsList.size() > 0) {
                String title = "供应商解冻结果通知";
                String content = "<p>您好！</p><br/>贵公司之前冻结的资料已经恢复，请在公司档案中查看！";
                sendEmailToContacts(contactsList, title, content);
            }


            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", frozenRow.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                /*iSrmBaseNotifications.insertSrmBaseNotifications("组织/品类恢复", "您好！贵公司之前冻结的资料已经恢复，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.recoveryInfoDetail", operatorUserId);*/
                iSrmBaseNotifications.insertSrmBaseNotifications("组织恢复", "您好！贵公司之前冻结的资料已经恢复，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.recoveryInfoDetail", operatorUserId);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, frozenRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 审批冻结品类、冻结地点
     *
     * @param jsonArray
     * @param dataType
     * @param
     * @return
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<?> updateDataHandlerJob(JSONArray jsonArray, String dataType, Map<String, String> paraMap) {
        List<SrmPosSupplierCategoriesEntity_HI> classList = new ArrayList<SrmPosSupplierCategoriesEntity_HI>();
        List<SrmPosFrozenCategoriesEntity_HI> frozenCategoriesList = new ArrayList<>();
        List<SrmPosFrozenSitesEntity_HI> siteList = new ArrayList<SrmPosFrozenSitesEntity_HI>();
        Integer operatorUserId = Integer.valueOf(paraMap.get("operatorUserId"));
        Integer frozenId = Integer.valueOf(paraMap.get("frozenId"));
        Date date = new Date(System.currentTimeMillis());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            if ("CLASS".equals(dataType)) {
                if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                    SrmPosSupplierCategoriesEntity_HI classRow = null;
                    Integer supplierCategoryId = jsonobj.getInteger("supplierCategoryId");
                    classRow = srmPosSupplierCategoriesDAO_HI.getById(supplierCategoryId);
                    classRow.setStatus("DISABLED");
                    classRow.setApprovedDate(null);
                    classRow.setInvalidDate(date);
                    classRow.setOperatorUserId(operatorUserId);
                    classList.add(classRow);

                    SrmPosFrozenCategoriesEntity_HI frozenCategories = null;
                    Integer frozenCategoryId = jsonobj.getInteger("frozenCategoryId");
                    frozenCategories = srmPosFrozenCategoriesDAO_HI.getById(frozenCategoryId);
                    if (frozenCategories == null) {
                        continue;
                    }
                    frozenCategories.setCategoryStatus("DISABLED");
                    frozenCategories.setOperatorUserId(operatorUserId);
                    frozenCategoriesList.add(frozenCategories);
                }
            }
            if ("SITES".equals(dataType)) {
                if ("Y".equals(jsonobj.getString("selectedFlag"))) {
                    Integer supplierSiteId = jsonobj.getInteger("supplierSiteId");
                    SrmPosFrozenSitesEntity_HI siteRow = null;
                    StringBuffer hql = new StringBuffer();
                    hql.append("from SrmPosFrozenSitesEntity_HI h where h.frozenId =");
                    hql.append(frozenId);
                    hql.append("and h.supplierSiteId = '");
                    hql.append(supplierSiteId);
                    hql.append("'");
                    siteRow = srmPosFrozenSitesDAO_HI.get(hql, new HashMap<String, Object>());
                    siteRow.setFrozeFlag("Y");
                    siteRow.setUnfrozeDate(jsonobj.getDate("unfrozenDate"));
                    siteRow.setOperatorUserId(operatorUserId);
                    siteList.add(siteRow);
                }
            }
        }
        if ("CLASS".equals(dataType)) {
            List retList = new ArrayList<>();
            retList.add(classList);
            retList.add(frozenCategoriesList);
            return retList;
        } else {
            return siteList;
        }
    }


    /**
     * 审批供应商恢复信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApprovalRecoveryJob(JSONObject params)
            throws Exception {
        // 若冻结采购标识为Y,则不允许提交;
        try {
            Integer frozenId = params.getInteger("frozenId");
            SrmPosFrozenInfoEntity_HI frozenRow = srmPosFrozenInfoDAO_HI.getById(Integer.parseInt(frozenId.toString()));
            Integer operatorUserId = params.getInteger("varUserId");
            String forbidPurchaseFlag = params.getString("forbidPurchaseFlag");
            String forbidPaymentFlag = params.getString("forbidPaymentFlag");
            frozenRow.setFreezeStatus("APPROVED");
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.update(frozenRow);
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("dataType", "CLASS");
            paraMap.put("operatorUserId", operatorUserId + "");
            paraMap.put("forbidPurchaseFlag", forbidPurchaseFlag);
            paraMap.put("forbidPaymentFlag", forbidPaymentFlag);
            /**审批冻结品类*/
            JSONArray classArray = params.getJSONArray("classData");
            List<?> classList = recoverDataHandler(classArray, paraMap);
            srmPosSupplierCategoriesDAO_HI.updateAll((List<?>) classList.get(0));
            srmPosFrozenCategoriesDAO_HI.updateAll((List<?>) classList.get(1));
            /**审批冻结地点*/
            JSONArray siteArray = params.getJSONArray("siteData");
            /**恢复供应商标记*/
            List<?> supplierList = recoverSupplierSiet(siteArray, paraMap);
            srmPosSupplierSitesDAO_HI.saveOrUpdateAll(supplierList);
            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", frozenRow.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("组织/品类恢复", "您好！贵公司之前冻结的资料已经恢复，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_frozen_info", frozenRow.getFrozenId(), "frozenId", "home.recoveryInfoDetail", operatorUserId);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, frozenRow);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 根据id查询供应商标识信息
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmPosFrozenInfoEntity_HI_RO> findSupplierFlag(
            Integer supplierId) throws Exception {
        LOGGER.info("根据id查询供应商标识信息，参数：" + supplierId);
        try {
            String sql = SrmPosFrozenInfoEntity_HI_RO.QUERY_FLAG_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            queryString.append(" and si.supplier_id  = :supplierId");
            map.put("supplierId", supplierId);
            List<SrmPosFrozenInfoEntity_HI_RO> list = srmPosFrozenInfoDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Description：保存
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeNumber  冻结单据号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * freezeItem  冻结的条目（SNBC_TYPE：地点或者品类）  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * freezeType  区分冻结和恢复单据  VARCHAR2  N
     * organizationId  库存组织id  NUMBER  N
     * frozenId  冻结单ID  NUMBER  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * freezeType  类型(POS_FREEZE_TYPE  冻结或解冻)  VARCHAR2  N
     * freezeNumber  冻结单号  VARCHAR2  N
     * freezeStatus  冻结单状态（POS_APPROVAL_STATUS）  VARCHAR2  N
     * freezingInstructions  (废弃)原因  VARCHAR2  N
     * forbidPurchaseFlag  禁止采购  VARCHAR2  N
     * forbidPaymentFlag  禁止付款  VARCHAR2  N
     * unfrozenDate  解冻时间  DATE  N
     * description  说明  VARCHAR2  N
     * frozenFileId  供应商冻结相关文件id  NUMBER  N
     * approveBy  审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveSupplierFrozenInfo(JSONObject params)
            throws Exception {
        try {
            SrmPosFrozenInfoEntity_HI frozenRow = null;
            Integer operatorUserId = params.getInteger("varUserId");
            String freezeType = params.getString("freezeType");
            String buttonFlag = params.getString("buttonFlag");
            String forbidPurchaseFlag = params.getString("forbidPurchaseFlag");
            String forbidPaymentFlag = params.getString("forbidPaymentFlag");
            Date date = new Date(System.currentTimeMillis());
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            Integer supplierId = params.getInteger("supplierId");
            String freezeNumber = null;
            // 保存冻结信息
            if (null == params.getInteger("frozenId")) {
                boolean flag = false;
                flag = findExistRecovery(params.get("supplierId"), params.get("frozenId"), freezeType);
                // 检查是否存在该供应商且状态不等于已核准的恢复单据
                if (flag) {
                    if ("RECOVERY".equals(freezeType)) {
                        return SToolUtils.convertResultJSONObj("E", "该供应商有未处理完的恢复单据,请先处理完成!", 0, null);
                    } else if ("FROZEN".equals(freezeType)) {
                        return SToolUtils.convertResultJSONObj("E", "该供应商有未处理完的冻结单据,请先处理完成!", 0, null);
                    }
                }

                frozenRow = new SrmPosFrozenInfoEntity_HI();
                if (freezeType.equals("FROZEN")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFDJ-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                } else if (freezeType.equals("RECOVERY")) {
                    freezeNumber = saafSequencesUtil.getDocSequences("SRM_POS_FROZEN_INFO", "GFHF-", dateFromate + "-", 4);
                    frozenRow.setFreezeNumber(freezeNumber);
                }
                frozenRow.setSupplierId(supplierId);
            } else {
                // 判断存在就是修改
                frozenRow = srmPosFrozenInfoDAO_HI.getById(params.getInteger("frozenId"));
                freezeNumber = frozenRow.getFreezeNumber();
            }
            frozenRow.setFreezeType(params.getString("freezeType"));
            if ("save".equals(buttonFlag)) {
                frozenRow.setFreezeStatus("DRAFT");
            } else {
                frozenRow.setFreezeStatus("APPROVING");
            }
            frozenRow.setForbidPurchaseFlag(forbidPurchaseFlag);
            frozenRow.setForbidPaymentFlag(forbidPaymentFlag);
            frozenRow.setUnfrozenDate(params.getDate("unfrozenDate"));
            frozenRow.setDescription(params.getString("description"));
            frozenRow.setFrozenFileId(params.getInteger("frozenFileId"));
            frozenRow.setApproveBy(operatorUserId);
            frozenRow.setApproveDate(date);
            frozenRow.setOperatorUserId(operatorUserId);
            srmPosFrozenInfoDAO_HI.saveOrUpdate(frozenRow);
            Integer frozenId = frozenRow.getFrozenId();
            //保存冻结原因
            //取消冻结原因
            /*List<SrmPosReasonsEntity_HI> reasonList = new ArrayList<SrmPosReasonsEntity_HI>();
            JSONArray valuesArray = params.getJSONArray("lineData");
            for (int i = 0; i < valuesArray.size(); i++) {
                JSONObject valuesJson = valuesArray.getJSONObject(i);
                SrmPosReasonsEntity_HI reasonRow = null;
                // 保存冻结原因判断
                if (valuesJson.getInteger("reasonsId") == null) {
                    reasonRow = new SrmPosReasonsEntity_HI();
                } else {
                    // 判断存在就是修改
                    reasonRow = srmPosReasonsDAO_HI.getById(valuesJson.getInteger("reasonsId"));
                }
                reasonRow.setReasonCode(valuesJson.getString("reasonCode"));
                reasonRow.setReasonDescription(valuesJson.getString("reasonDescription"));
                reasonRow.setSelectedFlag(valuesJson.getString("selectedFlag"));
                reasonRow.setDocId(frozenId);
                reasonRow.setDocTable("SRM_POS_FROZEN_INFO");
                reasonRow.setOperatorUserId(operatorUserId);
                reasonList.add(reasonRow);
            }
            srmPosReasonsDAO_HI.saveOrUpdateAll(reasonList);*/
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("dataType", "CLASS");
            paraMap.put("freezeType", freezeType);
            paraMap.put("operatorUserId", operatorUserId + "");
            paraMap.put("forbidPurchaseFlag", forbidPurchaseFlag);
            paraMap.put("frozenId", frozenRow.getFrozenId().toString());
            //冻结品类
            //取消冻结品类
/*            JSONArray classArray = params.getJSONArray("classData");
            List<SrmPosFrozenCategoriesEntity_HI> frozenClassList = new ArrayList<>();
            SrmPosFrozenCategoriesEntity_HI classRow = null;
            for (int i = 0; i < classArray.size(); i++) {
                JSONObject jsonobj = classArray.getJSONObject(i);
                Integer frozenCategoryId = jsonobj.getInteger("frozenCategoryId");
                if (null != frozenCategoryId) {
                    classRow = srmPosFrozenCategoriesDAO_HI.getById(frozenCategoryId);
                } else {
                    classRow = new SrmPosFrozenCategoriesEntity_HI();
                }
                classRow.setFrozenId(frozenId);
                classRow.setSupplierCategoryId(jsonobj.getInteger("supplierCategoryId"));
                classRow.setCategoryId(jsonobj.getInteger("categoryId"));
                classRow.setCategoryStatus(jsonobj.getString("statusType"));
                classRow.setSelectedFlag(jsonobj.getString("selectedFlag"));
                classRow.setOperatorUserId(operatorUserId);
                frozenClassList.add(classRow);
            }
            srmPosFrozenCategoriesDAO_HI.saveOrUpdateAll(frozenClassList);*/
            //冻结地址
            JSONArray siteArray = params.getJSONArray("siteData");
            paraMap.put("dataType", "SITES");

            List<SrmPosSupplierSitesEntity_HI_RO> siteList = null;
            StringBuffer addSite = new StringBuffer();
            Map<String, Object> addMap = new HashMap();
            if (freezeType.equals("RECOVERY")) {
                addSite.append(SrmPosSupplierSitesEntity_HI_RO.QUERY_SUPPLIER_SITES_LIST);

                //addSite.append(" AND (a.site_status = 'EFFECTIVE' OR a.site_status = 'INTRODUCING') AND a.froze_flag = 'Y'");
                addSite.append(" AND (a.site_status = 'EFFECTIVE') AND a.froze_flag = 'Y'");
                addSite.append(" and (a.froze_flag IS NULL OR a.froze_flag = '' OR a.froze_flag = :frozeFlag) and a.supplier_id = " + supplierId);
                addMap.put("frozeFlag", "Y");
                System.out.println(addSite);
                siteList = srmPosSupplierSitesDAO_HI_RO.findList(addSite.toString(), addMap);
            } else {
                addSite.append("SELECT t.supplier_site_id suppliersiteid, t.site_status sitestatus, t.temporary_site_flag temporarysiteflag FROM srm_pos_supplier_sites t WHERE t.site_status = 'EFFECTIVE' AND (t.froze_flag = 'N' OR t.froze_flag IS NULL) AND t.supplier_id = " + supplierId);
                siteList = srmPosSupplierSitesDAO_HI_RO.findList(addSite, addMap);
            }

            List<SrmPosFrozenSitesEntity_HI> frozenSitList = new ArrayList<>();
            SrmPosFrozenSitesEntity_HI frozenSite = null;
            if (null == params.getInteger("frozenId")) {
                for (SrmPosSupplierSitesEntity_HI_RO sites : siteList) {
                    frozenSite = new SrmPosFrozenSitesEntity_HI();
                    Integer supplierSiteId = sites.getSupplierSiteId();
                    for (int i = 0; i < siteArray.size(); i++) {
                        JSONObject jsonobj = siteArray.getJSONObject(i);
                        if (supplierSiteId.equals(jsonobj.getInteger("supplierSiteId"))) {
                            //恢复逻辑与冻结一致
/*                            if (freezeType.equals("RECOVERY")) {
                                frozenSite.setActionPurchaseFlag(jsonobj.getString("purchaseFlag"));
                                frozenSite.setActionPaymentFlag(jsonobj.getString("paymentFlag"));
                                frozenSite.setUnfrozeDate(jsonobj.getDate("unfrozeDate"));
                            } else {
                                frozenSite.setActionPurchaseFlag(jsonobj.getString("actionPurchaseFlag"));
                                frozenSite.setActionPaymentFlag(jsonobj.getString("actionPaymentFlag"));
                                frozenSite.setUnfrozeDate(frozenRow.getUnfrozenDate());
                            }*/
                            frozenSite.setActionPurchaseFlag(jsonobj.getString("actionPurchaseFlag"));
                            frozenSite.setActionPaymentFlag(jsonobj.getString("actionPaymentFlag"));
                            frozenSite.setUnfrozeDate(jsonobj.getDate("unfrozeDate"));

                            frozenSite.setSelectedFlag(jsonobj.getString("selectedFlag"));
                            frozenSite.setFrozeFlag("Y");

                        }
                    }
                    frozenSite.setFrozenId(frozenId);
                    frozenSite.setSupplierSiteId(sites.getSupplierSiteId());
                    frozenSite.setSiteStatus(sites.getSiteStatus());
                    frozenSite.setTemporarySiteFlag(sites.getTemporarySiteFlag());
                    frozenSite.setOperatorUserId(operatorUserId);
                    frozenSitList.add(frozenSite);
                }
            } else {
                for (int i = 0; i < siteArray.size(); i++) {
                    JSONObject jsonobj = siteArray.getJSONObject(i);
                    Integer frozenSiteId = jsonobj.getInteger("frozenSiteId");
                    frozenSite = srmPosFrozenSitesDAO_HI.getById(frozenSiteId);
                    if (frozenSite == null) {
                        continue;
                    }
                    frozenSite.setSelectedFlag(jsonobj.getString("selectedFlag"));
                    frozenSite.setActionPurchaseFlag(jsonobj.getString("actionPurchaseFlag"));
                    frozenSite.setActionPaymentFlag(jsonobj.getString("actionPaymentFlag"));
                    frozenSite.setFrozeFlag("Y");
                    frozenSite.setUnfrozeDate(frozenRow.getUnfrozenDate());
                    frozenSite.setOperatorUserId(operatorUserId);
                    frozenSitList.add(frozenSite);
                }
            }
            srmPosFrozenSitesDAO_HI.saveOrUpdateAll(frozenSitList);
            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, frozenRow);
            result.put("getFrozenId", frozenRow.getFrozenId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 查询冻结供应商LOV
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmPosFrozenInfoEntity_HI_RO> findFrozenOrRecoverySupplierLov(
            JSONObject params, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosFrozenInfoEntity_HI_RO.QUERY_FROZEN_OR_RECOVERY_SUPPLIER_LOV_SQL);
            SaafToolUtils.parperParam(params, "si.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(params, "si.supplier_status", "supplierStatus", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(params, "si.supplier_name", "supplierName", sb, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(params, "si.supplier_number", "supplierNumber", sb, queryParamMap, "LIKE");

            sb.append(" AND si.Supplier_Type IN (" + getSupplierType(params.getInteger("varUserId")) +")");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosFrozenInfoEntity_HI_RO> rowSet = srmPosFrozenInfoDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 检查是否存在该供应商且状态不等于已核准的恢复单据
     *
     * @param supplierId
     * @param frozenId
     * @return
     * @throws Exception
     * Update History
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public boolean findExistRecovery(Object supplierId, Object frozenId, String freezeType)
            throws Exception {
        try {
            int suppId = Integer.parseInt(supplierId.toString());
            Map<String, Object> map = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT t.frozen_id AS frozenId, t.supplier_id AS supplierId \n" +
                    "\t FROM srm_pos_frozen_info t \n" +
                    "\t WHERE t.freeze_status <> 'APPROVED' \n" +
                    "\t AND t.freeze_type =  \n" + "'" + freezeType + "'" +
                    "\t AND t.supplier_id = " + suppId);
            map.put("supplierId", suppId);
            map.put("freezeStatus", "APPROVED");
            List<SrmPosFrozenInfoEntity_HI_RO> rowSet = this.srmPosFrozenInfoDAO_HI_RO.findList(sb.toString(), map);
            if (rowSet.size() > 0) {
                SrmPosFrozenInfoEntity_HI_RO recovery = null;
                for (int i = 0; i < rowSet.size(); i++) {
                    recovery = rowSet.get(i);
                    if (frozenId != null && (!frozenId.equals(recovery.getFrozenId()) && supplierId.equals(recovery.getSupplierId()))) {
                        return true;
                    }
                    if (frozenId == null && supplierId.equals(recovery.getSupplierId())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
