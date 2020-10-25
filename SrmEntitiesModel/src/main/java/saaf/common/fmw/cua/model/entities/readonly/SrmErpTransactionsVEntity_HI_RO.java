package saaf.common.fmw.cua.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * SrmErpTransactionsVEntity_HI_RO Entity Object
 * Fri Nov 02 17:14:32 CST 2018  Auto Generate
 */

public class SrmErpTransactionsVEntity_HI_RO {
	//查询对账单入库退货单据信息
	public static final String QUERY_VIEW_SQL = "select \r\n" + 
			"v.po_code poCode,\r\n" + 
			"v.TRANSACTION_DATE transactionDate,\r\n" + 
			"v.ITEM_CODE itemCode,\r\n" + 
			"v.ITEM_NAME itemName,\r\n" + 
			"v.UNIT_OF_MEASURE unitOfMeasure,\r\n" + 
			"v.tax_rate taxRate,\r\n" + 
			"Round(v.UNIT_PRICE, 6) nonTaxPrice,\r\n" + 
			"v.QUANTITY transactionQuantity,\r\n" + 
			"Round(v.UNIT_PRICE * ( 1 + 0.01 * tax_rate ) * v.QUANTITY , 2)  taxAmount,\r\n" + 
			"Round(v.UNIT_PRICE * v.QUANTITY , 2) amount,\r\n" + 
			"Round(v.UNIT_PRICE * v.QUANTITY * tax_rate * 0.01 , 2) taxMoney,\r\n" + 
			"v.shipment_num shipmentNum,\r\n" + 
    		"v.delivery_number deliveryNumber,\r\n" + 
			"v.currency_code currencyCode,\r\n" + 
			"v.transaction_id transactionId,\r\n" + 
			"v.item_id itemId,\r\n" + 
			"v.transaction_type transactionType,\r\n" + 
    		"slv.meaning transactionTypeName,\r\n" +
			"v.uom_code uomCode,\r\n" + 
			"v.po_header_id poHeaderId,\r\n" + 
			"v.po_line_id poLineId,\r\n" + 
			"v.po_line_location_id poLineLocationId,\r\n" + 
			"v.po_distribution_id poDistributionId,\r\n" + 
			"v.UNIT_PRICE * ( 1 + 0.01 * tax_rate ) taxPrice,\r\n" + 
			"v.receipt_num receiptNum,\r\n" + 
			"v.SHIPMENT_LINE_ID shipmentLineId,\r\n" + 
			"v.SHIPMENT_LINE_NUM shipmentLineNum,\r\n" + 
			"v.destination_type_code destinationTypeCode\r\n" + 
			"from srm_erp_transactions_v v\r\n" + 
    		"LEFT JOIN saaf_lookup_values  slv on  v.transaction_type = slv.lookup_code and slv.lookup_type =  'SNBC_DZ_TYPE' \r\n" + 
			"where 1=1 \r\n";
    private java.math.BigDecimal taxRate;
    private java.math.BigDecimal organizationId;
    private java.math.BigDecimal itemId;
    private String itemCode;
    private String itemName;
    private String uomCode;
    private String unitOfMeasure;
    private java.math.BigDecimal transactionId;
    private java.math.BigDecimal parentTransactionId;
    private String transactionType;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;
    private java.math.BigDecimal quantity;
    private java.math.BigDecimal poHeaderId;
    private String poCode;
    private java.math.BigDecimal poLineId;
    private java.math.BigDecimal lineNum;
    private java.math.BigDecimal poLineLocationId;
    private java.math.BigDecimal poDistributionId;
    private java.math.BigDecimal unitPrice;
    private String currencyCode;
    private java.math.BigDecimal vendorId;
    private java.math.BigDecimal vendorSiteId;
    private java.math.BigDecimal supplierId;
    private String taxCode;
    private Integer operatorUserId;
    private String receiptNum;	//ERP接收单号
    private String destinationTypeCode;	//ERP目的类型
    private Integer shipmentLineId;	//ERP接收单行ID
    private Integer shipmentLineNum;//ERP接收单行号
    //自定义
    private java.math.BigDecimal taxAmount;   //含税总价
    private java.math.BigDecimal amount;	//不含税总价
    private java.math.BigDecimal taxMoney;	//税额
    private java.math.BigDecimal taxPrice;	//含税单价
    private java.math.BigDecimal nonTaxPrice; //不含税单价
    private java.math.BigDecimal transactionQuantity; //数量
    private String deliveryNumber; //送货单号
    private java.math.BigDecimal shipmentNum;	//发运行号
    private String transactionTypeName; //类型名称

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setOrganizationId(java.math.BigDecimal organizationId) {
		this.organizationId = organizationId;
	}

	
	public java.math.BigDecimal getOrganizationId() {
		return organizationId;
	}

	public void setItemId(java.math.BigDecimal itemId) {
		this.itemId = itemId;
	}

	
	public java.math.BigDecimal getItemId() {
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

	public void setTransactionId(java.math.BigDecimal transactionId) {
		this.transactionId = transactionId;
	}

	
	public java.math.BigDecimal getTransactionId() {
		return transactionId;
	}

	public void setParentTransactionId(java.math.BigDecimal parentTransactionId) {
		this.parentTransactionId = parentTransactionId;
	}

	
	public java.math.BigDecimal getParentTransactionId() {
		return parentTransactionId;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}

	
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}

	public void setPoHeaderId(java.math.BigDecimal poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	
	public java.math.BigDecimal getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	
	public String getPoCode() {
		return poCode;
	}

	public void setPoLineId(java.math.BigDecimal poLineId) {
		this.poLineId = poLineId;
	}

	
	public java.math.BigDecimal getPoLineId() {
		return poLineId;
	}

	public void setLineNum(java.math.BigDecimal lineNum) {
		this.lineNum = lineNum;
	}

	
	public java.math.BigDecimal getLineNum() {
		return lineNum;
	}

	public void setPoLineLocationId(java.math.BigDecimal poLineLocationId) {
		this.poLineLocationId = poLineLocationId;
	}

	
	public java.math.BigDecimal getPoLineLocationId() {
		return poLineLocationId;
	}

	public void setPoDistributionId(java.math.BigDecimal poDistributionId) {
		this.poDistributionId = poDistributionId;
	}

	
	public java.math.BigDecimal getPoDistributionId() {
		return poDistributionId;
	}

	public void setUnitPrice(java.math.BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	
	public java.math.BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setVendorId(java.math.BigDecimal vendorId) {
		this.vendorId = vendorId;
	}

	
	public java.math.BigDecimal getVendorId() {
		return vendorId;
	}

	public void setVendorSiteId(java.math.BigDecimal vendorSiteId) {
		this.vendorSiteId = vendorSiteId;
	}

	
	public java.math.BigDecimal getVendorSiteId() {
		return vendorSiteId;
	}

	public void setSupplierId(java.math.BigDecimal supplierId) {
		this.supplierId = supplierId;
	}

	
	public java.math.BigDecimal getSupplierId() {
		return supplierId;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	
	public String getTaxCode() {
		return taxCode;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


	public static String getQueryViewSql() {
		return QUERY_VIEW_SQL;
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


	public java.math.BigDecimal getTaxPrice() {
		return taxPrice;
	}


	public void setTaxPrice(java.math.BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}


	public java.math.BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}


	public void setNonTaxPrice(java.math.BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}


	public java.math.BigDecimal getTransactionQuantity() {
		return transactionQuantity;
	}


	public void setTransactionQuantity(java.math.BigDecimal transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
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
