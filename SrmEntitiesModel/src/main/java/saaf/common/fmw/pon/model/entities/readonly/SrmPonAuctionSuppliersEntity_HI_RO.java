package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPonAuctionSuppliersEntity_HI_RO Entity Object
 * Tue Apr 17 11:14:27 CST 2018  Auto Generate
 */

public class SrmPonAuctionSuppliersEntity_HI_RO {

	//招标的洽谈邀请供应商——选择供应商弹出框查询
	public static final String QUERY_SELECT_PONSUPPLIERSQL =
					"SELECT psi.supplier_id AS supplierId\n" +
					"      ,psi.supplier_name AS supplierName\n" +
					"      ,psi.supplier_number AS supplierNumber\n" +
					"      ,psi.supplier_status AS supplierStatus\n" +
					"      ,slv.meaning AS supplierStatusName\n" +
					"      ,spsc.supplier_contact_id AS supplierContactId\n" +
					"      ,spsc.contact_name AS supplierContactName\n" +
					"      ,spsc.mobile_phone AS supplierContactPhone\n" +
					"      ,spsc.email_address AS supplierContactEmail\n" +
                    "      ,spsc.creation_date AS supplierContactCreationDate\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_auction_suppliers spas\n" +
					"              ,srm_pon_auction_headers   spah\n" +
					"        WHERE  spas.supplier_id = psi.supplier_id\n" +
					"        AND    spas.auction_header_id = spah.auction_header_id\n" +
					"        AND    spah.auction_status NOT IN ('DRAFT')) AS numberOfInvitations\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_auction_suppliers spas2\n" +
					"              ,srm_pon_auction_headers   spah2\n" +
					"        WHERE  spas2.supplier_id = psi.supplier_id\n" +
					"        AND    spas2.auction_header_id = spah2.auction_header_id\n" +
					"        AND    spah2.auction_status NOT IN ('DRAFT')\n" +
					"        AND    spas2.awarded_status = '4') AS numberOfAwarded\n" +
					"FROM   srm_pos_supplier_info     psi\n" +
					"      ,srm_pos_supplier_contacts spsc\n" +
					"      ,saaf_lookup_values        slv\n" +
					"WHERE  psi.supplier_id = spsc.supplier_id\n" +
					"AND    psi.supplier_status = slv.lookup_code\n" +
					"AND    slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"AND    EXISTS (SELECT 1\n" +
					"        FROM   saaf_lookup_values slv2\n" +
					"        WHERE  slv2.lookup_code = psi.supplier_status\n" +
					"        AND    slv2.lookup_type = 'PON_SUPPLIER_STATUS'\n" +
					"        AND    slv2.enabled_flag = 'Y')\n";

	//竟标的洽谈邀请供应商——选择供应商弹出框查询
	public static final String QUERY_SELECT_PONSUPPLIERBIDDIN_SQL =
					"SELECT psi.supplier_id AS supplierId\n" +
					"      ,psi.supplier_name AS supplierName\n" +
					"      ,psi.supplier_number AS supplierNumber\n" +
					"      ,psi.supplier_status AS supplierStatus\n" +
					"      ,slv.meaning AS supplierStatusName\n" +
					"      ,spsc.supplier_contact_id AS supplierContactId\n" +
					"      ,spsc.contact_name AS supplierContactName\n" +
					"      ,spsc.mobile_phone AS supplierContactPhone\n" +
					"      ,spsc.email_address AS supplierContactEmail\n" +
                    "      ,spsc.creation_date AS supplierContactCreationDate\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_auction_suppliers spas\n" +
					"              ,srm_pon_auction_headers   spah\n" +
					"        WHERE  spas.supplier_id = psi.supplier_id\n" +
					"        AND    spas.auction_header_id = spah.auction_header_id\n" +
					"        AND    spah.auction_type = 'INQUIRY') AS numberOfInvitations\n" +
					"      ,(SELECT COUNT(1)\n" +
					"        FROM   srm_pon_auction_suppliers spas2\n" +
					"              ,srm_pon_auction_headers   spah2\n" +
					"        WHERE  spas2.supplier_id = psi.supplier_id\n" +
					"        AND    spas2.auction_header_id = spah2.auction_header_id\n" +
					"        AND    spah2.auction_type = 'INQUIRY'\n" +
					"        AND    spas2.awarded_status = '4') AS numberOfAwarded\n" +
					"FROM   srm_pos_supplier_info     psi\n" +
					"      ,srm_pos_supplier_contacts spsc\n" +
					"      ,saaf_lookup_values        slv\n" +
					"WHERE  psi.supplier_id = spsc.supplier_id\n" +
					"AND    psi.supplier_status = slv.lookup_code\n" +
					"AND    slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"AND    EXISTS (SELECT 1\n" +
					"        FROM   saaf_lookup_values slv2\n" +
					"        WHERE  slv2.lookup_code = psi.supplier_status\n" +
					"        AND    slv2.lookup_type = 'PON_SUPPLIER_STATUS'\n" +
					"        AND    slv2.enabled_flag = 'Y')\n";

	//查询供应商标签页
	public static final String QUERY_AUCTION_SUPPLIER_LIST_SQL =
			"SELECT\n" +
			"t.invite_id AS inviteId,\n" +
			"t.auction_header_id AS auctionHeaderId,\n" +
			"t.supplier_id AS supplierId,\n" +
			"t.supplier_name AS supplierName,\n" +
			"t.supplier_site_id AS supplierSiteId,\n" +
			"t.supplier_contact_id AS supplierContactId,\n" +
			"t.supplier_contact_name AS supplierContactName,\n" +
			"t.supplier_contact_phone AS supplierContactPhone,\n" +
			"t.supplier_contact_email AS supplierContactEmail,\n" +
			"t.last_round_amount AS lastRoundAmount,\n" +
			"t.number_of_invitations AS numberOfInvitations,\n" +
			"t.number_of_awarded AS numberOfAwarded,\n" +
			"t.quantity_score AS quantityScore,\n" +
			"t.performance_score AS performanceScore,\n" +
			"t.bid_bond_pay_status AS bidBondPayStatus,\n" +
			"t.bid_bond_pay_date AS bidBondPayDate,\n" +
			"t.bid_bond_return_date AS bidBondReturnDate,\n" +
			"t.bid_bond_fines AS bidBondFines,\n" +
			"t.tender_cost_pay_status AS tenderCostPayStatus,\n" +
			"t.first_view_date AS firstViewDate,\n" +
			"t.acknowledgement_flag AS acknowledgementFlag,\n" +
			"t.awarded_status AS awardedStatus,\n" +
			"t.version_num AS versionNum,\n" +
			"t.creation_date AS creationDate,\n" +
			"t.created_by AS createdBy,\n" +
			"t.last_updated_by AS lastUpdatedBy,\n" +
			"t.last_update_date AS lastUpdateDate,\n" +
			"t.last_update_login AS lastUpdateLogin,\n" +
			"t.attribute_category AS attributeCategory,\n" +
			"t.attribute1 AS attribute1,\n" +
			"t.attribute2 AS attribute2,\n" +
			"t.attribute3 AS attribute3,\n" +
			"t.attribute4 AS attribute4,\n" +
			"t.attribute5 AS attribute5,\n" +
			"t.attribute6 AS attribute6,\n" +
			"t.attribute7 AS attribute7,\n" +
			"t.attribute8 AS attribute8,\n" +
			"t.attribute9 AS attribute9,\n" +
			"t.attribute10 AS attribute10,\n" +
			"slv1.meaning AS bidBondPayStatusName,\n"+
			"slv3.meaning AS awardedStatusName,\n"+
			"slv2.meaning AS tenderCostPayStatusName,\n"+
            "spsi.supplier_ebs_number AS supplierEbsNumber\n" +
			"FROM\n" +
			"srm_pon_auction_suppliers t\n" +
			"LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type='PON_BOND_PAY_STATUS' AND t.bid_bond_pay_status = slv1.lookup_code\n" +
			"LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type='PON_AWARD_STATUS' AND t.awarded_status = slv3.lookup_code\n" +
            "LEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = t.supplier_id\n" +
			"LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type='PON_BOND_PAY_STATUS' AND t.tender_cost_pay_status = slv2.lookup_code\n"+
			"WHERE t.auction_header_id = ? \n";

	public static final String QUERY_AUCTION_SUPPLIERS_SQL =
					"SELECT (CASE\n" +
					"         WHEN (pah.bid_bond > 0 AND pas.bid_bond_pay_status IN (1, 3))\n" +
					"              OR nvl(pah.bid_bond, 0) = 0 THEN\n" +
					"          1\n" +
					"         ELSE\n" +
					"          0\n" +
					"       END) COUNT\n" +
					"FROM   srm_pon_auction_suppliers pas\n" +
					"      ,srm_pon_auction_headers   pah\n" +
					"WHERE  pas.auction_header_id = pah.auction_header_id\n" +
					"AND    pas.supplier_id = :supplierId\n" +
					"AND    pas.auction_header_id = :auctionHeaderId\n";

	public static final String QUERY_INVITATIONS_SQL =
					"SELECT COUNT(1) AS numberOfInvitations\n" +
					"FROM   srm_pon_auction_suppliers spas\n" +
					"      ,srm_pon_auction_headers   spah\n" +
					"WHERE  spas.supplier_id = :varSupplierId\n" +
					"AND    spas.auction_header_id = spah.auction_header_id\n" +
					"AND    spah.auction_type = 'INQUIRY'\n";

	public static final String QUERY_AWARDED_SQL =
					"SELECT COUNT(1) AS numberOfAwarded\n" +
					"FROM   srm_pon_auction_suppliers spas2\n" +
					"      ,srm_pon_auction_headers   spah2\n" +
					"WHERE  spas2.supplier_id = :varSupplierId\n" +
					"AND    spas2.auction_header_id = spah2.auction_header_id\n" +
					"AND    spah2.auction_type = 'INQUIRY'\n" +
					"AND    spas2.awarded_status = '4'\n";

	public static final String QUERY_AUCTION_SUPPLIERS_BOND_LIST_SQL =
			        "SELECT Spah.Auction_Number AS auctionNumber\n" +
					"      ,Spah.Auction_Title AS auctionTitle\n" +
					"      ,Si.Inst_Name AS instName\n" +
					"      ,Spah.Bid_Bond AS bidBond\n" +
					"      ,Psi.Supplier_Number AS supplierNumber\n" +
					"      ,Psi.Supplier_Name AS supplierName\n" +
					"      ,Spas.Bid_Bond_Fines AS bidBondFines\n" +
					"      ,Spas.Bid_Bond_Pay_Status AS bidBondPayStatus\n" +
					"      ,Spas.Bid_Bond_Pay_Date AS bidBondPayDate\n" +
					"      ,Spas.Bid_Bond_Return AS bidBondReturn\n" +
					"      ,Spas.Bid_Bond_Return_Date AS bidBondReturnDate\n" +
					"      ,Nvl(Spas.Return_Flag,'N') AS returnFlag\n" +
					"      ,(CASE WHEN\n" +
					"         Spas.Return_Flag = 'Y' THEN '是'\n" +
					"         ELSE '否'\n" +
					"        END) AS returnFlagDesc\n" +
					"      ,'true' AS disabledFlag\n" +
					"      ,Spas.Invite_Id inviteId  \n" +
					"  FROM Srm_Pon_Auction_Suppliers Spas\n" +
					"  LEFT JOIN Srm_Pos_Supplier_Info Psi\n" +
					"    ON Spas.Supplier_Id = Psi.Supplier_Id\n" +
					"      ,Srm_Pon_Auction_Headers Spah\n" +
					"      ,Saaf_Institution Si\n" +
					" WHERE Spas.Auction_Header_Id = Spah.Auction_Header_Id\n" +
					"   AND Spah.Auction_Type = 'TENDER'\n" +
					"   AND Spah.Org_Id = Si.Inst_Id\n" +
					"   AND Spas.Bid_Bond_Pay_Status = '1'";

	public static final String QUERY_AUCTION_ANALYSIS_LIST_SQL =
					"SELECT Pas.Supplier_Name AS supplierName\n" +
                            "      ,Si1.Inst_Name AS instName\n" +
                            "      ,Pas.Supplier_Id AS supplierId\n" +
                            "      ,Pah.Org_Id AS orgId\n" +
                            "      ,(SELECT COUNT(1)\n" +
                            "          FROM Srm_Pon_Bid_Headers     Pbh\n" +
                            "              ,Srm_Pon_Auction_Headers Pah2\n" +
                            "         WHERE Pbh.Auction_Header_Id = Pah2.Auction_Header_Id\n" +
                            "           AND Pah2.Auction_Status = 'COMPLETED'\n" +
                            "           AND Pah2.Item_Type = 'ENGINEERING'\n" +
                            "           AND Pbh.Award_Status = 4\n" +
                            "           AND Pah2.Org_Id = Pah.Org_Id\n" +
                            "           AND Pbh.Supplier_Id = Pas.Supplier_Id) AS awardCount\n" +
                            "      ,(SELECT SUM(Pbh.Total_Project_Cost)\n" +
                            "          FROM Srm_Pon_Bid_Headers     Pbh\n" +
                            "              ,Srm_Pon_Auction_Headers Pah2\n" +
                            "         WHERE Pbh.Auction_Header_Id = Pah2.Auction_Header_Id\n" +
                            "           AND Pah2.Auction_Status = 'COMPLETED'\n" +
                            "           AND Pah2.Item_Type = 'ENGINEERING'\n" +
                            "           AND Pbh.Award_Status = 4\n" +
                            "           AND Pah2.Org_Id = Pah.Org_Id\n" +
                            "           AND Pbh.Supplier_Id = Pas.Supplier_Id) AS awardAmount\n" +
                            "  FROM Srm_Pon_Auction_Headers   Pah\n" +
                            "      ,Srm_Pon_Auction_Suppliers Pas\n" +
                            "      ,Saaf_Institution          Si1\n" +
                            " WHERE Pah.Auction_Status = 'COMPLETED'\n" +
                            "   AND Pah.Item_Type = 'ENGINEERING'\n" +
                            "   AND Pah.Auction_Header_Id = Pas.Auction_Header_Id\n" +
                            "   AND Pas.Awarded_Status = 4\n" +
                            "   AND Pah.Org_Id = Si1.Inst_Id\n" +
                            "   AND Si1.Inst_Type = 'ORG'\n";

	public static final String QUERY_AUCTION_ANALYSIS_LIST_SQL2 =
					"SELECT COUNT(1)\n" +
					"  FROM Srm_Pon_Bid_Headers     Pbh\n" +
					"      ,Srm_Pon_Auction_Headers Pah2\n" +
					" WHERE Pbh.Auction_Header_Id = Pah2.Auction_Header_Id\n" +
					"   AND Pah2.Auction_Type = 'TENDER'\n" +
					"   AND Pah2.Auction_Status = 'COMPLETED'\n" +
					"   AND Pbh.Award_Status = 4\n" +
					"   AND Pah2.Org_Id = Pah.Org_Id\n" +
					"   AND Pbh.Supplier_Id = Pas.Supplier_Id\n";

	public static final String QUERY_SUPPLIER_LIST_SQL = "select spas.invite_id,\n" +
			"       spas.auction_header_id,\n" +
			"       spas.supplier_id,\n" +
			"       spas.supplier_name\n" +
			"  from srm_pon_auction_suppliers spas\n" +
			" where 1 = 1\n";

	private Integer inviteId; //供应商邀请ID
	private Integer auctionHeaderId; //洽谈ID
	private Integer count;
	private Integer supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
	private String supplierName; //供应商名称
	private Integer supplierSiteId; //供应商地点ID
	private Integer supplierContactId; //供应商联系人ID，关联表：SRM_POS_SUPPLIER_CONTACTS
	private String supplierContactName; //供应商联系人姓名
	private String supplierContactPhone; //供应商联系人电话
	private String supplierContactEmail; //供应商联系人邮箱
	private BigDecimal lastRoundAmount; //上一轮报价总金额
	private Integer numberOfInvitations; //被邀请次数
	private Integer numberOfAwarded; //中标次数
	private BigDecimal quantityScore; //质量得分
	private BigDecimal performanceScore; //绩效得分
	private String bidBondPayStatus; //保证金缴纳状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date bidBondPayDate; //保证金缴纳时间
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date bidBondReturnDate; //保证金退还时间
	private BigDecimal bidBondFines; //保证金扣罚金额
	private String tenderCostPayStatus; //标书费用缴纳状态
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date firstViewDate; //第一次查看标书时间
	private String acknowledgementFlag; //确认参与（Y/N）
	private String awardedStatus; //中标状态
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
	private String supplierEbsNumber;



	//别名
	private String supplierStatusName;//供应商的状态别名（pos模块）
	private String supplierStatus;//供应商的状态（pos模块）
	private String supplierNumber;//供应商编码
	private String bidBondPayStatusName;//保证金缴纳状态别名
	private String tenderCostPayStatusName;//标书费用缴纳状态别名
	private String awardedStatusName;//中标状态别名

	private String auctionNumber;
	private String auctionTitle;
	private String instName;
	private Integer awardCount;
	private BigDecimal awardAmount;
	private BigDecimal bidBond;
    private BigDecimal bidBondReturn;
    private String returnFlag;
	private String returnFlagDesc;
	private String disabledFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date supplierContactCreationDate;

    public Date getSupplierContactCreationDate() {
        return supplierContactCreationDate;
    }

    public void setSupplierContactCreationDate(Date supplierContactCreationDate) {
        this.supplierContactCreationDate = supplierContactCreationDate;
    }


    public String getSupplierEbsNumber() {
        return supplierEbsNumber;
    }

    public void setSupplierEbsNumber(String supplierEbsNumber) {
        this.supplierEbsNumber = supplierEbsNumber;
    }

    public String getAwardedStatusName() {
		return awardedStatusName;
	}

	public void setAwardedStatusName(String awardedStatusName) {
		this.awardedStatusName = awardedStatusName;
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

	public String getSupplierStatusName() {
		return supplierStatusName;
	}

	public void setSupplierStatusName(String supplierStatusName) {
		this.supplierStatusName = supplierStatusName;
	}

	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
	}


	public Integer getInviteId() {
		return inviteId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}


	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public void setLastRoundAmount(BigDecimal lastRoundAmount) {
		this.lastRoundAmount = lastRoundAmount;
	}


	public BigDecimal getLastRoundAmount() {
		return lastRoundAmount;
	}

	public void setNumberOfInvitations(Integer numberOfInvitations) {
		this.numberOfInvitations = numberOfInvitations;
	}


	public Integer getNumberOfInvitations() {
		return numberOfInvitations;
	}

	public void setNumberOfAwarded(Integer numberOfAwarded) {
		this.numberOfAwarded = numberOfAwarded;
	}


	public Integer getNumberOfAwarded() {
		return numberOfAwarded;
	}

	public void setQuantityScore(BigDecimal quantityScore) {
		this.quantityScore = quantityScore;
	}


	public BigDecimal getQuantityScore() {
		return quantityScore;
	}

	public void setPerformanceScore(BigDecimal performanceScore) {
		this.performanceScore = performanceScore;
	}


	public BigDecimal getPerformanceScore() {
		return performanceScore;
	}

	public void setBidBondPayStatus(String bidBondPayStatus) {
		this.bidBondPayStatus = bidBondPayStatus;
	}


	public String getBidBondPayStatus() {
		return bidBondPayStatus;
	}

	public void setBidBondPayDate(Date bidBondPayDate) {
		this.bidBondPayDate = bidBondPayDate;
	}


	public Date getBidBondPayDate() {
		return bidBondPayDate;
	}

	public void setBidBondReturnDate(Date bidBondReturnDate) {
		this.bidBondReturnDate = bidBondReturnDate;
	}


	public Date getBidBondReturnDate() {
		return bidBondReturnDate;
	}

	public void setBidBondFines(BigDecimal bidBondFines) {
		this.bidBondFines = bidBondFines;
	}


	public BigDecimal getBidBondFines() {
		return bidBondFines;
	}

	public void setTenderCostPayStatus(String tenderCostPayStatus) {
		this.tenderCostPayStatus = tenderCostPayStatus;
	}


	public String getTenderCostPayStatus() {
		return tenderCostPayStatus;
	}

	public void setFirstViewDate(Date firstViewDate) {
		this.firstViewDate = firstViewDate;
	}


	public Date getFirstViewDate() {
		return firstViewDate;
	}

	public void setAcknowledgementFlag(String acknowledgementFlag) {
		this.acknowledgementFlag = acknowledgementFlag;
	}


	public String getAcknowledgementFlag() {
		return acknowledgementFlag;
	}

	public void setAwardedStatus(String awardedStatus) {
		this.awardedStatus = awardedStatus;
	}


	public String getAwardedStatus() {
		return awardedStatus;
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

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	public String getAuctionTitle() {
		return auctionTitle;
	}

	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public Integer getAwardCount() {
		return awardCount;
	}

	public void setAwardCount(Integer awardCount) {
		this.awardCount = awardCount;
	}

	public BigDecimal getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	public BigDecimal getBidBond() {
		return bidBond;
	}

	public void setBidBond(BigDecimal bidBond) {
		this.bidBond = bidBond;
	}

    public BigDecimal getBidBondReturn() {
        return bidBondReturn;
    }

    public void setBidBondReturn(BigDecimal bidBondReturn) {
        this.bidBondReturn = bidBondReturn;
    }

    public String getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

	public String getReturnFlagDesc() {
		return returnFlagDesc;
	}

	public void setReturnFlagDesc(String returnFlagDesc) {
		this.returnFlagDesc = returnFlagDesc;
	}

	public String getDisabledFlag() {
		return disabledFlag;
	}

	public void setDisabledFlag(String disabledFlag) {
		this.disabledFlag = disabledFlag;
	}
}
