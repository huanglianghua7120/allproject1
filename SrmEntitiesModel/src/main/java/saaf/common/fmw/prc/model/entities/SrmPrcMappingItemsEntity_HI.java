package saaf.common.fmw.prc.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPrcMappingItemsEntity_HI Entity Object
 * Mon Feb 05 16:27:46 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_prc_mapping_items")
public class SrmPrcMappingItemsEntity_HI {
    private Integer mappingItemId;
    private Integer mappingHeaderId;
    private Integer itemId;
    private String itemNumber;
    private String itemDescription;
    private String unitOfMeasure;
    private BigDecimal lengthValue;
    private BigDecimal widthValue;
    private BigDecimal heightValue;
    private BigDecimal itemPrice;
    private String importU9Flag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date importU9Date;
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
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setMappingItemId(Integer mappingItemId) {
	this.mappingItemId = mappingItemId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "mapping_item_id", nullable = false, length = 11)    
    public Integer getMappingItemId() {
	return mappingItemId;
    }

    public void setMappingHeaderId(Integer mappingHeaderId) {
	this.mappingHeaderId = mappingHeaderId;
    }

    @Column(name = "mapping_header_id", nullable = false, length = 11)    
    public Integer getMappingHeaderId() {
	return mappingHeaderId;
    }

    public void setItemId(Integer itemId) {
	this.itemId = itemId;
    }

    @Column(name = "item_id", nullable = true, length = 11)    
    public Integer getItemId() {
	return itemId;
    }

    public void setItemNumber(String itemNumber) {
	this.itemNumber = itemNumber;
    }

    @Column(name = "item_number", nullable = true, length = 50)    
    public String getItemNumber() {
	return itemNumber;
    }

    public void setItemDescription(String itemDescription) {
	this.itemDescription = itemDescription;
    }

    @Column(name = "item_description", nullable = true, length = 240)    
    public String getItemDescription() {
	return itemDescription;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
	this.unitOfMeasure = unitOfMeasure;
    }

    @Column(name = "unit_of_measure", nullable = true, length = 150)    
    public String getUnitOfMeasure() {
	return unitOfMeasure;
    }

    public void setLengthValue(BigDecimal lengthValue) {
	this.lengthValue = lengthValue;
    }

    @Column(name = "length_value", precision = 22, scale = 2)    
    public BigDecimal getLengthValue() {
	return lengthValue;
    }

    public void setWidthValue(BigDecimal widthValue) {
	this.widthValue = widthValue;
    }

    @Column(name = "width_value", precision = 22, scale = 2)    
    public BigDecimal getWidthValue() {
	return widthValue;
    }

    public void setHeightValue(BigDecimal heightValue) {
	this.heightValue = heightValue;
    }

    @Column(name = "height_value", precision = 22, scale = 2)    
    public BigDecimal getHeightValue() {
	return heightValue;
    }

    public void setItemPrice(BigDecimal itemPrice) {
	this.itemPrice = itemPrice;
    }

    @Column(name = "item_price", precision = 22, scale = 6)    
    public BigDecimal getItemPrice() {
	return itemPrice;
    }

    public void setImportU9Flag(String importU9Flag) {
	this.importU9Flag = importU9Flag;
    }

    @Column(name = "import_u9_flag", nullable = true, length = 30)    
    public String getImportU9Flag() {
	return importU9Flag;
    }

    public void setImportU9Date(Date importU9Date) {
	this.importU9Date = importU9Date;
    }

    @Column(name = "import_u9_date", nullable = true, length = 0)    
    public Date getImportU9Date() {
	return importU9Date;
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

    @Version
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrmPrcMappingItemsEntity_HI that = (SrmPrcMappingItemsEntity_HI) o;

        if (!itemId.equals(that.itemId)) return false;
        return itemNumber.equals(that.itemNumber);
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + itemNumber.hashCode();
        return result;
    }
}

