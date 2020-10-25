package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SrmPoForecastInfoEntity_HI_RO Entity Object
 * Mon Apr 09 11:34:02 CST 2018  Auto Generate
 */

public class SrmPoForecastInfoEntity_HI_RO {

    public static final String QUERY_FORECAST_ORG_SQL =
					"SELECT fi.org_id\n" +
					"      ,fi.organization_id\n" +
					"      ,(SELECT org.inst_name\n" +
					"        FROM   saaf_institution org\n" +
					"        WHERE  fi.org_id = org.inst_id) orgName\n" +
					"      ,(SELECT organization.inst_name\n" +
					"        FROM   saaf_institution organization\n" +
					"        WHERE  fi.organization_id = organization.inst_id) organizationName\n" +
					"FROM   srm_po_forecast_info  fi\n" +
					"      ,srm_po_forecast_lines fl\n" +
					"WHERE  fi.forecast_id = fl.forecast_id\n";

	public static String QUERY_FORECAST_SQL =
					"SELECT fl.forecast_line_id forecastLineId\n" +
					"      ,fi.forecast_id forecastId\n" +
					"      ,bc.full_category_name fullCategoryName\n" +
					"      ,bc.full_category_code fullCategoryCode\n" +
					"      ,bi.item_name itemName\n" +
					"      ,bi.item_code itemCode\n" +
					"      ,si.supplier_id supplierId\n" +
					"      ,si.blacklist_flag blacklistFlag\n" +
					"      ,si.supplier_name supplierName\n" +
					"      ,fl.demand_quantity demandQuantity\n" +
					"      ,lv.meaning uomCodeDesc\n" +
					"      ,fl.demand_date demandDate\n" +
					"      ,emp.employee_name employeeName\n" +
					"      ,fi.forecast_status forecastStatus\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.forecast_status\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_STATUS') forecastStatusDesc\n" +
					"      ,fi.organization_id organizationId\n" +
					"      ,fi.org_id orgId\n" +
					"      ,(SELECT org.inst_name\n" +
					"        FROM   saaf_institution org\n" +
					"        WHERE  fi.org_id = org.inst_id) orgName\n" +
					"      ,(SELECT organization.inst_name\n" +
					"        FROM   saaf_institution organization\n" +
					"        WHERE  fi.organization_id = organization.inst_id) organizationName\n" +
					"      ,fi.forecast_name forecastName\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.prediction_type\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_TYPE') predictionTypeDesc\n" +
					"      ,fl.release_date releaseDate\n" +
					"      ,fl.invalid_date invalidDate\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.created_by = emp.user_id) createdUserName\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.invalid_by = emp.user_id) invalidUserName\n" +
					"      ,fi.creation_date creationDate\n" +
					"      ,fi.prediction_type predictionType\n" +
					"FROM   srm_po_forecast_info fi\n" +
					"LEFT   JOIN srm_po_forecast_lines fl\n" +
					"ON     fi.forecast_id = fl.forecast_id\n" +
					"LEFT   JOIN srm_base_items bi\n" +
					"ON     fl.item_id = bi.item_id\n" +
					"AND    bi.organization_id = fi.organization_id\n" +
					"LEFT   JOIN saaf_lookup_values lv\n" +
					"ON     bi.uom_code = lv.lookup_code\n" +
					"AND    lv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     bi.category_id = bc.category_id\n" +
					"LEFT   JOIN srm_pos_supplier_info si\n" +
					"ON     fl.supplier_id = si.supplier_id\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     fl.buyer_id = emp.employee_id\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_FORECAST_HEADER_SQL =
					"SELECT fi.forecast_id forecastId\n" +
					"      ,fi.forecast_status forecastStatus\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.forecast_status\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_STATUS') forecaStstatusDesc\n" +
					"      ,fi.organization_id organizationId\n" +
					"      ,fi.org_id orgId\n" +
					"      ,(SELECT org.inst_name\n" +
					"        FROM   saaf_institution org\n" +
					"        WHERE  fi.org_id = org.inst_id) orgName\n" +
					"      ,(SELECT organization.inst_name\n" +
					"        FROM   saaf_institution organization\n" +
					"        WHERE  fi.organization_id = organization.inst_id) organizationName\n" +
					"      ,fi.forecast_name forecastName\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.prediction_type\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_TYPE') predictionTypeDesc\n" +
					"      ,fi.creation_date creationDate\n" +
					"      ,fi.prediction_type predictionType\n" +
					"      ,fi.invalid_date invalidDate\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.created_by = emp.user_id) createdUserName\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.invalid_by = emp.user_id) invalidUserName\n" +
					"FROM   srm_po_forecast_info fi\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_FORECAST_LINE_SQL =
					"SELECT fl.forecast_line_id forecastLineId\n" +
					"      ,fi.forecast_id forecastId\n" +
					"      ,bc.full_category_name fullCategoryName\n" +
					"      ,bc.full_category_code fullCategoryCode\n" +
					"      ,bi.item_name itemName\n" +
					"      ,bi.item_code itemCode\n" +
					"      ,si.supplier_id supplierId\n" +
					"      ,si.blacklist_flag blacklistFlag\n" +
					"      ,si.supplier_name supplierName\n" +
					"      ,fl.demand_quantity demandQuantity\n" +
					"      ,lv.meaning uomCodeDesc\n" +
					"      ,fl.demand_date demandDate\n" +
					"      ,emp.employee_name employeeName\n" +
					"      ,fi.forecast_status forecaStstatus\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.forecast_status\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_STATUS') forecaStstatusDesc\n" +
					"      ,fi.organization_id organizationId\n" +
					"      ,fi.org_id orgId\n" +
					"      ,(SELECT org.inst_name\n" +
					"        FROM   saaf_institution org\n" +
					"        WHERE  fi.org_id = org.inst_id) orgName\n" +
					"      ,(SELECT organization.inst_name\n" +
					"        FROM   saaf_institution organization\n" +
					"        WHERE  fi.organization_id = organization.inst_id) organizationName\n" +
					"      ,fi.forecast_name forecastName\n" +
					"      ,(SELECT lv.meaning\n" +
					"        FROM   saaf_lookup_values lv\n" +
					"        WHERE  lv.lookup_code = fi.prediction_type\n" +
					"        AND    lv.lookup_type = 'ISP_FORECAST_TYPE') predictionTypeDesc\n" +
					"      ,fl.release_date releaseDate\n" +
					"      ,fl.invalid_date invalidDate\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.created_by = emp.user_id) createdUserName\n" +
					"      ,(SELECT emp.employee_name\n" +
					"        FROM   saaf_employees emp\n" +
					"        WHERE  fi.invalid_by = emp.user_id) invalidUserName\n" +
					"      ,fi.creation_date creationDate\n" +
					"      ,fi.prediction_type predictionType\n" +
					"FROM   srm_po_forecast_lines fl\n" +
					"LEFT   JOIN srm_po_forecast_info fi\n" +
					"ON     fi.forecast_id = fl.forecast_id\n" +
					"LEFT   JOIN srm_base_items bi\n" +
					"ON     fl.item_id = bi.item_id\n" +
					"AND    bi.organization_id = fi.organization_id\n" +
					"LEFT   JOIN saaf_lookup_values lv\n" +
					"ON     bi.uom_code = lv.lookup_code\n" +
					"AND    lv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     bi.category_id = bc.category_id\n" +
					"LEFT   JOIN srm_pos_supplier_info si\n" +
					"ON     fl.supplier_id = si.supplier_id\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     fl.buyer_id = emp.employee_id\n" +
					"WHERE  1 = 1\n";

	private Integer forecastLineId;
	private String fullCategoryName;
	private String fullCategoryCode;
	private String itemName;
	private String supplierName;
	private String blacklistFlag;
	private Integer supplierId;
	private BigDecimal demandQuantity; //需求数量
	private String uomCodeDesc;
	@JSONField(format = "yyyy-MM-dd")
	private Date demandDate;
	private String employeeName;
	private String orgName;
	private String organizationName;
	private String predictionTypeDesc;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDateTo;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDateFrom;
	private String ts;
	private List<SrmPoForecastInfoEntity_HI_RO> lineList;
	public String getTs() {
		return "Y";
	}





    private Integer forecastId; //预测ID
    private String forecastName; //预测名称
    private Integer orgId; //业务实体ID
    private Integer organizationId; //库存组织ID
    private String predictionType; //预测类型
    private String forecastStatus; //预测状态
    @JSONField(format = "yyyy-MM-dd")
    private Date invalidDate; //失效日期
    private Integer invalidBy; //失效人
    @JSONField(format = "yyyy-MM-dd")
    private Date releaseDate; //（废弃）发布日期
    private String categoryName; //（废弃）类别名称
    private String categoryCode; //（废弃）类别编码
    @JSONField(format = "yyyy-MM-dd")
    private Date forecastDate; //（废弃）预测日期
    private String itemCode; //（废弃）物料编码
    private String itemDescription; //（废弃）物料名称
    private Integer employeeId; //（废弃）采购员
    private BigDecimal needQuantity; //（废弃）需求数量
    @JSONField(format = "yyyy-MM-dd")
    private Date needByDate; //（废弃）需求日期
    private String vendnameGroup; //（废弃）供应商组合
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
    private String invalidUserName;	//失效人名称
	private String forecastStatusDesc; //预测状态描述
	private String createdUserName; //创建人名称

	public List<SrmPoForecastInfoEntity_HI_RO> getLineList() {
		return lineList;
	}

	public void setLineList(List<SrmPoForecastInfoEntity_HI_RO> lineList) {
		this.lineList = lineList;
	}

	public void setForecastId(Integer forecastId) {
		this.forecastId = forecastId;
	}

	
	public Integer getForecastId() {
		return forecastId;
	}

	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}

	
	public String getForecastName() {
		return forecastName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setPredictionType(String predictionType) {
		this.predictionType = predictionType;
	}

	
	public String getPredictionType() {
		return predictionType;
	}

	public void setForecastStatus(String forecastStatus) {
		this.forecastStatus = forecastStatus;
	}

	
	public String getForecastStatus() {
		return forecastStatus;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidBy(Integer invalidBy) {
		this.invalidBy = invalidBy;
	}

	
	public Integer getInvalidBy() {
		return invalidBy;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	
	public Date getForecastDate() {
		return forecastDate;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}

	
	public BigDecimal getNeedQuantity() {
		return needQuantity;
	}

	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}

	
	public Date getNeedByDate() {
		return needByDate;
	}

	public void setVendnameGroup(String vendnameGroup) {
		this.vendnameGroup = vendnameGroup;
	}

	
	public String getVendnameGroup() {
		return vendnameGroup;
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

	public String getFullCategoryName() {
		return fullCategoryName;
	}

	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}

	public String getFullCategoryCode() {
		return fullCategoryCode;
	}

	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getForecastStatusDesc() {
		return forecastStatusDesc;
	}

	public void setForecastStatusDesc(String forecastStatusDesc) {
		this.forecastStatusDesc = forecastStatusDesc;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPredictionTypeDesc() {
		return predictionTypeDesc;
	}

	public void setPredictionTypeDesc(String predictionTypeDesc) {
		this.predictionTypeDesc = predictionTypeDesc;
	}

	public BigDecimal getDemandQuantity() {
		return demandQuantity;
	}

	public void setDemandQuantity(BigDecimal demandQuantity) {
		this.demandQuantity = demandQuantity;
	}

    public Date getDemandDateTo() {
        return demandDateTo;
    }

    public void setDemandDateTo(Date demandDateTo) {
        this.demandDateTo = demandDateTo;
    }

    public Date getDemandDateFrom() {
        return demandDateFrom;
    }

    public void setDemandDateFrom(Date demandDateFrom) {
        this.demandDateFrom = demandDateFrom;
    }

	public String getInvalidUserName() {
		return invalidUserName;
	}

	public void setInvalidUserName(String invalidUserName) {
		this.invalidUserName = invalidUserName;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public Integer getForecastLineId() {
		return forecastLineId;
	}

	public void setForecastLineId(Integer forecastLineId) {
		this.forecastLineId = forecastLineId;
	}

	public String getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(String blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
}
