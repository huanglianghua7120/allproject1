package saaf.common.fmw.pon.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonPaymentTermsEntity_HI_RO Entity Object
 * Thu May 10 17:03:43 CST 2018  Auto Generate
 */

public class SrmPonPaymentTermsEntity_HI_RO {

	public static final String QUERY_PAYMENTERMSLIST_SQL="SELECT\n"
			+ "\tt.payment_term_id AS paymentTermId,\n"
			+ "\tt.payment_term_code AS paymentTermCode,\n"
			+ "\tt.payment_term_name AS paymentTermName,\n"
			+ "\tt.payment_term_status AS paymentTermStatus,\n"
			+ "\tt.version_num AS versionNum,\n"
			+ "\tt.creation_date AS creationDate,\n"
			+ "\tt.created_by AS createdBy,\n"
			+ "\tt.last_updated_by AS lastUpdatedBy,\n"
			+ "\tt.last_update_date AS lastUpdateDate,\n"
			+ "\tt.last_update_login AS lastUpdateLogin,\n"
			+ "\tt.attribute_category AS attributeCategory,\n"
			+ "\tt.attribute1 AS attribute1,\n"
			+ "\tt.attribute2 AS attribute2,\n"
			+ "\tt.attribute3 AS attribute3,\n"
			+ "\tt.attribute4 AS attribute4,\n"
			+ "\tt.attribute5 AS attribute5,\n"
			+ "\tt.attribute6 AS attribute6,\n"
			+ "\tt.attribute7 AS attribute7,\n"
			+ "\tt.attribute8 AS attribute8,\n"
			+ "\tt.attribute9 AS attribute9,\n"
			+ "\tt.attribute10 AS attribute10,\n"
			+ "\tslv.meaning AS paymentTermStatusName,\n"
			+ "\tse.employee_name AS createdByEmployeeName,\n"
			+ "\tse2.employee_name AS lastUpdatedByEmployeeName\n"
			+ "FROM\n"
			+ "\tsrm_pon_payment_terms t\n"
			+ "LEFT JOIN saaf_lookup_values slv ON slv.lookup_type = 'PON_OFFER_STATUS'\n"
			+ "AND slv.lookup_code = t.payment_term_status\n"
			+ "LEFT JOIN saaf_employees se ON se.user_id = t.created_by\n"
			+ "LEFT JOIN saaf_employees se2 ON se2.user_id = t.last_updated_by\n"
			+ "WHERE\n"
			+ "\t1 = 1\n";
	private Integer paymentTermId; //付款条件ID
    private String paymentTermCode; //付款条件编号
    private String paymentTermName; //付款条件名称
    private String paymentTermStatus; //状态
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd")
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

    //别名
	private String createdByEmployeeName;//创建人EmployeeName
	private String lastUpdatedByEmployeeName;//最后更新人EmployeeName
	private String paymentTermStatusName; //状态别名name

	public String getPaymentTermStatusName() {
		return paymentTermStatusName;
	}

	public void setPaymentTermStatusName(String paymentTermStatusName) {
		this.paymentTermStatusName = paymentTermStatusName;
	}

	public String getCreatedByEmployeeName() {
		return createdByEmployeeName;
	}

	public void setCreatedByEmployeeName(String createdByEmployeeName) {
		this.createdByEmployeeName = createdByEmployeeName;
	}

	public String getLastUpdatedByEmployeeName() {
		return lastUpdatedByEmployeeName;
	}

	public void setLastUpdatedByEmployeeName(String lastUpdatedByEmployeeName) {
		this.lastUpdatedByEmployeeName = lastUpdatedByEmployeeName;
	}

	public Integer getPaymentTermId() {
		return paymentTermId;
	}

	public void setPaymentTermId(Integer paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	public void setPaymentTermCode(String paymentTermCode) {
		this.paymentTermCode = paymentTermCode;
	}

	
	public String getPaymentTermCode() {
		return paymentTermCode;
	}

	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}

	
	public String getPaymentTermName() {
		return paymentTermName;
	}

	public void setPaymentTermStatus(String paymentTermStatus) {
		this.paymentTermStatus = paymentTermStatus;
	}

	
	public String getPaymentTermStatus() {
		return paymentTermStatus;
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
