package saaf.common.fmw.po.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPoRequisitionHeadersEntity_HI Entity Object
 * Sat Jun 09 16:36:46 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_po_requisition_headers")
public class SrmPoRequisitionHeadersEntity_HI {
    private Integer requisitionHeaderId; //采购申请ID
    private String requisitionNumber; //采购申请编号
    private Integer orgId; //业务实体ID
    private Integer organizationId; //库存组织ID
    private Integer departmentId; //部门ID
    private String requisitionType; //申请类型
    private Integer requisitionEmpId; //申请人ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date requisitionDate; //申请日期
    private String requisitionStatus; //申请状态
    private Integer approvalUserId; //审批用户ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate; //批准时间
    private String sourceNumber; //来源单号
    private String comments; //说明
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
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
    private Integer buyerId;

    public void setRequisitionHeaderId(Integer requisitionHeaderId) {
        this.requisitionHeaderId = requisitionHeaderId;
    }

    @Id
    @SequenceGenerator(name = "SRM_PO_REQUISITION_HEADERS_S", sequenceName = "SRM_PO_REQUISITION_HEADERS_S", allocationSize = 1)
    @GeneratedValue(generator = "SRM_PO_REQUISITION_HEADERS_S", strategy = GenerationType.SEQUENCE)
    @Column(name = "requisition_header_id", nullable = false, length = 11)
    public Integer getRequisitionHeaderId() {
        return requisitionHeaderId;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    @Column(name = "requisition_number", nullable = false, length = 30)
    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Column(name = "org_id", nullable = true, length = 11)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "organization_id", nullable = true, length = 11)
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "department_id", nullable = true, length = 11)
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setRequisitionType(String requisitionType) {
        this.requisitionType = requisitionType;
    }

    @Column(name = "requisition_type", nullable = true, length = 30)
    public String getRequisitionType() {
        return requisitionType;
    }

    public void setRequisitionEmpId(Integer requisitionEmpId) {
        this.requisitionEmpId = requisitionEmpId;
    }

    @Column(name = "requisition_emp_id", nullable = true, length = 11)
    public Integer getRequisitionEmpId() {
        return requisitionEmpId;
    }

    @Column(name = "buyer_id", nullable = true, length = 11)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    @Column(name = "requisition_date", nullable = true, length = 0)
    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionStatus(String requisitionStatus) {
        this.requisitionStatus = requisitionStatus;
    }

    @Column(name = "requisition_status", nullable = true, length = 30)
    public String getRequisitionStatus() {
        return requisitionStatus;
    }

    public void setApprovalUserId(Integer approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    @Column(name = "approval_user_id", nullable = true, length = 11)
    public Integer getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Column(name = "approval_date", nullable = true, length = 0)
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    @Column(name = "source_number", nullable = true, length = 30)
    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "comments", nullable = true, length = 2000)
    public String getComments() {
        return comments;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Column(name = "source_code", nullable = true, length = 30)
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "source_id", nullable = true, length = 30)
    public String getSourceId() {
        return sourceId;
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

    @Column(name = "last_updated_by", nullable = true, length = 11)
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
}
