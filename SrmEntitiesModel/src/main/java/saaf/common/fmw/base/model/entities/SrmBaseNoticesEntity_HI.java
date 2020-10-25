package saaf.common.fmw.base.model.entities;

import javax.persistence.*;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseNoticesEntity_HI Entity Object
 * Mon Jul 02 09:37:08 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_notices")
public class SrmBaseNoticesEntity_HI {
    private Integer noticeId;
    private String noticeCode;
    private String noticeTitle;
    private String noticeContent;
    private String noticeType;
    private String noticeStatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private Integer publishUserId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date cancelDate;
    private Integer cancelUserId;
    private String setTopFlag;
    private String receiverType;
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

    public void setNoticeId(Integer noticeId) {
	this.noticeId = noticeId;
    }

    @Id
    @SequenceGenerator(name = "SRM_BASE_NOTICES_S", sequenceName = "SRM_BASE_NOTICES_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_BASE_NOTICES_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id", nullable = false, length = 11)    
    public Integer getNoticeId() {
	return noticeId;
    }

    public void setNoticeCode(String noticeCode) {
	this.noticeCode = noticeCode;
    }

    @Column(name = "notice_code", nullable = false, length = 30)    
    public String getNoticeCode() {
	return noticeCode;
    }

    public void setNoticeTitle(String noticeTitle) {
	this.noticeTitle = noticeTitle;
    }

    @Column(name = "notice_title", nullable = false, length = 240)    
    public String getNoticeTitle() {
	return noticeTitle;
    }

    public void setNoticeContent(String noticeContent) {
	this.noticeContent = noticeContent;
    }

    @Column(name = "notice_content", nullable = true, length = 0)    
    public String getNoticeContent() {
	return noticeContent;
    }

    public void setNoticeType(String noticeType) {
	this.noticeType = noticeType;
    }

    @Column(name = "notice_type", nullable = false, length = 30)    
    public String getNoticeType() {
	return noticeType;
    }

    public void setNoticeStatus(String noticeStatus) {
	this.noticeStatus = noticeStatus;
    }

    @Column(name = "notice_status", nullable = false, length = 30)    
    public String getNoticeStatus() {
	return noticeStatus;
    }

    public void setPublishDate(Date publishDate) {
	this.publishDate = publishDate;
    }

    @Column(name = "publish_date", nullable = true, length = 0)    
    public Date getPublishDate() {
	return publishDate;
    }

    public void setPublishUserId(Integer publishUserId) {
	this.publishUserId = publishUserId;
    }

    @Column(name = "publish_user_id", nullable = true, length = 11)    
    public Integer getPublishUserId() {
	return publishUserId;
    }

    public void setCancelDate(Date cancelDate) {
	this.cancelDate = cancelDate;
    }

    @Column(name = "cancel_date", nullable = true, length = 0)    
    public Date getCancelDate() {
	return cancelDate;
    }

    public void setCancelUserId(Integer cancelUserId) {
	this.cancelUserId = cancelUserId;
    }

    @Column(name = "cancel_user_id", nullable = true, length = 11)    
    public Integer getCancelUserId() {
	return cancelUserId;
    }

    public void setSetTopFlag(String setTopFlag) {
	this.setTopFlag = setTopFlag;
    }

    @Column(name = "set_top_flag", nullable = true, length = 1)    
    public String getSetTopFlag() {
	return setTopFlag;
    }

    public void setReceiverType(String receiverType) {
	this.receiverType = receiverType;
    }

    @Column(name = "receiver_type", nullable = false, length = 30)    
    public String getReceiverType() {
	return receiverType;
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
    
    @Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}
}

