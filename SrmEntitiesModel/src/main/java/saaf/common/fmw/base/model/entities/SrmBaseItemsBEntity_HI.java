package saaf.common.fmw.base.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "srm_base_items_b")
public class SrmBaseItemsBEntity_HI {


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

    private Integer operatorUserId;

	@Id
    @SequenceGenerator(name = "SRM_BASE_ITEMS_S", sequenceName = "SRM_BASE_ITEMS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_BASE_ITEMS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    public Integer  getItemId() {
        return itemId;
    }

    public void setItemId( Integer itemId) {
        this.itemId = itemId;
    }

    @Column(name = "item_code")
    public String  getItemCode() {
        return itemCode;
    }

    public void setItemCode( String itemCode) {
        this.itemCode = itemCode;
    }

    @Column(name = "item_name")
    public String  getItemName() {
        return itemName;
    }

    public void setItemName( String itemName) {
        this.itemName = itemName;
    }

    @Column(name = "item_description")
    public String  getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription( String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Column(name = "global_flag")
    public String  getGlobalFlag() {
        return globalFlag;
    }

    public void setGlobalFlag( String globalFlag) {
        this.globalFlag = globalFlag;
    }

    @Column(name = "enabled_asl")
    public String  getEnabledAsl() {
        return enabledAsl;
    }

    public void setEnabledAsl( String enabledAsl) {
        this.enabledAsl = enabledAsl;
    }

    @Column(name = "uom_code")
    public String  getUomCode() {
        return uomCode;
    }

    public void setUomCode( String uomCode) {
        this.uomCode = uomCode;
    }

    @Column(name = "item_status")
    public String  getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus( String itemStatus) {
        this.itemStatus = itemStatus;
    }

    @Column(name = "purchasing_flag")
    public String  getPurchasingFlag() {
        return purchasingFlag;
    }

    public void setPurchasingFlag( String purchasingFlag) {
        this.purchasingFlag = purchasingFlag;
    }

    @Column(name = "agent_id")
    public Integer  getAgentId() {
        return agentId;
    }

    public void setAgentId( Integer agentId) {
        this.agentId = agentId;
    }

    @Column(name = "category_id")
    public Integer  getCategoryId() {
        return categoryId;
    }

    public void setCategoryId( Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "purchase_cycle")
    public BigDecimal  getPurchaseCycle() {
        return purchaseCycle;
    }

    public void setPurchaseCycle( BigDecimal purchaseCycle) {
        this.purchaseCycle = purchaseCycle;
    }

    @Column(name = "purchasing_lead_time")
    public BigDecimal  getPurchasingLeadTime() {
        return purchasingLeadTime;
    }

    public void setPurchasingLeadTime( BigDecimal purchasingLeadTime) {
        this.purchasingLeadTime = purchasingLeadTime;
    }

    @Column(name = "min_packing")
    public BigDecimal  getMinPacking() {
        return minPacking;
    }

    public void setMinPacking( BigDecimal minPacking) {
        this.minPacking = minPacking;
    }

    @Column(name = "benchmark_price")
    public BigDecimal  getBenchmarkPrice() {
        return benchmarkPrice;
    }

    public void setBenchmarkPrice( BigDecimal benchmarkPrice) {
        this.benchmarkPrice = benchmarkPrice;
    }

    @Column(name = "invalid_date")
    public Date  getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate( Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    @Column(name = "source_code")
    public String  getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode( String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(name = "source_id")
    public String  getSourceId() {
        return sourceId;
    }

    public void setSourceId( String sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "image_id")
    public Integer  getImageId() {
        return imageId;
    }

    public void setImageId( Integer imageId) {
        this.imageId = imageId;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name = "version_num", nullable = true, length = 11)
    public Integer getVersionNum() {
        return versionNum;
    }

    @Column(name = "creation_date")
    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "created_by")
    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "last_update_date")
    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_updated_by")
    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_update_login")
    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "attribute_category")
    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute1")
    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }

    @Column(name = "attribute2")
    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }

    @Column(name = "attribute3")
    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }

    @Column(name = "attribute4")
    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }

    @Column(name = "attribute5")
    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(name = "attribute6")
    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }

    @Column(name = "attribute7")
    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }

    @Column(name = "attribute8")
    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }

    @Column(name = "attribute9")
    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }

    @Column(name = "attribute10")
    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }

    @Column(name = "attribute11")
    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }

    @Column(name = "attribute12")
    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }

    @Column(name = "attribute13")
    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }

    @Column(name = "attribute14")
    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }

    @Column(name = "attribute15")
    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }

    @Column(name = "attribute16")
    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }

    @Column(name = "attribute17")
    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }

    @Column(name = "attribute18")
    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }

    @Column(name = "attribute19")
    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }

    @Column(name = "attribute20")
    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }

    @Column(name = "attribute21")
    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }

    @Column(name = "attribute22")
    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }

    @Column(name = "attribute23")
    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }

    @Column(name = "attribute24")
    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }

    @Column(name = "attribute25")
    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }

    @Column(name = "attribute26")
    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }

    @Column(name = "attribute27")
    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }

    @Column(name = "attribute28")
    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }

    @Column(name = "attribute29")
    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }

    @Column(name = "attribute30")
    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
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

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

}
