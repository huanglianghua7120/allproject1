package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActHiActinstEntity_HI Entity Object
 * Wed Aug 23 11:33:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_actinst")
public class ActHiActinstEntity_HI {
    private String id_;
    private String procDefId_;
    private String procInstId_;
    private String executionId_;
    private String actId_;
    private String taskId_;
    private String callProcInstId_;
    private String actName_;
    private String actType_;
    private String assignee_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime_;
    private Long duration_;
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

	@Column(name = "proc_def_id_", nullable = false, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = false, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	@Column(name = "execution_id_", nullable = false, length = 64)	
	public String getExecutionId_() {
		return executionId_;
	}

	public void setActId_(String actId_) {
		this.actId_ = actId_;
	}

	@Column(name = "act_id_", nullable = false, length = 255)	
	public String getActId_() {
		return actId_;
	}

	public void setTaskId_(String taskId_) {
		this.taskId_ = taskId_;
	}

	@Column(name = "task_id_", nullable = true, length = 64)	
	public String getTaskId_() {
		return taskId_;
	}

	public void setCallProcInstId_(String callProcInstId_) {
		this.callProcInstId_ = callProcInstId_;
	}

	@Column(name = "call_proc_inst_id_", nullable = true, length = 64)	
	public String getCallProcInstId_() {
		return callProcInstId_;
	}

	public void setActName_(String actName_) {
		this.actName_ = actName_;
	}

	@Column(name = "act_name_", nullable = true, length = 255)	
	public String getActName_() {
		return actName_;
	}

	public void setActType_(String actType_) {
		this.actType_ = actType_;
	}

	@Column(name = "act_type_", nullable = false, length = 255)	
	public String getActType_() {
		return actType_;
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

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
