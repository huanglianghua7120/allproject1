package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class SrmPosQualificationInfoEntity_HI_RO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer qualificationId;
	private String qualificationNumber;
	private String qualificationStatus;
	private String supplierName;
	private String supplierNumber;
	private String supplierStatus;
	private String userFullName;
	private String approveName;
	@JSONField(format = "yyyy-MM-dd")
	private Date approveDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;
	private Integer createdBy;
	private String qualificationStatusMean;

	private Integer supplierId;
	private String needOnsiteInspection;
	private String needSampleTrial;
	private String qualificationResult;

	private Integer categoryId;
	private Integer supplierCategoryId;
	private String categoryCode;
	private String categoryName;
	private String fullCategoryCode;
	private String fullCategoryName;
	private String bigCategoryCode;
	private String middleCategoryCode;
	private String smallCategoryCode;
	private String selectedFlag;
	private String bigCategoryMean;
	private String middleCategoryMean;
	private String smallCategoryMean;
	private Integer qualifCategoryId;
	private String description;

	private Integer qualificationFileId;
	private String qualificationFilePath;
	private String qualificationFileName;

	private Integer reportAppendixFileId;
	private String reportAppendixFilePath;
	private String reportAppendixFileName;

	private Integer projectReportFileId;
	private String projectReportFilePath;
	private String projectReportFileName;

	private Integer countSupplier;
	private Integer trialCateId;

	private String addFlag;
	private String categoryStatus;// 考察分类状态
	private String categoryStatusStr;// 考察分类状态说明
	private String sceneType;
	private String sceneTypeDisp;
	private Integer localeReviewCategoryId;// 现场考察分类ID
	private String lastFinishDate;//引入的最后审批通过时间
	private String supplierTypeName;
	private String countryName;
	private String supplierType;
	private String addressName;
	private String country;
	private String detailAddress;
	private String examineResultName;


	public static final String QUERY_COUNT_SUPPLIER_SQL = "SELECT count(*) countSupplier FROM `srm_pos_supplier_categories` t where t.supplier_id=:supplierId";
	/**
	 * 现场考察分类查询
	 */
	public static String QUERY_REVIEW_CATEGORIES_SQL = "SELECT\n" +
			"  a.supplier_category_id AS supplierCategoryId,\n" +
			"  a.category_id          AS categoryId,\n" +
			"  b.category_code        AS categoryCode,\n" +
			"  b.category_name        AS categoryName,\n" +
			"  b.full_category_code   AS fullCategoryCode,\n" +
			"  b.full_category_name   AS fullCategoryName,\n" +
			"  a.big_category_code    AS bigCategoryCode,\n" +
			"  a.middle_category_code AS middleCategoryCode,\n" +
			"  a.small_category_code  AS smallCategoryCode,\n" +
			"  (CASE WHEN c.add_flag = 'Y' THEN 'Y' ELSE 'N' END) AS addFlag,\n" +
			"  c.locale_review_category_id        AS localeReviewCategoryId,\n" +
			"  c.selected_flag        AS selectedFlag,\n" +
			"  a.status               AS categoryStatus,\n" +
			"  d.meaning              AS categoryStatusStr\n" +
			"FROM srm_pos_qualification_cates a\n" +
			"  LEFT JOIN srm_base_categories b\n" +
			"    ON a.category_id = b.category_id\n" +
			"  LEFT JOIN srm_pos_locale_review_cates c\n" +
			"    ON a.category_id = c.category_id and c.locale_review_id = :localeReviewId\n" +
			"  LEFT JOIN saaf_lookup_values d\n" +
			"    ON a.status = d.lookup_code\n" +
			"      AND d.lookup_type = 'POS_CATEGORY_STATUS'\n" +
			"WHERE 1 = 1";


	public static String QUERY_SAMPLE_CATEGORIES_SQL = "SELECT\r\n" + //
			"  a.supplier_category_id AS supplierCategoryId,\r\n" + //
			"  a.category_id          AS categoryId,\r\n" + //
			"  b.category_code        AS categoryCode,\r\n" + //
			"  b.category_name        AS categoryName,\r\n" + //
			"  b.full_category_code   AS fullCategoryCode,\r\n" +
			"  b.full_category_name   AS fullCategoryName,\r\n" +
			"  a.big_category_code    AS bigCategoryCode,\r\n" + //
			"  a.middle_category_code AS middleCategoryCode,\r\n" + //
			"  a.small_category_code  AS smallCategoryCode,\r\n" + //
			"  a.add_flag             AS addFlag,\r\n" + //
			"  c.trial_cate_id        AS trialCateId,\r\n" + //
			"  c.selected_flag        AS selectedFlag,\r\n" + //
			"  a.status               AS categoryStatus,\r\n" + //
			"  d.meaning              AS categoryStatusStr\r\n" + //
			"FROM srm_pos_qualification_cates a\r\n" + //
			"  LEFT JOIN srm_base_categories b\r\n" + //
			"    ON a.category_id = b.category_id\r\n" + //
			"  LEFT JOIN srm_pos_sample_trial_cates c\r\n" + //
			"    ON a.category_id = c.category_id and c.trial_id=:trialId\r\n" + //
			"  LEFT JOIN saaf_lookup_values d\r\n" + //
			"    ON a.status = d.lookup_code\r\n" + //
			"      AND d.lookup_type = 'POS_CATEGORY_STATUS'\r\n" + //
			"WHERE 1 = 1 AND a.add_flag = 'Y' ";//


	public static String QUERY_QUALIFICATION_SQL =
					"SELECT qi.qualification_id       qualificationId\n" +
					"      ,qi.qualification_number   qualificationNumber\n" +
					"      ,qi.qualification_status   qualificationStatus\n" +
					"      ,qi.supplier_id            supplierId\n" +
					"      ,qi.creation_date          creationDate\n" +
					"      ,qi.qualification_result   qualificationResult\n" +
					"      ,qi.description            description\n" +
					"      ,qi.approve_date           approveDate\n" +
					"      ,qi.qualification_file_id  qualificationFileId\n" +
					"      ,rf.access_path            qualificationFilePath\n" +
					"      ,rf.file_name              qualificationFileName\n" +
					"      ,qi.report_appendix_file_id  reportAppendixFileId\n" +
					"      ,rf2.access_path           reportAppendixFilePath\n" +
					"      ,rf2.file_name             reportAppendixFileName\n" +
					"      ,qi.project_report_file_id  projectReportFileId\n" +
					"      ,rf3.access_path           projectReportFilePath\n" +
					"      ,rf3.file_name             projectReportFileName\n" +
					"      ,si.supplier_number        supplierNumber\n" +
					"      ,si.supplier_name          supplierName\n" +
					"      ,su.user_full_name         userFullName\n" +
					"      ,sfu.user_full_name        approveName\n" +
					"      ,slv.meaning               qualificationStatusMean\n" +
					"      ,qi.scene_type             sceneType\n" +
					"      ,slv1.meaning              sceneTypeDisp\n" +
					"  FROM srm_pos_qualification_info qi\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = qi.qualification_status\n" +
					"   AND slv.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = qi.scene_type\n" +
					"   AND slv1.lookup_type = 'POS_SCENE_TYPE'\n" +
					"  LEFT JOIN saaf_base_result_file rf\n" +
					"    ON qi.qualification_file_id = rf.file_id\n" +
					"  LEFT JOIN saaf_base_result_file rf2\n" +
					"    ON qi.report_appendix_file_id = rf2.file_id\n" +
					"  LEFT JOIN saaf_base_result_file rf3\n" +
					"    ON qi.project_report_file_id = rf3.file_id\n" +
					"  LEFT JOIN saaf_users sfu\n" +
					"    ON qi.approve_by = sfu.user_id, srm_pos_supplier_info si, saaf_users su\n" +
					" WHERE qi.supplier_id = si.supplier_id\n" +
					"   AND qi.created_by = su.user_id";

	public static String QUERY_QUALIFICATION_STATUS_SQL =
			"SELECT qi.qualification_id       qualificationId\n" +
					"      ,qi.qualification_number   qualificationNumber\n" +
					"      ,qi.qualification_status   qualificationStatus\n" +
					"      ,qi.supplier_id            supplierId\n" +
					"      ,qi.creation_date          creationDate\n" +
					"      ,qi.qualification_result   qualificationResult\n" +
					"      ,qi.description            description\n" +
					"      ,qi.approve_date           approveDate\n" +
					"      ,qi.qualification_file_id  qualificationFileId\n" +
					"      ,qi.report_appendix_file_id reportAppendixFileId\n" +
					"      ,qi.project_report_file_id projectReportFileId\n" +
					"      ,si.supplier_number        supplierNumber\n" +
					"      ,si.supplier_name          supplierName\n" +
					"      ,si.supplier_id          supplierId\n" +
					"      ,si.supplier_status          supplierStatus\n" +
					"  FROM srm_pos_supplier_info si\n" +
					"  LEFT JOIN  srm_pos_qualification_info qi\n" +
					"  ON qi.supplier_id = si.supplier_id\n" +
					"  WHERE 1 = 1\n";

	public static String LOV_SUPPLIER_QUERY_SQL =
					"SELECT\n" +
					"  si.supplier_id supplierId,\n" +
					"  si.supplier_name supplierName,\n" +
					"  si.supplier_number supplierNumber,\n" +
					"  si.supplier_status supplierStatus\n" +
					"FROM\n" +
					"  srm_pos_supplier_info si\n" +
					"WHERE nvl(si.blacklist_flag, 'N') = 'N'";

	public static String LOV_SUPPLIER_QUERY_FOR_QUALIF_SQL =
					"SELECT\n" +
					"  si.supplier_id supplierId,\n" +
					"  si.supplier_name supplierName,\n" +
					"  si.supplier_number supplierNumber,\n" +
					"  si.supplier_status supplierStatus\n" +
					"FROM\n" +
					"  srm_pos_supplier_info si\n" +
					"WHERE EXISTS\n" +
					"  (SELECT\n" +
					"    1\n" +
					"  FROM\n" +
					"    srm_pos_supplier_sites pss\n" +
					"  WHERE pss.supplier_id = si.supplier_id\n" +
					"    AND pss.temporary_site_flag = 'Y')";

	public static String LOV_QUALIFICATION_QUERY_FOR_REVIEWS_SQL = "SELECT\r\n" + //
			"  t1.qualification_id AS qualificationId,\r\n" + //
			"  t1.qualification_number AS qualificationNumber,\r\n" + //
			"  t1.qualification_status AS qualificationStatus,\r\n" + //
			"  t1.`qualification_result` AS qualificationResult,\r\n" + //
			"  t1.creation_date AS creationDate\r\n" + //
			"FROM srm_pos_qualification_info t1\r\n" + //
			"  LEFT JOIN srm_pos_qualification_sites qs\n" +
			"  ON t1.qualification_id = qs.qualification_id\n" +
			"  LEFT JOIN srm_pos_qualification_cates qc\n" +
			"  ON t1.qualification_id = qc.qualification_id\n" +
			"WHERE 1 = 1\n" +
			"AND qs.supplier_site_id NOT IN (\n" +
			"\tSELECT\n" +
			"  lrs.supplier_site_id\n" +
			"\tFROM srm_pos_qualification_info t\n" +
			"  LEFT JOIN  srm_pos_locale_reviews lr\n" +
			"  ON lr.qualification_id = t.qualification_id\n" +
			"\tLEFT JOIN srm_pos_locale_review_sites lrs\n" +
			"\tON lrs.locale_review_id = lr.locale_review_id\n" +
			"\tLEFT JOIN srm_pos_locale_review_cates lrc\n" +
			"\tON lrc.locale_review_id = lr.locale_review_id\n" +
			"\tWHERE 1 = 1\n" +
			"\tAND t.`qualification_status` = 'APPROVED' \n" +
			"\tAND t.`qualification_result` = 'PASS' \n" +
			"\tAND lrs.selected_flag = 'Y'\n" +
			"\tAND (lrs.site_status = 'EFFECTIVE') -- 现场考察地点状态不为合格\n" +
			"\tAND lr.locale_review_result = 'PASS') \n " +
			"\tAND qc.supplier_category_id NOT IN (\n" +
			"\tSELECT\n" +
			"\t\tlrc.supplier_category_id\n" +
			"\tFROM\n" +
			"\t\tsrm_pos_qualification_info t\n" +
			"\tLEFT JOIN srm_pos_locale_reviews lr ON lr.qualification_id = t.qualification_id\n" +
			"\tLEFT JOIN srm_pos_locale_review_sites lrs ON lrs.locale_review_id = lr.locale_review_id\n" +
			"\tLEFT JOIN srm_pos_locale_review_cates lrc ON lrc.locale_review_id = lr.locale_review_id\n" +
			"\tWHERE\n" +
			"\t\t1 = 1\n" +
			"\tAND t.`qualification_status` = 'APPROVED'\n" +
			"\tAND t.`qualification_result` = 'PASS'\n" +
			"\tAND lrc.selected_flag = 'Y'\n" +
			"\tAND (\n" +
			"\t\tlrc.status = 'EFFECTIVE'\n" +
			"\t) -- 现场考察地点状态为合格\n" +
			"\tAND lr.locale_review_result = 'PASS')\n";//

	public static String LOV_QUALIFICATION_QUERY_SQL = " SELECT\n" +
			"  t.qualification_id AS qualificationId,\n" +
			"  t.qualification_number AS qualificationNumber,\n" +
			"  t.qualification_status AS qualificationStatus,\n" +
			"  t.`qualification_result` AS qualificationResult,\n" +
			"  t.creation_date AS creationDate\n" +
			"FROM srm_pos_qualification_info t\n" +
			"  LEFT JOIN  srm_pos_qualification_cates qc\n" +
			"  ON t.qualification_id = qc.qualification_id\n" +
			" where 1=1 ";//

	public static String QUERY_USER_RESP = "SELECT \r\n" + "t.user_id\r\n" + "FROM\r\n" + "saaf_user_resp t,\r\n"
			+ "saaf_responsibilitys r,\r\n" + "saaf_users u\r\n" + "WHERE\r\n"
			+ "t.responsibility_id = r.responsibility_id\r\n" + "AND r.responsibility_key = 'FIN_RESP'\r\n"
			+ "AND t.user_id = u.user_id\r\n" + "AND SYSDATE() BETWEEN t.start_date_active\r\n"
			+ "AND IFNULL(t.end_date_active,SYSDATE())\r\n" + "GROUP BY t.user_id\r\n";

	public static String QUERY_QUALIFICATION_TOEKP ="SELECT Spqi.Qualification_Number\n" +
			"      ,Spqi.Qualification_Id\n" +
			"      ,Spsi.Supplier_Name\n" +
			"      ,Spsi.Supplier_Type\n" +
			"      ,Slv.Meaning               supplierTypeName\n" +
			"      ,p.Address_Name\n" +
			"      ,p.Country\n" +
			"      ,p.Countryname\n" +
			"      ,p.Detail_Address\n" +
			"      ,Spqi.Qualification_Result\n" +
			"      ,Slv2.Meaning              examineResultName\n" +
			"      ,Spqi.Report_Appendix_File_Id\n" +
			"      ,Spqi.Project_Report_File_Id\n" +
			"      ,Spqi.description\n" +
			"      ,Spqi.attribute1\n" +
			"  FROM Srm_Pos_Qualification_Info Spqi\n" +
			"  LEFT JOIN Srm_Pos_Supplier_Info Spsi\n" +
			"    ON Spsi.Supplier_Id = Spqi.Supplier_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv\n" +
			"    ON Slv.Lookup_Type = 'POS_SUPPLIER_TYPE'\n" +
			"   AND Slv.Lookup_Code = Spsi.Supplier_Type\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv2\n" +
			"    ON Slv2.Lookup_Type = 'POS_EXAMINE_RESULT'\n" +
			"   AND Slv2.Lookup_Code = Spqi.Qualification_Result\n" +
			"  LEFT JOIN (SELECT Spsa.Address_Name\n" +
			"                           ,Spsa.Country\n" +
			"                           ,Slv1.Meaning Countryname\n" +
			"                           ,Spsa.Detail_Address\n" +
			"                           ,t.Qualification_Id\n" +
			"                       FROM Srm_Pos_Qualification_Sites t\n" +
			"                       LEFT JOIN Srm_Pos_Supplier_Addresses Spsa\n" +
			"                         ON Spsa.Supplier_Address_Id = t.Supplier_Address_Id\n" +
			"                       LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"                         ON Slv1.Lookup_Type = 'BASE_COUNTRY'\n" +
			"                        AND Slv1.Lookup_Code = Spsa.Country\n" +
			"                      WHERE t.Add_Flag = 'Y'\n" +
			"                      ORDER BY t.Creation_Date DESC) p\n" +
			"    ON p.Qualification_Id = Spqi.Qualification_Id\n" +
			" WHERE 1 = 1 ";


	public static String QUERY_QUALIFICATION_SITE_TOEKP="SELECT t.Qualification_Id\n" +
			"      ,t.site_name\n" +
			"      ,t.Org_Id\n" +
			"      ,Org.Inst_Code\n" +
			"      ,Org.Inst_Name\n" +
			"  FROM Srm_Pos_Qualification_Sites t\n" +
			"  LEFT JOIN Saaf_Institution Org\n" +
			"    ON t.Org_Id = Org.Inst_Id\n" +
			" WHERE t.Add_Flag = 'Y'";


	public static String QUERY_QUALIFICATION_SITE_FROMEKP="select spqi.qualification_id,\n" +
			"       spqi.qualification_number,\n" +
			"       spqi.qualification_status,\n" +
			"       spqi.attribute1\n" +
			"  from Srm_Pos_Qualification_Info spqi\n" +
			" where spqi.QUALIFICATION_STATUS in ('SUBMITTED','REJECTED')\n" +
			"   and spqi.attribute1 is not null";

	public static String QUERY_QUALIFICATION_CATEGORY_TOEKP="select pqc.qualification_id, pqc.category_id, sbc.category_name\n" +
			"  from srm_pos_qualification_cates pqc\n" +
			"  left join srm_base_categories sbc\n" +
			"    on pqc.category_id = sbc.category_id\n" +
			" where pqc.add_flag = 'Y'\n";



	private String siteName;
	private Integer orgId;
	private String instCode;
	private String instName;
	private String attribute1;

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getExamineResultName() {
		return examineResultName;
	}

	public void setExamineResultName(String examineResultName) {
		this.examineResultName = examineResultName;
	}

	public String getSupplierTypeName() {
		return supplierTypeName;
	}

	public void setSupplierTypeName(String supplierTypeName) {
		this.supplierTypeName = supplierTypeName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
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

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getLastFinishDate() {
		return lastFinishDate;
	}

	public void setLastFinishDate(String lastFinishDate) {
		this.lastFinishDate = lastFinishDate;
	}

	public Integer getCountSupplier() {
		return countSupplier;
	}

	public void setCountSupplier(Integer countSupplier) {
		this.countSupplier = countSupplier;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationNumber() {
		return qualificationNumber;
	}

	public void setQualificationNumber(String qualificationNumber) {
		this.qualificationNumber = qualificationNumber;
	}

	public String getQualificationStatus() {
		return qualificationStatus;
	}

	public void setQualificationStatus(String qualificationStatus) {
		this.qualificationStatus = qualificationStatus;
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

	public String getSupplierStatus() {
		return supplierStatus;
	}

	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = supplierStatus;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
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

	public String getQualificationStatusMean() {
		return qualificationStatusMean;
	}

	public void setQualificationStatusMean(String qualificationStatusMean) {
		this.qualificationStatusMean = qualificationStatusMean;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getNeedOnsiteInspection() {
		return needOnsiteInspection;
	}

	public void setNeedOnsiteInspection(String needOnsiteInspection) {
		this.needOnsiteInspection = needOnsiteInspection;
	}

	public String getNeedSampleTrial() {
		return needSampleTrial;
	}

	public void setNeedSampleTrial(String needSampleTrial) {
		this.needSampleTrial = needSampleTrial;
	}

	public String getQualificationResult() {
		return qualificationResult;
	}

	public void setQualificationResult(String qualificationResult) {
		this.qualificationResult = qualificationResult;
	}

	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}

	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
	}

	public String getFullCategoryCode() { return fullCategoryCode; }

	public void setFullCategoryCode(String fullCategoryCode) { this.fullCategoryCode = fullCategoryCode; }

	public String getFullCategoryName() { return fullCategoryName; }

	public void setFullCategoryName(String fullCategoryName) { this.fullCategoryName = fullCategoryName; }

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

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getBigCategoryMean() {
		return bigCategoryMean;
	}

	public void setBigCategoryMean(String bigCategoryMean) {
		this.bigCategoryMean = bigCategoryMean;
	}

	public String getMiddleCategoryMean() {
		return middleCategoryMean;
	}

	public void setMiddleCategoryMean(String middleCategoryMean) {
		this.middleCategoryMean = middleCategoryMean;
	}

	public String getSmallCategoryMean() {
		return smallCategoryMean;
	}

	public void setSmallCategoryMean(String smallCategoryMean) {
		this.smallCategoryMean = smallCategoryMean;
	}

	public Integer getQualifCategoryId() {
		return qualifCategoryId;
	}

	public void setQualifCategoryId(Integer qualifCategoryId) {
		this.qualifCategoryId = qualifCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQualificationFileId() {
		return qualificationFileId;
	}

	public void setQualificationFileId(Integer qualificationFileId) {
		this.qualificationFileId = qualificationFileId;
	}

	public String getQualificationFilePath() {
		return qualificationFilePath;
	}

	public void setQualificationFilePath(String qualificationFilePath) {
		this.qualificationFilePath = qualificationFilePath;
	}

	public String getQualificationFileName() {
		return qualificationFileName;
	}

	public void setQualificationFileName(String qualificationFileName) {
		this.qualificationFileName = qualificationFileName;
	}

	public Integer getReportAppendixFileId() {
		return reportAppendixFileId;
	}

	public void setReportAppendixFileId(Integer reportAppendixFileId) {
		this.reportAppendixFileId = reportAppendixFileId;
	}

	public String getReportAppendixFilePath() {
		return reportAppendixFilePath;
	}

	public void setReportAppendixFilePath(String reportAppendixFilePath) {
		this.reportAppendixFilePath = reportAppendixFilePath;
	}

	public String getReportAppendixFileName() {
		return reportAppendixFileName;
	}

	public void setReportAppendixFileName(String reportAppendixFileName) {
		this.reportAppendixFileName = reportAppendixFileName;
	}

	public Integer getProjectReportFileId() {
		return projectReportFileId;
	}

	public void setProjectReportFileId(Integer projectReportFileId) {
		this.projectReportFileId = projectReportFileId;
	}

	public String getProjectReportFilePath() {
		return projectReportFilePath;
	}

	public void setProjectReportFilePath(String projectReportFilePath) {
		this.projectReportFilePath = projectReportFilePath;
	}

	public String getProjectReportFileName() {
		return projectReportFileName;
	}

	public void setProjectReportFileName(String projectReportFileName) {
		this.projectReportFileName = projectReportFileName;
	}

	public String getSceneType() {
		return sceneType;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public String getSceneTypeDisp() {
		return sceneTypeDisp;
	}

	public void setSceneTypeDisp(String sceneTypeDisp) {
		this.sceneTypeDisp = sceneTypeDisp;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(String categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCategoryStatusStr() {
		return categoryStatusStr;
	}

	public void setCategoryStatusStr(String categoryStatusStr) {
		this.categoryStatusStr = categoryStatusStr;
	}

	public Integer getLocaleReviewCategoryId() {
		return localeReviewCategoryId;
	}

	public void setLocaleReviewCategoryId(Integer localeReviewCategoryId) {
		this.localeReviewCategoryId = localeReviewCategoryId;
	}

	public Integer getTrialCateId() {
		return trialCateId;
	}

	public void setTrialCateId(Integer trialCateId) {
		this.trialCateId = trialCateId;
	}


}
