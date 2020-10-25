package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActRuEventSubscrEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ru_event_subscr")
public class ActRuEventSubscrEntity_HI {
    private String id_;
    private Integer rev_;
    private String eventType_;
    private String eventName_;
    private String executionId_;
    private String procInstId_;
    private String activityId_;
    private String configuration_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date created_;
    private String procDefId_;
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

	public void setEventType_(String eventType_) {
		this.eventType_ = eventType_;
	}

	@Column(name = "event_type_", nullable = false, length = 255)	
	public String getEventType_() {
		return eventType_;
	}

	public void setEventName_(String eventName_) {
		this.eventName_ = eventName_;
	}

	@Column(name = "event_name_", nullable = true, length = 255)	
	public String getEventName_() {
		return eventName_;
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

	public void setActivityId_(String activityId_) {
		this.activityId_ = activityId_;
	}

	@Column(name = "activity_id_", nullable = true, length = 64)	
	public String getActivityId_() {
		return activityId_;
	}

	public void setConfiguration_(String configuration_) {
		this.configuration_ = configuration_;
	}

	@Column(name = "configuration_", nullable = true, length = 255)	
	public String getConfiguration_() {
		return configuration_;
	}

	public void setCreated_(Date created_) {
		this.created_ = created_;
	}

	@Column(name = "created_", nullable = false, length = 3)	
	public Date getCreated_() {
		return created_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	@Column(name = "proc_def_id_", nullable = true, length = 64)	
	public String getProcDefId_() {
		return procDefId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
