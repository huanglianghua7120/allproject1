package saaf.common.fmw.apimanager.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SaafProjectManagerEntity_HI Entity Object
 * Wed Sep 20 09:58:31 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_project_manager_h")
public class SaafProjectManagerHEntity_HI {
    private Integer sphId;
    private String projectName;
    private String projectCode;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer operatorUserId;
    private Integer  lastUpdateLogin;
    
    public void setLastUpdateLogin(Integer lastUpdateLogin) {
    	this.lastUpdateLogin = lastUpdateLogin;
    }
    @Transient	
    public Integer getLastUpdateLogin() {
    	return lastUpdateLogin;
    }

    

	public void setSphId(Integer sphId) {
		this.sphId = sphId;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "sph_id", nullable = false, length = 11)	
	public Integer getSphId() {
		return sphId;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "project_name", nullable = true, length = 100)	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Column(name = "project_code", nullable = true, length = 100)	
	public String getProjectCode() {
		return projectCode;
	}


	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
