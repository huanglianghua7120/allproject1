package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActHiTaskinstEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_taskinst")
public class ActHiTaskinstEntity_HI {
    private String id_;
    private String procDefId_;
    private String taskDefKey_;
    private String procInstId_;
    private String executionId_;
    private String name_;
    private String parentTaskId_;
    private String description_;
    private String owner_;
    private String assignee_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date claimTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime_;
    private Long duration_;
    private String deleteReason_;
    private Integer priority_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate_;
    private String formKey_;
    private String category_;
    private String tenantId_;

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = true, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setTaskDefKey_(String taskDefKey_) {
		this.taskDefKey_ = taskDefKey_;
	}

	@Column(name = "task_def_key_", nullable = true, length = 255)	
	public String getTaskDefKey_() {
		return taskDefKey_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = true, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	@Column(name = "execution_id_", nullable = true, length = 64)	
	public String getExecutionId_() {
		return executionId_;
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

	public void setStartTime_(Date startTime_) {
		this.startTime_ = startTime_;
	}

	@Column(name = "start_time_", nullable = false, length = 3)	
	public Date getStartTime_() {
		return startTime_;
	}

	public void setClaimTime_(Date claimTime_) {
		this.claimTime_ = claimTime_;
	}

	@Column(name = "claim_time_", nullable = true, length = 3)	
	public Date getClaimTime_() {
		return claimTime_;
	}

	public void setEndTime_(Date endTime_) {
		this.endTime_ = endTime_;
	}

	@Column(name = "end_time_", nullable = true, length = 3)	
	public Date getEndTime_() {
		return endTime_;
	}

	public void setDuration_(Long duration_) {
		this.duration_ = duration_;
	}

	@Column(name = "duration_", nullable = true, length = 20)	
	public Long getDuration_() {
		return duration_;
	}

	public void setDeleteReason_(String deleteReason_) {
		this.deleteReason_ = deleteReason_;
	}

	@Column(name = "delete_reason_", nullable = true, length = 4000)	
	public String getDeleteReason_() {
		return deleteReason_;
	}

	public void setPriority_(Integer priority_) {
		this.priority_ = priority_;
	}

	@Column(name = "priority_", nullable = true, length = 11)	
	public Integer getPriority_() {
		return priority_;
	}

	public void setDueDate_(Date dueDate_) {
		this.dueDate_ = dueDate_;
	}

	@Column(name = "due_date_", nullable = true, length = 3)	
	public Date getDueDate_() {
		return dueDate_;
	}

	public void setFormKey_(String formKey_) {
		this.formKey_ = formKey_;
	}

	@Column(name = "form_key_", nullable = true, length = 255)	
	public String getFormKey_() {
		return formKey_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	@Column(name = "category_", nullable = true, length = 255)	
	public String getCategory_() {
		return category_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
