package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafInstitutionEntity_HI Entity Object
 * Thu Apr 20 11:13:19 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_institution")
public class SaafInstitutionEntity_HI {
    private Integer instId;
    private Integer parentInstId;
    private String instCode;
    private String instName;
    private String instType;
    private Integer instLevel;
    private Integer ifLeaf;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private String enabled;
    private String remark;
    private String instRegion;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer operatorUserId;
    

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    @Id
    @SequenceGenerator(name = "ISEQ$$_77794", sequenceName = "ISEQ$$_77794", allocationSize = 1)
    @GeneratedValue(generator = "ISEQ$$_77794", strategy = GenerationType.SEQUENCE)
    @Column(name = "inst_id", nullable = false, length = 11)
    public Integer getInstId() {
        return instId;
    }

    public void setParentInstId(Integer parentInstId) {
        this.parentInstId = parentInstId;
    }

    @Column(name = "parent_inst_id", precision = 11, scale = 0)
    public Integer getParentInstId() {
        return parentInstId;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    @Column(name = "inst_code", nullable = false, length = 200)
    public String getInstCode() {
        return instCode;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    @Column(name = "inst_name", nullable = false, length = 240)
    public String getInstName() {
        return instName;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    @Column(name = "inst_type", nullable = false, length = 30)
    public String getInstType() {
        return instType;
    }

    public void setInstLevel(Integer instLevel) {
        this.instLevel = instLevel;
    }

    @Column(name = "inst_level", precision = 22, scale = 0)
    public Integer getInstLevel() {
        return instLevel;
    }

    public void setIfLeaf(Integer ifLeaf) {
        this.ifLeaf = ifLeaf;
    }

    @Column(name = "if_leaf", precision = 22, scale = 0)
    public Integer getIfLeaf() {
        return ifLeaf;
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

    @Column(name = "start_date", nullable = false, length = 0)
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

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Column(name = "enabled", nullable = true, length = 10)
    public String getEnabled() {
        return enabled;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "remark", nullable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    public void setInstRegion(String instRegion) {
        this.instRegion = instRegion;
    }

    @Column(name = "inst_region", nullable = true, length = 100)
    public String getInstRegion() {
        return instRegion;
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

