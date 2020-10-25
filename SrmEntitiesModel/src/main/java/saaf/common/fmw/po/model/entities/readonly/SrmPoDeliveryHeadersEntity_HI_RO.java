package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPoDeliveryHeadersEntity_HI_RO implements Serializable {


	//送货单列表查询-订单回货
	public static String QUERY_DELIVERY_PO_SQL =
					"SELECT\n" +
					"  ph.po_number poNumber\n" +
					"  , ins.inst_name orgName\n" +
					"  , ins.inst_id instId\n" +
					"  , ins2.inst_name billToOrganizationName\n" +
					"  , ins2.inst_id billToOrganizationId\n" +
					"  , ph.po_header_id poHeaderId\n" +
					"  , ph.description descriptionPh\n" +
					"  , ph.approved_date approvedDate\n" +
					"  , pl.line_number lineNumber\n" +
					"  , pl.demand_date demandDate\n" +
					"  , pl.may_notice_qty mayNoticeQty\n" +
					"  , pl.on_way_qty onWayQty\n" +
					"  , pl.received_qty receivedQty\n" +
					"  , pl.description descriptionPl\n" +
					"  , ins3.inst_name receiveToOrganizationName\n" +
					"  , ins3.inst_id receiveToOrganizationId\n" +
					"  , pl.demand_qty demandQty\n" +
					"  , pl.po_line_id poLineId\n" +
					"  , pl.version_num AS versionNumPl\n" +
					"  , pl.creation_date AS creationDatePl\n" +
					"  , pl.created_by AS createdByPl\n" +
					"  , bc.full_category_code fullCategoryCode\n" +
					"  , bc.full_category_Name fullCategoryName\n" +
					"  , bc.category_id categoryId\n" +
					"  , bi.item_code itemCode\n" +
					"  , bi.item_Name itemName\n" +
					"  , bi.item_Id itemId\n" +
					"  , se.employee_name employeeName\n" +
					"  , slv.meaning returnGoodsTypeName\n" +
					"  , slv2.meaning statusPh\n" +
					"  , slv3.meaning statusPl\n" +
					"  , slv4.meaning uomCode\n" +
					"FROM\n" +
					"  srm_po_headers ph\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = ph.org_id\n" +
					"  LEFT JOIN saaf_institution ins2\n" +
					"    ON ins2.inst_id = ph.bill_to_organization_id\n" +
					"  LEFT JOIN saaf_employees se\n" +
					"    ON se.employee_id = ph.buyer_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = ph.return_goods_type\n" +
					"    AND slv.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = ph.status\n" +
					"    AND slv2.lookup_type = 'ISP_PO_STATUS'\n" +
					"  , srm_po_lines pl\n" +
					"  LEFT JOIN saaf_institution ins3\n" +
					"    ON ins3.inst_id = pl.receive_to_organization_id\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = pl.item_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = pl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = pl.status\n" +
					"    AND slv3.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_code = bi.uom_code\n" +
					"    AND slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE ph.po_header_id = pl.po_header_id";

	//送货单列表查询-通知回货
	public static String QUERY_DELIVERY_NOTICE_SQL =
					"SELECT\n" +
					"  ph.po_number poNumber\n" +
					"  , pn.po_Notice_Code poNoticeCode\n" +
					"  , pn.issued_date issuedDate\n" +
					"  , pn.comments commentsPn\n" +
					"  , ph.po_header_id poHeaderId\n" +
					"  , ph.description descriptionPh\n" +
					"  , ph.approved_date approvedDate\n" +
					"  , ins.inst_name orgName\n" +
					"  , ins.inst_id instId\n" +
					"  , ins2.inst_name billToOrganizationName\n" +
					"  , ins2.inst_id billToOrganizationId\n" +
					"  , pl.line_number lineNumber\n" +
					"  , pl.demand_date demandDate\n" +
					"  , pl.may_notice_qty mayNoticeQty\n" +
					"  , pl.received_qty receivedQty\n" +
					"  , pl.description descriptionPl\n" +
					"  , pl.demand_qty demandQty\n" +
					"  , pl.po_line_id poLineId\n" +
					"  , pl.version_num AS versionNumPl\n" +
					"  , pl.creation_date AS creationDatePl\n" +
					"  , pl.created_by AS createdByPl\n" +
					"  , pnl.notice_delivery_date AS noticeDeliveryDate\n" +
					"  , pnl.notice_delivery_qty AS noticeDeliveryQty\n" +
					"  , pnl.on_way_qty AS onWayQty\n" +
					"  , pnl.line_id AS lineId\n" +
					"  , bc.full_category_code fullCategoryCode\n" +
					"  , bc.full_category_Name fullCategoryName\n" +
					"  , bc.category_id categoryId\n" +
					"  , ins3.inst_name receiveToOrganizationName\n" +
					"  , ins3.inst_id receiveToOrganizationId\n" +
					"  , bi.item_code itemCode\n" +
					"  , bi.item_Name itemName\n" +
					"  , bi.item_Id itemId\n" +
					"  , se.employee_name employeeName\n" +
					"  , slv.meaning returnGoodsTypeName\n" +
					"  , slv2.meaning statusPh\n" +
					"  , slv3.meaning statusPl\n" +
					"  , slv4.meaning uomCode\n" +
					"  , (\n" +
					"    pnl.notice_delivery_qty -\n" +
					"    (SELECT\n" +
					"      nvl(SUM(pdl.delivery_qty), 0)\n" +
					"    FROM\n" +
					"      srm_po_delivery_lines pdl\n" +
					"    WHERE pdl.notice_line_id = pnl.line_id\n" +
					"      AND pdl.delivery_line_status <> 'CANCELLED')\n" +
					"  ) mayDeliveryQty\n" +
					"FROM\n" +
					"  srm_po_notice pn\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = pn.org_id\n" +
					"  LEFT JOIN saaf_institution ins2\n" +
					"    ON ins2.inst_id = pn.bill_to_organization_id\n" +
					"  , srm_po_notice_line pnl\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON pl.po_line_id = pnl.po_line_id\n" +
					"  LEFT JOIN srm_po_headers ph\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"  LEFT JOIN saaf_employees se\n" +
					"    ON se.employee_id = ph.buyer_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = ph.return_goods_type\n" +
					"    AND slv.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = ph.status\n" +
					"    AND slv2.lookup_type = 'ISP_PO_STATUS'\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = pl.item_id\n" +
					"  LEFT JOIN saaf_institution ins3\n" +
					"    ON ins3.inst_id = pl.receive_to_organization_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = pl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = pl.status\n" +
					"    AND slv3.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON bi.uom_code = slv4.lookup_code\n" +
					"    AND slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE pn.po_notice_id = pnl.po_notice_id\n";

	//送货单查询-内部
	public static String QUERY_DELIVERY_SQL =
					"SELECT dh.delivery_header_id    deliveryHeaderId\n" +
					"      ,dh.delivery_number       deliveryNumber\n" +
					"      ,dh.delivery_date         deliveryDate\n" +
					"      ,dl.delivery_line_id      deliveryLineId\n" +
					"      ,dl.received_qty          receivedQty\n" +
					"      ,dl.delivery_qty          deliveryQty\n" +
					"      ,dl.received_date         receivedDate\n" +
					"      ,dl.creation_date         creationDate\n" +
					"      ,dl.delivery_line_status  deliveryLineStatus\n" +
					"      ,ph.po_number             poNumber\n" +
					"      ,ins.inst_name            receiveToOrganizationName\n" +
					"      ,ins.inst_id              receiveToOrganizationId\n" +
					"      ,pl.line_number           lineNumber\n" +
					"      ,pl.demand_date           demandDate\n" +
					"      ,pl.po_line_id            poLineId\n" +
					"      ,pn.po_notice_code        poNoticeCode\n" +
					"      ,pn.issued_date           issuedDate\n" +
					"      ,pnl.po_notice_id         poNoticeLineId\n" +
					"      ,pnl.notice_delivery_date noticeDeliveryDate\n" +
					"      ,pnl.notice_delivery_qty  noticeDeliveryQty\n" +
					"      ,bc.full_category_code    fullCategoryCode\n" +
					"      ,bc.full_category_name    fullCategoryName\n" +
					"      ,bc.category_id           categoryId\n" +
					"      ,bi.item_code             itemCode\n" +
					"      ,bi.item_name             itemName\n" +
					"      ,bi.item_id               itemId\n" +
					"      ,si.supplier_name         supplierName\n" +
					"      ,slv.meaning              returnGoodsTypeName\n" +
					"      ,slv2.meaning             statusStr\n" +
					"      ,slv3.meaning             deliveryLineStatusName\n" +
					"      ,slv4.meaning             uomCode\n" +
					"FROM   srm_po_delivery_headers dh\n" +
					"LEFT   JOIN srm_po_delivery_lines dl\n" +
					"ON     dl.delivery_header_id = dh.delivery_header_id\n" +
					"LEFT   JOIN saaf_institution ins\n" +
					"ON     dh.receive_to_organization_id = ins.inst_id\n" +
					"AND    ins.inst_type = 'ORGANIZATION'\n" +
					"LEFT   JOIN srm_po_lines pl\n" +
					"ON     pl.po_line_id = dl.po_line_id\n" +
					"LEFT   JOIN srm_po_headers ph\n" +
					"ON     ph.po_header_id = pl.po_header_id\n" +
					"LEFT   JOIN srm_po_notice_line pnl\n" +
					"ON     pnl.line_id = dl.notice_line_id\n" +
					"LEFT   JOIN srm_po_notice pn\n" +
					"ON     pn.po_notice_id = pnl.po_notice_id\n" +
					"LEFT   JOIN srm_pos_supplier_info si\n" +
					"ON     dh.supplier_id = si.supplier_id\n" +
					"LEFT   JOIN srm_base_items_b bi\n" +
					"ON     bi.item_id = pl.item_id\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     pl.category_id = bc.category_id\n" +
					"LEFT   JOIN saaf_employees se\n" +
					"ON     se.employee_id = ph.buyer_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"AND    dh.return_goods_type = slv.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_type = 'ISP_DELIVERY_ORDER_STATUS'\n" +
					"AND    dh.status = slv2.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_type = 'ISP_DELIVERY_ORDER_LINE_STATUS'\n" +
					"AND    dl.delivery_line_status = slv3.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values slv4\n" +
					"ON     slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"AND    slv4.lookup_code = bi.uom_code\n" +
					"WHERE  1 = 1\n";

	//送货通知查询-供应商/外部
	public static String QUERY_DELIVERY_SUPPLIER_SQL =
					"SELECT\n" +
					"  dh.delivery_header_id deliveryHeaderId,\n" +
					"  dh.creation_date creationDate,\n" +
					"  dh.delivery_date deliveryDate,\n" +
					"  dh.delivery_number deliveryNumber,\n" +
					"  dl.delivery_line_id deliveryLineId,\n" +
					"  dl.delivery_qty deliveryQty,\n" +
					"  dl.received_qty receivedQty,\n" +
					"  dl.received_date receivedDate,\n" +
					"  dl.delivery_line_status deliveryLineStatus,\n" +
					"  si1.inst_id receiveToOrganizationId,\n" +
					"  si1.inst_name receiveToOrganizationName,\n" +
					"  ph.po_number poNumber,\n" +
					"  pl.line_number lineNumber,\n" +
					"  pl.po_line_id poLineId,\n" +
					"  pn.po_notice_code poNoticeCode,\n" +
					"  pnl.po_notice_id poNoticeLineId,\n" +
					"  bi.item_Id itemId,\n" +
					"  bi.item_code itemCode,\n" +
					"  bi.item_Name itemName,\n" +
					"  bc.category_id categoryId,\n" +
					"  bc.full_category_code fullCategoryCode,\n" +
					"  bc.full_category_Name fullCategoryName,\n" +
					"  slv1.meaning returnGoodsTypeName,\n" +
					"  slv2.meaning statusStr,\n" +
					"  slv3.meaning deliveryLineStatusName,\n" +
					"  slv4.meaning uomCode\n" +
					"FROM\n" +
					"  srm_po_delivery_headers dh\n" +
					"  LEFT JOIN saaf_institution si1\n" +
					"    ON si1.inst_id = dh.receive_to_organization_id\n" +
					"    AND si1.inst_type = 'ORGANIZATION'\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = dh.return_goods_type\n" +
					"    AND slv1.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = dh.status\n" +
					"    AND slv2.lookup_type = 'ISP_DELIVERY_ORDER_STATUS',\n" +
					"  srm_po_delivery_lines dl\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON pl.po_line_id = dl.po_line_id\n" +
					"  LEFT JOIN srm_po_headers ph\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"  LEFT JOIN srm_po_notice_line pnl\n" +
					"    ON pnl.line_id = dl.notice_line_id\n" +
					"  LEFT JOIN srm_po_notice pn\n" +
					"    ON pn.po_notice_id = pnl.po_notice_id\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = pl.item_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = dl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = dl.delivery_line_status\n" +
					"    AND slv3.lookup_type = 'ISP_DELIVERY_ORDER_LINE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON bi.uom_code = slv4.lookup_code\n" +
					"    AND slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE dh.delivery_header_id = dl.delivery_header_id";

	public static String LOV_QUERY_DELIVERY_SQL="SELECT\r\n" +
			"dh.delivery_header_id deliveryHeaderId\r\n" + 
			",dh.delivery_number deliveryNumber\r\n" + 
			"FROM\r\n" + 
			"srm_po_delivery_headers dh\r\n" + 
			"WHERE 1=1";

	/**
	 * 送货单查询(重新打印)-查询头信息
	 */
	public static String QUERY_DELIVERY_HEADER_PRINT="SELECT \r\n" +
			"dh.delivery_header_id deliveryHeaderId \r\n" +
			",dh.delivery_number AS deliveryNumber\r\n" +
			",dh.delivery_date AS deliveryDate\r\n" +
			",psi.supplier_name AS supplierName\r\n" +
			",ins.inst_name instName \r\n" +
			"FROM \r\n" +
			"srm_po_delivery_headers dh \r\n" +
			"LEFT JOIN saaf_institution ins on dh.receive_to_organization_id = ins.inst_id \r\n" +
			"LEFT JOIN srm_pos_supplier_info psi on psi.supplier_id = dh.supplier_id \r\n" +
			"WHERE 1=1";

	/**
	 * 送货单查询(重新打印)-查询行信息
	 */
	public static String QUERY_DELIVERY_SUPPLIER_LINE_PRINT =
					"SELECT\n" +
					"  dh.delivery_header_id deliveryHeaderId\n" +
					"  , dh.delivery_number AS deliveryNumber\n" +
					"  , dl.delivery_qty AS deliveryQty\n" +
					"  , ins.inst_name instName\n" +
					"  , ph.po_number poNumber\n" +
					"  , bc.full_category_code fullCategoryCode\n" +
					"  , bc.full_category_Name fullCategoryName\n" +
					"  , bi.item_code itemCode\n" +
					"  , bi.item_Name itemName\n" +
					"  , slv4.meaning uomCode\n" +
					"FROM\n" +
					"  srm_po_delivery_headers dh\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = dh.receive_to_organization_id\n" +
					"  , srm_po_delivery_lines dl\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON pl.po_line_id = dl.po_line_id\n" +
					"  LEFT JOIN srm_po_headers ph\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = pl.item_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = dl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv4.lookup_code = bi.uom_code\n" +
					"WHERE dh.delivery_header_id = dl.delivery_header_id";
	/**
	 * 查询送货单信息-打印订单回货-头
	 */
	public static String QUERY_DELIVERY_PO_PRINT =
					"SELECT\n" +
					"  dh.delivery_header_id deliveryHeaderId,\n" +
					"  dh.delivery_number AS deliveryNumber,\n" +
					"  dh.delivery_date AS deliveryDate,\n" +
					"  psi.supplier_name AS supplierName,\n" +
					"  ph.po_number poNumber,\n" +
					"  ins.inst_name instName,\n" +
					"  bi.item_code itemCode,\n" +
					"  bi.item_Name itemName,\n" +
					"  slv4.meaning uomCode\n" +
					"FROM\n" +
					"  srm_po_delivery_headers dh\n" +
					"  LEFT JOIN srm_po_delivery_lines dl\n" +
					"    ON dl.delivery_header_id = dh.delivery_header_id\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON pl.po_line_id = dl.po_line_id\n" +
					"  LEFT JOIN srm_po_headers ph\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"  LEFT JOIN srm_pos_supplier_info psi\n" +
					"    ON psi.supplier_id = dh.supplier_id\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON dh.receive_to_organization_id = ins.inst_id\n" +
					"  LEFT JOIN srm_base_items bi\n" +
					"    ON pl.item_id = bi.item_id\n" +
					"    AND EXISTS\n" +
					"    (SELECT\n" +
					"      1\n" +
					"    FROM\n" +
					"      saaf_institution si,\n" +
					"      srm_po_headers ph\n" +
					"    WHERE si.inst_id = bi.organization_id\n" +
					"      AND si.inst_type = 'ORGANIZATION'\n" +
					"      AND si.parent_inst_id = ph.org_id\n" +
					"      AND pl.po_header_id = ph.po_header_id)\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON dl.category_id = bc.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv4.lookup_code = bi.uom_code\n" +
					"WHERE 1 = 1";

	/**
	 * 查询送货单信息-打印订单回货-行
	 */
	public static String QUERY_DELIVERY_PO_LINE_PRINT =
					"SELECT\n" +
					"  dh.delivery_header_id deliveryHeaderId\n" +
					"  , dh.delivery_number AS deliveryNumber\n" +
					"  , dl.delivery_qty AS deliveryQty\n" +
					"  , ins.inst_name instName\n" +
					"  , ph.po_number poNumber\n" +
					"  , bc.full_category_code fullCategoryCode\n" +
					"  , bc.full_category_Name fullCategoryName\n" +
					"  , bi.item_code itemCode\n" +
					"  , bi.item_Name itemName\n" +
					"  , slv4.meaning uomCode\n" +
					"FROM\n" +
					"  srm_po_delivery_headers dh\n" +
					"  LEFT JOIN saaf_institution ins\n" +
					"    ON ins.inst_id = dh.receive_to_organization_id\n" +
					"  , srm_po_delivery_lines dl\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON pl.po_line_id = dl.po_line_id\n" +
					"  LEFT JOIN srm_po_headers ph\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = pl.item_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = dl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv4.lookup_code = bi.uom_code\n" +
					"WHERE dh.delivery_header_id = dl.delivery_header_id\n";

	public static String QUERY_DELIVERY_LINE="SELECT \r\n" + 
			"dl.delivery_line_id deliveryLineId \r\n" + 
			",dl.special_use_num specialUseNum \r\n" + 
			",dl.demand_classify demandClassify \r\n" + 
			",dl.delivery_qty deliveryQty \r\n" + 
			",dl.weight weight \r\n" + 
			",dl.delivery_order_qty deliveryOrderQty\r\n" + 
			",dl.description description\r\n" + 
			",it.inst_name instName \r\n" +
			",i.inst_name deliveryAddress\r\n" +
			",bc.category_name categoryName \r\n" + 
			",bi.item_code itemCode \r\n" + 
			",bi.item_name itemName \r\n" + 
			",bi.unit_of_measure_name unitOfMeasureName\r\n" + 
			",bi.item_description itemDescription \r\n" + 
			",pn.demand_date demandDate  \r\n" + 
			",pn.po_notice_code poNoticeCode \r\n" + 
			",ph.po_number poNumber\r\n" + 
			"FROM \r\n" + 
			"srm_po_delivery_lines dl\r\n" + 
			"LEFT JOIN srm_po_notice pn on dl.po_notice_id = pn.po_notice_id\r\n" + 
			"LEFT JOIN srm_po_delivery_headers dh \r\n" + 
			"on dl.delivery_header_id = dh.delivery_header_id\r\n" + 
			"left join srm_base_items bi on dl.item_id = bi.item_id\r\n" + 
			"LEFT JOIN srm_base_categories bc on bi.category_code = bc.category_code \r\n" + 
			"left join srm_po_lines pl on dl.po_line_id = pl.po_line_id\r\n" + 
			"LEFT JOIN srm_po_headers ph on pl.po_header_id = ph.po_header_id\r\n" +
			"LEFT JOIN saaf_institution it on dl.inst_id = it.inst_id\r\n" + 
			"LEFT JOIN saaf_institution i on dh.delivery_site_id = i.inst_id\r\n" + 
			"WHERE 1=1\r\n";
	
	public static String QUERY_DELIVERY_LINE_PRINT="SELECT \r\n" + 
			"dl.delivery_line_id deliveryLineId \r\n" + 
			",dl.special_use_num specialUseNum \r\n" + 
			",dl.weight weight \r\n" + 
			",dl.delivery_order_qty deliveryOrderQty\r\n" + 
			",dh.delivery_header_id deliveryHeaderId\r\n" + 
			",bi.item_code itemCode \r\n" + 
			",bi.item_name itemName \r\n" + 
			",bi.unit_of_measure_name unitOfMeasureName\r\n" + 
			",ph.po_number poNumber\r\n" + 
			",pl.description description\r\n" + 
			"FROM \r\n" + 
			"srm_po_delivery_lines dl\r\n" + 
			"LEFT JOIN srm_po_delivery_headers dh \r\n" + 
			"on dl.delivery_header_id = dh.delivery_header_id\r\n" + 
			"LEFT JOIN srm_base_items bi on dl.item_id = bi.item_id\r\n" + 
			"LEFT JOIN srm_po_lines pl on dl.po_line_id = pl.po_line_id\r\n" + 
			"LEFT JOIN srm_po_headers ph on pl.po_header_id = ph.po_header_id\r\n" + 
			"WHERE 1=1";
	
	public static String QUERY_DELIVERY_LINE_PRINT2="SELECT \r\n" + 
			"dl.delivery_line_id deliveryLineId \r\n" + 
			",dl.special_use_num specialUseNum \r\n" + 
			",dl.weight weight \r\n" + 
			",pd.quantity deliveryOrderQty\r\n" + 
			",bi.item_id itemId\r\n" + 
			",bi.item_code itemCode \r\n" + 
			",bi.item_name itemName \r\n" + 
			",bi.unit_of_measure_name unitOfMeasureName\r\n" + 
			",ph.po_number poNumber\r\n" + 
			",dh.delivery_header_id \r\n" + 
			",pl.description description\r\n" + 
			"FROM \r\n" + 
			"srm_po_delivery_lines dl\r\n" + 
			"LEFT JOIN srm_po_delivery_headers dh \r\n" + 
			"on dl.delivery_header_id = dh.delivery_header_id\r\n" + 
			"LEFT JOIN srm_base_items bi on dl.item_id = bi.item_id\r\n" + 
			",srm_po_delivery_details pd\r\n" + 
			"LEFT JOIN srm_po_lines pl on pd.po_line_id = pl.po_line_id\r\n" + 
			"LEFT JOIN srm_po_headers ph on pl.po_header_id = ph.po_header_id\r\n" + 
			"WHERE 1=1\r\n" + 
			"AND dl.delivery_line_id = pd.delivery_line_id";
	
	
	public static String QUERY_DELIVERY_LINE_PRINT_COUNT="SELECT\r\n" + 
			"dl.delivery_line_id deliveryLineId \r\n" + 
			",SUM(dl.weight) weightCount \r\n" + 
			",sum(dl.delivery_order_qty) qtyCount\r\n" + 
			",bi.item_code itemCode \r\n" + 
			",bi.item_name itemName \r\n" + 
			",bi.unit_of_measure_name unitOfMeasureName\r\n" + 
			"FROM \r\n" + 
			"srm_po_delivery_lines dl\r\n" + 
			"LEFT JOIN srm_po_notice pn on dl.po_notice_id = pn.po_notice_id\r\n" + 
			"LEFT JOIN srm_po_delivery_headers dh\r\n" + 
			"on dl.delivery_header_id = dh.delivery_header_id\r\n" + 
			"left join srm_base_items bi on dl.item_id = bi.item_id\r\n" + 
			"WHERE 1=1\r\n";
	
	private Integer deliveryHeaderId;
    private String deliveryNumber;
    private String transportNumber;
    @JSONField(format = "yyyy-MM-dd")
    private Date deliveryDate;
    private Integer supplierId;
    private String documentType;
    private String receiveFlag;
    private String description;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDateDl;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDatePl;
    private String instName;
    private String supplierName;//供应商名称
    private String supplierNumber;
    
    private Integer deliveryLineId;
    private Integer lineNumber;
    private Integer poLineId;
    private Integer lineId;//送货通知行Id
    private Integer poNoticeLineId;
    private String specialUseNum;
    private String demandClassify;
    private BigDecimal deliveryQty;
    private BigDecimal weight;
    private BigDecimal deliveryOrderQty;
    private String deliveryStatus;
    private String deliveryStatusMean;
    private String categoryName;
    private String fullCategoryCode;
    private String fullCategoryName;
    private String itemCode;
    private Integer instId;//业务实体Id
	private String orgName;//别名-业务实体
    private Integer billToOrganizationId;//收单组织Id
	private String billToOrganizationName;//别名-收单组织
	private Integer receiveToOrganizationId;//收货组织Id
	private String receiveToOrganizationName;//别名-收货组织
    private Integer itemId;
    private Integer organizationId;
    private Integer organizationId2;
    private Integer categoryId;
    private String itemName;
    private String unitOfMeasureName;
    private String itemDescription;
    private String uomCode;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date noticeDeliveryDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date issuedDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date approvedDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date receivedDate;//接收日期
    private String poNoticeCode;
    private String poNumber;//采购订单行号
    private String poHeaderId;//采购订单头Id
    private String descriptionPh;//订单备注-采购订单头表
    private String descriptionPl;//订单行备注-采购订单行表

    private String deliveryDescription;
    private String employeeName;//采购员

    private String userFullName;
	private Integer versionNum;//头表版本号
	private Integer versionNumPl;//采购订单行表版本号
	private Integer versionNumDl;//行表版本号
	private Integer createdBy;//头创建人
	private Integer createdByDl;//行创建人
	private Integer createdByPl;//采购订单行创建人

    private String u9DocNumber;
    private String commentsPn;//送货通知备注
    private String lineComments;//送货通知行备注

    private BigDecimal weightCount;
    private BigDecimal qtyCount;
    private BigDecimal demandQty;//订单数量
    private BigDecimal mayNoticeQty;//可通知送货数量
    private BigDecimal onWayQty;//在途数量
    private BigDecimal receivedQty;//已接收数量
    private BigDecimal noticeDeliveryQty;//采购通知行表-通知送货数量
	private BigDecimal mayDeliveryQty;  //可送货数量

    private String deliveryAddress;
    private String deliveryLineStatus;//送货单行状态
    private String deliveryLineStatusName;//送货单行状态-别名
    private String returnGoodsTypeName;//回货方式
    private String statusPh;//采购订单头状态
    private String statusPl;//采购订单行状态
    private String statusStr;//送货单状态别名

	public String getSupplierNumber() {
		return supplierNumber;
	}
	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	public String getStatusPh() {
		return statusPh;
	}
	public void setStatusPh(String statusPh) {
		this.statusPh = statusPh;
	}
	public String getStatusPl() {
		return statusPl;
	}
	public void setStatusPl(String statusPl) {
		this.statusPl = statusPl;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getReturnGoodsTypeName() {
		return returnGoodsTypeName;
	}
	public void setReturnGoodsTypeName(String returnGoodsTypeName) {
		this.returnGoodsTypeName = returnGoodsTypeName;
	}
	public String getDeliveryLineStatus() {
		return deliveryLineStatus;
	}
	public void setDeliveryLineStatus(String deliveryLineStatus) {
		this.deliveryLineStatus = deliveryLineStatus;
	}
	public String getDeliveryLineStatusName() {
		return deliveryLineStatusName;
	}
	public void setDeliveryLineStatusName(String deliveryLineStatusName) {
		this.deliveryLineStatusName = deliveryLineStatusName;
	}
	public String getDescriptionPh() {
		return descriptionPh;
	}
	public void setDescriptionPh(String descriptionPh) {
		this.descriptionPh = descriptionPh;
	}
	public String getDescriptionPl() {
		return descriptionPl;
	}
	public void setDescriptionPl(String descriptionPl) {
		this.descriptionPl = descriptionPl;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
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
	public String getTransportNumber() {
		return transportNumber;
	}
	public void setTransportNumber(String transportNumber) {
		this.transportNumber = transportNumber;
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
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	public String getReceiveFlag() {
		return receiveFlag;
	}
	public void setReceiveFlag(String receiveFlag) {
		this.receiveFlag = receiveFlag;
	}
	public String getCommentsPn() {
		return commentsPn;
	}
	public void setCommentsPn(String commentsPn) {
		this.commentsPn = commentsPn;
	}
	public String getLineComments() {
		return lineComments;
	}
	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
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
	public Date getCreationDateDl() {
		return creationDateDl;
	}
	public void setCreationDateDl(Date creationDateDl) {
		this.creationDateDl = creationDateDl;
	}
	public Date getCreationDatePl() {
		return creationDatePl;
	}
	public void setCreationDatePl(Date creationDatePl) {
		this.creationDatePl = creationDatePl;
	}
	public Integer getInstId() {
		return instId;
	}
	public void setInstId(Integer instId) {
		this.instId = instId;
	}
	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}
	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getCreatedByDl() {
		return createdByDl;
	}
	public void setCreatedByDl(Integer createdByDl) {
		this.createdByDl = createdByDl;
	}
	public Integer getCreatedByPl() {
		return createdByPl;
	}
	public void setCreatedByPl(Integer createdByPl) {
		this.createdByPl = createdByPl;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}
	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
	public Integer getVersionNumDl() {
		return versionNumDl;
	}
	public void setVersionNumDl(Integer versionNumDl) {
		this.versionNumDl = versionNumDl;
	}
	public Integer getVersionNumPl() {
		return versionNumPl;
	}
	public void setVersionNumPl(Integer versionNumPl) {
		this.versionNumPl = versionNumPl;
	}
	public Integer getOrganizationId2() {
		return organizationId2;
	}
	public void setOrganizationId2(Integer organizationId2) {
		this.organizationId2 = organizationId2;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getFullCategoryCode() {
		return fullCategoryCode;
	}
	public void setFullCategoryCode(String fullCategoryCode) {
		this.fullCategoryCode = fullCategoryCode;
	}
	public String getFullCategoryName() {
		return fullCategoryName;
	}
	public void setFullCategoryName(String fullCategoryName) {
		this.fullCategoryName = fullCategoryName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getDeliveryLineId() {
		return deliveryLineId;
	}
	public void setDeliveryLineId(Integer deliveryLineId) {
		this.deliveryLineId = deliveryLineId;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public Integer getPoLineId() {
		return poLineId;
	}
	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getPoNoticeLineId() {
		return poNoticeLineId;
	}
	public void setPoNoticeLineId(Integer poNoticeLineId) {
		this.poNoticeLineId = poNoticeLineId;
	}
	public String getSpecialUseNum() {
		return specialUseNum;
	}
	public void setSpecialUseNum(String specialUseNum) {
		this.specialUseNum = specialUseNum;
	}
	public String getDemandClassify() {
		return demandClassify;
	}
	public void setDemandClassify(String demandClassify) {
		this.demandClassify = demandClassify;
	}
	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}
	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getDeliveryOrderQty() {
		return deliveryOrderQty;
	}
	public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
		this.deliveryOrderQty = deliveryOrderQty;
	}
	
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getDeliveryStatusMean() {
		return deliveryStatusMean;
	}
	public void setDeliveryStatusMean(String deliveryStatusMean) {
		this.deliveryStatusMean = deliveryStatusMean;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getBillToOrganizationName() {
		return billToOrganizationName;
	}
	public void setBillToOrganizationName(String billToOrganizationName) {
		this.billToOrganizationName = billToOrganizationName;
	}
	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}
	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}
	public void setUnitOfMeasureName(String unitOfMeasureName) {
		this.unitOfMeasureName = unitOfMeasureName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public Date getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	public Date getNoticeDeliveryDate() {
		return noticeDeliveryDate;
	}
	public void setNoticeDeliveryDate(Date noticeDeliveryDate) {
		this.noticeDeliveryDate = noticeDeliveryDate;
	}
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getPoNoticeCode() {
		return poNoticeCode;
	}
	public void setPoNoticeCode(String poNoticeCode) {
		this.poNoticeCode = poNoticeCode;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPoHeaderId() {
		return poHeaderId;
	}
	public void setPoHeaderId(String poHeaderId) {
		this.poHeaderId = poHeaderId;
	}
	public String getDeliveryDescription() {
		return deliveryDescription;
	}
	public void setDeliveryDescription(String deliveryDescription) {
		this.deliveryDescription = deliveryDescription;
	}
	public BigDecimal getWeightCount() {
		return weightCount;
	}
	public void setWeightCount(BigDecimal weightCount) {
		this.weightCount = weightCount;
	}
	public BigDecimal getQtyCount() {
		return qtyCount;
	}
	public void setQtyCount(BigDecimal qtyCount) {
		this.qtyCount = qtyCount;
	}
	public BigDecimal getDemandQty() {
		return demandQty;
	}
	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}
	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}
	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}
	public BigDecimal getOnWayQty() {
		return onWayQty;
	}
	public void setOnWayQty(BigDecimal onWayQty) {
		this.onWayQty = onWayQty;
	}
	public BigDecimal getReceivedQty() {
		return receivedQty;
	}
	public void setReceivedQty(BigDecimal receivedQty) {
		this.receivedQty = receivedQty;
	}
	public BigDecimal getNoticeDeliveryQty() {
		return noticeDeliveryQty;
	}
	public void setNoticeDeliveryQty(BigDecimal noticeDeliveryQty) {
		this.noticeDeliveryQty = noticeDeliveryQty;
	}

	public String getU9DocNumber() {
		return u9DocNumber;
	}
	public void setU9DocNumber(String u9DocNumber) {
		this.u9DocNumber = u9DocNumber;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
    
    public BigDecimal getMayDeliveryQty() {
		return mayDeliveryQty;
	}

	public void setMayDeliveryQty(BigDecimal mayDeliveryQty) {
		this.mayDeliveryQty = mayDeliveryQty;
	}
}
