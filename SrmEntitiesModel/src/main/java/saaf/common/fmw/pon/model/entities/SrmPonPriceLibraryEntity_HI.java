package saaf.common.fmw.pon.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonPriceLibraryEntity_HI Entity Object
 * Fri Apr 03 17:52:37 CST 2020  Auto Generate
 */
@Entity
@Table(name="SRM_PON_PRICE_LIBRARY")
public class SrmPonPriceLibraryEntity_HI {
    private Integer priceLibraryId;
    private String priceLibraryNumber;
    private Integer orgId;
    private Integer supplierId;
    private Integer buyerId;
    private Integer itemId;
    private String itemName;
    private String itemSpec;
    private Integer categoryId;
    private String taxRateCode;
    private String currencyCode;
    private String paymentCondition;
    private BigDecimal taxPrice;
    private BigDecimal nonTaxPrice;
    private String priceLibraryStatus;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date priceEffectiveDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date priceExpirationDate;
    private String remarks;
    private Integer priceLibraryVersion;
    private String sourceNumber;
    private String sourceCode;
    private String sourceId;
    private Integer versionNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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
    private BigDecimal materialsPrice;//材料单价
    private BigDecimal artificialPrice;//人工单价
    private Integer organizationId;

	public void setPriceLibraryId(Integer priceLibraryId) {
		this.priceLibraryId = priceLibraryId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_PRICE_LIBRARY_S", sequenceName = "SRM_PON_PRICE_LIBRARY_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_PRICE_LIBRARY_S", strategy = GenerationType.SEQUENCE)
	@Column(name="price_library_id", nullable=false, length=22)	
	public Integer getPriceLibraryId() {
		return priceLibraryId;
	}

	public void setPriceLibraryNumber(String priceLibraryNumber) {
		this.priceLibraryNumber = priceLibraryNumber;
	}

	@Column(name="price_library_number", nullable=true, length=240)	
	public String getPriceLibraryNumber() {
		return priceLibraryNumber;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name="org_id", nullable=true, length=22)	
	public Integer getOrgId() {
		return orgId;
	}

    @Column(name="organization_id", nullable=true, length=10)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name="supplier_id", nullable=true, length=22)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name="buyer_id", nullable=true, length=22)	
	public Integer getBuyerId() {
		return buyerId;
	}


    @Column(name = "materials_price", nullable = true, length = 15)
    public BigDecimal getMaterialsPrice() {
        return materialsPrice;
    }

    public void setMaterialsPrice(BigDecimal materialsPrice) {
        this.materialsPrice = materialsPrice;
    }

    @Column(name = "artificial_price", nullable = true, length = 15)
    public BigDecimal getArtificialPrice() {
        return artificialPrice;
    }

    public void setArtificialPrice(BigDecimal artificialPrice) {
        this.artificialPrice = artificialPrice;
    }


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name="item_id", nullable=true, length=22)	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name="item_name", nullable=true, length=240)	
	public String getItemName() {
		return itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	@Column(name="item_spec", nullable=true, length=240)	
	public String getItemSpec() {
		return itemSpec;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name="category_id", nullable=true, length=22)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	@Column(name="tax_rate_code", nullable=true, length=30)	
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name="currency_code", nullable=true, length=30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	@Column(name="payment_condition", nullable=true, length=100)	
	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name="tax_price", nullable=true, length=22)	
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	@Column(name="non_tax_price", nullable=true, length=22)	
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setPriceLibraryStatus(String priceLibraryStatus) {
		this.priceLibraryStatus = priceLibraryStatus;
	}

	@Column(name="price_library_status", nullable=true, length=30)	
	public String getPriceLibraryStatus() {
		return priceLibraryStatus;
	}

	public void setPriceEffectiveDate(Date priceEffectiveDate) {
		this.priceEffectiveDate = priceEffectiveDate;
	}

	@Column(name="price_effective_date", nullable=true, length=7)	
	public Date getPriceEffectiveDate() {
		return priceEffectiveDate;
	}

	public void setPriceExpirationDate(Date priceExpirationDate) {
		this.priceExpirationDate = priceExpirationDate;
	}

	@Column(name="price_expiration_date", nullable=true, length=7)	
	public Date getPriceExpirationDate() {
		return priceExpirationDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="remarks", nullable=true, length=2000)	
	public String getRemarks() {
		return remarks;
	}

	public void setPriceLibraryVersion(Integer priceLibraryVersion) {
		this.priceLibraryVersion = priceLibraryVersion;
	}

	@Column(name="price_library_version", nullable=true, length=22)	
	public Integer getPriceLibraryVersion() {
		return priceLibraryVersion;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	@Column(name="source_number", nullable=true, length=30)	
	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name="source_code", nullable=true, length=30)	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name="source_id", nullable=true, length=30)	
	public String getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name="version_num", nullable=true, length=22)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="creation_date", nullable=false, length=7)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_by", nullable=false, length=22)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="last_updated_by", nullable=false, length=22)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name="last_update_date", nullable=false, length=7)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name="last_update_login", nullable=true, length=22)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name="attribute_category", nullable=true, length=30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name="attribute1", nullable=true, length=240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name="attribute2", nullable=true, length=240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name="attribute3", nullable=true, length=240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name="attribute4", nullable=true, length=240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name="attribute5", nullable=true, length=240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name="attribute6", nullable=true, length=240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name="attribute7", nullable=true, length=240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name="attribute8", nullable=true, length=240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name="attribute9", nullable=true, length=240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name="attribute10", nullable=true, length=240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
