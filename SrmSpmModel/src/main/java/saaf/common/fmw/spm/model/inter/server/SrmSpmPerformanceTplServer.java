package saaf.common.fmw.spm.model.inter.server;

import org.springframework.util.ObjectUtils;
import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.spm.model.inter.ISrmSpmPerformanceTpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhg.base.utils.DateUtil;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.inter.server.SaafSequencesUtil;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceTplEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplCategoriesEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplDimensionEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorOwerEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorsEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceSchemeEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceTplEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.utils.SrmUtils;

@Component("srmSpmPerformanceTplServer")
public class SrmSpmPerformanceTplServer implements ISrmSpmPerformanceTpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmPerformanceTplServer.class);

    @Autowired
    private ViewObject<SrmSpmPerformanceTplEntity_HI> srmSpmPerformanceTplDAO_HI;

    @Autowired
    private ViewObject<SrmSpmTplCategoriesEntity_HI> srmTplCategoriesDAO_HI;

    @Autowired
    private ViewObject<SrmSpmTplDimensionEntity_HI> srmTplDimensionEntityDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorItemsEntity_HI_RO> srmSpmIndicatorsItemsEntityHIRO;

    @Autowired
    private ViewObject<SrmSpmTplIndicatorsEntity_HI> srmTplIndicatorsEntityDAO_HI;

    @Autowired
    private ViewObject<SrmSpmTplIndicatorItemsEntity_HI> srmTplIndicatorItemsEntityDAO_HI;

    @Autowired
    private ViewObject<SrmSpmTplIndicatorOwerEntity_HI> srmTplIndicatorOwerDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmPerformanceTplEntity_HI_RO> srmSpmPerformanceTplDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmPerformanceSchemeEntity_HI_RO> srmSpmPerformanceSchemeEntityDAO_HI_RO;

    @Autowired
    private SaafSequencesUtil saafSequencesUtil;

    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值

    public SrmSpmPerformanceTplServer() {
        super();
    }


    /**
     * Description：查询绩效模板
     * @param paramJSON 绩效模板查询参数
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmPerformanceTplEntity_HI_RO> queryPerformanceTplDetail(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        //StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmPerformanceTplEntity_HI_RO.QUERY_TPL_INFO_LIST);
        //count.append(SrmSpmPerformanceTplEntity_HI_RO.QUERY_TPL_INFO_COUNT);
        Integer categoryId = paramJSON.getInteger("categoryId");
        if (categoryId != null) {
            sb.append("  AND EXISTS (SELECT * FROM srm_spm_tpl_categories cate WHERE cate.TPL_ID = tpl.TPL_ID AND cate.CATEGORY_ID = " + categoryId + ") ");
            //count.append("  AND EXISTS (SELECT * FROM srm_spm_tpl_categories cate WHERE cate.TPL_ID = tpl.TPL_ID AND cate.CATEGORY_ID = " + categoryId + ") ");
        }
        SaafToolUtils.parperParam(paramJSON, "tpl.org_id", "orgId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "tpl.tpl_domain", "tplDomain", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, " tpl.tpl_code", "tplCode", queryParam, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(paramJSON, "tpl.tpl_name", "tplName", queryParam, queryParamMap, "LIKE");
        SaafToolUtils.parperParam(paramJSON, "tpl.status", "status", queryParam, queryParamMap, "=");
        String creationDateFrom = paramJSON.getString("creationDateFrom");
        if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
            queryParam.append(" AND trunc(tpl.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateFrom", creationDateFrom);
        }
        String creationDateTo = paramJSON.getString("creationDateTo");
        if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
            queryParam.append(" AND trunc(tpl.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("creationDateTo", creationDateTo);
        }
        String startDateFrom = paramJSON.getString("startDateFrom");
        if (startDateFrom != null && !"".equals(startDateFrom.trim())) {
            queryParam.append(" AND trunc(tpl.start_date) >= to_date(:startDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("startDateFrom", startDateFrom);
        }
        String startDateTo = paramJSON.getString("startDateTo");
        if (startDateTo != null && !"".equals(startDateTo.trim())) {
            queryParam.append(" AND trunc(tpl.start_date) <= to_date(:startDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("startDateTo", startDateTo);
        }
        sb.append(queryParam);
        /*sb.append(" and tpl.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+paramJSON.getInteger("varUserId")+") "+
                "      AND tpl.item_type IN  (" +getSupplierType(paramJSON.getInteger("varUserId"))+")");*/
        //count.append(queryParam);
        String countSql = "select count(1) from (" + sb + ") ";
        sb.append(" ORDER BY tpl.creation_date DESC");
        System.out.println(sb.toString());
        LOGGER.debug(sb.toString());

        //分页BUG
        //return srmSpmPerformanceTplDAO_HI_RO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return srmSpmPerformanceTplDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
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
     * Description：删除绩效模板
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deletePerformanceTpl(JSONObject jsonParams) throws Exception {
        try {
            Integer worId = jsonParams.getInteger("tplId");
            if (worId != null) {
                SrmSpmPerformanceTplEntity_HI performanceTplEntity_HI = srmSpmPerformanceTplDAO_HI.getById(worId);
                List<SrmSpmTplCategoriesEntity_HI> categoriesEntity_HI = srmTplCategoriesDAO_HI.findByProperty("tpl_id", worId);
                srmTplCategoriesDAO_HI.delete(categoriesEntity_HI);
                List<SrmSpmTplDimensionEntity_HI> dimensionEntity_HI = srmTplDimensionEntityDAO_HI.findByProperty("tpl_id", worId);
                if (!dimensionEntity_HI.isEmpty()) {
                    for (SrmSpmTplDimensionEntity_HI rt : dimensionEntity_HI) {
                        List<SrmSpmTplIndicatorsEntity_HI> list = srmTplIndicatorsEntityDAO_HI.findByProperty("tpl_dimension_id", rt.getTplDimensionId());
                        if (!list.isEmpty()) {
                            srmTplIndicatorItemsEntityDAO_HI.executeSql("DELETE FROM srm_spm_tpl_indicator_items WHERE EXISTS(SELECT*FROM srm_spm_tpl_indicators sti WHERE EXISTS(SELECT * FROM srm_spm_tpl_dimension sd WHERE sd.TPL_DIMENSION_ID=" + rt.getTplDimensionId() + " AND sd.TPL_DIMENSION_ID=sti.TPL_DIMENSION_ID) AND sti.TPL_INDICATOR_ID=srm_spm_tpl_indicator_items.TPL_INDICATOR_ID)");
                            srmTplIndicatorsEntityDAO_HI.delete(list);
                        }
                    }
                    srmTplDimensionEntityDAO_HI.delete(dimensionEntity_HI);
                }
                srmSpmPerformanceTplDAO_HI.delete(performanceTplEntity_HI);
                return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数错误，请重试！", 1, null);
            }
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", "数据删除错误", 1, null);
        }
    }

    /**
     * Description：查询绩效模板
     * @param tplId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> finderformancetpl(Integer tplId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceTplEntity_HI_RO.QUERY_TPL_INFO_LIST);
        queryParam.append(" AND tpl.tpl_id = " + tplId);
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        List<SrmSpmPerformanceTplEntity_HI_RO> list = srmSpmPerformanceTplDAO_HI_RO.findList(queryParam, queryParamMap);
        if (!list.isEmpty()) {
            map.put("tplHiRo", list.get(0));
        }
        return map;
    }
    /**
     * Description：保存绩效模板
     * @param jsonParams 绩效模板数据
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveformancetpl(JSONObject jsonParams) throws Exception {
        try {
            Integer tplId = jsonParams.getInteger("tplId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmSpmPerformanceTplEntity_HI performanceTplEntity_HI = null;
            if (tplId == 0) {
                performanceTplEntity_HI = new SrmSpmPerformanceTplEntity_HI();
                String code = saafSequencesUtil.getDocSequences("srm_spm_performance_tpl".toUpperCase(), "SPAT-" + DateUtil.date2Str(new Date(), "yyyyMMdd") + "-", 4);
                performanceTplEntity_HI.setTplCode(code);
                //performanceTplEntity_HI.setTplDomain(jsonParams.getString("tplDomain"));
                performanceTplEntity_HI.setDescription(jsonParams.getString("description"));
                performanceTplEntity_HI.setOperatorUserId(userId);
                performanceTplEntity_HI.setStatus(jsonParams.getString("status"));
                performanceTplEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
                performanceTplEntity_HI.setTplFrequency(jsonParams.getString("tplFrequency"));
                performanceTplEntity_HI.setTplName(jsonParams.getString("tplName"));
                performanceTplEntity_HI.setStartDate(jsonParams.getDate("startDate"));
                performanceTplEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                performanceTplEntity_HI.setItemType(jsonParams.getString("itemType"));
                srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
/*                JSONArray linesArray = jsonParams.getJSONArray("dataTable");
                if (linesArray.size() > 0) {
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmTplCategoriesEntity_HI categoriesEntity = new SrmSpmTplCategoriesEntity_HI();
                        categoriesEntity.setTplId(performanceTplEntity_HI.getTplId());
                        categoriesEntity.setCategoryId(valuesJson.getInteger("categoryId"));
                        categoriesEntity.setDescription(valuesJson.getString("description"));
                        categoriesEntity.setOperatorUserId(userId);
                        srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity);
                    }
                }*/
                JSONArray linesArray1 = jsonParams.getJSONArray("dataTable1");
                if (linesArray1.size() > 0) {
                    for (int i = 0, j = linesArray1.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray1.getJSONObject(i);
                        SrmSpmTplDimensionEntity_HI dimensionEntity_HI = new SrmSpmTplDimensionEntity_HI();
                        dimensionEntity_HI.setOperatorUserId(userId);
                        dimensionEntity_HI.setTplId(performanceTplEntity_HI.getTplId());
                        dimensionEntity_HI.setDescription(valuesJson.getString("descriptione"));
                        dimensionEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                        dimensionEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HI);
                    }
                }
                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, performanceTplEntity_HI);
            } else {
                performanceTplEntity_HI = srmSpmPerformanceTplDAO_HI.getById(tplId);
                //performanceTplEntity_HI.setTplDomain(jsonParams.getString("tplDomain"));
                performanceTplEntity_HI.setDescription(jsonParams.getString("description"));
                performanceTplEntity_HI.setOperatorUserId(userId);
                performanceTplEntity_HI.setStatus(jsonParams.getString("status"));
                performanceTplEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
                performanceTplEntity_HI.setTplFrequency(jsonParams.getString("tplFrequency"));
                performanceTplEntity_HI.setTplName(jsonParams.getString("tplName"));
                performanceTplEntity_HI.setStartDate(jsonParams.getDate("startDate"));
                performanceTplEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                performanceTplEntity_HI.setItemType(jsonParams.getString("itemType"));
                srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
                List<SrmSpmTplCategoriesEntity_HI> list = srmTplCategoriesDAO_HI.findByProperty("tpl_id", tplId);
                srmTplCategoriesDAO_HI.delete(list);
                /*JSONArray linesArray = jsonParams.getJSONArray("dataTable");
                if (linesArray.size() > 0) {
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmTplCategoriesEntity_HI categoriesEntity = new SrmSpmTplCategoriesEntity_HI();
                        categoriesEntity.setTplId(performanceTplEntity_HI.getTplId());
                        categoriesEntity.setCategoryId(valuesJson.getInteger("categoryId"));
                        categoriesEntity.setDescription(valuesJson.getString("description"));
                        categoriesEntity.setOperatorUserId(userId);
                        srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity);
                    }
                }*/
                JSONArray linesArray1 = jsonParams.getJSONArray("dataTable1");
                if (linesArray1.size() > 0) {
                    for (int i = 0, j = linesArray1.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray1.getJSONObject(i);

                        SrmSpmTplDimensionEntity_HI dimensionEntity_HI = null;
                        if (valuesJson.getInteger("tplDimensionId") == null) {
                            dimensionEntity_HI = new SrmSpmTplDimensionEntity_HI();
                        } else {
                            dimensionEntity_HI = srmTplDimensionEntityDAO_HI.getById(valuesJson.getInteger("tplDimensionId"));
                        }
                        dimensionEntity_HI.setOperatorUserId(userId);
                        dimensionEntity_HI.setTplId(performanceTplEntity_HI.getTplId());
                        dimensionEntity_HI.setDescription(valuesJson.getString("descriptione"));
                        dimensionEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                        dimensionEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HI);
                    }
                }
                JSONArray linesArray2 = jsonParams.getJSONArray("dataTable2");
                if (linesArray2.size() > 0) {
                    for (int i = 0, j = linesArray2.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray2.getJSONObject(i);
                        SrmSpmTplIndicatorsEntity_HI indicatorsEntity_HI = null;
                        if (valuesJson.getInteger("tplIndicatorId") == null) {
                            indicatorsEntity_HI = new SrmSpmTplIndicatorsEntity_HI();
                        } else {
                            indicatorsEntity_HI = srmTplIndicatorsEntityDAO_HI.getById(valuesJson.getInteger("tplIndicatorId"));
                        }
                        indicatorsEntity_HI.setOperatorUserId(userId);
                        indicatorsEntity_HI.setIndicatorId(valuesJson.getInteger("indicatorId"));
                        indicatorsEntity_HI.setTplDimensionId(valuesJson.getInteger("tplDimensionId"));
                        indicatorsEntity_HI.setScoreDeductingLimit(valuesJson.getBigDecimal("scoreDeductingLimit"));
                        indicatorsEntity_HI.setIndicatorWeight(valuesJson.getBigDecimal("dimensionWeight"));
/*                        BigDecimal numt = valuesJson.getBigDecimal("scoreDeductingLimit");
                        if (numt != null) {
                            indicatorsEntity_HI.setIndicatorWeight(null);
                        } else {
                            indicatorsEntity_HI.setIndicatorWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        }*/

                        indicatorsEntity_HI.setTargetValue(valuesJson.getBigDecimal("targetValue"));
                        srmTplIndicatorsEntityDAO_HI.saveOrUpdate(indicatorsEntity_HI);
 /*                       srmTplIndicatorOwerDAO_HI.executeSql("DELETE FROM srm_spm_tpl_indicator_ower WHERE tpl_indicator_id = " + indicatorsEntity_HI.getTplIndicatorId() + "");
                        String postCode = valuesJson.getString("postCode");
                        if (!"0".equals(postCode)) {
                            String[] array = postCode.split(",");
                            for (int t = 0; t < array.length; t++) {
                                SrmSpmTplIndicatorOwerEntity_HI OwerEntity_HI = new SrmSpmTplIndicatorOwerEntity_HI();
                                OwerEntity_HI.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                OwerEntity_HI.setPositionId(Integer.parseInt(array[t]));
                                OwerEntity_HI.setOperatorUserId(userId);
                                srmTplIndicatorOwerDAO_HI.saveOrUpdate(OwerEntity_HI);
                            }
                        }*/
                    }

                }
                JSONArray linesArray3 = jsonParams.getJSONArray("dataTable3");
                if (linesArray3.size() > 0) {
                    for (int i = 0, j = linesArray3.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray3.getJSONObject(i);
                        SrmSpmTplIndicatorItemsEntity_HI indicatorItemsEntity_HI = null;
                        if (valuesJson.getInteger("tplIndicatorItemId") == null) {
                            indicatorItemsEntity_HI = new SrmSpmTplIndicatorItemsEntity_HI();
                        } else {
                            indicatorItemsEntity_HI = srmTplIndicatorItemsEntityDAO_HI.getById(valuesJson.getInteger("tplIndicatorItemId"));
                        }
                        indicatorItemsEntity_HI.setOperatorUserId(userId);
                        indicatorItemsEntity_HI.setTplIndicatorId(valuesJson.getInteger("tplIndicatorId"));
                        indicatorItemsEntity_HI.setIndicatorItemId(valuesJson.getInteger("indicatorItemId"));
                        indicatorItemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        srmTplIndicatorItemsEntityDAO_HI.saveOrUpdate(indicatorItemsEntity_HI);
                    }
                }
                return SToolUtils.convertResultJSONObj("S", "修改成功", 1, performanceTplEntity_HI);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            return SToolUtils.convertResultJSONObj("E", "数据保存或修改错误" + e.getMessage(), 1, null);
        }

    }

    /**
     * Description：删除绩效模板行
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject deleteTplLine(JSONObject jsonParams) throws Exception {
        try {
            String type = jsonParams.getString("type");
            Integer worId = jsonParams.getInteger("id");
            Integer userId = jsonParams.getInteger("userId");
            if (!"".equals(type) && worId != null) {
                if ("T".equals(type)) {
                    SrmSpmTplCategoriesEntity_HI categoriesEntity_HI = srmTplCategoriesDAO_HI.getById(worId);
                    srmTplCategoriesDAO_HI.delete(categoriesEntity_HI);
                    return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
                } else if ("Y".equals(type)) {
                    SrmSpmTplDimensionEntity_HI dimensionEntity_HI = srmTplDimensionEntityDAO_HI.getById(worId);
                    List<SrmSpmTplIndicatorsEntity_HI> list = srmTplIndicatorsEntityDAO_HI.findByProperty("tpl_dimension_id", worId);
                    if (!list.isEmpty()) {
                        srmTplIndicatorItemsEntityDAO_HI.executeSql("DELETE FROM srm_spm_tpl_indicator_items  WHERE EXISTS(SELECT * FROM srm_spm_tpl_indicators sti WHERE EXISTS(SELECT * FROM srm_spm_tpl_dimension sd WHERE sd.TPL_DIMENSION_ID = " + worId + " AND sd.TPL_DIMENSION_ID=sti.TPL_DIMENSION_ID) AND sti.TPL_INDICATOR_ID=srm_spm_tpl_indicator_items.TPL_INDICATOR_ID)");
                        srmTplIndicatorsEntityDAO_HI.delete(list);
                    }
                    srmTplDimensionEntityDAO_HI.delete(dimensionEntity_HI);
                    return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
                } else if ("H".equals(type)) {
                    SrmSpmTplIndicatorsEntity_HI indicatorsEntity_HI = srmTplIndicatorsEntityDAO_HI.getById(worId);
                    List<SrmSpmTplIndicatorItemsEntity_HI> list = srmTplIndicatorItemsEntityDAO_HI.findByProperty("tpl_indicator_id", worId);
                    if (!list.isEmpty()) {
                        srmTplIndicatorItemsEntityDAO_HI.delete(list);
                    }
                    srmTplIndicatorsEntityDAO_HI.delete(indicatorsEntity_HI);
                    return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
                } else if ("N".equals(type)) {
                    SrmSpmTplIndicatorItemsEntity_HI itemsEntity_HI = srmTplIndicatorItemsEntityDAO_HI.getById(worId);
                    srmTplIndicatorItemsEntityDAO_HI.delete(itemsEntity_HI);
                    return SToolUtils.convertResultJSONObj("S", "数据删除成功", 1, null);
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数错误，请重试！", 1, null);
            }
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", "数据删除错误", 1, null);
        }
        return null;
    }

    /**
     * Description：更新状态
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateApproveformancetpl(JSONObject jsonParams) throws Exception {
            Integer tplId = jsonParams.getInteger("tplId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmSpmPerformanceTplEntity_HI performanceTplEntity_HI = null;
            if (tplId == 0) {
                performanceTplEntity_HI = new SrmSpmPerformanceTplEntity_HI();
                String code = saafSequencesUtil.getDocSequences("srm_spm_performance_tpl".toUpperCase(), "SPAT-" + DateUtil.date2Str(new Date(), "yyyyMMdd") + "-", 4);
                performanceTplEntity_HI.setTplCode(code);
                //performanceTplEntity_HI.setTplDomain(jsonParams.getString("tplDomain"));
                performanceTplEntity_HI.setDescription(jsonParams.getString("description"));
                performanceTplEntity_HI.setOperatorUserId(userId);
                performanceTplEntity_HI.setStatus("ACTIVE");
                performanceTplEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
                performanceTplEntity_HI.setTplFrequency(jsonParams.getString("tplFrequency"));
                performanceTplEntity_HI.setTplName(jsonParams.getString("tplName"));
                performanceTplEntity_HI.setStartDate(new Date());
                performanceTplEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
/*                JSONArray linesArray = jsonParams.getJSONArray("dataTable");
                if (linesArray.size() > 0) {
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmTplCategoriesEntity_HI categoriesEntity = new SrmSpmTplCategoriesEntity_HI();
                        categoriesEntity.setTplId(performanceTplEntity_HI.getTplId());
                        categoriesEntity.setCategoryId(valuesJson.getInteger("categoryId"));
                        categoriesEntity.setDescription(valuesJson.getString("description"));
                        categoriesEntity.setOperatorUserId(userId);
                        srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity);
                    }
                }*/
                JSONArray linesArray1 = jsonParams.getJSONArray("dataTable1");
                if (linesArray1.size() > 0) {
                    for (int i = 0, j = linesArray1.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray1.getJSONObject(i);
                        SrmSpmTplDimensionEntity_HI dimensionEntity_HI = new SrmSpmTplDimensionEntity_HI();
                        dimensionEntity_HI.setOperatorUserId(userId);
                        dimensionEntity_HI.setTplId(performanceTplEntity_HI.getTplId());
                        dimensionEntity_HI.setDescription(valuesJson.getString("descriptione"));
                        dimensionEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                        dimensionEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HI);
                        //每个评价维度都必须有至少一个评价指标
                        List<SrmSpmTplIndicatorsEntity_HI> indicatorsEntity_HI = srmTplIndicatorsEntityDAO_HI.findByProperty("tplDimensionId",valuesJson.getInteger("tplDimensionId"));
                        if(ObjectUtils.isEmpty(indicatorsEntity_HI)){
                            throw new UtilsException("评价维度"+valuesJson.getString("evaluateDimension")+"必须有至少一个评价指标");
                        }
                    }
                }
                return SToolUtils.convertResultJSONObj("S", "保存成功", 1, performanceTplEntity_HI);
            } else {
                performanceTplEntity_HI = srmSpmPerformanceTplDAO_HI.getById(tplId);
                //performanceTplEntity_HI.setTplDomain(jsonParams.getString("tplDomain"));
                performanceTplEntity_HI.setDescription(jsonParams.getString("description"));
                performanceTplEntity_HI.setOperatorUserId(userId);
                performanceTplEntity_HI.setStatus("ACTIVE");
                performanceTplEntity_HI.setOrgId(jsonParams.getInteger("orgId"));
                performanceTplEntity_HI.setTplFrequency(jsonParams.getString("tplFrequency"));
                performanceTplEntity_HI.setTplName(jsonParams.getString("tplName"));
                performanceTplEntity_HI.setStartDate(new Date());
                performanceTplEntity_HI.setEndDate(jsonParams.getDate("endDate"));
                srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
                /*List<SrmSpmTplCategoriesEntity_HI> list = srmTplCategoriesDAO_HI.findByProperty("tpl_id", tplId);
                srmTplCategoriesDAO_HI.delete(list);*/
 /*               JSONArray linesArray = jsonParams.getJSONArray("dataTable");
                if (linesArray.size() > 0) {
                    for (int i = 0, j = linesArray.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray.getJSONObject(i);
                        SrmSpmTplCategoriesEntity_HI categoriesEntity = new SrmSpmTplCategoriesEntity_HI();
                        categoriesEntity.setTplId(performanceTplEntity_HI.getTplId());
                        categoriesEntity.setCategoryId(valuesJson.getInteger("categoryId"));
                        categoriesEntity.setDescription(valuesJson.getString("description"));
                        categoriesEntity.setOperatorUserId(userId);
                        srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity);
                    }
                }*/
                JSONArray linesArray2 = jsonParams.getJSONArray("dataTable2");
                if (linesArray2.size() > 0) {
                    for (int i = 0, j = linesArray2.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray2.getJSONObject(i);
                        SrmSpmTplIndicatorsEntity_HI indicatorsEntity_HI = null;
                        if (valuesJson.getInteger("tplIndicatorId") == null) {
                            indicatorsEntity_HI = new SrmSpmTplIndicatorsEntity_HI();
                        } else {
                            indicatorsEntity_HI = srmTplIndicatorsEntityDAO_HI.getById(valuesJson.getInteger("tplIndicatorId"));
                        }
                        indicatorsEntity_HI.setOperatorUserId(userId);
                        indicatorsEntity_HI.setIndicatorId(valuesJson.getInteger("indicatorId"));
                        indicatorsEntity_HI.setTplDimensionId(valuesJson.getInteger("tplDimensionId"));
                        indicatorsEntity_HI.setScoreDeductingLimit(valuesJson.getBigDecimal("scoreDeductingLimit"));
                        indicatorsEntity_HI.setIndicatorWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        indicatorsEntity_HI.setTargetValue(valuesJson.getBigDecimal("targetValue"));
                        srmTplIndicatorsEntityDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                        /*srmTplIndicatorOwerDAO_HI.executeSql("DELETE FROM srm_spm_tpl_indicator_ower WHERE tpl_indicator_id = " + indicatorsEntity_HI.getTplIndicatorId() + "");
                        String postCode = valuesJson.getString("postCode");
                        if (!"0".equals(postCode)) {
                            String[] array = postCode.split(",");
                            for (int t = 0; t < array.length; t++) {
                                SrmSpmTplIndicatorOwerEntity_HI OwerEntity_HI = new SrmSpmTplIndicatorOwerEntity_HI();
                                OwerEntity_HI.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                OwerEntity_HI.setPositionId(Integer.parseInt(array[t]));
                                OwerEntity_HI.setOperatorUserId(userId);
                                srmTplIndicatorOwerDAO_HI.saveOrUpdate(OwerEntity_HI);
                            }
                        }*/
                    }
                }
                JSONArray linesArray1 = jsonParams.getJSONArray("dataTable1");
                if (linesArray1.size() > 0) {
                    for (int i = 0, j = linesArray1.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray1.getJSONObject(i);

                        SrmSpmTplDimensionEntity_HI dimensionEntity_HI = null;
                        if (valuesJson.getInteger("tplDimensionId") == null) {
                            dimensionEntity_HI = new SrmSpmTplDimensionEntity_HI();
                        } else {
                            dimensionEntity_HI = srmTplDimensionEntityDAO_HI.getById(valuesJson.getInteger("tplDimensionId"));
                        }
                        dimensionEntity_HI.setOperatorUserId(userId);
                        dimensionEntity_HI.setTplId(performanceTplEntity_HI.getTplId());
                        dimensionEntity_HI.setDescription(valuesJson.getString("descriptione"));
                        dimensionEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                        dimensionEntity_HI.setDimensionWeight(valuesJson.getBigDecimal("dimensionWeight"));
                        srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HI);
                        //每个评价维度都必须有至少一个评价指标
                        List<SrmSpmTplIndicatorsEntity_HI> indicatorsEntity_HI = srmTplIndicatorsEntityDAO_HI.findByProperty("tplDimensionId",valuesJson.getInteger("tplDimensionId"));
                        if(ObjectUtils.isEmpty(indicatorsEntity_HI)){
                            throw new UtilsException("评价维度"+valuesJson.getString("evaluateDimension")+"必须有至少一个评价指标");
                        }
                    }
                }

                /*JSONArray linesArray3 = jsonParams.getJSONArray("dataTable3");
                if (linesArray3.size() > 0) {
                    for (int i = 0, j = linesArray3.size(); i < j; i++) {
                        JSONObject valuesJson = linesArray3.getJSONObject(i);
                        SrmSpmTplIndicatorItemsEntity_HI indicatorItemsEntity_HI = null;
                        if (valuesJson.getInteger("tplIndicatorItemId") == null) {
                            indicatorItemsEntity_HI = new SrmSpmTplIndicatorItemsEntity_HI();
                        } else {
                            indicatorItemsEntity_HI = srmTplIndicatorItemsEntityDAO_HI.getById(valuesJson.getInteger("tplIndicatorItemId"));
                        }
                        indicatorItemsEntity_HI.setOperatorUserId(userId);
                        indicatorItemsEntity_HI.setTplIndicatorId(valuesJson.getInteger("tplIndicatorId"));
                        indicatorItemsEntity_HI.setIndicatorItemId(valuesJson.getInteger("indicatorItemId"));
                        indicatorItemsEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                        srmTplIndicatorItemsEntityDAO_HI.saveOrUpdate(indicatorItemsEntity_HI);
                    }
                }*/
                return SToolUtils.convertResultJSONObj("S", "修改成功", 1, performanceTplEntity_HI);
            }
    }

    /**
     * Description：更新状态
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public JSONObject updateRejecteformancetpl(JSONObject jsonParams) throws Exception {
        try {
            Integer tplId = jsonParams.getInteger("tplId");
            Integer userId = jsonParams.getInteger("varUserId");
            SrmSpmPerformanceTplEntity_HI performanceTplEntity_HI = srmSpmPerformanceTplDAO_HI.getById(tplId);
            performanceTplEntity_HI.setOperatorUserId(userId);
            performanceTplEntity_HI.setStatus("INACTIVE");
            performanceTplEntity_HI.setEndDate(new Date());
            srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
            return SToolUtils.convertResultJSONObj("S", "数据失效成功！", 1, performanceTplEntity_HI);
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", "数据保存或修改错误" + e.getMessage(), 1, null);
        }
    }

    /**
     * Description：绩效模板导出
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmPerformanceTplEntity_HI_RO> performanceTplListExport(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceTplEntity_HI_RO.QUERY_EXP_INFO_LIST);
            Map<String, Object> queryParamMap = new HashMap();
            SaafToolUtils.parperParam(paramJSON, "tpl.org_id", "orgId", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_domain", "tplDomain", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_code", "tplCode", queryParam, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "tpl.tpl_name", "tplName", queryParam, queryParamMap, "LIKE");
            SaafToolUtils.parperParam(paramJSON, "tpl.status", "status", queryParam, queryParamMap, "=");
            String creationDateFrom = paramJSON.getString("creationDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryParam.append(" AND trunc(tpl.creation_date) >= to_date(:creationDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("creationDateFrom", creationDateFrom);
            }
            String creationDateTo = paramJSON.getString("creationDateTo");
            if (creationDateTo != null && !"".equals(creationDateTo.trim())) {
                queryParam.append(" AND trunc(tpl.creation_date) <= to_date(:creationDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("creationDateTo", creationDateTo);
            }
            String startDateFrom = paramJSON.getString("startDateFrom");
            if (creationDateFrom != null && !"".equals(creationDateFrom.trim())) {
                queryParam.append(" AND trunc(tpl.start_date) >= to_date(:startDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("startDateFrom", startDateFrom);
            }
            String startDateTo = paramJSON.getString("startDateTo");
            if (startDateTo != null && !"".equals(startDateTo.trim())) {
                queryParam.append(" AND trunc(tpl.start_date) <= to_date(:startDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("startDateTo", startDateTo);
            }
            queryParam.append(" ORDER BY tpl.creation_date DESC");
            List<SrmSpmPerformanceTplEntity_HI_RO> list = srmSpmPerformanceTplDAO_HI_RO.findList(queryParam, queryParamMap);
            return list;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("导出失败");
        }
        //return null;
    }

    /**
     * Description：保存
     * @param tplId
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Map<String, Object> saveCopyPerformanceTpl(Integer tplId) throws Exception {
        try {
            SrmSpmPerformanceTplEntity_HI performanceTplEntity_HI = srmSpmPerformanceTplDAO_HI.getById(tplId);
            if (performanceTplEntity_HI != null) {
                Integer userId = performanceTplEntity_HI.getCreatedBy();
                if (performanceTplEntity_HI.getFromTplId() != null) {
                    SrmSpmPerformanceTplEntity_HI performanceTplEntity_HIt = new SrmSpmPerformanceTplEntity_HI();
                    performanceTplEntity_HIt.setFromTplId(tplId);
                    performanceTplEntity_HIt.setOperatorUserId(userId);
                    String code = performanceTplEntity_HI.getTplCode().substring(performanceTplEntity_HI.getTplCode().length() - 3, performanceTplEntity_HI.getTplCode().length());
                    Integer nn = Integer.parseInt(code) + 1;
                    if (nn >= 9 && nn <= 98) {
                        code = "0" + String.valueOf(nn);
                    } else if (nn >= 99) {
                        code = String.valueOf(nn);
                    } else {
                        code = "00" + String.valueOf(nn);
                    }
                    String codet = performanceTplEntity_HI.getTplCode().substring(0, 20) + code;
                    performanceTplEntity_HIt.setTplCode(codet);
                    performanceTplEntity_HIt.setStatus("NEW");
                    //performanceTplEntity_HIt.setTplDomain(performanceTplEntity_HI.getTplDomain());
                    performanceTplEntity_HIt.setOrgId(performanceTplEntity_HI.getOrgId());
                    performanceTplEntity_HIt.setTplFrequency(performanceTplEntity_HI.getTplFrequency());
                    performanceTplEntity_HIt.setTplName(performanceTplEntity_HI.getTplName());
                    srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HIt);
                    performanceTplEntity_HI.setStatus("INACTIVE");
                    performanceTplEntity_HI.setEndDate(new Date());
                    performanceTplEntity_HI.setOperatorUserId(userId);
                    srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
/*                    List<SrmSpmTplCategoriesEntity_HI> categoriesEntity_HI = srmTplCategoriesDAO_HI.findByProperty("tpl_id", tplId);
                    if (!categoriesEntity_HI.isEmpty()) {
                        for (SrmSpmTplCategoriesEntity_HI sdt : categoriesEntity_HI) {
                            SrmSpmTplCategoriesEntity_HI categoriesEntity_HIt = new SrmSpmTplCategoriesEntity_HI();
                            categoriesEntity_HIt.setOperatorUserId(userId);
                            categoriesEntity_HIt.setTplId(performanceTplEntity_HIt.getTplId());
                            categoriesEntity_HIt.setCategoryId(sdt.getCategoryId());
                            srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity_HIt);
                        }
                    }*/
                    List<SrmSpmTplDimensionEntity_HI> dimensionEntity_HI = srmTplDimensionEntityDAO_HI.findByProperty("tpl_id", tplId);
                    if (!dimensionEntity_HI.isEmpty()) {
                        for (SrmSpmTplDimensionEntity_HI sdt : dimensionEntity_HI) {
                            SrmSpmTplDimensionEntity_HI dimensionEntity_HIt = new SrmSpmTplDimensionEntity_HI();
                            dimensionEntity_HIt.setOperatorUserId(userId);
                            dimensionEntity_HIt.setTplId(performanceTplEntity_HIt.getTplId());
                            dimensionEntity_HIt.setDimensionWeight(sdt.getDimensionWeight());
                            dimensionEntity_HIt.setEvaluateDimension(sdt.getEvaluateDimension());
                            dimensionEntity_HIt.setDescription(sdt.getDescription());
                            srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HIt);
                            List<SrmSpmTplIndicatorsEntity_HI> list = srmTplIndicatorsEntityDAO_HI.findByProperty("tpl_dimension_id", sdt.getTplDimensionId());
                            if (!list.isEmpty()) {
                                for (SrmSpmTplIndicatorsEntity_HI sct : list) {
                                    SrmSpmTplIndicatorsEntity_HI indicatorsEntity_HI = new SrmSpmTplIndicatorsEntity_HI();
                                    indicatorsEntity_HI.setOperatorUserId(userId);
                                    indicatorsEntity_HI.setTplDimensionId(dimensionEntity_HIt.getTplDimensionId());
                                    indicatorsEntity_HI.setScoreDeductingLimit(sct.getScoreDeductingLimit());
                                    indicatorsEntity_HI.setIndicatorWeight(sct.getIndicatorWeight());
                                    indicatorsEntity_HI.setTargetValue(sct.getTargetValue());
                                    indicatorsEntity_HI.setIndicatorId(sct.getIndicatorId());
                                    srmTplIndicatorsEntityDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                                    List<SrmSpmTplIndicatorOwerEntity_HI> Ower = srmTplIndicatorOwerDAO_HI.findByProperty("tpl_indicator_id", sct.getTplIndicatorId());
                                    if (!Ower.isEmpty()) {
                                        for (SrmSpmTplIndicatorOwerEntity_HI owHt : Ower) {
                                            SrmSpmTplIndicatorOwerEntity_HI OwerEntity_HI = new SrmSpmTplIndicatorOwerEntity_HI();
                                            OwerEntity_HI.setPositionId(owHt.getPositionId());
                                            OwerEntity_HI.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                            OwerEntity_HI.setOperatorUserId(userId);
                                            srmTplIndicatorOwerDAO_HI.saveOrUpdate(OwerEntity_HI);
                                        }
                                    }
                                    List<SrmSpmTplIndicatorItemsEntity_HI> items = srmTplIndicatorItemsEntityDAO_HI.findByProperty("tpl_indicator_id", sct.getTplIndicatorId());
                                    if (!items.isEmpty()) {
                                        for (SrmSpmTplIndicatorItemsEntity_HI sctItems : items) {
                                            SrmSpmTplIndicatorItemsEntity_HI itemsEntity_HIt = new SrmSpmTplIndicatorItemsEntity_HI();
                                            itemsEntity_HIt.setOperatorUserId(userId);
                                            itemsEntity_HIt.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                            itemsEntity_HIt.setIndicatorItemId(sctItems.getIndicatorItemId());
                                            itemsEntity_HIt.setScore(sctItems.getScore());
                                            srmTplIndicatorItemsEntityDAO_HI.saveOrUpdate(itemsEntity_HIt);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return SToolUtils.convertResultJSONObj("S", "指标版本复制成功！", 1, performanceTplEntity_HIt.getTplId());
                } else {
                    SrmSpmPerformanceTplEntity_HI performanceTplEntity_HIt = new SrmSpmPerformanceTplEntity_HI();
                    performanceTplEntity_HIt.setFromTplId(tplId);
                    performanceTplEntity_HIt.setOperatorUserId(userId);
                    performanceTplEntity_HIt.setTplCode(performanceTplEntity_HI.getTplCode() + "-v001");
                    performanceTplEntity_HIt.setStatus("NEW");
                    //performanceTplEntity_HIt.setTplDomain(performanceTplEntity_HI.getTplDomain());
                    performanceTplEntity_HIt.setOrgId(performanceTplEntity_HI.getOrgId());
                    performanceTplEntity_HIt.setTplFrequency(performanceTplEntity_HI.getTplFrequency());
                    performanceTplEntity_HIt.setTplName(performanceTplEntity_HI.getTplName());
                    performanceTplEntity_HI.setStatus("INACTIVE");
                    performanceTplEntity_HI.setEndDate(new Date());
                    performanceTplEntity_HI.setOperatorUserId(userId);
                    srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HI);
                    srmSpmPerformanceTplDAO_HI.saveOrUpdate(performanceTplEntity_HIt);
/*                    List<SrmSpmTplCategoriesEntity_HI> categoriesEntity_HI = srmTplCategoriesDAO_HI.findByProperty("tpl_id", tplId);
                    if (!categoriesEntity_HI.isEmpty()) {
                        for (SrmSpmTplCategoriesEntity_HI sdt : categoriesEntity_HI) {
                            SrmSpmTplCategoriesEntity_HI categoriesEntity_HIt = new SrmSpmTplCategoriesEntity_HI();
                            categoriesEntity_HIt.setOperatorUserId(userId);
                            categoriesEntity_HIt.setTplId(performanceTplEntity_HIt.getTplId());
                            categoriesEntity_HIt.setCategoryId(sdt.getCategoryId());
                            srmTplCategoriesDAO_HI.saveOrUpdate(categoriesEntity_HIt);
                        }
                    }*/
                    List<SrmSpmTplDimensionEntity_HI> dimensionEntity_HI = srmTplDimensionEntityDAO_HI.findByProperty("tpl_id", tplId);
                    if (!dimensionEntity_HI.isEmpty()) {
                        for (SrmSpmTplDimensionEntity_HI sdt : dimensionEntity_HI) {
                            SrmSpmTplDimensionEntity_HI dimensionEntity_HIt = new SrmSpmTplDimensionEntity_HI();
                            dimensionEntity_HIt.setOperatorUserId(userId);
                            dimensionEntity_HIt.setTplId(performanceTplEntity_HIt.getTplId());
                            dimensionEntity_HIt.setDimensionWeight(sdt.getDimensionWeight());
                            dimensionEntity_HIt.setEvaluateDimension(sdt.getEvaluateDimension());
                            dimensionEntity_HIt.setDescription(sdt.getDescription());
                            srmTplDimensionEntityDAO_HI.saveOrUpdate(dimensionEntity_HIt);
                            List<SrmSpmTplIndicatorsEntity_HI> list = srmTplIndicatorsEntityDAO_HI.findByProperty("tpl_dimension_id", sdt.getTplDimensionId());
                            if (!list.isEmpty()) {
                                for (SrmSpmTplIndicatorsEntity_HI sct : list) {
                                    SrmSpmTplIndicatorsEntity_HI indicatorsEntity_HI = new SrmSpmTplIndicatorsEntity_HI();
                                    indicatorsEntity_HI.setOperatorUserId(userId);
                                    indicatorsEntity_HI.setTplDimensionId(dimensionEntity_HIt.getTplDimensionId());
                                    indicatorsEntity_HI.setScoreDeductingLimit(sct.getScoreDeductingLimit());
                                    indicatorsEntity_HI.setIndicatorWeight(sct.getIndicatorWeight());
                                    indicatorsEntity_HI.setIndicatorId(sct.getIndicatorId());
                                    indicatorsEntity_HI.setTargetValue(sct.getTargetValue());
                                    srmTplIndicatorsEntityDAO_HI.saveOrUpdate(indicatorsEntity_HI);
                                    List<SrmSpmTplIndicatorOwerEntity_HI> Ower = srmTplIndicatorOwerDAO_HI.findByProperty("tpl_indicator_id", sct.getTplIndicatorId());
                                    if (!Ower.isEmpty()) {
                                        for (SrmSpmTplIndicatorOwerEntity_HI owHt : Ower) {
                                            SrmSpmTplIndicatorOwerEntity_HI OwerEntity_HI = new SrmSpmTplIndicatorOwerEntity_HI();
                                            OwerEntity_HI.setPositionId(owHt.getPositionId());
                                            OwerEntity_HI.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                            OwerEntity_HI.setOperatorUserId(userId);
                                            srmTplIndicatorOwerDAO_HI.saveOrUpdate(OwerEntity_HI);
                                        }
                                    }
                                    List<SrmSpmTplIndicatorItemsEntity_HI> items = srmTplIndicatorItemsEntityDAO_HI.findByProperty("tpl_indicator_id", sct.getTplIndicatorId());
                                    if (!items.isEmpty()) {
                                        for (SrmSpmTplIndicatorItemsEntity_HI sctItems : items) {
                                            SrmSpmTplIndicatorItemsEntity_HI itemsEntity_HIt = new SrmSpmTplIndicatorItemsEntity_HI();
                                            itemsEntity_HIt.setOperatorUserId(userId);
                                            itemsEntity_HIt.setTplIndicatorId(indicatorsEntity_HI.getTplIndicatorId());
                                            itemsEntity_HIt.setIndicatorItemId(sctItems.getIndicatorItemId());
                                            itemsEntity_HIt.setScore(sctItems.getScore());
                                            srmTplIndicatorItemsEntityDAO_HI.saveOrUpdate(itemsEntity_HIt);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return SToolUtils.convertResultJSONObj("S", "指标版本复制成功！", 1, performanceTplEntity_HIt.getTplId());
                }
            } else {
                return SToolUtils.convertResultJSONObj("E", "参数无效请重试！", 1, null);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            throw new UtilsException("指标版本复制失败");
        }
        //return null;
    }

    /**
     * Description：查询绩效模板
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmIndicatorItemsEntity_HI_RO> queryItemTplList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        count.append(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(paramJSON, "st.INDICATOR_ID", "indicatorId", queryParam, queryParamMap, "=");
        if (paramJSON != null) {
            String tt = paramJSON.getString("indicatorItemIdt");
            if (!"[]".equals(tt)) {
                if (!"T".equals(tt) && !"".equals(tt)) {
                    tt = tt.replace("[", "").replace("]", "");
                    String[] array = tt.split(",");
                    String ids = " ";
                    for (int j = 0; j < array.length; j++) {
                        if (j < array.length - 1) {
                            ids += array[j] + ",";
                        } else {
                            ids += array[j];
                        }
                    }
                    sb.append(" AND st.INDICATOR_ITEM_ID not in(" + ids + ")");
                }
            }
        }
        sb.append(queryParam);
        count.append(queryParam);
        sb.append(" ORDER BY st.CREATION_DATE DESC");
        Pagination<SrmSpmIndicatorItemsEntity_HI_RO> result = srmSpmIndicatorsItemsEntityHIRO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：绩效模板计数
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean countformancetpl(JSONObject jsonParams) throws Exception {
        boolean flag = true;
        StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceTplEntity_HI_RO.QUERY_CATE_INFO_COUNT);
        String tplFrequency = jsonParams.getString("tplFrequency");
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(tplFrequency)) {
            return flag;
        }
        //queryParam.append(" AND tpl.TPL_DOMAIN='" + jsonParams.getString("tplDomain") + "' AND tpl.ORG_ID = " + jsonParams.getInteger("orgId") + " AND TPL_FREQUENCY = '" + tplFrequency + "' AND tpl.status = 'ACTIVE' ");
        queryParam.append(" AND tpl.ORG_ID = " + jsonParams.getInteger("orgId") + " AND TPL_FREQUENCY = '" + tplFrequency + "' AND tpl.status = 'ACTIVE' ");
/*        JSONArray linesArray = jsonParams.getJSONArray("dataTable");
        if (linesArray.size() > 0) {
            String ids = "";
            for (int i = 0, j = linesArray.size(); i < j; i++) {
                JSONObject valuesJson = linesArray.getJSONObject(i);
                if (i < linesArray.size() - 1) {
                    ids += valuesJson.getString("categoryId") + ",";
                } else {
                    ids += valuesJson.getString("categoryId");
                }
            }
            queryParam.append(" AND cate.CATEGORY_ID in(" + ids + ")");
        }*/
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        List<SrmSpmPerformanceTplEntity_HI_RO> list = srmSpmPerformanceTplDAO_HI_RO.findList(queryParam, queryParamMap);
        if (!list.isEmpty()) {
            flag = false;
        }
        return flag;
    }

    /**
     * Description：
     * @param jsonParams
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public boolean countCateScheme(JSONObject jsonParams) throws Exception {
        boolean flag = true;
        StringBuffer queryParam = new StringBuffer(SrmSpmPerformanceSchemeEntity_HI_RO.QUERY_SCHEME_NUMBER_SQL);
        queryParam.append(" AND ps.TPL_ID=" + jsonParams.getInteger("tplId") + " AND ps.STATUS != 'CANCELED' ");
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        List<SrmSpmPerformanceSchemeEntity_HI_RO> list = srmSpmPerformanceSchemeEntityDAO_HI_RO.findList(queryParam, queryParamMap);
        if (!list.isEmpty()) {
            flag = false;
        }
        return flag;
    }
}
