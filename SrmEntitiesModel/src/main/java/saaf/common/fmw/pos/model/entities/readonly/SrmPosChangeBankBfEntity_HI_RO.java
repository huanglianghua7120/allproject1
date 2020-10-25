package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosChangeBankBfEntity_HI_RO Entity Object
 * Fri Mar 16 15:03:52 CST 2018  Auto Generate
 */

public class SrmPosChangeBankBfEntity_HI_RO {
    private Integer changeBankBfId; //供应商银行变更前ID
    private Integer changeId; //变更ID
    private Integer bankAccountId; //银行账户ID
    private Integer supplierId; //供应商ID，关联表:srm_pos_supplier_info
    private Integer supplierSiteId; //(废弃)供应商地点ID
    private String assignmentType; //(废弃)账户分配层（POS_ASSIGNMENT_TYPE）
    private String bankAccountNumber; //银行账号
    private String bankUserName; //账户名称
    private Integer bankId; //银行ID,关联表:srm_base_banks
    private String bankName; //(备用)银行名称
    private Integer branchId; //分行ID,关联表:srm_base_bank_branches
    private String branchNumber; //(备用)分行编号
    private String branchName; //(备用)分行名称
    private String bankCurrency; //币种(BASE_CURRENCY)
    private String swiftCode; //SWIFT CODE
    private String ibanCode; //IBAN
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //起始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //终止日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate; //失效日期
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDateBf; //变更前-失效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDateAf; //变更后-失效日期
    private Integer operatorUserId;

	public void setChangeBankBfId(Integer changeBankBfId) {
		this.changeBankBfId = changeBankBfId;
	}

	
	public Integer getChangeBankBfId() {
		return changeBankBfId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	
	public Integer getChangeId() {
		return changeId;
	}

	public void setBankAccountId(Integer bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	
	public Integer getBankAccountId() {
		return bankAccountId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	
	public String getAssignmentType() {
		return assignmentType;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	
	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	
	public Integer getBankId() {
		return bankId;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	public String getBankName() {
		return bankName;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}

	
	public String getBranchNumber() {
		return branchNumber;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
	public String getBranchName() {
		return branchName;
	}

	public void setBankCurrency(String bankCurrency) {
		this.bankCurrency = bankCurrency;
	}

	
	public String getBankCurrency() {
		return bankCurrency;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	
	public String getSwiftCode() {
		return swiftCode;
	}

	public void setIbanCode(String ibanCode) {
		this.ibanCode = ibanCode;
	}

	
	public String getIbanCode() {
		return ibanCode;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	
	public Date getInvalidDate() {
		return invalidDate;
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

	public void setInvalidDateBf(Date invalidDateBf) {
		this.invalidDateBf = invalidDateBf;
	}

	
	public Date getInvalidDateBf() {
		return invalidDateBf;
	}

	public void setInvalidDateAf(Date invalidDateAf) {
		this.invalidDateAf = invalidDateAf;
	}

	
	public Date getInvalidDateAf() {
		return invalidDateAf;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
