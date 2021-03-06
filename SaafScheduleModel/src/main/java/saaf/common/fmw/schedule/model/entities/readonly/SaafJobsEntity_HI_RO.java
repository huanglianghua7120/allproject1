package saaf.common.fmw.schedule.model.entities.readonly;

import java.sql.Timestamp;


public class SaafJobsEntity_HI_RO {
    
    public SaafJobsEntity_HI_RO() {
        super();
    }
    
    public static final String QUERY_SQL =
    "select sj.JOB_ID            jobId, " +
    "       sj.JOB_NAME          jobName, " +
    "       sj.DESCRIPTION       description, " +
    "       sj.EXECUTABLE_NAME   executableName, " +
    "       sj.METHOD            method, " +
    "       sj.OUTPUT_FILE_TYPE  outputFileType, " +
    "       sj.SYSTEM            system, " +
    "       sj.MODULE            module, " +
    "       sj.JOB_TYPE          jobType, " +
    "       sj.CREATION_DATE     creationDate, " +
    "       sj.CREATED_BY        createdBy, " +
    "       sj.LAST_UPDATED_BY   lastUpdateBy, " +
    "       sj.LAST_UPDATE_DATE  lastUpdateDate, " +
    "       sj.LAST_UPDATE_LOGIN lastUpdateLogin, " +
    "       lv1.MEANING          methodMeaning, " +
    "       lv2.MEANING          systemMeaning, " +
    "       lv3.MEANING          moduleMeaning, " +
    "       lv4.MEANING          jobTypeMeaning " +
    "  from saaf_jobs sj " +
    "  left join saaf_lookup_values lv1 " +
    "    on lv1.LOOKUP_TYPE = 'HTTP_METHOD_TYPE' " +
    "   and lv1.LOOKUP_CODE = sj.METHOD " +
    "  left join saaf_lookup_values lv2 " +
    "    on lv2.LOOKUP_TYPE = 'SCHEDULED_SYSTEM' " +
    "   and lv2.LOOKUP_CODE = sj.SYSTEM " +
    "  left join saaf_lookup_values lv3 " +
    "    on lv3.LOOKUP_TYPE = 'SCHEDULED_SYSTEM_MODULE' " +
    "   and lv3.LOOKUP_CODE = sj.MODULE " +
    "  left join saaf_lookup_values lv4 " +
    "    on lv4.LOOKUP_TYPE = 'TASK_TYPE' " +
    "   and lv4.LOOKUP_CODE = sj.JOB_TYPE ";

    private Integer jobId;
    private String jobName;
    private String description;
    private String executableName;
    private String method;
    private String outputFileType;
    private String system;
    private String module;
    private String jobType;

    private Integer createdBy;
    private Integer lastUpdatedBy;
    private Timestamp lastUpdateDate;
    private Integer lastUpdateLogin;
    private Timestamp creationDate;
    
    //
    private String methodMeaning;
    private String systemMeaning;
    private String moduleMeaning;
    private String jobTypeMeaning;
    //
    //
    public String getMethodMeaning() {
        return methodMeaning;
    }
    public void setMethodMeaning(String methodMeaning) {
        this.methodMeaning = methodMeaning;
    }
    public String getSystemMeaning() {
        return systemMeaning;
    }
    public void setSystemMeaning(String systemMeaning) {
        this.systemMeaning = systemMeaning;
    }
    public String getModuleMeaning() {
        return moduleMeaning;
    }
    public void setModuleMeaning(String moduleMeaning) {
        this.moduleMeaning = moduleMeaning;
    }
    public String getJobTypeMeaning() {
        return jobTypeMeaning;
    }
    public void setJobTypeMeaning(String jobTypeMeaning) {
        this.jobTypeMeaning = jobTypeMeaning;
    }
    //

    public Integer getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutableName() {
        return executableName;
    }

    public String getMethod() {
        return method;
    }

    public String getOutputFileType() {
        return outputFileType;
    }

    public String getSystem() {
        return system;
    }

    public String getModule() {
        return module;
    }

    public String getJobType() {
        return jobType;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExecutableName(String executableName) {
        this.executableName = executableName;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setOutputFileType(String outputFileType) {
        this.outputFileType = outputFileType;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
