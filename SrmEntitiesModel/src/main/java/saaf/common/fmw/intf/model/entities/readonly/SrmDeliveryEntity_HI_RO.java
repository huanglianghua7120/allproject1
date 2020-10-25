package saaf.common.fmw.intf.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmDeliveryEntity_HI_RO {
	public static final String SRM_PO_DELIVERY_HEADERS = ""+
			"SELECT\r\n" + 
			"	t.delivery_header_id deliveryHeaderId,\r\n" + 
			"	t.delivery_number deliveryNumber,  /**送货单号**/\r\n" + 
			"	t.document_type documentType,  /**送货单类型**/\r\n" + 
			"	t.delivery_date deliveryDate,  /**送货日期**/\r\n" + 
			"	t.supplier_Id supplierId,\r\n" + 
			"	s.supplier_number supplierNumber, /**供应商编号**/\r\n" + 
			"	t.inst_id instId,\r\n" + 
			"	si.inst_code  instCode  /**分厂代码**/\r\n" + 
			"FROM\r\n" + 
			"	srm_po_delivery_headers t,\r\n" + 
			"	srm_pos_supplier_info s,\r\n" + 
			"	saaf_institution si\r\n" + 
			"WHERE\r\n" + 
			"	t.supplier_Id = s.supplier_id\r\n" + 
			"AND t.inst_id = si.inst_id";
	
	
	
	public static  final String SRM_PO_DELIVERY_LINES=""+
			"SELECT\r\n" + 
			"	l.delivery_order_qty deliveryOrderQty,  /**对应采购订单行数量**/\r\n" + 
			" 	ph.po_number poNumber,          /**采购订单**/\r\n" + 
			"	pl.line_number lineNumber,       /**采购订单行号**/\r\n" + 
			"	l.item_id itemId,\r\n" + 
			"	i.ITEM_CODE itemCode,           /**物料编码**/\r\n" + 
			"	l.SPECIAL_USE_NUM  specialUseNum,     /**番号**/\r\n" + 
			"   pl.u9_plan_line_id planLineId\r\n"+
			"FROM\r\n" + 
			"	srm_po_delivery_lines l,\r\n" + 
			"	srm_po_lines pl,\r\n" + 
			"	srm_po_headers ph,\r\n" + 
			"	srm_base_items i\r\n" + 
			"WHERE\r\n" + 
			"   l.delivery_header_id=:deliveryHeaderId " +
			" and l.po_line_id = pl.po_line_id\r\n" + 
			"AND pl.po_header_id = ph.po_header_id\r\n" + 
			"AND l.item_id = i.ITEM_ID\r\n"+
            " order by i.ITEM_CODE,ph.po_number ";
	
	public static final String SRM_PO_DELIVERY_LINES2 = 
			"SELECT\r\n" + 
			"	sum(d.quantity) deliveryOrderQty,  /**对应采购订单行数量**/\r\n" + 
			" 	ph.po_number poNumber,          /**采购订单**/\r\n" + 
			"	pl.line_number lineNumber,       /**采购订单行号**/\r\n" + 
			"	l.item_id itemId,\r\n" + 
			"	i.ITEM_CODE itemCode,           /**物料编码**/\r\n" + 
			"	ifnull(l.SPECIAL_USE_NUM,'')  specialUseNum,     /**番号**/\r\n" + 
			"   pl.u9_plan_line_id planLineId\r\n" + 
			"FROM\r\n" + 
			"	srm_po_delivery_lines l,\r\n" + 
			"   srm_po_delivery_details d,\r\n" + 
			"	srm_po_lines pl,\r\n" + 
			"	srm_po_headers ph,\r\n" + 
			"	srm_base_items i\r\n" + 
			"WHERE\r\n" + 
			"   l.delivery_header_id=:deliveryHeaderId " +
			" and  l.delivery_line_id = d.delivery_line_id\r\n" + 
			"and	d.po_line_id = pl.po_line_id\r\n" + 
			"AND pl.po_header_id = ph.po_header_id\r\n" + 
			"AND l.item_id = i.ITEM_ID\r\n"+
			"AND d.quantity > 0\r\n"+
			" group by ph.po_number,pl.line_number,l.item_id ,i.ITEM_CODE,ifnull(l.SPECIAL_USE_NUM,''),pl.u9_plan_line_id "+
            " order by i.ITEM_CODE,ph.po_number "  ;
	
	//验证采购订单生成送货单数量
	public static final String QUERY_VALIDATE1=
			"SELECT\r\n" + 
			"	h.po_number poNumber,\r\n" + 
			"	l.line_number lineNumber\r\n" + 
			"FROM\r\n" + 
			"	srm_po_delivery_lines dl,\r\n" + 
			"	srm_po_headers h,\r\n" + 
			"	srm_po_lines l\r\n" + 
			"WHERE dl.delivery_header_id = :deliveryHeaderId \r\n" + 
			"and dl.po_line_id = l.po_line_id\r\n" + 
			"AND h.po_header_id = l.po_header_id\r\n" + 
			"AND 0 > (\r\n" + 
			"	l.demand_qty - IFNULL(l.delivery_qty, 0) - (\r\n" + 
			"		SELECT\r\n" + 
			"			ifnull(\r\n" + 
			"				sum(tt.delivery_order_qty),\r\n" + 
			"				0\r\n" + 
			"			)\r\n" + 
			"		FROM\r\n" + 
			"			srm_po_delivery_lines tt,\r\n" + 
			"			srm_po_delivery_headers hh\r\n" + 
			"		WHERE\r\n" + 
			"			tt.po_line_id = l.po_line_id\r\n" + 
			"		AND tt.delivery_header_id = hh.delivery_header_id\r\n" + 
			"		AND hh. STATUS IN ('CREATED')\r\n" + 
			"	)\r\n" + 
			")" ;
	//验证送货通知数量创建送货单
	public static final String QUERY_VALIDATE2 = 
			"SELECT\r\n" + 
			"	n.po_notice_code poNoticeCode\r\n" + 
			"FROM\r\n" + 
			"	srm_po_notice n,\r\n" + 
			"	srm_po_delivery_lines dl\r\n" + 
			"WHERE  dl.delivery_header_id = :deliveryHeaderId\r\n" + 
			"and 	n.po_notice_id = dl.po_notice_id\r\n" + 
			"AND 0 > (\r\n" + 
			"	n.quantity - (\r\n" + 
			"		SELECT\r\n" + 
			"			ifnull(\r\n" + 
			"				sum(\r\n" + 
			"\r\n" + 
			"					IF (\r\n" + 
			"						hh. STATUS = 'CLOSED',\r\n" + 
			"						tt.delivery_qty,\r\n" + 
			"						tt.delivery_order_qty\r\n" + 
			"					)\r\n" + 
			"				),\r\n" + 
			"				0\r\n" + 
			"			)\r\n" + 
			"		FROM\r\n" + 
			"			srm_po_delivery_lines tt,\r\n" + 
			"			srm_po_delivery_headers hh\r\n" + 
			"		WHERE\r\n" + 
			"			tt.po_notice_id = n.po_notice_id\r\n" + 
			"		AND tt.delivery_header_id = hh.delivery_header_id\r\n" + 
			"		AND hh. STATUS IN ('CREATED', 'CLOSED')\r\n" + 
			"	)\r\n" + 
			") ";
	
	public static final String QUERY_VALIDATE21 = 
			"SELECT\r\n" + 
			"	n.po_notice_code poNoticeCode,\r\n" + 
			"	sph.po_number poNumber,\r\n" + 
			"	spl.line_number lineNumber\r\n" + 
			"FROM\r\n" + 
			"	srm_po_delivery_details dd,\r\n" + 
			"	srm_po_notice n,\r\n" + 
			"	srm_po_notice_line nl,\r\n" + 
			"	srm_po_lines spl,\r\n" + 
			"	srm_po_headers sph\r\n" + 
			"WHERE\r\n" + 
			"	1 = 1\r\n" + 
			"and dd.delivery_header_id = :deliveryHeaderId\r\n" + 
			"AND dd.po_notice_id = n.po_notice_id\r\n" + 
			"AND dd.po_notice_line_id = nl.LINE_ID\r\n" + 
			"AND dd.po_line_id = spl.po_line_id\r\n" + 
			"AND spl.po_header_id = sph.po_header_id\r\n" + 
			"AND 0 > (\r\n" + 
			"	spl.demand_qty - ifnull(spl.delivery_qty, 0) - (\r\n" + 
			"		SELECT\r\n" + 
			"			ifnull(sum(tt.quantity), 0)\r\n" + 
			"		FROM\r\n" + 
			"			srm_po_delivery_details tt,\r\n" + 
			"			srm_po_delivery_headers hh\r\n" + 
			"		WHERE\r\n" + 
			"			tt.po_line_id = spl.po_line_id\r\n" + 
			"		AND tt.delivery_header_id = hh.delivery_header_id\r\n" + 
			"		AND hh. STATUS IN ('CREATED')\r\n" + 
			"	)\r\n" + 
			")";
	
	private Integer deliveryHeaderId;
	private String deliveryNumber;
	private String documentType;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryDate;
	private Integer supplierId;
	private String supplierNumber;
	private Integer instId;
	private String instCode;
	
	private BigDecimal deliveryOrderQty;
	private String poNumber;
	private String lineNumber;
	private Integer itemId;
	private String itemCode;
	private String specialUseNum;
	private BigDecimal planLineId;
	private String poNoticeCode;
	public BigDecimal getPlanLineId() {
		return planLineId;
	}
	public void setPlanLineId(BigDecimal planLineId) {
		this.planLineId = planLineId;
	}
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}
	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
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
	public BigDecimal getDeliveryOrderQty() {
		return deliveryOrderQty;
	}
	public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
		this.deliveryOrderQty = deliveryOrderQty;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
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
	public String getSpecialUseNum() {
		return specialUseNum;
	}
	public void setSpecialUseNum(String specialUseNum) {
		this.specialUseNum = specialUseNum;
	}
	public String getPoNoticeCode() {
		return poNoticeCode;
	}
	public void setPoNoticeCode(String poNoticeCode) {
		this.poNoticeCode = poNoticeCode;
	}

}
