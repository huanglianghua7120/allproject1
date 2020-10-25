package saaf.common.fmw.pon.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonJuryCountersignsEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:38 CST 2018  Auto Generate
 */

public class SrmPonJuryCountersignsEntity_HI_RO {
    //会签查询（不分页）——已截止
    public static final String QUERY_JURYCOUNTERSIGNSLIST_SQL ="SELECT\n"
			+ "t.jury_countersign_id AS juryCountersignId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.jury_id AS juryId,\n"
			+ "t.round AS round,\n"
			+ "t.countersign_opinion AS countersignOpinion,\n"
			+ "t.countersign_comments AS countersignComments,\n"
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
			+ "t.attribute7 AS attribute7,\n"
			+ "t.attribute8 AS attribute8,\n"
			+ "t.attribute9 AS attribute9,\n"
			+ "t.attribute10 AS attribute10,\n"
			+ "spaj.employee_id AS employeeId,\n"
			+ "se.employee_name AS employeeName\n"
			+ "FROM srm_pon_jury_countersigns t\n"
			+ "LEFT JOIN srm_pon_auction_juries spaj ON spaj.jury_id = t.jury_id\n"
			+ "LEFT JOIN saaf_employees se ON se.employee_id = spaj.employee_id\n"
			+ "WHERE 1=1 ";

    private Integer juryCountersignId; //洽谈会签ID
    private Integer auctionHeaderId; //洽谈ID
    private Integer juryId; //评标小组ID
    private Integer round; //轮次
    private String countersignOpinion; //会签意见，AGREE：同意，REJECT：拒绝
    private String countersignComments; //会签说明
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
	private String countersignOpinionName;//同意或拒绝的中文——会签意见
	private String roundName;//会签次数——第几轮会签
	private String employeeName;
	private Integer employeeId;

	public String getCountersignOpinionName() {
		return countersignOpinionName;
	}

	public void setCountersignOpinionName(String countersignOpinionName) {
		this.countersignOpinionName = countersignOpinionName;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setJuryCountersignId(Integer juryCountersignId) {
		this.juryCountersignId = juryCountersignId;
	}

	
	public Integer getJuryCountersignId() {
		return juryCountersignId;
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

	public void setRound(Integer round) {
		this.round = round;
	}

	
	public Integer getRound() {
		return round;
	}

	public void setCountersignOpinion(String countersignOpinion) {
		this.countersignOpinion = countersignOpinion;
	}

	
	public String getCountersignOpinion() {
		return countersignOpinion;
	}

	public void setCountersignComments(String countersignComments) {
		this.countersignComments = countersignComments;
	}

	
	public String getCountersignComments() {
		return countersignComments;
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
