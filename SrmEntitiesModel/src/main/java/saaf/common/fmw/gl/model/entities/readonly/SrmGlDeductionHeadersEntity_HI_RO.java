package saaf.common.fmw.gl.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmGlDeductionHeadersEntity_HI_RO implements Serializable{

    public static String QUERY_DEDUCTION_SQL="SELECT\r\n" +
            "dh.deduction_id deductionId\r\n" +
            ",dh.deduction_number deductionNumber\r\n" +
            ",slv.meaning deductionStatus\r\n" +
            ",slv1.meaning transmitStatus\r\n" +
            ",si.supplier_number supplierNumber\r\n" +
            ",si.supplier_name supplierName\r\n" +
            ",slv2.meaning currencyCode\r\n" +
            ",dh.amount_deducted amountDeducted\r\n" +
            ",dh.creation_date creationDate\r\n" +
            ",su.user_full_name userFullName\r\n" +
            "FROM\r\n" +
            "srm_gl_deduction_headers dh\r\n" +
            "LEFT JOIN saaf_lookup_values slv on slv.lookup_code = dh.deduction_status\r\n" +
            "AND slv.lookup_type = 'GL_DEDUCTION_STATUS'\r\n" +
            "LEFT JOIN saaf_lookup_values slv1 on slv.lookup_code = dh.transmit_status\r\n" +
            "AND slv1.lookup_type = 'YSE_NO'\r\n" +
            "LEFT JOIN saaf_lookup_values slv2 on slv.lookup_code = dh.currency_code\r\n" +
            "AND slv2.lookup_type = 'BANK_CURRENCY',\r\n" +
            "srm_pos_supplier_info si,\r\n" +
            "saaf_users su  \r\n" +
            "WHERE\r\n" +
            "dh.supplier_id = si.supplier_id\r\n" +
            "AND dh.created_by = su.user_id";

    public static final String QUERY_DEDUCTION_NUMBER_SQL = "SELECT\n" +
            "\tsgdh.deduction_number deductionNumber,\n" +
            "\tsgdh.deduction_id deductionId\n" +
            "FROM\n" +
            "\tsrm_gl_deduction_headers sgdh\n" +
            "WHERE\n" +
            "\t1 = 1";

    public static final String QUERY_DEDUCTION_CREATED_BY_SQL = "SELECT\n" +
            "\tsgdh.created_by createdBy,\n" +
            "FROM\n" +
            "\tsrm_gl_deduction_headers sgdh\n" +
            "WHERE\n" +
            "\t1 = 1";

    private Integer deductionId;
    private String deductionNumber;
    private String deductionStatus;
    private String supplierNumber;
    private String supplierName;
    private String currencyCode;
    private BigDecimal amountDeducted;
    private BigDecimal amountOfRelief;
    private BigDecimal amountOfActual;
    private String reasonOfRelief;
    private String description;
    private Integer fileId;
    private String transmitStatus;
    @JSONField(format = "yyyy-MM-dd")
    private Date transmitDate;
    private Integer transmitId;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;


    public Integer getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(Integer deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionNumber() {
        return deductionNumber;
    }

    public void setDeductionNumber(String deductionNumber) {
        this.deductionNumber = deductionNumber;
    }

    public String getDeductionStatus() {
        return deductionStatus;
    }

    public void setDeductionStatus(String deductionStatus) {
        this.deductionStatus = deductionStatus;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierId(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmountDeducted() {
        return amountDeducted;
    }

    public void setAmountDeducted(BigDecimal amountDeducted) {
        this.amountDeducted = amountDeducted;
    }

    public BigDecimal getAmountOfRelief() {
        return amountOfRelief;
    }

    public void setAmountOfRelief(BigDecimal amountOfRelief) {
        this.amountOfRelief = amountOfRelief;
    }

    public BigDecimal getAmountOfActual() {
        return amountOfActual;
    }

    public void setAmountOfActual(BigDecimal amountOfActual) {
        this.amountOfActual = amountOfActual;
    }

    public String getReasonOfRelief() {
        return reasonOfRelief;
    }

    public void setReasonOfRelief(String reasonOfRelief) {
        this.reasonOfRelief = reasonOfRelief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getTransmitStatus() {
        return transmitStatus;
    }

    public void setTransmitStatus(String transmitStatus) {
        this.transmitStatus = transmitStatus;
    }

    public Date getTransmitDate() {
        return transmitDate;
    }

    public void setTransmitDate(Date transmitDate) {
        this.transmitDate = transmitDate;
    }

    public Integer getTransmitId() {
        return transmitId;
    }

    public void setTransmitId(Integer transmitId) {
        this.transmitId = transmitId;
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
}
