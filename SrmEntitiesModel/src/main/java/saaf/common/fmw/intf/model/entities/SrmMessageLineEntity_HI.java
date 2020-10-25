package saaf.common.fmw.intf.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "saaf_message_line")
public class SrmMessageLineEntity_HI {
	
	 private Integer messageLineId;
	    private Integer messageId;
	    private String type;
	    private String useridOrGroup;
	    private String userCode;
	    private String description;
	    private String userName;
	    private Integer versionNum;
	    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	    private Date creationDate;
	    private Integer createdBy;
	    private Integer lastUpdatedBy;
	    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	    private Date lastUpdateDate;
	    private Integer lastUpdateLogin;
	    private String attributeCategory;
	    private Integer supplierId;
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

	    public void setMessageLineId(Integer messageLineId) {
	        this.messageLineId = messageLineId;
	    }

	    @Id
	    @GeneratedValue
	    @Column(name = "message_line_id", precision = 22, scale = 0)
	    public Integer getMessageLineId() {
	        return messageLineId;
	    }

	    public void setMessageId(Integer messageId) {
	        this.messageId = messageId;
	    }

	    @Column(name = "message_id", precision = 22, scale = 0)
	    public Integer getMessageId() {
	        return messageId;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    @Column(name = "type", nullable = true, length = 20)
	    public String getType() {
	        return type;
	    }

	    public void setUseridOrGroup(String useridOrGroup) {
	        this.useridOrGroup = useridOrGroup;
	    }

	    @Column(name = "userid_or_group", nullable = true, length = 4000)
	    public String getUseridOrGroup() {
	        return useridOrGroup;
	    }

	    public void setUserCode(String userCode) {
	        this.userCode = userCode;
	    }

	    @Column(name = "user_code", nullable = true, length = 4000)
	    public String getUserCode() {
	        return userCode;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    @Column(name = "description", nullable = true, length = 240)
	    public String getDescription() {
	        return description;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    @Column(name = "user_name", nullable = true, length = 4000)
	    public String getUserName() {
	        return userName;
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
	   
	    @Column(name = "supplier_id", nullable = true, length = 11)
	    public Integer getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(Integer supplierId) {
			this.supplierId = supplierId;
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
