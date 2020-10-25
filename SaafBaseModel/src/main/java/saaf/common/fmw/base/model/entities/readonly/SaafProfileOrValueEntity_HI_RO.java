package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class SaafProfileOrValueEntity_HI_RO implements Serializable {
    public SaafProfileOrValueEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL_1 = " SELECT	epv.profile_value \"profileValue\"\n" +
        "        FROM\n" +
        "        saaf_profiles ep,\n" +
        "        saaf_profile_values epv\n" +
        "        WHERE\n" +
        "        ep.profile_id = epv.profile_id\n" +
        "        AND ep.enabled_flag = 'Y'\n" +
        "        AND date_format(ep.start_date_active,'%Y-%m-%d') <= CURDATE()  \n" +
        "        AND (ep.end_date_active is null or date_format(ep.end_date_active,'%Y-%m-%d') >= CURDATE())";

    public static String QUERY_SQL_2 = " SELECT \n" +
        "        ep.PROFILE_VALUE \"profileValue\"\n" +
        "        FROM\n" +
        "        saaf_profiles ep\n" +
        "        WHERE\n" +
        "        ep.enabled_flag = 'Y'\n" +
        "        AND date_format(ep.start_date_active,'%Y-%m-%d') <= CURDATE() \n" +
        "        AND (ep.end_date_active is null or date_format(ep.end_date_active,'%Y-%m-%d') >= CURDATE())";

    private Integer profileValueId;
    private String profileLevel;
    private String profileValue;

    public void setProfileValueId(Integer profileValueId) {
        this.profileValueId = profileValueId;
    }

    public Integer getProfileValueId() {
        return profileValueId;
    }

    public void setProfileLevel(String profileLevel) {
        this.profileLevel = profileLevel;
    }

    public String getProfileLevel() {
        return profileLevel;
    }

    public void setProfileValue(String profileValue) {
        this.profileValue = profileValue;
    }

    public String getProfileValue() {
        return profileValue;
    }
}
