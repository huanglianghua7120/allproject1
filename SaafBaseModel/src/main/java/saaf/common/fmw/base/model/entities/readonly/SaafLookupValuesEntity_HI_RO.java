package saaf.common.fmw.base.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class SaafLookupValuesEntity_HI_RO implements Serializable {

    public SaafLookupValuesEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL =
                    "SELECT slv.lookup_code lookupCode\n" +
                    "      ,slv.meaning     meaning\n" +
                    "      ,slv.tag         tag\n" +
                    "      ,slv.lookup_type lookuptype\n" +
                    "      ,slv.description description\n" +
                    "FROM   saaf_lookup_values slv\n" +
                    "WHERE  slv.enabled_flag = 'Y'\n" +
                    "AND    slv.start_date_active <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(slv.end_date_active, SYSDATE)) >= trunc(SYSDATE)\n";

    public static final String QUERY_LOOKUPVALUES_BYTYPE_CODE_SQL =
                    "SELECT\n" +
                    "  slv.lookup_code looupCode,\n" +
                    "  slv.lookup_type looupType,\n" +
                    "  slv.tag tag,\n" +
                    "  slv.description description,\n" +
                    "  slv.meaning meaning\n" +
                    "FROM\n" +
                    "  saaf_lookup_values slv\n" +
                    "WHERE slv.lookup_type = ?\n" +
                    "  AND slv.lookup_code = ?\n";

    public static final String QUERY_INVOICEIDT_SQL ="SELECT slv.lookup_code lookupCode,\r\n" +
            "       slv.lookup_type looupType,\r\n" +
            "       slv.tag tag,\r\n" +
            "       slv.description description,\r\n" +
            "       slv.meaning meaning\r\n" +
            "       FROM saaf_lookup_values slv\r\n" +
            "        WHERE 1 = 1\n";

    public static final Object QUERY_INVOICEID_LINE_SQL = "SELECT slv.lookup_code lookupCode,\r\n" +
            "       slv.lookup_type looupType,\r\n" +
            "       slv.tag tag,\r\n" +
            "       slv.description description,\r\n" +
            "       slv.meaning meaning\r\n" +
            "       FROM saaf_lookup_values slv\r\n" +
            "        WHERE slv.lookup_type = 'SPM_INDICATOR_NAME' AND slv.tag = :tag\n";

    public static final String QUERY_INVOICEID_LINET_SQL ="SELECT slv.lookup_code lookupCode,\r\n" +
            "       slv.lookup_type looupType,\r\n" +
            "       slv.tag tag,\r\n" +
            "       slv.description description,\r\n" +
            "       slv.meaning meaning\r\n" +
            "       FROM saaf_lookup_values slv\r\n" +
            "        WHERE slv.lookup_type = 'SPM_INDICATOR_NAME'\n";

    public static final Object QUERY_INVOICEID_TPL_SQL ="SELECT \r\n" +
            "  slv.lookup_code lookupCode,\r\n" +
            "  slv.lookup_type looupType,\r\n" +
            "  slv.tag tag,\r\n" +
            "  slv.description description,\r\n" +
            "  slv.meaning meaning \r\n" +
            "FROM\r\n" +
            "  srm_spm_indicators spm \r\n" +
            "  LEFT JOIN saaf_lookup_values slv \r\n" +
            "    ON spm.INDICATOR_CODE = slv.lookup_code \r\n" +
            "    AND slv.lookup_type = 'SPM_INDICATOR_NAME' \r\n" +
            "    AND slv.enabled_flag = 'Y'\r\n" +
            "    WHERE spm.INDICATOR_STATUS = 'ACTIVE' AND slv.tag = :tag\n";

    public static final Object QUERY_DELIVERY_TYPE_SQL = "SELECT slv.lookup_code lookupCode,\r\n" +
            "       slv.lookup_type looupType,\r\n" +
            "       slv.tag tag,\r\n" +
            "       slv.description description,\r\n" +
            "       slv.meaning meaning\r\n" +
            "       FROM saaf_lookup_values slv\r\n" +
            "       WHERE slv.lookup_type ='ISP_PR_EVENT_TYPE'\r\n";

    private Integer lookupValuesId;
    private String lookupType;
    private String lookupCode;
    private String meaning;
    private String description;
    private String tag;
    private String enabledFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;
    private String looupType;

    public String getLooupType() {
        return looupType;
    }

    public void setLooupType(String looupType) {
        this.looupType = looupType;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLookupType() {
        return lookupType;
    }

    public void setLookupType(String lookupType) {
        this.lookupType = lookupType;
    }

    public Integer getLookupValuesId() {
        return lookupValuesId;
    }

    public void setLookupValuesId(Integer lookupValuesId) {
        this.lookupValuesId = lookupValuesId;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
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

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }
}
