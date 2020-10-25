package saaf.common.fmw.bpm.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SaafActRuExecutionEntity_HI_RO {
	
	public static final String QUERY_SQL = "";
	
	private String id_;
	private Integer rev_;
	private String procInstId_;
	private String businessKey_;
	private String parentId_;
	private String procDefId_;
	private String superExec_;
	private String actId_;
	private Byte isActive_;
	private Byte isConcurrent_;
	private Byte isScope_;
	private Byte isEventScope_;
	private Integer suspensionState_;
	private Integer cachedEntState_;
	private String tenantId_;
	private String name_;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lockTime_;

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public Integer getRev_() {
		return rev_;
	}

	public void setRev_(Integer rev_) {
		this.rev_ = rev_;
	}

	public String getProcInstId_() {
		return procInstId_;
	}

	public void setProcInstId_(String procInstId_) {
		this.procInstId_ = procInstId_;
	}

	public String getBusinessKey_() {
		return businessKey_;
	}

	public void setBusinessKey_(String businessKey_) {
		this.businessKey_ = businessKey_;
	}

	public String getParentId_() {
		return parentId_;
	}

	public void setParentId_(String parentId_) {
		this.parentId_ = parentId_;
	}

	public String getProcDefId_() {
		return procDefId_;
	}

	public void setProcDefId_(String procDefId_) {
		this.procDefId_ = procDefId_;
	}

	public String getSuperExec_() {
		return superExec_;
	}

	public void setSuperExec_(String superExec_) {
		this.superExec_ = superExec_;
	}

	public String getActId_() {
		return actId_;
	}

	public void setActId_(String actId_) {
		this.actId_ = actId_;
	}

	public Byte getIsActive_() {
		return isActive_;
	}

	public void setIsActive_(Byte isActive_) {
		this.isActive_ = isActive_;
	}

	public Byte getIsConcurrent_() {
		return isConcurrent_;
	}

	public void setIsConcurrent_(Byte isConcurrent_) {
		this.isConcurrent_ = isConcurrent_;
	}

	public Byte getIsScope_() {
		return isScope_;
	}

	public void setIsScope_(Byte isScope_) {
		this.isScope_ = isScope_;
	}

	public Byte getIsEventScope_() {
		return isEventScope_;
	}

	public void setIsEventScope_(Byte isEventScope_) {
		this.isEventScope_ = isEventScope_;
	}

	public Integer getSuspensionState_() {
		return suspensionState_;
	}

	public void setSuspensionState_(Integer suspensionState_) {
		this.suspensionState_ = suspensionState_;
	}

	public Integer getCachedEntState_() {
		return cachedEntState_;
	}

	public void setCachedEntState_(Integer cachedEntState_) {
		this.cachedEntState_ = cachedEntState_;
	}

	public String getTenantId_() {
		return tenantId_;
	}

	public void setTenantId_(String tenantId_) {
		this.tenantId_ = tenantId_;
	}

	public String getName_() {
		return name_;
	}

	public void setName_(String name_) {
		this.name_ = name_;
	}

	public Date getLockTime_() {
		return lockTime_;
	}

	public void setLockTime_(Date lockTime_) {
		this.lockTime_ = lockTime_;
	}
}
