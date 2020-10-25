package saaf.common.fmw.spm.model.entities.readonly;


import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmComprehensivePerformanceResultsEntity_HI_RO implements Serializable{


    public static String QUERY_EVALUATE_DIMENSION="SELECT evaluate_dimension en_evaluateDimension,meaning AS evaluateDimension FROM srm_spm_supplier_score, saaf_lookup_values\r\n"+
            "WHERE evaluate_dimension = lookup_code and lookup_type = 'SPM_INDICATOR_DIMENSION' and score_type='DIMENSION'\r\n";

    public static String QUERY_EVALUATE_DIMENSION_CN="SELECT evaluate_dimension en_evaluateDimension FROM srm_spm_supplier_score\r\n"+
            "WHERE score_type='DIMENSION'\r\n";

    public static String QUERY_HEADER_INFO="SELECT\r\n" +
            " tb2.scheme_number schemeNumber\r\n" +
            ",tb2.scheme_name schemeName\r\n" +
            ",tb2.inst_id instId\r\n"+
            ",tb2.inst_name orgName\r\n" +
            ",tb.tpl_code tplCode\r\n" +
            ",tb.tpl_name tplName\r\n" +
            ",tb2.evaluate_period evaluatePeriod\r\n" +
            ",tb2.evaluate_interval_from evaluateIntervalFrom\r\n" +
            ",tb2.evaluate_interval_to evaluateIntervalTo\r\n" +
            ",bc.category_code categoryCode\r\n" +
            ",bc.category_name categoryName\r\n" +
            ",si.supplier_name supplierName\r\n" +
            "FROM\r\n" +
            "srm_spm_supplier_score ss\r\n"+
            "LEFT JOIN srm_base_categories bc ON ss.category_id = bc.category_id\r\n"+
            "LEFT JOIN srm_pos_supplier_info si ON ss.vendor_id = si.supplier_id\r\n"+
            "LEFT JOIN (select tc.tpl_category_id,tpl.tpl_code,tpl.tpl_name from srm_spm_tpl_categories tc ,srm_spm_performance_tpl tpl where tc.tpl_id = tpl.tpl_id) tb on ss.tpl_category_id=tb.tpl_category_id\r\n"+
            "LEFT JOIN (select ps.scheme_id, ps.scheme_number, ps.scheme_name, ps.evaluate_period,ps.evaluate_interval_from,ps.evaluate_interval_to,ins.inst_id,ins.inst_name from srm_spm_performance_scheme ps left join saaf_institution ins ON ps.org_id = ins.inst_id) tb2 on ss.scheme_id=tb2.scheme_id\r\n"+
            "where 1=1 \r\n";


    public static String QUERY_COMPREHENSIVE_PERFORMANCE_RESULTS_INFO_LIST="SELECT\n" +
            "  ss.supplier_score_id supplierScoreId,\n" +
            "  ss.scheme_id schemeId,\n" +
            "  ss.vendor_id vendorId,\n" +
            "  ss.category_id categoryId,\n" +
            "  ss.score comprehensiveScore,\n" +
            "  ss.rank comprehensiveRank,\n" +
            "  ss.curr_supply_ratio currSupplyRatio,\n" +
            "  ss.score_type scoreType,\n" +
            "  ss.evaluate_dimension evaluateDimension,\n" +
            "  ss.tpl_dimension_id tplDimensionId,\n" +
            "  tb1.tpl_code tplCode,\n" +
            "  tb1.tpl_name tplName,\n" +
            "  tb2.scheme_number schemeNumber,\n" +
            "  tb2.scheme_name schemeName,\n" +
            "  tb2.inst_id instId,\n" +
            "  tb2.inst_name orgName,\n" +
            "  tb2.meaning evaluatePeriod,\n" +
            "  tb2.evaluate_interval_from evaluateIntervalFrom,\n" +
            "  tb2.evaluate_interval_to evaluateIntervalTo,\n" +
            "  bc.category_code categoryCode,\n" +
            "  bc.category_name categoryName,\n" +
            "  si.supplier_name supplierName,\n" +
            "  costtb.score costScore,\n" +
            "  costtb.rank costRank,\n" +
            "  deliverytb.score deliveryScore,\n" +
            "  deliverytb.rank deliveryRank,\n" +
            "  qualitytb.score qualityScore,\n" +
            "  qualitytb.rank qualityRank,\n" +
            "  servicetb.score serviceScore,\n" +
            "  servicetb.rank serviceRank\n" +
            "FROM\n" +
            "  srm_spm_supplier_score ss\n" +
            "  LEFT JOIN srm_base_categories bc\n" +
            "    ON ss.category_id = bc.category_id\n" +
            "  LEFT JOIN srm_pos_supplier_info si\n" +
            "    ON ss.vendor_id = si.supplier_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      tpl.tpl_id,\n" +
            "      tc.tpl_category_id,\n" +
            "      tpl.tpl_code,\n" +
            "      tpl.tpl_name\n" +
            "    FROM\n" +
            "      srm_spm_tpl_categories tc,\n" +
            "      srm_spm_performance_tpl tpl\n" +
            "    WHERE tc.tpl_id = tpl.tpl_id) tb1\n" +
            "    ON ss.tpl_category_id = tb1.tpl_category_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      ps.scheme_id,\n" +
            "      ps.scheme_number,\n" +
            "      ps.scheme_name,\n" +
            "      ps.evaluate_period,\n" +
            "      slv.meaning,\n" +
            "      ps.evaluate_interval_from,\n" +
            "      ps.evaluate_interval_to,\n" +
            "      ins.inst_id,\n" +
            "      ins.inst_name\n" +
            "    FROM\n" +
            "      srm_spm_performance_scheme ps\n" +
            "      LEFT JOIN saaf_institution ins\n" +
            "        ON ps.org_id = ins.inst_id\n" +
            "      LEFT JOIN saaf_lookup_values slv\n" +
            "        ON ps.evaluate_period = slv.lookup_code\n" +
            "        AND slv.lookup_type = 'SPM_TEMPLATE_FREQUENCY') tb2\n" +
            "    ON ss.scheme_id = tb2.scheme_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      scheme_id,\n" +
            "      vendor_id,\n" +
            "      category_id,\n" +
            "      score,\n" +
            "      rank\n" +
            "    FROM\n" +
            "      srm_spm_supplier_score\n" +
            "    WHERE SCORE_TYPE = 'DIMENSION'\n" +
            "      AND EVALUATE_DIMENSION = 'COST'\n" +
            "      AND TPL_DIMENSION_ID IS NOT NULL) costtb\n" +
            "    ON ss.scheme_id = costtb.scheme_id\n" +
            "    AND ss.vendor_id = costtb.vendor_id\n" +
            "    AND ss.category_id = costtb.category_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      scheme_id,\n" +
            "      vendor_id,\n" +
            "      category_id,\n" +
            "      score,\n" +
            "      rank\n" +
            "    FROM\n" +
            "      srm_spm_supplier_score\n" +
            "    WHERE SCORE_TYPE = 'DIMENSION'\n" +
            "      AND EVALUATE_DIMENSION = 'DELIVERY'\n" +
            "      AND TPL_DIMENSION_ID IS NOT NULL) deliverytb\n" +
            "    ON ss.scheme_id = deliverytb.scheme_id\n" +
            "    AND ss.vendor_id = deliverytb.vendor_id\n" +
            "    AND ss.category_id = deliverytb.category_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      scheme_id,\n" +
            "      vendor_id,\n" +
            "      category_id,\n" +
            "      score,\n" +
            "      rank\n" +
            "    FROM\n" +
            "      srm_spm_supplier_score\n" +
            "    WHERE SCORE_TYPE = 'DIMENSION'\n" +
            "      AND EVALUATE_DIMENSION = 'QUALITY'\n" +
            "      AND TPL_DIMENSION_ID IS NOT NULL) qualitytb\n" +
            "    ON ss.scheme_id = qualitytb.scheme_id\n" +
            "    AND ss.vendor_id = qualitytb.vendor_id\n" +
            "    AND ss.category_id = qualitytb.category_id\n" +
            "  LEFT JOIN\n" +
            "    (SELECT\n" +
            "      scheme_id,\n" +
            "      vendor_id,\n" +
            "      category_id,\n" +
            "      score,\n" +
            "      rank\n" +
            "    FROM\n" +
            "      srm_spm_supplier_score\n" +
            "    WHERE SCORE_TYPE = 'DIMENSION'\n" +
            "      AND EVALUATE_DIMENSION = 'SERVICE'\n" +
            "      AND TPL_DIMENSION_ID IS NOT NULL) servicetb\n" +
            "    ON ss.scheme_id = servicetb.scheme_id\n" +
            "    AND ss.vendor_id = servicetb.vendor_id\n" +
            "    AND ss.category_id = servicetb.category_id\n" +
            "WHERE ss.score_type = 'CATEGORY'\n" +
            "  AND ss.evaluate_dimension IS NULL\n" +
            "  AND ss.tpl_dimension_id IS NULL\n";

    public static String QUERY_COMPREHENSIVE_PERFORMANCE_INFO="SELECT score comprehensiveScore,rank comprehensiveRank,(SELECT COUNT(DISTINCT vendor_id) AS count FROM srm_spm_supplier_score WHERE score_type='CATEGORY') AS count\r\n" +
            "FROM srm_spm_supplier_score WHERE score_type='CATEGORY' and evaluate_dimension IS NULL and tpl_dimension_id IS NULL\r\n";

    public static String QUERY_EVALUATE_DIMENSION_INFO="SELECT ss.score comprehensiveScore,ss.rank comprehensiveRank,td.DIMENSION_WEIGHT dimensionWeight,\r\n" +
            "(SELECT COUNT(DISTINCT vendor_id) AS COUNT FROM srm_spm_supplier_score WHERE score_type='DIMENSION') AS count\r\n" +
            " FROM srm_spm_supplier_score ss,srm_spm_tpl_dimension td WHERE  ss.TPL_DIMENSION_ID = td.TPL_DIMENSION_ID\r\n";

    public static String QUERY_EVALUATE_DIMENSION_INDICATOR_INFO="SELECT\r\n" +
            "slv.meaning AS indicatorName,\r\n" +
            "sis.score indicatorScore,\r\n" +
            "sti.indicator_weight AS indicatorWeight,\r\n" +
            "si.score_explain scoreExplain\r\n" +
            "FROM\r\n" +
            "srm_spm_indicator_score sis,\r\n" +
            "srm_spm_supplier_score ss,\r\n" +
            "srm_spm_indicators si\r\n" +
            "LEFT JOIN saaf_lookup_values slv ON slv.lookup_code=si.indicator_code AND slv.lookup_type='SPM_INDICATOR_NAME',\r\n" +
            "srm_spm_tpl_indicators sti\r\n" +
            "WHERE\r\n" +
            "sis.supplier_score_id=ss.supplier_score_id\r\n" +
            "AND sis.indicator_id=si.indicator_id\r\n" +
            "AND si.indicator_id = sti.indicator_id\r\n" +
            "AND ss.tpl_dimension_id = sti.tpl_dimension_id\r\n";


    private Integer supplierScoreId; //供应商分类得分ID
    private Integer instId;
    private String instName;
    private Integer schemeId;
    private String schemeNumber; //方案编号
    private String evaluatePeriod; //方案评价频率，关联表：SAAF_LOOKUP_VALUES（SPM_TEMPLATE_FREQUENCY）
    private String tplCode; //模板编码
    private String tplFrequency;//模板评价周期 关联表：SAAF_LOOKUP_VALUES（SPM_TEMPLATE_FREQUENCY）
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    private Integer supplierId;
    private String supplierName;
    private Integer categoryId;
    private String categoryCode;
    private String categoryName;
    private String evaluateDimension; //评价维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private String en_evaluateDimension;
    private String evaluateIndicator; //评价指标
    private BigDecimal comprehensiveScore; //综合分值
    private Integer comprehensiveRank; //综合排名
    private BigDecimal costScore; //成本分值
    private Integer costRank; //成本排名
    private BigDecimal deliveryScore; //交期分值
    private Integer deliveryRank; //交期排名
    private BigDecimal qualityScore; //品质分值
    private Integer qualityRank; //品质排名
    private BigDecimal serviceScore; //服务分值
    private Integer serviceRank; //服务排名

    private Integer count;
    private BigDecimal dimensionWeight; //维度权重

    private String indicatorName; //指标名称
    private BigDecimal indicatorScore; //指标得分
    private BigDecimal indicatorWeight; //指标权重
    private String scoreExplain; //评分标准
    private BigDecimal currSupplyRatio; //当前供货比例(%)
    private Integer vendorId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO

    private String scoreType; //分值类型（DIMENSION：维度得分，CATEGORY：类别得分）
    private Integer tplDimensionId; //模板维度ID，关联表：SRM_SPM_TPL_DIMENSION

    private BigDecimal score; //成本分值
    private Integer rank; //成本排名
    private String orgName;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public Integer getTplDimensionId() {
        return tplDimensionId;
    }

    public void setTplDimensionId(Integer tplDimensionId) {
        this.tplDimensionId = tplDimensionId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getCurrSupplyRatio() {
        return currSupplyRatio;
    }

    public void setCurrSupplyRatio(BigDecimal currSupplyRatio) {
        this.currSupplyRatio = currSupplyRatio;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public BigDecimal getIndicatorScore() {
        return indicatorScore;
    }

    public void setIndicatorScore(BigDecimal indicatorScore) {
        this.indicatorScore = indicatorScore;
    }

    public BigDecimal getIndicatorWeight() {
        return indicatorWeight;
    }

    public void setIndicatorWeight(BigDecimal indicatorWeight) {
        this.indicatorWeight = indicatorWeight;
    }

    public String getScoreExplain() {
        return scoreExplain;
    }

    public void setScoreExplain(String scoreExplain) {
        this.scoreExplain = scoreExplain;
    }

    public BigDecimal getDimensionWeight() {
        return dimensionWeight;
    }

    public void setDimensionWeight(BigDecimal dimensionWeight) {
        this.dimensionWeight = dimensionWeight;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getEn_evaluateDimension() {
        return en_evaluateDimension;
    }

    public void setEn_evaluateDimension(String en_evaluateDimension) {
        this.en_evaluateDimension = en_evaluateDimension;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public BigDecimal getCostScore() {
        return costScore;
    }

    public void setCostScore(BigDecimal costScore) {
        this.costScore = costScore;
    }

    public Integer getCostRank() {
        return costRank;
    }

    public void setCostRank(Integer costRank) {
        this.costRank = costRank;
    }

    public BigDecimal getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(BigDecimal deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public Integer getDeliveryRank() {
        return deliveryRank;
    }

    public void setDeliveryRank(Integer deliveryRank) {
        this.deliveryRank = deliveryRank;
    }

    public BigDecimal getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(BigDecimal qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getQualityRank() {
        return qualityRank;
    }

    public void setQualityRank(Integer qualityRank) {
        this.qualityRank = qualityRank;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Integer getServiceRank() {
        return serviceRank;
    }

    public void setServiceRank(Integer serviceRank) {
        this.serviceRank = serviceRank;
    }

    public BigDecimal getComprehensiveScore() {
        return comprehensiveScore;
    }

    public void setComprehensiveScore(BigDecimal comprehensiveScore) {
        this.comprehensiveScore = comprehensiveScore;
    }

    public Integer getComprehensiveRank() {
        return comprehensiveRank;
    }

    public void setComprehensiveRank(Integer comprehensiveRank) {
        this.comprehensiveRank = comprehensiveRank;
    }

    public Integer getSupplierScoreId() {
        return supplierScoreId;
    }

    public void setSupplierScoreId(Integer supplierScoreId) {
        this.supplierScoreId = supplierScoreId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public String getEvaluatePeriod() {
        return evaluatePeriod;
    }

    public void setEvaluatePeriod(String evaluatePeriod) {
        this.evaluatePeriod = evaluatePeriod;
    }

    public String getTplCode() {
        return tplCode;
    }

    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }

    public String getTplFrequency() {
        return tplFrequency;
    }

    public void setTplFrequency(String tplFrequency) {
        this.tplFrequency = tplFrequency;
    }

    public String getEvaluateIntervalFrom() {
        return evaluateIntervalFrom;
    }

    public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
        this.evaluateIntervalFrom = evaluateIntervalFrom;
    }

    public String getEvaluateIntervalTo() {
        return evaluateIntervalTo;
    }

    public void setEvaluateIntervalTo(String evaluateIntervalTo) {
        this.evaluateIntervalTo = evaluateIntervalTo;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEvaluateDimension() {
        return evaluateDimension;
    }

    public void setEvaluateDimension(String evaluateDimension) {
        this.evaluateDimension = evaluateDimension;
    }

    public String getEvaluateIndicator() {
        return evaluateIndicator;
    }

    public void setEvaluateIndicator(String evaluateIndicator) {
        this.evaluateIndicator = evaluateIndicator;
    }

}
