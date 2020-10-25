package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPosFrozenInfoEntity_HI_RO implements Serializable  {

    public static String QUERY_FROZEN_SQL =
					"SELECT fi.frozen_id            frozenId\n" +
					"      ,fi.supplier_id          supplierId\n" +
					"      ,fi.freeze_number        freezeNumber\n" +
					"      ,fi.freeze_status        freezeStatus\n" +
					"      ,fi.freeze_type          freezeType\n" +
					"      ,fi.description          description\n" +
					"      ,fi.forbid_payment_flag  forbidPaymentFlag\n" +
					"      ,fi.forbid_purchase_flag forbidPurchaseFlag\n" +
					"      ,fi.creation_date        creationDate\n" +
					"      ,fi.unfrozen_date        unfrozenDate\n" +
					"      ,fi.frozen_file_id       frozenFileId\n" +
					"      ,fi.approve_date         approveDate\n" +
					"      ,rf.access_path          frozenFilePath\n" +
					"      ,rf.file_name            frozenFileName\n" +
					"      ,si.supplier_name        supplierName\n" +
					"      ,si.supplier_number      supplierNumber\n" +
					"      ,su.user_full_name       userFullName\n" +
					"      ,sfu.user_full_name      approveName\n" +
					"      ,slv.meaning             freezeStatusMean\n" +
					"      ,slv2.meaning            freezeTypeMean\n" +
					"FROM   srm_pos_frozen_info fi\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     fi.freeze_status = slv.lookup_code\n" +
					"AND    slv.lookup_type = 'POS_FROZEN_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     fi.freeze_type = slv2.lookup_code\n" +
					"AND    slv2.lookup_type = 'POS_FREEZE_TYPE'\n" +
					"LEFT   JOIN saaf_base_result_file rf\n" +
					"ON     fi.frozen_file_id = rf.file_id\n" +
					"LEFT   JOIN saaf_users sfu\n" +
					"ON     fi.approve_by = sfu.user_id, srm_pos_supplier_info si, saaf_users su\n" +
					"WHERE  fi.supplier_id = si.supplier_id\n" +
					"AND    fi.created_by = su.user_id\n";

	public static String QUERY_FLAG_SQL =
					"SELECT si.supplier_id   supplierId\n" +
					"      ,si.supplier_name supplierName\n" +
					"      ,si.payment_flag  paymentFlag\n" +
					"      ,si.purchase_flag purchaseFlag\n" +
					"FROM   srm_pos_supplier_info si\n" +
					"      ,srm_pos_frozen_info   fi\n" +
					"WHERE  si.supplier_id = fi.supplier_id\n";

	/**
     * 查询冻结、恢复供应商供应商LOVfindFrozenOrRecoverySupplierLov
     */
    public static String QUERY_FROZEN_OR_RECOVERY_SUPPLIER_LOV_SQL =
					"SELECT si.supplier_id     supplierId\n" +
					"      ,si.supplier_name   supplierName\n" +
					"      ,si.supplier_number supplierNumber\n" +
					"      ,si.supplier_status supplierStatus\n" +
					"FROM   srm_pos_supplier_info si\n" +
					"WHERE  1 = 1\n";

	private Integer frozenId;
    private Integer orgId;
    private String freezeType;
    private String freezeNumber;
    private String freezeStatus;
    private String forbidPurchaseFlag;
    private String forbidPaymentFlag;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date unfrozenDate;
    private String freezeTypeMean;
    private String freezeStatusMean;
    
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    
    private String userFullName;
    private String approveName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approveDate;
    
    private Integer frozenFileId;
    private String frozenFilePath;
    private String frozenFileName;
    
    
    private String paymentFlag;
    private String purchaseFlag;
    
	public Integer getFrozenId() {
		return frozenId;
	}
	public void setFrozenId(Integer frozenId) {
		this.frozenId = frozenId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getFreezeType() {
		return freezeType;
	}
	public void setFreezeType(String freezeType) {
		this.freezeType = freezeType;
	}
	public String getFreezeNumber() {
		return freezeNumber;
	}
	public void setFreezeNumber(String freezeNumber) {
		this.freezeNumber = freezeNumber;
	}
	public String getFreezeStatus() {
		return freezeStatus;
	}
	public void setFreezeStatus(String freezeStatus) {
		this.freezeStatus = freezeStatus;
	}
	public String getForbidPurchaseFlag() {
		return forbidPurchaseFlag;
	}
	public void setForbidPurchaseFlag(String forbidPurchaseFlag) {
		this.forbidPurchaseFlag = forbidPurchaseFlag;
	}
	public String getForbidPaymentFlag() {
		return forbidPaymentFlag;
	}
	public void setForbidPaymentFlag(String forbidPaymentFlag) {
		this.forbidPaymentFlag = forbidPaymentFlag;
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
	public String getFreezeTypeMean() {
		return freezeTypeMean;
	}
	public void setFreezeTypeMean(String freezeTypeMean) {
		this.freezeTypeMean = freezeTypeMean;
	}
	public String getFreezeStatusMean() {
		return freezeStatusMean;
	}
	public void setFreezeStatusMean(String freezeStatusMean) {
		this.freezeStatusMean = freezeStatusMean;
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
	
	public String getApproveName() {
		return approveName;
	}
	
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public Integer getFrozenFileId() {
		return frozenFileId;
	}
	public void setFrozenFileId(Integer frozenFileId) {
		this.frozenFileId = frozenFileId;
	}
	public String getFrozenFilePath() {
		return frozenFilePath;
	}
	public void setFrozenFilePath(String frozenFilePath) {
		this.frozenFilePath = frozenFilePath;
	}
	public String getFrozenFileName() {
		return frozenFileName;
	}
	public void setFrozenFileName(String frozenFileName) {
		this.frozenFileName = frozenFileName;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public String getPurchaseFlag() {
		return purchaseFlag;
	}
	public void setPurchaseFlag(String purchaseFlag) {
		this.purchaseFlag = purchaseFlag;
	}
	public Date getUnfrozenDate() {
		return unfrozenDate;
	}
	public void setUnfrozenDate(Date unfrozenDate) {
		this.unfrozenDate = unfrozenDate;
	}
    
    
}
