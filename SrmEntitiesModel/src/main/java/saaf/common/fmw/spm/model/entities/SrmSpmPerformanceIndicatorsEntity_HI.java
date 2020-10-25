package saaf.common.fmw.spm.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmSpmPerformanceIndicatorsEntity_HI Entity Object
 * Thu Feb 13 16:17:46 CST 2020  Auto Generate
 */
@Entity
@Table(name = "SRM_SPM_PERFORMANCE_INDICATORS")
public class SrmSpmPerformanceIndicatorsEntity_HI {

	private Integer performanceIndicatorId; //绩效指标ID
	private Integer performanceId; //绩效头ID
	private Integer performanceLineId; //绩效行ID
	private Integer tplId; //绩效模板ID
	private Integer tplDimensionId; //绩效模板维度ID
	private Integer tplIndicatorId; //绩效模板指标ID
	private BigDecimal indicatorScore; //指标得分
	private String evaluateUserIdStr; //评分用户ID字符串，逗号分隔
	private String evaluateEmployeeIdStr; //评分员工ID字符串，逗号分隔
	private String evaluateEmployeeNumberStr; //评分员工编码字符串，逗号分隔
	private String evaluateEmployeeNameStr; //评分员工姓名字符串，逗号分隔
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
	private BigDecimal dimensionWeight;
	private BigDecimal indicatorWeight;
    private String evaluateUserNameStr; //评分用户姓名字符串，逗号分隔


	@Id
	@SequenceGenerator(name = "SRM_SPM_PERFORMANCE_IND_S", sequenceName = "SRM_SPM_PERFORMANCE_IND_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_PERFORMANCE_IND_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "performance_indicator_id", nullable = false, length = 10)
	public Integer getPerformanceIndicatorId() {
		return performanceIndicatorId;
	}

	public void setPerformanceIndicatorId(Integer performanceIndicatorId) {
		this.performanceIndicatorId = performanceIndicatorId;
	}

	@Column(name = "performance_id", nullable = true, length = 10)
	public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	@Column(name = "performance_line_id", nullable = true, length = 10)
	public Integer getPerformanceLineId() {
		return performanceLineId;
	}

	public void setPerformanceLineId(Integer performanceLineId) {
		this.performanceLineId = performanceLineId;
	}

	@Column(name = "tpl_id", nullable = true, length = 10)
	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	@Column(name = "tpl_dimension_id", nullable = true, length = 10)
	public Integer getTplDimensionId() {
		return tplDimensionId;
	}

	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}

	@Column(name = "tpl_indicator_id", nullable = true, length = 10)
	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}

	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}

	@Column(name = "indicator_score", precision = 15, scale = 6)
	public BigDecimal getIndicatorScore() {
		return indicatorScore;
	}

	public void setIndicatorScore(BigDecimal indicatorScore) {
		this.indicatorScore = indicatorScore;
	}

	@Column(name = "evaluate_user_id_str", nullable = true, length = 240)
	public String getEvaluateUserIdStr() {
		return evaluateUserIdStr;
	}

	public void setEvaluateUserIdStr(String evaluateUserIdStr) {
		this.evaluateUserIdStr = evaluateUserIdStr;
	}

	@Column(name = "Evaluate_Employee_Id_Str", nullable = true, length = 240)
	public String getEvaluateEmployeeIdStr() {
		return evaluateEmployeeIdStr;
	}

	public void setEvaluateEmployeeIdStr(String evaluateEmployeeIdStr) {
		this.evaluateEmployeeIdStr = evaluateEmployeeIdStr;
	}

	@Column(name = "Evaluate_Employee_Number_Str", nullable = true, length = 240)
	public String getEvaluateEmployeeNumberStr() {
		return evaluateEmployeeNumberStr;
	}

	public void setEvaluateEmployeeNumberStr(String evaluateEmployeeNumberStr) {
		this.evaluateEmployeeNumberStr = evaluateEmployeeNumberStr;
	}

    @Column(name = "Evaluate_User_Name_Str", nullable = true, length = 240)
    public String getEvaluateUserNameStr() {
        return evaluateUserNameStr;
    }

    public void setEvaluateUserNameStr(String evaluateUserNameStr) {
        this.evaluateUserNameStr = evaluateUserNameStr;
    }

    @Column(name = "Evaluate_Employee_Name_Str", nullable = true, length = 240)
	public String getEvaluateEmployeeNameStr() {
		return evaluateEmployeeNameStr;
	}

	public void setEvaluateEmployeeNameStr(String evaluateEmployeeNameStr) {
		this.evaluateEmployeeNameStr = evaluateEmployeeNameStr;
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

	@Column(name = "dimension_weight", precision = 15, scale = 6)
	public BigDecimal getDimensionWeight() {
		return dimensionWeight;
	}

	public void setDimensionWeight(BigDecimal dimensionWeight) {
		this.dimensionWeight = dimensionWeight;
	}

	@Column(name = "indicator_weight", precision = 15, scale = 6)
	public BigDecimal getIndicatorWeight() {
		return indicatorWeight;
	}

	public void setIndicatorWeight(BigDecimal indicatorWeight) {
		this.indicatorWeight = indicatorWeight;
	}
}
