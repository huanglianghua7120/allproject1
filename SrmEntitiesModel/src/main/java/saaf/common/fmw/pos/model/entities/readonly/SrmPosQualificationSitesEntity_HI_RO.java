package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPosQualificationSitesEntity_HI_RO Entity Object
 * Fri Mar 23 11:52:36 CST 2018  Auto Generate
 */

public class SrmPosQualificationSitesEntity_HI_RO {

    //查询已经选中的站点ID
	public static final String QUERY_SELECTED_SITE_ID = "SELECT\r\n" + //
			"	t.supplier_site_id AS supplierSiteId\r\n" + //
			"FROM\r\n" + //
			"	srm_pos_qualification_sites t\r\n" + //
			"WHERE\r\n" + //
			"	t.add_flag = 'Y'\r\n" + //
			"AND t.qualification_id =:qualificationId";//

	//查询已存在的地点
	public static String QUERY_SITE_BY_A =
					"SELECT pqs.qualification_site_id qualificationSiteId\n" +
					"      ,pqs.qualification_id qualificationId\n" +
					"      ,pqs.supplier_site_id supplierSiteId\n" +
					"      ,pqs.supplier_address_id supplierAddressId\n" +
					"      ,pqs.site_name siteName\n" +
					"      ,pqs.org_id orgId\n" +
					"      ,pqs.site_status siteStatus\n" +
					"      ,pqs.purchase_flag purchaseFlag\n" +
					"      ,(CASE pqs.purchase_flag WHEN 'Y' THEN '是' ELSE '否' END) purchaseFlagStr\n" +
					"      ,pqs.payment_flag paymentFlag\n" +
					"      ,(CASE pqs.payment_flag WHEN 'Y' THEN '是' ELSE '否' END) paymentFlagStr\n" +
					"      ,pqs.froze_flag frozeFlag\n" +
					"      ,(CASE pqs.froze_flag WHEN 'Y' THEN '是' ELSE '否'END) frozeFlagStr\n" +
					"      ,pqs.unfroze_date unfrozeDate\n" +
					"      ,pqs.qualified_date qualifiedDate\n" +
					"      ,pqs.invalid_date invalidDate\n" +
					"      ,pqs.temporary_site_flag temporarySiteFlag\n" +
					"      ,(CASE pqs.temporary_site_flag WHEN 'Y' THEN '是' ELSE '否'END) temporarySiteFlagName\n" +
					"      ,pqs.add_flag addFlag\n" +
					"      ,org.inst_name instName\n" +
					"      ,slv.meaning siteStatusStr\n" +
					"      ,pqs.version_num\n" +
					"      ,pqs.created_by\n" +
					"      ,pqs.creation_date\n" +
					"      ,pqs.last_updated_by\n" +
					"      ,pqs.last_update_date\n" +
					"      ,pqs.last_update_login\n" +
					"  FROM srm_pos_qualification_sites pqs\n" +
					"      ,saaf_institution       org\n" +
					"      ,saaf_lookup_values     slv\n" +
					" WHERE pqs.org_id = org.inst_id\n" +
					"   AND pqs.site_status = slv.lookup_code\n" +
					"   AND slv.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"   AND pqs.add_flag = 'A'\n";

	//查询新增部分的地点
	public static String QUERY_SITE_BY_NOT_A =
					"SELECT pqs.qualification_site_id qualificationSiteId\n" +
					"      ,pqs.qualification_id qualificationId\n" +
					"      ,pqs.supplier_site_id supplierSiteId\n" +
					"      ,pqs.supplier_address_id supplierAddressId\n" +
					"      ,pqs.site_name siteName\n" +
					"      ,pqs.org_id orgId\n" +
					"      ,pqs.site_status siteStatus\n" +
					"      ,pqs.purchase_flag purchaseFlag\n" +
					"      ,(CASE pqs.purchase_flag WHEN 'Y' THEN '是' ELSE '否' END) purchaseFlagStr\n" +
					"      ,pqs.payment_flag paymentFlag\n" +
					"      ,(CASE pqs.payment_flag WHEN 'Y' THEN '是' ELSE '否' END) paymentFlagStr\n" +
					"      ,pqs.froze_flag frozeFlag\n" +
					"      ,(CASE pqs.froze_flag WHEN 'Y' THEN '是' ELSE '否'END) frozeFlagStr\n" +
					"      ,pqs.unfroze_date unfrozeDate\n" +
					"      ,pqs.qualified_date qualifiedDate\n" +
					"      ,pqs.invalid_date invalidDate\n" +
					"      ,pqs.temporary_site_flag temporarySiteFlag\n" +
					"      ,(CASE pqs.temporary_site_flag WHEN 'Y' THEN '是' ELSE '否'END) temporarySiteFlagName\n" +
					"      ,pqs.add_flag addFlag\n" +
					"      ,org.inst_name instName\n" +
					"      ,slv.meaning siteStatusStr\n" +
					"      ,pqs.version_num\n" +
					"      ,pqs.created_by\n" +
					"      ,pqs.creation_date\n" +
					"      ,pqs.last_updated_by\n" +
					"      ,pqs.last_update_date\n" +
					"      ,pqs.last_update_login\n" +
					"  FROM srm_pos_qualification_sites pqs LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = pqs.site_status AND slv.lookup_type = 'POS_SUPPLIER_SITE_STATUS'\n" +
					"      ,saaf_institution       org\n" +
					" WHERE pqs.org_id = org.inst_id\n" ;

	private Integer qualificationSiteId; //资质审查地点ID
    private Integer qualificationId; //资质审查ID，关联表:srm_pos_qualification_info
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
    private String addFlag; //新增标识(Y/N)
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

    private String paymentFlagStr;
    private String purchaseFlagStr;
    private String temporarySiteFlagName;
    private String frozeFlagStr;
    private String instName;
    private String siteStatusStr;

	public void setQualificationSiteId(Integer qualificationSiteId) {
		this.qualificationSiteId = qualificationSiteId;
	}
	
	public Integer getQualificationSiteId() {
		return qualificationSiteId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public Integer getQualificationId() {
		return qualificationId;
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

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public String getAddFlag() {
		return addFlag;
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

	public String getPaymentFlagStr() {
		return paymentFlagStr;
	}

	public void setPaymentFlagStr(String paymentFlagStr) {
		this.paymentFlagStr = paymentFlagStr;
	}

	public String getPurchaseFlagStr() {
		return purchaseFlagStr;
	}

	public void setPurchaseFlagStr(String purchaseFlagStr) {
		this.purchaseFlagStr = purchaseFlagStr;
	}

	public String getTemporarySiteFlagName() {
		return temporarySiteFlagName;
	}

	public void setTemporarySiteFlagName(String temporarySiteFlagName) {
		this.temporarySiteFlagName = temporarySiteFlagName;
	}

	public String getFrozeFlagStr() {
		return frozeFlagStr;
	}

	public void setFrozeFlagStr(String frozeFlagStr) {
		this.frozeFlagStr = frozeFlagStr;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getSiteStatusStr() {
		return siteStatusStr;
	}

	public void setSiteStatusStr(String siteStatusStr) {
		this.siteStatusStr = siteStatusStr;
	}
}
