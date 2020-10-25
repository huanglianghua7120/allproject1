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
import saaf.common.fmw.base.model.entities.SaafUsersEntity_HI;
import saaf.common.fmw.base.model.inter.ISrmBaseNotifications;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.pos.model.entities.*;
import saaf.common.fmw.pos.model.entities.readonly.*;
import saaf.common.fmw.pos.model.inter.ISampleTrials;
import saaf.common.fmw.pos.model.inter.ISupplierInfo;

import java.util.*;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：样品试验
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Component("srmSampleTrialsServer")
public class SampleTrialsServer implements ISampleTrials {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleTrialsServer.class);

	@Autowired
	private SaafSequencesUtil saafSequencesUtil;

	@Autowired
	private BaseViewObject<SrmPosSampleTrialsEntity_HI_RO> srmPosSampleTrialsDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPosSampleTrialsEntity_HI> srmPosSampleTrialsDAO_HI;

	@Autowired
	private ViewObject<SrmPosSampleTrialCatesEntity_HI> srmPosSampleTrialCatesDAO_HI;

	@Autowired
	private ViewObject<SrmPosSupplierCategoriesEntity_HI> srmPosSupplierCategoriesDAO_HI;

	@Autowired
	private BaseViewObject<SrmPosSceneManageEntity_HI_RO> srmPosSceneManageDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmPosLocaleReviewsEntity_HI_RO> srmPosLocaleReviewsDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmPosLocaleReviewSitesEntity_HI_RO> srmPosLocaleReviewSitesDAO_HI_RO;

	@Autowired
	private ViewObject<SrmPosSupplierSitesEntity_HI> srmPosSupplierSitesDAO_HI;

	@Autowired
	private ViewObject<SrmPosSupplierInfoEntity_HI> srmPosSupplierInfoDAO_HI;

	@Autowired
	private ISupplierInfo iSupplierInfo;

	@Autowired
	private BaseViewObject<SrmPosQualificationSitesEntity_HI_RO> srmPosQualificationSitesDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmPosQualificationInfoEntity_HI_RO> srmPosQualificationInfoDAO_HI_RO;

	@Autowired
	private ISrmBaseNotifications iSrmBaseNotifications;//系统通知

	@Autowired
	private ViewObject<SaafUsersEntity_HI> saafUsersDAO_HI;  //用户DAO

	/**
	 * 查询样品试验列表
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public Pagination<SrmPosSampleTrialsEntity_HI_RO> findSampleTrialsList(JSONObject parameters, Integer pageIndex,
			Integer pageRows) throws Exception {
		try {
			StringBuffer queryString = new StringBuffer(SrmPosSampleTrialsEntity_HI_RO.QUERY_SAMPLETRIALS_SQL);
			Map<String, Object> map = new HashMap();
			// 查询过滤条件
			SaafToolUtils.parperParam(parameters, "b.supplier_name ", "supplierName", queryString, map, "LIKE");
			SaafToolUtils.parperParam(parameters, "a.trials_number", "trialsNumber", queryString, map, "LIKE");
			SaafToolUtils.parperParam(parameters, "a.trials_status", "trialsStatus", queryString, map, "=");
			SaafToolUtils.parperParam(parameters, "a.scene_type", "sceneType", queryString, map, "=");
			String creationDateFrom = parameters.getString("creationDateFrom");
			if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
				queryString.append(" AND trunc(a.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
				map.put("creationDateFrom", creationDateFrom);
			}
			String creationDateTo = parameters.getString("creationDateTo");
			if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
				queryString.append(" AND trunc(a.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
				map.put("creationDateTo", creationDateTo);
			}
			String countSql = "select count(1) from (" + queryString + ")";
			// 排序
			queryString.append(" ORDER BY a.creation_date DESC");
			Pagination<SrmPosSampleTrialsEntity_HI_RO> sampleList = srmPosSampleTrialsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
			return sampleList;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Description：保存样品试验
	 *
	 * =======================================================================
	 * 参数名称      参数描述           数据类型     是否必填
	 * trialId  样品试验ID  NUMBER  Y
	 * trialsNumber  样品试验编码  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
	 * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * sampleFileId  样品试验相关附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * approvedBy  审批人  NUMBER  N
	 * approvedDate  审批时间  DATE  N
	 * orgId  业务实体ID，关联表saaf_institution  NUMBER  N
	 * organizationId  库粗组织ID，关联表saaf_institution  NUMBER  N
	 * ekpNumber  EKP认证流程单号  VARCHAR2  N
	 * theme  主题  VARCHAR2  N
	 * trialId  样品试验ID  NUMBER  Y
	 * trialsNumber  样品试验编码  VARCHAR2  Y
	 * supplierId  供应商ID，关联表:srm_pos_supplier_info  NUMBER  N
	 * sceneType  场景类型(POS_SCENE_TYPE)  VARCHAR2  N
	 * qualificationId  资质审查ID，关联表:srm_pos_qualification_info  NUMBER  N
	 * trialsStatus  状态(POS_APPROVAL_STATUS)  VARCHAR2  N
	 * trialsResult  样品试验结果(POS_EXAMINE_RESULT)  VARCHAR2  N
	 * sampleFileId  样品试验相关附件ID  NUMBER  N
	 * description  说明  VARCHAR2  N
	 * approvedBy  审批人  NUMBER  N
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
	public JSONObject saveOrUpdateOrSubmitSampleTrials(JSONObject params) throws Exception {
		LOGGER.info("保存样品试验信息，参数：" + params.toString());
		SrmPosSampleTrialsEntity_HI sampleTrialsRow = null;
		try {
			Integer operatorUserId = params.getInteger("varUserId");
			String operatorType = params.getString("operatorType");
			// 保存样品试验判断
			if (null == params.getInteger("trialId")) {
				boolean flag = false;
				flag = findExistRecovery(params.get("supplierId"),params.get("trialId"));
				// 检查是否存在该供应商且状态不等于已核准的样品试验单据
				if (flag) {
					return SToolUtils.convertResultJSONObj("E", "该供应商有未处理完的样品试验单据,请先处理完成!", 0, null);
				}

				sampleTrialsRow = new SrmPosSampleTrialsEntity_HI();
				Date date = new Date();
				String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
				String trialsNumber = saafSequencesUtil.getDocSequences("SRM_POS_SAMPLE_TRIALS", "YPSY-", dateFromate, 4);
				sampleTrialsRow.setTrialsNumber(trialsNumber);
			} else {
				// 判断存在就是修改
				sampleTrialsRow = srmPosSampleTrialsDAO_HI.findByProperty("trialId", params.getInteger("trialId")).get(0);
			}

			sampleTrialsRow.setSupplierId(params.getInteger("supplierId"));
			sampleTrialsRow.setSceneType(params.getString("sceneType"));
			sampleTrialsRow.setQualificationId(params.getInteger("qualificationId"));
			String trialsStatus = null;
			if ("SAVE".equals(operatorType)) {
				trialsStatus = "DRAFT";
			} else if ("SUBMIT".equals(operatorType)) {
				trialsStatus = "SUBMITTED";
			}
			sampleTrialsRow.setTrialsStatus(trialsStatus);
			sampleTrialsRow.setTrialsResult(params.getString("trialsResult"));
			sampleTrialsRow.setSampleFileId(params.getInteger("sampleFileId"));
			sampleTrialsRow.setDescription(params.getString("description"));
			sampleTrialsRow.setOperatorUserId(operatorUserId);
			srmPosSampleTrialsDAO_HI.saveOrUpdate(sampleTrialsRow);

			LOGGER.info("getSupplierId：" + sampleTrialsRow.getSupplierId());

			List<SrmPosSampleTrialCatesEntity_HI> catesList = new ArrayList<SrmPosSampleTrialCatesEntity_HI>();
			JSONArray valuesArray = params.getJSONArray("lineData");
			if (null != valuesArray && valuesArray.size() != 0) {
				for (int i = 0; i < valuesArray.size(); i++) {
					JSONObject valuesJson = valuesArray.getJSONObject(i);
					SrmPosSampleTrialCatesEntity_HI catesRow = null;
					// 保存样品试验品类判断
					if (null == valuesJson.getInteger("trialCateId")) {
						catesRow = new SrmPosSampleTrialCatesEntity_HI();
						catesRow.setTrialId(sampleTrialsRow.getTrialId());
					} else {
						// 判断存在就是修改
						catesRow = srmPosSampleTrialCatesDAO_HI.getById(valuesJson.getInteger("trialCateId"));
					}
					catesRow.setSupplierCategoryId(valuesJson.getInteger("supplierCategoryId"));
					catesRow.setCategoryId(valuesJson.getInteger("categoryId"));
					catesRow.setBigCategoryCode(valuesJson.getString("bigCategoryCode"));
					catesRow.setMiddleCategoryCode(valuesJson.getString("middleCategoryCode"));
					catesRow.setSmallCategoryCode(valuesJson.getString("smallCategoryCode"));
					catesRow.setSelectedFlag(valuesJson.getString("selectedFlag"));
					catesRow.setStatus(valuesJson.getString("status"));
					catesRow.setOperatorUserId(operatorUserId);
					catesList.add(catesRow);
				}
				srmPosSampleTrialCatesDAO_HI.saveOrUpdateAll(catesList);
			}
			JSONObject result = SToolUtils.convertResultJSONObj("S", "保存样品试验成功", 1, sampleTrialsRow);
			return result;
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			throw new Exception(e);
		}
	}

	/**
	 * 删除样品试验
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject deleteSampleTrials(JSONObject params) throws Exception {
		LOGGER.info("删除样品试验信息，参数：" + params.toString());
		try {
			SrmPosSampleTrialsEntity_HI sampleTrialsRow = null;
			List<SrmPosSampleTrialsEntity_HI> sampleTrialsList = srmPosSampleTrialsDAO_HI.findByProperty("trialId", params.getInteger("trialId"));
			if (sampleTrialsList != null && sampleTrialsList.size() > 0) {
				sampleTrialsRow = sampleTrialsList.get(0);
				List<SrmPosSampleTrialCatesEntity_HI> cateList = srmPosSampleTrialCatesDAO_HI.findByProperty("trialId", params.getInteger("trialId"));
				srmPosSampleTrialCatesDAO_HI.deleteAll(cateList);
				srmPosSampleTrialsDAO_HI.delete(sampleTrialsRow);
			} else {
				return SToolUtils.convertResultJSONObj("E", "删除样品试验失败，" + params.getString("trialId") + "不存在", 0, null);
			}
			return SToolUtils.convertResultJSONObj("S", "删除样品试验成功", 1, null);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 根据id查询样品试验
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public List<SrmPosSampleTrialsEntity_HI_RO> findSampleTrials(Integer trialId) throws Exception {
		LOGGER.info("根据id查询样品试验，参数：" + trialId);
		try {
			String sql = SrmPosSampleTrialsEntity_HI_RO.QUERY_SAMPLETRIALS_SQL;
			StringBuffer queryString = new StringBuffer();
			Map<String, Object> map = new HashMap<String, Object>();
			queryString.append(" and a.trial_id  = :trialId");
			map.put("trialId", trialId);
			List<SrmPosSampleTrialsEntity_HI_RO> list = srmPosSampleTrialsDAO_HI_RO.findList(sql + queryString, map);
			return list;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 审批样品试验
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject updateApprovalSample(JSONObject jsonParams) throws Exception {
		LOGGER.info("======>>>" + jsonParams);
		try {
			Integer trialId = jsonParams.getInteger("trialId");
			if (null == trialId) {
				return SToolUtils.convertResultJSONObj("E", "审批失败,请传入参数trialId", 0, null);
			}

			Integer operatorUserId = jsonParams.getInteger("varUserId");
			SrmPosSampleTrialsEntity_HI sampleTrialsRow = (SrmPosSampleTrialsEntity_HI) srmPosSampleTrialsDAO_HI.getById(trialId);

			if (null == sampleTrialsRow) {
				return SToolUtils.convertResultJSONObj("E", "审批失败,没有查询到数据", 0, null);
			}
			jsonParams.put("supplierId", sampleTrialsRow.getSupplierId());
			sampleTrialsRow.setTrialsStatus("APPROVED");
			sampleTrialsRow.setApprovedBy(operatorUserId);
			sampleTrialsRow.setApprovedDate(new Date());
			sampleTrialsRow.setOperatorUserId(operatorUserId);

			//插入通知
			List<SaafUsersEntity_HI> usersList= saafUsersDAO_HI.findByProperty("supplierId",sampleTrialsRow.getSupplierId());
			if(null != usersList && usersList.size()>0){
				SaafUsersEntity_HI usersEntity = usersList.get(0);
				iSrmBaseNotifications.insertSrmBaseNotifications("样品试验","您好！贵公司的样品试验已经审批通过，请在公司档案中查看！"
						,usersEntity.getUserId(),"srm_pos_sample_trials",sampleTrialsRow.getTrialId(),"trialId","home.sampleTrialsDetail",operatorUserId);
			}

			// 判断考察结论是否“合格”，若是“不合格”，则直接更新单据状态为“已批准”；
			if ("FAIL".equals(sampleTrialsRow.getTrialsResult())) {
				srmPosSampleTrialsDAO_HI.update(sampleTrialsRow);
				return SToolUtils.convertResultJSONObj("S", "审批样品试验成功", 1, sampleTrialsRow);
			}
			// 判断考察结论是否“合格”，若是“合格”，则继续下一步判断；

			// 根据所选的业务类型，结合场景管理，判断该次引入是否需要经过现场考察：
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sceneType", sampleTrialsRow.getSceneType());
			StringBuffer sb = new StringBuffer(SrmPosSceneManageEntity_HI_RO.YES_NO_SQL);
			sb.append(" WHERE t.scene_type = :sceneType ");
			SrmPosSceneManageEntity_HI_RO scene = srmPosSceneManageDAO_HI_RO.findList(sb, map).get(0);

			// 若需要经过现场考察：
			if ("Y".equals(scene.getLocaleReviewFlag())) {
				map = new HashMap<String, Object>();
				map.put("qualificationId", sampleTrialsRow.getQualificationId());

				StringBuffer querySql = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.QUERY_REVIEW_ID_SQL);
				querySql.append(" and a.qualification_id  = :qualificationId");
				List<SrmPosLocaleReviewsEntity_HI_RO> reviewList = srmPosLocaleReviewsDAO_HI_RO.findList(querySql, map);

				// 若无符合条件的现场考察单，则不执行更新，直接修改单据状态为“已批准”。
				if (reviewList == null || reviewList.size() == 0) {
					srmPosSampleTrialsDAO_HI.update(sampleTrialsRow);
					return SToolUtils.convertResultJSONObj("S", "审批成功", 1, sampleTrialsRow);
				}

				// 分类ID
				Set<Integer> set = new HashSet<Integer>();
				// 得到样品试验分类中供应商产品与服务的ID
				JSONArray cateArray = jsonParams.getJSONArray("classData");
				for (int i = 0; i < cateArray.size(); i++) {
					JSONObject cateJson = cateArray.getJSONObject(i);
					for (SrmPosLocaleReviewsEntity_HI_RO locale : reviewList) {
						if (("Y".equals(cateJson.getString("selectedFlag")) && "Y".equals(locale.getSelectedFlag()))//
								&& cateJson.getInteger("categoryId").intValue() == locale.getCategoryId().intValue()) {
							set.add(locale.getCategoryId());
						}
					}
				}
				return handlerOtherApprovalData(jsonParams, sampleTrialsRow, operatorUserId, set , scene.getLocaleReviewFlag());
			} else {
				// 若不需要经过现场考察：
				// 供应商产品与服务ID集合
				Set<Integer> set = new HashSet<Integer>();
				// 得到样品试验分类中供应商产品与服务的ID
				JSONArray cateArray = jsonParams.getJSONArray("classData");
				for (int i = 0; i < cateArray.size(); i++) {
					JSONObject cateJson = cateArray.getJSONObject(i);
					if ("Y".equals(cateJson.getString("selectedFlag"))) {
						set.add(cateJson.getInteger("categoryId"));
					}
				}
				return handlerOtherApprovalData(jsonParams, sampleTrialsRow, operatorUserId, set, scene.getLocaleReviewFlag());
			}
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
	 * @param set
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	private JSONObject handlerOtherApprovalData(JSONObject jsonParam, SrmPosSampleTrialsEntity_HI sampleTrialsRow,
			Integer operatorUserId, Set<Integer> set, String localeReviewFlag) throws Exception {
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
		//若需要经过现场考察
		if ("Y".equals(localeReviewFlag)) {
			//根据资质单号查现场考察单
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qualificationId", sampleTrialsRow.getQualificationId());
			StringBuffer querySql = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.QUERY_REVIEW_ID_SQL);
			querySql.append(" AND a.qualification_id  = :qualificationId\r\n");
			querySql.append(" ORDER BY a.locale_review_id DESC \r\n");
			List<SrmPosLocaleReviewsEntity_HI_RO> reviewList = srmPosLocaleReviewsDAO_HI_RO.findList(querySql, map);
			if (reviewList.size() == 0) {
				//不存在现场考察单据
				//直接修改样品试验表单状态,不更新数据
				srmPosSampleTrialsDAO_HI.update(sampleTrialsRow);
				return SToolUtils.convertResultJSONObj("S", "审批成功", 1, sampleTrialsRow);
			}else {
				//获取最新的现场考察单据ID
				Integer localeReviewIdTemp =  reviewList.get(0).getLocaleReviewId();
				//获取单据下的supplierSiteId
				StringBuffer querySiteSql = new StringBuffer(SrmPosLocaleReviewsEntity_HI_RO.QUERY_REVIEW_SUPPLIER_SITE_ID_SQL);
				map.put("localeReviewId", localeReviewIdTemp);
				querySiteSql.append(" AND a.qualification_id  = :qualificationId");
				querySiteSql.append(" AND a.locale_review_id  = :localeReviewId");
				List<SrmPosLocaleReviewSitesEntity_HI_RO> reviewSiteList = srmPosLocaleReviewSitesDAO_HI_RO.findList(querySiteSql, map);
				List<SrmPosSupplierSitesEntity_HI> siteList = new ArrayList<SrmPosSupplierSitesEntity_HI>();
				//对现场考察单号下勾选了的地点进行状态更新
				for (int i = 0; i < reviewSiteList.size(); i++) {
					SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(reviewSiteList.get(i).getSupplierSiteId());
					System.out.println("SupplierSiteId: " + reviewSiteList.get(i).getSupplierSiteId());
					site.setSiteStatus("EFFECTIVE");
					site.setPurchaseFlag("Y");
					site.setPaymentFlag("Y");
					site.setQualifiedDate(date);
					site.setOperatorUserId(operatorUserId);
					siteList.add(site);
				}
				if(siteList != null && siteList.size() > 0){
					srmPosSupplierSitesDAO_HI.updateAll(siteList);
				}
			}
		} else {
			//不需要经过现场考察
			//直接取资质审查单中新增的地点更新状态
			Map<String, Object> queryParamMap = new HashMap<String, Object>();
			queryParamMap.put("qualificationId", sampleTrialsRow.getQualificationId());
			List<SrmPosQualificationSitesEntity_HI_RO> siteList = srmPosQualificationSitesDAO_HI_RO.findList(SrmPosQualificationSitesEntity_HI_RO.QUERY_SELECTED_SITE_ID, queryParamMap);
			for (SrmPosQualificationSitesEntity_HI_RO qSiet : siteList) {
				if (null != qSiet.getSupplierSiteId()) {
					SrmPosSupplierSitesEntity_HI site = srmPosSupplierSitesDAO_HI.getById(qSiet.getSupplierSiteId());
					site.setSiteStatus("EFFECTIVE");
					site.setPaymentFlag("Y");
					site.setPurchaseFlag("Y");
					site.setQualifiedDate(date);
					site.setOperatorUserId(operatorUserId);
					srmPosSupplierSitesDAO_HI.update(site);
				}
			}
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
		// 然后修改单据状态为“已批准”；
		srmPosSampleTrialsDAO_HI.update(sampleTrialsRow);
		return SToolUtils.convertResultJSONObj("S", "审批成功", 1, sampleTrialsRow);
	}

	/**
	 * 验证供应商编码是否存在
	 * 
	 * @param supplierId
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	public boolean findExistsNumber(int supplierId, String supplierNumber) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("supplierId", supplierId);
			map.put("supplierNumber", supplierNumber);
			List<SrmPosSupplierInfoEntity_HI> rowSet = this.srmPosSupplierInfoDAO_HI.findByProperty(map);
			if (rowSet.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 驳回样品试验
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public JSONObject updateRejectSample(JSONObject params) throws Exception {
		try {
			Integer trialId = params.getInteger("trialId");
			if (trialId == null) {
				return SToolUtils.convertResultJSONObj("E", "驳回失败,请传入参数trialId", 0, null);
			}
			SrmPosSampleTrialsEntity_HI sampleTrialsRow = srmPosSampleTrialsDAO_HI.getById(Integer.parseInt(trialId.toString()));
			Integer operatorUserId = params.getInteger("varUserId");
			sampleTrialsRow.setTrialsStatus("REJECTED");
			sampleTrialsRow.setOperatorUserId(operatorUserId);
			srmPosSampleTrialsDAO_HI.update(sampleTrialsRow);
			//插入通知
			iSrmBaseNotifications.insertSrmBaseNotifications("样品试验","您好！ 您所提交的样品试验，单号："+sampleTrialsRow.getTrialsNumber()+"；审批被驳回，请查看处理，谢谢！"
					,sampleTrialsRow.getCreatedBy(),"srm_pos_sample_trials",sampleTrialsRow.getTrialId(),"trialId","home.sampleTrialsDetail",operatorUserId);

			return SToolUtils.convertResultJSONObj("S", "驳回样品试验成功", 1, null);
		} catch (Exception e) {
			LOGGER.error("未知错误:{}", e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据id查询样品试验品类信息
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	@Override
	public List<SrmPosQualificationInfoEntity_HI_RO> findSampleCategoryInfo(JSONObject jsonParams) throws Exception {
		LOGGER.info(JSONObject.toJSONString(jsonParams));
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append(SrmPosQualificationInfoEntity_HI_RO.QUERY_SAMPLE_CATEGORIES_SQL);
			SaafToolUtils.parperParam(jsonParams, "a.qualification_id", "qualificationId", queryString, queryParamMap, "=");
			//样品试验在不是已批准的单据状态下，该关联的资质审查单号下的 已经走试验流程且通过的品类不再显示
			if(!"APPROVED".equals(jsonParams.getString("trialsStatus"))){
				queryString.append("\tAND a.supplier_category_id NOT IN(\n" +
						"\tSELECT supplier_category_id \n" +
						"\tFROM srm_pos_sample_trials t1,srm_pos_sample_trial_cates t2\n" +
						"\tWHERE t1.trial_id = t2.trial_id \n" +
						"\tAND t1.trials_status = 'APPROVED'\n" +
						"\tAND t1.trials_result = 'PASS'\n" +
						"\tAND t2. selected_flag = 'Y'\n");
				SaafToolUtils.parperParam(jsonParams, "t1.qualification_id", "qualificationId", queryString, queryParamMap, "=");
				queryString.append("\t)");
			}
			queryParamMap.put("trialId", jsonParams.getInteger("trialId"));
			List<SrmPosQualificationInfoEntity_HI_RO> rowSet = srmPosQualificationInfoDAO_HI_RO.findList(queryString.toString(), queryParamMap);
			return rowSet;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 检查是否存在该供应商且状态不等于已核准的样品试验单据
	 * @param supplierId
	 * @param trialId
	 * @return
	 * @throws Exception
	 * ===========================================================================
	 *  Version    Date           Updated By     Description
	 *  -------    -----------    -----------    ---------------
	 *  V1.0       2019-04-15     liuwenjun             创建
	 * ===========================================================================
	 */
	public boolean findExistRecovery(Object supplierId, Object trialId) throws Exception {
		try{
			int suppId = Integer.parseInt(supplierId.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT t.trial_id AS trialId,t.supplier_id AS supplierId\n" +
					"FROM srm_pos_sample_trials  t\n" +
					"WHERE t.trials_status <> 'APPROVED' \n" +
					"\tAND t.supplier_id =  " + suppId);
			map.put("supplierId", suppId);
			map.put("trialsStatus","APPROVED");
			List<SrmPosSampleTrialsEntity_HI_RO> rowSet = this.srmPosSampleTrialsDAO_HI_RO.findList(sb.toString(),map);
			if(rowSet.size()>0){
				SrmPosSampleTrialsEntity_HI_RO  recovery = null;
				for(int i=0;i<rowSet.size();i++){
					recovery = rowSet.get(i);
					if(trialId != null && (!trialId.equals(recovery.getTrialId()) && supplierId.equals(recovery.getSupplierId()))){
						return true;
					}
					if(trialId == null && supplierId.equals(recovery.getSupplierId())){
						return true;
					}
				}
			}
			return false;
		}catch(Exception e){
			throw new Exception(e);
		}

	}

}
