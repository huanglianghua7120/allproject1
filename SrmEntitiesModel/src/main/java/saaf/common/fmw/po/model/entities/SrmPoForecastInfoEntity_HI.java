package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import java.math.BigDecimal;

/**
 * SrmPoForecastInfoEntity_HI Entity Object
 * Mon Apr 09 11:34:02 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_forecast_info")
public class SrmPoForecastInfoEntity_HI {
    private Integer forecastId; //预测ID
    private String forecastName; //预测名称
    private Integer orgId; //业务实体ID
    private Integer organizationId; //库存组织ID
    private String predictionType; //预测类型
    private String forecastStatus; //预测状态
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate; //失效日期
    private Integer invalidBy; //失效人
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date releaseDate; //（废弃）发布日期
    private String categoryName; //（废弃）类别名称
    private String categoryCode; //（废弃）类别编码
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date forecastDate; //（废弃）预测日期
    private String itemCode; //（废弃）物料编码
    private String itemDescription; //（废弃）物料名称
    private Integer employeeId; //（废弃）采购员
    private BigDecimal needQuantity; //（废弃）需求数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date needByDate; //（废弃）需求日期
    private String vendnameGroup; //（废弃）供应商组合
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
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

	public void setForecastId(Integer forecastId) {
		this.forecastId = forecastId;
	}

	@Id
	@SequenceGenerator(name = "SRM_PO_FORECAST_INFO_S", sequenceName = "SRM_PO_FORECAST_INFO_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_PO_FORECAST_INFO_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "forecast_id", nullable = false, length = 11)	
	public Integer getForecastId() {
		return forecastId;
	}

	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}

	@Column(name = "forecast_name", nullable = false, length = 240)	
	public String getForecastName() {
		return forecastName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = true, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "organization_id", nullable = true, length = 11)	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setPredictionType(String predictionType) {
		this.predictionType = predictionType;
	}

	@Column(name = "prediction_type", nullable = true, length = 30)	
	public String getPredictionType() {
		return predictionType;
	}

	public void setForecastStatus(String forecastStatus) {
		this.forecastStatus = forecastStatus;
	}

	@Column(name = "forecast_status", nullable = true, length = 30)	
	public String getForecastStatus() {
		return forecastStatus;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	@Column(name = "invalid_date", nullable = true, length = 0)	
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidBy(Integer invalidBy) {
		this.invalidBy = invalidBy;
	}

	@Column(name = "invalid_by", nullable = true, length = 11)	
	public Integer getInvalidBy() {
		return invalidBy;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "release_date", nullable = true, length = 0)	
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "category_name", nullable = true, length = 300)	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_code", nullable = false, length = 50)	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	@Column(name = "forecast_date", nullable = false, length = 0)	
	public Date getForecastDate() {
		return forecastDate;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@Column(name = "item_code", nullable = false, length = 50)	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Column(name = "item_description", nullable = true, length = 240)	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "employee_id", nullable = true, length = 11)	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}

	@Column(name = "need_quantity", precision = 10, scale = 2)	
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

	public void setVendnameGroup(String vendnameGroup) {
		this.vendnameGroup = vendnameGroup;
	}

	@Column(name = "vendname_group", nullable = true, length = 3000)	
	public String getVendnameGroup() {
		return vendnameGroup;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_code", nullable = true, length = 30)	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_id", nullable = true, length = 30)	
	public String getSourceId() {
		return sourceId;
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
