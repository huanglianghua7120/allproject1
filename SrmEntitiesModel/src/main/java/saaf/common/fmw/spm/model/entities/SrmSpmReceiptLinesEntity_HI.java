package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmReceiptLinesEntity_HI Entity Object
 * Sat Apr 14 11:52:26 GMT+08:00 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_receipt_lines")
public class SrmSpmReceiptLinesEntity_HI {
    private Integer receiptLineId; //收退货行ID，主键，供其他表做外键
    private Integer receiptId; //收退货ID
    private Integer poHeaderId; //订单头ID，关联表：SRM_PO_HEADERS
    private String poNumber; //订单编号
    private String poLineNumber; //订单行编号
    private Integer deliveryHeaderId; //送货单头ID，关联表：SRM_PO_DELIVERY_HEADERS
    private String deliveryNumber; //送货单编号
    private Integer supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO（备用）
    private Integer itemId; //物料ID，关联表：SRM_BASE_ITEMS
    private BigDecimal unitPrice; //单价
    private BigDecimal quantity; //数量
    private BigDecimal receiptQuantity; //实收数量
    private BigDecimal eyeballingQuantity; //点收数量
    private BigDecimal rejectQuantity; //拒收数量
    private BigDecimal returnFillQuanitty; //退补数量
    private BigDecimal returnDeductQuanitty; //退扣数量
    private BigDecimal confirmedQuantity; //已立账数量
    private BigDecimal arriveQuantity; //实到数量
    private BigDecimal concessionQuantity; //让步接收数量
    private String canSyncFlag; //是否可以创建发票，1是可以
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date transmitDate; //传递日期
    private String sourceDocNo; //来源单据号
    private String sourceDocLineNo; //来源单据行号
    private BigDecimal sourceId; //U9单据行ID
    private String sourceNumber; //U9单据行号
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer operatorUserId;

	public void setReceiptLineId(Integer receiptLineId) {
		this.receiptLineId = receiptLineId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_RECEIPT_LINES_S", sequenceName = "SRM_SPM_RECEIPT_LINES_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_RECEIPT_LINES_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "receipt_line_id", nullable = false, length = 11)	
	public Integer getReceiptLineId() {
		return receiptLineId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	@Column(name = "receipt_id", nullable = true, length = 11)	
	public Integer getReceiptId() {
		return receiptId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = true, length = 11)	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	@Column(name = "po_number", nullable = true, length = 100)	
	public String getPoNumber() {
		return poNumber;
	}

	public void setPoLineNumber(String poLineNumber) {
		this.poLineNumber = poLineNumber;
	}

	@Column(name = "po_line_number", nullable = true, length = 100)	
	public String getPoLineNumber() {
		return poLineNumber;
	}

	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}

	@Column(name = "delivery_header_id", nullable = true, length = 11)	
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	@Column(name = "delivery_number", nullable = true, length = 100)	
	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "unit_price", precision = 22, scale = 2)	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "quantity", precision = 22, scale = 2)	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setReceiptQuantity(BigDecimal receiptQuantity) {
		this.receiptQuantity = receiptQuantity;
	}

	@Column(name = "receipt_quantity", precision = 22, scale = 2)	
	public BigDecimal getReceiptQuantity() {
		return receiptQuantity;
	}

	public void setEyeballingQuantity(BigDecimal eyeballingQuantity) {
		this.eyeballingQuantity = eyeballingQuantity;
	}

	@Column(name = "eyeballing_quantity", precision = 22, scale = 2)	
	public BigDecimal getEyeballingQuantity() {
		return eyeballingQuantity;
	}

	public void setRejectQuantity(BigDecimal rejectQuantity) {
		this.rejectQuantity = rejectQuantity;
	}

	@Column(name = "reject_quantity", precision = 22, scale = 2)	
	public BigDecimal getRejectQuantity() {
		return rejectQuantity;
	}

	public void setReturnFillQuanitty(BigDecimal returnFillQuanitty) {
		this.returnFillQuanitty = returnFillQuanitty;
	}

	@Column(name = "return_fill_quanitty", precision = 22, scale = 2)	
	public BigDecimal getReturnFillQuanitty() {
		return returnFillQuanitty;
	}

	public void setReturnDeductQuanitty(BigDecimal returnDeductQuanitty) {
		this.returnDeductQuanitty = returnDeductQuanitty;
	}

	@Column(name = "return_deduct_quanitty", precision = 22, scale = 2)	
	public BigDecimal getReturnDeductQuanitty() {
		return returnDeductQuanitty;
	}

	public void setConfirmedQuantity(BigDecimal confirmedQuantity) {
		this.confirmedQuantity = confirmedQuantity;
	}

	@Column(name = "confirmed_quantity", precision = 22, scale = 2)	
	public BigDecimal getConfirmedQuantity() {
		return confirmedQuantity;
	}

	public void setArriveQuantity(BigDecimal arriveQuantity) {
		this.arriveQuantity = arriveQuantity;
	}

	@Column(name = "arrive_quantity", precision = 22, scale = 2)	
	public BigDecimal getArriveQuantity() {
		return arriveQuantity;
	}

	public void setConcessionQuantity(BigDecimal concessionQuantity) {
		this.concessionQuantity = concessionQuantity;
	}

	@Column(name = "concession_quantity", precision = 22, scale = 2)	
	public BigDecimal getConcessionQuantity() {
		return concessionQuantity;
	}

	public void setCanSyncFlag(String canSyncFlag) {
		this.canSyncFlag = canSyncFlag;
	}

	@Column(name = "can_sync_flag", nullable = true, length = 30)	
	public String getCanSyncFlag() {
		return canSyncFlag;
	}

	public void setTransmitDate(Date transmitDate) {
		this.transmitDate = transmitDate;
	}

	@Column(name = "transmit_date", nullable = true, length = 0)	
	public Date getTransmitDate() {
		return transmitDate;
	}

	public void setSourceDocNo(String sourceDocNo) {
		this.sourceDocNo = sourceDocNo;
	}

	@Column(name = "source_doc_no", nullable = true, length = 240)	
	public String getSourceDocNo() {
		return sourceDocNo;
	}

	public void setSourceDocLineNo(String sourceDocLineNo) {
		this.sourceDocLineNo = sourceDocLineNo;
	}

	@Column(name = "source_doc_line_no", nullable = true, length = 240)	
	public String getSourceDocLineNo() {
		return sourceDocLineNo;
	}

	public void setSourceId(BigDecimal sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", precision = 22, scale = 0)	
	public BigDecimal getSourceId() {
		return sourceId;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	@Column(name = "source_number", nullable = true, length = 100)	
	public String getSourceNumber() {
		return sourceNumber;
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
}
