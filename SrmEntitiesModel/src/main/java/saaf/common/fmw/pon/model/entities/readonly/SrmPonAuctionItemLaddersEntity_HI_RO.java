package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonAuctionItemLaddersEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:26 CST 2018  Auto Generate
 */

public class SrmPonAuctionItemLaddersEntity_HI_RO {
	//查询阶梯数量（不分页）
	public static final String QUERY_AUCTION_ITEMSLADDERS_LIST_SQL = "" +
			"SELECT\n" +
			"\tt.item_ladder_id AS itemLadderId,\n" +
			"\tt.auction_line_id AS auctionLineId,\n" +
			"\tt.auction_header_id AS auctionHeaderId,\n" +
			"\tt.ladder_quantity AS ladderQuantity,\n" +
			"\tt.notes AS notes,\n" +
			"\tt.version_num AS versionNum,\n" +
			"\tt.creation_date AS creationDate,\n" +
			"\tt.created_by AS createdBy,\n" +
			"\tt.last_updated_by AS lastUpdatedBy,\n" +
			"\tt.last_update_date AS lastUpdateDate,\n" +
			"\tt.last_update_login AS lastUpdateLogin,\n" +
			"\tt.attribute_category AS attributeCategory,\n" +
			"\tt.attribute1 AS attribute1,\n" +
			"\tt.attribute2 AS attribute2,\n" +
			"\tt.attribute3 AS attribute3,\n" +
			"\tt.attribute4 AS attribute4,\n" +
			"\tt.attribute5 AS attribute5,\n" +
			"\tt.attribute6 AS attribute6,\n" +
			"\tt.attribute7 AS attribute7,\n" +
			"\tt.attribute8 AS attribute8,\n" +
			"\tt.attribute9 AS attribute9,\n" +
			"\tt.attribute10 AS attribute10\n" +
			"FROM\n" +
			"\tsrm_pon_auction_item_ladders t\n" +
			"WHERE\n" +
			"\tt.auction_line_id = ?";

	private Integer itemLadderId; //洽谈行阶梯ID
	private Integer auctionLineId; //洽谈行ID
	private Integer auctionHeaderId; //洽谈ID
	private BigDecimal ladderQuantity; //阶梯数量
	private String notes; //备注
	private Integer ladderCount;//阶梯数量条数统计
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

	public void setItemLadderId(Integer itemLadderId) {
		this.itemLadderId = itemLadderId;
	}


	public Integer getItemLadderId() {
		return itemLadderId;
	}

	public void setAuctionLineId(Integer auctionLineId) {
		this.auctionLineId = auctionLineId;
	}


	public Integer getAuctionLineId() {
		return auctionLineId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}


	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setLadderQuantity(BigDecimal ladderQuantity) {
		this.ladderQuantity = ladderQuantity;
	}


	public BigDecimal getLadderQuantity() {
		return ladderQuantity;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getNotes() {
		return notes;
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

	public Integer getLadderCount() {
		return ladderCount;
	}

	public void setLadderCount(Integer ladderCount) {
		this.ladderCount = ladderCount;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
