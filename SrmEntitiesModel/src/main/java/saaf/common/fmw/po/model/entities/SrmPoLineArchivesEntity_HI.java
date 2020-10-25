package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoLineArchivesEntity_HI Entity Object
 * Thu Mar 21 15:34:37 CST 2019  Auto Generate
 */
@Entity
@Table(name = "srm_po_line_archives")
public class SrmPoLineArchivesEntity_HI {
    private Integer poLineArchiveId; //采购订单行归档ID
    private Integer poArchiveId; //采购订单归档ID
	private Integer poLineId; //采购订单行ID
	private Integer poHeaderId; //订单头ID
	private Integer lineNumber; //行号
	private Integer itemId; //物料ID，关联表：srm_base_items
	private String itemName; //物料名称
	private String itemSpec; //物料规格
	private Integer categoryId; //采购分类ID，关联表：srm_base_categories
	private BigDecimal demandQty; //需求数量
	private BigDecimal minPoQty; //最小采购量
	private BigDecimal taxPrice; //含税单价
	private BigDecimal nonTaxPrice; //不含税单价
	private String ladderPriceFlag; //阶梯价标识（Y/N）
	private BigDecimal ladderQty; //阶梯范围
	private String accumulativeFlag; //累计结算标识（Y/N）
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date demandDate; //需求日期
	private Integer receiveToOrganizationId; //收货组织ID
	private String status; //行状态(PO_LINE_STATUS)
	private String description; //说明
	private BigDecimal mayNoticeQty; //可通知送货数量
	private BigDecimal onWayQty; //在途数量(已创建送货单数量)
	private BigDecimal receivedQty; //已接收数量
	private BigDecimal originalDemandQty; //原需求数量
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date originalDemandDate; //原需求日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date feedbackAdjustDate; //反馈调整日期
	private BigDecimal feedbackAdjustQty; //反馈调整数量
	private String feedbackStatus; //反馈状态
	private String feedbackResult; //反馈结果
	private String rejectReason; //供应商拒绝原因
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startDate; //有效开始日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endDate; //有效结束日期
	private String sourceNumber; //来源单号
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

	private BigDecimal returnQty;
	private String erpPoNumber;
	private String context;
	private String projectCategory;
	private String projectType;
	private String technicalTransNumber;
	private String subprojectNumber;
	private String acceptanceProcessNumber;
	private String taxRateCode;
	private BigDecimal nonTaxTotalPrice;
	private BigDecimal taxTotalPrice;
	private BigDecimal nonTaxActTotalPrice;
	private BigDecimal taxActTotalPrice;
	private String expenseItemCode;

    private Integer poLineCombId;
    private Integer contractItemId;
    private Integer contractId;

	@Id
	@SequenceGenerator(name = "SRM_PO_LINE_ARCHIVES_S", sequenceName = "SRM_PO_LINE_ARCHIVES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_LINE_ARCHIVES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "po_line_archive_id", nullable = false, length = 11)
	public Integer getPoLineArchiveId() {
		return poLineArchiveId;
	}

	public void setPoLineArchiveId(Integer poLineArchiveId) {
		this.poLineArchiveId = poLineArchiveId;
	}

	@Column(name = "po_archive_id", nullable = false, length = 11)
	public Integer getPoArchiveId() {
		return poArchiveId;
	}

	public void setPoArchiveId(Integer poArchiveId) {
		this.poArchiveId = poArchiveId;
	}

    @Column(name = "po_line_comb_id", nullable = true, length = 11)
    public Integer getPoLineCombId() {
        return poLineCombId;
    }

    public void setPoLineCombId(Integer poLineCombId) {
        this.poLineCombId = poLineCombId;
    }
    @Column(name = "contract_item_id", nullable = true, length = 10)
    public Integer getContractItemId() {
        return contractItemId;
    }

    public void setContractItemId(Integer contractItemId) {
        this.contractItemId = contractItemId;
    }

    @Column(name = "contract_id", nullable = true, length = 10)
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }


    @Column(name = "expense_item_code", nullable = true, length = 240)
    public String getExpenseItemCode() {
        return expenseItemCode;
    }

    public void setExpenseItemCode(String expenseItemCode) {
        this.expenseItemCode = expenseItemCode;
    }

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	@Column(name = "po_line_id", nullable = false, length = 11)
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = false, length = 11)
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Column(name = "line_number", nullable = false, length = 11)
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)
	public Integer getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_name", nullable = true, length = 240)
	public String getItemName() {
		return itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	@Column(name = "item_spec", nullable = true, length = 240)
	public String getItemSpec() {
		return itemSpec;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	@Column(name = "demand_qty", precision = 15, scale = 3)
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setMinPoQty(BigDecimal minPoQty) {
		this.minPoQty = minPoQty;
	}

	@Column(name = "min_po_qty", precision = 15, scale = 3)
	public BigDecimal getMinPoQty() {
		return minPoQty;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "tax_price", precision = 15, scale = 6)
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	@Column(name = "non_tax_price", precision = 15, scale = 6)
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setLadderPriceFlag(String ladderPriceFlag) {
		this.ladderPriceFlag = ladderPriceFlag;
	}

	@Column(name = "ladder_price_flag", nullable = true, length = 1)
	public String getLadderPriceFlag() {
		return ladderPriceFlag;
	}

	public void setLadderQty(BigDecimal ladderQty) {
		this.ladderQty = ladderQty;
	}

	@Column(name = "ladder_qty", precision = 15, scale = 3)
	public BigDecimal getLadderQty() {
		return ladderQty;
	}

	public void setAccumulativeFlag(String accumulativeFlag) {
		this.accumulativeFlag = accumulativeFlag;
	}

	@Column(name = "accumulative_flag", nullable = true, length = 1)
	public String getAccumulativeFlag() {
		return accumulativeFlag;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	@Column(name = "demand_date", nullable = true, length = 0)
	public Date getDemandDate() {
		return demandDate;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	@Column(name = "receive_to_organization_id", nullable = true, length = 11)
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 30)
	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 500)
	public String getDescription() {
		return description;
	}

	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}

	@Column(name = "may_notice_qty", precision = 15, scale = 3)
	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}

	public void setOnWayQty(BigDecimal onWayQty) {
		this.onWayQty = onWayQty;
	}

	@Column(name = "on_way_qty", precision = 15, scale = 3)
	public BigDecimal getOnWayQty() {
		return onWayQty;
	}

	public void setReceivedQty(BigDecimal receivedQty) {
		this.receivedQty = receivedQty;
	}

	@Column(name = "received_qty", precision = 15, scale = 3)
	public BigDecimal getReceivedQty() {
		return receivedQty;
	}

	public void setOriginalDemandQty(BigDecimal originalDemandQty) {
		this.originalDemandQty = originalDemandQty;
	}

	@Column(name = "original_demand_qty", precision = 15, scale = 3)
	public BigDecimal getOriginalDemandQty() {
		return originalDemandQty;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
	}

	@Column(name = "original_demand_date", nullable = true, length = 0)
	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setFeedbackAdjustDate(Date feedbackAdjustDate) {
		this.feedbackAdjustDate = feedbackAdjustDate;
	}

	@Column(name = "feedback_adjust_date", nullable = true, length = 0)
	public Date getFeedbackAdjustDate() {
		return feedbackAdjustDate;
	}

	public void setFeedbackAdjustQty(BigDecimal feedbackAdjustQty) {
		this.feedbackAdjustQty = feedbackAdjustQty;
	}

	@Column(name = "feedback_adjust_qty", precision = 15, scale = 3)
	public BigDecimal getFeedbackAdjustQty() {
		return feedbackAdjustQty;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	@Column(name = "feedback_status", nullable = true, length = 30)
	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	@Column(name = "feedback_result", nullable = true, length = 30)
	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "reject_reason", nullable = true, length = 240)
	public String getRejectReason() {
		return rejectReason;
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

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	@Column(name = "source_number", nullable = true, length = 30)
	public String getSourceNumber() {
		return sourceNumber;
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

	@Column(name = "return_qty", precision = 15, scale = 3)
	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	@Column(name = "erp_po_number", nullable = true, length = 200)
	public String getErpPoNumber() {
		return erpPoNumber;
	}

	public void setErpPoNumber(String erpPoNumber) {
		this.erpPoNumber = erpPoNumber;
	}

	@Column(name = "context", nullable = true, length = 200)
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "project_category", nullable = true, length = 100)
	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	@Column(name = "project_type", nullable = true, length = 100)
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name = "technical_trans_number", nullable = true, length = 200)
	public String getTechnicalTransNumber() {
		return technicalTransNumber;
	}

	public void setTechnicalTransNumber(String technicalTransNumber) {
		this.technicalTransNumber = technicalTransNumber;
	}

	@Column(name = "subproject_number", nullable = true, length = 200)
	public String getSubprojectNumber() {
		return subprojectNumber;
	}

	public void setSubprojectNumber(String subprojectNumber) {
		this.subprojectNumber = subprojectNumber;
	}

	@Column(name = "acceptance_process_number", nullable = true, length = 200)
	public String getAcceptanceProcessNumber() {
		return acceptanceProcessNumber;
	}

	public void setAcceptanceProcessNumber(String acceptanceProcessNumber) {
		this.acceptanceProcessNumber = acceptanceProcessNumber;
	}

	@Column(name = "tax_rate_code", nullable = true, length = 30)
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	@Column(name = "non_tax_total_price", precision = 15, scale = 4)
	public BigDecimal getNonTaxTotalPrice() {
		return nonTaxTotalPrice;
	}

	public void setNonTaxTotalPrice(BigDecimal nonTaxTotalPrice) {
		this.nonTaxTotalPrice = nonTaxTotalPrice;
	}

	@Column(name = "tax_total_price", precision = 15, scale = 4)
	public BigDecimal getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(BigDecimal taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

	@Column(name = "non_tax_act_total_price", precision = 15, scale = 4)
	public BigDecimal getNonTaxActTotalPrice() {
		return nonTaxActTotalPrice;
	}

	public void setNonTaxActTotalPrice(BigDecimal nonTaxActTotalPrice) {
		this.nonTaxActTotalPrice = nonTaxActTotalPrice;
	}

	@Column(name = "tax_act_total_price", precision = 15, scale = 4)
	public BigDecimal getTaxActTotalPrice() {
		return taxActTotalPrice;
	}

	public void setTaxActTotalPrice(BigDecimal taxActTotalPrice) {
		this.taxActTotalPrice = taxActTotalPrice;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
