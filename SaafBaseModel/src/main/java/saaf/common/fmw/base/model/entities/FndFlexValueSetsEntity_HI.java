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
 * FndFlexValueSetsEntity_HI Entity Object
 * Fri Aug 04 15:48:43 CST 2017  Auto Generate
 */
@Entity
@Table(name = "FND_FLEX_VALUE_SETS")
public class FndFlexValueSetsEntity_HI {
    private Integer flexValueSetId;
    private String flexValueSetName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdateLogin;
    private String validationType;
    private String protectedFlag;
    private String securityEnabledFlag;
    private String longlistFlag;
    private String formatType;
    private Integer maximumSize;
    private String alphanumericAllowedFlag;
    private String uppercaseOnlyFlag;
    private String numericModeEnabledFlag;
    private String description;
    private String dependantDefaultValue;
    private String dependantDefaultMeaning;
    private Integer parentFlexValueSetId;
    private String minimumValue;
    private String maximumValue;
    private Integer numberPrecision;
    private Integer operatorUserId;

    public void setFlexValueSetId(Integer flexValueSetId) {
        this.flexValueSetId = flexValueSetId;
    }

    @Id
    @GeneratedValue
    @Column(name = "flex_value_set_id", nullable = false, length = 10)
    public Integer getFlexValueSetId() {
        return flexValueSetId;
    }

    public void setFlexValueSetName(String flexValueSetName) {
        this.flexValueSetName = flexValueSetName;
    }

    @Column(name = "flex_value_set_name", nullable = false, length = 60)
    public String getFlexValueSetName() {
        return flexValueSetName;
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

    @Column(name = "last_updated_by", nullable = false, length = 15)
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

    @Column(name = "created_by", nullable = false, length = 15)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", nullable = true, length = 15)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    @Column(name = "validation_type", nullable = false, length = 1)
    public String getValidationType() {
        return validationType;
    }

    public void setProtectedFlag(String protectedFlag) {
        this.protectedFlag = protectedFlag;
    }

    @Column(name = "protected_flag", nullable = true, length = 1)
    public String getProtectedFlag() {
        return protectedFlag;
    }

    public void setSecurityEnabledFlag(String securityEnabledFlag) {
        this.securityEnabledFlag = securityEnabledFlag;
    }

    @Column(name = "security_enabled_flag", nullable = true, length = 1)
    public String getSecurityEnabledFlag() {
        return securityEnabledFlag;
    }

    public void setLonglistFlag(String longlistFlag) {
        this.longlistFlag = longlistFlag;
    }

    @Column(name = "longlist_flag", nullable = true, length = 1)
    public String getLonglistFlag() {
        return longlistFlag;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Column(name = "format_type", nullable = false, length = 1)
    public String getFormatType() {
        return formatType;
    }

    public void setMaximumSize(Integer maximumSize) {
        this.maximumSize = maximumSize;
    }

    @Column(name = "maximum_size", nullable = true, length = 3)
    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setAlphanumericAllowedFlag(String alphanumericAllowedFlag) {
        this.alphanumericAllowedFlag = alphanumericAllowedFlag;
    }

    @Column(name = "alphanumeric_allowed_flag", nullable = true, length = 1)
    public String getAlphanumericAllowedFlag() {
        return alphanumericAllowedFlag;
    }

    public void setUppercaseOnlyFlag(String uppercaseOnlyFlag) {
        this.uppercaseOnlyFlag = uppercaseOnlyFlag;
    }

    @Column(name = "uppercase_only_flag", nullable = true, length = 1)
    public String getUppercaseOnlyFlag() {
        return uppercaseOnlyFlag;
    }

    public void setNumericModeEnabledFlag(String numericModeEnabledFlag) {
        this.numericModeEnabledFlag = numericModeEnabledFlag;
    }

    @Column(name = "numeric_mode_enabled_flag", nullable = true, length = 1)
    public String getNumericModeEnabledFlag() {
        return numericModeEnabledFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }

    public void setDependantDefaultValue(String dependantDefaultValue) {
        this.dependantDefaultValue = dependantDefaultValue;
    }

    @Column(name = "dependant_default_value", nullable = true, length = 60)
    public String getDependantDefaultValue() {
        return dependantDefaultValue;
    }

    public void setDependantDefaultMeaning(String dependantDefaultMeaning) {
        this.dependantDefaultMeaning = dependantDefaultMeaning;
    }

    @Column(name = "dependant_default_meaning", nullable = true, length = 240)
    public String getDependantDefaultMeaning() {
        return dependantDefaultMeaning;
    }

    public void setParentFlexValueSetId(Integer parentFlexValueSetId) {
        this.parentFlexValueSetId = parentFlexValueSetId;
    }

    @Column(name = "parent_flex_value_set_id", nullable = true, length = 10)
    public Integer getParentFlexValueSetId() {
        return parentFlexValueSetId;
    }

    public void setMinimumValue(String minimumValue) {
        this.minimumValue = minimumValue;
    }

    @Column(name = "minimum_value", nullable = true, length = 150)
    public String getMinimumValue() {
        return minimumValue;
    }

    public void setMaximumValue(String maximumValue) {
        this.maximumValue = maximumValue;
    }

    @Column(name = "maximum_value", nullable = true, length = 150)
    public String getMaximumValue() {
        return maximumValue;
    }

    public void setNumberPrecision(Integer numberPrecision) {
        this.numberPrecision = numberPrecision;
    }

    @Column(name = "number_precision", nullable = true, length = 2)
    public Integer getNumberPrecision() {
        return numberPrecision;
    }
    
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

