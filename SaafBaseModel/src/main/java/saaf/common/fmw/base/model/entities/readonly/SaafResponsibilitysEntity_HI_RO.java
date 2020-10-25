package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafResponsibilitysEntity_HI_RO implements Serializable {

    public SaafResponsibilitysEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL =
                    "SELECT sr.responsibility_id   responsibilityId\n" +
                    "      ,sr.responsibility_name responsibilityName\n" +
                    "      ,sr.responsibility_key  responsibilityKey\n" +
                    "      ,sr.platform_code       platformCode\n" +
                    "      ,sr.start_date_active   startDateActive\n" +
                    "      ,sr.end_date_active     endDateActive\n" +
                    "      ,lv.meaning             platformStr\n" +
                    "FROM   saaf_responsibilitys sr\n" +
                    "      ,saaf_lookup_values   lv\n" +
                    "WHERE  lv.lookup_type = 'PLATFORM_CODE'\n" +
                    "AND    sr.platform_code = lv.lookup_code\n";

    //有效职责
    public static String QUERY_RESP_SQL =
                    "SELECT sr.responsibility_id   responsibilityId\n" +
                    "      ,sr.responsibility_name responsibilityName\n" +
                    "      ,sr.responsibility_key  responsibilityKey\n" +
                    "FROM   saaf_responsibilitys sr\n" +
                    "WHERE  sr.start_date_active <= trunc(SYSDATE)\n" +
                    "AND    nvl(sr.end_date_active, trunc(SYSDATE)) >= trunc(SYSDATE)\n";

    private Integer responsibilityId;
    private String responsibilityName;
    private String responsibilityKey;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    private String platformStr;


    public void setResponsibilityId(int responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public int getResponsibilityId() {
        return responsibilityId;
    }


    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityKey(String responsibilityKey) {
        this.responsibilityKey = responsibilityKey;
    }

    public String getResponsibilityKey() {
        return responsibilityKey;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformStr(String platformStr) {
        this.platformStr = platformStr;
    }

    public String getPlatformStr() {
        return platformStr;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

}
