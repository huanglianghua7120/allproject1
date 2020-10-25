package saaf.common.fmw.intf.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmIntfForecastsEntity_HI Entity Object Tue Dec 12 18:53:31 CST 2017 Auto
 * Generate
 */
@Entity
@Table(name = "SRM_INTF_FORECASTS")
public class SrmIntfForecastsEntity_HI {
	private Integer intfId;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date forecastDate;
	private String forecastName;
	private String forecastType;
	private String forecastCode;
	private String itemCode;
	private String itemDescription;
	private BigDecimal needQuantity;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date needByDate;
	private String employeeNumber;
	private String handleStatus;
	private String handleMsg;
	private Integer logId;
	private String sourceType;
	private String batchId;
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

	public void setIntfId(Integer intfId) {
		this.intfId = intfId;
	}

	@Id
	@GeneratedValue
	@Column(name = "intf_id", nullable = false, length = 11)
	public Integer getIntfId() {
		return intfId;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	@Column(name = "forecast_date", nullable = true, length = 0)
	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}

	@Column(name = "forecast_name", nullable = true, length = 100)
	public String getForecastName() {
		return forecastName;
	}

	public void setForecastType(String forecastType) {
		this.forecastType = forecastType;
	}

	@Column(name = "forecast_type", nullable = true, length = 100)
	public String getForecastType() {
		return forecastType;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "item_code", nullable = true, length = 100)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "item_description", nullable = true, length = 100)
	public String getItemDescription() {
		return itemDescription;
	}

	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}

	@Column(name = "need_quantity", precision = 20, scale = 6)
	public BigDecimal getNeedQuantity() {
		return needQuantity;
	}

	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}

	@Column(name = "need_by_date", nullable = true, length = 0)
	public Date getNeedByDate() {
		return needByDate;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	@Column(name = "employee_number", nullable = true, length = 100)
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	@Column(name = "handle_status", nullable = true, length = 30)
	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	@Column(name = "handle_msg", nullable = true, length = 0)
	public String getHandleMsg() {
		return handleMsg;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Column(name = "log_id", nullable = true, length = 11)
	public Integer getLogId() {
		return logId;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "source_type", nullable = true, length = 30)
	public String getSourceType() {
		return sourceType;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Column(name = "batch_id", nullable = true, length = 100)
	public String getBatchId() {
		return batchId;
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

	private Integer operatorUserId;

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient
	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	@Column(name = "forecast_code", nullable = true, length = 300)
	public String getForecastCode() {
		return forecastCode;
	}

	public void setForecastCode(String forecastCode) {
		this.forecastCode = forecastCode;
	}
}
