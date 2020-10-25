package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPosSampleTrialsEntity_HI_RO implements Serializable {

	private Integer trialId;
	private String trialsNumber;
	private Integer supplierId;
	private String sceneType;// 场景类型（业务类型）场景类型(POS_SCENE_TYPE)
	private Integer qualificationId;// 资质审查ID，关联表:srm_pos_qualification_info
	private String trialsStatus;// 状态(POS_APPROVAL_STATUS)
	private String trialsResult;// 样品试验结果(POS_EXAMINE_RESULT)
	private Integer sampleFileId;// 样品试验相关附件ID
	private String description;
	private Integer approvedBy;
	private String approvedName;
	@JSONField(format = "yyyy-MM-dd")
	private Date approvedDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;

	private String qualificationNumber;

	private Integer createdBy;
	private String createdName;
	private String userFullName;

	private String supplierName;
	private String supplierNumber;

	private String trialsStatusStr;
	private String sceneTypeStr;
	private String trialsResultStr;
	private String sampleFilePath;
	private String sampleFileName;

	private Integer trialCateId;
	private String bigCategoryCode;
	private Integer supplierCategoryId;
	private Integer categoryId;
	private String middleCategoryCode;
	private String smallCategoryCode;
	private String selectedFlag;
	private String bigCategoryMean;
	private String middleCategoryMean;
	private String smallCategoryMean;

	// 查询样品试验sql
	public static String QUERY_SAMPLETRIALS_SQL =
					"SELECT a.trial_id             AS trialId\n" +
					"      ,a.trials_number        AS trialsNumber\n" +
					"      ,a.supplier_id          AS supplierId\n" +
					"      ,b.supplier_name        AS supplierName\n" +
					"      ,b.supplier_number      AS supplierNumber\n" +
					"      ,a.trials_status        AS trialsStatus\n" +
					"      ,a.creation_date        AS creationDate\n" +
					"      ,a.created_by           AS createdBy\n" +
					"      ,c.user_full_name       AS userFullName\n" +
					"      ,a.scene_type           AS sceneType\n" +
					"      ,a.qualification_id     AS qualificationId\n" +
					"      ,h.qualification_number AS qualificationNumber\n" +
					"      ,a.trials_result        AS trialsResult\n" +
					"      ,a.sample_file_id       AS sampleFileId\n" +
					"      ,d.file_name            AS sampleFileName\n" +
					"      ,d.access_path          AS sampleFilePath\n" +
					"      ,a.description          AS description\n" +
					"      ,e.meaning              AS trialsStatusStr\n" +
					"      ,f.meaning              AS sceneTypeStr\n" +
					"      ,g.meaning              AS trialsResultStr\n" +
					"FROM   srm_pos_sample_trials a\n" +
					"LEFT   JOIN saaf_users c\n" +
					"ON     c.user_id = a.created_by\n" +
					"LEFT   JOIN saaf_base_result_file d\n" +
					"ON     d.file_id = a.sample_file_id\n" +
					"LEFT   JOIN saaf_lookup_values e\n" +
					"ON     e.lookup_code = a.trials_status\n" +
					"AND    e.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values f\n" +
					"ON     f.lookup_code = a.scene_type\n" +
					"AND    f.lookup_type = 'POS_SCENE_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values g\n" +
					"ON     g.lookup_code = a.trials_result\n" +
					"AND    g.lookup_type = 'POS_EXAMINE_RESULT'\n" +
					"LEFT   JOIN srm_pos_qualification_info h\n" +
					"ON     h.qualification_id = a.qualification_id, srm_pos_supplier_info b\n" +
					"WHERE  a.supplier_id = b.supplier_id\n";

//	public static String QUERY_SAMPLE_CATEGORY = "SELECT  \r\n" + "stc.trial_cate_id trialCateId \r\n"
//			+ ",stc.trial_id trialId  \r\n" + ",stc.small_category_code smallCategoryCode  \r\n"
//			+ ",stc.supplier_category_id supplierCategoryId \r\n" + ",stc.middle_category_code middleCategoryCode  \r\n"
//			+ ",stc.big_category_code bigCategoryCode\r\n" + ",stc.selected_flag selectedFlag \r\n"
//			+ ",slv.meaning smallCategoryMean  \r\n" + ",slv2.meaning middleCategoryMean  \r\n"
//			+ ",slv3.meaning bigCategoryMean  \r\n" + "FROM  \r\n" + "srm_pos_sample_trial_cates stc \r\n"
//			+ "LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = stc.small_category_code  \r\n"
//			+ "AND slv.lookup_type='BASE_SMALL_CATEGORY'  \r\n"
//			+ "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_code=stc.middle_category_code  \r\n"
//			+ "AND slv2.lookup_type='BASE_MIDDLE_CATEGORY'  \r\n"
//			+ "LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_code=stc.big_category_code  \r\n"
//			+ "AND slv3.lookup_type='BASE_BIG_CATEGORY'\r\n" + ",srm_pos_sample_trials st\r\n" + "WHERE 1=1  \r\n"
//			+ "AND stc.trial_id = st.trial_id";
//
//	public static String QUERY_USER_RESP = "SELECT \r\n" + "t.user_id\r\n" + "FROM\r\n" + "saaf_user_resp t,\r\n"
//			+ "saaf_responsibilitys r,\r\n" + "saaf_users u\r\n" + "WHERE\r\n"
//			+ "t.responsibility_id = r.responsibility_id\r\n" + "AND r.responsibility_key = 'FIN_RESP'\r\n"
//			+ "AND t.user_id = u.user_id\r\n" + "AND SYSDATE() BETWEEN t.start_date_active\r\n"
//			+ "AND IFNULL(t.end_date_active,SYSDATE())\r\n" + "GROUP BY t.user_id\r\n";

	// 返回样品试验ID集合
	public static String QUERY_TRIAL_ID_SQL =
					"SELECT a.qualification_id     AS qualificationId\n" +
					"      ,b.trial_cate_id        AS trialCateId\n" +
					"      ,b.supplier_category_id AS supplierCategoryId\n" +
					"      ,b.category_id          AS categoryId\n" +
					"      ,b.selected_flag        AS selectedFlag\n" +
					"FROM   srm_pos_sample_trials      a\n" +
					"      ,srm_pos_sample_trial_cates b\n" +
					"WHERE  a.trial_id = b.trial_id\n" +
					"AND    b.selected_flag = 'Y'\n" +
					"AND    a.trials_status = 'APPROVED'\n";

	public Integer getTrialId() {
		return trialId;
	}

	public void setTrialId(Integer trialId) {
		this.trialId = trialId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getTrialsNumber() {
		return trialsNumber;
	}

	public void setTrialsNumber(String trialsNumber) {
		this.trialsNumber = trialsNumber;
	}

	public String getTrialsStatus() {
		return trialsStatus;
	}

	public void setTrialsStatus(String trialsStatus) {
		this.trialsStatus = trialsStatus;
	}

	public String getTrialsResult() {
		return trialsResult;
	}

	public void setTrialsResult(String trialsResult) {
		this.trialsResult = trialsResult;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getTrialsStatusStr() {
		return trialsStatusStr;
	}

	public void setTrialsStatusStr(String trialsStatusStr) {
		this.trialsStatusStr = trialsStatusStr;
	}

	public Integer getTrialCateId() {
		return trialCateId;
	}

	public void setTrialCateId(Integer trialCateId) {
		this.trialCateId = trialCateId;
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

	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}

	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
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

	public Integer getSampleFileId() {
		return sampleFileId;
	}

	public void setSampleFileId(Integer sampleFileId) {
		this.sampleFileId = sampleFileId;
	}

	public String getSampleFilePath() {
		return sampleFilePath;
	}

	public void setSampleFilePath(String sampleFilePath) {
		this.sampleFilePath = sampleFilePath;
	}

	public String getSampleFileName() {
		return sampleFileName;
	}

	public void setSampleFileName(String sampleFileName) {
		this.sampleFileName = sampleFileName;
	}

	public String getSceneType() {
		return sceneType;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSceneTypeStr() {
		return sceneTypeStr;
	}

	public void setSceneTypeStr(String sceneTypeStr) {
		this.sceneTypeStr = sceneTypeStr;
	}

	public String getTrialsResultStr() {
		return trialsResultStr;
	}

	public void setTrialsResultStr(String trialsResultStr) {
		this.trialsResultStr = trialsResultStr;
	}

	public String getQualificationNumber() {
		return qualificationNumber;
	}

	public void setQualificationNumber(String qualificationNumber) {
		this.qualificationNumber = qualificationNumber;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
