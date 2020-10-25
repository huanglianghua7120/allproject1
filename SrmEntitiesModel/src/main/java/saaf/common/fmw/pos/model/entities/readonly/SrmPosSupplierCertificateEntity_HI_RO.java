package saaf.common.fmw.pos.model.entities.readonly;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * SrmPosSupplierCertificateEntity_HI_RO Entity Object Fri Mar 09 10:27:50 CST
 * 2018 Auto Generate
 */

public class SrmPosSupplierCertificateEntity_HI_RO {

	private Integer certificateId; //证书ID
	private Integer supplierId; //供应商ID，关联表:srm_pos_supplier_info
	private String certificateName; //证书名称
	private String certificateNumber; //证书编号
	private String certificateDescription; //证书说明
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; //证书生效日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; //证书失效日期
	private String longCertificateIndate; //证书是否长期有效
	private String dueToFreeze; //是否到期冻结
	private Integer fileId; //附件ID
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
	//别名
	private String supplierName;// 企业名称
    private String supplierShortName;//企业简称
	private String surplusDays;// 到期剩余天数
	private String earlyWarning;// 是否预警

	private String surplusDays1;// 到期剩余天数
	private String earlyWarning1;// 是否预警
	private String licenseNum;
	@JSONField(format = "yyyy-MM-dd")
	private Date license_indate;

	private String surplusDays2;// 到期剩余天数
	private String earlyWarning2;// 是否预警
	private String tissue_code;
	@JSONField(format = "yyyy-MM-dd")
	private Date tissue_indate;


	public String getSurplusDays2() {
		return surplusDays2;
	}

	public void setSurplusDays2(String surplusDays2) {
		this.surplusDays2 = surplusDays2;
	}

	public String getEarlyWarning2() {
		return earlyWarning2;
	}

	public void setEarlyWarning2(String earlyWarning2) {
		this.earlyWarning2 = earlyWarning2;
	}

	public String getTissue_code() {
		return tissue_code;
	}

	public void setTissue_code(String tissue_code) {
		this.tissue_code = tissue_code;
	}

	public Date getTissue_indate() {
		return tissue_indate;
	}

	public void setTissue_indate(Date tissue_indate) {
		this.tissue_indate = tissue_indate;
	}

	public Date getLicense_indate() {
		return license_indate;
	}

	public void setLicense_indate(Date license_indate) {
		this.license_indate = license_indate;
	}

	public String getSurplusDays1() {
		return surplusDays1;
	}

	public void setSurplusDays1(String surplusDays1) {
		this.surplusDays1 = surplusDays1;
	}

	public String getEarlyWarning1() {
		return earlyWarning1;
	}

	public void setEarlyWarning1(String earlyWarning1) {
		this.earlyWarning1 = earlyWarning1;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	private String fileName;
	private String filePath;

	// 返回资质到期查询SQL
	public static String CERTIFICATE_DUE_SQL =
					"SELECT st.suppliername      AS supplier_name\n" +
					"      ,st.certificatename   AS certificate_name\n" +
					"      ,st.certificatenumber AS certificate_number\n" +
					"      ,st.enddate           AS end_date\n" +
					"      ,st.surplusdays       AS surplus_days\n" +
					"      ,st.earlywarning      AS early_warning\n" +
					"      ,st.supplierstatus    AS supplier_status\n" +
					"FROM   (SELECT t2.supplier_name AS suppliername\n" +
					"              ,t1.certificate_name AS certificatename\n" +
					"              ,t1.certificate_number AS certificatenumber\n" +
					"              ,t1.end_date AS enddate\n" +
					"              ,t2.supplier_status AS supplierstatus\n" +
					"              ,decode(t1.end_date,\n" +
					"                      NULL,\n" +
					"                      '永久',\n" +
					"                      round(to_number(t1.end_date - SYSDATE))) AS surplusdays\n" +
					"              ,(CASE\n" +
					"                 WHEN round(to_number(t1.end_date - SYSDATE)) < 3 THEN\n" +
					"                  '是'\n" +
					"                 ELSE\n" +
					"                  '否'\n" +
					"               END) AS earlywarning\n" +
					"        FROM   srm_pos_supplier_certificate t1\n" +
					"        LEFT   JOIN srm_pos_supplier_info t2\n" +
					"        ON     t1.supplier_id = t2.supplier_id\n" +
					"        UNION ALL\n" +
					"        SELECT t2.supplier_name AS suppliername\n" +
					"              ,'营业执照号' AS certificatename\n" +
					"              ,t1.license_num AS certificatenumber\n" +
					"              ,t1.license_indate AS enddate\n" +
					"              ,t2.supplier_status AS supplierstatus\n" +
					"              ,decode((SELECT spsc.license_indate\n" +
					"                      FROM   srm_pos_supplier_credentials spsc\n" +
					"                      WHERE  t2.supplier_id = spsc.supplier_id),\n" +
					"                      NULL,\n" +
					"                      '永久',\n" +
					"                      round(to_number((SELECT spsc.license_indate\n" +
					"                                       FROM   srm_pos_supplier_credentials spsc\n" +
					"                                       WHERE  t2.supplier_id =\n" +
					"                                              spsc.supplier_id) - SYSDATE))) AS surplusdays\n" +
					"              ,(CASE\n" +
					"                 WHEN ((SELECT spsc.license_indate\n" +
					"                        FROM   srm_pos_supplier_credentials spsc\n" +
					"                        WHERE  t2.supplier_id = spsc.supplier_id) < SYSDATE) THEN\n" +
					"                  '是'\n" +
					"                 ELSE\n" +
					"                  '否'\n" +
					"               END) AS earlywarning\n" +
					"        FROM   srm_pos_supplier_credentials t1\n" +
					"        LEFT   JOIN srm_pos_supplier_info t2\n" +
					"        ON     t1.supplier_id = t2.supplier_id\n" +
					"        UNION ALL\n" +
					"        SELECT t2.supplier_name AS suppliername\n" +
					"              ,'组织机构代码号' AS certificatename\n" +
					"              ,t1.tissue_code AS certificatenumber\n" +
					"              ,t1.tissue_indate AS enddate\n" +
					"              ,t2.supplier_status AS supplierstatus\n" +
					"              ,decode((SELECT spsc.tissue_indate\n" +
					"                      FROM   srm_pos_supplier_credentials spsc\n" +
					"                      WHERE  t2.supplier_id = spsc.supplier_id),\n" +
					"                      NULL,\n" +
					"                      '永久',\n" +
					"                      round(to_number((SELECT spsc.tissue_indate\n" +
					"                                       FROM   srm_pos_supplier_credentials spsc\n" +
					"                                       WHERE  t2.supplier_id =\n" +
					"                                              spsc.supplier_id) - SYSDATE))) AS surplusdays\n" +
					"              ,(CASE\n" +
					"                 WHEN round(to_number((SELECT spsc.tissue_indate\n" +
					"                                       FROM   srm_pos_supplier_credentials spsc\n" +
					"                                       WHERE  t2.supplier_id =\n" +
					"                                              spsc.supplier_id) - SYSDATE)) < 3 THEN\n" +
					"                  '是'\n" +
					"                 ELSE\n" +
					"                  '否'\n" +
					"               END) AS earlywarning\n" +
					"        FROM   srm_pos_supplier_credentials t1\n" +
					"        LEFT   JOIN srm_pos_supplier_info t2\n" +
					"        ON     t1.supplier_id = t2.supplier_id) st\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_INVALID_CERTIFICATE_SQL = "SELECT\n" +
			"t.certificate_id certificateId,\n" +
			"t.supplier_id supplierId,\n" +
            "t2.supplier_name supplierName,\n" +
            "t2.supplier_short_name supplierShortName,\n"+
			"t.certificate_name certificateName,\n" +
			"t.certificate_number certificateNumber,\n" +
			"t.certificate_description certificateDescription,\n" +
			"t.start_date startDate,\n" +
			"t.end_date endDate,\n" +
			"t.long_certificate_indate longCertificateIndate,\n" +
			"t.due_to_freeze,\n" +
			"t.file_id fileId,\n" +
			"t.source_code sourceCode,\n" +
			"t.source_id sourceId,\n" +
			"t.version_num versionNum,\n" +
			"t.creation_date creationDate,\n" +
			"t.created_by createdBy,\n" +
			"t.last_updated_by lastUpdatedBy,\n" +
			"t.last_update_date lastUpdateDate,\n" +
			"t.last_update_login lastUpdateLogin,\n" +
            "t2.region region,\n" +
            "t2.supplier_type supplierType\n" +
			"FROM\n" +
			"srm_pos_supplier_certificate t\n" +
            "LEFT JOIN srm_pos_supplier_info t2 ON t.supplier_id = t2.supplier_id\n"+
			"WHERE\n" +
			"(t.long_certificate_indate IS NULL OR t.long_certificate_indate='')\n";

	       private String region;
	       private String supplierType;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
    //	public static String QUERY_INVALID_CERTIFICATE_NOTIFICATION_SQL = "SELECT\n" +
//			"t.certificate_id,\n" +
//			"sbn.notification_code,\n"+
//			"t.supplier_id,\n" +
//			"t.certificate_name,\n" +
//			"t.certificate_number,\n" +
//			"t.certificate_description,\n" +
//			"t.start_date,\n" +
//			"t.end_date,\n" +
//			"t.long_certificate_indate,\n" +
//			"t.due_to_freeze,\n" +
//			"t.file_id,\n" +
//			"t.source_code,\n" +
//			"t.source_id,\n" +
//			"t.version_num,\n" +
//			"t.creation_date,\n" +
//			"t.created_by,\n" +
//			"t.last_updated_by,\n" +
//			"t.last_update_date,\n" +
//			"t.last_update_login\n" +
//			"FROM\n" +
//			"srm_pos_supplier_certificate t\n" +
//			"LEFT JOIN srm_base_notifications sbn ON sbn.notification_type = '31' AND sbn.table_id = t.certificate_id\n"+
//			"WHERE\n" +
//			"t.long_certificate_indate IS NULL\n" +
//			"AND date(t.end_date) <= DATE_SUB(CURDATE(), INTERVAL 3 DAY)\n"+
//			"AND sbn.receiver_id = t.supplier_id";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public void setCertificateId(Integer certificateId) {
		this.certificateId = certificateId;
	}

	public Integer getCertificateId() {
		return certificateId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() {
		return supplierId;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSurplusDays() {
		return surplusDays;
	}

	public void setSurplusDays(String surplusDays) {
		this.surplusDays = surplusDays;
	}

	public String getEarlyWarning() {
		return earlyWarning;
	}

    public String getSupplierShortName() {
        return supplierShortName;
    }

    public void setSupplierShortName(String supplierShortName) {
        this.supplierShortName = supplierShortName;
    }

    public void setEarlyWarning(String earlyWarning) {
		this.earlyWarning = earlyWarning;
	}

}
