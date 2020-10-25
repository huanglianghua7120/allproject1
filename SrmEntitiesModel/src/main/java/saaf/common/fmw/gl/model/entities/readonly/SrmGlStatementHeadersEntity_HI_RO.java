package saaf.common.fmw.gl.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmGlStatementHeadersEntity_HI_RO implements Serializable{

    public static String QUERY_STATEMENT_LIST_SQL="SELECT\n" +
            "\tsgsh.statement_id statementId,\n" +
            "\tsgsh.statement_number statementNumber,\n" +
            "\tsgsh.bill_status billStatus,\n" +
            "\tsgsh.statement_status statementStatus,\n" +
            "\tsgsh.currency_code currencyCode,\n" +
            "\tsgsh.supplier_id supplierId,\n" +
            "\tsgsh.total_amount totalAmount,\n" +
            "\tsgsh.operator_id operatorId,\n" +
            "\tsgsh.description description,\n" +
            "\tsgsh.creation_date creationDate,\n" +
            "\tspsi.supplier_name supplierName,\n" +
            "\tspsi.supplier_number supplierNumber,\n" +
            "\tslv1.meaning billStatusName,\n" +
            "\tslv2.meaning statementStatusName,\n" +
            "\tslv3.meaning currencyCodeName,\n" +
            "\tsu.user_full_name operatorName\n" +
            "FROM\n" +
            "\tsrm_gl_statement_headers sgsh\n" +
            "LEFT JOIN srm_pos_supplier_info spsi ON sgsh.supplier_id = spsi.supplier_id\n" +
            "LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'YSE_NO'\n" +
            "AND slv1.lookup_code = sgsh.bill_status\n" +
            "LEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_type = 'GL_STATEMENT_STATUS'\n" +
            "AND slv2.lookup_code = sgsh.statement_status\n" +
            "LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_type = 'BANK_CURRENCY'\n" +
            "AND slv3.lookup_code = sgsh.currency_code\n" +
            "LEFT JOIN saaf_users su ON su.user_id = sgsh.operator_id\n" +
            "WHERE\n" +
            "\t1 = 1 ";

    public static String QUERY_STATEMENT_LOV_SQL="SELECT\n" +
            "\tsgsh.statement_id statementId,\n" +
            "\tsgsh.statement_number statementNumber\n" +
            "FROM\n" +
            "\tsrm_gl_statement_headers sgsh\n" +
            "WHERE\n" +
            "\t1 = 1 ";

    private Integer statementId;
    private String statementNumber;
    private String statementStatusName;
    private String statementStatus;
    private Integer supplierId;
    private String supplierName;
    private String supplierNumber;
    private String currencyCode;
    private String currencyCodeName;
    private BigDecimal totalAmount;
    private Integer operatorId;
    private String operatorName;
    private String billStatus;
    private String billStatusName;
    private String description;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;

    public Integer getStatementId() {
        return statementId;
    }

    public void setStatementId(Integer statementId) {
        this.statementId = statementId;
    }

    public String getStatementNumber() {
        return statementNumber;
    }

    public void setStatementNumber(String statementNumber) {
        this.statementNumber = statementNumber;
    }

    public String getStatementStatusName() {
        return statementStatusName;
    }

    public void setStatementStatusName(String statementStatusName) {
        this.statementStatusName = statementStatusName;
    }

    public String getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(String statementStatus) {
        this.statementStatus = statementStatus;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCodeName() {
        return currencyCodeName;
    }

    public void setCurrencyCodeName(String currencyCodeName) {
        this.currencyCodeName = currencyCodeName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillStatusName() {
        return billStatusName;
    }

    public void setBillStatusName(String billStatusName) {
        this.billStatusName = billStatusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
