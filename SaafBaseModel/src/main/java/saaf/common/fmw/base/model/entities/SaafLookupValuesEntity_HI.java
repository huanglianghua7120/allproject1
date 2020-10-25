package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafLookupValuesEntity_HI Entity Object
 * Thu Apr 20 11:13:23 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_lookup_values")
public class SaafLookupValuesEntity_HI {
    private Integer lookupValuesId;
    private String lookupType;
    private String lookupCode;
    private String meaning;
    private String description;
    private String tag;
    private String enabledFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;
    private Integer operatorUserId;

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", precision = 11, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setLookupValuesId(Integer lookupValuesId) {
        this.lookupValuesId = lookupValuesId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_LOOKUP_VALUES_S", sequenceName = "SAAF_LOOKUP_VALUES_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_LOOKUP_VALUES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "lookup_values_id", precision = 22, scale = 0)
    public Integer getLookupValuesId() {
        return lookupValuesId;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    @Column(name = "lookup_type", nullable = false, length = 30)
    public String getLookupType() {
        return lookupType;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    @Column(name = "lookup_code", nullable = false, length = 30)
    public String getLookupCode() {
        return lookupCode;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Column(name = "meaning", nullable = false, length = 50)
    public String getMeaning() {
        return meaning;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Column(name = "tag", nullable = true, length = 30)
    public String getTag() {
        return tag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Column(name = "enabled_flag", nullable = true, length = 1)
    public String getEnabledFlag() {
        return enabledFlag;
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

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = true, length = 30)
    public String getPlatformCode() {
        return platformCode;
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

    @Column(name = "created_by", precision = 22, scale = 0)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", precision = 22, scale = 0)
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

    @Column(name = "last_update_login", precision = 22, scale = 0)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

