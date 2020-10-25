package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPonAuctionItemsEntity_HI Entity Object
 * Tue Apr 17 11:14:24 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_auction_items")
public class SrmPonAuctionItemsEntity_HI {
    private Integer auctionLineId; //洽谈行ID
    private Integer auctionHeaderId; //洽谈ID
    private Integer lineNumber; //行号
    private String dispLineNumber; //显示行号
    private String lineType; //行类型，LINE：行，GROUP：组，GROUP_LINE：组的行
    private Integer parentLineId; //父级行ID，备用字段
    private Integer groupId; //组别ID
    private Integer itemId; //物料ID，关联表：SRM_BASE_ITEMS
    private String itemDescription; //物料说明
    private String unitOfMeasure; //计量单位
    private Integer categoryId; //分类ID，关联表：SRM_BASE_CATEGORIES
    private BigDecimal quantity; //数量
    private String taxRateCode; //税率
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //结束日期
    private String awardStatus; //决标状态
    private BigDecimal awardedQuantity; //决标数量
    private Integer fileId; //附件ID
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
	private String groupName; //组别名称
	private String itemCode; //物料编码
    private String specification; //规格型号
	private BigDecimal webReferencePrice;//网站参考价
	private BigDecimal discountPrice;//折后价
    private BigDecimal cost;//成本预算
    private BigDecimal marketInquiry;//市场询价
	public void setAuctionLineId(Integer auctionLineId) {
		this.auctionLineId = auctionLineId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_AUCTION_ITEMS_S", sequenceName = "SRM_PON_AUCTION_ITEMS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_AUCTION_ITEMS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "auction_line_id", nullable = false, length = 11)	
	public Integer getAuctionLineId() {
		return auctionLineId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	@Column(name = "auction_header_id", nullable = false, length = 11)	
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

    @Column(name = "specification", nullable = true, length = 240)
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Column(name = "line_number", nullable = true, length = 11)	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setDispLineNumber(String dispLineNumber) {
		this.dispLineNumber = dispLineNumber;
	}

	@Column(name = "disp_line_number", nullable = true, length = 30)	
	public String getDispLineNumber() {
		return dispLineNumber;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	@Column(name = "line_type", nullable = true, length = 30)	
	public String getLineType() {
		return lineType;
	}

	public void setParentLineId(Integer parentLineId) {
		this.parentLineId = parentLineId;
	}

	@Column(name = "parent_line_id", nullable = true, length = 11)	
	public Integer getParentLineId() {
		return parentLineId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_id", nullable = true, length = 11)	
	public Integer getGroupId() {
		return groupId;
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

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "quantity", precision = 15, scale = 3)	
	public BigDecimal getQuantity() {
		return quantity;
	}

    @Column(name = "cost", precision = 15, scale = 6)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	@Column(name = "tax_rate_code", nullable = true, length = 30)	
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "start_date", nullable = true, length = 0)	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "end_date", nullable = true, length = 0)	
	public Date getEndDate() {
		return endDate;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	@Column(name = "award_status", nullable = true, length = 30)	
	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardedQuantity(BigDecimal awardedQuantity) {
		this.awardedQuantity = awardedQuantity;
	}

	@Column(name = "awarded_quantity", precision = 15, scale = 3)	
	public BigDecimal getAwardedQuantity() {
		return awardedQuantity;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 11)	
	public Integer getFileId() {
		return fileId;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "notes", nullable = true, length = 2000)	
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

	@Column(name = "web_reference_price", precision = 15, scale = 6)
	public BigDecimal getWebReferencePrice() {
		return webReferencePrice;
	}

	public void setWebReferencePrice(BigDecimal webReferencePrice) {
		this.webReferencePrice = webReferencePrice;
	}

    @Column(name = "market_inquiry", precision = 15, scale = 6)
    public BigDecimal getMarketInquiry() {
        return marketInquiry;
    }

    public void setMarketInquiry(BigDecimal marketInquiry) {
        this.marketInquiry = marketInquiry;
    }

    @Column(name = "discount_price", precision = 15, scale = 6)
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Transient
	public String getGroupName() {
		return groupName;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Transient
	public String getItemCode() {
		return itemCode;
	}

}
