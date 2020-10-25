package saaf.common.fmw.pos.model.entities.readonly;



import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class SrmPosManagerCatesEntity_HI_RO implements Serializable {

	public static final String QUERY_MANAGER_CATES_SQL="SELECT \r\n"+
									" spmc.manager_cate_id managerCateId, \r\n"+
									" spmc.supplier_id supplierId, \r\n"+
									" spmc.org_id orgId, \r\n"+
									" spmc.document_type  documentType, \r\n"+
									" spmc.document_number documentNumber, \r\n"+
									" spmc.document_status documentStatus, \r\n"+
									" spmc.need_onsite_inspection needOnsiteInspection, \r\n"+
									" spmc.supplier_advantage supplierAdvantage, \r\n"+
									" spmc.description description, \r\n"+
									" spmc.reason reason, \r\n"+
									" spmc.file_id fileId, \r\n"+
									" spmc.VERSION_NUM versionNum, \r\n"+
									" spmc.CREATION_DATE creationDate, \r\n"+
									" spsi.supplier_name supplierName, \r\n"+
									" slv4.meaning meaning, \r\n"+
									"  su.user_full_name createName, \r\n"+
									"  si.inst_name instName, \r\n"+
									"  sbrf1.file_name fileName, \r\n"+
									"  sbrf1.access_path accessPath \r\n"+
									" FROM \r\n"+
									" srm_pos_manager_cates as spmc \r\n"+
									" left join srm_pos_supplier_info spsi on spmc.supplier_id=spsi.supplier_id \r\n"+
									" left join saaf_institution si on si.inst_id=spmc.org_id \r\n"+
									" left join saaf_users su on su.user_id = spmc.CREATED_BY"+
									" left JOIN saaf_lookup_values slv4 ON slv4.lookup_type = 'POS_QUALIFICATION_STATUS' \r\n"+
									" and  spmc.document_status = slv4.lookup_code"+
									" LEFT JOIN saaf_base_result_file sbrf1 ON spmc.file_id = sbrf1.file_id \r\n" + 
									" where 1=1 ";
	private String fileName;
	private String accessPath;
	
	private String instName;
	private String createName;
	private String supplierName;
	private String meaning;
	private Integer managerCateId;
    private Integer supplierId;
    private Integer orgId;
    private String documentType;
    private String documentNumber;
    private String documentStatus;
    private String needOnsiteInspection;
    private String supplierAdvantage;
    private String description;
    private String reason;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;//创建人
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private Integer fileId;
    
    
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
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public Integer getManagerCateId() {
		return managerCateId;
	}
	public void setManagerCateId(Integer managerCateId) {
		this.managerCateId = managerCateId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}
	public String getNeedOnsiteInspection() {
		return needOnsiteInspection;
	}
	public void setNeedOnsiteInspection(String needOnsiteInspection) {
		this.needOnsiteInspection = needOnsiteInspection;
	}
	public String getSupplierAdvantage() {
		return supplierAdvantage;
	}
	public void setSupplierAdvantage(String supplierAdvantage) {
		this.supplierAdvantage = supplierAdvantage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
    
}
