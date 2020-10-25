package saaf.common.fmw.pos.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmPosLocaleReviewsEntity_HI Entity Object
 * Tue Mar 20 14:33:39 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pos_locale_reviews")
public class SrmPosLocaleReviewsEntity_HI {
    private Integer localeReviewId; //现场考察ID
    private String localeReviewNumber; //现场考察
    private Integer supplierId; //供应商ID，关联表:srm_pos_supplier_info
    private String sceneType; //场景类型(POS_SCENE_TYPE)
    private Integer qualificationId; //资质审查ID
    private Integer investigationPlanLinesId; //考察计划行ID
    private String localeReviewStatus; //现场考察状态(POS_APPROVAL_STATUS)
    private String localeReviewResult; //现场考察结果(POS_EXAMINE_RESULT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date reviewDate; //考察时间
    private String reviewMember; //考察小组成员
    private String supplierMember; //供方成员
    private String supplyFlag; //是否已供货(Y/N)
    private String description; //说明
    private Integer fileId; //现场考察相关附件
    private Integer approvedBy; //(备用)审批人
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvedDate; //审批时间
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
    private Integer operatorUserId;

	public void setLocaleReviewId(Integer localeReviewId) {
		this.localeReviewId = localeReviewId;
	}

	@Id
	@SequenceGenerator(name = "SRM_POS_LOCALE_REVIEWS_S", sequenceName = "SRM_POS_LOCALE_REVIEWS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_POS_LOCALE_REVIEWS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "locale_review_id", nullable = false, length = 11)	
	public Integer getLocaleReviewId() {
		return localeReviewId;
	}

	public void setLocaleReviewNumber(String localeReviewNumber) {
		this.localeReviewNumber = localeReviewNumber;
	}

	@Column(name = "locale_review_number", nullable = false, length = 30)	
	public String getLocaleReviewNumber() {
		return localeReviewNumber;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = false, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	@Column(name = "scene_type", nullable = true, length = 30)	
	public String getSceneType() {
		return sceneType;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	@Column(name = "qualification_id", nullable = true, length = 11)	
	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setInvestigationPlanLinesId(Integer investigationPlanLinesId) {
		this.investigationPlanLinesId = investigationPlanLinesId;
	}

	@Column(name = "investigation_plan_lines_id", nullable = true, length = 11)
	public Integer getInvestigationPlanLinesId() {
		return investigationPlanLinesId;
	}

	public void setLocaleReviewStatus(String localeReviewStatus) {
		this.localeReviewStatus = localeReviewStatus;
	}

	@Column(name = "locale_review_status", nullable = true, length = 30)	
	public String getLocaleReviewStatus() {
		return localeReviewStatus;
	}

	public void setLocaleReviewResult(String localeReviewResult) {
		this.localeReviewResult = localeReviewResult;
	}

	@Column(name = "locale_review_result", nullable = true, length = 30)	
	public String getLocaleReviewResult() {
		return localeReviewResult;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Column(name = "review_date", nullable = true, length = 0)	
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewMember(String reviewMember) {
		this.reviewMember = reviewMember;
	}

	@Column(name = "review_member", nullable = true, length = 240)	
	public String getReviewMember() {
		return reviewMember;
	}

	public void setSupplierMember(String supplierMember) {
		this.supplierMember = supplierMember;
	}

	@Column(name = "supplier_member", nullable = true, length = 240)	
	public String getSupplierMember() {
		return supplierMember;
	}

	public void setSupplyFlag(String supplyFlag) {
		this.supplyFlag = supplyFlag;
	}

	@Column(name = "supply_flag", nullable = true, length = 1)	
	public String getSupplyFlag() {
		return supplyFlag;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 240)	
	public String getDescription() {
		return description;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 11)	
	public Integer getFileId() {
		return fileId;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	@Column(name = "approved_by", nullable = true, length = 11)	
	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Column(name = "approved_date", nullable = true, length = 0)	
	public Date getApprovedDate() {
		return approvedDate;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
