package saaf.common.fmw.intf.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmIntfCategoriesEntity_HI Entity Object
 * Fri Nov 17 17:46:00 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_INTF_CATEGORIES")
public class SrmIntfCategoriesEntity_HI {
    private Integer intfCateId;
    private String categoryCode;
    private String categoryName;
    private String enableFlag;
    private String categoryType;
    private String sourceType;
    private String handelStatus;
    private String errorMsg;
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

    public void setIntfCateId(Integer intfCateId) {
	this.intfCateId = intfCateId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "intf_cate_id", nullable = false, length = 11)    
    public Integer getIntfCateId() {
	return intfCateId;
    }

    public void setCategoryCode(String categoryCode) {
	this.categoryCode = categoryCode;
    }

    @Column(name = "category_code", nullable = true, length = 30)    
    public String getCategoryCode() {
	return categoryCode;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    @Column(name = "category_name", nullable = true, length = 200)    
    public String getCategoryName() {
	return categoryName;
    }

    public void setEnableFlag(String enableFlag) {
	this.enableFlag = enableFlag;
    }

    @Column(name = "enable_flag", nullable = true, length = 10)    
    public String getEnableFlag() {
	return enableFlag;
    }

    public void setCategoryType(String categoryType) {
	this.categoryType = categoryType;
    }

    @Column(name = "category_type", nullable = true, length = 30)    
    public String getCategoryType() {
	return categoryType;
    }

    public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
    }

    @Column(name = "source_type", nullable = true, length = 30)    
    public String getSourceType() {
	return sourceType;
    }

    public void setHandelStatus(String handelStatus) {
	this.handelStatus = handelStatus;
    }

    @Column(name = "handel_status", nullable = false, length = 10)    
    public String getHandelStatus() {
	return handelStatus;
    }

    public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
    }

    @Column(name = "error_msg", nullable = true, length = 200)    
    public String getErrorMsg() {
	return errorMsg;
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
}

