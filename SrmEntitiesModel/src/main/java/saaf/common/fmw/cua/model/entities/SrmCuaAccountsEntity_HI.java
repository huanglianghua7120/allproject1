package saaf.common.fmw.cua.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmCuaAccountsEntity_HI Entity Object
 * Tue Oct 30 11:45:29 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_cua_accounts")
public class SrmCuaAccountsEntity_HI {
    private Integer accountId;
    private String accountCode;
    private Integer orgId;
    private Integer supplierId;
    private String accountStatus;
    @JSONField(format = "yyyy-MM-dd")
    private Date accountDate;
    private java.math.BigDecimal receiveTaxAmount;
    private java.math.BigDecimal returnTaxAmount;
    private java.math.BigDecimal receiveAmount;
    private java.math.BigDecimal returnAmount;
    @JSONField(format = "yyyy-MM-dd")
    private Date confirmDate;
    private Integer accountFileId;
    private String comments;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd")
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer operatorUserId;

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	@Id
	@SequenceGenerator(name = "SRM_CUA_ACCOUNTS_S", sequenceName = "SRM_CUA_ACCOUNTS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_CUA_ACCOUNTS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "account_id", nullable = false, length = 22)	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Column(name = "account_code", nullable = false, length = 30)	
	public String getAccountCode() {
		return accountCode;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = false, length = 22)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = false, length = 22)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name = "account_status", nullable = true, length = 30)	
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	@Column(name = "account_date", nullable = true, length = 7)	
	public Date getAccountDate() {
		return accountDate;
	}

	public void setReceiveTaxAmount(java.math.BigDecimal receiveTaxAmount) {
		this.receiveTaxAmount = receiveTaxAmount;
	}

	@Column(name = "receive_tax_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getReceiveTaxAmount() {
		return receiveTaxAmount;
	}

	public void setReturnTaxAmount(java.math.BigDecimal returnTaxAmount) {
		this.returnTaxAmount = returnTaxAmount;
	}

	@Column(name = "return_tax_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getReturnTaxAmount() {
		return returnTaxAmount;
	}

	public void setReceiveAmount(java.math.BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	@Column(name = "receive_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReturnAmount(java.math.BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	@Column(name = "return_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "confirm_date", nullable = true, length = 7)	
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setAccountFileId(Integer accountFileId) {
		this.accountFileId = accountFileId;
	}

	@Column(name = "account_file_id", nullable = true, length = 22)	
	public Integer getAccountFileId() {
		return accountFileId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "comments", nullable = true, length = 2000)	
	public String getComments() {
		return comments;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	@Version
	@Column(name = "version_num", nullable = false, length = 22)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 7)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 22)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 22)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 7)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 22)	
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
