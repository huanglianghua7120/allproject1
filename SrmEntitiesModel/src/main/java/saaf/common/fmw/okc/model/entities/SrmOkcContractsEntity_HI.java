package saaf.common.fmw.okc.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "srm_okc_contracts")
public class SrmOkcContractsEntity_HI {

	private Integer contractId;

	private String contractCode;

	private String contractName;

	private String contractType;

	private Integer partyAId;

	private Integer partyBId;

	private Integer partyCId;

	private String contractStatus;

	private String contractApprovalStatus;

	private Integer handleDepartmentId;

	private String mainContractFlag;

	private String transferPoFlag;

	private Integer versionNumber;

	private String createdMode;

	private Integer templateId;

	private Integer watermarkId;

	private Integer paymentTermId;

	private BigDecimal totalAmount;

	private BigDecimal paidAmount;

	private String currencyCode;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyASignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyBSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date partyCSignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	private Integer mainContractId;

	private String signedSite;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date terminationDate;

	private String terminationReasons;

	private String comments;

	private String sourceCode;

    private String sourceId;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

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

	private String attribute11;

	private String attribute12;

	private String attribute13;

	private String attribute14;

	private String attribute15;

	private Integer attribute16;

	private Integer attribute17;

	private Integer attribute18;

	private Integer attribute19;

	private Integer attribute20;

	private BigDecimal attribute21;

	private BigDecimal attribute22;

	private BigDecimal attribute23;

	private BigDecimal attribute24;

	private BigDecimal attribute25;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;

    private Integer operatorUserId;

    private String ekpNumber;

    private BigDecimal receivedAmount;

	@Id
    @SequenceGenerator(name = "SRM_OKC_CONTRACTS_S", sequenceName = "SRM_OKC_CONTRACTS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_OKC_CONTRACTS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "contract_id")
    public Integer  getContractId() {
        return contractId;
    }

    public void setContractId( Integer contractId) {
        this.contractId = contractId;
    }

    @Column(name = "received_amount")
    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    @Column(name = "ekp_number", nullable = true, length = 240)
    public String getEkpNumber() {
        return ekpNumber;
    }

    public void setEkpNumber(String ekpNumber) {
        this.ekpNumber = ekpNumber;
    }

    @Column(name = "contract_code")
    public String  getContractCode() {
        return contractCode;
    }

    public void setContractCode( String contractCode) {
        this.contractCode = contractCode;
    }

    @Column(name = "contract_name")
    public String  getContractName() {
        return contractName;
    }

    public void setContractName( String contractName) {
        this.contractName = contractName;
    }

    @Column(name = "contract_type")
    public String  getContractType() {
        return contractType;
    }

    public void setContractType( String contractType) {
        this.contractType = contractType;
    }

    @Column(name = "party_a_id")
    public Integer  getPartyAId() {
        return partyAId;
    }

    public void setPartyAId( Integer partyAId) {
        this.partyAId = partyAId;
    }

    @Column(name = "party_b_id")
    public Integer  getPartyBId() {
        return partyBId;
    }

    public void setPartyBId( Integer partyBId) {
        this.partyBId = partyBId;
    }

    @Column(name = "party_c_id")
    public Integer  getPartyCId() {
        return partyCId;
    }

    public void setPartyCId( Integer partyCId) {
        this.partyCId = partyCId;
    }

    @Column(name = "contract_status")
    public String  getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus( String contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Column(name = "contract_approval_status")
    public String  getContractApprovalStatus() {
        return contractApprovalStatus;
    }

    public void setContractApprovalStatus( String contractApprovalStatus) {
        this.contractApprovalStatus = contractApprovalStatus;
    }

    @Column(name = "handle_department_id")
    public Integer  getHandleDepartmentId() {
        return handleDepartmentId;
    }

    public void setHandleDepartmentId( Integer handleDepartmentId) {
        this.handleDepartmentId = handleDepartmentId;
    }

    @Column(name = "main_contract_flag")
    public String  getMainContractFlag() {
        return mainContractFlag;
    }

    public void setMainContractFlag( String mainContractFlag) {
        this.mainContractFlag = mainContractFlag;
    }

    @Column(name = "transfer_po_flag")
    public String  getTransferPoFlag() {
        return transferPoFlag;
    }

    public void setTransferPoFlag( String transferPoFlag) {
        this.transferPoFlag = transferPoFlag;
    }

    @Column(name = "version_number")
    public Integer  getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber( Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Column(name = "created_mode")
    public String  getCreatedMode() {
        return createdMode;
    }

    public void setCreatedMode( String createdMode) {
        this.createdMode = createdMode;
    }

    @Column(name = "template_id")
    public Integer  getTemplateId() {
        return templateId;
    }

    public void setTemplateId( Integer templateId) {
        this.templateId = templateId;
    }

    @Column(name = "watermark_id")
    public Integer  getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId( Integer watermarkId) {
        this.watermarkId = watermarkId;
    }

    @Column(name = "payment_term_id")
    public Integer  getPaymentTermId() {
        return paymentTermId;
    }

    public void setPaymentTermId( Integer paymentTermId) {
        this.paymentTermId = paymentTermId;
    }

    @Column(name = "total_amount")
    public BigDecimal  getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount( BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "paid_amount")
    public BigDecimal  getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount( BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Column(name = "currency_code")
    public String  getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode( String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Column(name = "party_a_sign_date")
    public Date  getPartyASignDate() {
        return partyASignDate;
    }

    public void setPartyASignDate( Date partyASignDate) {
        this.partyASignDate = partyASignDate;
    }

    @Column(name = "party_b_sign_date")
    public Date  getPartyBSignDate() {
        return partyBSignDate;
    }

    public void setPartyBSignDate( Date partyBSignDate) {
        this.partyBSignDate = partyBSignDate;
    }

    @Column(name = "party_c_sign_date")
    public Date  getPartyCSignDate() {
        return partyCSignDate;
    }

    public void setPartyCSignDate( Date partyCSignDate) {
        this.partyCSignDate = partyCSignDate;
    }

    @Column(name = "start_date")
    public Date  getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date  getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "main_contract_id")
    public Integer  getMainContractId() {
        return mainContractId;
    }

    public void setMainContractId( Integer mainContractId) {
        this.mainContractId = mainContractId;
    }

    @Column(name = "signed_site")
    public String  getSignedSite() {
        return signedSite;
    }

    public void setSignedSite( String signedSite) {
        this.signedSite = signedSite;
    }

    @Column(name = "termination_date")
    public Date  getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate( Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    @Column(name = "termination_reasons")
    public String  getTerminationReasons() {
        return terminationReasons;
    }

    public void setTerminationReasons( String terminationReasons) {
        this.terminationReasons = terminationReasons;
    }

    @Column(name = "comments")
    public String  getComments() {
        return comments;
    }

    public void setComments( String comments) {
        this.comments = comments;
    }

    @Column(name = "source_code")
    public String  getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode( String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(name = "source_id")
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "version_num")
    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }

    @Column(name = "creation_date")
    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "created_by")
    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "last_update_date")
    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "last_updated_by")
    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_update_login")
    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Column(name = "attribute_category")
    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    @Column(name = "attribute1")
    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }

    @Column(name = "attribute2")
    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }

    @Column(name = "attribute3")
    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }

    @Column(name = "attribute4")
    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }

    @Column(name = "attribute5")
    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(name = "attribute6")
    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }

    @Column(name = "attribute7")
    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }

    @Column(name = "attribute8")
    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }

    @Column(name = "attribute9")
    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }

    @Column(name = "attribute10")
    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }

    @Column(name = "attribute11")
    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }

    @Column(name = "attribute12")
    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }

    @Column(name = "attribute13")
    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }

    @Column(name = "attribute14")
    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }

    @Column(name = "attribute15")
    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }

    @Column(name = "attribute16")
    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }

    @Column(name = "attribute17")
    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }

    @Column(name = "attribute18")
    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }

    @Column(name = "attribute19")
    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }

    @Column(name = "attribute20")
    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }

    @Column(name = "attribute21")
    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }

    @Column(name = "attribute22")
    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }

    @Column(name = "attribute23")
    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }

    @Column(name = "attribute24")
    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }

    @Column(name = "attribute25")
    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }

    @Column(name = "attribute26")
    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }

    @Column(name = "attribute27")
    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }

    @Column(name = "attribute28")
    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }

    @Column(name = "attribute29")
    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }

    @Column(name = "attribute30")
    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
