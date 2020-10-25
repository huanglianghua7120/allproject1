package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPonBidHeadersEntity_HI Entity Object
 * Tue Apr 17 11:14:32 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_bid_headers")
public class SrmPonBidHeadersEntity_HI {
    private Integer bidHeaderId; //供应商报价ID
    private Integer auctionHeaderId; //洽谈ID
    private String bidNumber; //报价编号
    private String bidStatus; //报价状态
    private Integer supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private String supplierName; //供应商名称
    private Integer supplierSiteId; //供应商地点ID
    private Integer supplierContactId; //供应商联系人ID，关联表：SRM_POS_SUPPLIER_CONTACTS
    private String supplierContactName; //供应商联系人姓名
    private String supplierContactPhone; //供应商联系人电话
    private String supplierContactEmail; //供应商联系人邮箱
	private String paymentCondition; //付款条件
    private String awardStatus; //中标状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date awardDate; //中标时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate; //发布时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cancelledDate; //取消时间
    private String currencyCode; //币种
    private String noteToAuctionOwner; //通知洽谈负责人(供应商备注)
    private Integer toOwnerFileId; //供应商附件ID
    private Integer originalBidHeaderId; //上一次有效报价ID
    private Integer poHeaderId; //采购订单ID
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
    private String bargainFlag;//'1':无需议价；'2':待议价；'3':议价数据待提交；'4':议价数据已提交
	private String bargainReason;//议价原因
    //别名
	private BigDecimal scoreSum;//综合得分
	private Integer scoreSumRanking;//综合得分排名
    private String taxRateCode;
    private BigDecimal tranManageFees;//运杂及管理费
    private BigDecimal measuresFees;//规费及措施费
    private BigDecimal tranManagePercen;//运杂及管理费比率
    private BigDecimal measuresPercen;//运杂及管理费比率
    private BigDecimal projectCosts;
    private BigDecimal engineeringTax;
    private BigDecimal totalProjectCost;
    private String poCreateFlag; //是否已创建采购订单（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date poCreateDate; //采购订单创建时间
    private String okcCreateFlag; //是否已创建合同（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date okcCreateDate; //合同创建时间


	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_BID_HEADERS_S", sequenceName = "SRM_PON_BID_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_BID_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "bid_header_id", nullable = false, length = 11)	
	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}

    @Column(name = "tax_rate_code", nullable = true, length = 240)
    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }


    @Column(name = "auction_header_id", nullable = false, length = 11)
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}

	@Column(name = "bid_number", nullable = false, length = 30)	
	public String getBidNumber() {
		return bidNumber;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	@Column(name = "bid_status", nullable = true, length = 30)	
	public String getBidStatus() {
		return bidStatus;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "supplier_name", nullable = true, length = 240)	
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	@Column(name = "supplier_site_id", nullable = true, length = 11)	
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierContactId(Integer supplierContactId) {
		this.supplierContactId = supplierContactId;
	}

	@Column(name = "supplier_contact_id", nullable = true, length = 11)	
	public Integer getSupplierContactId() {
		return supplierContactId;
	}

	public void setSupplierContactName(String supplierContactName) {
		this.supplierContactName = supplierContactName;
	}

	@Column(name = "supplier_contact_name", nullable = true, length = 240)	
	public String getSupplierContactName() {
		return supplierContactName;
	}

	public void setSupplierContactPhone(String supplierContactPhone) {
		this.supplierContactPhone = supplierContactPhone;
	}

	@Column(name = "supplier_contact_phone", nullable = true, length = 240)	
	public String getSupplierContactPhone() {
		return supplierContactPhone;
	}

	public void setSupplierContactEmail(String supplierContactEmail) {
		this.supplierContactEmail = supplierContactEmail;
	}

	@Column(name = "supplier_contact_email", nullable = true, length = 240)	
	public String getSupplierContactEmail() {
		return supplierContactEmail;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	@Column(name = "payment_condition", nullable = true, length = 30)
	public String getPaymentCondition() {
		return paymentCondition;
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

	@Column(name = "award_date", nullable = true, length = 0)	
	public Date getAwardDate() {
		return awardDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "publish_date", nullable = true, length = 0)	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	@Column(name = "cancelled_date", nullable = true, length = 0)	
	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setNoteToAuctionOwner(String noteToAuctionOwner) {
		this.noteToAuctionOwner = noteToAuctionOwner;
	}

	@Column(name = "note_to_auction_owner", nullable = true, length = 0)	
	public String getNoteToAuctionOwner() {
		return noteToAuctionOwner;
	}

	public void setToOwnerFileId(Integer toOwnerFileId) {
		this.toOwnerFileId = toOwnerFileId;
	}

	@Column(name = "to_owner_file_id", nullable = true, length = 11)	
	public Integer getToOwnerFileId() {
		return toOwnerFileId;
	}

	public void setOriginalBidHeaderId(Integer originalBidHeaderId) {
		this.originalBidHeaderId = originalBidHeaderId;
	}

	@Column(name = "original_bid_header_id", nullable = true, length = 11)	
	public Integer getOriginalBidHeaderId() {
		return originalBidHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = true, length = 11)	
	public Integer getPoHeaderId() {
		return poHeaderId;
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

	public void setScoreSum(BigDecimal scoreSum) {
		this.scoreSum = scoreSum;
	}
	@Transient
	public BigDecimal getScoreSum() {
		return scoreSum;
	}
	@Transient
	public Integer getScoreSumRanking() {
		return scoreSumRanking;
	}

	public void setScoreSumRanking(Integer scoreSumRanking) {
		this.scoreSumRanking = scoreSumRanking;
	}

	@Column(name = "bargain_flag", nullable = true, length = 11)
	public String getBargainFlag() {
		return bargainFlag;
	}

	public void setBargainFlag(String bargainFlag) {
		this.bargainFlag = bargainFlag;
	}

    @Column(name = "tran_manage_fees", precision = 15, scale = 6)
    public BigDecimal getTranManageFees() {
        return tranManageFees;
    }
    public void setTranManageFees(BigDecimal tranManageFees) {
        this.tranManageFees = tranManageFees;
    }
    @Column(name = "measures_fees", precision = 15, scale = 6)
    public BigDecimal getMeasuresFees() {
        return measuresFees;
    }

    public void setMeasuresFees(BigDecimal measuresFees) {
        this.measuresFees = measuresFees;
    }

    @Column(name = "tran_manage_percen", precision = 15, scale = 6)
    public BigDecimal getTranManagePercen() {
        return tranManagePercen;
    }

    public void setTranManagePercen(BigDecimal tranManagePercen) {
        this.tranManagePercen = tranManagePercen;
    }

    @Column(name = "measures_percen", precision = 15, scale = 6)
    public BigDecimal getMeasuresPercen() {
        return measuresPercen;
    }

    public void setMeasuresPercen(BigDecimal measuresPercen) {
        this.measuresPercen = measuresPercen;
    }

    @Column(name = "bargain_reason", nullable = true, length = 240)
	public String getBargainReason() {
		return bargainReason;
	}

	public void setBargainReason(String bargainReason) {
		this.bargainReason = bargainReason;
	}

    @Column(name = "project_costs", precision = 15, scale = 6)
    public BigDecimal getProjectCosts() {
        return projectCosts;
    }

    public void setProjectCosts(BigDecimal projectCosts) {
        this.projectCosts = projectCosts;
    }
    @Column(name = "engineering_tax", precision = 15, scale = 6)
    public BigDecimal getEngineeringTax() {
        return engineeringTax;
    }

    public void setEngineeringTax(BigDecimal engineeringTax) {
        this.engineeringTax = engineeringTax;
    }
    @Column(name = "total_project_cost", precision = 15, scale = 6)
    public BigDecimal getTotalProjectCost() {
        return totalProjectCost;
    }

    public void setTotalProjectCost(BigDecimal totalProjectCost) {
        this.totalProjectCost = totalProjectCost;
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
}
