package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class SrmPoNoticeEntity_HI_RO implements Serializable {

    public static final String QUERY_NOTICE_SQL = "SELECT\n" +
            "\tspn.po_notice_id poNoticeId,\n" +
            "\tspn.po_notice_code poNoticeCode,\n" +
            "\tspn.inst_id instId,\n" +
            "\tspn.item_id itemId,\n" +
            "\tspn.supplier_id supplierId,\n" +
            "\tspn.demand_date demandDate,\n" +
            "\tspn.quantity quantity,\n" +
            "\tspn.employee_num employeeNum,\n" +
            "\tspn.`status` STATUS,\n" +
            "\tspn.SPECIAL_USE_NUM specialUseNum,\n" +
            "\tspn.demand_classify demandClassify,\n" +
            "\tget_notice_qty_f (\n" +
            "\t\tspn.po_notice_id,\n" +
            "\t\t'CREATE_ACT_DELIVERY'\n" +
            "\t) deliveryOrderQty,\n" +
            //"\tif(get_notice_qty_f (spn.po_notice_id,'CREATE_ACT_DELIVERY')=0,'Y','N') closableFlag,\n" +
            "\tifnull(spn.delivery_qty,0) deliveryQty,\n" +
            "\tslv2.meaning affirmStatus,\n" +
            "\tspn.reject_reason rejectReason,\n" +
            "\tspn.VERSION_NUM versionNum,\n" +
            "\tspn.CREATION_DATE creationDate,\n" +
            "\tslv1.meaning noticeStatusName,\n" +
            "\tse.employee_name employeeName,\n" +
            "\tspsi.supplier_name supplierName,\n" +
            "\tsi.inst_name instName,\n" +
            "\ts2.inst_name deliverySiteName,\n" +
            "\tsbi.ITEM_CODE itemCode,\n" +
            "\tsbi.ITEM_NAME itemName,\n" +
            "\tsbc.CATEGORY_CODE categoryCode,\n" +
            "\tsbc.CATEGORY_NAME categoryName\n" +
            "FROM\n" +
            "\tsrm_po_notice AS spn\n" +
            "LEFT JOIN saaf_lookup_values AS slv1 ON slv1.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv1.lookup_code = spn.STATUS\n" +
            "LEFT JOIN saaf_lookup_values AS slv2 ON slv2.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv2.lookup_code = spn.affirm_status\n" +
            "LEFT JOIN saaf_employees AS se ON se.employee_number = spn.employee_num\n" +
            "LEFT JOIN srm_pos_supplier_info AS spsi ON spsi.supplier_id = spn.supplier_id\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = spn.inst_id\n" +
            "LEFT JOIN saaf_institution AS s2 ON s2.inst_id = spn.delivery_site_id\n" +
            "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = spn.item_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1 ";



    public static final String QUERY_NOTICE_COUNT_SQL="SELECT\n" +
            "\tcount(*)\n" +
            "FROM\n" +
            "\tsrm_po_notice AS spn\n" +
            "LEFT JOIN saaf_lookup_values AS slv1 ON slv1.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv1.lookup_code = spn.STATUS\n" +
            "LEFT JOIN saaf_lookup_values AS slv2 ON slv2.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv2.lookup_code = spn.affirm_status\n" +
            "LEFT JOIN saaf_employees AS se ON se.employee_number = spn.employee_num\n" +
            "LEFT JOIN srm_pos_supplier_info AS spsi ON spsi.supplier_id = spn.supplier_id\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = spn.inst_id\n" +
            "LEFT JOIN saaf_institution AS s2 ON s2.inst_id = spn.delivery_site_id\n" +
            "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = spn.item_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1 ";
    //查询送货通知
    public static final String QUERY_SRM_PO_NOTICE_SQL = "SELECT DISTINCT\n" +
    		"\tspn.po_notice_id poNoticeId,\n"+
    		"\tspn.po_notice_code poNoticeCode,\n"+
    		"\tspn.po_notice_status poNoticeStatus,\n"+
    		"\tspn.inst_id instId,\n"+
    		"\tspn.item_id itemId,\n"+
    		"\tspn.supplier_id supplierId,\n"+
    		"\tspn.quantity quantity,\n"+
    		"\tspn.employee_num employeeNum,\n"+
    		"\tspn.SPECIAL_USE_NUM specialUseNum,\n"+
    		"\tspn.demand_classify demandClassify,\n"+
    		"\tspn.reject_reason rejectReason,\n"+
    		"\tspn.VERSION_NUM versionNum,\n"+
    		"\tspn.CREATION_DATE creationDate,\n"+
    		"\tspn.issued_date issuedDate,\n"+
    		"\tspn.comments comments,\n"+
    		"\tst.site_name siteName,\n"+
    		"\tsi.inst_name instName,\n"+
    		"\tnl.notice_delivery_date noticeDeliveryDate,\n"+
    		"\tnl.notice_delivery_qty noticeDeliveryQty,\n"+
    		"\tnl.line_status lineStatus,\n"+
    		"\tslv2.meaning feedbackStatusStr,\n"+
    		"\tnl.feedback_status feedbackStatus,\n"+
    		"\tnl.feedback_result feedbackResult,\n"+
    		"\tnl.line_id lineId,\n"+
    		"\tnl.po_line_id poLineId,\n"+
    		"\tnl.feedback_adjust_date feedbackAdjustDate,\n"+
    		"\tnl.feedback_adjust_qty feedbackAdjustQty,\n"+
    		"\tpl.demand_date demandDate,\n"+
    		"\tpl.demand_qty demandQty,\n"+
    		"\tpl.line_number lineNumber,\n"+
    		"\tnl.original_delivery_date originalDemandDate,\n"+
    		"\tnl.original_delivery_qty originalDemandQty,\n"+
    		"\tspsi.supplier_name supplierName,\n"+
//    		"\tspss.site_name siteName,\n"+
    		"\tsbi.item_name itemName,\n"+
    		"\tsbi.item_code itemCode,\n"+
    		"\tsbc.category_name categoryName,\n"+
    		"\tsbc.category_code categoryCode,\n"+
    		"\t(pl.demand_qty-pl.notice_qty) mayNoticeQty,\n"+
    		"\tph.po_number poNumber,\n"+
    		"\tslv1.meaning noticeStatusName,\n"+
    		"\tslv.meaning lineStatusName, \n"+
    		"\t(SELECT sum(spnl.notified_qty) FROM srm_po_lines spnl LEFT JOIN srm_po_lines spl ON spl.po_line_id = spnl.po_line_id AND ( spl.`status` = 'APPROVED' || spl.`status` = 'NATURAL_CLOSED' || spl.`status` = 'ClosedExcess' ) WHERE spnl.po_line_id = pl.po_line_id) notifiedQty, \n"+
    		"\t(select sum(s2.received_qty) from srm_po_notice_line n2 LEFT JOIN srm_po_lines s2 ON s2.po_line_id = n2.po_line_id where n2.po_notice_id =spn.po_notice_id) receivedQty,\n"+
    		"\t(select sum(p3.on_way_qty) from srm_po_notice_line n3,srm_po_lines p3 where n3.line_status='APPROVED' and n3.po_line_id=p3.po_line_id and n3.po_notice_id=spn.po_notice_id )onWayQty\n"+
    		"\tFROM srm_po_notice AS spn \n"+
    		"\tLEFT JOIN srm_pos_supplier_sites st ON st.supplier_site_id=spn.supplier_site_id \n"+
    		"\tLEFT JOIN srm_po_notice_line nl ON nl.po_notice_id=spn.po_notice_id and nl.notice_delivery_date is not null \n"+
    		"\tLEFT JOIN srm_po_lines pl ON pl.po_line_id=nl.po_line_id and pl.po_header_id=nl.po_header_id \n"+
    		"\tLEFT JOIN srm_po_headers ph ON ph.po_header_id=nl.po_header_id \n"+
    		"\tLEFT JOIN saaf_lookup_values slv ON slv.lookup_code=nl.line_status and slv.lookup_type='ISP_DELIVERYNOTICE_LINE_STATUS' \n"+
    		"\tLEFT JOIN srm_base_items AS sbi ON sbi.item_id = pl.item_id \n"+
    		"\tLEFT JOIN srm_base_categories sbc ON sbc.category_id=pl.category_id \n"+
    		"\tLEFT JOIN saaf_institution si ON si.inst_id = spn.bill_to_organization_id \n"+
    		"\tLEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = spn.supplier_id \n"+
    		"\tLEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_code = spn.po_notice_status and slv1.lookup_type = 'ISP_DELIVERYNOTICE_STATUS' \n"+
    		"\tLEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_code = nl.feedback_status and slv2.lookup_type = 'ISP_PO_FEEDBACK_STATUS' \n"+
    		"WHERE\n" +
            "\t1 = 1 ";
    //查询分页
    public static final String QUERY_SRM_PO_NOTICE_COUNT_SQL="SELECT\n" +
            "\tcount(DISTINCT spn.po_notice_id)\n" +
            "\tFROM srm_po_notice AS spn\n" +
            "\tLEFT JOIN srm_pos_supplier_sites st ON st.supplier_site_id=spn.supplier_site_id \n"+
    		"\tLEFT JOIN srm_po_notice_line nl ON nl.po_notice_id=spn.po_notice_id \n"+
    		"\tLEFT JOIN srm_po_lines pl ON pl.po_line_id=nl.po_line_id and pl.po_header_id=nl.po_header_id \n"+
    		"\tLEFT JOIN saaf_lookup_values slv ON slv.lookup_code=nl.line_status and slv.lookup_type='ISP_DELIVERYNOTICE_LINE_STATUS' \n"+
    		"\tLEFT JOIN srm_base_items AS sbi ON sbi.item_id = pl.item_id \n"+
    		"\tLEFT JOIN srm_base_categories sbc ON sbc.category_id=pl.category_id \n"+
    		"\tLEFT JOIN saaf_institution si ON si.inst_id = spn.bill_to_organization_id \n"+
    		"\tLEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = spn.supplier_id \n"+
//    		"\tLEFT JOIN srm_pos_supplier_sites spss ON spss.supplier_site_id=spn.supplier_site_id \n"+
    		"\tLEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_code = spn.po_notice_status and slv1.lookup_type = 'PO_NOTICE_STATUS' \n"+
    		"WHERE\n" +
    		"\t1 = 1 ";

	public static final String QUERY_PO_NOTICE_INFO =
					"SELECT pnl.line_id lineLd\n" +
					"      ,pl.po_line_id poLineId\n" +
					"      ,si2.inst_name receiveToOrganizationName\n" +
					"      ,si3.inst_name billToOrganizationName\n" +
					"      ,pn.po_notice_code poNoticeCode\n" +
					"      ,pn.po_notice_id poNoticeId\n" +
					"      ,pl.demand_date demandDate\n" +
					"      ,pnl.original_delivery_date originalDemandDate\n" +
					"      ,pn.issued_date issuedDate\n" +
					"      ,bi.item_name itemName\n" +
					"      ,bi.item_code itemCode\n" +
					"      ,pl.demand_qty demandQty\n" +
					"      ,pnl.notice_delivery_qty noticeDeliveryQty -- 本次通知数量\n" +
					"      ,pnl.notice_delivery_date noticeDeliveryDate\n" +
					"      ,slv.meaning uomCodeDesc\n" +
					"      ,spsi.supplier_name supplierName\n" +
					"      ,sit.site_name siteName\n" +
					"      ,bc.category_name categoryName\n" +
					"      ,bc.category_code categoryCode\n" +
					"      ,pn.status status\n" +
					"      ,pn.po_notice_status poNoticeStatus\n" +
					"      ,slv4.meaning noticeStatusName\n" +
					"      ,pnl.line_status lineStatus\n" +
					"      ,slv1.meaning lineStatusName\n" +
					"      ,pnl.line_comments comments\n" +
					"      ,si1.inst_name instName\n" +
					"      ,ph.po_number poNumber\n" +
					"      ,pl.line_number lineNumber\n" +
					"      ,nvl(pl.may_notice_qty, 0) mayNoticeQty -- 可通知送货量\n" +
					"      ,pnl.original_delivery_date originalDeliveryDate\n" +
					"      ,pnl.original_delivery_qty originalDeliveryQty\n" +
					"      ,pnl.feedback_adjust_date feedbackAdjustDate\n" +
					"      ,pnl.feedback_adjust_qty feedbackAdjustQty\n" +
					"      ,pnl.feedback_status feedbackStatus\n" +
					"      ,slv2.meaning feedbackStatusStr\n" +
					"      ,pnl.feedback_result feedbackResult\n" +
					"      ,pnl.reject_reason rejectReason\n" +
					"      ,se.employee_name employeeName\n" +
					"      ,(pnl.notice_delivery_qty - pnl.on_way_qty - pdl.received_qty) mayQty -- 可送货数量\n" +
					"      ,pnl.on_way_qty onWayQty -- 在途数量\n" +
					"      ,pdl.received_qty receivedQty -- 接收数量\n" +
					"FROM   srm_po_notice pn\n" +
					"LEFT   JOIN saaf_institution si3\n" +
					"ON     si3.inst_id = pn.bill_to_organization_id\n" +
					"LEFT   JOIN saaf_institution si1\n" +
					"ON     si1.inst_id = pn.bill_to_organization_id\n" +
					"LEFT   JOIN saaf_employees se\n" +
					"ON     se.employee_id = pn.buyer_id\n" +
					"LEFT   JOIN srm_pos_supplier_info spsi\n" +
					"ON     spsi.supplier_id = pn.supplier_id\n" +
					"LEFT   JOIN srm_pos_supplier_sites sit\n" +
					"ON     pn.supplier_site_id = sit.supplier_site_id\n" +
					"LEFT   JOIN saaf_lookup_values slv4\n" +
					"ON     slv4.lookup_code = pn.po_notice_status\n" +
					"AND    slv4.lookup_type = 'ISP_DELIVERYNOTICE_STATUS',\n" +
					" srm_po_notice_line pnl\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_code = pnl.line_status\n" +
					"AND    slv1.lookup_type = 'ISP_DELIVERYNOTICE_LINE_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_code = pnl.feedback_status\n" +
					"AND    slv2.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\n" +
					"LEFT   JOIN srm_po_delivery_lines pdl\n" +
					"ON     pdl.notice_line_id = pnl.line_id, srm_po_lines pl\n" +
					"LEFT   JOIN srm_po_headers ph\n" +
					"ON     ph.po_header_id = pl.po_header_id\n" +
					"LEFT   JOIN srm_base_items_b bi\n" +
					"ON     bi.item_id = pl.item_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_code = bi.uom_code\n" +
					"AND    slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     bc.category_id = pl.category_id\n" +
					"LEFT   JOIN saaf_institution si2\n" +
					"ON     si2.inst_id = pl.receive_to_organization_id\n" +
					"AND    si2.inst_type = 'ORGANIZATION'\n" +
					"WHERE  pn.po_notice_id = pnl.po_notice_id\n" +
					"AND    pnl.po_line_id = pl.po_line_id\n" +
					"AND    pn.po_notice_code IS NOT NULL\n";

	//查询送货通知new
    public static final String QUERY_SRM_PO_NOTICE_LINE="\tSELECT a.line_id lineId,b.po_line_id poLineId,c.status status,si2.inst_name receiveToOrganizationName,a.line_id lineId,a.po_notice_id poNoticeId,si.inst_name instName,c.po_notice_code poNoticeCode,b.demand_date demandDate,a.notice_delivery_date noticeDeliveryDate,c.issued_date issuedDate,d.item_name itemName,d.item_code itemCode,b.demand_qty demandQty,a.notice_delivery_qty noticeDeliveryQty,spsi.supplier_name supplierName,st.site_name siteName,sbc.category_name categoryName,sbc.category_code categoryCode,a.line_status lineStatus,slv.meaning lineStatusName,\r\n"+
			"\ta.line_comments comments,e.po_number poNumber,b.line_number lineNumber,IFNULL(b.may_notice_qty,0) mayNoticeQty,a.original_delivery_date originalDemandDate,a.original_delivery_qty originalDemandQty,a.feedback_adjust_date feedbackAdjustDate,a.feedback_adjust_qty feedbackAdjustQty,\r\n"+
			"\tslv2.meaning feedbackStatusStr,a.reject_reason rejectReason,se.employee_name employeeName,d.meaning uomCodeDesc,a.feedback_status feedbackStatus,a.feedback_result feedbackResult\r\n"+
//			"\t(select sum(spl.notice_qty) from srm_po_notice_line spnl LEFT JOIN srm_po_lines spl ON spl.po_line_id = spnl.po_line_id and (spl.`status`='APPROVED' || spl.`status`='NATURAL_CLOSED' || spl.`status`='ClosedExcess') where spnl.po_notice_id =a.po_notice_id) noticeQty\r\n"+
			"\tfrom srm_po_notice_line a\r\n"+
			"\tLEFT JOIN srm_po_notice c ON a.po_notice_id=c.po_notice_id\r\n"+
			"\tLEFT JOIN saaf_institution si ON si.inst_id = c.bill_to_organization_id\r\n"+
			"\tLEFT JOIN saaf_employees se ON c.buyer_id = se.employee_id\r\n"+
			"\tLEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = c.supplier_id \r\n"+
			"\tLEFT JOIN srm_pos_supplier_sites st ON st.supplier_site_id=c.supplier_site_id \r\n"+
			"\tLEFT JOIN saaf_lookup_values slv ON slv.lookup_code=a.line_status and slv.lookup_type='ISP_DELIVERYNOTICE_LINE_STATUS'\r\n"+ 
			"\tLEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_code = a.feedback_status and slv2.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\r\n"+
			"\t,srm_po_lines b\r\n"+ 
			"\tLEFT JOIN srm_po_headers e ON e.po_header_id=b.po_header_id\r\n"+
			"\tLEFT JOIN (SELECT w.item_id,w.item_code,w.item_name,w.organization_id,z.meaning FROM srm_base_items w LEFT JOIN saaf_lookup_values z ON w.uom_code = z.lookup_code AND z.lookup_type = 'BASE_ITEMS_UNIT') d ON b.item_id = d.item_id\r\n"+
//			"\tLEFT JOIN srm_base_items d ON d.item_id=b.item_id\r\n"+
			"\tLEFT JOIN srm_base_categories sbc ON sbc.category_id=b.category_id\r\n"+ 
			"\tLEFT JOIN saaf_institution si2 ON si2.inst_id = b.receive_to_organization_id\r\n"+ 
			"\tWHERE a.po_line_id=b.po_line_id and c.po_notice_code is not null\r\n";
    public static final String QUERY_SRM_PO_NOTICE_LINE_COUNT="\tSELECT COUNT(c.po_notice_code)\r\n"+
    		"\tfrom srm_po_notice_line a\r\n"+
    		"\tLEFT JOIN srm_po_notice c ON a.po_notice_id=c.po_notice_id\r\n"+
    		"\tLEFT JOIN saaf_institution si ON si.inst_id = c.bill_to_organization_id\r\n"+ 
    		"\tLEFT JOIN srm_pos_supplier_info spsi ON spsi.supplier_id = c.supplier_id \r\n"+
    		"\tLEFT JOIN srm_pos_supplier_sites st ON st.supplier_site_id=c.supplier_site_id \r\n"+
    		"\tLEFT JOIN saaf_lookup_values slv ON slv.lookup_code=a.line_status and slv.lookup_type='ISP_DELIVERYNOTICE_LINE_STATUS'\r\n"+ 
    		"\tLEFT JOIN saaf_lookup_values slv2 ON slv2.lookup_code = a.feedback_status and slv2.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\r\n"+
    		"\t,srm_po_lines b\r\n"+ 
    		"\tLEFT JOIN srm_po_headers e ON e.po_header_id=b.po_header_id\r\n"+
    		"\tLEFT JOIN srm_base_items d ON d.item_id=b.item_id\r\n"+
    		"\tLEFT JOIN srm_base_categories sbc ON sbc.category_id=b.category_id\r\n"+ 
    		"\tWHERE a.po_line_id=b.po_line_id and c.po_notice_code is not null\r\n";
    //查询送货通知匹配的送货单
    public static final String QUERY_DELIVER_BY_NOTICE_ID="SELECT \n" +
            "h.delivery_number deliveryNumber,\n" +
            "\th.STATUS status,\n" +
            "\tl.delivery_order_qty deliveryOrderQty,\n" +
            "\th.CREATION_DATE creationDate,\n" +
            "\th.RECEIVE_flag receiveFlag\n" +
            "FROM\n" +
            "\tsrm_po_delivery_lines l\n" +
            "INNER JOIN srm_po_delivery_headers h ON h.delivery_header_id = l.delivery_header_id\n" +
            "WHERE\n" +
            "\tl.po_notice_id = :noticeId";

    public static final String QUERT_NOTICE_LIST_SQL = "SELECT \n" +
            " pn.item_id itemId,\n" +
            " pn.delivery_site_id instId,\n" +
            " pn.supplier_id supplierId,\n" +
            " bi.ITEM_CODE itemCode,\n" +
            " bi.ITEM_NAME itemName,\n" +
            " bc.CATEGORY_CODE categoryCode,\n" +
            " bc.CATEGORY_NAME categoryName,\n" +
            " sin.inst_name instName,\n" +
            " si.supplier_name supplierName,\n" +
            " pn.demand_date demandDate,\n" +
            "\tsum((pn.quantity - IFNULL(pn.delivery_qty,0))) sumNumber\n" +
            "from (\n" +
            "\tselect t.item_id,t.demand_date,t.quantity,t.delivery_qty,t.supplier_id,t.delivery_site_id from srm_po_notice as t \n" +
            "\t\t\t\t\t\t\t\t\t\t\twhere t.`status` in('CREATED','CONFIRMED')\n" +
            "\t\t\t\t\t\t\t\t\t\t\tAND t.demand_date between DATE_FORMAT(SYSDATE(),'yyyy-MM-dd') and date_add(now(), interval 6 day)\n" +
            ") as pn\n" +
            "inner join srm_base_items as bi on bi.ITEM_ID = pn.item_id\n" +
            "inner join srm_base_categories as bc on bc.CATEGORY_CODE = bi.CATEGORY_CODE\n" +
            "left join srm_pos_supplier_info as si on si.supplier_id = pn.supplier_id\n" +
            "left join saaf_institution as sin on sin.inst_id = pn.delivery_site_id\n" +
            "where 1=1 ";

    public static final String QUERY_NOTICE_LOV_SQL = "SELECT\n" +
            "\tn.po_notice_code poNoticeCode,\n" +
            "\tn.po_notice_id poNoticeId\n" +
            "FROM\n" +
            "\tsrm_po_notice n\n" +
            "WHERE\n" +
            "\t1 = 1";
    //导出
    public static final String QUERY_SUPPLY_NOTICE_EXPORT_SQL = "SELECT\n" +
            "\tsi.inst_name instName,\n" +
            "\tsbc.CATEGORY_CODE categoryCode,\n" +
            "\tsbc.CATEGORY_NAME categoryName,\n" +
            "\tsbi.ITEM_CODE itemCode,\n" +
            "\tsbi.ITEM_NAME itemName,\n" +
            "\tspn.demand_date demandDate,\n" +
            "\tspn.quantity,\n" +
            "\tspn.delivery_order_qty,\n" +
            "\tspn.delivery_qty,\n" +
            "\tspn.po_notice_code,\n" +
            "\tspn.demand_classify,\n" +
            "\tspn.SPECIAL_USE_NUM specialUseNum,\n" +
            "\tspn.`status` STATUS,\n" +
            "\tse.employee_name employeeName,\n" +
            "\tnl.PO_HEADER_NUMBER,\n" +
            "\tspsi.supplier_name supplierName,\n" +

            "\tslv1.meaning noticeStatusName,\n" +

            "\tnl.po_header_number poHeaderNumber,\n" +
            "\tnl.order_quantity orderQuantity,\n" +
            "\tnl.demand_date demandDate,\n" +
            "\tnl.MATCH_QUANTITY matchQuantity\n" +
            "FROM\n" +
            "\tsrm_po_notice AS spn\n" +
            "LEFT JOIN saaf_lookup_values AS slv1 ON slv1.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv1.lookup_code = spn. STATUS\n" +
            "LEFT JOIN saaf_employees AS se ON se.employee_number = spn.employee_num\n" +
            "LEFT JOIN srm_pos_supplier_info AS spsi ON spsi.supplier_id = spn.supplier_id\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = spn.inst_id\n" +
            "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = spn.item_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "LEFT JOIN srm_po_notice_line AS nl ON nl.po_notice_id = spn.po_notice_id\n" +
            "WHERE\n" +
            "\t1 = 1\n";

    public static final String QUERY_NOTICE_CODE_LOV = "select n.po_notice_code poNoticeCode from srm_po_notice n where 1=1";
    public static final String QUERY_NOTICE_SUM_SQL = "SELECT\n" +
            "\tsum(spn.quantity) sumQuantity,\n" +
            "\tsum(\n" +
            "\t\t\t(select\t\n" +
            "\t\t\t\tifnull(sum(IF(h.status = 'CLOSED',t.delivery_qty,t.delivery_order_qty)),0)\n" +
            "\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\tsrm_po_delivery_lines t,\n" +
            "\t\t\t\t\t\t(select dh.delivery_header_id,dh.`status` from srm_po_delivery_headers as dh where dh.`status`='CREATED') h\n" +
            "\t\t\t\t\tWHERE    \n" +
            "\t\t\t\t\t\tt.po_notice_id = spn.po_notice_id\n" +
            "\t\t\t\t\tand\t\t\tt.delivery_header_id = h.delivery_header_id\n" +
            "\t\t\t\t\t)\n" +
            "\t\t) sumDeliveryOrderQty,\n" +
            "\tsum(ifnull(spn.delivery_qty,0)) sumDeliveryQty\n" +
            "FROM\n" +
            "\tsrm_po_notice AS spn\n" +
            "LEFT JOIN saaf_lookup_values AS slv1 ON slv1.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv1.lookup_code = spn.STATUS\n" +
            "LEFT JOIN saaf_lookup_values AS slv2 ON slv2.lookup_type = 'PO_NOTICE_STATUS'\n" +
            "AND slv2.lookup_code = spn.affirm_status\n" +
            "LEFT JOIN saaf_employees AS se ON se.employee_number = spn.employee_num\n" +
            "LEFT JOIN srm_pos_supplier_info AS spsi ON spsi.supplier_id = spn.supplier_id\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = spn.inst_id\n" +
            "LEFT JOIN saaf_institution AS s2 ON s2.inst_id = spn.delivery_site_id\n" +
            "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = spn.item_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_ID = sbi.CATEGORY_ID\n" +
            "WHERE\n" +
            "\t1 = 1 ";
    public static final String QUERY_SRMPONOTICE_SQL="SELECT\n" +
			"  pn.po_notice_id poNoticeId,\n" +
			"  pn.po_notice_status poNoticeStatus,\n" +
			"  slv.meaning meaning,\n" +
			"  pn.po_notice_code poNoticeCode,\n" +
			"  pn.bill_to_organization_id billToOrganizationId,\n" +
			"  pn.supplier_id supplierId,\n" +
			"  psi.supplier_name supplierName,\n" +
			"  pn.supplier_site_id supplierSiteId,\n" +
			"  st.site_name siteName,\n" +
			"  emp.employee_id employeeId,\n" +
			"  emp.employee_name employeeName,\n" +
			"  pn.approved_date approvedDate,\n" +
			"  pn.issued_date issuedDate,\n" +
			"  pn.comments comments,\n" +
			"  pn.file_id fileId,\n" +
			"  pn.po_notice_versions poNoticeVersions,\n" +
			"  pn.creation_date creationDate,\n" +
			"  pn.buyer_id buyerId,\n" +
			"  pn.org_id orgId,\n" +
			"  in1.inst_name orgName,\n" +
			"  in2.inst_name billToOrganizationName,\n" +
			"  su.user_full_name createName,\n" +
			"  pn.file_id commonFileId,\n" +
			"  rf.access_Path commonFilePath,\n" +
			"  rf.file_Name commonFileName\n" +
			"FROM\n" +
			"  srm_po_notice pn\n" +
			"  LEFT JOIN saaf_lookup_values slv\n" +
			"    ON slv.lookup_code = pn.po_notice_status\n" +
			"    AND slv.lookup_type = 'ISP_DELIVERYNOTICE_STATUS'\n" +
			"  LEFT JOIN srm_pos_supplier_info psi\n" +
			"    ON psi.supplier_id = pn.supplier_id\n" +
			"  LEFT JOIN saaf_employees emp\n" +
			"    ON emp.employee_id = pn.buyer_id\n" +
			"  LEFT JOIN saaf_users su \n" +
			"    ON pn.created_by = su.user_id\n" +
			"  LEFT JOIN saaf_institution in1\n" +
			"    ON in1.inst_id = pn.org_id and in1.inst_type='ORG'\n" +
			"  LEFT JOIN saaf_institution in2\n" +
			"    ON in2.inst_id = pn.bill_to_organization_id and in2.inst_type='ORGANIZATION'\n" +
			"  LEFT JOIN saaf_base_result_file rf on pn.file_id = rf.file_id\n"+
			"  LEFT JOIN srm_pos_supplier_sites st on pn.supplier_site_id = st.supplier_site_id\n"+
			"WHERE 1 = 1\r\n";
            
    private BigDecimal sumQuantity;
    private BigDecimal sumDeliveryOrderQty;
    private BigDecimal sumDeliveryQty;

    private BigDecimal sumNumber8;
    private String deliverySiteName;
    private String deliveryNumber;
    private String receiveFlag;

    private BigDecimal sumNumber1;
    private BigDecimal sumNumber2;
    private BigDecimal sumNumber3;
    private BigDecimal sumNumber4;
    private BigDecimal sumNumber5;
    private BigDecimal sumNumber6;
    private BigDecimal sumNumber7;

    private BigDecimal sumNumber;
    private String poHeaderNumber;
    private String closableFlag;
    private String categoryName;
    private String noticeStatusName;
    private String employeeName;
    private String supplierName;
    private BigDecimal orderQuantity;
    private BigDecimal matchQuantity;
    private String instName;
    private String itemCode;
    private String itemName;
    private String categoryCode;
    private Integer poNoticeId;
    private String poNoticeCode;
    private Integer instId;
    private Integer itemId;
    private Integer supplierId;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDate;
    private BigDecimal quantity;
    private String employeeNum;
    private String status;
    private String specialUseNum;
    private String demandClassify;
    private BigDecimal deliveryOrderQty;
    private BigDecimal deliveryQty;
    private String affirmStatus;
    private String rejectReason;
    private String lineStatusName;
    private String siteName;
    private String checkBox;
    private String checkBoxFlag;
    private String poNoticeStatus;
    //private String billToOrganizationId;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date noticeDeliveryDate;
    private Integer day;//日
    private Integer noticeDeliveryQty;
    private String comments;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvedDate; //批准时间
    @JSONField(format = "yyyy-MM-dd")
    private Date issuedDate; //下达时间
    private Integer fileId; //附件ID
    private BigDecimal poNoticeVersions; //送货通知版本
    private Integer buyerId;
    private Integer supplierSiteId; //供应商地点ID
    private String meaning; //供应商地点ID
    private String orgName; //业务实体名称
    private Integer orgId; //业务实体ID
    private Integer employeeId;
    private BigDecimal demandQty;
    private String feedbackStatus;
    private String feedbackStatusStr;
    private String lineStatus;
    private Integer lineId;
    private Integer poLineId;
    @JSONField(format = "yyyy-MM-dd")
    private Date feedbackAdjustDate;
    private BigDecimal feedbackAdjustQty;
    private BigDecimal mayNoticeQty;
    private Integer lineNumber; // 行号
    private String poNumber; // 采购订单号
    @JSONField(format = "yyyy-MM-dd")
    private Date originalDemandDate;
    private BigDecimal originalDemandQty;
    private BigDecimal notifiedQty;
    private String feedbackResult;
    private String receiveToOrganizationName;
    private BigDecimal noticeQty;
    private String createName;
    private String uomCodeDesc;
	@JSONField(format = "yyyy-MM-dd")
	private Date originalDeliveryDate;
	private BigDecimal originalDeliveryQty;
	private Integer commonFileId;
	private String commonFilePath;
	private String commonFileName;
	private String billToOrganizationName;
	private Integer billToOrganizationId;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;

	public void setDay(Integer day) {
		this.day = day;
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

	public String getBillToOrganizationName() {
		return billToOrganizationName;
	}

	public void setBillToOrganizationName(String billToOrganizationName) {
		this.billToOrganizationName = billToOrganizationName;
	}

	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}

	public Integer getCommonFileId() {
		return commonFileId;
	}

	public void setCommonFileId(Integer commonFileId) {
		this.commonFileId = commonFileId;
	}

	public String getCommonFilePath() {
		return commonFilePath;
	}

	public void setCommonFilePath(String commonFilePath) {
		this.commonFilePath = commonFilePath;
	}

	public String getCommonFileName() {
		return commonFileName;
	}

	public void setCommonFileName(String commonFileName) {
		this.commonFileName = commonFileName;
	}

	public Date getOriginalDeliveryDate() {
		return originalDeliveryDate;
	}

	public void setOriginalDeliveryDate(Date originalDeliveryDate) {
		this.originalDeliveryDate = originalDeliveryDate;
	}

	public BigDecimal getOriginalDeliveryQty() {
		return originalDeliveryQty;
	}

	public void setOriginalDeliveryQty(BigDecimal originalDeliveryQty) {
		this.originalDeliveryQty = originalDeliveryQty;
	}

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public BigDecimal getNoticeQty() {
		return noticeQty;
	}

	public void setNoticeQty(BigDecimal noticeQty) {
		this.noticeQty = noticeQty;
	}

	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}

	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}

	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	public String getFeedbackStatusStr() {
		return feedbackStatusStr;
	}

	public void setFeedbackStatusStr(String feedbackStatusStr) {
		this.feedbackStatusStr = feedbackStatusStr;
	}

	public BigDecimal getNotifiedQty() {
		return notifiedQty;
	}

	public void setNotifiedQty(BigDecimal notifiedQty) {
		this.notifiedQty = notifiedQty;
	}

	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
	}

	public BigDecimal getOriginalDemandQty() {
		return originalDemandQty;
	}

	public void setOriginalDemandQty(BigDecimal originalDemandQty) {
		this.originalDemandQty = originalDemandQty;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}

	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}

	public Date getFeedbackAdjustDate() {
		return feedbackAdjustDate;
	}

	public void setFeedbackAdjustDate(Date feedbackAdjustDate) {
		this.feedbackAdjustDate = feedbackAdjustDate;
	}

	public BigDecimal getFeedbackAdjustQty() {
		return feedbackAdjustQty;
	}

	public void setFeedbackAdjustQty(BigDecimal feedbackAdjustQty) {
		this.feedbackAdjustQty = feedbackAdjustQty;
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

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public BigDecimal getPoNoticeVersions() {
		return poNoticeVersions;
	}

	public void setPoNoticeVersions(BigDecimal poNoticeVersions) {
		this.poNoticeVersions = poNoticeVersions;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/*public String getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setBillToOrganizationId(String billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}*/

	public BigDecimal getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(BigDecimal sumQuantity) {
        this.sumQuantity = sumQuantity;
    }

    public BigDecimal getSumDeliveryOrderQty() {
        return sumDeliveryOrderQty;
    }

    public void setSumDeliveryOrderQty(BigDecimal sumDeliveryOrderQty) {
        this.sumDeliveryOrderQty = sumDeliveryOrderQty;
    }

    public BigDecimal getSumDeliveryQty() {
        return sumDeliveryQty;
    }

    public void setSumDeliveryQty(BigDecimal sumDeliveryQty) {
        this.sumDeliveryQty = sumDeliveryQty;
    }

    public BigDecimal getSumNumber8() {

        return (sumNumber1==null?new BigDecimal(0):sumNumber1).add((sumNumber2==null?new BigDecimal(0):sumNumber2)).add((sumNumber3==null?new BigDecimal(0):sumNumber3)).add((sumNumber4==null?new BigDecimal(0):sumNumber4)).add((sumNumber5==null?new BigDecimal(0):sumNumber5)).add((sumNumber6==null?new BigDecimal(0):sumNumber6)).add((sumNumber7==null?new BigDecimal(0):sumNumber7));
    }

    public void setSumNumber8(BigDecimal sumNumber8) {
        this.sumNumber8 = sumNumber8;
    }

    public String getDeliverySiteName() {
        return deliverySiteName;
    }

    public void setDeliverySiteName(String deliverySiteName) {
        this.deliverySiteName = deliverySiteName;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getReceiveFlag() {
        return receiveFlag;
    }

    public void setReceiveFlag(String receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    public String getPoHeaderNumber() {
        return poHeaderNumber;
    }

    public void setPoHeaderNumber(String poHeaderNumber) {
        this.poHeaderNumber = poHeaderNumber;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getMatchQuantity() {
        return matchQuantity;
    }

    public void setMatchQuantity(BigDecimal matchQuantity) {
        this.matchQuantity = matchQuantity;
    }

    public BigDecimal getSumNumber1() {
        return sumNumber1;
    }

    public void setSumNumber1(BigDecimal sumNumber1) {

        this.sumNumber1 = sumNumber1;
    }

    public BigDecimal getSumNumber2() {
        return sumNumber2;
    }

    public void setSumNumber2(BigDecimal sumNumber2) {
        this.sumNumber2 = sumNumber2;
    }

    public BigDecimal getSumNumber3() {
        return sumNumber3;
    }

    public void setSumNumber3(BigDecimal sumNumber3) {
        this.sumNumber3 = sumNumber3;
    }

    public BigDecimal getSumNumber4() {

        return sumNumber4;
    }

    public void setSumNumber4(BigDecimal sumNumber4) {
        this.sumNumber4 = sumNumber4;
    }

    public BigDecimal getSumNumber5() {
        return sumNumber5;
    }

    public void setSumNumber5(BigDecimal sumNumber5) {
        this.sumNumber5 = sumNumber5;
    }

    public BigDecimal getSumNumber6() {
        return sumNumber6;
    }

    public void setSumNumber6(BigDecimal sumNumber6) {
        this.sumNumber6 = sumNumber6;
    }

    public BigDecimal getSumNumber7() {
        return sumNumber7;
    }

    public void setSumNumber7(BigDecimal sumNumber7) {
        this.sumNumber7 = sumNumber7;
    }

    public BigDecimal getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(BigDecimal sumNumber) {
        this.sumNumber = sumNumber;
    }

    public Integer getDay() {
       if(this.demandDate==null)return 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.demandDate);
        this.day = calendar.get(Calendar.DATE);

        return calendar.get(Calendar.DATE);
    }

	public String getCheckBox() {
		/*if ("APPROVED".equals(poNoticeStatus)
				&& "SUBMITTED".equals(feedbackStatus)
				&& "OPEN".equals(lineStatus)) {
			return "Y";
		}*/
		if ("APPROVED".equals(poNoticeStatus)) {
			return "Y";
		}
		return "N";
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public String getCheckBoxFlag() {
		/*if ("APPROVED".equals(poNoticeStatus)
				&& "NON_FEEDBACK".equals(feedbackStatus)
				&& "OPEN".equals(lineStatus)) {
			return "Y";
		}*/
		if ("APPROVED".equals(poNoticeStatus)) {
			return "Y";
		}
		return "N";
	}

	public void setCheckBoxFlag(String checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
	}

	public String getClosableFlag() {
        if(this.deliveryOrderQty==null)return null;
        if(this.deliveryOrderQty.compareTo(new BigDecimal(0))==0){
            return "Y";
        }
        return "N";
    }

    public void setClosableFlag(String closableFlag) {
        this.closableFlag = closableFlag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNoticeStatusName() {
        return noticeStatusName;
    }

    public void setNoticeStatusName(String noticeStatusName) {
        this.noticeStatusName = noticeStatusName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getPoNoticeId() {
        return poNoticeId;
    }

    public void setPoNoticeId(Integer poNoticeId) {
        this.poNoticeId = poNoticeId;
    }

    public String getPoNoticeCode() {
        return poNoticeCode;
    }

    public void setPoNoticeCode(String poNoticeCode) {
        this.poNoticeCode = poNoticeCode;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getDeliveryOrderQty() {
        return deliveryOrderQty;
    }

    public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
        this.deliveryOrderQty = deliveryOrderQty;
    }

    public BigDecimal getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getAffirmStatus() {
        return affirmStatus;
    }

    public void setAffirmStatus(String affirmStatus) {
        this.affirmStatus = affirmStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    

    public String getLineStatusName() {
		return lineStatusName;
	}

	public void setLineStatusName(String lineStatusName) {
		this.lineStatusName = lineStatusName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public String getPoNoticeStatus() {
		return poNoticeStatus;
	}

	public void setPoNoticeStatus(String poNoticeStatus) {
		this.poNoticeStatus = poNoticeStatus;
	}

	public Date getNoticeDeliveryDate() {
		return noticeDeliveryDate;
	}

	public void setNoticeDeliveryDate(Date noticeDeliveryDate) {
		this.noticeDeliveryDate = noticeDeliveryDate;
	}

	public Integer getNoticeDeliveryQty() {
		return noticeDeliveryQty;
	}

	public void setNoticeDeliveryQty(Integer noticeDeliveryQty) {
		this.noticeDeliveryQty = noticeDeliveryQty;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrmPoNoticeEntity_HI_RO that = (SrmPoNoticeEntity_HI_RO) o;

        if (!instName.equals(that.instName)) return false;
        if (!instId.equals(that.instId)) return false;
        if (!itemId.equals(that.itemId)) return false;
        return supplierId.equals(that.supplierId);
    }

    @Override
    public int hashCode() {
        int result = instId.hashCode();
        result = 31 * result + itemId.hashCode();
        result = 31 * result + supplierId.hashCode();
        return result;
    }


}

