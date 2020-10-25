package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmSupplierScoreEntity_HI Entity Object
 * Tue Apr 03 16:31:45 GMT+08:00 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_supplier_score")
public class SrmSpmSupplierScoreEntity_HI {
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

	public void setSupplierScoreId(Integer supplierScoreId) {
		this.supplierScoreId = supplierScoreId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_SUPPLIER_SCORE_S", sequenceName = "SRM_SPM_SUPPLIER_SCORE_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_SUPPLIER_SCORE_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "supplier_score_id", nullable = false, length = 11)	
	public Integer getSupplierScoreId() {
		return supplierScoreId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name = "scheme_id", nullable = true, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	@Column(name = "vendor_id", nullable = true, length = 11)	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}

	@Column(name = "tpl_category_id", nullable = true, length = 11)	
	public Integer getTplCategoryId() {
		return tplCategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	@Column(name = "segment1", nullable = true, length = 30)	
	public String getSegment1() {
		return segment1;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	@Column(name = "segment2", nullable = true, length = 30)	
	public String getSegment2() {
		return segment2;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	@Column(name = "segment3", nullable = true, length = 30)	
	public String getSegment3() {
		return segment3;
	}

	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}

	@Column(name = "segment4", nullable = true, length = 30)	
	public String getSegment4() {
		return segment4;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	@Column(name = "score_type", nullable = true, length = 30)	
	public String getScoreType() {
		return scoreType;
	}

	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}

	@Column(name = "tpl_dimension_id", nullable = true, length = 11)	
	public Integer getTplDimensionId() {
		return tplDimensionId;
	}

	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}

	@Column(name = "evaluate_dimension", nullable = true, length = 30)	
	public String getEvaluateDimension() {
		return evaluateDimension;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Column(name = "score", precision = 22, scale = 2)	
	public BigDecimal getScore() {
		return score;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "rank", nullable = true, length = 11)	
	public Integer getRank() {
		return rank;
	}

	public void setCurrSupplyRatio(BigDecimal currSupplyRatio) {
		this.currSupplyRatio = currSupplyRatio;
	}

	@Column(name = "curr_supply_ratio", precision = 22, scale = 2)	
	public BigDecimal getCurrSupplyRatio() {
		return currSupplyRatio;
	}

	public void setAdviseSupplyRatio(BigDecimal adviseSupplyRatio) {
		this.adviseSupplyRatio = adviseSupplyRatio;
	}

	@Column(name = "advise_supply_ratio", precision = 22, scale = 2)	
	public BigDecimal getAdviseSupplyRatio() {
		return adviseSupplyRatio;
	}

	public void setNewSupplyRatio(BigDecimal newSupplyRatio) {
		this.newSupplyRatio = newSupplyRatio;
	}

	@Column(name = "new_supply_ratio", precision = 22, scale = 2)	
	public BigDecimal getNewSupplyRatio() {
		return newSupplyRatio;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 30)	
	public String getStatus() {
		return status;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	@Column(name = "publish_flag", nullable = true, length = 30)	
	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "publish_date", nullable = true, length = 0)	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setSourceSchemeNumber(String sourceSchemeNumber) {
		this.sourceSchemeNumber = sourceSchemeNumber;
	}

	@Column(name = "source_scheme_number", nullable = true, length = 40)	
	public String getSourceSchemeNumber() {
		return sourceSchemeNumber;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 240)	
	public String getDescription() {
		return description;
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
