package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmShortMaterialConfigEntity_HI Entity Object Thu Jan 04 17:15:42 CST 2018
 * Auto Generate
 */
@Entity
@Table(name = "SRM_SHORT_MATERIAL_CONFIG")
public class SrmShortMaterialConfigEntity_HI {
	private Integer id;
	private String attrName;
	private String attrDesc;
	private String attrValue;
	private String isValid;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdatedBy;
	private Integer lastUpdateLogin;
	private String attrUn;

	@Column(name = "attr_un", nullable = true, length = 100)
	public String getAttrUn() {
		return attrUn;
	}

	public void setAttrUn(String attrUn) {
		this.attrUn = attrUn;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@Column(name = "attr_name", nullable = true, length = 50)
	public String getAttrName() {
		return attrName;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	@Column(name = "attr_desc", nullable = true, length = 100)
	public String getAttrDesc() {
		return attrDesc;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	@Column(name = "attr_value", nullable = true, length = 50)
	public String getAttrValue() {
		return attrValue;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Column(name = "is_valid", nullable = true, length = 5)
	public String getIsValid() {
		return isValid;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = true, length = 0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = true, length = 0)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = true, length = 11)
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
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
