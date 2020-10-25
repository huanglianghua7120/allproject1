package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmCalculateResultEntity_HI Entity Object
 * Thu May 17 11:35:05 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_calculate_result")
public class SrmSpmCalculateResultEntity_HI {
    private Integer resultId;
    private Integer schemeId;
    private Integer categoryId;
    private Integer itemId;
    private Integer supplierId;
    private BigDecimal score;
    private BigDecimal auntscore;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer versionNum;
    private Integer lastUpdateLogin;
    private Integer operatorUserId;

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_CALCULATE_RESULT_S", sequenceName = "SRM_SPM_CALCULATE_RESULT_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_CALCULATE_RESULT_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "result_id", nullable = false, length = 11)	
	public Integer getResultId() {
		return resultId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name = "scheme_id", nullable = true, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	@Column(name = "score", precision = 22, scale = 2)	
	public BigDecimal getScore() {
		return score;
	}

	public void setAuntscore(BigDecimal auntscore) {
		this.auntscore = auntscore;
	}

	@Column(name = "auntscore", precision = 22, scale = 2)	
	public BigDecimal getAuntscore() {
		return auntscore;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = true, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = true, length = 11)	
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

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
