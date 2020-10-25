package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActReModelEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_re_model")
public class ActReModelEntity_HI {
    private String id_;
    private Integer rev_;
    private String name_;
    private String key_;
    private String category_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime_;
    private Integer version_;
    private String metaInfo_;
    private String deploymentId_;
    private String editorSourceValueId_;
    private String editorSourceExtraValueId_;
    private String tenantId_;

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	@Column(name = "rev_", nullable = true, length = 11)	
	public Integer getRev_() {
		return rev_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

	@Column(name = "key_", nullable = true, length = 255)	
	public String getKey_() {
		return key_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	@Column(name = "category_", nullable = true, length = 255)	
	public String getCategory_() {
		return category_;
	}

	public void setCreateTime_(Date createTime_) {
		this.createTime_ = createTime_;
	}

	@Column(name = "create_time_", nullable = true, length = 3)	
	public Date getCreateTime_() {
		return createTime_;
	}

	public void setLastUpdateTime_(Date lastUpdateTime_) {
		this.lastUpdateTime_ = lastUpdateTime_;
	}

	@Column(name = "last_update_time_", nullable = true, length = 3)	
	public Date getLastUpdateTime_() {
		return lastUpdateTime_;
	}

	public void setVersion_(Integer version_) {
		this.version_ = version_;
	}

	@Column(name = "version_", nullable = true, length = 11)	
	public Integer getVersion_() {
		return version_;
	}

	public void setMetaInfo_(String metaInfo_) {
		this.metaInfo_ = metaInfo_;
	}

	@Column(name = "meta_info_", nullable = true, length = 4000)	
	public String getMetaInfo_() {
		return metaInfo_;
	}

	public void setDeploymentId_(String deploymentId_) {
		this.deploymentId_ = deploymentId_;
	}

	@Column(name = "deployment_id_", nullable = true, length = 64)	
	public String getDeploymentId_() {
		return deploymentId_;
	}

	public void setEditorSourceValueId_(String editorSourceValueId_) {
		this.editorSourceValueId_ = editorSourceValueId_;
	}

	@Column(name = "editor_source_value_id_", nullable = true, length = 64)	
	public String getEditorSourceValueId_() {
		return editorSourceValueId_;
	}

	public void setEditorSourceExtraValueId_(String editorSourceExtraValueId_) {
		this.editorSourceExtraValueId_ = editorSourceExtraValueId_;
	}

	@Column(name = "editor_source_extra_value_id_", nullable = true, length = 64)	
	public String getEditorSourceExtraValueId_() {
		return editorSourceExtraValueId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
