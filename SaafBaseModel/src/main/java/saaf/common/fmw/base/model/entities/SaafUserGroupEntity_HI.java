package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;


/**
 * SaafUserGroupEntity_HI Entity Object
 * Thu Apr 20 11:13:34 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_user_group")
public class SaafUserGroupEntity_HI {
    private Integer userGroupId;
    private String groupCode;
    private String groupDesc;
    private String groupType;
    private String typeDetail;
    private String selectResult;
    private String userType;
    private String groupSql;
    private String enabledFlag;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    private Integer operatorUserId;
    
    public void setOperatorUserId(Integer operatorUserId) {
    	this.operatorUserId = operatorUserId;
    }

    @Transient	
    public Integer getOperatorUserId() {
    	return operatorUserId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Id
    @GeneratedValue
    @Column(name = "user_group_id", precision = 22, scale = 0)
    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name = "group_code", nullable = false, length = 30)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @Column(name = "group_desc", nullable = false, length = 30)
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    @Column(name = "group_type", nullable = false, length = 30)
    public String getGroupType() {
        return groupType;
    }

    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    @Column(name = "type_detail", nullable = false, length = 4000)
    public String getTypeDetail() {
        return typeDetail;
    }

    public void setSelectResult(String selectResult) {
        this.selectResult = selectResult;
    }

    @Column(name = "select_result", nullable = true, length = 4000)
    public String getSelectResult() {
        return selectResult;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Column(name = "user_type", nullable = true, length = 30)
    public String getUserType() {
        return userType;
    }

    public void setGroupSql(String groupSql) {
        this.groupSql = groupSql;
    }

    @Column(name = "group_sql", nullable = false, length = 4000)
    public String getGroupSql() {
        return groupSql;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Column(name = "enabled_flag", nullable = true, length = 10)
    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", precision = 11, scale = 0)
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

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute_category", nullable = true, length = 30)
    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Column(name = "attribute1", nullable = true, length = 240)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Column(name = "attribute2", nullable = true, length = 240)
    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Column(name = "attribute3", nullable = true, length = 240)
    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Column(name = "attribute4", nullable = true, length = 240)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(name = "attribute5", nullable = true, length = 240)
    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Column(name = "attribute6", nullable = true, length = 240)
    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @Column(name = "attribute7", nullable = true, length = 240)
    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    @Column(name = "attribute8", nullable = true, length = 240)
    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    @Column(name = "attribute9", nullable = true, length = 240)
    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    @Column(name = "attribute10", nullable = true, length = 240)
    public String getAttribute10() {
        return attribute10;
    }
}

