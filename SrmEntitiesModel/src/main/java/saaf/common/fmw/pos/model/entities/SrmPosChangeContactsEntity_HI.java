package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosChangeContactsEntity_HI Entity Object
 * Wed Nov 15 10:52:55 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_change_contacts")
public class SrmPosChangeContactsEntity_HI {
    private Integer changeContactsId;
    private Integer changeId;
    private Integer supplierContactId;
    private Integer supplierId;
    private String contactName;
    private String mobilePhone;
    private String fixedPhone;
    private String faxPhone;
    private String emailAddress;
    private String departmentName;
    private String positionName;
    private String needAccountFlag;
    private String userName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date failureDate;
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

    public void setChangeContactsId(Integer changeContactsId) {
	this.changeContactsId = changeContactsId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_CHANGE_CONTACTS_S", sequenceName = "SRM_POS_CHANGE_CONTACTS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_CHANGE_CONTACTS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "change_contacts_id", nullable = false, length = 11)    
    public Integer getChangeContactsId() {
	return changeContactsId;
    }

    public void setChangeId(Integer changeId) {
	this.changeId = changeId;
    }

    @Column(name = "change_id", nullable = false, length = 11)    
    public Integer getChangeId() {
	return changeId;
    }

    public void setSupplierContactId(Integer supplierContactId) {
	this.supplierContactId = supplierContactId;
    }

    @Column(name = "supplier_contact_id", nullable = true, length = 11)    
    public Integer getSupplierContactId() {
	return supplierContactId;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = true, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    @Column(name = "contact_name", nullable = true, length = 100)    
    public String getContactName() {
	return contactName;
    }

    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    @Column(name = "mobile_phone", nullable = true, length = 30)    
    public String getMobilePhone() {
	return mobilePhone;
    }

    public void setFixedPhone(String fixedPhone) {
	this.fixedPhone = fixedPhone;
    }

    @Column(name = "fixed_phone", nullable = true, length = 100)    
    public String getFixedPhone() {
	return fixedPhone;
    }

    public void setFaxPhone(String faxPhone) {
	this.faxPhone = faxPhone;
    }

    @Column(name = "fax_phone", nullable = true, length = 100)    
    public String getFaxPhone() {
	return faxPhone;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    @Column(name = "email_address", nullable = true, length = 100)    
    public String getEmailAddress() {
	return emailAddress;
    }

    public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
    }

    @Column(name = "department_name", nullable = true, length = 50)    
    public String getDepartmentName() {
	return departmentName;
    }

    public void setPositionName(String positionName) {
	this.positionName = positionName;
    }

    @Column(name = "position_name", nullable = true, length = 50)    
    public String getPositionName() {
	return positionName;
    }

    public void setNeedAccountFlag(String needAccountFlag) {
	this.needAccountFlag = needAccountFlag;
    }

    @Column(name = "need_account_flag", nullable = true, length = 10)    
    public String getNeedAccountFlag() {
	return needAccountFlag;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    @Column(name = "user_name", nullable = true, length = 100)    
    public String getUserName() {
	return userName;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    @Column(name = "birth_date", nullable = true, length = 0)    
    public Date getBirthDate() {
	return birthDate;
    }

    public void setFailureDate(Date failureDate) {
	this.failureDate = failureDate;
    }

    @Column(name = "failure_date", nullable = true, length = 0)    
    public Date getFailureDate() {
	return failureDate;
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
    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

