package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosChangeContactBfEntity_HI_RO Entity Object
 * Fri Mar 16 17:43:16 CST 2018  Auto Generate
 */

public class SrmPosChangeContactBfEntity_HI_RO {
    private Integer changeContactBfId; //供应商联系人变更前ID
    private Integer changeId; //变更ID
    private Integer supplierContactId; //供应商联系人ID
    private String contactName; //联系人姓名
    private String mobilePhone; //手机号码
    private String fixedPhone; //固定电话
    private String faxPhone; //传真号码
    private String emailAddress; //联系人邮箱
    private String departmentName; //部门
    private String positionName; //职位
    private String needAccountFlag; //付款标识
    private String userName; //用户名
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthDate; //出生日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date failureDate; //失效日期
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

	public void setChangeContactBfId(Integer changeContactBfId) {
		this.changeContactBfId = changeContactBfId;
	}

	
	public Integer getChangeContactBfId() {
		return changeContactBfId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	
	public Integer getChangeId() {
		return changeId;
	}

	public void setSupplierContactId(Integer supplierContactId) {
		this.supplierContactId = supplierContactId;
	}

	
	public Integer getSupplierContactId() {
		return supplierContactId;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	
	public String getContactName() {
		return contactName;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	
	public String getFixedPhone() {
		return fixedPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	
	public String getFaxPhone() {
		return faxPhone;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	
	public String getPositionName() {
		return positionName;
	}

	public void setNeedAccountFlag(String needAccountFlag) {
		this.needAccountFlag = needAccountFlag;
	}

	
	public String getNeedAccountFlag() {
		return needAccountFlag;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	
	public Date getFailureDate() {
		return failureDate;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
