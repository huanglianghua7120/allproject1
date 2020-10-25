package saaf.common.fmw.po.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoTechnicalNumEntity_HI_RO Entity Object
 * Sat Jun 27 14:50:48 CST 2020  Auto Generate
 */

public class SrmPoTechnicalNumEntity_HI_RO {

    public static String QUERY_TECHNICAL_NUM_SQL ="SELECT Sptn.Technical_Code\n" +
            "      ,Sptn.Technical_Name\n" +
            "      ,Sptn.Technical_Id\n" +
            "      ,Sptn.Source_Id\n" +
            "      ,Sptn.Enable\n" +
            "      ,decode(Sptn.Enable,'Y','生效','失效') as enableName\n" +
            "      ,Sptn.Effective_Date\n" +
            "      ,Sptn.Expiration_Date\n" +
            "      ,Sptn.Version_Num\n" +
            "      ,Sptn.Creation_Date\n" +
            "      ,Sptn.Created_By\n" +
            "  FROM Srm_Po_Technical_Num Sptn\n" +
            " WHERE 1 = 1\n";


    private Integer technicalId;
    private String technicalCode;
    private String technicalName;
    private String sourceId;
    private String enable;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
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
    private Integer attribute16;
    private Integer attribute17;
    private Integer attribute18;
    private Integer attribute19;
    private Integer attribute20;
    private Integer attribute21;
    private Integer attribute22;
    private Integer attribute23;
    private Integer attribute24;
    private Integer attribute25;
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
    private String enableName;

    public String getEnableName() {
        return enableName;
    }

    public void setEnableName(String enableName) {
        this.enableName = enableName;
    }

    public void setTechnicalId(Integer technicalId) {
		this.technicalId = technicalId;
	}

	
	public Integer getTechnicalId() {
		return technicalId;
	}

	public void setTechnicalCode(String technicalCode) {
		this.technicalCode = technicalCode;
	}

	
	public String getTechnicalCode() {
		return technicalCode;
	}

	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}

	
	public String getTechnicalName() {
		return technicalName;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	
	public String getEnable() {
		return enable;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
	public Date getExpirationDate() {
		return expirationDate;
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

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
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

	public void setAttribute16(Integer attribute16) {
		this.attribute16 = attribute16;
	}

	
	public Integer getAttribute16() {
		return attribute16;
	}

	public void setAttribute17(Integer attribute17) {
		this.attribute17 = attribute17;
	}

	
	public Integer getAttribute17() {
		return attribute17;
	}

	public void setAttribute18(Integer attribute18) {
		this.attribute18 = attribute18;
	}

	
	public Integer getAttribute18() {
		return attribute18;
	}

	public void setAttribute19(Integer attribute19) {
		this.attribute19 = attribute19;
	}

	
	public Integer getAttribute19() {
		return attribute19;
	}

	public void setAttribute20(Integer attribute20) {
		this.attribute20 = attribute20;
	}

	
	public Integer getAttribute20() {
		return attribute20;
	}

	public void setAttribute21(Integer attribute21) {
		this.attribute21 = attribute21;
	}

	
	public Integer getAttribute21() {
		return attribute21;
	}

	public void setAttribute22(Integer attribute22) {
		this.attribute22 = attribute22;
	}

	
	public Integer getAttribute22() {
		return attribute22;
	}

	public void setAttribute23(Integer attribute23) {
		this.attribute23 = attribute23;
	}

	
	public Integer getAttribute23() {
		return attribute23;
	}

	public void setAttribute24(Integer attribute24) {
		this.attribute24 = attribute24;
	}

	
	public Integer getAttribute24() {
		return attribute24;
	}

	public void setAttribute25(Integer attribute25) {
		this.attribute25 = attribute25;
	}

	
	public Integer getAttribute25() {
		return attribute25;
	}

	public void setAttribute26(Date attribute26) {
		this.attribute26 = attribute26;
	}

	
	public Date getAttribute26() {
		return attribute26;
	}

	public void setAttribute27(Date attribute27) {
		this.attribute27 = attribute27;
	}

	
	public Date getAttribute27() {
		return attribute27;
	}

	public void setAttribute28(Date attribute28) {
		this.attribute28 = attribute28;
	}

	
	public Date getAttribute28() {
		return attribute28;
	}

	public void setAttribute29(Date attribute29) {
		this.attribute29 = attribute29;
	}

	
	public Date getAttribute29() {
		return attribute29;
	}

	public void setAttribute30(Date attribute30) {
		this.attribute30 = attribute30;
	}

	
	public Date getAttribute30() {
		return attribute30;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
