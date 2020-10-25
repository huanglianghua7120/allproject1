package saaf.common.fmw.common.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ExtAccessMessageEntity_HI Entity Object
 * Wed Oct 05 10:58:59 CST 2016  Auto Generate
 */
@Entity
@Table(name = "ext_access_message")
public class ExtAccessMessageEntity_HI {
    private Integer extIntId;
    private String interfaceCode;
    private String interfaceName;
    private String interfaceType;
    private String enableFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String serverUrl;

    public void setExtIntId(Integer extIntId) {
        this.extIntId = extIntId;
    }

    @Id
    @GeneratedValue
    @Column(name = "ext_int_id", nullable = false, length = 11)
    public Integer getExtIntId() {
        return extIntId;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    @Column(name = "interface_code", nullable = true, length = 240)
    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Column(name = "interface_name", nullable = true, length = 500)
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Column(name = "interface_type", nullable = true, length = 240)
    public String getInterfaceType() {
        return interfaceType;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Column(name = "enable_flag", nullable = true, length = 10)
    public String getEnableFlag() {
        return enableFlag;
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

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Column(name = "server_url", nullable = true, length = 240)
    public String getServerUrl() {
        return serverUrl;
    }
}

