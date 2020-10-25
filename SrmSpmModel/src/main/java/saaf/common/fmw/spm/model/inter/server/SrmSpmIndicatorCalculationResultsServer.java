package saaf.common.fmw.spm.model.inter.server;

import com.alibaba.fastjson.JSONObject;
import com.yhg.hibernate.core.dao.BaseViewObject;
import com.yhg.hibernate.core.paging.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;
import saaf.common.fmw.base.utils.UtilsException;
import saaf.common.fmw.common.utils.SaafToolUtils;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorCalculationResultsEntity_HI_RO;
import saaf.common.fmw.spm.model.inter.ISrmSpmIndicatorCalculationResults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name：SRM
 * Company Name：SIE
 * Program Name：
 * Description：绩效指标计算结果查询Server
 *
 * Update History
 * ===========================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-04-15     liuwenjun             创建
 * ===========================================================================
 */

@Component("srmSpmIndicatorCalculationResultsServer")
public class SrmSpmIndicatorCalculationResultsServer implements ISrmSpmIndicatorCalculationResults {

    private static final Logger LOGGER = LoggerFactory.getLogger(SrmSpmIndicatorCalculationResultsServer.class);

    public SrmSpmIndicatorCalculationResultsServer() {
        super();
    }

    @Autowired
    private BaseViewObject<SrmSpmIndicatorCalculationResultsEntity_HI_RO> srmSpmIndicatorCalculationResultsDAO_HI_RO;

    @Autowired
    private BaseViewObject<SaafLookupValuesEntity_HI_RO> lookupValuesEntityDAO_HI_RO;



    /**
     * 绩效指标计算结果查询
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public Pagination<SrmSpmIndicatorCalculationResultsEntity_HI_RO> findIndicatorCalculationResults(JSONObject paramJSON, Integer pageIndex, Integer pageRows) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmIndicatorCalculationResultsEntity_HI_RO.QUERY_INDICATOR_CALCULATION_RESULTS_INFO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            //评价组织
            SaafToolUtils.parperParam(paramJSON, "ps.org_id", "orgId", queryString, map, "=");
            //评价维度
            SaafToolUtils.parperParam(paramJSON, "ss.evaluate_dimension", "evaluateDimension", queryString, map, "=");
            //起始绩效月份从
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_interval_from", "evaluateIntervalFrom", queryString, map, ">=");
            //供应商名称
            SaafToolUtils.parperParam(paramJSON, "ss.vendor_id ", "supplierId", queryString, map, "=");
            //评价指标
            SaafToolUtils.parperParam(paramJSON, "slv2.lookup_code", "evaluateIndicator", queryString, map, "=");
            //起始绩效月份至
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_interval_to", "evaluateIntervalTo", queryString, map, "<=");
            //采购分类
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            //绩效方案编号
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_number", "schemeNumber", queryString, map, "=");
            //评价周期
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_period", "evaluatePeriod", queryString, map, "=");
            //采购名称
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "categoryName", queryString, map, "like");
            //绩效模板编号
            SaafToolUtils.parperParam(paramJSON, "ps.tpl_id", "tplId", queryString, map, "=");
            //绩效方案Id
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_id", "schemeId", queryString, map, "=");
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //供应商用户
                paramJSON.put("evaluateDimension", "COST"); //供应商不能查询成本维度数据
                SaafToolUtils.parperParam(paramJSON, "ss.evaluate_dimension",
                        "evaluateDimension", queryString, map, "!=");
            }
            String countSql = "select count(1) from (" + queryString + ")";
            // 排序
            queryString.append(" ORDER BY ps.org_id asc, ps.scheme_number DESC");
            Pagination<SrmSpmIndicatorCalculationResultsEntity_HI_RO> dataList = srmSpmIndicatorCalculationResultsDAO_HI_RO.findPagination(queryString,countSql, map, pageIndex, pageRows);
            return dataList;
        } catch (Exception e) {
            //throw new Exception(e);
            throw new UtilsException("绩效指标计算结果查询失败");
        }
    }



    /**
     * 导出指标计算结果数据
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SrmSpmIndicatorCalculationResultsEntity_HI_RO> findExportIndicatorCalculationResults(JSONObject paramJSON) throws Exception {
        try {
            StringBuffer queryString = new StringBuffer(SrmSpmIndicatorCalculationResultsEntity_HI_RO.QUERY_INDICATOR_CALCULATION_RESULTS_INFO_LIST);
            Map<String, Object> map = new HashMap<String, Object>();
            //评价组织
            SaafToolUtils.parperParam(paramJSON, "ps.org_id", "orgId", queryString, map, "=");
            //评价维度
            SaafToolUtils.parperParam(paramJSON, "ss.evaluate_dimension", "evaluateDimension", queryString, map, "=");
            //起始绩效月份从
            SaafToolUtils.parperParam(paramJSON, "date_format(ps.evaluate_interval_from,'%Y-%m')", "evaluateIntervalFrom", queryString, map, ">=");
            //供应商名称
            SaafToolUtils.parperParam(paramJSON, "ss.vendor_id ", "supplierId", queryString, map, "=");
            //评价指标
            SaafToolUtils.parperParam(paramJSON, "slv2.lookup_code", "evaluateIndicator", queryString, map, "=");
            //起始绩效月份至
            SaafToolUtils.parperParam(paramJSON, "date_format(ps.evaluate_interval_to,'%Y-%m')", "evaluateIntervalTo", queryString, map, "<=");
            //采购分类
            SaafToolUtils.parperParam(paramJSON, "ss.category_id ", "categoryId", queryString, map, "=");
            //绩效方案编号
            SaafToolUtils.parperParam(paramJSON, "ps.scheme_number", "schemeNumber", queryString, map, "=");
            //评价周期
            SaafToolUtils.parperParam(paramJSON, "ps.evaluate_period", "evaluatePeriod", queryString, map, "=");
            //采购名称
            SaafToolUtils.parperParam(paramJSON, "bc.category_name", "smallCategoryName", queryString, map, "like");
            //绩效模板编号
            SaafToolUtils.parperParam(paramJSON, "ps.tpl_id", "tplId", queryString, map, "=");
            if ("EX".equals(paramJSON.getString("varPlatformCode"))) { //供应商用户
                paramJSON.put("evaluateDimension", "COST"); //供应商不能查询成本维度数据
                SaafToolUtils.parperParam(paramJSON, "ss.evaluate_dimension", "evaluateDimension", queryString, map, "!=");
            }
            // 排序
            queryString.append(" ORDER BY ps.org_id asc, ps.scheme_number DESC");
            return srmSpmIndicatorCalculationResultsDAO_HI_RO.findList(queryString, map);
        } catch (Exception e) {
            //throw new Exception("导出失败");
            throw new UtilsException("导出失败");
        }
    }


    /**
     * 根据评价维度控制评价指标
     * @param paramJSON
     * @return
     * ==============================================================================
     *  Version    Date           Updated By     Description
     *  -------    -----------    -----------    ---------------
     *  V1.0       2020-04-15     hgq             创建
     * ==============================================================================
     */
    @Override
    public List<SaafLookupValuesEntity_HI_RO> selectChangeEvaluateDimension(JSONObject paramJSON) throws Exception {
        LOGGER.debug(paramJSON.toJSONString());
        String evaluateDimension = paramJSON.getString("evaluateDimension");
        StringBuffer queryString = new StringBuffer();
        Map<String, Object> queryParamMap = new HashMap<String, Object>();
        if (!"".equals(evaluateDimension) && null != evaluateDimension) {
            queryString.append(SaafLookupValuesEntity_HI_RO.QUERY_INVOICEID_LINE_SQL);
            queryParamMap.put("tag", evaluateDimension);
        }
        List<SaafLookupValuesEntity_HI_RO> list = lookupValuesEntityDAO_HI_RO.findList(queryString.toString(), queryParamMap);
        return list;
    }
}
