package saaf.common.fmw.base.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmBaseItemDeliversEntity_HI Entity Object
 * Thu May 31 10:18:13 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_item_delivers")
public class SrmBaseItemDeliversEntity_HI {
    private Integer itemDeliverId; //回货方式ID
    private Integer categoryId; //采购分类ID
    private Integer itemId; //物料ID
    private String deliverType; //回货类型
    private String deliverStatus; //状态
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

	public void setItemDeliverId(Integer itemDeliverId) {
		this.itemDeliverId = itemDeliverId;
	}

	@Id	
	@GeneratedValue	
	@Column(name = "item_deliver_id", nullable = false, length = 11)	
	public Integer getItemDeliverId() {
		return itemDeliverId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "category_id", nullable = true, length = 11)	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_id", nullable = false, length = 11)	
	public Integer getItemId() {
		return itemId;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}

	@Column(name = "deliver_type", nullable = false, length = 30)	
	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	@Column(name = "deliver_status", nullable = true, length = 30)	
	public String getDeliverStatus() {
		return deliverStatus;
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

	@Column(name = "last_updated_by", nullable = true, length = 11)	
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
