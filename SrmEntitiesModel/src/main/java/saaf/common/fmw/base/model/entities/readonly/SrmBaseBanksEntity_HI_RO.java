package saaf.common.fmw.base.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmBaseBanksEntity_HI_RO Entity Object
 * Mon Mar 12 14:23:41 CST 2018  Auto Generate
 */

public class SrmBaseBanksEntity_HI_RO {

	/**
	 * 银行查询SQL
	 */
	public static String QUERY_BANK="SELECT\n" +
			"\tt.bank_id AS bankId,\n" +
			"\tt.bank_code AS bankCode,\n" +
			"\tt.bank_name AS bankName,\n" +
			"\tt.country AS country,\n" +
			"\tt.swift_code AS swiftCode,\n" +
			"\tt.version_num AS versionNum,\n" +
			"\tt.creation_date AS creationDate,\n" +
			"\tt.created_by AS createdBy,\n" +
			"\tt.last_updated_by AS lastUpdatedBy,\n" +
			"\tt.last_update_date AS lastUpdateDate,\n" +
			"\tt.last_update_login AS lastUpdateLogin,\n" +
			"\tt.attribute_category AS attributeCategory,\n" +
			"\tt.attribute1 AS attribute1,\n" +
			"\tt.attribute2 AS attribute2,\n" +
			"\tt.attribute3 AS attribute3,\n" +
			"\tt.attribute4 AS attribute4,\n" +
			"\tt.attribute5 AS attribute5,\n" +
			"\tt.attribute6 AS attribute6,\n" +
			"\tt.attribute7 AS attribute7,\n" +
			"\tt.attribute8 AS attribute8,\n" +
			"\tt.attribute9 AS attribute9,\n" +
			"\tt.attribute10 AS attribute10\n" +
			"FROM\n" +
			"\tsrm_base_banks t\n" +
			"WHERE\n" +
			"\t1 = 1 ";


    private Integer bankId; //银行ID
    private String bankCode; //银行编号
    private String bankName; //银行名称
    private String country; //国家
    private String swiftCode; //SWIFT CODE
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

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	
	public Integer getBankId() {
		return bankId;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	public String getBankName() {
		return bankName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getCountry() {
		return country;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	
	public String getSwiftCode() {
		return swiftCode;
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
}
