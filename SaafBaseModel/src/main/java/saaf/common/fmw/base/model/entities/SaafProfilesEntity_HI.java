package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * SaafProfilesEntity_HI Entity Object
 * Thu Apr 20 11:13:29 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_profiles")
public class SaafProfilesEntity_HI {
    private Integer profileId;
    private String profileName;
    private String profileNumber;
    private String profileType;
    private String description;
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
    private String profileValue;
    private Integer versionNum;
    private Integer operatorUserId;

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Id
    @GeneratedValue
    @Column(name = "profile_id", precision = 22, scale = 0)
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    @Column(name = "profile_name", nullable = false, length = 240)
    public String getProfileName() {
        return profileName;
    }

    public void setProfileNumber(String profileNumber) {
        this.profileNumber = profileNumber;
    }

    @Column(name = "profile_number", nullable = false, length = 30)
    public String getProfileNumber() {
        return profileNumber;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    @Column(name = "profile_type", nullable = true, length = 30)
    public String getProfileType() {
        return profileType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
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

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    @Column(name = "profile_value", nullable = true, length = 200)
    public String getProfileValue() {
        return profileValue;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", precision = 11, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

