package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPosSupplierInfo2Entity_HI_RO implements Serializable {

	public static final String QUERY_SUPPLIER_INFO_SQL="SELECT  spsil.supplier_id supplierId, spsil.supplier_number supplierNumber, spsil.supplier_name supplierName, spsil.supplier_short_name supplierShortName, spsil.supplier_type supplierType, spsil.supplier_classify supplierClassify, spsil.supplier_industry supplierIndustry, spsil.supplier_status supplierStatus, spsil.home_url homeUrl, spsil.company_phone companyPhone, spsil.company_fax companyFax, spsil.related_supplier_id relatedSupplierId, spsil.parent_supplier_id parentSupplierId, spsil.staff_num staffNum, spsil.floor_area floorArea, spsil.company_description companyDescription, spsil.purchase_flag purchaseFlag, spsil.payment_flag paymentFlag, spsil.fin_classify finClassify, spsil.settle_acct_type settleAcctType, spsil.acct_check_staff acctCheckStaff, spsil.acct_check_type acctCheckType, spsil.VERSION_NUM versionNum, spsil.CREATION_DATE creationDate, spsil.CREATED_BY createdBy, spsil.LAST_UPDATED_BY lastUpdatedBy, spsil.LAST_UPDATE_DATE lastUpdateDate, spsil.LAST_UPDATE_LOGIN lastUpdateLogin, spsil.ATTRIBUTE_CATEGORY attributeCategory FROM srm_pos_supplier_info AS spsil WHERE 1 = 1 ";

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

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierShortName() {
		return supplierShortName;
	}
	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getSupplierClassify() {
		return supplierClassify;
	}
	public void setSupplierClassify(String supplierClassify) {
		this.supplierClassify = supplierClassify;
	}
	public String getSupplierIndustry() {
		return supplierIndustry;
	}
	public void setSupplierIndustry(String supplierIndustry) {
		this.supplierIndustry = supplierIndustry;
	}
	public String getSupplierStatus() {
		return supplierStatus;
	}
	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}
	public String getHomeUrl() {
		return homeUrl;
	}
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public Integer getRelatedSupplierId() {
		return relatedSupplierId;
	}
	public void setRelatedSupplierId(Integer relatedSupplierId) {
		this.relatedSupplierId = relatedSupplierId;
	}
	public Integer getParentSupplierId() {
		return parentSupplierId;
	}
	public void setParentSupplierId(Integer parentSupplierId) {
		this.parentSupplierId = parentSupplierId;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public String getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public String getFinClassify() {
		return finClassify;
	}
	public void setFinClassify(String finClassify) {
		this.finClassify = finClassify;
	}
	public String getSettleAcctType() {
		return settleAcctType;
	}
	public void setSettleAcctType(String settleAcctType) {
		this.settleAcctType = settleAcctType;
	}
	public String getAcctCheckStaff() {
		return acctCheckStaff;
	}
	public void setAcctCheckStaff(String acctCheckStaff) {
		this.acctCheckStaff = acctCheckStaff;
	}
	public String getAcctCheckType() {
		return acctCheckType;
	}
	public void setAcctCheckType(String acctCheckType) {
		this.acctCheckType = acctCheckType;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	public String getAttributeCategory() {
		return attributeCategory;
	}
	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getPassU9Flag() {
		return passU9Flag;
	}

	public void setPassU9Flag(String passU9Flag) {
		this.passU9Flag = passU9Flag;
	}

	public Integer getSupplierFileId() {
		return supplierFileId;
	}

	public void setSupplierFileId(Integer supplierFileId) {
		this.supplierFileId = supplierFileId;
	}

	public String getPosTax() {
		return posTax;
	}

	public void setPosTax(String posTax) {
		this.posTax = posTax;
	}

	public String getPosAcctCondition() {
		return posAcctCondition;
	}

	public void setPosAcctCondition(String posAcctCondition) {
		this.posAcctCondition = posAcctCondition;
	}

	public String getAbleCheckOrderFlag() {
		return ableCheckOrderFlag;
	}

	public void setAbleCheckOrderFlag(String ableCheckOrderFlag) {
		this.ableCheckOrderFlag = ableCheckOrderFlag;
	}

	public String getAbleEditFlag() {
		return ableEditFlag;
	}

	public void setAbleEditFlag(String ableEditFlag) {
		this.ableEditFlag = ableEditFlag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSrmDelivery() {
		return srmDelivery;
	}

	public void setSrmDelivery(String srmDelivery) {
		this.srmDelivery = srmDelivery;
	}

	public String getLogisticsSupplier() {
		return logisticsSupplier;
	}

	public void setLogisticsSupplier(String logisticsSupplier) {
		this.logisticsSupplier = logisticsSupplier;
	}

	public String getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(String blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Override
	public String toString() {
		return "SrmPosSupplierInfoEntity_HI_RO [supplierId=" + supplierId
				+ ", supplierNumber=" + supplierNumber + ", supplierName="
				+ supplierName + ", supplierShortName=" + supplierShortName
				+ ", supplierType=" + supplierType + ", supplierClassify="
				+ supplierClassify + ", supplierIndustry=" + supplierIndustry
				+ ", supplierStatus=" + supplierStatus + ", homeUrl=" + homeUrl
				+ ", companyPhone=" + companyPhone + ", companyFax="
				+ companyFax + ", relatedSupplierId=" + relatedSupplierId
				+ ", parentSupplierId=" + parentSupplierId + ", staffNum="
				+ staffNum + ", floorArea=" + floorArea
				+ ", companyDescription=" + companyDescription
				+ ", purchaseFlag=" + purchaseFlag + ", paymentFlag="
				+ paymentFlag + ", finClassify=" + finClassify
				+ ", settleAcctType=" + settleAcctType + ", acctCheckStaff="
				+ acctCheckStaff + ", acctCheckType=" + acctCheckType
				+ ", versionNum=" + versionNum + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", attributeCategory=" + attributeCategory + "]";
	}
		    
	
	
	

}
