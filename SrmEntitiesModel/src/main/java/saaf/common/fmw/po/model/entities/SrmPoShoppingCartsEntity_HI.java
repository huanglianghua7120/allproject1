package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoShoppingCartsEntity_HI Entity Object
 * Fri Jun 15 14:17:27 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_shopping_carts")
public class SrmPoShoppingCartsEntity_HI {
    private Integer shoppingCartId; //购物车ID
    private Integer orgId; //业务实体ID
    private Integer agreementLineId; //框架协议行ID，关联表：srm_po_lines
    private BigDecimal demandQty; //需求数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date demandDate; //需求时间
    private Integer receiveOrganizationId; //接收组织ID
    private String createdPoFlag; //创建采购订单标识（Y/N）
    private Integer poLineId; //采购订单行ID，关联表：srm_po_lines
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
    //自定义
	private Integer poHeaderId; //订单头ID
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; //价格有效开始日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; //价格有效结束日期
	private String paymentCondition; //付款条件
	private String settlementWay; //结算方式
	private BigDecimal taxPrice; // 含税单价
	private BigDecimal nonTaxPrice; // 不含税单价
	private Integer itemId;// 物料ID
	private String itemName;// 物料名称
	private Integer categoryId; //采购分类ID，关联表：srm_base_categories
	private Integer supplierId; //供应商ID
	private String currencyCode; //币种(BANK_CURRENCY)
	private String taxRateCode; //税率

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_SHOPPING_CARTS_S", sequenceName = "SRM_PO_SHOPPING_CARTS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_SHOPPING_CARTS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "shopping_cart_id", nullable = false, length = 11)	
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = false, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setAgreementLineId(Integer agreementLineId) {
		this.agreementLineId = agreementLineId;
	}

	@Column(name = "agreement_line_id", nullable = true, length = 11)	
	public Integer getAgreementLineId() {
		return agreementLineId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	@Column(name = "demand_qty", precision = 15, scale = 3)	
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	@Column(name = "demand_date", nullable = true, length = 0)	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setReceiveOrganizationId(Integer receiveOrganizationId) {
		this.receiveOrganizationId = receiveOrganizationId;
	}

	@Column(name = "receive_organization_id", nullable = true, length = 11)	
	public Integer getReceiveOrganizationId() {
		return receiveOrganizationId;
	}

	public void setCreatedPoFlag(String createdPoFlag) {
		this.createdPoFlag = createdPoFlag;
	}

	@Column(name = "created_po_flag", nullable = true, length = 1)	
	public String getCreatedPoFlag() {
		return createdPoFlag;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	@Column(name = "po_line_id", nullable = true, length = 11)	
	public Integer getPoLineId() {
		return poLineId;
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

	@Column(name = "last_updated_by", nullable = true, length = 11)	
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
	@Transient
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Transient
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Transient
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	@Transient
	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}
	@Transient
	public String getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}
	@Transient
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	@Transient
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}
	@Transient
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	@Transient
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Transient
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Transient
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	@Transient
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Transient
	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}
}
