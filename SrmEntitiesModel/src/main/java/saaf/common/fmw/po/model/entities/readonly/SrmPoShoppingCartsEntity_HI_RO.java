package saaf.common.fmw.po.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoShoppingCartsEntity_HI_RO Entity Object
 * Fri Jun 15 14:17:27 CST 2018  Auto Generate
 */

public class SrmPoShoppingCartsEntity_HI_RO {
	//购物车查询list（分页）
	public static final String QUERY_SHOPPINGCARTSLIST_SQL="SELECT t.shopping_cart_id AS shoppingCartId\n" +
			",t.org_id AS orgId\n" +
			",si.inst_name AS orgName\n" +
			",t.agreement_line_id AS agreementLineId\n" +
			",t.demand_qty AS demandQty\n" +
			",t.demand_date AS demandDate\n" +
			",t.receive_organization_id AS receiveOrganizationId\n" +
			",si2.inst_name AS receiveToOrganizationName\n" +
			",t.created_po_flag AS createdPoFlag\n" +
			",t.po_line_id AS poLineId\n" +
			",t.version_num AS versionNum\n" +
			",t.creation_date AS creationDate\n" +
			",t.created_by AS createdBy\n" +
			",t.last_updated_by AS lastUpdatedBy\n" +
			",t.last_update_date AS lastUpdateDate\n" +
			",t.last_update_login AS lastUpdateLogin\n" +
			",t.attribute_category AS attributeCategory\n" +
			",t.attribute1 AS attribute1\n" +
			",t.attribute2 AS attribute2\n" +
			",t.attribute3 AS attribute3\n" +
			",t.attribute4 AS attribute4\n" +
			",t.attribute5 AS attribute5\n" +
			",t.attribute6 AS attribute6\n" +
			",t.attribute7 AS attribute7\n" +
			",t.attribute8 AS attribute8\n" +
			",t.attribute9 AS attribute9\n" +
			",t.attribute10 AS attribute10\n" +
			",spl.po_header_id AS poHeaderId\n" +
			",spl.tax_price AS taxPrice\n" +
			",spl.non_tax_price AS nonTaxPrice\n" +
			",spl.start_date AS startDate\n" +
			",spl.end_date AS endDate\n" +
			",spl.category_id AS categoryId\n" +
			",spl.item_id AS itemId\n" +
			",spl.item_name AS itemName\n" +
			",spl.item_spec AS itemSpec\n" +
			",sbi.item_code AS itemCode\n" +
			",sbi.item_description AS itemDescription\n" +
			",sbi.uom_code AS uomCode\n" +
			",slv3.meaning AS uomCodeName\n" +
			",sph.currency_code AS currencyCode\n" +
			",slv.meaning AS currencyCodeName\n" +
			",sph.supplier_id AS supplierId\n" +
			",sps.supplier_name AS supplierName\n" +
			",sph.tax_rate_code AS taxRateCode\n" +
			",slv2.meaning AS taxRateCodeName\n" +
			",sph.payment_condition AS paymentCondition\n" +
			",sph.settlement_way AS settlementWay\n" +
			"FROM srm_po_shopping_carts t\n" +
			"LEFT JOIN srm_po_lines spl ON spl.po_line_id = t.agreement_line_id\n" +
			"LEFT JOIN srm_po_headers sph ON sph.po_header_id = spl.po_header_id\n" +
			"LEFT JOIN srm_base_items sbi ON sbi.item_id = spl.item_id\n" +
			"AND EXISTS(SELECT 1 FROM saaf_institution siv,srm_po_headers sph2\n" +
			"WHERE siv.inst_id = sbi.organization_id\n" +
			"AND siv.inst_type = 'ORGANIZATION'\n" +
			"AND siv.parent_inst_id = sph2.org_id\n" +
			"AND sph2.po_header_id = sph.po_header_id)\n" +
			"LEFT JOIN srm_pos_supplier_info sps ON sps.supplier_id=sph.supplier_id\n" +
			"LEFT JOIN saaf_institution si ON si.inst_id = t.org_id\n" +
			"LEFT JOIN saaf_institution si2 ON si2.inst_id = t.receive_organization_id\n" +
			"LEFT JOIN saaf_lookup_values slv ON slv.lookup_type='BANK_CURRENCY' AND slv.lookup_code=sph.currency_code\n" +
			"LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type='PON_TAX_LIST' AND slv2.lookup_code=sph.tax_rate_code\n" +
			"LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type='BASE_ITEMS_UNIT' AND slv3.lookup_code=sbi.uom_code\n" +
			"WHERE 1=1 ";

    private Integer shoppingCartId; //购物车ID
    private Integer orgId; //业务实体ID
    private Integer agreementLineId; //框架协议行ID，关联表：srm_po_lines
    private BigDecimal demandQty; //需求数量
    @JSONField(format = "yyyy-MM-dd")
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

    //自定义的别名
	private Integer poHeaderId; //订单头ID
	private BigDecimal taxPrice; // 含税单价
	private BigDecimal nonTaxPrice; // 不含税单价
	private String receiveToOrganizationName;// 接收组织name
	private Integer itemId;// 物料ID
	private String itemName;// 物料名称
	private String itemCode;// 物料编码
	private String itemDescription; //物料说明
	private String itemSpec; //物料规格
	private Integer categoryId; //采购分类ID，关联表：srm_base_categories
	private String currencyCode; //币种(BANK_CURRENCY)
	private String currencyCodeName; //币种name(BANK_CURRENCY)
	private String uomCode; //计量单位
	private String uomCodeName;// 计量单位的快码别名
	private String orgName; //业务实体name
	private Integer supplierId; //供应商ID
	private String supplierName; //供应商名称
	private String taxRateCode; //税率
	private String taxRateCodeName; //税率name
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; //价格有效开始日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; //价格有效结束日期
	private String paymentCondition; //付款条件
	private String settlementWay; //结算方式

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
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

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
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

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public String getTaxRateCodeName() {
		return taxRateCodeName;
	}

	public void setTaxRateCodeName(String taxRateCodeName) {
		this.taxRateCodeName = taxRateCodeName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}

	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setAgreementLineId(Integer agreementLineId) {
		this.agreementLineId = agreementLineId;
	}

	
	public Integer getAgreementLineId() {
		return agreementLineId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setReceiveOrganizationId(Integer receiveOrganizationId) {
		this.receiveOrganizationId = receiveOrganizationId;
	}

	
	public Integer getReceiveOrganizationId() {
		return receiveOrganizationId;
	}

	public void setCreatedPoFlag(String createdPoFlag) {
		this.createdPoFlag = createdPoFlag;
	}

	
	public String getCreatedPoFlag() {
		return createdPoFlag;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	
	public Integer getPoLineId() {
		return poLineId;
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
}
