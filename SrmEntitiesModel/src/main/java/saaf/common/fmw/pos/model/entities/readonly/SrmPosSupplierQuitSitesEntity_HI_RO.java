package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosSupplierQuitSitesEntity_HI_RO Entity Object
 * Fri Mar 16 10:49:40 CST 2018  Auto Generate
 */

public class SrmPosSupplierQuitSitesEntity_HI_RO {

	public static String QUERY_SITES="SELECT\n" +
			"\tt.supplier_quit_site_id AS supplierQuitSiteId,\n" +
			"\tt.supplier_quit_id AS supplierQuitId,\n" +
			"\tt.supplier_site_id AS supplierSiteId,\n" +
			"\tt.supplier_address_id AS supplierAddressId,\n" +
			"\tt.site_name AS siteName,\n" +
			"\tt.org_id AS orgId,\n" +
			"\tt.site_status AS siteStatus,\n" +
			"\tt.purchase_flag AS purchaseFlag,\n" +
			"\tt.payment_flag AS paymentFlag,\n" +
			"\tt.froze_flag AS frozeFlag,\n" +
			"\tt.unfroze_date AS unfrozeDate,\n" +
			"\tt.qualified_date AS qualifiedDate,\n" +
			"\tt.invalid_date AS invalidDate,\n" +
			"\tt.temporary_site_flag AS temporarySiteFlag,\n" +
			"\tt.selected_flag AS selectedFlag,\n" +
			"\tt.version_num AS versionNum,\n" +
			"\tt.creation_date AS creationDate,\n" +
			"\tt.created_by AS createdBy,\n" +
			"\tt.last_updated_by AS lastUpdatedBy,\n" +
			"\tt.last_update_date AS lastUpdateDate,\n" +
			"\tt.last_update_login AS lastUpdateLogin,\n" +
			"\tt.attribute_category AS attributeCategory,\n" +
			"\tt.attribute1 AS attribute1,\n" +
			"\tt.attribute2 AS attribute2,\n" +
			"\tt.attribute3 AS attribute3,\n" +
			"\tt.attribute4 AS attribute4,\n" +
			"\tt.attribute5 AS attribute5,\n" +
			"\tt.attribute6 AS attribute6,\n" +
			"\tt.attribute7 AS attribute7,\n" +
			"\tt.attribute8 AS attribute8,\n" +
			"\tt.attribute9 AS attribute9,\n" +
			"\tt.attribute10 AS attribute10,\n" +
			"\tsi.inst_name AS instName,\n" +
			"\tslv1.meaning AS siteStatusName,\n" +
			"\tslv2.meaning AS temporarySiteFlagName\n" +
			"FROM\n" +
			"\tsrm_pos_supplier_quit_sites t\n" +
			"LEFT JOIN saaf_institution si ON si.inst_id = t.org_id\n" +
			"LEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
			"LEFT JOIN saaf_lookup_values slv2 ON slv1.lookup_type = 'YSE_NO'\n" +
			"AND t.site_status = slv1.lookup_code,\n" +
			"AND t.temporary_site_flag = slv2.lookup_code\n";

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
	private String instName;
	private String siteStatusName;
	private String temporarySiteFlagName;

	public void setSupplierQuitSiteId(Integer supplierQuitSiteId) {
		this.supplierQuitSiteId = supplierQuitSiteId;
	}

	
	public Integer getSupplierQuitSiteId() {
		return supplierQuitSiteId;
	}

	public void setSupplierQuitId(Integer supplierQuitId) {
		this.supplierQuitId = supplierQuitId;
	}

	
	public Integer getSupplierQuitId() {
		return supplierQuitId;
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

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getSiteStatusName() {
		return siteStatusName;
	}

	public void setSiteStatusName(String siteStatusName) {
		this.siteStatusName = siteStatusName;
	}

	public String getTemporarySiteFlagName() {
		return temporarySiteFlagName;
	}

	public void setTemporarySiteFlagName(String temporarySiteFlagName) {
		this.temporarySiteFlagName = temporarySiteFlagName;
	}

	public Integer getOperatorUserId() {
		return operatorUserId;
	}
}
