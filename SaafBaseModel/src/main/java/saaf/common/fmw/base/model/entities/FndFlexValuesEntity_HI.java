package saaf.common.fmw.base.model.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;


/**
 * FndFlexValuesEntity_HI Entity Object
 * Fri Aug 04 15:48:43 CST 2017  Auto Generate
 */
@Entity
@Table(name = "FND_FLEX_VALUES")
public class FndFlexValuesEntity_HI {
    private Integer flexValueSetId;
    private Integer flexValueId;
    private String flexValue;
    private String flexValueMeaning;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdateLogin;
    private String enabledFlag;
    private String summaryFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String parentFlexValueLow;
    private String parentFlexValueHigh;
    private Integer structuredHierarchyLevel;
    private String hierarchyLevel;
    private String compiledValueAttributes;
    private String valueCategory;
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
    private Integer attributeSortOrder;
    private Integer operatorUserId;

    public void setFlexValueSetId(Integer flexValueSetId) {
        this.flexValueSetId = flexValueSetId;
    }

    @Column(name = "flex_value_set_id", nullable = false, length = 11)
    public Integer getFlexValueSetId() {
        return flexValueSetId;
    }

    public void setFlexValueId(Integer flexValueId) {
        this.flexValueId = flexValueId;
    }

    @Id
    @GeneratedValue
    @Column(name = "flex_value_id", nullable = false, length = 15)
    public Integer getFlexValueId() {
        return flexValueId;
    }

    public void setFlexValue(String flexValue) {
        this.flexValue = flexValue;
    }

    @Column(name = "flex_value", nullable = false, length = 150)
    public String getFlexValue() {
        return flexValue;
    }

    public void setFlexValueMeaning(String flexValueMeaning) {
        this.flexValueMeaning = flexValueMeaning;
    }

    @Column(name = "flex_value_meaning", nullable = true, length = 150)
    public String getFlexValueMeaning() {
        return flexValueMeaning;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = false, length = 0)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = false, length = 11)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
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

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", nullable = true, length = 11)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Column(name = "enabled_flag", nullable = false, length = 1)
    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setSummaryFlag(String summaryFlag) {
        this.summaryFlag = summaryFlag;
    }

    @Column(name = "summary_flag", nullable = true, length = 1)
    public String getSummaryFlag() {
        return summaryFlag;
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

    public void setParentFlexValueLow(String parentFlexValueLow) {
        this.parentFlexValueLow = parentFlexValueLow;
    }

    @Column(name = "parent_flex_value_low", nullable = true, length = 60)
    public String getParentFlexValueLow() {
        return parentFlexValueLow;
    }

    public void setParentFlexValueHigh(String parentFlexValueHigh) {
        this.parentFlexValueHigh = parentFlexValueHigh;
    }

    @Column(name = "parent_flex_value_high", nullable = true, length = 60)
    public String getParentFlexValueHigh() {
        return parentFlexValueHigh;
    }

    public void setStructuredHierarchyLevel(Integer structuredHierarchyLevel) {
        this.structuredHierarchyLevel = structuredHierarchyLevel;
    }

    @Column(name = "structured_hierarchy_level", nullable = true, length = 11)
    public Integer getStructuredHierarchyLevel() {
        return structuredHierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    @Column(name = "hierarchy_level", nullable = true, length = 30)
    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setCompiledValueAttributes(String compiledValueAttributes) {
        this.compiledValueAttributes = compiledValueAttributes;
    }

    @Column(name = "compiled_value_attributes", nullable = true, length = 0)
    public String getCompiledValueAttributes() {
        return compiledValueAttributes;
    }

    public void setValueCategory(String valueCategory) {
        this.valueCategory = valueCategory;
    }

    @Column(name = "value_category", nullable = true, length = 30)
    public String getValueCategory() {
        return valueCategory;
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

    public void setAttributeSortOrder(Integer attributeSortOrder) {
        this.attributeSortOrder = attributeSortOrder;
    }

    @Column(name = "attribute_sort_order", nullable = true, length = 15)
    public Integer getAttributeSortOrder() {
        return attributeSortOrder;
    }
    
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

