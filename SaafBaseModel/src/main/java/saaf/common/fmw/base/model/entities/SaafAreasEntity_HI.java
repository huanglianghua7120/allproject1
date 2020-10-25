package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * SaafAreasEntity_HI Entity Object
 * Thu Apr 20 11:13:11 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_areas")
public class SaafAreasEntity_HI {
    private Integer areaId;
    private Integer areaParentId;
    private Integer areaLevel;
    private String areaCode;
    private String areaName;
    private String areaDesc;
    private String isLeafNode;
    private Integer owerAreaId;
    private String ownerArea;
    private String hotCityFlag;
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
    private Integer operatorUserId;

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Id
    @GeneratedValue
    @Column(name = "area_id", precision = 22, scale = 0)
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }

    @Column(name = "area_parent_id", precision = 22, scale = 0)
    public Integer getAreaParentId() {
        return areaParentId;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    @Column(name = "area_level", precision = 22, scale = 0)
    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "area_code", nullable = true, length = 80)
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "area_name", nullable = true, length = 240)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    @Column(name = "area_desc", nullable = true, length = 1000)
    public String getAreaDesc() {
        return areaDesc;
    }

    public void setIsLeafNode(String isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    @Column(name = "is_leaf_node", nullable = true, length = 10)
    public String getIsLeafNode() {
        return isLeafNode;
    }

    public void setOwerAreaId(Integer owerAreaId) {
        this.owerAreaId = owerAreaId;
    }

    @Column(name = "ower_area_id", precision = 22, scale = 0)
    public Integer getOwerAreaId() {
        return owerAreaId;
    }

    public void setOwnerArea(String ownerArea) {
        this.ownerArea = ownerArea;
    }

    @Column(name = "owner_area", nullable = true, length = 240)
    public String getOwnerArea() {
        return ownerArea;
    }

    public void setHotCityFlag(String hotCityFlag) {
        this.hotCityFlag = hotCityFlag;
    }

    @Column(name = "hot_city_flag", nullable = true, length = 10)
    public String getHotCityFlag() {
        return hotCityFlag;
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

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

