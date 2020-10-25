package saaf.common.fmw.intf.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierInfoBean {
	private String U9MessageNO;
	private String JsonNO; // 报文号 系统名+当前时间截
	private String Code; // 供应商代码
	private String Name; // 供应商名称
	private String OperateType; // 操作类型 0新增1仅修改供应商主档2修改 供应商主档
								// 及添加供应商位置[供应商位置一旦添加不可修改]
	private String FromDate; // 生效时间
	private String ToDate; // 失效时间
	private String LocationCode; // 供应商位置编码
	private String LocationName; // 供应商位置名称
	private String CanPurchase; // 可采购 是:1
	private String CanPay; // 可付款
	private String OrgCode; // Homa 组织代码
	private String ShortName; // 测试供应商简称
	private String AssetCategory; // 不填
	private String BankAccount; //
	private String BankName; //
	private String AccountCur; //
	private String AccountName; //
	private String State; // 状态 0 失效 1 生效
	private String SettleMode; // 结算方式
	private String CheckMode; // 对账方式
	private String CheckAccounter; // 对账人员
	private String ShipTo; // Y 出货位置
	private String IsBillTo; // Y 开票位置
	private String Claim; // Y 索赔位置
	private String Inquire; // Y 询价位置
	private String Contrast; // Y 对账单位置
	private String Remit; // Y 汇款位置
	private String BillTo; // Y发票位置
	private String IsDefaultShipTo; // Y 默认出货位置
	private String IsDefaultIsBillTo; // Y 默认开票位置
	private String IsDefaultClaim; // Y 默认索赔位置
	private String IsDefaultInquire; // Y 默认询价位置
	private String IsDefaultContrast; // Y 默认对账单位置
	private String IsDefaultRemit; // Y 默认汇款位置
	private String CateGoryCode; // 供应商分类
	private String ReceiptRuleCode; // 收货原则
	private String CheckCurrencyCode; // 核币币种
	private String PaymentTermCode; // 付款条件
	private String APConfirmTermlCode; // 立账条件
	private String TaxScheduleCode; // 税组合
	private String SRMSupplier; // 是否SRM平台打单
	
	@JSONField(name = "U9MessageNO")
	public String getU9MessageNO() {
		return U9MessageNO;
	}
	@JSONField(name = "JsonNO")
	public String getJsonNO() {
		return JsonNO;
	}
	@JSONField(name = "Code")
	public String getCode() {
		return Code;
	}
	@JSONField(name = "Name")
	public String getName() {
		return Name;
	}
	@JSONField(name = "OperateType")
	public String getOperateType() {
		return OperateType;
	}
	@JSONField(name = "FromDate")
	public String getFromDate() {
		return FromDate;
	}
	@JSONField(name = "ToDate")
	public String getToDate() {
		return ToDate;
	}
	@JSONField(name = "LocationCode")
	public String getLocationCode() {
		return LocationCode;
	}
	@JSONField(name = "LocationName")
	public String getLocationName() {
		return LocationName;
	}
	@JSONField(name = "CanPurchase")
	public String getCanPurchase() {
		return CanPurchase;
	}
	@JSONField(name = "CanPay")
	public String getCanPay() {
		return CanPay;
	}
	@JSONField(name = "OrgCode")
	public String getOrgCode() {
		return OrgCode;
	}
	@JSONField(name = "ShortName")
	public String getShortName() {
		return ShortName;
	}
	@JSONField(name = "AssetCategory")
	public String getAssetCategory() {
		return AssetCategory;
	}
	 
	@JSONField(name = "BankAccount")
	public String getBankAccount() {
		return BankAccount;
	}
	@JSONField(name = "BankName")
	public String getBankName() {
		return BankName;
	}
	@JSONField(name = "AccountCur")
	public String getAccountCur() {
		return AccountCur;
	}
	@JSONField(name = "AccountName")
	public String getAccountName() {
		return AccountName;
	}
	@JSONField(name = "State")
	public String getState() {
		return State;
	}
	@JSONField(name = "SettleMode")
	public String getSettleMode() {
		return SettleMode;
	}
	@JSONField(name = "CheckMode")
	public String getCheckMode() {
		return CheckMode;
	}
	@JSONField(name = "CheckAccounter")
	public String getCheckAccounter() {
		return CheckAccounter;
	}
	@JSONField(name = "ShipTo")
	public String getShipTo() {
		return ShipTo;
	}
	@JSONField(name = "IsBillTo")
	public String getIsBillTo() {
		return IsBillTo;
	}
	@JSONField(name = "Claim")
	public String getClaim() {
		return Claim;
	}
	@JSONField(name = "Inquire")
	public String getInquire() {
		return Inquire;
	}
	@JSONField(name = "Contrast")
	public String getContrast() {
		return Contrast;
	}
	@JSONField(name = "Remit")
	public String getRemit() {
		return Remit;
	}
	@JSONField(name = "BillTo")
	public String getBillTo() {
		return BillTo;
	}
	@JSONField(name = "IsDefaultShipTo")
	public String getIsDefaultShipTo() {
		return IsDefaultShipTo;
	}
	@JSONField(name = "IsDefaultIsBillTo")
	public String getIsDefaultIsBillTo() {
		return IsDefaultIsBillTo;
	}
	@JSONField(name = "IsDefaultClaim")
	public String getIsDefaultClaim() {
		return IsDefaultClaim;
	}
	@JSONField(name = "IsDefaultInquire")
	public String getIsDefaultInquire() {
		return IsDefaultInquire;
	}
	@JSONField(name = "IsDefaultContrast")
	public String getIsDefaultContrast() {
		return IsDefaultContrast;
	}
	@JSONField(name = "IsDefaultRemit")
	public String getIsDefaultRemit() {
		return IsDefaultRemit;
	}
	@JSONField(name = "CateGoryCode")
	public String getCateGoryCode() {
		return CateGoryCode;
	}
	@JSONField(name = "ReceiptRuleCode")
	public String getReceiptRuleCode() {
		return ReceiptRuleCode;
	}
	@JSONField(name = "CheckCurrencyCode")
	public String getCheckCurrencyCode() {
		return CheckCurrencyCode;
	}
	@JSONField(name = "PaymentTermCode")
	public String getPaymentTermCode() {
		return PaymentTermCode;
	}
	@JSONField(name = "APConfirmTermlCode")
	public String getAPConfirmTermlCode() {
		return APConfirmTermlCode;
	}
	@JSONField(name = "TaxScheduleCode")
	public String getTaxScheduleCode() {
		return TaxScheduleCode;
	}
	public void setU9MessageNO(String u9MessageNO) {
		U9MessageNO = u9MessageNO;
	}
	public void setJsonNO(String jsonNO) {
		JsonNO = jsonNO;
	}
	public void setCode(String code) {
		Code = code;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setOperateType(String operateType) {
		OperateType = operateType;
	}
	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}
	public void setToDate(String toDate) {
		ToDate = toDate;
	}
	public void setLocationCode(String locationCode) {
		LocationCode = locationCode;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	public void setCanPurchase(String canPurchase) {
		CanPurchase = canPurchase;
	}
	public void setCanPay(String canPay) {
		CanPay = canPay;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public void setShortName(String shortName) {
		ShortName = shortName;
	}
	public void setAssetCategory(String assetCategory) {
		AssetCategory = assetCategory;
	}
	 
	public void setBankAccount(String bankAccount) {
		BankAccount = bankAccount;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public void setAccountCur(String accountCur) {
		AccountCur = accountCur;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public void setState(String state) {
		State = state;
	}
	public void setSettleMode(String settleMode) {
		SettleMode = settleMode;
	}
	public void setCheckMode(String checkMode) {
		CheckMode = checkMode;
	}
	public void setCheckAccounter(String checkAccounter) {
		CheckAccounter = checkAccounter;
	}
	public void setShipTo(String shipTo) {
		ShipTo = shipTo;
	}
	public void setIsBillTo(String isBillTo) {
		IsBillTo = isBillTo;
	}
	public void setClaim(String claim) {
		Claim = claim;
	}
	public void setInquire(String inquire) {
		Inquire = inquire;
	}
	public void setContrast(String contrast) {
		Contrast = contrast;
	}
	public void setRemit(String remit) {
		Remit = remit;
	}
	public void setBillTo(String billTo) {
		BillTo = billTo;
	}
	public void setIsDefaultShipTo(String isDefaultShipTo) {
		IsDefaultShipTo = isDefaultShipTo;
	}
	public void setIsDefaultIsBillTo(String isDefaultIsBillTo) {
		IsDefaultIsBillTo = isDefaultIsBillTo;
	}
	public void setIsDefaultClaim(String isDefaultClaim) {
		IsDefaultClaim = isDefaultClaim;
	}
	public void setIsDefaultInquire(String isDefaultInquire) {
		IsDefaultInquire = isDefaultInquire;
	}
	public void setIsDefaultContrast(String isDefaultContrast) {
		IsDefaultContrast = isDefaultContrast;
	}
	public void setIsDefaultRemit(String isDefaultRemit) {
		IsDefaultRemit = isDefaultRemit;
	}
	public void setCateGoryCode(String cateGoryCode) {
		CateGoryCode = cateGoryCode;
	}
	public void setReceiptRuleCode(String receiptRuleCode) {
		ReceiptRuleCode = receiptRuleCode;
	}
	public void setCheckCurrencyCode(String checkCurrencyCode) {
		CheckCurrencyCode = checkCurrencyCode;
	}
	public void setPaymentTermCode(String paymentTermCode) {
		PaymentTermCode = paymentTermCode;
	}
	public void setAPConfirmTermlCode(String aPConfirmTermlCode) {
		APConfirmTermlCode = aPConfirmTermlCode;
	}
	public void setTaxScheduleCode(String taxScheduleCode) {
		TaxScheduleCode = taxScheduleCode;
	}
	
	@JSONField(name = "SRMSupplier")
	public String getSRMSupplier() {
		return SRMSupplier;
	}
	public void setSRMSupplier(String sRMSupplier) {
		SRMSupplier = sRMSupplier;
	}

	

}
