package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;


/**
 * SaafPositionsEntity_HI Entity Object
 * Thu Apr 20 11:13:27 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_positions")
public class SaafPositionsEntity_HI {
    private Integer posId;
    private Integer parentPosId;
    private String posNumber;
    private String posName;
    private Integer posLevel;
    private Integer posSequence;
    private String remark;
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
    private Integer versionNum;
    private Integer operatorUserId;

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    @Id
   // @GeneratedValue
    @SequenceGenerator(name = "SAAF_POSITIONS_S", sequenceName = "SAAF_POSITIONS_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_POSITIONS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "pos_id", precision = 22, scale = 0)
    public Integer getPosId() {
        return posId;
    }

    public void setParentPosId(Integer parentPosId) {
        this.parentPosId = parentPosId;
    }

    @Column(name = "parent_pos_id", precision = 22, scale = 0)
    public Integer getParentPosId() {
        return parentPosId;
    }

    public void setPosNumber(String posNumber) {
        this.posNumber = posNumber;
    }

    @Column(name = "pos_number", nullable = true, length = 100)
    public String getPosNumber() {
        return posNumber;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    @Column(name = "pos_name", nullable = true, length = 100)
    public String getPosName() {
        return posName;
    }

    public void setPosLevel(Integer posLevel) {
        this.posLevel = posLevel;
    }

    @Column(name = "pos_level", precision = 22, scale = 0)
    public Integer getPosLevel() {
        return posLevel;
    }

    public void setPosSequence(Integer posSequence) {
        this.posSequence = posSequence;
    }

    @Column(name = "pos_sequence", precision = 22, scale = 0)
    public Integer getPosSequence() {
        return posSequence;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "remark", nullable = true, length = 200)
    public String getRemark() {
        return remark;
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

