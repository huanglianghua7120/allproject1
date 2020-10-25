package saaf.common.fmw.po.model.entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoPlanDemandHisEntity_HI Entity Object Sat Dec 30 10:59:29 CST 2017 Auto
 * Generate
 */
@Entity
@Table(name = "srm_po_plan_demand_his")
public class SrmPoPlanDemandHisEntity_HI {
	private Integer hisId;
	private Integer planDemandId;
	private Integer supplyInstId;
	private Integer instId;
	private Integer itemId;
	private String categoryCode;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date planDate;
	private String planType;
	private BigDecimal needQuantity;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date needByDate;
	private String employeeNumber;
	private String supplierNumber;
	private String specialUseNum;
	private String demandClassify;
	private Integer sourceId;
	private String handleStatus;
	private String handleMsg;
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
	private String u9HeaderId;
	private String planCode;

	public void setHisId(Integer hisId) {
		this.hisId = hisId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_PLAN_DEMANDS_S", sequenceName = "SRM_PO_PLAN_DEMANDS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_PLAN_DEMANDS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "his_id", nullable = false, length = 11)
	public Integer getHisId() {
		return hisId;
	}

	public void setPlanDemandId(Integer planDemandId) {
		this.planDemandId = planDemandId;
	}

	@Column(name = "plan_demand_id", nullable = true, length = 11)
	public Integer getPlanDemandId() {
		return planDemandId;
	}

	public void setSupplyInstId(Integer supplyInstId) {
		this.supplyInstId = supplyInstId;
	}

	@Column(name = "supply_inst_id", length = 11)
	public Integer getSupplyInstId() {
		return supplyInstId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	@Column(name = "inst_id", nullable = false, length = 11)
	public Integer getInstId() {
		return instId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = false, length = 11)
	public Integer getItemId() {
		return itemId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_code", nullable = true, length = 30)
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	@Column(name = "plan_date", nullable = true, length = 0)
	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Column(name = "plan_type", nullable = true, length = 100)
	public String getPlanType() {
		return planType;
	}

	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}

	@Column(name = "need_quantity", precision = 10, scale = 0)
	public BigDecimal getNeedQuantity() {
		return needQuantity;
	}

	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}

	@Column(name = "need_by_date", nullable = false, length = 0)
	public Date getNeedByDate() {
		return needByDate;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	@Column(name = "employee_number", nullable = true, length = 100)
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	@Column(name = "supplier_number", nullable = true, length = 100)
	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSpecialUseNum(String specialUseNum) {
		this.specialUseNum = specialUseNum;
	}

	@Column(name = "special_use_num", nullable = true, length = 240)
	public String getSpecialUseNum() {
		return specialUseNum;
	}

	public void setDemandClassify(String demandClassify) {
		this.demandClassify = demandClassify;
	}

	@Column(name = "demand_classify", nullable = true, length = 100)
	public String getDemandClassify() {
		return demandClassify;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 11)
	public Integer getSourceId() {
		return sourceId;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	@Column(name = "handle_status", nullable = true, length = 10)
	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	@Column(name = "handle_msg", nullable = true, length = 500)
	public String getHandleMsg() {
		return handleMsg;
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

	@Column(name = "U9_HEADER_ID", nullable = false, length = 0)
	public String getU9HeaderId() {
		return u9HeaderId;
	}

	public void setU9HeaderId(String u9HeaderId) {
		this.u9HeaderId = u9HeaderId;
	}

	@Column(name = "plan_code", nullable = false, length = 0)
	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

}
