package saaf.common.fmw.spm.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmSpmSupplierScoreEntity_HI_RO Entity Object
 * Tue Apr 03 10:47:26 GMT+08:00 2018  Auto Generate
 */

public class SrmSpmSupplierScoreEntity_HI_RO {

	//获取存在供货比例但是未包含在绩效计算结果中的供应商
	public static final String QUERY_OUT_SUPPLIER_LIST = "SELECT sp.category_id\n" +
			"      ,sp.vendor_id\n" +
			"      ,sp.curr_supply_ratio\n" +
			"      ,ifnull(sss.score_type\n" +
			"             ,'OTHER') score_type\n" +
			"      ,:scheme_id scheme_id\n" +
			"      ,sss.supplier_score_id\n" +
            "      ,ifnull(sss.publish_flag,'Y') publish_flag\n" +
			"  FROM (SELECT sbc.category_id\n" +
			"              ,spsl.supplier_id vendor_id\n" +
			"              ,spsl.proportion  curr_supply_ratio\n" +
			"          FROM srm_po_supply_proportion_h spsh\n" +
			"              ,srm_base_categories        sbc\n" +
			"              ,srm_po_supply_proportion_l spsl\n" +
			"         WHERE spsh.status = 'Y'\n" +
			"           AND spsh.supply_type = 'CLASSIFY'\n" +
			"           AND spsl.enable_flag = 'Y'\n" +
			"           AND spsl.supply_id = spsh.supply_id\n" +
			//"           AND sbc.small_category_code = spsh.small_category_code\n" +
			"           AND sbc.category_code = spsh.small_category_code\n" +
			"           AND spsh.supply_id =\n" +
			"               (SELECT MAX(spsph.supply_id)\n" +
			"                  FROM srm_po_supply_proportion_h spsph\n" +
			"                 WHERE spsph.status = 'Y'\n" +
			"                   AND spsph.supply_type = 'CLASSIFY'\n" +
			"                   AND spsph.small_category_code = spsh.small_category_code\n" +
			"                   AND EXISTS (SELECT 1\n" +
			"                          FROM srm_spm_performance_scheme ssps\n" +
			"                         WHERE ssps.scheme_id = :scheme_id\n" +
			"                           AND date_format(spsph.start_date_active\n" +
			"                                          ,'%Y-%m') <=\n" +
			"                               ssps.evaluate_interval_to\n" +
			"                           AND (spsph.end_date_active IS NULL OR\n" +
			"                               date_format(spsph.end_date_active\n" +
			"                                           ,'%Y-%m') >=\n" +
			"                               ssps.evaluate_interval_to)))\n" +
			"           AND EXISTS\n" +
			"         (SELECT 1\n" +
			"                  FROM srm_spm_supplier_score ssss\n" +
			"                 WHERE ssss.score_type IN ('CATEGORY'\n" +
			"                                          ,'OTHER')\n" +
			"                   AND ssss.scheme_id = :scheme_id\n" +
			"                   AND ssss.category_id = sbc.category_id)) sp\n" +
			"  LEFT JOIN srm_spm_supplier_score sss\n" +
			"    ON sss.score_type IN ('CATEGORY'\n" +
			"                         ,'OTHER')\n" +
			"   AND sss.scheme_id = :scheme_id\n" +
			"   AND sss.category_id = sp.category_id\n" +
			"   AND sss.vendor_id = sp.vendor_id\n";

    private Integer supplierScoreId; //供应商分类得分ID
    private Integer schemeId; //绩效方案ID，关联表：SRM_SPM_PERFORMANCE_SCHEME
    private Integer vendorId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer tplCategoryId; //模板分类ID，关联表：SRM_SPM_TPL_CATEGORIES
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private String scoreType; //分值类型（DIMENSION：维度得分，CATEGORY：类别得分）
    private Integer tplDimensionId; //模板维度ID，关联表：SRM_SPM_TPL_DIMENSION
    private String evaluateDimension; //评价维度，关联表：SAAF_LOOKUP_VALUES（SPM_INDICATOR_DIMENSION）
    private BigDecimal score; //分值
    private Integer rank; //排名
    private BigDecimal currSupplyRatio; //当前供货比例(%)
    private BigDecimal adviseSupplyRatio; //建议供货比例(%)
    private BigDecimal newSupplyRatio; //新供货比例(%)
    private String status; //状态，关联表：SAAF_LOOKUP_VALUES
    private String publishFlag; //是否有效，关联表：SAAF_LOOKUP_VALUES（YSE_NO）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate; //生效时间
    private String description; //说明、备注
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
    private Integer operatorUserId;

	public void setSupplierScoreId(Integer supplierScoreId) {
		this.supplierScoreId = supplierScoreId;
	}

	
	public Integer getSupplierScoreId() {
		return supplierScoreId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}

	
	public Integer getTplCategoryId() {
		return tplCategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	
	public String getSegment1() {
		return segment1;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	
	public String getSegment2() {
		return segment2;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	
	public String getSegment3() {
		return segment3;
	}

	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}

	
	public String getSegment4() {
		return segment4;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	
	public String getScoreType() {
		return scoreType;
	}

	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}

	
	public Integer getTplDimensionId() {
		return tplDimensionId;
	}

	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}

	
	public String getEvaluateDimension() {
		return evaluateDimension;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	
	public BigDecimal getScore() {
		return score;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	
	public Integer getRank() {
		return rank;
	}

	public void setCurrSupplyRatio(BigDecimal currSupplyRatio) {
		this.currSupplyRatio = currSupplyRatio;
	}

	
	public BigDecimal getCurrSupplyRatio() {
		return currSupplyRatio;
	}

	public void setAdviseSupplyRatio(BigDecimal adviseSupplyRatio) {
		this.adviseSupplyRatio = adviseSupplyRatio;
	}

	
	public BigDecimal getAdviseSupplyRatio() {
		return adviseSupplyRatio;
	}

	public void setNewSupplyRatio(BigDecimal newSupplyRatio) {
		this.newSupplyRatio = newSupplyRatio;
	}

	
	public BigDecimal getNewSupplyRatio() {
		return newSupplyRatio;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	
	public String getPublishFlag() {
		return publishFlag;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	
	public Date getPublishDate() {
		return publishDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getDescription() {
		return description;
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

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
