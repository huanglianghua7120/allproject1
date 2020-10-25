package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
public class SrmPosSupplierGradeLinesEntity_HI_RO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询级别行信息
	 */
	public static String QUERY_GRADE_LINES_SQL="select gl.grade_line_id gradeLineId\r\n" + 
			",gl.grade_id gradeId\r\n" + 
			",gl.supplier_id supplierId\r\n" + 
			",gl.comprehensive_score comprehensiveScore\r\n" + 
			",gl.adjust_grade adjustGrade\r\n" + 
			",gl.start_date_active startDateActive\r\n" + 
			",gl.end_date_active endDateActive\r\n" + 
			",gl.creation_date creationDate\r\n" + 
			",gl.line_note lineNote\r\n" + 
			",gl.last_update_date lastUpdateDate\r\n" + 
			",gl.version_num versionNum\r\n" + 
			",gl.created_by createdBy\r\n" + 
			",gl.creation_date creationDate\r\n" + 
			",gl.last_updated_by lastUpdatedBy\r\n" + 
			",gl.last_update_date lastUpdateDate\r\n" + 
			",gl.last_update_login lastUpdateLogin\r\n" +
			",su.user_full_name founder\r\n" + 
			",sfu.user_full_name lastModifier\r\n" + 
			",slv.meaning adjustGradeMean\r\n" + 
			",si.supplier_name supplierName\r\n" + 
			",si.supplier_number supplierNumber\r\n" + 
			"from \r\n" + 
			"srm_pos_supplier_grade_lines gl\r\n" + 
			"left join saaf_users su on gl.created_by = su.user_id\r\n" + 
			"left join saaf_users sfu on gl.last_updated_by = sfu.user_id\r\n" + 
			"left join saaf_lookup_values slv on gl.adjust_grade = slv.lookup_code\r\n" + 
			"and slv.lookup_type = 'GRADE' \r\n" + 
			"left join srm_pos_supplier_info si on gl.supplier_id = si.supplier_id\r\n" + 
			"where 1 = 1";
	
	/**
	 *查询级别行导出数据 
	 */
	public static String QUERY_GRADE_LINES_LIST_IMPORT =
					"SELECT\n" +
					"  gl.start_date_active startDateActive,\n" +
					"  gl.end_date_active endDateActive,\n" +
					"  gl.adjust_grade adjustGrade,\n" +
					"  si.supplier_number supplierNumber,\n" +
					"  si.supplier_name supplierName,\n" +
					"  slv.meaning adjustGradeMean\n" +
					"FROM\n" +
					"  srm_pos_supplier_grade_lines gl\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = gl.adjust_grade\n" +
					"    AND slv.lookup_type = 'GRADE',\n" +
					"  srm_pos_supplier_info si\n" +
					"WHERE gl.supplier_id = si.supplier_id";

	/**
	 * 查询历史级别行数据
	 */
	public static String QUERY_GRADE_LINES_HISTORY_LIST =
					"SELECT\n" +
					"  psg.grade_id AS gradeId,\n" +
					"  psg.grade_code AS gradeCode,\n" +
					"  psg.approval_date AS approvalDate,\n" +
					"  psl.grade_line_id AS gradeLineId,\n" +
					"  psl.supplier_id AS supplierId,\n" +
					"  psl.line_note AS lineNote,\n" +
					"  psl.start_date_active AS startDateActive,\n" +
					"  psl.end_date_active AS endDateActive,\n" +
					"  slv.meaning AS adjustGradeMean\n" +
					"FROM\n" +
					"  srm_pos_supplier_grade psg,\n" +
					"  srm_pos_supplier_grade_lines psl\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = psl.adjust_grade\n" +
					"    AND slv.lookup_type = 'GRADE'\n" +
					"WHERE psg.grade_id = psl.grade_id\n" +
					"  AND psl.supplier_id = :supplierId\n" +
					"  AND psl.grade_line_id <= :gradeLineId\n" +
					"  AND psg.grade_status = 'APPROVED'";

	/**
	 * 根据供应商名称查找供应商id
	 */
	public static String QUERY_SUPPLIER_ID="select s.supplier_id supplierId from srm_pos_supplier_info s where s.supplier_name = :supplierName";
	
	/**
	 * 查询数据是否重复
	 */
	public static String QUERY_COUNT="SELECT count(*) FROM srm_pos_supplier_grade_lines gl where (gl.grade_id = :gradeId and gl.supplier_id = :supplierId) ";
	
	
	private Integer gradeLineId;
    private Integer gradeId;
    private Integer supplierId;
    private BigDecimal comprehensiveScore;
    private String adjustGrade;
    private String lineNote;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private String founder;
    private String lastModifier;
    private String adjustGradeMean;
    private String supplierName;
    private String supplierNumber;
    
    private Integer versionNum;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    private Integer lastUpdateLogin;
    
    private Integer historyId;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate;

	private Integer count;
    
    
    
	public Integer getGradeLineId() {
		return gradeLineId;
	}
	public void setGradeLineId(Integer gradeLineId) {
		this.gradeLineId = gradeLineId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public BigDecimal getComprehensiveScore() {
		return comprehensiveScore;
	}
	public void setComprehensiveScore(BigDecimal comprehensiveScore) {
		this.comprehensiveScore = comprehensiveScore;
	}
	public String getAdjustGrade() {
		return adjustGrade;
	}
	public void setAdjustGrade(String adjustGrade) {
		this.adjustGrade = adjustGrade;
	}
	public String getLineNote() {
		return lineNote;
	}
	public void setLineNote(String lineNote) {
		this.lineNote = lineNote;
	}
	public Date getStartDateActive() {
		return startDateActive;
	}
	public void setStartDateActive(Date startDateActive) {
		this.startDateActive = startDateActive;
	}
	public Date getEndDateActive() {
		return endDateActive;
	}
	public void setEndDateActive(Date endDateActive) {
		this.endDateActive = endDateActive;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getLastModifier() {
		return lastModifier;
	}
	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}
	public String getAdjustGradeMean() {
		return adjustGradeMean;
	}
	public void setAdjustGradeMean(String adjustGradeMean) {
		this.adjustGradeMean = adjustGradeMean;
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
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
