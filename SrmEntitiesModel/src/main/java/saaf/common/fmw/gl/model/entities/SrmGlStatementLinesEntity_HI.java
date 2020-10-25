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
 * SrmGlStatementLinesEntity_HI Entity Object
 * Thu Mar 01 18:09:43 CST 2018  Auto Generate
 */
@Entity
@Table(name = "SRM_GL_STATEMENT_LINES")
public class SrmGlStatementLinesEntity_HI {
    private Integer statementLineId;
    private Integer statementId;
    private Integer itemId;
    private Integer categoryId;
    private String unitOfMeasure;
    private Integer poHeaderId;
    private String poNumber;
    private Integer deliveryHeaderId;
    private String deliveryNumber;
    private Integer receptId;
    private String receptNumber;
    private Integer receptLineId;
    private BigDecimal quantity;
    private BigDecimal oldBuyerPrice;
    private BigDecimal buyerPrice;
    private BigDecimal supplierPrice;
    private BigDecimal finallyPrice;
    private String priceRange;
    private String processMode;
    private BigDecimal percentageOfDeduction;
    private BigDecimal minimunOfDeduction;
    private BigDecimal proportionOfDeduction;
    private BigDecimal finalDeductedAmount;
    private String isPricingFlag;
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

    public void setStatementLineId(Integer statementLineId) {
        this.statementLineId = statementLineId;
    }

    @Id
    @GeneratedValue
    @Column(name = "statement_line_id", nullable = false, length = 11)
    public Integer getStatementLineId() {
        return statementLineId;
    }

    public void setStatementId(Integer statementId) {
        this.statementId = statementId;
    }

    @Column(name = "statement_id", nullable = true, length = 11)
    public Integer getStatementId() {
        return statementId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "item_id", nullable = true, length = 11)
    public Integer getItemId() {
        return itemId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "category_id", nullable = true, length = 11)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Column(name = "unit_of_measure", nullable = true, length = 150)
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    @Column(name = "po_header_id", nullable = true, length = 11)
    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @Column(name = "po_number", nullable = true, length = 100)
    public String getPoNumber() {
        return poNumber;
    }

    public void setDeliveryHeaderId(Integer deliveryHeaderId) {
        this.deliveryHeaderId = deliveryHeaderId;
    }

    @Column(name = "delivery_header_id", nullable = true, length = 11)
    public Integer getDeliveryHeaderId() {
        return deliveryHeaderId;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    @Column(name = "delivery_number", nullable = true, length = 100)
    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setReceptId(Integer receptId) {
        this.receptId = receptId;
    }

    @Column(name = "recept_id", nullable = true, length = 11)
    public Integer getReceptId() {
        return receptId;
    }

    public void setReceptNumber(String receptNumber) {
        this.receptNumber = receptNumber;
    }

    @Column(name = "recept_number", nullable = true, length = 100)
    public String getReceptNumber() {
        return receptNumber;
    }

    public void setReceptLineId(Integer receptLineId) {
        this.receptLineId = receptLineId;
    }

    @Column(name = "recept_line_id", nullable = true, length = 11)
    public Integer getReceptLineId() {
        return receptLineId;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity", precision = 22, scale = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setOldBuyerPrice(BigDecimal oldBuyerPrice) {
        this.oldBuyerPrice = oldBuyerPrice;
    }

    @Column(name = "old_buyer_price", precision = 22, scale = 2)
    public BigDecimal getOldBuyerPrice() {
        return oldBuyerPrice;
    }

    public void setBuyerPrice(BigDecimal buyerPrice) {
        this.buyerPrice = buyerPrice;
    }

    @Column(name = "buyer_price", precision = 22, scale = 2)
    public BigDecimal getBuyerPrice() {
        return buyerPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    @Column(name = "supplier_price", precision = 22, scale = 2)
    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setFinallyPrice(BigDecimal finallyPrice) {
        this.finallyPrice = finallyPrice;
    }

    @Column(name = "finally_price", precision = 22, scale = 2)
    public BigDecimal getFinallyPrice() {
        return finallyPrice;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    @Column(name = "price_range", nullable = true, length = 240)
    public String getPriceRange() {
        return priceRange;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    @Column(name = "process_mode", nullable = true, length = 240)
    public String getProcessMode() {
        return processMode;
    }

    public void setPercentageOfDeduction(BigDecimal percentageOfDeduction) {
        this.percentageOfDeduction = percentageOfDeduction;
    }

    @Column(name = "percentage_of_deduction", precision = 22, scale = 2)
    public BigDecimal getPercentageOfDeduction() {
        return percentageOfDeduction;
    }

    public void setMinimunOfDeduction(BigDecimal minimunOfDeduction) {
        this.minimunOfDeduction = minimunOfDeduction;
    }

    @Column(name = "minimun_of_deduction", precision = 22, scale = 2)
    public BigDecimal getMinimunOfDeduction() {
        return minimunOfDeduction;
    }

    public void setProportionOfDeduction(BigDecimal proportionOfDeduction) {
        this.proportionOfDeduction = proportionOfDeduction;
    }

    @Column(name = "proportion_of_deduction", precision = 22, scale = 2)
    public BigDecimal getProportionOfDeduction() {
        return proportionOfDeduction;
    }

    public void setFinalDeductedAmount(BigDecimal finalDeductedAmount) {
        this.finalDeductedAmount = finalDeductedAmount;
    }

    @Column(name = "final_deducted_amount", precision = 22, scale = 2)
    public BigDecimal getFinalDeductedAmount() {
        return finalDeductedAmount;
    }

    public void setIsPricingFlag(String isPricingFlag) {
        this.isPricingFlag = isPricingFlag;
    }

    @Column(name = "is_pricing_flag", nullable = true, length = 30)
    public String getIsPricingFlag() {
        return isPricingFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
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

