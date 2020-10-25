package saaf.common.fmw.common.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ExtLoginAccessEntity_HI Entity Object
 * Mon Sep 26 16:44:35 CST 2016  Auto Generate
 */
@Entity
@Table(name = "EXT_LOGIN_ACCESS")
public class ExtLoginAccessEntity_HI {
    private Integer accessId;
    private String accessTypeCode;
    private String accessTypeName;
    private String openId;
    private String clientId;
    private String nickName;
    private Integer memberId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    @Id
    @GeneratedValue
    @Column(name = "access_id", nullable = false, length = 11)
    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessTypeCode(String accessTypeCode) {
        this.accessTypeCode = accessTypeCode;
    }

    @Column(name = "access_type_code", nullable = true, length = 240)
    public String getAccessTypeCode() {
        return accessTypeCode;
    }

    public void setAccessTypeName(String accessTypeName) {
        this.accessTypeName = accessTypeName;
    }

    @Column(name = "access_type_name", nullable = true, length = 240)
    public String getAccessTypeName() {
        return accessTypeName;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "open_id", nullable = true, length = 250)
    public String getOpenId() {
        return openId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Column(name = "client_id", nullable = true, length = 250)
    public String getClientId() {
        return clientId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "nick_name", nullable = true, length = 240)
    public String getNickName() {
        return nickName;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Column(name = "member_id", nullable = true, length = 11)
    public Integer getMemberId() {
        return memberId;
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
}

