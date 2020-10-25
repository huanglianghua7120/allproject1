package saaf.common.fmw.pos.model.entities;


import javax.persistence.*;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosChangeBaseEntity_HI Entity Object
 * Thu Nov 16 10:14:00 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_change_base")
public class SrmPosChangeBaseEntity_HI {
    private Integer changeBaseId;
    private Integer changeId;
    private String supplierNameBf;
    private String supplierShortNameBf;
    private String supplierTypeBf;
    private String supplierClassifyBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String supplierIndustryBf;
    private String finClassifyBf;
    private String settleAcctTypeBf;
    private String posTaxBf;
    private String posTaxAf;
    private String posAcctConditionBf;
    private String acctCheckStaffBf;
    private String acctCheckTypeBf;
    private String srmDeliveryBf;
    private String srmDeliveryAf;
    private String purchaseFlagBf;
    private String paymentFlagBf;
    private String homeUrlBf;
    private String companyPhoneBf;
    private String companyFaxBf;
    private Integer staffNumBf;
    private String floorAreaBf;
    private String companyDescriptionBf;
    private String supplierNameAf;
    private String supplierShortNameAf;
    private String supplierTypeAf;
    private String supplierClassifyAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String supplierIndustryAf;
    private String finClassifyAf;
    private String settleAcctTypeAf;
    private String acctCheckStaffAf;
    private String acctCheckTypeAf;
    private String purchaseFlagAf;
    private String paymentFlagAf;
    private String homeUrlAf;
    private String companyPhoneAf;
    private String companyFaxAf;
    private Integer staffNumAf;
    private Integer supplierFileIdBf;
    private Integer supplierFileIdAf;
    private String ableCheckOrderFlagBf;
    private String ableCheckOrderFlagAf;
    private String addressBf;
    private String addressAf;
    private String companyDescription;
    private String companyRegisteredAddressBf;
    private String companyRegisteredAddressAf;
    private String registeredCapitalBf;
    private String registeredCapitalAf;
    private String indLegalPersonFlagBf;
    private String indLegalPersonFlagAf;
    private String valueAddedTaxInvFlagBf;
    private String valueAddedTaxInvFlagAf;
    private String valueAddedTaxInvDescBf;
    private String valueAddedTaxInvDescAf;
    private String associatedCompanyBf;
    private String associatedCompanyAf;
    private String regionBf;
    private String regionAf;
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

    public void setChangeBaseId(Integer changeBaseId) {
	this.changeBaseId = changeBaseId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_CHANGE_INFO_S", sequenceName = "SRM_POS_CHANGE_INFO_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_CHANGE_INFO_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "change_base_id", nullable = false, length = 11)    
    public Integer getChangeBaseId() {
	return changeBaseId;
    }

    public void setChangeId(Integer changeId) {
	this.changeId = changeId;
    }

    @Column(name = "change_id", nullable = true, length = 11)    
    public Integer getChangeId() {
	return changeId;
    }

    public void setSupplierNameBf(String supplierNameBf) {
	this.supplierNameBf = supplierNameBf;
    }

    @Column(name = "supplier_name_bf", nullable = true, length = 200)    
    public String getSupplierNameBf() {
	return supplierNameBf;
    }

    @Column(name = "address_bf", nullable = true, length = 200)
    public String getAddressBf() { return addressBf; }

    public void setAddressBf(String addressBf) { this.addressBf = addressBf; }

    @Column(name = "address_af", nullable = true, length = 200)
    public String getAddressAf() { return addressAf; }

    public void setAddressAf(String addressAf) { this.addressAf = addressAf; }

    public void setSupplierShortNameBf(String supplierShortNameBf) {
	this.supplierShortNameBf = supplierShortNameBf;
    }

    @Column(name = "supplier_short_name_bf", nullable = true, length = 100)    
    public String getSupplierShortNameBf() {
	return supplierShortNameBf;
    }

    public void setSupplierTypeBf(String supplierTypeBf) {
	this.supplierTypeBf = supplierTypeBf;
    }

    @Column(name = "supplier_type_bf", nullable = true, length = 30)    
    public String getSupplierTypeBf() {
	return supplierTypeBf;
    }

    public void setSupplierClassifyBf(String supplierClassifyBf) {
	this.supplierClassifyBf = supplierClassifyBf;
    }

    @Column(name = "supplier_classify_bf", nullable = true, length = 30)    
    public String getSupplierClassifyBf() {
	return supplierClassifyBf;
    }

    public void setSupplierIndustryBf(String supplierIndustryBf) {
	this.supplierIndustryBf = supplierIndustryBf;
    }

    @Column(name = "supplier_industry_bf", nullable = true, length = 50)    
    public String getSupplierIndustryBf() {
	return supplierIndustryBf;
    }

    public void setFinClassifyBf(String finClassifyBf) {
	this.finClassifyBf = finClassifyBf;
    }

    @Column(name = "fin_classify_bf", nullable = true, length = 30)    
    public String getFinClassifyBf() {
	return finClassifyBf;
    }

    public void setSettleAcctTypeBf(String settleAcctTypeBf) {
	this.settleAcctTypeBf = settleAcctTypeBf;
    }

    @Column(name = "settle_acct_type_bf", nullable = true, length = 30)    
    public String getSettleAcctTypeBf() {
	return settleAcctTypeBf;
    }

    public void setAcctCheckStaffBf(String acctCheckStaffBf) {
	this.acctCheckStaffBf = acctCheckStaffBf;
    }

    @Column(name = "acct_check_staff_bf", nullable = true, length = 50)    
    public String getAcctCheckStaffBf() {
	return acctCheckStaffBf;
    }

    public void setAcctCheckTypeBf(String acctCheckTypeBf) {
	this.acctCheckTypeBf = acctCheckTypeBf;
    }

    @Column(name = "acct_check_type_bf", nullable = true, length = 50)    
    public String getAcctCheckTypeBf() {
	return acctCheckTypeBf;
    }

    public void setPurchaseFlagBf(String purchaseFlagBf) {
	this.purchaseFlagBf = purchaseFlagBf;
    }

    @Column(name = "purchase_flag_bf", nullable = true, length = 10)    
    public String getPurchaseFlagBf() {
	return purchaseFlagBf;
    }

    public void setPaymentFlagBf(String paymentFlagBf) {
	this.paymentFlagBf = paymentFlagBf;
    }

    @Column(name = "payment_flag_bf", nullable = true, length = 10)    
    public String getPaymentFlagBf() {
	return paymentFlagBf;
    }

    public void setHomeUrlBf(String homeUrlBf) {
	this.homeUrlBf = homeUrlBf;
    }

    @Column(name = "home_url_bf", nullable = true, length = 200)    
    public String getHomeUrlBf() {
	return homeUrlBf;
    }

    public void setCompanyPhoneBf(String companyPhoneBf) {
	this.companyPhoneBf = companyPhoneBf;
    }

    @Column(name = "company_phone_bf", nullable = true, length = 100)    
    public String getCompanyPhoneBf() {
	return companyPhoneBf;
    }

    public void setCompanyFaxBf(String companyFaxBf) {
	this.companyFaxBf = companyFaxBf;
    }

    @Column(name = "company_fax_bf", nullable = true, length = 100)    
    public String getCompanyFaxBf() {
	return companyFaxBf;
    }

    public void setStaffNumBf(Integer staffNumBf) {
	this.staffNumBf = staffNumBf;
    }

    @Column(name = "staff_num_bf", nullable = true, length = 11)    
    public Integer getStaffNumBf() {
	return staffNumBf;
    }

    public void setFloorAreaBf(String floorAreaBf) {
	this.floorAreaBf = floorAreaBf;
    }

    @Column(name = "floor_area_bf", nullable = true, length = 100)    
    public String getFloorAreaBf() {
	return floorAreaBf;
    }

    public void setCompanyDescriptionBf(String companyDescriptionBf) {
	this.companyDescriptionBf = companyDescriptionBf;
    }

    @Column(name = "company_description_bf", nullable = true, length = 0)    
    public String getCompanyDescriptionBf() {
	return companyDescriptionBf;
    }

    public void setSupplierNameAf(String supplierNameAf) {
	this.supplierNameAf = supplierNameAf;
    }

    @Column(name = "supplier_name_af", nullable = true, length = 200)    
    public String getSupplierNameAf() {
	return supplierNameAf;
    }

    public void setSupplierShortNameAf(String supplierShortNameAf) {
	this.supplierShortNameAf = supplierShortNameAf;
    }

    @Column(name = "supplier_short_name_af", nullable = true, length = 100)    
    public String getSupplierShortNameAf() {
	return supplierShortNameAf;
    }

    public void setSupplierTypeAf(String supplierTypeAf) {
	this.supplierTypeAf = supplierTypeAf;
    }

    @Column(name = "supplier_type_af", nullable = true, length = 30)    
    public String getSupplierTypeAf() {
	return supplierTypeAf;
    }

    public void setSupplierClassifyAf(String supplierClassifyAf) {
	this.supplierClassifyAf = supplierClassifyAf;
    }

    @Column(name = "supplier_classify_af", nullable = true, length = 30)    
    public String getSupplierClassifyAf() {
	return supplierClassifyAf;
    }

    public void setSupplierIndustryAf(String supplierIndustryAf) {
	this.supplierIndustryAf = supplierIndustryAf;
    }

    @Column(name = "supplier_industry_af", nullable = true, length = 50)    
    public String getSupplierIndustryAf() {
	return supplierIndustryAf;
    }

    public void setFinClassifyAf(String finClassifyAf) {
	this.finClassifyAf = finClassifyAf;
    }

    @Column(name = "fin_classify_af", nullable = true, length = 30)    
    public String getFinClassifyAf() {
	return finClassifyAf;
    }

    public void setSettleAcctTypeAf(String settleAcctTypeAf) {
	this.settleAcctTypeAf = settleAcctTypeAf;
    }

    @Column(name = "settle_acct_type_af", nullable = true, length = 30)    
    public String getSettleAcctTypeAf() {
	return settleAcctTypeAf;
    }

    public void setAcctCheckStaffAf(String acctCheckStaffAf) {
	this.acctCheckStaffAf = acctCheckStaffAf;
    }

    @Column(name = "acct_check_staff_af", nullable = true, length = 50)    
    public String getAcctCheckStaffAf() {
	return acctCheckStaffAf;
    }

    public void setAcctCheckTypeAf(String acctCheckTypeAf) {
	this.acctCheckTypeAf = acctCheckTypeAf;
    }

    @Column(name = "acct_check_type_af", nullable = true, length = 50)    
    public String getAcctCheckTypeAf() {
	return acctCheckTypeAf;
    }

    public void setPurchaseFlagAf(String purchaseFlagAf) {
	this.purchaseFlagAf = purchaseFlagAf;
    }

    @Column(name = "purchase_flag_af", nullable = true, length = 10)    
    public String getPurchaseFlagAf() {
	return purchaseFlagAf;
    }

    public void setPaymentFlagAf(String paymentFlagAf) {
	this.paymentFlagAf = paymentFlagAf;
    }

    @Column(name = "payment_flag_af", nullable = true, length = 10)    
    public String getPaymentFlagAf() {
	return paymentFlagAf;
    }

    @Column(name = "srm_delivery_bf", nullable = true, length = 5)
    public String getSrmDeliveryBf() { return srmDeliveryBf; }

    public void setSrmDeliveryBf(String srmDeliveryBf) { this.srmDeliveryBf = srmDeliveryBf; }

    @Column(name = "srm_delivery_af", nullable = true, length = 5)
    public String getSrmDeliveryAf() { return srmDeliveryAf; }

    public void setSrmDeliveryAf(String srmDeliveryAf) { this.srmDeliveryAf = srmDeliveryAf; }

    public void setHomeUrlAf(String homeUrlAf) {
	this.homeUrlAf = homeUrlAf;
    }

    @Column(name = "home_url_af", nullable = true, length = 200)    
    public String getHomeUrlAf() {
	return homeUrlAf;
    }

    public void setCompanyPhoneAf(String companyPhoneAf) {
	this.companyPhoneAf = companyPhoneAf;
    }

    @Column(name = "company_phone_af", nullable = true, length = 100)    
    public String getCompanyPhoneAf() {
	return companyPhoneAf;
    }

    public void setCompanyFaxAf(String companyFaxAf) {
	this.companyFaxAf = companyFaxAf;
    }

    @Column(name = "company_fax_af", nullable = true, length = 100)    
    public String getCompanyFaxAf() {
	return companyFaxAf;
    }

    public void setStaffNumAf(Integer staffNumAf) {
	this.staffNumAf = staffNumAf;
    }

    @Column(name = "staff_num_af", nullable = true, length = 11)    
    public Integer getStaffNumAf() {
	return staffNumAf;
    }

    public void setFloorArea(String floorArea) {
	this.floorArea = floorArea;
    }

    @Column(name = "floor_area", nullable = true, length = 100)    
    public String getFloorArea() {
	return floorArea;
    }

    public void setCompanyDescription(String companyDescription) {
	this.companyDescription = companyDescription;
    }

    @Column(name = "company_description", nullable = true, length = 0)    
    public String getCompanyDescription() {
	return companyDescription;
    }

    @Column(name = "supplier_file_id_bf", nullable = true, length = 11)
    public Integer getSupplierFileIdBf() {
		return supplierFileIdBf;
	}

	public void setSupplierFileIdBf(Integer supplierFileIdBf) {
		this.supplierFileIdBf = supplierFileIdBf;
	}

	@Column(name = "supplier_file_id_af", nullable = true, length = 11)
	public Integer getSupplierFileIdAf() {
		return supplierFileIdAf;
	}

	public void setSupplierFileIdAf(Integer supplierFileIdAf) {
		this.supplierFileIdAf = supplierFileIdAf;
	}

    @Column(name = "company_Registered_Address_bf", nullable = true, length = 240)
    public String getCompanyRegisteredAddressBf() {
        return companyRegisteredAddressBf;
    }

    public void setCompanyRegisteredAddressBf(String companyRegisteredAddressBf) {
        this.companyRegisteredAddressBf = companyRegisteredAddressBf;
    }

    @Column(name = "company_Registered_Address_af", nullable = true, length = 240)
    public String getCompanyRegisteredAddressAf() {
        return companyRegisteredAddressAf;
    }

    public void setCompanyRegisteredAddressAf(String companyRegisteredAddressAf) {
        this.companyRegisteredAddressAf = companyRegisteredAddressAf;
    }

    @Column(name = "registered_Capital_bf", nullable = true, length = 30)
    public String getRegisteredCapitalBf() {
        return registeredCapitalBf;
    }

    public void setRegisteredCapitalBf(String registeredCapitalBf) {
        this.registeredCapitalBf = registeredCapitalBf;
    }

    @Column(name = "registered_Capital_af", nullable = true, length = 30)
    public String getRegisteredCapitalAf() {
        return registeredCapitalAf;
    }

    public void setRegisteredCapitalAf(String registeredCapitalAf) {
        this.registeredCapitalAf = registeredCapitalAf;
    }

    @Column(name = "ind_Legal_Person_Flag_bf", nullable = true, length = 10)
    public String getIndLegalPersonFlagBf() {
        return indLegalPersonFlagBf;
    }

    public void setIndLegalPersonFlagBf(String indLegalPersonFlagBf) {
        this.indLegalPersonFlagBf = indLegalPersonFlagBf;
    }

    @Column(name = "ind_Legal_Person_Flag_af", nullable = true, length = 10)
    public String getIndLegalPersonFlagAf() {
        return indLegalPersonFlagAf;
    }

    public void setIndLegalPersonFlagAf(String indLegalPersonFlagAf) {
        this.indLegalPersonFlagAf = indLegalPersonFlagAf;
    }

    @Column(name = "value_Added_Tax_Inv_Flag_bf", nullable = true, length = 10)
    public String getValueAddedTaxInvFlagBf() {
        return valueAddedTaxInvFlagBf;
    }

    public void setValueAddedTaxInvFlagBf(String valueAddedTaxInvFlagBf) {
        this.valueAddedTaxInvFlagBf = valueAddedTaxInvFlagBf;
    }

    @Column(name = "value_Added_Tax_Inv_Flag_af", nullable = true, length = 10)
    public String getValueAddedTaxInvFlagAf() {
        return valueAddedTaxInvFlagAf;
    }

    public void setValueAddedTaxInvFlagAf(String valueAddedTaxInvFlagAf) {
        this.valueAddedTaxInvFlagAf = valueAddedTaxInvFlagAf;
    }

    @Column(name = "value_Added_Tax_Inv_Desc_bf", nullable = true, length = 240)
    public String getValueAddedTaxInvDescBf() {
        return valueAddedTaxInvDescBf;
    }

    public void setValueAddedTaxInvDescBf(String valueAddedTaxInvDescBf) {
        this.valueAddedTaxInvDescBf = valueAddedTaxInvDescBf;
    }

    @Column(name = "value_Added_Tax_Inv_Desc_af", nullable = true, length = 240)
    public String getValueAddedTaxInvDescAf() {
        return valueAddedTaxInvDescAf;
    }

    public void setValueAddedTaxInvDescAf(String valueAddedTaxInvDescAf) {
        this.valueAddedTaxInvDescAf = valueAddedTaxInvDescAf;
    }

    @Column(name = "associated_Company_bf", nullable = true, length = 240)
    public String getAssociatedCompanyBf() {
        return associatedCompanyBf;
    }

    public void setAssociatedCompanyBf(String associatedCompanyBf) {
        this.associatedCompanyBf = associatedCompanyBf;
    }

    @Column(name = "associated_Company_af", nullable = true, length = 240)
    public String getAssociatedCompanyAf() {
        return associatedCompanyAf;
    }

    public void setAssociatedCompanyAf(String associatedCompanyAf) {
        this.associatedCompanyAf = associatedCompanyAf;
    }

    @Column(name = "region_bf", nullable = true, length = 100)
    public String getRegionBf() {
        return regionBf;
    }

    public void setRegionBf(String regionBf) {
        this.regionBf = regionBf;
    }

    @Column(name = "region_af", nullable = true, length = 100)
    public String getRegionAf() {
        return regionAf;
    }

    public void setRegionAf(String regionAf) {
        this.regionAf = regionAf;
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

    @Column(name = "pos_acct_condition_af", nullable = true, length = 10)
    public String getPosAcctConditionAf() {
        return posAcctConditionAf;
    }

    public void setPosAcctConditionAf(String posAcctConditionAf) {
        this.posAcctConditionAf = posAcctConditionAf;
    }

    @Column(name = "pos_acct_condition_bf", nullable = true, length = 10)
    public String getPosAcctConditionBf() {
        return posAcctConditionBf;
    }

    public void setPosAcctConditionBf(String posAcctConditionBf) {
        this.posAcctConditionBf = posAcctConditionBf;
    }

    @Column(name = "pos_tax_af", nullable = true, length = 10)
    public String getPosTaxAf() {
        return posTaxAf;
    }

    public void setPosTaxAf(String posTaxAf) {
        this.posTaxAf = posTaxAf;
    }

    private String posAcctConditionAf;

    @Column(name = "pos_tax_bf", nullable = true, length = 10)
    public String getPosTaxBf() {
        return posTaxBf;
    }

    public void setPosTaxBf(String posTaxBf) {
        this.posTaxBf = posTaxBf;
    }
    @Column(name = "able_check_order_flag_af", nullable = true, length = 10)
    public String getAbleCheckOrderFlagAf() {
        return ableCheckOrderFlagAf;
    }

    public void setAbleCheckOrderFlagAf(String ableCheckOrderFlagAf) {
        this.ableCheckOrderFlagAf = ableCheckOrderFlagAf;
    }

    private String floorArea;

    @Column(name = "able_check_order_flag_bf", nullable = true, length = 10)
    public String getAbleCheckOrderFlagBf() {
        return ableCheckOrderFlagBf;
    }

    public void setAbleCheckOrderFlagBf(String ableCheckOrderFlagBf) {
        this.ableCheckOrderFlagBf = ableCheckOrderFlagBf;
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


