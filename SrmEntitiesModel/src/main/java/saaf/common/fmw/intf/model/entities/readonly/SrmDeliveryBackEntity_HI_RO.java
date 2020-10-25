package saaf.common.fmw.intf.model.entities.readonly;

import java.math.BigDecimal;

public class SrmDeliveryBackEntity_HI_RO {
	
	
	public static final String QUERY_DELIVERY_INFO = 
			"SELECT\r\n" + 
			"	T.DOCNO docNo,\r\n" + 
			"	T.DELIVERY_NUMBER deliveryNumber,\r\n" + 
			"	T.log_Id logId,\r\n" + 
			"	T.INTF_ID intfId\r\n" + 
			"FROM\r\n" + 
			"	srm_intf_delivery T \n"
			+ " where 1=1  \n";
	
	
	
	
	public static final String QUERY_DELIVERY_SUCESS = 
			"SELECT t.PO_NUMBER poNumber, t.PO_LINE_NUMBER poLineNumber, t.DOCNO docNo, t.DELIVERY_NUMBER deliveryNumber, FROM srm_intf_delivery t where 1=1";
	
	public static final String   QUTERY_DELI_DETAIL = 
			"select t.detail_id detailId from srm_po_delivery_details t WHERE 1=1\r\n" ;
	
	
	
	public static final String QUERY_SAME_DELIVERY = 
			"SELECT\r\n" + 
			"	t.DOCNO docNo\r\n" + 
			"FROM\r\n" + 
			"	srm_intf_delivery t,\r\n" + 
			"	srm_intf_delivery t2\r\n" + 
			"WHERE\r\n" + 
			"	t.LOG_ID = :varLogId1\r\n" + 
			"AND t2.LOG_ID =:varLogId2\r\n" + 
			"AND t.PO_NUMBER = t2.PO_NUMBER\r\n" + 
			"AND t.PO_LINE_NUMBER = t2.PO_LINE_NUMBER\r\n" + 
			"AND t.DOCNO = t2.DOCNO\r\n" + 
			"and  t.QUANTITY = t2.QUANTITY\r\n" + 
			"AND IFNULL(t.DELIVERY_NUMBER, '') = IFNULL(t2.DELIVERY_NUMBER, '') ";
	
	
	
	private String docNo;
	private Integer detailId;
    private  BigDecimal residueQty ;	
	private String deliveryNumber;
	private Integer intfId;
	private Integer logId;
	private String poNumber;
	private String poLineNumber;
	private String docType;
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public Integer getIntfId() {
		return intfId;
	}
	public void setIntfId(Integer intfId) {
		this.intfId = intfId;
	}
 
	public BigDecimal getResidueQty() {
		return residueQty;
	}
	public void setResidueQty(BigDecimal residueQty) {
		this.residueQty = residueQty;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPoLineNumber() {
		return poLineNumber;
	}
	public void setPoLineNumber(String poLineNumber) {
		this.poLineNumber = poLineNumber;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	
	
	
	

}
