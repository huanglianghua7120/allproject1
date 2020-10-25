package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosQualificationInfoEntity_HI Entity Object
 * Tue Nov 14 14:52:30 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_qualification_info")
public class SrmPosQualificationInfoEntity_HI {
    private Integer qualificationId;
    private Integer supplierId;
    private Integer supplierAddrId;
    private Integer orgId;
    private String enterpriseType;
    private String sceneType;
    private String qualificationNumber;
    private String qualificationStatus;
    private String qualificationResult;
    private String needOnsiteInspection;
    private String inspectionResult;
    private String needSampleTrial;
    private String sampleTrialResult;
    private String reasonNoInvestigation;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date temporaryQualifiedDate;
    private String description;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private Integer qualificationFileId;
    private Integer reportAppendixFileId;
    private Integer projectReportFileId;

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
    
    private Integer approveBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approveDate;
    private Integer operatorUserId;

    public void setQualificationId(Integer qualificationId) {
	this.qualificationId = qualificationId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_QUALIFICATION_INFO_S", sequenceName = "SRM_POS_QUALIFICATION_INFO_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_QUALIFICATION_INFO_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "qualification_id", nullable = false, length = 11)    
    public Integer getQualificationId() {
	return qualificationId;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = false, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setSupplierAddrId(Integer supplierAddrId) {
	this.supplierAddrId = supplierAddrId;
    }

    @Column(name = "supplier_addr_id", nullable = true, length = 11)    
    public Integer getSupplierAddrId() {
	return supplierAddrId;
    }

    public void setOrgId(Integer orgId) {
	this.orgId = orgId;
    }

    @Column(name = "org_id", nullable = true, length = 11)    
    public Integer getOrgId() {
	return orgId;
    }

    public void setEnterpriseType(String enterpriseType) {
	this.enterpriseType = enterpriseType;
    }

    @Column(name = "enterprise_type", nullable = true, length = 30)    
    public String getEnterpriseType() {
	return enterpriseType;
    }

    public void setQualificationNumber(String qualificationNumber) {
	this.qualificationNumber = qualificationNumber;
    }

    @Column(name = "qualification_number", nullable = true, length = 100)    
    public String getQualificationNumber() {
	return qualificationNumber;
    }

    public void setQualificationStatus(String qualificationStatus) {
	this.qualificationStatus = qualificationStatus;
    }

    @Column(name = "qualification_status", nullable = true, length = 30)    
    public String getQualificationStatus() {
	return qualificationStatus;
    }

    public void setQualificationResult(String qualificationResult) {
	this.qualificationResult = qualificationResult;
    }

    @Column(name = "qualification_result", nullable = true, length = 30)    
    public String getQualificationResult() {
	return qualificationResult;
    }

    public void setNeedOnsiteInspection(String needOnsiteInspection) {
	this.needOnsiteInspection = needOnsiteInspection;
    }

    @Column(name = "need_onsite_inspection", nullable = true, length = 10)    
    public String getNeedOnsiteInspection() {
	return needOnsiteInspection;
    }

    public void setInspectionResult(String inspectionResult) {
	this.inspectionResult = inspectionResult;
    }

    @Column(name = "inspection_result", nullable = true, length = 30)    
    public String getInspectionResult() {
	return inspectionResult;
    }

    public void setNeedSampleTrial(String needSampleTrial) {
	this.needSampleTrial = needSampleTrial;
    }

    @Column(name = "need_sample_trial", nullable = true, length = 10)    
    public String getNeedSampleTrial() {
	return needSampleTrial;
    }

    public void setSampleTrialResult(String sampleTrialResult) {
	this.sampleTrialResult = sampleTrialResult;
    }

    @Column(name = "sample_trial_result", nullable = true, length = 30)    
    public String getSampleTrialResult() {
	return sampleTrialResult;
    }

    public void setReasonNoInvestigation(String reasonNoInvestigation) {
	this.reasonNoInvestigation = reasonNoInvestigation;
    }

    @Column(name = "reason_no_investigation", nullable = true, length = 240)    
    public String getReasonNoInvestigation() {
	return reasonNoInvestigation;
    }

    public void setTemporaryQualifiedDate(Date temporaryQualifiedDate) {
	this.temporaryQualifiedDate = temporaryQualifiedDate;
    }

    @Column(name = "temporary_qualified_date", nullable = true, length = 0)    
    public Date getTemporaryQualifiedDate() {
	return temporaryQualifiedDate;
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
    
    @Column(name = "qualification_file_id", nullable = true, length = 11)
    public Integer getQualificationFileId() {
		return qualificationFileId;
	}

	public void setQualificationFileId(Integer qualificationFileId) {
		this.qualificationFileId = qualificationFileId;
	}

    @Column(name = "report_appendix_file_id", nullable = true, length = 11)
    public Integer getReportAppendixFileId() {
        return reportAppendixFileId;
    }

    public void setReportAppendixFileId(Integer reportAppendixFileId) {
        this.reportAppendixFileId = reportAppendixFileId;
    }

    @Column(name = "project_report_file_id", nullable = true, length = 11)
    public Integer getProjectReportFileId() {
        return projectReportFileId;
    }

    public void setProjectReportFileId(Integer projectReportFileId) {
        this.projectReportFileId = projectReportFileId;
    }

    @Column(name = "approve_by", nullable = true, length = 11)
	public Integer getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(Integer approveBy) {
		this.approveBy = approveBy;
	}
	
	@Column(name = "approve_date", nullable = true, length = 0) 
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
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

    @Column(name = "scene_type", nullable = false, length = 30)
    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }
}

