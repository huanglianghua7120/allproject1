package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmRequestDatasEntity_HI Entity Object
 * Mon Apr 02 09:13:35 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_request_datas")
public class SrmSpmRequestDatasEntity_HI {
    private Integer dataId; //ID
    private Integer schemeId; //方案ID
    private Integer orgId; //组织ID，关联表：SAAF_INSTITUTION
    private Integer tplId; //绩效模板ID，关联表：SRM_SPM_PERFORMANCE_TPL
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    private Integer tplCategoryId; //模板分类ID，关联表：SRM_SPM_TPL_CATEGORIES
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private Integer vendorId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer poHeaderId; //订单头ID
    private Integer poLineId; //订单行ID
    private Integer deliveryHeaderId; //送货单头ID
    private Integer deliveryLineId; //送货单行ID
    private Integer detailId; //送货单明细ID
    private Integer itemId; //物料ID
    private Integer lineNumber;
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

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	@Id
	@SequenceGenerator(name = "SRM_SPM_REQUEST_DATAS_S", sequenceName = "SRM_SPM_REQUEST_DATAS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_SPM_REQUEST_DATAS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "data_id", nullable = false, length = 11)	
	public Integer getDataId() {
		return dataId;
	}

	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}

	@Column(name = "scheme_id", nullable = true, length = 11)	
	public Integer getSchemeId() {
		return schemeId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	@Column(name = "tpl_id", nullable = true, length = 11)	
	public Integer getTplId() {
		return tplId;
	}

	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}

	@Column(name = "evaluate_interval_from", nullable = true, length = 10)	
	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}

	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}

	@Column(name = "evaluate_interval_to", nullable = true, length = 10)	
	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}

	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}

	@Column(name = "tpl_category_id", nullable = true, length = 11)	
	public Integer getTplCategoryId() {
		return tplCategoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	@Column(name = "segment1", nullable = true, length = 30)	
	public String getSegment1() {
		return segment1;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	@Column(name = "segment2", nullable = true, length = 30)	
	public String getSegment2() {
		return segment2;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	@Column(name = "segment3", nullable = true, length = 30)	
	public String getSegment3() {
		return segment3;
	}

	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}

	@Column(name = "segment4", nullable = true, length = 30)	
	public String getSegment4() {
		return segment4;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	@Column(name = "vendor_id", nullable = true, length = 11)	
	public Integer getVendorId() {
		return vendorId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	@Column(name = "po_header_id", nullable = true, length = 11)	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	@Column(name = "po_line_id", nullable = true, length = 11)	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}

	@Column(name = "delivery_header_id", nullable = true, length = 11)	
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}

	public void setDeliveryLineId(Integer deliveryLineId) {
		this.deliveryLineId = deliveryLineId;
	}

	@Column(name = "delivery_line_id", nullable = true, length = 11)	
	public Integer getDeliveryLineId() {
		return deliveryLineId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@Column(name = "detail_id", nullable = true, length = 11)	
	public Integer getDetailId() {
		return detailId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = true, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "description", nullable = true, length = 240)	
	public String getDescription() {
		return description;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = false, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = false, length = 11)	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "last_updated_by", nullable = false, length = 11)	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "last_update_date", nullable = false, length = 0)	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	@Column(name = "last_update_login", nullable = true, length = 11)	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	@Column(name = "attribute_category", nullable = true, length = 30)	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", nullable = true, length = 240)	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", nullable = true, length = 240)	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", nullable = true, length = 240)	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", nullable = true, length = 240)	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", nullable = true, length = 240)	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	@Column(name = "attribute6", nullable = true, length = 240)	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	@Column(name = "attribute7", nullable = true, length = 240)	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	@Column(name = "attribute8", nullable = true, length = 240)	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	@Column(name = "attribute9", nullable = true, length = 240)	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	@Column(name = "attribute10", nullable = true, length = 240)	
	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	@Column(name = "attribute11", nullable = true, length = 240)	
	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	@Column(name = "attribute12", nullable = true, length = 240)	
	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	@Column(name = "attribute13", nullable = true, length = 240)	
	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	@Column(name = "attribute14", nullable = true, length = 240)	
	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	@Column(name = "attribute15", nullable = true, length = 240)	
	public String getAttribute15() {
		return attribute15;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
	

	@Column(name = "line_number", nullable = true, length = 11)	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
