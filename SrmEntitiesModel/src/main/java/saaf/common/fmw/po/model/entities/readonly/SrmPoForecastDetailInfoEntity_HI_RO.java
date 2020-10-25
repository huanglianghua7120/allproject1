package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPoForecastDetailInfoEntity_HI_RO implements Serializable{
	public static final String QUERY_FORECAST_DETAIL_INFO_SQL = "SELECT"
			+" l.FORECAST_DETAIL_ID forecastDetailId,"
			+" l.FORECAST_ID forecastId,"
			+" l.SUPPLIER_ID supplierId,"
			+" l.SUPPLIER_NMUBER supplierNmuber,"
			+" l.SUPPLIER_NAME supplierName,"
			+" l.VERSION_NUM versionNum,"
			+" l.CREATION_DATE creationDate,"
			+" h.FORECAST_NAME forecastName"
			+" FROM"
			+" srm_po_forecast_detail_info AS l"
			+" inner join srm_po_forecast_info as h on"
			+" l.FORECAST_ID = h.FORECAST_ID"
			+" and "
			+" 1 = 1";
			
	private String forecastName;
	private Integer forecastDetailId;
    private String forecastId;
    private Integer supplierId;
    private String supplierNmuber;
    private String supplierName;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    
	public String getForecastName() {
		return forecastName;
	}
	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}
	public Integer getForecastDetailId() {
		return forecastDetailId;
	}
	public void setForecastDetailId(Integer forecastDetailId) {
		this.forecastDetailId = forecastDetailId;
	}
	public String getForecastId() {
		return forecastId;
	}
	public void setForecastId(String forecastId) {
		this.forecastId = forecastId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierNmuber() {
		return supplierNmuber;
	}
	public void setSupplierNmuber(String supplierNmuber) {
		this.supplierNmuber = supplierNmuber;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}
	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}
	@Override
	public String toString() {
		return "SrmPoSupplyProportionH2Entity_HI_RO [forecastDetailId="
				+ forecastDetailId + ", forecastId=" + forecastId
				+ ", supplierId=" + supplierId + ", supplierNmuber="
				+ supplierNmuber + ", supplierName=" + supplierName
				+ ", versionNum=" + versionNum + ", creationDate="
				+ creationDate + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ "]";
	}
	
	
    

	

}
