package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActEvtLogEntity_HI Entity Object
 * Wed Aug 23 11:33:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_evt_log")
public class ActEvtLogEntity_HI {
    private Long logNr_;
    private String type_;
    private String procDefId_;
    private String procInstId_;
    private String executionId_;
    private String taskId_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp_;
    private String userId_;
    private String data_;
    private String lockOwner_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lockTime_;
    private Byte isProcessed_;

	public void setLogNr_(Long logNr_) {
		this.logNr_ = logNr_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "log_nr_", nullable = false, length = 20)	
	public Long getLogNr_() {
		return logNr_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 64)	
	public String getType_() {
		return type_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = true, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
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

	public void setTaskId_(String taskId_) {
		this.taskId_ = taskId_;
	}

	@Column(name = "task_id_", nullable = true, length = 64)	
	public String getTaskId_() {
		return taskId_;
	}

	public void setTimeStamp_(Date timeStamp_) {
		this.timeStamp_ = timeStamp_;
	}

	@Column(name = "time_stamp_", nullable = false, length = 3)	
	public Date getTimeStamp_() {
		return timeStamp_;
	}

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	@Column(name = "user_id_", nullable = true, length = 255)	
	public String getUserId_() {
		return userId_;
	}

	public void setData_(String data_) {
		this.data_ = data_;
	}

	@Column(name = "data_", nullable = true, length = 0)	
	public String getData_() {
		return data_;
	}

	public void setLockOwner_(String lockOwner_) {
		this.lockOwner_ = lockOwner_;
	}

	@Column(name = "lock_owner_", nullable = true, length = 255)	
	public String getLockOwner_() {
		return lockOwner_;
	}

	public void setLockTime_(Date lockTime_) {
		this.lockTime_ = lockTime_;
	}

	@Column(name = "lock_time_", nullable = true, length = 3)	
	public Date getLockTime_() {
		return lockTime_;
	}

	public void setIsProcessed_(Byte isProcessed_) {
		this.isProcessed_ = isProcessed_;
	}

	@Column(name = "is_processed_", nullable = true, length = 4)	
	public Byte getIsProcessed_() {
		return isProcessed_;
	}
}
