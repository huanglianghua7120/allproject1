package saaf.common.fmw.schedule.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class SaafSchedulesEntity_HI_RO {

    public SaafSchedulesEntity_HI_RO() {
        super();
    }

    public static final String QUERY_SQL = 
    "select ss.SCHEDULE_ID                scheduleId, " +
    "       ss.PHASE_CODE                 phaseCode, " +
    "       ss.STATUS_CODE                statusCode, " +
    "       ss.ACTUAL_START_DATE          actualStartDate, " +
    "       ss.ACTUAL_COMPLETION_DATE     actualCompletionDate, " +
    "       ss.LOG_FILE_NAME              logFileName, " +
    "       ss.SCHEDULE_EXPECT_START_DATE scheduleExpectStartDate, " +
    "       ss.SCHEDULE_EXPECT_END_DATE   scheduleExpectEndDate, " +
    "       ss.JOB_ID                     jobId, " +
    "       ss.QUARTZ_JOB_NAME            quartzJobName, " +
    "       ss.CORNEXPRESS                cornexpress, " +
    "       ss.PRIORITY                   priority, " +
    "       ss.PREVIOUS_FIRE_TIME         previousFireTime, " +
    "       ss.NEXT_FIRE_TIME             nextFireTime, " +
    "       ss.SCHEDULE_TYPE              scheduleType, " +
    "       ss.WAS_EXECUTED_TOTAL_COUNT   wasExecutedTotalCount, " +
    "       ss.WAS_EXECUTED_SUCCESS_COUNT wasExecutedSuccessCount, " +
    "       ss.WAS_EXECUTED_FAIL_COUNT    wasExecutedFailCount, " +
    "       ss.ARGUMENTS_TEXT             argumentsText, " +
    "       ss.FAIL_ATTEMPT_FREQUENCY     failAttemptFrequency, " +
    "       ss.TIMEOUT                    timeout, " +
    "       ss.CREATED_BY                 createdBy, " +
    "       sj.JOB_NAME                   jobName, " +
    "       sj.SYSTEM                     system, " +
    "       sj.MODULE                     module, " +
    "       su.USER_NAME                  userName, " +
    "       lv1.MEANING                   phaseCodeMeaning, " +
    "       lv2.MEANING                   statusCodeMeaning, " +
    "       lv3.MEANING                   scheduleTypeMeaning, " +
    "       lv4.MEANING                   systemMeaning, " +
    "       lv5.MEANING                   moduleMeaning " +
    "  from saaf_schedules ss " +
    "  left join saaf_users su " +
    "    on ss.created_by = su.user_id " +
    "  left join saaf_jobs sj " +
    "    on ss.JOB_ID = sj.JOB_ID " +
    "  left join saaf_lookup_values lv1 " +
    "    on lv1.LOOKUP_TYPE = 'CP_PHASE_CODE' " +
    "   and lv1.LOOKUP_CODE = ss.PHASE_CODE " +
    "  left join saaf_lookup_values lv2 " +
    "    on lv2.LOOKUP_TYPE = 'CP_STATUS_CODE' " +
    "   and lv2.LOOKUP_CODE = ss.STATUS_CODE " +
    "  left join saaf_lookup_values lv3 " +
    "    on lv3.LOOKUP_TYPE = 'SAAF_SCHE_TYPE' " +
    "   and lv3.LOOKUP_CODE = ss.SCHEDULE_TYPE " +
    "  left join saaf_lookup_values lv4 " +
    "   on lv4.LOOKUP_TYPE = 'SCHEDULED_SYSTEM' " +
    "   and lv4.LOOKUP_CODE = sj.SYSTEM " +
    "  left join saaf_lookup_values lv5 " +
    "    on lv5.LOOKUP_TYPE = 'SCHEDULED_SYSTEM_MODULE' " +
    "   and lv5.LOOKUP_CODE = sj.MODULE " +
    "  left join srm_spm_performance_scheme ps " +
    "    on ss.schedule_id = ps.request_id " +
    "  left join srm_spm_performance_scheme ps1 " +
    "    on ss.schedule_id = ps1.data_request_id " +
    "  left join srm_spm_performance_scheme ps2 " +
    "    on ss.schedule_id = ps2.ratio_request_id " +
    " where ss.IS_DELETED is null ";

    private Integer scheduleId;
    private String phaseCode;
    private String statusCode;
    private String phaseCodeMeaning;
    private String statusCodeMeaning;
    private Timestamp actualStartDate;
    private Timestamp actualCompletionDate;
    private String logFileName;
    private Timestamp scheduleExpectStartDate;
    private Timestamp scheduleExpectEndDate;
    private Integer jobId;
    private String quartzJobName;
    private String cornexpress;
    private Integer priority;
    private Timestamp previousFireTime;
    private Timestamp nextFireTime;
    private String scheduleType;
    private String scheduleTypeMeaning;
    private Integer wasExecutedTotalCount;
    private Integer wasExecutedSuccessCount;
    private Integer wasExecutedFailCount;
    private String argumentsText;
    private Integer failAttemptFrequency;
    private Integer timeout;
    private Integer createdBy;

    private String jobName;
    private String userName;
    
    private String system;
    private String module;
    
    private String systemMeaning;
    private String moduleMeaning;
    private Integer schemeId;


    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    //
    public void setSystemMeaning(String systemMeaning) {
        this.systemMeaning = systemMeaning;
    }
    public String getSystemMeaning() {
        return systemMeaning;
    }
    public void setModuleMeaning(String moduleMeaning) {
        this.moduleMeaning = moduleMeaning;
    }
    public String getModuleMeaning() {
        return moduleMeaning;
    }
    //

    public void setScheduleTypeMeaning(String scheduleTypeMeaning) {
        this.scheduleTypeMeaning = scheduleTypeMeaning;
    }
    public String getScheduleTypeMeaning() {
        return scheduleTypeMeaning;
    }

    public void setPhaseCodeMeaning(String phaseCodeMeaning) {
        this.phaseCodeMeaning = phaseCodeMeaning;
    }
    public String getPhaseCodeMeaning() {
        return phaseCodeMeaning;
    }
    
    public void setStatusCodeMeaning(String statusCodeMeaning) {
        this.statusCodeMeaning = statusCodeMeaning;
    }
    public String getStatusCodeMeaning() {
        return statusCodeMeaning;
    }

    public void setSystem(String system) {
        this.system = system;
    }
    public String getSystem() {
        return system;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }
    
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Timestamp actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getActualCompletionDate() {
        return actualCompletionDate;
    }

    public void setActualCompletionDate(Timestamp actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getScheduleExpectStartDate() {
        return scheduleExpectStartDate;
    }

    public void setScheduleExpectStartDate(Timestamp scheduleExpectStartDate) {
        this.scheduleExpectStartDate = scheduleExpectStartDate;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getScheduleExpectEndDate() {
        return scheduleExpectEndDate;
    }

    public void setScheduleExpectEndDate(Timestamp scheduleExpectEndDate) {
        this.scheduleExpectEndDate = scheduleExpectEndDate;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getQuartzJobName() {
        return quartzJobName;
    }

    public void setQuartzJobName(String quartzJobName) {
        this.quartzJobName = quartzJobName;
    }

    public String getCornexpress() {
        return cornexpress;
    }

    public void setCornexpress(String cornexpress) {
        this.cornexpress = cornexpress;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getPreviousFireTime() {
        return previousFireTime;
    }

    public void setPreviousFireTime(Timestamp previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Timestamp nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getWasExecutedTotalCount() {
        return wasExecutedTotalCount;
    }

    public void setWasExecutedTotalCount(Integer wasExecutedTotalCount) {
        this.wasExecutedTotalCount = wasExecutedTotalCount;
    }

    public Integer getWasExecutedSuccessCount() {
        return wasExecutedSuccessCount;
    }

    public void setWasExecutedSuccessCount(Integer wasExecutedSuccessCount) {
        this.wasExecutedSuccessCount = wasExecutedSuccessCount;
    }

    public Integer getWasExecutedFailCount() {
        return wasExecutedFailCount;
    }

    public void setWasExecutedFailCount(Integer wasExecutedFailCount) {
        this.wasExecutedFailCount = wasExecutedFailCount;
    }

    public String getArgumentsText() {
        return argumentsText;
    }

    public void setArgumentsText(String argumentsText) {
        this.argumentsText = argumentsText;
    }

    public Integer getFailAttemptFrequency() {
        return failAttemptFrequency;
    }

    public void setFailAttemptFrequency(Integer failAttemptFrequency) {
        this.failAttemptFrequency = failAttemptFrequency;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
