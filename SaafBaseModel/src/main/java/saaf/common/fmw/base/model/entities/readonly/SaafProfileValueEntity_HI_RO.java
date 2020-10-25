package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;

public class SaafProfileValueEntity_HI_RO implements Serializable {
    public SaafProfileValueEntity_HI_RO() {
        super();
    }

    public static String QUERY_SQL = "		SELECT\n" +
        "			spv.PROFILE_VALUE_ID \"profileValueId\",\n" +
        "			spv.PROFILE_ID \"profileId\",\n" +
        "			spv.PROFILE_LEVEL_ID \"profileLevelId\",\n" +
        "			spv.PROFILE_LEVEL \"profileLevel\",\n" +
        "			spv.PROFILE_VALUE \"profileValue\",\n" +
        "			spv.ENABLED_FLAG \"enabledFlag\",\n" +
        "			spv.DESCRIPTION \"description\",\n" +
        "\n" +
        "		IF (\n" +
        "			spv.PROFILE_LEVEL = 'O',\n" +
        "			org.INST_NAME,\n" +
        "\n" +
        "		IF (\n" +
        "			spv.PROFILE_LEVEL = 'R',\n" +
        "			respon.responsibility_name,\n" +
        "\n" +
        "		IF (\n" +
        "			spv.PROFILE_LEVEL = 'U',\n" +
        "			fu.user_name,\n" +
        "\n" +
        "		IF (\n" +
        "			spv.PROFILE_LEVEL = 'F',\n" +
        "			func.prompt,\n" +
        "			''\n" +
        "		)\n" +
        "		)\n" +
        "		)\n" +
        "		) \"profileLevelName\"\n" +
        "		FROM\n" +
        "			saaf_profile_values spv\n" +
        "		LEFT JOIN saaf_institution org ON spv.PROFILE_LEVEL_ID = org.INST_ID\n" +
        "		LEFT JOIN saaf_responsibilitys respon ON spv.PROFILE_LEVEL_ID = respon.responsibility_id\n" +
        "		LEFT JOIN saaf_users fu ON spv.PROFILE_LEVEL_ID = fu.user_id\n" +
        "		LEFT JOIN saaf_menu_functions func ON spv.PROFILE_LEVEL_ID = func.menu_id\n";

    private int profileValueId;
    private int profileId;
    private int profileLevelId;
    private String profileLevel;
    private String profileValue;
    private String enabledFlag;
    private String description;
    private String profileLevelName;

    public void setProfileValueId(int profileValueId) {
        this.profileValueId = profileValueId;
    }

    public int getProfileValueId() {
        return profileValueId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileLevelId(int profileLevelId) {
        this.profileLevelId = profileLevelId;
    }

    public int getProfileLevelId() {
        return profileLevelId;
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

    public void setProfileLevelName(String profileLevelName) {
        this.profileLevelName = profileLevelName;
    }

    public String getProfileLevelName() {
        return profileLevelName;
    }
}
