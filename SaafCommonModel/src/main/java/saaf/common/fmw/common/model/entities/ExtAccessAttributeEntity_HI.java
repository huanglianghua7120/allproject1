package saaf.common.fmw.common.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ExtAccessAttributeEntity_HI Entity Object
 * Mon Sep 26 16:44:05 CST 2016  Auto Generate
 */
@Entity
@Table(name = "EXT_ACCESS_ATTRIBUTE")
public class ExtAccessAttributeEntity_HI {
    private Integer extAttributeId;
    private Integer extIntId;
    private Integer extDisId;
    private String attributeName;
    private String attributeValue;
    private String enableFlage;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setExtAttributeId(Integer extAttributeId) {
        this.extAttributeId = extAttributeId;
    }

    @Id
    @GeneratedValue
    @Column(name = "ext_attribute_id", nullable = false, length = 11)
    public Integer getExtAttributeId() {
        return extAttributeId;
    }

    public void setExtIntId(Integer extIntId) {
        this.extIntId = extIntId;
    }

    @Column(name = "ext_int_id", nullable = true, length = 11)
    public Integer getExtIntId() {
        return extIntId;
    }

    public void setExtDisId(Integer extDisId) {
        this.extDisId = extDisId;
    }

    @Column(name = "ext_dis_id", nullable = true, length = 11)
    public Integer getExtDisId() {
        return extDisId;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Column(name = "attribute_name", nullable = true, length = 240)
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Column(name = "attribute_value", nullable = true, length = 0)
    public String getAttributeValue() {
        return attributeValue;
    }

    public void setEnableFlage(String enableFlage) {
        this.enableFlage = enableFlage;
    }

    @Column(name = "enable_flage", nullable = true, length = 10)
    public String getEnableFlage() {
        return enableFlage;
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

