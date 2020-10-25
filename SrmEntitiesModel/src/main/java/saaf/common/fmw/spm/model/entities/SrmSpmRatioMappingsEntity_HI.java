package saaf.common.fmw.spm.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmSpmRatioMappingsEntity_HI Entity Object
 * Fri Mar 30 17:23:37 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_spm_ratio_mappings")
public class SrmSpmRatioMappingsEntity_HI {
    private Integer mappingId;
    private Integer orgId;
    private Integer categoryId;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private Integer vendorCount;
    private BigDecimal supplyRatio1;
    private BigDecimal supplyRatio2;
    private BigDecimal supplyRatio3;
    private BigDecimal supplyRatio4;
    private BigDecimal supplyRatio5;
    private BigDecimal supplyRatio6;
    private BigDecimal supplyRatio7;
    private BigDecimal supplyRatio8;
    private BigDecimal supplyRatio9;
    private BigDecimal supplyRatio10;
    private BigDecimal supplyRatio11;
    private BigDecimal supplyRatio12;
    private BigDecimal supplyRatio13;
    private BigDecimal supplyRatio14;
    private BigDecimal supplyRatio15;
    private BigDecimal supplyRatio16;
    private BigDecimal supplyRatio17;
    private BigDecimal supplyRatio18;
    private BigDecimal supplyRatio19;
    private BigDecimal supplyRatio20;
    private String description;
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



    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public void setMappingId(Integer mappingId) {
	this.mappingId = mappingId;
    }

    @Id
    @SequenceGenerator(name = "SRM_SPM_RATIO_MAPPINGS_S", sequenceName = "SRM_SPM_RATIO_MAPPINGS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_SPM_RATIO_MAPPINGS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "mapping_id", nullable = false, length = 11)    
    public Integer getMappingId() {
	return mappingId;
    }

    public void setOrgId(Integer orgId) {
	this.orgId = orgId;
    }

    @Column(name = "org_id", nullable = true, length = 11)    
    public Integer getOrgId() {
	return orgId;
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

    public void setVendorCount(Integer vendorCount) {
	this.vendorCount = vendorCount;
    }

    @Column(name = "vendor_count", nullable = true, length = 11)    
    public Integer getVendorCount() {
	return vendorCount;
    }

    public void setSupplyRatio1(BigDecimal supplyRatio1) {
	this.supplyRatio1 = supplyRatio1;
    }

    @Column(name = "supply_ratio_1", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio1() {
	return supplyRatio1;
    }

    public void setSupplyRatio2(BigDecimal supplyRatio2) {
	this.supplyRatio2 = supplyRatio2;
    }

    @Column(name = "supply_ratio_2", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio2() {
	return supplyRatio2;
    }

    public void setSupplyRatio3(BigDecimal supplyRatio3) {
	this.supplyRatio3 = supplyRatio3;
    }

    @Column(name = "supply_ratio_3", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio3() {
	return supplyRatio3;
    }

    public void setSupplyRatio4(BigDecimal supplyRatio4) {
	this.supplyRatio4 = supplyRatio4;
    }

    @Column(name = "supply_ratio_4", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio4() {
	return supplyRatio4;
    }

    public void setSupplyRatio5(BigDecimal supplyRatio5) {
	this.supplyRatio5 = supplyRatio5;
    }

    @Column(name = "supply_ratio_5", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio5() {
	return supplyRatio5;
    }

    public void setSupplyRatio6(BigDecimal supplyRatio6) {
	this.supplyRatio6 = supplyRatio6;
    }

    @Column(name = "supply_ratio_6", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio6() {
	return supplyRatio6;
    }

    public void setSupplyRatio7(BigDecimal supplyRatio7) {
	this.supplyRatio7 = supplyRatio7;
    }

    @Column(name = "supply_ratio_7", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio7() {
	return supplyRatio7;
    }

    public void setSupplyRatio8(BigDecimal supplyRatio8) {
	this.supplyRatio8 = supplyRatio8;
    }

    @Column(name = "supply_ratio_8", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio8() {
	return supplyRatio8;
    }

    public void setSupplyRatio9(BigDecimal supplyRatio9) {
	this.supplyRatio9 = supplyRatio9;
    }

    @Column(name = "supply_ratio_9", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio9() {
	return supplyRatio9;
    }

    public void setSupplyRatio10(BigDecimal supplyRatio10) {
	this.supplyRatio10 = supplyRatio10;
    }

    @Column(name = "supply_ratio_10", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio10() {
	return supplyRatio10;
    }

    public void setSupplyRatio11(BigDecimal supplyRatio11) {
	this.supplyRatio11 = supplyRatio11;
    }

    @Column(name = "supply_ratio_11", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio11() {
	return supplyRatio11;
    }

    public void setSupplyRatio12(BigDecimal supplyRatio12) {
	this.supplyRatio12 = supplyRatio12;
    }

    @Column(name = "supply_ratio_12", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio12() {
	return supplyRatio12;
    }

    public void setSupplyRatio13(BigDecimal supplyRatio13) {
	this.supplyRatio13 = supplyRatio13;
    }

    @Column(name = "supply_ratio_13", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio13() {
	return supplyRatio13;
    }

    public void setSupplyRatio14(BigDecimal supplyRatio14) {
	this.supplyRatio14 = supplyRatio14;
    }

    @Column(name = "supply_ratio_14", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio14() {
	return supplyRatio14;
    }

    public void setSupplyRatio15(BigDecimal supplyRatio15) {
	this.supplyRatio15 = supplyRatio15;
    }

    @Column(name = "supply_ratio_15", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio15() {
	return supplyRatio15;
    }

    public void setSupplyRatio16(BigDecimal supplyRatio16) {
	this.supplyRatio16 = supplyRatio16;
    }

    @Column(name = "supply_ratio_16", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio16() {
	return supplyRatio16;
    }

    public void setSupplyRatio17(BigDecimal supplyRatio17) {
	this.supplyRatio17 = supplyRatio17;
    }

    @Column(name = "supply_ratio_17", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio17() {
	return supplyRatio17;
    }

    public void setSupplyRatio18(BigDecimal supplyRatio18) {
	this.supplyRatio18 = supplyRatio18;
    }

    @Column(name = "supply_ratio_18", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio18() {
	return supplyRatio18;
    }

    public void setSupplyRatio19(BigDecimal supplyRatio19) {
	this.supplyRatio19 = supplyRatio19;
    }

    @Column(name = "supply_ratio_19", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio19() {
	return supplyRatio19;
    }

    public void setSupplyRatio20(BigDecimal supplyRatio20) {
	this.supplyRatio20 = supplyRatio20;
    }

    @Column(name = "supply_ratio_20", precision = 22, scale = 2)    
    public BigDecimal getSupplyRatio20() {
	return supplyRatio20;
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
}

