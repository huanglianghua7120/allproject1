package saaf.common.fmw.cua.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmCuaInvoiceLinesEntity_HI_RO Entity Object
 * Fri Nov 09 17:08:43 CST 2018  Auto Generate
 */

public class SrmCuaInvoiceLinesEntity_HI_RO {
	//查询发票的对账单明细
	public static final String QUERY_SQL ="select\r\n" + 
			"scil.invoice_line_id invoiceLineId,\r\n" + 
			"scil.invoice_id invoiceId,\r\n" + 
			"sca.account_id accountId,\r\n" +
			"sca.account_code accountCode,\r\n" + 
			"sca.receive_tax_amount + sca.return_tax_amount taxAmount,\r\n" + 
			"sca.receive_amount + sca.return_amount amount,\r\n" + 
			"sca.receive_tax_amount + sca.return_tax_amount - (sca.receive_amount + sca.return_amount) taxMoney,\r\n" +
			"(select scal.tax_rate from srm_cua_account_lines scal where sca.account_id = scal.ACCOUNT_ID GROUP BY scal.TAX_RATE) taxRate,\r\n" +
			"(select scal.CURRENCY_CODE from srm_cua_account_lines scal where sca.account_id = scal.ACCOUNT_ID GROUP BY scal.CURRENCY_CODE) currencyCode\r\n" +
			"from srm_cua_invoice_lines scil\r\n" + 
			"left join srm_cua_accounts sca on scil.account_id = sca.account_id\r\n" +
			"WHERE\n" +
	        "\t1 = 1 ";
	//查询发票中可以添加的对账单
	public static final String QUERY_INVOICE_ACCOUNT_SQL = "select\r\n" + 
			"account_id accountId,\r\n" + 
			"account_code accountCode,\r\n" + 
			"sca.RECEIVE_TAX_AMOUNT + sca.RETURN_TAX_AMOUNT taxAmount,\r\n" + 
			"sca.receive_tax_amount + sca.return_tax_amount - (sca.receive_amount + sca.return_amount) taxMoney,\r\n" + 
			"sca.RECEIVE_AMOUNT + sca.RETURN_AMOUNT amount,\r\n" + 
			"(select scal.tax_rate from srm_cua_account_lines scal where sca.account_id = scal.ACCOUNT_ID GROUP BY scal.TAX_RATE) taxRate,\r\n" +
			"(select scal.CURRENCY_CODE from srm_cua_account_lines scal where sca.account_id = scal.ACCOUNT_ID GROUP BY scal.CURRENCY_CODE) currencyCode\r\n" +
			"from srm_cua_accounts sca \r\n" + 
			"where \n" +
			"\t 1 = 1 and sca.account_status='AFFIRM' \r";
    private Integer invoiceLineId;	//发票行id
    private Integer invoiceId;	//发票id
    private Integer accountId;	//对账单id
    private Integer versionNum;	//行版本号
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
    //自定义
    private String accountCode; //对账编号
    private java.math.BigDecimal taxAmount; //对账总金额(含税)
    private java.math.BigDecimal amount; //对账总金额(不含税)
    private java.math.BigDecimal taxRate; //税率
    private String currencyCode;  //币种
    private java.math.BigDecimal taxMoney; //税额

	public void setInvoiceLineId(Integer invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	
	public Integer getInvoiceLineId() {
		return invoiceLineId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	
	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	
	public Integer getAccountId() {
		return accountId;
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


	public java.math.BigDecimal getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}


	public java.math.BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}


	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public java.math.BigDecimal getTaxMoney() {
		return taxMoney;
	}


	public void setTaxMoney(java.math.BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}


	public static String getQuerySql() {
		return QUERY_SQL;
	}


	public String getAccountCode() {
		return accountCode;
	}


	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	
}
