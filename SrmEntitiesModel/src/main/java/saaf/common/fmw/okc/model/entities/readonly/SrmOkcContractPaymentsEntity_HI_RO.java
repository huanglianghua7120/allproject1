package saaf.common.fmw.okc.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class SrmOkcContractPaymentsEntity_HI_RO implements Serializable {

    public static String SRM_OKC_CONTRACT_PAYMENTS_QUERY_SQL =
                    "SELECT\n" +
                    "  t.payment_stage_id AS paymentStageId,\n" +
                    "  t.contract_id AS contractId,\n" +
                    "  t.payment_stage_name AS paymentStageName,\n" +
                    "  t.payment_stage_explain AS paymentStageExplain,\n" +
                    "  t.payment_proportion AS paymentProportion,\n" +
                    "  t.estimate_payment_date AS estimatePaymentDate,\n" +
                    "  t.warning_advance_days AS warningAdvanceDays,\n" +
                    "  t.payable_amount AS payableAmount,\n" +
                    "  t.paid_amount AS paidAmount,\n" +
                    "  t.payment_completed_flag AS paymentCompletedFlag,\n" +
                    "  t.version_num AS versionNum,\n" +
                    "  t.creation_date AS creationDate,\n" +
                    "  t.created_by AS createdBy,\n" +
                    "  t.last_update_date AS lastUpdateDate,\n" +
                    "  t.last_updated_by AS lastUpdatedBy,\n" +
                    "  t.last_update_login AS lastUpdateLogin,\n" +
                    "  t.attribute_category AS attributeCategory,\n" +
                    "  t.attribute1 AS attribute1,\n" +
                    "  t.attribute2 AS attribute2,\n" +
                    "  t.attribute3 AS attribute3,\n" +
                    "  t.attribute4 AS attribute4,\n" +
                    "  t.attribute5 AS attribute5,\n" +
                    "  t.attribute6 AS attribute6,\n" +
                    "  t.attribute7 AS attribute7,\n" +
                    "  t.attribute8 AS attribute8,\n" +
                    "  t.attribute9 AS attribute9,\n" +
                    "  t.attribute10 AS attribute10,\n" +
                    "  t.attribute11 AS attribute11,\n" +
                    "  t.attribute12 AS attribute12,\n" +
                    "  t.attribute13 AS attribute13,\n" +
                    "  t.attribute14 AS attribute14,\n" +
                    "  t.attribute15 AS attribute15,\n" +
                    "  t.attribute16 AS attribute16,\n" +
                    "  t.attribute17 AS attribute17,\n" +
                    "  t.attribute18 AS attribute18,\n" +
                    "  t.attribute19 AS attribute19,\n" +
                    "  t.attribute20 AS attribute20,\n" +
                    "  t.attribute21 AS attribute21,\n" +
                    "  t.attribute22 AS attribute22,\n" +
                    "  t.attribute23 AS attribute23,\n" +
                    "  t.attribute24 AS attribute24,\n" +
                    "  t.attribute25 AS attribute25,\n" +
                    "  t.attribute26 AS attribute26,\n" +
                    "  t.attribute27 AS attribute27,\n" +
                    "  t.attribute28 AS attribute28,\n" +
                    "  t.attribute29 AS attribute29,\n" +
                    "  t.attribute30 AS attribute30\n" +
                    "FROM\n" +
                    "  srm_okc_contract_payments t\n" +
                    "WHERE t.contract_id = :contractId";

    private Integer paymentStageId;

	private Integer contractId;

	private String paymentStageName;

	private String paymentStageExplain;

	private BigDecimal paymentProportion;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date estimatePaymentDate;

	private Integer warningAdvanceDays;

	private BigDecimal payableAmount;

	private BigDecimal paidAmount;

	private String paymentCompletedFlag;

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


    public SrmOkcContractPaymentsEntity_HI_RO(){
        super();
    }


    public Integer  getPaymentStageId() {
        return paymentStageId;
    }

    public void setPaymentStageId( Integer paymentStageId) {
        this.paymentStageId = paymentStageId;
    }


    public Integer  getContractId() {
        return contractId;
    }

    public void setContractId( Integer contractId) {
        this.contractId = contractId;
    }


    public String  getPaymentStageName() {
        return paymentStageName;
    }

    public void setPaymentStageName( String paymentStageName) {
        this.paymentStageName = paymentStageName;
    }


    public String  getPaymentStageExplain() {
        return paymentStageExplain;
    }

    public void setPaymentStageExplain( String paymentStageExplain) {
        this.paymentStageExplain = paymentStageExplain;
    }


    public BigDecimal  getPaymentProportion() {
        return paymentProportion;
    }

    public void setPaymentProportion( BigDecimal paymentProportion) {
        this.paymentProportion = paymentProportion;
    }


    public Date  getEstimatePaymentDate() {
        return estimatePaymentDate;
    }

    public void setEstimatePaymentDate( Date estimatePaymentDate) {
        this.estimatePaymentDate = estimatePaymentDate;
    }


    public Integer  getWarningAdvanceDays() {
        return warningAdvanceDays;
    }

    public void setWarningAdvanceDays( Integer warningAdvanceDays) {
        this.warningAdvanceDays = warningAdvanceDays;
    }


    public BigDecimal  getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount( BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }


    public BigDecimal  getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount( BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }


    public String  getPaymentCompletedFlag() {
        return paymentCompletedFlag;
    }

    public void setPaymentCompletedFlag( String paymentCompletedFlag) {
        this.paymentCompletedFlag = paymentCompletedFlag;
    }


    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }


    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }


    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }


    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }


    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }


    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }


    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }


    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }


    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }


    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }


    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }


    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }


    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }


    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }


    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }


    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }


    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }


    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }


    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }


    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }


    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }


    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }


    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }


    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }


    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }


    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }


    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }


    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }


    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }


    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }


    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }


    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }


    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }


    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
    }

}
