package saaf.common.fmw.cua.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmCuaAccountsEntity_HI_RO Entity Object
 * Tue Oct 30 11:45:29 CST 2018  Auto Generate
 */

public class SrmCuaAccountsEntity_HI_RO {
	//供应商lov
	public static final String QUERY_SUPPLIER_LOV = "SELECT\r\n" +
			"si.supplier_id supplierId,\r\n" +
			"si.supplier_name supplierName,\r\n" +
			"si.supplier_number supplierNumber,\r\n" +
			"si.supplier_status supplierStatus\r\n" +
			"FROM srm_pos_supplier_info si\r\n" +
			"WHERE 1 = 1 ";

	/**
	 * 查询没有冻结和退出的供应商信息
	 */
	public static final String QUERY_VALID_SUPPLIER = "select " +
			"       spsi.supplier_id     supplierId, " +
			"       spsi.supplier_number supplierNumber, " +
			"       spsi.supplier_name   supplierName, " +
			"       spsi.supplier_status supplierStatus, " +
			"       slv.meaning          supplierStatusMeaning " +
			"  from srm_pos_supplier_info spsi, saaf_lookup_values slv " +
			" where slv.lookup_type = 'POS_SUPPLIER_STATUS' " +
			"   and slv.lookup_code = spsi.supplier_status " ;

	//与账户关联业务实体lov
	public static final String QUERY_INSTITUTION_LOV =
					"SELECT t2.user_id   userId\n" +
					"      ,t1.inst_id   instId\n" +
					"      ,t1.inst_name instName\n" +
					"      ,t1.inst_code instCode\n" +
					"FROM   saaf_institution      t1\n" +
					"      ,saaf_user_access_orgs t2\n" +
					"WHERE  t1.inst_id = t2.inst_id\n" +
					"AND    t1.start_date <= trunc(SYSDATE)\n" +
					"AND    nvl(t1.end_date, trunc(SYSDATE)) >= trunc(SYSDATE)\n";

	//不与账户关联的业务实体lov
	public static final String QUERY_INSTITUTION_LOV_NO_CONNECTION =
					"SELECT t1.inst_id   instId\n" +
					"      ,t1.inst_name instName\n" +
					"      ,t1.inst_code instCode\n" +
					"FROM   saaf_institution t1\n" +
					"WHERE  t1.start_date <= trunc(SYSDATE)\n" +
					"AND    nvl(t1.end_date, trunc(SYSDATE)) >= trunc(SYSDATE)\n" +
					"AND    t1.inst_type = 'ORG'\n";

	//查询对账单列表信息
	 public static final String QUERY_ACCOUNTS_SQL =
					"SELECT sca.account_id accountId\n" +
					"      ,sca.account_code accountCode\n" +
					"      ,spsi.supplier_name supplierName\n" +
					"      ,si.inst_name instName\n" +
					"      ,sca.receive_tax_amount\n" +
					"      ,sca.return_tax_amount taxAmount\n" +
					"      ,sca.receive_amount\n" +
					"      ,sca.return_amount amount\n" +
					"      ,(SELECT scal.currency_code\n" +
					"        FROM   srm_cua_account_lines scal\n" +
					"        WHERE  sca.account_id = scal.account_id\n" +
					"        GROUP  BY scal.currency_code) currencyCode\n" +
					"      ,sca.account_date accountDate\n" +
					"      ,sca.creation_date creationDate\n" +
					"      ,sca.confirm_date confirmDate\n" +
					"      ,sca.comments comments\n" +
					"      ,sca.account_status accountStatus\n" +
					"      ,su.user_full_name userName\n" +
					"      ,slv1.meaning accountStatusName\n" +
					"FROM   srm_cua_accounts sca\n" +
					"LEFT   JOIN srm_pos_supplier_info spsi\n" +
					"ON     sca.supplier_id = spsi.supplier_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     sca.org_id = si.inst_id\n" +
					"AND    si.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     sca.created_by = su.user_id\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     sca.account_status = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'SNBC_DZ_DOCUMENT_STATUS'\n" +
					"WHERE  1 = 1\n";

	//查询对账单详情信息
	 public static final String  QUERY_ACCOUNT_DETAIL_SQL =
					"SELECT sca.account_id accountId\n" +
					"      ,sca.supplier_id supplierId\n" +
					"      ,sca.org_id instId\n" +
					"      ,si.inst_name instName\n" +
					"      ,spsi.supplier_name supplierName\n" +
					"      ,sca.receive_tax_amount receiveTaxAmount\n" +
					"      ,sca.return_tax_amount returnTaxAmount\n" +
					"      ,sca.receive_tax_amount + sca.return_tax_amount taxAmount\n" +
					"      ,sca.receive_amount receiveAmount\n" +
					"      ,sca.return_amount returnAmount\n" +
					"      ,sca.receive_amount + sca.return_amount amount\n" +
					"      ,sca.account_code accountCode\n" +
					"      ,sca.creation_date creationDate\n" +
					"      ,sca.account_date accountDate\n" +
					"      ,sca.comments comments\n" +
					"      ,su.user_full_name userName\n" +
					"      ,su1.user_full_name updateUserName\n" +
					"      ,sca.last_updated_by lastUpdatedBy\n" +
					"      ,sca.last_update_date lastUpdateDate\n" +
					"      ,sca.confirm_date confirmDate\n" +
					"      ,sbrf.file_name fileName\n" +
					"      ,sbrf.access_path accessPath\n" +
					"      ,sca.account_status accountStatus\n" +
					"FROM   srm_cua_accounts sca\n" +
					"LEFT   JOIN srm_pos_supplier_info spsi\n" +
					"ON     sca.supplier_id = spsi.supplier_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     sca.org_id = si.inst_id\n" +
					"AND    si.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     sca.created_by = su.user_id\n" +
					"LEFT   JOIN saaf_users su1\n" +
					"ON     sca.last_updated_by = su1.user_id\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sca.account_file_id = sbrf.file_id\n" +
					"WHERE  1 = 1\n";

	//查询对账单日期是当天的数量
	 public static final String  QUERY_ACCOUNT_COUNT_SQL = "SELECT * FROM srm_cua_accounts WHERE trunc(creation_date) = trunc(SYSDATE)";
	    
     //查询今天最大的流水号
     public static final String QUERY_LAST_ACCOUNT_CODE =
			 		"SELECT MAX(sca.account_code) accountCode\n" +
					 "FROM   srm_cua_accounts sca\n" +
					 "WHERE  trunc(sca.creation_date) = trunc(SYSDATE)\n";

	public static String QUERY_ACCOUNT_REPORT =
					"SELECT ca.org_id                orgId\n" +
					"      ,si.inst_name             orgName\n" +
					"      ,ca.account_code          accountCode\n" +
					"      ,ci.invoice_code          invoiceCode\n" +
					"      ,ci.supplier_id           supplierId\n" +
					"      ,psi.supplier_name        supplierName\n" +
					"      ,psi.supplier_number      supplierNumber\n" +
					"      ,cal.po_code              poNumber\n" +
					"      ,cal.item_id              itemId\n" +
					"      ,cal.item_code            itemCode\n" +
					"      ,cal.item_name            itemName\n" +
					"      ,cal.shipment_num         shipmentNum\n" +
					"      ,cal.receipt_num          receiptNum\n" +
					"      ,cal.transaction_quantity deliveryNumber\n" +
					"      ,ca.account_date          accountDate\n" +
					"FROM   srm_cua_accounts      ca\n" +
					"      ,srm_cua_account_lines cal\n" +
					"      ,srm_cua_invoices      ci\n" +
					"      ,srm_cua_invoice_lines cil\n" +
					"      ,srm_pos_supplier_info psi\n" +
					"      ,srm_erp_po_lines      epl\n" +
					"      ,saaf_institution      si\n" +
					"WHERE  ca.account_id = cal.account_id\n" +
					"AND    ci.invoice_id = cil.invoice_id\n" +
					"AND    cil.account_id = ca.account_id\n" +
					"AND    psi.supplier_id = ci.supplier_id\n" +
					"AND    epl.po_header_id = cal.po_header_id\n" +
					"AND    epl.po_line_id = cal.po_line_id\n" +
					"AND    si.inst_id = ca.org_id\n";

	private Integer accountId;	//对账单id
    private String accountCode;	//对账单号
    private String invoiceCode;	//发票单号
    private Integer orgId;	//业务实体ID
    private Integer instId;	//业务实体ID备用名字
    private Integer supplierId;	//供应商id
    private String supplierName;   //供应商名字
	private String supplierStatus; // 供应商状态
    private String supplierNumber;   //供应商编号
    private String poNumber;   //采购订单号
    private Integer itemId;   //物料ID
    private String itemCode;   //物料编码
    private String itemName;   //物料名称
    private String shipmentNum;   //发运行号
    private String receiptNum;   //接收单号
    private BigDecimal deliveryNumber;   //对账数量
    private BigDecimal receivedQty;   //接收数量
    private BigDecimal returnToVendorQty;   //退货数量
    private String orgName;   //业务实体名字
    private String instName;  //业务实体备用名字
    private String userName;   //创建人名字
    private String updateUserName; //修改人名字
    private BigDecimal taxAmount; //对账总金额(含税)
    private BigDecimal amount; //对账总金额(不含税)
    private BigDecimal taxRate; //税率
    private String currencyCode;  //币种
    private String accountStatus; //单据状态
    private String accountStatusName; //单据状态名称
    @JSONField(format = "yyyy-MM-dd")
    private Date accountDate;	//对账日期
    @JSONField(format = "yyyy-MM-dd")
    private Date receivedDate;	//接收日期
    @JSONField(format = "yyyy-MM-dd")
    private Date returnToVendorDate;	//退货日期
    private BigDecimal receiveTaxAmount;	//接受总金额（含税）
    private BigDecimal returnTaxAmount;	//退货总金额（含税）
    private BigDecimal receiveAmount;		//接受总金额（不含税）
    private BigDecimal returnAmount;		//退货总金额（不含税）
    @JSONField(format = "yyyy-MM-dd")
    private Date confirmDate;	//确认日期
    private Integer accountFileId;
    private String comments;  //備注
    private String accessPath; //路径
    private String fileName; //文件名
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;	//创建日期
    private Integer createdBy;		//创建人id
    private Integer lastUpdatedBy;	//修改人id
    @JSONField(format = "yyyy-MM-dd")
    private Date lastUpdateDate;	//修改日期
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


	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}


	public String getAccountCode() {
		return accountCode;
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

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}


	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}


	public Date getAccountDate() {
		return accountDate;
	}

	public void setReceiveTaxAmount(BigDecimal receiveTaxAmount) {
		this.receiveTaxAmount = receiveTaxAmount;
	}


	public BigDecimal getReceiveTaxAmount() {
		return receiveTaxAmount;
	}

	public void setReturnTaxAmount(BigDecimal returnTaxAmount) {
		this.returnTaxAmount = returnTaxAmount;
	}


	public BigDecimal getReturnTaxAmount() {
		return returnTaxAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}


	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}


	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}


	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setAccountFileId(Integer accountFileId) {
		this.accountFileId = accountFileId;
	}


	public Integer getAccountFileId() {
		return accountFileId;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getComments() {
		return comments;
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


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUpdateUserName() {
		return updateUserName;
	}


	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}


	public BigDecimal getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public BigDecimal getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public static String getQueryAccountsSql() {
		return QUERY_ACCOUNTS_SQL;
	}


	public static String getQueryAccountDetailSql() {
		return QUERY_ACCOUNT_DETAIL_SQL;
	}


	public String getInstName() {
		return instName;
	}


	public void setInstName(String instName) {
		this.instName = instName;
	}


	public static String getQueryAccountCountSql() {
		return QUERY_ACCOUNT_COUNT_SQL;
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


	public static String getQueryLastAccountCode() {
		return QUERY_LAST_ACCOUNT_CODE;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getAccountStatusName() {
		return accountStatusName;
	}


	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public String getReceiptNum() {
		return receiptNum;
	}

	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}

	public BigDecimal getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(BigDecimal deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	public BigDecimal getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(BigDecimal receivedQty) {
		this.receivedQty = receivedQty;
	}

	public BigDecimal getReturnToVendorQty() {
		return returnToVendorQty;
	}

	public void setReturnToVendorQty(BigDecimal returnToVendorQty) {
		this.returnToVendorQty = returnToVendorQty;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getReturnToVendorDate() {
		return returnToVendorDate;
	}

	public void setReturnToVendorDate(Date returnToVendorDate) {
		this.returnToVendorDate = returnToVendorDate;
	}

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}
}
