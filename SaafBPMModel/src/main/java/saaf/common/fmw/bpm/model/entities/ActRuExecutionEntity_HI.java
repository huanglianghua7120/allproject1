package saaf.common.fmw.bpm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ActRuExecutionEntity_HI Entity Object
 * Wed Aug 23 11:33:54 CST 2017  Auto Generate
 */
@Entity
@Table(name = "act_ru_execution")
public class ActRuExecutionEntity_HI {
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

//    public void setAct_ru_event_subscrEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_event_subscrEntity_HI) {
//        this.Act_ru_event_subscrEntity_HI = Act_ru_event_subscrEntity_HI;
//    }
//
//    @Column(name = "Act_ru_event_subscrEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_event_subscrEntity_HI() {
//        return Act_ru_event_subscrEntity_HI;
//    }
//
//    public void setAct_ru_executionEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_executionEntity_HI) {
//        this.Act_ru_executionEntity_HI = Act_ru_executionEntity_HI;
//    }
//
//    @Column(name = "Act_ru_executionEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_executionEntity_HI() {
//        return Act_ru_executionEntity_HI;
//    }
//
//    public void setAct_ru_executionEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_executionEntity_HI) {
//        this.Act_ru_executionEntity_HI = Act_ru_executionEntity_HI;
//    }
//
//    @Column(name = "Act_ru_executionEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_executionEntity_HI() {
//        return Act_ru_executionEntity_HI;
//    }
//
//    public void setAct_ru_executionEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_executionEntity_HI) {
//        this.Act_ru_executionEntity_HI = Act_ru_executionEntity_HI;
//    }
//
//    @Column(name = "Act_ru_executionEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_executionEntity_HI() {
//        return Act_ru_executionEntity_HI;
//    }
//
//    public void setAct_ru_identitylinkEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_identitylinkEntity_HI) {
//        this.Act_ru_identitylinkEntity_HI = Act_ru_identitylinkEntity_HI;
//    }
//
//    @Column(name = "Act_ru_identitylinkEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_identitylinkEntity_HI() {
//        return Act_ru_identitylinkEntity_HI;
//    }
//
//    public void setAct_ru_taskEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_taskEntity_HI) {
//        this.Act_ru_taskEntity_HI = Act_ru_taskEntity_HI;
//    }
//
//    @Column(name = "Act_ru_taskEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_taskEntity_HI() {
//        return Act_ru_taskEntity_HI;
//    }
//
//    public void setAct_ru_taskEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_taskEntity_HI) {
//        this.Act_ru_taskEntity_HI = Act_ru_taskEntity_HI;
//    }
//
//    @Column(name = "Act_ru_taskEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_taskEntity_HI() {
//        return Act_ru_taskEntity_HI;
//    }
//
//    public void setAct_ru_variableEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_variableEntity_HI) {
//        this.Act_ru_variableEntity_HI = Act_ru_variableEntity_HI;
//    }
//
//    @Column(name = "Act_ru_variableEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_variableEntity_HI() {
//        return Act_ru_variableEntity_HI;
//    }
//
//    public void setAct_ru_variableEntity_HI(List<ActRuExecutionEntity_HI> Act_ru_variableEntity_HI) {
//        this.Act_ru_variableEntity_HI = Act_ru_variableEntity_HI;
//    }
//
//    @Column(name = "Act_ru_variableEntity_HI")
//    public List<ActRuExecutionEntity_HI> getAct_ru_variableEntity_HI() {
//        return Act_ru_variableEntity_HI;
//    }

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

    public void setProcInstId_(String procInstId_) {
        this.procInstId_ = procInstId_;
    }

    @Column(name = "proc_inst_id_", nullable = true, length = 64)
    public String getProcInstId_() {
        return procInstId_;
    }

    public void setBusinessKey_(String businessKey_) {
        this.businessKey_ = businessKey_;
    }

    @Column(name = "business_key_", nullable = true, length = 255)
    public String getBusinessKey_() {
        return businessKey_;
    }

    public void setParentId_(String parentId_) {
        this.parentId_ = parentId_;
    }

    @Column(name = "parent_id_", nullable = true, length = 64)
    public String getParentId_() {
        return parentId_;
    }

    public void setProcDefId_(String procDefId_) {
        this.procDefId_ = procDefId_;
    }

    @Column(name = "proc_def_id_", nullable = true, length = 64)
    public String getProcDefId_() {
        return procDefId_;
    }

    public void setSuperExec_(String superExec_) {
        this.superExec_ = superExec_;
    }

    @Column(name = "super_exec_", nullable = true, length = 64)
    public String getSuperExec_() {
        return superExec_;
    }

    public void setActId_(String actId_) {
        this.actId_ = actId_;
    }

    @Column(name = "act_id_", nullable = true, length = 255)
    public String getActId_() {
        return actId_;
    }

    public void setIsActive_(Byte isActive_) {
        this.isActive_ = isActive_;
    }

    @Column(name = "is_active_", nullable = true, length = 4)
    public Byte getIsActive_() {
        return isActive_;
    }

    public void setIsConcurrent_(Byte isConcurrent_) {
        this.isConcurrent_ = isConcurrent_;
    }

    @Column(name = "is_concurrent_", nullable = true, length = 4)
    public Byte getIsConcurrent_() {
        return isConcurrent_;
    }

    public void setIsScope_(Byte isScope_) {
        this.isScope_ = isScope_;
    }

    @Column(name = "is_scope_", nullable = true, length = 4)
    public Byte getIsScope_() {
        return isScope_;
    }

    public void setIsEventScope_(Byte isEventScope_) {
        this.isEventScope_ = isEventScope_;
    }

    @Column(name = "is_event_scope_", nullable = true, length = 4)
    public Byte getIsEventScope_() {
        return isEventScope_;
    }

    public void setSuspensionState_(Integer suspensionState_) {
        this.suspensionState_ = suspensionState_;
    }

    @Column(name = "suspension_state_", nullable = true, length = 11)
    public Integer getSuspensionState_() {
        return suspensionState_;
    }

    public void setCachedEntState_(Integer cachedEntState_) {
        this.cachedEntState_ = cachedEntState_;
    }

    @Column(name = "cached_ent_state_", nullable = true, length = 11)
    public Integer getCachedEntState_() {
        return cachedEntState_;
    }

    public void setTenantId_(String tenantId_) {
        this.tenantId_ = tenantId_;
    }

    @Column(name = "tenant_id_", nullable = true, length = 255)
    public String getTenantId_() {
        return tenantId_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    @Column(name = "name_", nullable = true, length = 255)
    public String getName_() {
        return name_;
    }

    public void setLockTime_(Date lockTime_) {
        this.lockTime_ = lockTime_;
    }

    @Column(name = "lock_time_", nullable = true, length = 3)
    public Date getLockTime_() {
        return lockTime_;
    }
}
