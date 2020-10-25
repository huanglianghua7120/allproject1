package saaf.common.fmw.base.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SaafBaseResultFileEntity_HI Entity Object
 * Mon Jul 02 11:19:34 CST 2018  Auto Generate
 */
@Entity
@Table(name = "saaf_base_result_file")
public class SaafBaseResultFileEntity_HI {
    private Integer fileId; //表ID，主键，供其他表做外键
    private String fileName;
    private String fileType;
    private String filePath;
    private String accessPath;
    private BigDecimal fileSize;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;
    private String uploadAuthor;
    private String groupName;
    private String remoteFilename;
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
    private Integer operatorUserId;

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Id
    @SequenceGenerator(name = "SAAF_BASE_RESULT_FILE_S", sequenceName = "SAAF_BASE_RESULT_FILE_S", allocationSize = 1)
    @GeneratedValue(generator = "SAAF_BASE_RESULT_FILE_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "file_id", nullable = false, length = 11)
    public Integer getFileId() {
        return fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_name", nullable = true, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Column(name = "file_type", nullable = true, length = 30)
    public String getFileType() {
        return fileType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Column(name = "file_path", nullable = true, length = 100)
    public String getFilePath() {
        return filePath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    @Column(name = "access_path", nullable = true, length = 200)
    public String getAccessPath() {
        return accessPath;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    @Column(name = "file_size", precision = 10, scale = 0)
    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Column(name = "upload_date", nullable = true, length = 0)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadAuthor(String uploadAuthor) {
        this.uploadAuthor = uploadAuthor;
    }

    @Column(name = "upload_author", nullable = true, length = 100)
    public String getUploadAuthor() {
        return uploadAuthor;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name = "group_name", nullable = true, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setRemoteFilename(String remoteFilename) {
        this.remoteFilename = remoteFilename;
    }

    @Column(name = "remote_filename", nullable = true, length = 200)
    public String getRemoteFilename() {
        return remoteFilename;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Version
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

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}
