package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.base.utils.SToolUtils;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceEvaluateEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmTplIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmIndicators;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：绩效指标查询Server
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Component("srmSpmIndicatorsServer")
public class SrmSpmIndicatorsServer implements ISrmSpmIndicators {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorsServer.class);

    @Autowired
    private ViewObject<SrmSpmIndicatorsEntity_HI> srmSpmIndicatorsDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorsEntity_HI_RO> srmSpmIndicatorsEntityHIRO;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorItemsEntity_HI_RO> srmSpmIndicatorsItemsEntityHIRO;

    @Autowired
    private BaseViewObject<SrmSpmTplIndicatorsEntity_HI_RO> srmSpmTplIndicatorsEntityDAO_HI_RO;

    @Autowired
    private ViewObject<SrmSpmIndicatorItemsEntity_HI> srmSpmIndicatorItemsEntity_HI;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值


    public SrmSpmIndicatorsServer() {
        super();
    }
    /**
     * 绩效指标查询
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public List<SrmSpmIndicatorsEntity_HI> findSrmSpmIndicatorsInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmIndicatorsEntity_HI> findListResult = srmSpmIndicatorsDAO_HI.findList("FROM SrmSpmIndicatorsEntity_HI", queryParamMap);
        return findListResult;
    }

    /**
     * 绩效指标保存
     * @param queryParamJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    public Object saveSrmSpmIndicatorsInfo(JSONObject queryParamJSON) {
        SrmSpmIndicatorsEntity_HI srmSpmIndicatorsEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmIndicatorsEntity_HI.class);
        Object resultData = srmSpmIndicatorsDAO_HI.save(srmSpmIndicatorsEntity_HI);
        return resultData;
    }

    /**
     * Description：查询绩效指标
     * @param paramJSON 绩效指标查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmSpmIndicatorsEntity_HI_RO> SpmIndicatorsInfo(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        //StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmIndicatorsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        //count.append(SrmSpmIndicatorsEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(paramJSON, "spi.application_domain", "applicationDomain", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_code", "indicatorCode", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_dimension", "indicatorDimension", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_status", "indicatorStatus", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_value_type", "indicatorValueType", queryParam, queryParamMap, "=");
        String creationDateFrom = paramJSON.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryParam.append(" AND trunc(spi.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = paramJSON.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryParam.append(" AND trunc(spi.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        String startDate = paramJSON.getString("startDate");
        if (startDate != null && !"".equals(startDate.trim())) {
            queryParam.append(" AND (spi.start_date IS NULL OR trunc(spi.start_date) >= to_date(:startDate, 'yyyy-mm-dd'))\n");
            queryParamMap.put("startDate", startDate);
        }
        String endDate = paramJSON.getString("endDate");
        if (endDate != null && !"".equals(endDate.trim())) {
            queryParam.append(" AND (spi.start_date IS NULL OR trunc(spi.start_date) <= to_date(:endDate, 'yyyy-mm-dd'))\n");
            queryParamMap.put("endDate", endDate);
        }

        sb.append(queryParam);
        //count.append(queryParam);
        sb.append(" AND spi.item_type IN (" + getSupplierType(paramJSON.getInteger("varUserId")) + ")");
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY spi.creation_date DESC");
        LOGGER.debug(sb.toString());
        //分页BUG
        //Pagination<SrmSpmIndicatorsEntity_HI_RO> result = srmSpmIndicatorsEntityHIRO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        Pagination<SrmSpmIndicatorsEntity_HI_RO> result = srmSpmIndicatorsEntityHIRO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 获取供应商类型
     * @param userId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
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
     * 删除合同
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteContact(JSONObject jsonParams) throws Exception {
        try {
            if (null != jsonParams.getInteger("indicatorItemId")) {
                SrmSpmIndicatorItemsEntity_HI rowId = srmSpmIndicatorItemsEntity_HI.getById(jsonParams.getInteger("indicatorItemId"));
                if (rowId != null) {
                    srmSpmIndicatorItemsEntity_HI.delete(rowId);
                    return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
                } else {
                    return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效，无法删除", 1, null);
            }
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除失败");
        }
    }

    /**
     * Description：保存绩效指标
     *
     * @param jsonParams 绩效指标数据
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveIndicators(JSONObject jsonParams) throws Exception {
        Integer managerCateId = jsonParams.getInteger("indicatorId");
        Integer userId = jsonParams.getInteger("varUserId");
        SrmSpmIndicatorsEntity_HI indicatorsEntity_HI = null;
        if (managerCateId == 0) {
            indicatorsEntity_HI = new SrmSpmIndicatorsEntity_HI();
            indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
            indicatorsEntity_HI.setIndicatorName(jsonParams.getString("indicatorCode"));
            indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
            indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
            indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
            indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
            indicatorsEntity_HI.setIndicatorStatus(jsonParams.getString("indicatorStatus"));
            indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
            indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
            indicatorsEntity_HI.setStartDate(jsonParams.getDate("startDate"));
            indicatorsEntity_HI.setEndDate(jsonParams.getDate("endDate"));
            indicatorsEntity_HI.setTargetValue(jsonParams.getBigDecimal("targetValue"));
            indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
            indicatorsEntity_HI.setOperatorUserId(userId);
            srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
            JSONArray linesArray = jsonParams.getJSONArray("lineData");
            if (!ObjectUtils.isEmpty(linesArray)) {
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                    itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                    itemsEntity_HI.setSeqNumber(i + 1);
                    itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                    itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                    itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                    itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                    itemsEntity_HI.setOperatorUserId(userId);
                    srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功", 1, indicatorsEntity_HI);
        } else {
            indicatorsEntity_HI = srmSpmIndicatorsDAO_HI.getById(managerCateId);
            indicatorsEntity_HI.setIndicatorId(managerCateId);
            indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
            indicatorsEntity_HI.setIndicatorName(jsonParams.getString("indicatorCode"));
            indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
            indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
            indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
            indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
            indicatorsEntity_HI.setIndicatorStatus(jsonParams.getString("indicatorStatus"));
            indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
            indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
            indicatorsEntity_HI.setStartDate(jsonParams.getDate("startDate"));
            indicatorsEntity_HI.setEndDate(jsonParams.getDate("endDate"));
            indicatorsEntity_HI.setTargetValue(jsonParams.getBigDecimal("targetValue"));
            indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
            indicatorsEntity_HI.setOperatorUserId(userId);
            srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
            List<SrmSpmIndicatorItemsEntity_HI> list = srmSpmIndicatorItemsEntity_HI.findByProperty("indicator_id", managerCateId);
            srmSpmIndicatorItemsEntity_HI.delete(list);
            JSONArray linesArray = jsonParams.getJSONArray("lineData");
            if (!ObjectUtils.isEmpty(linesArray)) {
                for (int i = 0, j = linesArray.size(); i < j; i++) {
                    JSONObject valuesJson = linesArray.getJSONObject(i);
                    SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                    itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                    itemsEntity_HI.setSeqNumber(i + 1);
                    itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                    itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                    itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                    itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                    itemsEntity_HI.setOperatorUserId(userId);
                    srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                    srmSpmIndicatorItemsEntity_HI.fluch();
                }
            }
            return SToolUtils.convertResultJSONObj("S", "修改成功", 1, indicatorsEntity_HI);
        }
    }

    /**
     * 删除绩效指标
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteIndicatorsList(JSONObject jsonParams) throws Exception {
        try {
            Integer indicatorId = jsonParams.getInteger("indicatorId");
            if (!(indicatorId == null || "".equals(indicatorId))) {
                SrmSpmIndicatorsEntity_HI rowId = srmSpmIndicatorsDAO_HI.getById(jsonParams.getInteger("indicatorId"));
                if ("NEW".equals(rowId.getIndicatorStatus())) {
                    if (rowId != null) {
                        srmSpmIndicatorsDAO_HI.delete(rowId);
                    }
                    List<SrmSpmIndicatorItemsEntity_HI> list = srmSpmIndicatorItemsEntity_HI.findByProperty("indicator_id", jsonParams.getInteger("indicatorId"));
                    if (list != null && list.size() > 0) {
                        srmSpmIndicatorItemsEntity_HI.delete(list);
                    }
                } else {
                    return SToolUtils.convertResultJSONObj("E", "删除指标库失败,只能删除拟定的单据!", 0, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "删除指标失败," + jsonParams.getString("indicatorId") + "不存在", 0, null);
            }
            return SToolUtils.convertResultJSONObj("S", "删除成功", 1, null);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("删除失败");
        }
    }


    /**
     * 查询绩效指标
     * @param indicatorId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> findIndicators(Integer indicatorId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SrmSpmIndicatorsEntity_HI rowId = srmSpmIndicatorsDAO_HI.getById(indicatorId);
        List<SrmSpmIndicatorItemsEntity_HI> list = srmSpmIndicatorItemsEntity_HI.findByProperty("indicator_id", indicatorId);
        map.put("IndicatorsEntity", rowId);
        map.put("IndicatorItems", list);
        return map;
    }
    /**
     * Description：生效绩效指标
     * @param jsonParams 绩效指标生效所需参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject updateApproveIndicators(JSONObject jsonParams) {
        try {
            Integer managerCateId = jsonParams.getInteger("indicatorId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmSpmIndicatorsEntity_HI indicatorsEntity_HI = null;
            if (managerCateId == 0) {
                indicatorsEntity_HI = new SrmSpmIndicatorsEntity_HI();
                indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
                indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
                indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
                indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
                indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
                indicatorsEntity_HI.setIndicatorStatus("ACTIVE");
                indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
                indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
                indicatorsEntity_HI.setStartDate(new Date());
                indicatorsEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                indicatorsEntity_HI.setTargetValue(jsonParams.getBigDecimal("targetValue"));
                indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
                indicatorsEntity_HI.setOperatorUserId(userId);
                srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                JSONArray linesArray = jsonParams.getJSONArray("lineData");
                if(!ObjectUtils.isEmpty(linesArray)){
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                        itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                        itemsEntity_HI.setSeqNumber(i + 1);
                        if ("TEXT".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                        } else if ("NUMERIC".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                            itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                        }
                        itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        itemsEntity_HI.setOperatorUserId(userId);
                        srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                    }
                }

                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, indicatorsEntity_HI);
            } else {
                indicatorsEntity_HI = srmSpmIndicatorsDAO_HI.getById(managerCateId);
                indicatorsEntity_HI.setIndicatorId(managerCateId);
                indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
                indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
                indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
                indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
                indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
                indicatorsEntity_HI.setIndicatorStatus("ACTIVE");
                indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
                indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
                indicatorsEntity_HI.setStartDate(new Date());
                indicatorsEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                indicatorsEntity_HI.setTargetValue(jsonParams.getBigDecimal("targetValue"));
                indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
                indicatorsEntity_HI.setOperatorUserId(userId);
                srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                List<SrmSpmIndicatorItemsEntity_HI> list = srmSpmIndicatorItemsEntity_HI.findByProperty("indicator_id", managerCateId);
                srmSpmIndicatorItemsEntity_HI.delete(list);
                JSONArray linesArray = jsonParams.getJSONArray("lineData");
                if(!ObjectUtils.isEmpty(linesArray)){
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                        itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                        itemsEntity_HI.setSeqNumber(i + 1);
                        if ("TEXT".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                        } else if ("NUMERIC".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                            itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                        }
                        itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        itemsEntity_HI.setOperatorUserId(userId);
                        srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                    }
                }

                return SToolUtils.convertResultJSONObj("S", "修改成功", 1, indicatorsEntity_HI);
            }
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("e", "数据保存或修改错误", 1, null);
        }
    }

    /**
     * 修改绩效指标
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateRejectedIndicator(JSONObject jsonParams) throws Exception {
        try {
            Integer managerCateId = jsonParams.getInteger("indicatorId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmSpmIndicatorsEntity_HI indicatorsEntity_HI = null;
            if (managerCateId == 0) {
                indicatorsEntity_HI = new SrmSpmIndicatorsEntity_HI();
                indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
                indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
                indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
                indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
                indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
                indicatorsEntity_HI.setIndicatorStatus("INACTIVE");
                indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
                indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
                indicatorsEntity_HI.setStartDate(jsonParams.getDate("startDate"));
                indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
                indicatorsEntity_HI.setEndDate(new Date());
                indicatorsEntity_HI.setOperatorUserId(userId);
                srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                JSONArray linesArray = jsonParams.getJSONArray("lineData");
                if(!ObjectUtils.isEmpty(linesArray)){
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                        itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                        itemsEntity_HI.setSeqNumber(i + 1);
                        if ("TEXT".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                        } else if ("NUMERIC".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                            itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                        }
                        itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        itemsEntity_HI.setOperatorUserId(userId);
                        srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                    }
                }

                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, indicatorsEntity_HI);
            } else {
                indicatorsEntity_HI = srmSpmIndicatorsDAO_HI.getById(managerCateId);
                indicatorsEntity_HI.setIndicatorId(managerCateId);
                indicatorsEntity_HI.setIndicatorCode(jsonParams.getString("indicatorCode"));
                indicatorsEntity_HI.setApplicationDomain(jsonParams.getString("applicationDomain"));
                indicatorsEntity_HI.setIndicatorDimension(jsonParams.getString("indicatorDimension"));
                indicatorsEntity_HI.setIndicatorType(jsonParams.getString("indicatorType"));
                indicatorsEntity_HI.setIndicatorValueType(jsonParams.getString("indicatorValueType"));
                indicatorsEntity_HI.setIndicatorStatus("INACTIVE");
                indicatorsEntity_HI.setScoreDeductingLimit(jsonParams.getBigDecimal("scoreDeductingLimit"));
                indicatorsEntity_HI.setScoreExplain(jsonParams.getString("scoreExplain"));
                indicatorsEntity_HI.setStartDate(jsonParams.getDate("startDate"));
                indicatorsEntity_HI.setItemType(jsonParams.getString("itemType"));
                indicatorsEntity_HI.setEndDate(new Date());
                indicatorsEntity_HI.setOperatorUserId(userId);
                srmSpmIndicatorsDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                List<SrmSpmIndicatorItemsEntity_HI> list = srmSpmIndicatorItemsEntity_HI.findByProperty("indicator_id", managerCateId);
                srmSpmIndicatorItemsEntity_HI.delete(list);
                JSONArray linesArray = jsonParams.getJSONArray("lineData");
                if(!ObjectUtils.isEmpty(linesArray)){
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmIndicatorItemsEntity_HI itemsEntity_HI = new SrmSpmIndicatorItemsEntity_HI();
                        itemsEntity_HI.setIndicatorId(indicatorsEntity_HI.getIndicatorId());
                        itemsEntity_HI.setSeqNumber(i + 1);
                        if ("TEXT".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setIndicatorItemDesc(valuesJson.getString("indicatorItemDesc"));
                        } else if ("NUMERIC".equals(jsonParams.getString("indicatorValueType"))) {
                            itemsEntity_HI.setPk1Value(valuesJson.getString("pk1Value"));
                            itemsEntity_HI.setPk2Value(valuesJson.getString("pk2Value"));
                        }
                        itemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        itemsEntity_HI.setOperatorUserId(userId);
                        srmSpmIndicatorItemsEntity_HI.saveOrUpdate(itemsEntity_HI);
                    }
                }
                return SToolUtils.convertResultJSONObj("S", "修改成功", 1, indicatorsEntity_HI);
            }
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("e", "数据保存或修改错误", 1, null);
        }
    }

    /**
     * 修改绩效指标状态
     * @param indicatorId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean updateStatusIndicators(Integer indicatorId, String type) {
        boolean flag = true;
        try {
            if (type.equals("Y")) {
                StringBuffer queryParam = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.COUNT_QUERY_FLAG);
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                queryParam.append(" AND EXISTS(SELECT * FROM srm_spm_tpl_indicators sti WHERE sti.TPL_DIMENSION_ID=sd.TPL_DIMENSION_ID AND sti.INDICATOR_ID = " + indicatorId + " )");
                List<SrmSpmTplIndicatorsEntity_HI_RO> list = srmSpmTplIndicatorsEntityDAO_HI_RO.findList(queryParam, queryParamMap);
                if (list.size() > 0) {
                    flag = false;
                } else {
                    SrmSpmIndicatorsEntity_HI rowId = srmSpmIndicatorsDAO_HI.getById(indicatorId);
                    rowId.setIndicatorStatus("INACTIVE");
                    rowId.setEndDate(new Date());
                    rowId.setIndicatorId(indicatorId);
                    srmSpmIndicatorsDAO_HI.update(rowId);
                }
            } else if (type.equals("N")) {
                SrmSpmIndicatorsEntity_HI rowId = srmSpmIndicatorsDAO_HI.getById(indicatorId);
                StringBuffer queryParam = new StringBuffer(SrmSpmIndicatorsEntity_HI_RO.QUERY_FLAG);
                Map<String, Object> queryParamMap = new HashMap<String, Object>();
                queryParam.append(" AND spi.APPLICATION_DOMAIN = '" + rowId.getApplicationDomain() + "' AND spi.INDICATOR_CODE = '" + rowId.getIndicatorCode() + "' and spi.INDICATOR_DIMENSION = '" + rowId.getIndicatorDimension() + "'");
                List<SrmSpmIndicatorsEntity_HI_RO> list = srmSpmIndicatorsEntityHIRO.findList(queryParam, queryParamMap);
                if (list.size() > 0) {
                    flag = false;
                } else {
                    rowId.setIndicatorStatus("ACTIVE");
                    rowId.setStartDate(new Date());
                    rowId.setIndicatorId(indicatorId);
                    srmSpmIndicatorsDAO_HI.update(rowId);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 查询绩效指标行
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmIndicatorItemsEntity_HI_RO> queryIndicatorsItemList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        count.append(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(paramJSON, "st.INDICATOR_ID", "indicatorId", queryParam, queryParamMap, "=");
        sb.append(queryParam);
        count.append(queryParam);
        sb.append(" ORDER BY st.CREATION_DATE DESC");
        Pagination<SrmSpmIndicatorItemsEntity_HI_RO> result = srmSpmIndicatorsItemsEntityHIRO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * 查询绩效指标导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmIndicatorsEntity_HI_RO> queryIndicatorsExport(JSONObject paramJSON) throws Exception {
            StringBuffer queryParam = new StringBuffer(SrmSpmIndicatorsEntity_HI_RO.QUERY_EXPORT);
            Map<String, Object> queryParamMap = new HashMap();
            SaafToolUtils.parperParam(paramJSON, "spi.application_domain", "applicationDomain", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "spi.indicator_code", "indicatorCode", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "spi.indicator_dimension", "indicatorDimension", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "spi.indicator_status", "indicatorStatus", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "spi.indicator_value_type", "indicatorValueType", queryParam, queryParamMap, "=");
            String creationDateFrom = paramJSON.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryParam.append(" AND trunc(spi.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = paramJSON.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryParam.append(" AND trunc(spi.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("creationDateTo", creationDateTo);
            }
            String startDate = paramJSON.getString("startDate");
            if (startDate != null && !"".equals(startDate.trim())) {
                queryParam.append(" AND (spi.start_date IS NULL OR trunc(sp.start_date) >= to_date(:startDate, 'yyyy-mm-dd'))\n");
                queryParamMap.put("startDate", startDate);
            }
            String endDate = paramJSON.getString("endDate");
            if (endDate != null && !"".equals(endDate.trim())) {
                queryParam.append(" AND (spi.start_date IS NULL OR trunc(sp.start_date) <= to_date(:endDate, 'yyyy-mm-dd'))\n");
                queryParamMap.put("endDate", endDate);
            }
            queryParam.append(" AND spi.item_type IN (" + getSupplierType(paramJSON.getInteger("varUserId")) + ")");
            queryParam.append(" ORDER BY spi.creation_date DESC");
            List<SrmSpmIndicatorsEntity_HI_RO> list = srmSpmIndicatorsEntityHIRO.findList(queryParam, queryParamMap);
            return list;
    }

    /**
     * 汇总绩效指标数量
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean countIndicator(JSONObject paramJSON) throws Exception {
        LOGGER.info(paramJSON.toString());
        boolean flag = true;
        StringBuffer queryParam = new StringBuffer(SrmSpmIndicatorsEntity_HI_RO.QUERY_FLAG);
        Map<String, Object> queryParamMap = new HashMap();
        SaafToolUtils.parperParam(paramJSON, "spi.application_domain", "applicationDomain", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_code", "indicatorCode", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "spi.indicator_dimension", "indicatorDimension", queryParam, queryParamMap, "=");
        List<SrmSpmIndicatorsEntity_HI_RO> list = srmSpmIndicatorsEntityHIRO.findList(queryParam, queryParamMap);
        if (list.size() > 0) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 汇总绩效指标数量
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean countRejectedIndicator(JSONObject jsonParams) throws Exception {
        boolean flag = true;
        StringBuffer queryParam = new StringBuffer(SrmSpmTplIndicatorsEntity_HI_RO.COUNT_QUERY_FLAG);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryParam.append(" AND EXISTS(SELECT * FROM srm_spm_tpl_indicators sti WHERE sti.TPL_DIMENSION_ID = sd.TPL_DIMENSION_ID AND sti.INDICATOR_ID = " + jsonParams.getInteger("indicatorId") + " )");
        List<SrmSpmTplIndicatorsEntity_HI_RO> list = srmSpmTplIndicatorsEntityDAO_HI_RO.findList(queryParam, queryParamMap);
        if (list.size() > 0) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }


}
