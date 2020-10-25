package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActGePropertyEntity_HI Entity Object
 * Wed Aug 23 11:33:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ge_property")
public class ActGePropertyEntity_HI {
    private String name_;
    private String value_;
    private Integer rev_;

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "name_", nullable = false, length = 64)	
	public String getName_() {
		return name_;
	}

	public void setValue_(String value_) {
		this.value_ = value_;
	}

	@Column(name = "value_", nullable = true, length = 300)	
	public String getValue_() {
		return value_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	@Column(name = "rev_", nullable = true, length = 11)	
	public Integer getRev_() {
		return rev_;
	}
}
