package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActHiAttachmentEntity_HI Entity Object
 * Wed Aug 23 11:33:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_hi_attachment")
public class ActHiAttachmentEntity_HI {
    private String id_;
    private Integer rev_;
    private String userId_;
    private String name_;
    private String description_;
    private String type_;
    private String taskId_;
    private String procInstId_;
    private String url_;
    private String contentId_;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time_;

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

	@Column(name = "user_id_", nullable = true, length = 255)	
	public String getUserId_() {
		return userId_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = true, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setDescription_(String description_) {
		this.description_ = description_;
	}

	@Column(name = "description_", nullable = true, length = 4000)	
	public String getDescription_() {
		return description_;
	}

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = true, length = 255)	
	public String getType_() {
		return type_;
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

	public void setUrl_(String url_) {
		this.url_ = url_;
	}

	@Column(name = "url_", nullable = true, length = 4000)	
	public String getUrl_() {
		return url_;
	}

	public void setContentId_(String contentId_) {
		this.contentId_ = contentId_;
	}

	@Column(name = "content_id_", nullable = true, length = 64)	
	public String getContentId_() {
		return contentId_;
	}

	public void setTime_(Date time_) {
		this.time_ = time_;
	}

	@Column(name = "time_", nullable = true, length = 3)	
	public Date getTime_() {
		return time_;
	}
}
