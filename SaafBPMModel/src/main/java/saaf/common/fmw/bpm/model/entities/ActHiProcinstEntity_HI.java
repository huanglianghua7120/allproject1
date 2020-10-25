package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActHiProcinstEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_procinst")
public class ActHiProcinstEntity_HI {
    private String id_;
    private String procInstId_;
    private String businessKey_;
    private String procDefId_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime_;
    private Long duration_;
    private String startUserId_;
    private String startActId_;
    private String endActId_;
    private String superProcessInstanceId_;
    private String deleteReason_;
    private String tenantId_;
    private String name_;

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = false, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}

	public void setBusinessKey_(String businessKey_) {
		this.businessKey_ = businessKey_;
	}

	@Column(name = "business_key_", nullable = true, length = 255)	
	public String getBusinessKey_() {
		return businessKey_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = false, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
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

	public void setStartUserId_(String startUserId_) {
		this.startUserId_ = startUserId_;
	}

	@Column(name = "start_user_id_", nullable = true, length = 255)	
	public String getStartUserId_() {
		return startUserId_;
	}

	public void setStartActId_(String startActId_) {
		this.startActId_ = startActId_;
	}

	@Column(name = "start_act_id_", nullable = true, length = 255)	
	public String getStartActId_() {
		return startActId_;
	}

	public void setEndActId_(String endActId_) {
		this.endActId_ = endActId_;
	}

	@Column(name = "end_act_id_", nullable = true, length = 255)	
	public String getEndActId_() {
		return endActId_;
	}

	public void setSuperProcessInstanceId_(String superProcessInstanceId_) {
		this.superProcessInstanceId_ = superProcessInstanceId_;
	}

	@Column(name = "super_process_instance_id_", nullable = true, length = 64)	
	public String getSuperProcessInstanceId_() {
		return superProcessInstanceId_;
	}

	public void setDeleteReason_(String deleteReason_) {
		this.deleteReason_ = deleteReason_;
	}

	@Column(name = "delete_reason_", nullable = true, length = 4000)	
	public String getDeleteReason_() {
		return deleteReason_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}
}
