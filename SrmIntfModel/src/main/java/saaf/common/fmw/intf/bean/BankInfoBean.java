package saaf.common.fmw.intf.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class BankInfoBean {
	private String U9MessageNO;//u9对于接收到的每个报文进行的编号
	private String JsonNO   ;
	private String SupplierCode;
	private String IsDefault;   //是否默认银行账号   默认1
	private String BankCode;  //银行编码
    private String OrgCode;// Homa组织 拥有组织
    private String Currency;//币种
    private String IsDefaultCur;// 是否默认币种  默认1
    private String SupplierBankAccountCode;  //供应商银行账号
    private String SupplierBankAccountName; //供应商银行账号名称
	private String OperateType; // 操作类型 0、1新增  2修改 供应商主档
	@JSONField(name = "U9MessageNO")
	public String getU9MessageNO() {
		return U9MessageNO;
	}
	@JSONField(name = "JsonNO")
	public String getJsonNO() {
		return JsonNO;
	}
	@JSONField(name = "SupplierCode")
	public String getSupplierCode() {
		return SupplierCode;
	}
	@JSONField(name = "IsDefault")
	public String getIsDefault() {
		return IsDefault;
	}
	@JSONField(name = "BankCode")
	public String getBankCode() {
		return BankCode;
	}
	@JSONField(name = "OrgCode")
	public String getOrgCode() {
		return OrgCode;
	}
	@JSONField(name = "Currency")
	public String getCurrency() {
		return Currency;
	}
	@JSONField(name = "IsDefaultCur")
	public String getIsDefaultCur() {
		return IsDefaultCur;
	}
	@JSONField(name = "SupplierBankAccountCode")
	public String getSupplierBankAccountCode() {
		return SupplierBankAccountCode;
	}
	@JSONField(name = "SupplierBankAccountName")
	public String getSupplierBankAccountName() {
		return SupplierBankAccountName;
	}
	@JSONField(name = "OperateType")
	public String getOperateType() {
		return OperateType;
	}
	public void setU9MessageNO(String u9MessageNO) {
		U9MessageNO = u9MessageNO;
	}
	public void setJsonNO(String jsonNO) {
		JsonNO = jsonNO;
	}
	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}
	public void setIsDefault(String isDefault) {
		IsDefault = isDefault;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public void setIsDefaultCur(String isDefaultCur) {
		IsDefaultCur = isDefaultCur;
	}
	public void setSupplierBankAccountCode(String supplierBankAccountCode) {
		SupplierBankAccountCode = supplierBankAccountCode;
	}
	public void setSupplierBankAccountName(String supplierBankAccountName) {
		SupplierBankAccountName = supplierBankAccountName;
	}
	public void setOperateType(String operateType) {
		OperateType = operateType;
	}
	
	
	
}
