package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmPonAuctionHeadersEntity_HI Entity Object
 * Tue Apr 17 11:14:21 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_auction_headers")
public class SrmPonAuctionHeadersEntity_HI {
    private Integer auctionHeaderId; //洽谈ID
    private String auctionNumber; //洽谈编号
    private String auctionTitle; //洽谈标题
    private Integer orgId; //洽谈主体单位，关联表：SAAF_INSTITUTION
    private String auctionType; //洽谈类型，快码：PON_AUCTION_TYPE
    private String contractType; //合同类型（招标结果），快码：PON_CONTRACT_TYPE
    private String auctionStatus; //洽谈状态，快码：PON_AUCTION_STATUS
    private Integer buyerId; //采购员，关联表：SAAF_EMPLOYEES 的 EMPLOYEE_ID
    private String invitingBidWay; //邀标方式
    private Integer receiveToOrganizationId; //收货组织ID
    private String receiveToAddress; //收货/收单地址
    private String paymentCondition; //付款条件
    private String paymentConditionUpdateFlag; //允许供应商修改付款条件（Y/N）
    private String subsectionFlag; //分段价格（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bidStartDate; //报价开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bidEndDate; //报价截止时间
    private String currencyCode; //币种，关联表：SAAF_LOOKUP_VALUES（BANK_CURRENCY）
    private Integer numberPriceDecimals; //报价的小数位
    private String allowUpdateTaxRate; //允许修改税率（Y/N）
    private String showCurrentRoundMinPrice; //向供应商展示本轮最低价格（Y/N）
    private String showCurrentRoundRanking; //向供应商展示本轮排名（Y/N）
    private String allItemBidFlag; //供应商必须回应所有行（Y/N）
    private String multipleBidFlag; //允许多次报价（Y/N）
    private Integer maxBidFrequency; //多次报价次数
    private BigDecimal minDecreasingRange; //最小降幅
    private BigDecimal bidBond; //保证金金额
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date bidBondTerm; //保证金缴纳期限
    private String bidBondAccountNumber; //保证金缴纳账户
    private String bidBondBankName; //保证金缴纳账户开户行
    private BigDecimal tenderCost; //标书费用
    private String publishApprovalStatus; //发布审批状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishApprovalDate; //发布批准时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate; //发布时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date closeBiddingDate; //截标时间
    private String noteToSupplier; //给供应商的附注
    private String noteToJury; //给评标小组的附注
    private Integer toSupplierFileId; //给供应商的附件ID
    private Integer toJuryFileId; //给评标小组的附件ID
	private String allowScoreFlag;//是否可评分标识（Y/N）
    private Integer rounds; //第几轮
    private Integer firstRound; //第一轮ID
    private Integer lastRound; //上一轮ID
    private String auctionRoundNumber; //洽谈轮次编号
    private String openBiddingFlag; //是否开标（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date openBiddingDate; //开标时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date judgeCompleteDate; //评标完成时间
    private String awardStatus; //决标状态
    private String awardApprovalStatus; //决标审批状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date awardCompleteDate; //决标完成时间
    private String awardComments; //决标说明
    private String poCreateFlag; //是否已创建采购订单（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date poCreateDate; //采购订单创建时间
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
	private Integer templateId;//合同模板ID
	private String templateName;//合同模板名称
	private String templateCode;//合同模板编码
	private String itemType;//询价物料类型
	private String ekpNumber;//EKP编号
    private String taxRateCode;
    private BigDecimal percent;
    private Integer organizationId;
    private String okcCreateFlag; //是否已创建合同（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date okcCreateDate; //合同创建时间


	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_AUCTION_HEADERS_S", sequenceName = "SRM_PON_AUCTION_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_AUCTION_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "auction_header_id", nullable = false, length = 11)	
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

    @Column(name = "tax_rate_code", nullable = true, length = 240)
    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }

    @Column(name = "organization_id", nullable = true, length = 10)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "percent", precision = 15, scale = 6)
    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	@Column(name = "auction_number", nullable = false, length = 30)	
	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}

	@Column(name = "auction_title", nullable = false, length = 240)	
	public String getAuctionTitle() {
		return auctionTitle;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = false, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setAuctionType(String auctionType) {
		this.auctionType = auctionType;
	}

	@Column(name = "auction_type", nullable = true, length = 30)	
	public String getAuctionType() {
		return auctionType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name = "contract_type", nullable = true, length = 30)	
	public String getContractType() {
		return contractType;
	}

	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	@Column(name = "auction_status", nullable = true, length = 30)	
	public String getAuctionStatus() {
		return auctionStatus;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "buyer_id", nullable = true, length = 11)	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setInvitingBidWay(String invitingBidWay) {
		this.invitingBidWay = invitingBidWay;
	}

	@Column(name = "inviting_bid_way", nullable = true, length = 30)	
	public String getInvitingBidWay() {
		return invitingBidWay;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	@Column(name = "receive_to_organization_id", nullable = true, length = 11)	
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setReceiveToAddress(String receiveToAddress) {
		this.receiveToAddress = receiveToAddress;
	}

	@Column(name = "receive_to_address", nullable = true, length = 240)	
	public String getReceiveToAddress() {
		return receiveToAddress;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	@Column(name = "payment_condition", nullable = true, length = 30)	
	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentConditionUpdateFlag(String paymentConditionUpdateFlag) {
		this.paymentConditionUpdateFlag = paymentConditionUpdateFlag;
	}

	@Column(name = "payment_condition_update_flag", nullable = true, length = 1)	
	public String getPaymentConditionUpdateFlag() {
		return paymentConditionUpdateFlag;
	}

	public void setSubsectionFlag(String subsectionFlag) {
		this.subsectionFlag = subsectionFlag;
	}

	@Column(name = "subsection_flag", nullable = true, length = 1)	
	public String getSubsectionFlag() {
		return subsectionFlag;
	}

	public void setBidStartDate(Date bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	@Column(name = "bid_start_date", nullable = true, length = 0)	
	public Date getBidStartDate() {
		return bidStartDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	@Column(name = "bid_end_date", nullable = true, length = 0)	
	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setNumberPriceDecimals(Integer numberPriceDecimals) {
		this.numberPriceDecimals = numberPriceDecimals;
	}

	@Column(name = "number_price_decimals", nullable = true, length = 11)	
	public Integer getNumberPriceDecimals() {
		return numberPriceDecimals;
	}

	public void setAllowUpdateTaxRate(String allowUpdateTaxRate) {
		this.allowUpdateTaxRate = allowUpdateTaxRate;
	}

	@Column(name = "allow_update_tax_rate", nullable = true, length = 1)	
	public String getAllowUpdateTaxRate() {
		return allowUpdateTaxRate;
	}

	public void setShowCurrentRoundMinPrice(String showCurrentRoundMinPrice) {
		this.showCurrentRoundMinPrice = showCurrentRoundMinPrice;
	}

	@Column(name = "show_current_round_min_price", nullable = true, length = 1)	
	public String getShowCurrentRoundMinPrice() {
		return showCurrentRoundMinPrice;
	}

	public void setShowCurrentRoundRanking(String showCurrentRoundRanking) {
		this.showCurrentRoundRanking = showCurrentRoundRanking;
	}

	@Column(name = "show_current_round_ranking", nullable = true, length = 1)	
	public String getShowCurrentRoundRanking() {
		return showCurrentRoundRanking;
	}

	public void setAllItemBidFlag(String allItemBidFlag) {
		this.allItemBidFlag = allItemBidFlag;
	}

	@Column(name = "all_item_bid_flag", nullable = true, length = 1)	
	public String getAllItemBidFlag() {
		return allItemBidFlag;
	}

	public void setMultipleBidFlag(String multipleBidFlag) {
		this.multipleBidFlag = multipleBidFlag;
	}

	@Column(name = "multiple_bid_flag", nullable = true, length = 1)	
	public String getMultipleBidFlag() {
		return multipleBidFlag;
	}

	public void setMaxBidFrequency(Integer maxBidFrequency) {
		this.maxBidFrequency = maxBidFrequency;
	}

	@Column(name = "max_bid_frequency", nullable = true, length = 11)	
	public Integer getMaxBidFrequency() {
		return maxBidFrequency;
	}

	public void setMinDecreasingRange(BigDecimal minDecreasingRange) {
		this.minDecreasingRange = minDecreasingRange;
	}

	@Column(name = "min_decreasing_range", precision = 5, scale = 2)	
	public BigDecimal getMinDecreasingRange() {
		return minDecreasingRange;
	}

	public void setBidBond(BigDecimal bidBond) {
		this.bidBond = bidBond;
	}

	@Column(name = "bid_bond", precision = 15, scale = 2)	
	public BigDecimal getBidBond() {
		return bidBond;
	}

	public void setBidBondTerm(Date bidBondTerm) {
		this.bidBondTerm = bidBondTerm;
	}

	@Column(name = "bid_bond_term", nullable = true, length = 0)	
	public Date getBidBondTerm() {
		return bidBondTerm;
	}

	public void setBidBondAccountNumber(String bidBondAccountNumber) {
		this.bidBondAccountNumber = bidBondAccountNumber;
	}

	@Column(name = "bid_bond_account_number", nullable = true, length = 30)	
	public String getBidBondAccountNumber() {
		return bidBondAccountNumber;
	}

	public void setBidBondBankName(String bidBondBankName) {
		this.bidBondBankName = bidBondBankName;
	}

	@Column(name = "bid_bond_bank_name", nullable = true, length = 240)	
	public String getBidBondBankName() {
		return bidBondBankName;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	@Column(name = "tender_cost", precision = 15, scale = 2)	
	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setPublishApprovalStatus(String publishApprovalStatus) {
		this.publishApprovalStatus = publishApprovalStatus;
	}

	@Column(name = "publish_approval_status", nullable = true, length = 30)	
	public String getPublishApprovalStatus() {
		return publishApprovalStatus;
	}

	public void setPublishApprovalDate(Date publishApprovalDate) {
		this.publishApprovalDate = publishApprovalDate;
	}

	@Column(name = "publish_approval_date", nullable = true, length = 0)	
	public Date getPublishApprovalDate() {
		return publishApprovalDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "publish_date", nullable = true, length = 0)	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setCloseBiddingDate(Date closeBiddingDate) {
		this.closeBiddingDate = closeBiddingDate;
	}

	@Column(name = "close_bidding_date", nullable = true, length = 0)	
	public Date getCloseBiddingDate() {
		return closeBiddingDate;
	}

	public void setNoteToSupplier(String noteToSupplier) {
		this.noteToSupplier = noteToSupplier;
	}

	@Column(name = "note_to_supplier", nullable = true, length = 2000)	
	public String getNoteToSupplier() {
		return noteToSupplier;
	}

	public void setNoteToJury(String noteToJury) {
		this.noteToJury = noteToJury;
	}

	@Column(name = "note_to_jury", nullable = true, length = 2000)	
	public String getNoteToJury() {
		return noteToJury;
	}

	public void setToSupplierFileId(Integer toSupplierFileId) {
		this.toSupplierFileId = toSupplierFileId;
	}

	@Column(name = "to_supplier_file_id", nullable = true, length = 11)	
	public Integer getToSupplierFileId() {
		return toSupplierFileId;
	}

	public void setToJuryFileId(Integer toJuryFileId) {
		this.toJuryFileId = toJuryFileId;
	}

	@Column(name = "to_jury_file_id", nullable = true, length = 11)	
	public Integer getToJuryFileId() {
		return toJuryFileId;
	}

	public void setAllowScoreFlag(String allowScoreFlag) {
		this.allowScoreFlag = allowScoreFlag;
	}
	@Column(name = "allow_score_flag", nullable = true, length = 1)
	public String getAllowScoreFlag() {
		return allowScoreFlag;
	}
	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	@Column(name = "rounds", nullable = true, length = 11)	
	public Integer getRounds() {
		return rounds;
	}

	public void setFirstRound(Integer firstRound) {
		this.firstRound = firstRound;
	}

	@Column(name = "first_round", nullable = true, length = 11)	
	public Integer getFirstRound() {
		return firstRound;
	}

	public void setLastRound(Integer lastRound) {
		this.lastRound = lastRound;
	}

	@Column(name = "last_round", nullable = true, length = 11)	
	public Integer getLastRound() {
		return lastRound;
	}

	public void setAuctionRoundNumber(String auctionRoundNumber) {
		this.auctionRoundNumber = auctionRoundNumber;
	}

	@Column(name = "auction_round_number", nullable = true, length = 30)	
	public String getAuctionRoundNumber() {
		return auctionRoundNumber;
	}

	public void setOpenBiddingFlag(String openBiddingFlag) {
		this.openBiddingFlag = openBiddingFlag;
	}

	@Column(name = "open_bidding_flag", nullable = true, length = 1)	
	public String getOpenBiddingFlag() {
		return openBiddingFlag;
	}

	public void setOpenBiddingDate(Date openBiddingDate) {
		this.openBiddingDate = openBiddingDate;
	}

	@Column(name = "open_bidding_date", nullable = true, length = 0)	
	public Date getOpenBiddingDate() {
		return openBiddingDate;
	}

	public void setJudgeCompleteDate(Date judgeCompleteDate) {
		this.judgeCompleteDate = judgeCompleteDate;
	}

	@Column(name = "judge_complete_date", nullable = true, length = 0)	
	public Date getJudgeCompleteDate() {
		return judgeCompleteDate;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	@Column(name = "award_status", nullable = true, length = 30)	
	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardApprovalStatus(String awardApprovalStatus) {
		this.awardApprovalStatus = awardApprovalStatus;
	}

	@Column(name = "award_approval_status", nullable = true, length = 30)	
	public String getAwardApprovalStatus() {
		return awardApprovalStatus;
	}

	public void setAwardCompleteDate(Date awardCompleteDate) {
		this.awardCompleteDate = awardCompleteDate;
	}

	@Column(name = "award_complete_date", nullable = true, length = 0)	
	public Date getAwardCompleteDate() {
		return awardCompleteDate;
	}

	public void setAwardComments(String awardComments) {
		this.awardComments = awardComments;
	}

	@Column(name = "award_comments", nullable = true, length = 240)	
	public String getAwardComments() {
		return awardComments;
	}

	public void setPoCreateFlag(String poCreateFlag) {
		this.poCreateFlag = poCreateFlag;
	}

	@Column(name = "po_create_flag", nullable = true, length = 1)	
	public String getPoCreateFlag() {
		return poCreateFlag;
	}

	public void setPoCreateDate(Date poCreateDate) {
		this.poCreateDate = poCreateDate;
	}

	@Column(name = "po_create_date", nullable = true, length = 0)	
	public Date getPoCreateDate() {
		return poCreateDate;
	}

    @Column(name = "okc_create_flag", nullable = true, length = 1)
    public String getOkcCreateFlag() {
        return okcCreateFlag;
    }

    public void setOkcCreateFlag(String okcCreateFlag) {
        this.okcCreateFlag = okcCreateFlag;
    }

    @Column(name = "okc_create_date", nullable = true, length = 0)
    public Date getOkcCreateDate() {
        return okcCreateDate;
    }

    public void setOkcCreateDate(Date okcCreateDate) {
        this.okcCreateDate = okcCreateDate;
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

	@Column(name = "template_id", nullable = true, length = 240)
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Column(name = "template_name", nullable = true, length = 240)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "template_code", nullable = true, length = 240)
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}


	@Column(name = "item_type", nullable = true, length = 30)
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Column(name = "ekp_number", nullable = true, length = 240)
	public String getEkpNumber() {
		return ekpNumber;
	}

	public void setEkpNumber(String ekpNumber) {
		this.ekpNumber = ekpNumber;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
