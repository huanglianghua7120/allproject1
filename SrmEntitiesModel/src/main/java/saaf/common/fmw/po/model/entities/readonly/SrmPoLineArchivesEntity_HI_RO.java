package saaf.common.fmw.po.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoLineArchivesEntity_HI_RO Entity Object
 * Thu Mar 21 15:34:37 CST 2019  Auto Generate
 */

public class SrmPoLineArchivesEntity_HI_RO {
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
    private BigDecimal onWayQty; //在途数量
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
    
    // 自定义
 	private BigDecimal deliverableQty;
 	private String itemCode;//物料编码
 	private String unitOfMeasure;
 	private String instCode;
 	private String itemDescription;//物料说明
    private String poDocTypeDESC; //单据类型描述
    private String fullCategoryCode; //采购分类全编号
    private String fullCategoryName; //采购分类全名称
    private String categoryCode;//采购分类编码
    private String categoryName;//采购分类名称
    private Integer categoryLevel; //分类层级
	private Integer orgId; //业务实体ID
    private String orgName;// 业务实体名称
    private String poNumber; //采购订单号
    private String uomCode; //计量单位
    private String uomCodeName; //别名-物料单位名称
    private Integer supplierId; //供应商ID
    private String supplierName; //供应商名称
	private String currencyCode; //币种(BANK_CURRENCY)
	private String currencyCodeName; //币种name(BANK_CURRENCY)
    private String isShowPrice;//是否显示价格（Y/N）
	private String categoryIdList;//categoryIdList
	private Integer imageId; //物料图片ID
	private String imageFileName; // 物料图片名称
	private String imageAccessPath; // 物料图片下载路径
	private Integer agreementLineId;//框架协议行ID
	private Integer shoppingCarCount;//加入购物车的数量

	public void setPoLineArchiveId(Integer poLineArchiveId) {
		this.poLineArchiveId = poLineArchiveId;
	}

	
	public Integer getPoLineArchiveId() {
		return poLineArchiveId;
	}

	public void setPoArchiveId(Integer poArchiveId) {
		this.poArchiveId = poArchiveId;
	}

	
	public Integer getPoArchiveId() {
		return poArchiveId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	
	public String getItemSpec() {
		return itemSpec;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getDescription() {
		return description;
	}

	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}

	
	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}

	public void setOnWayQty(BigDecimal onWayQty) {
		this.onWayQty = onWayQty;
	}

	
	public BigDecimal getOnWayQty() {
		return onWayQty;
	}

	public void setReceivedQty(BigDecimal receivedQty) {
		this.receivedQty = receivedQty;
	}

	
	public BigDecimal getReceivedQty() {
		return receivedQty;
	}

	public void setOriginalDemandQty(BigDecimal originalDemandQty) {
		this.originalDemandQty = originalDemandQty;
	}

	
	public BigDecimal getOriginalDemandQty() {
		return originalDemandQty;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
	}

	
	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setFeedbackAdjustDate(Date feedbackAdjustDate) {
		this.feedbackAdjustDate = feedbackAdjustDate;
	}

	
	public Date getFeedbackAdjustDate() {
		return feedbackAdjustDate;
	}

	public void setFeedbackAdjustQty(BigDecimal feedbackAdjustQty) {
		this.feedbackAdjustQty = feedbackAdjustQty;
	}

	
	public BigDecimal getFeedbackAdjustQty() {
		return feedbackAdjustQty;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	
	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	
	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
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


	public BigDecimal getDeliverableQty() {
		return deliverableQty;
	}


	public void setDeliverableQty(BigDecimal deliverableQty) {
		this.deliverableQty = deliverableQty;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}


	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}


	public String getInstCode() {
		return instCode;
	}


	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getPoDocTypeDESC() {
		return poDocTypeDESC;
	}


	public void setPoDocTypeDESC(String poDocTypeDESC) {
		this.poDocTypeDESC = poDocTypeDESC;
	}


	public String getFullCategoryCode() {
		return fullCategoryCode;
	}


	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}


	public String getFullCategoryName() {
		return fullCategoryName;
	}


	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}


	public String getCategoryCode() {
		return categoryCode;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Integer getCategoryLevel() {
		return categoryLevel;
	}


	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getPoNumber() {
		return poNumber;
	}


	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	public String getUomCode() {
		return uomCode;
	}


	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}


	public String getUomCodeName() {
		return uomCodeName;
	}


	public void setUomCodeName(String uomCodeName) {
		this.uomCodeName = uomCodeName;
	}


	public Integer getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getCurrencyCodeName() {
		return currencyCodeName;
	}


	public void setCurrencyCodeName(String currencyCodeName) {
		this.currencyCodeName = currencyCodeName;
	}


	public String getIsShowPrice() {
		return isShowPrice;
	}


	public void setIsShowPrice(String isShowPrice) {
		this.isShowPrice = isShowPrice;
	}


	public String getCategoryIdList() {
		return categoryIdList;
	}


	public void setCategoryIdList(String categoryIdList) {
		this.categoryIdList = categoryIdList;
	}


	public Integer getImageId() {
		return imageId;
	}


	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public String getImageAccessPath() {
		return imageAccessPath;
	}


	public void setImageAccessPath(String imageAccessPath) {
		this.imageAccessPath = imageAccessPath;
	}


	public Integer getAgreementLineId() {
		return agreementLineId;
	}


	public void setAgreementLineId(Integer agreementLineId) {
		this.agreementLineId = agreementLineId;
	}


	public Integer getShoppingCarCount() {
		return shoppingCarCount;
	}


	public void setShoppingCarCount(Integer shoppingCarCount) {
		this.shoppingCarCount = shoppingCarCount;
	}


	public BigDecimal getMinPoQty() {
		return minPoQty;
	}


	public void setMinPoQty(BigDecimal minPoQty) {
		this.minPoQty = minPoQty;
	}
	
   public String getLadderPriceFlag() {
        return ladderPriceFlag;
    }

    public void setLadderPriceFlag(String ladderPriceFlag) {
        this.ladderPriceFlag = ladderPriceFlag;
    }

    public BigDecimal getLadderQty() {
        return ladderQty;
    }

    public void setLadderQty(BigDecimal ladderQty) {
        this.ladderQty = ladderQty;
    }

    public String getAccumulativeFlag() {
        return accumulativeFlag;
    }

    public void setAccumulativeFlag(String accumulativeFlag) {
        this.accumulativeFlag = accumulativeFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
	
    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }
	
	
}
