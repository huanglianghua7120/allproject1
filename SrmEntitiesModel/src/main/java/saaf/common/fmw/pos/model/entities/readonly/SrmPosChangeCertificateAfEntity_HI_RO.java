package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosChangeCertificateAfEntity_HI_RO Entity Object
 * Fri Mar 16 15:32:25 CST 2018  Auto Generate
 */

public class SrmPosChangeCertificateAfEntity_HI_RO {
    private Integer changeCertificateAfId; //认证证书变更后ID
    private Integer changeId; //变更ID，关联表:srm_pos_change_info
    private Integer certificateId; //供应商证书ID，关联表:srm_pos_supplier_certificate
    private String certificateName; //证书名称
    private String certificateNumber; //证书编号
    private String certificateDescription; //证书说明
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //证书生效日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //证书失效日期
    private String longCertificateIndate; //证书是否长期有效
    private String dueToFreeze; //是否到期冻结
    private Integer fileId; //附件id
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

	public void setChangeCertificateAfId(Integer changeCertificateAfId) {
		this.changeCertificateAfId = changeCertificateAfId;
	}

	
	public Integer getChangeCertificateAfId() {
		return changeCertificateAfId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	
	public Integer getChangeId() {
		return changeId;
	}

	public void setCertificateId(Integer certificateId) {
		this.certificateId = certificateId;
	}

	
	public Integer getCertificateId() {
		return certificateId;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	
	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	
	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateDescription(String certificateDescription) {
		this.certificateDescription = certificateDescription;
	}

	
	public String getCertificateDescription() {
		return certificateDescription;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	public void setLongCertificateIndate(String longCertificateIndate) {
		this.longCertificateIndate = longCertificateIndate;
	}

	
	public String getLongCertificateIndate() {
		return longCertificateIndate;
	}

	public void setDueToFreeze(String dueToFreeze) {
		this.dueToFreeze = dueToFreeze;
	}

	
	public String getDueToFreeze() {
		return dueToFreeze;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	
	public Integer getFileId() {
		return fileId;
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
}
