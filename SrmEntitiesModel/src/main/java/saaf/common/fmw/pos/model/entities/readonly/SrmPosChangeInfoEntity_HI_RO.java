package saaf.common.fmw.pos.model.entities.readonly;



import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

import java.util.Date;


public class SrmPosChangeInfoEntity_HI_RO implements Serializable {

	//查询变更单列表
    public static String QUERY_CHANGE_INFO_LIST =
			"SELECT\n" +
					"  pci.change_id changeId,\n" +
					"  pci.supplier_id supplierId,\n" +
					"  psi.supplier_name supplierName,\n" +
					"  psi.supplier_number supplierNumber,\n" +
					"  pci.change_number changeNumber,\n" +
					"  pci.source_type sourceType,\n" +
					"  pci.change_status changeStatus,\n" +
					"  slv1.meaning changeStatusStr,\n" +
					"  pci.change_reason changeReason,\n" +
					"  pci.approval_opinion approvalOpinion,\n" +
					"  pci.approval_date approvalDate,\n" +
					"  pci.approval_user_id approvalUserId,\n" +
					"  pci.created_by createdBy,\n" +
					"  pci.creation_date creationDate,\n" +
					"  pci.file_id fileId,\n" +
					"  sbrf.access_Path filePath,\n" +
					"  sbrf.file_Name fileName,\n" +
					"  pcc.license_num_af licenseNumAf,\n" +
					"  Nvl(\n" +
					"    emp1.employee_name,\n" +
					"    su1.user_full_name\n" +
					"  ) createdByName,\n" +
					"  emp2.employee_name approvalUserName\n" +
					"FROM\n" +
					"  srm_pos_change_info pci\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = pci.change_status\n" +
					"    AND slv1.lookup_type = 'POS_CHANGE_STATUS'\n" +
					"  LEFT JOIN saaf_base_result_file sbrf\n" +
					"    ON sbrf.file_id = pci.file_id\n" +
					"  LEFT JOIN saaf_users su2\n" +
					"    ON su2.user_id = pci.approval_user_id\n" +
					"  LEFT JOIN saaf_employees emp2\n" +
					"    ON emp2.employee_id = su2.employee_id\n" +
					"  LEFT JOIN srm_pos_change_credentials pcc\n" +
					"    ON pcc.change_id = pci.change_id,\n" +
					"  srm_pos_supplier_info psi,\n" +
					"  saaf_users su1\n" +
					"  LEFT JOIN saaf_employees emp1\n" +
					"    ON emp1.employee_id = su1.employee_id\n" +
					"WHERE pci.supplier_id = psi.supplier_id\n" +
					"  AND pci.created_by = su1.user_id";

	public static String COUNT_CHANGE_INFO_LIST =
					"SELECT\n" +
					"  COUNT(1)\n" +
					"FROM\n" +
					"  srm_pos_change_info pci\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = pci.change_status\n" +
					"    AND slv1.lookup_type = 'POS_CHANGE_STATUS'\n" +
					"  LEFT JOIN saaf_base_result_file sbrf\n" +
					"    ON sbrf.file_id = pci.file_id\n" +
					"  LEFT JOIN saaf_users su2\n" +
					"    ON su2.user_id = pci.approval_user_id\n" +
					"  LEFT JOIN saaf_employees emp2\n" +
					"    ON emp2.employee_id = su2.employee_id\n" +
					"  LEFT JOIN srm_pos_change_credentials pcc\n" +
					"    ON pcc.change_id = pci.change_id,\n" +
					"  srm_pos_supplier_info psi,\n" +
					"  saaf_users su1\n" +
					"  LEFT JOIN saaf_employees emp1\n" +
					"    ON emp1.employee_id = su1.employee_id\n" +
					"WHERE pci.supplier_id = psi.supplier_id\n" +
					"  AND pci.created_by = su1.user_id";

	//查询变更基本信息
    public static String QUERY_CHANGE_BASE_INFO = "SELECT\r\n " +
    		"	spcb.change_base_ID changeBaseId,\r\n" +
    		"	spcb.change_id changeId,\r\n" + 
    		"	spcb.supplier_name_bf supplierName,\r\n" + 
    		"	spcb.supplier_name_af supplierNameAfter,\r\n" + 
    		"	spcb.supplier_short_name_bf supplierShortName,\r\n" + 
    		"	spcb.supplier_short_name_af supplierShortNameAfter,\r\n" + 
    		"	spcb.supplier_type_bf supplierType,\r\n" + 
    		"	spcb.supplier_type_af supplierTypeAfter,\r\n" + 
    		"	spcb.supplier_classify_bf supplierClassify,\r\n" + 
    		"	spcb.supplier_classify_af supplierClassifyAfter,\r\n" +
            "	spcb.able_check_order_flag_bf ableCheckOrderFlag,\r\n" +
            "	spcb.able_check_order_flag_af ableCheckOrderFlagAfter,\r\n" +
            "	spcb.supplier_industry_bf supplierIndustry,\r\n" +
    		"	spcb.supplier_industry_af supplierIndustryAfter,\r\n" +
		    "	spcb.srm_delivery_bf srmDelivery,\r\n" +
		    "	spcb.srm_delivery_af srmDeliveryAfter,\r\n" +
		    "	spcb.address_bf address,\r\n" +
		    "	spcb.address_af addressAfter,\r\n" +
    		"	spcb.fin_classify_bf finClassify,\r\n" + 
    		"	spcb.fin_classify_af finClassifyAfter,\r\n" + 
    		"	spcb.settle_acct_type_bf settleAcctType,\r\n" +
		    "	slv1.meaning settleAcctTypeName,\r\n" +
		    "	slv2.meaning settleAcctTypeNameAfter,\r\n" +
    		"	spcb.settle_acct_type_af settleAcctTypeAfter,\r\n" +
            "	spcb.pos_acct_condition_bf posAcctCondition,\r\n" +
            "	spcb.pos_acct_condition_af posAcctConditionAfter,\r\n" +
            "	spcb.pos_tax_bf posTax,\r\n" +
            "	spcb.pos_tax_af posTaxAfter,\r\n" +
    		"	spcb.acct_check_staff_bf acctCheckStaff,\r\n" + 
    		"	spcb.acct_check_staff_af acctCheckStaffAfter,\r\n" + 
    		"	spcb.acct_check_type_bf acctCheckType,\r\n" + 
    		"	spcb.acct_check_type_af acctCheckTypeAfter,\r\n" +
            "	se.employee_name employeeName,\r\n" +
            "	se1.employee_name employeeNameAfter,\r\n" +
    		"	spcb.payment_flag_bf paymentFlag,\r\n" + 
    		"	spcb.payment_flag_af paymentFlagAfter,\r\n" + 
    		"	spcb.purchase_flag_bf purchaseFlag,\r\n" + 
    		"	spcb.purchase_flag_af purchaseFlagAfter,\r\n" + 
    		"	spcb.home_url_bf homeUrl,\r\n" + 
    		"	spcb.home_url_af homeUrlAfter,\r\n" + 
    		"	spcb.company_phone_bf companyPhone,\r\n" + 
    		"	spcb.company_phone_af companyPhoneAfter,\r\n" + 
    		"	spcb.company_fax_bf companyFax,\r\n" + 
    		"	spcb.company_fax_af companyFaxAfter,\r\n" + 
    		"	spcb.staff_num_bf staffNum,\r\n" + 
    		"	spcb.staff_num_af staffNumAfter,\r\n" + 
    		"	spcb.floor_area_bf floorArea,\r\n" + 
    		"	spcb.floor_area floorAreaAfter,\r\n" + 
    		"	spcb.company_description_bf companyDescription,\r\n" + 
    		"	spcb.company_description companyDescriptionAfter,\r\n" +
			"   spcb.company_Registered_Address_bf companyRegisteredAddress,\r\n" +
			"   spcb.company_Registered_Address_af companyRegisteredAddressAfter,\r\n" +
			"   spcb.registered_Capital_bf registeredCapital,\r\n" +
			"   spcb.registered_Capital_af registeredCapitalAfter,\r\n" +
			"   spcb.ind_Legal_Person_Flag_bf indLegalPersonFlag,\r\n" +
			"   spcb.ind_Legal_Person_Flag_af indLegalPersonFlagAfter,\r\n" +
			"   spcb.value_Added_Tax_Inv_Flag_bf valueAddedTaxInvoiceFlag,\r\n" +
			"   spcb.value_Added_Tax_Inv_Flag_af valueAddedTaxInvoiceFlagAfter,\r\n" +
			"   spcb.value_Added_Tax_Inv_Desc_bf valueAddedTaxInvoiceDesc,\r\n" +
			"   spcb.value_Added_Tax_Inv_Desc_af valueAddedTaxInvoiceDescAfter,\r\n" +
			"   spcb.associated_Company_bf associatedCompany,\r\n" +
			"   spcb.associated_Company_af associatedCompanyAfter,\r\n" +
			"   spcb.region_bf region,\r\n" +
			"   spcb.region_af regionAfter,\r\n" +
			"	spcb.supplier_file_id_bf supplierFileId,\r\n" +
    		"	sbrf1.access_Path supplierFilePath,\r\n" + 
    		"	sbrf1.file_Name supplierFileName,\r\n" + 
    		"	spcb.supplier_file_id_af supplierFileIdAfter,\r\n" + 
    		"	sbrf2.access_Path supplierFilePathAfter,\r\n" + 
    		"	sbrf2.file_Name supplierFileNameAfter,\r\n" + 
    		"	spcc.changge_credentials_id changgeCredentialsId,\r\n" + 
    		"	spcc.currency_code_bf currencyCode,\r\n" + 
    		"	spcc.currency_code_af currencyCodeAfter,\r\n" + 
    		"	spcc.much_in_one_bf muchInOne,\r\n" +
    		"	spcc.much_in_one_af muchInOneAfter,\r\n" +
    		"	spcc.license_num_bf licenseNum,\r\n" + 
    		"	spcc.license_num_af licenseNumAfter,\r\n" + 
    		"	spcc.license_indate_bf licenseIndate,\r\n" + 
    		"	spcc.license_indate_af licenseIndateAfter,\r\n" +
    		"	spcc.long_license_indate_bf longLicenseIndate,\r\n" + 
    		"	spcc.long_license_indate_af longLicenseIndateAfter,\r\n" + 
    		"	spcc.tax_code_bf taxCode,\r\n" + 
    		"	spcc.tax_code_af taxCodeAfter,\r\n" + 
    		"	spcc.tissue_code_bf tissueCode,\r\n" + 
    		"	spcc.tissue_code_af tissueCodeAfter,\r\n" + 
    		"	spcc.tissue_indate_bf tissueIndate,\r\n" + 
    		"	spcc.tissue_indate_af tissueIndateAfter,\r\n" + 
    		"	spcc.business_scope_bf businessScope,\r\n" + 
    		"	spcc.business_scope_af businessScopeAfter,\r\n" + 
    		"	spcc.representative_name_bf representativeName,\r\n" + 
    		"	spcc.representative_name_af representativeNameAfter,\r\n" + 
    		"	spcc.corporate_identity_bf corporateIdentity,\r\n" + 
    		"	spcc.corporate_identity_af corporateIdentityAfter,\r\n" + 
    		"	spcc.bank_permit_number_bf bankPermitNumber,\r\n" + 
    		"	spcc.bank_permit_number_af bankPermitNumberAfter,\r\n" + 
    		"	spcc.license_file_id_bf licenseFileId,\r\n" +
		    "  spcc.establishment_date_bf establishmentDate,\r\n" +
		    "  spcc.establishment_date_af establishmentDateAfter,\r\n" +
		  "	sbrf3.access_Path licenseFilePath,\r\n" +
    		"	sbrf3.file_Name licenseFileName,\r\n" + 
    		"	spcc.license_file_id_af licenseFileIdAfter,\r\n" + 
    		"	sbrf4.access_Path licenseFilePathAfter,\r\n" + 
    		"	sbrf4.file_Name licenseFileNameAfter,\r\n" + 
    		"	spcc.tissue_file_id_bf tissueFileId,\r\n" + 
    		"	sbrf5.access_Path tissueFilePath,\r\n" + 
    		"	sbrf5.file_Name tissueFileName,\r\n" + 
    		"	spcc.tissue_file_id_af tissueFileIdAfter,\r\n" + 
    		"	sbrf6.access_Path tissueFilePathAfter,\r\n" + 
    		"	sbrf6.file_Name tissueFileNameAfter,\r\n" + 
    		"	spcc.bank_permit_file_id_bf bankPermitFileId,\r\n" + 
    		"	sbrf7.access_Path bankPermitFilePath,\r\n" + 
    		"	sbrf7.file_Name bankPermitFileName,\r\n" + 
    		"	spcc.bank_permit_file_id_af bankPermitFileIdAfter,\r\n" + 
    		"	sbrf8.access_Path bankPermitFilePathAfter,\r\n" + 
    		"	sbrf8.file_Name bankPermitFileNameAfter,\r\n" + 
    		"	spcc.tax_file_id_bf taxFileId,\r\n" + 
    		"	sbrf9.access_Path taxFilePath,\r\n" + 
    		"	sbrf9.file_Name taxFileName,\r\n" + 
    		"	spcc.tax_file_id_af taxFileIdAfter,\r\n" + 
    		"	sbrf10.access_Path taxFilePathAfter,\r\n" + 
    		"	sbrf10.file_Name taxFileNameAfter,\r\n" + 
    		"	spcc.others_file_id_bf othersFileId,\r\n" + 
    		"	sbrf11.access_Path othersFilePath,\r\n" + 
    		"	sbrf11.file_Name othersFileName,\r\n" + 
    		"	spcc.others_file_id_af othersFileIdAfter,\r\n" + 
    		"	sbrf12.access_Path othersFilePathAfter,\r\n" +
    		"	sbrf12.file_Name othersFileNameAfter\r\n" +
    		"FROM\r\n" + 
    		"	srm_pos_change_base spcb\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf1 ON spcb.supplier_file_id_bf = sbrf1.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf2 ON spcb.supplier_file_id_af = sbrf2.file_id\r\n" +
            "LEFT JOIN saaf_employees se ON spcb.acct_check_staff_bf = se.employee_number\r\n" +
            "LEFT JOIN saaf_employees se1 ON spcb.acct_check_staff_af = se1.employee_number\r\n" +
		    "LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_SETTLE_ACCT_TYPE' and spcb.settle_acct_type_bf=slv1.lookup_code\r\n" +
		    "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'POS_SETTLE_ACCT_TYPE' and spcb.settle_acct_type_af=slv2.lookup_code,\r\n" +
    		" srm_pos_change_credentials spcc\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf3 ON spcc.license_file_id_bf = sbrf3.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf4 ON spcc.license_file_id_af = sbrf4.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf5 ON spcc.tissue_file_id_bf = sbrf5.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf6 ON spcc.tissue_file_id_af = sbrf6.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf7 ON spcc.bank_permit_file_id_bf = sbrf7.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf8 ON spcc.bank_permit_file_id_af = sbrf8.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf9 ON spcc.tax_file_id_bf = sbrf9.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf10 ON spcc.tax_file_id_af = sbrf10.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf11 ON spcc.others_file_id_bf = sbrf11.file_id\r\n" + 
    		"LEFT JOIN saaf_base_result_file sbrf12 ON spcc.others_file_id_af = sbrf12.file_id\r\n" + 
    		"WHERE\r\n" + 
    		"	1 = 1\r\n" + 
    		"AND spcb.change_id = spcc.change_id";

	//查询变更的产品与服务
	public static String QUERY_CHANGE_CATEGORY ="SELECT\n" +
			"\tspsc.change_category_id changeCategoryId,\n" +
			"\tspsc.supplier_category_id supplierCategoryId,\n" +
			"\tspsc.supplier_id supplierId,\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.category_id categoryId,\n" +
			"\tspsc.invalid_date invalidDate,\n" +
			"\tspsc.status status,\n" +
			"\tspsc.creation_date creationDate,\n" +
			"\tsbc.category_name fullCategoryName,\n" +
			"\tsbc.category_code fullCategoryCode,\n" +
			"\tslv4.meaning statusName\n" +
			"FROM\n" +
			"\tsrm_pos_change_categories spsc\n" +
			"LEFT JOIN srm_base_categories sbc ON spsc.category_id = sbc.category_id\n" +
			"LEFT JOIN saaf_lookup_values slv4 ON slv4.lookup_type = 'POS_CATEGORY_STATUS' and spsc.status = slv4.lookup_code\n" +
			"WHERE\n" +
			"\t1=1";

	//查询变更的认证证书
	public static String QUERY_CHANGE_CERTIFICATE ="SELECT\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.change_certificate_id changeCertificateId,\n" +
			"\tspsc.certificate_id certificateId,\n" +
			"\tspsc.certificate_name certificateName,\n" +
			"\tspsc.certificate_number certificateNumber,\n" +
			"\tspsc.file_id fileId,\n" +
			"\tsbrf.access_Path filePath,\n" +
			"\tsbrf.file_Name fileName,\n" +
			"\tspsc.start_date startDate,\n" +
			"\tspsc.certificate_description certificateDescription\n" +
			"FROM\n" +
			"\tsrm_pos_change_certificate spsc\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsc.file_id = sbrf.file_id\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的认证证书(保存前)
	public static String QUERY_SUPPLIER_CERTIFICATE ="SELECT\n" +
			"\tspsc.supplier_id supplierId,\n" +
			"\tspsc.certificate_id certificateId,\n" +
			"\tspsc.certificate_name certificateName,\n" +
			"\tspsc.certificate_number certificateNumber,\n" +
			"\tspsc.file_id fileId,\n" +
			"\tsbrf.access_Path filePath,\n" +
			"\tsbrf.file_Name fileName,\n" +
			"\tspsc.start_date startDate,\n" +
			"\tspsc.end_date endDate,\n" +
			"\tspsc.certificate_description certificateDescription\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_certificate spsc\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsc.file_id = sbrf.file_id\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的认证证书（变更前）
	public static String QUERY_CHANGE_CERTIFICATE_BF ="SELECT\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.change_certificate_bf_id changeCertificateBfId,\n" +
			"\tspsc.certificate_id certificateId,\n" +
			"\tspsc.certificate_name certificateName,\n" +
			"\tspsc.certificate_number certificateNumber,\n" +
			"\tspsc.file_id fileId,\n" +
			"\tsbrf.access_Path filePath,\n" +
			"\tsbrf.file_Name fileName,\n" +
			"\tspsc.end_date endDate,\n" +
			"\tspsc.certificate_description certificateDescription\n" +
			"FROM\n" +
			"\tsrm_pos_change_certificate_bf spsc\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsc.file_id = sbrf.file_id\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的认证证书（变更后）
	public static String QUERY_CHANGE_CERTIFICATE_AF ="SELECT\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.change_certificate_af_id changeCertificateAfId,\n" +
			"\tspsc.certificate_id certificateIdAf,\n" +
			"\tspsc.certificate_name certificateNameAf,\n" +
			"\tspsc.certificate_number certificateNumberAf,\n" +
			"\tspsc.file_id fileId,\n" +
			"\tsbrf.access_Path filePath,\n" +
			"\tsbrf.file_Name fileName,\n" +
			"\tspsc.end_date endDateAf,\n" +
			"\tspsc.certificate_description certificateDescriptionAf\n" +
			"FROM\n" +
			"\tsrm_pos_change_certificate_af spsc\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsc.file_id = sbrf.file_id\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的银行信息
	public static String QUERY_CHANGE_BANK ="SELECT\n" +
			"\tspsb.bank_account_id_bf bankAccountId,\n" +
			"\tspsb.bank_account_id_af bankAccountIdAfter,\n" +
			"\tspsb.bank_account_number_bf bankAccountNumber,\n" +
			"\tspsb.bank_account_number_af bankAccountNumberAfter,\n" +
			"\tspsb.bank_user_name_bf bankUserName,\n" +
			"\tspsb.bank_user_name_af bankUserNameAfter,\n" +
			"\tspsb.bank_id_bf bankId,\n" +
			"\tspsb.bank_id_af bankIdAfter,\n" +
			"\tspsb.bank_name_bf bankName,\n" +
			"\tspsb.bank_name_af bankNameAfter,\n" +
			"\tslv.meaning bankNameStr,\n" +
			"\tslv1.meaning bankNameStrAfter,\n" +
			"\tspsb.branch_id_bf branchId,\n" +
			"\tspsb.branch_id_af branchIdAfter,\n" +
			"\tspsb.branch_name_bf branchName,\n" +
			"\tspsb.branch_name_af branchNameAfter,\n" +
			"\tspsb.branch_number_bf branchNumber,\n" +
			"\tspsb.branch_number_af branchNumberAfter,\n" +
			"\tspsb.bank_currency_bf bankCurrency,\n" +
			"\tspsb.bank_currency_af bankCurrencyAfter,\n" +
			"\tspsb.end_date_bf endDate,\n" +
			"\tspsb.end_date_af endDateAfter,\n" +
			"\tspsb.invalid_date_bf invalidDate,\n" +
			"\tspsb.invalid_date_af invalidDateAfter,\n" +
			"\tspsb.change_id changeId,\n" +
			"\tspsb.change_bank_id changeBankId,\n" +
			"\tspsb.bank_link_bf bankLink,\n" +
			"\tspsb.bank_link_Af bankLinkAfter\n" +
			"FROM\n" +
			"\tsrm_pos_change_bank spsb LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'POS_BANK_NAME' and spsb.bank_name_bf=slv.lookup_code\n" +
			"\tLEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_BANK_NAME' and spsb.bank_name_af=slv1.lookup_code\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的银行信息(变更前)
	public static String QUERY_CHANGE_BANK_BF ="SELECT\n" +
			"\tspsb.change_bank_bf_id changeBankBfId,\n" +
			"\tspsb.bank_account_number bankAccountNumber,\n" +
			"\tspsb.bank_user_name bankUserName,\n" +
			"\tspsb.bank_id bankId,\n" +
			"\tspsb.bank_name bankName,\n" +
			"\tslv.meaning bankNameStr,\n" +
			"\tspsb.branch_id branchId,\n" +
			"\tspsb.branch_name branchName,\n" +
			"\tspsb.branch_number branchNumber,\n" +
			"\tspsb.bank_currency bankCurrency,\n" +
			"\tspsb.end_date endDate,\n" +
			"\tspsb.invalid_date invalidDate,\n" +
			"\tspsb.change_id changeId,\n" +
			"\tspsb.swift_code swiftCode,\n" +
			"\tspsb.iban_code ibanCode,\n" +
			"\tspsb.bank_link bankLink,\n" +
			"\tspsb.bank_account_id bankAccountId\n" +
			"FROM\n" +
			"\tsrm_pos_change_bank_bf spsb LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'POS_BANK_NAME' and spsb.bank_name=slv.lookup_code\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的银行信息（变更后）
	public static String QUERY_CHANGE_BANK_AF ="SELECT\n" +
			"\tspsb.change_bank_af_id changeBankAfId,\n" +
			"\tspsb.bank_account_number bankAccountNumberAf,\n" +
			"\tspsb.bank_user_name bankUserNameAf,\n" +
			"\tspsb.bank_id bankIdAf,\n" +
			"\tspsb.bank_name bankNameAf,\n" +
			"\tslv.meaning bankNameStrAf,\n" +
			"\tspsb.branch_id branchIdAf,\n" +
			"\tspsb.branch_name branchNameAf,\n" +
			"\tspsb.branch_number branchNumberAf,\n" +
			"\tspsb.bank_currency bankCurrencyAf,\n" +
			"\tspsb.end_date endDateAf,\n" +
			"\tspsb.invalid_date invalidDateAf,\n" +
			"\tspsb.change_id changeId,\n" +
			"\tspsb.swift_code swiftCodeAf,\n" +
			"\tspsb.iban_code ibanCodeAf,\n" +
            "\tspsb.bank_link bankLinkAf,\n" +
			"\tspsb.bank_account_id bankAccountIdAf\n" +
			"FROM\n" +
			"\tsrm_pos_change_bank_af spsb LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'POS_BANK_NAME' and spsb.bank_name=slv.lookup_code\n" +
			"WHERE\n" +
			"\t1 = 1 ";

	//查询变更的联系人
	public static String QUERY_CHANGE_CONTACTS ="SELECT\n" +
			"\tspsc.supplier_contact_id supplierContactId,\n" +
			"\tspsc.supplier_id supplierId,\n" +
			"\tspsc.contact_name contactName,\n" +
			"\tspsc.mobile_phone mobilePhone,\n" +
			"\tspsc.email_address emailAddress,\n" +
			"\tspsc.fixed_phone fixedPhone,\n" +
			"\tspsc.fax_phone faxPhone,\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.change_contacts_id changeContactsId\n" +
			"FROM\n" +
			"\tsrm_pos_change_contacts spsc\n" +
			"WHERE\n" +
			"\t1 = 1";

	//查询变更的联系人(变更前)
	public static String QUERY_CHANGE_CONTACTS_BF ="SELECT\n" +
			"\tspsc.supplier_contact_id supplierContactId,\n" +
			"\tspsc.contact_name contactName,\n" +
			"\tspsc.mobile_phone mobilePhone,\n" +
			"\tspsc.email_address emailAddress,\n" +
			"\tspsc.fixed_phone fixedPhone,\n" +
			"\tspsc.fax_phone faxPhone,\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.failure_date failureDate,\n" +
			"\tspsc.change_contact_bf_id changeContactBfId\n" +
			"FROM\n" +
			"\tsrm_pos_change_contact_bf spsc\n" +
			"WHERE\n" +
			"\t1 = 1";

	//查询变更的联系人(变更后)
	public static String QUERY_CHANGE_CONTACTS_AF ="SELECT\n" +
			"\tspsc.supplier_contact_id supplierContactIdAf,\n" +
			"\tspsc.contact_name contactNameAf,\n" +
			"\tspsc.mobile_phone mobilePhoneAf,\n" +
			"\tspsc.email_address emailAddressAf,\n" +
			"\tspsc.fixed_phone fixedPhoneAf,\n" +
			"\tspsc.fax_phone faxPhoneAf,\n" +
			"\tspsc.change_id changeId,\n" +
			"\tspsc.failure_date failureDateAf,\n" +
			"\tspsc.change_contact_af_id changeContactAfId\n" +
			"FROM\n" +
			"\tsrm_pos_change_contact_af spsc\n" +
			"WHERE\n" +
			"\t1 = 1";

    //查询变更的地址簿(变更前)
    public static String QUERY_CHANGE_ADDRESS_BF ="SELECT\n" +
            "\tspsa.change_address_bf_id changeAddressBfId,\n" +
            "\tspsa.change_id changeId,\n" +
            "\tspsa.supplier_address_id supplierAddressId,\n" +
            "\tspsa.address_name addressName,\n" +
            "\tspsa.country country,\n" +
            "\tslv.meaning AS countryName,\n" +
            "\tspsa.province province,\n" +
            "\tspsa.city city,\n" +
            "\tspsa.county county,\n" +
            "\tspsa.invalid_date invalidDate,\n" +
            "\tspsa.detail_address detailAddress\n" +
            "FROM\n" +
            "\tsrm_pos_change_address_bf spsa\n" +
            "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'BASE_COUNTRY'\n" +
            "AND spsa.country = slv.lookup_code\n" +
            "WHERE\n" +
            "\t1 = 1";

    //查询变更的地址簿(变更后)
    public static String QUERY_CHANGE_ADDRESS_AF ="SELECT\n" +
            "\tspsa.change_address_af_id changeAddressAfId,\n" +
            "\tspsa.change_id changeId,\n" +
            "\tspsa.supplier_address_id supplierAddressIdAf,\n" +
            "\tspsa.address_name addressNameAf,\n" +
            "\tspsa.country countryAf,\n" +
            "\tslv.meaning AS countryNameAf,\n" +
            "\tspsa.province provinceAf,\n" +
            "\tspsa.city cityAf,\n" +
            "\tspsa.county countyAf,\n" +
            "\tspsa.invalid_date invalidDateAf,\n" +
            "\tspsa.detail_address detailAddressAf\n" +
            "FROM\n" +
            "\tsrm_pos_change_address_af spsa\n" +
            "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'BASE_COUNTRY'\n" +
            "AND spsa.country = slv.lookup_code\n" +
            "WHERE\n" +
            "\t1 = 1";

    public static String QUERY_SUPPLIER_INFO_LIST = "SELECT\n" +
            "\tspsi.supplier_id supplierId,\n" +
            "\tspsi.supplier_name supplierName,\n" +
            "\tspsi.supplier_name supplierNameAfter,\n" +
            "\tspsi.supplier_number supplierNumber,\n" +
            "\tspsi.supplier_type supplierType,\n" +
            "\tspsi.supplier_type supplierTypeAfter,\n" +
            "\tspsi.supplier_status supplierStatus,\n" +
            "\tspsc.license_num licenseNum,\n" +
            "\tspsc.license_num licenseNumAfter,\n" +
            "\tspsc.tissue_code tissueCode,\n" +
            "\tspsc.tissue_code tissueCodeAfter,\n" +
            "\tspsc.tax_code taxCode,\n" +
            "\tspsc.tax_code taxCodeAfter,\n" +
            "\tslv.meaning statusStr,\n" +
            "\tslv1.meaning typeStr,\n" +
            "\tspsi.supplier_short_name supplierShortName,\n" +
            "\tspsi.supplier_short_name supplierShortNameAfter,\n" +
            "\tspsi.company_phone companyPhone,\n" +
            "\tspsi.company_phone companyPhoneAfter,\n" +
            "\tspsi.staff_num staffNum,\n" +
            "\tspsi.staff_num staffNumAfter,\n" +
            "\tspsi.supplier_industry supplierIndustry,\n" +
            "\tspsi.supplier_industry supplierIndustryAfter,\n" +
            "\tspsi.company_fax companyFax,\n" +
            "\tspsi.company_fax companyFaxAfter,\n" +
            "\tspsi.purchase_flag purchaseFlag,\n" +
            "\tspsi.purchase_flag purchaseFlagAfter,\n" +
			"\tspsi.address address,\n" +
			"\tspsi.address addressAfter,\n" +
            "\tspsi.payment_flag paymentFlag,\n" +
            "\tspsi.payment_flag paymentFlagAfter,\n" +
			"\tspsi.srm_delivery srmDelivery,\n" +
			"\tspsi.srm_delivery srmDeliveryAfter,\n" +
            "\tspsi.able_check_order_flag ableCheckOrderFlag,\n" +
            "\tspsi.able_check_order_flag ableCheckOrderFlagAfter,\n" +
            "\tspsi.company_description companyDescription,\n" +
            "\tspsi.company_description companyDescriptionAfter,\n" +
            "\tspsc.credentials_id credentialsId,\n" +
            "\tspsc.license_indate licenseIndate,\n" +
            "\tspsc.license_indate licenseIndateAfter,\n" +
            "\tspsc.long_license_indate longLicenseIndate,\n" +
            "\tspsc.long_license_indate longLicenseIndateAfter,\n" +
            "\tspsc.tissue_indate tissueIndate,\n" +
            "\tspsc.tissue_indate tissueIndateAfter,\n" +
            "\tspsc.bank_permit_number bankPermitNumber,\n" +
            "\tspsc.bank_permit_number bankPermitNumberAfter,\n" +
            "\tspsc.much_in_one muchInOne,\n" +
			"\tspsc.much_in_one muchInOneAfter,\n" +
            "\tspsc.business_scope businessScope,\n" +
            "\tspsc.business_scope businessScopeAfter,\n" +
            "\tspsc.corporate_identity corporateIdentity,\n" +
            "\tspsc.corporate_identity corporateIdentityAfter,\n" +
            "\tspsc.representative_name representativeName,\n" +
            "\tspsc.representative_name representativeNameAfter,\n" +
            "\tspsc.license_file_id licenseFileId,\n" +
            "\tspsc.license_file_id licenseFileIdAfter,\n" +
			"\tspsc.establishment_date establishmentDate,\n" +
			"\tspsc.establishment_date establishmentDateAfter,\n" +
            "\tsbrf1.access_Path licenseFilePath,\n" +
            "\tsbrf1.access_Path licenseFilePathAfter,\n" +
            "\tsbrf1.file_Name licenseFileName,\n" +
            "\tsbrf1.file_Name licenseFileNameAfter,\n" +
            "\tspsc.tissue_file_id tissueFileId,\n" +
            "\tspsc.tissue_file_id tissueFileIdAfter,\n" +
            "\tsbrf2.access_Path tissueFilePath,\n" +
            "\tsbrf2.access_Path tissueFilePathAfter,\n" +
            "\tsbrf2.file_Name tissueFileName,\n" +
            "\tsbrf2.file_Name tissueFileNameAfter,\n" +
            "\tspsc.tax_file_id taxFileId,\n" +
            "\tspsc.tax_file_id taxFileIdAfter,\n" +
            "\tsbrf3.access_Path taxFilePath,\n" +
            "\tsbrf3.access_Path taxFilePathAfter,\n" +
            "\tsbrf3.file_Name taxFileName,\n" +
            "\tsbrf3.file_Name taxFileNameAfter,\n" +
            "\tspsc.bank_permit_file_id bankPermitFileId,\n" +
            "\tspsc.bank_permit_file_id bankPermitFileIdAfter,\n" +
            "\tsbrf4.access_Path bankPermitFilePath,\n" +
            "\tsbrf4.access_Path bankPermitFilePathAfter,\n" +
            "\tsbrf4.file_Name bankPermitFileName,\n" +
            "\tsbrf4.file_Name bankPermitFileNameAfter,\n" +
            "\tspsc.others_file_id othersFileId,\n" +
            "\tspsc.others_file_id othersFileIdAfter,\n" +
            "\tsbrf5.access_Path othersFilePath,\n" +
            "\tsbrf5.access_Path othersFilePathAfter,\n" +
            "\tsbrf5.file_Name othersFileName,\n" +
            "\tsbrf5.file_Name othersFileNameAfter,\n" +
            "\tspsi.supplier_file_id supplierFileId,\n" +
            "\tspsi.supplier_file_id supplierFileIdAfter,\n" +
            "\tsbrf.access_Path supplierFilePath,\n" +
            "\tsbrf.access_Path supplierFilePathAfter,\n" +
            "\tsbrf.file_Name supplierFileName,\n" +
            "\tsbrf.file_Name supplierFileNameAfter,\n" +
            "\tsu.user_name userName,\n" +
            "\tspsi.fin_classify finClassify,\n" +
            "\tspsi.fin_classify finClassifyAfter,\n" +
            "\tspsi.settle_acct_type settleAcctType,\n" +
            "\tspsi.settle_acct_type settleAcctTypeAfter,\n" +
            "\tspsi.acct_check_staff acctCheckStaff,\n" +
            "\tspsi.acct_check_staff acctCheckStaffAfter,\n" +
            "\tspsi.acct_check_type acctCheckType,\n" +
            "\tspsi.acct_check_type acctCheckTypeAfter,\n" +
//            "\tse.employee_name employeeName,\n" +
//            "\tse.employee_name employeeNameAfter,\n" +
            "\tspsi.pos_acct_condition posAcctCondition,\n" +
            "\tspsi.pos_acct_condition posAcctConditionAfter,\n" +
            "\tspsi.pos_tax posTax,\n" +
            "\tspsi.pos_tax posTaxAfter,\n" +
			"\tspsi.company_Registered_Address     companyRegisteredAddress,\n" +
			"\tspsi.company_Registered_Address     companyRegisteredAddressAfter,\n" +
			"\tspsi.registered_Capital             registeredCapital,\n" +
			"\tspsi.registered_Capital             registeredCapitalAfter,\n" +
			"\tspsi.independent_Legal_Person_Flag  indLegalPersonFlag,\n" +
			"\tspsi.independent_Legal_Person_Flag  indLegalPersonFlagAfter,\n" +
			"\tspsi.value_Added_Tax_Invoice_Flag   valueAddedTaxInvoiceFlag,\n" +
			"\tspsi.value_Added_Tax_Invoice_Flag   valueAddedTaxInvoiceFlagAfter,\n" +
			"\tspsi.value_Added_Tax_Invoice_Desc   valueAddedTaxInvoiceDesc,\n" +
			"\tspsi.value_Added_Tax_Invoice_Desc   valueAddedTaxInvoiceDescAfter,\n" +
			"\tspsi.associated_Company         	   associatedCompany,\n" +
			"\tspsi.associated_Company         	   associatedCompanyAfter,\n" +
			"\tspsi.region			         	   region,\n" +
			"\tspsi.region			         	   regionAfter\n" +
            "FROM\n" +
            "\tsrm_pos_supplier_info spsi\n" +
            "LEFT JOIN saaf_base_result_file sbrf ON spsi.supplier_file_id = sbrf.file_id\n" +
            "LEFT JOIN saaf_users su ON spsi.supplier_id = su.supplier_id,\n" +
//            "LEFT JOIN saaf_employees se ON spsi.acct_check_staff = se.employee_number,\n" +
            " srm_pos_supplier_credentials spsc\n" +
            "LEFT JOIN saaf_base_result_file sbrf1 ON spsc.license_file_id = sbrf1.file_id\n" +
            "LEFT JOIN saaf_base_result_file sbrf2 ON spsc.tissue_file_id = sbrf2.file_id\n" +
            "LEFT JOIN saaf_base_result_file sbrf3 ON spsc.tax_file_id = sbrf3.file_id\n" +
            "LEFT JOIN saaf_base_result_file sbrf4 ON spsc.bank_permit_file_id = sbrf4.file_id\n" +
            "LEFT JOIN saaf_base_result_file sbrf5 ON spsc.others_file_id = sbrf5.file_id,\n" +
            " saaf_lookup_values slv,\n" +
            " saaf_lookup_values slv1\n" +
            "WHERE\n" +
            "\tspsi.supplier_id = spsc.supplier_id\n" +
            "AND slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
            "AND spsi.supplier_status = slv.lookup_code\n" +
            "AND slv1.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
            "AND spsi.supplier_type = slv1.lookup_code";

	public String getBankAccountNumberAfter() {
		return bankAccountNumberAfter;
	}

	public void setBankAccountNumberAfter(String bankAccountNumberAfter) {
		this.bankAccountNumberAfter = bankAccountNumberAfter;
	}

	public String getBankUserNameAfter() {
		return bankUserNameAfter;
	}

	public void setBankUserNameAfter(String bankUserNameAfter) {
		this.bankUserNameAfter = bankUserNameAfter;
	}

	public Integer getBankIdAfter() {
		return bankIdAfter;
	}

	public void setBankIdAfter(Integer bankIdAfter) {
		this.bankIdAfter = bankIdAfter;
	}

	public String getBankNameAfter() {
		return bankNameAfter;
	}

	public void setBankNameAfter(String bankNameAfter) {
		this.bankNameAfter = bankNameAfter;
	}

	public Integer getBranchIdAfter() {
		return branchIdAfter;
	}

	public void setBranchIdAfter(Integer branchIdAfter) {
		this.branchIdAfter = branchIdAfter;
	}

	public String getBranchNumberAfter() {
		return branchNumberAfter;
	}

	public void setBranchNumberAfter(String branchNumberAfter) {
		this.branchNumberAfter = branchNumberAfter;
	}

	public String getBranchNameAfter() {
		return branchNameAfter;
	}

	public void setBranchNameAfter(String branchNameAfter) {
		this.branchNameAfter = branchNameAfter;
	}

	public String getBankCurrencyAfter() {
		return bankCurrencyAfter;
	}

	public void setBankCurrencyAfter(String bankCurrencyAfter) {
		this.bankCurrencyAfter = bankCurrencyAfter;
	}

	public Date getEndDateAfter() {
		return endDateAfter;
	}

	public void setEndDateAfter(Date endDateAfter) {
		this.endDateAfter = endDateAfter;
	}

	public Date getInvalidDateAfter() {
		return invalidDateAfter;
	}

	public void setInvalidDateAfter(Date invalidDateAfter) {
		this.invalidDateAfter = invalidDateAfter;
	}

	public static String QUERY_SUPPLIER_BANK="SELECT\n" +
			"\tspsb.bank_account_id bankAccountId,\n" +
			"\tspsb.supplier_id supplierId,\n" +
			"\tspsb.bank_account_number bankAccountNumber,\n" +
			"\tspsb.bank_account_number bankAccountNumberAfter,\n" +
			"\tspsb.bank_user_name bankUserName,\n" +
			"\tspsb.bank_user_name bankUserNameAfter,\n" +
			"\tspsb.bank_id bankId,\n" +
			"\tspsb.bank_id bankIdAfter,\n" +
			"\tspsb.bank_name bankName,\n" +
			"\tslv.meaning bankNameStr,\n" +
			"\tslv.meaning bankNameStrAfter,\n" +
			"\tspsb.bank_name bankNameAfter,\n" +
			"\tspsb.branch_id branchId,\n" +
			"\tspsb.branch_id branchIdAfter,\n" +
			"\tspsb.branch_name branchName,\n" +
			"\tspsb.branch_name branchNameAfter,\n" +
			"\tspsb.branch_number branchNumber,\n" +
			"\tspsb.branch_number branchNumberAfter,\n" +
			"\tspsb.bank_currency bankCurrency,\n" +
			"\tspsb.bank_currency bankCurrencyAfter,\n" +
			"\tspsb.start_date startDate,\n" +
			"\tspsb.start_date startDateAfter,\n" +
			"\tspsb.invalid_date invalidDate,\n" +
			"\tspsb.invalid_date invalidDateAfter,\n" +
			"\tspsb.swift_code swiftCode,\n" +
			"\tspsb.iban_code ibanCode,\n" +
			"\tspsb.bank_link bankLink,\n" +
			"\tspsb.bank_link bankLinkAfter,\n" +
			"\tspsb.end_date endDate,\n" +

			"\tspsb.end_date endDateAfter\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_bank spsb LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'POS_BANK_NAME' and spsb.bank_name=slv.lookup_code\n" +
			"WHERE\n" +
			"\t1 = 1";

    private Integer changeId;
    private Integer supplierId;
    private String supplierNumber;
    private String changeNumber;
    private String sourceType;
    private String changeStatus;
    private String changeStatusStr;
    private String changeReason;
    private String description;
    private String reviewer;
    @JSONField(format = "yyyy-MM-dd")
    private Date auditTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private String createdByName;
    private Integer fileId;
    private String filePath;
    private String fileName;
    private Integer changeBaseId;
    private String supplierName;
    private String supplierNameAfter;
    private String supplierShortName;
    private String supplierShortNameAfter;
    private String supplierType;
    private String supplierTypeAfter;
    private String supplierClassify;
	private String supplierClassifyAfter;
    private String supplierIndustry;
    private String supplierIndustryAfter;
    private String finClassify;
    private String finClassifyAfter;
	private String address;
	private String addressAfter;
    private String employeeName;
    private String employeeNameAfter;
    private String settleAcctType;
    private String settleAcctTypeAfter;
	private String posAcctCondition;
	private String posAcctConditionAfter;
	private String acctCheckStaff;
	private String settleAcctTypeName;
	private String settleAcctTypeNameAfter;
	private String srmDelivery;
	private String srmDeliveryAfter;
	private String companyRegisteredAddress; //公司注册地址
	private String companyRegisteredAddressAfter; //公司注册地址
	private String registeredCapital; //注册资金(万)
	private String registeredCapitalAfter; //注册资金(万)
	private String indLegalPersonFlag; //是否独立法人
	private String indLegalPersonFlagAfter; //是否独立法人
	private String valueAddedTaxInvoiceFlag; //能否开6个税点的增值税专用发票
	private String valueAddedTaxInvoiceFlagAfter; //能否开6个税点的增值税专用发票
	private String valueAddedTaxInvoiceDesc; //能否开6个税点的增值税专用发票-说明
	private String valueAddedTaxInvoiceDescAfter; //能否开6个税点的增值税专用发票-说明
	private String associatedCompany; //关联公司
	private String associatedCompanyAfter; //关联公司
	private String region; //意向服务区域
	private String regionAfter; //意向服务区域

	public String getSrmDelivery() { return srmDelivery; }

	public void setSrmDelivery(String srmDelivery) { this.srmDelivery = srmDelivery; }

	public String getSrmDeliveryAfter() { return srmDeliveryAfter; }

	public void setSrmDeliveryAfter(String srmDeliveryAfter) { this.srmDeliveryAfter = srmDeliveryAfter; }

	public String getCompanyRegisteredAddress() {
		return companyRegisteredAddress;
	}

	public void setCompanyRegisteredAddress(String companyRegisteredAddress) {
		this.companyRegisteredAddress = companyRegisteredAddress;
	}

	public String getCompanyRegisteredAddressAfter() {
		return companyRegisteredAddressAfter;
	}

	public void setCompanyRegisteredAddressAfter(String companyRegisteredAddressAfter) {
		this.companyRegisteredAddressAfter = companyRegisteredAddressAfter;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRegisteredCapitalAfter() {
		return registeredCapitalAfter;
	}

	public void setRegisteredCapitalAfter(String registeredCapitalAfter) {
		this.registeredCapitalAfter = registeredCapitalAfter;
	}

	public String getIndLegalPersonFlag() {
		return indLegalPersonFlag;
	}

	public void setIndLegalPersonFlag(String indLegalPersonFlag) {
		this.indLegalPersonFlag = indLegalPersonFlag;
	}

	public String getIndLegalPersonFlagAfter() {
		return indLegalPersonFlagAfter;
	}

	public void setIndLegalPersonFlagAfter(String indLegalPersonFlagAfter) {
		this.indLegalPersonFlagAfter = indLegalPersonFlagAfter;
	}

	public String getValueAddedTaxInvoiceFlag() {
		return valueAddedTaxInvoiceFlag;
	}

	public void setValueAddedTaxInvoiceFlag(String valueAddedTaxInvoiceFlag) {
		this.valueAddedTaxInvoiceFlag = valueAddedTaxInvoiceFlag;
	}

	public String getValueAddedTaxInvoiceFlagAfter() {
		return valueAddedTaxInvoiceFlagAfter;
	}

	public void setValueAddedTaxInvoiceFlagAfter(String valueAddedTaxInvoiceFlagAfter) {
		this.valueAddedTaxInvoiceFlagAfter = valueAddedTaxInvoiceFlagAfter;
	}

	public String getValueAddedTaxInvoiceDesc() {
		return valueAddedTaxInvoiceDesc;
	}

	public void setValueAddedTaxInvoiceDesc(String valueAddedTaxInvoiceDesc) {
		this.valueAddedTaxInvoiceDesc = valueAddedTaxInvoiceDesc;
	}

	public String getValueAddedTaxInvoiceDescAfter() {
		return valueAddedTaxInvoiceDescAfter;
	}

	public void setValueAddedTaxInvoiceDescAfter(String valueAddedTaxInvoiceDescAfter) {
		this.valueAddedTaxInvoiceDescAfter = valueAddedTaxInvoiceDescAfter;
	}

	public String getAssociatedCompany() {
		return associatedCompany;
	}

	public void setAssociatedCompany(String associatedCompany) {
		this.associatedCompany = associatedCompany;
	}

	public String getAssociatedCompanyAfter() {
		return associatedCompanyAfter;
	}

	public void setAssociatedCompanyAfter(String associatedCompanyAfter) {
		this.associatedCompanyAfter = associatedCompanyAfter;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionAfter() {
		return regionAfter;
	}

	public void setRegionAfter(String regionAfter) {
		this.regionAfter = regionAfter;
	}

	public String getSettleAcctTypeName() {
		return settleAcctTypeName;
	}

	public void setSettleAcctTypeName(String settleAcctTypeName) {
		this.settleAcctTypeName = settleAcctTypeName;
	}

	public String getSettleAcctTypeNameAfter() {
		return settleAcctTypeNameAfter;
	}

	public void setSettleAcctTypeNameAfter(String settleAcctTypeNameAfter) {
		this.settleAcctTypeNameAfter = settleAcctTypeNameAfter;
	}

	public String getBankNameStr() {
		return bankNameStr;
	}

	public void setBankNameStr(String bankNameStr) {
		this.bankNameStr = bankNameStr;
	}

	public String getBankNameStrAfter() {
		return bankNameStrAfter;
	}

	public void setBankNameStrAfter(String bankNameStrAfter) {
		this.bankNameStrAfter = bankNameStrAfter;
	}

	private String bankNameStr;
	private String bankNameStrAfter;

    public String getPosTaxAfter() {
        return posTaxAfter;
    }

    public void setPosTaxAfter(String posTaxAfter) {
        this.posTaxAfter = posTaxAfter;
    }

    public String getPosTax() {
        return posTax;
    }

    public void setPosTax(String posTax) {
        this.posTax = posTax;
    }

    public String getPosAcctConditionAfter() {
        return posAcctConditionAfter;
    }

    public void setPosAcctConditionAfter(String posAcctConditionAfter) { this.posAcctConditionAfter = posAcctConditionAfter; }

    public String getPosAcctCondition() {
        return posAcctCondition;
    }

    public void setPosAcctCondition(String posAcctCondition) {
        this.posAcctCondition = posAcctCondition;
    }

    public String getEmployeeNameAfter() {
        return employeeNameAfter;
    }

    public void setEmployeeNameAfter(String employeeNameAfter) {
        this.employeeNameAfter = employeeNameAfter;
    }

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	public String getAddressAfter() { return addressAfter; }

	public void setAddressAfter(String addressAfter) { this.addressAfter = addressAfter; }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    private String acctCheckStaffAfter;
    private String posTax;
    private String posTaxAfter;
    private String acctCheckType;
    private String acctCheckTypeAfter;
    private String paymentFlag;
    private String paymentFlagAfter;
    private String purchaseFlag;
    private String purchaseFlagAfter;
    private String homeUrl;
    private String homeUrlAfter;
    private String companyPhone;
    private String companyPhoneAfter;
    private String companyFax;
    private String companyFaxAfter;
    private Integer staffNum;
    private Integer staffNumAfter;
    private String floorArea;
    private String floorAreaAfter;
    private String companyDescription;
    private String companyDescriptionAfter;
    private Integer supplierFileId;
    private String supplierFilePath;
    private String supplierFileName;
    private Integer supplierFileIdAfter;
    private String supplierFilePathAfter;
    private String supplierFileNameAfter;
    private Integer changgeCredentialsId;
    private String currencyCode;
    private String currencyCodeAfter;
    private String muchInOne;
    private String muchInOneAfter;
    private String licenseNum;
    private String licenseNumAfter;
    @JSONField(format = "yyyy-MM-dd")
    private Date licenseIndate;
    @JSONField(format = "yyyy-MM-dd")
    private Date licenseIndateAfter;
    private String longLicenseIndate;
    private String longLicenseIndateAfter;
    private String taxCode;
    private String taxCodeAfter;
    private String tissueCode;
    private String tissueCodeAfter;
    @JSONField(format = "yyyy-MM-dd")
    private Date tissueIndate;
    @JSONField(format = "yyyy-MM-dd")
    private Date tissueIndateAfter;
    private String businessScope;
    private String businessScopeAfter;
    private String representativeName;
    private String representativeNameAfter;
    private String corporateIdentity;
    private String corporateIdentityAfter;
    private String bankPermitNumber;
    private String bankPermitNumberAfter;
    private Integer licenseFileId;
    private String licenseFilePath;
    private String licenseFileName;
    private Integer licenseFileIdAfter;
    private String licenseFilePathAfter;
    private String licenseFileNameAfter;
    private Integer tissueFileId;
    private String tissueFilePath;
    private String tissueFileName;
    private Integer tissueFileIdAfter;
    private String tissueFilePathAfter;
    private String tissueFileNameAfter;
    private Integer bankPermitFileId;
    private String bankPermitFilePath;
    private String bankPermitFileName;
    private Integer bankPermitFileIdAfter;
    private String bankPermitFilePathAfter;
    private String bankPermitFileNameAfter;
    private Integer taxFileId;
    private String taxFilePath;
    private String taxFileName;
    private Integer taxFileIdAfter;
    private String taxFilePathAfter;
    private String taxFileNameAfter;
    private Integer othersFileId;
    private String othersFilePath;
    private String othersFileName;
    private Integer othersFileIdAfter;
    private String othersFilePathAfter;
    private String othersFileNameAfter;
    private String ableCheckOrderFlag;
    private String ableCheckOrderFlagAfter;

    public String getAbleCheckOrderFlagAfter() {
        return ableCheckOrderFlagAfter;
    }

    public void setAbleCheckOrderFlagAfter(String ableCheckOrderFlagAfter) {
        this.ableCheckOrderFlagAfter = ableCheckOrderFlagAfter;
    }

    public String getAbleCheckOrderFlag() {
        return ableCheckOrderFlag;

    }

    public void setAbleCheckOrderFlag(String ableCheckOrderFlag) {
        this.ableCheckOrderFlag = ableCheckOrderFlag;
    }

    private Integer supplierCategoryId;
	private Integer categoryId;
	private Integer changeCategoryId;
    private String bigCategoryCode;
	private String fullCategoryCode;
    private String middleCategoryCode;
    private String smallCategoryCode;
    private String status;
    private String statusStr;
	private String statusName;
    private String bigCategoryName;
	private String fullCategoryName;
    private String middleCategoryName;
    private String smallCategoryName;
	private String categoryName;
	private String categoryCode;
    private Integer changeCertificateId;
	private Integer changeCertificateBfId;
	private Integer changeCertificateAfId;
    private Integer certificateId;
    private String certificateName;
    private String certificateNumber;
    private String certificateDescription;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
	private Integer certificateIdAf;
	private String certificateNameAf;
	private String certificateNumberAf;
	private String certificateDescriptionAf;
	@JSONField(format = "yyyy-MM-dd")
	private Date startDateAf;

//    private Integer purchaseLimit;
//
//    public Integer getQuotaRate() {
//        return quotaRate;
//    }
//
//    public void setQuotaRate(Integer quotaRate) {
//        this.quotaRate = quotaRate;
//    }
//
//    public Integer getSupplyOrder() {
//        return supplyOrder;
//    }
//
//    public void setSupplyOrder(Integer supplyOrder) {
//        this.supplyOrder = supplyOrder;
//    }
//
//    public Integer getPurchaseLimit() {
//        return purchaseLimit;
//    }
//
//    public void setPurchaseLimit(Integer purchaseLimit) {
//        this.purchaseLimit = purchaseLimit;
//    }
//
//    private Integer supplyOrder;
//    private Integer quotaRate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

	private Integer changeBankBfId;
	private Integer changeBankAfId;
	private String swiftCode;
	private String ibanCode;
	private String bankLink;
	private String bankLinkAfter;
    private Integer changeBankId;
    private Integer bankAccountId;
    private String bankAccountNumber;
    private String bankUserName;
    private Integer bankId;
    private String bankName;
    private Integer branchId;
    private String branchNumber;
    private String branchName;
    private String bankCurrency;
	private String bankAccountNumberAfter;
	private String bankUserNameAfter;
	private Integer bankIdAfter;
	private String bankNameAfter;
	private Integer branchIdAfter;
	private String branchNumberAfter;
	private String branchNameAfter;
	private String bankCurrencyAfter;
	private String licenseNumAf;//营业执照号

	private String bankAccountNumberAf;
	private String bankUserNameAf;
	private Integer bankIdAf;
	private String bankNameAf;
	private String bankNameStrAf;
	private Integer branchIdAf;
	private String branchNameAf;
	private String branchNumberAf;
	private String bankCurrencyAf;
	@JSONField(format = "yyyy-MM-dd")
	private Date endDateAf;
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDateAf;
	private String swiftCodeAf;
	private String ibanCodeAf;
	private String bankLinkAf;
	private Integer bankAccountIdAf;

	public String getBankLink() {
		return bankLink;
	}

	public void setBankLink(String bankLink) {
		this.bankLink = bankLink;
	}

	public String getBankLinkAf() {
		return bankLinkAf;
	}

	public void setBankLinkAf(String bankLinkAf) {
		this.bankLinkAf = bankLinkAf;
	}

	public String getBankLinkAfter() {
		return bankLinkAfter;
	}

	public void setBankLinkAfter(String bankLinkAfter) {
		this.bankLinkAfter = bankLinkAfter;
	}

	public Date getStartDateAfter() {
		return startDateAfter;
	}

	public void setStartDateAfter(Date startDateAfter) {
		this.startDateAfter = startDateAfter;
	}

	@JSONField(format = "yyyy-MM-dd")
	private Date startDateAfter;
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date endDateAfter;
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDateAfter;

    private Integer supplierContactId;
    private String contactName;
    private String mobilePhone;
    private String fixedPhone;
    private String faxPhone;
    private String emailAddress;

    public Integer getChangeContactsId() {
        return changeContactsId;
    }

    public void setChangeContactsId(Integer changeContactsId) {
        this.changeContactsId = changeContactsId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFaxPhone() {
        return faxPhone;
    }

    public void setFaxPhone(String faxPhone) {
        this.faxPhone = faxPhone;
    }

    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public String getMobilePhone() { return mobilePhone; }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getSupplierContactId() {
        return supplierContactId;
    }

    public void setSupplierContactId(Integer supplierContactId) {
        this.supplierContactId = supplierContactId;
    }

    private Integer changeContactsId;
	private Integer changeContactBfId;
	private Integer changeContactAfId;
	private Integer supplierContactIdAf;
	private String contactNameAf;
	private String mobilePhoneAf;
	private String emailAddressAf;
	private String fixedPhoneAf;
	private String faxPhoneAf;
	@JSONField(format = "yyyy-MM-dd")
	private Date failureDateAf;


	@JSONField(format = "yyyy-MM-dd")
	private Date failureDate;

	public Date getFailureDate() {
		return failureDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	public Integer getChangeContactBfId() {
		return changeContactBfId;
	}

	public void setChangeContactBfId(Integer changeContactBfId) {
		this.changeContactBfId = changeContactBfId;
	}

	public Integer getChangeContactAfId() {
		return changeContactAfId;
	}

	public void setChangeContactAfId(Integer changeContactAfId) {
		this.changeContactAfId = changeContactAfId;
	}

	public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getSupplierCategoryId() {
        return supplierCategoryId;
    }

    public void setSupplierCategoryId(Integer supplierCategoryId) {
        this.supplierCategoryId = supplierCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getChangeCategoryId() {
        return changeCategoryId;
    }

    public void setChangeCategoryId(Integer changeCategoryId) {
        this.changeCategoryId = changeCategoryId;
    }

    public String getBigCategoryCode() {
        return bigCategoryCode;
    }

    public void setBigCategoryCode(String bigCategoryCode) {
        this.bigCategoryCode = bigCategoryCode;
    }

	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

    public String getMiddleCategoryCode() {
        return middleCategoryCode;
    }

    public void setMiddleCategoryCode(String middleCategoryCode) {
        this.middleCategoryCode = middleCategoryCode;
    }

    public String getSmallCategoryCode() {
        return smallCategoryCode;
    }

    public void setSmallCategoryCode(String smallCategoryCode) {
        this.smallCategoryCode = smallCategoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setFullCategoryName(String fullCategoryName) {
        this.fullCategoryName = fullCategoryName;
    }

	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setBigCategoryName(String bigCategoryName) {
		this.bigCategoryName = bigCategoryName;
	}

    public String getMiddleCategoryName() {
        return middleCategoryName;
    }

    public void setMiddleCategoryName(String middleCategoryName) {
        this.middleCategoryName = middleCategoryName;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public Integer getChangeCertificateId() {
        return changeCertificateId;
    }

    public void setChangeCertificateId(Integer changeCertificateId) {
        this.changeCertificateId = changeCertificateId;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateDescription() {
        return certificateDescription;
    }

    public void setCertificateDescription(String certificateDescription) {
        this.certificateDescription = certificateDescription;
    }

    public Integer getChangeBankId() {
        return changeBankId;
    }

    public void setChangeBankId(Integer changeBankId) {
        this.changeBankId = changeBankId;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankCurrency() {
        return bankCurrency;
    }

    public void setBankCurrency(String bankCurrency) {
        this.bankCurrency = bankCurrency;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
	public Integer getChangeId() {
		return changeId;
	}
	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getChangeNumber() {
		return changeNumber;
	}
	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getChangeStatusStr() {
		return changeStatusStr;
	}
	public void setChangeStatusStr(String changeStatusStr) {
		this.changeStatusStr = changeStatusStr;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
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
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getChangeBaseId() {
		return changeBaseId;
	}
	public void setChangeBaseId(Integer changeBaseId) {
		this.changeBaseId = changeBaseId;
	}
	public String getSupplierNameAfter() {
		return supplierNameAfter;
	}
	public void setSupplierNameAfter(String supplierNameAfter) {
		this.supplierNameAfter = supplierNameAfter;
	}
	public String getSupplierShortName() {
		return supplierShortName;
	}
	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}
	public String getSupplierShortNameAfter() {
		return supplierShortNameAfter;
	}
	public void setSupplierShortNameAfter(String supplierShortNameAfter) {
		this.supplierShortNameAfter = supplierShortNameAfter;
	}
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getSupplierTypeAfter() {
		return supplierTypeAfter;
	}
	public void setSupplierTypeAfter(String supplierTypeAfter) {
		this.supplierTypeAfter = supplierTypeAfter;
	}
	public String getSupplierClassify() {
		return supplierClassify;
	}
	public void setSupplierClassify(String supplierClassify) {
		this.supplierClassify = supplierClassify;
	}
	public String getSupplierClassifyAfter() {
		return supplierClassifyAfter;
	}
	public void setSupplierClassifyAfter(String supplierClassifyAfter) {
		this.supplierClassifyAfter = supplierClassifyAfter;
	}
	public String getSupplierIndustry() {
		return supplierIndustry;
	}
	public void setSupplierIndustry(String supplierIndustry) {
		this.supplierIndustry = supplierIndustry;
	}
	public String getSupplierIndustryAfter() {
		return supplierIndustryAfter;
	}
	public void setSupplierIndustryAfter(String supplierIndustryAfter) {
		this.supplierIndustryAfter = supplierIndustryAfter;
	}
	public String getFinClassify() {
		return finClassify;
	}
	public void setFinClassify(String finClassify) {
		this.finClassify = finClassify;
	}
	public String getFinClassifyAfter() {
		return finClassifyAfter;
	}
	public void setFinClassifyAfter(String finClassifyAfter) {
		this.finClassifyAfter = finClassifyAfter;
	}
	public String getSettleAcctType() {
		return settleAcctType;
	}
	public void setSettleAcctType(String settleAcctType) {
		this.settleAcctType = settleAcctType;
	}
	public String getSettleAcctTypeAfter() {
		return settleAcctTypeAfter;
	}
	public void setSettleAcctTypeAfter(String settleAcctTypeAfter) {
		this.settleAcctTypeAfter = settleAcctTypeAfter;
	}
	public String getAcctCheckStaff() {
		return acctCheckStaff;
	}
	public void setAcctCheckStaff(String acctCheckStaff) {
		this.acctCheckStaff = acctCheckStaff;
	}
	public String getAcctCheckStaffAfter() {
		return acctCheckStaffAfter;
	}
	public void setAcctCheckStaffAfter(String acctCheckStaffAfter) {
		this.acctCheckStaffAfter = acctCheckStaffAfter;
	}
	public String getAcctCheckType() {
		return acctCheckType;
	}
	public void setAcctCheckType(String acctCheckType) {
		this.acctCheckType = acctCheckType;
	}
	public String getAcctCheckTypeAfter() {
		return acctCheckTypeAfter;
	}
	public void setAcctCheckTypeAfter(String acctCheckTypeAfter) {
		this.acctCheckTypeAfter = acctCheckTypeAfter;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public String getPaymentFlagAfter() {
		return paymentFlagAfter;
	}
	public void setPaymentFlagAfter(String paymentFlagAfter) {
		this.paymentFlagAfter = paymentFlagAfter;
	}
	public String getPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public String getPurchaseFlagAfter() {
		return purchaseFlagAfter;
	}
	public void setPurchaseFlagAfter(String purchaseFlagAfter) {
		this.purchaseFlagAfter = purchaseFlagAfter;
	}
	public String getHomeUrl() {
		return homeUrl;
	}
	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}
	public String getHomeUrlAfter() {
		return homeUrlAfter;
	}
	public void setHomeUrlAfter(String homeUrlAfter) {
		this.homeUrlAfter = homeUrlAfter;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyPhoneAfter() {
		return companyPhoneAfter;
	}
	public void setCompanyPhoneAfter(String companyPhoneAfter) {
		this.companyPhoneAfter = companyPhoneAfter;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	public String getCompanyFaxAfter() {
		return companyFaxAfter;
	}
	public void setCompanyFaxAfter(String companyFaxAfter) {
		this.companyFaxAfter = companyFaxAfter;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public Integer getStaffNumAfter() {
		return staffNumAfter;
	}
	public void setStaffNumAfter(Integer staffNumAfter) {
		this.staffNumAfter = staffNumAfter;
	}
	public String getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}
	public String getFloorAreaAfter() {
		return floorAreaAfter;
	}
	public void setFloorAreaAfter(String floorAreaAfter) {
		this.floorAreaAfter = floorAreaAfter;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getCompanyDescriptionAfter() {
		return companyDescriptionAfter;
	}
	public void setCompanyDescriptionAfter(String companyDescriptionAfter) {
		this.companyDescriptionAfter = companyDescriptionAfter;
	}
	public Integer getSupplierFileId() {
		return supplierFileId;
	}
	public void setSupplierFileId(Integer supplierFileId) {
		this.supplierFileId = supplierFileId;
	}
	public String getSupplierFilePath() {
		return supplierFilePath;
	}
	public void setSupplierFilePath(String supplierFilePath) {
		this.supplierFilePath = supplierFilePath;
	}
	public String getSupplierFileName() {
		return supplierFileName;
	}
	public void setSupplierFileName(String supplierFileName) {
		this.supplierFileName = supplierFileName;
	}
	public Integer getSupplierFileIdAfter() {
		return supplierFileIdAfter;
	}
	public void setSupplierFileIdAfter(Integer supplierFileIdAfter) {
		this.supplierFileIdAfter = supplierFileIdAfter;
	}
	public String getSupplierFilePathAfter() {
		return supplierFilePathAfter;
	}
	public void setSupplierFilePathAfter(String supplierFilePathAfter) {
		this.supplierFilePathAfter = supplierFilePathAfter;
	}
	public String getSupplierFileNameAfter() {
		return supplierFileNameAfter;
	}
	public void setSupplierFileNameAfter(String supplierFileNameAfter) {
		this.supplierFileNameAfter = supplierFileNameAfter;
	}
	public Integer getChanggeCredentialsId() {
		return changgeCredentialsId;
	}
	public void setChanggeCredentialsId(Integer changgeCredentialsId) {
		this.changgeCredentialsId = changgeCredentialsId;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getCurrencyCodeAfter() {
		return currencyCodeAfter;
	}
	public void setCurrencyCodeAfter(String currencyCodeAfter) {
		this.currencyCodeAfter = currencyCodeAfter;
	}
	public String getMuchInOne() {
		return muchInOne;
	}
	public void setMuchInOne(String muchInOne) {
		this.muchInOne = muchInOne;
	}
	public String getMuchInOneAfter() {
		return muchInOneAfter;
	}
	public void setMuchInOneAfter(String muchInOneAfter) {
		this.muchInOneAfter = muchInOneAfter;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getLicenseNumAfter() {
		return licenseNumAfter;
	}
	public void setLicenseNumAfter(String licenseNumAfter) {
		this.licenseNumAfter = licenseNumAfter;
	}
	public Date getLicenseIndate() {
		return licenseIndate;
	}
	public void setLicenseIndate(Date licenseIndate) {
		this.licenseIndate = licenseIndate;
	}
	public Date getLicenseIndateAfter() {
		return licenseIndateAfter;
	}
	public void setLicenseIndateAfter(Date licenseIndateAfter) {
		this.licenseIndateAfter = licenseIndateAfter;
	}
	public String getLongLicenseIndate() {
		return longLicenseIndate;
	}
	public void setLongLicenseIndate(String longLicenseIndate) {
		this.longLicenseIndate = longLicenseIndate;
	}
	public String getLongLicenseIndateAfter() {
		return longLicenseIndateAfter;
	}
	public void setLongLicenseIndateAfter(String longLicenseIndateAfter) {
		this.longLicenseIndateAfter = longLicenseIndateAfter;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getTaxCodeAfter() {
		return taxCodeAfter;
	}
	public void setTaxCodeAfter(String taxCodeAfter) {
		this.taxCodeAfter = taxCodeAfter;
	}
	public String getTissueCode() {
		return tissueCode;
	}
	public void setTissueCode(String tissueCode) {
		this.tissueCode = tissueCode;
	}
	public String getTissueCodeAfter() {
		return tissueCodeAfter;
	}
	public void setTissueCodeAfter(String tissueCodeAfter) {
		this.tissueCodeAfter = tissueCodeAfter;
	}
	public Date getTissueIndate() {
		return tissueIndate;
	}
	public void setTissueIndate(Date tissueIndate) {
		this.tissueIndate = tissueIndate;
	}
	public Date getTissueIndateAfter() {
		return tissueIndateAfter;
	}
	public void setTissueIndateAfter(Date tissueIndateAfter) {
		this.tissueIndateAfter = tissueIndateAfter;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getBusinessScopeAfter() {
		return businessScopeAfter;
	}
	public void setBusinessScopeAfter(String businessScopeAfter) {
		this.businessScopeAfter = businessScopeAfter;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getRepresentativeNameAfter() {
		return representativeNameAfter;
	}
	public void setRepresentativeNameAfter(String representativeNameAfter) {
		this.representativeNameAfter = representativeNameAfter;
	}
	public String getCorporateIdentity() {
		return corporateIdentity;
	}
	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}
	public String getCorporateIdentityAfter() {
		return corporateIdentityAfter;
	}
	public void setCorporateIdentityAfter(String corporateIdentityAfter) {
		this.corporateIdentityAfter = corporateIdentityAfter;
	}
	public String getBankPermitNumber() {
		return bankPermitNumber;
	}
	public void setBankPermitNumber(String bankPermitNumber) {
		this.bankPermitNumber = bankPermitNumber;
	}
	public String getBankPermitNumberAfter() {
		return bankPermitNumberAfter;
	}
	public void setBankPermitNumberAfter(String bankPermitNumberAfter) {
		this.bankPermitNumberAfter = bankPermitNumberAfter;
	}
	public Integer getLicenseFileId() {
		return licenseFileId;
	}
	public void setLicenseFileId(Integer licenseFileId) {
		this.licenseFileId = licenseFileId;
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
	public Integer getLicenseFileIdAfter() {
		return licenseFileIdAfter;
	}
	public void setLicenseFileIdAfter(Integer licenseFileIdAfter) {
		this.licenseFileIdAfter = licenseFileIdAfter;
	}
	public String getLicenseFilePathAfter() {
		return licenseFilePathAfter;
	}
	public void setLicenseFilePathAfter(String licenseFilePathAfter) {
		this.licenseFilePathAfter = licenseFilePathAfter;
	}
	public String getLicenseFileNameAfter() {
		return licenseFileNameAfter;
	}
	public void setLicenseFileNameAfter(String licenseFileNameAfter) {
		this.licenseFileNameAfter = licenseFileNameAfter;
	}
	public Integer getTissueFileId() {
		return tissueFileId;
	}
	public void setTissueFileId(Integer tissueFileId) {
		this.tissueFileId = tissueFileId;
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
	public Integer getTissueFileIdAfter() {
		return tissueFileIdAfter;
	}
	public void setTissueFileIdAfter(Integer tissueFileIdAfter) {
		this.tissueFileIdAfter = tissueFileIdAfter;
	}
	public String getTissueFilePathAfter() {
		return tissueFilePathAfter;
	}
	public void setTissueFilePathAfter(String tissueFilePathAfter) {
		this.tissueFilePathAfter = tissueFilePathAfter;
	}
	public String getTissueFileNameAfter() {
		return tissueFileNameAfter;
	}
	public void setTissueFileNameAfter(String tissueFileNameAfter) {
		this.tissueFileNameAfter = tissueFileNameAfter;
	}
	public Integer getBankPermitFileId() {
		return bankPermitFileId;
	}
	public void setBankPermitFileId(Integer bankPermitFileId) {
		this.bankPermitFileId = bankPermitFileId;
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
	public Integer getBankPermitFileIdAfter() {
		return bankPermitFileIdAfter;
	}
	public void setBankPermitFileIdAfter(Integer bankPermitFileIdAfter) {
		this.bankPermitFileIdAfter = bankPermitFileIdAfter;
	}
	public String getBankPermitFilePathAfter() {
		return bankPermitFilePathAfter;
	}
	public void setBankPermitFilePathAfter(String bankPermitFilePathAfter) {
		this.bankPermitFilePathAfter = bankPermitFilePathAfter;
	}
	public String getBankPermitFileNameAfter() {
		return bankPermitFileNameAfter;
	}
	public void setBankPermitFileNameAfter(String bankPermitFileNameAfter) {
		this.bankPermitFileNameAfter = bankPermitFileNameAfter;
	}
	public Integer getTaxFileId() {
		return taxFileId;
	}
	public void setTaxFileId(Integer taxFileId) {
		this.taxFileId = taxFileId;
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
	public Integer getTaxFileIdAfter() {
		return taxFileIdAfter;
	}
	public void setTaxFileIdAfter(Integer taxFileIdAfter) {
		this.taxFileIdAfter = taxFileIdAfter;
	}
	public String getTaxFilePathAfter() {
		return taxFilePathAfter;
	}
	public void setTaxFilePathAfter(String taxFilePathAfter) {
		this.taxFilePathAfter = taxFilePathAfter;
	}
	public String getTaxFileNameAfter() {
		return taxFileNameAfter;
	}
	public void setTaxFileNameAfter(String taxFileNameAfter) {
		this.taxFileNameAfter = taxFileNameAfter;
	}
	public Integer getOthersFileId() {
		return othersFileId;
	}
	public void setOthersFileId(Integer othersFileId) {
		this.othersFileId = othersFileId;
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
	public Integer getOthersFileIdAfter() {
		return othersFileIdAfter;
	}
	public void setOthersFileIdAfter(Integer othersFileIdAfter) {
		this.othersFileIdAfter = othersFileIdAfter;
	}
	public String getOthersFilePathAfter() {
		return othersFilePathAfter;
	}
	public void setOthersFilePathAfter(String othersFilePathAfter) {
		this.othersFilePathAfter = othersFilePathAfter;
	}
	public String getOthersFileNameAfter() {
		return othersFileNameAfter;
	}
	public void setOthersFileNameAfter(String othersFileNameAfter) {
		this.othersFileNameAfter = othersFileNameAfter;
	}

	public String getLicenseNumAf() {
		return licenseNumAf;
	}

	public void setLicenseNumAf(String licenseNumAf) {
		this.licenseNumAf = licenseNumAf;
	}

	private Integer approvalUserId;
	private String approvalOpinion;

	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getChangeCertificateBfId() {
		return changeCertificateBfId;
	}

	public void setChangeCertificateBfId(Integer changeCertificateBfId) {
		this.changeCertificateBfId = changeCertificateBfId;
	}

	public Integer getChangeCertificateAfId() {
		return changeCertificateAfId;
	}

	public void setChangeCertificateAfId(Integer changeCertificateAfId) {
		this.changeCertificateAfId = changeCertificateAfId;
	}

	public Integer getChangeBankBfId() {
		return changeBankBfId;
	}

	public void setChangeBankBfId(Integer changeBankBfId) {
		this.changeBankBfId = changeBankBfId;
	}

	public Integer getChangeBankAfId() {
		return changeBankAfId;
	}

	public void setChangeBankAfId(Integer changeBankAfId) {
		this.changeBankAfId = changeBankAfId;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getIbanCode() {
		return ibanCode;
	}

	public void setIbanCode(String ibanCode) {
		this.ibanCode = ibanCode;
	}

	private Integer changeAddressAfId;
	private Integer changeAddressBfId;
    private Integer supplierAddressId;
    private String addressName;
    private String country;
    private String countryName;
    private String province;
    private String city;
    private String county;
    private String detailAddress;

	private Integer supplierAddressIdAf;
	private String addressNameAf;
	private String countryAf;
	private String countryNameAf;
	private String provinceAf;
	private String cityAf;
	private String countyAf;
	private String detailAddressAf;

	@JSONField(format = "yyyy-MM-dd")
    private  Date establishmentDateAfter;
	@JSONField(format = "yyyy-MM-dd")
	private  Date establishmentDate;

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public Date getEstablishmentDateAfter() {
		return establishmentDateAfter;
	}

	public void setEstablishmentDateAfter(Date establishmentDateAfter) {
		this.establishmentDateAfter = establishmentDateAfter;
	}

	public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getChangeAddressAfId() {
        return changeAddressAfId;
    }

    public void setChangeAddressAfId(Integer changeAddressAfId) {
        this.changeAddressAfId = changeAddressAfId;
    }

    public Integer getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(Integer supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getCertificateIdAf() {
		return certificateIdAf;
	}

	public void setCertificateIdAf(Integer certificateIdAf) {
		this.certificateIdAf = certificateIdAf;
	}

	public String getCertificateNameAf() {
		return certificateNameAf;
	}

	public void setCertificateNameAf(String certificateNameAf) {
		this.certificateNameAf = certificateNameAf;
	}

	public String getCertificateNumberAf() {
		return certificateNumberAf;
	}

	public void setCertificateNumberAf(String certificateNumberAf) {
		this.certificateNumberAf = certificateNumberAf;
	}

	public String getCertificateDescriptionAf() {
		return certificateDescriptionAf;
	}

	public void setCertificateDescriptionAf(String certificateDescriptionAf) {
		this.certificateDescriptionAf = certificateDescriptionAf;
	}

	public Date getStartDateAf() {
		return startDateAf;
	}

	public void setStartDateAf(Date startDateAf) {
		this.startDateAf = startDateAf;
	}

	public String getBankAccountNumberAf() {
		return bankAccountNumberAf;
	}

	public void setBankAccountNumberAf(String bankAccountNumberAf) {
		this.bankAccountNumberAf = bankAccountNumberAf;
	}

	public String getBankUserNameAf() {
		return bankUserNameAf;
	}

	public void setBankUserNameAf(String bankUserNameAf) {
		this.bankUserNameAf = bankUserNameAf;
	}

	public Integer getBankIdAf() {
		return bankIdAf;
	}

	public void setBankIdAf(Integer bankIdAf) {
		this.bankIdAf = bankIdAf;
	}

	public String getBankNameAf() {
		return bankNameAf;
	}

	public void setBankNameAf(String bankNameAf) {
		this.bankNameAf = bankNameAf;
	}

	public String getBankNameStrAf() {
		return bankNameStrAf;
	}

	public void setBankNameStrAf(String bankNameStrAf) {
		this.bankNameStrAf = bankNameStrAf;
	}

	public Integer getBranchIdAf() {
		return branchIdAf;
	}

	public void setBranchIdAf(Integer branchIdAf) {
		this.branchIdAf = branchIdAf;
	}

	public String getBranchNameAf() {
		return branchNameAf;
	}

	public void setBranchNameAf(String branchNameAf) {
		this.branchNameAf = branchNameAf;
	}

	public String getBranchNumberAf() {
		return branchNumberAf;
	}

	public void setBranchNumberAf(String branchNumberAf) {
		this.branchNumberAf = branchNumberAf;
	}

	public String getBankCurrencyAf() {
		return bankCurrencyAf;
	}

	public void setBankCurrencyAf(String bankCurrencyAf) {
		this.bankCurrencyAf = bankCurrencyAf;
	}

	public Date getEndDateAf() {
		return endDateAf;
	}

	public void setEndDateAf(Date endDateAf) {
		this.endDateAf = endDateAf;
	}

	public Date getInvalidDateAf() {
		return invalidDateAf;
	}

	public void setInvalidDateAf(Date invalidDateAf) {
		this.invalidDateAf = invalidDateAf;
	}

	public String getSwiftCodeAf() {
		return swiftCodeAf;
	}

	public void setSwiftCodeAf(String swiftCodeAf) {
		this.swiftCodeAf = swiftCodeAf;
	}

	public String getIbanCodeAf() {
		return ibanCodeAf;
	}

	public void setIbanCodeAf(String ibanCodeAf) {
		this.ibanCodeAf = ibanCodeAf;
	}

	public Integer getBankAccountIdAf() {
		return bankAccountIdAf;
	}

	public void setBankAccountIdAf(Integer bankAccountIdAf) {
		this.bankAccountIdAf = bankAccountIdAf;
	}

	public Integer getSupplierContactIdAf() {
		return supplierContactIdAf;
	}

	public void setSupplierContactIdAf(Integer supplierContactIdAf) {
		this.supplierContactIdAf = supplierContactIdAf;
	}

	public String getContactNameAf() {
		return contactNameAf;
	}

	public void setContactNameAf(String contactNameAf) {
		this.contactNameAf = contactNameAf;
	}

	public String getMobilePhoneAf() {
		return mobilePhoneAf;
	}

	public void setMobilePhoneAf(String mobilePhoneAf) {
		this.mobilePhoneAf = mobilePhoneAf;
	}

	public String getEmailAddressAf() {
		return emailAddressAf;
	}

	public void setEmailAddressAf(String emailAddressAf) {
		this.emailAddressAf = emailAddressAf;
	}

	public String getFixedPhoneAf() {
		return fixedPhoneAf;
	}

	public void setFixedPhoneAf(String fixedPhoneAf) {
		this.fixedPhoneAf = fixedPhoneAf;
	}

	public String getFaxPhoneAf() {
		return faxPhoneAf;
	}

	public void setFaxPhoneAf(String faxPhoneAf) {
		this.faxPhoneAf = faxPhoneAf;
	}

	public Date getFailureDateAf() {
		return failureDateAf;
	}

	public void setFailureDateAf(Date failureDateAf) {
		this.failureDateAf = failureDateAf;
	}

	public Integer getSupplierAddressIdAf() {
		return supplierAddressIdAf;
	}

	public void setSupplierAddressIdAf(Integer supplierAddressIdAf) {
		this.supplierAddressIdAf = supplierAddressIdAf;
	}

	public String getAddressNameAf() {
		return addressNameAf;
	}

	public void setAddressNameAf(String addressNameAf) {
		this.addressNameAf = addressNameAf;
	}

	public String getCountryAf() {
		return countryAf;
	}

	public void setCountryAf(String countryAf) {
		this.countryAf = countryAf;
	}

	public String getCountryNameAf() {
		return countryNameAf;
	}

	public void setCountryNameAf(String countryNameAf) {
		this.countryNameAf = countryNameAf;
	}

	public String getProvinceAf() {
		return provinceAf;
	}

	public void setProvinceAf(String provinceAf) {
		this.provinceAf = provinceAf;
	}

	public String getCityAf() {
		return cityAf;
	}

	public void setCityAf(String cityAf) {
		this.cityAf = cityAf;
	}

	public String getCountyAf() {
		return countyAf;
	}

	public void setCountyAf(String countyAf) {
		this.countyAf = countyAf;
	}

	public String getDetailAddressAf() {
		return detailAddressAf;
	}

	public void setDetailAddressAf(String detailAddressAf) {
		this.detailAddressAf = detailAddressAf;
	}

	public Integer getChangeAddressBfId() {
		return changeAddressBfId;
	}

	public void setChangeAddressBfId(Integer changeAddressBfId) {
		this.changeAddressBfId = changeAddressBfId;
	}

	private  String approvalUserName;
	@JSONField(format = "yyyy-MM-dd")
	private  Date approvalDate;

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

}
