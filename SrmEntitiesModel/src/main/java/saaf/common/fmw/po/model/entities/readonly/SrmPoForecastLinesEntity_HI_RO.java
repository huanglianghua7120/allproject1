package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPoForecastLinesEntity_HI_RO Entity Object
 * Mon Apr 09 11:34:02 CST 2018  Auto Generate
 */

public class SrmPoForecastLinesEntity_HI_RO implements Serializable {

	public static String QUERY_ITEM_LOV_EXIT =
					"SELECT sbi.item_id              itemId\n" +
					"      ,sbi.item_code            itemCode\n" +
					"      ,sbi.item_name            itemName\n" +
					"      ,sbi.item_status          itemStatus\n" +
					"      ,sbi.min_packing          minPacking\n" +
					"      ,sbi.uom_code             uomCode\n" +
					"      ,sbc.category_code        categoryCode\n" +
					"      ,sbc.category_name        categoryName\n" +
					"      ,sbc.full_category_code   fullCategoryCode\n" +
					"      ,sbc.full_category_name   fullCategoryName\n" +
					"      ,slv1.meaning             uomCodeDesc\n" +
					"      ,sbc.category_id          categoryId\n" +
					"      ,sprl.requisition_line_id requisitionLineId\n" +
					"FROM   srm_base_items sbi\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     sbi.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_po_requisition_lines sprl\n" +
					"ON     sprl.item_id = sbi.item_id, srm_base_categories sbc\n" +
					"WHERE  sbi.category_id = sbc.category_id\n";

	public static String QUERY_ITEM_LOV_MAXLINENUMBER = "SELECT MAX(sprl.line_number) AS maxLineNumber FROM srm_po_requisition_lines sprl WHERE 1 = 1\n";

	public static String QUERY_ITEM_LOV_SQL =
					"SELECT sbi.item_id            itemId\n" +
					"      ,sbi.item_code          itemCode\n" +
					"      ,sbi.item_name          itemName\n" +
					"      ,sbi.item_status        itemStatus\n" +
					"      ,sbi.min_packing        minPacking\n" +
					"      ,sbi.uom_code           uomCode\n" +
					"      ,sbi.specification      specification\n" +
					"      ,sbc.category_code      categoryCode\n" +
					"      ,sbc.category_name      categoryName\n" +
					"      ,sbc.full_category_code fullCategoryCode\n" +
					"      ,sbc.full_category_name fullCategoryName\n" +
					"      ,sbc.expense_item_code  expenseItemCode\n" +
					"      ,slv1.meaning           uomCodeDesc\n" +
					"      ,sbc.category_id        categoryId\n" +
					"      ,emp.employee_name      employeeName\n" +
					"      ,emp.employee_id        employeeId\n" +
					"FROM   srm_base_items sbi\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     emp.employee_id = sbi.agent_id, srm_base_categories sbc,\n" +
					" saaf_lookup_values slv1\n" +
					"WHERE  sbi.category_id = sbc.category_id\n" +
					"AND    sbi.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"AND    sbi.item_status = 'ACT'\n";

	public static String COUNT_ITEM_LOV_SQL =
					"SELECT COUNT(1)\n" +
					"FROM   srm_base_items sbi\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     emp.employee_id = sbi.agent_id, srm_base_categories sbc,\n" +
					" saaf_lookup_values slv1\n" +
					"WHERE  sbi.category_id = sbc.category_id\n" +
					"AND    sbi.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"AND    sbi.item_status = 'ACT'\n";

	public static String QUERY_ITEM_EXIST =
			"SELECT sbi.item_id              itemId\n" +
					"FROM   srm_base_items sbi\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     sbi.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_po_requisition_lines sprl\n" +
					"ON     sprl.item_id = sbi.item_id, srm_base_categories sbc\n" +
					"WHERE  sbi.category_id = sbc.category_id\n";

	private String itemCode;
	private String itemName;
	private String categoryCode;
	private String categoryName;
	private String fullCategoryCode;
	private String fullCategoryName;
	private String uomCodeDesc;
	private String uomCode;
	private Integer employeeId;
	private String employeeName;
	private BigDecimal minPacking; //最小包装量


    private Integer forecastLineId; //预测行ID
    private Integer forecastId; //预测ID
    private Integer itemId; //物料ID，关联表：srm_base_items
    private Integer categoryId; //采购分类ID，关联表：srm_base_categories
    private Integer supplierId; //供应商ID
    private BigDecimal demandQuantity; //需求数量
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDate; //需求日期
    private Integer buyerId; //采购员ID，关联表：saaf_employees
    private String releaseFlag; //发布标识(Y/N)
    @JSONField(format = "yyyy-MM-dd")
    private Date releaseDate; //发布时间
    @JSONField(format = "yyyy-MM-dd")
    private Date invalidDate; //失效时间
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
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
    private Integer operatorUserId;
	private Integer maxLineNumber;
	private String specification;
	private String expenseItemCode;


	public Integer getMaxLineNumber() {
		return maxLineNumber;
	}

	public void setMaxLineNumber(Integer maxLineNumber) {
		this.maxLineNumber = maxLineNumber;
	}

	public void setForecastLineId(Integer forecastLineId) {
		this.forecastLineId = forecastLineId;
	}

	
	public Integer getForecastLineId() {
		return forecastLineId;
	}

	public void setForecastId(Integer forecastId) {
		this.forecastId = forecastId;
	}

	
	public Integer getForecastId() {
		return forecastId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setDemandQuantity(BigDecimal demandQuantity) {
		this.demandQuantity = demandQuantity;
	}

	
	public BigDecimal getDemandQuantity() {
		return demandQuantity;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setReleaseFlag(String releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	
	public String getReleaseFlag() {
		return releaseFlag;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public BigDecimal getMinPacking() {
		return minPacking;
	}

	public void setMinPacking(BigDecimal minPacking) {
		this.minPacking = minPacking;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getExpenseItemCode() {
		return expenseItemCode;
	}

	public void setExpenseItemCode(String expenseItemCode) {
		this.expenseItemCode = expenseItemCode;
	}
}
