package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


/**
 * Created by huangtao on 2016/9/9.
 */
public class SaafProductVersionEntity_HI_RO implements Serializable {
    public SaafProductVersionEntity_HI_RO() {
        super();
    }

    public static final String query = " SELECT spv.product_version_id   AS \"productVersionId\"\n" +
        "    		      ,spv.created_by           AS \"createdBy\"\n" +
        "    		      ,spv.download_number      AS \"downloadNumber\"\n" +
        "    		      ,spv.download_url         AS \"downloadUrl\"\n" +
        "    		      ,spv.enable_flag          AS \"enableFlag\"\n" +
        "    		      ,spv.forced_download_flag AS \"forcedDownloadFlag\"\n" +
        "    		      ,spv.update_date AS \"updateDate\"\n" +
        "    		      ,spv.product_code         AS \"productCode\"\n" +
        "    		      ,spv.version_number       AS \"versionNumber\"\n" +
        "    		      ,spv.product_name         AS \"productName\"\n" +
        "    		      ,spv.program_name         AS \"programName\"\n" +
        "    		      ,spv.release_platform     AS \"releasePlatform\"\n" +
        "    		      ,spv.version_remark       AS \"versionRemark\"\n" +
        "    		      ,slv.meaning              AS \"releaseplatformValue\"\n" +
        "    		 FROM saaf_product_version spv\n" +
        "    		 LEFT JOIN saaf_lookup_values slv\n" +
        "    		   ON slv.lookup_code = spv.release_platform\n" +
        "    		  AND slv.lookup_type = 'RELEASE_PLATFORM'\n" +
        "    		WHERE 1 = 1 ";

    private Integer productVersionId;
    private String productCode;
    private String productName;
    private Integer versionNumber;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    private String enableFlag;
    private String releasePlatform;
    private String programName;
    private String downloadUrl;
    private Integer downloadNumber;
    private String forcedDownloadFlag;
    private String versionRemark;

    private Integer createdBy;

    private String releaseplatformValue;

    public String getReleaseplatformValue() {
        return releaseplatformValue;
    }

    public void setReleaseplatformValue(String releaseplatformValue) {
        this.releaseplatformValue = releaseplatformValue;
    }

    public Integer getProductVersionId() {
        return productVersionId;
    }

    public void setProductVersionId(Integer productVersionId) {
        this.productVersionId = productVersionId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getReleasePlatform() {
        return releasePlatform;
    }

    public void setReleasePlatform(String releasePlatform) {
        this.releasePlatform = releasePlatform;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getForcedDownloadFlag() {
        return forcedDownloadFlag;
    }

    public void setForcedDownloadFlag(String forcedDownloadFlag) {
        this.forcedDownloadFlag = forcedDownloadFlag;
    }

    public String getVersionRemark() {
        return versionRemark;
    }

    public void setVersionRemark(String versionRemark) {
        this.versionRemark = versionRemark;
    }


    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }


}
