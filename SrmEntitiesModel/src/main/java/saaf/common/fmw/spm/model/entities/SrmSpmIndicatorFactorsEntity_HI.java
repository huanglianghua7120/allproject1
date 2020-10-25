package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmIndicatorFactorsEntity_HI Entity Object
 * Tue Mar 06 08:54:51 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_indicator_factors")
public class SrmSpmIndicatorFactorsEntity_HI {
    private Integer indicatorFactorId; //指标计算因子ID
    private Integer indicatorScoreId; //供应商指标得分ID，关联表：SRM_SPM_INDICATOR_SCORE
    private BigDecimal pk1Value; //计算因子1
    private BigDecimal pk2Value; //计算因子2
    private BigDecimal pk3Value; //计算因子3
    private BigDecimal pk4Value; //计算因子4
    private BigDecimal pk5Value; //计算因子5
    private BigDecimal pk6Value; //计算因子6
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

	public void setIndicatorFactorId(Integer indicatorFactorId) {
		this.indicatorFactorId = indicatorFactorId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_INDICATOR_FACTORS_S", sequenceName = "SRM_SPM_INDICATOR_FACTORS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_INDICATOR_FACTORS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "indicator_factor_id", nullable = false, length = 11)	
	public Integer getIndicatorFactorId() {
		return indicatorFactorId;
	}

	public void setIndicatorScoreId(Integer indicatorScoreId) {
		this.indicatorScoreId = indicatorScoreId;
	}

	@Column(name = "indicator_score_id", nullable = true, length = 11)	
	public Integer getIndicatorScoreId() {
		return indicatorScoreId;
	}

	public void setPk1Value(BigDecimal pk1Value) {
		this.pk1Value = pk1Value;
	}

	@Column(name = "pk1_value", precision = 22, scale = 2)	
	public BigDecimal getPk1Value() {
		return pk1Value;
	}

	public void setPk2Value(BigDecimal pk2Value) {
		this.pk2Value = pk2Value;
	}

	@Column(name = "pk2_value", precision = 22, scale = 2)	
	public BigDecimal getPk2Value() {
		return pk2Value;
	}

	public void setPk3Value(BigDecimal pk3Value) {
		this.pk3Value = pk3Value;
	}

	@Column(name = "pk3_value", precision = 22, scale = 2)	
	public BigDecimal getPk3Value() {
		return pk3Value;
	}

	public void setPk4Value(BigDecimal pk4Value) {
		this.pk4Value = pk4Value;
	}

	@Column(name = "pk4_value", precision = 22, scale = 2)	
	public BigDecimal getPk4Value() {
		return pk4Value;
	}

	public void setPk5Value(BigDecimal pk5Value) {
		this.pk5Value = pk5Value;
	}

	@Column(name = "pk5_value", precision = 22, scale = 2)	
	public BigDecimal getPk5Value() {
		return pk5Value;
	}

	public void setPk6Value(BigDecimal pk6Value) {
		this.pk6Value = pk6Value;
	}

	@Column(name = "pk6_value", precision = 22, scale = 2)	
	public BigDecimal getPk6Value() {
		return pk6Value;
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
}
