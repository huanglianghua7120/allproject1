package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmSpmReceiptHeadersEntity_HI Entity Object
 * Sat Apr 14 11:52:26 GMT+08:00 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_receipt_headers")
public class SrmSpmReceiptHeadersEntity_HI {
    private Integer receiptId; //收退货ID，主键，供其他表做外键
    private String receiptNumber; //收退货单编号
    private String receiptType; //收退货类型，关联表：SAAF_LOOKUP_VALUES（GL_RECEIPT_TYPE）
    private Integer supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer poHeaderId; //订单头ID，关联表：SRM_PO_HEADERS(备用)
    private String poNumber; //订单编号(备用)
    private Integer deliveryHeaderId; //送货单头ID，关联表：SRM_PO_DELIVERY_HEADERS(备用)
    private String deliveryNumber; //送货单编号(备用)
    private Integer orgId; //收货单位，关联表：SAAF_INSTITUTION
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date businessDate; //收货日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date transmitDate; //传递日期
    private BigDecimal sourceId; //来源单据ID(备用)
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

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_RECEIPT_HEADERS_S", sequenceName = "SRM_SPM_RECEIPT_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_RECEIPT_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "receipt_id", nullable = false, length = 11)	
	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	@Column(name = "receipt_number", nullable = true, length = 100)	
	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	@Column(name = "receipt_type", nullable = true, length = 30)	
	public String getReceiptType() {
		return receiptType;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)	
	public Integer getSupplierId() {
		return supplierId;
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

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	@Column(name = "business_date", nullable = true, length = 0)	
	public Date getBusinessDate() {
		return businessDate;
	}

	public void setTransmitDate(Date transmitDate) {
		this.transmitDate = transmitDate;
	}

	@Column(name = "transmit_date", nullable = true, length = 0)	
	public Date getTransmitDate() {
		return transmitDate;
	}

	public void setSourceId(BigDecimal sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", precision = 22, scale = 0)	
	public BigDecimal getSourceId() {
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
