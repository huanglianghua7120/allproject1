package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmSpmManualScoreEntity_HI_RO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String QUERY_MANUAL_INFO_LIST =
					"SELECT sce.manual_score_id manualScoreId\n" +
					"      ,sce.org_id orgId\n" +
					"      ,sint.inst_name instName\n" +
					"      ,sce.supplier_id supplierId\n" +
					"      ,srp.supplier_name supplierName\n" +
					"      ,srp.supplier_number supplierNumber\n" +
					"      ,sce.category_id categoryId\n" +
					"      ,cate.category_code categoryCode\n" +
					"      ,cate.category_name categoryName\n" +
					"      ,sbrf.access_path filePath\n" +
					"      ,sbrf.file_name fileName\n" +
					"      ,(SELECT sl.meaning\n" +
					"        FROM   saaf_lookup_values sl\n" +
					"        WHERE  sl.lookup_type = 'SPM_INDICATOR_DIMENSION'\n" +
					"        AND    sl.lookup_code = sce.evaluate_dimension) evaluateDimensionName\n" +
					"      ,sce.evaluate_dimension evaluateDimension\n" +
					"      ,sce.tpl_indicators_id tplIndicatorsId\n" +
					"      ,sce.tpl_indicators_items_id tplIndicatorsItemsId\n" +
					"      ,sce.po_header_id poHeaderId\n" +
					"      ,sce.happened_date happenedDate\n" +
					"      ,sce.project project\n" +
					"      ,sce.score score\n" +
					"      ,(SELECT sl.meaning\n" +
					"        FROM   saaf_lookup_values sl\n" +
					"        WHERE  sl.lookup_type = 'SPM_MANUAL_STATUS'\n" +
					"        AND    sl.lookup_code = sce.status) statusName\n" +
					"      ,sce.status status\n" +
					"      ,sce.description description\n" +
					"      ,sce.start_date startDate\n" +
					"      ,sce.end_date endDate\n" +
					"      ,sce.file_id fileId\n" +
					"      ,sp.indicator_id indicatorId\n" +
					"      ,(SELECT sl.meaning\n" +
					"        FROM   saaf_lookup_values sl\n" +
					"        WHERE  sl.lookup_type = 'SPM_INDICATOR_TYPE'\n" +
					"        AND    sl.lookup_code = sp.indicator_type) indicatorTypeName\n" +
					"      ,sp.indicator_type indicatorType\n" +
					"      ,(SELECT sl.meaning\n" +
					"        FROM   saaf_lookup_values sl\n" +
					"        WHERE  sl.lookup_type = 'SPM_INDICATOR_NAME'\n" +
					"        AND    sl.lookup_code = sp.indicator_code) indicatorName\n" +
					"      ,item.indicator_item_id indicatorItems\n" +
					"      ,(CASE\n" +
					"         WHEN sp.indicator_value_type = 'NUMERIC' THEN\n" +
					"          item.indicator_item_desc\n" +
					"         WHEN sp.indicator_value_type = 'TEXT' THEN\n" +
					"          item.pk1_value || '<=||<' || item.pk2_value\n" +
					"       END) indicatorItemDesc\n" +
					"      ,'Y' AS ts\n" +
					"      ,sce.created_by createdBy\n" +
					"      ,(SELECT su.user_full_name\n" +
					"        FROM   saaf_users su\n" +
					"        WHERE  su.user_id = sce.created_by) creationName\n" +
					"      ,sce.creation_date creationDate\n" +
					"      ,(SELECT su.user_full_name\n" +
					"        FROM   saaf_users su\n" +
					"        WHERE  su.user_id = sce.last_updated_by) updatedName\n" +
					"      ,sce.last_updated_by lastUpdatedBy\n" +
					"      ,sce.last_update_date lastUpdateDate\n" +
                    "      ,sce.item_type itemType\n" +
                    "      ,slv.meaning AS itemName\n" +
					"FROM   srm_spm_manual_score sce\n" +
					"LEFT   JOIN saaf_institution sint\n" +
					"ON     sce.org_id = sint.inst_id\n" +
					"LEFT   JOIN srm_pos_supplier_info srp\n" +
					"ON     sce.supplier_id = srp.supplier_id\n" +
					"LEFT   JOIN srm_base_categories cate\n" +
					"ON     sce.category_id = cate.category_id\n" +
					"LEFT   JOIN srm_spm_indicators sp\n" +
					"ON     sce.indicator_id = sp.indicator_id\n" +
					"LEFT   JOIN srm_spm_indicator_items item\n" +
					"ON     sce.indicator_item_id = item.indicator_item_id\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sce.file_id = sbrf.file_id\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     slv.lookup_code = sce.item_type\n" +
                    "AND    slv.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_COUNT =
					"SELECT COUNT(1)\n" +
					"FROM   srm_spm_manual_score sce\n" +
					"LEFT   JOIN saaf_institution sint\n" +
					"ON     sce.org_id = sint.inst_id\n" +
					"LEFT   JOIN srm_pos_supplier_info srp\n" +
					"ON     sce.supplier_id = srp.supplier_id\n" +
					"LEFT   JOIN srm_base_categories cate\n" +
					"ON     sce.category_id = cate.category_id\n" +
					"LEFT   JOIN srm_spm_indicators sp\n" +
					"ON     sce.indicator_id = sp.indicator_id\n" +
					"LEFT   JOIN srm_spm_indicator_items item\n" +
					"ON     sce.indicator_item_id = item.indicator_item_id\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sce.file_id = sbrf.file_id\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_NUM ="SELECT sce.manual_score_id manualScoreId, sce.org_id instId FROM srm_spm_manual_score sce WHERE 1 = 1\n";

	public static final String QUERY_AVG_INFO =
					"SELECT AVG(nvl(spm.score, 0)) score\n" +
					"FROM   srm_spm_manual_score spm\n" +
					"WHERE  spm.org_id = :orgId\n" +
					"AND    spm.supplier_id = :supplierId\n" +
					"AND    spm.category_id = :categoryId\n" +
					"AND    spm.evaluate_dimension = :dimensionName\n" +
					"AND    spm.indicator_id = :indicatorId\n" +
					"AND    spm.status = 'ACTIVE'\n" +
					"AND    spm.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    spm.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n";

	public static final String QUERY_AVG_INFO11 =
					"SELECT sc.supplier_id\n" +
					"      ,sc.category_id\n" +
					"      ,sc.indicator_id\n" +
					"      ,AVG(sc.score)\n" +
					"FROM   srm_spm_manual_score sc\n" +
					"WHERE  sc.evaluate_dimension = 'QUALITY'\n" +
					"AND    sc.status = 'ACTIVE'\n" +
					"AND    sc.supplier_id = :supplierId\n" +
					"AND    sc.category_id = :categoryId\n" +
					"AND    sc.indicator_id = :indicatorId\n" +
					"AND    sc.org_id = :orgId\n" +
					"AND    sc.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    sc.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n" +
					"GROUP  BY sc.supplier_id\n" +
					"         ,sc.category_id\n" +
					"         ,sc.indicator_id\n";

	public static final String QUERY_ADD_INFO =
					"SELECT SUM(nvl(spm.score, 0)) score\n" +
					"FROM   srm_spm_manual_score spm\n" +
					"WHERE  spm.org_id = :orgId\n" +
					"AND    spm.supplier_id = :supplierId\n" +
					"AND    spm.category_id = :categoryId\n" +
					"AND    spm.evaluate_dimension = :dimensionName\n" +
					"AND    spm.indicator_id = :indicatorId\n" +
					"AND    spm.status = 'ACTIVE'\n" +
					"AND    spm.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    spm.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n";

	public static final String QUERY_ADD_INFO11 =
					"SELECT sc.supplier_id\n" +
					"      ,sc.category_id\n" +
					"      ,sc.indicator_id\n" +
					"      ,AVG(sc.score) score\n" +
					"FROM   srm_spm_manual_score sc\n" +
					"WHERE  sc.evaluate_dimension = 'QUALITY'\n" +
					"AND    sc.status = 'ACTIVE'\n" +
					"AND    sc.supplier_id = :supplierId\n" +
					"AND    sc.category_id = :categoryId\n" +
					"AND    sc.indicator_id = :indicatorId\n" +
					"AND    sc.org_id = :orgId\n" +
					"AND    sc.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    sc.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n" +
					"GROUP  BY sc.supplier_id\n" +
					"         ,sc.category_id\n" +
					"         ,sc.indicator_id\n";

	public static final String QUERY_DEDUCT_INFO =
					"SELECT SUM(nvl(spm.score, 0)) score\n" +
					"FROM   srm_spm_manual_score spm\n" +
					"WHERE  spm.org_id = :orgId\n" +
					"AND    spm.supplier_id = :supplierId\n" +
					"AND    spm.category_id = :categoryId\n" +
					"AND    spm.evaluate_dimension = :dimensionName\n" +
					"AND    spm.indicator_id = :indicatorId\n" +
					"AND    spm.status = 'ACTIVE'\n" +
					"AND    spm.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    spm.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n";

	public static final String QUERY_DEDUCT_INFO11 =
					"SELECT sc.supplier_id\n" +
					"      ,sc.category_id\n" +
					"      ,sc.indicator_id\n" +
					"      ,AVG(sc.score) score\n" +
					"FROM   srm_spm_manual_score sc\n" +
					"WHERE  sc.evaluate_dimension = 'QUALITY'\n" +
					"AND    sc.status = 'ACTIVE'\n" +
					"AND    sc.supplier_id = :supplierId\n" +
					"AND    sc.category_id = :categoryId\n" +
					"AND    sc.indicator_id = :indicatorId\n" +
					"AND    sc.org_id = :orgId\n" +
					"AND    sc.happened_date >= to_date(:evaluateIntervalFrom, 'yyyy-mm-dd')\n" +
					"AND    sc.happened_date <= to_date(:evaluateIntervalTo, 'yyyy-mm-dd')\n" +
					"GROUP  BY sc.supplier_id\n" +
					"         ,sc.category_id\n" +
					"         ,sc.indicator_id\n";

	public static final String CALCULATE_MANUAL_SCORE =
					"SELECT Nvl(SUM(Sms.Score),0) AS score\n" +
					"      ,Listagg(Sms.Manual_Score_Id,',') Within GROUP(ORDER BY Manual_Score_Id) AS manualScoreIdStr\n" +
					"  FROM Srm_Spm_Manual_Score Sms\n" +
					" WHERE Sms.Status = 'ACTIVE'";

	private Integer manualScoreId; //手工评分ID
    private String orgId; //组织ID，关联表：SAAF_INSTITUTION
    private String supplierId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private String evaluateDimension; //评价维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private Integer tplIndicatorsId; //模板指标ID，关联表：SRM_SPM_TPL_INDICATORS
    private Integer tplIndicatorsItemsId; //模板指标评分项ID，关联表：SRM_SPM_TPL_INDICATOR_ITEMS
    private Integer IndicatorsId; //模板指标ID，关联表：SRM_SPM_INDICATORS
    private Integer IndicatorsItemsId;
    private Integer poHeaderId; //订单ID
    @JSONField(format = "yyyy-MM-dd")
    private Date happenedDate; //发生日期
	private String project; //加减分项
    private BigDecimal score; //分值
    private String status; //状态，关联表：SAAF_LOOKUP_VALUES（SPM_MANUAL_STATUS）
    private String description; //说明、备注
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate; //生效日期
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate; //失效日期
    private Integer fileId; //附件ID
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    
    private String instName;
    private String supplierName;
    private String supplierNumber;
    private String categoryName;
    private String categoryCode;
    private String ts;
    private String evaluateDimensionName;
    private String statusName;
    private String creationName;
    private String updatedName;
    private String indicatorCode;
    private String indicatorName;
    private String indicatorItemDesc;
    private String filePath;
    private String fileName;
    private Integer instId;
    private Integer indicatorItems;
    private String indicatorType;
    private Integer indicatorId;
    private String indicatorTypeName;
	private String manualScoreIdStr;
	private String itemType;
    private String itemName;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIndicatorTypeName() {
		return indicatorTypeName;
	}
	public void setIndicatorTypeName(String indicatorTypeName) {
		this.indicatorTypeName = indicatorTypeName;
	}
	public Integer getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}
	public String getIndicatorType() {
		return indicatorType;
	}
	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}
	public String getIndicatorCode() {
		return indicatorCode;
	}
	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getIndicatorItemDesc() {
		return indicatorItemDesc;
	}
	public void setIndicatorItemDesc(String indicatorItemDesc) {
		this.indicatorItemDesc = indicatorItemDesc;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public Integer getManualScoreId() {
		return manualScoreId;
	}
	public void setManualScoreId(Integer manualScoreId) {
		this.manualScoreId = manualScoreId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getSegment3() {
		return segment3;
	}
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}
	public String getSegment4() {
		return segment4;
	}
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}
	public String getEvaluateDimension() {
		return evaluateDimension;
	}
	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}
	public Integer getTplIndicatorsId() {
		return tplIndicatorsId;
	}
	public void setTplIndicatorsId(Integer tplIndicatorsId) {
		this.tplIndicatorsId = tplIndicatorsId;
	}
	public Integer getTplIndicatorsItemsId() {
		return tplIndicatorsItemsId;
	}
	public void setTplIndicatorsItemsId(Integer tplIndicatorsItemsId) {
		this.tplIndicatorsItemsId = tplIndicatorsItemsId;
	}
	public Integer getPoHeaderId() {
		return poHeaderId;
	}
	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	public Date getHappenedDate() {
		return happenedDate;
	}
	public void setHappenedDate(Date happenedDate) {
		this.happenedDate = happenedDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
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
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
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
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getEvaluateDimensionName() {
		return evaluateDimensionName;
	}
	public void setEvaluateDimensionName(String evaluateDimensionName) {
		this.evaluateDimensionName = evaluateDimensionName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCreationName() {
		return creationName;
	}
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	public String getUpdatedName() {
		return updatedName;
	}
	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}
	public Integer getIndicatorsId() {
		return IndicatorsId;
	}
	public void setIndicatorsId(Integer indicatorsId) {
		IndicatorsId = indicatorsId;
	}
	public Integer getIndicatorsItemsId() {
		return IndicatorsItemsId;
	}
	public void setIndicatorsItemsId(Integer indicatorsItemsId) {
		IndicatorsItemsId = indicatorsItemsId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public Integer getIndicatorItems() {
		return indicatorItems;
	}
	public void setIndicatorItems(Integer indicatorItems) {
		this.indicatorItems = indicatorItems;
	}

	public String getManualScoreIdStr() {
		return manualScoreIdStr;
	}

	public void setManualScoreIdStr(String manualScoreIdStr) {
		this.manualScoreIdStr = manualScoreIdStr;
	}
}
