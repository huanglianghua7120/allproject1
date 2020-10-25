package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;

public class SrmSpmRequestNoticeEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String QUERY_MANUAL_INFO_LIST = "SELECT \r\n" + 
			"  spt.notice_id noticeId,\r\n" + 
			"  spt.scheme_id schemeId,\r\n" + 
			"  spt.user_id userId,\r\n" + 
			"  spt.indicator_id indicatorId,\r\n" + 
			"  spt.indicator_name indicatorName,\r\n" + 
			"  spt.indicator_dimension indicatorDimension,\r\n" + 
			"  spt.dimension_name dimensionName,\r\n" + 
			"  spt.category_code categoryCode,\r\n" + 
			"  spt.category_name categoryName,\r\n" + 
			"  spt.supplier_number supplierNumber,\r\n" + 
			"  spt.supplier_name supplierName,\r\n" + 
			"  spt.evaluate_interval_from evaluateIntervalFrom,\r\n" + 
			"  spt.evaluate_interval_to evaluateIntervalTo,\r\n" + 
			"  spt.source_id sourceId\r\n" + 
			"  FROM srm_spm_request_notice spt \r\n" +
			"WHERE 1=1";
	public static final String  QUERY_COUNT = "select count(*) from srm_spm_request_notice spt where 1=1 ";
	private Integer noticeId; // ID
	private Integer schemeId; // 方案ID
	private Integer userId; // 用户ID，关联表：SAAF_USER
	private Integer indicatorId; // 指标ID
	private String indicatorName; // 指标名称
	private String indicatorDimension; // 指标维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
	private String dimensionName; // 维度名称
	private Integer categoryId; // 分类ID（备用），关联表：SRM_BASE_CATEGORIES
	private String categoryCode; // 分类编码
	private String categoryName; // 分类名称
	private Integer supplierId; // 供应商ID，关联表：SRM_POS_SUPPLIER_INFO
	private String supplierNumber; // 供应商编码
	private String supplierName; // 供应商名称
	private String evaluateIntervalFrom; // 评价区间从
	private String evaluateIntervalTo; // 评价区间至
	private String description; // 说明、备注
	private Integer sourceId; // 来源ID
	
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getIndicatorDimension() {
		return indicatorDimension;
	}
	public void setIndicatorDimension(String indicatorDimension) {
		this.indicatorDimension = indicatorDimension;
	}
	public String getDimensionName() {
		return dimensionName;
	}
	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
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
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

}
