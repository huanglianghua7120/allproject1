package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosFrozenInfoEntity_HI Entity Object
 * Wed Nov 15 10:53:53 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_frozen_info")
public class SrmPosFrozenInfoEntity_HI {
    private Integer frozenId;
    private Integer supplierId;
    private Integer orgId;
    private String freezeType;
    private String freezeNumber;
    private String freezeStatus;
    private String freezingInstructions;
    private String forbidPurchaseFlag;
    private String forbidPaymentFlag;
    private String description;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date unfrozenDate ;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private String attribute8;
    private String attribute9;
    private String attribute10;
    
    private Integer frozenFileId;
    private Integer approveBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approveDate;
    private Integer operatorUserId;
    
    public void setFrozenId(Integer frozenId) {
	this.frozenId = frozenId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_FROZEN_INFO_S", sequenceName = "SRM_POS_FROZEN_INFO_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_FROZEN_INFO_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "frozen_id", nullable = false, length = 11)    
    public Integer getFrozenId() {
	return frozenId;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = false, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setOrgId(Integer orgId) {
	this.orgId = orgId;
    }

    @Column(name = "org_id", nullable = true, length = 11)    
    public Integer getOrgId() {
	return orgId;
    }

    public void setFreezeType(String freezeType) {
	this.freezeType = freezeType;
    }

    @Column(name = "freeze_type", nullable = false, length = 30)    
    public String getFreezeType() {
	return freezeType;
    }

    public void setFreezeNumber(String freezeNumber) {
	this.freezeNumber = freezeNumber;
    }

    @Column(name = "freeze_number", nullable = true, length = 100)    
    public String getFreezeNumber() {
	return freezeNumber;
    }

    public void setFreezeStatus(String freezeStatus) {
	this.freezeStatus = freezeStatus;
    }

    @Column(name = "freeze_status", nullable = true, length = 30)    
    public String getFreezeStatus() {
	return freezeStatus;
    }

    public void setFreezingInstructions(String freezingInstructions) {
	this.freezingInstructions = freezingInstructions;
    }

    @Column(name = "freezing_instructions", nullable = true, length = 240)    
    public String getFreezingInstructions() {
	return freezingInstructions;
    }

    public void setForbidPurchaseFlag(String forbidPurchaseFlag) {
	this.forbidPurchaseFlag = forbidPurchaseFlag;
    }

    @Column(name = "forbid_purchase_flag", nullable = true, length = 10)    
    public String getForbidPurchaseFlag() {
	return forbidPurchaseFlag;
    }

    public void setForbidPaymentFlag(String forbidPaymentFlag) {
	this.forbidPaymentFlag = forbidPaymentFlag;
    }

    @Column(name = "forbid_payment_flag", nullable = true, length = 10)    
    public String getForbidPaymentFlag() {
	return forbidPaymentFlag;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)    
    public String getDescription() {
	return description;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

    @Column(name = "version_num", nullable = true, length = 11)    
    public Integer getVersionNum() {
	return versionNum;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    @Column(name = "creation_date", nullable = false, length = 0)    
    public Date getCreationDate() {
	return creationDate;
    }

    @Column(name = "unfrozen_date", nullable = false, length = 0)    
    public Date getUnfrozenDate() {
		return unfrozenDate;
	}

	public void setUnfrozenDate(Date unfrozenDate) {
		this.unfrozenDate = unfrozenDate;
	}

	public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
    }

    @Column(name = "created_by", nullable = false, length = 11)    
    public Integer getCreatedBy() {
	return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
	this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", nullable = false, length = 11)    
    public Integer getLastUpdatedBy() {
	return lastUpdatedBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_update_date", nullable = false, length = 0)    
    public Date getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
	this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "last_update_login", nullable = true, length = 11)    
    public Integer getLastUpdateLogin() {
	return lastUpdateLogin;
    }

    public void setAttributeCategory(String attributeCategory) {
	this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute_category", nullable = true, length = 30)    
    public String getAttributeCategory() {
	return attributeCategory;
    }

    public void setAttribute1(String attribute1) {
	this.attribute1 = attribute1;
    }

    @Column(name = "attribute1", nullable = true, length = 240)    
    public String getAttribute1() {
	return attribute1;
    }

    public void setAttribute2(String attribute2) {
	this.attribute2 = attribute2;
    }

    @Column(name = "attribute2", nullable = true, length = 240)    
    public String getAttribute2() {
	return attribute2;
    }

    public void setAttribute3(String attribute3) {
	this.attribute3 = attribute3;
    }

    @Column(name = "attribute3", nullable = true, length = 240)    
    public String getAttribute3() {
	return attribute3;
    }

    public void setAttribute4(String attribute4) {
	this.attribute4 = attribute4;
    }

    @Column(name = "attribute4", nullable = true, length = 240)    
    public String getAttribute4() {
	return attribute4;
    }

    public void setAttribute5(String attribute5) {
	this.attribute5 = attribute5;
    }

    @Column(name = "attribute5", nullable = true, length = 240)    
    public String getAttribute5() {
	return attribute5;
    }

    public void setAttribute6(String attribute6) {
	this.attribute6 = attribute6;
    }

    @Column(name = "attribute6", nullable = true, length = 240)    
    public String getAttribute6() {
	return attribute6;
    }

    public void setAttribute7(String attribute7) {
	this.attribute7 = attribute7;
    }

    @Column(name = "attribute7", nullable = true, length = 240)    
    public String getAttribute7() {
	return attribute7;
    }

    public void setAttribute8(String attribute8) {
	this.attribute8 = attribute8;
    }

    @Column(name = "attribute8", nullable = true, length = 240)    
    public String getAttribute8() {
	return attribute8;
    }

    public void setAttribute9(String attribute9) {
	this.attribute9 = attribute9;
    }

    @Column(name = "attribute9", nullable = true, length = 240)    
    public String getAttribute9() {
	return attribute9;
    }

    public void setAttribute10(String attribute10) {
	this.attribute10 = attribute10;
    }

    @Column(name = "attribute10", nullable = true, length = 240)    
    public String getAttribute10() {
	return attribute10;
    }
    
    @Column(name = "frozen_file_id", nullable = true, length = 11) 
	public Integer getFrozenFileId() {
		return frozenFileId;
	}

	public void setFrozenFileId(Integer frozenFileId) {
		this.frozenFileId = frozenFileId;
	}
	
	
	@Column(name = "approve_by", nullable = true, length = 11) 
	public Integer getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(Integer approveBy) {
		this.approveBy = approveBy;
	}
	
	@Column(name = "approve_date", nullable = true, length = 0) 
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
    
    
}

