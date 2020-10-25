package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmPoApprovedListEntity_HI_RO implements Serializable {
	
	public static String QUERY_APPROVED_SQL =
					"SELECT al.list_id\n" +
					"      ,al.org_id\n" +
					"      ,in1.inst_name         org_name\n" +
					"      ,al.organization_id\n" +
					"      ,in2.inst_name         organization_name\n" +
					"      ,bc.category_id\n" +
					"      ,bc.full_category_code\n" +
					"      ,bc.full_category_name\n" +
					"      ,al.day_capacity\n" +
					"      ,al.list_status        list_status_number\n" +
					"      ,slv.meaning           list_status\n" +
					"      ,al.supplier_id\n" +
					"      ,al.item_id\n" +
					"      ,bi.item_code\n" +
					"      ,bi.item_name\n" +
					"      ,si.supplier_number\n" +
					"      ,si.supplier_name\n" +
					"      ,al.disabled_flag\n" +
					"      ,bi.uom_code           uom_code_num\n" +
					"      ,slv1.meaning          uom_code\n" +
					"      ,al.creation_date\n" +
					"      ,su.user_full_name\n" +
					"FROM   srm_po_approved_list al\n" +
					"LEFT   JOIN saaf_institution in1\n" +
					"ON     in1.inst_id = al.org_id\n" +
					"LEFT   JOIN saaf_institution in2\n" +
					"ON     in2.inst_id = al.organization_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = al.list_status\n" +
					"AND    slv.lookup_type = 'ISP_ASL_STATUS'\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     su.user_id = al.created_by, srm_base_items bi\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = bi.uom_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     bc.category_id = bi.category_id, srm_pos_supplier_info si\n" +
					"WHERE  al.item_id = bi.item_id\n" +
					"AND    al.organization_id = bi.organization_id\n" +
					"AND    al.supplier_id = si.supplier_id\n";

	public static String QUERY_SUPPLIER_ID="select s.supplier_id supplierId from srm_pos_supplier_info s where s.supplier_name = :supplierName";
	
	public static String QUERY_ITEM_ID="select i.item_id itemId from srm_base_items i where i.item_code = :itemCode";

	public static String QUERY_ORGANIZATION_ID = "select t.inst_id instId from saaf_institution t where t.inst_name = :instName and t.inst_type = 'ORGANIZATION' ";

	public static String QUERY_ORG_ID = "select t.inst_id instId from saaf_institution t where t.inst_name = :instName and t.inst_type = 'ORG' ";

	public static String QUERY_COUNT="SELECT count(*) FROM srm_po_approved_list t where (t.item_id = :itemId and t.supplier_id = :supplierId and t.organization_id = :organizationId) ";
	
	private Integer listId;
	private Integer supplierId;
	private Integer itemId;
	private BigDecimal dayCapacity;
	private String enabledFlag;
	private String listStatus;

	private String itemCode;
	private String itemName;

	private String supplierNumber;
	private String supplierName;

	private Integer count;
	private Integer orgId;
	private String orgName;
	private Integer organizationId;
	private String organizationName;
	private Integer categoryId;
	private String fullCategoryName;
	private String fullCategoryCode;
	private String disabledFlag;
	private String uomCode;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;
	private String userFullName;
	private String listStatusNumber;

	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public BigDecimal getDayCapacity() {
		return dayCapacity;
	}
	public void setDayCapacity(BigDecimal dayCapacity) {
		this.dayCapacity = dayCapacity;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public String getListStatus() {
		return listStatus;
	}
	public void setListStatus(String listStatus) {
		this.listStatus = listStatus;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getOrgName() { return orgName; }
	public void setOrgName(String orgName) { this.orgName = orgName; }
	public String getOrganizationName() { return organizationName; }
	public void setOrganizationName(String organizationName) { this.organizationName = organizationName; }
	public String getFullCategoryName() { return fullCategoryName; }
	public void setFullCategoryName(String fullCategoryName) { this.fullCategoryName = fullCategoryName; }
	public String getFullCategoryCode() { return fullCategoryCode; }
	public void setFullCategoryCode(String fullCategoryCode) { this.fullCategoryCode = fullCategoryCode; }
	public String getDisabledFlag() {
		if("Y".equals(disabledFlag)){
			return "是";
		}else{
			return "否";
		}
	}
	public void setDisabledFlag(String disabledFlag) { this.disabledFlag = disabledFlag; }
	public String getUomCode() { return uomCode; }
	public void setUomCode(String uomCode) { this.uomCode = uomCode; }
	public Date getCreationDate() { return creationDate; }
	public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
	public String getUserFullName() { return userFullName; }
	public void setUserFullName(String userFullName) { this.userFullName = userFullName; }
	public Integer getOrgId() { return orgId; }
	public void setOrgId(Integer orgId) { this.orgId = orgId; }
	public Integer getOrganizationId() { return organizationId; }
	public void setOrganizationId(Integer organizationId) { this.organizationId = organizationId; }
	public Integer getCategoryId() { return categoryId; }
	public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getListStatusNumber() { return listStatusNumber; }
    public void setListStatusNumber(String listStatusNumber) { this.listStatusNumber = listStatusNumber; }
}
