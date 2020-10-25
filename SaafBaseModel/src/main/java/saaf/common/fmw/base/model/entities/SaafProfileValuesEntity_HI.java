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
 * SaafProfileValuesEntity_HI Entity Object
 * Thu Apr 20 11:13:28 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_profile_values")
public class SaafProfileValuesEntity_HI {
    private Integer profileValueId;
    private Integer profileId;
    private Integer profileLevelId;
    private String profileLevel;
    private String profileValue;
    private String enabledFlag;
    private String description;
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

    public void setProfileValueId(Integer profileValueId) {
        this.profileValueId = profileValueId;
    }

    @Id
    @GeneratedValue
    @Column(name = "profile_value_id", precision = 22, scale = 0)
    public Integer getProfileValueId() {
        return profileValueId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    @Column(name = "profile_id", precision = 22, scale = 0)
    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileLevelId(Integer profileLevelId) {
        this.profileLevelId = profileLevelId;
    }

    @Column(name = "profile_level_id", precision = 22, scale = 0)
    public Integer getProfileLevelId() {
        return profileLevelId;
    }

    public void setProfileLevel(String profileLevel) {
        this.profileLevel = profileLevel;
    }

    @Column(name = "profile_level", nullable = true, length = 30)
    public String getProfileLevel() {
        return profileLevel;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    @Column(name = "profile_value", nullable = true, length = 240)
    public String getProfileValue() {
        return profileValue;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Column(name = "enabled_flag", nullable = true, length = 1)
    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
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

