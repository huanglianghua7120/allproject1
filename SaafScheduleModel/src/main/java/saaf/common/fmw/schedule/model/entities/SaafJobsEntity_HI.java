package saaf.common.fmw.schedule.model.entities;

import javax.persistence.*;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafJobsEntity_HI Entity Object
 * Sat Dec 23 16:29:56 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_jobs")
public class SaafJobsEntity_HI {
    private Integer jobId;
    private String jobName;
    private String description;
    private String executableName;
    private String method;
    private String outputFileType;
    private String system;
    private String module;
    private String jobType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;

    public void setJobId(Integer jobId) {
	this.jobId = jobId;
    }

    @Id    
    @SequenceGenerator(name = "SAAF_JOBS_S", sequenceName = "SAAF_JOBS_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_JOBS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "job_id", nullable = false, length = 11)    
    public Integer getJobId() {
	return jobId;
    }

    public void setJobName(String jobName) {
	this.jobName = jobName;
    }

    @Column(name = "job_name", nullable = true, length = 200)    
    public String getJobName() {
	return jobName;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 0)    
    public String getDescription() {
	return description;
    }

    public void setExecutableName(String executableName) {
	this.executableName = executableName;
    }

    @Column(name = "executable_name", nullable = true, length = 500)    
    public String getExecutableName() {
	return executableName;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    @Column(name = "method", nullable = true, length = 240)    
    public String getMethod() {
	return method;
    }

    public void setOutputFileType(String outputFileType) {
	this.outputFileType = outputFileType;
    }

    @Column(name = "output_file_type", nullable = true, length = 100)    
    public String getOutputFileType() {
	return outputFileType;
    }

    public void setSystem(String system) {
	this.system = system;
    }

    @Column(name = "system", nullable = true, length = 240)    
    public String getSystem() {
	return system;
    }

    public void setModule(String module) {
	this.module = module;
    }

    @Column(name = "module", nullable = true, length = 240)    
    public String getModule() {
	return module;
    }

    public void setJobType(String jobType) {
	this.jobType = jobType;
    }

    @Column(name = "job_type", nullable = true, length = 120)    
    public String getJobType() {
	return jobType;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    @Column(name = "creation_date", nullable = true, length = 0)    
    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
    }

    @Column(name = "created_by", nullable = true, length = 11)    
    public Integer getCreatedBy() {
	return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = true, length = 11)    
    public Integer getLastUpdatedBy() {
	return lastUpdatedBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = true, length = 0)    
    public Date getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
	this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", nullable = true, length = 11)    
    public Integer getLastUpdateLogin() {
	return lastUpdateLogin;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

    @Column(name = "version_num", nullable = true, length = 11)    
    public Integer getVersionNum() {
	return versionNum;
    }
    
    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
    	this.operatorUserId = operatorUserId;
    }

    @Transient	
    public Integer getOperatorUserId() {
    	return operatorUserId;
    }
}

