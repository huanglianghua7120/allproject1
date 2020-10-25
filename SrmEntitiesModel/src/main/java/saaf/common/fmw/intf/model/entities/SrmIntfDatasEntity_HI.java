package saaf.common.fmw.intf.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmIntfDatasEntity_HI Entity Object
 * Thu Nov 30 10:14:11 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_INTF_DATAS")
public class SrmIntfDatasEntity_HI {
    private Integer dataId;
    private String intfType;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date syncDate;
    private Integer sourceLogId;
    private String sourceType;
    private String handleStatus;
    private String errorMsg;
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
    private String attribute16;
    private String attribute17;
    private String attribute18;
    private String attribute19;
    private String attribute20;
    private String attribute21;
    private String attribute22;
    private String attribute23;
    private String attribute24;
    private String attribute25;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;

    private String batchId;
    private Integer operatorUserId;

    public void setDataId(Integer dataId) {
	this.dataId = dataId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "data_id", nullable = false, length = 11)    
    public Integer getDataId() {
	return dataId;
    }

    public void setIntfType(String intfType) {
	this.intfType = intfType;
    }

    @Column(name = "intf_type", nullable = true, length = 100)    
    public String getIntfType() {
	return intfType;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 500)    
    public String getDescription() {
	return description;
    }

    public void setSyncDate(Date syncDate) {
	this.syncDate = syncDate;
    }

    @Column(name = "sync_date", nullable = true, length = 0)    
    public Date getSyncDate() {
	return syncDate;
    }

    public void setSourceLogId(Integer sourceLogId) {
	this.sourceLogId = sourceLogId;
    }

    @Column(name = "source_log_id", nullable = true, length = 11)    
    public Integer getSourceLogId() {
	return sourceLogId;
    }

    public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
    }

    @Column(name = "source_type", nullable = true, length = 30)    
    public String getSourceType() {
	return sourceType;
    }

    public void setHandleStatus(String handleStatus) {
	this.handleStatus = handleStatus;
    }

    @Column(name = "handle_status", nullable = false, length = 10)    
    public String getHandleStatus() {
	return handleStatus;
    }

    public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
    }

    @Column(name = "error_msg", nullable = true, length = 200)    
    public String getErrorMsg() {
	return errorMsg;
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

    public void setAttribute16(String attribute16) {
	this.attribute16 = attribute16;
    }

    @Column(name = "attribute16", nullable = true, length = 240)    
    public String getAttribute16() {
	return attribute16;
    }

    public void setAttribute17(String attribute17) {
	this.attribute17 = attribute17;
    }

    @Column(name = "attribute17", nullable = true, length = 240)    
    public String getAttribute17() {
	return attribute17;
    }

    public void setAttribute18(String attribute18) {
	this.attribute18 = attribute18;
    }

    @Column(name = "attribute18", nullable = true, length = 240)    
    public String getAttribute18() {
	return attribute18;
    }

    public void setAttribute19(String attribute19) {
	this.attribute19 = attribute19;
    }

    @Column(name = "attribute19", nullable = true, length = 240)    
    public String getAttribute19() {
	return attribute19;
    }

    public void setAttribute20(String attribute20) {
	this.attribute20 = attribute20;
    }

    @Column(name = "attribute20", nullable = true, length = 240)    
    public String getAttribute20() {
	return attribute20;
    }

    public void setAttribute21(String attribute21) {
	this.attribute21 = attribute21;
    }

    @Column(name = "attribute21", nullable = true, length = 240)    
    public String getAttribute21() {
	return attribute21;
    }

    public void setAttribute22(String attribute22) {
	this.attribute22 = attribute22;
    }

    @Column(name = "attribute22", nullable = true, length = 240)    
    public String getAttribute22() {
	return attribute22;
    }

    public void setAttribute23(String attribute23) {
	this.attribute23 = attribute23;
    }

    @Column(name = "attribute23", nullable = true, length = 240)    
    public String getAttribute23() {
	return attribute23;
    }

    public void setAttribute24(String attribute24) {
	this.attribute24 = attribute24;
    }

    @Column(name = "attribute24", nullable = true, length = 240)    
    public String getAttribute24() {
	return attribute24;
    }

    public void setAttribute25(String attribute25) {
	this.attribute25 = attribute25;
    }

    @Column(name = "attribute25", nullable = true, length = 240)    
    public String getAttribute25() {
	return attribute25;
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
    @Column(name = "batch_id", nullable = true, length = 100)  
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
    
}

