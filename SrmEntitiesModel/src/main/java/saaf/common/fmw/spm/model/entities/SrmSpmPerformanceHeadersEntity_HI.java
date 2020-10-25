package saaf.common.fmw.spm.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmSpmPerformanceHeadersEntity_HI Entity Object
 * Tue Feb 11 15:39:26 CST 2020  Auto Generate
 */
@Entity
@Table(name = "SRM_SPM_PERFORMANCE_HEADERS")
public class SrmSpmPerformanceHeadersEntity_HI {

	private Integer performanceId; //绩效ID
	private Integer orgId; //组织ID
	private String performanceNumber; //绩效编号
	private String performanceName; //绩效名称
	private Integer tplId; //绩效模板ID
	private String evaluatePeriod; //评价频率
	private String status; //状态
	private String evaluateIntervalFrom; //绩效月份从
	private String evaluateIntervalTo; //绩效月份至
	private String publishFlag; //是否已发布
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date publishDate; //发布时间
	private String description; //说明、备注
	private Integer fileId; //附件ID（备用）
	private String evaluateYear; //评价年份
	private String evaluateQuarter; //评价季度
	private String evaluateMonth; //评价月份
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

	@Id
	@SequenceGenerator(name = "SRM_SPM_PERFORMANCE_HEADERS_S", sequenceName = "SRM_SPM_PERFORMANCE_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_PERFORMANCE_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "performance_id", nullable = false, length = 10)
	public Integer getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(Integer performanceId) {
		this.performanceId = performanceId;
	}

	@Column(name = "org_id", nullable = true, length = 10)
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "performance_number", nullable = true, length = 30)
	public String getPerformanceNumber() {
		return performanceNumber;
	}

	public void setPerformanceNumber(String performanceNumber) {
		this.performanceNumber = performanceNumber;
	}

	@Column(name = "performance_name", nullable = true, length = 240)
	public String getPerformanceName() {
		return performanceName;
	}

	public void setPerformanceName(String performanceName) {
		this.performanceName = performanceName;
	}

    @Column(name = "item_type", nullable = false, length = 30)
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Column(name = "tpl_id", nullable = true, length = 10)
	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	@Column(name = "evaluate_period", nullable = true, length = 30)
	public String getEvaluatePeriod() {
		return evaluatePeriod;
	}

	public void setEvaluatePeriod(String evaluatePeriod) {
		this.evaluatePeriod = evaluatePeriod;
	}

	@Column(name = "status", nullable = true, length = 30)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "evaluate_interval_from", nullable = true, length = 10)
	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}

	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}

	@Column(name = "evaluate_interval_to", nullable = true, length = 10)
	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}

	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}

	@Column(name = "publish_flag", nullable = true, length = 30)
	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	@Column(name = "publish_date", nullable = false, length = 0)
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "description", nullable = true, length = 240)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 11)
	public Integer getFileId() {
		return fileId;
	}

	@Column(name = "evaluate_year", nullable = true, length = 10)
	public String getEvaluateYear() {
		return evaluateYear;
	}

	public void setEvaluateYear(String evaluateYear) {
		this.evaluateYear = evaluateYear;
	}

	@Column(name = "evaluate_quarter", nullable = true, length = 10)
	public String getEvaluateQuarter() {
		return evaluateQuarter;
	}

	public void setEvaluateQuarter(String evaluateQuarter) {
		this.evaluateQuarter = evaluateQuarter;
	}

	@Column(name = "evaluate_month", nullable = true, length = 10)
	public String getEvaluateMonth() {
		return evaluateMonth;
	}

	public void setEvaluateMonth(String evaluateMonth) {
		this.evaluateMonth = evaluateMonth;
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
