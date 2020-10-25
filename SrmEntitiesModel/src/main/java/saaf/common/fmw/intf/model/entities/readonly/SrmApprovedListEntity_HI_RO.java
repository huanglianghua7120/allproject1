package saaf.common.fmw.intf.model.entities.readonly;

import java.math.BigDecimal;

public class SrmApprovedListEntity_HI_RO {

	public static final String SRM_PO_APPROVED_LIST =""+
			"SELECT\r\n" + 
			"  t.list_id listId,\r\n" + 
			"	I.ITEM_CODE itemCode,/**物料编码**/\r\n" + 
			"	T.list_number RowNo,/**序号**/\r\n" + 
			"  IF (t.enabled_flag = 'Y', '1', '0') status,/**0生效0为失效1生效**/\r\n" + 
			"  si.supplier_number supplierCode,/**供应商编码**/\r\n" + 
			"  t.day_capacity dayCapacity,/**日供货量**/\r\n" + 
			"  '2017-01-01' fromDate,/**有效期从**/\r\n" + 
			"  '9999-12-30' toDate /**有效期至**/\r\n" + 
			"FROM\r\n" + 
			"	srm_po_approved_list t,\r\n" + 
			"	srm_pos_supplier_info si,\r\n" + 
			"	srm_base_items i\r\n" + 
			"WHERE\r\n" + 
			"	t.item_id = i.ITEM_ID\r\n" + 
			"AND t.supplier_id = si.supplier_id\n";
	
	private Integer listId;
	private String itemCode;
	private Integer RowNo;
	private String status;
	private String supplierCode;
	private String fromDate;
	private String toDate;
	private BigDecimal dayCapacity;
	
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Integer getRowNo() {
		return RowNo;
	}
	public void setRowNo(Integer rowNo) {
		RowNo = rowNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public BigDecimal getDayCapacity() {
		return dayCapacity;
	}
	public void setDayCapacity(BigDecimal dayCapacity) {
		this.dayCapacity = dayCapacity;
	}
	
}
