package saaf.common.fmw.prc.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPrcIndicatorHeadersEntity_HI_RO {
	
	 public static String QUERY_INDICATOR_SQL="select \r\n" + 
	 		" ih.indicator_header_id indicatorHeaderId\r\n" + 
	 		",ih.indicator_number indicatorNumber\r\n" + 
	 		",ih.disp_indicator_number dispIndicatorNumber\r\n" + 
	 		",ih.indicator_name indicatorName\r\n" + 
	 		",ih.indicator_price indicatorPrice\r\n" + 
	 		",ih.indicator_status indicatorStatus\r\n" + 
	 		",ih.creation_date creationDate\r\n" + 
	 		",ih.end_date_active endDateActive\r\n" + 
	 		",ih.description description\r\n" + 
	 		",ih.additional_information additionalInformation\r\n" + 
	 		",ih.computational_formula computationalFormula\r\n" +
	 		",ih.unit_of_measure unitOfMeasure\r\n" + 
	 		",ih.price_create_date priceCreateDate\r\n" + 
	 		",su.user_full_name userFullName\r\n" + 
	 		",u.user_full_name lastUpdatedName\r\n" + 
	 		",slv.meaning indicatorStatusMean\r\n" + 
	 		"from \r\n" + 
	 		"srm_prc_indicator_headers ih\r\n" + 
	 		"left join saaf_users su on ih.created_by = su.user_id\r\n" + 
	 		"left join saaf_users u on ih.last_updated_by = u.user_id\r\n" + 
	 		"left join saaf_lookup_values slv on ih.indicator_status = slv.lookup_code\r\n" + 
	 		"and slv.lookup_type = 'PRC_INDICATOR_STATUS'\r\n"+
	 		"where 1=1";



	public static String QUERY_INDICATOR_SUPPLIER_SQL="SELECT\n" +
			"\tspis.original_price originalPrice,\n" +
			"\tspis.finally_price finallyPrice,\n" +
			"\tspis.adjustment_flag adjustmentFlag,\n" +
			"\tspis.indicator_supplier_id indicatorSupplierId,\n" +
			"\tspis.supplier_id supplierId,\n" +
			"\tspsi.supplier_name supplierName,\n" +
			"\tspsi.supplier_number supplierNumber\n" +
			"FROM\n" +
			"\tsrm_prc_indicator_suppliers spis,\n" +
			"\tsrm_pos_supplier_info spsi\n" +
			"WHERE\n" +
			"\tspis.supplier_id = spsi.supplier_id ";

	public static String QUERY_INDICATOR_TO_ITEM_SQL="SELECT\n" +
			"\tspmi.mapping_header_id mappingHeaderId,\n" +
			"\tspmi.mapping_item_id mappingItemId,\n" +
			"\tspmi.length_value lengthValue,\n" +
			"\tspmi.width_value widthValue,\n" +
			"\tspmi.height_value heightValue\n" +
			"FROM\n" +
			"\tsrm_prc_mapping_items spmi\n" +
			"WHERE\n" +
			"\t1 = 1";
	
	public static String EXP_INDICATOR_HEADER_SQL="SELECT\r\n" + 
			"	t.INDICATOR_HEADER_ID indicatorHeaderId,\r\n" + 
			"	t.INDICATOR_NAME indicatorName,\r\n" + 
			"	t.UNIT_OF_MEASURE unitOfMeasure\r\n" + 
			"  FROM\r\n" + 
			"	srm_prc_indicator_headers T where 1=1  ";
	
	public static String EXP_INDICATOR_SUPPLIER_SQL ="SELECT\r\n" + 
			" DISTINCT	s.SUPPLIER_ID supplierId,\r\n" + 
			"	i.SUPPLIER_NAME supplierName\r\n" +  
			"FROM\r\n" + 
			"	srm_prc_indicator_suppliers S,\r\n" + 
			"	srm_pos_supplier_info i,\r\n" + 
			"	srm_prc_indicator_headers T\r\n" + 
			"WHERE\r\n" + 
			"	s.INDICATOR_HEADER_ID = t.INDICATOR_HEADER_ID\r\n" + 
			"AND s.SUPPLIER_ID = i.supplier_id  ";
	
	
	public static String EXP_INDICATOR_SUPP_PRICE_SQL ="SELECT\r\n" + 
			"  	s.SUPPLIER_ID supplierId,\r\n" + 
			"	i.SUPPLIER_NAME supplierName, t.INDICATOR_HEADER_ID indicatorHeaderId,s.ORIGINAL_PRICE originalPrice\r\n" +  
			"FROM\r\n" + 
			"	srm_prc_indicator_suppliers S,\r\n" + 
			"	srm_pos_supplier_info i,\r\n" + 
			"	srm_prc_indicator_headers T\r\n" + 
			"WHERE\r\n" + 
			"	s.INDICATOR_HEADER_ID = t.INDICATOR_HEADER_ID\r\n" + 
			"AND s.SUPPLIER_ID = i.supplier_id  ";

	 private Integer indicatorHeaderId;
	 private String indicatorNumber;
	 private String dispIndicatorNumber;
	 private String indicatorName;
	 private String indicatorStatus;
	 private BigDecimal indicatorPrice;
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 private Date endDateActive;
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 private Date creationDate;
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 private Date lastUpdateDate;
	 private String indicatorStatusMean;
	 private String userFullName;
	 private String lastUpdatedName;
	 private String additionalInformation;
	 private String unitOfMeasure;
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 private Date priceCreateDate;
	 private String description;
	 private String computationalFormula;
	 private Integer supplierId;
	 private String adjustmentFlag;
	 private String supplierName;
	 private String supplierNumber;
	 private BigDecimal originalPrice;
	 private BigDecimal finallyPrice;
	 private Integer mappingHeaderId;
	 private Integer mappingItemId;
	 private BigDecimal lengthValue;
	 private BigDecimal widthValue;
	 private BigDecimal heightValue;
	private Integer indicatorSupplierId;

	public Integer getIndicatorSupplierId() { return indicatorSupplierId; }

	public void setIndicatorSupplierId(Integer indicatorSupplierId) { this.indicatorSupplierId = indicatorSupplierId; }

	public Integer getMappingHeaderId() { return mappingHeaderId; }

	public void setMappingHeaderId(Integer mappingHeaderId) { this.mappingHeaderId = mappingHeaderId; }
	public Integer getMappingItemId() { return mappingItemId; }

	public void setMappingItemId(Integer mappingItemId) { this.mappingItemId = mappingItemId; }

	public BigDecimal getLengthValue() { return lengthValue; }

	public void setLengthValue(BigDecimal lengthValue) { this.lengthValue = lengthValue; }

	public BigDecimal getWidthValue() { return widthValue; }

	public void setWidthValue(BigDecimal widthValue) { this.widthValue = widthValue; }

	public BigDecimal getHeightValue() { return heightValue; }

	public void setHeightValue(BigDecimal heightValue) { this.heightValue = heightValue; }

	public Integer getSupplierId() { return supplierId; }

	public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

	public String getAdjustmentFlag() {
		return adjustmentFlag;
	}

	public void setAdjustmentFlag(String adjustmentFlag) {
		this.adjustmentFlag = adjustmentFlag;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getFinallyPrice() {
		return finallyPrice;
	}

	public void setFinallyPrice(BigDecimal finallyPrice) {
		this.finallyPrice = finallyPrice;
	}
	 
	 
	public Integer getIndicatorHeaderId() {
		return indicatorHeaderId;
	}
	public void setIndicatorHeaderId(Integer indicatorHeaderId) {
		this.indicatorHeaderId = indicatorHeaderId;
	}
	public String getIndicatorNumber() {
		return indicatorNumber;
	}
	public void setIndicatorNumber(String indicatorNumber) {
		this.indicatorNumber = indicatorNumber;
	}
	public String getDispIndicatorNumber() {
		return dispIndicatorNumber;
	}
	public void setDispIndicatorNumber(String dispIndicatorNumber) {
		this.dispIndicatorNumber = dispIndicatorNumber;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getIndicatorStatus() {
		return indicatorStatus;
	}
	public void setIndicatorStatus(String indicatorStatus) {
		this.indicatorStatus = indicatorStatus;
	}
	public BigDecimal getIndicatorPrice() {
		return indicatorPrice;
	}
	public void setIndicatorPrice(BigDecimal indicatorPrice) {
		this.indicatorPrice = indicatorPrice;
	}
	public Date getEndDateActive() {
		return endDateActive;
	}
	public void setEndDateActive(Date endDateActive) {
		this.endDateActive = endDateActive;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getIndicatorStatusMean() {
		return indicatorStatusMean;
	}
	public void setIndicatorStatusMean(String indicatorStatusMean) {
		this.indicatorStatusMean = indicatorStatusMean;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getLastUpdatedName() {
		return lastUpdatedName;
	}
	public void setLastUpdatedName(String lastUpdatedName) {
		this.lastUpdatedName = lastUpdatedName;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public Date getPriceCreateDate() {
		return priceCreateDate;
	}
	public void setPriceCreateDate(Date priceCreateDate) {
		this.priceCreateDate = priceCreateDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComputationalFormula() {
		return computationalFormula;
	}
	public void setComputationalFormula(String computationalFormula) {
		this.computationalFormula = computationalFormula;
	}
	
	 
	 
}
