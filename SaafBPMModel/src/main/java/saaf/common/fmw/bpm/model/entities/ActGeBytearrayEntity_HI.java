package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * ActGeBytearrayEntity_HI Entity Object
 * Wed Aug 23 11:33:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ge_bytearray")
public class ActGeBytearrayEntity_HI {
    private String id_;
    private Integer rev_;
    private String name_;
    private String deploymentId_;
    private byte[] bytes_;
    private Byte generated_;

//	public void setAct_re_modelEntity_HI(List<ActGeBytearrayEntity_HI> Act_re_modelEntity_HI) {
//		this.Act_re_modelEntity_HI = Act_re_modelEntity_HI;
//	}
//
//	@Column(name = "Act_re_modelEntity_HI")	
//	public List<ActGeBytearrayEntity_HI> getAct_re_modelEntity_HI() {
//		return Act_re_modelEntity_HI;
//	}
//
//	public void setAct_re_modelEntity_HI(List<ActGeBytearrayEntity_HI> Act_re_modelEntity_HI) {
//		this.Act_re_modelEntity_HI = Act_re_modelEntity_HI;
//	}
//
//	@Column(name = "Act_re_modelEntity_HI")	
//	public List<ActGeBytearrayEntity_HI> getAct_re_modelEntity_HI() {
//		return Act_re_modelEntity_HI;
//	}
//
//	public void setAct_ru_jobEntity_HI(List<ActGeBytearrayEntity_HI> Act_ru_jobEntity_HI) {
//		this.Act_ru_jobEntity_HI = Act_ru_jobEntity_HI;
//	}
//
//	@Column(name = "Act_ru_jobEntity_HI")	
//	public List<ActGeBytearrayEntity_HI> getAct_ru_jobEntity_HI() {
//		return Act_ru_jobEntity_HI;
//	}
//
//	public void setAct_ru_variableEntity_HI(List<ActGeBytearrayEntity_HI> Act_ru_variableEntity_HI) {
//		this.Act_ru_variableEntity_HI = Act_ru_variableEntity_HI;
//	}
//
//	@Column(name = "Act_ru_variableEntity_HI")	
//	public List<ActGeBytearrayEntity_HI> getAct_ru_variableEntity_HI() {
//		return Act_ru_variableEntity_HI;
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

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setDeploymentId_(String deploymentId_) {
		this.deploymentId_ = deploymentId_;
	}

	@Column(name = "deployment_id_", nullable = true, length = 64)	
	public String getDeploymentId_() {
		return deploymentId_;
	}

	public void setBytes_(byte[] bytes_) {
		this.bytes_ = bytes_;
	}

	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "bytes_",columnDefinition="BLOB", nullable = true, length = 0)	
	public byte[] getBytes_() {
		return bytes_;
	}

	public void setGenerated_(Byte generated_) {
		this.generated_ = generated_;
	}

	@Column(name = "generated_", nullable = true, length = 4)	
	public Byte getGenerated_() {
		return generated_;
	}
}
