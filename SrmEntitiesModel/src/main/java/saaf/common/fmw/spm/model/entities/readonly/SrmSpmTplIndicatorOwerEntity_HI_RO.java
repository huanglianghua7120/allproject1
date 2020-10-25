package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;

public class SrmSpmTplIndicatorOwerEntity_HI_RO implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String COUNT_QUERY_FLAG ="SELECT ow.`POSITION_ID` positionId,ow.`TPL_INDICATOR_OWER_ID` tplIndicatorOwerId,slv.`lookup_code` postCode,slv.`meaning` postName FROM srm_spm_tpl_indicator_ower ow LEFT JOIN saaf_lookup_values slv ON ow.`POSITION_ID`=slv.`lookup_code` AND slv.`lookup_type`='EMPLOYEE_POST' where 1=1";
	public static final String COUNT_MESSAGE_FLAG ="SELECT  ower.`TPL_INDICATOR_OWER_ID` tplIndicatorOwerId, ower.`POSITION_CODE` postCode, ower.`POSITION_ID` positionId, su.`user_id` userId, su.`user_name` userName  FROM srm_spm_tpl_indicator_ower ower,`saaf_employees` eye ,`saaf_users` su WHERE ower.`POSITION_ID` = eye.quarters_code AND eye.`employee_id` = su.`employee_id` ";
	private Integer tplIndicatorOwerId; // ID
	private Integer tplIndicatorId; // 模板指标ID
	private Integer departmentId; // 部门ID（备用）
	private Integer positionId; // 职位ID
	private Integer employeeId; // 员工ID（备用），关联表：SAAF_EMPLOYEES
	private String description; // 说明、备注
	private String postCode;
	private String postName;
	
	private Integer userId;
	private String userName;
	public Integer getTplIndicatorOwerId() {
		return tplIndicatorOwerId;
	}
	public void setTplIndicatorOwerId(Integer tplIndicatorOwerId) {
		this.tplIndicatorOwerId = tplIndicatorOwerId;
	}
	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}
	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
