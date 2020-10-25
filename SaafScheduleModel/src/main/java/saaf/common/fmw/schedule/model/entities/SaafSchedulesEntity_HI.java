package saaf.common.fmw.schedule.model.entities;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "saaf_schedules")
public class SaafSchedulesEntity_HI {

    public SaafSchedulesEntity_HI() {
        super();
    }

    private Integer scheduleId;
    private String phaseCode;
    private String statusCode;
    private Timestamp actualStartDate;
    private Timestamp actualCompletionDate;
    private String logFileName;
    private String outputFileName;
    private String outputFileType;
    private Timestamp scheduleExpectStartDate;
    private Timestamp scheduleExpectEndDate;
    private Integer jobId;
    private String quartzJobName;
    private String cornexpress;
    private Integer priority;
    private Timestamp previousFireTime;
    private Timestamp nextFireTime;
    private String scheduleType;
    private Blob argumentObj;
    private String isDeleted;
    private Integer wasExecutedTotalCount;
    private Integer wasExecutedSuccessCount;
    private Integer wasExecutedFailCount;
    private String argumentsText;
    private Integer failAttemptFrequency;
    private Integer timeout;

    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp creationDate;
    private Integer operatorUserId;

    @Id
    @SequenceGenerator(name = "SAAF_SCHEDULES_S", sequenceName = "SAAF_SCHEDULES_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_SCHEDULES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "SCHEDULE_ID", nullable = false, length = 10)
    public Integer getScheduleId() {
        return scheduleId;
    }

    @Column(name = "PHASE_CODE", nullable = true, length = 240)
    public String getPhaseCode() {
        return phaseCode;
    }

    @Column(name = "STATUS_CODE", nullable = true, length = 240)
    public String getStatusCode() {
        return statusCode;
    }

    @Column(name = "ACTUAL_START_DATE", nullable = true, length = 0)
    public Timestamp getActualStartDate() {
        return actualStartDate;
    }

    @Column(name = "ACTUAL_COMPLETION_DATE", nullable = true, length = 0)
    public Timestamp getActualCompletionDate() {
        return actualCompletionDate;
    }

    @Column(name = "LOG_FILE_NAME", nullable = true, length = 240)
    public String getLogFileName() {
        return logFileName;
    }

    @Column(name = "OUTPUT_FILE_NAME", nullable = true, length = 240)
    public String getOutputFileName() {
        return outputFileName;
    }

    @Column(name = "OUTPUT_FILE_TYPE", nullable = true, length = 10)
    public String getOutputFileType() {
        return outputFileType;
    }

    @Column(name = "SCHEDULE_EXPECT_START_DATE", nullable = true, length = 0)
    public Timestamp getScheduleExpectStartDate() {
        return scheduleExpectStartDate;
    }

    @Column(name = "SCHEDULE_EXPECT_END_DATE", nullable = true, length = 0)
    public Timestamp getScheduleExpectEndDate() {
        return scheduleExpectEndDate;
    }

    @Column(name = "JOB_ID", nullable = true, length = 10)
    public Integer getJobId() {
        return jobId;
    }

    @Column(name = "QUARTZ_JOB_NAME", nullable = false, length = 480)
    public String getQuartzJobName() {
        return quartzJobName;
    }

    @Column(name = "CORNEXPRESS", nullable = true, length = 240)
    public String getCornexpress() {
        return cornexpress;
    }

    @Column(name = "PRIORITY", nullable = true, length = 10)
    public Integer getPriority() {
        return priority;
    }

    @Column(name = "PREVIOUS_FIRE_TIME", nullable = true, length = 0)
    public Timestamp getPreviousFireTime() {
        return previousFireTime;
    }

    @Column(name = "NEXT_FIRE_TIME", nullable = true, length = 0)
    public Timestamp getNextFireTime() {
        return nextFireTime;
    }

    @Column(name = "SCHEDULE_TYPE", nullable = true, length = 120)
    public String getScheduleType() {
        return scheduleType;
    }

    @Column(name = "ARGUMENT_OBJ", nullable = true, length = 0)
    public Blob getArgumentObj() {
        return argumentObj;
    }

    @Column(name = "IS_DELETED", nullable = true, length = 1)
    public String getIsDeleted() {
        return isDeleted;
    }

    @Column(name = "WAS_EXECUTED_TOTAL_COUNT", nullable = true, length = 10)
    public Integer getWasExecutedTotalCount() {
        return wasExecutedTotalCount;
    }

    @Column(name = "WAS_EXECUTED_SUCCESS_COUNT", nullable = true, length = 10)
    public Integer getWasExecutedSuccessCount() {
        return wasExecutedSuccessCount;
    }

    @Column(name = "WAS_EXECUTED_FAIL_COUNT", nullable = true, length = 10)
    public Integer getWasExecutedFailCount() {
        return wasExecutedFailCount;
    }

    @Column(name = "ARGUMENTS_TEXT", nullable = true, length = 4000)
    public String getArgumentsText() {
        return argumentsText;
    }

    @Column(name = "FAIL_ATTEMPT_FREQUENCY", nullable = true, length = 10)
    public Integer getFailAttemptFrequency() {
        return failAttemptFrequency;
    }

    @Column(name = "TIMEOUT", nullable = true, length = 10)
    public Integer getTimeout() {
        return timeout;
    }

    @Column(name = "CREATED_BY", nullable = true, length = 10)
    public Integer getCreatedBy() {
        return createdBy;
    }

    @Column(name = "LAST_UPDATED_BY", nullable = true, length = 10)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Column(name = "LAST_UPDATE_DATE", nullable = true, length = 0)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Column(name = "LAST_UPDATE_LOGIN", nullable = true, length = 10)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    @Column(name = "CREATION_DATE", nullable = true, length = 0)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setActualStartDate(Timestamp actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public void setActualCompletionDate(Timestamp actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void setOutputFileType(String outputFileType) {
        this.outputFileType = outputFileType;
    }

    public void setScheduleExpectStartDate(Timestamp scheduleExpectStartDate) {
        this.scheduleExpectStartDate = scheduleExpectStartDate;
    }

    public void setScheduleExpectEndDate(Timestamp scheduleExpectEndDate) {
        this.scheduleExpectEndDate = scheduleExpectEndDate;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setQuartzJobName(String quartzJobName) {
        this.quartzJobName = quartzJobName;
    }

    public void setCornexpress(String cornexpress) {
        this.cornexpress = cornexpress;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPreviousFireTime(Timestamp previousFireTime) {
        this.previousFireTime = previousFireTime;
    }

    public void setNextFireTime(Timestamp nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public void setArgumentObj(Blob argumentObj) {
        this.argumentObj = argumentObj;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setWasExecutedTotalCount(Integer wasExecutedTotalCount) {
        this.wasExecutedTotalCount = wasExecutedTotalCount;
    }

    public void setWasExecutedSuccessCount(Integer wasExecutedSuccessCount) {
        this.wasExecutedSuccessCount = wasExecutedSuccessCount;
    }

    public void setWasExecutedFailCount(Integer wasExecutedFailCount) {
        this.wasExecutedFailCount = wasExecutedFailCount;
    }

    public void setArgumentsText(String argumentsText) {
        this.argumentsText = argumentsText;
    }

    public void setFailAttemptFrequency(Integer failAttemptFrequency) {
        this.failAttemptFrequency = failAttemptFrequency;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
    
     public void setOperatorUserId(Integer operatorUserId) {
    	this.operatorUserId = operatorUserId;
    }

    @Transient	
    public Integer getOperatorUserId() {
    	return operatorUserId;
    }

}
