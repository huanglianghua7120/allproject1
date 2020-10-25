package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafUserAccessOrgsEntity_HI Entity Object
 * Thu Apr 20 11:13:33 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_user_access_orgs")
public class SaafUserAccessOrgsEntity_HI {
    private Integer accessOrgId;
    private Integer userId;
    private Integer instId;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    
    private Integer operatorUserId;

    public void setAccessOrgId(Integer accessOrgId) {
        this.accessOrgId = accessOrgId;
    }

    @Id
    @SequenceGenerator(name = "ISEQ$$_77889", sequenceName = "ISEQ$$_77889", allocationSize = 1)
    @GeneratedValue(generator = "ISEQ$$_77889", strategy = GenerationType.SEQUENCE)
    @Column(name = "access_org_id", nullable = false, length = 11)
    public Integer getAccessOrgId() {
        return accessOrgId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "user_id", precision = 22, scale = 0)
    public Integer getUserId() {
        return userId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    @Column(name = "inst_id", precision = 22, scale = 0)
    public Integer getInstId() {
        return instId;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = true, length = 30)
    public String getPlatformCode() {
        return platformCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "start_date", nullable = true, length = 0)
    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "end_date", nullable = true, length = 0)
    public Date getEndDate() {
        return endDate;
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

