package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierAddressesEntity_HI_RO Entity Object
 * Fri Mar 09 15:32:48 CST 2018  Auto Generate
 */

public class SrmPosSupplierAddressesEntity_HI_RO {
	/**
	 * 查询供应商的地址
	 */
	public static String QUERY_ADDRESS =
					"SELECT DISTINCT t.supplier_address_id AS supplierAddressId\n" +
					"               ,t.supplier_id         AS supplier_id\n" +
					"               ,t.address_name        AS address_name\n" +
					"               ,t.country             AS country\n" +
					"               ,t.province            AS province\n" +
					"               ,t.city                AS city\n" +
					"               ,t.county              AS county\n" +
					"               ,t.detail_address      AS detail_address\n" +
					"               ,t.invalid_date        AS invalid_date\n" +
					"               ,t.source_id           AS sourceId\n" +
					"               ,t.source_code         AS sourceCode\n" +
					"               ,t.version_num         AS version_num\n" +
					"               ,t.creation_date       AS creation_date\n" +
					"               ,t.created_by          AS created_by\n" +
					"               ,t.last_updated_by     AS last_updated_by\n" +
					"               ,t.last_update_date    AS last_update_date\n" +
					"               ,t.last_update_login   AS last_update_login\n" +
					"               ,t.attribute_category  AS attribute_category\n" +
//					"               ,si.selected_flag      AS selectedFlag\n" +
					"               ,t.attribute1          AS attribute1\n" +
					"               ,t.attribute2          AS attribute2\n" +
					"               ,t.attribute3          AS attribute3\n" +
					"               ,t.attribute4          AS attribute4\n" +
					"               ,t.attribute5          AS attribute5\n" +
					"               ,t.attribute6          AS attribute6\n" +
					"               ,t.attribute7          AS attribute7\n" +
					"               ,t.attribute8          AS attribute8\n" +
					"               ,t.attribute9          AS attribute9\n" +
					"               ,t.attribute10         AS attribute10\n" +
					"               ,slv.meaning           AS country_name\n" +
					"  FROM srm_pos_supplier_addresses t\n" +
					"  LEFT JOIN srm_pos_supplier_sites b\n" +
					"    ON b.supplier_address_id = t.supplier_address_id\n" +
					"   AND b.supplier_id = t.supplier_id\n" +
//					"  LEFT JOIN (SELECT b.selected_flag\n" +
//					"                   ,a.supplier_address_id,b.frozen_id\n" +
//					"               FROM srm_pos_supplier_sites a\n" +
//					"                   ,srm_pos_frozen_sites b\n" +
//					"              WHERE a.supplier_site_id = b.supplier_site_id) si\n" +
//					"    ON si.supplier_address_id = t.supplier_address_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_COUNTRY'\n" +
					"   AND t.country = slv.lookup_code\n" +
//					"  LEFT JOIN srm_pos_locale_review_sites splrs\n" +
//					"    ON splrs.supplier_site_id = b.supplier_site_id\n" +
//					"  LEFT JOIN srm_pos_locale_reviews splr\n" +
//					"    ON splrs.locale_review_id = splr.locale_review_id\n" +
					" WHERE 1 = 1\n";

	public static String QUERY_ADDRESS_FROZEN_SQL =
					"SELECT b.selected_flag       selectedFlag\n" +
					"      ,a.supplier_address_id supplierAddressId\n" +
					"FROM   srm_pos_supplier_sites     a\n" +
					"      ,srm_pos_frozen_sites       b\n" +
					"      ,srm_pos_supplier_addresses c\n" +
					"WHERE  a.supplier_site_id = b.supplier_site_id\n" +
					"AND    a.supplier_address_id = c.supplier_address_id\n" +
					"AND    b.selected_flag = 'Y'";
	
//	public static String QUERY_ADDRESS_FROZEN =
//			"SELECT DISTINCT t.supplier_address_id AS supplier_address_id\n" +
//			"               ,t.supplier_id         AS supplier_id\n" +
//			"               ,t.address_name        AS address_name\n" +
//			"               ,t.country             AS country\n" +
//			"               ,t.province            AS province\n" +
//			"               ,t.city                AS city\n" +
//			"               ,t.county              AS county\n" +
//			"               ,t.detail_address      AS detail_address\n" +
//			"               ,t.invalid_date        AS invalid_date\n" +
//			"               ,t.source_id           AS sourceId\n" +
//			"               ,t.source_code         AS sourceCode\n" +
//			"               ,t.version_num         AS version_num\n" +
//			"               ,t.creation_date       AS creation_date\n" +
//			"               ,t.created_by          AS created_by\n" +
//			"               ,t.last_updated_by     AS last_updated_by\n" +
//			"               ,t.last_update_date    AS last_update_date\n" +
//			"               ,t.last_update_login   AS last_update_login\n" +
//			"               ,t.attribute_category  AS attribute_category\n" +
//			"               ,si.selected_flag      AS selectedFlag\n" +
//			"               ,si.frozen_site_id     AS frozenSiteId\n" +
//			"               ,t.attribute1          AS attribute1\n" +
//			"               ,t.attribute2          AS attribute2\n" +
//			"               ,t.attribute3          AS attribute3\n" +
//			"               ,t.attribute4          AS attribute4\n" +
//			"               ,t.attribute5          AS attribute5\n" +
//			"               ,t.attribute6          AS attribute6\n" +
//			"               ,t.attribute7          AS attribute7\n" +
//			"               ,t.attribute8          AS attribute8\n" +
//			"               ,t.attribute9          AS attribute9\n" +
//			"               ,t.attribute10         AS attribute10\n" +
//			"               ,slv.meaning           AS country_name\n" +
//			"  FROM srm_pos_supplier_addresses t\n" +
//			"  LEFT JOIN srm_pos_supplier_sites b\n" +
//			"    ON b.supplier_address_id = t.supplier_address_id\n" +
//			"   AND b.supplier_id = t.supplier_id\n" +
//			"  LEFT JOIN (SELECT b.selected_flag\n" +
//			"                   ,a.supplier_address_id,b.frozen_id,b.frozen_site_id\n" +
//			"               FROM srm_pos_supplier_sites a\n" +
//			"                   ,srm_pos_frozen_sites b\n" +
//			"              WHERE a.supplier_site_id = b.supplier_site_id) si\n" +
//			"    ON si.supplier_address_id = t.supplier_address_id\n" +
//			"  LEFT JOIN saaf_lookup_values slv\n" +
//			"    ON slv.lookup_type = 'BASE_COUNTRY'\n" +
//			"   AND t.country = slv.lookup_code\n" +
//			" WHERE 1 = 1";

	private Integer supplierAddressId; //供应商地址ID
	private Integer supplierId; //供应商ID
	private String addressName; //地址名称
	private String country; //国家(BASE_COUNTRY)
	private String province; //省(州)
	private String city; //城市
	private String county; //县(区)
	private String detailAddress; //详细地址
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date invalidDate; //失效时间
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
	private Integer frozenSiteId;

    private String countryName;//国家别名
    private String selectedFlag;//是否冻结

	public Integer getFrozenSiteId() {
		return frozenSiteId;
	}

	public void setFrozenSiteId(Integer frozenSiteId) {
		this.frozenSiteId = frozenSiteId;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setSupplierAddressId(Integer supplierAddressId) {
		this.supplierAddressId = supplierAddressId;
	}


	public Integer getSupplierAddressId() {
		return supplierAddressId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}


	public Integer getSupplierId() {
		return supplierId;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}


	public String getAddressName() {
		return addressName;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getCountry() {
		return country;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	public String getProvince() {
		return province;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public String getCity() {
		return city;
	}

	public void setCounty(String county) {
		this.county = county;
	}


	public String getCounty() {
		return county;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	public String getDetailAddress() {
		return detailAddress;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}


	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}


	public Integer getVersionNum() {
		return versionNum;
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

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}


	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}


	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}


	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}


	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}


	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}


	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}


	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}


	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}


	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}


	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}


	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}


	public Integer getOperatorUserId() {
		return operatorUserId;
	}

	public String getSelectedFlag() {
		return selectedFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}
	
}
