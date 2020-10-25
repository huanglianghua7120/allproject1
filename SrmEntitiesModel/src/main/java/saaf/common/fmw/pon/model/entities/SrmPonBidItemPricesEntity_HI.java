package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPonBidItemPricesEntity_HI Entity Object
 * Tue Apr 17 11:14:34 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_bid_item_prices")
public class SrmPonBidItemPricesEntity_HI {
    private Integer bidLineId; //供应商报价行ID
    private Integer bidHeaderId; //供应商报价头ID
    private Integer auctionHeaderId; //洽谈头ID
    private Integer auctionLineId; //洽谈行ID
    private Integer itemLadderId; //洽谈行阶梯ID
    private Integer itemId; //物料ID
    private String itemDescription; //物料说明
    private String unitOfMeasure; //计量单位
    private BigDecimal promisedQuantity; //承诺数量
    private BigDecimal taxPrice; //含税单价
    private String taxRate; //税率
    private BigDecimal noTaxPrice; //不含税单价
    private Integer rank; //排名
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date promisedStartDate; //承诺开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date promisedEndDate; //承诺结束日期
    private BigDecimal awardProportion;//中标份额
    private BigDecimal awardQuantity; //中标数量
    private String awardStatus; //中标状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date awardDate; //中标时间
    private Integer bidLineFileId; //附件
    private String notes; //备注
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
	private BigDecimal bargain; //含税单价
	private BigDecimal materialsPrice;//材料单价
	private BigDecimal artificialPrice;//人工单价
	private BigDecimal bargainTax; //议价税率
    private String specification;

	public void setBidLineId(Integer bidLineId) {
		this.bidLineId = bidLineId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_BID_ITEM_PRICES_S", sequenceName = "SRM_PON_BID_ITEM_PRICES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_BID_ITEM_PRICES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "bid_line_id", nullable = false, length = 11)	
	public Integer getBidLineId() {
		return bidLineId;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	@Column(name = "bid_header_id", nullable = false, length = 11)	
	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	@Column(name = "auction_header_id", nullable = false, length = 11)	
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setAuctionLineId(Integer auctionLineId) {
		this.auctionLineId = auctionLineId;
	}

	@Column(name = "auction_line_id", nullable = false, length = 11)	
	public Integer getAuctionLineId() {
		return auctionLineId;
	}

	public void setItemLadderId(Integer itemLadderId) {
		this.itemLadderId = itemLadderId;
	}

	@Column(name = "item_ladder_id", nullable = true, length = 11)	
	public Integer getItemLadderId() {
		return itemLadderId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "item_description", nullable = true, length = 240)	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Column(name = "unit_of_measure", nullable = true, length = 30)	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

    @Column(name = "specification", nullable = true, length = 240)
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setPromisedQuantity(BigDecimal promisedQuantity) {
		this.promisedQuantity = promisedQuantity;
	}

	@Column(name = "promised_quantity", precision = 15, scale = 3)	
	public BigDecimal getPromisedQuantity() {
		return promisedQuantity;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "tax_price", precision = 15, scale = 6)	
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "tax_rate", nullable = true, length = 30)	
	public String getTaxRate() {
		return taxRate;
	}

	public void setNoTaxPrice(BigDecimal noTaxPrice) {
		this.noTaxPrice = noTaxPrice;
	}

	@Column(name = "no_tax_price", precision = 15, scale = 6)	
	public BigDecimal getNoTaxPrice() {
		return noTaxPrice;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "rank", nullable = true, length = 11)	
	public Integer getRank() {
		return rank;
	}

	public void setPromisedStartDate(Date promisedStartDate) {
		this.promisedStartDate = promisedStartDate;
	}

	@Column(name = "promised_start_date", nullable = true, length = 0)	
	public Date getPromisedStartDate() {
		return promisedStartDate;
	}

	public void setPromisedEndDate(Date promisedEndDate) {
		this.promisedEndDate = promisedEndDate;
	}

	@Column(name = "promised_end_date", nullable = true, length = 0)	
	public Date getPromisedEndDate() {
		return promisedEndDate;
	}
	
	@Column(name = "award_proportion", precision = 15, scale = 3)	
	public BigDecimal getAwardProportion() {
		return awardProportion;
	}

	public void setAwardProportion(BigDecimal awardProportion) {
		this.awardProportion = awardProportion;
	}

	public void setAwardQuantity(BigDecimal awardQuantity) {
		this.awardQuantity = awardQuantity;
	}

	@Column(name = "award_quantity", precision = 15, scale = 3)	
	public BigDecimal getAwardQuantity() {
		return awardQuantity;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	@Column(name = "award_status", nullable = true, length = 30)	
	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}

	@Column(name = "award_date", nullable = true, length = 11)
	public Date getAwardDate() {
		return awardDate;
	}

	public void setBidLineFileId(Integer bidLineFileId) {
		this.bidLineFileId = bidLineFileId;
	}

	@Column(name = "bid_line_file_id", nullable = true, length = 11)	
	public Integer getBidLineFileId() {
		return bidLineFileId;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "notes", nullable = true, length = 240)	
	public String getNotes() {
		return notes;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 11)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 11)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name = "attribute_category", nullable = true, length = 30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", nullable = true, length = 240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", nullable = true, length = 240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", nullable = true, length = 240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", nullable = true, length = 240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", nullable = true, length = 240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name = "attribute6", nullable = true, length = 240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name = "attribute7", nullable = true, length = 240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name = "attribute8", nullable = true, length = 240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name = "attribute9", nullable = true, length = 240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name = "attribute10", nullable = true, length = 240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	@Column(name = "bargain", nullable = true, length = 15)
	public BigDecimal getBargain() {
		return bargain;
	}

	public void setBargain(BigDecimal bargain) {
		this.bargain = bargain;
	}

	@Column(name = "materials_price", nullable = true, length = 15)
	public BigDecimal getMaterialsPrice() {
		return materialsPrice;
	}

	public void setMaterialsPrice(BigDecimal materialsPrice) {
		this.materialsPrice = materialsPrice;
	}

	@Column(name = "artificial_price", nullable = true, length = 15)
	public BigDecimal getArtificialPrice() {
		return artificialPrice;
	}

	public void setArtificialPrice(BigDecimal artificialPrice) {
		this.artificialPrice = artificialPrice;
	}

	@Column(name = "bargain_tax", nullable = true, length = 15)
	public BigDecimal getBargainTax() {
		return bargainTax;
	}

	public void setBargainTax(BigDecimal bargainTax) {
		this.bargainTax = bargainTax;
	}
}
