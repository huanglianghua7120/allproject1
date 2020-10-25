package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActRuJobEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ru_job")
public class ActRuJobEntity_HI {
    private String id_;
    private Integer rev_;
    private String type_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lockExpTime_;
    private String lockOwner_;
    private Byte exclusive_;
    private String executionId_;
    private String processInstanceId_;
    private String procDefId_;
    private Integer retries_;
    private String exceptionStackId_;
    private String exceptionMsg_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date duedate_;
    private String repeat_;
    private String handlerType_;
    private String handlerCfg_;
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

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	@Column(name = "rev_", nullable = true, length = 11)	
	public Integer getRev_() {
		return rev_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = false, length = 255)	
	public String getType_() {
		return type_;
	}

	public void setLockExpTime_(Date lockExpTime_) {
		this.lockExpTime_ = lockExpTime_;
	}

	@Column(name = "lock_exp_time_", nullable = true, length = 3)	
	public Date getLockExpTime_() {
		return lockExpTime_;
	}

	public void setLockOwner_(String lockOwner_) {
		this.lockOwner_ = lockOwner_;
	}

	@Column(name = "lock_owner_", nullable = true, length = 255)	
	public String getLockOwner_() {
		return lockOwner_;
	}

	public void setExclusive_(Byte exclusive_) {
		this.exclusive_ = exclusive_;
	}

	@Column(name = "exclusive_", nullable = true, length = 1)	
	public Byte getExclusive_() {
		return exclusive_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	@Column(name = "execution_id_", nullable = true, length = 64)	
	public String getExecutionId_() {
		return executionId_;
	}

	public void setProcessInstanceId_(String processInstanceId_) {
		this.processInstanceId_ = processInstanceId_;
	}

	@Column(name = "process_instance_id_", nullable = true, length = 64)	
	public String getProcessInstanceId_() {
		return processInstanceId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = true, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setRetries_(Integer retries_) {
		this.retries_ = retries_;
	}

	@Column(name = "retries_", nullable = true, length = 11)	
	public Integer getRetries_() {
		return retries_;
	}

	public void setExceptionStackId_(String exceptionStackId_) {
		this.exceptionStackId_ = exceptionStackId_;
	}

	@Column(name = "exception_stack_id_", nullable = true, length = 64)	
	public String getExceptionStackId_() {
		return exceptionStackId_;
	}

	public void setExceptionMsg_(String exceptionMsg_) {
		this.exceptionMsg_ = exceptionMsg_;
	}

	@Column(name = "exception_msg_", nullable = true, length = 4000)	
	public String getExceptionMsg_() {
		return exceptionMsg_;
	}

	public void setDuedate_(Date duedate_) {
		this.duedate_ = duedate_;
	}

	@Column(name = "duedate_", nullable = true, length = 3)	
	public Date getDuedate_() {
		return duedate_;
	}

	public void setRepeat_(String repeat_) {
		this.repeat_ = repeat_;
	}

	@Column(name = "repeat_", nullable = true, length = 255)	
	public String getRepeat_() {
		return repeat_;
	}

	public void setHandlerType_(String handlerType_) {
		this.handlerType_ = handlerType_;
	}

	@Column(name = "handler_type_", nullable = true, length = 255)	
	public String getHandlerType_() {
		return handlerType_;
	}

	public void setHandlerCfg_(String handlerCfg_) {
		this.handlerCfg_ = handlerCfg_;
	}

	@Column(name = "handler_cfg_", nullable = true, length = 4000)	
	public String getHandlerCfg_() {
		return handlerCfg_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
