package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmIndicatorsEntity_HI Entity Object
 * Tue Mar 06 08:54:42 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_indicators")
public class SrmSpmIndicatorsEntity_HI {
    private Integer indicatorId; //指标ID
    private String indicatorCode; //指标编码，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_CODE）
    private String indicatorName; //指标名称
    private String applicationDomain; //应用领域，关联表：SAAF_LOOKUP_VALUES（SPM_APPLICATION_DOMAIN）
    private String indicatorDimension; //指标维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private String indicatorType; //评价类型（数据来源），关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_TYPE）
    private String indicatorValueType; //评价项值类型，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_VALUE）
    private String indicatorStatus; //指标状态，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_STATUS）
    private BigDecimal scoreDeductingLimit; //扣分上限
    private BigDecimal targetValue; //目标值
    private String scoreExplain; //指标逻辑描述
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //生效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //失效日期
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
    private String itemType;

	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_INDICATORS_S", sequenceName = "SRM_SPM_INDICATORS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_INDICATORS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "indicator_id", nullable = false, length = 11)	
	public Integer getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}

	@Column(name = "indicator_code", nullable = true, length = 30)	
	public String getIndicatorCode() {
		return indicatorCode;
	}

	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}

	@Column(name = "indicator_name", nullable = true, length = 240)	
	public String getIndicatorName() {
		return indicatorName;
	}


    @Column(name = "item_type", nullable = true, length = 30)
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }


	public void setApplicationDomain(String applicationDomain) {
		this.applicationDomain = applicationDomain;
	}

	@Column(name = "application_domain", nullable = true, length = 30)	
	public String getApplicationDomain() {
		return applicationDomain;
	}

	public void setIndicatorDimension(String indicatorDimension) {
		this.indicatorDimension = indicatorDimension;
	}

	@Column(name = "indicator_dimension", nullable = true, length = 30)	
	public String getIndicatorDimension() {
		return indicatorDimension;
	}

	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}

	@Column(name = "indicator_type", nullable = true, length = 30)	
	public String getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorValueType(String indicatorValueType) {
		this.indicatorValueType = indicatorValueType;
	}

	@Column(name = "indicator_value_type", nullable = true, length = 30)	
	public String getIndicatorValueType() {
		return indicatorValueType;
	}

	public void setIndicatorStatus(String indicatorStatus) {
		this.indicatorStatus = indicatorStatus;
	}

	@Column(name = "indicator_status", nullable = true, length = 30)	
	public String getIndicatorStatus() {
		return indicatorStatus;
	}

	public void setScoreDeductingLimit(BigDecimal scoreDeductingLimit) {
		this.scoreDeductingLimit = scoreDeductingLimit;
	}

	@Column(name = "score_deducting_limit", precision = 22, scale = 2)	
	public BigDecimal getScoreDeductingLimit() {
		return scoreDeductingLimit;
	}

	public void setScoreExplain(String scoreExplain) {
		this.scoreExplain = scoreExplain;
	}

	@Column(name = "score_explain", nullable = true, length = 240)	
	public String getScoreExplain() {
		return scoreExplain;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "start_date", nullable = true, length = 0)	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "end_date", nullable = true, length = 0)	
	public Date getEndDate() {
		return endDate;
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
	
	
	@Column(name = "target_value", nullable = true, precision = 22, scale = 2)
	public BigDecimal getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
