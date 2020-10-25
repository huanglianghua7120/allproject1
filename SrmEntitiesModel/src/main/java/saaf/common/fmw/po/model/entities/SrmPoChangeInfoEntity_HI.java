package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmPoChangeInfoEntity_HI Entity Object
 * Wed Dec 06 18:44:34 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_PO_CHANGE_INFO")
public class SrmPoChangeInfoEntity_HI {
    private Integer poChangeId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;
    private String poNumber;
    private Integer poLineNum;
    private Integer poHeaderId;
    private Integer poLineId;
    private String changeType;
    private BigDecimal orderQuantity;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date needByDate;
    private String status;
    private BigDecimal oldOrderQuantity;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date oldNeedByDate;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
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
    private BigDecimal originQuantity;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date originDate;

    public void setPoChangeId(Integer poChangeId) {
	this.poChangeId = poChangeId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_CHANGE_INFO_S", sequenceName = "SRM_PO_CHANGE_INFO_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_CHANGE_INFO_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "po_change_id", nullable = false, length = 11)    
    public Integer getPoChangeId() {
	return poChangeId;
    }
    

    public void setPlanDate(Date planDate) {
	this.planDate = planDate;
    }

    @Column(name = "plan_date", nullable = false, length = 0)    
    public Date getPlanDate() {
	return planDate;
    }

    public void setPoNumber(String poNumber) {
	this.poNumber = poNumber;
    }

    @Column(name = "po_number", nullable = false, length = 100)    
    public String getPoNumber() {
	return poNumber;
    }

    public void setPoLineNum(Integer poLineNum) {
	this.poLineNum = poLineNum;
    }

    @Column(name = "po_line_num", nullable = false, length = 11)    
    public Integer getPoLineNum() {
	return poLineNum;
    }

    public void setPoHeaderId(Integer poHeaderId) {
	this.poHeaderId = poHeaderId;
    }

    @Column(name = "po_header_id", nullable = false, length = 11)    
    public Integer getPoHeaderId() {
	return poHeaderId;
    }

    public void setPoLineId(Integer poLineId) {
	this.poLineId = poLineId;
    }

    @Column(name = "po_line_id", nullable = false, length = 11)    
    public Integer getPoLineId() {
	return poLineId;
    }

    public void setChangeType(String changeType) {
	this.changeType = changeType;
    }

    @Column(name = "change_type", nullable = false, length = 30)    
    public String getChangeType() {
	return changeType;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
	this.orderQuantity = orderQuantity;
    }

    @Column(name = "order_quantity", precision = 20, scale = 6)
    public BigDecimal getOrderQuantity() {
	return orderQuantity;
    }

    public void setNeedByDate(Date needByDate) {
	this.needByDate = needByDate;
    }

    @Column(name = "need_by_date", nullable = true, length = 0)    
    public Date getNeedByDate() {
	return needByDate;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Column(name = "status", nullable = false, length = 30)    
    public String getStatus() {
	return status;
    }

    public void setOldOrderQuantity(BigDecimal oldOrderQuantity) {
	this.oldOrderQuantity = oldOrderQuantity;
    }

    @Column(name = "old_order_quantity", precision = 20, scale = 6)
    public BigDecimal getOldOrderQuantity() {
	return oldOrderQuantity;
    }

    public void setOldNeedByDate(Date oldNeedByDate) {
	this.oldNeedByDate = oldNeedByDate;
    }

    @Column(name = "old_need_by_date", nullable = true, length = 0)    
    public Date getOldNeedByDate() {
	return oldNeedByDate;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }
    @Version
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

    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
    
    @Column(name = "origin_quantity", precision = 20, scale = 6)
    public BigDecimal getOriginQuantity() {
		return originQuantity;
	}
     
	public void setOriginQuantity(BigDecimal originQuantity) {
		this.originQuantity = originQuantity;
	}
    @Column(name = "origin_date", nullable = true, length = 0)  
	public Date getOriginDate() {
		return originDate;
	}

	public void setOriginDate(Date originDate) {
		this.originDate = originDate;
	}
}

