package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafLoginsHisEntity_HI Entity Object
 * Thu Apr 20 11:13:21 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_logins_his")
public class SaafLoginsHisEntity_HI {
    private Integer loginsId;
    private Integer userId;
    private String loginName;
    private String loginIp;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    private String loginType;
    private String sessionId;
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer operatorUserId;

    public void setLoginsId(Integer loginsId) {
        this.loginsId = loginsId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_LOGINS_HIS_S", sequenceName = "SAAF_LOGINS_HIS_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_LOGINS_HIS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "logins_id", precision = 22, scale = 0)
    public Integer getLoginsId() {
        return loginsId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "user_id", precision = 22, scale = 0)
    public Integer getUserId() {
        return userId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "login_name", nullable = true, length = 100)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Column(name = "login_ip", nullable = true, length = 30)
    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "login_time", nullable = true, length = 0)
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Column(name = "login_type", nullable = true, length = 20)
    public String getLoginType() {
        return loginType;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "session_id", nullable = true, length = 100)
    public String getSessionId() {
        return sessionId;
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

