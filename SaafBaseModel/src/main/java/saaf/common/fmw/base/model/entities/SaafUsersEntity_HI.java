package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;


/**
 * SaafUsersEntity_HI Entity Object
 * Thu Apr 20 11:13:36 CST 2017  Auto Generate
 */
@SolrDocument(solrCoreName = "collection1")
@Entity
@Table(name = "saaf_users")
public class SaafUsersEntity_HI implements Serializable {
    @org.springframework.data.annotation.Id
    @Field("id")
    private Integer userId;
    @Field
    private Integer employeeId;
    @Field
    private Integer memberId;
    @Field
    private String isadmin;
    @Field
    private String userName;
    @Field
    private String userFullName;
    @Field
    private String encryptedPassword;
    @Field
    private String platformCode;

    @Field
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @Field
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @Field
    private Integer createdBy;
    @Field
    private Integer lastUpdatedBy;
    @Field
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    @Field
    private Integer lastUpdateLogin;
    @Field
    private Integer versionNum;
    @Field
    private Integer operatorUserId;
    @Field
    private Integer supplierId;
    @Field
    private String supplierPrimaryFlag;
    @Field
    private Integer userFileId;
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_USERS_S", sequenceName = "SAAF_USERS_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_USERS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", precision = 22, scale = 0)
    public Integer getUserId() {
        return userId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Column(name = "employee_id", precision = 22, scale = 0)
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Column(name = "member_id", precision = 22, scale = 0)
    public Integer getMemberId() {
        return memberId;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    @Column(name = "isadmin", nullable = false, length = 20)
    public String getIsadmin() {
        return isadmin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_name", nullable = false, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Column(name = "user_full_name", nullable = true, length = 100)
    public String getUserFullName() {
        return userFullName;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Column(name = "encrypted_password", nullable = false, length = 100)
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = false, length = 30)
    public String getPlatformCode() {
        return platformCode;
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

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", precision = 22, scale = 0)
    public Integer getVersionNum() {
        return versionNum;
    }
    

    @Column(name = "supplier_id", precision = 22, scale = 0)
    public Integer getSupplierId() {
		return supplierId;
	}
    
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	@Column(name = "user_file_id", precision = 11, scale = 0)
	public Integer getUserFileId() {
		return userFileId;
	}

	public void setUserFileId(Integer userFileId) {
		this.userFileId = userFileId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    @Column(name = "supplier_primary_flag", nullable = true, length = 1)
    public String getSupplierPrimaryFlag() {
        return supplierPrimaryFlag;
    }

    public void setSupplierPrimaryFlag(String supplierPrimaryFlag) {
        this.supplierPrimaryFlag = supplierPrimaryFlag;
    }
}

