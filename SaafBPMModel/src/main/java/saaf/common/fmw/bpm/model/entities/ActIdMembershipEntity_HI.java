package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActIdMembershipEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_id_membership")
public class ActIdMembershipEntity_HI {
    private String userId_;
    private String groupId_;

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "user_id_", nullable = false, length = 64)	
	public String getUserId_() {
		return userId_;
	}

	public void setGroupId_(String groupId_) {
		this.groupId_ = groupId_;
	}

	@Column(name = "group_id_", nullable = false, length = 64)	
	public String getGroupId_() {
		return groupId_;
	}
}
