package saaf.common.fmw.base.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmBaseItemsEntity_HI Entity Object
 * Fri Jun 08 14:09:59 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_locations")
public class SrmBaseLocationsEntity_HI {

	private Integer locationId;
	private String locationCode;
	private String locationName;
    private Integer organizationId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date activeDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date inactiveDate;
	private String shipToSiteFlag;
	private String billToSiteFlag;
    private String rmk;
    private Integer versionNum;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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
    private BigDecimal attribute16;
    private BigDecimal attribute17;
    private BigDecimal attribute18;
    private BigDecimal attribute19;
    private BigDecimal attribute20;
    private BigDecimal attribute21;
    private BigDecimal attribute22;
    private BigDecimal attribute23;
    private BigDecimal attribute24;
    private BigDecimal attribute25;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute26;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute27;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute28;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute29;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date attribute30;
    private Integer operatorUserId;

    @Id
    @SequenceGenerator(name = "SRM_BASE_LOCATIONS_S", sequenceName = "SRM_BASE_LOCATIONS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_BASE_LOCATIONS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "location_id" , nullable=false, length=22)
	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

    @Column(name="location_code", nullable=true, length=240)
    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    @Column(name="location_name", nullable=true, length=240)
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Column(name="organization_id", nullable=true, length=22)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name="active_date", nullable=true, length=7)
    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    @Column(name="inactive_date", nullable=true, length=7)
    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    @Column(name="ship_to_site_flag", nullable=true, length=10)
    public String getShipToSiteFlag() {
        return shipToSiteFlag;
    }

    public void setShipToSiteFlag(String shipToSiteFlag) {
        this.shipToSiteFlag = shipToSiteFlag;
    }

    @Column(name="bill_to_site_flag", nullable=true, length=10)
    public String getBillToSiteFlag() {
        return billToSiteFlag;
    }

    public void setBillToSiteFlag(String billToSiteFlag) {
        this.billToSiteFlag = billToSiteFlag;
    }

    @Column(name="rmk", nullable=true, length=240)
    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
    @Column(name="version_num", nullable=false, length=22)
    public Integer getVersionNum() {
        return versionNum;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name="creation_date", nullable=false, length=7)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name="created_by", nullable=false, length=22)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name="last_update_date", nullable=false, length=7)
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name="last_updated_by", nullable=false, length=22)
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name="last_update_login", nullable=true, length=22)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    @Column(name="attribute_category", nullable=true, length=30)
    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Column(name="attribute1", nullable=true, length=240)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Column(name="attribute2", nullable=true, length=240)
    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Column(name="attribute3", nullable=true, length=240)
    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Column(name="attribute4", nullable=true, length=240)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(name="attribute5", nullable=true, length=240)
    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Column(name="attribute6", nullable=true, length=240)
    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @Column(name="attribute7", nullable=true, length=240)
    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    @Column(name="attribute8", nullable=true, length=240)
    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    @Column(name="attribute9", nullable=true, length=240)
    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    @Column(name="attribute10", nullable=true, length=240)
    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    @Column(name="attribute11", nullable=true, length=240)
    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    @Column(name="attribute12", nullable=true, length=240)
    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    @Column(name="attribute13", nullable=true, length=240)
    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    @Column(name="attribute14", nullable=true, length=240)
    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    @Column(name="attribute15", nullable=true, length=240)
    public String getAttribute15() {
        return attribute15;
    }

    public void setAttribute16(BigDecimal attribute16) {
        this.attribute16 = attribute16;
    }

    @Column(name="attribute16", nullable=true, length=22)
    public BigDecimal getAttribute16() {
        return attribute16;
    }

    public void setAttribute17(BigDecimal attribute17) {
        this.attribute17 = attribute17;
    }

    @Column(name="attribute17", nullable=true, length=22)
    public BigDecimal getAttribute17() {
        return attribute17;
    }

    public void setAttribute18(BigDecimal attribute18) {
        this.attribute18 = attribute18;
    }

    @Column(name="attribute18", nullable=true, length=22)
    public BigDecimal getAttribute18() {
        return attribute18;
    }

    public void setAttribute19(BigDecimal attribute19) {
        this.attribute19 = attribute19;
    }

    @Column(name="attribute19", nullable=true, length=22)
    public BigDecimal getAttribute19() {
        return attribute19;
    }

    public void setAttribute20(BigDecimal attribute20) {
        this.attribute20 = attribute20;
    }

    @Column(name="attribute20", nullable=true, length=22)
    public BigDecimal getAttribute20() {
        return attribute20;
    }

    public void setAttribute21(BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }

    @Column(name="attribute21", nullable=true, length=22)
    public BigDecimal getAttribute21() {
        return attribute21;
    }

    public void setAttribute22(BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }

    @Column(name="attribute22", nullable=true, length=22)
    public BigDecimal getAttribute22() {
        return attribute22;
    }

    public void setAttribute23(BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }

    @Column(name="attribute23", nullable=true, length=22)
    public BigDecimal getAttribute23() {
        return attribute23;
    }

    public void setAttribute24(BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }

    @Column(name="attribute24", nullable=true, length=22)
    public BigDecimal getAttribute24() {
        return attribute24;
    }

    public void setAttribute25(BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }

    @Column(name="attribute25", nullable=true, length=22)
    public BigDecimal getAttribute25() {
        return attribute25;
    }

    public void setAttribute26(Date attribute26) {
        this.attribute26 = attribute26;
    }

    @Column(name="attribute26", nullable=true, length=7)
    public Date getAttribute26() {
        return attribute26;
    }

    public void setAttribute27(Date attribute27) {
        this.attribute27 = attribute27;
    }

    @Column(name="attribute27", nullable=true, length=7)
    public Date getAttribute27() {
        return attribute27;
    }

    public void setAttribute28(Date attribute28) {
        this.attribute28 = attribute28;
    }

    @Column(name="attribute28", nullable=true, length=7)
    public Date getAttribute28() {
        return attribute28;
    }

    public void setAttribute29(Date attribute29) {
        this.attribute29 = attribute29;
    }

    @Column(name="attribute29", nullable=true, length=7)
    public Date getAttribute29() {
        return attribute29;
    }

    public void setAttribute30(Date attribute30) {
        this.attribute30 = attribute30;
    }

    @Column(name="attribute30", nullable=true, length=7)
    public Date getAttribute30() {
        return attribute30;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


}
