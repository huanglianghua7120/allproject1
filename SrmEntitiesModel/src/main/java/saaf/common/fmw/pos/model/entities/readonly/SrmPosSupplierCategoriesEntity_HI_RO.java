package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosSupplierCategoriesEntity_HI_RO Entity Object
 * Fri Mar 09 10:27:49 CST 2018  Auto Generate
 */

public class SrmPosSupplierCategoriesEntity_HI_RO {

	public static String QUERY_CATEGORIES =
			"SELECT t.supplier_category_id  AS supplierCategoryId\n" +
			"      ,t.supplier_id           AS supplierId\n" +
			"      ,t.category_id           AS categoryId\n" +
			"      ,t.cooperative_product   AS cooperativeProduct\n" +
			"      ,t.management_brand      AS managementBrand\n" +
			"      ,t.supply_cycle          AS supplyCycle\n" +
			"      ,t.status                AS status\n" +
			"      ,t.approved_date         AS approvedDate\n" +
			"      ,t.purchase_limit        AS purchaseLimit\n" +
			"      ,t.supply_order          AS supplyOrder\n" +
			"      ,t.quota_rate            AS quotaRate\n" +
			"      ,t.invalid_date          AS invalidDate\n" +
			"      ,t.source_code           AS sourceCode\n" +
			"      ,t.source_id             AS sourceId\n" +
			"      ,t.version_num           AS versionNum\n" +
			"      ,t.creation_date         AS creationDate\n" +
			"      ,t.created_by            AS createdBy\n" +
			"      ,t.last_updated_by       AS lastUpdatedBy\n" +
			"      ,t.last_update_date      AS lastUpdateDate\n" +
			"      ,t.last_update_login     AS lastUpdateLogin\n" +
			"      ,t.attribute_category    AS attributeCategory\n" +
			"      ,t.attribute1            AS attribute1\n" +
			"      ,t.attribute2            AS attribute2\n" +
			"      ,t.attribute3            AS attribute3\n" +
			"      ,t.attribute4            AS attribute4\n" +
			"      ,t.attribute5            AS attribute5\n" +
			"      ,t.attribute6            AS attribute6\n" +
			"      ,t.attribute7            AS attribute7\n" +
			"      ,t.attribute8            AS attribute8\n" +
			"      ,t.attribute9            AS attribute9\n" +
			"      ,t.attribute10           AS attribute10\n" +
			"      ,slv.meaning             AS statusName\n" +
			"      ,sbcd.full_category_code AS fullCategoryCode\n" +
			"      ,sbcd.full_category_name AS fullCategoryName\n" +
			"  FROM srm_pos_supplier_categories t\n" +
			"  LEFT JOIN saaf_lookup_values slv\n" +
			"    ON slv.lookup_type = 'POS_CATEGORY_STATUS'\n" +
			"   AND slv.lookup_code = t.status\n" +
			"  LEFT JOIN srm_base_categories sbcd\n" +
			"    ON sbcd.category_id = t.category_id\n" +
			" WHERE 1 = 1";
	
	//查询冻结品类-新增
	public static String QUERY_CATEGORIES_SQL = "SELECT DISTINCT\r\n"+
			"b.supplier_id supplierId,\r\n"+
			"b.status status,\r\n"+
			"b.supplier_category_id supplierCategoryId,\r\n"+
			"a.category_id categoryId,\r\n"+
			"a.category_code categoryCode,\r\n"+
			"a.category_name categoryName,\r\n"+
//			"c.selected_flag selectedFlag,\r\n"+
			//"c.frozen_category_id frozenCategoryId,\r\n"+
			"(case b.status\r\n"+
			"WHEN 'DISABLED' THEN '失效'\r\n"+
			"WHEN 'EFFECTIVE' THEN '合格'\r\n"+
			"WHEN 'INTRODUCING' THEN '合格申请中'\r\n"+
			"WHEN 'NEW' THEN ''END) statusStr \r\n"+
			"FROM srm_pos_supplier_categories b\r\n"+
			",srm_pos_frozen_categories c\r\n"+
			//"LEFT JOIN srm_pos_frozen_categories c ON b.supplier_category_id = c.category_id,\r\n"+
//			"LEFT JOIN srm_pos_frozen_categories c ON b.supplier_category_id = c.supplier_category_id and b.category_id=c.category_id\r\n"+
			"\t,srm_base_categories a\r\n"+
//			"\t,srm_pos_frozen_categories c\r\n"+
			"WHERE a.category_id = b.category_id \r\n";
//			"AND b.supplier_category_id = c.supplier_category_id \r\n";



	//查询冻结品类-新增1
	public static String QUERY_CATEGORIES_SQL1 = "SELECT DISTINCT\r\n"+
			"b.supplier_id supplierId,\r\n"+
			"b.status status,\r\n"+
			"b.supplier_category_id supplierCategoryId,\r\n"+
			"a.category_id categoryId,\r\n"+
			"a.category_code categoryCode,\r\n"+
			"a.category_name categoryName,\r\n"+
			"\tc.selected_flag selectedFlag,\r\n"+
//			"c.selected_flag selectedFlag,\r\n"+
			//"c.frozen_category_id frozenCategoryId,\r\n"+
			"(case b.status\r\n"+
			"WHEN 'DISABLED' THEN '失效'\r\n"+
			"WHEN 'EFFECTIVE' THEN '合格'\r\n"+
			"WHEN 'INTRODUCING' THEN '合格申请中'\r\n"+
			"WHEN 'NEW' THEN ''END) statusStr \r\n"+
			"FROM srm_pos_supplier_categories b\r\n"+
			",srm_pos_frozen_categories c\r\n"+
			//"LEFT JOIN srm_pos_frozen_categories c ON b.supplier_category_id = c.category_id,\r\n"+
//			"LEFT JOIN srm_pos_frozen_categories c ON b.supplier_category_id = c.supplier_category_id and b.category_id=c.category_id\r\n"+
			"\t,srm_base_categories a\r\n"+
//			"\t,srm_pos_frozen_categories c\r\n"+
			"WHERE a.category_id = b.category_id \r\n";
//			"AND b.supplier_category_id = c.supplier_category_id \r\n";


	//查询冻结品类-修改
		public static String QUERY_CATEGORIES_UPDATE_SQL = "\tSELECT DISTINCT\r\n"+
				"\tb.supplier_id supplierId,\r\n"+
				"\tb.status status,\r\n"+
				"\tb.supplier_category_id supplierCategoryId,\r\n"+
				"\ta.category_id categoryId,\r\n"+
				"\ta.category_code categoryCode,\r\n"+
				"\ta.category_name categoryName,\r\n"+
				"\tc.selected_flag selectedFlag,\r\n"+
				"\tc.frozen_category_id frozenCategoryId,\r\n"+
				"\t(case b.status\r\n"+
				"\tWHEN 'DISABLED' THEN '失效'\r\n"+
				"\tWHEN 'EFFECTIVE' THEN '合格'\r\n"+
				"\tWHEN 'INTRODUCING' THEN '合格申请中'\r\n"+
				"\tWHEN 'NEW' THEN ''END) statusStr \r\n"+
				"\tFROM srm_pos_supplier_categories b\r\n";
				//"LEFT JOIN srm_pos_frozen_categories c ON b.supplier_category_id = c.supplier_category_id and b.category_id=c.category_id\r\n"+
				//"\t,srm_base_categories a\r\n"+
				//"WHERE a.category_id = b.category_id \r\n";
	

	//查询供应商有效的品类
	public static String QUERY_CATEGORIES_BY_EFF =
					"SELECT psc.supplier_category_id AS supplierCategoryId\n" +
					"      ,psc.supplier_id          AS supplierId\n" +
					"      ,psc.category_id          AS categoryId\n" +
					"      ,psc.status\n" +
					"      ,slv.meaning              AS statusDisp\n" +
					"      ,sbcd.full_category_code  AS fullCategoryCode\n" +
					"      ,sbcd.full_category_name  AS fullCategoryName\n" +
					"  FROM srm_pos_supplier_categories psc\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'POS_CATEGORY_STATUS'\n" +
					"   AND slv.lookup_code = psc.status\n" +
					"  LEFT JOIN srm_base_categories sbcd\n" +
					"    ON sbcd.category_id = psc.category_id\n" +
					" WHERE 1 = 1\n" +
					"   AND psc.status = 'EFFECTIVE'\n";

	private Integer supplierCategoryId; //产品和服务ID
	private Integer supplierId; //供应商ID，关联表:srm_pos_supplier_info
	private Integer categoryId; //采购分类ID,关联表：srm_base_categories
	private String bigCategoryCode; //(备用)大类编码
	private String middleCategoryCode; //(备用)中类编码
	private String smallCategoryCode; //(备用)小类编码
	private String cooperativeProduct; //合作产品
	private String managementBrand; //经营品牌
	private Integer supplyCycle; //产品供货周期
	private String status; //状态(POS_CATEGORY_STATUS)
	@JSONField(format = "yyyy-MM-dd")
	private Date approvedDate; //批准日期
	private Integer purchaseLimit; //采购限量
	private Integer supplyOrder; //供货顺序
	private Integer quotaRate; //配额比例
	@JSONField(format = "yyyy-MM-dd")
	private Date invalidDate; //失效日期
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
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private Integer operatorUserId;
	private Integer frozenCategoryId;

    //别名字段
	public String statusName;
	public String bigCategoryName;
	private String categoryCode; //采购分类编号
	private String categoryName; //采购分类名称
	private String selectedFlag;
	private String statusStr;
	private String fullCategoryCode;
	private String fullCategoryName;
	private String statusDisp;

	public Integer getFrozenCategoryId() {
		return frozenCategoryId;
	}

	public void setFrozenCategoryId(Integer frozenCategoryId) {
		this.frozenCategoryId = frozenCategoryId;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getBigCategoryName() {
		return bigCategoryName;
	}

	public void setBigCategoryName(String bigCategoryName) {
		this.bigCategoryName = bigCategoryName;
	}

	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
	}

	
	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setBigCategoryCode(String bigCategoryCode) {
		this.bigCategoryCode = bigCategoryCode;
	}

	
	public String getBigCategoryCode() {
		return bigCategoryCode;
	}

	public void setMiddleCategoryCode(String middleCategoryCode) {
		this.middleCategoryCode = middleCategoryCode;
	}

	
	public String getMiddleCategoryCode() {
		return middleCategoryCode;
	}

	public void setSmallCategoryCode(String smallCategoryCode) {
		this.smallCategoryCode = smallCategoryCode;
	}

	
	public String getSmallCategoryCode() {
		return smallCategoryCode;
	}

	public void setCooperativeProduct(String cooperativeProduct) {
		this.cooperativeProduct = cooperativeProduct;
	}

	
	public String getCooperativeProduct() {
		return cooperativeProduct;
	}

	public void setManagementBrand(String managementBrand) {
		this.managementBrand = managementBrand;
	}

	
	public String getManagementBrand() {
		return managementBrand;
	}

	public void setSupplyCycle(Integer supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

	
	public Integer getSupplyCycle() {
		return supplyCycle;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	
	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setPurchaseLimit(Integer purchaseLimit) {
		this.purchaseLimit = purchaseLimit;
	}

	
	public Integer getPurchaseLimit() {
		return purchaseLimit;
	}

	public void setSupplyOrder(Integer supplyOrder) {
		this.supplyOrder = supplyOrder;
	}

	
	public Integer getSupplyOrder() {
		return supplyOrder;
	}

	public void setQuotaRate(Integer quotaRate) {
		this.quotaRate = quotaRate;
	}

	
	public Integer getQuotaRate() {
		return quotaRate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	
	public Date getInvalidDate() {
		return invalidDate;
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

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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

	public String getStatusDisp() {
		return statusDisp;
	}

	public void setStatusDisp(String statusDisp) {
		this.statusDisp = statusDisp;
	}
}
