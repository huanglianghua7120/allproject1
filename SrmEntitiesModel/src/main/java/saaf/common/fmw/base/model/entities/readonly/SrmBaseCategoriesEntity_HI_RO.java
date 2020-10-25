package saaf.common.fmw.base.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseCategoriesEntity_HI_RO Entity Object
 * Fri Mar 09 09:29:06 CST 2018  Auto Generate
 */

public class SrmBaseCategoriesEntity_HI_RO  implements Serializable {

	public static String GET_INFO = "SELECT sbc.category_id AS categoryId\n" +
			",sbc.category_code  AS categoryCode\n" +
			",sbc.category_name    AS categoryName\n" +
			",sbcp.category_name   AS parentCategoryName\n" +
			",sbc.parent_category_id  AS parentCategoryId\n" +
			"FROM srm_base_categories sbc\n" +
			"LEFT JOIN srm_base_categories sbcp\n" +
			"ON (sbc.parent_category_id = sbcp.category_id)\n" +
			"WHERE 1 = 1  ";


/*	public static String GET_PO_HEADER_SQLS = "SELECT\n"+
			"\tt.CATEGORY_ID AS categoryId,\n"+
			"\tt.CATEGORY_CODE AS categoryCode,\n"+
			"\tt.CATEGORY_NAME AS categoryName,\n"+
			"\tt.full_category_code fullCategoryCode,\n"+
			"\tt.full_category_name fullCategoryName\n"+
			"\tFROM srm_base_categories t\n"+
			" where t.leaf_flag = 'Y'  ";*/
	public static String GET_PO_HEADER_SQLS =
		"SELECT t.Category_Id        AS categoryId\n" +
		"      ,t.Category_Code      AS categoryCode\n" +
		"      ,t.Category_Name      AS categoryName\n" +
		"      ,t.Full_Category_Code fullCategoryCode\n" +
		"      ,t.Full_Category_Name fullCategoryName\n" +
		"  FROM Srm_Base_Categories t\n" +
		" WHERE　t.ENABLED_FLAG='Y' and NOT EXISTS (SELECT 1\n" +
		"          FROM Srm_Base_Categories Sbc\n" +
		"         WHERE Sbc.Parent_Category_Id = t.Category_Id)\n";

	public static String GET_PO_HEADER_SQLS1 = "SELECT\n"+
			"\tt.CATEGORY_ID AS categoryId1,\n"+
			"\tt.CATEGORY_CODE AS categoryCode1,\n"+
			"\tt.CATEGORY_NAME AS categoryName1\n"+
			"\tFROM srm_base_categories t\n"+
			" where 1=1  ";

	public static String QUERY_CATEGORIES_SQL = "SELECT\r\n"+
			"b.supplier_id supplierId,\r\n"+
			"b.status statusType,\r\n"+
			"b.supplier_category_id supplierCategoryId,\r\n"+
			"a.category_id categoryId,\r\n"+
			"a.category_code categoryCode,\r\n"+
			"a.category_name categoryName,\r\n"+
			"c.selected_flag selectedFlag,\r\n"+
			"c.frozen_category_id frozenCategoryId,\r\n"+
			"(case b.status\r\n"+
			"WHEN 'DISABLED' THEN '失效'\r\n"+
			"WHEN 'EFFECTIVE' THEN '合格'\r\n"+
			"WHEN 'INTRODUCING' THEN '合格申请中'\r\n"+
			"WHEN 'NEW' THEN ''END) statusStr \r\n"+
			"FROM srm_base_categories a\r\n"+
			"LEFT JOIN srm_pos_frozen_categories c ON a.category_id = c.category_id,\r\n"+
			"srm_pos_supplier_categories b\r\n"+
			"WHERE a.category_id = b.supplier_category_id \r\n";

	public static String QUERY_FINDPROCUREMENTMAINTENANCELIST_SQL = "SELECT\n"+
			"\tt.category_id categoryId,\n"+
			"\tt.category_code categoryCode,\n"+
			"\tt.category_name categoryName,\n"+
			"\tt.file_id fileId,\n"+

			"\tpare.full_category_code pareFullCategoryCode,\n"+
			"\tpare.full_category_name pareFullCategoryName,\n"+

			"\tsbc.category_id sonCategoryId,\n"+
			"\tsbc.full_category_code sonFullCategoryCode,\n"+
			"\tsbc.full_category_name sonFullCategoryName,\n"+
			"\tt.full_category_code fullCategoryCode,\n"+
			"\tt.full_category_name fullCategoryName,\n"+
			"\tt.expense_item_code expenseItemCode,\n"+
			"\tt.expense_item_name expenseItemName,\n"+
			/*"\tsbc3.full_category_code sonFullCategoryCode3,\n"+
			"\tsbc3.full_category_name sonFullCategoryName3,\n"+

			"\tsbc4.full_category_code sonFullCategoryCode4,\n"+
			"\tsbc4.full_category_name sonFullCategoryName4,\n"+*/


			"\tt.enabled_flag enabledFlag,\n"+
			/*"\tCASE\n"+
			"\tWHEN t.enabled_flag = 'Y' THEN\n"+
			"\t'生效'\n"+
			"\tELSE\n"+
			"\t'失效'\n"+
			"\tend enabledFlagDesc,\n"+


			"\tsbc.enabled_flag enabledFlagSon,\n"+
			"\tCASE\n"+
			"\tWHEN sbc.enabled_flag = 'Y' THEN\n"+
			"\t'生效'\n"+
			"\tELSE\n"+
			"\t'失效'\n"+
			"\tend enabledFlagDescSon,\n"+*/


///////////////////////////

			"\tCASE\n"+
			"\tWHEN sbc.enabled_flag is not null and sbc.enabled_flag = 'Y' THEN\n"+
			"\t'生效'\n"+
			"\tWHEN sbc.enabled_flag is not null and sbc.enabled_flag = 'N' THEN\n"+
			"\t'失效'\n"+
			"\tWHEN sbc.enabled_flag is null and t.enabled_flag = 'Y' THEN\n"+
			"\t'生效'\n"+
			"\tELSE\n"+
			"\t'失效'\n"+
			"\tend enabledFlagDesc,\n"+



			"\tCASE\n"+
			"\tWHEN sbc.invalid_date is null THEN\n"+
			"\tt.invalid_date\n"+
			"\tELSE\n"+
			"\tsbc.invalid_date\n"+
			"\tend invalidDate,\n"+


			"\tCASE\n"+
			"\tWHEN emp3.employee_name is null THEN\n"+
			"\temp.employee_name\n"+
			"\tELSE\n"+
			"\temp3.employee_name\n"+
			"\tend createrUserName,\n"+


			"\tCASE\n"+
			"\tWHEN sbc.creation_date is null THEN\n"+
			"\tt.creation_date\n"+
			"\tELSE\n"+
			"\tsbc.creation_date\n"+
			"\tend creationDate,\n"+

			"\tCASE\n"+
			"\tWHEN emp4.employee_name is null THEN\n"+
			"\temp1.employee_name\n"+
			"\tELSE\n"+
			"\temp4.employee_name\n"+
			"\tend lastUpdaterUserName,\n"+

			"\tCASE\n"+
			"\tWHEN sbc.last_update_date is null THEN\n"+
			"\tt.last_update_date\n"+
			"\tELSE\n"+
			"\tsbc.last_update_date\n"+
			"\tend lastUpdateDate,\n"+



			"\tCASE\n"+
			"\tWHEN rf2.access_Path is null THEN\n"+
			"\trf1.access_Path\n"+
			"\tELSE\n"+
			"\trf2.access_Path\n"+
			"\tend accessPath,\n"+

			"\tCASE\n"+
			"\tWHEN rf2.file_Name is null THEN\n"+
			"\trf1.file_Name\n"+
			"\tELSE\n"+
			"\trf2.file_Name\n"+
			"\tend fileName,\n"+


//////////////////////////////////////


//			"\tpare.enabled_flag enabledFlag,\n"+
//			"\tCASE\n"+
//			"\tWHEN pare.enabled_flag = 'Y' THEN\n"+
//			"\t'生效'\n"+
//			"\tWHEN pare.enabled_flag is null THEN\n"+
//			"\t'生效'\n"+
//			"\tELSE\n"+
//			"\t'失效'\n"+
//			"\tend enabledFlagDesc,\n"+



//			"\tsbc.invalid_date invalidDateSon,\n"+
//			"\tsbc.creation_date creationDateSon,\n"+
//			"\tsbc.last_update_date lastUpdateDateSon,\n"+

//			"\trf2.access_Path AS accessPathSon,\n"+
//			"\trf2.file_Name AS fileNameSon,\n"+

//			"\temp3.employee_name createrUserNameSon,\n"+
//			"\temp4.employee_name lastUpdaterUserNameSon,\n"+


//			"\temp.employee_name createrUserName,\n"+
//			"\tt.creation_date creationDate,\n"+
//			"\tt.invalid_date invalidDate,\n"+
			"\tt.category_level categoryLevel\n"+
//			"\temp1.employee_name lastUpdaterUserName,\n"+
//			"\tt.last_update_date lastUpdateDate\n"+
//			"\trf1.access_Path AS accessPath,\n"+
//			"\trf1.file_Name AS fileName\n"+
			"\tFROM\n"+
			"\tsrm_base_categories t\n"+
			"\tLEFT JOIN saaf_employees emp\n"+
			"\tON t.created_by = emp.user_id\n"+
			"\tLEFT JOIN saaf_employees emp1\n"+
			"\tON t.last_updated_by = emp1.user_id\n"+



			"\tLEFT JOIN srm_base_categories sbc\n"+
			"\tON sbc.parent_category_id = t.category_id\n"+


			"\tLEFT JOIN saaf_employees emp3\n"+
			"\tON sbc.created_by = emp3.user_id\n"+
			"\tLEFT JOIN saaf_employees emp4\n"+
			"\tON sbc.last_updated_by = emp4.user_id\n"+


			"\tLEFT JOIN srm_base_categories pare\n"+
			"\tON pare.category_id = t.parent_category_id\n"+
			/*"\tLEFT JOIN srm_base_categories sbc3\n"+
			"\tON sbc3.parent_category_id = sbc.category_id\n"+

			"\tLEFT JOIN srm_base_categories sbc4\n"+
			"\tON sbc4.parent_category_id = sbc3.category_id\n"+*/

			"\tLEFT JOIN saaf_base_result_file rf1 ON rf1.file_id = t.file_id\n"+
			"\tLEFT JOIN saaf_base_result_file rf2 ON rf2.file_id = sbc.file_id\n"+
			"WHERE 1=1";

	//所有有效的采购分类
	public static String GET_ALL_CATEGORY_SQL =
			"SELECT\n" +
					"  sbc.category_id AS categoryId,\n" +
					"  sbc.category_code AS categoryCode,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  sbc.parent_category_id AS parentCategoryId,\n" +
					"  sbc.full_category_code AS fullCategoryCode,\n" +
					"  sbc.full_category_name AS fullCategoryName,\n" +
					"  sbc.category_level AS categoryLevel,\n" +
					"  sbc.leaf_flag AS leafFlag\n" +
					"FROM\n" +
					"  srm_base_categories sbc\n" +
					"WHERE sbc.enabled_flag = 'Y'";



	//采购分类维护
	private String parentFullCategoryCode;//上级编码
	private String parentFullCategoryName;//上级名称
	private String sonFullCategoryCode;//下级编码
	private String sonFullCategoryName;//下级名称
	private String enabledFlagDesc;//状态描述
	private String createrUserName;//创建人
	private String lastUpdaterUserName;//更新人
	private String ts;
	private Integer categoryId; //采购分类ID
	private String categoryCode; //采购分类编号
	private String categoryName; //采购分类名称
	private Integer parentCategoryId; //父采购分类ID
	private String fullCategoryCode; //采购分类全编号
	private String fullCategoryName; //采购分类全名称
	private Integer categoryLevel; //分类层级
	private String leafFlag; //是否叶节点(Y/N)
	private String enabledFlag; //是否有效(Y/N)
	private Integer versionNum;
	private Integer fileId;
	private String accessPath;
	private String fileName; //是否有效(Y/N)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date invalidDate;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDateTo;//更新时间至
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDateFrom;//更新时间从
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
	private Integer supplierId;
	private String statusStr;
	private String selectedFlag;
	private Integer frozenCategoryId;
	private String statusType;
	private Integer supplierCategoryId;

	private String parentCategoryName;


	private String sonFullCategoryCode3;
	private String sonFullCategoryName3;
	private String sonFullCategoryCode4;
	private String sonFullCategoryName4;


	private String pareFullCategoryCode;
	private String pareFullCategoryName;


	private Integer sonCategoryId;


	private String enabledFlagDescSon;

	private String enabledFlagSon;



	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date invalidDateSon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDateSon;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDateSon;

	private String accessPathSon;
	private String fileNameSon;
	private String createrUserNameSon;
	private String lastUpdaterUserNameSon;

	private String expenseItemCode; //费用物料编码
	private String expenseItemName; //费用物料描述

	public Date getInvalidDateSon() {
		return invalidDateSon;
	}

	public void setInvalidDateSon(Date invalidDateSon) {
		this.invalidDateSon = invalidDateSon;
	}

	public Date getCreationDateSon() {
		return creationDateSon;
	}

	public void setCreationDateSon(Date creationDateSon) {
		this.creationDateSon = creationDateSon;
	}

	public Date getLastUpdateDateSon() {
		return lastUpdateDateSon;
	}

	public void setLastUpdateDateSon(Date lastUpdateDateSon) {
		this.lastUpdateDateSon = lastUpdateDateSon;
	}

	public String getAccessPathSon() {
		return accessPathSon;
	}

	public void setAccessPathSon(String accessPathSon) {
		this.accessPathSon = accessPathSon;
	}

	public String getFileNameSon() {
		return fileNameSon;
	}

	public void setFileNameSon(String fileNameSon) {
		this.fileNameSon = fileNameSon;
	}

	public String getCreaterUserNameSon() {
		return createrUserNameSon;
	}

	public void setCreaterUserNameSon(String createrUserNameSon) {
		this.createrUserNameSon = createrUserNameSon;
	}

	public String getLastUpdaterUserNameSon() {
		return lastUpdaterUserNameSon;
	}

	public void setLastUpdaterUserNameSon(String lastUpdaterUserNameSon) {
		this.lastUpdaterUserNameSon = lastUpdaterUserNameSon;
	}

	public String getEnabledFlagSon() {
		return enabledFlagSon;
	}

	public void setEnabledFlagSon(String enabledFlagSon) {
		this.enabledFlagSon = enabledFlagSon;
	}

	public String getEnabledFlagDescSon() {
		return enabledFlagDescSon;
	}

	public void setEnabledFlagDescSon(String enabledFlagDescSon) {
		this.enabledFlagDescSon = enabledFlagDescSon;
	}

	public Integer getSonCategoryId() {
		return sonCategoryId;
	}

	public void setSonCategoryId(Integer sonCategoryId) {
		this.sonCategoryId = sonCategoryId;
	}

	public String getPareFullCategoryCode() {
		return pareFullCategoryCode;
	}

	public void setPareFullCategoryCode(String pareFullCategoryCode) {
		this.pareFullCategoryCode = pareFullCategoryCode;
	}

	public String getPareFullCategoryName() {
		return pareFullCategoryName;
	}

	public void setPareFullCategoryName(String pareFullCategoryName) {
		this.pareFullCategoryName = pareFullCategoryName;
	}

	public String getSonFullCategoryCode3() {
		return sonFullCategoryCode3;
	}

	public void setSonFullCategoryCode3(String sonFullCategoryCode3) {
		this.sonFullCategoryCode3 = sonFullCategoryCode3;
	}

	public String getSonFullCategoryName3() {
		return sonFullCategoryName3;
	}

	public void setSonFullCategoryName3(String sonFullCategoryName3) {
		this.sonFullCategoryName3 = sonFullCategoryName3;
	}

	public String getSonFullCategoryCode4() {
		return sonFullCategoryCode4;
	}

	public void setSonFullCategoryCode4(String sonFullCategoryCode4) {
		this.sonFullCategoryCode4 = sonFullCategoryCode4;
	}

	public String getSonFullCategoryName4() {
		return sonFullCategoryName4;
	}

	public void setSonFullCategoryName4(String sonFullCategoryName4) {
		this.sonFullCategoryName4 = sonFullCategoryName4;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	//查询条件弹出框
	private Integer categoryId1; //采购分类ID
	private String categoryCode1; //采购分类CODE
	private String categoryName1; //采购分类名称

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getCategoryName() {
		return categoryName;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}


	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public Integer getCategoryId1() {
		return categoryId1;
	}

	public void setCategoryId1(Integer categoryId1) {
		this.categoryId1 = categoryId1;
	}

	public String getCategoryCode1() {
		return categoryCode1;
	}

	public void setCategoryCode1(String categoryCode1) {
		this.categoryCode1 = categoryCode1;
	}

	public String getCategoryName1() {
		return categoryName1;
	}

	public void setCategoryName1(String categoryName1) {
		this.categoryName1 = categoryName1;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	public Integer getFrozenCategoryId() {
		return frozenCategoryId;
	}

	public void setFrozenCategoryId(Integer frozenCategoryId) {
		this.frozenCategoryId = frozenCategoryId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Integer getSupplierCategoryId() {
		return supplierCategoryId;
	}

	public void setSupplierCategoryId(Integer supplierCategoryId) {
		this.supplierCategoryId = supplierCategoryId;
	}

	public String getParentFullCategoryCode() {
		return parentFullCategoryCode;
	}

	public void setParentFullCategoryCode(String parentFullCategoryCode) {
		this.parentFullCategoryCode = parentFullCategoryCode;
	}

	public String getParentFullCategoryName() {
		return parentFullCategoryName;
	}

	public void setParentFullCategoryName(String parentFullCategoryName) {
		this.parentFullCategoryName = parentFullCategoryName;
	}

	public String getEnabledFlagDesc() {
		return enabledFlagDesc;
	}

	public void setEnabledFlagDesc(String enabledFlagDesc) {
		this.enabledFlagDesc = enabledFlagDesc;
	}

	public String getCreaterUserName() {
		return createrUserName;
	}

	public void setCreaterUserName(String createrUserName) {
		this.createrUserName = createrUserName;
	}

	public String getLastUpdaterUserName() {
		return lastUpdaterUserName;
	}

	public void setLastUpdaterUserName(String lastUpdaterUserName) {
		this.lastUpdaterUserName = lastUpdaterUserName;
	}
	public String getTs() {
		return "Y";
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Date getLastUpdateDateTo() {
		return lastUpdateDateTo;
	}

	public void setLastUpdateDateTo(Date lastUpdateDateTo) {
		this.lastUpdateDateTo = lastUpdateDateTo;
	}

	public Date getLastUpdateDateFrom() {
		return lastUpdateDateFrom;
	}

	public void setLastUpdateDateFrom(Date lastUpdateDateFrom) {
		this.lastUpdateDateFrom = lastUpdateDateFrom;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSonFullCategoryCode() {
		return sonFullCategoryCode;
	}

	public void setSonFullCategoryCode(String sonFullCategoryCode) {
		this.sonFullCategoryCode = sonFullCategoryCode;
	}

	public String getSonFullCategoryName() {
		return sonFullCategoryName;
	}

	public void setSonFullCategoryName(String sonFullCategoryName) {
		this.sonFullCategoryName = sonFullCategoryName;
	}

	public String getExpenseItemCode() {
		return expenseItemCode;
	}

	public void setExpenseItemCode(String expenseItemCode) {
		this.expenseItemCode = expenseItemCode;
	}

	public String getExpenseItemName() {
		return expenseItemName;
	}

	public void setExpenseItemName(String expenseItemName) {
		this.expenseItemName = expenseItemName;
	}
}
