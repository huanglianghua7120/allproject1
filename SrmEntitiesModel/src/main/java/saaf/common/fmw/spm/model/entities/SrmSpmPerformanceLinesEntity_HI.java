package saaf.common.fmw.spm.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmSpmPerformanceLinesEntity_HI Entity Object
 * Wed Feb 12 16:51:05 CST 2020  Auto Generate
 */
@Entity
@Table(name = "SRM_SPM_PERFORMANCE_LINES")
public class SrmSpmPerformanceLinesEntity_HI implements Comparable{

	private Integer performanceLineId; //绩效行ID
	private Integer performanceId; //绩效ID
	private Integer supplierId; //供应商ID
	private BigDecimal score; //综合得分
	private Integer rank; //排名
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date calculateDate; //计算时间
	private String confirmFlag; //是否已确认
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date confirmDate; //确认日期
	private String performanceComment; //绩效评语
	private String improvementPlan; //改进计划
	private String manualScoreIdStr; //加减分id串
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
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;
	private Integer operatorUserId;

	@Id
	@SequenceGenerator(name = "SRM_SPM_PERFORMANCE_LINES_S", sequenceName = "SRM_SPM_PERFORMANCE_LINES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_PERFORMANCE_LINES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "performance_line_id", nullable = false, length = 10)
	public Integer getPerformanceLineId() {
		return performanceLineId;
	}

	public void setPerformanceLineId(Integer performanceLineId) {
		this.performanceLineId = performanceLineId;
	}

	@Column(name = "performance_id", nullable = true, length = 10)
	public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	@Column(name = "supplier_id", nullable = true, length = 10)
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "score", precision = 15, scale = 6)
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Column(name = "rank", nullable = true, length = 10)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "calculate_date", nullable = true, length = 0)
	public Date getCalculateDate() {
		return calculateDate;
	}

	public void setCalculateDate(Date calculateDate) {
		this.calculateDate = calculateDate;
	}

	@Column(name = "confirm_flag", nullable = true, length = 10)
	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	@Column(name = "confirm_date", nullable = true, length = 0)
	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "performance_comment", nullable = true, length = 1000)
	public String getPerformanceComment() {
		return performanceComment;
	}

	public void setPerformanceComment(String performanceComment) {
		this.performanceComment = performanceComment;
	}

	@Column(name = "improvement_plan", nullable = true, length = 1000)
	public String getImprovementPlan() {
		return improvementPlan;
	}

	public void setImprovementPlan(String improvementPlan) {
		this.improvementPlan = improvementPlan;
	}

	@Column(name = "manual_score_id_str", nullable = true, length = 240)
	public String getManualScoreIdStr() {
		return manualScoreIdStr;
	}

	public void setManualScoreIdStr(String manualScoreIdStr) {
		this.manualScoreIdStr = manualScoreIdStr;
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	@Override
	public int compareTo(Object o) {
		BigDecimal a;
		BigDecimal b;
		if (null == this.getScore()){
			a = new BigDecimal("0");
		}else {
			a = this.getScore();
		}
		SrmSpmPerformanceLinesEntity_HI object = (SrmSpmPerformanceLinesEntity_HI)o;
		if (null == object.getScore()){
			b = new BigDecimal("0");
		}else {
			b = object.getScore();
		}
		return - a.compareTo(b);
	}
}
