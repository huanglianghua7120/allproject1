package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmPosSupplierInfoEntity_HI_RO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String GET_SUPPLIER_INFO_NAME = "SELECT pos.supplier_id supplierId, pos.supplier_name supplierName, pos.supplier_number supplierNumber, pos.supplier_ebs_number supplierEbsNumber FROM srm_pos_supplier_info pos WHERE 1=1 ";

	public static String QUERY_SUPPLIER_UPDATE_LOV_SQL = "SELECT\n" +
			"\tspsi.supplier_id supplierId,\n" +
			"\tspsi.supplier_name supplierName,\n" +
			"\tspsi.supplier_number supplierNumber,\n" +
			"\tspsi.supplier_ebs_number supplierEbsNumber,\n" +
			"\tspsi.supplier_status supplierStatus,\n" +
			"\tspsi.acct_check_type acctCheckType,\n" +
			"\tspsi.able_check_order_flag ableCheckOrderFlag,\n" +
			"\tslv.meaning acctCheckTypeStr,\n" +
			"\tslv1.meaning supplierStatusStr\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_info spsi \n" +
			"LEFT JOIN saaf_lookup_values slv1 \n" +
			"ON slv1.lookup_type = 'POS_SUPPLIER_STATUS' \n" +
			"and spsi.supplier_status = slv1.lookup_code\n" +
			"LEFT JOIN saaf_lookup_values slv \n" +
			"ON slv.lookup_type='POS_ACCT_CHECK_TYPE' \n" +
			"and spsi.acct_check_type = slv.lookup_code\n" +
			"WHERE\n" +
			"\t1=1";

	//供应商列表
	public static String QUERY_SUPPLIER_LIST =
					"SELECT\n" +
					"  psi.supplier_id AS supplierId,\n" +
					"  psi.supplier_number AS supplierNumber,\n" +
					"  psi.supplier_name AS supplierName,\n" +
					"  psi.supplier_short_name AS supplierShortName,\n" +
					"  psi.supplier_type AS supplierType,\n" +
					"  psi.supplier_classify AS supplierClassify,\n" +
					"  psi.supplier_industry AS supplierIndustry,\n" +
					"  psi.supplier_status AS supplierStatus,\n" +
					"  psi.approval_user_id AS approvalUserId,\n" +
					"  psi.approval_date AS approvalDate,\n" +
					"  psi.creation_date AS creationDate,\n" +
					"  slv1.meaning AS typeStr,\n" +
					"  slv2.meaning AS statusStr,\n" +
					"  slv3.meaning AS industryStr,\n" +
					"  emp.employee_name AS approvalEmployeeName,\n" +
					"  psc.license_num AS licenseNum\n" +
					"FROM\n" +
					"  srm_pos_supplier_info psi\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = psi.supplier_type\n" +
					"    AND slv1.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = psi.supplier_status\n" +
					"    AND slv2.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = psi.supplier_industry\n" +
					"    AND slv3.lookup_type = 'POS_SUPPLIER_INDUSTRY'\n" +
					"  LEFT JOIN saaf_users su\n" +
					"    ON su.user_id = psi.approval_user_id\n" +
					"  LEFT JOIN saaf_employees emp\n" +
					"    ON emp.employee_id = su.employee_id,\n" +
					"  srm_pos_supplier_credentials psc\n" +
					"WHERE psi.supplier_id = psc.supplier_id\n";

	//供应商列表分页计数
	public static String COUNT_SUPPLIER_LIST =
					"SELECT\n" +
					"  COUNT(1)\n" +
					"FROM\n" +
					"  srm_pos_supplier_info psi\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = psi.supplier_type\n" +
					"    AND slv1.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = psi.supplier_status\n" +
					"    AND slv2.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = psi.supplier_industry\n" +
					"    AND slv3.lookup_type = 'POS_SUPPLIER_INDUSTRY'\n" +
					"  LEFT JOIN saaf_users su\n" +
					"    ON su.user_id = psi.approval_user_id\n" +
					"  LEFT JOIN saaf_employees emp\n" +
					"    ON emp.employee_id = su.employee_id,\n" +
					"  srm_pos_supplier_credentials psc\n" +
					"WHERE psi.supplier_id = psc.supplier_id";

	//供应商基础信息查询
	public static String QUERY_SUPPLIER_INFO =
					"SELECT psi.supplier_id           AS supplierId\n" +
					"      ,psi.supplier_number       AS supplierNumber\n" +
					"      ,psi.supplier_name         AS supplierName\n" +
					"      ,psi.supplier_short_name   AS supplierShortName\n" +
					"      ,psi.supplier_type         AS supplierType\n" +
					"      ,psi.supplier_classify     AS supplierClassify\n" +
					"      ,psi.supplier_industry     AS supplierIndustry\n" +
					"      ,psi.supplier_status       AS supplierStatus\n" +
					"      ,psi.home_url              AS homeUrl\n" +
					"      ,psi.company_phone         AS companyPhone\n" +
					"      ,psi.company_fax           AS companyFax\n" +
					"      ,psi.related_supplier_id   AS relatedSupplierId\n" +
					"      ,psi.parent_supplier_id    AS parentSupplierId\n" +
					"      ,psi.staff_num             AS staffNum\n" +
					"      ,psi.floor_area            AS floorArea\n" +
					"      ,psi.company_description   AS companyDescription\n" +
					"      ,psi.purchase_flag         AS purchaseFlag\n" +
					"      ,psi.payment_flag          AS paymentFlag\n" +
					"      ,psi.fin_classify          AS finClassify\n" +
					"      ,psi.settle_acct_type      AS settleAcctType\n" +
					"      ,psi.acct_check_staff      AS acctCheckStaff\n" +
					"      ,psi.acct_check_type       AS acctCheckType\n" +
					"      ,psi.supplier_file_id      AS supplierFileId\n" +
					"      ,psi.pos_tax               AS posTax\n" +
					"      ,psi.pos_acct_condition    AS posAcctCondition\n" +
					"      ,psi.able_check_order_flag AS ableCheckOrderFlag\n" +
					"      ,psi.able_edit_flag        AS ableEditFlag\n" +
					"      ,psi.address               AS address\n" +
					"      ,psi.srm_delivery          AS srmDelivery\n" +
					"      ,psi.logistics_supplier    AS logisticsSupplier\n" +
					"      ,psi.blacklist_flag        AS blacklistFlag\n" +
					"      ,psi.approval_user_id      AS approvalUserId\n" +
					"      ,psi.approval_date         AS approvalDate\n" +
					"      ,psi.approval_comments     AS approvalComments\n" +
					"      ,psi.source_code           AS sourceCode\n" +
					"      ,psi.source_id             AS sourceId\n" +
					"      ,psi.version_num           AS versionNum\n" +
					"      ,psi.creation_date         AS creationDate\n" +
					"      ,psi.created_by            AS createdBy\n" +
					"      ,psi.last_updated_by       AS lastUpdatedBy\n" +
					"      ,psi.last_update_date      AS lastUpdateDate\n" +
					"      ,psi.last_update_login     AS lastUpdateLogin\n" +
					"      ,psi.attribute_category    AS attributeCategory\n" +
					"      ,psi.attribute1            AS attribute1\n" +
					"      ,psi.attribute2            AS attribute2\n" +
					"      ,psi.attribute3            AS attribute3\n" +
					"      ,psi.attribute4            AS attribute4\n" +
					"      ,psi.attribute5            AS attribute5\n" +
					"      ,psi.attribute6            AS attribute6\n" +
					"      ,psi.attribute7            AS attribute7\n" +
					"      ,psi.attribute8            AS attribute8\n" +
					"      ,psi.attribute9            AS attribute9\n" +
					"      ,psi.attribute10           AS attribute10\n" +
					"      ,psi.grade_line_id         AS gradeLineId\n" +
					"      ,slv.meaning               AS statusStr\n" +
					"      ,slv1.meaning              AS typeStr\n" +
					"      ,brf.access_path           AS supplierFilePath\n" +
					"      ,brf.file_name             AS supplierFileName\n" +
					"      ,brf2.access_path          AS bankPermitFilePath\n" +
					"      ,brf2.file_name            AS bankPermitFileName\n" +
					"      ,spsc.license_num          AS licenseNum\n" +
					"      ,spsc.tissue_code          AS tissueCode\n" +
					"      ,spsc.tax_code             AS taxCode\n" +
					"      ,se.employee_name          AS createByName\n" +
					"      ,psi.company_Registered_Address          AS companyRegisteredAddress\n" +
					"      ,psi.registered_Capital                  AS registeredCapital\n" +
					"      ,psi.independent_Legal_Person_Flag       AS independentLegalPersonFlag\n" +
					"      ,psi.value_Added_Tax_Invoice_Flag        AS valueAddedTaxInvoiceFlag\n" +
					"      ,psi.value_Added_Tax_Invoice_Desc        AS valueAddedTaxInvoiceDesc\n" +
					"      ,psi.associated_Company         		    AS associatedCompany\n" +
					"      ,psi.region    		         		    AS region\n" +
					"      ,psi.SUPPLIER_EBS_NUMBER    		        AS supplierEbsNumber\n" +
					"FROM   srm_pos_supplier_info psi\n" +
					"LEFT   JOIN saaf_base_result_file brf\n" +
					"ON     brf.file_id = psi.supplier_file_id\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     su.user_id = psi.created_by\n" +
					"LEFT   JOIN saaf_employees se\n" +
					"ON     se.employee_id = su.employee_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = psi.supplier_status\n" +
					"AND    slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = psi.supplier_type\n" +
					"AND    slv1.lookup_type = 'POS_SUPPLIER_TYPE',\n" +
					" srm_pos_supplier_credentials spsc\n" +
					"LEFT   JOIN saaf_base_result_file brf2\n" +
					"ON     brf2.file_id = spsc.bank_permit_file_id\n" +
					"WHERE  psi.supplier_id = spsc.supplier_id\n";

	//查询供应商列表
	public static String QUERY_SUPPLIER_INFO_LIST =
					"SELECT\n" +
					"  t.supplier_id AS supplierId,\n" +
					"  t.supplier_number AS supplierNumber,\n" +
					"  t.supplier_name AS supplierName,\n" +
					"  t.supplier_short_name AS supplierShortName,\n" +
					"  t.supplier_type AS supplierType,\n" +
					"  t.supplier_classify AS supplierClassify,\n" +
					"  t.supplier_industry AS supplierIndustry,\n" +
					"  t.supplier_status AS supplierStatus,\n" +
					"  t.home_url AS homeUrl,\n" +
					"  t.company_phone AS companyPhone,\n" +
					"  t.company_fax AS companyFax,\n" +
					"  t.related_supplier_id AS relatedSupplierId,\n" +
					"  t.parent_supplier_id AS parentSupplierId,\n" +
					"  t.staff_num AS staffNum,\n" +
					"  t.floor_area AS floorArea,\n" +
					"  t.company_description AS companyDescription,\n" +
					"  t.purchase_flag AS purchaseFlag,\n" +
					"  t.payment_flag AS paymentFlag,\n" +
					"  t.fin_classify AS finClassify,\n" +
					"  t.settle_acct_type AS settleAcctType,\n" +
					"  t.acct_check_staff AS acctCheckStaff,\n" +
					"  t.acct_check_type AS acctCheckType,\n" +
					"  t.pass_u9_flag AS passU9Flag,\n" +
					"  t.supplier_file_id AS supplierFileId,\n" +
					"  t.pos_tax AS posTax,\n" +
					"  t.blacklist_flag AS blacklistFlag,\n" +
					"  t.pos_acct_condition AS posAcctCondition,\n" +
					"  t.able_check_order_flag AS ableCheckOrderFlag,\n" +
					"  t.able_edit_flag AS ableEditFlag,\n" +
					"  t.address AS address,\n" +
					"  t.srm_delivery AS srmDelivery,\n" +
					"  t.logistics_supplier AS logisticsSupplier,\n" +
					"  t.source_code AS sourceCode,\n" +
					"  t.approval_user_id AS approvalUserId,\n" +
					"  t.approval_date AS approvalDate,\n" +
					"  t.approval_comments AS approvalComments,\n" +
					"  t.version_num AS versionNum,\n" +
					"  t.creation_date AS creationDate,\n" +
					"  t.created_by AS createdBy,\n" +
					"  t.last_updated_by AS lastUpdatedBy,\n" +
					"  t.last_update_date AS lastUpdateDate,\n" +
					"  t.last_update_login AS lastUpdateLogin,\n" +
					"  t.attribute_category AS attributeCategory,\n" +
					"  t.attribute1 AS attribute1,\n" +
					"  t.attribute2 AS attribute2,\n" +
					"  t.attribute3 AS attribute3,\n" +
					"  t.attribute4 AS attribute4,\n" +
					"  t.attribute5 AS attribute5,\n" +
					"  t.attribute6 AS attribute6,\n" +
					"  t.attribute7 AS attribute7,\n" +
					"  t.attribute8 AS attribute8,\n" +
					"  t.attribute9 AS attribute9,\n" +
					"  t.attribute10 AS attribute10,\n" +
					"  t.grade_line_id AS gradeLineId,\n" +
					"  slv.meaning AS statusStr,\n" +
					"  slv1.meaning AS typeStr,\n" +
					"  slv2.meaning AS industryStr,\n" +
					"  slv3.meaning AS gradeStr,\n" +
					"  brf.access_Path AS supplierFilePath,\n" +
					"  brf.file_Name AS supplierFileName,\n" +
					"  spsc.license_num AS licenseNum,\n" +
					"  spsc.tissue_code AS tissueCode,\n" +
					"  spsc.tax_code AS taxCode,\n" +
					"  spsc.credentials_id AS credentialsId,\n" +
					"  spsc.establishment_date AS establishmentDate,\n" +
					"  spsc.license_indate AS licenseIndate,\n" +
					"  spsc.long_license_indate AS longLicenseIndate,\n" +
					"  spsc.tissue_indate AS tissueIndate,\n" +
					"  spsc.bank_permit_number AS bankPermitNumber,\n" +
					"  spsc.much_in_one AS muchInOne,\n" +
					"  spsc.business_scope AS businessScope,\n" +
					"  spsc.corporate_identity AS corporateIdentity,\n" +
					"  spsc.representative_name AS representativeName,\n" +
					"  spsc.license_file_id AS licenseFileId,\n" +
					"  sbrf1.access_Path AS licenseFilePath,\n" +
					"  sbrf1.file_Name AS licenseFileName,\n" +
					"  spsc.tissue_file_id AS tissueFileId,\n" +
					"  sbrf2.access_Path AS tissueFilePath,\n" +
					"  sbrf2.file_Name AS tissueFileName,\n" +
					"  spsc.tax_file_id AS taxFileId,\n" +
					"  sbrf3.access_Path AS taxFilePath,\n" +
					"  sbrf3.file_Name AS taxFileName,\n" +
					"  spsc.bank_permit_file_id AS bankPermitFileId,\n" +
					"  sbrf4.access_Path AS bankPermitFilePath,\n" +
					"  sbrf4.file_Name AS bankPermitFileName,\n" +
					"  spsc.others_file_id AS othersFileId,\n" +
					"  sbrf5.access_Path AS othersFilePath,\n" +
					"  su1.user_full_name AS creater,\n" +
					"  se.employee_name AS employeeName,\n" +
					"  sbrf5.file_Name AS othersFileName,\n" +
					"  spsgl.adjust_grade AS supplierGrade,\n" +
					"  su2.user_name AS userName\n" +
					"  ,t.company_Registered_Address          AS companyRegisteredAddress\n" +
					"  ,t.registered_Capital                  AS registeredCapital\n" +
					"  ,t.independent_Legal_Person_Flag       AS independentLegalPersonFlag\n" +
					"  ,decode(t.independent_Legal_Person_Flag,'Y','是','N','否','') AS independentLegalPersonFlagStr\n" +
					"  ,t.value_Added_Tax_Invoice_Flag        AS valueAddedTaxInvoiceFlag\n" +
					"  ,decode(t.value_Added_Tax_Invoice_Flag,'Y','是','N','否','') AS valueAddedTaxInvoiceFlagStr\n" +
					"  ,t.value_Added_Tax_Invoice_Desc        AS valueAddedTaxInvoiceDesc\n" +
					"  ,t.associated_Company         		  AS associatedCompany\n" +
					"  ,t.region    		         		  AS region\n" +
					"  ,t.supplier_ebs_number    		      AS supplierEbsNumber\n" +
                    "  ,t.request_id    		         	  AS requestId\n" +
					"FROM\n" +
					"  srm_pos_supplier_info t\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"    AND t.supplier_status = slv.lookup_code\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
					"    AND t.supplier_type = slv1.lookup_code\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'POS_SUPPLIER_INDUSTRY'\n" +
					"    AND t.supplier_Industry = slv2.lookup_code\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'BASE_INST_REGION'\n" +
					"    AND t.region = slv4.lookup_code\n" +
					"  LEFT JOIN srm_pos_supplier_credentials spsc\n" +
					"    ON t.supplier_id = spsc.supplier_id\n" +
					"  LEFT JOIN saaf_base_result_file brf\n" +
					"    ON brf.file_id = t.supplier_file_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrf1\n" +
					"    ON spsc.license_file_id = sbrf1.file_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrf2\n" +
					"    ON spsc.tissue_file_id = sbrf2.file_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrf3\n" +
					"    ON spsc.tax_file_id = sbrf3.file_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrf4\n" +
					"    ON spsc.bank_permit_file_id = sbrf4.file_id\n" +
					"  LEFT JOIN saaf_employees se\n" +
					"    ON t.approval_user_id = se.employee_id\n" +
					"  LEFT JOIN saaf_base_result_file sbrf5\n" +
					"    ON spsc.others_file_id = sbrf5.file_id\n" +
					"  LEFT JOIN srm_pos_supplier_grade_lines spsgl\n" +
					"    ON t.grade_line_id = spsgl.grade_line_id\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_type = 'GRADE'\n" +
					"    AND spsgl.adjust_grade = slv3.lookup_code\n" +
					"  LEFT JOIN saaf_users su1\n" +
					"    ON su1.user_id = t.created_by\n" +
					"  LEFT JOIN saaf_users su2\n" +
					"    ON su2.supplier_id = t.supplier_id\n" +
					//解决内部创建供应商无法查看到登录账号的问题
					//"    AND su2.supplier_primary_flag = 'Y'\n" +
					"WHERE 1 = 1";

	public static String QUERY_SUPPLIER_INFO_LIST_FOR_UPDATE ="SELECT\n" +
			"\tt.supplier_id AS supplierId,\n" +
			"\tt.supplier_number AS supplierNumber,\n" +
			"\tt.supplier_name AS supplierName,\n" +
			"\tt.supplier_short_name AS supplierShortName,\n" +
			"\tt.supplier_type AS supplierType,\n" +
			"\tt.supplier_classify AS supplierClassify,\n" +
			"\tt.supplier_industry AS supplierIndustry,\n" +
			"\tt.supplier_status AS supplierStatus,\n" +
			"\tt.home_url AS homeUrl,\n" +
			"\tt.company_phone AS companyPhone,\n" +
			"\tt.company_fax AS companyFax,\n" +
			"\tt.related_supplier_id AS relatedSupplierId,\n" +
			"\tt.parent_supplier_id AS parentSupplierId,\n" +
			"\tt.staff_num AS staffNum,\n" +
			"\tt.floor_area AS floorArea,\n" +
			"\tt.company_description AS companyDescription,\n" +
			"\tt.purchase_flag AS purchaseFlag,\n" +
			"\tt.payment_flag AS paymentFlag,\n" +
			"\tt.fin_classify AS finClassify,\n" +
			"\tt.settle_acct_type AS settleAcctType,\n" +
			"\tt.acct_check_staff AS acctCheckStaff,\n" +
			"\tt.acct_check_type AS acctCheckType,\n" +
			"\tt.pass_u9_flag AS passU9Flag,\n" +
			"\tt.supplier_file_id AS supplierFileId,\n" +
			"\tt.pos_tax AS posTax,\n" +
			"\t(SELECT if(count(1)=0,'N','Y')\n" +
			"FROM\n" +
			"srm_pos_blacklists b \n" +
			"WHERE b.supplier_id = t.supplier_id  \n" +
			"AND b.blacklist_status = 'APPROVED' \n" +
			"AND (b.permanent_flag = 'Y' or ( b.relieve_date IS NULL\tOR DATE_FORMAT(b.relieve_date, '%Y-%m-%d') >= SYSDATE()))\n" +
			") AS blacklistFlag,\n" +
			"\tt.pos_acct_condition AS posAcctCondition,\n" +
			"\tt.able_check_order_flag AS ableCheckOrderFlag,\n" +
			"\tt.able_edit_flag AS ableEditFlag,\n" +
			"\tt.address AS address,\n" +
			"\tt.srm_delivery AS srmDelivery,\n" +
			"\tt.logistics_supplier AS logisticsSupplier,\n" +
			"\tt.source_code AS sourceCode,\n" +
			"\tt.approval_user_id AS approvalUserId,\n" +
			"\tt.approval_date AS approvalDate,\n" +
			"\tt.approval_comments AS approvalComments,\n" +
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
			"\tt.grade_line_id AS gradeLineId,\n" +
			"\tslv.meaning AS statusStr,\n" +
			"\tslv1.meaning AS typeStr,\n" +
			"\tslv2.meaning AS industryStr,\n" +
			"\tbrf.access_Path AS supplierFilePath,\n" +
			"\tbrf.file_Name AS supplierFileName,\n" +
			"\tspsc.license_num AS licenseNum,\n" +
			"\tspsc.tissue_code AS tissueCode,\n" +
			"\tspsc.tax_code AS taxCode,\n" +
			"\tspsc.credentials_id AS credentialsId,\n" +
			"\tspsc.establishment_date AS establishmentDate,\n" +
			"\tspsc.license_indate AS licenseIndate,\n" +
			"\tspsc.long_license_indate AS longLicenseIndate,\n" +
			"\tspsc.tissue_indate AS tissueIndate,\n" +
			"\tspsc.bank_permit_number AS bankPermitNumber,\n" +
			"\tspsc.much_in_one AS muchInOne,\n" +
			"\tspsc.business_scope AS businessScope,\n" +
			"\tspsc.corporate_identity AS corporateIdentity,\n" +
			"\tspsc.representative_name AS representativeName,\n" +
			"\tspsc.license_file_id AS licenseFileId,\n" +
			"\tsbrf1.access_Path AS licenseFilePath,\n" +
			"\tsbrf1.file_Name AS licenseFileName,\n" +
			"\tspsc.tissue_file_id AS tissueFileId,\n" +
			"\tsbrf2.access_Path AS tissueFilePath,\n" +
			"\tsbrf2.file_Name AS tissueFileName,\n" +
			"\tspsc.tax_file_id AS taxFileId,\n" +
			"\tsbrf3.access_Path AS taxFilePath,\n" +
			"\tsbrf3.file_Name AS taxFileName,\n" +
			"\tspsc.bank_permit_file_id AS bankPermitFileId,\n" +
			"\tsbrf4.access_Path AS bankPermitFilePath,\n" +
			"\tsbrf4.file_Name AS bankPermitFileName,\n" +
			"\tspsc.others_file_id AS othersFileId,\n" +
			"\tsbrf5.access_Path AS othersFilePath,\n" +
			"\tu.user_name AS userName,\n" +
			"\tse.employee_name AS employeeName,\n" +
			"\tsbrf5.file_Name AS othersFileName\n" +
			"\t,t.company_Registered_Address          AS companyRegisteredAddress\n" +
			"\t,t.registered_Capital                  AS registeredCapital\n" +
			"\t,t.independent_Legal_Person_Flag       AS independentLegalPersonFlag\n" +
			"\t,t.value_Added_Tax_Invoice_Flag        AS valueAddedTaxInvoiceFlag\n" +
			"\t,t.value_Added_Tax_Invoice_Desc        AS valueAddedTaxInvoiceDesc\n" +
			"\t,t.associated_Company         		  AS associatedCompany\n" +
			"\t,t.region         		 			  AS region\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_info t\n" +
			"LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
			"AND t.supplier_status = slv.lookup_code\n" +
			"LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
			"AND t.supplier_type = slv1.lookup_code\n" +
			"LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'POS_SUPPLIER_INDUSTRY'\n" +
			"AND t.supplier_Industry = slv2.lookup_code\n" +
			"LEFT JOIN srm_pos_supplier_credentials spsc ON t.supplier_id = spsc.supplier_id\n" +
			"LEFT JOIN saaf_base_result_file brf ON brf.file_id = t.supplier_file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf1 ON spsc.license_file_id = sbrf1.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf2 ON spsc.tissue_file_id = sbrf2.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf3 ON spsc.tax_file_id = sbrf3.file_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf4 ON spsc.bank_permit_file_id = sbrf4.file_id\n" +
			"LEFT JOIN saaf_users u ON t.supplier_id = u.supplier_id\n" +
			"LEFT JOIN saaf_employees se ON t.approval_user_id = se.employee_id\n" +
			"LEFT JOIN saaf_base_result_file sbrf5 ON spsc.others_file_id = sbrf5.file_id\n" +
			"WHERE\n" +
			"t.supplier_Status IN ('APPROVED','EFFETIVE','INTRODUCING','QUIT') ";

	//供应商页面查询关联的产品
	public static String QUERY_SUPCATEGORY = "SELECT slv.lookup_code lookupCode, slv.meaning meaning, slv.tag tag\n" +
			"  FROM saaf_lookup_values slv\n" +
			" WHERE 1 = 1\n" +
			"   AND slv.lookup_type = 'BASE_BIG_CATEGORY'\n" +
			"   and slv.lookup_code in\n" +
			"   (SELECT B.big_category_code FROM srm_pos_supplier_categories B)";

	//查询供应商的认证证书
	public static String QUERY_SUPPLIER_CERTIFICATE ="SELECT\r\n" +
			"  spsc.supplier_id supplierId,\r\n" +
			"  spsc.certificate_id certificateId,\r\n" +
			"  spsc.certificate_name certificateName,\r\n" +
			"  spsc.certificate_number certificateNumber,\r\n" +
			"  spsc.file_id fileId,\r\n" +
			"  sbrf.access_Path filePath,\r\n" +
			"  sbrf.file_Name fileName,\r\n" +
			"  spsc.start_date startDate,\r\n" +
			"  spsc.certificate_description certificateDescription\r\n" +
			"FROM\r\n" +
			"  srm_pos_supplier_certificate spsc\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsc.file_id = sbrf.file_id\r\n" +
			"WHERE\r\n" +
			"  1 = 1 ";

	public static String QUERY_COUNT ="SELECT\r\n" +
			"  count(1)\r\n" +
			"FROM\r\n" +
			"  srm_pos_supplier_info spsi\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf ON spsi.supplier_file_id = sbrf.file_id\r\n" +
			"LEFT JOIN saaf_users su ON spsi.supplier_id = su.supplier_id\r\n" +
			"LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'POS_SETTLE_ACCT_TYPE' and spsi.settle_acct_type=slv2.lookup_code,\r\n" +
//        "LEFT JOIN saaf_employees se ON spsi.acct_check_staff = se.employee_number,\r\n" +
			" srm_pos_supplier_credentials spsc\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf1 ON spsc.license_file_id = sbrf1.file_id\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf2 ON spsc.tissue_file_id = sbrf2.file_id\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf3 ON spsc.tax_file_id = sbrf3.file_id\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf4 ON spsc.bank_permit_file_id = sbrf4.file_id\r\n" +
			"LEFT JOIN saaf_base_result_file sbrf5 ON spsc.others_file_id = sbrf5.file_id,\r\n" +
			" saaf_lookup_values slv,\r\n" +
			" saaf_lookup_values slv1\r\n" +
			"WHERE\r\n" +
			"  spsi.supplier_id = spsc.supplier_id\r\n" +
			"AND slv.lookup_type = 'POS_SUPPLIER_INFO_STATUS'\r\n" +
			"AND spsi.supplier_status = slv.lookup_code\r\n" +
			"AND slv1.lookup_type = 'POS_SUPPLIER_TYPE'\r\n" +
			"AND spsi.supplier_type = slv1.lookup_code ";

	//查询引入进度
	public static String QUERY_INTRODUCING_PROGRESS =
					"SELECT t.supplierId\n" +
					"      ,t.supplierName\n" +
					"      ,t.qualificationNumber\n" +
					"      ,t.sceneTypeDesc\n" +
					"      ,t.addressName\n" +
					"      ,t.instName\n" +
					"      ,t.qualificationStatusDesc\n" +
					"      ,t.approveDateQ\n" +
					"      ,t.localeReviewNumber\n" +
					"      ,t.localeReviewStatusDESC\n" +
					"      ,t.approvedDateR\n" +
					"      ,t.trialsNumber\n" +
					"      ,t.trialsStatusDESC\n" +
					"      ,t.approvedDateS\n" +
					"      ,t.scheduleStatus\n" +
					"      ,(SELECT val.meaning\n" +
					"        FROM   saaf_lookup_values val\n" +
					"        WHERE  val.lookup_code = t.schedulestatus\n" +
					"        AND    val.lookup_type = 'POS_INTRODUCING_STATUS') scheduleDESC\n" +
					"      ,t.qualificationResultMean\n" +
					"      ,t.localeReviewResultMean\n" +
					"      ,t.trialsResultMean\n" +
					"      ,decode(t.schedulestatus, 'FINISHED', t.endtime, NULL) endTime\n" +
					"      ,(decode(t.schedulestatus,\n" +
					"               'FINISHED',\n" +
					"               round(to_number(t.endtime - t.approvedateq)),\n" +
					"               round(to_number(SYSDATE - t.approvedateq))) + 1) totalDays\n" +
					"FROM   (SELECT t.supplier_id supplierid\n" +
					"              ,t.supplier_name suppliername\n" +
					"              ,pqi.qualification_number qualificationnumber\n" +
					"              ,pqi.scene_type\n" +
					"              ,(SELECT val.meaning\n" +
					"                FROM   saaf_lookup_values val\n" +
					"                WHERE  pqi.scene_type = val.lookup_code\n" +
					"                AND    val.lookup_type = 'POS_SCENE_TYPE') scenetypedesc\n" +
					"              ,(SELECT suadd.address_name\n" +
					"                FROM   srm_pos_supplier_addresses suadd\n" +
					"                WHERE  suadd.supplier_address_id = si.supplier_address_id) addressname\n" +
					"              ,(SELECT fn.inst_name\n" +
					"                FROM   saaf_institution fn\n" +
					"                WHERE  si.org_id = fn.inst_id) instname\n" +
					"              ,pqi.org_id instid\n" +
					"              ,(SELECT val.meaning\n" +
					"                FROM   saaf_lookup_values val\n" +
					"                WHERE  pqi.qualification_status = val.lookup_code\n" +
					"                AND    val.lookup_type = 'POS_APPROVAL_STATUS') qualificationstatusdesc\n" +
					"              ,pqi.approve_date approvedateq\n" +
					"              ,plr.locale_review_number localereviewnumber\n" +
					"              ,(SELECT val.meaning\n" +
					"                FROM   saaf_lookup_values val\n" +
					"                WHERE  plr.locale_review_status = val.lookup_code\n" +
					"                AND    val.lookup_type = 'POS_APPROVAL_STATUS') localereviewstatusdesc\n" +
					"              ,plr.approved_date approveddater\n" +
					"              ,pst.trials_number trialsnumber\n" +
					"              ,(SELECT val.meaning\n" +
					"                FROM   saaf_lookup_values val\n" +
					"                WHERE  pst.trials_status = val.lookup_code\n" +
					"                AND    val.lookup_type = 'POS_APPROVAL_STATUS') trialsstatusdesc\n" +
					"              ,pst.approved_date approveddates\n" +
					"              ,(SELECT slv.meaning\n" +
					"                FROM   saaf_lookup_values slv\n" +
					"                WHERE  pqi.qualification_result = slv.lookup_code\n" +
					"                AND    slv.lookup_type = 'POS_EXAMINE_RESULT') qualificationresultmean\n" +
					"              ,(SELECT slv.meaning\n" +
					"                FROM   saaf_lookup_values slv\n" +
					"                WHERE  plr.locale_review_result = slv.lookup_code\n" +
					"                AND    slv.lookup_type = 'POS_EXAMINE_RESULT') localereviewresultmean\n" +
					"              ,(SELECT slv.meaning\n" +
					"                FROM   saaf_lookup_values slv\n" +
					"                WHERE  pst.trials_result = slv.lookup_code\n" +
					"                AND    slv.lookup_type = 'POS_EXAMINE_RESULT') trialsresultmean\n" +
					"              ,(CASE\n" +
					"                 WHEN ss.qualified_censor_flag = 'Y'\n" +
					"                      AND ss.locale_review_flag = 'Y'\n" +
					"                      AND ss.sample_trials_flag = 'Y'\n" +
					"                      AND (pqi.qualification_status = 'APPROVED' AND\n" +
					"                      plr.locale_review_status = 'APPROVED' AND\n" +
					"                      pst.trials_status = 'APPROVED' AND\n" +
					"                      pqi.qualification_result = 'PASS' AND\n" +
					"                      plr.locale_review_result = 'PASS' AND\n" +
					"                      pst.trials_result = 'PASS') THEN\n" +
					"                  'FINISHED'\n" +
					"                 WHEN ss.qualified_censor_flag = 'Y'\n" +
					"                      AND ss.sample_trials_flag = 'Y'\n" +
					"                      AND (ss.locale_review_flag != 'Y' OR\n" +
					"                      ss.locale_review_flag IS NULL OR\n" +
					"                      ss.locale_review_flag = '')\n" +
					"                      AND (pqi.qualification_status = 'APPROVED' AND\n" +
					"                      pst.trials_status = 'APPROVED' AND\n" +
					"                      pqi.qualification_result = 'PASS' AND\n" +
					"                      pst.trials_result = 'PASS') THEN\n" +
					"                  'FINISHED'\n" +
					"                 WHEN ss.qualified_censor_flag = 'Y'\n" +
					"                      AND ss.locale_review_flag = 'Y'\n" +
					"                      AND (ss.sample_trials_flag != 'Y' OR\n" +
					"                      ss.sample_trials_flag IS NULL OR\n" +
					"                      ss.sample_trials_flag = '')\n" +
					"                      AND (pqi.qualification_status = 'APPROVED' AND\n" +
					"                      plr.locale_review_status = 'APPROVED' AND\n" +
					"                      pqi.qualification_result = 'PASS' AND\n" +
					"                      plr.locale_review_result = 'PASS') THEN\n" +
					"                  'FINISHED'\n" +
					"                 WHEN ss.qualified_censor_flag = 'Y'\n" +
					"                      AND (ss.locale_review_flag != 'Y' OR\n" +
					"                      ss.locale_review_flag IS NULL OR\n" +
					"                      ss.locale_review_flag = '')\n" +
					"                      AND (ss.sample_trials_flag != 'Y' OR\n" +
					"                      ss.sample_trials_flag IS NULL OR\n" +
					"                      ss.sample_trials_flag = '')\n" +
					"                      AND (pqi.qualification_status = 'APPROVED' AND\n" +
					"                      pqi.qualification_result = 'PASS') THEN\n" +
					"                  'FINISHED'\n" +
					"                 ELSE\n" +
					"                  'PROCESSING'\n" +
					"               END) AS schedulestatus\n" +
					"              ,(SELECT greatest(decode(psm.qualified_censor_flag,\n" +
					"                                       'Y',\n" +
					"                                       nvl(pqi.approve_date,\n" +
					"                                           to_date('1970-01-01', 'yyyy-mm-dd')),\n" +
					"                                       to_date('1970-01-01', 'yyyy-mm-dd')),\n" +
					"                                decode(psm.locale_review_flag,\n" +
					"                                       'Y',\n" +
					"                                       nvl(plr.approved_date,\n" +
					"                                           to_date('1970-01-01', 'yyyy-mm-dd')),\n" +
					"                                       to_date('1970-01-01', 'yyyy-mm-dd')),\n" +
					"                                decode(psm.sample_trials_flag,\n" +
					"                                       'Y',\n" +
					"                                       nvl(pst.approved_date,\n" +
					"                                           to_date('1970-01-01', 'yyyy-mm-dd')),\n" +
					"                                       to_date('1970-01-01', 'yyyy-mm-dd')))\n" +
					"                FROM   srm_pos_scene_manage psm\n" +
					"                WHERE  pqi.scene_type = psm.scene_type) endtime\n" +
					"              ,ss.qualified_censor_flag aa\n" +
					"              ,ss.locale_review_flag bb\n" +
					"              ,ss.sample_trials_flag cc\n" +
					"        FROM   srm_pos_supplier_info      t\n" +
					"              ,srm_pos_qualification_info pqi\n" +
					"        LEFT   JOIN srm_pos_sample_trials pst\n" +
					"        ON     pst.trial_id =\n" +
					"               (SELECT MAX(t2.trial_id)\n" +
					"                FROM   srm_pos_sample_trials t2\n" +
					"                WHERE  t2.qualification_id = pqi.qualification_id)\n" +
					"        LEFT   JOIN srm_pos_scene_manage ss\n" +
					"        ON     pqi.scene_type = ss.scene_type\n" +
					"        LEFT   JOIN srm_pos_locale_reviews plr\n" +
					"        ON     plr.locale_review_id =\n" +
					"               (SELECT MAX(t1.locale_review_id)\n" +
					"                FROM   srm_pos_locale_reviews t1\n" +
					"                WHERE  t1.qualification_id = pqi.qualification_id)\n" +
					"        AND    pqi.supplier_id = plr.supplier_id,\n" +
					"         srm_pos_qualification_sites si\n" +
					"        WHERE  t.supplier_id = pqi.supplier_id\n" +
					"        AND    si.qualification_id = pqi.qualification_id\n" +
					"        AND    si.add_flag = 'Y') t\n" +
					"WHERE  1 = 1\n";


	public static String QUERY_SUPPLIER_NUMBER = "SELECT get_supplier_number_f() AS supplierNumber FROM dual";

	public static String LOV_SUPPLIER_QUERY_SQL =
					"SELECT\n" +
					"  psi.supplier_id AS supplierId,\n" +
					"  psi.supplier_number AS supplierNumber,\n" +
					"  psi.supplier_name AS supplierName,\n" +
					"  psi.supplier_status AS supplierStatus,\n" +
					"  slv1.meaning AS supplierStatusDesc\n" +
					"FROM\n" +
					"  srm_pos_supplier_info psi,\n" +
					"  saaf_lookup_values slv1\n" +
					"WHERE psi.supplier_status = slv1.lookup_code\n" +
					"  AND slv1.lookup_type = 'POS_SUPPLIER_STATUS'";

	public static String QUERY_PURCHASE_TOTAL_PRICE_INFO ="SELECT  si.inst_name instName,\n" +
			" spsi.supplier_number supplierNumber,\n" +
			" spsi.supplier_name supplierName,\n" +
			" slv.meaning supplierType,\n" +
			" spsc.license_num licenseNum,\n" +
			" SUM(spl.demand_qty*spl.non_tax_price) AS nonTaxTotalPrice \n" +
			" FROM  srm_po_headers sph,\n" +
			" srm_po_lines spl,\n" +
			" saaf_institution si,\n" +
			" srm_pos_supplier_info spsi,\n" +
			" srm_pos_supplier_credentials spsc,\n" +
			" saaf_lookup_values slv\n" +
			" WHERE sph.po_header_id = spl.po_header_id \n" +
			" AND sph.org_id = si.inst_id \n" +
			" AND sph.supplier_id = spsi.supplier_id \n" +
			" AND spsi.supplier_id = spsc.supplier_id\n" +
			" AND slv.lookup_type = 'POS_SUPPLIER_TYPE' \n" +
			" AND spsi.supplier_type = slv.lookup_code ";


	public static String QUERY_PURCHASE_RECEIVE_PRICE_INFO = "SELECT si.inst_name instName,\n" +
			" spsi.supplier_name supplierName, \n" +
			" SUM(spdl.received_qty*spl.non_tax_price) AS nonTaxReceivePrice \n" +
			" FROM  srm_po_delivery_lines spdl,\n" +
			" srm_po_lines spl,\n" +
			" srm_po_delivery_headers spdh,\n" +
			" saaf_institution si,\n" +
			" srm_pos_supplier_info spsi\n" +
			" WHERE spdl.po_line_id = spl.po_line_id \n" +
			" AND spdh.delivery_header_id = spdl.delivery_header_id \n" +
			" AND si.inst_id = spdh.org_id\n" +
			" AND\tspsi.supplier_id = spdh.supplier_id ";


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

	//别名
	//引入进度查询字段
	private Integer instId; //组织ID
	private String qualificationNumber;  //资质审查编号
	private String sceneTypeDesc;  //业务类型描述
	private String addressName;  //地址名称
	private String instName;  //组织名称
	private String qualificationStatusDesc;  //资质审查状态描述
	@JSONField(format = "yyyy-MM-dd")
	private Date approveDateQ; // 自制审查审批时间
	private String localeReviewNumber;  //现场考察单号
	private String localeReviewStatusDESC; //现场考察状态
	@JSONField(format = "yyyy-MM-dd")
	private Date approvedDateR; // 现场考察审批时间
	private String trialsNumber; //样品实验单号
	private String trialsStatusDESC; //样品实验状态描述
	private String scheduleDESC; // 引入进度描述
	private String scheduleStatus; // 引入进度
	@JSONField(format = "yyyy-MM-dd")
	private Date approvedDateS; // 样品实验审批时间
	private Integer totalDays; //总天数
	@JSONField(format = "yyyy-MM-dd")
	private Date endTime;
	@JSONField(format = "yyyy-MM-dd")
	private Date establishmentDate; // 成立时间
	private String createByName; //供应商创建人名称
	private String typeStr;
	private String statusStr;//供应商状态别名
	private String gradeStr;//供应商状态别名
	private String industryStr;
	private String licenseNum;
	private String taxCode;
	private String tissueCode;
	private Integer credentialsId;
	@JSONField(format = "yyyy-MM-dd")
	private Date licenseIndate;
	private String longLicenseIndate;
	@JSONField(format = "yyyy-MM-dd")
	private Date tissueIndate;
	private String bankPermitNumber;
	private String muchInOne;
	private String businessScope;
	private String corporateIdentity;
	private String representativeName;
	private String lookupCode;
	private String meaning;
	private String tag;
	private Integer supplierCategoryId;
	private String bigCategoryCode;
	private String middleCategoryCode;
	private String smallCategoryCode;
	private String status;
	private String bigCategoryName;
	private String middleCategoryName;
	private String smallCategoryName;
	private Integer certificateId;
	private String certificateName;
	private String certificateNumber;
	private String certificateDescription;
	private Integer bankAccountId;
	private String bankAccountNumber;
	private String bankUserName;
	private Integer bankId;
	private String bankName;
	private String bankNameStr;
	private String settleAcctTypeName;
	private String creater;
	private String qualificationResultMean;
	private String localeReviewResultMean;
	private String trialsResultMean;
	private String approvalEmployeeName;
	private String companyRegisteredAddress; //公司注册地址
	private String registeredCapital; //注册资金(万)
	private String independentLegalPersonFlag; //是否独立法人
	private String independentLegalPersonFlagStr; //是否独立法人
	private String valueAddedTaxInvoiceFlag; //能否开6个税点的增值税专用发票
	private String valueAddedTaxInvoiceFlagStr; //能否开6个税点的增值税专用发票
	private String valueAddedTaxInvoiceDesc; //能否开6个税点的增值税专用发票-说明
	private String associatedCompany; //关联公司
	private String region; //意向服务区域
	private String regionStr; //意向服务区域
	private String requestId;//报文请求ID
	private String supplierEbsNumber;//供应商EBS编号


	//报表的别名
	public BigDecimal supplierCount;//供应商数量
	public String supplierProportion;//供应商占比（百分比）
	public String timeFrame;//交易时间范围，快码TRANSACTIONCYCLE
	public String timeFrameName;//交易时间范围别名
	public String supplierGrade;//供应商级别
	private BigDecimal nonTaxTotalPrice;//订单总金额
	private BigDecimal nonTaxReceivePrice;//接收总金额


	public String getGradeStr() {
		return gradeStr;
	}

	public void setGradeStr(String gradeStr) {
		this.gradeStr = gradeStr;
	}

	public String getSupplierGrade() {
		return supplierGrade;
	}

	public void setSupplierGrade(String supplierGrade) {
		this.supplierGrade = supplierGrade;
	}



	public BigDecimal getNonTaxTotalPrice() { return nonTaxTotalPrice; }

	public void setNonTaxTotalPrice(BigDecimal nonTaxTotalPrice) { this.nonTaxTotalPrice = nonTaxTotalPrice;}

	public BigDecimal getNonTaxReceivePrice() { return nonTaxReceivePrice; }

	public void setNonTaxReceivePrice(BigDecimal nonTaxReceivePrice) { this.nonTaxReceivePrice = nonTaxReceivePrice; }

	public Integer getGradeLineId() {
		return gradeLineId;
	}

	public void setGradeLineId(Integer gradeLineId) {
		this.gradeLineId = gradeLineId;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getTimeFrameName() {
		return timeFrameName;
	}

	public void setTimeFrameName(String timeFrameName) {
		this.timeFrameName = timeFrameName;
	}

	public BigDecimal getSupplierCount() {
		return supplierCount;
	}

	public void setSupplierCount(BigDecimal supplierCount) {
		this.supplierCount = supplierCount;
	}

	public String getSupplierProportion() {
		return supplierProportion;
	}

	public void setSupplierProportion(String supplierProportion) {
		this.supplierProportion = supplierProportion;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getBankNameStr() {
		return bankNameStr;
	}

	public void setBankNameStr(String bankNameStr) {
		this.bankNameStr = bankNameStr;
	}

	public String getSettleAcctTypeName() {
		return settleAcctTypeName;
	}

	public void setSettleAcctTypeName(String settleAcctTypeName) {
		this.settleAcctTypeName = settleAcctTypeName;
	}

	private Integer branchId;
	private String branchNumber;
	private String branchName;
	private String bankCurrency;
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate;
	private Integer supplierContactId;
	private String contactName;
	private String mobilePhone;
	private String fixedPhone;
	private String faxPhone;
	private String emailAddress;
	private Integer purchaseLimit;
	private Integer supplyOrder;
	private Integer quotaRate;
	private String cooperativeProduct;
	private Integer employeeId;
	private String employeeName;
	private String employeeNumber;
	private Integer licenseFileId;
	private Integer tissueFileId;
	private Integer taxFileId;
	private Integer bankPermitFileId;
	private Integer othersFileId;
	private Integer fileId;
	private String filePath;
	private String fileName;
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
	private String supplierFilePath;
	private String supplierFileName;
	private String userName;
	@JSONField(format = "yyyy-MM-dd")
	private Date failureDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date invalid;
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate;
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	public String getSupplierType() {
		return supplierType;
	}
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getSupplierStatus() {
		return supplierStatus;
	}
	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getIndustryStr() {
		return industryStr;
	}
	public void setIndustryStr(String industryStr) {
		this.industryStr = industryStr;
	}
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	public String getPassU9Flag() { return passU9Flag; }
	public void setPassU9Flag(String passU9Flag) { this.passU9Flag = passU9Flag; }
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getTissueCode() {
		return tissueCode;
	}
	public void setTissueCode(String tissueCode) {
		this.tissueCode = tissueCode;
	}
	public String getLookupCode() {
		return lookupCode;
	}
	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	public String getAbleEditFlag() { return ableEditFlag; }
	public void setAbleEditFlag(String ableEditFlag) { this.ableEditFlag = ableEditFlag; }
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}
	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
	}
	public String getBigCategoryCode() {
		return bigCategoryCode;
	}
	public void setBigCategoryCode(String bigCategoryCode) {
		this.bigCategoryCode = bigCategoryCode;
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
	public String getBigCategoryName() {
		return bigCategoryName;
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
	public Integer getSupplierContactId() {
		return supplierContactId;
	}
	public void setSupplierContactId(Integer supplierContactId) {
		this.supplierContactId = supplierContactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getFixedPhone() {
		return fixedPhone;
	}
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getSupplierShortName() {
		return supplierShortName;
	}
	public void setSupplierShortName(String supplierShortName) {
		this.supplierShortName = supplierShortName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public String getSupplierIndustry() {
		return supplierIndustry;
	}
	public void setSupplierIndustry(String supplierIndustry) {
		this.supplierIndustry = supplierIndustry;
	}
	public String getCompanyFax() {
		return companyFax;
	}
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
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
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public Date getLicenseIndate() {
		return licenseIndate;
	}
	public void setLicenseIndate(Date licenseIndate) {
		this.licenseIndate = licenseIndate;
	}
	public String getLongLicenseIndate() {
		return longLicenseIndate;
	}
	public void setLongLicenseIndate(String longLicenseIndate) {
		this.longLicenseIndate = longLicenseIndate;
	}
	public Date getTissueIndate() {
		return tissueIndate;
	}
	public void setTissueIndate(Date tissueIndate) {
		this.tissueIndate = tissueIndate;
	}
	public String getBankPermitNumber() {
		return bankPermitNumber;
	}
	public void setBankPermitNumber(String bankPermitNumber) {
		this.bankPermitNumber = bankPermitNumber;
	}
	public String getMuchInOne() {
		return muchInOne;
	}
	public void setMuchInOne(String muchInOne) {
		this.muchInOne = muchInOne;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getCorporateIdentity() {
		return corporateIdentity;
	}
	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public Integer getCredentialsId() {
		return credentialsId;
	}
	public void setCredentialsId(Integer credentialsId) {
		this.credentialsId = credentialsId;
	}
	public Integer getLicenseFileId() {
		return licenseFileId;
	}
	public void setLicenseFileId(Integer licenseFileId) {
		this.licenseFileId = licenseFileId;
	}
	public Integer getTissueFileId() {
		return tissueFileId;
	}
	public void setTissueFileId(Integer tissueFileId) {
		this.tissueFileId = tissueFileId;
	}
	public Integer getTaxFileId() {
		return taxFileId;
	}
	public void setTaxFileId(Integer taxFileId) {
		this.taxFileId = taxFileId;
	}
	public Integer getBankPermitFileId() {
		return bankPermitFileId;
	}
	public void setBankPermitFileId(Integer bankPermitFileId) {
		this.bankPermitFileId = bankPermitFileId;
	}
	public Integer getOthersFileId() {
		return othersFileId;
	}
	public void setOthersFileId(Integer othersFileId) {
		this.othersFileId = othersFileId;
	}
	public Integer getSupplierFileId() {
		return supplierFileId;
	}
	public void setSupplierFileId(Integer supplierFileId) {
		this.supplierFileId = supplierFileId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPurchaseLimit() {
		return purchaseLimit;
	}
	public void setPurchaseLimit(Integer purchaseLimit) {
		this.purchaseLimit = purchaseLimit;
	}
	public Integer getSupplyOrder() {
		return supplyOrder;
	}
	public void setSupplyOrder(Integer supplyOrder) {
		this.supplyOrder = supplyOrder;
	}
	public Integer getQuotaRate() {
		return quotaRate;
	}
	public void setQuotaRate(Integer quotaRate) {
		this.quotaRate = quotaRate;
	}
	public String getCooperativeProduct() {
		return cooperativeProduct;
	}
	public void setCooperativeProduct(String cooperativeProduct) {
		this.cooperativeProduct = cooperativeProduct;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
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

	public String getSrmDelivery() { return srmDelivery; }
	public void setSrmDelivery(String srmDelivery) { this.srmDelivery = srmDelivery; }

	public Date getInvalid() {
		return invalid;
	}

	public void setInvalid(Date invalid) {
		this.invalid = invalid;
	}

	public Date getFailureDate() {
		return failureDate;
	}

	public void setFailureDate(Date failureDate) {
		this.failureDate = failureDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getSupplierClassify() {
		return supplierClassify;
	}

	public void setSupplierClassify(String supplierClassify) {
		this.supplierClassify = supplierClassify;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
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

	public String getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
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

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
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

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getQualificationNumber() {
		return qualificationNumber;
	}

	public void setQualificationNumber(String qualificationNumber) {
		this.qualificationNumber = qualificationNumber;
	}

	public String getSceneTypeDesc() {
		return sceneTypeDesc;
	}

	public void setSceneTypeDesc(String sceneTypeDesc) {
		this.sceneTypeDesc = sceneTypeDesc;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getQualificationStatusDesc() {
		return qualificationStatusDesc;
	}

	public void setQualificationStatusDesc(String qualificationStatusDesc) {
		this.qualificationStatusDesc = qualificationStatusDesc;
	}

	public Date getApproveDateQ() {
		return approveDateQ;
	}

	public void setApproveDateQ(Date approveDateQ) {
		this.approveDateQ = approveDateQ;
	}

	public String getLocaleReviewNumber() {
		return localeReviewNumber;
	}

	public void setLocaleReviewNumber(String localeReviewNumber) {
		this.localeReviewNumber = localeReviewNumber;
	}

	public String getLocaleReviewStatusDESC() {
		return localeReviewStatusDESC;
	}

	public void setLocaleReviewStatusDESC(String localeReviewStatusDESC) {
		this.localeReviewStatusDESC = localeReviewStatusDESC;
	}

	public Date getApprovedDateR() {
		return approvedDateR;
	}

	public void setApprovedDateR(Date approvedDateR) {
		this.approvedDateR = approvedDateR;
	}

	public String getTrialsNumber() {
		return trialsNumber;
	}

	public void setTrialsNumber(String trialsNumber) {
		this.trialsNumber = trialsNumber;
	}

	public String getTrialsStatusDESC() {
		return trialsStatusDESC;
	}

	public void setTrialsStatusDESC(String trialsStatusDESC) {
		this.trialsStatusDESC = trialsStatusDESC;
	}

	public String getScheduleDESC() {
		return scheduleDESC;
	}

	public void setScheduleDESC(String scheduleDESC) {
		this.scheduleDESC = scheduleDESC;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getApprovedDateS() {
		return approvedDateS;
	}

	public void setApprovedDateS(Date approvedDateS) {
		this.approvedDateS = approvedDateS;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getQualificationResultMean() {
		return qualificationResultMean;
	}

	public void setQualificationResultMean(String qualificationResultMean) {
		this.qualificationResultMean = qualificationResultMean;
	}

	public String getLocaleReviewResultMean() {
		return localeReviewResultMean;
	}

	public void setLocaleReviewResultMean(String localeReviewResultMean) {
		this.localeReviewResultMean = localeReviewResultMean;
	}

	public String getTrialsResultMean() {
		return trialsResultMean;
	}

	public void setTrialsResultMean(String trialsResultMean) {
		this.trialsResultMean = trialsResultMean;
	}

	public String getApprovalEmployeeName() {
		return approvalEmployeeName;
	}

	public void setApprovalEmployeeName(String approvalEmployeeName) {
		this.approvalEmployeeName = approvalEmployeeName;
	}

	public String getCompanyRegisteredAddress() {
		return companyRegisteredAddress;
	}

	public void setCompanyRegisteredAddress(String companyRegisteredAddress) {
		this.companyRegisteredAddress = companyRegisteredAddress;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getIndependentLegalPersonFlag() {
		return independentLegalPersonFlag;
	}

	public void setIndependentLegalPersonFlag(String independentLegalPersonFlag) {
		this.independentLegalPersonFlag = independentLegalPersonFlag;
	}

	public String getIndependentLegalPersonFlagStr() {
		return independentLegalPersonFlagStr;
	}

	public void setIndependentLegalPersonFlagStr(String independentLegalPersonFlagStr) {
		this.independentLegalPersonFlagStr = independentLegalPersonFlagStr;
	}

	public String getValueAddedTaxInvoiceFlag() {
		return valueAddedTaxInvoiceFlag;
	}

	public void setValueAddedTaxInvoiceFlag(String valueAddedTaxInvoiceFlag) {
		this.valueAddedTaxInvoiceFlag = valueAddedTaxInvoiceFlag;
	}

	public String getValueAddedTaxInvoiceFlagStr() {
		return valueAddedTaxInvoiceFlagStr;
	}

	public void setValueAddedTaxInvoiceFlagStr(String valueAddedTaxInvoiceFlagStr) {
		this.valueAddedTaxInvoiceFlagStr = valueAddedTaxInvoiceFlagStr;
	}

	public String getValueAddedTaxInvoiceDesc() {
		return valueAddedTaxInvoiceDesc;
	}

	public void setValueAddedTaxInvoiceDesc(String valueAddedTaxInvoiceDesc) {
		this.valueAddedTaxInvoiceDesc = valueAddedTaxInvoiceDesc;
	}

	public String getAssociatedCompany() {
		return associatedCompany;
	}

	public void setAssociatedCompany(String associatedCompany) {
		this.associatedCompany = associatedCompany;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionStr() {
		return regionStr;
	}

	public void setRegionStr(String regionStr) {
		this.regionStr = regionStr;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSupplierEbsNumber() {
		return supplierEbsNumber;
	}

	public void setSupplierEbsNumber(String supplierEbsNumber) {
		this.supplierEbsNumber = supplierEbsNumber;
	}
}
