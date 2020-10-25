package saaf.common.fmw.pos.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhy on 2018/3/14.
 */
public class SrmPosSceneManageEntity_HI_RO implements Serializable {

	//是否需要资质审查，样品试验，现场考察SQL
    public static  String YES_NO_SQL =
                    "SELECT\n" +
                    "  t.qualified_censor_flag AS qualifiedCensorFlag,\n" +
                    "  t.sample_trials_flag AS sampleTrialsFlag,\n" +
                    "  t.locale_review_flag AS localeReviewFlag\n" +
                    "FROM\n" +
                    "  srm_pos_scene_manage t";

    public static String QUERY_SCENE_SQL =
                    "SELECT\n" +
                    "  sm.scene_manage_id sceneManageId,\n" +
                    "  sm.scene_type sceneType,\n" +
                    "  sm.scene_description sceneDescription,\n" +
                    "  sm.qualified_censor_flag qualifiedCensorFlag,\n" +
                    "  sm.locale_review_flag localeReviewFlag,\n" +
                    "  sm.sample_trials_flag sampleTrialsFlag,\n" +
                    "  sm.last_update_date lastUpdateDate,\n" +
                    "  su.user_full_name userName,\n" +
                    "  slv.meaning sceneTypeMean\n" +
                    "FROM\n" +
                    "  srm_pos_scene_manage sm\n" +
                    "  LEFT JOIN saaf_lookup_values slv\n" +
                    "    ON sm.scene_type = slv.lookup_code\n" +
                    "    AND slv.lookup_type = 'POS_SCENE_TYPE',\n" +
                    "  saaf_users su\n" +
                    "WHERE sm.created_by = su.user_id";

    public static String COMMON_QUERY_SQL =
                    "SELECT\n" +
                    "  psm.scene_manage_id AS sceneManageId,\n" +
                    "  psm.scene_type AS sceneType,\n" +
                    "  psm.scene_description AS sceneDescription,\n" +
                    "  psm.qualified_censor_flag AS qualifiedCensorFlag,\n" +
                    "  psm.locale_review_flag AS localeReviewFlag,\n" +
                    "  psm.sample_trials_flag AS sampleTrialsFlag\n" +
                    "FROM\n" +
                    "  srm_pos_scene_manage psm\n" +
                    "WHERE 1 = 1";

    private Integer sceneManageId;
    private String sceneType;
    private String sceneDescription;
    private String qualifiedCensorFlag;
    private String localeReviewFlag;
    private String sampleTrialsFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    private String userName;
    private String sceneTypeMean;
    
    private String meaning;

    public Integer getSceneManageId() {
        return sceneManageId;
    }

    public void setSceneManageId(Integer sceneManageId) {
        this.sceneManageId = sceneManageId;
    }
    
	public String getSceneType() {
		return sceneType;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public String getSceneDescription() {
        return sceneDescription;
    }

    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    public String getQualifiedCensorFlag() {
        return qualifiedCensorFlag;
    }

    public void setQualifiedCensorFlag(String qualifiedCensorFlag) {
        this.qualifiedCensorFlag = qualifiedCensorFlag;
    }

    public String getLocaleReviewFlag() {
        return localeReviewFlag;
    }

    public void setLocaleReviewFlag(String localeReviewFlag) {
        this.localeReviewFlag = localeReviewFlag;
    }

    public String getSampleTrialsFlag() {
        return sampleTrialsFlag;
    }

    public void setSampleTrialsFlag(String sampleTrialsFlag) {
        this.sampleTrialsFlag = sampleTrialsFlag;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSceneTypeMean() {
        return sceneTypeMean;
    }

    public void setSceneTypeMean(String sceneTypeMean) {
        this.sceneTypeMean = sceneTypeMean;
    }

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
    
}
