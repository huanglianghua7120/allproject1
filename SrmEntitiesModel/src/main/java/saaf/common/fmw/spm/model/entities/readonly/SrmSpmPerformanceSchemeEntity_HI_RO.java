package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class SrmSpmPerformanceSchemeEntity_HI_RO implements Serializable{

    public static final String QUERY_SCHEME_NUMBER_SQL = "SELECT ps.scheme_number schemeNumber, ps.scheme_name schemeName, ps.scheme_id schemeId FROM srm_spm_performance_scheme ps WHERE 1 = 1\n";

    public static final String QUERY_SCHEME_TPL_SQL =
                    "SELECT tpl.tpl_id        tplId\n" +
                    "      ,tpl.tpl_code      tplCode\n" +
                    "      ,tpl.tpl_name      tplName\n" +
                    "      ,slv.meaning       tplFrequency\n" +
                    "      ,tpl.tpl_frequency en_tplFrequency\n" +
                    "FROM   srm_spm_performance_tpl tpl\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     slv.lookup_code = tpl.tpl_frequency\n" +
                    "AND    slv.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
                    "WHERE  1 = 1\n";

    public static String QUERY_SCHEME_INFO_LIST =
                    "SELECT ps.scheme_id              schemeId\n" +
                    "      ,ps.request_id             scheduleId\n" +
                    "      ,ps.scheme_number          schemeNumber\n" +
                    "      ,ps.scheme_name            schemeName\n" +
                    "      ,ps.org_id                 orgId\n" +
                    "      ,ins.inst_name             instName\n" +
                    "      ,tpl.tpl_code              tplCode\n" +
                    "      ,tpl.tpl_name              tplName\n" +
                    "      ,tpl.tpl_frequency         tplFrequency\n" +
                    "      ,slv1.meaning              evaluatePeriod\n" +
                    "      ,ps.evaluate_interval_from evaluateIntervalFrom\n" +
                    "      ,ps.evaluate_interval_to   evaluateIntervalTo\n" +
                    "      ,slv.meaning               status\n" +
                    "      ,ps.status                 en_status\n" +
                    "      ,slv2.meaning              ratioRequestStatus\n" +
                    "      ,ps.ratio_request_status   en_ratioRequestStatus\n" +
                    "      ,su.user_full_name         creationName\n" +
                    "      ,ps.creation_date          creationDate\n" +
                    "      ,u.user_full_name          updatedName\n" +
                    "      ,ps.last_update_date       updateDate\n" +
                    "FROM   srm_spm_performance_scheme ps\n" +
                    "LEFT   JOIN srm_spm_performance_tpl tpl\n" +
                    "ON     ps.tpl_id = tpl.tpl_id\n" +
                    "LEFT   JOIN saaf_institution ins\n" +
                    "ON     ps.org_id = ins.inst_id\n" +
                    "LEFT   JOIN saaf_users su\n" +
                    "ON     ps.created_by = su.user_id\n" +
                    "LEFT   JOIN saaf_users u\n" +
                    "ON     ps.last_updated_by = u.user_id\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     slv.lookup_code = ps.status\n" +
                    "AND    slv.lookup_type = 'SPM_SCHEME_STATUS'\n" +
                    "LEFT   JOIN saaf_lookup_values slv2\n" +
                    "ON     slv2.lookup_code = ps.ratio_request_status\n" +
                    "AND    slv2.lookup_type = 'SPM_REQUEST_STATUS'\n" +
                    "LEFT   JOIN saaf_lookup_values slv1\n" +
                    "ON     slv1.lookup_code = ps.evaluate_period\n" +
                    "AND    slv1.lookup_type = 'SPM_TEMPLATE_FREQUENCY'\n" +
                    "WHERE  1 = 1\n";

    public static String QUERY_SCHEME_COUNT =
                    "SELECT COUNT(1) AS COUNT\n" +
                    "FROM   srm_spm_performance_scheme t\n" +
                    "WHERE  t.org_id = :orgId\n" +
                    "AND    t.tpl_id = :tplId\n" +
                    "AND    t.evaluate_interval_from = :evaluateIntervalFrom\n" +
                    "AND    t.status != :status\n";

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
    private Integer requestId; //请求ID
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
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    private String tplCode;
    private String tplName;
    private String tplFrequency;//模板评价周期
    private String instName;

    private String creationName;
    private String updatedName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    private String en_status;
    private String en_tplFrequency;

    private Integer count;

    private Integer scheduleId;


    private String requestStatus; //计算请求状态
    private Integer dataRequestId; //数据收集请求ID
    private String dataRequestStatus; //数据收集请求状态
    private Integer ratioRequestId; //比例应用请求ID
    private String ratioRequestStatus; //比例应用请求状态
    private String en_ratioRequestStatus;


    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getEn_ratioRequestStatus() {
        return en_ratioRequestStatus;
    }

    public void setEn_ratioRequestStatus(String en_ratioRequestStatus) {
        this.en_ratioRequestStatus = en_ratioRequestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Integer getDataRequestId() {
        return dataRequestId;
    }

    public void setDataRequestId(Integer dataRequestId) {
        this.dataRequestId = dataRequestId;
    }

    public String getDataRequestStatus() {
        return dataRequestStatus;
    }

    public void setDataRequestStatus(String dataRequestStatus) {
        this.dataRequestStatus = dataRequestStatus;
    }

    public Integer getRatioRequestId() {
        return ratioRequestId;
    }

    public void setRatioRequestId(Integer ratioRequestId) {
        this.ratioRequestId = ratioRequestId;
    }

    public String getRatioRequestStatus() {
        return ratioRequestStatus;
    }

    public void setRatioRequestStatus(String ratioRequestStatus) {
        this.ratioRequestStatus = ratioRequestStatus;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getEn_tplFrequency() {
        return en_tplFrequency;
    }

    public void setEn_tplFrequency(String en_tplFrequency) {
        this.en_tplFrequency = en_tplFrequency;
    }

    public String getEn_status() {
        return en_status;
    }

    public void setEn_status(String en_status) {
        this.en_status = en_status;
    }

    private String ts;

    public String getTs() {
        return "Y";
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTplCode() {
        return tplCode;
    }

    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public String getTplFrequency() {
        return tplFrequency;
    }

    public void setTplFrequency(String tplFrequency) {
        this.tplFrequency = tplFrequency;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeNumber() {
        return schemeNumber;
    }

    public void setSchemeNumber(String schemeNumber) {
        this.schemeNumber = schemeNumber;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTplId() {
        return tplId;
    }

    public void setTplId(Integer tplId) {
        this.tplId = tplId;
    }

    public String getEvaluatePeriod() {
        return evaluatePeriod;
    }

    public void setEvaluatePeriod(String evaluatePeriod) {
        this.evaluatePeriod = evaluatePeriod;
    }

    public String getEvaluateIntervalFrom() {
        return evaluateIntervalFrom;
    }

    public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
        this.evaluateIntervalFrom = evaluateIntervalFrom;
    }

    public String getEvaluateIntervalTo() {
        return evaluateIntervalTo;
    }

    public void setEvaluateIntervalTo(String evaluateIntervalTo) {
        this.evaluateIntervalTo = evaluateIntervalTo;
    }

    public Date getSubmitRequestDate() {
        return submitRequestDate;
    }

    public void setSubmitRequestDate(Date submitRequestDate) {
        this.submitRequestDate = submitRequestDate;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(String publishFlag) {
        this.publishFlag = publishFlag;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
