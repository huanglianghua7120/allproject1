package saaf.common.fmw.pon.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPonAuctionSuppliersEntity_HI Entity Object
 * Tue Apr 17 11:14:27 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_auction_suppliers")
public class SrmPonAuctionSuppliersEntity_HI {
    private Integer inviteId; //供应商邀请ID
    private String invitingBidWay; //邀标方式 
    private Integer auctionHeaderId; //洽谈ID
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
    private String returnFlag; //保证金是否已退还
	private BigDecimal bidBondReturn; //保证金退还金额
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

	public void setInviteId(Integer inviteId) {
		this.inviteId = inviteId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PON_AUCTION_SUPPLIERS_S", sequenceName = "SRM_PON_AUCTION_SUPPLIERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PON_AUCTION_SUPPLIERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "invite_id", nullable = false, length = 11)	
	public Integer getInviteId() {
		return inviteId;
	}

	public void setAuctionHeaderId(Integer auctionHeaderId) {
		this.auctionHeaderId = auctionHeaderId;
	}
	
	@Column(name = "inviting_bid_way", nullable = false, length = 30)
	public String getInvitingBidWay() {
		return invitingBidWay;
	}

	public void setInvitingBidWay(String invitingBidWay) {
		this.invitingBidWay = invitingBidWay;
	}

	@Column(name = "auction_header_id", nullable = false, length = 11)	
	public Integer getAuctionHeaderId() {
		return auctionHeaderId;
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

	public void setLastRoundAmount(BigDecimal lastRoundAmount) {
		this.lastRoundAmount = lastRoundAmount;
	}

	@Column(name = "last_round_amount", precision = 18, scale = 6)	
	public BigDecimal getLastRoundAmount() {
		return lastRoundAmount;
	}

	public void setNumberOfInvitations(Integer numberOfInvitations) {
		this.numberOfInvitations = numberOfInvitations;
	}

	@Column(name = "number_of_invitations", nullable = true, length = 11)	
	public Integer getNumberOfInvitations() {
		return numberOfInvitations;
	}

	public void setNumberOfAwarded(Integer numberOfAwarded) {
		this.numberOfAwarded = numberOfAwarded;
	}

	@Column(name = "number_of_awarded", nullable = true, length = 11)	
	public Integer getNumberOfAwarded() {
		return numberOfAwarded;
	}

	public void setQuantityScore(BigDecimal quantityScore) {
		this.quantityScore = quantityScore;
	}

	@Column(name = "quantity_score", precision = 5, scale = 2)	
	public BigDecimal getQuantityScore() {
		return quantityScore;
	}

	public void setPerformanceScore(BigDecimal performanceScore) {
		this.performanceScore = performanceScore;
	}

	@Column(name = "performance_score", precision = 5, scale = 2)	
	public BigDecimal getPerformanceScore() {
		return performanceScore;
	}

	public void setBidBondPayStatus(String bidBondPayStatus) {
		this.bidBondPayStatus = bidBondPayStatus;
	}

	@Column(name = "bid_bond_pay_status", nullable = true, length = 30)	
	public String getBidBondPayStatus() {
		return bidBondPayStatus;
	}

	public void setBidBondPayDate(Date bidBondPayDate) {
		this.bidBondPayDate = bidBondPayDate;
	}

	@Column(name = "bid_bond_pay_date", nullable = true, length = 0)	
	public Date getBidBondPayDate() {
		return bidBondPayDate;
	}

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    @Column(name = "return_flag", nullable = true, length = 1)
    public String getReturnFlag() {
        return returnFlag;
    }

	public void setBidBondReturnDate(Date bidBondReturnDate) {
		this.bidBondReturnDate = bidBondReturnDate;
	}

	@Column(name = "bid_bond_return_date", nullable = true, length = 0)	
	public Date getBidBondReturnDate() {
		return bidBondReturnDate;
	}

	public void setBidBondReturn(BigDecimal bidBondReturn) {
		this.bidBondReturn = bidBondReturn;
	}

	@Column(name = "bid_bond_return", precision = 15, scale = 2)
	public BigDecimal getBidBondReturn() {
		return bidBondReturn;
	}

	public void setBidBondFines(BigDecimal bidBondFines) {
		this.bidBondFines = bidBondFines;
	}

	@Column(name = "bid_bond_fines", precision = 15, scale = 2)	
	public BigDecimal getBidBondFines() {
		return bidBondFines;
	}

	public void setTenderCostPayStatus(String tenderCostPayStatus) {
		this.tenderCostPayStatus = tenderCostPayStatus;
	}

	@Column(name = "tender_cost_pay_status", nullable = true, length = 30)	
	public String getTenderCostPayStatus() {
		return tenderCostPayStatus;
	}

	public void setFirstViewDate(Date firstViewDate) {
		this.firstViewDate = firstViewDate;
	}

	@Column(name = "first_view_date", nullable = true, length = 0)	
	public Date getFirstViewDate() {
		return firstViewDate;
	}

	public void setAcknowledgementFlag(String acknowledgementFlag) {
		this.acknowledgementFlag = acknowledgementFlag;
	}

	@Column(name = "acknowledgement_flag", nullable = true, length = 1)	
	public String getAcknowledgementFlag() {
		return acknowledgementFlag;
	}

	public void setAwardedStatus(String awardedStatus) {
		this.awardedStatus = awardedStatus;
	}

	@Column(name = "awarded_status", nullable = true, length = 30)	
	public String getAwardedStatus() {
		return awardedStatus;
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
}
