package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafUserRespEntity_HI Entity Object
 * Thu Apr 20 11:13:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_user_resp")
public class SaafUserRespEntity_HI {
    private Integer userRespId;
    private Integer userId;
    private String userRespName;
    private Integer responsibilityId;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;
    private Integer operatorUserId;
    public void setUserRespId(Integer userRespId) {
        this.userRespId = userRespId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_USER_RESP_S", sequenceName = "SAAF_USER_RESP_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_USER_RESP_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_resp_id", precision = 22, scale = 0)
    public Integer getUserRespId() {
        return userRespId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Column(name = "user_id", precision = 22, scale = 0)
    public Integer getUserId() {
        return userId;
    }

    public void setUserRespName(String userRespName) {
        this.userRespName = userRespName;
    }

    @Column(name = "user_resp_name", nullable = true, length = 100)
    public String getUserRespName() {
        return userRespName;
    }

    public void setResponsibilityId(Integer responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    @Column(name = "responsibility_id", precision = 22, scale = 0)
    public Integer getResponsibilityId() {
        return responsibilityId;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = false, length = 30)
    public String getPlatformCode() {
        return platformCode;
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
    @Column(name = "version_num", precision = 22, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

