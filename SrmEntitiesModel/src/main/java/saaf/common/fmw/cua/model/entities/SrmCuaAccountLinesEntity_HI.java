package saaf.common.fmw.cua.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * SrmCuaAccountLinesEntity_HI Entity Object
 * Tue Oct 30 11:45:13 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_cua_account_lines")
public class SrmCuaAccountLinesEntity_HI {
    private Integer accountLineId;
    private Integer accountId;
    private Integer transactionId;
    @JSONField(format = "yyyy-MM-dd")
    private Date transactionDate;
    private String transactionType;
    private Integer itemId;
    private String itemCode;
    private String itemName;
    private String uomCode;
    private String unitOfMeasure;
    private java.math.BigDecimal taxRate;
    private java.math.BigDecimal transactionQuantity;
    private Integer deliveryHeaderId;
    private Integer deliveryLineId;
    private Integer poHeaderId;
    private Integer poLineId;
    private Integer poLineLocationId;
    private Integer poDistributionId;
    private java.math.BigDecimal taxPrice;
    private java.math.BigDecimal nonTaxPrice;
    private String currencyCode;
    private String lineComments;
    private Integer versionNum;
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
    //后来添加的
    private java.math.BigDecimal shipmentNum; //发运行号 DELIVERY_NUMBER
    private String deliveryNumber;	//送货单号 
    private String poCode;	//采购订单编号
    private String receiptNum;	//ERP接收单号
    private String destinationTypeCode;	//ERP目的类型
    private Integer shipmentLineId;	//ERP接收单行ID
    private Integer shipmentLineNum;//ERP接收单行号
    
	public void setAccountLineId(Integer accountLineId) {
		this.accountLineId = accountLineId;
	}
	@Id
	@SequenceGenerator(name = "SRM_CUA_ACCOUNTS_S", sequenceName = "SRM_CUA_ACCOUNTS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_CUA_ACCOUNTS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "account_line_id", nullable = false, length = 22)	
	public Integer getAccountLineId() {
		return accountLineId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@Column(name = "account_id", nullable = false, length = 22)	
	public Integer getAccountId() {
		return accountId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "transaction_id", nullable = true, length = 22)	
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name = "transaction_date", nullable = true, length = 7)	
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "transaction_type", nullable = true, length = 30)	
	public String getTransactionType() {
		return transactionType;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 22)	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "item_code", nullable = true, length = 30)	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_name", nullable = true, length = 240)	
	public String getItemName() {
		return itemName;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	@Column(name = "uom_code", nullable = true, length = 10)	
	public String getUomCode() {
		return uomCode;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	@Column(name = "unit_of_measure", nullable = true, length = 25)	
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setTaxRate(java.math.BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Column(name = "tax_rate", nullable = true, length = 22)	
	public java.math.BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTransactionQuantity(java.math.BigDecimal transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}

	@Column(name = "transaction_quantity", nullable = true, length = 22)	
	public java.math.BigDecimal getTransactionQuantity() {
		return transactionQuantity;
	}

	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}

	@Column(name = "delivery_header_id", nullable = true, length = 22)	
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}

	public void setDeliveryLineId(Integer deliveryLineId) {
		this.deliveryLineId = deliveryLineId;
	}

	@Column(name = "delivery_line_id", nullable = true, length = 22)	
	public Integer getDeliveryLineId() {
		return deliveryLineId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = true, length = 22)	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	@Column(name = "po_line_id", nullable = true, length = 22)	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineLocationId(Integer poLineLocationId) {
		this.poLineLocationId = poLineLocationId;
	}

	@Column(name = "po_line_location_id", nullable = true, length = 22)	
	public Integer getPoLineLocationId() {
		return poLineLocationId;
	}

	public void setPoDistributionId(Integer poDistributionId) {
		this.poDistributionId = poDistributionId;
	}

	@Column(name = "po_distribution_id", nullable = true, length = 22)	
	public Integer getPoDistributionId() {
		return poDistributionId;
	}

	public void setTaxPrice(java.math.BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "tax_price", nullable = true, length = 22)	
	public java.math.BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(java.math.BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	@Column(name = "non_tax_price", nullable = true, length = 22)	
	public java.math.BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	@Column(name = "line_comments", nullable = true, length = 2000)	
	public String getLineComments() {
		return lineComments;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = false, length = 22)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 7)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 22)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 22)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 7)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 22)	
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
	@Column(name = "currency_code", nullable = true, length = 30)	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Column(name = "shipment_num", nullable = true, length = 22)	
	public java.math.BigDecimal getShipmentNum() {
		return shipmentNum;
	}
	
	public void setShipmentNum(java.math.BigDecimal shipmentNum) {
		this.shipmentNum = shipmentNum;
	}
	
	@Column(name = "delivery_number", nullable = true, length = 100)	
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	
	@Column(name = "po_code", nullable = true, length = 30)	
	public String getPoCode() {
		return poCode;
	}
	
	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}
	
	@Column(name = "receipt_num", nullable = true, length = 30)	
	public String getReceiptNum() {
		return receiptNum;
	}
	
	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
	}
	
	@Column(name = "destination_type_code", nullable = true, length = 25)	
	public String getDestinationTypeCode() {
		return destinationTypeCode;
	}
	
	public void setDestinationTypeCode(String destinationTypeCode) {
		this.destinationTypeCode = destinationTypeCode;
	}
	
	@Column(name = "SHIPMENT_LINE_ID", nullable = true, length = 22)	
	public Integer getShipmentLineId() {
		return shipmentLineId;
	}
	
	public void setShipmentLineId(Integer shipmentLineId) {
		this.shipmentLineId = shipmentLineId;
	}
	
	@Column(name = "SHIPMENT_LINE_NUM", nullable = true, length = 22)	
	public Integer getShipmentLineNum() {
		return shipmentLineNum;
	}
	
	public void setShipmentLineNum(Integer shipmentLineNum) {
		this.shipmentLineNum = shipmentLineNum;
	}
	
	
}
