package saaf.common.fmw.spm.model.entities.readonly;


import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmIndicatorCalculationResultsEntity_HI_RO implements Serializable{

    public static String QUERY_INDICATOR_CALCULATION_RESULTS_INFO_LIST="SELECT\r\n" +
            "ps.scheme_id schemeId\r\n" +
            ",ps.scheme_number schemeNumber\r\n" +
            ",ps.scheme_name schemeName\r\n" +
            ",ps.org_id orgId\r\n" +
            ",ins.inst_name orgName\r\n" +
            ",tpl.tpl_code tplCode\r\n" +
            ",tpl.tpl_name tplName\r\n" +
            ",tpl.tpl_frequency tplFrequency\r\n" +
            ",slv.meaning evaluatePeriod\r\n" +
            ",ps.evaluate_interval_from evaluateIntervalFrom\r\n" +
            ",ps.evaluate_interval_to evaluateIntervalTo\r\n" +
            ",psi.supplier_name supplierName\r\n" +
            ",bc.category_code categoryCode\r\n" +
            ",bc.category_name categoryName\r\n" +
            ",slv1.meaning evaluateDimension\r\n" +
            ",slv2.meaning AS indicatorName\r\n" +
            ",sis.score indicatorScore\r\n" +
            "FROM\r\n" +
            "srm_spm_performance_scheme ps\r\n" +
            "LEFT JOIN srm_spm_performance_tpl tpl ON ps.tpl_id = tpl.tpl_id\r\n" +
            "LEFT JOIN saaf_institution ins ON ps.org_id = ins.inst_id\r\n" +
            "LEFT JOIN saaf_lookup_values slv ON ps.evaluate_period = slv.lookup_code\r\n" +
            "AND slv.lookup_type = 'SPM_TEMPLATE_FREQUENCY',\r\n" +
            "srm_spm_supplier_score ss\r\n" +
            "LEFT JOIN saaf_lookup_values slv1 ON ss.evaluate_dimension = slv1.lookup_code\r\n" +
            "AND slv1.lookup_type = 'SPM_INDICATOR_DIMENSION'\r\n" +
            "LEFT JOIN srm_base_categories bc ON ss.category_id = bc.category_id\r\n" +
            "LEFT JOIN srm_pos_supplier_info psi ON ss.vendor_id = psi.supplier_id,\r\n" +
            "srm_spm_indicator_score sis,\r\n" +
            "srm_spm_indicators si\r\n" +
            "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_code=si.INDICATOR_CODE AND slv2.lookup_type='SPM_INDICATOR_NAME'\r\n" +
            "WHERE\r\n" +
            "ps.scheme_id = ss.scheme_id\r\n" +
            "AND ss.supplier_score_id = sis.supplier_score_id\r\n" +
            "AND sis.INDICATOR_ID=si.INDICATOR_ID\r\n";

    private String instName;
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
    private String evaluateIndicator; //评价指标
    private BigDecimal indicatorScore; //分值
    private String indicatorName; //评价指标
    private String orgName;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public BigDecimal getIndicatorScore() {
        return indicatorScore;
    }

    public void setIndicatorScore(BigDecimal indicatorScore) {
        this.indicatorScore = indicatorScore;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
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
