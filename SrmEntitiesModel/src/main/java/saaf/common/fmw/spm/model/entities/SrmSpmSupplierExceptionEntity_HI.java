package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmSupplierExceptionEntity_HI Entity Object
 * Wed Mar 14 08:48:52 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_supplier_exception")
public class SrmSpmSupplierExceptionEntity_HI {
    private Integer exceptionId;
    private Integer orgId;
    private String exceptionObject;
    private Integer supplierId;
    private Integer categoryId;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String exceptionType;
    private String exceptionStatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
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

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public void setExceptionId(Integer exceptionId) {
	this.exceptionId = exceptionId;
    }

    @Id
    @SequenceGenerator(name = "SRM_SPM_SUPPLIER_EXCEPTION_S", sequenceName = "SRM_SPM_SUPPLIER_EXCEPTION_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_SPM_SUPPLIER_EXCEPTION_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "exception_id", nullable = false, length = 11)    
    public Integer getExceptionId() {
	return exceptionId;
    }

    public void setOrgId(Integer orgId) {
	this.orgId = orgId;
    }

    @Column(name = "org_id", nullable = true, length = 11)    
    public Integer getOrgId() {
	return orgId;
    }

    public void setExceptionObject(String exceptionObject) {
	this.exceptionObject = exceptionObject;
    }

    @Column(name = "exception_object", nullable = true, length = 30)    
    public String getExceptionObject() {
	return exceptionObject;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = true, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
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

    public void setExceptionType(String exceptionType) {
	this.exceptionType = exceptionType;
    }

    @Column(name = "exception_type", nullable = true, length = 30)    
    public String getExceptionType() {
	return exceptionType;
    }

    public void setExceptionStatus(String exceptionStatus) {
	this.exceptionStatus = exceptionStatus;
    }

    @Column(name = "exception_status", nullable = true, length = 30)    
    public String getExceptionStatus() {
	return exceptionStatus;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    @Column(name = "start_date", nullable = true, length = 0)    
    public Date getStartDate() {
	return startDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    @Column(name = "end_date", nullable = true, length = 0)    
    public Date getEndDate() {
	return endDate;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }
}

