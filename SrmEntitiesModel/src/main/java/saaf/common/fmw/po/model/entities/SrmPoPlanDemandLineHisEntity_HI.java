package saaf.common.fmw.po.model.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoPlanDemandLineHisEntity_HI Entity Object
 * Sat Dec 30 10:59:36 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_po_plan_demand_line_his")
public class SrmPoPlanDemandLineHisEntity_HI {
    private Integer hisId;
    private Integer lineId;
    private Integer planDemandId;
    private Integer supplierId;
    private BigDecimal distributeQuantity;
    private BigDecimal poQuantity;
    private BigDecimal proportion;
    private BigDecimal monthProportion;
    private BigDecimal dayCapacity;
    private String isApprovedList;
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

    public void setHisId(Integer hisId) {
	this.hisId = hisId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_PLAN_DEMAND_LINE_S", sequenceName = "SRM_PO_PLAN_DEMAND_LINE_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_PLAN_DEMAND_LINE_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "his_id", nullable = false, length = 11)    
    public Integer getHisId() {
	return hisId;
    }

    public void setLineId(Integer lineId) {
	this.lineId = lineId;
    }

    @Column(name = "line_id", nullable = true, length = 11)    
    public Integer getLineId() {
	return lineId;
    }

    public void setPlanDemandId(Integer planDemandId) {
	this.planDemandId = planDemandId;
    }

    @Column(name = "plan_demand_id", nullable = false, length = 11)    
    public Integer getPlanDemandId() {
	return planDemandId;
    }

    public void setSupplierId(Integer supplierId) {
	this.supplierId = supplierId;
    }

    @Column(name = "supplier_id", nullable = true, length = 11)    
    public Integer getSupplierId() {
	return supplierId;
    }

    public void setDistributeQuantity(BigDecimal distributeQuantity) {
	this.distributeQuantity = distributeQuantity;
    }

    @Column(name = "distribute_quantity", precision = 10, scale = 0)    
    public BigDecimal getDistributeQuantity() {
	return distributeQuantity;
    }

    public void setPoQuantity(BigDecimal poQuantity) {
	this.poQuantity = poQuantity;
    }

    @Column(name = "po_quantity", precision = 10, scale = 0)    
    public BigDecimal getPoQuantity() {
	return poQuantity;
    }

    public void setProportion(BigDecimal proportion) {
	this.proportion = proportion;
    }

    @Column(name = "proportion", precision = 10, scale = 0)    
    public BigDecimal getProportion() {
	return proportion;
    }

    public void setMonthProportion(BigDecimal monthProportion) {
	this.monthProportion = monthProportion;
    }

    @Column(name = "month_proportion", precision = 10, scale = 0)    
    public BigDecimal getMonthProportion() {
	return monthProportion;
    }

    public void setDayCapacity(BigDecimal dayCapacity) {
	this.dayCapacity = dayCapacity;
    }

    @Column(name = "day_capacity", precision = 10, scale = 0)    
    public BigDecimal getDayCapacity() {
	return dayCapacity;
    }

    public void setIsApprovedList(String isApprovedList) {
	this.isApprovedList = isApprovedList;
    }

    @Column(name = "is_approved_list", nullable = true, length = 10)    
    public String getIsApprovedList() {
	return isApprovedList;
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
}

