package saaf.common.fmw.base.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseItemsEntity_HI Entity Object
 * Fri Jun 08 14:09:59 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_items")
@IdClass(SrmBaseItemsIdEntity_HI.class)
public class SrmBaseItemsEntity_HI {
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

	private BigDecimal cost; //成本
	private String specification; //规格型号
	private String region; //组织区域

	private Integer operatorUserId;

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Id
	@Column(name = "item_id", nullable = false, length = 11)
	public Integer getItemId() {
		return itemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "item_code", nullable = true, length = 50)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_name", nullable = false, length = 240)
	public String getItemName() {
		return itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "item_description", nullable = true, length = 240)
	public String getItemDescription() {
		return itemDescription;
	}

	public void setGlobalFlag(String globalFlag) {
		this.globalFlag = globalFlag;
	}

	@Column(name = "global_flag", nullable = true, length = 1)
	public String getGlobalFlag() {
		return globalFlag;
	}

	public void setEnabledAsl(String enabledAsl) {
		this.enabledAsl = enabledAsl;
	}

	@Column(name = "enabled_asl", nullable = true, length = 1)
	public String getEnabledAsl() {
		return enabledAsl;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Id
	@Column(name = "organization_id", nullable = false, length = 11)
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	@Column(name = "uom_code", nullable = true, length = 30)
	public String getUomCode() {
		return uomCode;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	@Column(name = "item_status", nullable = true, length = 30)
	public String getItemStatus() {
		return itemStatus;
	}

	public void setPurchasingFlag(String purchasingFlag) {
		this.purchasingFlag = purchasingFlag;
	}

	@Column(name = "purchasing_flag", nullable = true, length = 10)
	public String getPurchasingFlag() {
		return purchasingFlag;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	@Column(name = "agent_id", nullable = true, length = 10)
	public Integer getAgentId() {
		return agentId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = false, length = 11)
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setPurchaseCycle(BigDecimal purchaseCycle) {
		this.purchaseCycle = purchaseCycle;
	}

	@Column(name = "purchase_cycle", precision = 11, scale = 2)
	public BigDecimal getPurchaseCycle() {
		return purchaseCycle;
	}

	public void setPurchasingLeadTime(BigDecimal purchasingLeadTime) {
		this.purchasingLeadTime = purchasingLeadTime;
	}

	@Column(name = "purchasing_lead_time", precision = 11, scale = 2)
	public BigDecimal getPurchasingLeadTime() {
		return purchasingLeadTime;
	}

	public void setMinPacking(BigDecimal minPacking) {
		this.minPacking = minPacking;
	}

	@Column(name = "min_packing", precision = 11, scale = 2)
	public BigDecimal getMinPacking() {
		return minPacking;
	}

	public void setBenchmarkPrice(BigDecimal benchmarkPrice) {
		this.benchmarkPrice = benchmarkPrice;
	}

	@Column(name = "benchmark_price", precision = 11, scale = 2)
	public BigDecimal getBenchmarkPrice() {
		return benchmarkPrice;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	@Column(name = "image_id", nullable = true, length = 11)
	public Integer getImageId() {
		return imageId;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	@Column(name = "invalid_date", nullable = true, length = 0)
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = true, length = 30)
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 30)
	public String getSourceId() {
		return sourceId;
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

	@Column(name = "last_updated_by", nullable = true, length = 11)
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
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

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "cost", precision = 11, scale = 2)
	public BigDecimal getCost() {
		return cost;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "specification", nullable = true, length = 30)
	public String getSpecification() {
		return specification;
	}

	@Column(name = "region", nullable = true, length = 100)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
