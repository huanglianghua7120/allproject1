package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosChangeCategoriesEntity_HI Entity Object
 * Wed Nov 15 10:52:23 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_change_categories")
public class SrmPosChangeCategoriesEntity_HI {
    private Integer changeCategoryId;
    private Integer changeId;
    private Integer supplierCategoryId;
    private Integer supplierId;
    private Integer categoryId;
    private String bigCategoryCode;
    private String middleCategoryCode;
    private String smallCategoryCode;
    private Integer cooperativeProduct;
    private String managementBrand;
    private Integer supplyCycle;
    private Integer purchaseLimit;
    private Integer supplyOrder;
    private Integer quotaRate;
    private String status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvedDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate;
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

    public void setChangeCategoryId(Integer changeCategoryId) {
	this.changeCategoryId = changeCategoryId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_CHANGE_CATEGORIES_S", sequenceName = "SRM_POS_CHANGE_CATEGORIES_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_CHANGE_CATEGORIES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "change_category_id", nullable = false, length = 11)    
    public Integer getChangeCategoryId() {
	return changeCategoryId;
    }

    public void setChangeId(Integer changeId) {
	this.changeId = changeId;
    }

    @Column(name = "change_id", nullable = false, length = 11)    
    public Integer getChangeId() {
	return changeId;
    }

    public void setSupplierCategoryId(Integer supplierCategoryId) {
	this.supplierCategoryId = supplierCategoryId;
    }

    @Column(name = "supplier_category_id", nullable = true, length = 11)    
    public Integer getSupplierCategoryId() {
	return supplierCategoryId;
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

    public void setBigCategoryCode(String bigCategoryCode) {
	this.bigCategoryCode = bigCategoryCode;
    }

    @Column(name = "big_category_code", nullable = true, length = 30)
    public String getBigCategoryCode() {
	return bigCategoryCode;
    }

    public void setMiddleCategoryCode(String middleCategoryCode) {
	this.middleCategoryCode = middleCategoryCode;
    }

    @Column(name = "middle_category_code", nullable = true, length = 30)    
    public String getMiddleCategoryCode() {
	return middleCategoryCode;
    }

    public void setSmallCategoryCode(String smallCategoryCode) {
	this.smallCategoryCode = smallCategoryCode;
    }

    @Column(name = "small_category_code", nullable = true, length = 30)    
    public String getSmallCategoryCode() {
	return smallCategoryCode;
    }

    public void setCooperativeProduct(Integer cooperativeProduct) {
	this.cooperativeProduct = cooperativeProduct;
    }

    @Column(name = "cooperative_product", nullable = true, length = 11)    
    public Integer getCooperativeProduct() {
	return cooperativeProduct;
    }

    public void setManagementBrand(String managementBrand) {
	this.managementBrand = managementBrand;
    }

    @Column(name = "management_brand", nullable = true, length = 500)    
    public String getManagementBrand() {
	return managementBrand;
    }

    public void setSupplyCycle(Integer supplyCycle) {
	this.supplyCycle = supplyCycle;
    }

    @Column(name = "supply_cycle", nullable = true, length = 11)    
    public Integer getSupplyCycle() {
	return supplyCycle;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Column(name = "status", nullable = true, length = 30)    
    public String getStatus() {
	return status;
    }

    public void setApprovedDate(Date approvedDate) {
	this.approvedDate = approvedDate;
    }

    @Column(name = "approved_date", nullable = true, length = 0)    
    public Date getApprovedDate() {
	return approvedDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    @Column(name = "invalid_date", nullable = true, length = 0)
    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setPurchaseLimit(Integer purchaseLimit) {
        this.purchaseLimit = purchaseLimit;
    }

    @Column(name = "purchase_limit", nullable = true, length = 11)
    public Integer getPurchaseLimit() {
        return purchaseLimit;
    }

    public void setSupplyOrder(Integer supplyOrder) {
        this.supplyOrder = supplyOrder;
    }

    @Column(name = "supply_order", nullable = true, length = 11)
    public Integer getSupplyOrder() {
        return supplyOrder;
    }

    public void setQuotaRate(Integer quotaRate) {
        this.quotaRate = quotaRate;
    }

    @Column(name = "quota_rate", nullable = true, length = 11)
    public Integer getQuotaRate() {
        return quotaRate;
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
    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

