package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiIdentitylinkEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_identitylink")
public class ActHiIdentitylinkEntity_HI {
    private String id_;
    private String groupId_;
    private String type_;
    private String userId_;
    private String taskId_;
    private String procInstId_;

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setGroupId_(String groupId_) {
		this.groupId_ = groupId_;
	}

	@Column(name = "group_id_", nullable = true, length = 255)	
	public String getGroupId_() {
		return groupId_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 255)	
	public String getType_() {
		return type_;
	}

	public void setUserId_(String userId_) {
		this.userId_ = userId_;
	}

	@Column(name = "user_id_", nullable = true, length = 255)	
	public String getUserId_() {
		return userId_;
	}

	public void setTaskId_(String taskId_) {
		this.taskId_ = taskId_;
	}

	@Column(name = "task_id_", nullable = true, length = 64)	
	public String getTaskId_() {
		return taskId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = true, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}
}
