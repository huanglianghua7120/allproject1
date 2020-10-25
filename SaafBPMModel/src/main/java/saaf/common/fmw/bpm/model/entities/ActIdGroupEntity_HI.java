package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * ActIdGroupEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_id_group")
public class ActIdGroupEntity_HI {
    private String id_;
    private Integer rev_;
    private String name_;
    private String type_;

//	public void setAct_id_membershipEntity_HI(List<ActIdGroupEntity_HI> Act_id_membershipEntity_HI) {
//		this.Act_id_membershipEntity_HI = Act_id_membershipEntity_HI;
//	}
//
//	@Column(name = "Act_id_membershipEntity_HI")	
//	public List<ActIdGroupEntity_HI> getAct_id_membershipEntity_HI() {
//		return Act_id_membershipEntity_HI;
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

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 255)	
	public String getType_() {
		return type_;
	}
}
