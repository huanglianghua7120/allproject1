package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmPoNoticeEntity_HI Entity Object
 * Wed Aug 15 14:33:04 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_notice")
public class SrmPoNoticeEntity_HI {
    private Integer poNoticeId; //送货通知ID
    private String poNoticeCode; //送货通知编号
    private Integer orgId; //业务实体ID
    private Integer billToOrganizationId; //收单组织ID
    private Integer supplierId; //供应商ID
    private Integer supplierSiteId; //供应商地点ID
    private Integer buyerId; //采购员ID，关联表：saaf_employees
    private String poNoticeStatus; //送货通知状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvedDate; //批准时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date issuedDate; //下达时间
    private String comments; //说明
    private Integer fileId; //附件ID
    private BigDecimal poNoticeVersions; //送货通知版本
    private Integer instId; //（废弃）分厂(货主组织)
    private Integer deliverySiteId; //（废弃）送货地点id(saaf_institution inst_id)
    private Integer itemId; //（废弃）物料id
    private Integer poStarvingId; //（废弃）工单缺料id
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date demandDate; //（废弃）需求日期
    private BigDecimal quantity; //（废弃）需求数量
    private String employeeNum; //（废弃）采购员编码
    private String status; //（废弃）送货通知单状态(PO_NOTICE_STATUS)
    private String specialUseNum; //（废弃）番号
    private String demandClassify; //（废弃）需求分类
    private BigDecimal deliveryOrderQty; //（废弃）已创建送货单数量
    private BigDecimal deliveryQty; //（废弃）已收货数量
    private String affirmStatus; //（废弃）供应商确认状态(PO_AFFIRM_STATUS)
    private String documentType; //（废弃）单据类型(PO_DOC_TYPE)
    private String rejectReason; //（废弃）供应商拒绝原因
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
    private Integer operatorUserId;

	public void setPoNoticeId(Integer poNoticeId) {
		this.poNoticeId = poNoticeId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_NOTICE_S", sequenceName = "SRM_PO_NOTICE_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_NOTICE_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "po_notice_id", nullable = false, length = 11)	
	public Integer getPoNoticeId() {
		return poNoticeId;
	}

	public void setPoNoticeCode(String poNoticeCode) {
		this.poNoticeCode = poNoticeCode;
	}

	@Column(name = "po_notice_code", nullable = false, length = 30)	
	public String getPoNoticeCode() {
		return poNoticeCode;
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

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_id", nullable = false, length = 11)	
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

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "buyer_id", nullable = true, length = 11)	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setPoNoticeStatus(String poNoticeStatus) {
		this.poNoticeStatus = poNoticeStatus;
	}

	@Column(name = "po_notice_status", nullable = true, length = 30)	
	public String getPoNoticeStatus() {
		return poNoticeStatus;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Column(name = "approved_date", nullable = true, length = 0)	
	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	@Column(name = "issued_date", nullable = true, length = 0)	
	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "comments", nullable = true, length = 2000)	
	public String getComments() {
		return comments;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "file_id", nullable = true, length = 11)	
	public Integer getFileId() {
		return fileId;
	}

	public void setPoNoticeVersions(BigDecimal poNoticeVersions) {
		this.poNoticeVersions = poNoticeVersions;
	}

	@Column(name = "po_notice_versions", precision = 4, scale = 1)	
	public BigDecimal getPoNoticeVersions() {
		return poNoticeVersions;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	@Column(name = "inst_id", nullable = true, length = 11)	
	public Integer getInstId() {
		return instId;
	}

	public void setDeliverySiteId(Integer deliverySiteId) {
		this.deliverySiteId = deliverySiteId;
	}

	@Column(name = "delivery_site_id", nullable = true, length = 11)	
	public Integer getDeliverySiteId() {
		return deliverySiteId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setPoStarvingId(Integer poStarvingId) {
		this.poStarvingId = poStarvingId;
	}

	@Column(name = "po_starving_id", nullable = true, length = 11)	
	public Integer getPoStarvingId() {
		return poStarvingId;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	@Column(name = "demand_date", nullable = true, length = 0)	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "quantity", precision = 20, scale = 6)	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	@Column(name = "employee_num", nullable = true, length = 30)	
	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "status", nullable = true, length = 30)	
	public String getStatus() {
		return status;
	}

	public void setSpecialUseNum(String specialUseNum) {
		this.specialUseNum = specialUseNum;
	}

	@Column(name = "special_use_num", nullable = true, length = 240)	
	public String getSpecialUseNum() {
		return specialUseNum;
	}

	public void setDemandClassify(String demandClassify) {
		this.demandClassify = demandClassify;
	}

	@Column(name = "demand_classify", nullable = true, length = 240)	
	public String getDemandClassify() {
		return demandClassify;
	}

	public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
		this.deliveryOrderQty = deliveryOrderQty;
	}

	@Column(name = "delivery_order_qty", precision = 20, scale = 6)	
	public BigDecimal getDeliveryOrderQty() {
		return deliveryOrderQty;
	}

	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	@Column(name = "delivery_qty", precision = 20, scale = 6)	
	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}

	public void setAffirmStatus(String affirmStatus) {
		this.affirmStatus = affirmStatus;
	}

	@Column(name = "affirm_status", nullable = true, length = 30)	
	public String getAffirmStatus() {
		return affirmStatus;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Column(name = "document_type", nullable = true, length = 30)	
	public String getDocumentType() {
		return documentType;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Column(name = "reject_reason", nullable = true, length = 240)	
	public String getRejectReason() {
		return rejectReason;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
