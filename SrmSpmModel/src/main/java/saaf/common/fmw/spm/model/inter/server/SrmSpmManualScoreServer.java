package saaf.common.fmw.spm.model.inter.server;

import saaf.common.fmw.base.model.entities.SaafLookupValuesEntity_HI;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseUserCategoriesEntity_HI_RO;
import saaf.common.fmw.base.model.inter.ISrmBaseUserCategories;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.spm.model.inter.ISrmSpmManualScore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhg.base.utils.SToolUtils;
import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.po.model.entities.readonly.SrmPoBaseCategoriesEntity_HI_RO;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierInfoEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;
import saaf.common.fmw.spm.model.entities.SrmSpmManualScoreEntity_HI;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorItemsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;

import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.dao.ViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import saaf.common.fmw.utils.SrmUtils;
/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：加减分查询Server
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */
@Component("srmSpmManualScoreServer")
public class SrmSpmManualScoreServer implements ISrmSpmManualScore {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmManualScoreServer.class);

    @Autowired
    private ViewObject<SrmSpmManualScoreEntity_HI> srmSpmManualScoreDAO_HI;

    @Autowired
    private ViewObject<SrmSpmIndicatorItemsEntity_HI> SrmSpmIndicatorItemsDAO_HI;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorItemsEntity_HI_RO> srmSpmIndicatorItemsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmManualScoreEntity_HI_RO> srmSpmManualScoreDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> lookupValuesEntityDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmSpmIndicatorsEntity_HI_RO> srmSpmIndicatorsEntityDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPosSupplierInfoEntity_HI_RO> srmPosSupplierInfoEntityDAO_HI_RO;

    @Autowired
    private BaseViewObject<SrmPoBaseCategoriesEntity_HI_RO> srmPoBaseCategoriesEntityDAO_HI_RO;
    @Autowired
    private ISrmBaseUserCategories srmBaseUserCategories;

    @Autowired
    private ViewObject<SaafLookupValuesEntity_HI> saafLookupValuesDAO_HI;//快码值


    public SrmSpmManualScoreServer() {
        super();
    }

    /**
     * Description：查询加减分
     * @param queryParamJSON 加减分查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    public List<SrmSpmManualScoreEntity_HI> findSrmSpmManualScoreInfo(JSONObject queryParamJSON) {
        Map<String, Object> queryParamMap = SToolUtils.fastJsonObj2Map(queryParamJSON);
        List<SrmSpmManualScoreEntity_HI> findListResult = srmSpmManualScoreDAO_HI.findList("from SrmSpmManualScoreEntity_HI", queryParamMap);
        return findListResult;
    }

    /**
     * Description：保存加减分
     * @param queryParamJSON
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    public Object saveSrmSpmManualScoreInfo(JSONObject queryParamJSON) {
        SrmSpmManualScoreEntity_HI srmSpmManualScoreEntity_HI = JSON.parseObject(queryParamJSON.toString(), SrmSpmManualScoreEntity_HI.class);
        Object resultData = srmSpmManualScoreDAO_HI.save(srmSpmManualScoreEntity_HI);
        return resultData;
    }

    /**
     * Description：查询加减分
     * @param paramJSON 加减分查询参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Pagination<SrmSpmManualScoreEntity_HI_RO> findManualInfoList(JSONObject paramJSON, Integer pageIndex, Integer pageRows) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer sb = new StringBuffer();
        //StringBuffer count = new StringBuffer();
        StringBuffer queryParam = new StringBuffer();
        sb.append(SrmSpmManualScoreEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
        //count.append(SrmSpmManualScoreEntity_HI_RO.QUERY_COUNT);
        SaafToolUtils.parperParam(paramJSON, "sce.org_id", "orgId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sce.evaluate_dimension", "evaluateDimension", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sce.supplier_id", "supplierId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sce.status", "status", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sce.category_id", "categoryId", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sp.indicator_code", "indicatorCode", queryParam, queryParamMap, "=");
        SaafToolUtils.parperParam(paramJSON, "sp.indicator_type", "indicatorType", queryParam, queryParamMap, "=");
        String happenedDateFrom = paramJSON.getString("happenedDateFrom");
        if (happenedDateFrom != null && !"".equals(happenedDateFrom.trim())) {
            queryParam.append(" AND sce.happened_date >= to_date(:happenedDateFrom, 'yyyy-mm-dd')\n");
            queryParamMap.put("happenedDateFrom", happenedDateFrom);
        }
        String happenedDateTo = paramJSON.getString("happenedDateTo");
        if (happenedDateTo != null && !"".equals(happenedDateTo.trim())) {
            queryParam.append(" AND sce.happened_date <= to_date(:happenedDateTo, 'yyyy-mm-dd')\n");
            queryParamMap.put("happenedDateTo", happenedDateTo);
        }

        sb.append(queryParam);

        sb.append(" and sce.org_id in (SELECT distinct (sua.inst_id) Organization_Id\n" +
                "                               FROM saaf_user_access_orgs sua,\n" +
                "                                    saaf_institution      si,\n" +
                "                                    saaf_users            su\n" +
                "                              WHERE sua.user_id = su.user_id\n" +
                "                                AND sua.inst_id = si.inst_id\n" +
                "                                and sua.platform_code = 'SAAF'\n" +
                "                                and si.inst_type='ORG'\n" +
                "                                and sua.user_id = "+paramJSON.getInteger("varUserId")+") "+
                "      AND sce.item_type IN  (" +getSupplierType(paramJSON.getInteger("varUserId"))+")");
        //count.append(queryParam);
        String countSql = "select count(1) from (" + sb + ")";
        sb.append(" ORDER BY sce.creation_date DESC");
        LOGGER.debug(sb.toString());
        //分页BUG
        //Pagination<SrmSpmManualScoreEntity_HI_RO> result = srmSpmManualScoreDAO_HI_RO.findPagination(sb.toString(), count, queryParamMap, pageIndex, pageRows);
        Pagination<SrmSpmManualScoreEntity_HI_RO> result = srmSpmManualScoreDAO_HI_RO.findPagination(sb.toString(),countSql, queryParamMap, pageIndex, pageRows);
        return result;
    }

    /**
     * Description：查询供应商类型
     * @param userId
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
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
     * Description：删除加减分
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Object deleteManualScore(JSONObject jsonParams) throws Exception {
        JSONArray exceptionIds = jsonParams.getJSONArray("data");
        if (exceptionIds.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        SrmSpmManualScoreEntity_HI manualScoreEntity_HI = null;
        List<SrmSpmManualScoreEntity_HI> list = new ArrayList<SrmSpmManualScoreEntity_HI>();
        for (int i = 0, j = exceptionIds.size(); i < j; i++) {
            Integer exceptionId = exceptionIds.getInteger(i);
            manualScoreEntity_HI = srmSpmManualScoreDAO_HI.getById(exceptionId);
            if (manualScoreEntity_HI != null && "NEW".equals(manualScoreEntity_HI.getStatus())) { //拟定删除
                list.add(manualScoreEntity_HI);
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无可删除的数据", 0, null);
        }
        try {
            srmSpmManualScoreDAO_HI.delete(list);
            return SToolUtils.convertResultJSONObj("S", "删除成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception("删除失败");
            throw new UtilsException("删除失败");
        }
    }

    /**
     * Description：保存加减分
     * @param jsonParams 加减分参数
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Object saveManualScore(JSONObject jsonParams) throws Exception {
        JSONArray jsonArray = jsonParams.getJSONArray("data");
        Integer varUserId = jsonParams.getInteger("varUserId");
        try {
            for (int i = 0, j = jsonArray.size(); i < j; i++) {
                JSONObject valuesJson = jsonArray.getJSONObject(i);
                if (valuesJson.getInteger("manualScoreId") == null) {
                    SrmSpmManualScoreEntity_HI manualScoreEntity_HI = new SrmSpmManualScoreEntity_HI();
                    manualScoreEntity_HI.setOperatorUserId(varUserId);
                    manualScoreEntity_HI.setCategoryId(valuesJson.getInteger("categoryId"));
                    manualScoreEntity_HI.setDescription(valuesJson.getString("description"));
                    manualScoreEntity_HI.setEndDate(valuesJson.getDate("endDate"));
                    manualScoreEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                    manualScoreEntity_HI.setFileId(valuesJson.getInteger("fileId"));
                    manualScoreEntity_HI.setOrgId(valuesJson.getString("orgId"));
                    manualScoreEntity_HI.setSupplierId(valuesJson.getString("supplierId"));
                    manualScoreEntity_HI.setIndicatorsId(valuesJson.getInteger("indicatorId"));
                    manualScoreEntity_HI.setIndicatorsItemsId(valuesJson.getIntValue("indicatorItems"));
                    manualScoreEntity_HI.setProject(valuesJson.getString("project"));
                    manualScoreEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                    manualScoreEntity_HI.setStartDate(valuesJson.getDate("startDate"));
                    manualScoreEntity_HI.setStatus(valuesJson.getString("status"));
                    manualScoreEntity_HI.setHappenedDate(valuesJson.getDate("happenedDate"));
                    manualScoreEntity_HI.setItemType(valuesJson.getString("itemType"));
                    srmSpmManualScoreDAO_HI.saveOrUpdate(manualScoreEntity_HI);
                } else {
                    SrmSpmManualScoreEntity_HI manualScoreEntity_HI = srmSpmManualScoreDAO_HI.getById(valuesJson.getInteger("manualScoreId"));
                    manualScoreEntity_HI.setOperatorUserId(varUserId);
                    manualScoreEntity_HI.setCategoryId(valuesJson.getInteger("categoryId"));
                    manualScoreEntity_HI.setDescription(valuesJson.getString("description"));
                    manualScoreEntity_HI.setEndDate(valuesJson.getDate("endDate"));
                    manualScoreEntity_HI.setEvaluateDimension(valuesJson.getString("evaluateDimension"));
                    manualScoreEntity_HI.setFileId(valuesJson.getInteger("fileId"));
                    manualScoreEntity_HI.setOrgId(valuesJson.getString("orgId"));
                    manualScoreEntity_HI.setSupplierId(valuesJson.getString("supplierId"));
                    manualScoreEntity_HI.setIndicatorsId(valuesJson.getInteger("indicatorId"));
                    manualScoreEntity_HI.setIndicatorsItemsId(valuesJson.getIntValue("indicatorItems"));
                    manualScoreEntity_HI.setProject(valuesJson.getString("project"));
                    manualScoreEntity_HI.setScore(valuesJson.getBigDecimal("score"));
                    manualScoreEntity_HI.setStartDate(valuesJson.getDate("startDate"));
                    manualScoreEntity_HI.setStatus(valuesJson.getString("status"));
                    manualScoreEntity_HI.setHappenedDate(valuesJson.getDate("happenedDate"));
                    manualScoreEntity_HI.setItemType(valuesJson.getString("itemType"));
                    srmSpmManualScoreDAO_HI.saveOrUpdate(manualScoreEntity_HI);
                }
            }
            return SToolUtils.convertResultJSONObj("S", "保存成功", 0, null);
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception("保存失败");
            throw new UtilsException("保存失败");
            //return SToolUtils.convertResultJSONObj("E", "保存失败", 0, null);
        }
    }

    /**
     * Description：汇总加减分数量
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    private boolean countManualScore(JSONObject jsonParams) {
        // TODO Auto-generated method stub
        boolean falg = true;
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        queryString.append(SrmSpmManualScoreEntity_HI_RO.QUERY_NUM);
        queryString.append(" AND sce.org_id=" + jsonParams.getInteger("orgId") + " AND sce.supplier_id=" + jsonParams.getInteger("supplierId") + " AND sce.category_id=" + jsonParams.getInteger("categoryId") + " AND sce.INDICATOR_ID=" + jsonParams.getInteger("indicatorId") + " AND sce.INDICATOR_ITEM_ID=" + jsonParams.getInteger("indicatorItems") + " AND status in('ACTIVE','NEW')");
        List<SrmSpmManualScoreEntity_HI_RO> list = srmSpmManualScoreDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        if (!list.isEmpty()) {
            falg = false;
        }
        return falg;
    }

    /**
     * Description：生效加减分
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Object updateEffectiveManualScore(JSONObject jsonParams) throws Exception {
        JSONArray exceptionIds = jsonParams.getJSONArray("data");
        Integer varUserId = jsonParams.getInteger("varUserId");
        if (exceptionIds.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
        }
        SrmSpmManualScoreEntity_HI manualScoreEntity_HI = null;
        List<SrmSpmManualScoreEntity_HI> list = new ArrayList<SrmSpmManualScoreEntity_HI>();
        for (int i = 0, j = exceptionIds.size(); i < j; i++) {
            Integer exceptionId = exceptionIds.getInteger(i);
            manualScoreEntity_HI = srmSpmManualScoreDAO_HI.getById(exceptionId);
            if ("NEW".equals(manualScoreEntity_HI.getStatus())) {//拟定->生效
                manualScoreEntity_HI.setStatus("ACTIVE");
                manualScoreEntity_HI.setStartDate(new Date());
                manualScoreEntity_HI.setOperatorUserId(varUserId);
            }
            if (manualScoreEntity_HI != null) {
                list.add(manualScoreEntity_HI);
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
        }
        try {
            srmSpmManualScoreDAO_HI.update(list);
            return SToolUtils.convertResultJSONObj("S", "生效成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception("生效失败");
            throw new UtilsException("生效失败");
        }
    }

    /**
     * Description：失效加减分
     * @param jsonParams
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Object updateInvalidManualScore(JSONObject jsonParams) throws Exception {
        JSONArray exceptionIds = jsonParams.getJSONArray("data");
        Integer varUserId = jsonParams.getInteger("varUserId");
        if (exceptionIds.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
        }
        SrmSpmManualScoreEntity_HI manualScoreEntity_HI = null;
        List<SrmSpmManualScoreEntity_HI> list = new ArrayList<SrmSpmManualScoreEntity_HI>();
        for (int i = 0, j = exceptionIds.size(); i < j; i++) {
            Integer exceptionId = exceptionIds.getInteger(i);
            manualScoreEntity_HI = srmSpmManualScoreDAO_HI.getById(exceptionId);
            if ("ACTIVE".equals(manualScoreEntity_HI.getStatus())) {//拟定->生效
                manualScoreEntity_HI.setStatus("INACTIVE");
                manualScoreEntity_HI.setEndDate(new Date());
                manualScoreEntity_HI.setOperatorUserId(varUserId);
            }
            if (manualScoreEntity_HI != null) {
                list.add(manualScoreEntity_HI);
            }
        }
        if (list.isEmpty()) {
            return SToolUtils.convertResultJSONObj("S", "无生效的数据", 0, null);
        }
        try {
            srmSpmManualScoreDAO_HI.update(list);
            return SToolUtils.convertResultJSONObj("S", "生效成功", list.size(), null);
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new Exception("生效失败");
            throw new UtilsException("生效失败");
        }
    }
    /**
     * Description：导出加减分
     * @param paramJSON
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SrmSpmManualScoreEntity_HI_RO> exportManualScore(JSONObject paramJSON) throws Exception {
        try {
            Map<String, Object> queryParamMap = new HashMap();
            StringBuffer queryParam = new StringBuffer();
            queryParam.append(SrmSpmManualScoreEntity_HI_RO.QUERY_MANUAL_INFO_LIST);
            SaafToolUtils.parperParam(paramJSON, "sce.org_id", "orgId", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "sce.evaluate_dimension", "evaluateDimension", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "sce.supplier_id", "supplierId", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "sce.status", "status", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "sce.category_id", "categoryId", queryParam, queryParamMap, "=");
            SaafToolUtils.parperParam(paramJSON, "sp.indicator_code", "indicatorCode", queryParam, queryParamMap, "=");
            String happenedDateFrom = paramJSON.getString("happenedDateFrom");
            if (happenedDateFrom != null && !"".equals(happenedDateFrom.trim())) {
                queryParam.append(" AND sce.happened_date >= to_date(:happenedDateFrom, 'yyyy-mm-dd')\n");
                queryParamMap.put("happenedDateFrom", happenedDateFrom);
            }
            String happenedDateTo = paramJSON.getString("happenedDateTo");
            if (happenedDateTo != null && !"".equals(happenedDateTo.trim())) {
                queryParam.append(" AND sce.happened_date <= to_date(:happenedDateTo, 'yyyy-mm-dd')\n");
                queryParamMap.put("happenedDateTo", happenedDateTo);
            }
            queryParam.append(" ORDER BY sce.creation_date DESC");
            LOGGER.debug(queryParam.toString());
            return srmSpmManualScoreDAO_HI_RO.findList(queryParam, queryParamMap);
        } catch (Exception e) {
            //throw new Exception("导出失败");
            throw new UtilsException("导出失败");
        }
    }

    /**
     * Description：查询绩效类型
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public List<SaafLookupValuesEntity_HI_RO> selectindicatorType(JSONObject jsonParam) {
        String evaluateDimension = jsonParam.getString("evaluateDimension");
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(evaluateDimension)) {
            return null;
        }
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap();
        queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEIDT_SQL);
        queryString.append(" AND slv.lookup_type = 'SPM_INDICATOR_NAME' AND slv.tag='" + evaluateDimension + "' AND slv.lookup_code NOT IN ('INSPECT_QUALIFICATION_RATE-A','INSPECT_QUALIFICATION_RATE-B','PRICE_RANK','DELIVERY_RATE')");
        List<SaafLookupValuesEntity_HI_RO> list = lookupValuesEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return list;
    }

    /**
     * Description：查询绩效类型
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public Map<String, Object> findManualIndicatorType(JSONObject jsonParam) {
        String indicatorType = jsonParam.getString("indicatorType");
        String evaluateDimension = jsonParam.getString("evaluateDimension");
        //验证字符串是否含有SQL关键字及字符，有则返回NULL
        if (SrmUtils.isContainSQL(indicatorType) || SrmUtils.isContainSQL(evaluateDimension)) {
            return null;
        }
        Map<String, Object> map = new HashMap();
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap();
        queryString.append(SrmSpmIndicatorsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
        queryString.append(" AND sp.APPLICATION_DOMAIN = '" + jsonParam.getString("applicationDomain") + "' AND sp.INDICATOR_CODE = '" + indicatorType + "' AND sp.INDICATOR_DIMENSION = '" + evaluateDimension + "' AND sp.INDICATOR_STATUS='ACTIVE' AND sp.INDICATOR_TYPE IN ('MANUAL-ADD','MANUAL-DEDUCT','MANUAL-SCORE')");
        List<SrmSpmIndicatorsEntity_HI_RO> list = srmSpmIndicatorsEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        if (!list.isEmpty()) {
            StringBuffer queryStringt = new StringBuffer();
            Map<String, Object> queryParamMapt = new HashMap();
            queryStringt.append(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
            queryStringt.append(" AND st.indicator_id=" + list.get(0).getIndicatorId() + "");
            List<SrmSpmIndicatorItemsEntity_HI_RO> listItems = new ArrayList();
            List<SrmSpmIndicatorItemsEntity_HI_RO> items = srmSpmIndicatorItemsDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
            if (!items.isEmpty()) {
                if (list.get(0).getIndicatorValueType().equals("NUMERIC")) {
                    for (SrmSpmIndicatorItemsEntity_HI_RO ro : items) {
                        SrmSpmIndicatorItemsEntity_HI_RO rot = new SrmSpmIndicatorItemsEntity_HI_RO();
                        rot.setIndicatorItemDesc(ro.getPk1Value() + "<=||<" + ro.getPk2Value());
                        rot.setIndicatorId(ro.getIndicatorId());
                        rot.setIndicatorItemId(ro.getIndicatorItemId());
                        rot.setScore(ro.getScore());
                        rot.setSeqNumber(ro.getSeqNumber());
                        listItems.add(rot);
                    }
                } else {
                    for (SrmSpmIndicatorItemsEntity_HI_RO ro : items) {
                        SrmSpmIndicatorItemsEntity_HI_RO rot = new SrmSpmIndicatorItemsEntity_HI_RO();
                        rot.setIndicatorItemDesc(ro.getIndicatorItemDesc());
                        rot.setIndicatorId(ro.getIndicatorId());
                        rot.setIndicatorItemId(ro.getIndicatorItemId());
                        rot.setScore(ro.getScore());
                        rot.setSeqNumber(ro.getSeqNumber());
                        listItems.add(rot);
                    }
                }
            }
            map.put("Items", listItems);
            map.put("IndicatorType", list.get(0));
        }
        return map;
    }

    @Override
    public SrmSpmIndicatorItemsEntity_HI findManualIndicatorItems(JSONObject jsonParam) {
        // TODO Auto-generated method stub
        SrmSpmIndicatorItemsEntity_HI rod = SrmSpmIndicatorItemsDAO_HI.getById(jsonParam.getInteger("indicatorItems"));
        return rod;
    }

    /**
     * Description：导入
     * @param jsonParam
     * @return =======================================================================
     * Version    Date           Updated By     Description
     * -------    -----------    -----------    ---------------
     * V1.0       2020-07-09     SIE 谢晓霞       创建
     * =======================================================================
     */
    @Override
    public JSONObject saveManualImport(JSONObject jsonParam) throws Exception {
        try {
            JSONArray array = jsonParam.getJSONArray("data");
            Integer userId = jsonParam.getInteger("varUserId");
            JSONArray resultArrayError = new JSONArray();
            JSONObject e = null;
            List<SrmSpmManualScoreEntity_HI> list = new ArrayList<SrmSpmManualScoreEntity_HI>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObj = array.getJSONObject(i);
                String supplierNumber = jsonObj.getString("supplierNumber");
                Integer supplierId = null;
                StringBuffer queryStringt = new StringBuffer(SrmPosSupplierInfoEntity_HI_RO.GET_SUPPLIER_INFO_NAME);
                Map<String, Object> queryParamMapt = new HashMap<String, Object>();
                queryStringt.append(" AND pos.supplier_number = '" + supplierNumber + "'");
                List<SrmPosSupplierInfoEntity_HI_RO> supplierRO = srmPosSupplierInfoEntityDAO_HI_RO.findList(queryStringt.toString(), queryParamMapt);
                if (!supplierRO.isEmpty()) {
                    if (supplierRO.size() == 1) {
                        supplierId = supplierRO.get(0).getSupplierId();
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "供应商信息错误！");
                        e.put("ROW_NUM", i + 1);
                        resultArrayError.add(e);
                        continue;
                    }
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "供应商信息错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                BigDecimal score = jsonObj.getBigDecimal("score");
                if (score == null) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分值不能为空");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                String indicatorName = jsonObj.getString("indicatorName");
                Integer indicatorId = null;
                StringBuffer queryStringdt = new StringBuffer(SrmSpmIndicatorsEntity_HI_RO.QUERY_SUPPLIER_INFO);
                Map<String, Object> queryParamMapdt = new HashMap();
                queryStringdt.append(" and tt.indicatorName='" + indicatorName + "'");
                List<SrmSpmIndicatorsEntity_HI_RO> listIndicators = srmSpmIndicatorsEntityDAO_HI_RO.findList(queryStringdt.toString(), queryParamMapdt);
                if (!listIndicators.isEmpty()) {
                    if (listIndicators.size() == 1) {
                        indicatorId = listIndicators.get(0).getIndicatorId();
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "指标名称错误！");
                        e.put("ROW_NUM", i + 1);
                        resultArrayError.add(e);
                        continue;
                    }
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "指标名称错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }

                Integer indicatorItemId = null;
                String indicatorItemDesc = jsonObj.getString("indicatorItemDesc");
                if (indicatorItemDesc.contains("||")) {
                    String pk1 = "", pk2 = "";
                    String[] tt = indicatorItemDesc.split("<=||<");
                    pk1 = tt[0];
                    pk2 = tt[1];
                    StringBuffer queryStringditem = new StringBuffer(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
                    Map<String, Object> queryParamMapditem = new HashMap<String, Object>();
                    queryStringditem.append(" AND st.PK1_VALUE = '" + pk1 + "' AND st.PK3_VALUE = '" + pk2 + "' AND st.indicator_id='" + indicatorId + "'");
                    List<SrmSpmIndicatorItemsEntity_HI_RO> listItems = srmSpmIndicatorItemsDAO_HI_RO.findList(queryStringditem.toString(), queryParamMapditem);
                    if (!listItems.isEmpty()) {
                        if (listItems.size() == 1) {
                            indicatorItemId = listItems.get(0).getIndicatorItemId();
                            score = listItems.get(0).getScore();
                        } else {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "指标项数据错误！");
                            e.put("ROW_NUM", i + 1);
                            resultArrayError.add(e);
                            continue;
                        }
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "指标项数据错误！");
                        e.put("ROW_NUM", i + 1);
                        resultArrayError.add(e);
                        continue;
                    }
                } else {
                    StringBuffer queryStringditem = new StringBuffer(SrmSpmIndicatorItemsEntity_HI_RO.QUERY_SUPPLIER_INFO_LIST);
                    Map<String, Object> queryParamMapditem = new HashMap<String, Object>();
                    queryStringditem.append(" AND st.indicator_item_desc='" + indicatorItemDesc + "' AND st.indicator_id='" + indicatorId + "'");
                    List<SrmSpmIndicatorItemsEntity_HI_RO> listItems = srmSpmIndicatorItemsDAO_HI_RO.findList(queryStringditem.toString(), queryParamMapditem);
                    if (!listItems.isEmpty()) {
                        if (listItems.size() == 1) {
                            indicatorItemId = listItems.get(0).getIndicatorItemId();
                            score = listItems.get(0).getScore();
                        } else {
                            e = new JSONObject();
                            e.put("ERR_MESSAGE", "指标项数据错误！");
                            e.put("ROW_NUM", i + 1);
                            resultArrayError.add(e);
                            continue;
                        }
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "指标项数据错误！");
                        e.put("ROW_NUM", i + 1);
                        resultArrayError.add(e);
                        continue;
                    }
                }

                String evaluateDimensionName = jsonObj.getString("evaluateDimensionName");

                String evaluateDimension = "";
                StringBuffer queryStringd = new StringBuffer(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEIDT_SQL);
                Map<String, Object> queryParamMapd = new HashMap<String, Object>();
                queryStringd.append(" AND slv.meaning='" + evaluateDimensionName + "' AND slv.lookup_type='SPM_INDICATOR_DIMENSION'");
                List<SaafLookupValuesEntity_HI_RO> lookupRO = lookupValuesEntityDAO_HI_RO.findList(queryStringd.toString(), queryParamMapd);
                if (!lookupRO.isEmpty()) {
                    if (lookupRO.size() == 1) {
                        evaluateDimension = lookupRO.get(0).getLookupCode();
                    } else {
                        e = new JSONObject();
                        e.put("ERR_MESSAGE", "维度错误！");
                        e.put("ROW_NUM", i + 1);
                        resultArrayError.add(e);
                        continue;
                    }
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "维度错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                String description = jsonObj.getString("description");
                Integer categoryId = null;
                String categoryCode = jsonObj.getString("categoryCode");
                StringBuffer queryStringC = new StringBuffer(SrmPoBaseCategoriesEntity_HI_RO.QUERY_CATEGORIES_SQL);
                Map<String, Object> queryParamMapC = new HashMap<String, Object>();
                queryStringC.append(" AND cate.CATEGORY_CODE = '" + categoryCode + "'");
                List<SrmPoBaseCategoriesEntity_HI_RO> cateList = srmPoBaseCategoriesEntityDAO_HI_RO.findList(queryStringC.toString(), queryParamMapC);
                if (!cateList.isEmpty()) {
                    categoryId = cateList.get(0).getCategoryId();
                } else {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分类信息错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                Date happed = jsonObj.getDate("happenedDate");
                if (happed == null) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "分类信息错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                String instName = jsonObj.getString("instName");
                if (!"广东奥马冰箱有限公司".equals(instName)) {
                    e = new JSONObject();
                    e.put("ERR_MESSAGE", "组织信息错误！");
                    e.put("ROW_NUM", i + 1);
                    resultArrayError.add(e);
                    continue;
                }
                SrmSpmManualScoreEntity_HI scoreEntity_HI = new SrmSpmManualScoreEntity_HI();
                scoreEntity_HI.setOrgId(jsonObj.getString("orgId"));
                scoreEntity_HI.setSupplierId(String.valueOf(supplierId));
                scoreEntity_HI.setCategoryId(categoryId);
                scoreEntity_HI.setEvaluateDimension(evaluateDimension);
                scoreEntity_HI.setIndicatorsId(indicatorId);
                scoreEntity_HI.setIndicatorsItemsId(indicatorItemId);
                scoreEntity_HI.setHappenedDate(happed);
                scoreEntity_HI.setScore(score);
                scoreEntity_HI.setDescription(description);
                scoreEntity_HI.setOperatorUserId(userId);
                scoreEntity_HI.setStatus("NEW");
                list.add(scoreEntity_HI);
            }
            if (resultArrayError.size() > 0) {
                return SToolUtils.convertResultJSONObj("ERR_IMPORT", "导入失败", resultArrayError.size(), resultArrayError);
            }
            try {
                srmSpmManualScoreDAO_HI.insert(list);
            } catch (Exception ee) {
                //ee.printStackTrace();
                e = new JSONObject();
                e.put("msg", "导入失败");
                e.put("status", "E");
                return e;
            }
            JSONObject resultObj = new JSONObject();
            resultObj.put("msg", "导入成功行数为:" + list.size() + "行!");
            resultObj.put("status", "S");
            return resultObj;
        } catch (Exception e) {
            return SToolUtils.convertResultJSONObj("E", "未按照正确时间格式输入" + e.getMessage(), 1L, null);
        }
    }

    public List<SrmSpmManualScoreEntity_HI_RO> caculateManualScore(Integer supplierId, String happenedDateFrom, String happenedDateTo) {
        Map<String, Object> queryParamMap = new HashMap();
        StringBuffer queryParam = new StringBuffer();
        queryParam.append(SrmSpmManualScoreEntity_HI_RO.CALCULATE_MANUAL_SCORE);
        queryParam.append(" AND Sms.Supplier_Id = " + supplierId);
        if (happenedDateFrom != null && !"".equals(happenedDateFrom.trim())) {
            queryParam.append("AND TO_DATE(to_char(Sms.Happened_Date,'yyyy-mm'),'yyyy-mm') >= TO_DATE('" + happenedDateFrom + "','yyyy-mm')");
        }
        if (happenedDateTo != null && !"".equals(happenedDateTo.trim())) {
            queryParam.append("AND TO_DATE(to_char(Sms.Happened_Date,'yyyy-mm'),'yyyy-mm') <= TO_DATE('" + happenedDateTo + "','yyyy-mm')");
        }
        List<SrmSpmManualScoreEntity_HI_RO> list = srmSpmManualScoreDAO_HI_RO.findList(queryParam, queryParamMap);
        return list;
    }


}
