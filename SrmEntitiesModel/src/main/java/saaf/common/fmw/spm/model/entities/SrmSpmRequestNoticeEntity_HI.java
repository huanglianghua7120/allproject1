package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmRequestNoticeEntity_HI Entity Object
 * Wed Apr 04 12:44:25 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_request_notice")
public class SrmSpmRequestNoticeEntity_HI {
    private Integer noticeId; //ID
    private Integer schemeId; //方案ID
    private Integer userId; //用户ID，关联表：SAAF_USER
    private Integer indicatorId; //指标ID
    private String indicatorName; //指标名称
    private String indicatorDimension; //指标维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private String dimensionName; //维度名称
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String categoryCode; //分类编码
    private String categoryName; //分类名称
    private Integer supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private String supplierNumber; //供应商编码
    private String supplierName; //供应商名称
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    private String description; //说明、备注
    private Integer sourceId; //来源ID
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer operatorUserId;

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_REQUEST_NOTICE_S", sequenceName = "SRM_SPM_REQUEST_NOTICE_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_REQUEST_NOTICE_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "notice_id", nullable = false, length = 11)	
	public Integer getNoticeId() {
		return noticeId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name = "scheme_id", nullable = true, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_id", nullable = true, length = 11)	
	public Integer getUserId() {
		return userId;
	}

	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	@Column(name = "indicator_id", nullable = true, length = 30)	
	public Integer getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	@Column(name = "indicator_name", nullable = true, length = 240)	
	public String getIndicatorName() {
		return indicatorName;
	}

	public void setIndicatorDimension(String indicatorDimension) {
		this.indicatorDimension = indicatorDimension;
	}

	@Column(name = "indicator_dimension", nullable = true, length = 30)	
	public String getIndicatorDimension() {
		return indicatorDimension;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	@Column(name = "dimension_name", nullable = true, length = 240)	
	public String getDimensionName() {
		return dimensionName;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_code", nullable = true, length = 100)	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_name", nullable = true, length = 240)	
	public String getCategoryName() {
		return categoryName;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	@Column(name = "supplier_number", nullable = true, length = 100)	
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "supplier_name", nullable = true, length = 240)	
	public String getSupplierName() {
		return supplierName;
	}

	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}

	@Column(name = "evaluate_interval_from", nullable = true, length = 10)	
	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}

	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}

	@Column(name = "evaluate_interval_to", nullable = true, length = 10)	
	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 240)	
	public String getDescription() {
		return description;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 11)	
	public Integer getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 11)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 11)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name = "attribute_category", nullable = true, length = 30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", nullable = true, length = 240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", nullable = true, length = 240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", nullable = true, length = 240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", nullable = true, length = 240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", nullable = true, length = 240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name = "attribute6", nullable = true, length = 240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name = "attribute7", nullable = true, length = 240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name = "attribute8", nullable = true, length = 240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name = "attribute9", nullable = true, length = 240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name = "attribute10", nullable = true, length = 240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
