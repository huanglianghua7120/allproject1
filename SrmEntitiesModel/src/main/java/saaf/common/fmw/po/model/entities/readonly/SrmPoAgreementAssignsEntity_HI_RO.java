package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class SrmPoAgreementAssignsEntity_HI_RO implements Serializable{

    public static String QUERY_COUNT="SELECT\n" +
            "  count(*) as count\n" +
            "FROM\n" +
            "  srm_po_agreement_assigns t\n" +
            "WHERE t.po_header_id = :poHeaderId\n" +
            "  AND t.org_id = :orgId\n" +
            "  AND t.bill_to_organization_id = :billToOrganizationId\n" +
            "  AND t.receive_to_organization_id = :receiveToOrganizationId\r\n";

    public static String QUERY_PO_AGREEMENT_ASSIGNS_INFO="SELECT\n" +
            "  paa.agreement_assign_id agreementAssignId,\n" +
            "  paa.po_header_id poHeaderId,\n" +
            "  paa.org_id orgId,\n" +
            "  paa.bill_to_organization_id billToOrganizationId,\n" +
            "  paa.receive_to_organization_id receiveToOrganizationId\n" +
            "FROM\n" +
            "  srm_po_agreement_assigns paa\n" +
            "WHERE paa.po_header_id = :poHeaderId\n" +
            "  AND paa.org_id = :orgId\r\n";


    private Integer agreementAssignId;
    private Integer poHeaderId;
    private Integer orgId;
    private Integer billToOrganizationId;
    private Integer receiveToOrganizationId;
    private String defaultFlag;
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
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAgreementAssignId() {
        return agreementAssignId;
    }

    public void setAgreementAssignId(Integer agreementAssignId) {
        this.agreementAssignId = agreementAssignId;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getBillToOrganizationId() {
        return billToOrganizationId;
    }

    public void setBillToOrganizationId(Integer billToOrganizationId) {
        this.billToOrganizationId = billToOrganizationId;
    }

    public Integer getReceiveToOrganizationId() {
        return receiveToOrganizationId;
    }

    public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
        this.receiveToOrganizationId = receiveToOrganizationId;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
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

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }
}
