package saaf.common.fmw.pon.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonJuryScoresEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:35 CST 2018  Auto Generate
 */

public class SrmPonJuryScoresEntity_HI_RO {

	//查询单条的洽谈评分
	public static final String QUERY_JURYSCORESLIST_SQL="SELECT\n"
			+ "t.jury_score_id AS juryScoreId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.jury_id AS juryId,\n"
			+ "t.score_comments AS scoreComments,\n"
			+ "t.score_file_id AS scoreFileId,\n"
			+ "t.version_num AS versionNum,\n"
			+ "t.creation_date AS creationDate,\n"
			+ "t.created_by AS createdBy,\n"
			+ "t.last_updated_by AS lastUpdatedBy,\n"
			+ "t.last_update_date AS lastUpdateDate,\n"
			+ "t.last_update_login AS lastUpdateLogin,\n"
			+ "t.attribute_category AS attributeCategory,\n"
			+ "t.attribute1 AS attribute1,\n"
			+ "t.attribute2 AS attribute2,\n"
			+ "t.attribute3 AS attribute3,\n"
			+ "t.attribute4 AS attribute4,\n"
			+ "t.attribute5 AS attribute5,\n"
			+ "t.attribute6 AS attribute6,\n"
			+ "t.attribute7 AS attribute7,\t\n"
			+ "t.attribute8 AS attribute8,\n"
			+ "t.attribute9 AS attribute9,\n"
			+ "t.attribute10 AS attribute10,\n"
			+ "sbrf.file_Name AS scoreFileName,\n"
			+ "sbrf.access_Path AS scoreFileAccessPath\n"
			+ "FROM srm_pon_jury_scores t\n"
			+ "LEFT JOIN saaf_base_result_file sbrf ON sbrf.file_id = t.score_file_id\n"
			+ "WHERE 1=1 ";

    private Integer juryScoreId; //洽谈评分ID
    private Integer auctionHeaderId; //洽谈ID
    private Integer juryId; //评标小组ID
    private String scoreComments; //评分说明
    private Integer scoreFileId; //评分附件ID
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
    private Integer operatorUserId;
    //别名
	private String scoreFileName; //评分附件name
	private String scoreFileAccessPath; //评分附件路径

	public String getScoreFileName() {
		return scoreFileName;
	}

	public void setScoreFileName(String scoreFileName) {
		this.scoreFileName = scoreFileName;
	}

	public String getScoreFileAccessPath() {
		return scoreFileAccessPath;
	}

	public void setScoreFileAccessPath(String scoreFileAccessPath) {
		this.scoreFileAccessPath = scoreFileAccessPath;
	}

	public void setJuryScoreId(Integer juryScoreId) {
		this.juryScoreId = juryScoreId;
	}

	
	public Integer getJuryScoreId() {
		return juryScoreId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setJuryId(Integer juryId) {
		this.juryId = juryId;
	}

	
	public Integer getJuryId() {
		return juryId;
	}

	public void setScoreComments(String scoreComments) {
		this.scoreComments = scoreComments;
	}

	
	public String getScoreComments() {
		return scoreComments;
	}

	public void setScoreFileId(Integer scoreFileId) {
		this.scoreFileId = scoreFileId;
	}

	
	public Integer getScoreFileId() {
		return scoreFileId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
