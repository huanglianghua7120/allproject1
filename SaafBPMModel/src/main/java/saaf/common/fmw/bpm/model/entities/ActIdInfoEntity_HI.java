package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActIdInfoEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_id_info")
public class ActIdInfoEntity_HI {
    private String id_;
    private Integer rev_;
    private String userId_;
    private String type_;
    private String key_;
    private String value_;
    private String password_;
    private String parentId_;

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

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	@Column(name = "user_id_", nullable = true, length = 64)	
	public String getUserId_() {
		return userId_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 64)	
	public String getType_() {
		return type_;
	}

	public void setKey_(String key_) {
		this.key_ = key_;
	}

	@Column(name = "key_", nullable = true, length = 255)	
	public String getKey_() {
		return key_;
	}

	public void setValue_(String value_) {
		this.value_ = value_;
	}

	@Column(name = "value_", nullable = true, length = 255)	
	public String getValue_() {
		return value_;
	}

	public void setPassword_(String password_) {
		this.password_ = password_;
	}

	@Column(name = "password_", nullable = true, length = 0)	
	public String getPassword_() {
		return password_;
	}

	public void setParentId_(String parentId_) {
		this.parentId_ = parentId_;
	}

	@Column(name = "parent_id_", nullable = true, length = 255)	
	public String getParentId_() {
		return parentId_;
	}
}
