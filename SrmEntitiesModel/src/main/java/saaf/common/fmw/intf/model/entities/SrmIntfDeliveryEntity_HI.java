package saaf.common.fmw.intf.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmIntfDeliveryEntity_HI Entity Object
 * Thu Dec 14 15:02:03 CST 2017  Auto Generate
 */
@Entity
@Table(name = "SRM_INTF_DELIVERY")
public class SrmIntfDeliveryEntity_HI {
    private Integer intfId;
    private String deliveryNumber;
    private String deliveryLineNumber;
    private String itemCode;
    private BigDecimal quantity;
    private String poNumber;
    private BigDecimal poLineNumber;
    private String docno;
    private String docType;
    private String handleStatus;
    private String handleMsg;
    private Integer logId;
    private String sourceType;
    private String batchId;
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

    public void setIntfId(Integer intfId) {
	this.intfId = intfId;
    }

    @Id    
    @GeneratedValue    
    @Column(name = "intf_id", nullable = false, length = 11)    
    public Integer getIntfId() {
	return intfId;
    }

    public void setDeliveryNumber(String deliveryNumber) {
	this.deliveryNumber = deliveryNumber;
    }

    @Column(name = "delivery_number", nullable = true, length = 30)    
    public String getDeliveryNumber() {
	return deliveryNumber;
    }

    public void setDeliveryLineNumber(String deliveryLineNumber) {
	this.deliveryLineNumber = deliveryLineNumber;
    }

    @Column(name = "delivery_line_number", nullable = true, length = 100)    
    public String getDeliveryLineNumber() {
	return deliveryLineNumber;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    @Column(name = "item_code", nullable = true, length = 100)    
    public String getItemCode() {
	return itemCode;
    }

    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }

    @Column(name = "quantity", precision = 20, scale = 6)    
    public BigDecimal getQuantity() {
	return quantity;
    }

    public void setPoNumber(String poNumber) {
	this.poNumber = poNumber;
    }

    @Column(name = "po_number", nullable = true, length = 100)    
    public String getPoNumber() {
	return poNumber;
    }

    public void setPoLineNumber(BigDecimal poLineNumber) {
	this.poLineNumber = poLineNumber;
    }

    @Column(name = "po_line_number", precision = 10, scale = 0)    
    public BigDecimal getPoLineNumber() {
	return poLineNumber;
    }

    public void setDocno(String docno) {
	this.docno = docno;
    }

    @Column(name = "docno", nullable = true, length = 100)    
    public String getDocno() {
	return docno;
    }

    public void setDocType(String docType) {
	this.docType = docType;
    }

    @Column(name = "doc_type", nullable = true, length = 100)    
    public String getDocType() {
	return docType;
    }

    public void setHandleStatus(String handleStatus) {
	this.handleStatus = handleStatus;
    }

    @Column(name = "handle_status", nullable = true, length = 30)    
    public String getHandleStatus() {
	return handleStatus;
    }

    public void setHandleMsg(String handleMsg) {
	this.handleMsg = handleMsg;
    }

    @Column(name = "handle_msg", nullable = true, length = 0)    
    public String getHandleMsg() {
	return handleMsg;
    }

    public void setLogId(Integer logId) {
	this.logId = logId;
    }

    @Column(name = "log_id", nullable = true, length = 11)    
    public Integer getLogId() {
	return logId;
    }

    public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
    }

    @Column(name = "source_type", nullable = true, length = 30)    
    public String getSourceType() {
	return sourceType;
    }

    public void setBatchId(String batchId) {
	this.batchId = batchId;
    }

    @Column(name = "batch_id", nullable = true, length = 100)    
    public String getBatchId() {
	return batchId;
    }

    public void setVersionNum(Integer versionNum) {
	this.versionNum = versionNum;
    }

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
    
    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

