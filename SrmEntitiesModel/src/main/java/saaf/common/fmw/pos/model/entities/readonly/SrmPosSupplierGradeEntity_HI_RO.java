package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author zhy Created by zhy on 2018/09/04.
 */
public class SrmPosSupplierGradeEntity_HI_RO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询供应商级别管理列表
	 */
	public static String QUERY_GRADE_LIST_SQL =
					"SELECT sg.grade_id gradeId\n" +
					"      ,sg.grade_code gradeCode\n" +
					"      ,sg.grade_status gradeStatus\n" +
					"      ,sg.grade_note gradeNote\n" +
					"      ,sg.approval_date approvalDate\n" +
					"      ,sg.evaluate_period evaluatePeriod\n" +
					"      ,sg.creation_date creationDate\n" +
					"      ,nvl(su.user_full_name, emp1.employee_name) founder\n" +
					"      ,nvl(sfu.user_full_name, emp2.employee_name) approver\n" +
					"      ,slv.meaning gradeStatusMean\n" +
					"      ,slv2.meaning evaluatePeriodMean\n" +
					"FROM   srm_pos_supplier_grade sg\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     su.user_id = sg.created_by\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     emp1.employee_id = su.employee_id\n" +
					"LEFT   JOIN saaf_users sfu\n" +
					"ON     sfu.user_id = sg.approval_employee_id\n" +
					"LEFT   JOIN saaf_employees emp2\n" +
					"ON     emp2.employee_id = sfu.employee_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = sg.grade_status\n" +
					"AND    slv.lookup_type = 'GRADE_ STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_code = sg.evaluate_period\n" +
					"AND    slv2.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
					"WHERE  1 = 1\n";

	/**
	 * 查询级别头信息
	 */
	public static String QUERY_GRADE_INFO_SQL="select sg.grade_id gradeId\r\n" + 
			",sg.grade_code gradeCode\r\n" + 
			",sg.grade_status gradeStatus\r\n" + 
			",sg.grade_note gradeNote\r\n" + 
			",sg.evaluate_period evaluatePeriod\r\n" + 
			",sg.creation_date creationDate\r\n" + 
			",sg.approval_date approvalDate\r\n" +
			",sg.evaluate_start_date evaluateStartDate\r\n" + 
			",sg.evaluate_end_date evaluateEndDate\r\n" + 
			",su.user_full_name founder\r\n" + 
			",slv.meaning gradeStatusMean\r\n" + 
			",slv2.meaning evaluatePeriodMean\r\n" + 
			",sg.file_id gradeFileId\r\n" + 
			",rf.file_Name gradeFileName\r\n" + 
			",rf.access_Path gradeFilePath\r\n" + 
			"from srm_pos_supplier_grade sg\r\n" + 
			"left join saaf_users su on sg.created_by = su.user_id \r\n" + 
			"left join saaf_lookup_values slv on sg.grade_status = slv.lookup_code\r\n" + 
			"and slv.lookup_type = 'GRADE_ STATUS'\r\n" + 
			"left join saaf_lookup_values slv2 on sg.evaluate_period = slv2.lookup_code\r\n" + 
			"and slv2.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\r\n" + 
			"left join saaf_base_result_file rf on sg.file_id = rf.file_id\r\n" + 
			"where 1 = 1";
	
	
	
	public static String QUERY_GRADE_LIST_IMPORT="";
	
	
	
	
	
	
	
	private Integer gradeId;
    private String gradeCode;
    private String gradeStatus;
    private String evaluatePeriod;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate;
    private String gradeNote;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private String founder;
    private String approver;
    private String gradeStatusMean;
    private String evaluatePeriodMean;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date evaluateStartDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date evaluateEndDate;
    
    private Integer gradeFileId;
    private String gradeFilePath;
    private String gradeFileName;
    
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeStatus() {
		return gradeStatus;
	}
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	public String getEvaluatePeriod() {
		return evaluatePeriod;
	}
	public void setEvaluatePeriod(String evaluatePeriod) {
		this.evaluatePeriod = evaluatePeriod;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getGradeNote() {
		return gradeNote;
	}
	public void setGradeNote(String gradeNote) {
		this.gradeNote = gradeNote;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getGradeStatusMean() {
		return gradeStatusMean;
	}
	public void setGradeStatusMean(String gradeStatusMean) {
		this.gradeStatusMean = gradeStatusMean;
	}
	public String getEvaluatePeriodMean() {
		return evaluatePeriodMean;
	}
	public void setEvaluatePeriodMean(String evaluatePeriodMean) {
		this.evaluatePeriodMean = evaluatePeriodMean;
	}
	public Date getEvaluateStartDate() {
		return evaluateStartDate;
	}
	public void setEvaluateStartDate(Date evaluateStartDate) {
		this.evaluateStartDate = evaluateStartDate;
	}
	public Date getEvaluateEndDate() {
		return evaluateEndDate;
	}
	public void setEvaluateEndDate(Date evaluateEndDate) {
		this.evaluateEndDate = evaluateEndDate;
	}
	
	public Integer getGradeFileId() {
		return gradeFileId;
	}
	public void setGradeFileId(Integer gradeFileId) {
		this.gradeFileId = gradeFileId;
	}
	public String getGradeFilePath() {
		return gradeFilePath;
	}
	public void setGradeFilePath(String gradeFilePath) {
		this.gradeFilePath = gradeFilePath;
	}
	public String getGradeFileName() {
		return gradeFileName;
	}
	public void setGradeFileName(String gradeFileName) {
		this.gradeFileName = gradeFileName;
	}
    

}
