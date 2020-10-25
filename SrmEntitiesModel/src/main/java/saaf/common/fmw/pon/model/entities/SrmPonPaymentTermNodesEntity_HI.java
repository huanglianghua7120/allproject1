package saaf.common.fmw.pon.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPonPaymentTermNodesEntity_HI Entity Object
 * Mon May 14 08:56:47 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pon_payment_term_nodes")
public class SrmPonPaymentTermNodesEntity_HI {
	private Integer termCodeId; //付款节点ID
	private String paymentTermCode; //付款条件编号
	private Integer lineNumber; //行号
	private String paymentTermNode; //付款节点
	private Integer biasDays; //偏置天数
	private BigDecimal paymentProportion; //付款比例
	private String sourceId;//数据来源ID
	private String sourceCode;//数据来源
	private String sourceHeaderId;//数据来源头表ID
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

	@Id
	@SequenceGenerator(name="SRM_PON_PAYMENT_TERM_NODES_S", sequenceName="SRM_PON_PAYMENT_TERM_NODES_S", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="SRM_PON_PAYMENT_TERM_NODES_S",strategy=GenerationType.SEQUENCE)
	@Column(name = "term_code_id", nullable = false, length = 11)
	public Integer getTermCodeId() {
		return termCodeId;
	}

	public void setPaymentTermCode(String paymentTermCode) {
		this.paymentTermCode = paymentTermCode;
	}

	@Column(name = "payment_term_code", nullable = false, length = 30)
	public String getPaymentTermCode() {
		return paymentTermCode;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Column(name = "line_number", nullable = true, length = 11)
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setPaymentTermNode(String paymentTermNode) {
		this.paymentTermNode = paymentTermNode;
	}

	@Column(name = "payment_term_node", nullable = true, length = 240)
	public String getPaymentTermNode() {
		return paymentTermNode;
	}

	public void setBiasDays(Integer biasDays) {
		this.biasDays = biasDays;
	}

	@Column(name = "bias_days", nullable = true, length = 11)
	public Integer getBiasDays() {
		return biasDays;
	}

	public void setPaymentProportion(BigDecimal paymentProportion) {
		this.paymentProportion = paymentProportion;
	}

	@Column(name = "payment_proportion", precision = 5, scale = 2)
	public BigDecimal getPaymentProportion() {
		return paymentProportion;
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

	@Column(name = "source_code", nullable = true, length = 200)
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "source_id", nullable = true, length = 200)
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "source_header_id", nullable = true, length = 200)
	public String getSourceHeaderId() {
		return sourceHeaderId;
	}

	public void setSourceHeaderId(String sourceHeaderId) {
		this.sourceHeaderId = sourceHeaderId;
	}
}
