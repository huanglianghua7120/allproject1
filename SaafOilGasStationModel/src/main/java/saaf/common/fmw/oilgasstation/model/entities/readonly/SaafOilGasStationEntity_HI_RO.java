package saaf.common.fmw.oilgasstation.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SaafOilGasStationEntity_HI_RO Entity Object
 * Thu Oct 19 10:49:24 CST 2017  Auto Generate
 */

public class SaafOilGasStationEntity_HI_RO {
	
	public static final String QUERY_UID_SQL = "select uid from saaf_oil_gas_station where 1=1 ";
	
	public static final String QUERY_GAS_STATION_SQL = " SELECT " +
			" 	saafOilGasStation.gas_id AS gasId , " +
			" 	saafOilGasStation.address AS address , " +
			" 	saafOilGasStation.city AS city , " +
			" 	saafOilGasStation.created_by AS createdBy , " +
			" 	saafOilGasStation.creation_date AS creationDate , " +
			" 	saafOilGasStation.detailurl_bd AS detailurlBd , " +
			" 	saafOilGasStation.last_update_date AS lastUpdateDate , " +
			" 	saafOilGasStation.last_update_login AS lastUpdateLogin , " +
			" 	saafOilGasStation.last_updated_by AS lastUpdatedBy , " +
			" 	saafOilGasStation.phonenumber AS phonenumber , " +
			" 	saafOilGasStation.point_lat AS pointLat , " +
			" 	saafOilGasStation.point_lng AS pointLng , " +
			" 	saafOilGasStation.postcode AS postcode , " +
			" 	saafOilGasStation.province AS province , " +
			" 	saafOilGasStation.tags0 AS tags0 , " +
			" 	saafOilGasStation.tags1 AS tags1 , " +
			" 	saafOilGasStation.tags2 AS tags2 , " +
			" 	saafOilGasStation.title AS title , " +
			" 	saafOilGasStation.uid AS uid , " +
			" 	saafOilGasStation.url AS url , " +
			" 	saafOilGasStation.version_num AS versionNum " +
			" FROM " +
			" 	saaf_oil_gas_station saafOilGasStation " +
			" WHERE 1=1 ";
	
    private Integer gasId;
    private String address; //地址
    private String city; //所属城市
    private String detailurlBd; //百度地图详细地址
    private String phonenumber; //联系电话
    private String pointLat; //地图的经度
    private String pointLng; //地图的维度
    private String postcode; //邮编
    private String province; //所属省份
    private String tags0; //用途
    private String tags1; //关键字
    private String tags2; //所属单位
    private String title; //标题
    private String uid; //百度地图唯一标识
    private String url; //地图标识地址
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private Integer versionNum;
    private Integer operatorUserId;

	public void setGasId(Integer gasId) {
		this.gasId = gasId;
	}

	
	public Integer getGasId() {
		return gasId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getCity() {
		return city;
	}

	public void setDetailurlBd(String detailurlBd) {
		this.detailurlBd = detailurlBd;
	}

	
	public String getDetailurlBd() {
		return detailurlBd;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPointLat(String pointLat) {
		this.pointLat = pointLat;
	}

	
	public String getPointLat() {
		return pointLat;
	}

	public void setPointLng(String pointLng) {
		this.pointLng = pointLng;
	}

	
	public String getPointLng() {
		return pointLng;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	
	public String getPostcode() {
		return postcode;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	
	public String getProvince() {
		return province;
	}

	public void setTags0(String tags0) {
		this.tags0 = tags0;
	}

	
	public String getTags0() {
		return tags0;
	}

	public void setTags1(String tags1) {
		this.tags1 = tags1;
	}

	
	public String getTags1() {
		return tags1;
	}

	public void setTags2(String tags2) {
		this.tags2 = tags2;
	}

	
	public String getTags2() {
		return tags2;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getTitle() {
		return title;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
	public String getUid() {
		return uid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getUrl() {
		return url;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
