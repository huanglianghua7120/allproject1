package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafProfilesEntity_HI_RO implements Serializable {
    public SaafProfilesEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL = " SELECT sp.profile_id        \"profileId\"\r\n" +
        "       ,sp.profile_name      \"profileName\"\r\n" +
        "       ,sp.profile_number    \"profileNumber\"\r\n" +
        "       ,sp.profile_type      \"profileType\"\r\n" +
        "       ,sp.profile_value     \"profileValue\"\r\n" +
        "       ,sp.enabled_flag      \"enabledFlag\"\r\n" +
        "       ,sp.description       \"description\"\r\n" +
        "       ,sp.start_date_active \"startDateActive\"\r\n" +
        "       ,sp.end_date_active \"endDateActive\"\r\n" +
        "       ,slv.meaning          \"profileTypeStr\"\r\n" +
        "  FROM saaf_profiles      sp\r\n" +
        "      ,saaf_lookup_values slv\r\n" +
        " WHERE slv.lookup_type = 'PROFILE_TYPE'\r\n" +
        "   AND sp.profile_type = slv.lookup_code\r\n" +
        "   AND slv.enabled_flag = 'Y' ";

    private int profileId;
    private String profileName;
    private String profileNumber;
    private String profileType;
    private String profileValue;
    private String enabledFlag;
    private String description;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    private String profileTypeStr;

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileNumber(String profileNumber) {
        this.profileNumber = profileNumber;
    }

    public String getProfileNumber() {
        return profileNumber;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    public String getProfileValue() {
        return profileValue;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public void setProfileTypeStr(String profileTypeStr) {
        this.profileTypeStr = profileTypeStr;
    }

    public String getProfileTypeStr() {
        return profileTypeStr;
    }
}
