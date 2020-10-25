package saaf.common.fmw.pos.model.entities.readonly;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPosSupplierQuitEntity_HI_RO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String QUERY_SUPPLIER_QUIT =
					"SELECT\n" +
					"  psq.supplier_quit_id supplierQuitId,\n" +
					"  psq.supplier_id supplierId,\n" +
					"  psi.supplier_name supplierName,\n" +
					"  psi.supplier_number supplierNumber,\n" +
					"  psq.org_id orgId,\n" +
					"  psq.frozen_id frozenId,\n" +
					"  psq.document_number documentNumber,\n" +
					"  psq.document_status documentStatus,\n" +
					"  slv1.meaning statusDesc,\n" +
					"  psq.description,\n" +
					"  psq.inventory_cleanup_flag inventoryCleanupFlag,\n" +
					"  psq.payment_settle_flag paymentSettleFlag,\n" +
					"  psq.quit_date quitDate,\n" +
					"  psq.quit_forever_flag quitForeverFlag,\n" +
					"  psq.file_id fileId,\n" +
					"  rf.file_Name fileName,\n" +
					"  rf.access_Path accessPath,\n" +
					"  psq.version_num versionNum,\n" +
					"  psq.creation_date creationDate,\n" +
					"  psq.created_by createdBy,\n" +
					"  nvl(\n" +
					"    emp.employee_name,\n" +
					"    su.user_full_name\n" +
					"  ) userFullName,\n" +
					"  psq.last_updated_by lastUpdatedBy,\n" +
					"  psq.last_update_date lastUpdateDate\n" +
					"FROM\n" +
					"  srm_pos_supplier_quit psq\n" +
					"  LEFT JOIN saaf_base_result_file rf\n" +
					"    ON psq.file_id = rf.file_id,\n" +
					"  srm_pos_supplier_info psi,\n" +
					"  saaf_lookup_values slv1,\n" +
					"  saaf_users su\n" +
					"  LEFT JOIN saaf_employees emp\n" +
					"    ON emp.employee_id = su.employee_id\n" +
					"WHERE psq.supplier_id = psi.supplier_id\n" +
					"  AND psq.document_status = slv1.lookup_code\n" +
					"  AND slv1.lookup_type = 'POS_APPROVAL_STATUS'\n" +
					"  AND psq.created_by = su.user_id\n";

	public static String QUERY_SUPPLIER_LOV  =
			          "SELECT\n" +
					  "  t.supplier_id supplierId,\n" +
					  "  t.supplier_name supplierName,\n" +
					  "  t.supplier_number supplierNumber,\n" +
					  "  t.supplier_status supplierStatus,\n" +
					  "  slv.meaning supplierStatusDesc\n" +
					  "FROM\n" +
					  "  srm_pos_supplier_info t,\n" +
					  "  saaf_lookup_values slv\n" +
					  "WHERE slv.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					  "  AND slv.lookup_code = t.supplier_status";

	public static String QUERY_FROZEN_LOV =
			  "select t.freeze_number freezeNumber,t.frozen_id frozenId from srm_pos_frozen_info t\n"
			  + " where 1=1 \n ";
	
	
	    private Integer supplierQuitId;
	    private Integer supplierId;
	    private String supplierName;
	    private String supplierStatus;
	    private String supplierStatusDesc;
	    private String supplierNumber;
	    private Integer orgId;
	    private Integer frozenId;
	    private String documentNumber;
	    private String freezeNumber;
	    private String documentStatus;
	    private String statusDesc;
	    private String description;
	    private String inventoryCleanupFlag;
	    private String paymentSettleFlag;
		@JSONField(format = "yyyy-MM-dd HH:mm:ss")
		private Date quitDate;
		private String quitForeverFlag;
	    private Integer versionNum;
	    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	    private Date creationDate;
	    private Integer createdBy;
	    private String userFullName;
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
	    private Integer fileId;
	    private String fileName;
	    private String accessPath;
		public Integer getSupplierQuitId() {
			return supplierQuitId;
		}
		public void setSupplierQuitId(Integer supplierQuitId) {
			this.supplierQuitId = supplierQuitId;
		}
		public Integer getSupplierId() {
			return supplierId;
		}
		public void setSupplierId(Integer supplierId) {
			this.supplierId = supplierId;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public String getSupplierNumber() {
			return supplierNumber;
		}
		public void setSupplierNumber(String supplierNumber) {
			this.supplierNumber = supplierNumber;
		}
		public Integer getOrgId() {
			return orgId;
		}
		public void setOrgId(Integer orgId) {
			this.orgId = orgId;
		}
		public Integer getFrozenId() {
			return frozenId;
		}
		public void setFrozenId(Integer frozenId) {
			this.frozenId = frozenId;
		}
		public String getDocumentNumber() {
			return documentNumber;
		}
		public void setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
		}
		public String getFreezeNumber() {
			return freezeNumber;
		}
		public void setFreezeNumber(String freezeNumber) {
			this.freezeNumber = freezeNumber;
		}
		public String getDocumentStatus() {
			return documentStatus;
		}
		public void setDocumentStatus(String documentStatus) {
			this.documentStatus = documentStatus;
		}
		public String getStatusDesc() {
			return statusDesc;
		}
		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getInventoryCleanupFlag() {
			return inventoryCleanupFlag;
		}
		public void setInventoryCleanupFlag(String inventoryCleanupFlag) {
			this.inventoryCleanupFlag = inventoryCleanupFlag;
		}
		public String getPaymentSettleFlag() {
			return paymentSettleFlag;
		}
		public void setPaymentSettleFlag(String paymentSettleFlag) {
			this.paymentSettleFlag = paymentSettleFlag;
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
		public String getUserFullName() {
			return userFullName;
		}
		public void setUserFullName(String userFullName) {
			this.userFullName = userFullName;
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
		public Integer getFileId() {
			return fileId;
		}
		public void setFileId(Integer fileId) {
			this.fileId = fileId;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getAccessPath() {
			return accessPath;
		}
		public void setAccessPath(String accessPath) {
			this.accessPath = accessPath;
		}
		public String getSupplierStatus() {
			return supplierStatus;
		}
		public void setSupplierStatus(String supplierStatus) {
			this.supplierStatus = supplierStatus;
		}
		public String getSupplierStatusDesc() {
			return supplierStatusDesc;
		}
		public void setSupplierStatusDesc(String supplierStatusDesc) {
			this.supplierStatusDesc = supplierStatusDesc;
		}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getQuitForeverFlag() {
		return quitForeverFlag;
	}

	public void setQuitForeverFlag(String quitForeverFlag) {
		this.quitForeverFlag = quitForeverFlag;
	}
}
