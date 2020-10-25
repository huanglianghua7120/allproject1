package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPonJuryScoreLinesEntity_HI Entity Object
 * Tue Apr 17 11:14:37 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_jury_score_lines")
public class SrmPonJuryScoreLinesEntity_HI {
    private Integer juryScoreLineId; //洽谈评分明细ID
    private Integer juryScoreId; //洽谈评分ID
    private Integer bidHeaderId; //供应商报价ID
    private String scorePartition; //评标分区
    private BigDecimal score; //得分
	private Integer scoreRuleId;//评分规则id
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
    private Integer operatorUserId;
	private Integer bidHeaderIdV;//供应商报价ID(备用)
	public void setJuryScoreLineId(Integer juryScoreLineId) {
		this.juryScoreLineId = juryScoreLineId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_JURY_SCORES_S", sequenceName = "SRM_PON_JURY_SCORES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_JURY_SCORES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "jury_score_line_id", nullable = false, length = 11)	
	public Integer getJuryScoreLineId() {
		return juryScoreLineId;
	}

	public void setJuryScoreId(Integer juryScoreId) {
		this.juryScoreId = juryScoreId;
	}

	@Column(name = "jury_score_id", nullable = false, length = 11)	
	public Integer getJuryScoreId() {
		return juryScoreId;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	@Column(name = "bid_header_id", nullable = false, length = 11)	
	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setScorePartition(String scorePartition) {
		this.scorePartition = scorePartition;
	}

	@Column(name = "score_partition", nullable = true, length = 30)	
	public String getScorePartition() {
		return scorePartition;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Column(name = "score", precision = 5, scale = 2)	
	public BigDecimal getScore() {
		return score;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setBidHeaderIdV(Integer bidHeaderIdV) {
		this.bidHeaderIdV = bidHeaderIdV;
	}

	@Transient
	public Integer getBidHeaderIdV() {
		return bidHeaderIdV;
	}

	@Column(name = "score_rule_id", nullable = false, length = 11)
	public Integer getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(Integer scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}
}
