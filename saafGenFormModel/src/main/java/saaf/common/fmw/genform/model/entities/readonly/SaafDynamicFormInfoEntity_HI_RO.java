package saaf.common.fmw.genform.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafDynamicFormInfoEntity_HI_RO Entity Object Tue Jul 25 12:18:10 GMT+08:00
 * 2017 Auto Generate
 */

public class SaafDynamicFormInfoEntity_HI_RO {
	private Integer id;
	private String name;
	private String filename;
	private String description;
	private String url;
	private String groupname;
	private String remotefilename;
	private String tableName;
	private String modelsAttributes;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setRemotefilename(String remotefilename) {
		this.remotefilename = remotefilename;
	}

	public String getRemotefilename() {
		return remotefilename;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getModelsAttributes() {
		return modelsAttributes;
	}

	public void setModelsAttributes(String modelsAttributes) {
		this.modelsAttributes = modelsAttributes;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

}
