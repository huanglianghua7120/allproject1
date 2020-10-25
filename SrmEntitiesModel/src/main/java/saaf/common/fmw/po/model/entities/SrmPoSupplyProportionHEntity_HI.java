package saaf.common.fmw.po.model.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoSupplyProportionHEntity_HI Entity Object
 * Wed Nov 15 10:57:26 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_po_supply_proportion_h")
public class SrmPoSupplyProportionHEntity_HI {
	
    private Integer supplyId;
    private String supplyType;
    private String smallCategoryCode;
    private String smallCategoryName;
    private String itemCode;
    private String itemDescription;
    private Integer instId;
    private Integer itemId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String description;
    private String status;
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
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    private BigDecimal proportion;
    @Transient
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    @Transient
    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }
    @Transient
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    @Transient
    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    private Integer operatorUserId;

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
    
    public void setSupplyId(Integer supplyId) {
	this.supplyId = supplyId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_SUPPLY_PROPORTION_H_S", sequenceName = "SRM_PO_SUPPLY_PROPORTION_H_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_SUPPLY_PROPORTION_H_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "supply_id", nullable = false, length = 11)    
    public Integer getSupplyId() {
	return supplyId;
    }

    public void setSupplyType(String supplyType) {
	this.supplyType = supplyType;
    }

    @Column(name = "supply_type", nullable = false, length = 30)    
    public String getSupplyType() {
	return supplyType;
    }

    public void setSmallCategoryCode(String smallCategoryCode) {
	this.smallCategoryCode = smallCategoryCode;
    }

    @Column(name = "small_category_code", nullable = true, length = 100)    
    public String getSmallCategoryCode() {
	return smallCategoryCode;
    }

    public void setSmallCategoryName(String smallCategoryName) {
	this.smallCategoryName = smallCategoryName;
    }

    @Column(name = "small_category_name", nullable = true, length = 200)    
    public String getSmallCategoryName() {
	return smallCategoryName;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    @Column(name = "item_id", nullable = false, length = 11)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "item_code", nullable = true, length = 30)
    public String getItemCode() {
	return itemCode;
    }

    public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
    }

    @Column(name = "item_description", nullable = true, length = 240)    
    public String getItemDescription() {
	return itemDescription;
    }

    public void setInstId(Integer instId) {
	this.instId = instId;
    }

    @Column(name = "inst_id", nullable = true, length = 11)    
    public Integer getInstId() {
	return instId;
    }

    public void setStartDateActive(Date startDateActive) {
	this.startDateActive = startDateActive;
    }

    @Column(name = "start_date_active", nullable = false, length = 0)    
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

    public void setStatus(String status) {
	this.status = status;
    }

    @Column(name = "status", nullable = false, length = 30)    
    public String getStatus() {
	return status;
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

	@Override
	public String toString() {
		return "SrmPoSupplyProportionHEntity_HI [supplyId=" + supplyId
				+ ", supplyType=" + supplyType + ", smallCategoryCode="
				+ smallCategoryCode + ", smallCategoryName="
				+ smallCategoryName + ", itemCode=" + itemCode
				+ ", itemDescription=" + itemDescription + ", instId=" + instId
				+ ", startDateActive=" + startDateActive + ", endDateActive="
				+ endDateActive + ", description=" + description + ", status="
				+ status + ", versionNum=" + versionNum + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", attributeCategory=" + attributeCategory + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5="
				+ attribute5 + ", attribute6=" + attribute6 + ", attribute7="
				+ attribute7 + ", attribute8=" + attribute8 + ", attribute9="
				+ attribute9 + ", attribute10=" + attribute10 + "]";
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrmPoSupplyProportionHEntity_HI that = (SrmPoSupplyProportionHEntity_HI) o;
        if(null==that.supplyType) return that.supplyType==supplyType;
        if (!supplyType.equals(that.supplyType)) return false;
        if(null==that.smallCategoryCode) return that.smallCategoryCode==smallCategoryCode;
        if (!smallCategoryCode.equals(that.smallCategoryCode)) return false;
        if(null==that.itemCode) return that.itemCode==itemCode;
        if (!itemCode.equals(that.itemCode)) return false;
        if(null==that.instId) return that.instId==instId;
        if (!instId.equals(that.instId)) return false;
        if(null==that.startDateActive) return that.startDateActive==startDateActive;
        if (!startDateActive.equals(that.startDateActive)) return false;
        if(null==that.endDateActive) return that.endDateActive==endDateActive;
        if (!endDateActive.equals(that.endDateActive)) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result = supplyType.hashCode();
        result = 31 * result + smallCategoryCode.hashCode();
        result = 31 * result + itemCode.hashCode();
        result = 31 * result + instId.hashCode();
        result = 31 * result + startDateActive.hashCode();
        result = 31 * result + endDateActive.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}

