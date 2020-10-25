package saaf.common.fmw.bpm.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SaafActHiProcinstEntity_HI_RO {
	
	public static final String QUERY_MY_PROC_SQL = " SELECT " +
			"   ahpi.ID_, " +
			"   ahpi.PROC_INST_ID_, " +
			"   ahpi.BUSINESS_KEY_, " +
			"   ahpi.PROC_DEF_ID_, " +
			"   ahpi.START_TIME_, " +
			"   ahpi.END_TIME_, " +
			"   ahpi.DURATION_, " +
			"   ahpi.START_USER_ID_, " +
			"   ahpi.START_ACT_ID_, " +
			"   ahpi.END_ACT_ID_, " +
			"   ahpi.SUPER_PROCESS_INSTANCE_ID_, " +
			"   ahpi.DELETE_REASON_, " +
			"   ahpi.TENANT_ID_, " +
			"   ahpi.NAME_, " +
			" 	saib.SAIB_TASK_ID_ , " +
			" 	saib.SAIB_BUSINESS_ID_ , " +
			" 	saib.SAIB_TASK_TITLE_ , " +
			" 	saib.SAIB_ID_ " +
			" FROM " +
			" 	act_hi_procinst ahpi , " +
			" 	saaf_act_instance_business saib " +
			" WHERE " +
			" 	ahpi.PROC_INST_ID_ = saib.SAIB_INSTANCE_ID_ ";
	
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
	
	private String saibTaskId;
	private String saibBusinessId;
	private String saibTaskTitle;
	private String saibId;

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getProcInstId_() {
		return procInstId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	public String getBusinessKey_() {
		return businessKey_;
	}

	public void setBusinessKey_(String businessKey_) {
		this.businessKey_ = businessKey_;
	}

	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	public Date getStartTime_() {
		return startTime_;
	}

	public void setStartTime_(Date startTime_) {
		this.startTime_ = startTime_;
	}

	public Date getEndTime_() {
		return endTime_;
	}

	public void setEndTime_(Date endTime_) {
		this.endTime_ = endTime_;
	}

	public Long getDuration_() {
		return duration_;
	}

	public void setDuration_(Long duration_) {
		this.duration_ = duration_;
	}

	public String getStartUserId_() {
		return startUserId_;
	}

	public void setStartUserId_(String startUserId_) {
		this.startUserId_ = startUserId_;
	}

	public String getStartActId_() {
		return startActId_;
	}

	public void setStartActId_(String startActId_) {
		this.startActId_ = startActId_;
	}

	public String getEndActId_() {
		return endActId_;
	}

	public void setEndActId_(String endActId_) {
		this.endActId_ = endActId_;
	}

	public String getSuperProcessInstanceId_() {
		return superProcessInstanceId_;
	}

	public void setSuperProcessInstanceId_(String superProcessInstanceId_) {
		this.superProcessInstanceId_ = superProcessInstanceId_;
	}

	public String getDeleteReason_() {
		return deleteReason_;
	}

	public void setDeleteReason_(String deleteReason_) {
		this.deleteReason_ = deleteReason_;
	}

	public String getTenantId_() {
		return tenantId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public String getSaibTaskId() {
		return saibTaskId;
	}

	public void setSaibTaskId(String saibTaskId) {
		this.saibTaskId = saibTaskId;
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

	public String getSaibId() {
		return saibId;
	}

	public void setSaibId(String saibId) {
		this.saibId = saibId;
	}

}
