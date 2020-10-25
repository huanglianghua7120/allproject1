package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActReDeploymentEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_re_deployment")
public class ActReDeploymentEntity_HI {
    private String id_;
    private String name_;
    private String category_;
    private String tenantId_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deployTime_;

//	public void setAct_ge_bytearrayEntity_HI(List<ActReDeploymentEntity_HI> Act_ge_bytearrayEntity_HI) {
//		this.Act_ge_bytearrayEntity_HI = Act_ge_bytearrayEntity_HI;
//	}
//
//	@Column(name = "Act_ge_bytearrayEntity_HI")	
//	public List<ActReDeploymentEntity_HI> getAct_ge_bytearrayEntity_HI() {
//		return Act_ge_bytearrayEntity_HI;
//	}
//
//	public void setAct_re_modelEntity_HI(List<ActReDeploymentEntity_HI> Act_re_modelEntity_HI) {
//		this.Act_re_modelEntity_HI = Act_re_modelEntity_HI;
//	}
//
//	@Column(name = "Act_re_modelEntity_HI")	
//	public List<ActReDeploymentEntity_HI> getAct_re_modelEntity_HI() {
//		return Act_re_modelEntity_HI;
//	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	@Column(name = "category_", nullable = true, length = 255)	
	public String getCategory_() {
		return category_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}

	public void setDeployTime_(Date deployTime_) {
		this.deployTime_ = deployTime_;
	}

	@Column(name = "deploy_time_", nullable = true, length = 3)	
	public Date getDeployTime_() {
		return deployTime_;
	}
}
