package saaf.common.fmw.rule.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * PageModelInfoEntity_HI Entity Object
 * Thu Jul 06 10:50:38 CST 2017  Auto Generate
 */
@Entity
@Table(name = "page_model_info")
public class PageModelInfoEntity_HI {
    private Integer modelId;
    private String ruleBusinessLineCode;
    private String modelCode;
    private String modelName;
    private String modelDesc;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @Id
    @GeneratedValue
    @Column(name = "model_id", nullable = false, length = 11)
    public Integer getModelId() {
        return modelId;
    }

    public void setRuleBusinessLineCode(String businessLineCode) {
        this.ruleBusinessLineCode = businessLineCode;
    }

    @Column(name = "rule_business_line_code", nullable = true, length = 100)
    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @Column(name = "model_code", nullable = false, length = 100)
    public String getModelCode() {
        return modelCode;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Column(name = "model_name", nullable = true, length = 100)
    public String getModelName() {
        return modelName;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    @Column(name = "model_desc", nullable = true, length = 400)
    public String getModelDesc() {
        return modelDesc;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "version_num", nullable = true, length = 11)
    public Integer getVersionNum() {
        return versionNum;
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

