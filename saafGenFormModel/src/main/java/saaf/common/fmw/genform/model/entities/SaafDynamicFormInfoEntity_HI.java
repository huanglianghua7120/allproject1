package saaf.common.fmw.genform.model.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafDynamicFormInfoEntity_HI Entity Object Tue Jul 25 12:18:10 GMT+08:00 2017
 * Auto Generate
 */
@Entity
@Table(name = "saaf_dynamic_form_info")
public class SaafDynamicFormInfoEntity_HI {
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
	private Integer operatorUserId;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name", nullable = true, length = 200)
	public String getName() {
		return name;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "filename", nullable = true, length = 200)
	public String getFilename() {
		return filename;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 1000)
	public String getDescription() {
		return description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "url", nullable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "groupname", nullable = true, length = 50)
	public String getGroupname() {
		return groupname;
	}

	public void setRemotefilename(String remotefilename) {
		this.remotefilename = remotefilename;
	}

	@Column(name = "remotefilename", nullable = true, length = 200)
	public String getRemotefilename() {
		return remotefilename;
	}

	@Column(name = "tablename", nullable = true, length = 50)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "text")
	@Column(name = "modelsAttributes", nullable = true)
	public String getModelsAttributes() {
		return modelsAttributes;
	}

	public void setModelsAttributes(String modelsAttributes) {
		this.modelsAttributes = modelsAttributes;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", precision = 22, scale = 0)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", precision = 22, scale = 0)
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", precision = 22, scale = 0)
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
}
