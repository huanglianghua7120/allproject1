package saaf.common.fmw.pos.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmPosInvestigationPlanLinesEntity_HI Entity Object
 * Tue Nov 27 14:55:00 CST 2018  Auto Generate
 */
@Entity
@Table(name="srm_pos_investigation_plan_lines")
public class SrmPosInvestigationPlanLinesEntity_HI {
    private Integer investigationPlanLinesId; //考察计划行ID
    private Integer investigationPlanId; //考察计划ID,关联表:srm_pos_investigation_plan
    private Integer supplierId; //供应商ID,关联表:srm_pos_supplier_info
    private String plannedBy; //计划考察人
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date plannedTime; //计划考察时间
    private String linesDescription; //行备注
    private Integer versionNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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
    private Integer operatorUserId;

	public void setInvestigationPlanLinesId(Integer investigationPlanLinesId) {
		this.investigationPlanLinesId = investigationPlanLinesId;
	}

	@Id
	@SequenceGenerator(name = "SRM_POS_INVESTIGATION_PLAN_S", sequenceName = "SRM_POS_INVESTIGATION_PLAN_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_POS_INVESTIGATION_PLAN_S", strategy = GenerationType.SEQUENCE)
	@Column(name="investigation_plan_lines_id", nullable=false, length=11)	
	public Integer getInvestigationPlanLinesId() {
		return investigationPlanLinesId;
	}

	public void setInvestigationPlanId(Integer investigationPlanId) {
		this.investigationPlanId = investigationPlanId;
	}

	@Column(name="investigation_plan_id", nullable=false, length=11)	
	public Integer getInvestigationPlanId() {
		return investigationPlanId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name="supplier_id", nullable=false, length=11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setPlannedBy(String plannedBy) {
		this.plannedBy = plannedBy;
	}

	@Column(name="planned_by", nullable=true, length=100)	
	public String getPlannedBy() {
		return plannedBy;
	}

	public void setPlannedTime(Date plannedTime) {
		this.plannedTime = plannedTime;
	}

	@Column(name="planned_time", nullable=false, length=0)	
	public Date getPlannedTime() {
		return plannedTime;
	}

	public void setLinesDescription(String linesDescription) {
		this.linesDescription = linesDescription;
	}

	@Column(name="lines_description", nullable=true, length=1000)	
	public String getLinesDescription() {
		return linesDescription;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name="version_num", nullable=true, length=11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name="creation_date", nullable=false, length=0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="created_by", nullable=false, length=11)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name="last_updated_by", nullable=false, length=11)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name="last_update_date", nullable=false, length=0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name="last_update_login", nullable=true, length=11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name="attribute_category", nullable=true, length=30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name="attribute1", nullable=true, length=240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name="attribute2", nullable=true, length=240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name="attribute3", nullable=true, length=240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name="attribute4", nullable=true, length=240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name="attribute5", nullable=true, length=240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name="attribute6", nullable=true, length=240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name="attribute7", nullable=true, length=240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name="attribute8", nullable=true, length=240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name="attribute9", nullable=true, length=240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name="attribute10", nullable=true, length=240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SrmPosInvestigationPlanLinesEntity_HI that = (SrmPosInvestigationPlanLinesEntity_HI) o;
		if(null==that.supplierId) return that.supplierId==supplierId;
		if (!supplierId.equals(that.supplierId)) return false;
		if(null==that.plannedBy) return that.plannedBy==plannedBy;
		if (!plannedBy.equals(that.plannedBy)) return false;
		if(null==that.plannedTime) return that.plannedTime==plannedTime;
		if (!plannedTime.equals(that.plannedTime)) return false;
		return true;
	}
}
