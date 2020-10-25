package saaf.common.fmw.oilgasstation.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SaafOilGasStationEntity_HI Entity Object
 * Thu Oct 19 10:49:24 CST 2017  Auto Generate
 */
@Entity
@Table(name = "saaf_oil_gas_station")
public class SaafOilGasStationEntity_HI {
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

	@Id	
	@GeneratedValue	
	@Column(name = "gas_id", nullable = false, length = 11)	
	public Integer getGasId() {
		return gasId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "address", nullable = true, length = 1000)	
	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "city", nullable = true, length = 100)	
	public String getCity() {
		return city;
	}

	public void setDetailurlBd(String detailurlBd) {
		this.detailurlBd = detailurlBd;
	}

	@Column(name = "detailurl_bd", nullable = true, length = 500)	
	public String getDetailurlBd() {
		return detailurlBd;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	@Column(name = "phonenumber", nullable = true, length = 50)	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPointLat(String pointLat) {
		this.pointLat = pointLat;
	}

	@Column(name = "point_lat", nullable = true, length = 10)	
	public String getPointLat() {
		return pointLat;
	}

	public void setPointLng(String pointLng) {
		this.pointLng = pointLng;
	}

	@Column(name = "point_lng", nullable = true, length = 10)	
	public String getPointLng() {
		return pointLng;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "postcode", nullable = true, length = 50)	
	public String getPostcode() {
		return postcode;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "province", nullable = true, length = 50)	
	public String getProvince() {
		return province;
	}

	public void setTags0(String tags0) {
		this.tags0 = tags0;
	}

	@Column(name = "tags0", nullable = true, length = 100)	
	public String getTags0() {
		return tags0;
	}

	public void setTags1(String tags1) {
		this.tags1 = tags1;
	}

	@Column(name = "tags1", nullable = true, length = 100)	
	public String getTags1() {
		return tags1;
	}

	public void setTags2(String tags2) {
		this.tags2 = tags2;
	}

	@Column(name = "tags2", nullable = true, length = 100)	
	public String getTags2() {
		return tags2;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "title", nullable = true, length = 200)	
	public String getTitle() {
		return title;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "uid", nullable = true, length = 64)	
	public String getUid() {
		return uid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "url", nullable = true, length = 500)	
	public String getUrl() {
		return url;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Column(name = "creation_date", nullable = true, length = 0)	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", nullable = true, length = 11)	
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

	@Column(name = "last_update_date", nullable = true, length = 0)	
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

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	@Version
	@Column(name = "version_num", nullable = true, length = 11)	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
