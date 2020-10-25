package saaf.common.fmw.bpm.model.entities.readonly;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;

public class SaafActRuTaskEntity_HI_RO {
	
	public static final String QUERY_MY_TASK_SQL = " SELECT " +
		"  actrt.ID_, " +
		"  actrt.REV_, " +
		"  actrt.EXECUTION_ID_, " +
		"  actrt.PROC_INST_ID_, " +
		"  actrt.PROC_DEF_ID_, " +
		"  actrt.NAME_, " +
		"  actrt.PARENT_TASK_ID_, " +
		"  actrt.DESCRIPTION_, " +
		"  actrt.TASK_DEF_KEY_, " +
		"  actrt.OWNER_, " +
		"  actrt.FORM_KEY_, " +
		"  actrt.SUSPENSION_STATE_, " +
		"  actrt.TENANT_ID_, " +
		"  actrt.CATEGORY_, " +
		"  actrt.DUE_DATE_, " +
		"  actrt.CREATE_TIME_, " +
		"  actrt.PRIORITY_, " +
		"  actrt.DELEGATION_, " +
		"  actrt.ASSIGNEE_, " +
		"  saib.SAIB_ID_ , " +
		"  saib.SAIB_BUSINESS_ID_ , " +
		"  saib.SAIB_TASK_TITLE_ " +
		" FROM " +
		"  act_ru_task actrt , " +
		"  saaf_act_instance_business saib " +
		" WHERE " +
		"  actrt.PROC_INST_ID_ = saib.SAIB_INSTANCE_ID_ " +
		" AND actrt.ID_ = saib.SAIB_TASK_ID_ ";
	
	private String id_;
	private Integer rev_;
	private String executionId_;
	private String procInstId_;
	private String procDefId_;
	private String name_;
	private String parentTaskId_;
	private String description_;
	private String taskDefKey_;
	private String owner_;
	private String assignee_;
	private String delegation_;
	private Integer priority_;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime_;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date dueDate_;
	private String category_;
	private Integer suspensionState_;
	private String tenantId_;
	private String formKey_;
	private Integer saibId;
	private String saibBusinessId;
	private String saibTaskTitle;

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public Integer getRev_() {
		return rev_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	public String getExecutionId_() {
		return executionId_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	public String getProcInstId_() {
		return procInstId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getParentTaskId_() {
		return parentTaskId_;
	}

	public void setParentTaskId_(String parentTaskId_) {
		this.parentTaskId_ = parentTaskId_;
	}

	public String getDescription_() {
		return description_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}

	public String getTaskDefKey_() {
		return taskDefKey_;
	}

	public void setTaskDefKey_(String taskDefKey_) {
		this.taskDefKey_ = taskDefKey_;
	}

	public String getOwner_() {
		return owner_;
	}

	public void setOwner_(String owner_) {
		this.owner_ = owner_;
	}

	public String getAssignee_() {
		return assignee_;
	}

	public void setAssignee_(String assignee_) {
		this.assignee_ = assignee_;
	}

	public String getDelegation_() {
		return delegation_;
	}

	public void setDelegation_(String delegation_) {
		this.delegation_ = delegation_;
	}

	public Integer getPriority_() {
		return priority_;
	}

	public void setPriority_(Integer priority_) {
		this.priority_ = priority_;
	}

	public Date getCreateTime_() {
		return createTime_;
	}

	public void setCreateTime_(Date createTime_) {
		this.createTime_ = createTime_;
	}

	public Date getDueDate_() {
		return dueDate_;
	}

	public void setDueDate_(Date dueDate_) {
		this.dueDate_ = dueDate_;
	}

	public String getCategory_() {
		return category_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	public Integer getSuspensionState_() {
		return suspensionState_;
	}

	public void setSuspensionState_(Integer suspensionState_) {
		this.suspensionState_ = suspensionState_;
	}

	public String getTenantId_() {
		return tenantId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	public String getFormKey_() {
		return formKey_;
	}

	public void setFormKey_(String formKey_) {
		this.formKey_ = formKey_;
	}

	public Integer getSaibId() {
		return saibId;
	}

	public void setSaibId(Integer saibId) {
		this.saibId = saibId;
	}

	public String getSaibBusinessId() {
		return saibBusinessId;
	}

	public void setSaibBusinessId(String saibBusinessId) {
		this.saibBusinessId = saibBusinessId;
	}

	public String getSaibTaskTitle() {
		return saibTaskTitle;
	}

	public void setSaibTaskTitle(String saibTaskTitle) {
		this.saibTaskTitle = saibTaskTitle;
	}

	// public void setAct_ru_identitylinkEntity_HI(List<ActRuTaskEntity_HI>
	// Act_ru_identitylinkEntity_HI) {
	// this.Act_ru_identitylinkEntity_HI = Act_ru_identitylinkEntity_HI;
	// }
	//
	// @Column(name = "Act_ru_identitylinkEntity_HI")
	// public List<ActRuTaskEntity_HI> getAct_ru_identitylinkEntity_HI() {
	// return Act_ru_identitylinkEntity_HI;
	// }

}
