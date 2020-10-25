package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSONArray;
import com.yhg.hibernate.core.dao.BaseViewObject;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceHeadersEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceEvaluateEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceLinesEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceEvaluate;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceEvaluateEntity_HI;
import com.yhg.hibernate.core.dao.ViewObject;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceIndicators;

@Component("srmSpmPerformanceEvaluateServer")
public class SrmSpmPerformanceEvaluateServer implements ISrmSpmPerformanceEvaluate{
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceEvaluateServer.class);

	@Autowired
	private ViewObject<SrmSpmPerformanceHeadersEntity_HI> srmSpmPerformanceHeadersDAO_HI;

	@Autowired
	private ViewObject<SrmSpmPerformanceEvaluateEntity_HI> srmSpmPerformanceEvaluateDAO_HI;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceEvaluateEntity_HI_RO> srmSpmPerformanceEvaluateDAO_HI_RO;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceLinesEntity_HI_RO> srmSpmPerformanceLinesEntityHIRO;

	@Autowired
	private BaseViewObject<SrmSpmPerformanceIndicatorsEntity_HI_RO> srmSpmPerformanceIndicatorsEntityHIRO;


	@Autowired
	private ISrmSpmPerformanceIndicators srmSpmPerformanceIndicators;

	public SrmSpmPerformanceEvaluateServer() {
		super();
	}

    /**
     * 查询绩效的评分信息
     * @param paramsMap
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public List<SrmSpmPerformanceEvaluateEntity_HI_RO> queryPerformanceEvaluateList(Map<String, Object> paramsMap){
		StringBuffer sb = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE_INFO);
		if(null != paramsMap.get("performanceId")){
			sb.append(" AND Spe.Performance_Id = :performanceId");
		}
		if(null != paramsMap.get("performanceLineId")){
			sb.append(" AND Spe.Performance_Line_Id = :performanceLineId");
		}
		if(null != paramsMap.get("performanceIndicatorId")){
			sb.append(" AND Spe.Performance_Indicator_Id = :performanceIndicatorId");
		}
		List<SrmSpmPerformanceEvaluateEntity_HI_RO> list = srmSpmPerformanceEvaluateDAO_HI_RO.findList(sb.toString(), paramsMap);
		return list;
	}


    /**
     * 查询绩效的某一评分人的评分信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Map<String, Object> findPerformanceEvaluateById(JSONObject jsonParam) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(SrmSpmPerformanceLinesEntity_HI_RO.QUERY_PERFORMANCE_LINES_LIST);
		SaafToolUtils.parperParam(jsonParam, "Spl.Performance_Id", "performanceId", sb, queryParamMap, "=");
		sb.append(" ORDER BY Psi.Supplier_Number");

		List<SrmSpmPerformanceLinesEntity_HI_RO> row = srmSpmPerformanceLinesEntityHIRO.findList(sb.toString(), queryParamMap);

		for (int i = 0; i < row.size(); i++) {

			Map<String, Object> queryEvaluateMap = new HashMap<String, Object>();
			StringBuffer queryEvaluateSql = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE_LIST);
			Integer performanceLineId = row.get(i).getPerformanceLineId();
			if ("SINGLE".equals(jsonParam.getString("searchMode"))) {
				queryEvaluateSql.append(" AND spe.evaluate_user_id = " + jsonParam.getInteger("varUserId"));
			}
			queryEvaluateSql.append(" AND spe.performance_line_id = " + performanceLineId);

			List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList = srmSpmPerformanceEvaluateDAO_HI_RO.findList(queryEvaluateSql, queryEvaluateMap);
			row.get(i).setEvaluateList(evaluateList);
		}
		map.put("row", row);
		return map;
	}


    /**
     * 查询绩效详细评分信息
     * @param jsonParam
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Map<String, Object> findPerformanceEvaluateDetail(JSONObject jsonParam) {

		Map<String, Object> map = new HashMap<String, Object>();
		Integer performanceId = jsonParam.getInteger("performanceId");
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		//查询头信息
		SrmSpmPerformanceHeadersEntity_HI headerEntity = srmSpmPerformanceHeadersDAO_HI.getById(performanceId);
		//查询供应商信息
		StringBuffer supplierSb = new StringBuffer();
		supplierSb.append(SrmSpmPerformanceLinesEntity_HI_RO.QUERY_PERFORMANCE_LINES_LIST);
		supplierSb.append(" AND Spl.Performance_Id = " + performanceId);
		supplierSb.append(" ORDER BY Spl.rank,Psi.Supplier_Number");
		List<SrmSpmPerformanceLinesEntity_HI_RO> suppilerList = srmSpmPerformanceLinesEntityHIRO.findList(supplierSb.toString(), queryParamMap);

        for (int i = 0; i < suppilerList.size(); i++) {

            //查询评价人信息
            StringBuffer personSb = new StringBuffer();
            personSb.append(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE_PERSON);
            personSb.append(" AND Spe.Performance_Id = " + performanceId);
            personSb.append(" AND Spe.Performance_Line_Id = " + suppilerList.get(i).getPerformanceLineId());
            personSb.append(" GROUP BY Spe.Evaluate_User_Id,Nvl(Se.Employee_Name,Su.User_Name)");
            personSb.append(" ORDER BY Spe.Evaluate_User_Id");
            List<SrmSpmPerformanceEvaluateEntity_HI_RO> personList = srmSpmPerformanceEvaluateDAO_HI_RO.findList(personSb, queryParamMap);
            suppilerList.get(i).setEvaluateList(personList);

			//查询指标信息
			StringBuffer indicatorSb = new StringBuffer();
			indicatorSb.append(SrmSpmPerformanceIndicatorsEntity_HI_RO.QUERY_PERFORMANCE_INDICATORS_LIST);
			indicatorSb.append(" AND Spi.Performance_Id = " + performanceId);
			indicatorSb.append(" AND Spi.Performance_Line_Id = " + suppilerList.get(i).getPerformanceLineId());
			indicatorSb.append(" ORDER BY Std.Evaluate_Dimension, Spi.Indicator_Code");

            List<SrmSpmPerformanceIndicatorsEntity_HI_RO> indicatorList = srmSpmPerformanceIndicatorsEntityHIRO.findList(indicatorSb.toString(), queryParamMap);
            for (int j = 0; j < indicatorList.size(); j++) {

				List<SrmSpmPerformanceEvaluateEntity_HI_RO> scoreList = srmSpmPerformanceEvaluateDAO_HI_RO.findList(personSb, queryParamMap);

				//查询分数
				Map<String, Object> queryEvaluateMap = new HashMap<String, Object>();
				StringBuffer evaluateSb = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE);
				evaluateSb.append(" AND Spe.Performance_Id = " + performanceId);
				evaluateSb.append(" AND Spe.Performance_Line_Id = " + suppilerList.get(i).getPerformanceLineId());
				evaluateSb.append(" AND Spe.Performance_Indicator_Id = " + indicatorList.get(j).getPerformanceIndicatorId());

				List<SrmSpmPerformanceEvaluateEntity_HI_RO> evaluateList = srmSpmPerformanceEvaluateDAO_HI_RO.findList(evaluateSb, queryEvaluateMap);
                for (int a = 0; a < evaluateList.size(); a++) {
                    for (int b = 0; b < scoreList.size(); b++) {
                        if (evaluateList.get(a).getEvaluateUserId().intValue() == scoreList.get(b).getEvaluateUserId().intValue()){
							scoreList.get(b).setScore(evaluateList.get(a).getScore());
                        }
                    }
                }
				indicatorList.get(j).setEvaluateList(scoreList);
			}
			//查询加减分数据
			String manualScoreIdStr = suppilerList.get(i).getManualScoreIdStr();
			if (null != manualScoreIdStr && !"".equals(manualScoreIdStr)){
				List<SrmSpmPerformanceIndicatorsEntity_HI_RO> manualList = srmSpmPerformanceIndicators.findManualScoreList(suppilerList.get(i).getSupplierId(), null, null, manualScoreIdStr);
				if (null != manualList && manualList.size() > 0){
					for (int a = 0; a < manualList.size(); a ++){
						manualList.get(a).setEvaluateList(personList);
						indicatorList.add(manualList.get(a));
					}
				}
			}
			/*List<SrmSpmPerformanceIndicatorsEntity_HI_RO> manualList = srmSpmPerformanceIndicators.findManualScoreList(suppilerList.get(i).getSupplierId(), headerEntity.getEvaluateIntervalFrom(), headerEntity.getEvaluateIntervalFrom(), null);
			for (SrmSpmPerformanceIndicatorsEntity_HI_RO manualEntity: manualList){
				manualEntity.setEvaluateList(personList);
				indicatorList.add(manualEntity);
			}*/
            suppilerList.get(i).setIndicatorList(indicatorList);
		}
		map.put("row", suppilerList);
		return map;
	}


    /**
     * 查询绩效的某一评分人的评分信息-excel导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public List<SrmSpmPerformanceEvaluateEntity_HI_RO> findPerformanceEvaluateExport(JSONObject paramJSON) throws Exception {
		try {
			StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE_EXPORT_LIST);
			Map<String, Object> queryParamMap = new HashMap();
			SaafToolUtils.parperParam(paramJSON, "spe.Performance_Id", "performanceId", queryParam, queryParamMap, "=");
			SaafToolUtils.parperParam(paramJSON, "spe.evaluate_user_id", "varUserId", queryParam, queryParamMap, "=");
			queryParam.append(" ORDER BY Psi.Supplier_Number,Std.Evaluate_Dimension,Spi.Indicator_Code");
			List<SrmSpmPerformanceEvaluateEntity_HI_RO> list = srmSpmPerformanceEvaluateDAO_HI_RO.findList(queryParam, queryParamMap);
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
            throw new UtilsException("查询失败");
		}
		//return null;
	}


    /**
     * 保存绩效评分信息
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject saveEvaluate(JSONObject jsonParams) throws Exception {
		try {
			String action = jsonParams.getString("action");
			Integer userId = jsonParams.getInteger("varUserId");
			Date date = new Date();

			//保存指标信息
			JSONArray evaluateArray = jsonParams.getJSONArray("evaluateData");
			for (int i = 0, j = evaluateArray.size(); i < j; i++) {
				JSONObject valuesJson = evaluateArray.getJSONObject(i);
				Integer evaluateId = valuesJson.getInteger("evaluateId");
				if (null != evaluateId) {
					SrmSpmPerformanceEvaluateEntity_HI performanceEvaluateEntity_HI = srmSpmPerformanceEvaluateDAO_HI.getById(evaluateId);
					performanceEvaluateEntity_HI.setScore(valuesJson.getBigDecimal("score"));
					if (null != valuesJson.getBigDecimal("score") && null == performanceEvaluateEntity_HI.getEvaluateDate()) {
						//记录第一次评分日期
						performanceEvaluateEntity_HI.setEvaluateDate(date);
					}
					performanceEvaluateEntity_HI.setEvaluateComment(valuesJson.getString("evaluateComment"));
					if ("提交".equals(action)){
						performanceEvaluateEntity_HI.setSubmitFlag("Y");
						performanceEvaluateEntity_HI.setSubmitDate(date);
					}
					performanceEvaluateEntity_HI.setOperatorUserId(userId);
					srmSpmPerformanceEvaluateDAO_HI.saveOrUpdate(performanceEvaluateEntity_HI);
				}else {
					throw new RuntimeException("找不到评价行，id为：" + evaluateId);
				}
			}
			return SToolUtils.convertResultJSONObj("S", action + "成功", 0, null);
		
		} catch (Exception e) {
			//return SToolUtils.convertResultJSONObj("e", "数据保存或修改错误", 1, null);
            throw new UtilsException("数据保存或修改错误");
		}
	}

    /**
     * 查询绩效的监控评分信息
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public Map<String, Object> findPerformanceEvaluateMonitorById(Integer performanceId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> queryParamMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_MONITOR_SQL);
		queryParamMap.put("performanceId", performanceId);

		List<SrmSpmPerformanceEvaluateEntity_HI_RO> row = srmSpmPerformanceEvaluateDAO_HI_RO.findList(sb.toString(), queryParamMap);

		map.put("row", row);
		return map;
	}


    /**
     * 根据绩效ID、供应商编码、评价维度、指标名称、用户ID 确认当前行对应的evaluateId
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	public Integer findEvaluateId(JSONObject paramJSON, Integer userId){
		StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.FIND_PERFORMANCE_EVALUATE_ID);
		Map<String, Object> queryParamMap = new HashMap();
		SaafToolUtils.parperParam(paramJSON, "spe.Performance_Id", "performanceId", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Psi.Supplier_Number", "supplierNumber", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Slv1.Meaning", "evaluateDimensionName", queryParam, queryParamMap, "=");
		SaafToolUtils.parperParam(paramJSON, "Spi.Indicator_Code", "indicatorCode", queryParam, queryParamMap, "=");
		queryParam.append(" AND spe.evaluate_user_id = " + userId);

		List<SrmSpmPerformanceEvaluateEntity_HI_RO> list = srmSpmPerformanceEvaluateDAO_HI_RO.findList(queryParam, queryParamMap);
		if (list.size() > 0) {
			return list.get(0).getEvaluateId();
		}else{
			return null;
		}
	}

    /**
     * 根据传入的字符串判断检查输入的分数是否为0-100的正浮点数，是就返回true，否则为false
     * @param str
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public boolean isPositiveNumber(String str) {
		Pattern pattern = Pattern.compile("^(\\d{1,2}(\\.\\d{1,2})?|100|100\\.0|100\\.00)$");
		return pattern.matcher(str).matches();
	}

    /**
     * 导入校验——绩效评分
     * @param jsonArray
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONArray checkArray(JSONArray jsonArray, JSONObject info, Integer userId) {
		if (jsonArray.isEmpty()) {
			return null;
		}
		Integer performanceId = Integer.parseInt(info.getString("performanceId"));
		JSONArray error = new JSONArray();
		JSONObject e = null;
		for (Integer i = 0; i < jsonArray.size(); i++) { // 检验数据正确性
			JSONObject object = jsonArray.getJSONObject(i);
			//根据绩效ID、供应商编码、评价维度、指标名称、用户ID 确认当前行对应的evaluateId
			Integer evaluateId = findEvaluateId(object, userId);
			if (null == evaluateId){
				e = new JSONObject();
				e.put("ERR_MESSAGE", "找不到评分行信息");
				e.put("ROW_NUM", i + 1);
				error.add(e);
				continue;
			}
			boolean temp = isPositiveNumber(object.getString("score"));// 检查输入的分数是否为0-100的正浮点数
			if (!temp) {
				e = new JSONObject();
				e.put("ERR_MESSAGE", "得分" + object.getString("score") + "输入有误，请输入0-100且保留2位小数的分数");
				e.put("ROW_NUM", i + 1);
				error.add(e);
				continue;
			}
		}
		return error;
	}


    /**
     * 批量导入——绩效评分
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
	@Override
	public JSONObject saveBatchImportEvaluateScore(JSONObject jsonParams) throws Exception{

		LOGGER.info("参数：" + jsonParams.toString());
		try {
			if (jsonParams.getJSONArray("data") == null || "".equals(jsonParams.getJSONArray("data"))
					|| jsonParams.getJSONArray("data").size() <= 0) {
				return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入的数据为空，不可导入", 0, null);
			}
			Integer userId = jsonParams.getInteger("varUserId");
			JSONObject info = jsonParams.getJSONObject("info");
			JSONArray jsonArray = jsonParams.getJSONArray("data");
			JSONArray error = checkArray(jsonArray, info, userId);//导入前校验
			if (error.size() > 0) {
				return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", error.size(), error);
			}

			JSONObject jsonData = new JSONObject(); // 最终返回的结果
			List<SrmSpmPerformanceEvaluateEntity_HI> list = new ArrayList<>();
			for (Integer i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonObject.put("performanceId",Integer.parseInt(info.getString("performanceId")));
				Integer evaluateId = findEvaluateId(jsonObject, userId);
				SrmSpmPerformanceEvaluateEntity_HI evaluateEntity = srmSpmPerformanceEvaluateDAO_HI.getById(evaluateId);
				if (null == evaluateEntity) {
					throw new RuntimeException("找不到绩效评分行");
				} else {
					evaluateEntity.setScore(jsonObject.getBigDecimal("score"));
					if (null == evaluateEntity.getEvaluateDate()) {
						//记录第一次评分日期
						evaluateEntity.setEvaluateDate(new Date());
					}
					evaluateEntity.setOperatorUserId(userId);
				}
				list.add(evaluateEntity);
			}
			srmSpmPerformanceEvaluateDAO_HI.saveOrUpdateAll(list);
			return SToolUtils.convertResultJSONObj("S", "成功导入" + list.size() + "行数据", 1, jsonData);

		} catch (Exception e) {
			//throw new RuntimeException(e.getCause().getMessage());
            throw new UtilsException("导入失败");
        }
	}

    /**
     * 检查绩效的评分是否已经全部提交
     * @param performanceId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Boolean QueryPerformanceEvaluateSubmitFlag(Integer performanceId){

        StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceEvaluateEntity_HI_RO.QUERY_PERFORMANCE_EVALUATE_ID);
        Map<String, Object> queryParamMap = new HashMap();
        queryParam.append(" AND Spe.Performance_Id = " + performanceId);
        queryParam.append(" AND Spe.Submit_Flag IS NULL");
        List<SrmSpmPerformanceEvaluateEntity_HI_RO> list = srmSpmPerformanceEvaluateDAO_HI_RO.findList(queryParam, queryParamMap);
        if (null == list || list.size() == 0){
			return true;
        }else{
            return false;
        }
    }

}
