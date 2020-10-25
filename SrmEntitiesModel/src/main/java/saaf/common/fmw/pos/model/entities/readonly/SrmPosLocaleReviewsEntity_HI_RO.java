package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmPosLocaleReviewsEntity_HI_RO Entity Object Tue Mar 20 14:33:39 CST 2018
 * Auto Generate
 */

public class SrmPosLocaleReviewsEntity_HI_RO {

	// 根据资质审查单号寻找相应“已批准”、且结论是“合格”的现场考察单；
	public static final String QUERY_REVIEW_ID_SQL =
					"SELECT a.locale_review_id     AS localeReviewId\n" +
					"      ,a.qualification_id     AS qualificationId\n" +
					"      ,b.supplier_category_id AS supplierCategoryId\n" +
					"      ,b.category_id          AS categoryId\n" +
					"      ,b.selected_flag        AS selectedFlag\n" +
					"FROM   srm_pos_locale_reviews      a\n" +
					"      ,srm_pos_locale_review_cates b\n" +
					"WHERE  a.locale_review_id = b.locale_review_id\n" +
					"AND    a.locale_review_result = 'PASS'\n" +
					"AND    b.selected_flag = 'Y'\n";

	// 根据资质审查单号寻找相应“已批准”、且结论是“合格”的现场考察单的地点ID；
	public static final String QUERY_REVIEW_SUPPLIER_SITE_ID_SQL =
					"SELECT splrs.supplier_site_id AS supplierSiteId\n" +
					"      ,a.locale_review_id     AS localeReviewId\n" +
					"      ,a.qualification_id     AS qualificationId\n" +
					"      ,b.supplier_category_id AS supplierCategoryId\n" +
					"      ,b.category_id          AS categoryId\n" +
					"      ,splrs.selected_flag    AS selectedFlag\n" +
					"FROM   srm_pos_locale_review_sites splrs\n" +
					"      ,srm_pos_locale_reviews      a\n" +
					"LEFT   JOIN srm_pos_locale_review_cates b\n" +
					"ON     b.locale_review_id = a.locale_review_id\n" +
					"WHERE  splrs.locale_review_id = a.locale_review_id\n" +
					"AND    a.locale_review_result = 'PASS'\n" +
					"AND    splrs.selected_flag = 'Y'\n";

	// 现场考察分页查询SQL
	public static String PAGE_SQL =
					"SELECT t1.locale_review_id            AS localeReviewId\n" +
					"      ,t1.investigation_plan_lines_id AS investigationPlanLinesId\n" +
					"      ,t1.locale_review_number        AS localeReviewNumber\n" +
					"      ,t2.supplier_name               AS supplierName\n" +
					"      ,t1.locale_review_status        AS localeReviewStatus\n" +
					"      ,t4.meaning                     AS localeReviewStatusStr\n" +
					"      ,t1.scene_type                  AS sceneType\n" +
					"      ,t5.meaning                     AS sceneTypeStr\n" +
					"      ,t2.supplier_id                 AS supplierId\n" +
					"      ,t1.qualification_id            AS qualificationId\n" +
					"      ,t1.created_by                  AS createdBy\n" +
					"      ,t3.user_full_name              AS createName\n" +
					"      ,t1.creation_date               AS creationDate\n" +
					"FROM   srm_pos_locale_reviews t1\n" +
					"LEFT   JOIN saaf_users t3\n" +
					"ON     t3.user_id = t1.created_by\n" +
					"LEFT   JOIN saaf_lookup_values t4\n" +
					"ON     t4.lookup_code = t1.locale_review_status\n" +
					"AND    t4.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values t5\n" +
					"ON     t5.lookup_code = t1.scene_type\n" +
					"AND    t5.lookup_type = 'POS_SCENE_TYPE', srm_pos_supplier_info t2\n" +
					"WHERE  t1.supplier_id = t2.supplier_id\n";

	// 现场考察查询SQL
	public static String LOCALE_REVIEW_SQL =
					"SELECT t1.locale_review_id            AS localeReviewId\n" +
					"      ,t1.locale_review_number        AS localeReviewNumber\n" +
					"      ,t1.investigation_plan_lines_id AS investigationPlanLinesId\n" +
					"      ,ipl.investigation_plan_id      AS investigationPlanId\n" +
					"      ,ip.plan_number                 AS planNumber\n" +
					"      ,t1.supplier_id                 AS supplierId\n" +
					"      ,t1.scene_type                  AS sceneType\n" +
					"      ,t1.qualification_id            AS qualificationId\n" +
					"      ,t1.locale_review_status        AS localeReviewStatus\n" +
					"      ,t1.locale_review_result        AS localeReviewResult\n" +
					"      ,t1.review_date                 AS reviewDate\n" +
					"      ,t1.review_member               AS reviewMember\n" +
					"      ,t1.supplier_member             AS supplierMember\n" +
					"      ,t1.supply_flag                 AS supplyFlag\n" +
					"      ,t1.description                 AS description\n" +
					"      ,t1.file_id                     AS fileId\n" +
					"      ,t2.supplier_number             AS supplierNumber\n" +
					"      ,t2.supplier_name               AS supplierName\n" +
					"      ,t5.meaning                     AS localeReviewStatusStr\n" +
					"      ,t6.meaning                     AS sceneTypeStr\n" +
					"      ,t7.meaning                     AS supplyFlagStr\n" +
					"      ,t4.qualification_number        AS qualificationNumber\n" +
					"      ,t1.created_by                  AS createdBy\n" +
					"      ,t3.user_full_name              AS createName\n" +
					"      ,t1.creation_date               AS creationDate\n" +
					"      ,rf1.file_name                  AS fileName\n" +
					"      ,rf1.access_path                AS accessPath\n" +
					"FROM   srm_pos_locale_reviews t1\n" +
					"LEFT   JOIN saaf_users t3\n" +
					"ON     t3.user_id = t1.created_by\n" +
					"LEFT   JOIN srm_pos_qualification_info t4\n" +
					"ON     t4.qualification_id = t1.qualification_id\n" +
					"LEFT   JOIN saaf_lookup_values t5\n" +
					"ON     t5.lookup_code = t1.locale_review_status\n" +
					"AND    t5.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values t6\n" +
					"ON     t6.lookup_code = t1.scene_type\n" +
					"AND    t6.lookup_type = 'POS_SCENE_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values t7\n" +
					"ON     t7.lookup_code = t1.scene_type\n" +
					"AND    t7.lookup_type = 'YES_NO'\n" +
					"LEFT   JOIN saaf_base_result_file rf1\n" +
					"ON     rf1.file_id = t1.file_id\n" +
					"LEFT   JOIN srm_pos_investigation_plan_lines ipl\n" +
					"ON     ipl.investigation_plan_lines_id = t1.investigation_plan_lines_id\n" +
					"AND    ipl.supplier_id = t1.supplier_id\n" +
					"LEFT   JOIN srm_pos_investigation_plan ip\n" +
					"ON     ip.investigation_plan_id = ipl.investigation_plan_id,\n" +
					" srm_pos_supplier_info t2\n" +
					"WHERE  t1.supplier_id = t2.supplier_id\n";

	// 现场考察查询SQL
	public static String LOCALE_REVIEW_SQL_NEW = "SELECT\r\n" + //
			"  t1.locale_review_id     AS localeReviewId,\r\n" + //
			"  t1.locale_review_number AS localeReviewNumber,\r\n" + //
			"  t1.supplier_id          AS supplierId,\r\n" + //
			"  t1.scene_type           AS sceneType,\r\n" + //
			"  t1.qualification_id     AS qualificationId,\r\n" + //
			"  t1.locale_review_status AS localeReviewStatus,\r\n" + //
			"  t1.locale_review_result AS localeReviewResult,\r\n" + //
			"  t1.review_date          AS reviewDate,\r\n" + //
			"  t1.review_member        AS reviewMember,\r\n" + //
			"  t1.supplier_member      AS supplierMember,\r\n" + //
			"  t1.supply_flag          AS supplyFlag,\r\n" + //
			"  t1.description          AS description,\r\n" + //
			"  t1.file_id              AS fileId,\r\n" + //
			"  t2.supplier_number      AS supplierNumber,\r\n" + //
			"  t2.supplier_name        AS supplierName,\r\n" + //
			"  t5.meaning              AS localeReviewStatusStr,\r\n" + //
			"  t6.meaning              AS sceneTypeStr,\r\n" + //
			"  t7.meaning              AS supplyFlagStr,\r\n" + //
			"  t4.qualification_number AS qualificationNumber,\r\n" + //
			"  t1.created_by           AS createdBy,\r\n" + //
			"  t3.user_full_name       AS createName,\r\n" + //
			"  t1.creation_date        AS creationDate,\r\n" + //
			"  ipl.investigation_plan_id investigationPlanId,\r\n" + //
			"  ip.plan_number planNumber,\n" + //
			"  rf1.file_Name AS fileName,\r\n" + //
			"  rf1.access_Path AS accessPath\r\n" + //
			"FROM srm_pos_locale_reviews t1\r\n" + //
			"  LEFT JOIN srm_pos_supplier_info t2\r\n" + //
			"    ON t1.supplier_id = t2.supplier_id\r\n" + //
			"  LEFT JOIN saaf_users t3\r\n" + //
			"    ON t3.user_id = t1.created_by\r\n" + //
			"  LEFT JOIN srm_pos_qualification_info t4\r\n" + //
			"    ON t1.qualification_id = t4.qualification_id\r\n" + //
			"  LEFT JOIN saaf_lookup_values t5\r\n" + //
			"    ON t5.lookup_type = 'POS_APPROVAL_STATUS'\r\n" + //
			"      AND t1.locale_review_status = t5.lookup_code\r\n" + //
			"  LEFT JOIN saaf_lookup_values t6\r\n" + //
			"    ON t6.lookup_type = 'POS_SCENE_TYPE'\r\n" + //
			"      AND t1.scene_type = t6.lookup_code\r\n" + //
			"  LEFT JOIN saaf_lookup_values t7\r\n" + //
			"    ON t7.lookup_type = 'YES_NO'\r\n" + //
			"      AND t1.scene_type = t7.lookup_code\r\n" + //
			"  LEFT JOIN saaf_base_result_file rf1 ON rf1.file_id = t1.file_id\r\n" + //
			"	LEFT JOIN srm_pos_investigation_plan_lines ipl  ON ipl.supplier_id = t1.supplier_id\r\n" +
			"	LEFT JOIN srm_pos_investigation_plan ip  ON ipl.investigation_plan_id = ip.investigation_plan_id\r\n" + //
			"WHERE 1 = 1";//

	private Integer localeReviewId; // 现场考察ID
	private String localeReviewNumber; // 现场考察
	private Integer investigationPlanLinesId; //考察计划行ID，关联表:srm_pos_investigation_plan_lines
	private Integer supplierId; // 供应商ID，关联表:srm_pos_supplier_info
	private String sceneType; // 场景类型(POS_SCENE_TYPE)
	private Integer qualificationId; // 资质审查ID
	private String localeReviewStatus; // 现场考察状态(POS_APPROVAL_STATUS)
	private String localeReviewResult; // 现场考察结果(POS_EXAMINE_RESULT)
	@JSONField(format = "yyyy-MM-dd")
	private Date reviewDate; // 考察时间
	private String reviewMember; // 考察小组成员
	private String supplierMember; // 供方成员
	private String supplyFlag; // 是否已供货(Y/N)
	private String description; // 说明
	private Integer fileId; // 现场考察相关附件
	private Integer approvedBy; // (备用)审批人
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date approvedDate; // 审批时间
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd")
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

	private String supplierNumber;// 供应商编号
	private String supplierName;// 企业名称
	private String createName;// 创建用户全名
	private String localeReviewStatusStr;// POS_APPROVAL_STATUS快码含义
	private String sceneTypeStr;// POS_SCENE_TYPE快码含义
	private String supplyFlagStr;// yes_no快码描述
	private String qualificationNumber;// 资质审查单号
	private String selectedFlag;
	private Integer supplierCategoryId;
	private Integer categoryId;
	private String fileName;
	private String accessPath;
	private String planNumber;//计划考察单号
	private Integer investigationPlanId; //考察计划ID,关联表:srm_pos_investigation_plan

	public Integer getInvestigationPlanLinesId() {
		return investigationPlanLinesId;
	}

	public void setInvestigationPlanLinesId(Integer investigationPlanLinesId) {
		this.investigationPlanLinesId = investigationPlanLinesId;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public Integer getInvestigationPlanId() {
		return investigationPlanId;
	}

	public void setInvestigationPlanId(Integer investigationPlanId) {
		this.investigationPlanId = investigationPlanId;
	}

	public void setLocaleReviewId(Integer localeReviewId) {
		this.localeReviewId = localeReviewId;
	}

	public Integer getLocaleReviewId() {
		return localeReviewId;
	}

	public void setLocaleReviewNumber(String localeReviewNumber) {
		this.localeReviewNumber = localeReviewNumber;
	}

	public String getLocaleReviewNumber() {
		return localeReviewNumber;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public String getSceneType() {
		return sceneType;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setLocaleReviewStatus(String localeReviewStatus) {
		this.localeReviewStatus = localeReviewStatus;
	}

	public String getLocaleReviewStatus() {
		return localeReviewStatus;
	}

	public void setLocaleReviewResult(String localeReviewResult) {
		this.localeReviewResult = localeReviewResult;
	}

	public String getLocaleReviewResult() {
		return localeReviewResult;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewMember(String reviewMember) {
		this.reviewMember = reviewMember;
	}

	public String getReviewMember() {
		return reviewMember;
	}

	public void setSupplierMember(String supplierMember) {
		this.supplierMember = supplierMember;
	}

	public String getSupplierMember() {
		return supplierMember;
	}

	public void setSupplyFlag(String supplyFlag) {
		this.supplyFlag = supplyFlag;
	}

	public String getSupplyFlag() {
		return supplyFlag;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getLocaleReviewStatusStr() {
		return localeReviewStatusStr;
	}

	public void setLocaleReviewStatusStr(String localeReviewStatusStr) {
		this.localeReviewStatusStr = localeReviewStatusStr;
	}

	public String getSceneTypeStr() {
		return sceneTypeStr;
	}

	public void setSceneTypeStr(String sceneTypeStr) {
		this.sceneTypeStr = sceneTypeStr;
	}

	public String getSupplyFlagStr() {
		return supplyFlagStr;
	}

	public void setSupplyFlagStr(String supplyFlagStr) {
		this.supplyFlagStr = supplyFlagStr;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getQualificationNumber() {
		return qualificationNumber;
	}

	public void setQualificationNumber(String qualificationNumber) {
		this.qualificationNumber = qualificationNumber;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
}
