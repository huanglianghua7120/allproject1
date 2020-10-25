package saaf.common.fmw.base.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class SrmBaseUserCategoriesEntity_HI_RO implements Serializable {

    public static String SRM_BASE_USER_CATEGORIES_QUERY_SQL =
                    "SELECT\n" +
                    "  sbuc.user_category_id AS userCategoryId,\n" +
                    "  sbuc.user_id AS userId,\n" +
                    "  sbuc.category_id AS categoryId,\n" +
                    "  sbuc.enabled_flag AS enabledFlag,\n" +
                    "  sbuc.version_num AS versionNum,\n" +
                    "  sbuc.creation_date AS creationDate,\n" +
                    "  sbuc.created_by AS createdBy,\n" +
                    "  sbuc.last_update_date AS lastUpdateDate,\n" +
                    "  sbuc.last_updated_by AS lastUpdatedBy,\n" +
                    "  sbuc.last_update_login AS lastUpdateLogin,\n" +
                    "  sbuc.attribute_category AS attributeCategory,\n" +
                    "  sbuc.attribute1 AS attribute1,\n" +
                    "  sbuc.attribute2 AS attribute2,\n" +
                    "  sbuc.attribute3 AS attribute3,\n" +
                    "  sbuc.attribute4 AS attribute4,\n" +
                    "  sbuc.attribute5 AS attribute5,\n" +
                    "  sbuc.attribute6 AS attribute6,\n" +
                    "  sbuc.attribute7 AS attribute7,\n" +
                    "  sbuc.attribute8 AS attribute8,\n" +
                    "  sbuc.attribute9 AS attribute9,\n" +
                    "  sbuc.attribute10 AS attribute10,\n" +
                    "  sbuc.attribute11 AS attribute11,\n" +
                    "  sbuc.attribute12 AS attribute12,\n" +
                    "  sbuc.attribute13 AS attribute13,\n" +
                    "  sbuc.attribute14 AS attribute14,\n" +
                    "  sbuc.attribute15 AS attribute15,\n" +
                    "  sbuc.attribute16 AS attribute16,\n" +
                    "  sbuc.attribute17 AS attribute17,\n" +
                    "  sbuc.attribute18 AS attribute18,\n" +
                    "  sbuc.attribute19 AS attribute19,\n" +
                    "  sbuc.attribute20 AS attribute20,\n" +
                    "  sbuc.attribute21 AS attribute21,\n" +
                    "  sbuc.attribute22 AS attribute22,\n" +
                    "  sbuc.attribute23 AS attribute23,\n" +
                    "  sbuc.attribute24 AS attribute24,\n" +
                    "  sbuc.attribute25 AS attribute25,\n" +
                    "  sbuc.attribute26 AS attribute26,\n" +
                    "  sbuc.attribute27 AS attribute27,\n" +
                    "  sbuc.attribute28 AS attribute28,\n" +
                    "  sbuc.attribute29 AS attribute29,\n" +
                    "  sbuc.attribute30 AS attribute30,\n" +
                    "  su.user_name AS userName,\n" +
                    "  se.employee_name AS employeeName,\n" +
                    "  sbc.full_category_code AS categoryCode,\n" +
                    "  sbc.full_category_name AS categoryName\n" +
                    "FROM\n" +
                    "  srm_base_user_categories sbuc,\n" +
                    "  saaf_users su\n" +
                    "  LEFT JOIN saaf_employees se\n" +
                    "    ON se.employee_id = su.employee_id,\n" +
                    "  srm_base_categories sbc\n" +
                    "WHERE sbuc.user_id = su.user_id\n" +
                    "  AND sbuc.category_id = sbc.category_id";

    public static String QUERY_USER_CATEGORIES_SQL =
            "SELECT Listagg(Sbuc.Category_Id\n" +
            "              ,',') Within GROUP(ORDER BY Sbuc.Category_Id) AS catagoryIdStr\n" +
            "  FROM Srm_Base_User_Categories Sbuc\n" +
            " WHERE Sbuc.Enabled_Flag = 'Y'\n";

    private Integer userCategoryId;

	private Integer userId;

	private Integer categoryId;

	private String enabledFlag;

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

    private String userName;

    private String employeeName;

    private String categoryCode;

    private String categoryName;

    private String catagoryIdStr;


    public SrmBaseUserCategoriesEntity_HI_RO(){
        super();
    }


    public Integer  getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId( Integer userCategoryId) {
        this.userCategoryId = userCategoryId;
    }


    public Integer  getUserId() {
        return userId;
    }

    public void setUserId( Integer userId) {
        this.userId = userId;
    }


    public Integer  getCategoryId() {
        return categoryId;
    }

    public void setCategoryId( Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String  getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag( String enabledFlag) {
        this.enabledFlag = enabledFlag;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getCatagoryIdStr() {
        return catagoryIdStr;
    }

    public void setCatagoryIdStr(String catagoryIdStr) {
        this.catagoryIdStr = catagoryIdStr;
    }
}
