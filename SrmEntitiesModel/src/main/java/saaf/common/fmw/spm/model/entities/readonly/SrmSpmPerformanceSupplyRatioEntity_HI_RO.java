package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmSpmPerformanceSupplyRatioEntity_HI_RO implements Serializable{

    public static String QUERY_PERFORMANCE_SUPPLY_RATIO_LIST_1="SELECT\n" +
            "  ss.supplier_score_id supplierScoreId,\n" +
            "  ss.scheme_id schemeId,\n" +
            "  ss.category_id categoryId,\n" +
            "  bc.category_code categoryCode,\n" +
            "  bc.category_name categoryName,\n" +
            "  slv.meaning status,\n" +
            "  psi.supplier_number supplierNumber,\n" +
            "  psi.supplier_name supplierName,\n" +
            "  ss.curr_supply_ratio currSupplyRatio,\n" +
            "  ss.publish_flag publishFlag,\n" +
            "  ps.evaluate_interval_from evaluateIntervalFrom,\n" +
            "  ps.evaluate_interval_to evaluateIntervalTo,\n" +
            "  CONCAT(IFNULL(ps.evaluate_interval_from,' ') ,'至',IFNULL(ps.evaluate_interval_to,'-') ) evaluateInterval,\n" +
            "  ss.rank rank,\n" +
            "  ss.advise_supply_ratio adviseSupplyRatio,\n" +
            "  ss.new_supply_ratio newSupplyRatio,\n" +
            "  ss.description description,\n" +
            "  ss.source_scheme_number sourceSchemeNumber\n" +
            "FROM\n" +
            "  srm_spm_supplier_score ss\n" +
            "  LEFT JOIN srm_base_categories bc ON ss.category_id = bc.category_id\n" +
            "  LEFT JOIN srm_pos_supplier_info psi ON ss.vendor_id = psi.supplier_id\n" +
            "  LEFT JOIN saaf_lookup_values slv on slv.lookup_code = ss.status\r\n" +
            "  AND slv.lookup_type = 'SPM_RATIO_CONFIRM_STATUS',\r\n" +
            "  srm_spm_performance_scheme ps\n" +
            "WHERE ss.scheme_id = ps.scheme_id\n"+
            "  AND (ss.score_type='CATEGORY' or ss.score_type='OTHER')\n"+
            "  AND ss.category_id=:categoryId\n"+
            "  AND ss.scheme_id=:schemeId\n";

    public static String QUERY_PERFORMANCE_SUPPLY_RATIO_LIST="SELECT\n" +
            "  ss.supplier_score_id supplierScoreId,\n" +
            "  ss.scheme_id schemeId,\n" +
            "  ss.category_id categoryId,\n" +
            "  bc.category_code categoryCode,\n" +
            "  bc.category_name categoryName,\n" +
            "  slv.meaning status,\n" +
            "  ss.status en_status,\n" +
            "  psi.supplier_id supplierId,\n" +
            "  psi.supplier_number supplierNumber,\n" +
            "  psi.supplier_name supplierName,\n" +
            "  ss.curr_supply_ratio currSupplyRatio,\n" +
            "  ss.publish_flag publishFlag,\n" +
            "  ps.evaluate_interval_from evaluateIntervalFrom,\n" +
            "  ps.evaluate_interval_to evaluateIntervalTo,\n" +
            "  CONCAT(IFNULL(ps.evaluate_interval_from,' ') ,'至',IFNULL(ps.evaluate_interval_to,'-') ) evaluateInterval,\n" +
            "  ss.rank rank,\n" +
            "  ss.advise_supply_ratio adviseSupplyRatio,\n" +
            "  ss.new_supply_ratio newSupplyRatio,\n" +
            "  ss.description description,\n" +
            "  ss.source_scheme_number sourceSchemeNumber\n" +
            "FROM\n" +
            "  srm_spm_supplier_score ss\n" +
            "  LEFT JOIN srm_base_categories bc ON ss.category_id = bc.category_id\n" +
            "  LEFT JOIN srm_pos_supplier_info psi ON ss.vendor_id = psi.supplier_id\n" +
            "  LEFT JOIN saaf_lookup_values slv on slv.lookup_code = ss.status\r\n" +
            "  AND slv.lookup_type = 'SPM_RATIO_CONFIRM_STATUS',\r\n" +
            "  srm_spm_performance_scheme ps\n" +
            "WHERE ss.scheme_id = ps.scheme_id\n"+
            "  AND (ss.score_type='CATEGORY' or ss.score_type='OTHER')\n";

    public static final String QUERY_SCHEME_NUMBER_SQL = "SELECT\n" +
//            "  ss.supplier_score_id supplierScoreId,\n" +
            "   DISTINCT ss.source_scheme_number sourceSchemeNumber\n" +
            "FROM\n" +
            "  srm_spm_supplier_score ss\n" +
            "WHERE source_scheme_number IS NOT NULL\n";

    private Integer supplierScoreId; //供应商分类得分ID
    private Integer schemeId; //绩效方案ID，关联表：SRM_SPM_PERFORMANCE_SCHEME
    private Integer vendorId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer tplCategoryId; //模板分类ID，关联表：SRM_SPM_TPL_CATEGORIES
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private String scoreType; //分值类型（DIMENSION：维度得分，CATEGORY：类别得分）
    private Integer tplDimensionId; //模板维度ID，关联表：SRM_SPM_TPL_DIMENSION
    private String evaluateDimension; //评价维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private BigDecimal score; //分值
    private Integer rank; //排名
    private BigDecimal currSupplyRatio; //当前供货比例(%)
    private BigDecimal adviseSupplyRatio; //建议供货比例(%)
    private BigDecimal newSupplyRatio; //新供货比例(%)
    private String status; //状态，关联表：SAAF_LOOKUP_VALUES
    private String publishFlag; //是否有效，关联表：SAAF_LOOKUP_VALUES（YSE_NO）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate; //生效时间
    private String sourceSchemeNumber; //来源方案编号
    private String description; //说明、备注
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    private String schemeNumber; //方案编号
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    private String categoryCode;
    private String categoryName;
    private String evaluateInterval; //评价区间
    private String en_status;


    public String getEn_status() {
        return en_status;
    }

    public void setEn_status(String en_status) {
        this.en_status = en_status;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getEvaluateInterval() {
        return evaluateInterval;
    }

    public void setEvaluateInterval(String evaluateInterval) {
        this.evaluateInterval = evaluateInterval;
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

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getSupplierScoreId() {
        return supplierScoreId;
    }

    public void setSupplierScoreId(Integer supplierScoreId) {
        this.supplierScoreId = supplierScoreId;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getTplCategoryId() {
        return tplCategoryId;
    }

    public void setTplCategoryId(Integer tplCategoryId) {
        this.tplCategoryId = tplCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment4() {
        return segment4;
    }

    public void setSegment4(String segment4) {
        this.segment4 = segment4;
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

    public String getEvaluateDimension() {
        return evaluateDimension;
    }

    public void setEvaluateDimension(String evaluateDimension) {
        this.evaluateDimension = evaluateDimension;
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

    public BigDecimal getCurrSupplyRatio() {
        return currSupplyRatio;
    }

    public void setCurrSupplyRatio(BigDecimal currSupplyRatio) {
        this.currSupplyRatio = currSupplyRatio;
    }

    public BigDecimal getAdviseSupplyRatio() {
        return adviseSupplyRatio;
    }

    public void setAdviseSupplyRatio(BigDecimal adviseSupplyRatio) {
        this.adviseSupplyRatio = adviseSupplyRatio;
    }

    public BigDecimal getNewSupplyRatio() {
        return newSupplyRatio;
    }

    public void setNewSupplyRatio(BigDecimal newSupplyRatio) {
        this.newSupplyRatio = newSupplyRatio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(String publishFlag) {
        this.publishFlag = publishFlag;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getSourceSchemeNumber() {
        return sourceSchemeNumber;
    }

    public void setSourceSchemeNumber(String sourceSchemeNumber) {
        this.sourceSchemeNumber = sourceSchemeNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
