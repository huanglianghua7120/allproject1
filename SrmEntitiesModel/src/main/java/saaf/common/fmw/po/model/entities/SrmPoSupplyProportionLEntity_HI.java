package saaf.common.fmw.po.model.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoSupplyProportionLEntity_HI Entity Object
 * Wed Nov 15 10:57:38 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_po_supply_proportion_l")
public class SrmPoSupplyProportionLEntity_HI {
    private Integer supplyDetailId;
    private Integer supplyId;
    private Integer supplierId;
    private String supplierNmuber;
    private String supplierName;
    private BigDecimal proportion;
    private String enableFlag;
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

	@Override
	public String toString() {
		return "SrmPoSupplyProportionLEntity_HI [supplyDetailId="
				+ supplyDetailId + ", supplyId=" + supplyId + ", supplierId="
				+ supplierId + ", supplierNmuber=" + supplierNmuber
				+ ", supplierName=" + supplierName + ", proportion="
				+ proportion + ", enableFlag=" + enableFlag + ", versionNum="
				+ versionNum + ", creationDate=" + creationDate
				+ ", createdBy=" + createdBy + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateLogin=" + lastUpdateLogin
				+ ", attributeCategory=" + attributeCategory + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5="
				+ attribute5 + ", attribute6=" + attribute6 + ", attribute7="
				+ attribute7 + ", attribute8=" + attribute8 + ", attribute9="
				+ attribute9 + ", attribute10=" + attribute10
				+ ", operatorUserId=" + operatorUserId + "]";
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
    public void setSupplyDetailId(Integer supplyDetailId) {
	this.supplyDetailId = supplyDetailId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_SUPPLY_PROPORTION_L_S", sequenceName = "SRM_PO_SUPPLY_PROPORTION_L_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_SUPPLY_PROPORTION_L_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "supply_detail_id", nullable = false, length = 11)    
    public Integer getSupplyDetailId() {
	return supplyDetailId;
    }

    public void setSupplyId(Integer supplyId) {
	this.supplyId = supplyId;
    }

    @Column(name = "supply_id", nullable = false, length = 11)    
    public Integer getSupplyId() {
	return supplyId;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = false, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setSupplierNmuber(String supplierNmuber) {
	this.supplierNmuber = supplierNmuber;
    }

    @Column(name = "supplier_nmuber", nullable = false, length = 50)    
    public String getSupplierNmuber() {
	return supplierNmuber;
    }

    public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
    }

    @Column(name = "supplier_name", nullable = true, length = 200)    
    public String getSupplierName() {
	return supplierName;
    }

    public void setProportion(BigDecimal proportion) {
	this.proportion = proportion;
    }

    @Column(name = "proportion", precision = 10, scale = 0)    
    public BigDecimal getProportion() {
	return proportion;
    }

    public void setEnableFlag(String enableFlag) {
	this.enableFlag = enableFlag;
    }

    @Column(name = "enable_flag", nullable = true, length = 10)    
    public String getEnableFlag() {
	return enableFlag;
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
}

