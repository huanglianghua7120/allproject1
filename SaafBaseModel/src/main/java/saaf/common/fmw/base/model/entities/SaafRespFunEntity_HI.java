package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafRespFunEntity_HI Entity Object
 * Thu Apr 20 11:13:30 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_resp_fun")
public class SaafRespFunEntity_HI {
    private Integer respFunId;
    private Integer respId;
    private Integer menuId;
    private Integer funButtonId;
    private String ifOperation;
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

    public void setRespFunId(Integer respFunId) {
        this.respFunId = respFunId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_RESP_FUN_S", sequenceName = "SAAF_RESP_FUN_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_RESP_FUN_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "resp_fun_id", precision = 22, scale = 0)
    public Integer getRespFunId() {
        return respFunId;
    }

    public void setRespId(Integer respId) {
        this.respId = respId;
    }

    @Column(name = "resp_id", precision = 22, scale = 0)
    public Integer getRespId() {
        return respId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Column(name = "menu_id", precision = 22, scale = 0)
    public Integer getMenuId() {
        return menuId;
    }

    public void setFunButtonId(Integer funButtonId) {
        this.funButtonId = funButtonId;
    }

    @Column(name = "fun_button_id", precision = 22, scale = 0)
    public Integer getFunButtonId() {
        return funButtonId;
    }

    public void setIfOperation(String ifOperation) {
        this.ifOperation = ifOperation;
    }

    @Column(name = "if_operation", nullable = true, length = 10)
    public String getIfOperation() {
        return ifOperation;
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

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

