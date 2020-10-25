package saaf.common.fmw.bpm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
/**
 * 结合业务的主键获取流程待办的相关信息
 */
public class SaafActRuTaskUrlEntity_HI_RO {
    public static final String QUERY_SQL = "SELECT DISTINCT RUTASK.ID_,\n" + 
    "			 RUTASK.NAME_,\n" + 
    "			 RUTASK.ASSIGNEE_,\n" + 
    "       RUTASK.REV_,\n" + 
    "       RUTASK.EXECUTION_ID_,\n" + 
    "       RUTASK.PROC_INST_ID_,\n" + 
    "       RUTASK.PROC_DEF_ID_,\n" + 
    "       RUTASK.PARENT_TASK_ID_,\n" + 
    "       RUTASK.DESCRIPTION_,\n" + 
    "       RUTASK.OWNER_,\n" + 
    "       RUTASK.ASSIGNEE_,\n" + 
    "       RUTASK.DELEGATION_,\n" + 
    "       RUTASK.PRIORITY_,\n" + 
    "       RUTASK.CREATE_TIME_,\n" + 
    "       RUTASK.DUE_DATE_,\n" + 
    "       RUTASK.CATEGORY_,\n" + 
    "       RUTASK.SUSPENSION_STATE_,\n" + 
    "       RUTASK.TENANT_ID_,\n" + 
    "       RUTASK.FORM_KEY_,\n" + 
    "       SAIB.SAIB_INSTANCE_ID_,\n" + 
    "       SAIB.SAIB_TASK_TITLE_,\n" + 
    "       SAIB.Saib_Business_Id_,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR1,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR2,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_STR3,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE1,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_DATE2,\n" + 
    "       SAIB.SAIB_BUSINESSDATA_INT1\n" + 
    "  FROM ACT_RU_TASK RUTASK, SAAF_ACT_INSTANCE_BUSINESS SAIB\n" + 
    "  WHERE SAIB.SAIB_INSTANCE_ID_ = RUTASK.PROC_INST_ID_\n";
    private String id_;
    private String name_;
    private Integer rev_;
    private String executionId_;
    private String procInstId_;
    private String procDefId_;
    private String parentTaskId_;
    private String description_;
    private String owner_;
    private String assignee_;
    private String delegation_;
    private Integer priority_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate_;
    private String category_;
    private Integer suspensionState_;
    private String tenantId_;
    private String formKey_;
    private String saibInstanceId_;
    private String saibTaskTitle_;
    private String saibBusinessId_;
    private String saibBusinessdataStr1;
    private String saibBusinessdataStr2;
    private String saibBusinessdataStr3;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate1;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date saibBusinessdataDate2;
    private Integer saibBusinessdataInt1;
    public SaafActRuTaskUrlEntity_HI_RO() {
        super();
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getId_() {
        return id_;
    }

    public void setRev_(Integer rev_) {
        this.rev_ = rev_;
    }

    public Integer getRev_() {
        return rev_;
    }

    public void setExecutionId_(String executionId_) {
        this.executionId_ = executionId_;
    }

    public String getExecutionId_() {
        return executionId_;
    }

    public void setProcInstId_(String procInstId_) {
        this.procInstId_ = procInstId_;
    }

    public String getProcInstId_() {
        return procInstId_;
    }

    public void setProcDefId_(String procDefId_) {
        this.procDefId_ = procDefId_;
    }

    public String getProcDefId_() {
        return procDefId_;
    }

    public void setParentTaskId_(String parentTaskId_) {
        this.parentTaskId_ = parentTaskId_;
    }

    public String getParentTaskId_() {
        return parentTaskId_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setOwner_(String owner_) {
        this.owner_ = owner_;
    }

    public String getOwner_() {
        return owner_;
    }

    public void setAssignee_(String assignee_) {
        this.assignee_ = assignee_;
    }

    public String getAssignee_() {
        return assignee_;
    }

    public void setDelegation_(String delegation_) {
        this.delegation_ = delegation_;
    }

    public String getDelegation_() {
        return delegation_;
    }

    public void setPriority_(Integer priority_) {
        this.priority_ = priority_;
    }

    public Integer getPriority_() {
        return priority_;
    }

    public void setCreateTime_(Date createTime_) {
        this.createTime_ = createTime_;
    }

    public Date getCreateTime_() {
        return createTime_;
    }

    public void setDueDate_(Date dueDate_) {
        this.dueDate_ = dueDate_;
    }

    public Date getDueDate_() {
        return dueDate_;
    }

    public void setCategory_(String category_) {
        this.category_ = category_;
    }

    public String getCategory_() {
        return category_;
    }

    public void setSuspensionState_(Integer suspensionState_) {
        this.suspensionState_ = suspensionState_;
    }

    public Integer getSuspensionState_() {
        return suspensionState_;
    }

    public void setTenantId_(String tenantId_) {
        this.tenantId_ = tenantId_;
    }

    public String getTenantId_() {
        return tenantId_;
    }

    public void setFormKey_(String formKey_) {
        this.formKey_ = formKey_;
    }

    public String getFormKey_() {
        return formKey_;
    }

    public void setSaibInstanceId_(String saibInstanceId_) {
        this.saibInstanceId_ = saibInstanceId_;
    }

    public String getSaibInstanceId_() {
        return saibInstanceId_;
    }

    public void setSaibTaskTitle_(String saibTaskTitle_) {
        this.saibTaskTitle_ = saibTaskTitle_;
    }

    public String getSaibTaskTitle_() {
        return saibTaskTitle_;
    }

    public void setSaibBusinessId_(String saibBusinessId_) {
        this.saibBusinessId_ = saibBusinessId_;
    }

    public String getSaibBusinessId_() {
        return saibBusinessId_;
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

    public void setSaibBusinessdataInt1(Integer saibBusinessdataInt1) {
        this.saibBusinessdataInt1 = saibBusinessdataInt1;
    }

    public Integer getSaibBusinessdataInt1() {
        return saibBusinessdataInt1;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getName_() {
        return name_;
    }
}
