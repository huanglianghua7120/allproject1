package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActHiCommentEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_comment")
public class ActHiCommentEntity_HI {
    private String id_;
    private String type_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time_;
    private String userId_;
    private String taskId_;
    private String procInstId_;
    private String action_;
    private String message_;
    private String fullMsg_;

	public void setId_(String id_) {
		this.id_ = id_;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "id_", nullable = false, length = 64)	
	public String getId_() {
		return id_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 255)	
	public String getType_() {
		return type_;
	}

	public void setTime_(Date time_) {
		this.time_ = time_;
	}

	@Column(name = "time_", nullable = false, length = 3)	
	public Date getTime_() {
		return time_;
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

	public void setAction_(String action_) {
		this.action_ = action_;
	}

	@Column(name = "action_", nullable = true, length = 255)	
	public String getAction_() {
		return action_;
	}

	public void setMessage_(String message_) {
		this.message_ = message_;
	}

	@Column(name = "message_", nullable = true, length = 4000)	
	public String getMessage_() {
		return message_;
	}

	public void setFullMsg_(String fullMsg_) {
		this.fullMsg_ = fullMsg_;
	}

	@Column(name = "full_msg_", nullable = true, length = 0)	
	public String getFullMsg_() {
		return fullMsg_;
	}
}
