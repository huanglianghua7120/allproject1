package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosChangeCredentialsEntity_HI Entity Object
 * Wed Nov 15 10:53:17 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_change_credentials")
public class SrmPosChangeCredentialsEntity_HI {
    private Integer changgeCredentialsId;
    private Integer changeId;
    private String currencyCodeBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date annualInspectionDateBf;
    private Integer businessIndateBf;
    private String longBusinessIndateBf;
    private String enterpriseTypeBf;
    private String muchInOneBf;
    private String licenseNumBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date licenseIndateBf;
    private String longLicenseIndateBf;
    private String taxCodeBf;
    private String tissueCodeBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date tissueIndateBf;
    private String longTissueIndateBf;
    private String businessScopeBf;
    private String representativeNameBf;
    private String corporateIdentityBf;
    private String bankPermitNumberBf;
    private String overseasRegCertificateBf;
    private String overseasRegNumberBf;
    private Integer enrollCapitalBf;
    private String registeredAddressBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date establishmentDateBf;
    private String noProvidedIdentityBf;
    private Integer licenseFileIdBf;
    private Integer tissueFileIdBf;
    private Integer bankPermitFileIdBf;
    private Integer taxFileIdBf;
    private Integer othersFileIdBf;
    private String licenseAreaBf;
    private String licenseAddrBf;
    private String currencyCodeAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date annualInspectionDateAf;
    private Integer businessIndateAf;
    private String longBusinessIndateAf;
    private String enterpriseTypeAf;
    private String muchInOneAf;
    private String licenseNumAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date licenseIndateAf;
    private String longLicenseIndateAf;
    private String taxCodeAf;
    private String tissueCodeAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date tissueIndateAf;
    private String longTissueIndateAf;
    private String businessScopeAf;
    private String representativeNameAf;
    private String corporateIdentityAf;
    private String bankPermitNumberAf;
    private String overseasRegCertificateAf;
    private String overseasRegNumberAf;
    private Integer enrollCapitalAf;
    private String registeredAddressAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date establishmentDateAf;
    private String noProvidedIdentityAf;
    private String licenseAreaAf;
    private String licenseAddrAf;
    private Integer licenseFileIdAf;
    private Integer tissueFileIdAf;
    private Integer bankPermitFileIdAf;
    private Integer taxFileIdAf;
    private Integer othersFileIdAf;
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

    public void setChanggeCredentialsId(Integer changgeCredentialsId) {
	this.changgeCredentialsId = changgeCredentialsId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_CHANGE_INFO_S", sequenceName = "SRM_POS_CHANGE_INFO_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_CHANGE_INFO_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "changge_credentials_id", nullable = false, length = 11)    
    public Integer getChanggeCredentialsId() {
	return changgeCredentialsId;
    }

    public void setChangeId(Integer changeId) {
	this.changeId = changeId;
    }

    @Column(name = "change_id", nullable = false, length = 11)    
    public Integer getChangeId() {
	return changeId;
    }

    public void setCurrencyCodeBf(String currencyCodeBf) {
	this.currencyCodeBf = currencyCodeBf;
    }

    @Column(name = "currency_code_bf", nullable = true, length = 30)    
    public String getCurrencyCodeBf() {
	return currencyCodeBf;
    }

    public void setAnnualInspectionDateBf(Date annualInspectionDateBf) {
	this.annualInspectionDateBf = annualInspectionDateBf;
    }

    @Column(name = "annual_inspection_date_bf", nullable = true, length = 0)    
    public Date getAnnualInspectionDateBf() {
	return annualInspectionDateBf;
    }

    public void setBusinessIndateBf(Integer businessIndateBf) {
	this.businessIndateBf = businessIndateBf;
    }

    @Column(name = "business_indate_bf", nullable = true, length = 11)    
    public Integer getBusinessIndateBf() {
	return businessIndateBf;
    }

    public void setLongBusinessIndateBf(String longBusinessIndateBf) {
	this.longBusinessIndateBf = longBusinessIndateBf;
    }

    @Column(name = "long_business_indate_bf", nullable = true, length = 10)    
    public String getLongBusinessIndateBf() {
	return longBusinessIndateBf;
    }

    public void setEnterpriseTypeBf(String enterpriseTypeBf) {
	this.enterpriseTypeBf = enterpriseTypeBf;
    }

    @Column(name = "enterprise_type_bf", nullable = true, length = 30)    
    public String getEnterpriseTypeBf() {
	return enterpriseTypeBf;
    }

    public void setMuchInOneBf(String muchInOneBf) {
	this.muchInOneBf = muchInOneBf;
    }

    @Column(name = "much_in_one_bf", nullable = true, length = 10)
    public String getMuchInOneBf() {
	return muchInOneBf;
    }

    public void setLicenseNumBf(String licenseNumBf) {
	this.licenseNumBf = licenseNumBf;
    }

    @Column(name = "license_num_bf", nullable = true, length = 100)    
    public String getLicenseNumBf() {
	return licenseNumBf;
    }

    public void setLicenseIndateBf(Date licenseIndateBf) {
	this.licenseIndateBf = licenseIndateBf;
    }

    @Column(name = "license_indate_bf", nullable = true, length = 0)
    public Date getLicenseIndateBf() {
	return licenseIndateBf;
    }

    public void setLongLicenseIndateBf(String longLicenseIndateBf) {
	this.longLicenseIndateBf = longLicenseIndateBf;
    }

    @Column(name = "long_license_indate_bf", nullable = true, length = 10)    
    public String getLongLicenseIndateBf() {
	return longLicenseIndateBf;
    }

    public void setTaxCodeBf(String taxCodeBf) {
	this.taxCodeBf = taxCodeBf;
    }

    @Column(name = "tax_code_bf", nullable = true, length = 100)    
    public String getTaxCodeBf() {
	return taxCodeBf;
    }

    public void setTissueCodeBf(String tissueCodeBf) {
	this.tissueCodeBf = tissueCodeBf;
    }

    @Column(name = "tissue_code_bf", nullable = true, length = 100)    
    public String getTissueCodeBf() {
	return tissueCodeBf;
    }

    public void setTissueIndateBf(Date tissueIndateBf) {
	this.tissueIndateBf = tissueIndateBf;
    }

    @Column(name = "tissue_indate_bf", nullable = true, length = 0)    
    public Date getTissueIndateBf() {
	return tissueIndateBf;
    }

    public void setLongTissueIndateBf(String longTissueIndateBf) {
	this.longTissueIndateBf = longTissueIndateBf;
    }

    @Column(name = "long_tissue_indate_bf", nullable = true, length = 100)    
    public String getLongTissueIndateBf() {
	return longTissueIndateBf;
    }

    public void setBusinessScopeBf(String businessScopeBf) {
	this.businessScopeBf = businessScopeBf;
    }

    @Column(name = "business_scope_bf", nullable = true, length = 200)    
    public String getBusinessScopeBf() {
	return businessScopeBf;
    }

    public void setRepresentativeNameBf(String representativeNameBf) {
	this.representativeNameBf = representativeNameBf;
    }

    @Column(name = "representative_name_bf", nullable = true, length = 100)    
    public String getRepresentativeNameBf() {
	return representativeNameBf;
    }

    public void setCorporateIdentityBf(String corporateIdentityBf) {
	this.corporateIdentityBf = corporateIdentityBf;
    }

    @Column(name = "corporate_identity_bf", nullable = true, length = 100)    
    public String getCorporateIdentityBf() {
	return corporateIdentityBf;
    }

    public void setBankPermitNumberBf(String bankPermitNumberBf) {
	this.bankPermitNumberBf = bankPermitNumberBf;
    }

    @Column(name = "bank_permit_number_bf", nullable = true, length = 100)    
    public String getBankPermitNumberBf() {
	return bankPermitNumberBf;
    }

    public void setOverseasRegCertificateBf(String overseasRegCertificateBf) {
	this.overseasRegCertificateBf = overseasRegCertificateBf;
    }

    @Column(name = "overseas_reg_certificate_bf", nullable = true, length = 100)    
    public String getOverseasRegCertificateBf() {
	return overseasRegCertificateBf;
    }

    public void setOverseasRegNumberBf(String overseasRegNumberBf) {
	this.overseasRegNumberBf = overseasRegNumberBf;
    }

    @Column(name = "overseas_reg_number_bf", nullable = true, length = 100)    
    public String getOverseasRegNumberBf() {
	return overseasRegNumberBf;
    }

    public void setEnrollCapitalBf(Integer enrollCapitalBf) {
	this.enrollCapitalBf = enrollCapitalBf;
    }

    @Column(name = "enroll_capital_bf", nullable = true, length = 11)    
    public Integer getEnrollCapitalBf() {
	return enrollCapitalBf;
    }

    public void setRegisteredAddressBf(String registeredAddressBf) {
	this.registeredAddressBf = registeredAddressBf;
    }

    @Column(name = "registered_address_bf", nullable = true, length = 240)    
    public String getRegisteredAddressBf() {
	return registeredAddressBf;
    }

    public void setEstablishmentDateBf(Date establishmentDateBf) {
	this.establishmentDateBf = establishmentDateBf;
    }

    @Column(name = "establishment_date_bf", nullable = true, length = 0)    
    public Date getEstablishmentDateBf() {
	return establishmentDateBf;
    }

    public void setNoProvidedIdentityBf(String noProvidedIdentityBf) {
	this.noProvidedIdentityBf = noProvidedIdentityBf;
    }

    @Column(name = "no_provided_identity_bf", nullable = true, length = 10)    
    public String getNoProvidedIdentityBf() {
	return noProvidedIdentityBf;
    }

    public void setLicenseFileIdBf(Integer licenseFileIdBf) {
	this.licenseFileIdBf = licenseFileIdBf;
    }

    @Column(name = "license_file_id_bf", nullable = true, length = 11)    
    public Integer getLicenseFileIdBf() {
	return licenseFileIdBf;
    }

    public void setTissueFileIdBf(Integer tissueFileIdBf) {
	this.tissueFileIdBf = tissueFileIdBf;
    }

    @Column(name = "tissue_file_id_bf", nullable = true, length = 11)    
    public Integer getTissueFileIdBf() {
	return tissueFileIdBf;
    }

    public void setBankPermitFileIdBf(Integer bankPermitFileIdBf) {
	this.bankPermitFileIdBf = bankPermitFileIdBf;
    }

    @Column(name = "bank_permit_file_id_bf", nullable = true, length = 11)    
    public Integer getBankPermitFileIdBf() {
	return bankPermitFileIdBf;
    }

    public void setTaxFileIdBf(Integer taxFileIdBf) {
	this.taxFileIdBf = taxFileIdBf;
    }

    @Column(name = "tax_file_id_bf", nullable = true, length = 11)    
    public Integer getTaxFileIdBf() {
	return taxFileIdBf;
    }

    public void setOthersFileIdBf(Integer othersFileIdBf) {
	this.othersFileIdBf = othersFileIdBf;
    }

    @Column(name = "others_file_id_bf", nullable = true, length = 11)    
    public Integer getOthersFileIdBf() {
	return othersFileIdBf;
    }

    public void setLicenseAreaBf(String licenseAreaBf) {
	this.licenseAreaBf = licenseAreaBf;
    }

    @Column(name = "license_area_bf", nullable = true, length = 100)    
    public String getLicenseAreaBf() {
	return licenseAreaBf;
    }

    public void setLicenseAddrBf(String licenseAddrBf) {
	this.licenseAddrBf = licenseAddrBf;
    }

    @Column(name = "license_addr_bf", nullable = true, length = 100)    
    public String getLicenseAddrBf() {
	return licenseAddrBf;
    }

    public void setCurrencyCodeAf(String currencyCodeAf) {
	this.currencyCodeAf = currencyCodeAf;
    }

    @Column(name = "currency_code_af", nullable = true, length = 30)    
    public String getCurrencyCodeAf() {
	return currencyCodeAf;
    }

    public void setAnnualInspectionDateAf(Date annualInspectionDateAf) {
	this.annualInspectionDateAf = annualInspectionDateAf;
    }

    @Column(name = "annual_inspection_date_af", nullable = true, length = 0)    
    public Date getAnnualInspectionDateAf() {
	return annualInspectionDateAf;
    }

    public void setBusinessIndateAf(Integer businessIndateAf) {
	this.businessIndateAf = businessIndateAf;
    }

    @Column(name = "business_indate_af", nullable = true, length = 11)    
    public Integer getBusinessIndateAf() {
	return businessIndateAf;
    }

    public void setLongBusinessIndateAf(String longBusinessIndateAf) {
	this.longBusinessIndateAf = longBusinessIndateAf;
    }

    @Column(name = "long_business_indate_af", nullable = true, length = 10)    
    public String getLongBusinessIndateAf() {
	return longBusinessIndateAf;
    }

    public void setEnterpriseTypeAf(String enterpriseTypeAf) {
	this.enterpriseTypeAf = enterpriseTypeAf;
    }

    @Column(name = "enterprise_type_af", nullable = true, length = 30)    
    public String getEnterpriseTypeAf() {
	return enterpriseTypeAf;
    }

    public void setMuchInOneAf(String muchInOneAf) {
	this.muchInOneAf = muchInOneAf;
    }

    @Column(name = "much_in_one_af", nullable = true, length = 10)
    public String getMuchInOneAf() {
	return muchInOneAf;
    }

    public void setLicenseNumAf(String licenseNumAf) {
	this.licenseNumAf = licenseNumAf;
    }

    @Column(name = "license_num_af", nullable = true, length = 100)    
    public String getLicenseNumAf() {
	return licenseNumAf;
    }

    public void setLicenseIndateAf(Date licenseIndateAf) {
	this.licenseIndateAf = licenseIndateAf;
    }

    @Column(name = "license_indate_af", nullable = true, length = 0)
    public Date getLicenseIndateAf() {
	return licenseIndateAf;
    }

    public void setLongLicenseIndateAf(String longLicenseIndateAf) {
	this.longLicenseIndateAf = longLicenseIndateAf;
    }

    @Column(name = "long_license_indate_af", nullable = true, length = 10)    
    public String getLongLicenseIndateAf() {
	return longLicenseIndateAf;
    }

    public void setTaxCodeAf(String taxCodeAf) {
	this.taxCodeAf = taxCodeAf;
    }

    @Column(name = "tax_code_af", nullable = true, length = 100)    
    public String getTaxCodeAf() {
	return taxCodeAf;
    }

    public void setTissueCodeAf(String tissueCodeAf) {
	this.tissueCodeAf = tissueCodeAf;
    }

    @Column(name = "tissue_code_af", nullable = true, length = 100)    
    public String getTissueCodeAf() {
	return tissueCodeAf;
    }

    public void setTissueIndateAf(Date tissueIndateAf) {
	this.tissueIndateAf = tissueIndateAf;
    }

    @Column(name = "tissue_indate_af", nullable = true, length = 0)    
    public Date getTissueIndateAf() {
	return tissueIndateAf;
    }

    public void setLongTissueIndateAf(String longTissueIndateAf) {
	this.longTissueIndateAf = longTissueIndateAf;
    }

    @Column(name = "long_tissue_indate_af", nullable = true, length = 100)    
    public String getLongTissueIndateAf() {
	return longTissueIndateAf;
    }

    public void setBusinessScopeAf(String businessScopeAf) {
	this.businessScopeAf = businessScopeAf;
    }

    @Column(name = "business_scope_af", nullable = true, length = 200)    
    public String getBusinessScopeAf() {
	return businessScopeAf;
    }

    public void setRepresentativeNameAf(String representativeNameAf) {
	this.representativeNameAf = representativeNameAf;
    }

    @Column(name = "representative_name_af", nullable = true, length = 100)    
    public String getRepresentativeNameAf() {
	return representativeNameAf;
    }

    public void setCorporateIdentityAf(String corporateIdentityAf) {
	this.corporateIdentityAf = corporateIdentityAf;
    }

    @Column(name = "corporate_identity_af", nullable = true, length = 100)    
    public String getCorporateIdentityAf() {
	return corporateIdentityAf;
    }

    public void setBankPermitNumberAf(String bankPermitNumberAf) {
	this.bankPermitNumberAf = bankPermitNumberAf;
    }

    @Column(name = "bank_permit_number_af", nullable = true, length = 100)    
    public String getBankPermitNumberAf() {
	return bankPermitNumberAf;
    }

    public void setOverseasRegCertificateAf(String overseasRegCertificateAf) {
	this.overseasRegCertificateAf = overseasRegCertificateAf;
    }

    @Column(name = "overseas_reg_certificate_af", nullable = true, length = 100)    
    public String getOverseasRegCertificateAf() {
	return overseasRegCertificateAf;
    }

    public void setOverseasRegNumberAf(String overseasRegNumberAf) {
	this.overseasRegNumberAf = overseasRegNumberAf;
    }

    @Column(name = "overseas_reg_number_af", nullable = true, length = 100)    
    public String getOverseasRegNumberAf() {
	return overseasRegNumberAf;
    }

    public void setEnrollCapitalAf(Integer enrollCapitalAf) {
	this.enrollCapitalAf = enrollCapitalAf;
    }

    @Column(name = "enroll_capital_af", nullable = true, length = 11)    
    public Integer getEnrollCapitalAf() {
	return enrollCapitalAf;
    }

    public void setRegisteredAddressAf(String registeredAddressAf) {
	this.registeredAddressAf = registeredAddressAf;
    }

    @Column(name = "registered_address_af", nullable = true, length = 240)    
    public String getRegisteredAddressAf() {
	return registeredAddressAf;
    }

    public void setEstablishmentDateAf(Date establishmentDateAf) {
	this.establishmentDateAf = establishmentDateAf;
    }

    @Column(name = "establishment_date_af", nullable = true, length = 0)    
    public Date getEstablishmentDateAf() {
	return establishmentDateAf;
    }

    public void setNoProvidedIdentityAf(String noProvidedIdentityAf) {
	this.noProvidedIdentityAf = noProvidedIdentityAf;
    }

    @Column(name = "no_provided_identity_af", nullable = true, length = 10)    
    public String getNoProvidedIdentityAf() {
	return noProvidedIdentityAf;
    }

    public void setLicenseAreaAf(String licenseAreaAf) {
	this.licenseAreaAf = licenseAreaAf;
    }

    @Column(name = "license_area_af", nullable = true, length = 100)    
    public String getLicenseAreaAf() {
	return licenseAreaAf;
    }

    public void setLicenseAddrAf(String licenseAddrAf) {
	this.licenseAddrAf = licenseAddrAf;
    }

    @Column(name = "license_addr_af", nullable = true, length = 100)    
    public String getLicenseAddrAf() {
	return licenseAddrAf;
    }

    public void setLicenseFileIdAf(Integer licenseFileIdAf) {
	this.licenseFileIdAf = licenseFileIdAf;
    }

    @Column(name = "license_file_id_af", nullable = true, length = 11)    
    public Integer getLicenseFileIdAf() {
	return licenseFileIdAf;
    }

    public void setTissueFileIdAf(Integer tissueFileIdAf) {
	this.tissueFileIdAf = tissueFileIdAf;
    }

    @Column(name = "tissue_file_id_af", nullable = true, length = 11)    
    public Integer getTissueFileIdAf() {
	return tissueFileIdAf;
    }

    public void setBankPermitFileIdAf(Integer bankPermitFileIdAf) {
	this.bankPermitFileIdAf = bankPermitFileIdAf;
    }

    @Column(name = "bank_permit_file_id_af", nullable = true, length = 11)    
    public Integer getBankPermitFileIdAf() {
	return bankPermitFileIdAf;
    }

    public void setTaxFileIdAf(Integer taxFileIdAf) {
	this.taxFileIdAf = taxFileIdAf;
    }

    @Column(name = "tax_file_id_af", nullable = true, length = 11)    
    public Integer getTaxFileIdAf() {
	return taxFileIdAf;
    }

    public void setOthersFileIdAf(Integer othersFileIdAf) {
	this.othersFileIdAf = othersFileIdAf;
    }

    @Column(name = "others_file_id_af", nullable = true, length = 11)    
    public Integer getOthersFileIdAf() {
	return othersFileIdAf;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

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

	private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
          this.operatorUserId = operatorUserId;
      }

      @Transient
      public Integer getOperatorUserId() {
          return operatorUserId;
      }
}

