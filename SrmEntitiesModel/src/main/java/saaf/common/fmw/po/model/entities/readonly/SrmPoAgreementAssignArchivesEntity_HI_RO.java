package saaf.common.fmw.po.model.entities.readonly;

import javax.persistence.Version;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Transient;

/**
 * SrmPoAgreementAssignsArchivesEntity_HI_RO Entity Object
 * Mon Mar 25 11:11:05 CST 2019  Auto Generate
 */

public class SrmPoAgreementAssignArchivesEntity_HI_RO {
    private Integer agreementAssignArchiveId; //分配归档ID
    private Integer agreementAssignId; //分配ID
    private Integer poArchiveId; //采购订单归档ID
    private Integer poHeaderId; //协议ID
    private Integer orgId; //业务实体ID
    private Integer billToOrganizationId; //收单组织ID
    private Integer receiveToOrganizationId; //收货组织ID
    private String defaultFlag; //默认标识（Y/N）
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

	public void setAgreementAssignArchiveId(Integer agreementAssignArchiveId) {
		this.agreementAssignArchiveId = agreementAssignArchiveId;
	}

	
	public Integer getAgreementAssignArchiveId() {
		return agreementAssignArchiveId;
	}

	public void setAgreementAssignId(Integer agreementAssignId) {
		this.agreementAssignId = agreementAssignId;
	}

	
	public Integer getAgreementAssignId() {
		return agreementAssignId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}

	
	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	
	public String getDefaultFlag() {
		return defaultFlag;
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


	public Integer getPoArchiveId() {
		return poArchiveId;
	}


	public void setPoArchiveId(Integer poArchiveId) {
		this.poArchiveId = poArchiveId;
	}
	
	
}
