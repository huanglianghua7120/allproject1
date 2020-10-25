package saaf.common.fmw.common.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * SaafImagesEntity_HI Entity Object
 * Thu Apr 20 11:13:17 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_images")
public class SaafImagesEntity_HI {
    private Integer id;
    private String storageAddress;
    private String imageName;
    private Integer targetId;
    private String targetType;
    private String imgType;
    private String imgChannel;
    private String url;
    private Integer width;
    private Integer height;
    private Integer imgSize;
    private String disabled;
    private String mtimeFilmCode;
    private String mtimeFilmName;
    private String mtimeFilmNameEn;
    private String mtimeMessageType;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", precision = 22, scale = 0)
    public Integer getId() {
        return id;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    @Column(name = "storage_address", nullable = true, length = 50)
    public String getStorageAddress() {
        return storageAddress;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Column(name = "image_name", nullable = true, length = 200)
    public String getImageName() {
        return imageName;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Column(name = "target_id", precision = 22, scale = 0)
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @Column(name = "target_type", nullable = true, length = 20)
    public String getTargetType() {
        return targetType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    @Column(name = "img_type", nullable = true, length = 255)
    public String getImgType() {
        return imgType;
    }

    public void setImgChannel(String imgChannel) {
        this.imgChannel = imgChannel;
    }

    @Column(name = "img_channel", nullable = true, length = 255)
    public String getImgChannel() {
        return imgChannel;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "url", nullable = true, length = 255)
    public String getUrl() {
        return url;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Column(name = "width", precision = 22, scale = 0)
    public Integer getWidth() {
        return width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Column(name = "height", precision = 22, scale = 0)
    public Integer getHeight() {
        return height;
    }

    public void setImgSize(Integer imgSize) {
        this.imgSize = imgSize;
    }

    @Column(name = "img_size", precision = 22, scale = 0)
    public Integer getImgSize() {
        return imgSize;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Column(name = "disabled", nullable = true, length = 10)
    public String getDisabled() {
        return disabled;
    }

    public void setMtimeFilmCode(String mtimeFilmCode) {
        this.mtimeFilmCode = mtimeFilmCode;
    }

    @Column(name = "mtime_film_code", nullable = true, length = 300)
    public String getMtimeFilmCode() {
        return mtimeFilmCode;
    }

    public void setMtimeFilmName(String mtimeFilmName) {
        this.mtimeFilmName = mtimeFilmName;
    }

    @Column(name = "mtime_film_name", nullable = true, length = 500)
    public String getMtimeFilmName() {
        return mtimeFilmName;
    }

    public void setMtimeFilmNameEn(String mtimeFilmNameEn) {
        this.mtimeFilmNameEn = mtimeFilmNameEn;
    }

    @Column(name = "mtime_film_name_en", nullable = true, length = 500)
    public String getMtimeFilmNameEn() {
        return mtimeFilmNameEn;
    }

    public void setMtimeMessageType(String mtimeMessageType) {
        this.mtimeMessageType = mtimeMessageType;
    }

    @Column(name = "mtime_message_type", nullable = true, length = 240)
    public String getMtimeMessageType() {
        return mtimeMessageType;
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

