package saaf.common.fmw.pos.model.entities;

import javax.persistence.*;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierQuitSitesEntity_HI Entity Object
 * Fri Mar 16 10:49:40 CST 2018  Auto Generate
 */
@Entity
@Table(name = "srm_pos_supplier_quit_sites")
public class SrmPosSupplierQuitSitesEntity_HI {
    private Integer supplierQuitSiteId; //供应商地点退出ID
    private Integer supplierQuitId; //供应商退出ID
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

	public void setSupplierQuitSiteId(Integer supplierQuitSiteId) {
		this.supplierQuitSiteId = supplierQuitSiteId;
	}

	@Id
	@SequenceGenerator(name = "SRM_POS_SUPPLIER_QUIT_S", sequenceName = "SRM_POS_SUPPLIER_QUIT_S", allocationSize = 1)
	@GeneratedValue(generator = "SRM_POS_SUPPLIER_QUIT_S", strategy = GenerationType.SEQUENCE)
	@Column(name = "supplier_quit_site_id", nullable = false, length = 11)	
	public Integer getSupplierQuitSiteId() {
		return supplierQuitSiteId;
	}

	public void setSupplierQuitId(Integer supplierQuitId) {
		this.supplierQuitId = supplierQuitId;
	}

	@Column(name = "supplier_quit_id", nullable = false, length = 11)	
	public Integer getSupplierQuitId() {
		return supplierQuitId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	@Column(name = "supplier_site_id", nullable = true, length = 11)	
	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierAddressId(Integer supplierAddressId) {
		this.supplierAddressId = supplierAddressId;
	}

	@Column(name = "supplier_address_id", nullable = true, length = 11)	
	public Integer getSupplierAddressId() {
		return supplierAddressId;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Column(name = "site_name", nullable = true, length = 30)	
	public String getSiteName() {
		return siteName;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "org_id", nullable = false, length = 11)	
	public Integer getOrgId() {
		return orgId;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	@Column(name = "site_status", nullable = true, length = 30)	
	public String getSiteStatus() {
		return siteStatus;
	}

	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}

	@Column(name = "purchase_flag", nullable = true, length = 1)	
	public String getPurchaseFlag() {
		return purchaseFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	@Column(name = "payment_flag", nullable = true, length = 1)	
	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setFrozeFlag(String frozeFlag) {
		this.frozeFlag = frozeFlag;
	}

	@Column(name = "froze_flag", nullable = true, length = 1)	
	public String getFrozeFlag() {
		return frozeFlag;
	}

	public void setUnfrozeDate(Date unfrozeDate) {
		this.unfrozeDate = unfrozeDate;
	}

	@Column(name = "unfroze_date", nullable = true, length = 0)	
	public Date getUnfrozeDate() {
		return unfrozeDate;
	}

	public void setQualifiedDate(Date qualifiedDate) {
		this.qualifiedDate = qualifiedDate;
	}

	@Column(name = "qualified_date", nullable = true, length = 0)	
	public Date getQualifiedDate() {
		return qualifiedDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	@Column(name = "invalid_date", nullable = true, length = 0)	
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setTemporarySiteFlag(String temporarySiteFlag) {
		this.temporarySiteFlag = temporarySiteFlag;
	}

	@Column(name = "temporary_site_flag", nullable = true, length = 1)	
	public String getTemporarySiteFlag() {
		return temporarySiteFlag;
	}

	public void setSelectedFlag(String selectedFlag) {
		this.selectedFlag = selectedFlag;
	}

	@Column(name = "selected_flag", nullable = true, length = 1)	
	public String getSelectedFlag() {
		return selectedFlag;
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

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	@Transient	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
