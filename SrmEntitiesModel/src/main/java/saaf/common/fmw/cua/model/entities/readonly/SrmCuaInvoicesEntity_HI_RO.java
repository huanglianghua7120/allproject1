package saaf.common.fmw.cua.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * SrmCuaInvoicesEntity_HI_RO Entity Object
 * Fri Nov 09 17:08:47 CST 2018  Auto Generate
 */

public class SrmCuaInvoicesEntity_HI_RO {
	 //查询发票列表信息
	 public static final String QUERY_INVOICE_SQL =
			 		"SELECT sci.invoice_id invoiceId\n" +
					 "      ,sci.invoice_code invoiceCode\n" +
					 "      ,sci.org_id instId\n" +
					 "      ,spsi.supplier_name supplierName\n" +
					 "      ,si.inst_name instName\n" +
					 "      ,(SELECT listagg(sca.account_code, ',') within GROUP(ORDER BY sca.account_code)\n" +
					 "        FROM   srm_cua_accounts      sca\n" +
					 "              ,srm_cua_invoice_lines scil\n" +
					 "        WHERE  scil.account_id = sca.account_id) accountCode\n" +
					 "      ,sci.creation_date creationDate\n" +
					 "      ,sci.ext_invoice_code extInvoiceCode\n" +
					 "      ,sci.invoice_amount invoiceAmount\n" +
					 "      ,sci.tax_rate taxRate\n" +
					 "      ,sci.tax_amount taxAmount\n" +
					 "      ,sci.untaxed_amount untaxedAmount\n" +
					 "      ,sci.currency_code currencyCode\n" +
					 "      ,sci.billing_date billingDate\n" +
					 "      ,sci.gl_date glDate\n" +
					 "      ,sci.condition_date conditionDate\n" +
					 "      ,sppt.payment_term_name condition\n" +
					 "      ,sci.exchange_date exchangeDate\n" +
					 "      ,sci.remit_account remitAccount\n" +
					 "      ,sci.payment_method paymentMethod\n" +
					 "      ,sci.recording_date recordingDate\n" +
					 "      ,sci.authentic_flag authenticFlag\n" +
					 "      ,sci.recording_num recordingNum\n" +
					 "      ,slv2.meaning interfaceStatus\n" +
					 "      ,sci.file_id fileId\n" +
					 "      ,sbrf.file_name fileName\n" +
					 "      ,sbrf.access_path accessPath\n" +
					 "      ,su.user_full_name userName\n" +
					 "      ,slv1.meaning invoiceStatusName\n" +
					 "      ,sci.invoice_status invoiceStatus\n" +
					 "      ,sci.approval_date approvalDate\n" +
					 "      ,sci.standard_error_msg standardErrorMsg\n" +
					 "      ,sci.credit_error_msg creditErrorMsg\n" +
					 "FROM   srm_cua_invoices sci\n" +
					 "LEFT   JOIN srm_pos_supplier_info spsi\n" +
					 "ON     sci.supplier_id = spsi.supplier_id\n" +
					 "LEFT   JOIN saaf_institution si\n" +
					 "ON     sci.org_id = si.inst_id\n" +
					 "AND    si.inst_type = 'ORG'\n" +
					 "LEFT   JOIN srm_pon_payment_terms sppt\n" +
					 "ON     sci.condition = sppt.payment_term_code\n" +
					 "LEFT   JOIN saaf_users su\n" +
					 "ON     sci.created_by = su.user_id\n" +
					 "LEFT   JOIN saaf_lookup_values slv1\n" +
					 "ON     sci.invoice_status = slv1.lookup_code\n" +
					 "AND    slv1.lookup_type = 'SNBC_DZ_DOCUMENT_STATUS'\n" +
					 "LEFT   JOIN saaf_lookup_values slv2\n" +
					 "ON     sci.interface_status = slv2.lookup_code\n" +
					 "AND    slv2.lookup_type = 'SNBC_FP_INTERFACE_STATUS'\n" +
					 "LEFT   JOIN saaf_base_result_file sbrf\n" +
					 "ON     sci.file_id = sbrf.file_id\n" +
					 "WHERE  1 = 1\n";

	public static final String QUERY_INVOICEBYID_SQL =
					"SELECT sci.invoice_id         invoiceId\n" +
					"      ,sci.invoice_code       invoiceCode\n" +
					"      ,sci.supplier_id        supplierId\n" +
					"      ,sci.org_id             instId\n" +
					"      ,spsi.supplier_name     supplierName\n" +
					"      ,si.inst_name           instName\n" +
					"      ,sci.creation_date      creationDate\n" +
					"      ,sci.ext_invoice_code   extInvoiceCode\n" +
					"      ,sci.invoice_amount     invoiceAmount\n" +
					"      ,sci.tax_rate           taxRate\n" +
					"      ,sci.tax_amount         taxAmount\n" +
					"      ,sci.untaxed_amount     untaxedAmount\n" +
					"      ,sci.currency_code      currencyCode\n" +
					"      ,sbrf.file_name         fileName\n" +
					"      ,sbrf.access_path       accessPath\n" +
					"      ,sci.gl_date            glDate\n" +
					"      ,sci.condition_date     conditionDate\n" +
					"      ,sci.exchange_date      exchangeDate\n" +
					"      ,sci.remit_account      remitAccount\n" +
					"      ,sci.billing_date       billingDate\n" +
					"      ,sci.reject_reason      rejectReason\n" +
					"      ,sci.recording_date     recordingDate\n" +
					"      ,sci.authentic_flag     authenticFlag\n" +
					"      ,sci.recording_num      recordingNum\n" +
					"      ,sci.interface_status   interfaceStatus\n" +
					"      ,sci.payment_method     paymentMethod\n" +
					"      ,sppt.payment_term_name paymentTermName\n" +
					"      ,sci.file_id            fileId\n" +
					"      ,su.user_full_name      userName\n" +
					"      ,sci.invoice_status     invoiceStatus\n" +
					"      ,sci.comments           comments\n" +
					"      ,sci.approval_date      approvalDate\n" +
					"FROM   srm_cua_invoices sci\n" +
					"LEFT   JOIN srm_pos_supplier_info spsi\n" +
					"ON     sci.supplier_id = spsi.supplier_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     sci.org_id = si.inst_id\n" +
					"AND    si.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     sci.created_by = su.user_id\n" +
					"LEFT   JOIN srm_pon_payment_terms sppt\n" +
					"ON     sci.condition = sppt.payment_term_code\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sci.file_id = sbrf.file_id\n" +
					"WHERE  1 = 1\n";

	//查询今天最大的流水号
     public static final String QUERY_LAST_INVOICE_CODE =
			 		 "SELECT MAX(invoice_code) invoiceCode\n" +
					 "FROM   srm_cua_invoices sci\n" +
					 "WHERE  trunc(sci.creation_date) = trunc(SYSDATE)\n";

	private Integer invoiceId;	//发票ID
    private String invoiceCode;	//发票编号
    private String invoiceStatus;	//发票状态
    private Integer orgId;	//业务实体ID
    private Integer instId; //业务实体ID
    private Integer supplierId;	//供应商ID
    private String extInvoiceCode;	//发票号
    private java.math.BigDecimal invoiceAmount;	//发票金额（含税）
    private java.math.BigDecimal taxRate;	//税率
    private java.math.BigDecimal taxAmount;	//税额
    private java.math.BigDecimal untaxedAmount;	//未税金额
    private String currencyCode;	//币种
    @JSONField(format = "yyyy-MM-dd")
    private Date billingDate;	//开票日期
    @JSONField(format = "yyyy-MM-dd")
    private Date recordingDate;	//入账日期
    private String authenticFlag;	//发票真假
    private java.math.BigDecimal recordingNum;	//入账张数
    private String interfaceStatus;	//接口同步状态
    private Integer fileId;	//附件ID
    private String comments;	//备注
    @JSONField(format = "yyyy-MM-dd")
    private Date glDate;	//GL日期
    @JSONField(format = "yyyy-MM-dd")
    private Date conditionDate;	//条件日期
    private String condition;	//条件
    @JSONField(format = "yyyy-MM-dd")
    private Date exchangeDate;	//付款汇率日期
    private String remitAccount;	//汇入银行账号
    @JSONField(format = "yyyy-MM-dd")	
    private Date approvalDate;	//批准时间
    private String rejectReason;	//驳回原因
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
    private String standardErrorMsg;	//标准类型失败原因
    private String creditErrorMsg;	//贷项类型失败原因
    //自定义
    private String supplierName; //供应商名称
    private String instName; //业务实体名称
    private String userName; //经办人
    private List<SrmCuaInvoiceLinesEntity_HI_RO> invoiceLineList;	//发票行
    private String accountCode;	//对账单号
    private String paymentMethod; //汇款方法
    private String accessPath; //路径
    private String fileName; //文件名
    private String invoiceStatusName; //状态名
    private String paymentTermName; //条件名

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	
	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setExtInvoiceCode(String extInvoiceCode) {
		this.extInvoiceCode = extInvoiceCode;
	}

	
	public String getExtInvoiceCode() {
		return extInvoiceCode;
	}

	public void setInvoiceAmount(java.math.BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	
	public java.math.BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxAmount(java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	
	public java.math.BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setUntaxedAmount(java.math.BigDecimal untaxedAmount) {
		this.untaxedAmount = untaxedAmount;
	}

	
	public java.math.BigDecimal getUntaxedAmount() {
		return untaxedAmount;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	
	public Date getBillingDate() {
		return billingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	
	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setAuthenticFlag(String authenticFlag) {
		this.authenticFlag = authenticFlag;
	}

	
	public String getAuthenticFlag() {
		return authenticFlag;
	}

	public void setRecordingNum(java.math.BigDecimal recordingNum) {
		this.recordingNum = recordingNum;
	}

	
	public java.math.BigDecimal getRecordingNum() {
		return recordingNum;
	}

	public void setInterfaceStatus(String interfaceStatus) {
		this.interfaceStatus = interfaceStatus;
	}

	
	public String getInterfaceStatus() {
		return interfaceStatus;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	
	public Integer getFileId() {
		return fileId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public String getComments() {
		return comments;
	}

	public void setGlDate(Date glDate) {
		this.glDate = glDate;
	}

	
	public Date getGlDate() {
		return glDate;
	}

	public void setConditionDate(Date conditionDate) {
		this.conditionDate = conditionDate;
	}

	
	public Date getConditionDate() {
		return conditionDate;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	
	public String getCondition() {
		return condition;
	}

	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	
	public Date getExchangeDate() {
		return exchangeDate;
	}

	public void setRemitAccount(String remitAccount) {
		this.remitAccount = remitAccount;
	}

	
	public String getRemitAccount() {
		return remitAccount;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
	public String getRejectReason() {
		return rejectReason;
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getInstName() {
		return instName;
	}


	public void setInstName(String instName) {
		this.instName = instName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public static String getQueryInvoiceSql() {
		return QUERY_INVOICE_SQL;
	}


	public List<SrmCuaInvoiceLinesEntity_HI_RO> getInvoiceLineList() {
		return invoiceLineList;
	}


	public void setInvoiceLineList(List<SrmCuaInvoiceLinesEntity_HI_RO> invoiceLineList) {
		this.invoiceLineList = invoiceLineList;
	}


	public static String getQueryInvoicebyidSql() {
		return QUERY_INVOICEBYID_SQL;
	}


	public String getAccountCode() {
		return accountCode;
	}


	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}


	public static String getQueryLastInvoiceCode() {
		return QUERY_LAST_INVOICE_CODE;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public Integer getInstId() {
		return instId;
	}


	public void setInstId(Integer instId) {
		this.instId = instId;
	}


	public String getAccessPath() {
		return accessPath;
	}


	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getInvoiceStatusName() {
		return invoiceStatusName;
	}


	public void setInvoiceStatusName(String invoiceStatusName) {
		this.invoiceStatusName = invoiceStatusName;
	}


	public String getPaymentTermName() {
		return paymentTermName;
	}


	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}


	public String getStandardErrorMsg() {
		return standardErrorMsg;
	}


	public void setStandardErrorMsg(String standardErrorMsg) {
		this.standardErrorMsg = standardErrorMsg;
	}


	public String getCreditErrorMsg() {
		return creditErrorMsg;
	}


	public void setCreditErrorMsg(String creditErrorMsg) {
		this.creditErrorMsg = creditErrorMsg;
	}
	
	
	
}
