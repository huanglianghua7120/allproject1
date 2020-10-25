package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActRuTaskEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ru_task")
public class ActRuTaskEntity_HI {
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

//	public void setAct_ru_identitylinkEntity_HI(List<ActRuTaskEntity_HI> Act_ru_identitylinkEntity_HI) {
//		this.Act_ru_identitylinkEntity_HI = Act_ru_identitylinkEntity_HI;
//	}
//
//	@Column(name = "Act_ru_identitylinkEntity_HI")	
//	public List<ActRuTaskEntity_HI> getAct_ru_identitylinkEntity_HI() {
//		return Act_ru_identitylinkEntity_HI;
//	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	@Column(name = "rev_", nullable = true, length = 11)	
	public Integer getRev_() {
		return rev_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	@Column(name = "execution_id_", nullable = true, length = 64)	
	public String getExecutionId_() {
		return executionId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = true, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = true, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setParentTaskId_(String parentTaskId_) {
		this.parentTaskId_ = parentTaskId_;
	}

	@Column(name = "parent_task_id_", nullable = true, length = 64)	
	public String getParentTaskId_() {
		return parentTaskId_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}

	@Column(name = "description_", nullable = true, length = 4000)	
	public String getDescription_() {
		return description_;
	}

	public void setTaskDefKey_(String taskDefKey_) {
		this.taskDefKey_ = taskDefKey_;
	}

	@Column(name = "task_def_key_", nullable = true, length = 255)	
	public String getTaskDefKey_() {
		return taskDefKey_;
	}

	public void setOwner_(String owner_) {
		this.owner_ = owner_;
	}

	@Column(name = "owner_", nullable = true, length = 255)	
	public String getOwner_() {
		return owner_;
	}

	public void setAssignee_(String assignee_) {
		this.assignee_ = assignee_;
	}

	@Column(name = "assignee_", nullable = true, length = 255)	
	public String getAssignee_() {
		return assignee_;
	}

	public void setDelegation_(String delegation_) {
		this.delegation_ = delegation_;
	}

	@Column(name = "delegation_", nullable = true, length = 64)	
	public String getDelegation_() {
		return delegation_;
	}

	public void setPriority_(Integer priority_) {
		this.priority_ = priority_;
	}

	@Column(name = "priority_", nullable = true, length = 11)	
	public Integer getPriority_() {
		return priority_;
	}

	public void setCreateTime_(Date createTime_) {
		this.createTime_ = createTime_;
	}

	@Column(name = "create_time_", nullable = true, length = 3)	
	public Date getCreateTime_() {
		return createTime_;
	}

	public void setDueDate_(Date dueDate_) {
		this.dueDate_ = dueDate_;
	}

	@Column(name = "due_date_", nullable = true, length = 3)	
	public Date getDueDate_() {
		return dueDate_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	@Column(name = "category_", nullable = true, length = 255)	
	public String getCategory_() {
		return category_;
	}

	public void setSuspensionState_(Integer suspensionState_) {
		this.suspensionState_ = suspensionState_;
	}

	@Column(name = "suspension_state_", nullable = true, length = 11)	
	public Integer getSuspensionState_() {
		return suspensionState_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}

	public void setFormKey_(String formKey_) {
		this.formKey_ = formKey_;
	}

	@Column(name = "form_key_", nullable = true, length = 255)	
	public String getFormKey_() {
		return formKey_;
	}
}
