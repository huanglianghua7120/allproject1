package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoHeadersEntity_HI Entity Object
 * Wed Jun 20 11:44:12 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_headers")
public class SrmPoHeadersEntity_HI {
    private Integer poHeaderId; //采购订单ID
    private String poNumber; //采购订单号
    private Integer orgId; //业务实体ID
    private Integer billToOrganizationId; //收单组织ID
    private String poDocType; //单据类型，ORDER：订单，AGREEMENT：协议
    private Integer supplierId; //供应商ID，关联表：srm_pos_supplier_info
    private Integer supplierSiteId; //供应商地点ID，关联表：srm_pos_supplier_sites
    private String currencyCode; //币种(BANK_CURRENCY)
    private String taxRateCode; //税率
    private Integer buyerId; //采购员ID，关联表：saaf_employees
    private String returnGoodsType; //回货类型
    private String paymentCondition; //付款条件
    private String settlementWay; //结算方式
    private BigDecimal poVersions; //订单版本
    private String status; //状态
    private Integer approvalUserId; //审批用户ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvedDate; //批准时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //有效开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //有效结束日期
    private String description; //说明
    private Integer poFileId; //附件ID
    private String isGlobal;//是否全局
    private String agreementClause; //协议条款
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
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
    private String auctionNumber;

    private Integer contractId;
    private String contractCode;

	private String prNumber;
	private String locationCode;
	private String poType;
    private Integer shipToOrganizationId;
	private Integer shipToLocationId;
	private Integer billToLocationId;
	private Integer organizationId;
	private String logiShipToLocationCode;
    private String logiBillToLocationCode;

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_HEADERS_S", sequenceName = "SRM_PO_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "po_header_id", nullable = false, length = 11)	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Column(name = "po_number", nullable = false, length = 30)	
	public String getPoNumber() {
		return poNumber;
	}



    @Column(name = "auction_number", nullable = true, length = 200)
    public String getAuctionNumber() {
        return auctionNumber;
    }

    public void setAuctionNumber(String auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    @Column(name = "organization_id", nullable = true, length = 11)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}

	@Column(name = "bill_to_organization_id", nullable = true, length = 11)	
	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setPoDocType(String poDocType) {
		this.poDocType = poDocType;
	}

	@Column(name = "po_doc_type", nullable = true, length = 30)	
	public String getPoDocType() {
		return poDocType;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	@Column(name = "supplier_site_id", nullable = true, length = 11)	
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	@Column(name = "tax_rate_code", nullable = true, length = 30)	
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "buyer_id", nullable = true, length = 11)	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setReturnGoodsType(String returnGoodsType) {
		this.returnGoodsType = returnGoodsType;
	}

	@Column(name = "return_goods_type", nullable = true, length = 30)	
	public String getReturnGoodsType() {
		return returnGoodsType;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	@Column(name = "payment_condition", nullable = true, length = 30)	
	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}

	@Column(name = "settlement_way", nullable = true, length = 30)	
	public String getSettlementWay() {
		return settlementWay;
	}

	public void setPoVersions(BigDecimal poVersions) {
		this.poVersions = poVersions;
	}

	@Column(name = "po_versions", precision = 4, scale = 1)	
	public BigDecimal getPoVersions() {
		return poVersions;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 30)	
	public String getStatus() {
		return status;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	@Column(name = "approval_user_id", nullable = true, length = 11)	
	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Column(name = "approved_date", nullable = true, length = 0)	
	public Date getApprovedDate() {
		return approvedDate;
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

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 500)	
	public String getDescription() {
		return description;
	}

	public void setPoFileId(Integer poFileId) {
		this.poFileId = poFileId;
	}

	@Column(name = "po_file_id", nullable = true, length = 11)	
	public Integer getPoFileId() {
		return poFileId;
	}
	
	@Column(name = "is_global", nullable = true, length = 10)
	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public void setAgreementClause(String agreementClause) {
		this.agreementClause = agreementClause;
	}

	@Column(name = "agreement_clause", nullable = true, length = 2000)	
	public String getAgreementClause() {
		return agreementClause;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = true, length = 30)	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 30)	
	public String getSourceId() {
		return sourceId;
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

	@Column(name = "last_updated_by", nullable = true, length = 11)	
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

	@Column(name = "contract_id", nullable = true, length = 240)
	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	@Column(name = "contract_code", nullable = true, length = 240)
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	@Column(name = "pr_number", nullable = true, length = 200)
	public String getPrNumber() {
		return prNumber;
	}

	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}

	@Column(name = "location_code", nullable = true, length = 200)
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	@Column(name = "po_type", nullable = true, length = 30)
	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

    @Column(name = "ship_to_organization_id", nullable = true, length = 10)
    public Integer getShipToOrganizationId() {
        return shipToOrganizationId;
    }

    public void setShipToOrganizationId(Integer shipToOrganizationId) {
        this.shipToOrganizationId = shipToOrganizationId;
    }

	@Column(name = "ship_to_location_id", nullable = true, length = 10)
	public Integer getShipToLocationId() {
		return shipToLocationId;
	}

	public void setShipToLocationId(Integer shipToLocationId) {
		this.shipToLocationId = shipToLocationId;
	}

	@Column(name = "bill_to_location_id", nullable = true, length = 10)
	public Integer getBillToLocationId() {
		return billToLocationId;
	}

	public void setBillToLocationId(Integer billToLocationId) {
		this.billToLocationId = billToLocationId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}


    @Column(name = "logi_ship_to_location_code", nullable = true, length = 240)
    public String getLogiShipToLocationCode() {
        return logiShipToLocationCode;
    }

    public void setLogiShipToLocationCode(String logiShipToLocationCode) {
        this.logiShipToLocationCode = logiShipToLocationCode;
    }
    @Column(name = "logi_bill_to_location_code", nullable = true, length = 240)
    public String getLogiBillToLocationCode() {
        return logiBillToLocationCode;
    }

    public void setLogiBillToLocationCode(String logiBillToLocationCode) {
        this.logiBillToLocationCode = logiBillToLocationCode;
    }

    @Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


}
