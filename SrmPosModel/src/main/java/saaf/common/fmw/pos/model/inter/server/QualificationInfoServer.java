package saaf.common.fmw.pos.model.inter.server;

import com.alibaba.fastjson.JSON;
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
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafEmployeesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISaafBaseResultFile;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.ESBClientUtils;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.base.utils.enums.ESBParams;
import saaf.common.fmw.base.ws.service.EKPSyncService;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.message.email.utils.SendMailUtil;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.*;
import saaf.common.fmw.pos.model.inter.IQualificationInfo;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;
import saaf.common.fmw.utils.SrmUtils;

import javax.ws.rs.DefaultValue;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static saaf.common.fmw.services.CommonAbstractServices.ERROR_STATUS;
import static saaf.common.fmw.services.CommonAbstractServices.SUCCESS_STATUS;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：资质审查类
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Component("srmQualificationInfoServer")
public class QualificationInfoServer implements IQualificationInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(QualificationInfoServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private BaseViewObject<SrmPosQualificationInfoEntity_HI_RO> srmPosQualificationInfoDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosQualificationInfoEntity_HI> srmPosQualificationInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosQualificationCatesEntity_HI> srmPosQualificationCatesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosQualificationSitesEntity_HI> srmPosQualificationSitesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosSceneManageEntity_HI_RO> srmPosSceneManageDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosQualificationSitesEntity_HI_RO> srmPosQualificationSitesDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosQualificationCatesEntity_HI_RO> srmPosQualificationCatesDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ISupplierInfo iSupplierInfo;

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值
    @Autowired
    private ISaafBaseResultFile iSaafBaseResultFile;
    @Autowired
    private EKPSyncService ekpSyncService;

    @Autowired
    private ViewObject<SaafEmployeesEntity_HI> saafEmployeesDAO_HI;
    @Autowired
    private ViewObject<SrmPosSupplierContactsEntity_HI> srmPosSupplierContactsDAO_HI;

    private static SendMailUtil sendMailUtil = new SendMailUtil(true);
    @Autowired
    private BaseViewObject<SaafUserEmployeesEntity_HI_RO> saafUserEmployeesDAO_HI_RO;

    /**
     * 按条件查询资质审查列表
     *
     * @param parameters
     * @param pageIndex
     * @param pageRows
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationInfoList(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_SQL);
            Map<String, Object> map = new HashMap();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "qi.qualification_number", "qualificationNumber", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "qi.qualification_status", "qualificationStatus", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "si.supplier_name ", "supplierName", queryString, map, "LIKE");
            SaafToolUtils.parperParam(parameters, "qi.scene_type", "sceneType", queryString, map, "=");
            String creationDateFrom = parameters.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryString.append(" AND trunc(qi.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                map.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = parameters.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryString.append(" AND trunc(qi.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                map.put("creationDateTo", creationDateTo);
            }
            queryString.append(" AND qi.Supplier_Id IN\n" +
                    " (SELECT Psi.Supplier_Id\n" +
                    "          FROM Srm_Pos_Supplier_Info Psi\n" +
                    "         WHERE Psi.Supplier_Status IN ('APPROVED'\n" +
                    "                                      ,'EFFETIVE'\n" +
                    "                                      ,'INTRODUCING'\n" +
                    "                                      ,'QUIT')\n" +
                    "           AND Psi.Supplier_Type IN (" + getSupplierType(parameters.getInteger("varUserId")) + "))");

            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY qi.creation_date desc");
            Pagination<SrmPosQualificationInfoEntity_HI_RO> qualificationList = srmPosQualificationInfoDAO_HI_RO
                    .findPagination(queryString,countSql, map, pageIndex, pageRows);
            return qualificationList;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private String getSupplierType(Integer userId) {
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        List<SrmBaseUserCategoriesEntity_HI_RO> userCategoriesList = srmBaseUserCategories.findUserCategories(json);
        Map map = new HashMap();
        map.put("lookupType", "POS_SUPPLIER_TYPE");
        map.put("enabledFlag", "Y");
        List<SaafLookupValuesEntity_HI> lookupValues = saafLookupValuesDAO_HI.findByProperty(map);
        List categoryCode = new ArrayList();
        for (SaafLookupValuesEntity_HI vo : lookupValues) {
            for (SrmBaseUserCategoriesEntity_HI_RO ro : userCategoriesList) {
                if (ro.getCategoryCode().equals(vo.getLookupCode())) {
                    categoryCode.add(ro.getCategoryCode());
                }
            }
        }
        String supplierType = String.valueOf(categoryCode.stream().distinct().collect(Collectors.joining("','")));
        supplierType = "'" + supplierType + "'";
        return supplierType;
    }

    /**
     * Description：保存或提交资质审查
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * qualificationId  资质审查ID  NUMBER  Y
     * qualificationNumber  资质审查单号  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
     * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
     * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
     * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
     * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
     * temporaryQualifiedDate  废弃)临时合格至  DATE  N
     * qualificationFileId  资质审查相关附件  NUMBER  N
     * approveBy  (备用)审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * description  说明  VARCHAR2  N
     * qualityAgreement  签订质量协议  VARCHAR2  N
     * procurementAgreement  签订采购协议  VARCHAR2  N
     * oaNum  oa审批编号  VARCHAR2  N
     * processId    NUMBER  N
     * needSampleTrial  (废弃)是否需要样品试验  VARCHAR2  N
     * sampleTrialResult  (废弃)样品试验是否通过  VARCHAR2  N
     * reasonNoInvestigation  废弃)不考察原因  VARCHAR2  N
     * temporaryQualifiedDate  废弃)临时合格至  DATE  N
     * qualificationFileId  资质审查相关附件  NUMBER  N
     * approveBy  (备用)审批人  NUMBER  N
     * approveDate  审批时间  DATE  N
     * description  说明  VARCHAR2  N
     * reportAppendixFileId  现场考察报告附件  NUMBER  N
     * projectReportFileId  样板工程报告附件  NUMBER  N
     * qualificationId  资质审查ID  NUMBER  Y
     * qualificationNumber  资质审查单号  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * supplierAddrId  (废弃)地址ID，关联表:srm_pos_supplier_addrs  NUMBER  N
     * orgId  (废弃)业务实体ID,关联表:srm_base_orgs  NUMBER  N
     * enterpriseType  (废弃)企业性质(POS_ENTERPRISE_TYPE)  VARCHAR2  N
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationStatus  资质审查状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * qualificationResult  资质审查结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * needOnsiteInspection  (废弃)是否需要现场考察  VARCHAR2  N
     * inspectionResult  (废弃)现场考察是否通过  VARCHAR2  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject saveQualificationInfo(JSONObject params) throws Exception {
        LOGGER.info("保存资质审查信息，参数：" + params.toString());
        SrmPosQualificationInfoEntity_HI qualificationRow = null;
        try {
            boolean repeatFlag = true;
            repeatFlag = findExistQualification(params);
            if (repeatFlag) {
                return SToolUtils.convertResultJSONObj("E", "该供应商还有同一业务类型的单据没有完成，请先完成前面的单据！", 0, null);
            }

            Integer operatorUserId = params.getInteger("varUserId");
            Integer qualificationId = params.getInteger("qualificationId");
            String action = params.getString("action");
            if (action == null || "".equals(action.trim())) {
                action = "SAVE";
            }
            if(params.get("supplierId")==null){
                return SToolUtils.convertResultJSONObj("E", "请选择供应商", 0, null);
            }
            if(params.get("sceneType")==null){
                return SToolUtils.convertResultJSONObj("E", "请选择业务类型！", 0, null);
            }
            if(params.get("qualificationResult")==null){
                return SToolUtils.convertResultJSONObj("E", "请选择审查结果！", 0, null);
            }

            boolean newFlag = true;
            Date date = new Date();
            // 资质审查ID不存在，就是新增，否则就是修改
            if (qualificationId == null) {
                qualificationRow = new SrmPosQualificationInfoEntity_HI();
                String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
                //String qualificationNumber = saafSequencesUtil.getDocSequences("SRM_POS_QUALIFICATION_INFO", "ZZSC-", dateFromate, 4);
                String qualificationNumber = saafSequencesUtil.getDocSequences("SRM_POS_QUALIFICATION_INFO", "YR-", dateFromate, "-", 4);
                qualificationRow.setQualificationNumber(qualificationNumber);
                qualificationRow.setSupplierId(params.getInteger("supplierId"));
            } else {
                newFlag = false;
                qualificationRow = srmPosQualificationInfoDAO_HI.findByProperty("qualificationId", params.getInteger("qualificationId")).get(0);
                if ("SUBMITTED".equals(qualificationRow.getQualificationStatus()) || "APPROVED".equals(qualificationRow.getQualificationStatus())) {
                    return SToolUtils.convertResultJSONObj("E", "已提交或已批准的资质审查单据，不允许保存或提交！", 0, null);
                }
            }
            qualificationRow.setSceneType(params.getString("sceneType"));
            qualificationRow.setQualificationResult(params.getString("qualificationResult"));
            qualificationRow.setNeedOnsiteInspection(params.getString("needOnsiteInspection"));
            qualificationRow.setNeedSampleTrial(params.getString("needSampleTrial"));
            qualificationRow.setQualificationFileId(params.getInteger("qualificationFileId"));
            qualificationRow.setReportAppendixFileId(params.getInteger("reportAppendixFileId"));
            qualificationRow.setProjectReportFileId(params.getInteger("projectReportFileId"));
            qualificationRow.setDescription(params.getString("description"));
            qualificationRow.setQualificationStatus("DRAFT");
            qualificationRow.setApproveBy(operatorUserId);
            qualificationRow.setApproveDate(new Date());
            qualificationRow.setOperatorUserId(operatorUserId);
            srmPosQualificationInfoDAO_HI.saveOrUpdate(qualificationRow);
            qualificationId = qualificationRow.getQualificationId();
            LOGGER.info("getQualificationId：" + qualificationId);

            //保存新引入的供应商地点
            JSONArray newSiteArray = params.getJSONArray("newSiteData");
            if (newSiteArray != null) {
                List<SrmPosQualificationSitesEntity_HI> newSiteList = JSON.parseArray(newSiteArray.toJSONString(), SrmPosQualificationSitesEntity_HI.class);
                List<SrmPosQualificationSitesEntity_HI> qualificationSitesEntityList = new ArrayList<SrmPosQualificationSitesEntity_HI>();
                SrmPosQualificationSitesEntity_HI qualificationSitesEntity = null;
                for (SrmPosQualificationSitesEntity_HI effSite : newSiteList) {
                    if (effSite.getQualificationSiteId() != null && effSite.getQualificationSiteId() > 0) {
                        qualificationSitesEntity = srmPosQualificationSitesDAO_HI.getById(effSite.getQualificationSiteId());
                        qualificationSitesEntity.setAddFlag(effSite.getAddFlag());
                        qualificationSitesEntity.setSiteName(effSite.getSiteName());
                    } else {
                        qualificationSitesEntity = effSite;
                        qualificationSitesEntity.setQualificationId(qualificationId);
                    }
                    qualificationSitesEntity.setOperatorUserId(operatorUserId);
                    qualificationSitesEntityList.add(qualificationSitesEntity);
                }
                srmPosQualificationSitesDAO_HI.saveOrUpdateAll(qualificationSitesEntityList);
            }

            //保存已存在有效的供应商地点，只有当第一次新增时进行保存
            if (newFlag) {
                JSONArray effSiteArray = params.getJSONArray("effSiteData");
                if (effSiteArray != null) {
                    List<SrmPosQualificationSitesEntity_HI> effSiteList = JSON.parseArray(effSiteArray.toJSONString(), SrmPosQualificationSitesEntity_HI.class);
                    for (SrmPosQualificationSitesEntity_HI effSite : effSiteList) {
                        effSite.setQualificationId(qualificationId);
                        effSite.setAddFlag("A");
                        effSite.setOperatorUserId(operatorUserId);
                    }
                    srmPosQualificationSitesDAO_HI.saveOrUpdateAll(effSiteList);
                }
            }

            //保存新引入的品类
            JSONArray newCategoryArray = params.getJSONArray("newCategoryData");
            if (newCategoryArray != null) {
                List<SrmPosQualificationCatesEntity_HI> newCategoryList = JSON.parseArray(newCategoryArray.toJSONString(), SrmPosQualificationCatesEntity_HI.class);
                for (SrmPosQualificationCatesEntity_HI newCategory : newCategoryList) {
                    newCategory.setQualificationId(qualificationId);
                    newCategory.setAddFlag("Y");
                    newCategory.setOperatorUserId(operatorUserId);
                }
                srmPosQualificationCatesDAO_HI.saveOrUpdateAll(newCategoryList);
            }

            //保存已存在的合格品类
            if (newFlag) {
                JSONArray effCategoryArray = params.getJSONArray("effCategoryData");
                if (effCategoryArray != null) {
                    List<SrmPosQualificationCatesEntity_HI> effCategoryList = JSON.parseArray(effCategoryArray.toJSONString(), SrmPosQualificationCatesEntity_HI.class);
                    for (SrmPosQualificationCatesEntity_HI effCategory : effCategoryList) {
                        effCategory.setQualificationId(qualificationId);
                        effCategory.setAddFlag("N");
                        effCategory.setOperatorUserId(operatorUserId);
                    }
                    srmPosQualificationCatesDAO_HI.saveOrUpdateAll(effCategoryList);
                }
            }

            JSONObject result = null;
            if ("SUBMIT".equals(action)) {
                int countSite = 0;
                List<SrmPosQualificationSitesEntity_HI> siteList = srmPosQualificationSitesDAO_HI.findByProperty("qualificationId", qualificationId);
                for (SrmPosQualificationSitesEntity_HI siteEntity : siteList) {
                    if ("Y".equals(siteEntity.getAddFlag())) {
                        countSite++;
                    }
                }
                //判断新引入的地点是否存在
                if (!"40".equals(qualificationRow.getSceneType()) && !"20".equals(qualificationRow.getSceneType())) {
                    if (countSite == 0) {
                        throw new RuntimeException("提交失败，必须包含至少一个新引入的地点！");
                        //return SToolUtils.convertResultJSONObj("E", "提交失败，必须包含至少一个新引入的地点！", 0, null);
                    }
                }
                int countCategory = 0;
                List<SrmPosQualificationCatesEntity_HI> categoryList = srmPosQualificationCatesDAO_HI.findByProperty("qualificationId", qualificationId);
                for (SrmPosQualificationCatesEntity_HI categoryEntity : categoryList) {
                    if ("Y".equals(categoryEntity.getAddFlag())) {
                        countCategory++;
                    }
                }
                //判断新引入的品类是否存在
                if (!"40".equals(qualificationRow.getSceneType()) && !"20".equals(qualificationRow.getSceneType())) {
                    if (countCategory == 0) {
                        throw new RuntimeException("提交失败，必须包含至少一个新引入的品类！");
                        //return SToolUtils.convertResultJSONObj("E", "提交失败，必须包含至少一个新引入的品类！", 0, null);
                    }
                }
                if ("40".equals(qualificationRow.getSceneType()) || "20".equals(qualificationRow.getSceneType())) {
                    if (countSite == 0 && countCategory == 0) {
                        throw new RuntimeException("提交失败，必须包含至少一个新引入的品类或新引入的地点！");
                        //return SToolUtils.convertResultJSONObj("E", "提交失败，必须包含至少一个新引入的品类或新引入的地点！", 0, null);
                    }
                }
                qualificationRow.setQualificationStatus("SUBMITTED");
                srmPosQualificationInfoDAO_HI.saveOrUpdate(qualificationRow);
                //
               savePosQualificationToEkp(qualificationRow.getQualificationId(),operatorUserId,params);
                result = SToolUtils.convertResultJSONObj("S", "提交资质审查成功", 1, qualificationRow);
            } else {
                result = SToolUtils.convertResultJSONObj("S", "保存资质审查成功", 1, qualificationRow);
            }

            result.put("qualificationId", qualificationRow.getQualificationId());
            return result;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
    }


    /**
     * Description：提交供应商引入发送至EKP系统
     *
     * @param qualificationId
     * @param userId
     * @return
     * @throws Exception
     * ==============================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-05-22     xiexiaoxia             创建
     * ==============================================================================
     */
    @Override
    public JSONObject savePosQualificationToEkp(Integer qualificationId, Integer userId,JSONObject params) throws Exception {
        if (null == qualificationId || "".equals(qualificationId)) {
            // return SToolUtils.convertResultJSONObj(ERROR_STATUS, "参数为空", 0, null);
            throw new RuntimeException("传输EKP所需参数为空！");
        }
        //查询快码值对应的流程模板Id
        String tempateId = "";
        Map<String, Object> slvMap = new HashMap<>();
        slvMap.put("lookupType", "SER_EKF_FD_DATA");
        slvMap.put("lookupCode", "SER_SUP_INFO");
        List<SaafLookupValuesEntity_HI> slvList = saafLookupValuesDAO_HI.findByProperty(slvMap);
        if (null == slvList || slvList.size() < 1) {
            //return SToolUtils.convertResultJSONObj(ERROR_STATUS, "没有对应的流程模板Id", 0, null);
            throw new RuntimeException("没有对应的流程模板Id！");
        }
        tempateId = slvList.get(0).getMeaning();
        //tempateId="138d683f6710a7b49250e35454393340";
        SrmPosQualificationInfoEntity_HI header = srmPosQualificationInfoDAO_HI.getById(qualificationId);

        if (null == header || "".equals(header)) {
            //return SToolUtils.convertResultJSONObj(ERROR_STATUS, "无此记录，已被删除", 0, null);
            throw new RuntimeException("无此记录，已被删除！");
        }

        String employeeNumber="";
        List<SaafEmployeesEntity_HI> employeeList=saafEmployeesDAO_HI.findByProperty("userId",userId);
        if(null==employeeList||employeeList.isEmpty()){
            //return SToolUtils.convertResultJSONObj(ERROR_STATUS, "该用户没有对应员工信息", 0, null);
            throw new RuntimeException("该用户没有对应员工信息！");
        }
        employeeNumber=employeeList.get(0).getEmployeeNumber();
        //employeeNumber="SN7892";
        if(null==employeeNumber||"".equals(employeeNumber)){
            //return SToolUtils.convertResultJSONObj(ERROR_STATUS, "查无此员工工号", 0, null);
            throw new RuntimeException("查无此员工工号！");
        }

        Map<String, Object> map = new HashMap<>();
        // 文件附件列表
        Map<String, Map<String, byte[]>> fileMap = new HashMap<>();
        //行附件列表
        JSONArray lineFileArray = new JSONArray();
        //头层的附件
        Map<String,byte[]> fileHeader = new HashMap<>();
        //首次提交EKP
        SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(header.getSupplierId());
        if (null == supplier || "".equals(supplier)) {
            LOGGER.error("查无此供应商", header.getSupplierId());
            //return SToolUtils.convertResultJSONObj(ERROR_STATUS, "查无此供应商", 0, header.getSupplierId());
            throw new RuntimeException("查无此供应商");
        }

        StringBuffer queryString = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_TOEKP);
        queryString.append(" and  spqi.Qualification_Id="+qualificationId );
        List<SrmPosQualificationInfoEntity_HI_RO> list = srmPosQualificationInfoDAO_HI_RO.findList(queryString.toString());
        if (null == list || list.isEmpty()) {
            //+return SToolUtils.convertResultJSONObj(ERROR_STATUS, "无此记录，已被删除", 0, null);
            throw new RuntimeException("无此记录，已被删除");
        }

        Map<String,byte[]> report = new HashMap<>();
        if(null != list.get(0).getReportAppendixFileId()&& !"".equals(list.get(0).getReportAppendixFileId())){
            //现场考察报告
            report.put(params.getString("reportAppendixFileName"),iSaafBaseResultFile.findFileToByte(list.get(0).getReportAppendixFileId()));
            //fileMap.put("fd_387c6a8b0c8eba",report);
        }

        Map<String,byte[]> headerFile = new HashMap<>();
        if(null != list.get(0).getProjectReportFileId()&& !"".equals(list.get(0).getProjectReportFileId())){
            //样板工程报告
            headerFile.put(params.getString("projectReportFileName"),iSaafBaseResultFile.findFileToByte(list.get(0).getProjectReportFileId()));
           // fileMap.put("fd_387c6a8c69177c",headerFile);
        }

        //地点
        List<String> siteNameList=new ArrayList<>();
        List<String> instCodeList=new ArrayList<>();
        List<String> instNameList=new ArrayList<>();
        StringBuffer siteString = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_SITE_TOEKP);
        siteString.append(" and  t.Qualification_Id="+qualificationId );
        List<SrmPosQualificationInfoEntity_HI_RO> siteList = srmPosQualificationInfoDAO_HI_RO.findList(siteString.toString());
        if(null!=siteList){
            for(SrmPosQualificationInfoEntity_HI_RO ro:siteList){
                siteNameList.add(ro.getSiteName());
                instCodeList.add(ro.getInstCode());
                instNameList.add(ro.getInstName());
            }
           // map.put("fd_387c6afd2ac1e6.fd_387c6b36e1202e", siteNameList);
           // map.put("fd_387c6afd2ac1e6.fd_387c6b3b22c448", instCodeList);
           // map.put("fd_387c6afd2ac1e6.fd_387c6b3b89de1a", instNameList);
        }


        //品类
        List<String> qualInfo=new ArrayList<>();
        StringBuffer qualInfoString = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_CATEGORY_TOEKP);
        qualInfoString.append(" and  pqc.Qualification_Id="+qualificationId );
        List<SrmPosQualificationInfoEntity_HI_RO> qualInfoList = srmPosQualificationInfoDAO_HI_RO.findList(qualInfoString.toString());
        if(null!=qualInfoList){
            for(SrmPosQualificationInfoEntity_HI_RO ro:qualInfoList){
                qualInfo.add(ro.getCategoryName());
            }
            //map.put("fd_38809eb18ff14e.fd_38809ee999ce22", qualInfo);
        }


        //查询快码
        Map<String, Object> mapV = new HashMap<>();
        mapV.put("lookupType", "SUPPLIER_INTRO_EKP_FIELD");
        List<SaafLookupValuesEntity_HI> lookupValueList = saafLookupValuesDAO_HI.findByProperty(mapV);
        if (!ObjectUtils.isEmpty(lookupValueList)) {
            String siteLineNumber = "";
            String qualInfoLineNumber = "";
            for (int i = 0; i < lookupValueList.size(); i++) {
                SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                if ("siteLineNumber".equals(interfaceFields.getTag())) {
                    siteLineNumber = interfaceFields.getLookupCode();
                }
                if ("qualInfoLineNumber".equals(interfaceFields.getTag())) {
                    qualInfoLineNumber = interfaceFields.getLookupCode();
                }
            }
            if(ObjectUtils.isEmpty(siteLineNumber)&&ObjectUtils.isEmpty(qualInfoLineNumber)){
                throw new UtilsException("请维护供应商引入EKP接口地点行序号及品类行序号字段");
            }

            for (int i = 0; i < lookupValueList.size(); i++) {
                SaafLookupValuesEntity_HI interfaceFields = lookupValueList.get(i);
                if ("qualificationNumber".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getQualificationNumber());
                }
                if ("supplierName".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getSupplierName());
                }
                if ("supplierTypeName".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getSupplierTypeName());
                }
                if ("addressName".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getAddressName());
                }
                if ("countryName".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getCountryName());
                }
                if ("detailAddress".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getDetailAddress());
                }
                if ("examineResultName".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getExamineResultName());
                }
                if ("description".equals(interfaceFields.getTag())) {
                    map.put(interfaceFields.getLookupCode(), list.get(0).getDescription());
                }

                if ("reportAppendixFile".equals(interfaceFields.getTag())) {
                    fileMap.put(interfaceFields.getLookupCode(),report);
                }

                if ("projectReportFile".equals(interfaceFields.getTag())) {
                    fileMap.put(interfaceFields.getLookupCode(),headerFile);
                }
                if ("siteName".equals(interfaceFields.getTag())) {
                    map.put(siteLineNumber + "." + interfaceFields.getLookupCode(), siteNameList);
                }
                if ("instCode".equals(interfaceFields.getTag())) {
                    map.put(siteLineNumber + "." + interfaceFields.getLookupCode(), instCodeList);
                }
                if ("instName".equals(interfaceFields.getTag())) {
                    map.put(siteLineNumber + "." + interfaceFields.getLookupCode(), instNameList);
                }
                if ("qualInfo".equals(interfaceFields.getTag())) {
                    map.put(qualInfoLineNumber + "." + interfaceFields.getLookupCode(), qualInfo);
                }
            }
        }




        /*map.put("fd_387c69f747959c", list.get(0).getQualificationNumber());
        map.put("fd_387c6a9555d77a", list.get(0).getSupplierName());
        map.put("fd_387c6aa7f4cf7c", list.get(0).getSupplierTypeName());
        map.put("fd_387c6a98f2eaae", list.get(0).getAddressName());
        map.put("fd_387c6a9c1559b8", list.get(0).getCountryName());
        map.put("fd_387c6aa2864d4e", list.get(0).getDetailAddress());
        map.put("fd_387c6a6c55e23e", list.get(0).getExamineResultName());*/

       /* JSONArray qualificationNumber = new JSONArray(1);//供应商引入单号
        JSONArray supplierName = new JSONArray(1);//供应商名称
        JSONArray supplierType = new JSONArray(1);//供应商类型
        JSONArray  addressName = new JSONArray(1);//供应商地址名称
        JSONArray  countryName = new JSONArray(1);//国家
        JSONArray  detailAddress = new JSONArray(1);//详细地址
        JSONArray  examineResultName = new JSONArray(1);//审查结果
        if(null!=list){
            qualificationNumber.add(list.get(0).getQualificationNumber());
            supplierName.add(list.get(0).getSupplierName());
            supplierType.add(list.get(0).getSupplierType());
            addressName.add(list.get(0).getAddressName());
            countryName.add(list.get(0).getCountryName());
            detailAddress.add(list.get(0).getDetailAddress());
            examineResultName.add(list.get(0).getExamineResultName());
        }

        map.put("fd_387c69f747959c", qualificationNumber);
        map.put("fd_387c6a9555d77a", supplierName);
        map.put("fd_387c6aa7f4cf7c", supplierType);
        map.put("fd_387c6a98f2eaae", addressName);
        map.put("fd_387c6a9c1559b8", countryName);
        map.put("fd_387c6aa2864d4e", detailAddress);
        map.put("fd_387c6a6c55e23e", examineResultName);*/





        //文档关键字
        List<String> descList = new ArrayList<String>(4);
        descList.add("【SRM】");
        descList.add("供应商引入");
        descList.add(list.get(0).getQualificationNumber());
        descList.add(list.get(0).getSupplierName());

        //调用EKP的启动流程
        String processId = ekpSyncService.createFlow("10", list.get(0).getAttribute1(), tempateId, "【SRM】供应商引入" + list.get(0).getQualificationNumber() + list.get(0).getSupplierName(),employeeNumber, descList, map, fileMap, lineFileArray,"POS");
        if (null == processId || "".equals(processId)) {
            //  LOGGER.error("EKP没有返回值", processId);
            throw new UtilsException("EKP没有返回值" + processId);
        }
        Boolean flag = ekpSyncService.isJSONValid(processId);
        if (flag) {
            //json对象
            // LOGGER.error("EKP返回值错误", processId);
            throw new UtilsException("EKP返回值错误" + processId);
        }
        header.setAttribute1(processId);
        header.setOperatorUserId(userId);
        srmPosQualificationInfoDAO_HI.saveOrUpdate(header);
        srmPosQualificationInfoDAO_HI.fluch();
        return SToolUtils.convertResultJSONObj(SUCCESS_STATUS, "操作成功", 0, processId);
    }


    /**
     * 删除资质审查
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public JSONObject deleteQualificationInfo(JSONObject params) throws Exception {
        LOGGER.info("删除资质审查信息，参数：" + params.toString());
        try {
            SrmPosQualificationInfoEntity_HI qualificationRow = null;
            Integer qualificationId = params.getInteger("qualificationId");
            if (qualificationId != null && qualificationId > 0) {
                List qualificationList = srmPosQualificationInfoDAO_HI.findByProperty("qualificationId", qualificationId);
                if (qualificationList != null && qualificationList.size() > 0) {
                    qualificationRow = (SrmPosQualificationInfoEntity_HI) qualificationList.get(0);
                    if (!("DRAFT".equals(qualificationRow.getQualificationStatus()) || "REJECTED".equals(qualificationRow.getQualificationStatus()))) {
                        return SToolUtils.convertResultJSONObj("E", "非拟定或驳回状态的资质审查单据不允许删除！", 0, null);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除资质审查失败，" + params.getString("qualificationId") + "不存在！", 0, null);
                }

                List siteList = srmPosQualificationSitesDAO_HI.findByProperty("qualificationId", qualificationId);
                List categoryList = srmPosQualificationCatesDAO_HI.findByProperty("qualificationId", qualificationId);
                //删除引入的品类
                if (categoryList != null && categoryList.size() > 0) {
                    srmPosQualificationCatesDAO_HI.delete(categoryList);
                }
                //删除引入的地点
                if (siteList != null && siteList.size() > 0) {
                    srmPosQualificationSitesDAO_HI.delete(siteList);
                }
                //删除单据题头
                if (qualificationRow != null) {
                    srmPosQualificationInfoDAO_HI.delete(qualificationRow);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "删除资质审查成功", 1, null);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV:供应商名称
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosQualificationInfoEntity_HI_RO> findSupplierName(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosQualificationInfoEntity_HI_RO.LOV_SUPPLIER_QUERY_SQL);
            SaafToolUtils.parperParam(parameters, "si.supplier_id", "supplier_id", sb, queryParamMap, "=");// 如果是供应商查询
            SaafToolUtils.parperParam(parameters, "si.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "si.supplier_name", "supplierName", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(parameters, "si.supplier_number", "supplierNumber", sb, queryParamMap, "like");
            if (parameters.get("supplierStatus") != null) {
                String supplierStatus = parameters.getString("supplierStatus");
                if ("QUALIFICATION".equals(supplierStatus)) {
                    sb.append(" AND si.supplier_status IN ('APPROVED','INTRODUCING','EFFETIVE','QUIT' )\r\n");
                }
                if ("BLACKLIST".equals(supplierStatus)) {
                    sb.append(" AND si.supplier_status IN ('APPROVED','INTRODUCING','EFFETIVE','QUIT' )\r\n");
                }
                if ("GRADE".equals(supplierStatus)) {
                    sb.append(" AND si.supplier_status IN ('APPROVED','INTRODUCING','EFFETIVE')\r\n");
                    //sb.append(" AND si.grade_line_id IS NULL");
                }
                if ("NOTICE".equals(supplierStatus)) {
                    sb.append(" AND si.supplier_status IN ('APPROVED','INTRODUCING','EFFETIVE','QUIT' )\r\n");
                }
            }

            if (parameters.get("orgId") != null) {
                Integer orgId = parameters.getIntValue("orgId");
                sb.append(" AND EXISTS (SELECT 1 FROM srm_pos_supplier_sites ss WHERE ss.supplier_id = si.supplier_id\r\n" +
                        "AND ss.org_id = " + orgId + ")");
            }

            sb.append(" and si.Supplier_Type IN (" + getSupplierType(parameters.getInteger("varUserId")) + ")");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosQualificationInfoEntity_HI_RO> rowSet = srmPosQualificationInfoDAO_HI_RO
                    .findPagination(sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
    }

    /**
     * LOV:供应商名称,用于资质审查
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosQualificationInfoEntity_HI_RO> findSupplierNameForQualif(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosQualificationInfoEntity_HI_RO.LOV_SUPPLIER_QUERY_SQL);
            if (null != parameters.getInteger("sceneType") && !"".equals(parameters.getString("sceneType"))) {
                String sceneType = parameters.getString("sceneType");
                /* 供应商引入 */
                if ("11".equals(sceneType) || "30".equals(sceneType)) {
                    sb.append(" AND si.supplier_status = 'APPROVED'\n");
                }
                /* 新增品类/组织 */
                if ("20".equals(sceneType)) {
                    sb.append(" AND si.supplier_status = 'EFFETIVE'\n");
                }
                /* 临时供应商转正式供应商 */
                if ("40".equals(sceneType)) {
                    sb = new StringBuffer();
                    sb.append(SrmPosQualificationInfoEntity_HI_RO.LOV_SUPPLIER_QUERY_FOR_QUALIF_SQL);
                    sb.append(" AND si.supplier_status = 'EFFETIVE'\n");
                }
                /* 退出供应商重新引入 */
                if ("50".equals(sceneType)) {
                    sb.append(" AND si.supplier_status = 'QUIT'\n");
                }
            }
            SaafToolUtils.parperParam(parameters, "si.supplier_id", "supplier_id", sb, queryParamMap, "=");// 如果是供应商查询
            SaafToolUtils.parperParam(parameters, "si.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "si.supplier_name", "supplierName", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(parameters, "si.supplier_number", "supplierNumber", sb, queryParamMap, "like");

            sb.append(" and si.Supplier_Type IN (" + getSupplierType(parameters.getInteger("varUserId")) + ")");

            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosQualificationInfoEntity_HI_RO> rowSet =
                    srmPosQualificationInfoDAO_HI_RO.findPagination(sb.toString(), countSql,queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV:资质审查单
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationNumber(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosQualificationInfoEntity_HI_RO.LOV_QUALIFICATION_QUERY_SQL);
            SaafToolUtils.parperParam(parameters, "t.qualification_number", "qualificationNumber", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(parameters, "t.qualification_status", "qualificationStatus", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "t.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "t.creation_date", "creationDate", sb, queryParamMap, "=");
            sb.append("\tAND t.`qualification_status` = 'APPROVED'\n");
            sb.append("\tAND t.`qualification_result` = 'PASS'\n");
            sb.append("\tAND qc.supplier_category_id NOT IN(\n" +
                    "\tSELECT spstc.supplier_category_id FROM srm_pos_sample_trials spst,srm_pos_sample_trial_cates spstc \n" +
                    "\tWHERE spst.trial_id = spstc.trial_id \n" +
                    "\tAND spstc.selected_flag = 'Y' \n" +
                    "\tAND spst.trials_status= 'APPROVED' \n" +
                    "\tAND spst.trials_result = 'PASS'and spst.supplier_id = " + parameters.getInteger("supplierId") + "\n" +
                    "\t)\n" +
                    "\tGROUP BY t.qualification_id");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosQualificationInfoEntity_HI_RO> rowSet = srmPosQualificationInfoDAO_HI_RO
                    .findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * LOV:资质审查单,使用在现场考察查找资质审查单
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Pagination<SrmPosQualificationInfoEntity_HI_RO> findQualificationNumberForReviews(
            JSONObject parameters, @DefaultValue("1") Integer pageIndex, @DefaultValue("10") Integer pageRows)
            throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap<String, Object>();
            StringBuffer sb = new StringBuffer();
            sb.append(SrmPosQualificationInfoEntity_HI_RO.LOV_QUALIFICATION_QUERY_FOR_REVIEWS_SQL);
            SaafToolUtils.parperParam(parameters, "t1.qualification_number", "qualificationNumber", sb, queryParamMap, "like");
            SaafToolUtils.parperParam(parameters, "t1.qualification_status", "qualificationStatus", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "t1.supplier_id", "supplierId", sb, queryParamMap, "=");
            SaafToolUtils.parperParam(parameters, "t1.creation_date", "creationDate", sb, queryParamMap, "=");
            sb.append(" AND t1.qualification_status = 'APPROVED'");
            sb.append(" AND t1.qualification_result = 'PASS'");
            sb.append(" GROUP BY t1.qualification_id ");
            String countSql = "select count(1) from (" + sb + ")";
            Pagination<SrmPosQualificationInfoEntity_HI_RO> rowSet = srmPosQualificationInfoDAO_HI_RO
                    .findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    /**
     * 根据id查询资质审查信息
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public List<SrmPosQualificationInfoEntity_HI_RO> findQualificationInfo(
            Integer qualificationId) throws Exception {
        LOGGER.info("根据id查询资质审查信息，参数：" + qualificationId);
        try {
            String sql = SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_SQL;
            StringBuffer queryString = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            queryString.append(" AND qi.qualification_id = :qualificationId");
            map.put("qualificationId", qualificationId);
            List<SrmPosQualificationInfoEntity_HI_RO> list = srmPosQualificationInfoDAO_HI_RO.findList(sql + queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 资质审查审批
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public JSONObject updateApprovalQualification(JSONObject params) throws Exception {
        Integer qualificationId = params.getInteger("qualificationId");
        if (qualificationId == null) {
            return SToolUtils.convertResultJSONObj("E", "审批失败，参数无效！", 0, null);
        }
        Integer operatorUserId = params.getInteger("varUserId");
        LOGGER.info("=operatorUserId=====》》》" + operatorUserId);
        try {
            SrmPosQualificationInfoEntity_HI qualificationRow = srmPosQualificationInfoDAO_HI.getById(Integer.parseInt(qualificationId.toString()));
            if (qualificationRow == null || (!"SUBMITTED".equals(qualificationRow.getQualificationStatus())&&!"REJECTED".equals(qualificationRow.getQualificationStatus()))) {
                return SToolUtils.convertResultJSONObj("E", "审批失败，单据不存在或单据非待审核及驳回状态！", 0, null);
            }

            int supplierId = qualificationRow.getSupplierId();
            Date currDate = new Date();

            //如果资质审查的结果是通过，如果不是通过，则只更新资质审查单据的状态为已批准即可
            if ("PASS".equals(qualificationRow.getQualificationResult())) {
                //查询场景类型，判断是否需要走现场考察和样品试验
                String sceneType = qualificationRow.getSceneType();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sceneType", sceneType);
                StringBuffer sb = new StringBuffer(SrmPosSceneManageEntity_HI_RO.YES_NO_SQL);
                sb.append(" WHERE t.scene_type = :sceneType ");
                List<SrmPosSceneManageEntity_HI_RO> sceneList = srmPosSceneManageDAO_HI_RO.findList(sb, map);
                if (sceneList == null || sceneList.size() <= 0) {
                    return SToolUtils.convertResultJSONObj("E", "审批失败，场景管理中没有维护当前场景类型！", 1, null);
                }
                SrmPosSceneManageEntity_HI_RO sceneRow = sceneList.get(0);

                //查询新引入的地点
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                queryParamMap.put("addFlag", "Y");
                queryParamMap.put("qualificationId", qualificationId);
                StringBuffer queryString = new StringBuffer();
                queryString.append(SrmPosQualificationSitesEntity_HI_RO.QUERY_SITE_BY_NOT_A);
                queryString.append(" AND pqs.add_flag = :addFlag");
                queryString.append(" AND pqs.qualification_id = :qualificationId");
                List<SrmPosQualificationSitesEntity_HI_RO> siteList = srmPosQualificationSitesDAO_HI_RO.findList(queryString.toString(), queryParamMap);
                //新增或更新供应商的地点
                SrmPosSupplierSitesEntity_HI sitesEntity = null;
                SrmPosQualificationSitesEntity_HI qualificationSitesEntity_hi = null;
                //List<SrmPosSupplierSitesEntity_HI> supplierSiteList = new ArrayList();
                for (SrmPosQualificationSitesEntity_HI_RO qualificationSitesEntity : siteList) {
                    Map<String, Object> queryMap1 = new HashMap<String, Object>();
                    queryMap1.put("supplierAddressId", qualificationSitesEntity.getSupplierAddressId());
                    queryMap1.put("orgId", qualificationSitesEntity.getOrgId());
                    queryMap1.put("supplierId", supplierId);
                    List<SrmPosSupplierSitesEntity_HI> existsList = srmPosSupplierSitesDAO_HI.findByProperty(queryMap1);
                    if (existsList != null && existsList.size() > 0) {
                        sitesEntity = existsList.get(0);
                        sitesEntity.setTemporarySiteFlag("N");
                        sitesEntity.setInvalidDate(null);
                    } else {
                        sitesEntity = new SrmPosSupplierSitesEntity_HI();
                        sitesEntity.setSupplierId(supplierId);
                        sitesEntity.setSupplierAddressId(qualificationSitesEntity.getSupplierAddressId());
                        sitesEntity.setOrgId(qualificationSitesEntity.getOrgId());
                    }
                    sitesEntity.setSiteName(qualificationSitesEntity.getSiteName());
                    //如果需要走现场考察和样品试验流程，状态为引入中，否则状态为合格
                    if ("Y".equals(sceneRow.getLocaleReviewFlag()) || "Y".equals(sceneRow.getSampleTrialsFlag())) {
                        sitesEntity.setSiteStatus("INTRODUCING");
                    } else {
                        sitesEntity.setSiteStatus("EFFECTIVE");
                        sitesEntity.setPurchaseFlag("Y");
                        sitesEntity.setPaymentFlag("Y");
                        sitesEntity.setQualifiedDate(currDate);
                    }
                    //临时引入
                    if ("30".equals(qualificationRow.getSceneType())) {
                        sitesEntity.setTemporarySiteFlag("Y");
                    }
                    if ("40".equals(qualificationRow.getSceneType())) {
                        sitesEntity.setTemporarySiteFlag("N");
                    }
                    sitesEntity.setOperatorUserId(operatorUserId);
                    srmPosSupplierSitesDAO_HI.saveOrUpdate(sitesEntity);
                    srmPosSupplierSitesDAO_HI.fluch();

                    //回写供应商地点ID
                    if (qualificationSitesEntity.getSupplierSiteId() == null) {
                        qualificationSitesEntity_hi = srmPosQualificationSitesDAO_HI.getById(qualificationSitesEntity.getQualificationSiteId());
                        qualificationSitesEntity_hi.setSupplierSiteId(sitesEntity.getSupplierSiteId());
                        qualificationSitesEntity_hi.setOperatorUserId(operatorUserId);
                        srmPosQualificationSitesDAO_HI.update(qualificationSitesEntity_hi);
                        srmPosQualificationSitesDAO_HI.fluch();
                    }
                }

                //查询新引入的品类
                Map<String, Object> queryParamMap1 = new HashMap<String, Object>();
                queryParamMap1.put("addFlag", "Y");
                queryParamMap1.put("qualificationId", qualificationId);
                StringBuffer queryString1 = new StringBuffer();
                queryString1.append(SrmPosQualificationCatesEntity_HI_RO.QUERY_CATEGORY_BY_ADDFLAG);
                queryString1.append(" AND pqc.add_flag = :addFlag");
                queryString1.append(" AND pqc.qualification_id = :qualificationId");
                List<SrmPosQualificationCatesEntity_HI_RO> categoryList = srmPosQualificationCatesDAO_HI_RO.findList(queryString1.toString(), queryParamMap1);
                //新增或更新供应商品类
                SrmPosSupplierCategoriesEntity_HI categoriesEntity = null;
                SrmPosQualificationCatesEntity_HI qualificationCatesEntity_hi = null;
                for (SrmPosQualificationCatesEntity_HI_RO qualificationCatesEntity : categoryList) {
                    Map<String, Object> queryMap2 = new HashMap<String, Object>();
                    queryMap2.put("categoryId", qualificationCatesEntity.getCategoryId());
                    queryMap2.put("supplierId", supplierId);
                    List<SrmPosSupplierCategoriesEntity_HI> existsList = srmPosSupplierCategoriesDAO_HI.findByProperty(queryMap2);
                    if (existsList != null && existsList.size() > 0) {
                        categoriesEntity = existsList.get(0);
                    } else {
                        categoriesEntity = new SrmPosSupplierCategoriesEntity_HI();
                        categoriesEntity.setSupplierId(supplierId);
                        categoriesEntity.setCategoryId(qualificationCatesEntity.getCategoryId());
                    }
                    //如果需要走现场考察和样品试验流程，状态为引入中，否则状态为合格
                    if ("Y".equals(sceneRow.getLocaleReviewFlag()) || "Y".equals(sceneRow.getSampleTrialsFlag())) {
                        categoriesEntity.setStatus("INTRODUCING");
                    } else {
                        if ("40".equals(qualificationRow.getSceneType())) {
                            categoriesEntity.setStatus("INTRODUCING");
                        } else {
                            categoriesEntity.setStatus("EFFECTIVE");
                        }
                        categoriesEntity.setApprovedDate(currDate);
                    }
                    categoriesEntity.setOperatorUserId(operatorUserId);
                    srmPosSupplierCategoriesDAO_HI.saveOrUpdate(categoriesEntity);
                    srmPosSupplierCategoriesDAO_HI.fluch();

                    //回写供应商类别ID
                    if (qualificationCatesEntity.getSupplierCategoryId() == null) {
                        qualificationCatesEntity_hi = srmPosQualificationCatesDAO_HI.getById(qualificationCatesEntity.getQualifCategoryId());
                        qualificationCatesEntity_hi.setSupplierCategoryId(categoriesEntity.getSupplierCategoryId());
                        qualificationCatesEntity_hi.setOperatorUserId(operatorUserId);
                        srmPosQualificationCatesDAO_HI.update(qualificationCatesEntity_hi);
                        srmPosQualificationCatesDAO_HI.fluch();
                    }
                }

                //更新供应商头层的状态
                SrmPosSupplierInfoEntity_HI supplierInfoEntity_hi = srmPosSupplierInfoDAO_HI.getById(supplierId);
                if (supplierInfoEntity_hi.getSupplierNumber() == null || "".equals(supplierInfoEntity_hi.getSupplierNumber().trim())) {
                    String supplierNumber = iSupplierInfo.getSupplierNumber();
                    supplierInfoEntity_hi.setSupplierNumber(supplierNumber);
                }
                if ("APPROVED".equals(supplierInfoEntity_hi.getSupplierStatus()) || "QUIT".equals(supplierInfoEntity_hi.getSupplierStatus())) {
                    //如果需要走现场考察和样品试验流程，状态为引入中，否则状态为合格
                    if ("Y".equals(sceneRow.getLocaleReviewFlag()) || "Y".equals(sceneRow.getSampleTrialsFlag())) {
                        supplierInfoEntity_hi.setSupplierStatus("INTRODUCING");
                    } else {
                        supplierInfoEntity_hi.setSupplierStatus("EFFETIVE");
                    }
                }
                supplierInfoEntity_hi.setOperatorUserId(operatorUserId);
                srmPosSupplierInfoDAO_HI.update(supplierInfoEntity_hi);
                srmPosSupplierInfoDAO_HI.fluch();
            }
            //更新单据状态为已批准
            qualificationRow.setQualificationStatus("APPROVED");
            qualificationRow.setOperatorUserId(operatorUserId);
            srmPosQualificationInfoDAO_HI.update(qualificationRow);
            srmPosQualificationInfoDAO_HI.fluch();

            //审批通过后发送EBS

            JSONObject jsonParams=new JSONObject();
            jsonParams.put("varUserId",operatorUserId);
            jsonParams.put("supplierId",qualificationRow.getSupplierId());
            iSupplierInfo.updateSrmPosSupplierInfoSendToEBS(jsonParams);

            //发送邮件-联系人
            List<SrmPosSupplierContactsEntity_HI> contactsList = srmPosSupplierContactsDAO_HI.findByProperty("supplierId", supplierId);
            if (contactsList != null && contactsList.size() > 0) {
                String title = "供应商引入结果通知";
                String content = "<p>您好！</p><br/>恭喜贵公司已成为我司的合格供应商，望合作愉快！";
                sendEmailToContacts(contactsList, title, content);
            }

            //发送邮件，创建人
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERLIST_SQL);
            queryString.append(" and su.user_id ="+qualificationRow.getCreatedBy());
            List<SaafUserEmployeesEntity_HI_RO> user = saafUserEmployeesDAO_HI_RO.findList(queryString.toString());
            String[] emailAddress = new String[user.size()];
            emailAddress[0] = user.get(0).getEmail();
            sendMailUtil.doSendHtmlEmail("供应商引入审批通知", "<p>您好！</p><br/>" + "<p>您提交的供应商引入流程，单据号：" + qualificationRow.getQualificationNumber()+"，已审批通过！", emailAddress);

            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", qualificationRow.getSupplierId());
           if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("资质审查", "您好！贵公司的资质审查已经审批通过，请在公司档案中查看！"
                        , usersEntity.getCreatedBy(), "srm_pos_qualification_info", qualificationRow.getQualificationId(), "qualificationId", "home.qualificationInfoDetail", operatorUserId);
            }

            return SToolUtils.convertResultJSONObj("S", "审批资质审查成功", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
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
     * 验证供应商编码是否存在
     *
     * @param supplierId
     * @param supplierNumber
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    public boolean findExistsNumber(int supplierId, String supplierNumber) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("supplierId", supplierId);
        map.put("supplierNumber", supplierNumber);
        List rowSet = this.srmPosSupplierInfoDAO_HI.findByProperty(map);
        if (rowSet != null && rowSet.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 资质审查驳回
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public JSONObject updateRejectQualification(JSONObject params) throws Exception {
        Integer qualificationId = params.getInteger("qualificationId");
        if (qualificationId == null) {
            return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数qualificationId", 0, null);
        }
        Integer operatorUserId = params.getInteger("varUserId");
        try {
            SrmPosQualificationInfoEntity_HI qualificationRow = srmPosQualificationInfoDAO_HI.getById(Integer.parseInt(qualificationId.toString()));
            qualificationRow.setQualificationStatus("REJECTED");
            qualificationRow.setOperatorUserId(operatorUserId);
            srmPosQualificationInfoDAO_HI.update(qualificationRow);
            //发送邮件，创建人
            StringBuffer queryString = new StringBuffer();
            queryString.append(SaafUserEmployeesEntity_HI_RO.QUERY_USERLIST_SQL);
            queryString.append(" and su.user_id ="+qualificationRow.getCreatedBy());
            List<SaafUserEmployeesEntity_HI_RO> user = saafUserEmployeesDAO_HI_RO.findList(queryString.toString());
            String[] emailAddress = new String[user.size()];
            emailAddress[0] = user.get(0).getEmail();
            sendMailUtil.doSendHtmlEmail("供应商引入驳回通知", "<p>您好！</p><br/>" + "<p>您提交的供应商引入流程，单据号：" + qualificationRow.getQualificationNumber()+"，已被驳回！", emailAddress);

            //插入通知
            iSrmBaseNotifications.insertSrmBaseNotifications("资质审查", "您好！ 您所提交的资质审查，单号：" + qualificationRow.getQualificationNumber() + "；被驳回，请查看处理，谢谢！"
                    , qualificationRow.getCreatedBy(), "srm_pos_qualification_info", qualificationRow.getQualificationId(), "qualificationId", "home.qualificationInfoDetail", operatorUserId);
            return SToolUtils.convertResultJSONObj("S", "驳回资质审查成功", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
    }

    /**
     * 查询考察品类信息
     *
     * @param jsonParams
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public List<SrmPosQualificationInfoEntity_HI_RO> findSrmReviewCategories(JSONObject jsonParams) throws Exception {
        LOGGER.info(JSONObject.toJSONString(jsonParams));
        Map<String, Object> queryParamMap = new HashMap();
        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(SrmPosQualificationInfoEntity_HI_RO.QUERY_REVIEW_CATEGORIES_SQL);
            SaafToolUtils.parperParam(jsonParams, "a.qualification_id", "qualificationId", queryString, queryParamMap, "=");
            queryParamMap.put("localeReviewId", jsonParams.getInteger("localeReviewId"));
            queryString.append(" AND a.add_flag = 'Y' ");
            List<SrmPosQualificationInfoEntity_HI_RO> rowSet = srmPosQualificationInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);
            return rowSet;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 供应商引入数量计算——按照年份/月份（供应商引入退出报表）
     *
     * @param currentYear
     * @param currentYearMonth
     * @param i
     * @return
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public Integer findSupplierIntroduceCount(String currentYear, String currentYearMonth, Integer i) {
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(currentYear) || SrmUtils.isContainSQL(currentYearMonth)) {
            return null;
        }
        Integer count = 0;
        StringBuilder sb = new StringBuilder(
                "SELECT t.supplier_id AS supplierId\n" +
                        "      ,MIN(t.lastFinishDate) lastFinishDate\n" +
                        "FROM   (SELECT tt.qualification_id\n" +
                        "              ,tt.supplier_id\n" +
                        "              ,tt.approve_date\n" +
                        "              ,get_qualification_success_lastfinishdate_f(tt.qualification_id,\n" +
                        "                                                          tt.supplier_id,\n" +
                        "                                                          tt.scene_type) AS lastFinishDate\n" +
                        "        FROM   srm_pos_qualification_info tt\n" +
                        "        WHERE  get_qualification_success_lastfinishdate_f(tt.qualification_id,\n" +
                        "                                                          tt.supplier_id,\n" +
                        "                                                          tt.scene_type) IS NOT NULL) t\n");
//        if (null != currentYear && !"".equals(currentYear)) {
//            sb.append(" WHERE trunc(t.lastFinishDate) = add_months(to_date('" + currentYear + "', 'yyyy-mm-dd'), -" + i + " * 12)");
//            sb.append(" AND NOT EXISTS(\n"
//                    + "SELECT spqi2.supplier_id\n"
//                    + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
//                    + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
//                    + "FROM srm_pos_qualification_info tt\n"
//                    + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi2\n"
//                    + "WHERE spqi2.supplier_id=t.supplier_id\n"
//                    + "AND YEAR(spqi2.lastFinishDate)<YEAR(DATE_SUB('" + currentYear + "', INTERVAL " + i + " YEAR))) ");
//        }
//        if (null != currentYearMonth && !"".equals(currentYearMonth)) {
//            sb.append(" WHERE trunc(t.lastFinishDate) = add_months(to_date('" + currentYearMonth + "', 'yyyy-mm-dd'), -" + i + ")");
//            sb.append(" AND NOT EXISTS(\n"
//                    + "SELECT spqi2.supplier_id\n"
//                    + "FROM(SELECT tt.qualification_id,tt.supplier_id,tt.approve_date,\n"
//                    + "get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) AS lastFinishDate\n"
//                    + "FROM srm_pos_qualification_info tt\n"
//                    + "WHERE get_qualification_success_lastFinishDate_f (tt.qualification_id,tt.supplier_id,tt.scene_type) IS NOT NULL) spqi2\n"
//                    + "WHERE spqi2.supplier_id=t.supplier_id\n"
//                    + "AND date_format(spqi2.lastFinishDate,'%Y-%m')<date_format(DATE_SUB('" + currentYearMonth + "', INTERVAL " + i + " MONTH),'%Y-%m')) ");
//        }
        sb.append(" GROUP BY t.supplier_id");
        List<SrmPosQualificationInfoEntity_HI_RO> list = srmPosQualificationInfoDAO_HI_RO.findList(sb.toString());
        if (null != list) {
            count = list.size();
        }
        return count;
    }

    /**
     * @return boolean
     * @Author HZZ
     * @Description 同一供应商同一业务类型检查是否有非已批准状态的重复资质审查账单
     * @Date 10:26 2018/11/8
     * @Param @param parameters
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    public boolean findExistQualification(JSONObject parameters) throws Exception {
        try {
            boolean flag = false;
            StringBuffer queryString = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_STATUS_SQL);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询过滤条件
            SaafToolUtils.parperParam(parameters, "si.supplier_id", "supplierId", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "qi.scene_type", "sceneType", queryString, map, "=");
            SaafToolUtils.parperParam(parameters, "qi.qualification_id", "qualificationId", queryString, map, "!=");
            queryString.append(" AND qi.qualification_status NOT IN ('APPROVED','ABANDONED') ");
            List<SrmPosQualificationInfoEntity_HI_RO> qualificationList = srmPosQualificationInfoDAO_HI_RO.findList(queryString, map);
            if (qualificationList!=null&&qualificationList.size() > 0) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * 保存ekpStatus
     *
     * @return
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-05-24     谢晓霞             创建
     * =======================================================================
     */
    @Override
    public void saveEkpStatus() throws Exception {
        StringBuffer sql = new StringBuffer(SrmPosQualificationInfoEntity_HI_RO.QUERY_QUALIFICATION_SITE_FROMEKP);
        List<SrmPosQualificationInfoEntity_HI_RO> list = srmPosQualificationInfoDAO_HI_RO.findList(sql.toString());
        if(null != list && list.size()>0){

            ESBClientUtils esb = new ESBClientUtils();
            JSONObject baseQueryParams = new JSONObject();
            JSONArray businessData = new JSONArray();
            for (SrmPosQualificationInfoEntity_HI_RO ro : list) {
                JSONObject businessJson = new JSONObject();
                businessJson.put("fdId",ro.getAttribute1());
                businessData.add(businessJson);
            }
            JSONObject result = esb.doPost(ESBParams.SystemName.PCB_SRM.getValue(), ESBParams.SystemName.SRM_SERVER.getValue(),
                    ESBParams.SystemName.EKP.getValue(), ESBParams.ServiceName.EKP_REVIEW_MAIN.getValue(),
                    null,"S",baseQueryParams,businessData);
            LOGGER.info("----------------------EKP查询返回结果: " + result);

            if(SUCCESS_STATUS.equalsIgnoreCase(result.getString("status"))) {
                if (null != result.getJSONObject("data")) {
                    JSONObject data = result.getJSONObject("data");
                    if (null != data.getJSONObject("obj")) {
                        JSONObject obj = data.getJSONObject("obj");
                        if (null != obj.getJSONArray("rows") && obj.getJSONArray("rows").size() > 0) {
                            JSONArray rows = obj.getJSONArray("rows");
                            LOGGER.info("----------------------供应商引入EKP审批,返回结果: " + rows);
                            for (int i = 0 ; i < rows.size(); i++) {
                                JSONObject row = rows.getJSONObject(i);
                                List<SrmPosQualificationInfoEntity_HI> rowList = srmPosQualificationInfoDAO_HI.findByProperty("attribute1", row.getString("fdId"));
                                if(null!=rowList && rowList.size()>0) {
                                    SrmPosQualificationInfoEntity_HI qualInfo = rowList.get(0);
                                    if(!"10".equals(row.getString("docStatus"))){
                                        JSONObject params = new JSONObject();
                                        JSONObject responseJson = new JSONObject();
                                        params.put("qualificationId",qualInfo.getQualificationId());
                                        params.put("varUserId",-999);
                                        if("30".equals(row.getString("docStatus"))){
                                            //qualInfo.setQualificationStatus("APPROVED");
                                            responseJson = updateApprovalQualification(params);
                                        }else if("11".equals(row.getString("docStatus"))&&!"REJECTED".equals(qualInfo.getQualificationStatus())){
                                            //qualInfo.setQualificationStatus("REJECTED");
                                            responseJson = updateRejectQualification(params);
                                        }else if("00".equals(row.getString("docStatus"))){
                                            //qualInfo.setQualificationStatus("ABANDONED");
                                            responseJson = updateAbandonedQualification(params);
                                        }else if("20".equals(row.getString("docStatus"))&&"REJECTED".equals(qualInfo.getQualificationStatus())){
                                            responseJson = updateSubmittdQualification(params);
                                        }
                                        if("S".equals(responseJson.getString("status"))){
                                            qualInfo.setAttribute2(row.getString("fdNumber"));
                                            qualInfo.setOperatorUserId(-999);
                                            srmPosQualificationInfoDAO_HI.saveEntity(qualInfo);
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new UtilsException("供应商引入结果查询EKP接口返回结果rows不能为空, 返回结果: " + result);
                        }
                    } else {
                        throw new UtilsException("供应商引入结果查询EKP接口返回结果obj为空, 返回结果: " + result);
                    }
                } else {
                    throw new UtilsException("供应商引入结果查询SRM调用EKP接口srm工具类返回结果data为空, 返回结果: " + result);
                }
            } else {
                throw new UtilsException("供应商引入结果查询EKP接口返回结果不为S, 返回结果: " + result);
            }
        }
    }
    /**
     * 资质审查废弃
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2019-04-15     liuwenjun             创建
     * ===========================================================================
     */
    @Override
    public JSONObject updateAbandonedQualification(JSONObject params) throws Exception {
        Integer qualificationId = params.getInteger("qualificationId");
        if (qualificationId == null) {
            throw new RuntimeException("废弃失败,请传入参数资质审查ID");
        }
        Integer operatorUserId = params.getInteger("varUserId");
            SrmPosQualificationInfoEntity_HI qualificationRow = srmPosQualificationInfoDAO_HI.getById(Integer.parseInt(qualificationId.toString()));
            qualificationRow.setQualificationStatus("ABANDONED");
            qualificationRow.setOperatorUserId(operatorUserId);
            srmPosQualificationInfoDAO_HI.update(qualificationRow);
            iSrmBaseNotifications.insertSrmBaseNotifications("资质审查", "您好！ 您所提交的资质审查，单号：" + qualificationRow.getQualificationNumber() + "；被废弃，请查看处理，谢谢！"
                    , qualificationRow.getCreatedBy(), "srm_pos_qualification_info", qualificationRow.getQualificationId(), "qualificationId", "home.qualificationInfoDetail", operatorUserId);
            return SToolUtils.convertResultJSONObj("S", "废弃资质审查成功", 1, null);

    }

    /**
     * 资质审查待审批
     *
     * @param params
     * @return
     * @throws Exception
     * ===========================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-07-10     谢晓霞             创建
     * ===========================================================================
     */
    @Override
    public JSONObject updateSubmittdQualification(JSONObject params) throws Exception {
        Integer qualificationId = params.getInteger("qualificationId");
        if (qualificationId == null) {
            throw new RuntimeException("更新待审批状态失败,请传入参数资质审查ID");
        }
        Integer operatorUserId = params.getInteger("varUserId");
        SrmPosQualificationInfoEntity_HI qualificationRow = srmPosQualificationInfoDAO_HI.getById(Integer.parseInt(qualificationId.toString()));
        qualificationRow.setQualificationStatus("SUBMITTED");
        qualificationRow.setOperatorUserId(operatorUserId);
        srmPosQualificationInfoDAO_HI.update(qualificationRow);
        iSrmBaseNotifications.insertSrmBaseNotifications("资质审查", "您好！ 您被驳回的资质审查，单号：" + qualificationRow.getQualificationNumber() + "；已在EKP中重新提交审批，请查看处理，谢谢！"
                , qualificationRow.getCreatedBy(), "srm_pos_qualification_info", qualificationRow.getQualificationId(), "qualificationId", "home.qualificationInfoDetail", operatorUserId);
        return SToolUtils.convertResultJSONObj("S", "提交资质审查成功", 1, null);

    }

}
