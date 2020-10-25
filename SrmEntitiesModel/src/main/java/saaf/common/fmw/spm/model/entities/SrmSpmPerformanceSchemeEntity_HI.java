package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmPerformanceSchemeEntity_HI Entity Object
 * Tue Apr 03 09:25:44 GMT+08:00 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_performance_scheme")
public class SrmSpmPerformanceSchemeEntity_HI {
    private Integer schemeId; //方案ID
    private String schemeNumber; //方案编号
    private String schemeName; //方案名称
    private Integer orgId; //组织ID，关联表：SAAF_INSTITUTION
    private Integer tplId; //绩效模板ID，关联表：SRM_SPM_PERFORMANCE_TPL
    private String evaluatePeriod; //评价频率，关联表：SAAF_LOOKUP_VALUES（SPM_TEMPLATE_FREQUENCY）
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date submitRequestDate; //提交请求时间
    private Integer requestId; //计算请求ID
    private String requestStatus; //计算请求状态
    private Integer dataRequestId; //数据收集请求ID
    private String dataRequestStatus; //数据收集请求状态
    private Integer ratioRequestId; //比例应用请求ID
    private String ratioRequestStatus; //比例应用请求状态
    private Integer batchId; //审批批次ID
    private String status; //状态，关联表：SAAF_LOOKUP_VALUES（SPM_SCHEME_STATUS）
    private String publishFlag; //是否已发布，关联表：SAAF_LOOKUP_VALUES（YSE_NO）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate; //发布时间
    private String description; //说明、备注
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //生效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //失效日期
    private Integer fileId; //附件ID（备用）
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

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_PERFORMANCE_SCHEME_S", sequenceName = "SRM_SPM_PERFORMANCE_SCHEME_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_PERFORMANCE_SCHEME_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "scheme_id", nullable = false, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setSchemeNumber(String schemeNumber) {
		this.schemeNumber = schemeNumber;
	}

	@Column(name = "scheme_number", nullable = true, length = 30)	
	public String getSchemeNumber() {
		return schemeNumber;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	@Column(name = "scheme_name", nullable = true, length = 240)	
	public String getSchemeName() {
		return schemeName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	@Column(name = "tpl_id", nullable = true, length = 11)	
	public Integer getTplId() {
		return tplId;
	}

	public void setEvaluatePeriod(String evaluatePeriod) {
		this.evaluatePeriod = evaluatePeriod;
	}

	@Column(name = "evaluate_period", nullable = true, length = 30)	
	public String getEvaluatePeriod() {
		return evaluatePeriod;
	}

	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}

	@Column(name = "evaluate_interval_from", nullable = true, length = 10)	
	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}

	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}

	@Column(name = "evaluate_interval_to", nullable = true, length = 10)	
	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}

	public void setSubmitRequestDate(Date submitRequestDate) {
		this.submitRequestDate = submitRequestDate;
	}

	@Column(name = "submit_request_date", nullable = true, length = 0)	
	public Date getSubmitRequestDate() {
		return submitRequestDate;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	@Column(name = "request_id", nullable = true, length = 11)	
	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Column(name = "request_status", nullable = true, length = 30)	
	public String getRequestStatus() {
		return requestStatus;
	}

	public void setDataRequestId(Integer dataRequestId) {
		this.dataRequestId = dataRequestId;
	}

	@Column(name = "data_request_id", nullable = true, length = 11)	
	public Integer getDataRequestId() {
		return dataRequestId;
	}

	public void setDataRequestStatus(String dataRequestStatus) {
		this.dataRequestStatus = dataRequestStatus;
	}

	@Column(name = "data_request_status", nullable = true, length = 30)	
	public String getDataRequestStatus() {
		return dataRequestStatus;
	}

	public void setRatioRequestId(Integer ratioRequestId) {
		this.ratioRequestId = ratioRequestId;
	}

	@Column(name = "ratio_request_id", nullable = true, length = 11)	
	public Integer getRatioRequestId() {
		return ratioRequestId;
	}

	public void setRatioRequestStatus(String ratioRequestStatus) {
		this.ratioRequestStatus = ratioRequestStatus;
	}

	@Column(name = "ratio_request_status", nullable = true, length = 30)	
	public String getRatioRequestStatus() {
		return ratioRequestStatus;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Column(name = "batch_id", nullable = true, length = 11)	
	public Integer getBatchId() {
		return batchId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 30)	
	public String getStatus() {
		return status;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	@Column(name = "publish_flag", nullable = true, length = 30)	
	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "publish_date", nullable = true, length = 0)	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 240)	
	public String getDescription() {
		return description;
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

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 11)	
	public Integer getFileId() {
		return fileId;
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
