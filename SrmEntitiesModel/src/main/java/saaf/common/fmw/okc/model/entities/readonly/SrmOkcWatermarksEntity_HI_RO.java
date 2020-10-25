package saaf.common.fmw.okc.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Version;
import java.math.BigDecimal;
import javax.persistence.Transient;

/**
 * SrmOkcWatermarksEntity_HI_RO Entity Object
 * Mon May 27 18:02:01 CST 2019  Auto Generate
 */

public class SrmOkcWatermarksEntity_HI_RO {
    public static String QUERY_WATERMARKS_SQL = "SELECT sw.watermark_id,sw.watermark_code,sw.watermark_name,sw.watermark_file_id,sw.attribute1,sw.attribute2,sw.attribute16 \r\n" +
            ",sw.created_by,sw.creation_date \r\n" +
            ",su.user_full_name AS created_by_name\r\n" +
            "FROM srm_okc_watermarks sw\r\n" +
            "LEFT JOIN saaf_users su ON su.user_id=sw.created_by";
    private Integer watermarkId; //水印ID，主键
    private String watermarkCode; //水印编号
    private String watermarkName; //水印名称
    private Integer watermarkFileId; //模板文件ID
    private Integer uploadUserId; //文件上传用户
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate; //文件上传时间
    private Integer versionNum; //版本号
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdatedBy;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String attribute1;//上传文件名称
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
    private Integer operatorUserId;

    private String createdByName;

    public void setWatermarkId(Integer watermarkId) {
        this.watermarkId = watermarkId;
    }


    public Integer getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkCode(String watermarkCode) {
        this.watermarkCode = watermarkCode;
    }


    public String getWatermarkCode() {
        return watermarkCode;
    }

    public void setWatermarkName(String watermarkName) {
        this.watermarkName = watermarkName;
    }


    public String getWatermarkName() {
        return watermarkName;
    }

    public void setWatermarkFileId(Integer watermarkFileId) {
        this.watermarkFileId = watermarkFileId;
    }


    public Integer getWatermarkFileId() {
        return watermarkFileId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }


    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }


    public Date getUploadDate() {
        return uploadDate;
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

    public void setAttribute21(BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }


    public BigDecimal getAttribute21() {
        return attribute21;
    }

    public void setAttribute22(BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }


    public BigDecimal getAttribute22() {
        return attribute22;
    }

    public void setAttribute23(BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }


    public BigDecimal getAttribute23() {
        return attribute23;
    }

    public void setAttribute24(BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }


    public BigDecimal getAttribute24() {
        return attribute24;
    }

    public void setAttribute25(BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }


    public BigDecimal getAttribute25() {
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

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
}
