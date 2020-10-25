package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonJuryScoreLinesEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:37 CST 2018  Auto Generate
 */

public class SrmPonJuryScoreLinesEntity_HI_RO {
	//查询洽谈评分明细list（不分页）
	public static final String QUERY_JURYSCORELINESLIST_SQL="SELECT\n"
			+ "t.jury_score_line_id AS juryScoreLineId,\n"
			+ "t.jury_score_id AS juryScoreId,\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.score_partition AS scorePartition,\n"
			+ "t.score AS score,\n"
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
			+ "spbh.bid_header_id AS bidHeaderIdV,\n"
			+ "spbh.bid_status AS bidStatus,\n"
			+ "spbh.supplier_id AS supplierId,\n"
			+ "spbh.auction_header_id AS auctionHeaderId,\n"
			+ "spbh.supplier_name AS supplierName,\n"
			+ "spjs.jury_id AS juryId\n"
			+ "FROM srm_pon_jury_score_lines t\n"
			+ "LEFT JOIN srm_pon_jury_scores spjs ON spjs.jury_score_id = t.jury_score_id\n"
			+ "RIGHT JOIN srm_pon_bid_headers spbh ON spbh.bid_header_id=t.bid_header_id ";
	//评分汇总查询——评分明细list（不分页）
	public static final String QUERY_JURYSCORELINESBYBIDHEADERIDLIST_SQL="SELECT\n"
			+ "t.jury_score_line_id AS juryScoreLineId,\n"
			+ "t.jury_score_id AS juryScoreId,\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.score_partition AS scorePartition,\n"
			+ "t.score AS score,\n"
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
			+ "spjs.score_comments AS scoreComments,\n"
			+ "spjs.score_file_id AS scoreFileId,\n"
			+ "sbrf.file_Name AS scoreFileName,\n"
			+ "sbrf.access_Path AS scoreFileAccessPath,\n"
			+ "se.employee_id AS employeeId,\n"
			+ "se.employee_name AS employeeName,\n"
			+ "spjs.jury_id AS juryId\n"
			+ "FROM srm_pon_jury_score_lines t\n"
			+ "LEFT JOIN srm_pon_jury_scores spjs ON spjs.jury_score_id=t.jury_score_id\n"
			+ "LEFT JOIN srm_pon_auction_juries spaj ON spaj.jury_id=spjs.jury_id\n"
			+ "LEFT JOIN saaf_employees se ON se.employee_id=spaj.employee_id\n"
			+ "LEFT JOIN saaf_base_result_file sbrf ON sbrf.file_id = spjs.score_file_id ";

	public static final String QUERY_SCORE_LINES_ALL_SQL="select spas.supplier_name,\n" +
			"       spas.supplier_id,\n" +
			"       spasr.score_item,\n" +
			"       spasr.power_weight,\n" +
			"       se.employee_name,\n" +
			"       spjsl.score,\n" +
			"       nvl(spasr.score_rule_id, spjsl.score_rule_id) score_rule_id,\n" +
			"       spjsl.jury_score_line_id,\n" +
			"       spjsl.jury_score_id,\n" +
			"       spaj.jury_id,\n" +
			"       spasr.auction_header_id,\n" +
			"       spbh.bid_header_id bid_header_id\n" +
			"  from srm_pon_auction_score_rules spasr\n" +
			"  left join srm_pon_bid_headers spbh on spbh.auction_header_id = spasr.auction_header_id\n" +
			"  left join srm_pon_jury_score_lines spjsl on spjsl.score_rule_id = spasr.score_rule_id\n" +
			"  left join srm_pon_auction_suppliers spas\n" +
			"    on spasr.auction_header_id = spas.auction_header_id,\n" +
			" saaf_employees se,\n" +
			" srm_pon_auction_juries spaj\n" +
			" where 1 = 1\n" +
			"   and spaj.employee_id = se.employee_id\n" +
			"   and spbh.supplier_id = spas.supplier_id\n" +
			"   and spbh.bid_status = 'ACT'\n" +
			"   and spaj.auction_header_id = spasr.auction_header_id\n" +
			"   and spaj.user_duty = 5\n";

    private Integer juryScoreLineId; //洽谈评分明细ID
    private Integer juryScoreId; //洽谈评分ID
    private Integer bidHeaderId; //供应商报价ID
    private String scorePartition; //评标分区
	@JSONField(format = "##0.00")
    private BigDecimal score; //得分
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
	private Integer supplierId;//供应商Id
	private String supplierName;//供应商名称
	private String bidStatus;//供应商报价状态
	private Integer bidHeaderIdV;//供应商报价ID(备用)
	private Integer auctionHeaderId;//洽谈Id
	private String scoreFileName; //评分附件name
	private String scoreFileAccessPath; //评分附件路径
	private String employeeName;
	private Integer employeeId;
	private Integer juryId; //评标小组ID
	private Integer scoreFileId; //评分附件ID
	private String scoreItem;//评分项
	private BigDecimal powerWeight;//权重
	private Integer scoreRuleId;//洽谈评分规则ID

	public Integer getScoreFileId() {
		return scoreFileId;
	}

	public void setScoreFileId(Integer scoreFileId) {
		this.scoreFileId = scoreFileId;
	}

	private String scoreComments; //评分说明

	public String getScoreComments() {
		return scoreComments;
	}

	public void setScoreComments(String scoreComments) {
		this.scoreComments = scoreComments;
	}

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

	public Integer getJuryId() {
		return juryId;
	}

	public void setJuryId(Integer juryId) {
		this.juryId = juryId;
	}

	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	public Integer getBidHeaderIdV() {
		return bidHeaderIdV;
	}

	public void setBidHeaderIdV(Integer bidHeaderIdV) {
		this.bidHeaderIdV = bidHeaderIdV;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public void setJuryScoreLineId(Integer juryScoreLineId) {
		this.juryScoreLineId = juryScoreLineId;
	}

	
	public Integer getJuryScoreLineId() {
		return juryScoreLineId;
	}

	public void setJuryScoreId(Integer juryScoreId) {
		this.juryScoreId = juryScoreId;
	}

	
	public Integer getJuryScoreId() {
		return juryScoreId;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	
	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setScorePartition(String scorePartition) {
		this.scorePartition = scorePartition;
	}

	
	public String getScorePartition() {
		return scorePartition;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	
	public BigDecimal getScore() {
		return score;
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

	public String getScoreItem() {
		return scoreItem;
	}

	public void setScoreItem(String scoreItem) {
		this.scoreItem = scoreItem;
	}

	public BigDecimal getPowerWeight() {
		return powerWeight;
	}

	public void setPowerWeight(BigDecimal powerWeight) {
		this.powerWeight = powerWeight;
	}

	public Integer getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(Integer scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}
}
