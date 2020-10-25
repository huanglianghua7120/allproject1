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
 * SaafProjectApiMamagerEntity_HI Entity Object
 * Wed Sep 20 09:58:34 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_project_api_mamager")
public class SaafProjectApiMamagerEntity_HI {
    private Integer spaId;
    private String interfaceName;
    private String requestMode;
    private String spaStatus;
    private String urlAddress;
    private String spaDesc;
    private String param;
    private String paramDict;
    private String returnedValue;
    private String returnedValueDict;
    private String projectName;
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
    private String developer;
    
    
    
    
    public void setLastUpdateLogin(Integer lastUpdateLogin) {
    	this.lastUpdateLogin = lastUpdateLogin;
    }
    @Transient	
    public Integer getLastUpdateLogin() {
    	return lastUpdateLogin;
    }


	public void setSpaId(Integer spaId) {
		this.spaId = spaId;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "spa_id", nullable = false, length = 11)	
	public Integer getSpaId() {
		return spaId;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	@Column(name = "developer", nullable = true, length = 400)	
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	@Column(name = "interface_name", nullable = true, length = 400)	
	public String getInterfaceName() {
		return interfaceName;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}

	@Column(name = "request_mode", nullable = true, length = 100)	
	public String getRequestMode() {
		return requestMode;
	}

	public void setSpaStatus(String spaStatus) {
		this.spaStatus = spaStatus;
	}

	@Column(name = "spa_status", nullable = true, length = 100)	
	public String getSpaStatus() {
		return spaStatus;
	}

	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
	}

	@Column(name = "url_address", nullable = true, length = 400)	
	public String getUrlAddress() {
		return urlAddress;
	}

	public void setSpaDesc(String spaDesc) {
		this.spaDesc = spaDesc;
	}

	@Column(name = "spa_desc", nullable = true, length = 400)	
	public String getSpaDesc() {
		return spaDesc;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Column(name = "param", nullable = true, length = 100)	
	public String getParam() {
		return param;
	}

	public void setParamDict(String paramDict) {
		this.paramDict = paramDict;
	}

	@Column(name = "param_dict", nullable = true, length = 100)	
	public String getParamDict() {
		return paramDict;
	}

	public void setReturnedValue(String returnedValue) {
		this.returnedValue = returnedValue;
	}

	@Column(name = "returned_value", nullable = true, length = 100)	
	public String getReturnedValue() {
		return returnedValue;
	}

	public void setReturnedValueDict(String returnedValueDict) {
		this.returnedValueDict = returnedValueDict;
	}

	@Column(name = "returned_value_dict", nullable = true, length = 100)	
	public String getReturnedValueDict() {
		return returnedValueDict;
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
