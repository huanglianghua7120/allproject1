package saaf.common.fmw.intf.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "saaf_message_head")
public class SrmMessageHeadEntity_HI {
	
	private Integer messageId;
    private String messageCode;
    private String messageDesc;
    private String messageType;
    private String messageUrl;
    private String messageTex;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    private String description;
    private String status;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String categoryCode1;
    private String categoryCode2;
    private String categoryCode3;
    private String noticeType;
    private Integer messageFileId;
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
    
    public void setOperatorUserId(Integer operatorUserId) {
    	this.operatorUserId = operatorUserId;
    }

    @Transient	
    public Integer getOperatorUserId() {
    	return operatorUserId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @Id
    @GeneratedValue
    @Column(name = "message_id", precision = 22, scale = 0)
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    @Column(name = "message_code", nullable = false, length = 20)
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

    @Column(name = "message_desc", nullable = false, length = 30)
    public String getMessageDesc() {
        return messageDesc;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Column(name = "message_type", nullable = false, length = 30)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    @Column(name = "message_url", nullable = false, length = 100)
    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageTex(String messageTex) {
        this.messageTex = messageTex;
    }

    @Column(name = "message_tex", nullable = true, length = 4000)
    public String getMessageTex() {
        return messageTex;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    @Column(name = "start_date_active", nullable = true, length = 0)
    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "description", nullable = true, length = 240)
    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status", nullable = true, length = 30)
    public String getStatus() {
        return status;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "version_num", precision = 22, scale = 0)
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

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute_category", nullable = true, length = 30)
    public String getAttributeCategory() {
        return attributeCategory;
    }
    
    @Column(name = "category_code1", nullable = true, length = 255)
    public String getCategoryCode1() {
		return categoryCode1;
	}

	public void setCategoryCode1(String categoryCode1) {
		this.categoryCode1 = categoryCode1;
	}
	
	@Column(name = "category_code2", nullable = true, length = 255)
	public String getCategoryCode2() {
		return categoryCode2;
	}

	public void setCategoryCode2(String categoryCode2) {
		this.categoryCode2 = categoryCode2;
	}
	
	@Column(name = "category_code3", nullable = true, length = 255)
	public String getCategoryCode3() {
		return categoryCode3;
	}

	public void setCategoryCode3(String categoryCode3) {
		this.categoryCode3 = categoryCode3;
	}
	
	@Column(name = "notice_type", nullable = true, length = 255)
	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	@Column(name = "message_file_id", nullable = true, length = 11)
    public Integer getMessageFileId() {
		return messageFileId;
	}

	public void setMessageFileId(Integer messageFileId) {
		this.messageFileId = messageFileId;
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

}
