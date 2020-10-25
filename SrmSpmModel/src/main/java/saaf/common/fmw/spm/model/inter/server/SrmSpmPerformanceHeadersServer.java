package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.apache.http.client.utils.DateUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceEvaluateEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceHeadersEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmManualScore;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceEvaluate;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceHeaders;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceHeadersEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceIndicators;

@Component("srmSpmPerformanceHeadersServer")
public class SrmSpmPerformanceHeadersServer implements ISrmSpmPerformanceHeaders{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceHeadersServer.class);

	@Autowired
	private SaafSequencesUtil saafSequencesUtil;

	@Autowired
	private ViewObject<SrmSpmPerformanceHeadersEntity_HI> srmSpmPerformanceHeadersDAO_HI;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceHeadersEntity_HI_RO> srmSpmPerformanceHeadersEntityHIRO;

	@Autowired
	private ViewObject<SrmSpmPerformanceLinesEntity_HI> srmSpmPerformanceLinesDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceIndicatorsEntity_HI> srmSpmPerformanceIndicatorsDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceEvaluateEntity_HI> srmSpmPerformanceEvaluateDAO_HI;

	@Autowired
	private ISrmSpmPerformanceIndicators srmSpmPerformanceIndicators;

	@Autowired
	private ISrmSpmPerformanceEvaluate srmSpmPerformanceEvaluate;

	@Autowired
	private ISrmSpmManualScore srmSpmManualScore;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

	public SrmSpmPerformanceHeadersServer() {
		super();
	}


    /**
     * 查询绩效列表（分页）
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> queryPerformanceHeadersList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		StringBuffer queryParam = new StringBuffer();
		sb.append(SrmSpmPerformanceHeadersEntity_HI_RO.QUERY_PERFORMANCE_HEADERS_LIST);
		SaafToolUtils.parperParam(paramJSON, "Sph.Org_Id", "orgId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Status", "status", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Period", "evaluatePeriod", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Tpl_Id", "tplId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Interval_From", "evaluateIntervalFrom", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Interval_To", "evaluateIntervalTo", queryParam, queryParamMap, "=");

		/*		String evaluateIntervalFromFrom = paramJSON.getString("evaluateIntervalFromFrom");
		if (evaluateIntervalFromFrom != null && !"".equals(evaluateIntervalFromFrom.trim())) {
			queryParam.append(" AND trunc(spi.Evaluate_Interval_From) >= to_date(:evaluateIntervalFromFrom, 'yyyy-mm')\n");
			queryParamMap.put("evaluateIntervalFromFrom", evaluateIntervalFromFrom);
		}
		String evaluateIntervalFromTo = paramJSON.getString("evaluateIntervalFromTo");
		if (evaluateIntervalFromTo != null && !"".equals(evaluateIntervalFromTo.trim())) {
			queryParam.append(" AND trunc(spi.Evaluate_Interval_From) <= to_date(:evaluateIntervalFromTo, 'yyyy-mm')\n");
			queryParamMap.put("evaluateIntervalFromTo", evaluateIntervalFromTo);
		}
		String evaluateIntervalToFrom = paramJSON.getString("evaluateIntervalToFrom");
		if (evaluateIntervalToFrom != null && !"".equals(evaluateIntervalToFrom.trim())) {
			queryParam.append(" AND trunc(spi.Evaluate_Interval_To) >= to_date(:evaluateIntervalToFrom, 'yyyy-mm')\n");
			queryParamMap.put("evaluateIntervalToFrom", evaluateIntervalToFrom);
		}
		String evaluateIntervalToTo = paramJSON.getString("evaluateIntervalToTo");
		if (evaluateIntervalToTo != null && !"".equals(evaluateIntervalToTo.trim())) {
			queryParam.append(" AND trunc(spi.Evaluate_Interval_To) <= to_date(:evaluateIntervalToTo, 'yyyy-mm')\n");
			queryParamMap.put("evaluateIntervalToTo", evaluateIntervalToTo);
		}*/
		sb.append(queryParam);
        Integer userId = paramJSON.getInteger("varUserId");
        queryParamMap.put("userId", userId);
		//权限控制 只能查看自己创建的及自己需要评分的绩效
        /*

        if (null != userId){
			sb.append(" AND ((Sph.Created_By = " + userId + ") OR EXISTS (SELECT 1 FROM Srm_Spm_Performance_Evaluate Spe WHERE Spe.Performance_Id = Sph.Performance_Id AND Spe.Evaluate_User_Id = " + userId + " AND ROWNUM = 1))");
		}*/

        sb.append(" and ((Sph.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+userId+") "+
                "      AND Sph.item_type IN  (" +getSupplierType(userId)+"))"+
                "      OR ((Sph.Created_By = " + userId + ") OR EXISTS (SELECT 1 FROM Srm_Spm_Performance_Evaluate Spe WHERE Spe.Performance_Id = Sph.Performance_Id AND Spe.Evaluate_User_Id = " + userId + " AND ROWNUM = 1)))");

        String countSql = "select count(1) from (" + sb + ")";
		sb.append(" ORDER BY Sph.Performance_Number DESC");
		System.out.println(sb.toString());
		Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> result = srmSpmPerformanceHeadersEntityHIRO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
		return result;
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
     * 供应商-查询绩效列表（分页）
     * @param paramJSON
     * @param pageIndex
     * @param pageRows
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> queryPerformanceResultList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		StringBuffer queryParam = new StringBuffer();
		sb.append(SrmSpmPerformanceHeadersEntity_HI_RO.QUERY_PERFORMANCE_RESULT_LIST);
		SaafToolUtils.parperParam(paramJSON, "Sph.Performance_Id", "performanceId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Spls.Performance_Line_Id", "performanceLineId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Org_Id", "orgId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Performance_Number", "performanceNumber", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Spls.Supplier_Id", "supplierId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Tpl_Id", "tplId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Period", "evaluatePeriod", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Interval_From", "evaluateIntervalFrom", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Sph.Evaluate_Interval_To", "evaluateIntervalTo", queryParam, queryParamMap, "=");
		sb.append(queryParam);
		if(!"Y".equals(paramJSON.getString("isSupplier"))){
			sb.append(" and Sph.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+paramJSON.getInteger("varUserId")+") "+
                "      AND Sph.item_type IN  (" +getSupplierType(paramJSON.getInteger("varUserId"))+")");
		}

        String countSql = "select count(1) from (" + sb + ")";
		sb.append(" ORDER BY Sph.Performance_Number DESC,Si.Inst_Name,Psi.Supplier_Number");
		System.out.println(sb.toString());
		Pagination<SrmSpmPerformanceHeadersEntity_HI_RO> result = srmSpmPerformanceHeadersEntityHIRO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
		return result;
	}

    /**
     * 查询绩效头信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findPerformanceHeaderById(Integer performanceId) {
        Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(SrmSpmPerformanceHeadersEntity_HI_RO.QUERY_PERFORMANCE_HEADER);
		sb.append(" AND Sph.Performance_Id = :performanceId");
		queryParamMap.put("performanceId", performanceId);
		sb.append(" ORDER BY Sph.Performance_Number DESC");

		List<SrmSpmPerformanceHeadersEntity_HI_RO> row = srmSpmPerformanceHeadersEntityHIRO.findList(sb.toString(), queryParamMap);
		map.put("row", row);
        return map;
    }

    /**
     * 保存绩效信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject savePerformance(JSONObject jsonParams) throws Exception {
		try {
			Integer performanceId = jsonParams.getInteger("performanceId");
			if (null == performanceId){
				return SToolUtils.convertResultJSONObj("E", "请输入performanceId", 1, null);
			}
			String action = jsonParams.getString("action");
			String status = jsonParams.getString("status");
			Integer userId = jsonParams.getInteger("varUserId");
			SrmSpmPerformanceHeadersEntity_HI performanceHeaderEntity_HI = null;
			List<SrmSpmPerformanceLinesEntity_HI> linesList = new ArrayList<>();
			List<SrmSpmPerformanceIndicatorsEntity_HI> indicatorsList = new ArrayList<>();
			if (performanceId == 0) {
				//保存绩效头信息
				performanceHeaderEntity_HI = new SrmSpmPerformanceHeadersEntity_HI();
				Date date = new Date(System.currentTimeMillis());
				//生成编号
				String dateFromate = DateUtils.formatDate(date, "yyyyMMdd");
				String performanceNumber = saafSequencesUtil.getDocSequences("SRM_SPM_PERFORMANCE_HEADERS", "JX-", dateFromate + "-", 3);
				performanceHeaderEntity_HI.setPerformanceNumber(performanceNumber);

				performanceHeaderEntity_HI.setPerformanceName(jsonParams.getString("performanceName"));
                if ("发起评分".equals(action)){
					performanceHeaderEntity_HI.setStatus("SCORING");
				}else if ("发布".equals(action)){
					performanceHeaderEntity_HI.setStatus("PUBLISH");
					performanceHeaderEntity_HI.setPublishFlag("Y");
					performanceHeaderEntity_HI.setPublishDate(new Date());
				}else if("保存".equals(action)){
					performanceHeaderEntity_HI.setStatus(status);
				}
				performanceHeaderEntity_HI.setEvaluatePeriod(jsonParams.getString("evaluatePeriod"));
				performanceHeaderEntity_HI.setEvaluateYear(jsonParams.getString("evaluateYear"));
				performanceHeaderEntity_HI.setEvaluateQuarter(jsonParams.getString("evaluateQuarter"));
				performanceHeaderEntity_HI.setEvaluateMonth(jsonParams.getString("evaluateMonth"));
				performanceHeaderEntity_HI.setEvaluateIntervalFrom(jsonParams.getString("evaluateIntervalFrom"));
				performanceHeaderEntity_HI.setEvaluateIntervalTo(jsonParams.getString("evaluateIntervalTo"));
				performanceHeaderEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
				performanceHeaderEntity_HI.setTplId(jsonParams.getInteger("tplId"));
                performanceHeaderEntity_HI.setItemType(jsonParams.getString("itemType"));
				performanceHeaderEntity_HI.setOperatorUserId(userId);
				srmSpmPerformanceHeadersDAO_HI.saveOrUpdate(performanceHeaderEntity_HI);
				srmSpmPerformanceHeadersDAO_HI.fluch();
				//保存供应商信息
				JSONArray linesArray = jsonParams.getJSONArray("lineData");
				for (int i = 0; i < linesArray.size(); i++) {
					JSONObject valuesJson = linesArray.getJSONObject(i);
					SrmSpmPerformanceLinesEntity_HI performanceLineEntity_HI = new SrmSpmPerformanceLinesEntity_HI();
					performanceLineEntity_HI.setPerformanceId(performanceHeaderEntity_HI.getPerformanceId());
					performanceLineEntity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
					performanceLineEntity_HI.setScore(valuesJson.getBigDecimal("score"));
					performanceLineEntity_HI.setPerformanceComment(valuesJson.getString("performanceComment"));
					performanceLineEntity_HI.setImprovementPlan(valuesJson.getString("improvementPlan"));
					performanceLineEntity_HI.setOperatorUserId(userId);
					linesList.add(performanceLineEntity_HI);
				}
				srmSpmPerformanceLinesDAO_HI.save(linesList);
				srmSpmPerformanceLinesDAO_HI.fluch();
				//保存指标信息
				JSONArray indicatorsArray = jsonParams.getJSONArray("indicatorsData");
				for (int j = 0; j < linesList.size(); j++) {
					for (int i = 0; i < indicatorsArray.size(); i++) {
						JSONObject valuesJson = indicatorsArray.getJSONObject(i);
						SrmSpmPerformanceIndicatorsEntity_HI performanceIndicatorEntity_HI = new SrmSpmPerformanceIndicatorsEntity_HI();
						performanceIndicatorEntity_HI.setPerformanceId(performanceHeaderEntity_HI.getPerformanceId());
						performanceIndicatorEntity_HI.setPerformanceLineId(linesList.get(j).getPerformanceLineId());
						performanceIndicatorEntity_HI.setTplId(valuesJson.getInteger("tplId"));
						performanceIndicatorEntity_HI.setTplDimensionId(valuesJson.getInteger("tplDimensionId"));
						performanceIndicatorEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
						performanceIndicatorEntity_HI.setTplIndicatorId(valuesJson.getInteger("tplIndicatorId"));
						performanceIndicatorEntity_HI.setIndicatorWeight(valuesJson.getBigDecimal("indicatorWeight"));
						performanceIndicatorEntity_HI.setEvaluateUserIdStr(valuesJson.getString("userIdStr"));
						performanceIndicatorEntity_HI.setEvaluateEmployeeIdStr(valuesJson.getString("employeeIdStr"));
						performanceIndicatorEntity_HI.setEvaluateEmployeeNumberStr(valuesJson.getString("employeeNumberStr"));
						performanceIndicatorEntity_HI.setEvaluateEmployeeNameStr(valuesJson.getString("employeeNameStr"));
                        performanceIndicatorEntity_HI.setEvaluateUserNameStr(valuesJson.getString("userNameStr"));
						performanceIndicatorEntity_HI.setOperatorUserId(userId);
						indicatorsList.add(performanceIndicatorEntity_HI);
					}
				}
				srmSpmPerformanceIndicatorsDAO_HI.save(indicatorsList);
				srmSpmPerformanceIndicatorsDAO_HI.fluch();
				//动作为发起评分则生成打分明细项数据
				if ("发起评分".equals(action)){
					createPerformanceEvaluateInfo(indicatorsList, userId);
				}
				return SToolUtils.convertResultJSONObj("S", action + "成功", 1, performanceHeaderEntity_HI);
			} else {
				//更新绩效头信息
				performanceHeaderEntity_HI = srmSpmPerformanceHeadersDAO_HI.getById(performanceId);
				if (performanceHeaderEntity_HI.getVersionNum().intValue() != jsonParams.getInteger("versionNum").intValue()){
					return SToolUtils.convertResultJSONObj("E", "该绩效单据已被更新，请重新查询后进行操作！", 1, null);
				}
				performanceHeaderEntity_HI.setPerformanceId(performanceId);
				performanceHeaderEntity_HI.setPerformanceNumber(jsonParams.getString("performanceNumber"));
				performanceHeaderEntity_HI.setPerformanceName(jsonParams.getString("performanceName"));
				if ("发起评分".equals(action)){
					if (!"NEW".equals(performanceHeaderEntity_HI.getStatus())){
						//throw new RuntimeException("只有“拟定”状态单据可以发起评分");
                        throw new UtilsException("只有“拟定”状态单据可以发起评分");
					}
					performanceHeaderEntity_HI.setStatus("SCORING");
				}else if ("发布".equals(action)){
					if (!"SCORING_FINISHED".equals(performanceHeaderEntity_HI.getStatus())){
						//throw new RuntimeException("只有“完成评分”状态单据可以发布");
                        throw new UtilsException("只有“完成评分”状态单据可以发布");
					}
					performanceHeaderEntity_HI.setStatus("PUBLISH");
					performanceHeaderEntity_HI.setPublishFlag("Y");
					performanceHeaderEntity_HI.setPublishDate(new Date());
				}else {
					performanceHeaderEntity_HI.setStatus(status);
				}
				performanceHeaderEntity_HI.setEvaluatePeriod(jsonParams.getString("evaluatePeriod"));
				performanceHeaderEntity_HI.setEvaluateYear(jsonParams.getString("evaluateYear"));
				performanceHeaderEntity_HI.setEvaluateQuarter(jsonParams.getString("evaluateQuarter"));
				performanceHeaderEntity_HI.setEvaluateMonth(jsonParams.getString("evaluateMonth"));
				performanceHeaderEntity_HI.setEvaluateIntervalFrom(jsonParams.getString("evaluateIntervalFrom"));
				performanceHeaderEntity_HI.setEvaluateIntervalTo(jsonParams.getString("evaluateIntervalTo"));
				performanceHeaderEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
				performanceHeaderEntity_HI.setTplId(jsonParams.getInteger("tplId"));
                performanceHeaderEntity_HI.setItemType(jsonParams.getString("itemType"));
				performanceHeaderEntity_HI.setOperatorUserId(userId);
				srmSpmPerformanceHeadersDAO_HI.saveOrUpdate(performanceHeaderEntity_HI);
				srmSpmPerformanceHeadersDAO_HI.fluch();
				if ("NEW".equals(status)) {//只有拟定状态单据可以全量更新
					//删除指标信息
					List<SrmSpmPerformanceIndicatorsEntity_HI> oldIndicatorslist = srmSpmPerformanceIndicatorsDAO_HI.findByProperty("performanceId", performanceId);
					srmSpmPerformanceIndicatorsDAO_HI.delete(oldIndicatorslist);
					srmSpmPerformanceIndicatorsDAO_HI.fluch();
					//删除供应商信息
					List<SrmSpmPerformanceLinesEntity_HI> oldLineslist = srmSpmPerformanceLinesDAO_HI.findByProperty("performanceId", performanceId);
					srmSpmPerformanceLinesDAO_HI.delete(oldLineslist);
					srmSpmPerformanceLinesDAO_HI.fluch();
					//保存供应商信息
					JSONArray linesArray = jsonParams.getJSONArray("lineData");
					for (int i = 0; i < linesArray.size(); i++) {
						JSONObject valuesJson = linesArray.getJSONObject(i);
						SrmSpmPerformanceLinesEntity_HI performanceLineEntity_HI = new SrmSpmPerformanceLinesEntity_HI();
						performanceLineEntity_HI.setPerformanceId(performanceHeaderEntity_HI.getPerformanceId());
						performanceLineEntity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
						performanceLineEntity_HI.setScore(valuesJson.getBigDecimal("score"));
						performanceLineEntity_HI.setPerformanceComment(valuesJson.getString("performanceComment"));
						performanceLineEntity_HI.setImprovementPlan(valuesJson.getString("improvementPlan"));
						performanceLineEntity_HI.setOperatorUserId(userId);
						linesList.add(performanceLineEntity_HI);
					}
					srmSpmPerformanceLinesDAO_HI.save(linesList);
					srmSpmPerformanceLinesDAO_HI.fluch();
					//保存指标信息
					JSONArray indicatorsArray = jsonParams.getJSONArray("indicatorsData");
					for (int j = 0; j < linesList.size(); j++) {
						for (int i = 0; i < indicatorsArray.size(); i++) {
							JSONObject valuesJson = indicatorsArray.getJSONObject(i);
							SrmSpmPerformanceIndicatorsEntity_HI performanceIndicatorEntity_HI = new SrmSpmPerformanceIndicatorsEntity_HI();
							performanceIndicatorEntity_HI.setPerformanceId(performanceHeaderEntity_HI.getPerformanceId());
							performanceIndicatorEntity_HI.setPerformanceLineId(linesList.get(j).getPerformanceLineId());
							performanceIndicatorEntity_HI.setTplId(valuesJson.getInteger("tplId"));
							performanceIndicatorEntity_HI.setTplDimensionId(valuesJson.getInteger("tplDimensionId"));
							performanceIndicatorEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
							performanceIndicatorEntity_HI.setTplIndicatorId(valuesJson.getInteger("tplIndicatorId"));
							performanceIndicatorEntity_HI.setIndicatorWeight(valuesJson.getBigDecimal("indicatorWeight"));
							performanceIndicatorEntity_HI.setEvaluateUserIdStr(valuesJson.getString("userIdStr"));
							performanceIndicatorEntity_HI.setEvaluateEmployeeIdStr(valuesJson.getString("employeeIdStr"));
							performanceIndicatorEntity_HI.setEvaluateEmployeeNumberStr(valuesJson.getString("employeeNumberStr"));
							performanceIndicatorEntity_HI.setEvaluateEmployeeNameStr(valuesJson.getString("employeeNameStr"));
                            performanceIndicatorEntity_HI.setEvaluateUserNameStr(valuesJson.getString("userNameStr"));
							performanceIndicatorEntity_HI.setOperatorUserId(userId);
							indicatorsList.add(performanceIndicatorEntity_HI);
						}
					}
					srmSpmPerformanceIndicatorsDAO_HI.save(indicatorsList);
					srmSpmPerformanceIndicatorsDAO_HI.fluch();
					//动作为发起评分则生成打分明细项数据
					if ("发起评分".equals(action)) {
						createPerformanceEvaluateInfo(indicatorsList, userId);
					}
				}else{
					//非拟定单据只能修改行信息
					//保存供应商信息
					JSONArray linesArray = jsonParams.getJSONArray("lineData");
					for (int i = 0; i < linesArray.size(); i++) {
						JSONObject valuesJson = linesArray.getJSONObject(i);
						SrmSpmPerformanceLinesEntity_HI performanceLineEntity_HI = srmSpmPerformanceLinesDAO_HI.getById(valuesJson.getInteger("performanceLineId"));
						performanceLineEntity_HI.setPerformanceId(performanceHeaderEntity_HI.getPerformanceId());
						performanceLineEntity_HI.setSupplierId(valuesJson.getInteger("supplierId"));
						performanceLineEntity_HI.setScore(valuesJson.getBigDecimal("score"));
						performanceLineEntity_HI.setPerformanceComment(valuesJson.getString("performanceComment"));
						performanceLineEntity_HI.setImprovementPlan(valuesJson.getString("improvementPlan"));
						performanceLineEntity_HI.setOperatorUserId(userId);
						linesList.add(performanceLineEntity_HI);
					}
					srmSpmPerformanceLinesDAO_HI.save(linesList);
					srmSpmPerformanceLinesDAO_HI.fluch();
				}
				return SToolUtils.convertResultJSONObj("S", action + "成功", 1, performanceHeaderEntity_HI);
			}
		} catch (Exception e) {
			//return SToolUtils.convertResultJSONObj("e", "数据保存或修改错误", 1, null);
			//throw new RuntimeException(e.getCause().getMessage());
            throw new UtilsException("数据保存或修改错误:" + e.getCause().getMessage());
		}
	}


    /**
     * 生成绩效打分明细信息
     * @param indicatorsList
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public void createPerformanceEvaluateInfo(List<SrmSpmPerformanceIndicatorsEntity_HI> indicatorsList, Integer userId) throws Exception {
		try {
			List<SrmSpmPerformanceEvaluateEntity_HI> evaluateList = new ArrayList<>();
			for (SrmSpmPerformanceIndicatorsEntity_HI performanceIndicatorEntity_HI : indicatorsList) {
				String evaluateUserIdStr = performanceIndicatorEntity_HI.getEvaluateUserIdStr();
				if (null != evaluateUserIdStr && !"".equals(evaluateUserIdStr)) {
					String[] evaluateUserIdArray = evaluateUserIdStr.split(",");
					for (int i = 0; i < evaluateUserIdArray.length; i++) {
						SrmSpmPerformanceEvaluateEntity_HI performanceEvaluateEntity_HI = new SrmSpmPerformanceEvaluateEntity_HI();
						performanceEvaluateEntity_HI.setPerformanceId(performanceIndicatorEntity_HI.getPerformanceId());
						performanceEvaluateEntity_HI.setPerformanceLineId(performanceIndicatorEntity_HI.getPerformanceLineId());
						performanceEvaluateEntity_HI.setPerformanceIndicatorId(performanceIndicatorEntity_HI.getPerformanceIndicatorId());
						performanceEvaluateEntity_HI.setOperatorUserId(userId);
						performanceEvaluateEntity_HI.setEvaluateUserId(Integer.parseInt(evaluateUserIdArray[i]));
						evaluateList.add(performanceEvaluateEntity_HI);
					}
				}
			}
			srmSpmPerformanceEvaluateDAO_HI.saveAll(evaluateList);
		}catch (Exception e){
			//throw new RuntimeException("生成绩效打分明细信息时发生错误: " + e.getMessage());
            throw new UtilsException("生成绩效打分明细信息时发生错误:" + e.getCause().getMessage());
		}
	}


    /**
     * 计算绩效行得分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject saveCalculateScore(JSONObject jsonParams) throws Exception {
		try {
			Integer performanceId = jsonParams.getInteger("performanceId");
			Integer userId = jsonParams.getInteger("varUserId");
            BigDecimal divideNum = new BigDecimal("100");
            Date date = new Date();
			if (null == performanceId || userId == null){
				return SToolUtils.convertResultJSONObj("E", "参数错误", 1, null);
			}
			SrmSpmPerformanceHeadersEntity_HI headerEntity = srmSpmPerformanceHeadersDAO_HI.getById(performanceId);
			if (null == headerEntity){
				return SToolUtils.convertResultJSONObj("E", "找不到该绩效单据", 1, null);
			}
			//0.校验是否所有评分数据都已提交
			Boolean submitFlag = srmSpmPerformanceEvaluate.QueryPerformanceEvaluateSubmitFlag(performanceId);
			if (false == submitFlag){
				return SToolUtils.convertResultJSONObj("E", "还有未提交的评分数据", 1, null);
			}

			//更新头状态为“完成评分”
			headerEntity.setStatus("SCORING_FINISHED");
			headerEntity.setOperatorUserId(userId);
			srmSpmPerformanceHeadersDAO_HI.saveOrUpdate(headerEntity);

			//开始计算
			//1.计算指标分数
			srmSpmPerformanceIndicators.saveCalculateIndicatorScore(jsonParams);
			//2.计算行分数
			//查找所有行
			List<SrmSpmPerformanceLinesEntity_HI> lineList = srmSpmPerformanceLinesDAO_HI.findByProperty("performanceId", performanceId);
			for (SrmSpmPerformanceLinesEntity_HI lineEntity : lineList){
				//查找每个行的指标评分
				Map<String, Object> indicatorParamsMap = new HashMap<>();
				indicatorParamsMap.put("performanceId", lineEntity.getPerformanceId());
				indicatorParamsMap.put("performanceLineId", lineEntity.getPerformanceLineId());
				List<SrmSpmPerformanceIndicatorsEntity_HI> indicatorList = srmSpmPerformanceIndicatorsDAO_HI.findByProperty(indicatorParamsMap);
				BigDecimal lineScore = new BigDecimal("0");
				//计算评分得分
				for (int i = 0; i < indicatorList.size(); i ++){
					lineScore = lineScore.add(indicatorList.get(i).getIndicatorScore().multiply(indicatorList.get(i).getDimensionWeight()).divide(divideNum));
				}
				//计算加减分
				List<SrmSpmManualScoreEntity_HI_RO> manualScoreList = srmSpmManualScore.caculateManualScore(lineEntity.getSupplierId(), headerEntity.getEvaluateIntervalFrom(), headerEntity.getEvaluateIntervalTo());
				if (null != manualScoreList && manualScoreList.size() > 0){
					lineScore = lineScore.add(manualScoreList.get(0).getScore());
					lineEntity.setManualScoreIdStr(manualScoreList.get(0).getManualScoreIdStr());
				}
				//四舍五入到小数点后一位
				lineScore = lineScore.setScale(1, BigDecimal.ROUND_HALF_UP);
				lineEntity.setScore(lineScore);
				lineEntity.setCalculateDate(date);
				lineEntity.setOperatorUserId(userId);
				srmSpmPerformanceLinesDAO_HI.saveOrUpdate(lineEntity);
			}
			//3.计算排名
			List<SrmSpmPerformanceLinesEntity_HI> newLineList = srmSpmPerformanceLinesDAO_HI.findByProperty("performanceId", performanceId);
			if (null != newLineList && newLineList.size() > 0){
				Collections.sort(newLineList);
				int index = 0;// 排名
				BigDecimal lastScore = new BigDecimal("-1");// 最近一次的分
				for (SrmSpmPerformanceLinesEntity_HI lineEntity : newLineList){
					if (lineEntity.getScore().compareTo(lastScore) != 0){
						lastScore = lineEntity.getScore();
						index ++;
						lineEntity.setRank(index);
					}else{
						lineEntity.setRank(newLineList.get(index - 1).getRank());
						index ++;
					}
					lineEntity.setOperatorUserId(userId);
					srmSpmPerformanceLinesDAO_HI.saveOrUpdate(lineEntity);
				}
			}

			return SToolUtils.convertResultJSONObj("S", "计算得分成功", 1, headerEntity);
		} catch (Exception e) {
			//throw new RuntimeException("计算得分时发生错误:" + e.getCause().getMessage());
            throw new UtilsException("计算得分时发生错误:" + e.getCause().getMessage());
		}
	}

    /**
     * 删除绩效信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject deletePerformance(JSONObject jsonParams) throws Exception {
		try {
			Integer performanceId = jsonParams.getInteger("performanceId");
			if (null == performanceId){
				return SToolUtils.convertResultJSONObj("E", "请输入performanceId", 1, null);
			}
			SrmSpmPerformanceHeadersEntity_HI headerEntity = srmSpmPerformanceHeadersDAO_HI.getById(performanceId);
			if (null == headerEntity){
				return SToolUtils.convertResultJSONObj("E", "找不到绩效单据", 1, null);
			}
			if (!"NEW".equals(headerEntity.getStatus())){
				return SToolUtils.convertResultJSONObj("E", "只能删除状态为拟定的绩效", 1, null);
			}
			List<SrmSpmPerformanceLinesEntity_HI> lineList = srmSpmPerformanceLinesDAO_HI.findByProperty("performanceId", performanceId);
			if (null != lineList && lineList.size() > 0){
				srmSpmPerformanceLinesDAO_HI.deleteAll(lineList);
			}
			srmSpmPerformanceHeadersDAO_HI.delete(headerEntity);
			return SToolUtils.convertResultJSONObj("S", "删除绩效成功", 1, null);
		} catch (Exception e) {
			//throw new RuntimeException("删除绩效时发生错误:" + e.getCause().getMessage());
            throw new UtilsException("删除绩效时发生错误:" + e.getCause().getMessage());
		}
	}
}
