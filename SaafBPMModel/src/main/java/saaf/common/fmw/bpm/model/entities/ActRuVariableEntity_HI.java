package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActRuVariableEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ru_variable")
public class ActRuVariableEntity_HI {
    private String id_;
    private Integer rev_;
    private String type_;
    private String name_;
    private String executionId_;
    private String procInstId_;
    private String taskId_;
    private String bytearrayId_;
    private double double_;
    private Long long_;
    private String text_;
    private String text2_;

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

	public void setType_(String type_) {
		this.type_ = type_;
	}

	@Column(name = "type_", nullable = false, length = 255)	
	public String getType_() {
		return type_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	@Column(name = "name_", nullable = false, length = 255)	
	public String getName_() {
		return name_;
	}

	public void setExecutionId_(String executionId_) {
		this.executionId_ = executionId_;
	}

	@Column(name = "execution_id_", nullable = true, length = 64)	
	public String getExecutionId_() {
		return executionId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	@Column(name = "proc_inst_id_", nullable = true, length = 64)	
	public String getProcInstId_() {
		return procInstId_;
	}

	public void setTaskId_(String taskId_) {
		this.taskId_ = taskId_;
	}

	@Column(name = "task_id_", nullable = true, length = 64)	
	public String getTaskId_() {
		return taskId_;
	}

	public void setBytearrayId_(String bytearrayId_) {
		this.bytearrayId_ = bytearrayId_;
	}

	@Column(name = "bytearray_id_", nullable = true, length = 64)	
	public String getBytearrayId_() {
		return bytearrayId_;
	}

	public void setDouble_(double double_) {
		this.double_ = double_;
	}

	@Column(name = "double_", nullable = true, length = 0)	
	public double getDouble_() {
		return double_;
	}

	public void setLong_(Long long_) {
		this.long_ = long_;
	}

	@Column(name = "long_", nullable = true, length = 20)	
	public Long getLong_() {
		return long_;
	}

	public void setText_(String text_) {
		this.text_ = text_;
	}

	@Column(name = "text_", nullable = true, length = 4000)	
	public String getText_() {
		return text_;
	}

	public void setText2_(String text2_) {
		this.text2_ = text2_;
	}

	@Column(name = "text2_", nullable = true, length = 4000)	
	public String getText2_() {
		return text2_;
	}
}
