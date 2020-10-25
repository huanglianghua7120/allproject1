package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierCredentialsEntity_HI Entity Object
 * Thu Mar 29 16:58:45 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pos_supplier_credentials")
public class SrmPosSupplierCredentialsEntity_HI {
    private Integer credentialsId; //资质信息ID
    private Integer supplierId; //供应商信息ID，关联表:srm_pos_supplier_info
    private String currencyCode; //币种首选项
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date annualInspectionDate; //年检日期
    private Integer businessIndate; //营业期限(已废弃)
    private String longBusinessIndate; //营业期限是否长期有效
    private String enterpriseType; //企业性质（POS_ENTERPRISE_TYPE）
    private String muchInOne; //多证合一
    private String licenseNum; //营业执照号
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date licenseIndate; //营业期限
    private String longLicenseIndate; //营业执照号是否长期有效
    private String taxCode; //税务登记证号
    private String tissueCode; //组织机构代码
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date tissueIndate; //组织机构代码到期日
    private String longTissueIndate; //组织机构代码是否长期有效
    private String businessScope; //营业范围
    private String representativeName; //法人代表姓名
    private String bankPermitNumber; //开户银行许可证号
    private String corporateIdentity; //法人代表身份证号
    private String overseasRegCertificate; //境外供应商注册登记证
    private String overseasRegNumber; //境外供应商注册登记号
    private Integer enrollCapital; //注册资金（万元）
    private String registeredAddress; //注册地址
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date establishmentDate; //成立日期
    private String noProvidedIdentity; //是否不能提供法人身份证
    private String licenseArea; //营业执照地区
    private String licenseAddr; //营业执照地址
    private Integer licenseFileId; //营业执照副本复印件
    private Integer tissueFileId; //组织机构代码副本复印件
    private Integer bankPermitFileId; //开户银行许可证副本复印件
    private Integer taxFileId; //税务登记副本复印件
    private Integer othersFileId; //其他附件
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
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

	public void setCredentialsId(Integer credentialsId) {
		this.credentialsId = credentialsId;
	}

	@Id
	@SequenceGenerator(name = "SRM_POS_SUPPLIER_INFO_S", sequenceName = "SRM_POS_SUPPLIER_INFO_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_POS_SUPPLIER_INFO_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "credentials_id", nullable = false, length = 11)	
	public Integer getCredentialsId() {
		return credentialsId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = false, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setAnnualInspectionDate(Date annualInspectionDate) {
		this.annualInspectionDate = annualInspectionDate;
	}

	@Column(name = "annual_inspection_date", nullable = true, length = 0)	
	public Date getAnnualInspectionDate() {
		return annualInspectionDate;
	}

	public void setBusinessIndate(Integer businessIndate) {
		this.businessIndate = businessIndate;
	}

	@Column(name = "business_indate", nullable = true, length = 11)	
	public Integer getBusinessIndate() {
		return businessIndate;
	}

	public void setLongBusinessIndate(String longBusinessIndate) {
		this.longBusinessIndate = longBusinessIndate;
	}

	@Column(name = "long_business_indate", nullable = true, length = 30)	
	public String getLongBusinessIndate() {
		return longBusinessIndate;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	@Column(name = "enterprise_type", nullable = true, length = 30)	
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setMuchInOne(String muchInOne) {
		this.muchInOne = muchInOne;
	}

	@Column(name = "much_in_one", nullable = true, length = 10)
	public String getMuchInOne() {
		return muchInOne;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	@Column(name = "license_num", nullable = true, length = 100)	
	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseIndate(Date licenseIndate) {
		this.licenseIndate = licenseIndate;
	}

	@Column(name = "license_indate", nullable = true, length = 0)	
	public Date getLicenseIndate() {
		return licenseIndate;
	}

	public void setLongLicenseIndate(String longLicenseIndate) {
		this.longLicenseIndate = longLicenseIndate;
	}

	@Column(name = "long_license_indate", nullable = true, length = 30)	
	public String getLongLicenseIndate() {
		return longLicenseIndate;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Column(name = "tax_code", nullable = true, length = 100)	
	public String getTaxCode() {
		return taxCode;
	}

	public void setTissueCode(String tissueCode) {
		this.tissueCode = tissueCode;
	}

	@Column(name = "tissue_code", nullable = true, length = 100)	
	public String getTissueCode() {
		return tissueCode;
	}

	public void setTissueIndate(Date tissueIndate) {
		this.tissueIndate = tissueIndate;
	}

	@Column(name = "tissue_indate", nullable = true, length = 0)	
	public Date getTissueIndate() {
		return tissueIndate;
	}

	public void setLongTissueIndate(String longTissueIndate) {
		this.longTissueIndate = longTissueIndate;
	}

	@Column(name = "long_tissue_indate", nullable = true, length = 10)	
	public String getLongTissueIndate() {
		return longTissueIndate;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	@Column(name = "business_scope", nullable = true, length = 240)	
	public String getBusinessScope() {
		return businessScope;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	@Column(name = "representative_name", nullable = true, length = 200)	
	public String getRepresentativeName() {
		return representativeName;
	}

	public void setBankPermitNumber(String bankPermitNumber) {
		this.bankPermitNumber = bankPermitNumber;
	}

	@Column(name = "bank_permit_number", nullable = true, length = 100)	
	public String getBankPermitNumber() {
		return bankPermitNumber;
	}

	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}

	@Column(name = "corporate_identity", nullable = true, length = 30)	
	public String getCorporateIdentity() {
		return corporateIdentity;
	}

	public void setOverseasRegCertificate(String overseasRegCertificate) {
		this.overseasRegCertificate = overseasRegCertificate;
	}

	@Column(name = "overseas_reg_certificate", nullable = true, length = 100)	
	public String getOverseasRegCertificate() {
		return overseasRegCertificate;
	}

	public void setOverseasRegNumber(String overseasRegNumber) {
		this.overseasRegNumber = overseasRegNumber;
	}

	@Column(name = "overseas_reg_number", nullable = true, length = 100)	
	public String getOverseasRegNumber() {
		return overseasRegNumber;
	}

	public void setEnrollCapital(Integer enrollCapital) {
		this.enrollCapital = enrollCapital;
	}

	@Column(name = "enroll_capital", nullable = true, length = 11)	
	public Integer getEnrollCapital() {
		return enrollCapital;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	@Column(name = "registered_address", nullable = true, length = 240)	
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	@Column(name = "establishment_date", nullable = true, length = 0)	
	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setNoProvidedIdentity(String noProvidedIdentity) {
		this.noProvidedIdentity = noProvidedIdentity;
	}

	@Column(name = "no_provided_identity", nullable = true, length = 10)	
	public String getNoProvidedIdentity() {
		return noProvidedIdentity;
	}

	public void setLicenseArea(String licenseArea) {
		this.licenseArea = licenseArea;
	}

	@Column(name = "license_area", nullable = true, length = 10)	
	public String getLicenseArea() {
		return licenseArea;
	}

	public void setLicenseAddr(String licenseAddr) {
		this.licenseAddr = licenseAddr;
	}

	@Column(name = "license_addr", nullable = true, length = 500)	
	public String getLicenseAddr() {
		return licenseAddr;
	}

	public void setLicenseFileId(Integer licenseFileId) {
		this.licenseFileId = licenseFileId;
	}

	@Column(name = "license_file_id", nullable = true, length = 11)	
	public Integer getLicenseFileId() {
		return licenseFileId;
	}

	public void setTissueFileId(Integer tissueFileId) {
		this.tissueFileId = tissueFileId;
	}

	@Column(name = "tissue_file_id", nullable = true, length = 11)	
	public Integer getTissueFileId() {
		return tissueFileId;
	}

	public void setBankPermitFileId(Integer bankPermitFileId) {
		this.bankPermitFileId = bankPermitFileId;
	}

	@Column(name = "bank_permit_file_id", nullable = true, length = 11)	
	public Integer getBankPermitFileId() {
		return bankPermitFileId;
	}

	public void setTaxFileId(Integer taxFileId) {
		this.taxFileId = taxFileId;
	}

	@Column(name = "tax_file_id", nullable = true, length = 11)	
	public Integer getTaxFileId() {
		return taxFileId;
	}

	public void setOthersFileId(Integer othersFileId) {
		this.othersFileId = othersFileId;
	}

	@Column(name = "others_file_id", nullable = true, length = 11)	
	public Integer getOthersFileId() {
		return othersFileId;
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
}
