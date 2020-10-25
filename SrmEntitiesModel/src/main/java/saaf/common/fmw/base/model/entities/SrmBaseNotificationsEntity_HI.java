package saaf.common.fmw.base.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmBaseNotificationsEntity_HI Entity Object
 * Mon Aug 13 10:46:25 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_base_notifications")
public class SrmBaseNotificationsEntity_HI {
    private Integer notificationId; //通知ID
    private String notificationCode; //通知编号
    private String notificationStatus; //通知状态，快码：DAI_BAN_TYPE
    private String notificationType; //通知类型，快码
    private String menuType; //业务类型（菜单名称）
    private String notificationContent; //通知内容
    private Integer receiverId; //接收者ID（用户ID，职责ID，供应商ID）
    private String viewedFlag; //查看标识（Y：已查看，N：未查看）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date viewDate; //查看时间
    private String tableName; //关联功能的表名
    private Integer tableId; //关联功能的表ID
    private String tableIdName; //关联功能的表ID字段名
    private String functionUrl; //关联功能的链接
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

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@Id
	@SequenceGenerator(name = "SRM_BASE_NOTIFICATIONS_S", sequenceName = "SRM_BASE_NOTIFICATIONS_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_BASE_NOTIFICATIONS_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "notification_id", nullable = false, length = 11)	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationCode(String notificationCode) {
		this.notificationCode = notificationCode;
	}

	@Column(name = "notification_code", nullable = false, length = 30)	
	public String getNotificationCode() {
		return notificationCode;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	@Column(name = "notification_status", nullable = false, length = 30)	
	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	@Column(name = "notification_type", nullable = false, length = 30)	
	public String getNotificationType() {
		return notificationType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "menu_type", nullable = true, length = 30)	
	public String getMenuType() {
		return menuType;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	@Column(name = "notification_content", nullable = true, length = 2000)	
	public String getNotificationContent() {
		return notificationContent;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name = "receiver_id", nullable = true, length = 11)	
	public Integer getReceiverId() {
		return receiverId;
	}

	public void setViewedFlag(String viewedFlag) {
		this.viewedFlag = viewedFlag;
	}

	@Column(name = "viewed_flag", nullable = true, length = 1)	
	public String getViewedFlag() {
		return viewedFlag;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	@Column(name = "view_date", nullable = true, length = 0)	
	public Date getViewDate() {
		return viewDate;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "table_name", nullable = true, length = 240)	
	public String getTableName() {
		return tableName;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	@Column(name = "table_id", nullable = true, length = 11)	
	public Integer getTableId() {
		return tableId;
	}

	public void setTableIdName(String tableIdName) {
		this.tableIdName = tableIdName;
	}

	@Column(name = "table_id_name", nullable = true, length = 240)	
	public String getTableIdName() {
		return tableIdName;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	@Column(name = "function_url", nullable = true, length = 240)	
	public String getFunctionUrl() {
		return functionUrl;
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
