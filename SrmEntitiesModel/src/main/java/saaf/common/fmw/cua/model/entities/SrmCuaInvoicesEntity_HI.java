package saaf.common.fmw.cua.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmCuaInvoicesEntity_HI Entity Object
 * Fri Nov 09 17:08:47 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_cua_invoices")
public class SrmCuaInvoicesEntity_HI {
    private Integer invoiceId;
    private String invoiceCode;
    private String invoiceStatus;
    private Integer orgId;
    private Integer supplierId;
    private String extInvoiceCode;
    private java.math.BigDecimal invoiceAmount;
    private java.math.BigDecimal taxRate;
    private java.math.BigDecimal taxAmount;
    private java.math.BigDecimal untaxedAmount;
    private String currencyCode;
    @JSONField(format = "yyyy-MM-dd")
    private Date billingDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date recordingDate;
    private String authenticFlag;
    private java.math.BigDecimal recordingNum;
    private String interfaceStatus;
    private Integer fileId;
    private String comments;
    @JSONField(format = "yyyy-MM-dd")
    private Date glDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date conditionDate;
    private String condition;
    @JSONField(format = "yyyy-MM-dd")
    private Date exchangeDate;
    private String remitAccount;
    @JSONField(format = "yyyy-MM-dd")
    private Date approvalDate;
    private String rejectReason;
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
    private String paymentMethod;
    private String standardErrorMsg;	//标准类型失败原因
    private String creditErrorMsg;	//贷项类型失败原因

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	@Id
	@SequenceGenerator(name = "SRM_CUA_INVOICES_S", sequenceName = "SRM_CUA_INVOICES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_CUA_INVOICES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "invoice_id", nullable = false, length = 22)	
	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	@Column(name = "invoice_code", nullable = false, length = 30)	
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	@Column(name = "invoice_status", nullable = true, length = 30)	
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 22)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 22)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setExtInvoiceCode(String extInvoiceCode) {
		this.extInvoiceCode = extInvoiceCode;
	}

	@Column(name = "ext_invoice_code", nullable = true, length = 150)	
	public String getExtInvoiceCode() {
		return extInvoiceCode;
	}

	public void setInvoiceAmount(java.math.BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name = "invoice_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "tax_rate", nullable = true, length = 22)	
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxAmount(java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Column(name = "tax_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setUntaxedAmount(java.math.BigDecimal untaxedAmount) {
		this.untaxedAmount = untaxedAmount;
	}

	@Column(name = "untaxed_amount", nullable = true, length = 22)	
	public java.math.BigDecimal getUntaxedAmount() {
		return untaxedAmount;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	@Column(name = "billing_date", nullable = true, length = 7)	
	public Date getBillingDate() {
		return billingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	@Column(name = "recording_date", nullable = true, length = 7)	
	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setAuthenticFlag(String authenticFlag) {
		this.authenticFlag = authenticFlag;
	}

	@Column(name = "authentic_flag", nullable = true, length = 1)	
	public String getAuthenticFlag() {
		return authenticFlag;
	}

	public void setRecordingNum(java.math.BigDecimal recordingNum) {
		this.recordingNum = recordingNum;
	}

	@Column(name = "recording_num", nullable = true, length = 22)	
	public java.math.BigDecimal getRecordingNum() {
		return recordingNum;
	}

	public void setInterfaceStatus(String interfaceStatus) {
		this.interfaceStatus = interfaceStatus;
	}

	@Column(name = "interface_status", nullable = true, length = 30)	
	public String getInterfaceStatus() {
		return interfaceStatus;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 22)	
	public Integer getFileId() {
		return fileId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "comments", nullable = true, length = 2000)	
	public String getComments() {
		return comments;
	}

	public void setGlDate(Date glDate) {
		this.glDate = glDate;
	}

	@Column(name = "gl_date", nullable = true, length = 7)	
	public Date getGlDate() {
		return glDate;
	}

	public void setConditionDate(Date conditionDate) {
		this.conditionDate = conditionDate;
	}

	@Column(name = "condition_date", nullable = true, length = 7)	
	public Date getConditionDate() {
		return conditionDate;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "\"condition\"", nullable = true, length = 30)
	public String getCondition() {
		return condition;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	@Column(name = "exchange_date", nullable = true, length = 7)	
	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setRemitAccount(String remitAccount) {
		this.remitAccount = remitAccount;
	}

	@Column(name = "remit_account", nullable = true, length = 100)	
	public String getRemitAccount() {
		return remitAccount;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "approval_date", nullable = true, length = 7)	
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "reject_reason", nullable = true, length = 2000)	
	public String getRejectReason() {
		return rejectReason;
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
	
	@Column(name = "PAYMENT_METHOD", nullable = true, length = 30)	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	@Column(name = "STANDARD_ERROR_MSG", nullable = true, length = 30)	
	public String getStandardErrorMsg() {
		return standardErrorMsg;
	}
	
	public void setStandardErrorMsg(String standardErrorMsg) {
		this.standardErrorMsg = standardErrorMsg;
	}
	
	@Column(name = "CREDIT_ERROR_MSG", nullable = true, length = 30)	
	public String getCreditErrorMsg() {
		return creditErrorMsg;
	}
	
	public void setCreditErrorMsg(String creditErrorMsg) {
		this.creditErrorMsg = creditErrorMsg;
	}
	@Override
	public String toString() {
		return "SrmCuaInvoicesEntity_HI [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceStatus="
				+ invoiceStatus + ", orgId=" + orgId + ", supplierId=" + supplierId + ", extInvoiceCode="
				+ extInvoiceCode + ", invoiceAmount=" + invoiceAmount + ", taxRate=" + taxRate + ", taxAmount="
				+ taxAmount + ", untaxedAmount=" + untaxedAmount + ", currencyCode=" + currencyCode + ", billingDate="
				+ billingDate + ", recordingDate=" + recordingDate + ", authenticFlag=" + authenticFlag
				+ ", recordingNum=" + recordingNum + ", interfaceStatus=" + interfaceStatus + ", fileId=" + fileId
				+ ", comments=" + comments + ", glDate=" + glDate + ", conditionDate=" + conditionDate + ", condition="
				+ condition + ", exchangeDate=" + exchangeDate + ", remitAccount=" + remitAccount + ", approvalDate="
				+ approvalDate + ", rejectReason=" + rejectReason + ", versionNum=" + versionNum + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin + ", attributeCategory=" + attributeCategory
				+ ", attribute1=" + attribute1 + ", attribute2=" + attribute2 + ", attribute3=" + attribute3
				+ ", attribute4=" + attribute4 + ", attribute5=" + attribute5 + ", attribute6=" + attribute6
				+ ", attribute7=" + attribute7 + ", attribute8=" + attribute8 + ", attribute9=" + attribute9
				+ ", attribute10=" + attribute10 + ", attribute11=" + attribute11 + ", attribute12=" + attribute12
				+ ", attribute13=" + attribute13 + ", attribute14=" + attribute14 + ", attribute15=" + attribute15
				+ ", operatorUserId=" + operatorUserId + "]";
	}
	
	
}
