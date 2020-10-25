package saaf.common.fmw.intf.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmAutoProcessEntity_HI_RO {
	
	
	public static final String OVERDUE_QUERY=
			"SELECT\r\n" + 
			"	t.po_header_id poHeaderId\r\n" + 
			"FROM\r\n" + 
			"	srm_po_headers t\r\n" + 
			"WHERE\r\n" + 
			"	1 = 1\r\n" + 
			"AND IFNULL(t.overdue_flag, 'N') != 'Y'\r\n" + 
			"AND  t.PO_DOC_TYPE !='03' \r\n" + 
			"AND t.`status` IN (  'APPROVING','NEW','REJECT' )\r\n" + 
			"AND DATE_FORMAT(SYSDATE(), '%Y-%m-%d') > DATE_FORMAT(t.CREATION_DATE, '%Y-%m-%d') ";
	
	
	
	public static final String PO_NATURAL_CLOSED_QUERY = 
			"SELECT\r\n" + 
			"	t.po_line_id poLineId\r\n" + 
			"FROM\r\n" + 
			"	srm_po_lines t\r\n" + 
			"WHERE\r\n" + 
			"	t.delivery_qty = t.demand_qty\r\n" + 
			"and  t.`status` not in ('NATURAL_CLOSED','SHORTAGE_CLOSED') ";
	
	public static final String NOTICE_NATURAL_CLOSED_QUERY =
			"SELECT\r\n" + 
			"	t.po_notice_id poNoticeId\r\n" + 
			"FROM\r\n" + 
			"	srm_po_notice t\r\n" + 
			"WHERE\r\n" + 
			"	t.delivery_qty = t.quantity\r\n" + 
			"and t.`status` not in ('NATURAL_CLOSED','SHORTAGE_CLOSED') ";
	
	
	
	
	
	
    private Integer poHeaderId;
    private Integer poLineId;  
    private Integer poNoticeId;
    private String poStatus;
    private Integer lineNumber;
    private String poNumber;
    
    private Integer planDemandId;
    private Integer instId;
    private String instCode;
    private Integer itemId;
    private String itemCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;
    private String planType;
    private String demandClassify;
    private BigDecimal needQuantity;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date needByDate;
    private String employerNumber;
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
	public Integer getPoHeaderId() {
		return poHeaderId;
	}
	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	public Integer getPoLineId() {
		return poLineId;
	}
	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}
	public String getPoStatus() {
		return poStatus;
	}
	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public Integer getPlanDemandId() {
		return planDemandId;
	}
	public void setPlanDemandId(Integer planDemandId) {
		this.planDemandId = planDemandId;
	}
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getDemandClassify() {
		return demandClassify;
	}
	public void setDemandClassify(String demandClassify) {
		this.demandClassify = demandClassify;
	}
	public BigDecimal getNeedQuantity() {
		return needQuantity;
	}
	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}
	public Date getNeedByDate() {
		return needByDate;
	}
	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}
	public String getEmployerNumber() {
		return employerNumber;
	}
	public void setEmployerNumber(String employerNumber) {
		this.employerNumber = employerNumber;
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
	public Integer getPoNoticeId() {
		return poNoticeId;
	}
	public void setPoNoticeId(Integer poNoticeId) {
		this.poNoticeId = poNoticeId;
	} 
    
    
    
 
       
	 
}
