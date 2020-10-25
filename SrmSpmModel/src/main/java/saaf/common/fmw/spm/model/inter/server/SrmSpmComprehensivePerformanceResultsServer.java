package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmComprehensivePerformanceResultsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmComprehensivePerformanceResults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：综合绩效结果查询Server
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */

@Component("srmSpmComprehensivePerformanceResultsServer")
public class SrmSpmComprehensivePerformanceResultsServer implements ISrmSpmComprehensivePerformanceResults {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmComprehensivePerformanceResultsServer.class);

    public SrmSpmComprehensivePerformanceResultsServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> srmSpmComprehensivePerformanceResultsDAO_HI_RO;

    /**
     * 查询评价维度数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public String findEvaluateDimensionData(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        JSONArray jsonArray = new JSONArray();
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_EVALUATE_DIMENSION);
            StringBuffer buffer = new StringBuffer();
            Map<String, Object> map = new HashMap<String, Object>();
            SaafToolUtils.parperParam(paramJSON, "scheme_id", "schemeId", buffer, map, "=");
            SaafToolUtils.parperParam(paramJSON, "category_id", "categoryId", buffer, map, "=");
            SaafToolUtils.parperParam(paramJSON, "vendor_id", "vendorId", buffer, map, "=");
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //供应商用户
                paramJSON.put("evaluateDimension", "COST"); //供应商不能查询成本维度数据
                SaafToolUtils.parperParam(paramJSON, "evaluate_dimension", "evaluateDimension", queryString, map, "!=");
            }
            queryString.append(buffer);
            String countSql = "select count(1) from (" + queryString + ")";
            queryString.append(" ORDER BY lookup_values_id");
            //获取评价维度
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> comprehensivePerformanceList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            JSONArray arrayType = JSON.parseObject(JSONArray.toJSONString(comprehensivePerformanceList)).getJSONArray("data");
            if (arrayType.size() > 0) {
                for (int i = 0; i < arrayType.size(); i++) {
                    String evaluateDimension = arrayType.getJSONObject(i).getString("evaluateDimension");
                    String en_evaluateDimension = arrayType.getJSONObject(i).getString("en_evaluateDimension");

                    StringBuffer sqlBuff1 = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_EVALUATE_DIMENSION_INFO);
                    StringBuffer sqlBuff2 = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_EVALUATE_DIMENSION_INDICATOR_INFO);

                    StringBuffer strBuff = new StringBuffer();
                    Map<String, Object> mapParam = new HashMap<String, Object>();
                    SaafToolUtils.parperParam(paramJSON, "category_id", "categoryId", strBuff, mapParam, "=");
                    SaafToolUtils.parperParam(paramJSON, "ss.scheme_id", "schemeId", strBuff, mapParam, "=");
                    SaafToolUtils.parperParam(paramJSON, "ss.vendor_id", "vendorId", strBuff, mapParam, "=");
                    if (!"".equals(en_evaluateDimension)) {
                        sqlBuff1.append(" and ss.evaluate_dimension = :en_evaluateDimension");
                        sqlBuff2.append(" and ss.evaluate_dimension = :en_evaluateDimension");
                        mapParam.put("en_evaluateDimension", en_evaluateDimension);
                    }
                    sqlBuff1.append(strBuff);
                    sqlBuff2.append(strBuff);
                    String countSql1 = "select count(1) from (" + sqlBuff1 + ")";
                    String countSql2 = "select count(1) from (" + sqlBuff2 + ")";
                    Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> evaluateDimensionList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(sqlBuff1,countSql1, mapParam, pageIndex, pageRows);
                    Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> indicatorList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(sqlBuff2,countSql2, mapParam, pageIndex, pageRows);

                    JSONObject obj = JSON.parseObject(JSONArray.toJSONString(indicatorList));
                    JSONArray arrayData = JSON.parseObject(JSONArray.toJSONString(evaluateDimensionList)).getJSONArray("data");
                    if (arrayData.size() > 0) {
                        obj.put("comprehensiveScore", arrayData.getJSONObject(0).getBigDecimal("comprehensiveScore"));
                        obj.put("comprehensiveRank", arrayData.getJSONObject(0).getInteger("comprehensiveRank"));
                        obj.put("count", arrayData.getJSONObject(0).getInteger("count"));
                        obj.put("dimensionWeight", arrayData.getJSONObject(0).getBigDecimal("dimensionWeight"));
                    }
                    obj.put("evaluateDimension", evaluateDimension);
                    jsonArray.add(obj);
                }
            }
            return jsonArray.toJSONString();
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询评价维度数据失败");
        }
    }



    /**
     * 查询综合绩效信息数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findComprehensivePerformanceData(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_COMPREHENSIVE_PERFORMANCE_INFO);
            Map<String, Object> map = new HashMap<String, Object>();
            //查询条件
            SaafToolUtils.parperParam(paramJSON, "supplier_score_id", "supplierScoreId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "scheme_id", "schemeId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "category_id", "categoryId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "vendor_id", "vendorId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> evaluateDimensionList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return evaluateDimensionList;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("查询综合绩效信息数据失败");
        }
    }



    /**
     * 综合绩效结果详情头
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findComprehensivePerformanceResultsHeader(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_HEADER_INFO);
            Map<String, Object> map = new HashMap<String, Object>();
            //根据shcemeId查询
            SaafToolUtils.parperParam(paramJSON, "ss.supplier_score_id", "supplierScoreId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.scheme_id", "schemeId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.category_id", "categoryId", queryString, map, "=");
            SaafToolUtils.parperParam(paramJSON, "ss.vendor_id", "vendorId", queryString, map, "=");
            String countSql = "select count(1) from (" + queryString + ")";
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> dataList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("综合绩效结果详情失败");
        }
    }


    /**
     * 综合绩效结果查询
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findComprehensivePerformanceResults(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_COMPREHENSIVE_PERFORMANCE_RESULTS_INFO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 评价组织
            SaafToolUtils.parperParam(paramJSON, "tb2.inst_id", "orgId", queryString, map, "=");
            //绩效方案编号
            SaafToolUtils.parperParam(paramJSON, "tb2.scheme_id", "schemeId", queryString, map, "=");
            //供应商名称
            SaafToolUtils.parperParam(paramJSON, "ss.vendor_id ", "supplierId", queryString, map, "=");
            //绩效模板编号
            SaafToolUtils.parperParam(paramJSON, "tb1.tpl_id ", "tplId", queryString, map, "=");
            //采购分类
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            //评价周期
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_period", "evaluatePeriod", queryString, map, "=");
            //绩效月份从
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_interval_from", "evaluateIntervalFrom", queryString, map, ">=");
            //绩效月份至
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_interval_to", "evaluateIntervalTo", queryString, map, "<=");
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY tb2.inst_id asc, tb2.scheme_id DESC");
            Pagination<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> dataList = srmSpmComprehensivePerformanceResultsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("综合绩效结果查询失败");
        }
    }


    /**
     * 导出综合绩效结果数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmComprehensivePerformanceResultsEntity_HI_RO> findExportComprehensivePerformanceResults(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmComprehensivePerformanceResultsEntity_HI_RO.QUERY_COMPREHENSIVE_PERFORMANCE_RESULTS_INFO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            // 评价组织
            SaafToolUtils.parperParam(paramJSON, "tb2.inst_id", "orgId", queryString, map, "=");
            //绩效方案编号
            SaafToolUtils.parperParam(paramJSON, "tb2.scheme_id", "schemeId", queryString, map, "=");
            //供应商名称
            SaafToolUtils.parperParam(paramJSON, "ss.vendor_id ", "supplierId", queryString, map, "=");
            //绩效模板编号
            SaafToolUtils.parperParam(paramJSON, "tb1.tpl_id ", "tplId", queryString, map, "=");
            //采购分类
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            //评价周期
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_period", "evaluatePeriod", queryString, map, "=");
            //绩效月份从
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_interval_from", "evaluateIntervalFrom", queryString, map, ">=");
            //绩效月份至
            SaafToolUtils.parperParam(paramJSON, "tb2.evaluate_interval_to", "evaluateIntervalTo", queryString, map, "<=");
            // 排序
            queryString.append(" ORDER BY tb2.inst_id asc, tb2.scheme_id DESC");
            return srmSpmComprehensivePerformanceResultsDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("导出失败");
        }
    }

}
