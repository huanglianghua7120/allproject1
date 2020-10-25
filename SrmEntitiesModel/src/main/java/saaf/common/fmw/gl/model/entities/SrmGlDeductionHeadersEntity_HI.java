package saaf.common.fmw.gl.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmGlDeductionHeadersEntity_HI Entity Object
 * Wed Feb 28 11:34:20 CST 2018  Auto Generate
 */
@Entity
@Table(name = "SRM_GL_DEDUCTION_HEADERS")
public class SrmGlDeductionHeadersEntity_HI {
    private Integer deductionId;
    private String deductionNumber;
    private String deductionStatus;
    private Integer supplierId;
    private String currencyCode;
    private BigDecimal amountDeducted;
    private BigDecimal amountOfRelief;
    private BigDecimal amountOfActual;
    private String reasonOfRelief;
    private String description;
    private Integer fileId;
    private String transmitStatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date transmitDate;
    private Integer transmitId;
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

    public void setDeductionId(Integer deductionId) {
	this.deductionId = deductionId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "deduction_id", nullable = false, length = 11)    
    public Integer getDeductionId() {
	return deductionId;
    }

    public void setDeductionNumber(String deductionNumber) {
	this.deductionNumber = deductionNumber;
    }

    @Column(name = "deduction_number", nullable = true, length = 30)    
    public String getDeductionNumber() {
	return deductionNumber;
    }

    public void setDeductionStatus(String deductionStatus) {
	this.deductionStatus = deductionStatus;
    }

    @Column(name = "deduction_status", nullable = true, length = 30)    
    public String getDeductionStatus() {
	return deductionStatus;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = true, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
    }

    @Column(name = "currency_code", nullable = true, length = 30)    
    public String getCurrencyCode() {
	return currencyCode;
    }

    public void setAmountDeducted(BigDecimal amountDeducted) {
	this.amountDeducted = amountDeducted;
    }

    @Column(name = "amount_deducted", precision = 22, scale = 2)    
    public BigDecimal getAmountDeducted() {
	return amountDeducted;
    }

    public void setAmountOfRelief(BigDecimal amountOfRelief) {
	this.amountOfRelief = amountOfRelief;
    }

    @Column(name = "amount_of_relief", precision = 22, scale = 2)    
    public BigDecimal getAmountOfRelief() {
	return amountOfRelief;
    }

    public void setAmountOfActual(BigDecimal amountOfActual) {
	this.amountOfActual = amountOfActual;
    }

    @Column(name = "amount_of_actual", precision = 22, scale = 2)    
    public BigDecimal getAmountOfActual() {
	return amountOfActual;
    }

    public void setReasonOfRelief(String reasonOfRelief) {
	this.reasonOfRelief = reasonOfRelief;
    }

    @Column(name = "reason_of_relief", nullable = true, length = 240)    
    public String getReasonOfRelief() {
	return reasonOfRelief;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 500)    
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

    public void setTransmitStatus(String transmitStatus) {
	this.transmitStatus = transmitStatus;
    }

    @Column(name = "transmit_status", nullable = true, length = 30)    
    public String getTransmitStatus() {
	return transmitStatus;
    }

    public void setTransmitDate(Date transmitDate) {
	this.transmitDate = transmitDate;
    }

    @Column(name = "transmit_date", nullable = true, length = 0)    
    public Date getTransmitDate() {
	return transmitDate;
    }

    public void setTransmitId(Integer transmitId) {
	this.transmitId = transmitId;
    }

    @Column(name = "transmit_id", nullable = true, length = 11)    
    public Integer getTransmitId() {
	return transmitId;
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
}

