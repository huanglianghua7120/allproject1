package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosChangeBankEntity_HI Entity Object
 * Thu Dec 07 18:32:17 CST 2017  Auto Generate
 */
@Entity
@Table(name = "srm_pos_change_bank")
public class SrmPosChangeBankEntity_HI {
    private Integer changeBankId;
    private Integer changeId;
    private Integer bankAccountIdBf;
    private Integer supplierSiteIdBf;
    private String assignmentTypeBf;
    private String bankAccountNumberBf;
    private String bankUserNameBf;
    private Integer bankIdBf;
    private String bankNameBf;
    private Integer branchIdBf;
    private String branchNumberBf;
    private String branchNameBf;
    private String bankCurrencyBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDateBf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDateAf;
    private Integer bankAccountIdAf;
    private Integer supplierSiteIdAf;
    private String assignmentTypeAf;
    private String bankAccountNumberAf;
    private String bankUserNameAf;
    private Integer bankIdAf;
    private String bankNameAf;
    private Integer branchIdAf;
    private String branchNumberAf;
    private String branchNameAf;
    private String bankCurrencyAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateAf;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateAf;
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

    public void setChangeBankId(Integer changeBankId) {
        this.changeBankId = changeBankId;
    }

    @Id
    @SequenceGenerator(name = "SRM_POS_CHANGE_BANK_S", sequenceName = "SRM_POS_CHANGE_BANK_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_POS_CHANGE_BANK_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "change_bank_id", nullable = false, length = 11)
    public Integer getChangeBankId() {
        return changeBankId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    @Column(name = "change_id", nullable = true, length = 11)
    public Integer getChangeId() {
        return changeId;
    }

    public void setBankAccountIdBf(Integer bankAccountIdBf) {
        this.bankAccountIdBf = bankAccountIdBf;
    }

    @Column(name = "bank_account_id_bf", nullable = true, length = 11)
    public Integer getBankAccountIdBf() {
        return bankAccountIdBf;
    }

    public void setSupplierSiteIdBf(Integer supplierSiteIdBf) {
        this.supplierSiteIdBf = supplierSiteIdBf;
    }

    @Column(name = "supplier_site_id_bf", nullable = true, length = 11)
    public Integer getSupplierSiteIdBf() {
        return supplierSiteIdBf;
    }

    public void setAssignmentTypeBf(String assignmentTypeBf) {
        this.assignmentTypeBf = assignmentTypeBf;
    }

    @Column(name = "assignment_type_bf", nullable = true, length = 30)
    public String getAssignmentTypeBf() {
        return assignmentTypeBf;
    }

    public void setBankAccountNumberBf(String bankAccountNumberBf) {
        this.bankAccountNumberBf = bankAccountNumberBf;
    }

    @Column(name = "bank_account_number_bf", nullable = true, length = 100)
    public String getBankAccountNumberBf() {
        return bankAccountNumberBf;
    }

    public void setBankUserNameBf(String bankUserNameBf) {
        this.bankUserNameBf = bankUserNameBf;
    }

    @Column(name = "bank_user_name_bf", nullable = true, length = 100)
    public String getBankUserNameBf() {
        return bankUserNameBf;
    }

    public void setBankIdBf(Integer bankIdBf) {
        this.bankIdBf = bankIdBf;
    }

    @Column(name = "bank_id_bf", nullable = true, length = 11)
    public Integer getBankIdBf() {
        return bankIdBf;
    }

    public void setBankNameBf(String bankNameBf) {
        this.bankNameBf = bankNameBf;
    }

    @Column(name = "bank_name_bf", nullable = true, length = 200)
    public String getBankNameBf() {
        return bankNameBf;
    }

    public void setBranchIdBf(Integer branchIdBf) {
        this.branchIdBf = branchIdBf;
    }

    @Column(name = "branch_id_bf", nullable = true, length = 11)
    public Integer getBranchIdBf() {
        return branchIdBf;
    }

    public void setBranchNumberBf(String branchNumberBf) {
        this.branchNumberBf = branchNumberBf;
    }

    @Column(name = "branch_number_bf", nullable = true, length = 200)
    public String getBranchNumberBf() {
        return branchNumberBf;
    }

    public void setBranchNameBf(String branchNameBf) {
        this.branchNameBf = branchNameBf;
    }

    @Column(name = "branch_name_bf", nullable = true, length = 200)
    public String getBranchNameBf() {
        return branchNameBf;
    }

    public void setBankCurrencyBf(String bankCurrencyBf) {
        this.bankCurrencyBf = bankCurrencyBf;
    }

    @Column(name = "bank_currency_bf", nullable = true, length = 30)
    public String getBankCurrencyBf() {
        return bankCurrencyBf;
    }

    public void setStartDateBf(Date startDateBf) {
        this.startDateBf = startDateBf;
    }

    @Column(name = "start_date_bf", nullable = true, length = 0)
    public Date getStartDateBf() {
        return startDateBf;
    }

    public void setEndDateBf(Date endDateBf) {
        this.endDateBf = endDateBf;
    }

    @Column(name = "end_date_bf", nullable = true, length = 0)
    public Date getEndDateBf() {
        return endDateBf;
    }

    public void setBankAccountIdAf(Integer bankAccountIdAf) {
        this.bankAccountIdAf = bankAccountIdAf;
    }

    @Column(name = "bank_account_id_af", nullable = true, length = 11)
    public Integer getBankAccountIdAf() {
        return bankAccountIdAf;
    }

    public void setSupplierSiteIdAf(Integer supplierSiteIdAf) {
        this.supplierSiteIdAf = supplierSiteIdAf;
    }

    @Column(name = "supplier_site_id_af", nullable = true, length = 11)
    public Integer getSupplierSiteIdAf() {
        return supplierSiteIdAf;
    }

    public void setAssignmentTypeAf(String assignmentTypeAf) {
        this.assignmentTypeAf = assignmentTypeAf;
    }

    @Column(name = "assignment_type_af", nullable = true, length = 30)
    public String getAssignmentTypeAf() {
        return assignmentTypeAf;
    }

    public void setBankAccountNumberAf(String bankAccountNumberAf) {
        this.bankAccountNumberAf = bankAccountNumberAf;
    }

    @Column(name = "bank_account_number_af", nullable = true, length = 100)
    public String getBankAccountNumberAf() {
        return bankAccountNumberAf;
    }

    public void setBankUserNameAf(String bankUserNameAf) {
        this.bankUserNameAf = bankUserNameAf;
    }

    @Column(name = "bank_user_name_af", nullable = true, length = 100)
    public String getBankUserNameAf() {
        return bankUserNameAf;
    }

    public void setBankIdAf(Integer bankIdAf) {
        this.bankIdAf = bankIdAf;
    }

    @Column(name = "bank_id_af", nullable = true, length = 11)
    public Integer getBankIdAf() {
        return bankIdAf;
    }

    public void setBankNameAf(String bankNameAf) {
        this.bankNameAf = bankNameAf;
    }

    @Column(name = "bank_name_af", nullable = true, length = 200)
    public String getBankNameAf() {
        return bankNameAf;
    }

    public void setBranchIdAf(Integer branchIdAf) {
        this.branchIdAf = branchIdAf;
    }

    @Column(name = "branch_id_af", nullable = true, length = 11)
    public Integer getBranchIdAf() {
        return branchIdAf;
    }

    public void setBranchNumberAf(String branchNumberAf) {
        this.branchNumberAf = branchNumberAf;
    }

    @Column(name = "branch_number_af", nullable = true, length = 200)
    public String getBranchNumberAf() {
        return branchNumberAf;
    }

    public void setBranchNameAf(String branchNameAf) {
        this.branchNameAf = branchNameAf;
    }

    @Column(name = "branch_name_af", nullable = true, length = 200)
    public String getBranchNameAf() {
        return branchNameAf;
    }

    public void setBankCurrencyAf(String bankCurrencyAf) {
        this.bankCurrencyAf = bankCurrencyAf;
    }

    @Column(name = "bank_currency_af", nullable = true, length = 30)
    public String getBankCurrencyAf() {
        return bankCurrencyAf;
    }

    public void setStartDateAf(Date startDateAf) {
        this.startDateAf = startDateAf;
    }

    @Column(name = "start_date_af", nullable = true, length = 0)
    public Date getStartDateAf() {
        return startDateAf;
    }

    public void setEndDateAf(Date endDateAf) {
        this.endDateAf = endDateAf;
    }

    @Column(name = "end_date_af", nullable = true, length = 0)
    public Date getEndDateAf() {
        return endDateAf;
    }


    @Column(name = "invalid_date_bf", nullable = true, length = 0)
    public Date getInvalidDateBf() {
        return invalidDateBf;
    }

    public void setInvalidDateBf(Date invalidDateBf) {
        this.invalidDateBf = invalidDateBf;
    }

    @Column(name = "invalid_date_af", nullable = true, length = 0)
    public Date getInvalidDateAf() {
        return invalidDateAf;
    }

    public void setInvalidDateAf(Date invalidDateAf) {
        this.invalidDateAf = invalidDateAf;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

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

    private Integer operatorUserId;
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    @Transient
    public Integer getOperatorUserId() {
        return operatorUserId;
    }
}

