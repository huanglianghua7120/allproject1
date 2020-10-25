package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafDbConfigEntity_HI Entity Object
 * Wed Dec 20 14:21:38 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SAAF_DB_CONFIG")
public class SaafDbConfigEntity_HI {
    private Integer id;
    private String sys;
    private String ip;
    private String dbname;
    private String loginuser;
    private String pwd;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setId(Integer id) {
	this.id = id;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "id", nullable = false, length = 11)    
    public Integer getId() {
	return id;
    }

    public void setSys(String sys) {
	this.sys = sys;
    }

    @Column(name = "sys", nullable = true, length = 50)    
    public String getSys() {
	return sys;
    }

    public void setIp(String ip) {
	this.ip = ip;
    }

    @Column(name = "ip", nullable = true, length = 50)    
    public String getIp() {
	return ip;
    }

    public void setDbname(String dbname) {
	this.dbname = dbname;
    }

    @Column(name = "dbname", nullable = true, length = 50)    
    public String getDbname() {
	return dbname;
    }

    public void setLoginuser(String loginuser) {
	this.loginuser = loginuser;
    }

    @Column(name = "loginuser", nullable = true, length = 50)    
    public String getLoginuser() {
	return loginuser;
    }

    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    @Column(name = "pwd", nullable = true, length = 50)    
    public String getPwd() {
	return pwd;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    @Column(name = "creation_date", nullable = true, length = 0)    
    public Date getCreationDate() {
	return creationDate;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = true, length = 11)    
    public Integer getLastUpdatedBy() {
	return lastUpdatedBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = true, length = 0)    
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

