package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosLocaleReviewSitesEntity_HI_RO Entity Object
 * Tue Mar 20 14:33:39 CST 2018  Auto Generate
 */

public class SrmPosLocaleReviewSitesEntity_HI_RO {
    private Integer localeReviewSiteId; //现场考察地点ID
    private Integer localeReviewId; //现场考察ID，关联表:srm_pos_locale_reviews
    private Integer supplierSiteId; //供应商地点ID
    private Integer supplierAddressId; //供应商地址ID
    private String siteName; //地点名称
    private Integer orgId; //业务实体ID(关联saaf_institution)
    private String siteStatus; //地点状态(POS_SUPPLIER_SITE_STATUS)
    private String purchaseFlag; //可采购(Y/N)
    private String paymentFlag; //可付款(Y/N)
    private String frozeFlag; //已冻结(Y/N)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date unfrozeDate; //解冻时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date qualifiedDate; //合格时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate; //失效时间
    private String temporarySiteFlag; //临时地点标识
    private String selectedFlag; //勾选标识(Y/N)
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

	public void setLocaleReviewSiteId(Integer localeReviewSiteId) {
		this.localeReviewSiteId = localeReviewSiteId;
	}

	
	public Integer getLocaleReviewSiteId() {
		return localeReviewSiteId;
	}

	public void setLocaleReviewId(Integer localeReviewId) {
		this.localeReviewId = localeReviewId;
	}

	
	public Integer getLocaleReviewId() {
		return localeReviewId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierAddressId(Integer supplierAddressId) {
		this.supplierAddressId = supplierAddressId;
	}

	
	public Integer getSupplierAddressId() {
		return supplierAddressId;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	
	public String getSiteName() {
		return siteName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	
	public String getSiteStatus() {
		return siteStatus;
	}

	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}

	
	public String getPurchaseFlag() {
		return purchaseFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	
	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setFrozeFlag(String frozeFlag) {
		this.frozeFlag = frozeFlag;
	}

	
	public String getFrozeFlag() {
		return frozeFlag;
	}

	public void setUnfrozeDate(Date unfrozeDate) {
		this.unfrozeDate = unfrozeDate;
	}

	
	public Date getUnfrozeDate() {
		return unfrozeDate;
	}

	public void setQualifiedDate(Date qualifiedDate) {
		this.qualifiedDate = qualifiedDate;
	}

	
	public Date getQualifiedDate() {
		return qualifiedDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setTemporarySiteFlag(String temporarySiteFlag) {
		this.temporarySiteFlag = temporarySiteFlag;
	}

	
	public String getTemporarySiteFlag() {
		return temporarySiteFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	
	public String getSelectedFlag() {
		return selectedFlag;
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
