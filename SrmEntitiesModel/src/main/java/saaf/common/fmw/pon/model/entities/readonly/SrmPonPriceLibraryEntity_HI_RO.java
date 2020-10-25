package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonPriceLibraryEntity_HI_RO Entity Object
 * Fri Apr 03 17:52:37 CST 2020  Auto Generate
 */

public class SrmPonPriceLibraryEntity_HI_RO {

	public static final String CHECK_PRICE_LIBRARY_SQL = "select t.price_library_id,\n" +
			"       t.price_library_number,\n" +
			"       t.org_id,\n" +
			"       t.supplier_id,\n" +
			"       t.buyer_id,\n" +
			"       t.item_id,\n" +
			"       t.item_name,\n" +
			"       t.item_spec,\n" +
			"       t.category_id,\n" +
			"       t.tax_rate_code,\n" +
			"       t.currency_code,\n" +
			"       t.payment_condition,\n" +
			"       t.tax_price,\n" +
			"       t.non_tax_price,\n" +
			"       t.price_library_status,\n" +
			"       t.price_effective_date,\n" +
			"       t.price_expiration_date,\n" +
			"       t.remarks,\n" +
			"       t.price_library_version,\n" +
			"       t.source_number,\n" +
			"       t.source_code,\n" +
			"       t.source_id,\n" +
            "       t.materials_price,\n" +
            "       t.artificial_price\n" +
			"  from srm_pon_price_library t\n" +
			" where 1 = 1\n";

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

    public BigDecimal getMaterialsPrice() {
        return materialsPrice;
    }

    public void setMaterialsPrice(BigDecimal materialsPrice) {
        this.materialsPrice = materialsPrice;
    }

    public BigDecimal getArtificialPrice() {
        return artificialPrice;
    }

    public void setArtificialPrice(BigDecimal artificialPrice) {
        this.artificialPrice = artificialPrice;
    }

    public void setPriceLibraryId(Integer priceLibraryId) {
		this.priceLibraryId = priceLibraryId;
	}

	
	public Integer getPriceLibraryId() {
		return priceLibraryId;
	}

	public void setPriceLibraryNumber(String priceLibraryNumber) {
		this.priceLibraryNumber = priceLibraryNumber;
	}

	
	public String getPriceLibraryNumber() {
		return priceLibraryNumber;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	
	public String getItemSpec() {
		return itemSpec;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	
	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setPriceLibraryStatus(String priceLibraryStatus) {
		this.priceLibraryStatus = priceLibraryStatus;
	}

	
	public String getPriceLibraryStatus() {
		return priceLibraryStatus;
	}

	public void setPriceEffectiveDate(Date priceEffectiveDate) {
		this.priceEffectiveDate = priceEffectiveDate;
	}

	
	public Date getPriceEffectiveDate() {
		return priceEffectiveDate;
	}

	public void setPriceExpirationDate(Date priceExpirationDate) {
		this.priceExpirationDate = priceExpirationDate;
	}

	
	public Date getPriceExpirationDate() {
		return priceExpirationDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getRemarks() {
		return remarks;
	}

	public void setPriceLibraryVersion(Integer priceLibraryVersion) {
		this.priceLibraryVersion = priceLibraryVersion;
	}

	
	public Integer getPriceLibraryVersion() {
		return priceLibraryVersion;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	
	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
