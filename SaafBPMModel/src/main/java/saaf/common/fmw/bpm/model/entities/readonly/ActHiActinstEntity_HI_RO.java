package saaf.common.fmw.bpm.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class ActHiActinstEntity_HI_RO {
	
	public final static String QUERY_ACTHIACTINST=" SELECT " +
			" 	actHiActinst.ID_ AS id_ , " +
			" 	actHiActinst.PROC_DEF_ID_ AS procDefId_ , " +
			" 	actHiActinst.PROC_INST_ID_ AS procInstId_ , " +
			" 	actHiActinst.EXECUTION_ID_ AS executionId_ , " +
			" 	actHiActinst.ACT_ID_ AS actId_ , " +
			" 	actHiActinst.TASK_ID_ AS taskId_ , " +
			" 	actHiActinst.CALL_PROC_INST_ID_ AS callProcInstId_ , " +
			" 	actHiActinst.ACT_NAME_ AS actName_ , " +
			" 	actHiActinst.ACT_TYPE_ AS actType_ , " +
			" 	actHiActinst.ASSIGNEE_ AS assignee_ , " +
			" 	actHiActinst.START_TIME_ AS startTime_ , " +
			" 	actHiActinst.END_TIME_ AS endTime_ , " +
			" 	actHiActinst.DURATION_ AS duration_ , " +
			" 	actHiActinst.TENANT_ID_ AS tenantId_ , " +
			" 	actHiComment.TYPE_ AS type_ , " +
			" 	actHiComment.TIME_ AS time_ , " +
			" 	actHiComment.USER_ID_ AS userId_ , " +
			" 	actHiComment.ACTION_ AS action_ , " +
			" 	actHiComment.MESSAGE_ AS message_ , " +
			" 	actHiComment.FULL_MSG_ AS fullMsg_, " +
			" 	saafUsers.user_full_name AS userFullName, " +
			" 	saafUsers.user_name AS userName " +
			" FROM " +
			" 	act_hi_actinst actHiActinst " +
			" LEFT JOIN act_hi_comment actHiComment  " +
			" ON actHiActinst.task_id_ = actHiComment.task_id_ " + 
			" LEFT JOIN saaf_users saafUsers  " +
			" ON  actHiActinst.ASSIGNEE_=saafUsers.user_id " +
			" WHERE 1=1 ";
	
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
	//审批意见表信息开始
	private String type_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time_;
    private String userId_;
    private String action_;
    private String message_;
    private byte[] fullMsg_;
    //审批意见表信息结束
    private String userFullName;
    private String userName;

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	public String getProcInstId_() {
		return procInstId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	public String getExecutionId_() {
		return executionId_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	public String getActId_() {
		return actId_;
	}

	public void setActId_(String actId_) {
		this.actId_ = actId_;
	}

	public String getTaskId_() {
		return taskId_;
	}

	public void setTaskId_(String taskId_) {
		this.taskId_ = taskId_;
	}

	public String getCallProcInstId_() {
		return callProcInstId_;
	}

	public void setCallProcInstId_(String callProcInstId_) {
		this.callProcInstId_ = callProcInstId_;
	}

	public String getActName_() {
		return actName_;
	}

	public void setActName_(String actName_) {
		this.actName_ = actName_;
	}

	public String getActType_() {
		return actType_;
	}

	public void setActType_(String actType_) {
		this.actType_ = actType_;
	}

	public String getAssignee_() {
		return assignee_;
	}

	public void setAssignee_(String assignee_) {
		this.assignee_ = assignee_;
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

	public String getTenantId_() {
		return tenantId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	public String getType_() {
		return type_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	public Date getTime_() {
		return time_;
	}

	public void setTime_(Date time_) {
		this.time_ = time_;
	}

	public String getUserId_() {
		return userId_;
	}

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	public String getAction_() {
		return action_;
	}

	public void setAction_(String action_) {
		this.action_ = action_;
	}

	public String getMessage_() {
		return message_;
	}

	public void setMessage_(String message_) {
		this.message_ = message_;
	}

	public byte[] getFullMsg_() {
		return fullMsg_;
	}

	public void setFullMsg_(byte[] fullMsg_) {
		this.fullMsg_ = fullMsg_;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
