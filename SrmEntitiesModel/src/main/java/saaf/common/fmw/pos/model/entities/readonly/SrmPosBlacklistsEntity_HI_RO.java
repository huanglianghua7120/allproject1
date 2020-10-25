package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPosBlacklistsEntity_HI_RO implements Serializable{
	
	public static String QUERY_BLACK_SQL =
					"SELECT b.blacklist_id      blacklistId\n" +
					"      ,b.blacklist_number  blacklistNumber\n" +
					"      ,b.supplier_id       supplierId\n" +
					"      ,b.creation_date     creationDate\n" +
					"      ,b.blacklist_status  blacklistStatus\n" +
					"      ,b.relieve_date      relieveDate\n" +
					"      ,b.permanent_flag    permanentFlag\n" +
					"      ,b.description       description\n" +
					"      ,b.blacklist_file_id blacklistFileId\n" +
					"      ,slv.meaning         blacklistStatusMean\n" +
					"      ,f.access_path       blacklistFilePath\n" +
					"      ,f.file_name         blacklistFileName\n" +
					"      ,i.supplier_name     supplierName\n" +
					"      ,i.supplier_number   supplierNumber\n" +
					"      ,u.user_full_name    userFullName\n" +
					"FROM   srm_pos_blacklists b\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     b.blacklist_status = slv.lookup_code\n" +
					"AND    slv.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"LEFT   JOIN saaf_base_result_file f\n" +
					"ON     b.blacklist_file_id = f.file_id, srm_pos_supplier_info i, saaf_users u\n" +
					"WHERE  b.supplier_id = i.supplier_id\n" +
					"AND    b.created_by = u.user_id\n";

	private Integer blacklistId;
    private String blacklistNumber;
    private String blacklistStatus;
    private String permanentFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date relieveDate;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    
    private String blacklistStatusMean;
   
    private Integer blacklistFileId;
    private String blacklistFilePath;
    private String blacklistFileName;
    
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    
    private String userFullName;

	public Integer getBlacklistId() {
		return blacklistId;
	}

	public void setBlacklistId(Integer blacklistId) {
		this.blacklistId = blacklistId;
	}

	public String getBlacklistNumber() {
		return blacklistNumber;
	}

	public void setBlacklistNumber(String blacklistNumber) {
		this.blacklistNumber = blacklistNumber;
	}

	public String getBlacklistStatus() {
		return blacklistStatus;
	}

	public void setBlacklistStatus(String blacklistStatus) {
		this.blacklistStatus = blacklistStatus;
	}

	public String getPermanentFlag() {
		return permanentFlag;
	}

	public void setPermanentFlag(String permanentFlag) {
		this.permanentFlag = permanentFlag;
	}

	public Date getRelieveDate() {
		return relieveDate;
	}

	public void setRelieveDate(Date relieveDate) {
		this.relieveDate = relieveDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getBlacklistStatusMean() {
		return blacklistStatusMean;
	}

	public void setBlacklistStatusMean(String blacklistStatusMean) {
		this.blacklistStatusMean = blacklistStatusMean;
	}

	public Integer getBlacklistFileId() {
		return blacklistFileId;
	}

	public void setBlacklistFileId(Integer blacklistFileId) {
		this.blacklistFileId = blacklistFileId;
	}

	public String getBlacklistFilePath() {
		return blacklistFilePath;
	}

	public void setBlacklistFilePath(String blacklistFilePath) {
		this.blacklistFilePath = blacklistFilePath;
	}

	public String getBlacklistFileName() {
		return blacklistFileName;
	}

	public void setBlacklistFileName(String blacklistFileName) {
		this.blacklistFileName = blacklistFileName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
    
    
}
