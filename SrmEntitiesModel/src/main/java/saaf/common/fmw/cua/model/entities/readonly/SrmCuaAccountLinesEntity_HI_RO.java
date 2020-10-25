package saaf.common.fmw.cua.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmCuaAccountLinesEntity_HI_RO Entity Object
 * Tue Oct 30 11:45:13 CST 2018  Auto Generate
 */

public class SrmCuaAccountLinesEntity_HI_RO {
    //查询对账单行信息
    public static final String QUERY_ACCOUNT_LINES_SQL ="select\r\n" + 
    		"scal.ACCOUNT_LINE_ID accountLineId,\r\n" + 
    		"scal.ACCOUNT_ID accountId,\r\n" + 
    		"scal.TRANSACTION_ID transactionId,\r\n" + 
    		"scal.TRANSACTION_DATE transactionDate,\r\n" + 
    		"scal.transaction_type transactionType,\r\n" + 
    		"slv.meaning transactionTypeName,\r\n" +
    		"scal.item_id itemId,\r\n" + 
    		"scal.ITEM_CODE itemCode,\r\n" + 
    		"scal.ITEM_NAME itemName,\r\n" + 
    		"scal.uom_code uomCode,\r\n" + 
    		"scal.UNIT_OF_MEASURE unitOfMeasure,\r\n" + 
    		"scal.tax_rate taxRate,\r\n" + 
    		"scal.TRANSACTION_QUANTITY transactionQuantity,\r\n" + 
    		"scal.po_header_id poHeaderId,\r\n" + 
    		"scal.po_line_id poLineId,\r\n" + 
    		"scal.po_line_location_id poLineLocationId,\r\n" + 
    		"scal.po_distribution_id poDistributionId,\r\n" + 
    		"scal.TAX_PRICE taxPrice,\r\n" + 
    		"scal.NON_TAX_PRICE nonTaxPrice,\r\n" + 
    		"scal.CURRENCY_CODE currencyCode,\r\n" + 
    		"scal.LINE_COMMENTS lineComments,\r\n" + 
    		"scal.VERSION_NUM versionNum,\r\n" + 
    		"Round(scal.TAX_PRICE * scal.TRANSACTION_QUANTITY, 2) taxAmount,\r\n" + 
    		"Round(scal.NON_TAX_PRICE * scal.TRANSACTION_QUANTITY, 2) amount,\r\n" + 
    		"Round((scal.TAX_PRICE - scal.NON_TAX_PRICE) * scal.TRANSACTION_QUANTITY, 2) taxMoney,\r\n" + 
    		"scal.shipment_num shipmentNum,\r\n" + 
    		"scal.delivery_number deliveryNumber,\r\n" + 
    		"scal.PO_CODE poCode,\r\n" + 
    		"scal.receipt_num receiptNum,\r\n" + 
    		"scal.SHIPMENT_LINE_ID shipmentLineId,\r\n" + 
    		"scal.SHIPMENT_LINE_NUM shipmentLineNum,\r\n" + 
    		"scal.destination_type_code destinationTypeCode\r\n" + 
    		"from srm_cua_account_lines scal\r\n" +
//    		"left join srm_erp_transactions_v v on scal.TRANSACTION_ID = v.TRANSACTION_ID\r\n" +
    		"LEFT JOIN saaf_lookup_values  slv on  scal.transaction_type = slv.lookup_code and slv.lookup_type =  'SNBC_DZ_TYPE' \r\n" + 
    		"WHERE\n" +
            "\t1 = 1 ";
    private Integer accountLineId;	//对账单行表id
    private Integer accountId;	//对账单头表id
    private Integer transactionId;	//ERP事务处理ID
    @JSONField(format = "yyyy-MM-dd")
    private Date transactionDate;	//ERP事务处理时间
    private String transactionType;	//ERP事务处理类型
    private Integer itemId;	//物料ID
    private String itemCode;	//
    private String itemName;	//物料CODE
    private String uomCode;	//计量单位CODE
    private String poCode;	//采购订单编号
    private String unitOfMeasure;	//计量单位名称
    private java.math.BigDecimal taxRate;	//税率
    private java.math.BigDecimal transactionQuantity;	//ERP事务处理数量
    private Integer deliveryHeaderId;	//SRM送货单ID	
    private Integer deliveryLineId;	//SRM送货单行ID
    private Integer poHeaderId;	//ERP采购订单ID
    private Integer poLineId;	//ERP采购订单行ID
    private Integer poLineLocationId;	//ERP采购订单发运行ID
    private Integer poDistributionId;	//ERP采购订单分配行ID
    private java.math.BigDecimal taxPrice;	//含税单价
    private java.math.BigDecimal nonTaxPrice;	//不含税单价
    private String currencyCode;	//币种
    private String lineComments;	//备注
    private Integer versionNum;	//行版本号，用来处理锁
    private java.math.BigDecimal shipmentNum;	//发运行号
    private String deliveryNumber;	//送货单号
    private String receiptNum;	//ERP接收单号
    private String destinationTypeCode;	//ERP目的类型
    private Integer shipmentLineId;	//ERP接收单行ID
    private Integer shipmentLineNum;//ERP接收单行号
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd")
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer operatorUserId;
    //自定义
    private java.math.BigDecimal taxAmount; //含税总价
    private java.math.BigDecimal amount; //不含税总价
    private java.math.BigDecimal taxMoney; //税额
    private java.math.BigDecimal lineNum;  //采购订单行号
    private String transactionTypeName; //类型名称

	public void setAccountLineId(Integer accountLineId) {
		this.accountLineId = accountLineId;
	}

	
	public Integer getAccountLineId() {
		return accountLineId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	
	public Integer getAccountId() {
		return accountId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	
	public String getTransactionType() {
		return transactionType;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	
	public String getUomCode() {
		return uomCode;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTransactionQuantity(java.math.BigDecimal transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}

	
	public java.math.BigDecimal getTransactionQuantity() {
		return transactionQuantity;
	}

	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}

	
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}

	public void setDeliveryLineId(Integer deliveryLineId) {
		this.deliveryLineId = deliveryLineId;
	}

	
	public Integer getDeliveryLineId() {
		return deliveryLineId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineLocationId(Integer poLineLocationId) {
		this.poLineLocationId = poLineLocationId;
	}

	
	public Integer getPoLineLocationId() {
		return poLineLocationId;
	}

	public void setPoDistributionId(Integer poDistributionId) {
		this.poDistributionId = poDistributionId;
	}

	
	public Integer getPoDistributionId() {
		return poDistributionId;
	}

	public void setTaxPrice(java.math.BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	
	public java.math.BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(java.math.BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	
	public java.math.BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	
	public String getLineComments() {
		return lineComments;
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public static String getQueryAccountLinesSql() {
		return QUERY_ACCOUNT_LINES_SQL;
	}


	public java.math.BigDecimal getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}


	public java.math.BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
	}


	public java.math.BigDecimal getTaxMoney() {
		return taxMoney;
	}


	public void setTaxMoney(java.math.BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}


	public java.math.BigDecimal getLineNum() {
		return lineNum;
	}


	public void setLineNum(java.math.BigDecimal lineNum) {
		this.lineNum = lineNum;
	}


	public String getPoCode() {
		return poCode;
	}


	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}


	public String getDeliveryNumber() {
		return deliveryNumber;
	}


	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}


	public String getTransactionTypeName() {
		return transactionTypeName;
	}


	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}


	public java.math.BigDecimal getShipmentNum() {
		return shipmentNum;
	}


	public void setShipmentNum(java.math.BigDecimal shipmentNum) {
		this.shipmentNum = shipmentNum;
	}


	public String getReceiptNum() {
		return receiptNum;
	}


	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}


	public String getDestinationTypeCode() {
		return destinationTypeCode;
	}


	public void setDestinationTypeCode(String destinationTypeCode) {
		this.destinationTypeCode = destinationTypeCode;
	}


	public Integer getShipmentLineId() {
		return shipmentLineId;
	}


	public void setShipmentLineId(Integer shipmentLineId) {
		this.shipmentLineId = shipmentLineId;
	}


	public Integer getShipmentLineNum() {
		return shipmentLineNum;
	}


	public void setShipmentLineNum(Integer shipmentLineNum) {
		this.shipmentLineNum = shipmentLineNum;
	}
	
	
	
}
