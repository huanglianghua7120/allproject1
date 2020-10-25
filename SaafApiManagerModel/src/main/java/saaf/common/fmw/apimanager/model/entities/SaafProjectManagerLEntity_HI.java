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
 * SaafProjectManagerLEntity_HI Entity Object
 * Wed Sep 20 15:06:11 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_project_manager_l")
public class SaafProjectManagerLEntity_HI {
    private Integer splId;
    private String projectCode;
    private String modelName;
    private String modelCode;
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


	public void setSplId(Integer splId) {
		this.splId = splId;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "spl_id", nullable = false, length = 11)	
	public Integer getSplId() {
		return splId;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Column(name = "project_code", nullable = true, length = 100)	
	public String getProjectCode() {
		return projectCode;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "model_name", nullable = true, length = 400)	
	public String getModelName() {
		return modelName;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	@Column(name = "model_code", nullable = true, length = 400)	
	public String getModelCode() {
		return modelCode;
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
