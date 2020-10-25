package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierInfoEntity_HI Entity Object
 * Thu Mar 29 16:58:40 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pos_supplier_info")
public class SrmPosSupplierInfoEntity_HI {
    private Integer supplierId; //供应商ID
    private String supplierNumber; //供应商编码
    private String supplierName; //供应商名称
    private String supplierShortName; //供应商简称
    private String supplierType; //供应商类型(POS_SUPPLIER_TYPE)
    private String supplierClassify; //(废弃)供应商分类(POS_SUPPLIER_CLASSIFY)
    private String supplierIndustry; //供应商行业(POS_SUPPLIER_INDUSTRY)
    private String supplierStatus; //供应商状态(POS_SUPPLIER_STATUS)
    private String homeUrl; //公司官网
    private String companyPhone; //公司电话
    private String companyFax; //公司传真
    private Integer relatedSupplierId; //关联供应商ID 
    private Integer parentSupplierId; //父供应商ID 
    private Integer staffNum; //员工数量
    private String floorArea; //占地面积
    private String companyDescription; //公司简介
    private String purchaseFlag; //可采购标识
    private String paymentFlag; //可付款标识
    private String finClassify; //(废弃)财务分类(POS_FIN_CLASSIFY)
    private String settleAcctType; //(废弃)结算方式(POS_SETTLE_ACCT_TYPE)
    private String acctCheckStaff; //(废弃)对账人员编码(POS_ACCT_CHECK_CREW)
    private String acctCheckType; //(废弃)对账方式(POS_ACCT_CHECK_TYPE)
    private String passU9Flag; //(废弃)传U9标识
    private Integer supplierFileId; //公司简介附件
    private String posTax; //(废弃)税组合(POS_TAX_LIST)
    private String posAcctCondition; //(废弃)立账条件(POS_ACCOUNT_CONDITION)
    private String ableCheckOrderFlag; //(废弃)允许确认采购标识
    private String ableEditFlag; //(废弃)是否可修改
    private String address; //(废弃)供应商地址
    private String srmDelivery; //(废弃)允许平台送货
    private String logisticsSupplier; //(废弃)是否为物流供应商
    private String blacklistFlag; //黑名单供应商(Y/N)
    private String sourceCode; //供应商来源(REGISTER:注册，CREATE:创建，其他类型为其他系统来源)
    private String sourceId; //供应商来源ID（当供应商数据来源于其他系统时才有）
    private Integer approvalUserId; //审核人
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate; //审核通过时间
    private String approvalComments; //审核意见
	private Integer gradeLineId;//供应商级别行ID，srm_pos_supplier_grade_lines
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
	private String companyRegisteredAddress; //公司注册地址
	private String registeredCapital; //注册资金(万)
	private String independentLegalPersonFlag; //是否独立法人
	private String valueAddedTaxInvoiceFlag; //能否开6个税点的增值税专用发票
	private String valueAddedTaxInvoiceDesc; //能否开6个税点的增值税专用发票-说明
	private String associatedCompany; //关联公司
	private String region; //意向服务区域
	private String requestId;//报文请求ID
	private String supplierEbsNumber;//供应商EBS编号

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Id
	@SequenceGenerator(name = "SRM_POS_SUPPLIER_INFO_S", sequenceName = "SRM_POS_SUPPLIER_INFO_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_POS_SUPPLIER_INFO_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "supplier_id", nullable = false, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	@Column(name = "supplier_number", nullable = true, length = 50)	
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "supplier_name", nullable = false, length = 200)	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}

	@Column(name = "supplier_short_name", nullable = true, length = 200)	
	public String getSupplierShortName() {
		return supplierShortName;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	@Column(name = "supplier_type", nullable = true, length = 50)	
	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierClassify(String supplierClassify) {
		this.supplierClassify = supplierClassify;
	}

	@Column(name = "supplier_classify", nullable = true, length = 30)	
	public String getSupplierClassify() {
		return supplierClassify;
	}

	public void setSupplierIndustry(String supplierIndustry) {
		this.supplierIndustry = supplierIndustry;
	}

	@Column(name = "supplier_industry", nullable = true, length = 30)	
	public String getSupplierIndustry() {
		return supplierIndustry;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	@Column(name = "supplier_status", nullable = true, length = 30)	
	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	@Column(name = "home_url", nullable = true, length = 200)	
	public String getHomeUrl() {
		return homeUrl;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	@Column(name = "company_phone", nullable = true, length = 100)	
	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	@Column(name = "company_fax", nullable = true, length = 100)	
	public String getCompanyFax() {
		return companyFax;
	}

	public void setRelatedSupplierId(Integer relatedSupplierId) {
		this.relatedSupplierId = relatedSupplierId;
	}

	@Column(name = "related_supplier_id", nullable = true, length = 11)	
	public Integer getRelatedSupplierId() {
		return relatedSupplierId;
	}

	public void setParentSupplierId(Integer parentSupplierId) {
		this.parentSupplierId = parentSupplierId;
	}

	@Column(name = "parent_supplier_id", nullable = true, length = 11)	
	public Integer getParentSupplierId() {
		return parentSupplierId;
	}

	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}

	@Column(name = "staff_num", nullable = true, length = 11)	
	public Integer getStaffNum() {
		return staffNum;
	}

	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}

	@Column(name = "floor_area", nullable = true, length = 30)	
	public String getFloorArea() {
		return floorArea;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	@Column(name = "company_description", nullable = true, length = 4000)
	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}

	@Column(name = "purchase_flag", nullable = true, length = 10)	
	public String getPurchaseFlag() {
		return purchaseFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	@Column(name = "payment_flag", nullable = true, length = 10)	
	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setFinClassify(String finClassify) {
		this.finClassify = finClassify;
	}

	@Column(name = "fin_classify", nullable = true, length = 30)	
	public String getFinClassify() {
		return finClassify;
	}

	public void setSettleAcctType(String settleAcctType) {
		this.settleAcctType = settleAcctType;
	}

	@Column(name = "settle_acct_type", nullable = true, length = 30)	
	public String getSettleAcctType() {
		return settleAcctType;
	}

	public void setAcctCheckStaff(String acctCheckStaff) {
		this.acctCheckStaff = acctCheckStaff;
	}

	@Column(name = "acct_check_staff", nullable = true, length = 50)	
	public String getAcctCheckStaff() {
		return acctCheckStaff;
	}

	public void setAcctCheckType(String acctCheckType) {
		this.acctCheckType = acctCheckType;
	}

	@Column(name = "acct_check_type", nullable = true, length = 50)	
	public String getAcctCheckType() {
		return acctCheckType;
	}

	public void setPassU9Flag(String passU9Flag) {
		this.passU9Flag = passU9Flag;
	}

	@Column(name = "pass_u9_flag", nullable = true, length = 30)	
	public String getPassU9Flag() {
		return passU9Flag;
	}

	public void setSupplierFileId(Integer supplierFileId) {
		this.supplierFileId = supplierFileId;
	}

	@Column(name = "supplier_file_id", nullable = true, length = 11)	
	public Integer getSupplierFileId() {
		return supplierFileId;
	}

	public void setPosTax(String posTax) {
		this.posTax = posTax;
	}

	@Column(name = "pos_tax", nullable = true, length = 30)	
	public String getPosTax() {
		return posTax;
	}

	public void setPosAcctCondition(String posAcctCondition) {
		this.posAcctCondition = posAcctCondition;
	}

	@Column(name = "pos_acct_condition", nullable = true, length = 30)	
	public String getPosAcctCondition() {
		return posAcctCondition;
	}

	public void setAbleCheckOrderFlag(String ableCheckOrderFlag) {
		this.ableCheckOrderFlag = ableCheckOrderFlag;
	}

	@Column(name = "able_check_order_flag", nullable = true, length = 10)	
	public String getAbleCheckOrderFlag() {
		return ableCheckOrderFlag;
	}

	public void setAbleEditFlag(String ableEditFlag) {
		this.ableEditFlag = ableEditFlag;
	}

	@Column(name = "able_edit_flag", nullable = true, length = 10)	
	public String getAbleEditFlag() {
		return ableEditFlag;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "address", nullable = true, length = 200)	
	public String getAddress() {
		return address;
	}

	public void setSrmDelivery(String srmDelivery) {
		this.srmDelivery = srmDelivery;
	}

	@Column(name = "srm_delivery", nullable = true, length = 5)	
	public String getSrmDelivery() {
		return srmDelivery;
	}

	public void setLogisticsSupplier(String logisticsSupplier) {
		this.logisticsSupplier = logisticsSupplier;
	}

	@Column(name = "logistics_supplier", nullable = true, length = 5)	
	public String getLogisticsSupplier() {
		return logisticsSupplier;
	}

	public void setBlacklistFlag(String blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	@Column(name = "blacklist_flag", nullable = true, length = 1)	
	public String getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = true, length = 30)	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 30)	
	public String getSourceId() {
		return sourceId;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	@Column(name = "approval_user_id", nullable = true, length = 11)	
	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(name = "approval_date", nullable = true, length = 0)	
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	@Column(name = "approval_comments", nullable = true, length = 2000)	
	public String getApprovalComments() {
		return approvalComments;
	}

	@Column(name = "grade_line_id", nullable = true, length = 11)
	public Integer getGradeLineId() {
		return gradeLineId;
	}

	public void setGradeLineId(Integer gradeLineId) {
		this.gradeLineId = gradeLineId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setCompanyRegisteredAddress(String companyRegisteredAddress) {
		this.companyRegisteredAddress = companyRegisteredAddress;
	}

	@Column(name = "company_Registered_Address", nullable = true, length = 240)
	public String getCompanyRegisteredAddress() {
		return companyRegisteredAddress;
	}

	@Column(name = "registered_Capital", nullable = true, length = 30)
	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "independent_Legal_Person_Flag", nullable = true, length = 10)
	public String getIndependentLegalPersonFlag() {
		return independentLegalPersonFlag;
	}

	public void setIndependentLegalPersonFlag(String independentLegalPersonFlag) {
		this.independentLegalPersonFlag = independentLegalPersonFlag;
	}

	@Column(name = "value_Added_Tax_Invoice_Flag", nullable = true, length = 10)
	public String getValueAddedTaxInvoiceFlag() {
		return valueAddedTaxInvoiceFlag;
	}

	public void setValueAddedTaxInvoiceFlag(String valueAddedTaxInvoiceFlag) {
		this.valueAddedTaxInvoiceFlag = valueAddedTaxInvoiceFlag;
	}

	@Column(name = "value_Added_Tax_Invoice_Desc", nullable = true, length = 240)
	public String getValueAddedTaxInvoiceDesc() {
		return valueAddedTaxInvoiceDesc;
	}

	public void setValueAddedTaxInvoiceDesc(String valueAddedTaxInvoiceDesc) {
		this.valueAddedTaxInvoiceDesc = valueAddedTaxInvoiceDesc;
	}

	@Column(name = "associated_Company", nullable = true, length = 240)
	public String getAssociatedCompany() {
		return associatedCompany;
	}

	public void setAssociatedCompany(String associatedCompany) {
		this.associatedCompany = associatedCompany;
	}

	@Column(name = "region", nullable = true, length = 100)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "request_id", nullable = true, length = 200)
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Column(name = "supplier_ebs_number", nullable = true, length = 240)
	public String getSupplierEbsNumber() {
		return supplierEbsNumber;
	}

	public void setSupplierEbsNumber(String supplierEbsNumber) {
		this.supplierEbsNumber = supplierEbsNumber;
	}
}
