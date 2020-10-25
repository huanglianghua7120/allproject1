package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * ActReProcdefEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_re_procdef")
public class ActReProcdefEntity_HI {
    private String id_;
    private Integer rev_;
    private String category_;
    private String name_;
    private String key_;
    private Integer version_;
    private String deploymentId_;
    private String resourceName_;
    private String dgrmResourceName_;
    private String description_;
    private Byte hasStartFormKey_;
    private Byte hasGraphicalNotation_;
    private Integer suspensionState_;
    private String tenantId_;

//	public void setAct_ru_executionEntity_HI(List<ActReProcdefEntity_HI> Act_ru_executionEntity_HI) {
//		this.Act_ru_executionEntity_HI = Act_ru_executionEntity_HI;
//	}
//
//	@Column(name = "Act_ru_executionEntity_HI")	
//	public List<ActReProcdefEntity_HI> getAct_ru_executionEntity_HI() {
//		return Act_ru_executionEntity_HI;
//	}
//
//	public void setAct_ru_identitylinkEntity_HI(List<ActReProcdefEntity_HI> Act_ru_identitylinkEntity_HI) {
//		this.Act_ru_identitylinkEntity_HI = Act_ru_identitylinkEntity_HI;
//	}
//
//	@Column(name = "Act_ru_identitylinkEntity_HI")	
//	public List<ActReProcdefEntity_HI> getAct_ru_identitylinkEntity_HI() {
//		return Act_ru_identitylinkEntity_HI;
//	}
//
//	public void setAct_ru_taskEntity_HI(List<ActReProcdefEntity_HI> Act_ru_taskEntity_HI) {
//		this.Act_ru_taskEntity_HI = Act_ru_taskEntity_HI;
//	}
//
//	@Column(name = "Act_ru_taskEntity_HI")	
//	public List<ActReProcdefEntity_HI> getAct_ru_taskEntity_HI() {
//		return Act_ru_taskEntity_HI;
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

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	@Column(name = "rev_", nullable = true, length = 11)	
	public Integer getRev_() {
		return rev_;
	}

	public void setCategory_(String category_) {
		this.category_ = category_;
	}

	@Column(name = "category_", nullable = true, length = 255)	
	public String getCategory_() {
		return category_;
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

	@Column(name = "key_", nullable = false, length = 255)	
	public String getKey_() {
		return key_;
	}

	public void setVersion_(Integer version_) {
		this.version_ = version_;
	}

	@Column(name = "version_", nullable = false, length = 11)	
	public Integer getVersion_() {
		return version_;
	}

	public void setDeploymentId_(String deploymentId_) {
		this.deploymentId_ = deploymentId_;
	}

	@Column(name = "deployment_id_", nullable = true, length = 64)	
	public String getDeploymentId_() {
		return deploymentId_;
	}

	public void setResourceName_(String resourceName_) {
		this.resourceName_ = resourceName_;
	}

	@Column(name = "resource_name_", nullable = true, length = 4000)	
	public String getResourceName_() {
		return resourceName_;
	}

	public void setDgrmResourceName_(String dgrmResourceName_) {
		this.dgrmResourceName_ = dgrmResourceName_;
	}

	@Column(name = "dgrm_resource_name_", nullable = true, length = 4000)	
	public String getDgrmResourceName_() {
		return dgrmResourceName_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}

	@Column(name = "description_", nullable = true, length = 4000)	
	public String getDescription_() {
		return description_;
	}

	public void setHasStartFormKey_(Byte hasStartFormKey_) {
		this.hasStartFormKey_ = hasStartFormKey_;
	}

	@Column(name = "has_start_form_key_", nullable = true, length = 4)	
	public Byte getHasStartFormKey_() {
		return hasStartFormKey_;
	}

	public void setHasGraphicalNotation_(Byte hasGraphicalNotation_) {
		this.hasGraphicalNotation_ = hasGraphicalNotation_;
	}

	@Column(name = "has_graphical_notation_", nullable = true, length = 4)	
	public Byte getHasGraphicalNotation_() {
		return hasGraphicalNotation_;
	}

	public void setSuspensionState_(Integer suspensionState_) {
		this.suspensionState_ = suspensionState_;
	}

	@Column(name = "suspension_state_", nullable = true, length = 11)	
	public Integer getSuspensionState_() {
		return suspensionState_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	@Column(name = "tenant_id_", nullable = true, length = 255)	
	public String getTenantId_() {
		return tenantId_;
	}
}
