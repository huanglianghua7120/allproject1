package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonBidHeadersEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:32 CST 2018  Auto Generate
 */

public class SrmPonBidHeadersEntity_HI_RO {

	public static final String QUERY_BID_SUPPLIER_LIST_SQL ="SELECT\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.bid_number AS bidNumber,\n"
			+ "t.bid_status AS bidStatus,\n"
			+ "t.supplier_id AS supplierId,\n"
			+ "t.supplier_name AS supplierName,\n"
			+ "t.supplier_site_id AS supplierSiteId,\n"
			+ "t.supplier_contact_id AS supplierContactId,\n"
			+ "t.supplier_contact_name AS supplierContactName,\n"
			+ "t.supplier_contact_phone AS supplierContactPhone,\n"
			+ "t.supplier_contact_email AS supplierContactEmail,\n"
			+ "t.payment_condition AS paymentCondition,\n"
			+ "t.award_status AS awardStatus,\n"
			+ "t.award_date AS awardDate,\n"
			+ "t.publish_date AS publishDate,\n"
			+ "t.cancelled_date AS cancelledDate,\n"
			+ "t.currency_code AS currencyCode,\n"
			+ "t.note_to_auction_owner AS noteToAuctionOwner,\n"
			+ "t.to_owner_file_id AS toOwnerFileId,\n"
			+ "t.original_bid_header_id AS originalBidHeaderId,\n"
			+ "t.po_header_id AS poHeaderId,\n"
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
			+ "sppt.payment_term_name AS paymentTermName,\n"
			+ "slv.meaning AS bidStatusName\n"
			+ "FROM srm_pon_bid_headers t\n"
			+ "LEFT JOIN srm_pon_payment_terms sppt ON sppt.payment_term_code = t.payment_condition\n"
			+ "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type ='PON_OFFER_STATUS' AND slv.lookup_code = t.bid_status\n"
			+ "WHERE 1=1 and t.auction_header_id = ? ";

	public static final String QUERY_BIDHEADERS = "SELECT \n" +
			"a.bid_header_id,\n" +
			"a.auction_header_id,\n" +
			"a.supplier_id,\n"+
			"a.bid_number,\n" +
			"a.supplier_contact_name,\n" +
			"slv.meaning as bid_status,\n" +
			"a.creation_date,\n" +
			"a.publish_date\n" +
			"FROM srm_pon_bid_headers a LEFT JOIN saaf_lookup_values slv ON slv.lookup_type='PON_OFFER_STATUS' and a.bid_status = slv.lookup_code\n"+
			"WHERE 1=1";
	public static final String QUERY_BIDHEADERSLIST_SQL ="SELECT\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.bid_number AS bidNumber,\n"
			+ "t.bid_status AS bidStatus,\n"
			+ "t.supplier_id AS supplierId,\n"
			+ "t.supplier_name AS supplierName,\n"
			+ "t.supplier_site_id AS supplierSiteId,\n"
			+ "t.supplier_contact_id AS supplierContactId,\n"
			+ "t.supplier_contact_name AS supplierContactName,\n"
			+ "t.supplier_contact_phone AS supplierContactPhone,\n"
			+ "t.supplier_contact_email AS supplierContactEmail,\n"
			+ "t.payment_condition AS paymentCondition,\n"
			+ "t.award_status AS awardStatus,\n"
			+ "t.award_date AS awardDate,\n"
			+ "t.publish_date AS publishDate,\n"
			+ "t.cancelled_date AS cancelledDate,\n"
			+ "t.currency_code AS currencyCode,\n"
			+ "t.note_to_auction_owner AS noteToAuctionOwner,\n"
			+ "t.to_owner_file_id AS toOwnerFileId,\n"
			+ "t.original_bid_header_id AS originalBidHeaderId,\n"
			+ "t.po_header_id AS poHeaderId,\n"
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
			+ "sppt.payment_term_name AS paymentTermName,\n"
			+ "slv.meaning AS bidStatusName,\n"
            + "spah.item_type AS itemType,\n"
            + "t.tran_manage_fees AS tranManageFees,\n"
            + "t.tran_manage_percen AS tranManagePercen,\n"
            + "t.measures_fees AS measuresFees,\n"
            + "t.measures_percen AS measuresPercen,\n"
            + "t.project_costs AS projectCosts,\n"
            + "t.engineering_tax AS engineeringTax,\n"
            + "t.total_project_cost AS totalProjectCost\n"
			+ "FROM srm_pon_bid_headers t\n"
			+ "LEFT JOIN srm_pon_payment_terms sppt ON sppt.payment_term_code = t.payment_condition\n"
			+ "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type ='PON_OFFER_STATUS' AND slv.lookup_code = t.bid_status\n"
            + "LEFT JOIN srm_pon_auction_headers spah ON spah.auction_header_id = t.auction_header_id\n"
			+ "WHERE 1=1 ";
	//查询供应商报价头表（不分页）——带有报价总价与报价总价排名（用于已截止）
	public static final String QUERY_BIDHEADERSVLIST_SQL ="SELECT\n"
			+ "t.bid_header_id AS bidHeaderId,\n"
			+ "t.auction_header_id AS auctionHeaderId,\n"
			+ "t.bid_number AS bidNumber,\n"
			+ "t.bid_status AS bidStatus,\n"
			+ "t.supplier_id AS supplierId,\n"
			+ "t.supplier_name AS supplierName,\n"
			+ "t.supplier_site_id AS supplierSiteId,\n"
			+ "t.supplier_contact_id AS supplierContactId,\n"
			+ "t.supplier_contact_name AS supplierContactName,\n"
			+ "t.supplier_contact_phone AS supplierContactPhone,\n"
			+ "t.supplier_contact_email AS supplierContactEmail,\n"
			+ "t.payment_condition AS paymentCondition,\n"
			+ "t.award_status AS awardStatus,\n"
			+ "t.award_date AS awardDate,\n"
			+ "t.publish_date AS publishDate,\n"
			+ "t.cancelled_date AS cancelledDate,\n"
			+ "t.currency_code AS currencyCode,\n"
			+ "t.note_to_auction_owner AS noteToAuctionOwner,\n"
			+ "t.to_owner_file_id AS toOwnerFileId,\n"
			+ "t.original_bid_header_id AS originalBidHeaderId,\n"
			+ "t.po_header_id AS poHeaderId,\n"
			+ "t.version_num AS versionNum,\n"
			+ "t.creation_date AS creationDate,\n"
			+ "t.created_by AS createdBy,\n"
			+ "t.last_updated_by AS lastUpdatedBy,\n"
			+ "t.last_update_date AS lastUpdateDate,\n"
			+ "t.last_update_login AS lastUpdateLogin,\n"
			+ "t.attribute_category AS attributeCategory,\n"
			+ "t.bargain_flag AS bargainFlag,\n"
			+ "t.bargain_reason AS bargainReason,\n"
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
			+ "sppt.payment_term_name AS paymentTermName,\n"
			+ "slv.meaning AS bidStatusName,\n"
			+ "spas.invite_id AS inviteId,\n"
			+ "spas.number_of_invitations AS numberOfInvitations,\n"
			+ "spas.number_of_awarded AS numberOfAwarded,\n"
			+ "spas.bid_bond_pay_status AS bidBondPayStatus,\n"
			+ "spas.tender_cost_pay_status AS tenderCostPayStatus,\n"
			+ "slv1.meaning AS bidBondPayStatusName,\n"
			+ "slv2.meaning AS tenderCostPayStatusName,\n"
			+ "slv3.meaning AS awardStatusName,\n"
            + "t.tran_manage_fees AS tranManageFees,\n"
            + "t.tran_manage_percen AS tranManagePercen,\n"
            + "t.measures_fees AS measuresFees,\n"
            + "t.measures_percen AS measuresPercen,\n"
            + "t.project_costs AS projectCosts,\n"
            + "t.engineering_tax AS engineeringTax,\n"
            + "t.total_project_cost AS totalProjectCost,\n"
            + "spsi.supplier_ebs_number AS supplierEbsNumber,\n"
            + "spsi.supplier_number AS supplierNumber,\n"
            + "spsi.supplier_status AS supplierStatus,\n"
            + "t.tax_rate_code AS taxRateCode,\n"
            + "slv4.meaning AS taxRateName,\n"
            + "cast(Decode(t.Award_Status,'4','是','否') as varchar2(10)) AS awardStatusToEkp,\n"
            + "rf.file_name AS toOwnerFileName,\n"
            + "spah.item_type AS itemType,\n"
            + "spah.ekp_number AS ekpNumber,\n"
            + "spah.auction_number AS auctionNumber,\n"
            + "spah.auction_title AS auctionTitle,\n"
            + "t.okc_Create_Flag AS okcCreateFlag,\n"
            + "t.okc_Create_Date AS okcCreateDate,\n"
            + "t.po_Create_Flag AS poCreateFlag,\n"
            + "t.po_Create_Date AS poCreateDate,\n"
            + "cast(CASE\n" +
            "         WHEN Spah.Auction_Header_Id IN\n" +
            "              (SELECT pah.Last_Round AS Auction_Header_Id\n" +
            "                 FROM Srm_Pon_Auction_Headers pah\n" +
            "                WHERE Spah.Auction_Number = pah.Auction_Number) THEN\n" +
            "          '失效'\n" +
            "         ELSE\n" +
            "          '有效'\n" +
            "       END as varchar2(10) ) AS roundStatus\n"
			+ "FROM srm_pon_bid_headers t\n"
			+ "LEFT JOIN srm_pon_payment_terms sppt ON sppt.payment_term_code = t.payment_condition\n"
			+ "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type ='PON_OFFER_STATUS' AND slv.lookup_code = t.bid_status\n"
			+ "LEFT JOIN srm_pon_auction_suppliers spas ON spas.auction_header_id = t.auction_header_id AND spas.supplier_id = t.supplier_id\n"
            + "LEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = t.supplier_id\n"
			+ "LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type='PON_BOND_PAY_STATUS' AND spas.bid_bond_pay_status = slv1.lookup_code\n"
			+ "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type='PON_BOND_PAY_STATUS' AND spas.tender_cost_pay_status = slv2.lookup_code\n"
			+"LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type='PON_AWARD_STATUS' AND t.award_status = slv3.lookup_code\n"
            +"LEFT JOIN saaf_lookup_values slv4 ON slv4.lookup_type='PON_TAX_LIST' AND slv4.lookup_code=t.tax_rate_code\n"
            +"LEFT JOIN saaf_base_result_file rf ON  rf.file_id = t.to_owner_file_id\n"
            + "LEFT JOIN srm_pon_auction_headers spah ON spah.auction_header_id = t.auction_header_id\n"
			+ "WHERE 1=1 ";

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
	//别名
	private String paymentTermName;//付款条件名称
	private String bidStatusName; //报价状态别名
	private BigDecimal totalAccountOffer;//报价总计金额
	private BigDecimal totalAccountBiddinng;//中标总计金额
	private String bidBondPayStatusName;//保证金缴纳状态别名
	private String tenderCostPayStatusName;//标书费用缴纳状态别名
	private Integer totalAccountOfferRanking;//报价总计金额排名
	private Integer numberOfInvitations; //供应商被邀请次数
	private Integer numberOfAwarded; //供应商中标次数
	private String bidBondPayStatus; //保证金缴纳状态
	private String tenderCostPayStatus; //标书费用缴纳状态
	private Integer inviteId; //供应商邀请ID
	private String awardStatusName; //中标状态别名
	private String bargainFlag;//'1':无需议价；'2':待议价；'3':议价数据待提交；'4':议价数据已提交
	private String bargainReason;//议价原因
    private BigDecimal percent;
    private BigDecimal tranManageFees;
    private BigDecimal tranManagePercen;
    private BigDecimal measuresFees;
    private BigDecimal measuresPercen;
    private BigDecimal projectCosts;
    private BigDecimal engineeringTax;
    private BigDecimal totalProjectCost;
    private Integer totalProjectCostRank;
    private String supplierEbsNumber;
    private String taxRateCode;
    private String taxRateName;
    private String awardStatusToEkp;
    private String toOwnerFileName;
    private String itemType;
    private String auctionTitle;
    private String auctionNumber;
    private String roundStatus;
    private String supplierNumber;
    private String supplierStatus;
    private String okcCreateFlag; //是否已创建合同（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date okcCreateDate; //合同创建时间
    private String poCreateFlag; //是否已创建采购订单（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date poCreateDate; //采购订单创建时间

    public String getOkcCreateFlag() {
        return okcCreateFlag;
    }

    public void setOkcCreateFlag(String okcCreateFlag) {
        this.okcCreateFlag = okcCreateFlag;
    }

    public Date getOkcCreateDate() {
        return okcCreateDate;
    }

    public void setOkcCreateDate(Date okcCreateDate) {
        this.okcCreateDate = okcCreateDate;
    }

    public String getPoCreateFlag() {
        return poCreateFlag;
    }

    public void setPoCreateFlag(String poCreateFlag) {
        this.poCreateFlag = poCreateFlag;
    }

    public Date getPoCreateDate() {
        return poCreateDate;
    }

    public void setPoCreateDate(Date poCreateDate) {
        this.poCreateDate = poCreateDate;
    }

    public String getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(String supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getRoundStatus() {
        return roundStatus;
    }

    public void setRoundStatus(String roundStatus) {
        this.roundStatus = roundStatus;
    }

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getAuctionNumber() {
        return auctionNumber;
    }

    public void setAuctionNumber(String auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getToOwnerFileName() {
        return toOwnerFileName;
    }

    public void setToOwnerFileName(String toOwnerFileName) {
        this.toOwnerFileName = toOwnerFileName;
    }

    public String getAwardStatusToEkp() {
        return awardStatusToEkp;
    }

    public void setAwardStatusToEkp(String awardStatusToEkp) {
        this.awardStatusToEkp = awardStatusToEkp;
    }

    public String getTaxRateCode() {
        return taxRateCode;
    }

    public void setTaxRateCode(String taxRateCode) {
        this.taxRateCode = taxRateCode;
    }

    public String getTaxRateName() {
        return taxRateName;
    }

    public void setTaxRateName(String taxRateName) {
        this.taxRateName = taxRateName;
    }

    public String getSupplierEbsNumber() {
        return supplierEbsNumber;
    }

    public void setSupplierEbsNumber(String supplierEbsNumber) {
        this.supplierEbsNumber = supplierEbsNumber;
    }

    public Integer getTotalProjectCostRank() {
        return totalProjectCostRank;
    }

    public void setTotalProjectCostRank(Integer totalProjectCostRank) {
        this.totalProjectCostRank = totalProjectCostRank;
    }

    public BigDecimal getProjectCosts() {
        return projectCosts;
    }

    public void setProjectCosts(BigDecimal projectCosts) {
        this.projectCosts = projectCosts;
    }

    public BigDecimal getEngineeringTax() {
        return engineeringTax;
    }

    public void setEngineeringTax(BigDecimal engineeringTax) {
        this.engineeringTax = engineeringTax;
    }

    public BigDecimal getTotalProjectCost() {
        return totalProjectCost;
    }

    public void setTotalProjectCost(BigDecimal totalProjectCost) {
        this.totalProjectCost = totalProjectCost;
    }



    public BigDecimal getTranManageFees() {
        return tranManageFees;
    }

    public void setTranManageFees(BigDecimal tranManageFees) {
        this.tranManageFees = tranManageFees;
    }

    public BigDecimal getTranManagePercen() {
        return tranManagePercen;
    }

    public void setTranManagePercen(BigDecimal tranManagePercen) {
        this.tranManagePercen = tranManagePercen;
    }

    public BigDecimal getMeasuresFees() {
        return measuresFees;
    }

    public void setMeasuresFees(BigDecimal measuresFees) {
        this.measuresFees = measuresFees;
    }

    public BigDecimal getMeasuresPercen() {
        return measuresPercen;
    }

    public void setMeasuresPercen(BigDecimal measuresPercen) {
        this.measuresPercen = measuresPercen;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String getAwardStatusName() {
		return awardStatusName;
	}

	public void setAwardStatusName(String awardStatusName) {
		this.awardStatusName = awardStatusName;
	}

	public Integer getInviteId() {
		return inviteId;
	}

	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
	}

	public String getBidBondPayStatusName() {
		return bidBondPayStatusName;
	}

	public void setBidBondPayStatusName(String bidBondPayStatusName) {
		this.bidBondPayStatusName = bidBondPayStatusName;
	}

	public String getTenderCostPayStatusName() {
		return tenderCostPayStatusName;
	}

	public void setTenderCostPayStatusName(String tenderCostPayStatusName) {
		this.tenderCostPayStatusName = tenderCostPayStatusName;
	}

	public Integer getTotalAccountOfferRanking() {
		return totalAccountOfferRanking;
	}

	public void setTotalAccountOfferRanking(Integer totalAccountOfferRanking) {
		this.totalAccountOfferRanking = totalAccountOfferRanking;
	}

	public Integer getNumberOfInvitations() {
		return numberOfInvitations;
	}

	public void setNumberOfInvitations(Integer numberOfInvitations) {
		this.numberOfInvitations = numberOfInvitations;
	}

	public Integer getNumberOfAwarded() {
		return numberOfAwarded;
	}

	public void setNumberOfAwarded(Integer numberOfAwarded) {
		this.numberOfAwarded = numberOfAwarded;
	}

	public String getBidBondPayStatus() {
		return bidBondPayStatus;
	}

	public void setBidBondPayStatus(String bidBondPayStatus) {
		this.bidBondPayStatus = bidBondPayStatus;
	}

	public String getTenderCostPayStatus() {
		return tenderCostPayStatus;
	}

	public void setTenderCostPayStatus(String tenderCostPayStatus) {
		this.tenderCostPayStatus = tenderCostPayStatus;
	}

	public BigDecimal getTotalAccountOffer() {
		return totalAccountOffer;
	}

	public void setTotalAccountOffer(BigDecimal totalAccountOffer) {
		this.totalAccountOffer = totalAccountOffer;
	}

	public BigDecimal getTotalAccountBiddinng() {
		return totalAccountBiddinng;
	}

	public void setTotalAccountBiddinng(BigDecimal totalAccountBiddinng) {
		this.totalAccountBiddinng = totalAccountBiddinng;
	}
	public String getBidStatusName() {
		return bidStatusName;
	}

	public void setBidStatusName(String bidStatusName) {
		this.bidStatusName = bidStatusName;
	}

	public String getPaymentTermName() {
		return paymentTermName;
	}

	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}

	public void setBidHeaderId(Integer bidHeaderId) {
		this.bidHeaderId = bidHeaderId;
	}


	public Integer getBidHeaderId() {
		return bidHeaderId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}


	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
	}

	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}


	public String getBidNumber() {
		return bidNumber;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}


	public String getBidStatus() {
		return bidStatus;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}


	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}


	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierContactId(Integer supplierContactId) {
		this.supplierContactId = supplierContactId;
	}


	public Integer getSupplierContactId() {
		return supplierContactId;
	}

	public void setSupplierContactName(String supplierContactName) {
		this.supplierContactName = supplierContactName;
	}


	public String getSupplierContactName() {
		return supplierContactName;
	}

	public void setSupplierContactPhone(String supplierContactPhone) {
		this.supplierContactPhone = supplierContactPhone;
	}


	public String getSupplierContactPhone() {
		return supplierContactPhone;
	}

	public void setSupplierContactEmail(String supplierContactEmail) {
		this.supplierContactEmail = supplierContactEmail;
	}


	public String getSupplierContactEmail() {
		return supplierContactEmail;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}


	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
	}


	public Date getAwardDate() {
		return awardDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


	public Date getPublishDate() {
		return publishDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}


	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setNoteToAuctionOwner(String noteToAuctionOwner) {
		this.noteToAuctionOwner = noteToAuctionOwner;
	}


	public String getNoteToAuctionOwner() {
		return noteToAuctionOwner;
	}

	public void setToOwnerFileId(Integer toOwnerFileId) {
		this.toOwnerFileId = toOwnerFileId;
	}


	public Integer getToOwnerFileId() {
		return toOwnerFileId;
	}

	public void setOriginalBidHeaderId(Integer originalBidHeaderId) {
		this.originalBidHeaderId = originalBidHeaderId;
	}


	public Integer getOriginalBidHeaderId() {
		return originalBidHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}


	public Integer getPoHeaderId() {
		return poHeaderId;
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

	public String getBargainFlag() {
		return bargainFlag;
	}

	public void setBargainFlag(String bargainFlag) {
		this.bargainFlag = bargainFlag;
	}

	public String getBargainReason() {
		return bargainReason;
	}

	public void setBargainReason(String bargainReason) {
		this.bargainReason = bargainReason;
	}
}
