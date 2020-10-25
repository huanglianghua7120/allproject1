package saaf.common.fmw.prc.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPrcIndicatorHeadersEntity_HI Entity Object
 * Mon Feb 05 16:26:44 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_prc_indicator_headers")
public class SrmPrcIndicatorHeadersEntity_HI {
    private Integer indicatorHeaderId;
    private String indicatorNumber;
    private Integer indicatorVersion;
    private String dispIndicatorNumber;
    private String indicatorName;
    private String indicatorStatus;
    private String additionalInformation;
    private String unitOfMeasure;
    private BigDecimal indicatorPrice;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date priceCreateDate;
    private String computationalFormula;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String description;
    private Integer fromSourceId;
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

    public void setIndicatorHeaderId(Integer indicatorHeaderId) {
	this.indicatorHeaderId = indicatorHeaderId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "indicator_header_id", nullable = false, length = 11)    
    public Integer getIndicatorHeaderId() {
	return indicatorHeaderId;
    }

    public void setIndicatorNumber(String indicatorNumber) {
	this.indicatorNumber = indicatorNumber;
    }

    @Column(name = "indicator_number", nullable = true, length = 30)    
    public String getIndicatorNumber() {
	return indicatorNumber;
    }

    public void setIndicatorVersion(Integer indicatorVersion) {
	this.indicatorVersion = indicatorVersion;
    }

    @Column(name = "indicator_version", nullable = true, length = 11)    
    public Integer getIndicatorVersion() {
	return indicatorVersion;
    }

    public void setDispIndicatorNumber(String dispIndicatorNumber) {
	this.dispIndicatorNumber = dispIndicatorNumber;
    }

    @Column(name = "disp_indicator_number", nullable = true, length = 30)    
    public String getDispIndicatorNumber() {
	return dispIndicatorNumber;
    }

    public void setIndicatorName(String indicatorName) {
	this.indicatorName = indicatorName;
    }

    @Column(name = "indicator_name", nullable = true, length = 240)    
    public String getIndicatorName() {
	return indicatorName;
    }

    public void setIndicatorStatus(String indicatorStatus) {
	this.indicatorStatus = indicatorStatus;
    }

    @Column(name = "indicator_status", nullable = true, length = 30)    
    public String getIndicatorStatus() {
	return indicatorStatus;
    }

    public void setAdditionalInformation(String additionalInformation) {
	this.additionalInformation = additionalInformation;
    }

    @Column(name = "additional_information", nullable = true, length = 240)    
    public String getAdditionalInformation() {
	return additionalInformation;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
	this.unitOfMeasure = unitOfMeasure;
    }

    @Column(name = "unit_of_measure", nullable = true, length = 150)    
    public String getUnitOfMeasure() {
	return unitOfMeasure;
    }

    public void setIndicatorPrice(BigDecimal indicatorPrice) {
	this.indicatorPrice = indicatorPrice;
    }

    @Column(name = "indicator_price", precision = 22, scale = 6)    
    public BigDecimal getIndicatorPrice() {
	return indicatorPrice;
    }

    public void setPriceCreateDate(Date priceCreateDate) {
	this.priceCreateDate = priceCreateDate;
    }

    @Column(name = "price_create_date", nullable = true, length = 0)    
    public Date getPriceCreateDate() {
	return priceCreateDate;
    }

    public void setComputationalFormula(String computationalFormula) {
	this.computationalFormula = computationalFormula;
    }

    @Column(name = "computational_formula", nullable = true, length = 240)    
    public String getComputationalFormula() {
	return computationalFormula;
    }

    public void setStartDateActive(Date startDateActive) {
	this.startDateActive = startDateActive;
    }

    @Column(name = "start_date_active", nullable = true, length = 0)    
    public Date getStartDateActive() {
	return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
	this.endDateActive = endDateActive;
    }

    @Column(name = "end_date_active", nullable = true, length = 0)    
    public Date getEndDateActive() {
	return endDateActive;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 500)    
    public String getDescription() {
	return description;
    }

    public void setFromSourceId(Integer fromSourceId) {
	this.fromSourceId = fromSourceId;
    }

    @Column(name = "from_source_id", nullable = true, length = 11)    
    public Integer getFromSourceId() {
	return fromSourceId;
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
    
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

