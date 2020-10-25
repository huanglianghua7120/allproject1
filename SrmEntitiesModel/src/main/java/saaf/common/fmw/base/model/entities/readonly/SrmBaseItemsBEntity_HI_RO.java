package saaf.common.fmw.base.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Project Name：SRM标准产品
 * Company Name：SIE
 * Program Name：SrmBaseItemsBEntity_HI_RO.java
 * Description：物料基表的只读实体类
 *
 * Update History
 * ==============================================================================
 *  Version    Date           Updated By     Description
 *  -------    -----------    -----------    ---------------
 *  V1.0       2019-05-29     秦晓钊          创建
 * ==============================================================================
 */
public class SrmBaseItemsBEntity_HI_RO implements Serializable {

    //查询所有物料
    public static String SRM_BASE_ITEMS_B_QUERY_SQL =
            "SELECT\n" +
                    "  sib.item_id AS itemId,\n" +
                    "  sib.item_code AS itemCode,\n" +
                    "  sib.item_name AS itemName,\n" +
                    "  sib.item_description AS itemDescription,\n" +
                    "  sib.global_flag AS globalFlag,\n" +
                    "  sib.enabled_asl AS enabledAsl,\n" +
                    "  sib.uom_code AS uomCode,\n" +
                    "  sib.item_status AS itemStatus,\n" +
                    "  sib.purchasing_flag AS purchasingFlag,\n" +
                    "  sib.agent_id AS agentId,\n" +
                    "  sib.category_id AS categoryId,\n" +
                    "  sib.purchase_cycle AS purchaseCycle,\n" +
                    "  sib.purchasing_lead_time AS purchasingLeadTime,\n" +
                    "  sib.min_packing AS minPacking,\n" +
                    "  sib.benchmark_price AS benchmarkPrice,\n" +
                    "  sib.invalid_date AS invalidDate,\n" +
                    "  sib.source_code AS sourceCode,\n" +
                    "  sib.source_id AS sourceId,\n" +
                    "  sib.image_id AS imageId,\n" +
                    "  sib.specification AS specification,\n" +
                    "  sib.version_num AS versionNum,\n" +
                    "  sib.creation_date AS creationDate,\n" +
                    "  sib.created_by AS createdBy,\n" +
                    "  sib.last_update_date AS lastUpdateDate,\n" +
                    "  sib.last_updated_by AS lastUpdatedBy,\n" +
                    "  sib.last_update_login AS lastUpdateLogin,\n" +
                    "  sib.cost AS cost,\n" +
                    "  sib.region AS region,\n" +
                    "  sib.attribute_category AS attributeCategory,\n" +
                    "  sib.attribute1 AS attribute1,\n" +
                    "  sib.attribute2 AS attribute2,\n" +
                    "  sib.attribute3 AS attribute3,\n" +
                    "  sib.attribute4 AS attribute4,\n" +
                    "  sib.attribute5 AS attribute5,\n" +
                    "  sib.attribute6 AS attribute6,\n" +
                    "  sib.attribute7 AS attribute7,\n" +
                    "  sib.attribute8 AS attribute8,\n" +
                    "  sib.attribute9 AS attribute9,\n" +
                    "  sib.attribute10 AS attribute10,\n" +
                    "  sib.attribute11 AS attribute11,\n" +
                    "  sib.attribute12 AS attribute12,\n" +
                    "  sib.attribute13 AS attribute13,\n" +
                    "  sib.attribute14 AS attribute14,\n" +
                    "  sib.attribute15 AS attribute15,\n" +
                    "  sib.attribute16 AS attribute16,\n" +
                    "  sib.attribute17 AS attribute17,\n" +
                    "  sib.attribute18 AS attribute18,\n" +
                    "  sib.attribute19 AS attribute19,\n" +
                    "  sib.attribute20 AS attribute20,\n" +
                    "  sib.attribute21 AS attribute21,\n" +
                    "  sib.attribute22 AS attribute22,\n" +
                    "  sib.attribute23 AS attribute23,\n" +
                    "  sib.attribute24 AS attribute24,\n" +
                    "  sib.attribute25 AS attribute25,\n" +
                    "  sib.attribute26 AS attribute26,\n" +
                    "  sib.attribute27 AS attribute27,\n" +
                    "  sib.attribute28 AS attribute28,\n" +
                    "  sib.attribute29 AS attribute29,\n" +
                    "  sib.attribute30 AS attribute30,\n" +
                    "  sbc.full_category_code AS fullCategoryCode,\n" +
                    "  sbc.full_category_name AS fullCategoryName,\n" +
                    "  slv1.meaning AS uomCodeName,\n" +
                    "  slv2.meaning AS itemStatusName\n" +
                    "FROM\n" +
                    "  srm_base_items_b sib\n" +
                    "  LEFT JOIN srm_base_categories sbc\n" +
                    "    ON sib.category_id = sbc.category_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv1\n" +
                    "    ON slv1.lookup_code = sib.uom_code\n" +
                    "    AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
                    "  LEFT JOIN saaf_lookup_values slv2\n" +
                    "    ON slv2.lookup_code = sib.item_status\n" +
                    "    AND slv2.lookup_type = 'BASE_DATA_STATUS'\n" +
                    "WHERE 1 = 1";

    //查询指定业务实体下的物料
    public static String QUERY_ITEMS_BY_ORG_SQL =
                    "SELECT\n" +
                    "  sib.item_id AS itemId,\n" +
                    "  sib.item_code AS itemCode,\n" +
                    "  sib.item_name AS itemName,\n" +
                    "  sib.item_description AS itemDescription,\n" +
                    "  sib.global_flag AS globalFlag,\n" +
                    "  sib.enabled_asl AS enabledAsl,\n" +
                    "  sib.uom_code AS uomCode,\n" +
                    "  sib.item_status AS itemStatus,\n" +
                    "  sib.purchasing_flag AS purchasingFlag,\n" +
                    "  sib.agent_id AS agentId,\n" +
                    "  sib.category_id AS categoryId,\n" +
                    "  sib.purchase_cycle AS purchaseCycle,\n" +
                    "  sib.purchasing_lead_time AS purchasingLeadTime,\n" +
                    "  sib.min_packing AS minPacking,\n" +
                    "  sib.benchmark_price AS benchmarkPrice,\n" +
                    "  sib.invalid_date AS invalidDate,\n" +
                    "  sib.source_code AS sourceCode,\n" +
                    "  sib.source_id AS sourceId,\n" +
                    "  sib.image_id AS imageId,\n" +
                    "  sib.version_num AS versionNum,\n" +
                    "  sib.creation_date AS creationDate,\n" +
                    "  sib.created_by AS createdBy,\n" +
                    "  sib.last_update_date AS lastUpdateDate,\n" +
                    "  sib.last_updated_by AS lastUpdatedBy,\n" +
                    "  sib.last_update_login AS lastUpdateLogin,\n" +
                    "  sib.cost AS cost,\n" +
                    "  sib.specification  AS specification,\n" +
                    "  sib.region AS region,\n" +
                    "  sib.attribute_category AS attributeCategory,\n" +
                    "  sib.attribute1 AS attribute1,\n" +
                    "  sib.attribute2 AS attribute2,\n" +
                    "  sib.attribute3 AS attribute3,\n" +
                    "  sib.attribute4 AS attribute4,\n" +
                    "  sib.attribute5 AS attribute5,\n" +
                    "  sib.attribute6 AS attribute6,\n" +
                    "  sib.attribute7 AS attribute7,\n" +
                    "  sib.attribute8 AS attribute8,\n" +
                    "  sib.attribute9 AS attribute9,\n" +
                    "  sib.attribute10 AS attribute10,\n" +
                    "  sib.attribute11 AS attribute11,\n" +
                    "  sib.attribute12 AS attribute12,\n" +
                    "  sib.attribute13 AS attribute13,\n" +
                    "  sib.attribute14 AS attribute14,\n" +
                    "  sib.attribute15 AS attribute15,\n" +
                    "  sib.attribute16 AS attribute16,\n" +
                    "  sib.attribute17 AS attribute17,\n" +
                    "  sib.attribute18 AS attribute18,\n" +
                    "  sib.attribute19 AS attribute19,\n" +
                    "  sib.attribute20 AS attribute20,\n" +
                    "  sib.attribute21 AS attribute21,\n" +
                    "  sib.attribute22 AS attribute22,\n" +
                    "  sib.attribute23 AS attribute23,\n" +
                    "  sib.attribute24 AS attribute24,\n" +
                    "  sib.attribute25 AS attribute25,\n" +
                    "  sib.attribute26 AS attribute26,\n" +
                    "  sib.attribute27 AS attribute27,\n" +
                    "  sib.attribute28 AS attribute28,\n" +
                    "  sib.attribute29 AS attribute29,\n" +
                    "  sib.attribute30 AS attribute30,\n" +
                    "  sbc.full_category_code AS fullCategoryCode,\n" +
                    "  sbc.full_category_name AS fullCategoryName,\n" +
                    "  slv1.meaning AS uomCodeName,\n" +
                    "  slv2.meaning AS itemStatusName\n" +
                    "FROM\n" +
                    "  srm_base_items_b sib\n" +
                    "  LEFT JOIN srm_base_categories sbc\n" +
                    "    ON sib.category_id = sbc.category_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv1\n" +
                    "    ON slv1.lookup_code = sib.uom_code\n" +
                    "    AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
                    "  LEFT JOIN saaf_lookup_values slv2\n" +
                    "    ON slv2.lookup_code = sib.item_status\n" +
                    "    AND slv2.lookup_type = 'BASE_DATA_STATUS'\n" +
                    "WHERE 1 = 1\n" +
                    "  AND EXISTS\n" +
                    "  (SELECT\n" +
                    "    1\n" +
                    "  FROM\n" +
                    "    srm_base_items sbi,\n" +
                    "    saaf_institution si\n" +
                    "  WHERE sbi.item_id = sib.item_id\n" +
                    "    AND sbi.organization_id = si.inst_id\n" +
                    "    AND si.parent_inst_id = :orgId)";

    private Integer itemId;

	private String itemCode;

	private String itemName;

	private String itemDescription;

	private String globalFlag;

	private String enabledAsl;

	private String uomCode;

	private String itemStatus;

	private String purchasingFlag;

	private Integer agentId;

	private Integer categoryId;

	private BigDecimal purchaseCycle;

	private BigDecimal purchasingLeadTime;

	private BigDecimal minPacking;

	private BigDecimal benchmarkPrice;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date invalidDate;

	private String sourceCode;

	private String sourceId;

	private Integer imageId;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

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

    private BigDecimal cost; //成本
    private String specification; //规格型号
    private String region; //组织区域

    //别名
    private String categoryCode;//采购分类编码
    private String categoryName;//采购分类名称
    private String itemStatusName;//物料状态
    private String uomCodeName; //物料单位
    private String fullCategoryCode;
    private String fullCategoryName;


    public SrmBaseItemsBEntity_HI_RO(){
        super();
    }


    public Integer  getItemId() {
        return itemId;
    }

    public void setItemId( Integer itemId) {
        this.itemId = itemId;
    }


    public String  getItemCode() {
        return itemCode;
    }

    public void setItemCode( String itemCode) {
        this.itemCode = itemCode;
    }


    public String  getItemName() {
        return itemName;
    }

    public void setItemName( String itemName) {
        this.itemName = itemName;
    }


    public String  getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription( String itemDescription) {
        this.itemDescription = itemDescription;
    }


    public String  getGlobalFlag() {
        return globalFlag;
    }

    public void setGlobalFlag( String globalFlag) {
        this.globalFlag = globalFlag;
    }


    public String  getEnabledAsl() {
        return enabledAsl;
    }

    public void setEnabledAsl( String enabledAsl) {
        this.enabledAsl = enabledAsl;
    }


    public String  getUomCode() {
        return uomCode;
    }

    public void setUomCode( String uomCode) {
        this.uomCode = uomCode;
    }


    public String  getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus( String itemStatus) {
        this.itemStatus = itemStatus;
    }


    public String  getPurchasingFlag() {
        return purchasingFlag;
    }

    public void setPurchasingFlag( String purchasingFlag) {
        this.purchasingFlag = purchasingFlag;
    }


    public Integer  getAgentId() {
        return agentId;
    }

    public void setAgentId( Integer agentId) {
        this.agentId = agentId;
    }


    public Integer  getCategoryId() {
        return categoryId;
    }

    public void setCategoryId( Integer categoryId) {
        this.categoryId = categoryId;
    }


    public BigDecimal  getPurchaseCycle() {
        return purchaseCycle;
    }

    public void setPurchaseCycle( BigDecimal purchaseCycle) {
        this.purchaseCycle = purchaseCycle;
    }


    public BigDecimal  getPurchasingLeadTime() {
        return purchasingLeadTime;
    }

    public void setPurchasingLeadTime( BigDecimal purchasingLeadTime) {
        this.purchasingLeadTime = purchasingLeadTime;
    }


    public BigDecimal  getMinPacking() {
        return minPacking;
    }

    public void setMinPacking( BigDecimal minPacking) {
        this.minPacking = minPacking;
    }


    public BigDecimal  getBenchmarkPrice() {
        return benchmarkPrice;
    }

    public void setBenchmarkPrice( BigDecimal benchmarkPrice) {
        this.benchmarkPrice = benchmarkPrice;
    }


    public Date  getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate( Date invalidDate) {
        this.invalidDate = invalidDate;
    }


    public String  getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode( String sourceCode) {
        this.sourceCode = sourceCode;
    }


    public String  getSourceId() {
        return sourceId;
    }

    public void setSourceId( String sourceId) {
        this.sourceId = sourceId;
    }


    public Integer  getImageId() {
        return imageId;
    }

    public void setImageId( Integer imageId) {
        this.imageId = imageId;
    }


    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }


    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }


    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }


    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }


    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }


    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }


    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }


    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }


    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }


    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }


    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }


    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }


    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }


    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }


    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }


    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }


    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }


    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }


    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }


    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }


    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }


    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }


    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }


    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }


    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }


    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }


    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }


    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }


    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }


    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }


    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }


    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }


    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }


    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
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

    public String getUomCodeName() {
        return uomCodeName;
    }

    public void setUomCodeName(String uomCodeName) {
        this.uomCodeName = uomCodeName;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
