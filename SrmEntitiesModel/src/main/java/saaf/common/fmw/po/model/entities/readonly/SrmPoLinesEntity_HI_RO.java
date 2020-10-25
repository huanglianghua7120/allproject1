package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPoLinesEntity_HI_RO Entity Object
 * Mon Apr 09 20:41:25 CST 2018  Auto Generate
 */

public class SrmPoLinesEntity_HI_RO {

	//查询采购订单
    public static String QUERY_LINES_SQL = "SELECT\n" +
            "\n" +
            "\tDATE_SUB(\n" +
            "\t\tl.demand_date,\n" +
            "\t\tINTERVAL sbi.CONFIRM_LEAD_TIME DAY\n" +
            "\t) confirmLeadDate,\n" +
            "\tl.demand_classify demandClassify,\n" +
            "\tl.SPECIAL_USE_NUM specialUseNum,\n" +
            "\tsfi.inst_name instName,\n" +
            "\th.PO_DOC_TYPE documentType,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tt.profile_value\n" +
            "\t\tFROM\n" +
            "\t\t\tsaaf_profiles t\n" +
            "\t\tWHERE\n" +
            "\t\t\tt.profile_number = 'DELIVERY_LEAD_TIME'\n" +
            "\t) profileValue,\n" +
            "\tsfi.inst_id instId,\n" +
            "\tsbc.CATEGORY_NAME categoryName,\n" +
            "\tsbi.ITEM_CODE itemCode,\n" +
            "\tsbi.ITEM_NAME itemName,\n" +
            "\th.po_number poNumber,\n" +
            "\tl.demand_date demandDate,\n" +
            "\tl.demand_qty demandQty,\n" +
            "\tl.item_id itemId,\n" +
            "\tl.po_line_id poLineId,\n" +
            "\th.supplier_id supplierId,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tifnull(\n" +
            "\t\t\t\tsum(\n" +
            "\n" +
            "\t\t\t\t\tIF (\n" +
            "\t\t\t\t\t\th. STATUS = 'CLOSED',\n" +
            "\t\t\t\t\t\tt.delivery_qty,\n" +
            "\t\t\t\t\t\tt.delivery_order_qty\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\t),\n" +
            "\t\t\t\t0\n" +
            "\t\t\t)\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_delivery_lines t,\n" +
            "\t\t\t(\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tdh. STATUS,\n" +
            "\t\t\t\t\tdh.delivery_header_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tdh.`status` = 'CREATED'\n" +
            "\t\t\t) hh\n" +
            "\t\tWHERE\n" +
            "\t\t\tt.po_line_id = l.po_line_id\n" +
            "\t\tAND t.delivery_header_id = hh.delivery_header_id\n" +
            "\t) noticeQty,\n" +
            "\tifnull(l.delivery_qty, 0) deliveryQty,\n" +
            "\t(\n" +
            "\t\tl.demand_qty - ifnull(l.delivery_qty, 0) - (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tifnull(\n" +
            "\t\t\t\t\tsum(t.delivery_order_qty),\n" +
            "\t\t\t\t\t0\n" +
            "\t\t\t\t)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsrm_po_delivery_lines t,\n" +
            "\t\t\t\t(\n" +
            "\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\tdh.delivery_header_id\n" +
            "\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\tdh.`status` = 'CREATED'\n" +
            "\t\t\t\t) hh\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tt.po_line_id = l.po_line_id\n" +
            "\t\t\tAND t.delivery_header_id = hh.delivery_header_id\n" +
            "\t\t)\n" +
            "\t) deliverableQty\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tspl.demand_date,\n" +
            "\t\t\tspl.demand_classify,\n" +
            "\t\t\tspl.SPECIAL_USE_NUM,\n" +
            "\t\t\tspl.demand_qty,\n" +
            "\t\t\tspl.item_id,\n" +
            "\t\t\tspl.po_line_id,\n" +
            "\t\t\tspl.inst_id,\n" +
            "\t\t\tspl.delivery_qty,\n" +
            "\t\t\tspl.po_header_id,\n" +
            "\t\t\tspl.CREATION_DATE\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_lines AS spl\n" +
            "\t\tWHERE\n" +
            "\t\t\tspl.`status` = 'APPROVED'\n" +
            "\t\tAND spl.DELIVERY_TYPE = '1'\n" +
            "\t\tAND 0 < (\n" +
            "\t\t\tspl.demand_qty - ifnull(spl.delivery_qty, 0) - (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tifnull(\n" +
            "\t\t\t\t\t\tsum(t.delivery_order_qty),\n" +
            "\t\t\t\t\t\t0\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_lines t,\n" +
            "\t\t\t\t\t(\n" +
            "\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\tpdh.delivery_header_id\n" +
            "\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\tsrm_po_delivery_headers AS pdh\n" +
            "\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\tpdh.`status` = 'CREATED'\n" +
            "\t\t\t\t\t) AS h2\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tt.po_line_id = spl.po_line_id\n" +
            "\t\t\t\tAND t.delivery_header_id = h2.delivery_header_id\n" +
            "\t\t\t)\n" +
            "\t\t)\n" +
            "\t) AS l\n" +
            "INNER JOIN srm_po_headers AS h ON h.po_header_id = l.po_header_id\n" +
            "INNER JOIN saaf_institution AS sfi ON sfi.inst_id = l.inst_id\n" +
            "INNER JOIN srm_base_items AS sbi ON sbi.ITEM_ID = l.item_id\n" +
            "INNER JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1 ";

    //采购订单统计
    public static String QUERY_LINES_COUNT_SQL = "SELECT\n" +
            "count(*)\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tspl.demand_date,\n" +
            "\t\t\tspl.demand_classify,\n" +
            "\t\t\tspl.SPECIAL_USE_NUM,\n" +
            "\t\t\tspl.demand_qty,\n" +
            "\t\t\tspl.item_id,\n" +
            "\t\t\tspl.po_line_id,\n" +
            "\t\t\tspl.inst_id,\n" +
            "\t\t\tspl.delivery_qty,\n" +
            "\t\t\tspl.po_header_id,\n" +
            "\t\t\tspl.CREATION_DATE\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_lines AS spl\n" +
            "\t\tWHERE\n" +
            "\t\t\tspl.`status` = 'APPROVED'\n" +
            "\t\tAND spl.DELIVERY_TYPE = '1'\n" +
            "\t\tAND 0 < (\n" +
            "\t\t\tspl.demand_qty - ifnull(spl.delivery_qty, 0) - (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tifnull(\n" +
            "\t\t\t\t\t\tsum(t.delivery_order_qty),\n" +
            "\t\t\t\t\t\t0\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_lines t,\n" +
            "\t\t\t\t\t(\n" +
            "\t\t\t\t\t\tSELECT\n" +
            "\t\t\t\t\t\t\tpdh.delivery_header_id\n" +
            "\t\t\t\t\t\tFROM\n" +
            "\t\t\t\t\t\t\tsrm_po_delivery_headers AS pdh\n" +
            "\t\t\t\t\t\tWHERE\n" +
            "\t\t\t\t\t\t\tpdh.`status` = 'CREATED'\n" +
            "\t\t\t\t\t) AS h2\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tt.po_line_id = spl.po_line_id\n" +
            "\t\t\t\tAND t.delivery_header_id = h2.delivery_header_id\n" +
            "\t\t\t)\n" +
            "\t\t)\n" +
            "\t) AS l\n" +
            "INNER JOIN srm_po_headers AS h ON h.po_header_id = l.po_header_id\n" +
            "INNER JOIN saaf_institution AS sfi ON sfi.inst_id = l.inst_id\n" +
            "INNER JOIN srm_base_items AS sbi ON sbi.ITEM_ID = l.item_id\n" +
            "INNER JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1 ";
    //送货通知
    public static final String QUERY_NOTICE_SQL = "SELECT\n" +
            "\tDATE_SUB(\n" +
            "\t\tn.demand_date,\n" +
            "\t\tINTERVAL sbi.CONFIRM_LEAD_TIME DAY\n" +
            "\t) confirmLeadDate,\n" +
            "\tn.po_notice_code poNoticeCode,\n" +
            "\tsi.inst_name instName,\n" +
            "\tn.document_type documentType,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tt.profile_value\n" +
            "\t\tFROM\n" +
            "\t\t\tsaaf_profiles t\n" +
            "\t\tWHERE\n" +
            "\t\t\tt.profile_number = 'DELIVERY_LEAD_TIME'\n" +
            "\t) profileValue,\n" +
            "\tsi.inst_id instId,\n" +
            "\ts2.inst_id deliverySiteId,\n" +
            "\ts2.inst_name deliverySiteName,\n" +
            "\tsbi.ITEM_CODE itemCode,\n" +
            "\tsbi.ITEM_NAME itemName,\n" +
            "\tsbi.ITEM_id itemId,\n" +
            "\tsbc.CATEGORY_NAME categoryName,\n" +
            "\tn.SPECIAL_USE_NUM specialUseNum,\n" +
            "\tifnull(n.quantity, 0) demandQty,\n" +
            "\tn.po_notice_id poNoticeId,\n" +
            "\tn.demand_date demandDate,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tifnull(\n" +
            "\t\t\t\tsum(\n" +
            "\n" +
            "\t\t\t\t\tIF (\n" +
            "\t\t\t\t\t\th. STATUS = 'CLOSED',\n" +
            "\t\t\t\t\t\tt.delivery_qty,\n" +
            "\t\t\t\t\t\tt.delivery_order_qty\n" +
            "\t\t\t\t\t)\n" +
            "\t\t\t\t),\n" +
            "\t\t\t\t0\n" +
            "\t\t\t)\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_delivery_lines t\n" +
            "\t\tINNER JOIN (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tdh.`status`,dh.delivery_header_id\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tdh. STATUS = 'CREATED'\n" +
            "\t\t) h ON t.delivery_header_id = h.delivery_header_id\n" +
            "\t\tWHERE\n" +
            "\t\t\tt.po_notice_id = n.po_notice_id\n" +
            "\t) noticeQty,\n" +
            "\tifnull(n.delivery_qty, 0) deliveryQty,\n" +
            "\t(\n" +
            "\t\tn.quantity - (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tifnull(\n" +
            "\t\t\t\t\tsum(\n" +
            "\n" +
            "\t\t\t\t\t\tIF (\n" +
            "\t\t\t\t\t\t\th. STATUS = 'CLOSED',\n" +
            "\t\t\t\t\t\t\tt.delivery_qty,\n" +
            "\t\t\t\t\t\t\tt.delivery_order_qty\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t),\n" +
            "\t\t\t\t\t0\n" +
            "\t\t\t\t)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsrm_po_delivery_lines t\n" +
            "\t\t\tINNER JOIN (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tdh. STATUS,\n" +
            "\t\t\t\t\tdh.delivery_header_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tdh. STATUS IN ('CREATED', 'CLOSED')\n" +
            "\t\t\t) h ON t.delivery_header_id = h.delivery_header_id\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tt.po_notice_id = n.po_notice_id\n" +
            "\t\t)\n" +
            "\t) deliverableQty\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tpn.inst_id,\n" +
            "\t\t\tpn.item_id,\n" +
            "\t\t\tpn.po_notice_id,\n" +
            "\t\t\tpn.demand_date,\n" +
            "\t\t\tpn.po_notice_code,\n" +
            "\t\t\tpn.document_type,\n" +
            "\t\t\tpn.SPECIAL_USE_NUM,\n" +
            "\t\t\tpn.quantity,\n" +
            "\t\t\tpn.delivery_qty,\n" +
            "\t\t\tpn.CREATION_DATE,\n" +
            "\t\t\tpn.delivery_site_id,\n" +
            "\t\t\tpn.supplier_id\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_notice AS pn\n" +
            "\t\tWHERE\n" +
            "\t\t\tpn. STATUS = 'CONFIRMED'\n" +
            "\t\tAND 0 < pn.quantity - (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tifnull(\n" +
            "\t\t\t\t\tsum(\n" +
            "\n" +
            "\t\t\t\t\t\tIF (\n" +
            "\t\t\t\t\t\t\th. STATUS = 'CLOSED',\n" +
            "\t\t\t\t\t\t\tt.delivery_qty,\n" +
            "\t\t\t\t\t\t\tt.delivery_order_qty\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t),\n" +
            "\t\t\t\t\t0\n" +
            "\t\t\t\t)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsrm_po_delivery_lines t\n" +
            "\t\t\tINNER JOIN (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tdh. STATUS,\n" +
            "\t\t\t\t\tdh.delivery_header_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tdh. STATUS IN ('CREATED', 'CLOSED')\n" +
            "\t\t\t) h ON t.delivery_header_id = h.delivery_header_id\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tt.po_notice_id = pn.po_notice_id\n" +
            "\t\t)\n" +
            "\t) AS n\n" +
            "INNER JOIN saaf_institution AS si ON si.inst_id = n.inst_id\n" +
            "INNER JOIN srm_base_items AS sbi ON sbi.ITEM_ID = n.item_id\n" +
            "LEFT JOIN saaf_institution AS s2 ON s2.inst_id = n.delivery_site_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1";
    //送货通知统计
    public static final String QUERY_NOTICE_COUNT_SQL = "SELECT\n" +
            "\tcount(*)\n" +
            "FROM\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tpn.inst_id,\n" +
            "\t\t\tpn.item_id,\n" +
            "\t\t\tpn.po_notice_id,\n" +
            "\t\t\tpn.demand_date,\n" +
            "\t\t\tpn.po_notice_code,\n" +
            "\t\t\tpn.document_type,\n" +
            "\t\t\tpn.SPECIAL_USE_NUM,\n" +
            "\t\t\tpn.quantity,\n" +
            "\t\t\tpn.delivery_qty,\n" +
            "\t\t\tpn.CREATION_DATE,\n" +
            "\t\t\tpn.delivery_site_id,\n" +
            "\t\t\tpn.supplier_id\n" +
            "\t\tFROM\n" +
            "\t\t\tsrm_po_notice AS pn\n" +
            "\t\tWHERE\n" +
            "\t\t\tpn. STATUS = 'CONFIRMED'\n" +
            "\t\tAND 0 < pn.quantity - (\n" +
            "\t\t\tSELECT\n" +
            "\t\t\t\tifnull(\n" +
            "\t\t\t\t\tsum(\n" +
            "\n" +
            "\t\t\t\t\t\tIF (\n" +
            "\t\t\t\t\t\t\th. STATUS = 'CLOSED',\n" +
            "\t\t\t\t\t\t\tt.delivery_qty,\n" +
            "\t\t\t\t\t\t\tt.delivery_order_qty\n" +
            "\t\t\t\t\t\t)\n" +
            "\t\t\t\t\t),\n" +
            "\t\t\t\t\t0\n" +
            "\t\t\t\t)\n" +
            "\t\t\tFROM\n" +
            "\t\t\t\tsrm_po_delivery_lines t\n" +
            "\t\t\tINNER JOIN (\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tdh. STATUS,\n" +
            "\t\t\t\t\tdh.delivery_header_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\tsrm_po_delivery_headers AS dh\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tdh. STATUS IN ('CREATED', 'CLOSED')\n" +
            "\t\t\t) h ON t.delivery_header_id = h.delivery_header_id\n" +
            "\t\t\tWHERE\n" +
            "\t\t\t\tt.po_notice_id = pn.po_notice_id\n" +
            "\t\t)\n" +
            "\t) AS n\n" +
            "INNER JOIN saaf_institution AS si ON si.inst_id = n.inst_id\n" +
            "INNER JOIN srm_base_items AS sbi ON sbi.ITEM_ID = n.item_id\n" +
            "LEFT JOIN saaf_institution AS s2 ON s2.inst_id = n.delivery_site_id\n" +
            "LEFT JOIN srm_base_categories AS sbc ON sbc.CATEGORY_CODE = sbi.CATEGORY_CODE\n" +
            "WHERE\n" +
            "\t1 = 1  ";
    
    
    public static final String QUERY_NOTICE_CAN_DELIVER = "SELECT\r\n" + 
    		"	get_notice_qty_f (\r\n" + 
    		"		t.po_notice_id,\r\n" + 
    		"		'CAN_CREATE_DELIVERY'\r\n" + 
    		"	)  deliverableQty\r\n" + 
    		"FROM\r\n" + 
    		"	srm_po_notice t "
    		+ " where 1=1 ";
    
    public static final String QUERY_PO_CAN_DELIVERY = "SELECT\r\n" + 
    		"	get_po_qty_f (\r\n" + 
    		"		t.po_line_id,\r\n" + 
    		"		'CAN_CREATE_DELIVERY'\r\n" + 
    		"	) deliverableQty\r\n" + 
    		"FROM\r\n" + 
    		"	srm_po_lines t"
    		+ " where 1=1 ";

    /**
     * 简单查询订单行信息
     */
	public static final Object QUERY_ORDER_LINE_INFO = "SELECT t.po_line_id AS poLineId FROM srm_po_lines t WHERE 1 = 1";

    public static String GET_PO_LINE_SQL = " select b.ITEM_CODE itemCode, "
            + " b.UNIT_OF_MEASURE UnitOfMeasure, "
            + " c.inst_code instCode, "
            + " a.demand_qty demandQty, "
            + " a.line_number lineNumber, "
            + " a.demand_date demandDate, "
            + " a.demand_classify demandClassify, "
            + " a.SPECIAL_USE_NUM SpecialUseNum, "
            + " a.price, "
            + " h.po_number poNumber , "
            + " a.description "
            + " from SRM_PO_lines a left join srm_base_items b on a.item_id = b.ITEM_ID "
            + " left join saaf_institution c on a.inst_id = c.inst_id "
            + " join srm_po_headers h on  h.po_header_id=a.po_header_id "
            + " where 1=1 ";

    public static String FIND_PO_HEADERS =
					"SELECT decode(ph.po_doc_type, 'ORDER', '采购订单', ph.po_doc_type) poDocTypeDesc\n" +
					"      ,ph.po_number poNumber\n" +
					"      ,ph.status\n" +
					"      ,val.meaning statusDesc\n" +
					"      ,pl.demand_qty demandQty\n" +
					"      ,pl.may_notice_qty mayNoticeQty\n" +
					"      ,pl.on_way_qty onWayQty\n" +
					"      ,pl.received_qty receivedQty\n" +
					"FROM   srm_po_lines pl\n" +
					"LEFT   JOIN srm_po_headers ph\n" +
					"ON     pl.po_header_id = ph.po_header_id\n" +
					"LEFT   JOIN saaf_lookup_values val\n" +
					"ON     val.lookup_code = ph.status\n" +
					"AND    val.lookup_type = 'ISP_PO_STATUS', srm_po_requisition_lines rl\n" +
					"WHERE  1 = 1\n";

	public static String FIND_PON_AUCTION =
					"SELECT slv.meaning\n" +
					"      ,aph.auction_number\n" +
					"      ,aph.auction_status\n" +
					"      ,slv3.meaning\n" +
					"      ,api.quantity\n" +
					"      ,NULL\n" +
					"      ,NULL\n" +
					"      ,NULL\n" +
					"FROM   srm_pon_auction_headers aph\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'PON_AUCTION_TYPE'\n" +
					"AND    slv.lookup_code = aph.auction_type\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_type = 'PON_AUCTION_STATUS'\n" +
					"AND    slv3.lookup_code = aph.auction_status, srm_pon_auction_items api\n" +
					"WHERE  aph.auction_header_id = api.auction_header_id\n";

	public static String QUERY_PO_FRAMEWORK_AGREEMEN_LINE_SQL = "SELECT pl.line_number AS lineNumber FROM srm_po_lines pl WHERE pl.po_header_id = :poHeaderId" ;

    //目录采购查询list（分页）——初始化数据查询
    public static final String QUERY_SRMPOLINESINFOLIST_SQL =
					"SELECT t.po_line_id AS poLineId\n" +
					"      ,t.po_line_id AS agreementLineId\n" +
					"      ,t.po_header_id AS poHeaderId\n" +
					"      ,t.line_number AS lineNumber\n" +
					"      ,t.item_id AS itemId\n" +
					"      ,t.item_name AS itemName\n" +
					"      ,t.item_spec AS itemSpec\n" +
					"      ,t.category_id AS categoryId\n" +
					"      ,t.demand_qty AS demandQty\n" +
					"      ,t.min_po_qty AS minPoQty\n" +
					"      ,t.tax_price AS taxPrice\n" +
					"      ,t.non_tax_price AS nonTaxPrice\n" +
					"      ,t.ladder_price_flag AS ladderPriceFlag\n" +
					"      ,t.ladder_qty AS ladderQty\n" +
					"      ,t.accumulative_flag AS accumulativeFlag\n" +
					"      ,t.demand_date AS demandDate\n" +
					"      ,t.receive_to_organization_id AS receiveToOrganizationId\n" +
					"      ,t.status AS status\n" +
					"      ,t.description AS description\n" +
					"      ,t.may_notice_qty AS mayNoticeQty\n" +
					"      ,t.on_way_qty AS onWayQty\n" +
					"      ,t.received_qty AS receivedQty\n" +
					"      ,t.original_demand_qty AS originalDemandQty\n" +
					"      ,t.original_demand_date AS originalDemandDate\n" +
					"      ,t.feedback_adjust_date AS feedbackAdjustDate\n" +
					"      ,t.feedback_adjust_qty AS feedbackAdjustQty\n" +
					"      ,t.feedback_status AS feedbackStatus\n" +
					"      ,t.feedback_result AS feedbackResult\n" +
					"      ,t.reject_reason AS rejectReason\n" +
					"      ,t.start_date AS startDate\n" +
					"      ,t.end_date AS endDate\n" +
					"      ,t.source_number AS sourceNumber\n" +
					"      ,t.source_code AS sourceCode\n" +
					"      ,t.source_id AS sourceId\n" +
					"      ,t.version_num AS versionNum\n" +
					"      ,t.creation_date AS creationDate\n" +
					"      ,t.created_by AS createdBy\n" +
					"      ,t.last_updated_by AS lastUpdatedBy\n" +
					"      ,t.last_update_date AS lastUpdateDate\n" +
					"      ,t.last_update_login AS lastUpdateLogin\n" +
					"      ,t.attribute_category AS attributeCategory\n" +
					"      ,t.attribute1 AS attribute1\n" +
					"      ,t.attribute2 AS attribute2\n" +
					"      ,t.attribute3 AS attribute3\n" +
					"      ,t.attribute4 AS attribute4\n" +
					"      ,t.attribute5 AS attribute5\n" +
					"      ,t.attribute6 AS attribute6\n" +
					"      ,t.attribute7 AS attribute7\n" +
					"      ,t.attribute8 AS attribute8\n" +
					"      ,t.attribute9 AS attribute9\n" +
					"      ,t.attribute10 AS attribute10\n" +
					"      ,x.ln_count\n" +
					"      ,sph.currency_code AS currencyCode\n" +
					"      ,slv2.meaning AS currencyCodeName\n" +
					"      ,sph.po_number AS poNumber\n" +
					"      ,sph.org_id AS orgId\n" +
					"      ,si.inst_name AS orgName\n" +
					"      ,sph.supplier_id AS supplierId\n" +
					"      ,sps.supplier_name AS supplierName\n" +
					"      ,sbi.item_code AS itemCode\n" +
					"      ,sbi.uom_code AS uomCode\n" +
					"      ,slv.meaning AS uomCodeName\n" +
					"      ,sbc.full_category_code AS fullCategoryCode\n" +
					"      ,sbc.full_category_name AS fullCategoryName\n" +
					"      ,sbc.category_code AS categoryCode\n" +
					"      ,sbc.category_name AS categoryName\n" +
					"      ,sbc.category_level AS categoryLevel\n" +
					"      ,sbi.image_id AS imageId\n" +
					"      ,sbrf.file_name AS imageFileName\n" +
					"      ,sbrf.access_path AS imageAccessPath\n" +
					"      ,(SELECT COUNT(sphc.shopping_cart_id)\n" +
					"        FROM   srm_po_shopping_carts sphc\n" +
					"        WHERE  sphc.org_id = sph.org_id\n" +
					"        AND    (sphc.created_po_flag IS NULL OR sphc.created_po_flag = '' OR\n" +
					"              sphc.created_po_flag = 'N')\n" +
					"        AND    sphc.agreement_line_id = t.po_line_id\n" +
					"        AND    sphc.created_by = :userId) AS shoppingCarCount\n" +
					"FROM   srm_po_lines t\n" +
					"LEFT   JOIN srm_po_headers sph\n" +
					"ON     sph.po_header_id = t.po_header_id\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = t.item_id\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sbi.image_id = sbrf.file_id\n" +
					"LEFT   JOIN srm_pos_supplier_info sps\n" +
					"ON     sps.supplier_id = sph.supplier_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = sph.org_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"AND    slv.lookup_code = sbi.uom_code\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_type = 'BANK_CURRENCY'\n" +
					"AND    slv2.lookup_code = sph.currency_code\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbc.category_id = t.category_id, (SELECT t1.item_id\n" +
					"              ,COUNT(t1.item_id) ln_count\n" +
					"        FROM   srm_po_lines t1\n" +
					"        GROUP  BY t1.item_id) x\n" +
					"WHERE  t.item_id = x.item_id\n" +
					"AND    sph.status = 'APPROVED'\n" +
					"AND    sph.po_doc_type = 'AGREEMENT'\n" +
					"AND    t.status = 'OPEN'\n" +
					"AND    (sph.start_date IS NULL OR sph.start_date <= trunc(SYSDATE))\n" +
					"AND    (sph.end_date IS NULL OR sph.end_date >= trunc(SYSDATE))\n" +
					"AND    (t.start_date IS NULL OR t.start_date <= trunc(SYSDATE))\n" +
					"AND    (t.end_date IS NULL OR t.end_date >= trunc(SYSDATE))\n" +
					"AND    trunc(SYSDATE - 30) <= trunc(sph.creation_date)\n" +
					"ORDER  BY x.ln_count DESC, sbi.item_code\n";

	//目录采购查询list（分页）——搜索按钮触发的SQL
	public static final String QUERY_SRMPOLINESINFOLISTV_SQL =
					"SELECT t.po_line_id AS poLineId\n" +
					"      ,t.po_line_id AS agreementLineId\n" +
					"      ,t.po_header_id AS poHeaderId\n" +
					"      ,t.line_number AS lineNumber\n" +
					"      ,t.item_id AS itemId\n" +
					"      ,t.item_name AS itemName\n" +
					"      ,t.item_spec AS itemSpec\n" +
					"      ,t.category_id AS categoryId\n" +
					"      ,t.demand_qty AS demandQty\n" +
					"      ,t.min_po_qty AS minPoQty\n" +
					"      ,t.tax_price AS taxPrice\n" +
					"      ,t.non_tax_price AS nonTaxPrice\n" +
					"      ,t.ladder_price_flag AS ladderPriceFlag\n" +
					"      ,t.ladder_qty AS ladderQty\n" +
					"      ,t.accumulative_flag AS accumulativeFlag\n" +
					"      ,t.demand_date AS demandDate\n" +
					"      ,t.receive_to_organization_id AS receiveToOrganizationId\n" +
					"      ,t.status AS status\n" +
					"      ,t.description AS description\n" +
					"      ,t.may_notice_qty AS mayNoticeQty\n" +
					"      ,t.on_way_qty AS onWayQty\n" +
					"      ,t.received_qty AS receivedQty\n" +
					"      ,t.original_demand_qty AS originalDemandQty\n" +
					"      ,t.original_demand_date AS originalDemandDate\n" +
					"      ,t.feedback_adjust_date AS feedbackAdjustDate\n" +
					"      ,t.feedback_adjust_qty AS feedbackAdjustQty\n" +
					"      ,t.feedback_status AS feedbackStatus\n" +
					"      ,t.feedback_result AS feedbackResult\n" +
					"      ,t.reject_reason AS rejectReason\n" +
					"      ,t.start_date AS startDate\n" +
					"      ,t.end_date AS endDate\n" +
					"      ,t.source_number AS sourceNumber\n" +
					"      ,t.source_code AS sourceCode\n" +
					"      ,t.source_id AS sourceId\n" +
					"      ,t.version_num AS versionNum\n" +
					"      ,t.creation_date AS creationDate\n" +
					"      ,t.created_by AS createdBy\n" +
					"      ,t.last_updated_by AS lastUpdatedBy\n" +
					"      ,t.last_update_date AS lastUpdateDate\n" +
					"      ,t.last_update_login AS lastUpdateLogin\n" +
					"      ,t.attribute_category AS attributeCategory\n" +
					"      ,t.attribute1 AS attribute1\n" +
					"      ,t.attribute2 AS attribute2\n" +
					"      ,t.attribute3 AS attribute3\n" +
					"      ,t.attribute4 AS attribute4\n" +
					"      ,t.attribute5 AS attribute5\n" +
					"      ,t.attribute6 AS attribute6\n" +
					"      ,t.attribute7 AS attribute7\n" +
					"      ,t.attribute8 AS attribute8\n" +
					"      ,t.attribute9 AS attribute9\n" +
					"      ,t.attribute10 AS attribute10\n" +
					"      ,sph.currency_code AS currencyCode\n" +
					"      ,slv2.meaning AS currencyCodeName\n" +
					"      ,sph.po_number AS poNumber\n" +
					"      ,sph.org_id AS orgId\n" +
					"      ,si.inst_name AS orgName\n" +
					"      ,sph.supplier_id AS supplierId\n" +
					"      ,sps.supplier_name AS supplierName\n" +
					"      ,sbi.item_code AS itemCode\n" +
					"      ,sbi.uom_code AS uomCode\n" +
					"      ,slv.meaning AS uomCodeName\n" +
					"      ,sbc.full_category_code AS fullCategoryCode\n" +
					"      ,sbc.full_category_name AS fullCategoryName\n" +
					"      ,sbc.category_code AS categoryCode\n" +
					"      ,sbc.category_name AS categoryName\n" +
					"      ,sbc.category_level AS categoryLevel\n" +
					"      ,sbi.image_id AS imageId\n" +
					"      ,sbrf.file_name AS imageFileName\n" +
					"      ,sbrf.access_path AS imageAccessPath\n" +
					"      ,(SELECT COUNT(sphc.shopping_cart_id)\n" +
					"        FROM   srm_po_shopping_carts sphc\n" +
					"        WHERE  sphc.org_id = sph.org_id\n" +
					"        AND    (sphc.created_po_flag IS NULL OR sphc.created_po_flag = '' OR\n" +
					"              sphc.created_po_flag = 'N')\n" +
					"        AND    sphc.agreement_line_id = t.po_line_id\n" +
					"        AND    sphc.created_by = :userId) AS shoppingCarCount\n" +
					"FROM   srm_po_lines t\n" +
					"LEFT   JOIN srm_po_headers sph\n" +
					"ON     sph.po_header_id = t.po_header_id\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = t.item_id\n" +
					"LEFT   JOIN saaf_base_result_file sbrf\n" +
					"ON     sbi.image_id = sbrf.file_id\n" +
					"LEFT   JOIN srm_pos_supplier_info sps\n" +
					"ON     sps.supplier_id = sph.supplier_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = sph.org_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"AND    slv.lookup_code = sbi.uom_code\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_type = 'BANK_CURRENCY'\n" +
					"AND    slv2.lookup_code = sph.currency_code\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbc.category_id = t.category_id\n" +
					"WHERE  sph.status = 'APPROVED'\n" +
					"AND    sph.po_doc_type = 'AGREEMENT'\n" +
					"AND    t.status = 'OPEN'\n" +
					"AND    (sph.start_date IS NULL OR sph.start_date <= trunc(SYSDATE))\n" +
					"AND    (sph.end_date IS NULL OR sph.end_date >= trunc(SYSDATE))\n" +
					"AND    (t.start_date IS NULL OR t.start_date <= trunc(SYSDATE))\n" +
					"AND    (t.end_date IS NULL OR t.end_date >= trunc(SYSDATE))\n";

	public static final String QUERY_PO_LINE_NUMBER_MAX_SQL =
			"SELECT MAX(Spl.Line_Number) AS lineNumber\n" +
			"  FROM Srm_Po_Lines Spl\n" +
			" WHERE Spl.Po_Header_Id = :poHeaderId\n";

	private Integer poLineId; //采购订单行ID
    private Integer poHeaderId; //订单头ID
    private Integer lineNumber; //行号
    private Integer itemId; //物料ID，关联表：srm_base_items
    private String itemName; //物料名称
    private String itemSpec; //物料规格
    private Integer categoryId; //采购分类ID，关联表：srm_base_categories
    private BigDecimal demandQty; //需求数量
    private BigDecimal minPoQty; //最小采购量
    private BigDecimal taxPrice; //含税单价
    private BigDecimal nonTaxPrice; //不含税单价
    private String ladderPriceFlag; //阶梯价标识（Y/N）
    private BigDecimal ladderQty; //阶梯范围
    private String accumulativeFlag; //累计结算标识（Y/N）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date demandDate; //需求日期
    private Integer receiveToOrganizationId; //收货组织ID
    private String status; //行状态(PO_LINE_STATUS)
    private String description; //说明
    private BigDecimal mayNoticeQty; //可通知送货数量
    private BigDecimal onWayQty; //在途数量(已创建送货单数量)
    private BigDecimal receivedQty; //已接收数量
    private BigDecimal originalDemandQty; //原需求数量
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date originalDemandDate; //原需求日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackAdjustDate; //反馈调整日期
    private BigDecimal feedbackAdjustQty; //反馈调整数量
    private String feedbackStatus; //反馈状态
    private String feedbackResult; //反馈结果
    private String rejectReason; //供应商拒绝原因
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //有效开始日期
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //有效结束日期
    private String sourceNumber; //来源单号
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
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
    private Integer operatorUserId;

    // 自定义
 	private BigDecimal deliverableQty;
 	private String itemCode;//物料编码
 	private String unitOfMeasure;
 	private String instCode;
 	private String itemDescription;//物料说明
    private String poDocTypeDESC; //单据类型描述
    private String fullCategoryCode; //采购分类全编号
    private String fullCategoryName; //采购分类全名称
    private String categoryCode;//采购分类编码
    private String categoryName;//采购分类名称
    private Integer categoryLevel; //分类层级
	private Integer orgId; //业务实体ID
    private String orgName;// 业务实体名称
    private String poNumber; //采购订单号
    private String uomCode; //计量单位
    private String uomCodeName; //别名-物料单位名称
    private Integer supplierId; //供应商ID
    private String supplierName; //供应商名称
	private String currencyCode; //币种(BANK_CURRENCY)
	private String currencyCodeName; //币种name(BANK_CURRENCY)
    private String isShowPrice;//是否显示价格（Y/N）
	private String categoryIdList;//categoryIdList
	private Integer imageId; //物料图片ID
	private String imageFileName; // 物料图片名称
	private String imageAccessPath; // 物料图片下载路径
	private Integer agreementLineId;//框架协议行ID
	private Integer shoppingCarCount;//加入购物车的数量
	private String statusDesc;//订单单据状态

	private BigDecimal returnQty;
	private String erpPoNumber;
	private String context;
	private String projectCategory;
	private String projectType;
	private String technicalTransNumber;
	private String subprojectNumber;
	private String acceptanceProcessNumber;
	private String taxRateCode;
	private BigDecimal nonTaxTotalPrice;
	private BigDecimal taxTotalPrice;
	private BigDecimal nonTaxActTotalPrice;
	private BigDecimal taxActTotalPrice;

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Integer getShoppingCarCount() {
		return shoppingCarCount;
	}

	public void setShoppingCarCount(Integer shoppingCarCount) {
		this.shoppingCarCount = shoppingCarCount;
	}

	public Integer getAgreementLineId() {
		return agreementLineId;
	}

	public void setAgreementLineId(Integer agreementLineId) {
		this.agreementLineId = agreementLineId;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageAccessPath() {
		return imageAccessPath;
	}

	public void setImageAccessPath(String imageAccessPath) {
		this.imageAccessPath = imageAccessPath;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCodeName() {
		return currencyCodeName;
	}

	public void setCurrencyCodeName(String currencyCodeName) {
		this.currencyCodeName = currencyCodeName;
	}

	public String getCategoryIdList() {
		return categoryIdList;
	}

	public void setCategoryIdList(String categoryIdList) {
		this.categoryIdList = categoryIdList;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getIsShowPrice() {
        return isShowPrice;
    }

    public void setIsShowPrice(String isShowPrice) {
        this.isShowPrice = isShowPrice;
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

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public String getUomCodeName() {
        return uomCodeName;
    }

    public void setUomCodeName(String uomCodeName) {
        this.uomCodeName = uomCodeName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	
	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	
	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	
	public Integer getItemId() {
		return itemId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	
	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
	}

	
	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	
	public Date getDemandDate() {
		return demandDate;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	
	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getDescription() {
		return description;
	}

	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}

	
	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}

	public void setOnWayQty(BigDecimal onWayQty) {
		this.onWayQty = onWayQty;
	}

	
	public BigDecimal getOnWayQty() {
		return onWayQty;
	}

	public void setReceivedQty(BigDecimal receivedQty) {
		this.receivedQty = receivedQty;
	}

	
	public BigDecimal getReceivedQty() {
		return receivedQty;
	}

	public void setOriginalDemandQty(BigDecimal originalDemandQty) {
		this.originalDemandQty = originalDemandQty;
	}

	
	public BigDecimal getOriginalDemandQty() {
		return originalDemandQty;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
	}

	
	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setFeedbackAdjustDate(Date feedbackAdjustDate) {
		this.feedbackAdjustDate = feedbackAdjustDate;
	}

	
	public Date getFeedbackAdjustDate() {
		return feedbackAdjustDate;
	}

	public void setFeedbackAdjustQty(BigDecimal feedbackAdjustQty) {
		this.feedbackAdjustQty = feedbackAdjustQty;
	}

	
	public BigDecimal getFeedbackAdjustQty() {
		return feedbackAdjustQty;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	
	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	
	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	
	public String getSourceId() {
		return sourceId;
	}

	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}

	
	public Integer getVersionNum() {
		return versionNum;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	
	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setAttributeCategory(String attributeCategory) {
		this.attributeCategory = attributeCategory;
	}

	
	public String getAttributeCategory() {
		return attributeCategory;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	
	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	
	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	
	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	
	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	
	public String getAttribute10() {
		return attribute10;
	}

	public void setOperatorUserId(Integer operatorUserId) {
		this.operatorUserId = operatorUserId;
	}

	
	public Integer getOperatorUserId() {
		return operatorUserId;
	}


	public BigDecimal getDeliverableQty() {
		return deliverableQty;
	}


	public void setDeliverableQty(BigDecimal deliverableQty) {
		this.deliverableQty = deliverableQty;
	}


	public String getPoNumber() {
		return poNumber;
	}


	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}


	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}


	public String getInstCode() {
		return instCode;
	}


	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

    public String getPoDocTypeDESC() {
        return poDocTypeDESC;
    }

    public void setPoDocTypeDESC(String poDocTypeDESC) {
        this.poDocTypeDESC = poDocTypeDESC;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public BigDecimal getMinPoQty() {
        return minPoQty;
    }

    public void setMinPoQty(BigDecimal minPoQty) {
        this.minPoQty = minPoQty;
    }

    public String getLadderPriceFlag() {
        return ladderPriceFlag;
    }

    public void setLadderPriceFlag(String ladderPriceFlag) {
        this.ladderPriceFlag = ladderPriceFlag;
    }

    public BigDecimal getLadderQty() {
        return ladderQty;
    }

    public void setLadderQty(BigDecimal ladderQty) {
        this.ladderQty = ladderQty;
    }

    public String getAccumulativeFlag() {
        return accumulativeFlag;
    }

    public void setAccumulativeFlag(String accumulativeFlag) {
        this.accumulativeFlag = accumulativeFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public String getErpPoNumber() {
		return erpPoNumber;
	}

	public void setErpPoNumber(String erpPoNumber) {
		this.erpPoNumber = erpPoNumber;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getTechnicalTransNumber() {
		return technicalTransNumber;
	}

	public void setTechnicalTransNumber(String technicalTransNumber) {
		this.technicalTransNumber = technicalTransNumber;
	}

	public String getSubprojectNumber() {
		return subprojectNumber;
	}

	public void setSubprojectNumber(String subprojectNumber) {
		this.subprojectNumber = subprojectNumber;
	}

	public String getAcceptanceProcessNumber() {
		return acceptanceProcessNumber;
	}

	public void setAcceptanceProcessNumber(String acceptanceProcessNumber) {
		this.acceptanceProcessNumber = acceptanceProcessNumber;
	}

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public BigDecimal getNonTaxTotalPrice() {
		return nonTaxTotalPrice;
	}

	public void setNonTaxTotalPrice(BigDecimal nonTaxTotalPrice) {
		this.nonTaxTotalPrice = nonTaxTotalPrice;
	}

	public BigDecimal getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(BigDecimal taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

	public BigDecimal getNonTaxActTotalPrice() {
		return nonTaxActTotalPrice;
	}

	public void setNonTaxActTotalPrice(BigDecimal nonTaxActTotalPrice) {
		this.nonTaxActTotalPrice = nonTaxActTotalPrice;
	}

	public BigDecimal getTaxActTotalPrice() {
		return taxActTotalPrice;
	}

	public void setTaxActTotalPrice(BigDecimal taxActTotalPrice) {
		this.taxActTotalPrice = taxActTotalPrice;
	}
}
