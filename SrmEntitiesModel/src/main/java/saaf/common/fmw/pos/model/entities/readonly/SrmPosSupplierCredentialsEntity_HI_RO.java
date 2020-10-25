package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosSupplierCredentialsEntity_HI_RO Entity Object
 * Fri Mar 09 10:27:48 CST 2018  Auto Generate
 */

public class SrmPosSupplierCredentialsEntity_HI_RO {

	public static String QUERY_CREDENTIALS = "SELECT\n" +
			"\tt.credentials_id AS credentialsId,\n" +
			"\tt.supplier_id AS supplierId,\n" +
			"\tt.currency_code AS currencyCode,\n" +
			"\tt.annual_inspection_date AS annualInspectionDate,\n" +
			"\tt.business_indate AS businessIndate,\n" +
			"\tt.long_business_indate AS longBusinessIndate,\n" +
			"\tt.enterprise_type AS enterpriseType,\n" +
			"\tt.much_in_one AS muchInOne,\n" +
			"\tt.license_num AS licenseNum,\n" +
			"\tt.license_indate AS licenseIndate,\n" +
			"\tt.tax_code AS taxCode,\n" +
			"\tt.long_license_indate AS longLicenseIndate,\n" +
			"\ttissue_code AS tissueCode,\n" +
			"\tt.tissue_indate AS tissueIndate,\n" +
			"\tt.long_tissue_indate AS longTissueIndate,\n" +
			"\tt.business_scope AS businessScope,\n" +
			"\tt.representative_name AS representativeName,\n" +
			"\tt.bank_permit_number AS bankPermitNumber,\n" +
			"\tt.corporate_identity AS corporateIdentity,\n" +
			"\tt.overseas_reg_certificate AS overseasRegCertificate,\n" +
			"\tt.overseas_reg_number AS overseasRegNumber,\n" +
			"\tt.enroll_capital AS enrollCapital,\n" +
			"\tt.registered_address AS registeredAddress,\n" +
			"\tt.establishment_date AS establishmentDate,\n" +
			"\tt.no_provided_identity AS noProvidedIdentity,\n" +
			"\tt.license_area AS licenseArea,\n" +
			"\tt.license_addr AS licenseAddr,\n" +
			"\tt.license_file_id AS licenseFileId,\n" +
			"\tt.tissue_file_id AS tissueFileId,\n" +
			"\tt.bank_permit_file_id AS bankPermitFileId,\n" +
			"\tt.tax_file_id AS taxFileId,\n" +
			"\tt.others_file_id AS othersFileId,\n" +
			"\tt.source_code AS sourceCode,\n" +
			"\tt.source_id AS sourceId,\n" +
			"\tt.version_num AS versionNum,\n" +
			"\tt.creation_date AS creationDate,\n" +
			"\tt.created_by AS createdBy,\n" +
			"\tt.last_updated_by AS lastUpdatedBy,\n" +
			"\tt.last_update_date AS lastUpdateDate,\n" +
			"\tt.last_update_login AS lastUpdateLogin,\n" +
			"\tt.attribute_category AS attributeCategory,\n" +
			"\tt.attribute1 AS attribute1,\n" +
			"\tt.attribute2 AS attribute2,\n" +
			"\tt.attribute3 AS attribute3,\n" +
			"\tt.attribute4 AS attribute4,\n" +
			"\tt.attribute5 AS attribute5,\n" +
			"\tt.attribute6 AS attribute6,\n" +
			"\tt.attribute7 AS attribute7,\n" +
			"\tt.attribute8 AS attribute8,\n" +
			"\tt.attribute9 AS attribute9,\n" +
			"\tt.attribute10 AS attribute10,\n" +
			"\tsbrf1.access_Path AS licenseFilePath,\n" +
			"\tsbrf1.file_Name AS licenseFileName,\n" +
			"\tsbrf2.access_Path AS tissueFilePath,\n" +
			"\tsbrf2.file_Name AS tissueFileName,\n" +
			"\tsbrf3.access_Path AS taxFilePath,\n" +
			"\tsbrf3.file_Name AS taxFileName,\n" +
			"\tsbrf4.access_Path AS bankPermitFilePath,\n" +
			"\tsbrf4.file_Name AS bankPermitFileName,\n" +
			"\tsbrf5.access_Path AS othersFilePath,\n" +
			"\tsbrf5.file_Name AS othersFileName\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_credentials t\n" +
			"LEFT JOIN saaf_base_result_file sbrf1 ON t.license_file_id = sbrf1.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf2 ON t.tissue_file_id = sbrf2.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf3 ON t.tax_file_id = sbrf3.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf4 ON t.bank_permit_file_id = sbrf4.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf5 ON t.others_file_id = sbrf5.file_id\n" +
			"WHERE\n" +
			"\t1 = 1 ";

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
	@JSONField(format = "yyyy-MM-dd")
	private Date licenseIndate; //营业期限
	private String longLicenseIndate; //营业执照号是否长期有效
	private String taxCode; //税务登记证号
	private String tissueCode; //组织机构代码
	@JSONField(format = "yyyy-MM-dd")
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
	@JSONField(format = "yyyy-MM-dd")
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

    //别名字段
	private String licenseFilePath;
	private String licenseFileName;
	private String tissueFilePath;
	private String tissueFileName;
	private String taxFilePath;
	private String taxFileName;
	private String bankPermitFilePath;
	private String bankPermitFileName;
	private String othersFilePath;
	private String othersFileName;

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getLicenseFilePath() {
		return licenseFilePath;
	}

	public void setLicenseFilePath(String licenseFilePath) {
		this.licenseFilePath = licenseFilePath;
	}

	public String getLicenseFileName() {
		return licenseFileName;
	}

	public void setLicenseFileName(String licenseFileName) {
		this.licenseFileName = licenseFileName;
	}

	public String getTissueFilePath() {
		return tissueFilePath;
	}

	public void setTissueFilePath(String tissueFilePath) {
		this.tissueFilePath = tissueFilePath;
	}

	public String getTissueFileName() {
		return tissueFileName;
	}

	public void setTissueFileName(String tissueFileName) {
		this.tissueFileName = tissueFileName;
	}

	public String getTaxFilePath() {
		return taxFilePath;
	}

	public void setTaxFilePath(String taxFilePath) {
		this.taxFilePath = taxFilePath;
	}

	public String getTaxFileName() {
		return taxFileName;
	}

	public void setTaxFileName(String taxFileName) {
		this.taxFileName = taxFileName;
	}

	public String getBankPermitFilePath() {
		return bankPermitFilePath;
	}

	public void setBankPermitFilePath(String bankPermitFilePath) {
		this.bankPermitFilePath = bankPermitFilePath;
	}

	public String getBankPermitFileName() {
		return bankPermitFileName;
	}

	public void setBankPermitFileName(String bankPermitFileName) {
		this.bankPermitFileName = bankPermitFileName;
	}

	public String getOthersFilePath() {
		return othersFilePath;
	}

	public void setOthersFilePath(String othersFilePath) {
		this.othersFilePath = othersFilePath;
	}

	public String getOthersFileName() {
		return othersFileName;
	}

	public void setOthersFileName(String othersFileName) {
		this.othersFileName = othersFileName;
	}

	public void setCredentialsId(Integer credentialsId) {
		this.credentialsId = credentialsId;
	}

	
	public Integer getCredentialsId() {
		return credentialsId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setAnnualInspectionDate(Date annualInspectionDate) {
		this.annualInspectionDate = annualInspectionDate;
	}

	
	public Date getAnnualInspectionDate() {
		return annualInspectionDate;
	}

	public void setBusinessIndate(Integer businessIndate) {
		this.businessIndate = businessIndate;
	}

	
	public Integer getBusinessIndate() {
		return businessIndate;
	}

	public void setLongBusinessIndate(String longBusinessIndate) {
		this.longBusinessIndate = longBusinessIndate;
	}

	
	public String getLongBusinessIndate() {
		return longBusinessIndate;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setMuchInOne(String muchInOne) {
		this.muchInOne = muchInOne;
	}

	
	public String getMuchInOne() {
		return muchInOne;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	
	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseIndate(Date licenseIndate) {
		this.licenseIndate = licenseIndate;
	}

	
	public Date getLicenseIndate() {
		return licenseIndate;
	}

	public void setLongLicenseIndate(String longLicenseIndate) {
		this.longLicenseIndate = longLicenseIndate;
	}

	
	public String getLongLicenseIndate() {
		return longLicenseIndate;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	
	public String getTaxCode() {
		return taxCode;
	}

	public void setTissueCode(String tissueCode) {
		this.tissueCode = tissueCode;
	}

	
	public String getTissueCode() {
		return tissueCode;
	}

	public void setTissueIndate(Date tissueIndate) {
		this.tissueIndate = tissueIndate;
	}

	
	public Date getTissueIndate() {
		return tissueIndate;
	}

	public void setLongTissueIndate(String longTissueIndate) {
		this.longTissueIndate = longTissueIndate;
	}

	
	public String getLongTissueIndate() {
		return longTissueIndate;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	
	public String getBusinessScope() {
		return businessScope;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	
	public String getRepresentativeName() {
		return representativeName;
	}

	public void setBankPermitNumber(String bankPermitNumber) {
		this.bankPermitNumber = bankPermitNumber;
	}

	
	public String getBankPermitNumber() {
		return bankPermitNumber;
	}

	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}

	
	public String getCorporateIdentity() {
		return corporateIdentity;
	}

	public void setOverseasRegCertificate(String overseasRegCertificate) {
		this.overseasRegCertificate = overseasRegCertificate;
	}

	
	public String getOverseasRegCertificate() {
		return overseasRegCertificate;
	}

	public void setOverseasRegNumber(String overseasRegNumber) {
		this.overseasRegNumber = overseasRegNumber;
	}

	
	public String getOverseasRegNumber() {
		return overseasRegNumber;
	}

	public void setEnrollCapital(Integer enrollCapital) {
		this.enrollCapital = enrollCapital;
	}

	
	public Integer getEnrollCapital() {
		return enrollCapital;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	
	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setNoProvidedIdentity(String noProvidedIdentity) {
		this.noProvidedIdentity = noProvidedIdentity;
	}

	
	public String getNoProvidedIdentity() {
		return noProvidedIdentity;
	}

	public void setLicenseArea(String licenseArea) {
		this.licenseArea = licenseArea;
	}

	
	public String getLicenseArea() {
		return licenseArea;
	}

	public void setLicenseAddr(String licenseAddr) {
		this.licenseAddr = licenseAddr;
	}

	
	public String getLicenseAddr() {
		return licenseAddr;
	}

	public void setLicenseFileId(Integer licenseFileId) {
		this.licenseFileId = licenseFileId;
	}

	
	public Integer getLicenseFileId() {
		return licenseFileId;
	}

	public void setTissueFileId(Integer tissueFileId) {
		this.tissueFileId = tissueFileId;
	}

	
	public Integer getTissueFileId() {
		return tissueFileId;
	}

	public void setBankPermitFileId(Integer bankPermitFileId) {
		this.bankPermitFileId = bankPermitFileId;
	}

	
	public Integer getBankPermitFileId() {
		return bankPermitFileId;
	}

	public void setTaxFileId(Integer taxFileId) {
		this.taxFileId = taxFileId;
	}

	
	public Integer getTaxFileId() {
		return taxFileId;
	}

	public void setOthersFileId(Integer othersFileId) {
		this.othersFileId = othersFileId;
	}

	
	public Integer getOthersFileId() {
		return othersFileId;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
