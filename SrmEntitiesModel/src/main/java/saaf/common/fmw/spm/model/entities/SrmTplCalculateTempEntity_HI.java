package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmTplCalculateTempEntity_HI Entity Object
 * Fri Apr 13 16:43:21 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_calculate_temp")
public class SrmTplCalculateTempEntity_HI {
    private Integer tempId;
    private Integer schemeId;
    private Integer supplierId;
    private Integer itemId;
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
    
    private Integer categoryId;

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_CALCULATE_TEMP_S", sequenceName = "SRM_SPM_CALCULATE_TEMP_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_CALCULATE_TEMP_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "temp_id", nullable = false, length = 11)	
	public Integer getTempId() {
		return tempId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name = "scheme_id", nullable = true, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}
	
	

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
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
	
	
	
	@Column(name = "CATEGORY_ID", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
