package saaf.common.fmw.common.model.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * EcpVoucheridControlEntity_HI Entity Object
 * Thu Apr 20 11:14:21 CST 2017  Auto Generate
 */
@Entity
@Table(name = "ecp_voucherid_control")
public class EcpVoucheridControlEntity_HI {
    private Integer rulesId;
    private Integer customerId;
    private Integer orgId;
    private Integer siteId;
    private Integer organizationId;
    private String voucherTable;
    private String voucheridPrefix;
    private String currentVoucherType;
    private Integer voucheridLength;
    private Integer currentVoucherid;
    private Integer repeatTimes;
    private String status;
    private String voucherStatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;
    private Integer createdBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
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
    private String attribute11;
    private String attribute12;
    private String attribute13;
    private String attribute14;
    private String attribute15;
    private Integer controlId;

    @Id
    @GeneratedValue
    @Column(name = "control_id", nullable = false, length = 11)
    public Integer getControlId() {
        return controlId;
    }

    public void setControlId(Integer controlId) {
        this.controlId = controlId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    @Column(name = "rules_id", precision = 22, scale = 0)
    public Integer getRulesId() {
        return rulesId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "customer_id", precision = 22, scale = 0)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Column(name = "org_id", precision = 22, scale = 0)
    public Integer getOrgId() {
        return orgId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Column(name = "site_id", precision = 22, scale = 0)
    public Integer getSiteId() {
        return siteId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "organization_id", precision = 22, scale = 0)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setVoucherTable(String voucherTable) {
        this.voucherTable = voucherTable;
    }

    @Column(name = "voucher_table", nullable = false, length = 30)
    public String getVoucherTable() {
        return voucherTable;
    }

    public void setVoucheridPrefix(String voucheridPrefix) {
        this.voucheridPrefix = voucheridPrefix;
    }

    @Column(name = "voucherid_prefix", nullable = true, length = 30)
    public String getVoucheridPrefix() {
        return voucheridPrefix;
    }

    public void setCurrentVoucherType(String currentVoucherType) {
        this.currentVoucherType = currentVoucherType;
    }

    @Column(name = "current_voucher_type", nullable = true, length = 30)
    public String getCurrentVoucherType() {
        return currentVoucherType;
    }

    public void setVoucheridLength(Integer voucheridLength) {
        this.voucheridLength = voucheridLength;
    }

    @Column(name = "voucherid_length", precision = 22, scale = 0)
    public Integer getVoucheridLength() {
        return voucheridLength;
    }

    public void setCurrentVoucherid(Integer currentVoucherid) {
        this.currentVoucherid = currentVoucherid;
    }

    @Column(name = "current_voucherid", precision = 22, scale = 0)
    public Integer getCurrentVoucherid() {
        return currentVoucherid;
    }

    public void setRepeatTimes(Integer repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    @Column(name = "repeat_times", precision = 22, scale = 0)
    public Integer getRepeatTimes() {
        return repeatTimes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status", nullable = true, length = 30)
    public String getStatus() {
        return status;
    }

    public void setVoucherStatus(String voucherStatus) {
        this.voucherStatus = voucherStatus;
    }

    @Column(name = "voucher_status", nullable = true, length = 30)
    public String getVoucherStatus() {
        return voucherStatus;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    @Column(name = "start_date_active", nullable = true, length = 0)
    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    @Column(name = "end_date_active", nullable = true, length = 0)
    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_by", precision = 22, scale = 0)
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "creation_date", nullable = false, length = 0)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "last_updated_by", precision = 22, scale = 0)
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

    @Column(name = "last_update_login", precision = 22, scale = 0)
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

    @Column(name = "attribute1", nullable = true, length = 150)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Column(name = "attribute2", nullable = true, length = 150)
    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Column(name = "attribute3", nullable = true, length = 150)
    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Column(name = "attribute4", nullable = true, length = 150)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Column(name = "attribute5", nullable = true, length = 150)
    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Column(name = "attribute6", nullable = true, length = 150)
    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @Column(name = "attribute7", nullable = true, length = 150)
    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    @Column(name = "attribute8", nullable = true, length = 150)
    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    @Column(name = "attribute9", nullable = true, length = 150)
    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    @Column(name = "attribute10", nullable = true, length = 150)
    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11 = attribute11;
    }

    @Column(name = "attribute11", nullable = true, length = 150)
    public String getAttribute11() {
        return attribute11;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12 = attribute12;
    }

    @Column(name = "attribute12", nullable = true, length = 150)
    public String getAttribute12() {
        return attribute12;
    }

    public void setAttribute13(String attribute13) {
        this.attribute13 = attribute13;
    }

    @Column(name = "attribute13", nullable = true, length = 150)
    public String getAttribute13() {
        return attribute13;
    }

    public void setAttribute14(String attribute14) {
        this.attribute14 = attribute14;
    }

    @Column(name = "attribute14", nullable = true, length = 150)
    public String getAttribute14() {
        return attribute14;
    }

    public void setAttribute15(String attribute15) {
        this.attribute15 = attribute15;
    }

    @Column(name = "attribute15", nullable = true, length = 150)
    public String getAttribute15() {
        return attribute15;
    }
}

