package saaf.common.fmw.po.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmShortMaterialWarmEntity_HI Entity Object Tue Jan 23 11:47:35 CST 2018 Auto
 * Generate
 */
@Entity
@Table(name = "SRM_SHORT_MATERIAL_WARM")
public class SrmShortMaterialWarmEntity_HI {
	private Integer id;
	private String instId;
	private String instName;
	private String itemcode;
	private String itemname;
	private String seibancode;
	private String unit;
	private String itemflag;
	private String gjfl;
	private String gjfln;
	private String blfl;
	private String whman;
	private String whmanname;
	private String employee;
	private BigDecimal t0Qty;
	private BigDecimal t0InvQty;
	private BigDecimal t0DifferQty;
	private BigDecimal t1Qty;
	private BigDecimal t1InvQty;
	private BigDecimal t1DifferQty;
	private BigDecimal t2Qty;
	private BigDecimal t2InvQty;
	private BigDecimal t2DifferQty;
	private BigDecimal t3Qty;
	private BigDecimal t3InvQty;
	private BigDecimal t3DifferQty;
	private BigDecimal t4Qty;
	private BigDecimal t4InvQty;
	private BigDecimal t4DifferQty;
	private BigDecimal t5Qty;
	private BigDecimal t5InvQty;
	private BigDecimal t5DifferQty;
	private BigDecimal t6Qty;
	private BigDecimal t6InvQty;
	private BigDecimal t6DifferQty;
	private BigDecimal t7Qty;
	private BigDecimal t7InvQty;
	private BigDecimal t7DifferQty;
	private BigDecimal total3daysQty;
	private BigDecimal total3daysDifferQty;
	private String urgent;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;
	private Integer lastUpdateLogin;
	private Integer createdBy;
	private String categoryCode;
	private String categoryName;
	private String itemProp;
	private String itemPropDesc;
	
	private String mrpcode;
	
	public void setMrpcode(String mrpcode) {
		this.mrpcode = mrpcode;
	}

	@Column(name = "mrpcode", nullable = true, length = 100)
	public String getMrpcode() {
		return mrpcode;
	}

	public void setItemProp(String itemProp) {
		this.itemProp = itemProp;
	}

	@Column(name = "item_prop", nullable = true, length = 10)
	public String getItemProp() {
		return itemProp;
	}

	public void setItemPropDesc(String itemPropDesc) {
		this.itemPropDesc = itemPropDesc;
	}

	@Column(name = "item_prop_desc", nullable = true, length = 100)
	public String getItemPropDesc() {
		return itemPropDesc;
	}

	@Column(name = "category_code", nullable = true, length = 100)
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_name", nullable = true, length = 100)
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy == null ? -1 : createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 11)
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	@Column(name = "inst_id", nullable = true, length = 50)
	public String getInstId() {
		return instId;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	@Column(name = "inst_name", nullable = true, length = 50)
	public String getInstName() {
		return instName;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	@Column(name = "itemcode", nullable = true, length = 50)
	public String getItemcode() {
		return itemcode;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	@Column(name = "itemname", nullable = true, length = 250)
	public String getItemname() {
		return itemname;
	}

	public void setSeibancode(String seibancode) {
		this.seibancode = seibancode;
	}

	@Column(name = "seibancode", nullable = true, length = 30)
	public String getSeibancode() {
		return seibancode;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "unit", nullable = true, length = 30)
	public String getUnit() {
		return unit;
	}

	public void setItemflag(String itemflag) {
		this.itemflag = itemflag;
	}

	@Column(name = "itemflag", nullable = true, length = 100)
	public String getItemflag() {
		return itemflag;
	}

	public void setGjfl(String gjfl) {
		this.gjfl = gjfl;
	}

	@Column(name = "gjfl", nullable = true, length = 50)
	public String getGjfl() {
		return gjfl;
	}

	public void setGjfln(String gjfln) {
		this.gjfln = gjfln;
	}

	@Column(name = "gjfln", nullable = true, length = 100)
	public String getGjfln() {
		return gjfln;
	}

	public void setBlfl(String blfl) {
		this.blfl = blfl;
	}

	@Column(name = "blfl", nullable = true, length = 50)
	public String getBlfl() {
		return blfl;
	}

	public void setWhman(String whman) {
		this.whman = whman;
	}

	@Column(name = "whman", nullable = true, length = 50)
	public String getWhman() {
		return whman;
	}

	public void setWhmanname(String whmanname) {
		this.whmanname = whmanname;
	}

	@Column(name = "whmanname", nullable = true, length = 100)
	public String getWhmanname() {
		return whmanname;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	@Column(name = "employee", nullable = true, length = 20)
	public String getEmployee() {
		return employee;
	}

	public void setT0Qty(BigDecimal t0Qty) {
		this.t0Qty = t0Qty;
	}

	@Column(name = "t0_qty", precision = 24, scale = 9)
	public BigDecimal getT0Qty() {
		return t0Qty;
	}

	public void setT0InvQty(BigDecimal t0InvQty) {
		this.t0InvQty = t0InvQty;
	}

	@Column(name = "t0_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT0InvQty() {
		return t0InvQty;
	}

	public void setT0DifferQty(BigDecimal t0DifferQty) {
		this.t0DifferQty = t0DifferQty;
	}

	@Column(name = "t0_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT0DifferQty() {
		return t0DifferQty;
	}

	public void setT1Qty(BigDecimal t1Qty) {
		this.t1Qty = t1Qty;
	}

	@Column(name = "t1_qty", precision = 24, scale = 9)
	public BigDecimal getT1Qty() {
		return t1Qty;
	}

	public void setT1InvQty(BigDecimal t1InvQty) {
		this.t1InvQty = t1InvQty;
	}

	@Column(name = "t1_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT1InvQty() {
		return t1InvQty;
	}

	public void setT1DifferQty(BigDecimal t1DifferQty) {
		this.t1DifferQty = t1DifferQty;
	}

	@Column(name = "t1_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT1DifferQty() {
		return t1DifferQty;
	}

	public void setT2Qty(BigDecimal t2Qty) {
		this.t2Qty = t2Qty;
	}

	@Column(name = "t2_qty", precision = 24, scale = 9)
	public BigDecimal getT2Qty() {
		return t2Qty;
	}

	public void setT2InvQty(BigDecimal t2InvQty) {
		this.t2InvQty = t2InvQty;
	}

	@Column(name = "t2_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT2InvQty() {
		return t2InvQty;
	}

	public void setT2DifferQty(BigDecimal t2DifferQty) {
		this.t2DifferQty = t2DifferQty;
	}

	@Column(name = "t2_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT2DifferQty() {
		return t2DifferQty;
	}

	public void setT3Qty(BigDecimal t3Qty) {
		this.t3Qty = t3Qty;
	}

	@Column(name = "t3_qty", precision = 24, scale = 9)
	public BigDecimal getT3Qty() {
		return t3Qty;
	}

	public void setT3InvQty(BigDecimal t3InvQty) {
		this.t3InvQty = t3InvQty;
	}

	@Column(name = "t3_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT3InvQty() {
		return t3InvQty;
	}

	public void setT3DifferQty(BigDecimal t3DifferQty) {
		this.t3DifferQty = t3DifferQty;
	}

	@Column(name = "t3_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT3DifferQty() {
		return t3DifferQty;
	}

	public void setT4Qty(BigDecimal t4Qty) {
		this.t4Qty = t4Qty;
	}

	@Column(name = "t4_qty", precision = 24, scale = 9)
	public BigDecimal getT4Qty() {
		return t4Qty;
	}

	public void setT4InvQty(BigDecimal t4InvQty) {
		this.t4InvQty = t4InvQty;
	}

	@Column(name = "t4_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT4InvQty() {
		return t4InvQty;
	}

	public void setT4DifferQty(BigDecimal t4DifferQty) {
		this.t4DifferQty = t4DifferQty;
	}

	@Column(name = "t4_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT4DifferQty() {
		return t4DifferQty;
	}

	public void setT5Qty(BigDecimal t5Qty) {
		this.t5Qty = t5Qty;
	}

	@Column(name = "t5_qty", precision = 24, scale = 9)
	public BigDecimal getT5Qty() {
		return t5Qty;
	}

	public void setT5InvQty(BigDecimal t5InvQty) {
		this.t5InvQty = t5InvQty;
	}

	@Column(name = "t5_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT5InvQty() {
		return t5InvQty;
	}

	public void setT5DifferQty(BigDecimal t5DifferQty) {
		this.t5DifferQty = t5DifferQty;
	}

	@Column(name = "t5_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT5DifferQty() {
		return t5DifferQty;
	}

	public void setT6Qty(BigDecimal t6Qty) {
		this.t6Qty = t6Qty;
	}

	@Column(name = "t6_qty", precision = 24, scale = 9)
	public BigDecimal getT6Qty() {
		return t6Qty;
	}

	public void setT6InvQty(BigDecimal t6InvQty) {
		this.t6InvQty = t6InvQty;
	}

	@Column(name = "t6_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT6InvQty() {
		return t6InvQty;
	}

	public void setT6DifferQty(BigDecimal t6DifferQty) {
		this.t6DifferQty = t6DifferQty;
	}

	@Column(name = "t6_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT6DifferQty() {
		return t6DifferQty;
	}

	public void setT7Qty(BigDecimal t7Qty) {
		this.t7Qty = t7Qty;
	}

	@Column(name = "t7_qty", precision = 24, scale = 9)
	public BigDecimal getT7Qty() {
		return t7Qty;
	}

	public void setT7InvQty(BigDecimal t7InvQty) {
		this.t7InvQty = t7InvQty;
	}

	@Column(name = "t7_inv_qty", precision = 24, scale = 9)
	public BigDecimal getT7InvQty() {
		return t7InvQty;
	}

	public void setT7DifferQty(BigDecimal t7DifferQty) {
		this.t7DifferQty = t7DifferQty;
	}

	@Column(name = "t7_differ_qty", precision = 24, scale = 9)
	public BigDecimal getT7DifferQty() {
		return t7DifferQty;
	}

	public void setTotal3daysQty(BigDecimal total3daysQty) {
		this.total3daysQty = total3daysQty;
	}

	@Column(name = "total_3days_qty", precision = 24, scale = 9)
	public BigDecimal getTotal3daysQty() {
		return total3daysQty;
	}

	public void setTotal3daysDifferQty(BigDecimal total3daysDifferQty) {
		this.total3daysDifferQty = total3daysDifferQty;
	}

	@Column(name = "total_3days_differ_qty", precision = 24, scale = 9)
	public BigDecimal getTotal3daysDifferQty() {
		return total3daysDifferQty;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}

	@Column(name = "urgent", nullable = true, length = 20)
	public String getUrgent() {
		return urgent;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = true, length = 0)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = true, length = 11)
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = true, length = 0)
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

	private Integer operatorUserId;

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
