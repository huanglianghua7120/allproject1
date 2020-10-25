package saaf.common.fmw.base.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;

/**
 * SaafEmployeesEntity_HI Entity Object Thu Apr 20 11:13:15 CST 2017 Auto
 * Generate
 */
@Entity
@Table(name = "saaf_employees")
public class SaafEmployeesEntity_HI {
	private Integer employeeId;
	private Integer userId;
	private String employeeNumber;
	private String accountNumber;
	private String employeeName;
	private String platformCode;
	private String sex;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date birthDay;
	private String cardNo;
	private Integer instId;
	private String instName;
	private String instCode;
	private String enabledFlag;
	private Integer positionId;
	private String positionCode;
	private String positionName;
	private Integer positionInstId;
	private String telPhone;
	private String mobilePhone;
	private String email;
	private String postalAddress;
	private String postcode;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private Integer operatorUserId;
	private String poFlag;
	private String quartersCode;
	private String parentsQuartersCode;
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Id
	@SequenceGenerator(name = "SAAF_EMPLOYEES_S", sequenceName = "SAAF_EMPLOYEES_S", allocationSize = 1)
	@GeneratedValue(generator = "SAAF_EMPLOYEES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "employee_id", precision = 22, scale = 0)
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_id", precision = 22, scale = 0)
	public Integer getUserId() {
		return userId;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	@Column(name = "employee_number", nullable = false, length = 30)
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "account_number", nullable = true, length = 30)
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "employee_name", nullable = false, length = 150)
	public String getEmployeeName() {
		return employeeName;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	@Column(name = "platform_code", nullable = false, length = 30)
	public String getPlatformCode() {
		return platformCode;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "sex", nullable = true, length = 30)
	public String getSex() {
		return sex;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Column(name = "birth_day", nullable = true, length = 0)
	public Date getBirthDay() {
		return birthDay;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "card_no", nullable = true, length = 30)
	public String getCardNo() {
		return cardNo;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	@Column(name = "inst_id", precision = 22, scale = 0)
	public Integer getInstId() {
		return instId;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	@Column(name = "inst_name", nullable = true, length = 240)
	public String getInstName() {
		return instName;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	@Column(name = "inst_code", nullable = true, length = 30)
	public String getInstCode() {
		return instCode;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	@Column(name = "enabled_flag", nullable = true, length = 1)
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	@Column(name = "position_id", precision = 22, scale = 0)
	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "position_name", nullable = true, length = 60)
	public String getPositionName() {
		return positionName;
	}
	
	@Column(name = "position_code", nullable = true, length = 60)
	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public void setPositionInstId(Integer positionInstId) {
		this.positionInstId = positionInstId;
	}

	@Column(name = "position_inst_id", precision = 22, scale = 0)
	public Integer getPositionInstId() {
		return positionInstId;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	@Column(name = "tel_phone", nullable = true, length = 30)
	public String getTelPhone() {
		return telPhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "mobile_phone", nullable = true, length = 30)
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "email", nullable = true, length = 60)
	public String getEmail() {
		return email;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	@Column(name = "postal_address", nullable = true, length = 240)
	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "postcode", nullable = true, length = 30)
	public String getPostcode() {
		return postcode;
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
	@Column(name = "po_flag", nullable = true, length = 10)
	public String getPoFlag() {
		return poFlag;
	}

	public void setPoFlag(String poFlag) {
		this.poFlag = poFlag;
	}
	@Column(name = "quarters_code", nullable = true, length = 30)
	public String getQuartersCode() {
		return quartersCode;
	}

	public void setQuartersCode(String quartersCode) {
		this.quartersCode = quartersCode;
	}
	@Column(name = "parents_quarters_code", nullable = true, length = 30)
	public String getParentsQuartersCode() {
		return parentsQuartersCode;
	}

	public void setParentsQuartersCode(String parentsQuartersCode) {
		this.parentsQuartersCode = parentsQuartersCode;
	}
}
