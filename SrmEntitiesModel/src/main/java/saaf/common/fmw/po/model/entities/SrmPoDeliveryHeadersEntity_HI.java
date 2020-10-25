package saaf.common.fmw.po.model.entities;

import javax.persistence.*;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoDeliveryHeadersEntity_HI Entity Object Tue Dec 12 14:40:30 CST 2017 Auto
 * Generate
 */
@Entity
@Table(name = "srm_po_delivery_headers")
public class SrmPoDeliveryHeadersEntity_HI {
	private Integer deliveryHeaderId;//送货单ID
	private String deliveryNumber;//送货单编号
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryDate;//送货日期
	private Integer supplierId;//供应商ID，关联表：srm_pos_supplier_info
	private Integer orgId;//业务实体Id
	private Integer billToOrganizationId;//收单组织id
	private Integer receiveToOrganizationId;//收货组织id
	private String status;//送货单状态(POS_DELIVERY_STATUS)
	private String returnGoodsType;//回货类型
	private String description;//说明
	private Integer supplierSiteId;//供应商地点ID，关联表：srm_pos_supplier_sites
	private Integer versionNum;
	private String sourceCode;//数据来源
	private String sourceId;//数据来源ID
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

	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_DELIVERY_HEADERS_S", sequenceName = "SRM_PO_DELIVERY_HEADERS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_DELIVERY_HEADERS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "delivery_header_id", nullable = false, length = 11)
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}

	@Column(name = "delivery_number", nullable = false, length = 100)
	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = false, length = 30)
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_Id", nullable = false, length = 30)
	public String getSourceId() {
		return sourceId;
	}

	@Column(name = "supplier_Site_Id", nullable = false, length = 11)
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "delivery_date", nullable = true, length = 0)
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = true, length = 11)
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_Id", nullable = true, length = 11)
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

	@Column(name = "status", nullable = false, length = 30)
	public String getStatus() {
		return status;
	}

	public void setReturnGoodsType(String returnGoodsType) {
		this.returnGoodsType = returnGoodsType;
	}

	@Column(name = "return_goods_type", nullable = true, length = 30)
	public String getReturnGoodsType() {
		return returnGoodsType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 500)
	public String getDescription() {
		return description;
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
