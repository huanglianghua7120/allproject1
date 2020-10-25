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
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosLocaleReviewsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSampleTrialsEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSceneManageEntity_HI_RO;
import saaf.common.fmw.pos.model.inter.ISrmPosLocaleReviews;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;

import java.util.*;

@Component("srmPosLocaleReviewsServer")
public class SrmPosLocaleReviewsServer implements ISrmPosLocaleReviews {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosLocaleReviewsServer.class);

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ViewObject<SrmPosLocaleReviewsEntity_HI> srmPosLocaleReviewsDAO_HI;

    @Autowired
    private ViewObject<SrmPosLocaleReviewSitesEntity_HI> srmPosLocaleReviewSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosLocaleReviewCatesEntity_HI> srmPosLocaleReviewCatesDAO_HI;

    @Autowired
    private BaseViewObject<SrmPosLocaleReviewsEntity_HI_RO> srmPosLocaleReviewsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSceneManageEntity_HI_RO> srmPosSceneManageDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSampleTrialsEntity_HI_RO> srmPosSampleTrialsDAO_HI_RO;

    @Autowired
    private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

    @Autowired
    private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

    @Autowired
    private ISupplierInfo iSupplierInfo;

    @Autowired
    private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

    @Autowired
    private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

    /**
     * 分页查询现场考察数据
     *
     * @param jsonParams
     * @param pageIndex
     * @param pageRows
     * @return
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmPosLocaleReviewsEntity_HI_RO> findSrmPosLocaleReviewsInfoList(JSONObject jsonParams,
                                                                                       Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sql = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.PAGE_SQL);
        SaafToolUtils.parperParam(jsonParams, "t2.supplier_name", "supplierName", sql, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t1.locale_review_number", "localeReviewNumber", sql, queryParamMap, "like");
        SaafToolUtils.parperParam(jsonParams, "t1.locale_review_status", "localeReviewStatus", sql, queryParamMap, "=");
        SaafToolUtils.parperParam(jsonParams, "t1.scene_type", "sceneType", sql, queryParamMap, "=");
        String creationDateFrom = jsonParams.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            sql.append(" AND trunc(t1.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = jsonParams.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            sql.append(" AND trunc(t1.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        String countSql = "select count(1) from (" + sql + ")";
        // 排序
        sql.append(" ORDER BY t1.creation_date DESC");
        Pagination<SrmPosLocaleReviewsEntity_HI_RO> page = srmPosLocaleReviewsDAO_HI_RO.findPagination(sql.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return page;
    }

    /**
     * Description：保存或更新现场考察数据
     *
     * =======================================================================
     * 参数名称      参数描述           数据类型     是否必填
     * localeReviewId  现场考察ID  NUMBER  Y
     * localeReviewNumber  现场考察  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID  NUMBER  N
     * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * reviewDate  考察时间  DATE  N
     * reviewMember  考察小组成员  VARCHAR2  N
     * supplierMember  供方成员  VARCHAR2  N
     * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * fileId  现场考察相关附件  NUMBER  N
     * approvedBy  (备用)审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     * localeReviewId  现场考察ID  NUMBER  Y
     * localeReviewNumber  现场考察  VARCHAR2  Y
     * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  Y
     * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
     * qualificationId  资质审查ID  NUMBER  N
     * investigationPlanLinesId  考察计划行ID，关联表:srm_pos_investigation_plan_lines  NUMBER  N
     * localeReviewStatus  现场考察状态(POS_APPROVAL_STATUS)  VARCHAR2  N
     * localeReviewResult  现场考察结果(POS_EXAMINE_RESULT)  VARCHAR2  N
     * reviewDate  考察时间  DATE  N
     * reviewMember  考察小组成员  VARCHAR2  N
     * supplierMember  供方成员  VARCHAR2  N
     * supplyFlag  是否已供货(Y/N)  VARCHAR2  N
     * description  说明  VARCHAR2  N
     * fileId  现场考察相关附件  NUMBER  N
     * approvedBy  (备用)审批人  NUMBER  N
     * approvedDate  审批时间  DATE  N
     *
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */

    @Override
    public JSONObject saveLocaleReviewsInfo(JSONObject params) throws Exception {
        LOGGER.info("保存现场考察信息，参数：" + params.toString());
        SrmPosLocaleReviewsEntity_HI reviewRow = null;
        try {
            if(params.get("supplierId")==null){
                return SToolUtils.convertResultJSONObj("E", "请选择供应商", 0, null);

            }
            if(params.get("sceneType")==null){
                return SToolUtils.convertResultJSONObj("E", "请选择业务类型", 0, null);

            }
            Integer operatorUserId = params.getInteger("varUserId");
            Date date = new Date();
            String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
            //查重
            boolean repeatFlag = true;
            repeatFlag = findExistLocaleReviews(params);
            if (repeatFlag) {
                return SToolUtils.convertResultJSONObj("E", "该供应商还有同一业务类型的单据没有完成，请先完成前面的单据！", 0, null);
            }

            // 保存考察数据信息判断
            if (null == params.getInteger("localeReviewId")) {
                reviewRow = new SrmPosLocaleReviewsEntity_HI();
                String localeReviewNumber = saafSequencesUtil.getDocSequences("SRM_POS_LOCALE_REVIEWS", "XCKC-", dateFromate, 4);
                reviewRow.setLocaleReviewNumber(localeReviewNumber);
                reviewRow.setLocaleReviewStatus("DRAFT");// 新增为拟定状态
            } else {
                // 判断存在就是修改
                reviewRow = srmPosLocaleReviewsDAO_HI.findByProperty("localeReviewId", params.getInteger("localeReviewId")).get(0);
            }

            // 如果为提交操作，则现场考察数据为SUBMITTED状态
            if ("SUBMIT".equals(params.getString("operate"))) {
                reviewRow.setLocaleReviewStatus("SUBMITTED");// 待审核
            }

            reviewRow.setSupplierId(params.getInteger("supplierId"));
            reviewRow.setSceneType(params.getString("sceneType"));
            reviewRow.setQualificationId(params.getInteger("qualificationId"));
            if (Integer.parseInt(params.getString("sceneType")) == 60) {
                reviewRow.setInvestigationPlanLinesId(params.getInteger("investigationPlanLinesId"));
            } else {
                reviewRow.setInvestigationPlanLinesId(null);
            }
            reviewRow.setLocaleReviewResult(params.getString("localeReviewResult"));
            reviewRow.setReviewDate(params.getDate("reviewDate"));
            reviewRow.setReviewMember(params.getString("reviewMember"));
            reviewRow.setSupplierMember(params.getString("supplierMember"));
            reviewRow.setFileId(params.getInteger("fileId"));
            reviewRow.setSupplyFlag(params.getString("supplyFlag"));
            reviewRow.setDescription(params.getString("description"));
            reviewRow.setCreatedBy(operatorUserId);
            reviewRow.setOperatorUserId(operatorUserId);
            srmPosLocaleReviewsDAO_HI.saveOrUpdate(reviewRow);

            LOGGER.info("getFrozenId：" + reviewRow.getLocaleReviewId());

            // 保存地点层
            List<SrmPosLocaleReviewSitesEntity_HI> siteList = new ArrayList<SrmPosLocaleReviewSitesEntity_HI>();
            JSONArray siteArray = params.getJSONArray("siteData");
            if (null != siteArray && siteArray.size() != 0) {
                SrmPosLocaleReviewSitesEntity_HI siteRow = null;
                for (int i = 0; i < siteArray.size(); i++) {
                    JSONObject siteJson = siteArray.getJSONObject(i);
                    // 保存冻结原因判断
                    if (siteJson.getInteger("localeReviewSiteId") == null) {
                        siteRow = new SrmPosLocaleReviewSitesEntity_HI();
                    } else {
                        // 判断存在就是修改
                        siteRow = srmPosLocaleReviewSitesDAO_HI.getById(siteJson.getInteger("localeReviewSiteId"));
                    }
                    siteRow.setLocaleReviewId(reviewRow.getLocaleReviewId());
                    siteRow.setSupplierSiteId(siteJson.getInteger("supplierSiteId"));
                    siteRow.setSupplierAddressId(siteJson.getInteger("supplierAddressId"));
                    siteRow.setSiteName(siteJson.getString("siteName"));
                    siteRow.setOrgId(siteJson.getInteger("orgId"));
                    siteRow.setSiteStatus(siteJson.getString("siteStatus"));
                    siteRow.setTemporarySiteFlag(siteJson.getString("temporarySiteFlag"));
                    siteRow.setSelectedFlag(siteJson.getString("selectedFlag"));
                    siteRow.setOperatorUserId(operatorUserId);
                    siteList.add(siteRow);
                }
                srmPosLocaleReviewSitesDAO_HI.saveOrUpdateAll(siteList);
            }
            List<SrmPosLocaleReviewCatesEntity_HI> cateList = new ArrayList<SrmPosLocaleReviewCatesEntity_HI>();
            JSONArray cateArray = params.getJSONArray("classData");
            if (null != cateArray && cateArray.size() != 0) {
                SrmPosLocaleReviewCatesEntity_HI cateRow = null;
                for (int i = 0; i < cateArray.size(); i++) {
                    JSONObject cateJson = cateArray.getJSONObject(i);
                    // 保存冻结原因判断
                    if (cateJson.getInteger("localeReviewCategoryId") == null) {
                        cateRow = new SrmPosLocaleReviewCatesEntity_HI();
                    } else {
                        // 判断存在就是修改
                        cateRow = srmPosLocaleReviewCatesDAO_HI.getById(cateJson.getInteger("localeReviewCategoryId"));
                    }
                    cateRow.setLocaleReviewId(reviewRow.getLocaleReviewId());
                    cateRow.setSupplierCategoryId(cateJson.getInteger("supplierCategoryId"));
                    cateRow.setCategoryId(cateJson.getInteger("categoryId"));
                    cateRow.setBigCategoryCode(cateJson.getString("bigCategoryCode"));
                    cateRow.setMiddleCategoryCode(cateJson.getString("middleCategoryCode"));
                    cateRow.setSmallCategoryCode(cateJson.getString("smallCategoryCode"));
                    cateRow.setAddFlag(cateJson.getString("addFlag"));
                    cateRow.setStatus(cateJson.getString("status"));
                    cateRow.setSelectedFlag(cateJson.getString("selectedFlag"));
                    cateRow.setOperatorUserId(operatorUserId);
                    cateList.add(cateRow);
                }
                srmPosLocaleReviewSitesDAO_HI.saveOrUpdateAll(cateList);
            }
            JSONObject result = SToolUtils.convertResultJSONObj("S", "保存成功", 1, reviewRow);
            result.put("getFrozenId", reviewRow.getLocaleReviewId());
            return result;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 根据ID删除现场考察数据
     *
     * @param jsonParams
     * @return
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject deleteSrmPosLocaleReviewsInfo(JSONObject jsonParams) {
        LOGGER.info("-->" + jsonParams.toString());
        SrmPosLocaleReviewsEntity_HI entity = null;
        List<SrmPosLocaleReviewSitesEntity_HI> siteList = null;
        List<SrmPosLocaleReviewCatesEntity_HI> cateList = null;
        try {
            entity = srmPosLocaleReviewsDAO_HI.findByProperty("locale_review_id", jsonParams.getInteger("localeReviewId")).get(0);
            if (entity != null) {
                if (!("DRAFT".equals(entity.getLocaleReviewStatus()) || "REJECTED".equals(entity.getLocaleReviewStatus()))) {
                    return SToolUtils.convertResultJSONObj("E", "非拟定或驳回状态的现场考察单据不允许删除！", 0, null);
                }
                siteList = srmPosLocaleReviewSitesDAO_HI.findByProperty("locale_review_id", entity.getLocaleReviewId());
                cateList = srmPosLocaleReviewCatesDAO_HI.findByProperty("locale_review_id", entity.getLocaleReviewId());
                if (siteList != null && siteList.size() != 0) {
                    srmPosLocaleReviewSitesDAO_HI.delete(siteList);
                }
                if (cateList != null && cateList.size() != 0) {
                    srmPosLocaleReviewCatesDAO_HI.delete(cateList);
                }
                srmPosLocaleReviewsDAO_HI.delete(entity);
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除失败，" + jsonParams.getString("localeReviewId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功!", 1, null);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj("E", "删除失败!" + e, 0, null);
        }
    }

    /**
     * 根据ID更新现场考察数据
     *
     * @param jsonParams
     * @return
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateSrmPosLocaleReviewsInfo(JSONObject jsonParams) {
        LOGGER.info(jsonParams.toString());
        try {
            SrmPosLocaleReviewsEntity_HI srmPosLocaleReviewsEntity_HI = JSON.parseObject(jsonParams.toString(), SrmPosLocaleReviewsEntity_HI.class);
            srmPosLocaleReviewsDAO_HI.update(srmPosLocaleReviewsEntity_HI);
            return SToolUtils.convertResultJSONObj("S", "更新成功!", 0, srmPosLocaleReviewsEntity_HI);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            return SToolUtils.convertResultJSONObj("S", "更新失败!" + e, 0, null);
        }
    }

    /**
     * 查询现场考察信息
     *
     * @param localeReviewId
     * @return
     * @throws Exception
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public List<SrmPosLocaleReviewsEntity_HI_RO> findSrmPosLocaleReviewsInfo(Integer localeReviewId) throws Exception {
        LOGGER.info("根据id查询现场考察信息：" + localeReviewId);
        try {
            StringBuffer queryString = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.LOCALE_REVIEW_SQL);
            Map<String, Object> map = new HashMap();
            queryString.append(" AND t1.locale_review_id  = :localeReviewId");
            map.put("localeReviewId", localeReviewId);
            List<SrmPosLocaleReviewsEntity_HI_RO> list = srmPosLocaleReviewsDAO_HI_RO.findList(queryString, map);
            return list;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 审批现场考察信息
     *
     * @param jsonParam
     * @return
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateApprovalLocaleReviewInfo(JSONObject jsonParam) throws Exception {
        try {
            Integer localeReviewId = jsonParam.getInteger("localeReviewId");
            if (localeReviewId == null) {
                return SToolUtils.convertResultJSONObj("E", "审批失败,请传入参数localeReviewId", 0, null);
            }
            SrmPosLocaleReviewsEntity_HI bean = srmPosLocaleReviewsDAO_HI.getById(localeReviewId);
            if (null == bean) {
                return SToolUtils.convertResultJSONObj("E", "审批失败,没有查询到数据", 0, null);
            }

            Integer operatorUserId = jsonParam.getInteger("varUserId");
            bean.setLocaleReviewStatus("APPROVED");
            bean.setApprovedDate(new Date());
            bean.setOperatorUserId(operatorUserId);
            srmPosLocaleReviewsDAO_HI.saveOrUpdate(bean);
            //插入通知
            List<SaafUsersEntity_HI> usersList = saafUsersDAO_HI.findByProperty("supplierId", bean.getSupplierId());
            if (null != usersList && usersList.size() > 0) {
                SaafUsersEntity_HI usersEntity = usersList.get(0);
                iSrmBaseNotifications.insertSrmBaseNotifications("现场考察", "您好！贵公司的现场考察已经审批通过，请在公司档案中查看！"
                        , usersEntity.getUserId(), "srm_pos_locale_reviews", bean.getLocaleReviewId(), "localeReviewId", "home.localeReviewsInfoDetail", operatorUserId);
            }

            /**
             * 1.判断考察结论是否“合格”，若是“不合格”，则直接更新单据状态为“已批准”；若是“合格”，则继续下一步判断； 1.1
             * “不合格”，则直接更新单据状态为“已批准” FAIL 1.2 “合格”，则继续下一步判断 PASS
             */

            // 1.1 “不合格”，则直接更新单据状态为“已批准” FAIL
            if ("FAIL".equals(bean.getLocaleReviewResult())) {
                return SToolUtils.convertResultJSONObj("S", "审批成功", 1, bean);
            }

            // 1.2 “合格”，则继续下一步判断 PASS
            /**
             * 2.根据所选的业务类型，结合场景管理，判断该次引入是否需要经过样品试验，若需要： 10:全新供应商引入 20:退出供应商重新引入
             * 30:临时供应商引入 40:新增品类/组织
             */
            // 业务类型
            // 场景管理
            String sceneType = jsonParam.getString("sceneType");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sceneType", sceneType);
            StringBuffer sb = new StringBuffer(SrmPosSceneManageEntity_HI_RO.YES_NO_SQL);
            sb.append(" WHERE t.scene_type = :sceneType ");
            SrmPosSceneManageEntity_HI_RO scene = srmPosSceneManageDAO_HI_RO.findList(sb, map).get(0);
            //
            /**
             * 2.1 需要 2.1.1 根据资质审查单号寻找相应“已批准”的样品样品试验单； 2.1.2
             * 若无符合条件的样品试验单，则不执行更新，直接修改单据状态为“已批准”。 2.1.3 若有符合条件的样品试验单， 2.1.2.1
             * 筛选出现场考察单、样品试验单同时勾选的品类，更新相应供应商产品与服务的类别状态为“合格”，并且记录合格时间。且根据现场考察单中，
             * 勾选的地点更新相应的供应商地点状态为“合格”，并且记录合格时间。 2.1.2.2
             * 若供应商头层状态是“引入中”，则更新供应商头层状态为“合格”.
             * 2.1.2.3合格后，若供应商编码为空，则生成供应商编码，生成规则为配置文件“供应商编码代码”+6位流水。 2.1.2.4
             * 然后修改单据状态为“已批准”；
             */

            // 2.1 需要
            if ("Y".equals(scene.getSampleTrialsFlag())) {
                // 2.1.1 根据资质审查单号寻找相应“已批准”的样品样品试验单；
                Integer qualificationId = jsonParam.getInteger("qualificationId");
                map = new HashMap<String, Object>();
                map.put("qualificationId", qualificationId);
                StringBuffer querySql = new StringBuffer(SrmPosSampleTrialsEntity_HI_RO.QUERY_TRIAL_ID_SQL);
                querySql.append(" and a.qualification_id  = :qualificationId");
                List<SrmPosSampleTrialsEntity_HI_RO> trialList = srmPosSampleTrialsDAO_HI_RO.findList(querySql, map);
                // 2.1.2 若无符合条件的样品试验单，则不执行更新，直接修改单据状态为“已批准”。
                if (trialList == null || trialList.size() == 0) {
                    return SToolUtils.convertResultJSONObj("S", "审批成功", 1, bean);
                }

                /**
                 * 2.1.3 若有符合条件的样品试验单， 2.1.2.1
                 * 筛选出现场考察单、样品试验单同时勾选的品类，更新相应供应商产品与服务的类别状态为“合格”，并且记录合格时间。
                 * 且根据现场考察单中，勾选的地点更新相应的供应商地点状态为“合格”，并且记录合格时间。 2.1.2.2
                 * 若供应商头层状态是“引入中”，则更新供应商头层状态为“合格”.
                 * 2.1.2.3合格后，若供应商编码为空，则生成供应商编码，生成规则为配置文件“供应商编码代码”+6位流水。 2.1.2.4
                 * 然后修改单据状态为“已批准”；
                 */
                // 满足条件的分类ID
                Set<Integer> set = new HashSet<Integer>();
                // 得到现场考察分类中供应商产品与服务的ID
                JSONArray cateArray = jsonParam.getJSONArray("classData");
                for (int i = 0; i < cateArray.size(); i++) {
                    JSONObject cateJson = cateArray.getJSONObject(i);
                    for (SrmPosSampleTrialsEntity_HI_RO trial : trialList) {
                        if (("Y".equals(cateJson.getString("selectedFlag")) && "Y".equals(trial.getSelectedFlag()))
                                && cateJson.getInteger("categoryId") != null && trial.getCategoryId() != null
                                && cateJson.getInteger("categoryId").intValue() == trial.getCategoryId().intValue()) {
                            set.add(trial.getCategoryId());
                        }
                    }
                }
                handlerOtherApprovalData(jsonParam, operatorUserId, set);
            } else {
                /**
                 * 2.2不需要 2.2.1
                 * 根据现场考察勾选的类别，更新相应供应商产品与服务的类别状态为“合格”，并且记录合格时间。且根据现场考察单中，
                 * 勾选的地点更新相应的供应商地点状态为“合格”，并且记录合格时间。 2.2.2
                 * 若供应商头层状态是“引入中”，则更新供应商头层状态为“合格”. 2.2.2
                 * 若供应商编码为空，则生成供应商编码，生成规则为配置文件“供应商编码代码”+6位流水。 2.2.2
                 * 并且修改单据状态为“已批准”。
                 */

                // 供应商产品与服务ID集合
                Set<Integer> set = new HashSet<Integer>();
                JSONArray cateArray = jsonParam.getJSONArray("classData");
                for (int i = 0; i < cateArray.size(); i++) {
                    JSONObject cateJson = cateArray.getJSONObject(i);
                    if ("Y".equals(cateJson.getString("selectedFlag"))) {
                        set.add(cateJson.getInteger("categoryId"));
                    }
                }
                handlerOtherApprovalData(jsonParam, operatorUserId, set);
            }
            return SToolUtils.convertResultJSONObj("S", "审批成功", 1, bean);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }

    }

    /**
     * （2）若不经过样品试验，则根据现场考察勾选的类别，更新相应供应商产品与服务的类别状态为“合格”，并且记录合格时间。且根据现场考察单中，
     * 勾选的地点更新相应的供应商地点状态为“合格”，并且记录合格时间。 （3）若供应商头层状态是“引入中”，则更新供应商头层状态为“合格”.
     * （4）合格后，若供应商编码为空，则生成供应商编码，生成规则为配置文件“供应商编码代码”+6位流水。
     *
     * @param jsonParam
     * @param operatorUserId
     * @param set
     * @return
     * @throws Exception
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    private void handlerOtherApprovalData(JSONObject jsonParam, Integer operatorUserId, Set<Integer> set) throws Exception {
        Date date = new Date();
        Integer supplierId = jsonParam.getInteger("supplierId");
        Map<String, Object> queryMap = null;
        // 供应商产品与服务ID集合不为0时，更新相应供应商产品与服务的类别状态为“合格”，并且记录合格时间
        if (set.size() != 0) {
            for (Integer categoryId : set) {
                queryMap = new HashMap<String, Object>();
                queryMap.put("supplierId", supplierId);
                queryMap.put("categoryId", categoryId);
                List<SrmPosSupplierCategoriesEntity_HI> supplierCateList = srmPosSupplierCategoriesDAO_HI.findByProperty(queryMap);
                if (null != supplierCateList && supplierCateList.size() != 0) {
                    SrmPosSupplierCategoriesEntity_HI supplierCate = supplierCateList.get(0);
                    supplierCate.setStatus("EFFECTIVE");
                    supplierCate.setApprovedDate(date);
                    supplierCate.setOperatorUserId(operatorUserId);
                    srmPosSupplierCategoriesDAO_HI.update(supplierCate);
                }
            }
        }

        // 根据现场考察单中，勾选的地点更新相应的供应商地点状态为“合格”，并且记录合格时间。
        List<SrmPosSupplierSitesEntity_HI> siteList = new ArrayList<SrmPosSupplierSitesEntity_HI>();
        List<SrmPosLocaleReviewSitesEntity_HI> siteQueryList = new ArrayList<SrmPosLocaleReviewSitesEntity_HI>();
        Map querySitMap = new HashMap();
        querySitMap.put("localeReviewId", jsonParam.getInteger("localeReviewId"));
        querySitMap.put("selectedFlag", "Y");
        siteQueryList = srmPosLocaleReviewSitesDAO_HI.findByProperty(querySitMap);

        //JSONArray siteArray = jsonParam.getJSONArray("siteData");
        for (int i = 0; i < siteQueryList.size(); i++) {
            //JSONObject siteJson = siteArray.getJSONObject(i);
            SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(siteQueryList.get(i).getSupplierSiteId());
            //if ("Y".equals(siteQueryList.get(i).getSelectedFlag())) {
            site.setSiteStatus("EFFECTIVE");
            site.setPurchaseFlag("Y");
            site.setPaymentFlag("Y");
            site.setQualifiedDate(date);
            site.setOperatorUserId(operatorUserId);
            siteList.add(site);
        }
        if (siteList != null && siteList.size() > 0) {
            srmPosSupplierSitesDAO_HI.updateAll(siteList);
        }
        // 若供应商头层状态是“引入中”，则更新供应商头层状态为“合格”,合格后，若供应商编码为空，则生成供应商编码，生成规则为配置文件“供应商编码代码”+6位流水。
        SrmPosSupplierInfoEntity_HI supplier = srmPosSupplierInfoDAO_HI.getById(supplierId);
        if ("INTRODUCING".equals(supplier.getSupplierStatus())) {
            supplier.setSupplierStatus("EFFETIVE");
            if (supplier.getSupplierNumber() == null || "".equals(supplier.getSupplierNumber().trim())) {
                // 生成规则为配置文件“供应商编码代码”+6位流水。
                String supplierNumber = iSupplierInfo.getSupplierNumber();
                supplier.setSupplierNumber(supplierNumber);
            }
            supplier.setOperatorUserId(operatorUserId);
            srmPosSupplierInfoDAO_HI.update(supplier);
        }
    }

    /**
     * 驳回现场考察信息
     *
     * @param jsonParam
     * @return
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    @Override
    public JSONObject updateRejectLocaleReviewInfo(JSONObject jsonParam) throws Exception {
        try {
            Integer localeReviewId = jsonParam.getInteger("localeReviewId");
            if (localeReviewId == null) {
                return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数localeReviewId", 0, null);
            }
            SrmPosLocaleReviewsEntity_HI bean = srmPosLocaleReviewsDAO_HI.getById(localeReviewId);
            Integer operatorUserId = jsonParam.getInteger("varUserId");
            bean.setLocaleReviewStatus("REJECTED");
            bean.setOperatorUserId(operatorUserId);
            srmPosLocaleReviewsDAO_HI.update(bean);
            //插入通知
            iSrmBaseNotifications.insertSrmBaseNotifications("现场考察", "您好！您所提交的现场考察，单号：" + bean.getLocaleReviewNumber() + "；被驳回，请查看处理，谢谢！"
                    , bean.getCreatedBy(), "srm_pos_locale_reviews", bean.getLocaleReviewId(), "localeReviewId", "home.localeReviewsInfoDetail", operatorUserId);
            return SToolUtils.convertResultJSONObj("S", "驳回成功", 1, bean);
        } catch (Exception e) {
            LOGGER.error("未知错误:{}", e);
            throw new Exception(e);
        }
    }

    /**
     * 查询是否有重复现场考察信息
     *
     * @param params
     * @return
     * @throws Exception
     * Update History
     * =======================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-06-16     HLH             创建
     * =======================================================================
     */
    public boolean findExistLocaleReviews(JSONObject params) throws Exception {
        LOGGER.info("判断是否有重复现场考察信息：");
        try {
            Integer localeReviewId = params.getInteger("localeReviewId");
            Integer supplierId = params.getInteger("supplierId");
            boolean flag = false;
            StringBuffer queryString = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.LOCALE_REVIEW_SQL_NEW);
            Map<String, Object> map = new HashMap<String, Object>();
            queryString.append(" and t1.supplier_id  = :supplierId ");
            map.put("supplierId", supplierId);

            /* 如果资质审查单号不为空的，则检查“同一个供应商，同一个资质审查单号”，如果现场考察单有不等于“已核准”的单据 */
            if (null != params.get("qualificationId") && !"".equals(params.getString("qualificationId"))) {
                Integer qualificationId = params.getInteger("qualificationId");
                queryString.append(" and t1.qualification_id  = :qualificationId ");
                map.put("qualificationId", qualificationId);
                queryString.append(" and ip.investigation_plan_id  IS NULL ");
            }
            /* 如果考察计划单号不为空的，则检查“同一个供应商，同一个考察计划单号”，如果现场考察单有不等于“已核准”的单据 */
            if (null != params.get("investigationPlanLinesId") && !"".equals(params.getString("investigationPlanLinesId"))) {
                Integer investigationPlanLinesId = params.getInteger("investigationPlanLinesId");
                queryString.append(" and t1.investigation_plan_lines_id  = :investigationPlanLinesId ");
                map.put("investigationPlanLinesId", investigationPlanLinesId);
                queryString.append(" and t1.qualification_id  IS NULL ");
            }
            //判断是新增还是修改
            if (null != localeReviewId) {//修改
                //非本单据
                queryString.append(" and t1.locale_review_id  != :localeReviewId ");
                map.put("localeReviewId", localeReviewId);
            } else {//新增
                queryString.append(" and t1.locale_review_id  IS NOT NULL ");
            }
            //非已批准状态
            queryString.append(" and t1.locale_review_status NOT IN ('APPROVED') ");
            List<SrmPosLocaleReviewsEntity_HI_RO> list = srmPosLocaleReviewsDAO_HI_RO.findList(queryString, map);
            if (list.size() > 0) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
