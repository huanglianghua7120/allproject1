package saaf.common.fmw.base.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**srm_base_org_locations
 * SrmBaseLocationsEntity_HI_RO Entity Object
 * Wed Nov 07 10:22:25 CST 2018  Auto Generate
 */
public class SrmBaseLocationsEntity_HI_RO implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String QUERY_LOC_SQL = "select t.location_id         locationId,\n" +
			"       t.location_code       locationCode,\n" +
			"       t.location_name,\n" +
			"       t.organization_id     organizationId,\n" +
            "       si.inst_name     organizationName,\n" +
			"       t.ship_to_site_flag   shipToSiteFlag,\n" +
			"       t.bill_to_site_flag   billToSiteFlag,\n" +
            "       t.active_date   activeDate,\n" +
            "       t.inactive_date   inactiveDate,\n" +
            "       t.rmk   rmk\n" +
			"  from srm_base_locations t\n" +
            "  left join saaf_institution si on si.inst_id=t.organization_id and si.inst_type='ORGANIZATION'\n" +
			" where 1 = 1\n";


	public static String QUERY_LOC_SQL_NEW = "SELECT t.Location_Id locationId\n" +
			"      ,t.Location_Code locationCode\n" +
			"      ,t.location_name locationName\n" +
			"  FROM Srm_Base_Locations t\n" +
			" WHERE 1=1";

	public static String QUERY_BILL_TO_LOC_SQL =
			"SELECT t.Location_Id AS locationId\n" +
			"        ,t.Location_Code AS locationCode\n" +
			"        ,t.location_name AS locationName\n" +
            "        ,t.organization_id AS organizationId\n" +
			"    FROM Srm_Base_Locations t\n" +
			"   WHERE t.Bill_To_Site_Flag = 'Y'\n" +
            "     AND (t.active_Date IS NULL OR t.active_Date > SYSDATE - 1)\n" +
			"     AND (t.Inactive_Date IS NULL OR t.Inactive_Date < SYSDATE + 1)";

	public static String QUERY_SHIP_TO_LOC_SQL ="SELECT t.Location_Id AS locationId\n" +
            "        ,t.Location_Code AS locationCode\n" +
            "        ,t.location_name AS locationName\n" +
            "        ,t.organization_id AS organizationId\n" +
            "    FROM Srm_Base_Locations t\n" +
            "   WHERE t.ship_To_Site_Flag = 'Y'\n" +
            "     AND (t.active_Date IS NULL OR t.active_Date > SYSDATE - 1)\n" +
            "     AND (t.Inactive_Date IS NULL OR t.Inactive_Date < SYSDATE + 1)";
			/*"SELECT t.Location_Id AS locationId\n" +
					"      ,t.Location_Code AS locationCode\n" +
					"      ,t.Description AS locationName\n" +
					"  FROM (SELECT Sbl.Location_Id\n" +
					"              ,Sbl.Location_Code\n" +
					"              ,Sbl.Description\n" +
					"          FROM Srm_Base_Locations Sbl\n" +
					"         WHERE Sbl.Ship_To_Site_Flag = 'Y'\n" +
					"           AND (Sbl.Inactive_Date IS NULL OR Sbl.Inactive_Date < SYSDATE + 1)\n" +
					"           AND Sbl.Ledger_Name = (SELECT Sbl1.Ledger_Name\n" +
					"                                    FROM Srm_Base_Locations Sbl1\n" +
					"                                   WHERE Sbl1.Ou_Id = :orgId\n" +
					"                                     AND Rownum = 1)\n" +
					"        UNION ALL\n" +
					"        SELECT Sbl.Location_Id\n" +
					"              ,Sbl.Location_Code\n" +
					"              ,Sbl.Description\n" +
					"          FROM Srm_Base_Locations Sbl\n" +
					"         WHERE Sbl.Ship_To_Site_Flag = 'Y'\n" +
					"           AND (Sbl.Inactive_Date IS NULL OR Sbl.Inactive_Date < SYSDATE + 1)\n" +
					"           AND Sbl.Ou_Id IS NULL) t\n" +
					"WHERE 1 = 1  ";*/

    private Integer locationId;
    private String locationCode;
    private String locationName;
    private Integer organizationId;
    @JSONField(format = "yyyy-MM-dd")
    private Date activeDate;
    @JSONField(format = "yyyy-MM-dd")
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
    private String organizationName;

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getShipToSiteFlag() {
        return shipToSiteFlag;
    }

    public void setShipToSiteFlag(String shipToSiteFlag) {
        this.shipToSiteFlag = shipToSiteFlag;
    }

    public String getBillToSiteFlag() {
        return billToSiteFlag;
    }

    public void setBillToSiteFlag(String billToSiteFlag) {
        this.billToSiteFlag = billToSiteFlag;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
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

    public BigDecimal getAttribute16() {
        return attribute16;
    }

    public void setAttribute16(BigDecimal attribute16) {
        this.attribute16 = attribute16;
    }

    public BigDecimal getAttribute17() {
        return attribute17;
    }

    public void setAttribute17(BigDecimal attribute17) {
        this.attribute17 = attribute17;
    }

    public BigDecimal getAttribute18() {
        return attribute18;
    }

    public void setAttribute18(BigDecimal attribute18) {
        this.attribute18 = attribute18;
    }

    public BigDecimal getAttribute19() {
        return attribute19;
    }

    public void setAttribute19(BigDecimal attribute19) {
        this.attribute19 = attribute19;
    }

    public BigDecimal getAttribute20() {
        return attribute20;
    }

    public void setAttribute20(BigDecimal attribute20) {
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

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
