package saaf.common.fmw.base.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * SaafProductVersionEntity_HI Entity Object
 * Thu Apr 20 11:13:28 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_product_version")
public class SaafProductVersionEntity_HI {
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
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setProductVersionId(Integer productVersionId) {
        this.productVersionId = productVersionId;
    }

    @Id
    @GeneratedValue
    @Column(name = "product_version_id", precision = 22, scale = 0)
    public Integer getProductVersionId() {
        return productVersionId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "product_code", nullable = true, length = 80)
    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "product_name", nullable = true, length = 240)
    public String getProductName() {
        return productName;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Column(name = "version_number", precision = 22, scale = 0)
    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "update_date", nullable = true, length = 0)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Column(name = "enable_flag", nullable = true, length = 10)
    public String getEnableFlag() {
        return enableFlag;
    }

    public void setReleasePlatform(String releasePlatform) {
        this.releasePlatform = releasePlatform;
    }

    @Column(name = "release_platform", nullable = true, length = 240)
    public String getReleasePlatform() {
        return releasePlatform;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Column(name = "program_name", nullable = true, length = 500)
    public String getProgramName() {
        return programName;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Column(name = "download_url", nullable = true, length = 1000)
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    @Column(name = "download_number", precision = 22, scale = 0)
    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setForcedDownloadFlag(String forcedDownloadFlag) {
        this.forcedDownloadFlag = forcedDownloadFlag;
    }

    @Column(name = "forced_download_flag", nullable = true, length = 10)
    public String getForcedDownloadFlag() {
        return forcedDownloadFlag;
    }

    public void setVersionRemark(String versionRemark) {
        this.versionRemark = versionRemark;
    }

    @Column(name = "version_remark", nullable = true, length = 1000)
    public String getVersionRemark() {
        return versionRemark;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    @Column(name = "platform_code", nullable = true, length = 30)
    public String getPlatformCode() {
        return platformCode;
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

    @Column(name = "created_by", precision = 22, scale = 0)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", precision = 22, scale = 0)
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

    @Column(name = "last_update_login", precision = 22, scale = 0)
    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }
}

