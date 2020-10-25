package saaf.common.fmw.bpm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SaafActTaskHistoryEntity_HI_RO {
    public static final String QUERY_SQL = "SELECT AHA.PROC_DEF_ID_,\n" + 
    "       AHA.PROC_INST_ID_,\n" + 
    "       AHA.EXECUTION_ID_,\n" + 
    "       AHA.ACT_ID_,\n" + 
    "       AHA.TASK_ID_,\n" + 
    "       AHA.ACT_NAME_,\n" + 
    "       AHA.ACT_TYPE_,\n" + 
    "       AHA.ASSIGNEE_,\n" + 
    "       AHA.START_TIME_,\n" + 
    "       AHA.END_TIME_,\n" + 
    "       AHC.MESSAGE_,\n" + 
    "       AHC.FULL_MSG_,\n" + 
    "       AHC.ACTION_\n" + 
    "  FROM ACT_HI_ACTINST AHA, ACT_HI_COMMENT AHC\n" + 
    " WHERE AHA.PROC_INST_ID_ = AHC.PROC_INST_ID_\n" + 
    "   AND AHA.TASK_ID_ = AHC.TASK_ID_";
    
    public static final String QUERY_TASK_HISTORY_SQL = " SELECT " + 
    		" 	ahti.ID_ , " + 
    		" 	ahti.PROC_DEF_ID_ , " + 
    		" 	ahti.TASK_DEF_KEY_ , " + 
    		" 	ahti.PROC_INST_ID_ , " + 
    		" 	ahti.EXECUTION_ID_ , " + 
    		" 	ahti.NAME_ , " + 
    		" 	ahti.PARENT_TASK_ID_ , " + 
    		" 	ahti.DESCRIPTION_ , " + 
    		" 	ahti.OWNER_ , " + 
    		" 	ahti.ASSIGNEE_ , " + 
    		" 	ahti.START_TIME_ , " + 
    		" 	ahti.CLAIM_TIME_ , " + 
    		" 	ahti.END_TIME_ , " + 
    		" 	ahti.DURATION_ , " + 
    		" 	ahti.DELETE_REASON_ , " + 
    		" 	ahti.PRIORITY_ , " + 
    		" 	ahti.DUE_DATE_ , " + 
    		" 	ahti.FORM_KEY_ , " + 
    		" 	ahti.CATEGORY_ , " + 
    		" 	ahti.TENANT_ID_ , " + 
    		" 	saib.SAIB_INSTANCE_ID_ , " + 
    		" 	saib.SAIB_TASK_ID_ , " + 
    		" 	saib.SAIB_TASK_TITLE_ , " + 
    		" 	saib.SAIB_BUSINESS_ID_ " + 
    		" FROM " + 
    		" 	act_hi_taskinst AS ahti , " + 
    		" 	saaf_act_instance_business AS saib " + 
    		" WHERE " + 
    		" 	ahti.ID_ = saib.SAIB_TASK_ID_ ";
    
    private String procDefId_;
    private String procInstId_;
    private String executionId_;
    private String actId_;
    private String taskId_;
    private String actName_;
    private String actType_;
    private String assignee_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime_;
    private String message_;
    private String fullMsg_;
    private String action_;
    public SaafActTaskHistoryEntity_HI_RO() {
        super();
    }

    public void setProcDefId_(String procDefId_) {
        this.procDefId_ = procDefId_;
    }

    public String getProcDefId_() {
        return procDefId_;
    }

    public void setProcInstId_(String procInstId_) {
        this.procInstId_ = procInstId_;
    }

    public String getProcInstId_() {
        return procInstId_;
    }

    public void setExecutionId_(String executionId_) {
        this.executionId_ = executionId_;
    }

    public String getExecutionId_() {
        return executionId_;
    }

    public void setActId_(String actId_) {
        this.actId_ = actId_;
    }

    public String getActId_() {
        return actId_;
    }

    public void setTaskId_(String taskId_) {
        this.taskId_ = taskId_;
    }

    public String getTaskId_() {
        return taskId_;
    }

    public void setActName_(String actName_) {
        this.actName_ = actName_;
    }

    public String getActName_() {
        return actName_;
    }

    public void setActType_(String actType_) {
        this.actType_ = actType_;
    }

    public String getActType_() {
        return actType_;
    }

    public void setAssignee_(String assignee_) {
        this.assignee_ = assignee_;
    }

    public String getAssignee_() {
        return assignee_;
    }

    public void setStartTime_(Date startTime_) {
        this.startTime_ = startTime_;
    }

    public Date getStartTime_() {
        return startTime_;
    }

    public void setEndTime_(Date endTime_) {
        this.endTime_ = endTime_;
    }

    public Date getEndTime_() {
        return endTime_;
    }

    public void setMessage_(String message_) {
        this.message_ = message_;
    }

    public String getMessage_() {
        return message_;
    }

    public void setFullMsg_(String fullMsg_) {
        this.fullMsg_ = fullMsg_;
    }

    public String getFullMsg_() {
        return fullMsg_;
    }

    public void setAction_(String action_) {
        this.action_ = action_;
    }

    public String getAction_() {
        return action_;
    }
}
