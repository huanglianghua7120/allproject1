package saaf.common.fmw.bpm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SaafActInstIntiatorEntity_HI_RO {
    public static final String QUERY_SQL = "SELECT SAI_INFO.SAII_INITIATOR_,\n" + 
    "              SAI_INFO.SAII_INSTANCE_ID_,\n" + 
    "              SAI_INFO.SAII_TASK_ID_,\n" + 
    "              SAI_INFO.SAII_INIT_DATE_,\n" + 
    "              ACTHI.ACT_NAME_,\n" + 
    "              ACTHI.PROC_DEF_ID_,\n" + 
    "              ACTHI.ASSIGNEE_,\n" + 
    "              ACTHI.START_TIME_,\n" + 
    "              ACTHI.END_TIME_\n" + 
    "       FROM saaf_act_instance_initinfo SAI_INFO, ACT_HI_ACTINST ACTHI\n" + 
    "       WHERE SAI_INFO.SAII_INSTANCE_ID_ = ACTHI.PROC_INST_ID_ AND\n" + 
    "             SAI_INFO.SAII_TASK_ID_ = ACTHI.Task_Id_";
    private String saiiInitiator_;
    private String saiiInstanceId_;
    private String saiiTaskId_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saiiInitDate_;
    private String actName_;
    private String procDefId_;
    private String assignee_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime_;
    public SaafActInstIntiatorEntity_HI_RO() {
        super();
    }

    public void setSaiiInitiator_(String saiiInitiator_) {
        this.saiiInitiator_ = saiiInitiator_;
    }

    public String getSaiiInitiator_() {
        return saiiInitiator_;
    }

    public void setSaiiInstanceId_(String saiiInstanceId_) {
        this.saiiInstanceId_ = saiiInstanceId_;
    }

    public String getSaiiInstanceId_() {
        return saiiInstanceId_;
    }

    public void setSaiiTaskId_(String saiiTaskId_) {
        this.saiiTaskId_ = saiiTaskId_;
    }

    public String getSaiiTaskId_() {
        return saiiTaskId_;
    }

    public void setSaiiInitDate_(Date saiiInitDate_) {
        this.saiiInitDate_ = saiiInitDate_;
    }

    public Date getSaiiInitDate_() {
        return saiiInitDate_;
    }

    public void setActName_(String actName_) {
        this.actName_ = actName_;
    }

    public String getActName_() {
        return actName_;
    }

    public void setProcDefId_(String procDefId_) {
        this.procDefId_ = procDefId_;
    }

    public String getProcDefId_() {
        return procDefId_;
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
}
