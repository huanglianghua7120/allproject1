package saaf.common.fmw.bpm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SaafActHiTaskUrlEntity_HI_RO {
    public static final String QUERY_SQL = "SELECT ACTHI.ID_,\n" + 
    "       ACTHI.PROC_DEF_ID_,\n" + 
    "       ACTHI.PROC_INST_ID_,\n" + 
    "       ACTHI.EXECUTION_ID_,\n" + 
    "       ACTHI.ACT_ID_,\n" + 
    "       ACTHI.TASK_ID_,\n" + 
    "       ACTHI.CALL_PROC_INST_ID_,\n" + 
    "       ACTHI.ACT_NAME_,\n" + 
    "       ACTHI.ACT_TYPE_,\n" + 
    "       ACTHI.ASSIGNEE_,\n" + 
    "       ACTHI.START_TIME_,\n" + 
    "       ACTHI.END_TIME_,\n" + 
    "       ACTHI.DURATION_,\n" + 
    "       ACTHI.TENANT_ID_,\n" + 
    "       SAIB.SAIB_BUSINESS_ID_,\n" + 
    "       SAIB.SAIB_TASK_TITLE_,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR1,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR2,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR3,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR4,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR5,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR6,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR7,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR8,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR9,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR10,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE1,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE2,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE3,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE4,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE5,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE6,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE7,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE8,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE9,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE10,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT1,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT2,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT3,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT4,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT5,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT6,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT7,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT8,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT9,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT10\n" + 
    "  FROM ACT_HI_ACTINST ACTHI, SAAF_ACT_INSTANCE_BUSINESS SAIB\n" + 
    " WHERE SAIB.SAIB_INSTANCE_ID_ = ACTHI.PROC_INST_ID_\n" + 
    "   AND SAIB.SAIB_TASK_ID_ = ACTHI.TASK_ID_\n" + 
    "   AND ACTHI.END_TIME_ IS NOT NULL";
    public SaafActHiTaskUrlEntity_HI_RO() {
        super();
    }
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
    private String saibBusinessId_;
    private String saibTaskTitle_;
    private String saibBusinessdataStr1;
    private String saibBusinessdataStr2;
    private String saibBusinessdataStr3;
    private String saibBusinessdataStr4;
    private String saibBusinessdataStr5;
    private String saibBusinessdataStr6;
    private String saibBusinessdataStr7;
    private String saibBusinessdataStr8;
    private String saibBusinessdataStr9;
    private String saibBusinessdataStr10;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate1;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate2;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate3;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate4;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate5;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate6;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate7;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate8;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate9;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate10;
    private Integer saibBusinessdataInt1;
    private Integer saibBusinessdataInt2;
    private Integer saibBusinessdataInt3;
    private Integer saibBusinessdataInt4;
    private Integer saibBusinessdataInt5;
    private Integer saibBusinessdataInt6;
    private Integer saibBusinessdataInt7;
    private Integer saibBusinessdataInt8;
    private Integer saibBusinessdataInt9;
    private Integer saibBusinessdataInt10;

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getId_() {
        return id_;
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

    public void setCallProcInstId_(String callProcInstId_) {
        this.callProcInstId_ = callProcInstId_;
    }

    public String getCallProcInstId_() {
        return callProcInstId_;
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

    public void setDuration_(Long duration_) {
        this.duration_ = duration_;
    }

    public Long getDuration_() {
        return duration_;
    }

    public void setTenantId_(String tenantId_) {
        this.tenantId_ = tenantId_;
    }

    public String getTenantId_() {
        return tenantId_;
    }

    public void setSaibBusinessId_(String saibBusinessId_) {
        this.saibBusinessId_ = saibBusinessId_;
    }

    public String getSaibBusinessId_() {
        return saibBusinessId_;
    }

    public void setSaibTaskTitle_(String saibTaskTitle_) {
        this.saibTaskTitle_ = saibTaskTitle_;
    }

    public String getSaibTaskTitle_() {
        return saibTaskTitle_;
    }

    public void setSaibBusinessdataStr1(String saibBusinessdataStr1) {
        this.saibBusinessdataStr1 = saibBusinessdataStr1;
    }

    public String getSaibBusinessdataStr1() {
        return saibBusinessdataStr1;
    }

    public void setSaibBusinessdataStr2(String saibBusinessdataStr2) {
        this.saibBusinessdataStr2 = saibBusinessdataStr2;
    }

    public String getSaibBusinessdataStr2() {
        return saibBusinessdataStr2;
    }

    public void setSaibBusinessdataStr3(String saibBusinessdataStr3) {
        this.saibBusinessdataStr3 = saibBusinessdataStr3;
    }

    public String getSaibBusinessdataStr3() {
        return saibBusinessdataStr3;
    }

    public void setSaibBusinessdataStr4(String saibBusinessdataStr4) {
        this.saibBusinessdataStr4 = saibBusinessdataStr4;
    }

    public String getSaibBusinessdataStr4() {
        return saibBusinessdataStr4;
    }

    public void setSaibBusinessdataStr5(String saibBusinessdataStr5) {
        this.saibBusinessdataStr5 = saibBusinessdataStr5;
    }

    public String getSaibBusinessdataStr5() {
        return saibBusinessdataStr5;
    }

    public void setSaibBusinessdataStr6(String saibBusinessdataStr6) {
        this.saibBusinessdataStr6 = saibBusinessdataStr6;
    }

    public String getSaibBusinessdataStr6() {
        return saibBusinessdataStr6;
    }

    public void setSaibBusinessdataStr7(String saibBusinessdataStr7) {
        this.saibBusinessdataStr7 = saibBusinessdataStr7;
    }

    public String getSaibBusinessdataStr7() {
        return saibBusinessdataStr7;
    }

    public void setSaibBusinessdataStr8(String saibBusinessdataStr8) {
        this.saibBusinessdataStr8 = saibBusinessdataStr8;
    }

    public String getSaibBusinessdataStr8() {
        return saibBusinessdataStr8;
    }

    public void setSaibBusinessdataStr9(String saibBusinessdataStr9) {
        this.saibBusinessdataStr9 = saibBusinessdataStr9;
    }

    public String getSaibBusinessdataStr9() {
        return saibBusinessdataStr9;
    }

    public void setSaibBusinessdataStr10(String saibBusinessdataStr10) {
        this.saibBusinessdataStr10 = saibBusinessdataStr10;
    }

    public String getSaibBusinessdataStr10() {
        return saibBusinessdataStr10;
    }

    public void setSaibBusinessdataDate1(Date saibBusinessdataDate1) {
        this.saibBusinessdataDate1 = saibBusinessdataDate1;
    }

    public Date getSaibBusinessdataDate1() {
        return saibBusinessdataDate1;
    }

    public void setSaibBusinessdataDate2(Date saibBusinessdataDate2) {
        this.saibBusinessdataDate2 = saibBusinessdataDate2;
    }

    public Date getSaibBusinessdataDate2() {
        return saibBusinessdataDate2;
    }

    public void setSaibBusinessdataDate3(Date saibBusinessdataDate3) {
        this.saibBusinessdataDate3 = saibBusinessdataDate3;
    }

    public Date getSaibBusinessdataDate3() {
        return saibBusinessdataDate3;
    }

    public void setSaibBusinessdataDate4(Date saibBusinessdataDate4) {
        this.saibBusinessdataDate4 = saibBusinessdataDate4;
    }

    public Date getSaibBusinessdataDate4() {
        return saibBusinessdataDate4;
    }

    public void setSaibBusinessdataDate5(Date saibBusinessdataDate5) {
        this.saibBusinessdataDate5 = saibBusinessdataDate5;
    }

    public Date getSaibBusinessdataDate5() {
        return saibBusinessdataDate5;
    }

    public void setSaibBusinessdataDate6(Date saibBusinessdataDate6) {
        this.saibBusinessdataDate6 = saibBusinessdataDate6;
    }

    public Date getSaibBusinessdataDate6() {
        return saibBusinessdataDate6;
    }

    public void setSaibBusinessdataDate7(Date saibBusinessdataDate7) {
        this.saibBusinessdataDate7 = saibBusinessdataDate7;
    }

    public Date getSaibBusinessdataDate7() {
        return saibBusinessdataDate7;
    }

    public void setSaibBusinessdataDate8(Date saibBusinessdataDate8) {
        this.saibBusinessdataDate8 = saibBusinessdataDate8;
    }

    public Date getSaibBusinessdataDate8() {
        return saibBusinessdataDate8;
    }

    public void setSaibBusinessdataDate9(Date saibBusinessdataDate9) {
        this.saibBusinessdataDate9 = saibBusinessdataDate9;
    }

    public Date getSaibBusinessdataDate9() {
        return saibBusinessdataDate9;
    }

    public void setSaibBusinessdataDate10(Date saibBusinessdataDate10) {
        this.saibBusinessdataDate10 = saibBusinessdataDate10;
    }

    public Date getSaibBusinessdataDate10() {
        return saibBusinessdataDate10;
    }

    public void setSaibBusinessdataInt1(Integer saibBusinessdataInt1) {
        this.saibBusinessdataInt1 = saibBusinessdataInt1;
    }

    public Integer getSaibBusinessdataInt1() {
        return saibBusinessdataInt1;
    }

    public void setSaibBusinessdataInt2(Integer saibBusinessdataInt2) {
        this.saibBusinessdataInt2 = saibBusinessdataInt2;
    }

    public Integer getSaibBusinessdataInt2() {
        return saibBusinessdataInt2;
    }

    public void setSaibBusinessdataInt3(Integer saibBusinessdataInt3) {
        this.saibBusinessdataInt3 = saibBusinessdataInt3;
    }

    public Integer getSaibBusinessdataInt3() {
        return saibBusinessdataInt3;
    }

    public void setSaibBusinessdataInt4(Integer saibBusinessdataInt4) {
        this.saibBusinessdataInt4 = saibBusinessdataInt4;
    }

    public Integer getSaibBusinessdataInt4() {
        return saibBusinessdataInt4;
    }

    public void setSaibBusinessdataInt5(Integer saibBusinessdataInt5) {
        this.saibBusinessdataInt5 = saibBusinessdataInt5;
    }

    public Integer getSaibBusinessdataInt5() {
        return saibBusinessdataInt5;
    }

    public void setSaibBusinessdataInt6(Integer saibBusinessdataInt6) {
        this.saibBusinessdataInt6 = saibBusinessdataInt6;
    }

    public Integer getSaibBusinessdataInt6() {
        return saibBusinessdataInt6;
    }

    public void setSaibBusinessdataInt7(Integer saibBusinessdataInt7) {
        this.saibBusinessdataInt7 = saibBusinessdataInt7;
    }

    public Integer getSaibBusinessdataInt7() {
        return saibBusinessdataInt7;
    }

    public void setSaibBusinessdataInt8(Integer saibBusinessdataInt8) {
        this.saibBusinessdataInt8 = saibBusinessdataInt8;
    }

    public Integer getSaibBusinessdataInt8() {
        return saibBusinessdataInt8;
    }

    public void setSaibBusinessdataInt9(Integer saibBusinessdataInt9) {
        this.saibBusinessdataInt9 = saibBusinessdataInt9;
    }

    public Integer getSaibBusinessdataInt9() {
        return saibBusinessdataInt9;
    }

    public void setSaibBusinessdataInt10(Integer saibBusinessdataInt10) {
        this.saibBusinessdataInt10 = saibBusinessdataInt10;
    }

    public Integer getSaibBusinessdataInt10() {
        return saibBusinessdataInt10;
    }
}
