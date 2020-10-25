package saaf.common.fmw.base.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmBaseItemsEntity_HI_RO Entity Object
 * Mon May 07 11:31:20 CST 2018  Auto Generate
 */

public class SrmBaseItemsEntity_HI_RO {

	//查看物料（招标模块）——分页
	public static String QUERY_PON_BASE_ITEM_SQL =
					"SELECT\n" +
					"  t.item_id AS itemId,\n" +
					"  t.item_code AS itemCode,\n" +
					/*"  t.organization_id AS organizationId,\n" +*/
					"  t.item_name AS itemName,\n" +
					"  t.item_description AS itemDescription,\n" +
					"  t.image_id AS imageId,\n" +
					"  t.benchmark_price AS benchmarkPrice,\n" +
					"  t.agent_id AS agentId,\n" +
					"  t.min_packing AS minPacking,\n" +
					"  t.item_status AS itemStatus,\n" +
					"  t.purchasing_flag AS purchasingFlag,\n" +
					"  t.uom_code AS uomCode,\n" +
					"  t.global_flag AS globalFlag,\n" +
					"  t.purchase_cycle AS purchaseCycle,\n" +
					"  t.purchasing_lead_time AS purchasingLeadTime,\n" +
					"  t.category_id AS categoryId,\n" +
					"  t.invalid_date AS invalidDate,\n" +
					"  t.source_code AS sourceCode,\n" +
					"  t.source_id AS sourceId,\n" +
					"  t.version_num AS versionNum,\n" +
					"  t.creation_date AS creationDate,\n" +
					"  t.created_by AS createdBy,\n" +
					"  t.last_updated_by AS lastUpdatedBy,\n" +
					"  t.last_update_date AS lastUpdateDate,\n" +
					"  t.last_update_login AS lastUpdateLogin,\n" +
					"  t.cost AS cost,\n" +
					"  t.specification  AS specification,\n" +
					"  t.region AS region,\n" +
					"  t.attribute_category AS attributeCategory,\n" +
					"  t.attribute1 AS attribute1,\n" +
					"  t.attribute2 AS attribute2,\n" +
					"  t.attribute3 AS attribute3,\n" +
					"  t.attribute4 AS attribute4,\n" +
					"  t.attribute5 AS attribute5,\n" +
					"  t.attribute6 AS attribute6,\n" +
					"  t.attribute7 AS attribute7,\n" +
					"  t.attribute8 AS attribute8,\n" +
					"  t.attribute9 AS attribute9,\n" +
					"  t.attribute10 AS attribute10,\n" +
					"  sbc.category_code AS categoryCode,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  sbc.full_category_code AS fullCategoryCode,\n" +
					"  sbc.full_category_name AS fullCategoryName,\n" +
					"  slv.meaning AS itemStatusName,\n" +
					"  slv2.meaning AS uomCodeName\n" +
					"FROM\n" +
					"  srm_base_items_b t\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = t.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"    AND slv.lookup_code = t.item_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv2.lookup_code = t.uom_code\n" +
					"WHERE 1 = 1";


/*	注释于20190531，qinxiaozhao，没有使用到
    public static String QUERY_ITEM_SQL = "SELECT   \r\n" +
			"bi.ITEM_ID itemId  \r\n" +
			",bi.ITEM_CODE itemCode  \r\n" +
			",bi.ITEM_NAME itemName  \r\n" +
			",bi.PURCHASING_FLAG purchasingFlag\r\n" +
			",bi.CATEGORY_CODE categoryCode \r\n" +
			",bc.CATEGORY_NAME categoryName \r\n" +
			",bi.PURCHASING_LEAD_TIME purchasingLeadTime  \r\n" +
			",bi.LAST_UPDATE_DATE lastUpdateDate  \r\n" +
			",se.employee_name employeeName  \r\n" +
			"FROM   \r\n" +
			"srm_base_items bi  \r\n" +
			"LEFT JOIN saaf_employees se ON bi.AGENT_id = se.employee_id\r\n" +
			"LEFT JOIN srm_base_categories bc on bi.category_code= bc.category_code\r\n" +
			"LEFT JOIN saaf_lookup_values slv on slv.lookup_type = 'DELIVERY_TYPE'\r\n" +
			"AND slv.lookup_code = bi.delivery_type  \r\n" +
			"WHERE 1=1";*/

/*	注释于20190531，qinxiaozhao，没有使用到
    public static String QUERY_ITEM_COUNT = "SELECT\r\n" +
			"count(1)\r\n" +
			"FROM\r\n" +
			"srm_base_items bi  \r\n" +
			"LEFT JOIN saaf_employees se ON bi.AGENT_id = se.employee_id\r\n" +
			"LEFT JOIN srm_base_categories bc on bi.category_code= bc.category_code\r\n" +
			"LEFT JOIN saaf_lookup_values slv on slv.lookup_type = 'DELIVERY_TYPE'\r\n" +
			"AND slv.lookup_code = bi.DELIVERY_TYPE  \r\n" +
			"WHERE 1=1";*/

	public static String QUERY_SRM_BASE_ITME_SQL =
					"SELECT\n" +
					"  sbi.item_id AS itemId,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbi.organization_id AS organizationId,\n" +
					"  ins.inst_code AS instCode,\n" +
					"  ins.inst_name AS instName,\n" +
					"  sbi.item_name AS itemName,\n" +
					"  ins2.inst_name AS parentInstName,\n" +
					"  sbi.item_description AS itemDescription,\n" +
					"  sbi.image_id AS imageId,\n" +
					"  sbi.benchmark_price AS benchmarkPrice,\n" +
					"  sbi.agent_id AS agentId,\n" +
					"  sbi.min_packing AS minPacking,\n" +
					"  sbi.item_status AS itemStatus,\n" +
					"  sbi.purchasing_flag AS purchasingFlag,\n" +
					"  sbi.uom_code AS uomCode,\n" +
					"  sbi.global_flag AS globalFlag,\n" +
					"  sbi.enabled_Asl AS enabledAsl,\n" +
					"  sbi.purchase_cycle AS purchaseCycle,\n" +
					"  sbi.purchasing_lead_time AS purchasingLeadTime,\n" +
					"  sbi.category_id AS categoryId,\n" +
					"  sbi.invalid_date AS invalidDate,\n" +
					"  sbi.source_code AS sourceCode,\n" +
					"  sbi.source_id AS sourceId,\n" +
					"  sbi.version_num AS versionNum,\n" +
					"  sbi.creation_date AS creationDate,\n" +
					"  sbi.created_by AS createdBy,\n" +
					"  sbi.last_updated_by AS lastUpdatedBy,\n" +
					"  sbi.last_update_date AS lastUpdateDate,\n" +
					"  sbi.last_update_login AS lastUpdateLogin,\n" +
					"  sbi.cost AS cost,\n" +
					"  sbi.specification  AS specification,\n" +
					"  sbi.region AS region,\n" +
					"  sbi.attribute_category AS attributeCategory,\n" +
					"  bc.category_code AS categoryCode,\n" +
					"  bc.category_name AS categoryName,\n" +
					"  bc.full_category_code AS fullCategoryCode,\n" +
					"  bc.full_category_name AS fullCategoryName,\n" +
					"  slv.meaning AS itemStatusName,\n" +
					"  slv2.meaning AS uomCodeName,\n" +
					"  se.employee_name AS employeeName,\n" +
					"  rf1.access_Path AS accessPath,\n" +
					"  rf1.file_Name AS fileName\n" +
					"FROM\n" +
					"  srm_base_items sbi\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = sbi.category_id\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = sbi.organization_id\n" +
					"  LEFT JOIN saaf_institution ins2\n" +
					"    ON ins2.inst_id = ins.Parent_Inst_Id\n" +
					"  LEFT JOIN saaf_employees se\n" +
					"    ON sbi.agent_id = se.employee_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'ITEM_STATUS'\n" +
					"    AND slv.lookup_code = sbi.item_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv2.lookup_code = sbi.uom_code\n" +
					"  LEFT JOIN saaf_base_result_file rf1\n" +
					"    ON rf1.file_id = sbi.image_id\n" +
					"WHERE 1 = 1";

	public static String COUNT_ITME_SQL =
					"SELECT\n" +
					"  COUNT(1)\n" +
					"FROM\n" +
					"  srm_base_items sbi\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = sbi.category_id\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = sbi.organization_id\n" +
					"  LEFT JOIN saaf_employees se\n" +
					"    ON sbi.agent_id = se.employee_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"    AND slv.lookup_code = sbi.item_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv2.lookup_code = sbi.uom_code\n" +
					"  LEFT JOIN saaf_base_result_file rf1\n" +
					"    ON rf1.file_id = sbi.image_id\n" +
					"WHERE 1 = 1";

	public static final String QUERY_BASE_ITEM_BY_ITEM_CODE = "SELECT t.item_id itemId FROM srm_base_items t WHERE t.item_code = :itemCode";

	public static String QUERY_ITEM =
					"SELECT\n" +
					"  t.invalid_date AS invalidDate,\n" +
					"  t.item_id itemId\n" +
					"FROM\n" +
					"  srm_base_items t\n" +
					"WHERE 1 = 1\n" +
					"  AND t.item_id = :itemId\n" +
					"  AND t.organization_id = :organizationId";

	public static String QUERY_ITEM_BY_ORGANIZATION =
					"SELECT\n" +
					"  sbi.item_id,\n" +
					"  sbi.item_code,\n" +
					"  sbi.item_name,\n" +
					"  sbi.item_description,\n" +
					"  sbi.global_flag,\n" +
					"  sbi.organization_id,\n" +
					"  si.inst_name\n" +
					"FROM\n" +
					"  srm_base_items sbi,\n" +
					"  saaf_institution si\n" +
					"WHERE sbi.organization_id = si.inst_id\n" +
					"  AND si.inst_type = 'ORGANIZATION'\n" +
					"  AND (\n" +
					"    sbi.invalid_date IS NULL\n" +
					"    OR sbi.invalid_date >= sysdate\n" +
					"  )\n" +
					"  AND sbi.organization_id = :organizationId";

	public static String QUERY_ORG_ITEM_SQL =
					"SELECT Ins2.Inst_Name AS parentInstName\n" +
					"      ,Ins.Inst_Id    AS instId\n" +
					"      ,Ins.Inst_Name  AS instName\n" +
					"      ,(CASE\n" +
					"         WHEN (Sbi.Item_Id IS NULL or Sbi.item_status='INACT') THEN\n" +
					"          'N'\n" +
					"         ELSE\n" +
					"          'Y'\n" +
					"       END) enabledFlag\n" +
					"  FROM Saaf_Institution Ins\n" +
					"  LEFT JOIN Saaf_Institution Ins2\n" +
					"    ON Ins.Parent_Inst_Id = Ins2.Inst_Id\n" +
					"  LEFT JOIN Srm_Base_Items Sbi\n" +
					"    ON Ins.Inst_Id = Sbi.Organization_Id\n" +
					"   AND Sbi.item_id = :itemId\n" +
			        " WHERE Ins.Inst_Region = (SELECT Sbib.region\n" +
					"                            FROM Srm_Base_Items_b Sbib\n" +
					"                           WHERE Sbib.Item_Id = :itemId)\n" +
					"   AND Ins.Inst_Type = 'ORGANIZATION'\n" +
					"   AND Ins.Enabled = 'Y'\n" +
					" ORDER BY Ins2.Inst_Name,Ins.Inst_Name \n";

	public static String QUERY_ITEMB_STATUS_SQL =
			"SELECT COUNT(1) count\n" +
			"  FROM Srm_Base_Items_b Sbib\n" +
			" WHERE Sbib.Item_Id = :itemId\n" +
			"   AND EXISTS (SELECT 1\n" +
			"          FROM Srm_Base_Items Sbi\n" +
			"         WHERE Sbi.Item_Id = Sbib.Item_Id\n" +
			"           AND Sbi.Item_Status = 'ACT')";


	private Integer itemId; //物料ID
    private String itemCode; //物料编码
    private String itemName; //物料名称
    private String itemDescription; //物料说明
    private String globalFlag; //全局标识
	private String enabledAsl; //启用ASL
    private Integer organizationId; //库存组织ID
    private String uomCode; //计量单位
    private String itemStatus; //物料状态
    private String purchasingFlag; //可采购标识
    private Integer agentId; //采购员ID
    private Integer categoryId; //采购分类ID
    private BigDecimal purchaseCycle; //采购周期
    private BigDecimal purchasingLeadTime; //采购提前期
    private BigDecimal minPacking; //最小包装量
    private BigDecimal benchmarkPrice; //基准价
    private Integer imageId; //图片ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate; //失效时间
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
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;
	private Integer attribute16;
	private Integer attribute17;
	private Integer attribute18;
	private Integer attribute19;
	private Integer attribute20;
	private BigDecimal attribute21;
	private BigDecimal attribute22;
	private BigDecimal attribute23;
	private BigDecimal attribute24;
	private BigDecimal attribute25;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;
    private Integer operatorUserId;

	private BigDecimal cost; //成本
	private String specification; //规格型号
	private String region; //组织区域

    //别名
	private String categoryCode;//采购分类编码
	private String categoryName;//采购分类名称
	private String fullCategoryCode;//采购分类全编码
	private String fullCategoryName;//采购分类全名称
	private String itemStatusName;//物料编码的状态别名
	private String employeeName;//人员名称
	private String createrUserName;//创建人
	private String lastUpdaterUserName;//更新人
	private String uomCodeName; //别名-物料单位名称
	private Integer parentInstId;//组织的父组织Id（业务实体）
	private String parentInstName;//组织的父组织名称（业务实体名称）
	private String accessPath;
	private String fileName;
	private Integer instId;
	private String instCode;//组织编码
	private String instName;//组织名称
	private String enabledFlag; //是否分配组织

	private Integer count;

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	private String ts;
	public String getTs() {
		return "Y";
	}

	public Integer getParentInstId() {
		return parentInstId;
	}

	public void setParentInstId(Integer parentInstId) {
		this.parentInstId = parentInstId;
	}

	public String getParentInstName() {
		return parentInstName;
	}

	public void setParentInstName(String parentInstName) {
		this.parentInstName = parentInstName;
	}

	public String getUomCodeName() {
		return uomCodeName;
	}

	public void setUomCodeName(String uomCodeName) {
		this.uomCodeName = uomCodeName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getItemStatusName() {
		return itemStatusName;
	}

	public void setItemStatusName(String itemStatusName) {
		this.itemStatusName = itemStatusName;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setGlobalFlag(String globalFlag) {
		this.globalFlag = globalFlag;
	}

	
	public String getGlobalFlag() {
		return globalFlag;
	}

	public void setEnabledAsl(String enabledAsl) {
		this.enabledAsl = enabledAsl;
	}

	public String getEnabledAsl() {
		return enabledAsl;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	
	public String getUomCode() {
		return uomCode;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	
	public String getItemStatus() {
		return itemStatus;
	}

	public void setPurchasingFlag(String purchasingFlag) {
		this.purchasingFlag = purchasingFlag;
	}

	
	public String getPurchasingFlag() {
		return purchasingFlag;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	
	public Integer getAgentId() {
		return agentId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setPurchaseCycle(BigDecimal purchaseCycle) {
		this.purchaseCycle = purchaseCycle;
	}

	
	public BigDecimal getPurchaseCycle() {
		return purchaseCycle;
	}

	public void setPurchasingLeadTime(BigDecimal purchasingLeadTime) {
		this.purchasingLeadTime = purchasingLeadTime;
	}

	
	public BigDecimal getPurchasingLeadTime() {
		return purchasingLeadTime;
	}

	public void setMinPacking(BigDecimal minPacking) {
		this.minPacking = minPacking;
	}

	
	public BigDecimal getMinPacking() {
		return minPacking;
	}

	public void setBenchmarkPrice(BigDecimal benchmarkPrice) {
		this.benchmarkPrice = benchmarkPrice;
	}

	
	public BigDecimal getBenchmarkPrice() {
		return benchmarkPrice;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	
	public Integer getImageId() {
		return imageId;
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
	
	public Integer getOperatorUserId() {
		return operatorUserId;
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

	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	public Integer getAttribute16() {
		return attribute16;
	}

	public void setAttribute16(Integer attribute16) {
		this.attribute16 = attribute16;
	}

	public Integer getAttribute17() {
		return attribute17;
	}

	public void setAttribute17(Integer attribute17) {
		this.attribute17 = attribute17;
	}

	public Integer getAttribute18() {
		return attribute18;
	}

	public void setAttribute18(Integer attribute18) {
		this.attribute18 = attribute18;
	}

	public Integer getAttribute19() {
		return attribute19;
	}

	public void setAttribute19(Integer attribute19) {
		this.attribute19 = attribute19;
	}

	public Integer getAttribute20() {
		return attribute20;
	}

	public void setAttribute20(Integer attribute20) {
		this.attribute20 = attribute20;
	}

	public BigDecimal getAttribute21() {
		return attribute21;
	}

	public void setAttribute21(BigDecimal attribute21) {
		this.attribute21 = attribute21;
	}

	public BigDecimal getAttribute22() {
		return attribute22;
	}

	public void setAttribute22(BigDecimal attribute22) {
		this.attribute22 = attribute22;
	}

	public BigDecimal getAttribute23() {
		return attribute23;
	}

	public void setAttribute23(BigDecimal attribute23) {
		this.attribute23 = attribute23;
	}

	public BigDecimal getAttribute24() {
		return attribute24;
	}

	public void setAttribute24(BigDecimal attribute24) {
		this.attribute24 = attribute24;
	}

	public BigDecimal getAttribute25() {
		return attribute25;
	}

	public void setAttribute25(BigDecimal attribute25) {
		this.attribute25 = attribute25;
	}

	public Date getAttribute26() {
		return attribute26;
	}

	public void setAttribute26(Date attribute26) {
		this.attribute26 = attribute26;
	}

	public Date getAttribute27() {
		return attribute27;
	}

	public void setAttribute27(Date attribute27) {
		this.attribute27 = attribute27;
	}

	public Date getAttribute28() {
		return attribute28;
	}

	public void setAttribute28(Date attribute28) {
		this.attribute28 = attribute28;
	}

	public Date getAttribute29() {
		return attribute29;
	}

	public void setAttribute29(Date attribute29) {
		this.attribute29 = attribute29;
	}

	public Date getAttribute30() {
		return attribute30;
	}

	public void setAttribute30(Date attribute30) {
		this.attribute30 = attribute30;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
