package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPoLinesCombEntity_HI_RO Entity Object
 * Mon Apr 09 20:41:25 CST 2018  Auto Generate
 */

public class SrmPoLinesCombEntity_HI_RO {

	/*public static String QUERY_LINES_COMB_SQL =
			"SELECT Sbc.Expense_Item_Code AS itemCode\n" +
			"      ,Sbc.Expense_Item_Name AS itemName\n" +
			"      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Demand_Qty END) AS demandQty\n" +
			"      ,MAX(CASE WHEN Spl.Status = 'CANCELLED' THEN NULL ELSE Spl.Demand_Date END)  AS demandDate\n" +
			"      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Non_Tax_Total_Price END) AS nonTaxTotalPrice\n" +
			"      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Tax_Total_Price END) AS taxTotalPrice\n" +
			"      ,Listagg(Spl.Po_Line_Id, ',') Within GROUP(ORDER BY Spl.Po_Line_Id) AS poLineIdStr\n" +
            "      ,spl.CONTEXT as context"+
            "      ,spl.PROJECT_CATEGORY as projectCategory"+
            "      ,spl.PROJECT_TYPE as projectType"+
            "      ,spl.TECHNICAL_TRANS_NUMBER as technicalTransNumber"+
            "      ,spl.SUBPROJECT_NUMBER as subprojectNumber"+
            "      ,spl.ACCEPTANCE_PROCESS_NUMBER as acceptanceProcessNumber"+
			"  FROM Srm_Po_Lines        Spl\n" +
			"      ,Srm_Base_Categories Sbc\n" +
			" WHERE Spl.Category_Id = Sbc.Category_Id\n" +
			"   AND Spl.Po_Header_Id = :poHeaderId\n" +
			" GROUP BY Sbc.Expense_Item_Code\n" +
			"         ,Sbc.Expense_Item_Name\n"+
            "         ,Spl.CONTEXT\n"+
            "         ,Spl.PROJECT_CATEGORY\n"+
            "         ,Spl.PROJECT_TYPE\n"+
            "         ,Spl.TECHNICAL_TRANS_NUMBER\n"+
            "         ,Spl.SUBPROJECT_NUMBER\n"+
            "         ,Spl.ACCEPTANCE_PROCESS_NUMBER"
            ;*/

    public static String QUERY_LINES_COMB_SQL =
            "SELECT spl.Expense_Item_Code AS itemCode\n" +
                    "      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Demand_Qty END) AS demandQty\n" +
                    "      ,MAX(CASE WHEN Spl.Status = 'CANCELLED' THEN NULL ELSE Spl.Demand_Date END)  AS demandDate\n" +
                    "      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Non_Tax_Total_Price END) AS nonTaxTotalPrice\n" +
                    "      ,SUM(CASE WHEN Spl.Status = 'CANCELLED' THEN 0 ELSE Spl.Tax_Total_Price END) AS taxTotalPrice\n" +
                    "      ,Listagg(Spl.Po_Line_Id, ',') Within GROUP(ORDER BY Spl.Po_Line_Id) AS poLineIdStr\n" +
                    "      ,spl.CONTEXT as context"+
                    "      ,spl.PROJECT_CATEGORY as projectCategory"+
                    "      ,spl.PROJECT_TYPE as projectType"+
                    "      ,spl.TECHNICAL_TRANS_NUMBER as technicalTransNumber"+
                    "      ,spl.SUBPROJECT_NUMBER as subprojectNumber"+
                    "      ,spl.ACCEPTANCE_PROCESS_NUMBER as acceptanceProcessNumber"+
                    "  FROM Srm_Po_Lines        Spl\n" +
                    " WHERE Spl.Po_Header_Id = :poHeaderId\n" +
                    " GROUP BY Spl.Expense_Item_Code\n" +
                    "         ,Spl.CONTEXT\n"+
                    "         ,Spl.PROJECT_CATEGORY\n"+
                    "         ,Spl.PROJECT_TYPE\n"+
                    "         ,Spl.TECHNICAL_TRANS_NUMBER\n"+
                    "         ,Spl.SUBPROJECT_NUMBER\n"+
                    "         ,Spl.ACCEPTANCE_PROCESS_NUMBER"
            ;

	private Integer poLineCombId; //采购订单合并行ID
	private Integer poHeaderId; //订单头ID
	private Integer lineNumber; //行号
	private Integer itemId; //物料ID，关联表：srm_base_items
	private String itemCode; //物料名称
	private String itemName; //物料名称
	private Integer categoryId; //采购分类ID，关联表：srm_base_categories
	private BigDecimal demandQty; //需求数量
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date demandDate; //需求日期
	private String status; //行状态(PO_LINE_STATUS)
	private String description; //说明
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

	private String erpPoNumber;
	private String taxRateCode;
	private BigDecimal nonTaxTotalPrice;
	private BigDecimal taxTotalPrice;
	private BigDecimal nonTaxActTotalPrice;
	private BigDecimal taxActTotalPrice;
	private String poLineIdStr;

	private String context;
	private String projectCategory;
	private String projectType;
	private String technicalTransNumber;
	private String subprojectNumber;
	private String acceptanceProcessNumber;

	public Integer getPoLineCombId() {
		return poLineCombId;
	}

	public void setPoLineCombId(Integer poLineCombId) {
		this.poLineCombId = poLineCombId;
	}

	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	public String getErpPoNumber() {
		return erpPoNumber;
	}

	public void setErpPoNumber(String erpPoNumber) {
		this.erpPoNumber = erpPoNumber;
	}

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public BigDecimal getNonTaxTotalPrice() {
		return nonTaxTotalPrice;
	}

	public void setNonTaxTotalPrice(BigDecimal nonTaxTotalPrice) {
		this.nonTaxTotalPrice = nonTaxTotalPrice;
	}

	public BigDecimal getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(BigDecimal taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

	public BigDecimal getNonTaxActTotalPrice() {
		return nonTaxActTotalPrice;
	}

	public void setNonTaxActTotalPrice(BigDecimal nonTaxActTotalPrice) {
		this.nonTaxActTotalPrice = nonTaxActTotalPrice;
	}

	public BigDecimal getTaxActTotalPrice() {
		return taxActTotalPrice;
	}

	public void setTaxActTotalPrice(BigDecimal taxActTotalPrice) {
		this.taxActTotalPrice = taxActTotalPrice;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPoLineIdStr() {
		return poLineIdStr;
	}

	public void setPoLineIdStr(String poLineIdStr) {
		this.poLineIdStr = poLineIdStr;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getTechnicalTransNumber() {
		return technicalTransNumber;
	}

	public void setTechnicalTransNumber(String technicalTransNumber) {
		this.technicalTransNumber = technicalTransNumber;
	}

	public String getSubprojectNumber() {
		return subprojectNumber;
	}

	public void setSubprojectNumber(String subprojectNumber) {
		this.subprojectNumber = subprojectNumber;
	}

	public String getAcceptanceProcessNumber() {
		return acceptanceProcessNumber;
	}

	public void setAcceptanceProcessNumber(String acceptanceProcessNumber) {
		this.acceptanceProcessNumber = acceptanceProcessNumber;
	}
}
