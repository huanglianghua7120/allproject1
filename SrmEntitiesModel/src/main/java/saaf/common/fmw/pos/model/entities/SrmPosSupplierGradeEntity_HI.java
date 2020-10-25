package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierGradeEntity_HI Entity Object
 * Mon Sep 03 17:57:30 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pos_supplier_grade")
public class SrmPosSupplierGradeEntity_HI {
    private Integer gradeId;
    private String gradeCode;
    private String gradeStatus;
    private String evaluatePeriod;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date evaluateStartDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date evaluateEndDate;
    private Integer approvalEmployeeId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate;
    private Integer fileId;
    private String gradeNote;
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
    
    public void setGradeId(Integer gradeId) {
	this.gradeId = gradeId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_SUPPLIER_GRADE_S", sequenceName = "SRM_POS_SUPPLIER_GRADE_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_SUPPLIER_GRADE_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "grade_id", nullable = false, length = 11)    
    public Integer getGradeId() {
	return gradeId;
    }

    public void setGradeCode(String gradeCode) {
	this.gradeCode = gradeCode;
    }

    @Column(name = "grade_code", nullable = false, length = 30)    
    public String getGradeCode() {
	return gradeCode;
    }

    public void setGradeStatus(String gradeStatus) {
	this.gradeStatus = gradeStatus;
    }

    @Column(name = "grade_status", nullable = false, length = 30)    
    public String getGradeStatus() {
	return gradeStatus;
    }

    public void setEvaluatePeriod(String evaluatePeriod) {
	this.evaluatePeriod = evaluatePeriod;
    }

    @Column(name = "evaluate_period", nullable = false, length = 30)    
    public String getEvaluatePeriod() {
	return evaluatePeriod;
    }
    
    @Column(name = "evaluate_start_date", nullable = true, length = 0)
    public Date getEvaluateStartDate() {
		return evaluateStartDate;
	}

	public void setEvaluateStartDate(Date evaluateStartDate) {
		this.evaluateStartDate = evaluateStartDate;
	}
	
	@Column(name = "evaluate_end_date", nullable = true, length = 0)
	public Date getEvaluateEndDate() {
		return evaluateEndDate;
	}

	public void setEvaluateEndDate(Date evaluateEndDate) {
		this.evaluateEndDate = evaluateEndDate;
	}

	public void setApprovalEmployeeId(Integer approvalEmployeeId) {
	this.approvalEmployeeId = approvalEmployeeId;
    }

    @Column(name = "approval_employee_id", nullable = true, length = 11)    
    public Integer getApprovalEmployeeId() {
	return approvalEmployeeId;
    }

    public void setApprovalDate(Date approvalDate) {
	this.approvalDate = approvalDate;
    }

    @Column(name = "approval_date", nullable = true, length = 0)    
    public Date getApprovalDate() {
	return approvalDate;
    }

    public void setFileId(Integer fileId) {
	this.fileId = fileId;
    }

    @Column(name = "file_id", nullable = true, length = 11)    
    public Integer getFileId() {
	return fileId;
    }

    public void setGradeNote(String gradeNote) {
	this.gradeNote = gradeNote;
    }

    @Column(name = "grade_note", nullable = true, length = 2000)    
    public String getGradeNote() {
	return gradeNote;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

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
    
    @Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
    
    
}

