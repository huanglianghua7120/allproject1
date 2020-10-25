package saaf.common.fmw.pon.model.entities.readonly;

import java.math.BigDecimal;
import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPonPaymentTermNodesEntity_HI_RO Entity Object
 * Thu May 10 17:03:42 CST 2018  Auto Generate
 */

public class SrmPonPaymentTermNodesEntity_HI_RO {

	public static final String QUERYPAYMENTTERMNODESLIST_SQL =
					"SELECT\n" +
					"  pptn.term_code_id AS termCodeId,\n" +
					"  pptn.payment_term_code AS paymentTermCode,\n" +
					"  pptn.line_number AS lineNumber,\n" +
					"  pptn.payment_term_node AS paymentTermNode,\n" +
					"  pptn.bias_days AS biasDays,\n" +
					"  pptn.payment_proportion AS paymentProportion,\n" +
					"  pptn.version_num AS versionNum,\n" +
					"  pptn.creation_date AS creationDate,\n" +
					"  pptn.created_by AS createdBy,\n" +
					"  pptn.last_updated_by AS lastUpdatedBy,\n" +
					"  pptn.last_update_date AS lastUpdateDate,\n" +
					"  pptn.last_update_login AS lastUpdateLogin,\n" +
					"  pptn.attribute_category AS attributeCategory,\n" +
					"  pptn.attribute1 AS attribute1,\n" +
					"  pptn.attribute2 AS attribute2,\n" +
					"  pptn.attribute3 AS attribute3,\n" +
					"  pptn.attribute4 AS attribute4,\n" +
					"  pptn.attribute5 AS attribute5,\n" +
					"  pptn.attribute6 AS attribute6,\n" +
					"  pptn.attribute7 AS attribute7,\n" +
					"  pptn.attribute8 AS attribute8,\n" +
					"  pptn.attribute9 AS attribute9,\n" +
					"  pptn.attribute10 AS attribute10,\n" +
					"  ppt.payment_term_id AS paymentTermId\n" +
					"FROM\n" +
					"  srm_pon_payment_term_nodes pptn,\n" +
					"  srm_pon_payment_terms ppt\n" +
					"WHERE pptn.payment_term_code = ppt.payment_term_code";

	private Integer termCodeId; //付款节点ID
	private Integer paymentTermId;
    private String paymentTermCode; //付款条件编号
    private Integer lineNumber; //行号
    private String paymentTermNode; //付款节点
    private Integer biasDays; //偏置天数
    private BigDecimal paymentProportion; //付款比例
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

	public void setTermCodeId(Integer termCodeId) {
		this.termCodeId = termCodeId;
	}

	public Integer getTermCodeId() {
		return termCodeId;
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

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setPaymentTermNode(String paymentTermNode) {
		this.paymentTermNode = paymentTermNode;
	}

	public String getPaymentTermNode() {
		return paymentTermNode;
	}

	public void setBiasDays(Integer biasDays) {
		this.biasDays = biasDays;
	}
	
	public Integer getBiasDays() {
		return biasDays;
	}

	public void setPaymentProportion(BigDecimal paymentProportion) {
		this.paymentProportion = paymentProportion;
	}
	
	public BigDecimal getPaymentProportion() {
		return paymentProportion;
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
