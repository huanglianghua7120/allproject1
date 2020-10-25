package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SrmPoHeadersEntity_HI_RO Entity Object Mon Apr 09 20:41:24 CST 2018 Auto
 * Generate
 */

public class SrmPoHeadersEntity_HI_RO {
	//
	public static String GET_SUM_DEMANDQTY_SQL =
					"SELECT\n" +
					"  ph.source_id sourceId,\n" +
					"  SUM(pl.demand_qty) sumDemandQty\n" +
					"FROM\n" +
					"  srm_po_headers ph\n" +
					"  LEFT JOIN srm_po_lines pl\n" +
					"    ON ph.po_header_id = pl.po_header_id\n" +
					"WHERE 1 = 1\n" +
					"  AND pl.item_id = :itemId\n" +
					"  AND ph.source_id =\n" +
					"  (SELECT\n" +
					"    rh.requisition_header_id\n" +
					"  FROM\n" +
					"    srm_po_requisition_headers rh\n" +
					"  WHERE rh.source_id = :poHeaderId)\n" +
	                " GROUP BY Ph.Source_Id";

	public static String GET_PO_HEADER_SQL =
					"SELECT a.po_number poNumber\n" +
					"      ,a.po_header_id poHeaderId\n" +
					"      ,(CASE\n" +
					"         WHEN a.po_doc_type = '01' THEN\n" +
					"          'POA'\n" +
					"         WHEN a.po_doc_type = '02' THEN\n" +
					"          'POD'\n" +
					"         WHEN a.po_doc_type = '03' THEN\n" +
					"          'POC'\n" +
					"         ELSE\n" +
					"          NULL\n" +
					"       END) poDocType\n" +
					"      ,a.currency_code currencyCode\n" +
					"      ,(CASE\n" +
					"         WHEN a.status = 'APPROVED' THEN\n" +
					"          '2'\n" +
					"         WHEN a.status = 'APPROVING' THEN\n" +
					"          '1'\n" +
					"         WHEN a.status = 'OPENED' THEN\n" +
					"          '0'\n" +
					"         ELSE\n" +
					"          '-1'\n" +
					"       END) status\n" +
					"      ,b.employee_number employeeNumber\n" +
					"      ,c.supplier_number supplierNumber\n" +
					"      ,a.creation_date creationDate\n" +
					"      ,a.contract_code contractNumber\n" +
					"FROM   srm_po_headers a\n" +
					"LEFT   JOIN saaf_employees b\n" +
					"ON     a.buyer_id = b.employee_id\n" +
					"LEFT   JOIN srm_pos_supplier_info c\n" +
					"ON     a.supplier_id = c.supplier_id\n" +
					"WHERE  1 = 1\n";

	public static String GET_PO_CHANGE_SQL =
					"SELECT\n" +
					"  sph.po_number poNumber,\n" +
					"  emp.employee_number employeeNumber,\n" +
					"  spl.line_number lineNumber,\n" +
					"  spl.demand_qty demandQty,\n" +
					"  spl.demand_date demandDate,\n" +
					"  spl.po_line_id poLineId\n" +
					"FROM\n" +
					"  srm_po_headers sph\n" +
					"  JOIN srm_po_lines spl\n" +
					"    ON sph.po_header_id = spl.po_header_id\n" +
					"  LEFT JOIN saaf_employees emp\n" +
					"    ON emp.employee_id = sph.buyer_id\n" +
					"WHERE 1 = 1";

	public static String QUERY_ORDER_NOTICE_LIST_SQL =
					"SELECT b.line_number AS lineNumber\n" +
					"      ,a.po_header_id AS poHeaderId\n" +
					"      ,a.po_number AS poNumber\n" +
					"      ,a.supplier_id AS supplierId\n" +
					"      ,a.supplier_site_id AS supplierSiteId\n" +
					"      ,b.po_line_id AS poLineId\n" +
					"      ,a.org_id AS orgId\n" +
					"      ,c.inst_name AS orgName\n" +
					"      ,a.bill_to_organization_id AS billToOrganizationId\n" +
					"      ,m.inst_name AS billToOrganizationName\n" +
					"      ,a.ship_to_organization_id AS shipToOrganizationId\n" +
					"      ,m2.inst_name AS shipToOrganizationName\n" +
					"      ,a.creation_date AS creationDate\n" +
					"      ,b.demand_date AS demandDate\n" +
					"      ,b.item_id AS itemId\n" +
					"      ,nvl(bi.item_code,\n" +
					"           (SELECT a.item_code\n" +
					"            FROM   srm_base_items_b a\n" +
					"            WHERE  b.item_id = a.item_id)) AS itemCode\n" +
					"      ,nvl(bi.item_name,\n" +
					"           (SELECT a.item_name\n" +
					"            FROM   srm_base_items_b a\n" +
					"            WHERE  b.item_id = a.item_id)) AS itemName\n" +
					"      ,slv3.meaning AS uomCodeDesc\n" +
					"      ,b.demand_qty AS demandQty\n" +
					"      ,b.category_id AS categoryId\n" +
					"      ,e.category_name AS categoryName\n" +
					"      ,e.category_code AS categoryCode\n" +
					"      ,a.description AS description\n" +
					"      ,b.description AS lineDescription\n" +
					"      ,a.buyer_id AS buyerId\n" +
					"      ,f.employee_name AS employeeName\n" +
					"      ,a.return_goods_type AS returnGoodsType\n" +
					"      ,j.meaning AS returnGoodsTypeStr\n" +
					"      ,a.status AS status\n" +
					"      ,h.meaning AS statusStr\n" +
					"      ,b.status AS lineStatus\n" +
					"      ,i.meaning AS lineStatusStr\n" +
					"      ,s1.meaning AS currencyCode\n" +
					"      ,s2.meaning AS taxRateCode\n" +
					"      ,b.tax_price AS taxPrice\n" +
					"      ,b.non_tax_price AS nonTaxPrice\n" +
					"      ,(SELECT SUM(w.tax_price)\n" +
					"        FROM   srm_po_lines w\n" +
					"        WHERE  w.po_header_id = a.po_header_id) AS taxTotalPrice\n" +
					"      ,(SELECT SUM(w.non_tax_price)\n" +
					"        FROM   srm_po_lines w\n" +
					"        WHERE  w.po_header_id = a.po_header_id) AS nonTaxTotalPrice\n" +
					"      ,s3.meaning AS paymentCondition\n" +
					"      ,s4.meaning AS settlementWay\n" +
					"      ,b.receive_to_organization_id AS receiveToOrganizationId\n" +
					"      ,n.inst_name AS receiveToOrganizationName\n" +
					"      ,b.may_notice_qty AS mayNoticeQty\n" +
					"      ,b.on_way_qty AS onWayQty\n" +
					"      ,b.received_qty AS receivedQty\n" +
					"      ,b.original_demand_qty AS originalDemandQty\n" +
					"      ,b.original_demand_date AS originalDemandDate\n" +
					"      ,b.feedback_adjust_date AS feedbackAdjustDate\n" +
					"      ,b.feedback_adjust_qty AS feedbackAdjustQty\n" +
					"      ,b.feedback_status AS feedbackStatus\n" +
					"      ,k.meaning AS feedbackStatusStr\n" +
					"      ,b.feedback_result AS feedbackResult\n" +
					"      ,l.meaning AS feedbackResultStr\n" +
					"      ,b.reject_reason AS rejectReason\n" +
					"FROM   srm_po_headers a\n" +
					"LEFT   JOIN srm_po_lines b\n" +
					"ON     a.po_header_id = b.po_header_id\n" +
					"LEFT   JOIN saaf_institution c\n" +
					"ON     a.org_id = c.inst_id\n" +
					"AND    c.inst_type = 'ORG'\n" +
					"LEFT   JOIN srm_base_items bi\n" +
					"ON     b.item_id = bi.item_id\n" +
					"AND    bi.organization_id = a.bill_to_organization_id\n" +
					"LEFT   JOIN saaf_lookup_values slv3\n" +
					"ON     slv3.lookup_code = bi.uom_code\n" +
					"AND    slv3.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN srm_base_categories e\n" +
					"ON     b.category_id = e.category_id\n" +
					"LEFT   JOIN saaf_lookup_values s1\n" +
					"ON     a.currency_code = s1.lookup_code\n" +
					"AND    s1.lookup_type = 'BANK_CURRENCY'\n" +
					"LEFT   JOIN saaf_lookup_values s2\n" +
					"ON     a.tax_rate_code = s2.lookup_code\n" +
					"AND    s2.lookup_type = 'PON_TAX_LIST'\n" +
					"LEFT   JOIN saaf_lookup_values s3\n" +
					"ON     a.payment_condition = s3.lookup_code\n" +
					"AND    s3.lookup_type = 'PON_PAY_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values s4\n" +
					"ON     a.settlement_way = s4.lookup_code\n" +
					"AND    s4.lookup_type = 'PAYMENT_METHOD'\n" +
					"LEFT   JOIN saaf_employees f\n" +
					"ON     a.buyer_id = f.employee_id\n" +
					"LEFT   JOIN saaf_lookup_values h\n" +
					"ON     h.lookup_type = 'ISP_PO_STATUS'\n" +
					"AND    a.status = h.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values i\n" +
					"ON     i.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
					"AND    b.status = i.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values j\n" +
					"ON     j.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"AND    a.return_goods_type = j.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values k\n" +
					"ON     k.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\n" +
					"AND    b.feedback_status = k.lookup_code\n" +
					"LEFT   JOIN saaf_lookup_values l\n" +
					"ON     l.lookup_type = 'ISP_PO_SUPPLIER_FEEDBACK'\n" +
					"AND    b.feedback_result = l.lookup_code\n" +
					"LEFT   JOIN saaf_institution m\n" +
					"ON     a.bill_to_organization_id = m.inst_id\n" +
					"AND    m.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_institution m2\n" +
					"ON     a.ship_to_organization_id = m2.inst_id\n" +
					"AND    m2.inst_type = 'ORG'\n" +
					"LEFT   JOIN saaf_institution n\n" +
					"ON     b.receive_to_organization_id = n.inst_id\n" +
					"AND    n.inst_type = 'ORGANIZATION'\n" +
					"WHERE  a.status = 'APPROVED'\n" +
					"AND    a.return_goods_type = 'BASE_ON_NOTICE'\n" +
					"AND    b.may_notice_qty > 0\n" +
					"AND    b.status = 'OPEN'\n" +
					"AND    b.feedback_status = 'CONFIRMED'\n";

	/**
	 * 供应商查询订单
	 */
	public static String QUERY_ORDER_LIST_SQL =
					"SELECT\n" +
					"  sph.po_header_id AS poHeaderId\n" +
					"  , sph.po_number AS poNumber\n" +
					"  , sph.org_id AS orgId\n" +
					"  , sph.bill_to_organization_id AS billToOrganizationId\n" +
					"  , sph.ship_to_organization_id AS shipToOrganizationId\n" +
					"  , sph.supplier_id AS supplierId\n" +
					"  , sph.supplier_site_id AS supplierSiteId\n" +
					"  , sph.currency_code AS currencyCode\n" +
					"  , spl.tax_rate_code AS taxRateCode\n" +
                    "  , slv10.meaning AS lineTaxRate\n" +
					"  , sph.buyer_id AS buyerId\n" +
					"  , sph.pr_number AS prNumber\n" +
					"  , sph.location_code AS locationCode\n" +
					"  , sph.return_goods_type AS returnGoodsType\n" +
					"  , sph.payment_condition AS paymentCondition\n" +
					"  , sph.settlement_way AS settlementWay\n" +
					"  , sph.po_versions AS poVersions\n" +
					"  , sph.status AS status\n" +
					"  , sph.approved_date AS approvedDate\n" +
					"  , sph.start_date AS startDate\n" +
					"  , sph.end_date AS endDate\n" +
					"  , sph.description AS description\n" +
					"  , sph.creation_date AS creationDate\n" +
					"  , si1.inst_name AS orgName\n" +
					"  , slv1.meaning AS currencyCodeStr\n" +
					"  , slv2.meaning AS taxRateCodeStr\n" +
					"  , ppt.payment_term_name AS paymentConditionStr\n" +
					"  , slv3.meaning AS settlementWayStr\n" +
					"  , emp.employee_name AS employeeName\n" +
					"  , slv4.meaning AS statusStr\n" +
					"  , slv5.meaning AS returnGoodsTypeStr\n" +
					"  , si2.inst_name AS billToOrganizationName\n" +
					"  , psi.supplier_name AS supplierName\n" +
					"  , pss.site_name as siteName\n" +
					"  , spl.po_line_id AS poLineId\n" +
                    "  , spl.po_line_comb_id AS poLineCombId\n" +
					"  , spl.line_number AS lineNumber\n" +
					"  , spl.item_id AS itemId\n" +
					"  , spl.item_name AS itemName\n" +
					"  , spl.item_spec AS itemSpec\n" +
					"  , spl.demand_date AS demandDate\n" +
					"  , spl.demand_qty AS demandQty\n" +
					"  , spl.category_id AS categoryId\n" +
					"  , spl.description AS lineDescription\n" +
					"  , spl.status AS lineStatus\n" +
					"  , spl.tax_price AS taxPrice\n" +
					"  , spl.non_tax_price AS nonTaxPrice\n" +
					"  , spl.receive_to_organization_id AS receiveToOrganizationId\n" +
					"  , spl.may_notice_qty AS mayNoticeQty\n" +
					"  , spl.on_way_qty AS onWayQty\n" +
					"  , spl.received_qty AS receivedQty\n" +
					"  , spl.return_qty AS returnQty\n" +
					"  , spl.original_demand_qty AS originalDemandQty\n" +
					"  , spl.original_demand_date AS originalDemandDate\n" +
					"  , spl.feedback_adjust_date AS feedbackAdjustDate\n" +
					"  , spl.feedback_adjust_qty AS feedbackAdjustQty\n" +
					"  , spl.feedback_status AS feedbackStatus\n" +
					"  , spl.feedback_result AS feedbackResult\n" +
					"  , spl.reject_reason AS rejectReason\n" +
					"  , spl.erp_po_number AS erpPoNumber\n" +
                    "  ,spl.non_tax_act_total_price as nonTaxActTotalPrice\n"+
					"  , sbib.item_code AS itemCode\n" +
					"  , sbc.full_category_code AS categoryCode\n" +
					"  , sbc.full_category_name AS categoryName\n" +
					"  , slv6.meaning AS uomCodeDesc\n" +
					"  , slv7.meaning AS lineStatusStr\n" +
					"  , slv8.meaning AS feedbackStatusStr\n" +
					"  , slv9.meaning AS feedbackResultStr\n" +
					"  , si3.inst_name AS receiveToOrganizationName\n" +
                    "  , sph.organization_id AS organizationId\n" +
                    "  , si4.inst_name AS organizationName\n" +
                    "  , sph.contract_Id AS contractId\n" +
                    "  , sph.contract_code AS contractCode\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    ROUND(\n" +
					"      SUM(spl1.demand_qty * spl1.tax_price)\n" +
					"      , 2\n" +
					"    )\n" +
					"  FROM\n" +
					"    srm_po_lines spl1\n" +
					"  WHERE spl1.po_header_id = sph.po_header_id) AS taxTotalPrice\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    ROUND(\n" +
					"      SUM(\n" +
					"        spl2.demand_qty * spl2.non_tax_price\n" +
					"      )\n" +
					"      , 2\n" +
					"    )\n" +
					"  FROM\n" +
					"    srm_po_lines spl2\n" +
					"  WHERE spl2.po_header_id = sph.po_header_id) AS nonTaxTotalPrice\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    SUM(pnl.notice_delivery_qty)\n" +
					"  FROM\n" +
					"    srm_po_notice_line pnl\n" +
					"  WHERE pnl.po_line_id = spl.po_line_id) AS notifiedQty\n" +
					"FROM\n" +
					"  srm_po_headers sph\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = sph.currency_code\n" +
					"    AND slv1.lookup_type = 'BANK_CURRENCY'\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = sph.tax_rate_code\n" +
					"    AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"  LEFT JOIN srm_pon_payment_terms ppt\n" +
					"    ON ppt.payment_term_code = sph.payment_condition\n" +
					"    AND ppt.payment_term_status = 'ACT'\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = sph.settlement_way\n" +
					"    AND slv3.lookup_type = 'PAYMENT_METHOD'\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_code = sph.status\n" +
					"    AND slv4.lookup_type = 'ISP_PO_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv5\n" +
					"    ON slv5.lookup_code = sph.return_goods_type\n" +
					"    AND slv5.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"  LEFT JOIN saaf_institution si2\n" +
					"    ON si2.inst_id = sph.bill_to_organization_id\n" +
					"  left join srm_pos_supplier_sites pss\n" +
					"    on pss.supplier_site_id = sph.supplier_site_id\n" +
					"  left join srm_po_lines spl\n" +
					"    on spl.po_header_id = sph.po_header_id\n" +
                    "  LEFT JOIN saaf_lookup_values slv10\n" +
                    "    ON slv10.lookup_code = spl.tax_rate_code\n" +
                    "    AND slv10.lookup_type = 'PON_TAX_LIST'\n" +
					"  left join srm_base_items_b sbib\n" +
					"    on sbib.item_id = spl.item_id\n" +
					"  left join saaf_lookup_values slv6\n" +
					"    on slv6.lookup_code = sbib.uom_code\n" +
					"    and slv6.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  left join srm_base_categories sbc\n" +
					"    on sbc.category_id = sbib.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv7\n" +
					"    ON slv7.lookup_code = spl.status\n" +
					"    AND slv7.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv8\n" +
					"    ON slv8.lookup_code = spl.feedback_status\n" +
					"    AND slv8.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv9\n" +
					"    ON slv9.lookup_code = spl.feedback_result\n" +
					"    AND slv9.lookup_type = 'ISP_PO_SUPPLIER_FEEDBACK'\n" +
					"  LEFT JOIN saaf_institution si3\n" +
					"    ON si3.inst_id = spl.receive_to_organization_id\n" +
					"    AND si3.inst_type = 'ORGANIZATION'\n" +
                    "  LEFT JOIN saaf_institution si4\n" +
                    "    ON sph.organization_id = si4.inst_id\n" +
					"  , saaf_institution si1\n" +
					"  , saaf_employees emp\n" +
					"  , srm_pos_supplier_info psi\n" +
					"WHERE sph.org_id = si1.inst_id\n" +
					"  AND sph.buyer_id = emp.employee_id\n" +
					"  AND sph.supplier_id = psi.supplier_id\n" +
					"  AND sph.po_doc_type = 'ORDER'\n";

	/**
	 * 供应商查询采购订单行
	 */
	public static String QUERY_ORDER_LINE_BY_SUPPLIER_SQL =
					"SELECT\n" +
					"  spl.po_header_id AS poHeaderId,\n" +
					"  spl.po_line_id AS poLineId,\n" +
                    "  spl.po_line_comb_id AS poLineCombId,\n" +
					"  spl.line_number AS lineNumber,\n" +
					"  spl.item_id AS itemId,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  spl.item_name AS itemName,\n" +
					"  spl.item_spec AS itemSpec,\n" +
					"  slv1.meaning AS uomCodeName,\n" +
					"  spl.demand_date AS demandDate,\n" +
					"  spl.demand_qty AS demandQty,\n" +
					"  spl.category_id AS categoryId,\n" +
					"  sbc.full_category_code AS categoryCode,\n" +
					"  sbc.full_category_name AS categoryName,\n" +
					"  spl.expense_item_code AS expenseItemCode,\n" +
					"  spl.description AS lineDescription,\n" +
					"  spl.status AS lineStatus,\n" +
					"  slv2.meaning AS lineStatusStr,\n" +
					"  spl.tax_price AS taxPrice,\n" +
					"  spl.non_tax_price AS nonTaxPrice,\n" +
					"  spl.receive_to_organization_id AS receiveToOrganizationId,\n" +
					"  si.inst_code AS receiveToOrganizationCode,\n" +
					"  si.inst_name AS receiveToOrganizationName,\n" +
					"  spl.may_notice_qty AS mayNoticeQty,\n" +
					"  spl.on_way_qty AS onWayQty,\n" +
					"  spl.received_qty AS receivedQty,\n" +
					"  spl.original_demand_qty AS originalDemandQty,\n" +
					"  spl.original_demand_date AS originalDemandDate,\n" +
					"  spl.feedback_adjust_date AS feedbackAdjustDate,\n" +
					"  spl.feedback_adjust_qty AS feedbackAdjustQty,\n" +
					"  spl.feedback_status AS feedbackStatus,\n" +
					"  slv3.meaning AS feedbackStatusStr,\n" +
					"  spl.feedback_result AS feedbackResult,\n" +
					"  slv4.meaning AS feedbackResultStr,\n" +
					"  spl.reject_reason AS rejectReason,\n" +
					"  spl.source_code AS sourceCode,\n" +
					"  spl.return_qty AS returnQty,\n" +
					"  spl.erp_po_number AS erpPoNumber,\n" +
					"  spl.context AS context,\n" +
					"  spl.project_category AS projectCategory,\n" +
					"  spl.project_type AS projectType,\n" +
					"  spl.technical_trans_number AS technicalTransNumber,\n" +
					"  spl.subproject_number AS subprojectNumber,\n" +
					"  spl.acceptance_process_number AS acceptanceProcessNumber,\n" +
					"  spl.tax_rate_code AS taxRateCode,\n" +
					"  spl.non_tax_total_price AS nonTaxTotalPrice,\n" +
					"  spl.tax_total_price AS taxTotalPrice,\n" +
					"  spl.non_tax_act_total_price AS nonTaxActTotalPrice,\n" +
					"  spl.tax_act_total_price AS taxActTotalPrice,\n" +
                    "  spl.contract_item_id AS contractItemId,\n" +
                    "  spl.contract_id AS contractId,\n" +
                    "  'Y' AS receiveIsDisabled\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    SUM(pnl.notice_delivery_qty)\n" +
					"  FROM\n" +
					"    srm_po_notice_line pnl\n" +
					"  WHERE pnl.po_line_id = spl.po_line_id) AS notifiedQty\n" +
					"FROM\n" +
					"  srm_po_lines spl\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON spl.item_id = sbi.item_id\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = sbi.uom_code\n" +
					"    AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = spl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_code = spl.status\n" +
					"    AND slv2.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = spl.feedback_status\n" +
					"    AND slv3.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values slv4\n" +
					"    ON slv4.lookup_code = spl.feedback_result\n" +
					"    AND slv4.lookup_type = 'ISP_PO_SUPPLIER_FEEDBACK'\n" +
					"  LEFT JOIN saaf_institution si\n" +
					"    ON si.inst_id = spl.receive_to_organization_id\n" +
					"WHERE 1 = 1\n";

	/**
	 * 汇总订单信息SQL
	 */
	public static String GET_PO_GETHER_ORDER_SQL =
					"SELECT e.meaning AS returnGoodsTypeStr\n" +
					"      ,f.inst_name AS receiveToOrganizationName\n" +
					"      ,b.demand_date AS demandDate\n" +
					"      ,d.item_code AS itemCode\n" +
					"      ,d.item_name AS itemName\n" +
					"      ,z.meaning AS uomCodeDesc\n" +
					"      ,SUM(b.demand_qty) AS demandQty\n" +
					"FROM   srm_po_headers a\n" +
					"LEFT   JOIN saaf_lookup_values e\n" +
					"ON     e.lookup_code = a.return_goods_type\n" +
					"AND    e.lookup_type = 'ISP_DELIVERY_TYPE', srm_po_lines b\n" +
					"LEFT   JOIN saaf_institution f\n" +
					"ON     f.inst_id = b.receive_to_organization_id, saaf_institution c,\n" +
					" srm_base_items_b d\n" +
					"LEFT   JOIN saaf_lookup_values z\n" +
					"ON     z.lookup_code = d.uom_code\n" +
					"AND    z.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE  a.po_header_id = b.po_header_id\n" +
					"AND    a.org_id = c.inst_id\n" +
					"AND    b.item_id = d.item_id\n" +
					"AND    a.status = 'APPROVED'\n" +
					"AND    b.status = 'OPEN'\n" +
					"AND    (b.feedback_status = 'CONFIRMED' OR b.feedback_status = 'APPROVED')\n";

	/**
	 * 供应商查询单头信息
	 */
	public static String QUERY_ORDER_HEADER_BY_SUPPLIER_SQL = "SELECT\n" +
			"  a.po_header_id            AS poHeaderId,\n" +
			"  a.po_number               AS poNumber,\n" +
			"  a.contract_id             AS contractId,\n" +
			"  a.contract_code           AS contractCode,\n" +
            "  soc.contract_name           AS contractName,\n" +
            "  soc.party_a_sign_date           AS contractDate,\n" +
			"  a.org_id                  AS orgId,\n" +
			"  c.inst_name               AS orgName,\n" +
			"  a.bill_to_organization_id AS billToOrganizationId,\n" +
			"  m.inst_name               AS billToOrganizationName,\n" +
			"  a.ship_to_organization_id AS shipToOrganizationId,\n" +
			"  m2.inst_name              AS shipToOrganizationName,\n" +
			"  a.creation_date           AS creationDate,\n" +
			"  a.buyer_id                AS employeeId,\n" +
			"  a.description             AS description,\n" +
			"  f.employee_name           AS employeeName,\n" +
			"  f2.employee_name          AS createdName,\n" +
			"  a.return_goods_type       AS returnGoodsType,\n" +
			"  j.meaning                 AS returnGoodsTypeStr,\n" +
			"  a.status                  AS STATUS,\n" +
			"  h.meaning                 AS statusStr,\n" +
			"  a.currency_code           AS currencyCode,\n" +
			"  s11.meaning           AS currencyCodeStr,\n" +
			"  s12.meaning           AS taxRateCodeStr,\n" +
			"  a.payment_condition  AS paymentTermCode,\n" +
			"  ppt.payment_term_id  AS paymentTermId,\n" +
			"  ppt.payment_term_name  AS paymentTermName,\n" +
			"  s14.meaning          AS settlementWayStr,\n" +
			"  a.tax_rate_code           AS taxRateCode,\n" +
			"  (SELECT SUM(w.demand_qty*w.tax_price) FROM srm_po_lines w WHERE w.po_header_id = a.po_header_id AND w.status <> 'CANCELLED') AS taxTotalPrice,\n" +
			"  (SELECT SUM(w.demand_qty*w.non_tax_price) FROM srm_po_lines w WHERE w.po_header_id = a.po_header_id AND w.status <> 'CANCELLED') AS NonTaxTotalPrice,\n" +
			"  (SELECT SUM(w.received_qty*w.tax_price) FROM srm_po_lines w WHERE w.po_header_id = a.po_header_id AND w.status <> 'CANCELLED') AS taxActTotalPrice,\n" +
			"  (SELECT SUM(w.received_qty*w.non_tax_price) FROM srm_po_lines w WHERE w.po_header_id = a.po_header_id AND w.status <> 'CANCELLED') AS NonTaxActTotalPrice,\n" +
			"  a.payment_condition       AS paymentCondition,\n" +
			"  a.settlement_way          AS settlementWay,\n" +
			"  a.supplier_id             AS supplierId,\n" +
			"  w.supplier_name           AS supplierName,\n" +
			"  a.supplier_site_id        AS supplierSiteId,\n" +
			"  q.site_name               AS siteName,\n" +
			"  a.approved_date           AS approvedDate,\n" +
			"  a.po_versions       AS poVersions,\n" +
			"  a.version_num       AS versionNum,\n" +
			"  a.po_file_id              AS commonFileId,\n" +
			"  a.pr_number                  AS prNumber,\n" +
			"  a.location_code              AS locationCode,\n" +
			"  a.bill_to_location_id           AS billToLocationId,\n" +
			"  sbl1.location_name              AS billToLocationCode,\n" +
			"  a.ship_to_location_id           AS shipToLocationId,\n" +
			"  sbl2.location_name              AS shipToLocationCode,\n" +
			"  a.po_type              AS poType,\n" +
			"  rf.access_Path commonFilePath,\n" +
			"  rf.file_Name commonFileName,\n" +
            "  a.organization_id organizationId,\n" +
            "  si1.inst_name     organizationName\n" +
            "  ,a.source_id     sourceId\n" +
            "  ,a.auction_number     auctionNumber\n" +
            "  ,a.logi_ship_to_location_code     logiShipToLocationCode\n" +
            "  ,a.logi_bill_to_location_code     logiBillToLocationCode\n" +
            "  ,slv1.meaning     logiShipToLocationName\n" +
            "  ,slv2.meaning     logiBillToLocationName\n" +
			"FROM srm_po_headers a\n" +
			"  LEFT JOIN saaf_institution c\n" +
			"    ON a.org_id = c.inst_id\n" +
			"      AND c.inst_type = 'ORG'\n" +
            "LEFT   JOIN saaf_institution si1\n" +
            "ON     si1.inst_id = a.organization_id\n" +
			"  LEFT JOIN srm_base_locations sbl1\n" +
			"    ON a.bill_to_location_id = sbl1.location_id\n" +
			"  LEFT JOIN srm_base_locations sbl2\n" +
			"    ON a.ship_to_location_id = sbl2.location_id\n" +
			"  LEFT JOIN saaf_employees f\n" +
			"    ON a.buyer_id = f.employee_id\n" +
			"  LEFT JOIN saaf_employees f2\n" +
			"    ON a.created_by = f2.user_id\n" +
			"    LEFT JOIN saaf_lookup_values h\n" +
			"    ON h.lookup_type = 'ISP_PO_STATUS'\n" +
			"      AND a.status = h.lookup_code\n" +
			"      LEFT JOIN saaf_lookup_values j  ON j.lookup_type = 'ISP_DELIVERY_TYPE' AND a.return_goods_type = j.lookup_code\n" +
			"      LEFT JOIN saaf_lookup_values s11  ON s11.lookup_type = 'BANK_CURRENCY' AND a.currency_code = s11.lookup_code\n" +
			"      LEFT JOIN saaf_lookup_values s12  ON s12.lookup_type = 'PON_TAX_LIST' AND a.tax_rate_code = s12.lookup_code\n" +
			"      LEFT JOIN srm_pon_payment_terms ppt ON a.payment_condition = ppt.payment_term_code AND ppt.payment_term_status = 'ACT'\n" +
			"      LEFT JOIN saaf_lookup_values s14  ON s14.lookup_type = 'PAYMENT_METHOD' AND a.settlement_way = s14.lookup_code\n" +
			"      LEFT JOIN saaf_institution m\n" +
			"    ON a.bill_to_organization_id = m.inst_id\n" +
			"      AND m.inst_type = 'ORGANIZATION'\n" +
			"      LEFT JOIN saaf_institution m2\n" +
			"    ON a.ship_to_organization_id = m2.inst_id\n" +
			"      AND m2.inst_type = 'ORGANIZATION'\n" +
			"      LEFT JOIN srm_pos_supplier_info w ON a.supplier_id = w.supplier_id\n" +
			"      LEFT JOIN saaf_base_result_file rf on a.po_file_id = rf.file_id\n" +
			"      LEFT JOIN srm_pos_supplier_sites q ON a.supplier_site_id = q.supplier_site_id\n" +
            "      LEFT JOIN saaf_lookup_values slv1  ON slv1.lookup_type = 'LOGISTICS_SZ_RECEIVER' AND a.logi_ship_to_location_code = slv1.lookup_code\n" +
            "      LEFT JOIN saaf_lookup_values slv2  ON slv2.lookup_type = 'LOGISTICS_SZ_RECEIVER' AND a.logi_bill_to_location_code = slv2.lookup_code\n" +
            "      LEFT JOIN srm_okc_contracts soc ON soc.contract_id = a.contract_id\n" +
			"WHERE \n" +
			" a.po_header_id = :poHeaderId";

	public static String QUERY_ORDER_HEADER_SQL = "SELECT\r\n" + 
			"	sph.po_header_id poHeaderId,\r\n" + 
			"	sph.po_number poNumber,\r\n" + 
			"	sph.po_doc_type poDocType,\r\n" +
			"	sph.supplier_id supplierId,\r\n" + 
			"	spsi.supplier_name supplierName,\r\n" + 
			"	spsi.supplier_number supplierNumber,\r\n" + 
			"	spsi.acct_check_type acctCheckType,\r\n" + 
			"	slv.meaning acctCheckTypeStr,\r\n" + 
			"	spsi.able_check_order_flag ableCheckOrderFlag,\r\n" + 
			"	#sph.contract_number contractNumber,\r\n" + 
			"	#sph.demand_date demandDate,\r\n" + 
			"	sph.buyer_id buyerId,\r\n" + 
			"	se.employee_name buyerName,\r\n" + 
			"	sph.currency_code currencyCode,\r\n" + 
			"	su.user_full_name approveBy,\r\n" + 
			"	sph.status status,\r\n" +
			"	#sph.overdue_flag overdueFlag,\r\n" + 
			"	#sph.pass_u9_flag passU9Flag,\r\n" + 
			"	#if(sph.pass_u9_flag='Y','是','否') passU9FlagStr,\r\n" + 
			"	sph.description description,\r\n" + 
			"	sph.creation_date creationDate\r\n" + 
			"FROM\r\n" + 
			"	srm_po_headers sph left join saaf_users su on sph.approval_user_id=su.user_id,\r\n" + 
			"	saaf_employees se,\r\n" + 
			"	srm_pos_supplier_info spsi LEFT JOIN saaf_lookup_values slv ON slv.lookup_type='POS_ACCT_CHECK_TYPE' and spsi.acct_check_type = slv.lookup_code\r\n" + 
			"WHERE\r\n" + 
			"	sph.buyer_id = se.employee_id\r\n" + 
			"AND sph.supplier_id = spsi.supplier_id";

	public static String QUERY_PRINT_ORDER_HEADER_SQL =
					"SELECT a.buyerName\n" +
					"      ,a.buyerPhone\n" +
					"      ,a.creationDate\n" +
					"      ,a.description\n" +
					"      ,a.poNumber\n" +
					"      ,a.supplierName\n" +
					"      ,a.supplierNumber\n" +
					"      ,a.contractNumber\n" +
					"      ,a.address\n" +
					"      ,spsc.contact_name contactName\n" +
					"      ,spsc.mobile_phone mobilePhone\n" +
					"      ,spsc.fax_phone    faxPhone\n" +
					"FROM   (SELECT spsi.supplier_name supplierName\n" +
					"              ,spsi.supplier_number supplierNumber\n" +
					"              ,spsi.address address\n" +
					"              ,se.employee_name buyerName\n" +
					"              ,se.mobile_phone buyerPhone\n" +
					"              ,sph.po_number poNumber\n" +
					"              ,sph.po_header_id\n" +
					"              ,sph.contract_code contractNumber\n" +
					"              ,sph.description description\n" +
					"              ,sph.creation_date creationDate\n" +
					"              ,(SELECT MIN(b.supplier_contact_id)\n" +
					"                FROM   srm_pos_supplier_contacts b\n" +
					"                WHERE  b.supplier_id = spsi.supplier_id) AS supplier_contact_id\n" +
					"        FROM   srm_po_headers        sph\n" +
					"              ,srm_pos_supplier_info spsi\n" +
					"              ,saaf_employees        se\n" +
					"        WHERE  sph.supplier_id = spsi.supplier_id\n" +
					"        AND    sph.buyer_id = se.employee_id) a\n" +
					"LEFT   JOIN srm_pos_supplier_contacts spsc\n" +
					"ON     spsc.supplier_contact_id = a.supplier_contact_id\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_ORDER_LINE_SQL = "SELECT\n" + "\tspl.po_header_id poHeaderId,\n"
			+ "\tspl.po_line_id poLineId,\n" + "\tspl.line_number lineNumber,\n" + "\tspl.inst_id instId,\n"
			+ "\tsi2.inst_name instName,\n" + "\tspl.item_id itemId,\n" + "\tspl.price price,\n"
			+ "\tspl.demand_qty demandQty,\n" + "\tspl.status status,\n" + "\tslv2.meaning statusStr,\n"
			+ "\tspl.special_use_num specialUseNum,\n" + "\tspl.demand_classify demandClassify,\n"
			+ "\tget_po_qty_f (spl.po_line_id,'CREATE_ACT_NOTICE')  noticeQty,\n"
			+ "\tget_po_qty_f (spl.po_line_id,'CREATE_ACT_DELIVERY') deliveryOrderQty,\n"
			+ "\tspl.delivery_qty deliveryQty,\n" + "\tspl.demand_date demandDate,\n"
			+ "\tspl.affirm_status affirmStatus,\n" + "\tslv1.meaning affirmStatusStr,\n"
			+ "\tspl.reject_reason rejectReason,\n" + "\tspl.description lineDescription,\n"
			+ "\tsbi.item_code itemCode,\n" + "\tsbi.item_name itemName,\n" + "\tsbi.item_prop itemProp,\n"
			+ "\tsbi.item_prop_desc itemPropDesc,\n" + "\tsbi.category_code categoryCode,\n"
			+ "\tsbi.unit_of_measure_name unit,\n" + "\tspl.delivery_type deliveryType,\n"
			+ "\tslv3.meaning deliveryTypeStr,\n" + "\tsbc.category_name categoryName\n" + "FROM\n"
			+ "\tsrm_po_lines spl left join saaf_lookup_values slv3 on slv3.lookup_type ='DELIVERY_TYPE' AND spl.delivery_type = slv3.lookup_code\n"
			+ "\tleft join saaf_lookup_values slv1 on slv1.lookup_type ='PO_AFFIRM_STATUS' AND spl.affirm_status = slv1.lookup_code,\n"
			+ "\tsrm_base_items sbi left join srm_base_categories sbc on sbi.category_code = sbc.category_code,\n"
			+ "\tsaaf_institution si2,\n" + "\tsaaf_lookup_values slv2\n" + "WHERE\n" + "\tspl.item_id = sbi.item_id\n"
			+ "AND spl.inst_id = si2.inst_id\n" + "AND slv2.lookup_type = 'PO_LINE_STATUS'\n"
			+ "AND spl.status = slv2.lookup_code ";

	public static String QUERY_INSITITUTION_LIST_SQL = "SELECT si.inst_code instCode, si.inst_id instId, si.inst_name instName FROM saaf_institution si WHERE 1 = 1 ";

	private Integer instId;
	private String instName;
	private String instCode;

	public static String QUERY_MAX_LINE_NUMBER_SQL = "SELECT MAX(line_number) lineNumber FROM srm_po_lines WHERE po_header_id =:poHeaderId ";

	public static String QUERY_DELIVERY_LIST_SQL =
					"SELECT p.creationDate\n" +
					"      ,p.docNumber\n" +
					"      ,p.docType\n" +
					"      ,p.quantity\n" +
					"      ,p.status\n" +
					"      ,su.user_full_name createdBy\n" +
					"FROM   (SELECT spnl.creation_date creationDate\n" +
					"              ,slv.meaning status\n" +
					"              ,spnl.created_by createdBy\n" +
					"              ,spn.po_notice_code docNumber\n" +
					"              ,spnl.notice_delivery_qty quantity\n" +
					"              ,spl.po_line_id poLineId\n" +
					"              ,'送货通知' docType\n" +
					"        FROM   srm_po_notice_line spnl\n" +
					"              ,srm_po_notice      spn\n" +
					"              ,srm_po_lines       spl\n" +
					"              ,saaf_lookup_values slv\n" +
					"        WHERE  spl.po_line_id = spnl.po_line_id\n" +
					"        AND    spl.po_header_id = spnl.po_header_id\n" +
					"        AND    spnl.po_notice_id = spn.po_notice_id\n" +
					"        AND    slv.lookup_type = 'PO_NOTICE_STATUS'\n" +
					"        AND    slv.lookup_code = spn.status\n" +
					"        UNION ALL\n" +
					"        SELECT spdl.creation_date creationDate\n" +
					"              ,slv1.meaning status\n" +
					"              ,spdl.created_by createdBy\n" +
					"              ,spdh.delivery_number docNumber\n" +
					"              ,spdl.delivery_order_qty quantity\n" +
					"              ,spl.po_line_id poLineId\n" +
					"              ,'送货单' docType\n" +
					"        FROM   srm_po_delivery_lines   spdl\n" +
					"              ,srm_po_delivery_headers spdh\n" +
					"              ,srm_po_lines            spl\n" +
					"              ,saaf_lookup_values      slv1\n" +
					"        WHERE  spl.po_line_id = spdl.po_line_id\n" +
					"        AND    spdl.delivery_header_id = spdh.delivery_header_id\n" +
					"        AND    slv1.lookup_type = 'PO_DELIVERY_HEADER_STATUS'\n" +
					"        AND    slv1.lookup_code = spdh.status) p\n" +
					"LEFT   JOIN saaf_users su\n" +
					"ON     su.user_id = p.createdby\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_ORDER_LOV_SQL = "SELECT sph.po_header_id poHeaderId, sph.po_number poNumber FROM srm_po_headers sph WHERE 1 = 1 ";

	public static String QUERY_SUPPLIER_LOV_SQL =
					"SELECT spsi.supplier_id           supplierId\n" +
					"      ,spsi.supplier_name         supplierName\n" +
					"      ,spsi.supplier_number       supplierNumber\n" +
					"      ,spsi.supplier_status       supplierStatus\n" +
					"      ,spsi.acct_check_type       acctCheckType\n" +
					"      ,spsi.able_check_order_flag ableCheckOrderFlag\n" +
					"      ,slv.meaning                acctCheckTypeStr\n" +
					"      ,slv1.meaning               supplierStatusStr\n" +
					"      ,spsb.bank_currency         currencyCode\n" +
					"FROM   srm_pos_supplier_info spsi\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     slv1.lookup_type = 'POS_SUPPLIER_STATUS'\n" +
					"AND    spsi.supplier_status = slv1.lookup_code\n" +
					"LEFT   JOIN srm_pos_supplier_bank spsb\n" +
					"ON     spsi.supplier_id = spsb.supplier_id\n" +
					"LEFT   JOIN saaf_lookup_values slv\n" +
					"ON     slv.lookup_type = 'POS_ACCT_CHECK_TYPE'\n" +
					"AND    spsi.acct_check_type = slv.lookup_code\n" +
					"WHERE  1 = 1\n";

	public static String QUERY_CATEGORY_LOV_SQL = "SELECT sbc.category_id categoryId\r\n" + 
			",sbc.category_code categoryCode\r\n" + 
			",sbc.category_name categoryName\r\n" + 
			",sbc.full_category_code fullCategoryCode\r\n" + 
			",sbc.full_category_name fullCategoryName\r\n" + 
			"FROM srm_base_categories sbc \r\n" + 
			"WHERE 1 = 1";

	public static String QUERY_ITEM_LOV_SQL =
					"SELECT sbi.item_id            itemId\n" +
					"      ,sbi.item_code          itemCode\n" +
					"      ,sbi.item_name          itemName\n" +
					"      ,sbi.item_status        itemStatus\n" +
					"      ,sbi.specification      specification\n" +
					"      ,sbc.category_id        categoryId\n" +
					"      ,sbc.category_code      categoryCode\n" +
					"      ,sbc.category_name      categoryName\n" +
					"      ,sbc.full_category_code fullCategoryCode\n" +
					"      ,sbc.full_category_name fullCategoryName\n" +
					"      ,sbc.expense_item_code  expenseItemCode\n" +
					"      ,slv1.meaning           uomCodeDesc\n" +
					"      ,sbi.uom_code           unit\n" +
                    "      ,sbi.cost           cost\n" +
					"FROM   srm_base_items sbi\n" +
					"LEFT   JOIN srm_base_categories sbc\n" +
					"ON     sbi.category_id = sbc.category_id\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     sbi.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE  sbi.item_status = 'ACT'\n";

	// 查询供应商能供的物料lov
	public static String QUERY_SUPPLIER_ITEM_LOV_SQL = "SELECT\n" + "\tspal.item_id,\n" + "\tsbi.item_code,\n"
			+ "\tsbi.item_name,\n" + "\tsbi.item_status itemStatus,\n" + "\tsbi.category_code categoryCode,\n"
			+ "\tsbc.category_name categoryName,\n" + "\tsbi.delivery_type deliveryType,\n"
			+ "\tsbi.unit_of_measure_name unit,\n" + "\tspal.supplier_id supplierId,\n"
			+ "\tspsi.acct_check_type acctCheckType,\n" + "\tslv.meaning acctCheckTypeStr,\n"
			+ "\tspsb.bank_currency currencyCode,\n" + "\tspsi.able_check_order_flag ableCheckOrderFlag,\n"
			+ "\tspsi.supplier_status supplierStatus,\n" + "\tslv1.meaning supplierStatusStr,\n"
			+ "\tspsi.supplier_name supplierName,\n" + "\tspsi.supplier_number supplierNumber\n" + "FROM\n"
			+ "\tsrm_po_approved_list spal,\n"
			+ "\tsrm_pos_supplier_info spsi LEFT JOIN saaf_lookup_values slv ON slv.lookup_type='POS_ACCT_CHECK_TYPE' and spsi.acct_check_type = slv.lookup_code\n"
			+ "\tLEFT JOIN srm_pos_supplier_bank spsb ON spsi.supplier_id = spsb.supplier_id\n"
			+ "\tLEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_SUPPLIER_INFO_STATUS' and spsi.supplier_status = slv1.lookup_code,\n"
			+ "\tsrm_base_items sbi\n" + "LEFT JOIN srm_base_categories sbc ON sbi.category_code = sbc.category_code\n"
			+ "WHERE\n" + "\tspal.enabled_flag = 'Y'\n" + "AND spal.item_id = sbi.item_id\n"
			+ "AND spal.supplier_id = spsi.supplier_id ";

	public static String QUERY_SUPPLIER_ITEM_LOV_COUNT = "SELECT\n" + "\tcount(1)\n" + "FROM\n"
			+ "\tsrm_po_approved_list spal,\n"
			+ "\tsrm_pos_supplier_info spsi LEFT JOIN saaf_lookup_values slv ON slv.lookup_type='POS_ACCT_CHECK_TYPE' and spsi.acct_check_type = slv.lookup_code\n"
			+ "\tLEFT JOIN saaf_lookup_values slv1 ON slv1.lookup_type = 'POS_SUPPLIER_INFO_STATUS' and spsi.supplier_status = slv1.lookup_code,\n"
			+ "\tsrm_base_items sbi\n" + "LEFT JOIN srm_base_categories sbc ON sbi.category_code = sbc.category_code\n"
			+ "WHERE\n" + "\tspal.enabled_flag = 'Y'\n" + "AND spal.item_id = sbi.item_id\n"
			+ "AND spal.supplier_id = spsi.supplier_id ";

	// 送货通知匹配采购订单
	public static final String QUERY_ORDER_JOIN_SQL = "SELECT\n" + "\th.po_header_id poHeaderId,\n"
			+ "\th.CREATION_DATE creationDate,\n" + "\th.po_number poNumber,\n" + "\tsi.supplier_name supplierName,\n"
			+ "\tl.demand_qty demandQty,\n" + "\th.demand_date demandDate,\n" + "\tl.SPECIAL_USE_NUM specialUseNum,\n"
			+ "\tl.delivery_qty deliveryQty,\n" + "\tl.po_line_id poLineId,\n" + "\tget_po_qty_f (\n"
			+ "\t\tl.po_line_id,\n" + "\t\t'CAN_CREATE_NOTICE'\n" + "\t) matchingNumber,\n" + "\tget_po_qty_f (\n"
			+ "\t\tl.po_line_id,\n" + "\t\t'CREATE_ACT_NOTICE'\n" + "\t) noticeQty\n" + "FROM\n"
			+ "\tsrm_po_headers AS h\n" + "LEFT JOIN srm_po_lines AS l ON l.po_header_id = h.po_header_id\n"
			+ "LEFT JOIN srm_pos_supplier_info AS si ON si.supplier_id = h.supplier_id\n"
			+ "LEFT JOIN saaf_institution AS i ON i.inst_id = l.inst_id\n"
			+ "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = l.item_id\n" + "WHERE\n" + "\t0 <(\n"
			+ "\tl.demand_qty - ifnull(l.delivery_qty,0)-\n"
			+ "  (select  ifnull(SUM(l2.MATCH_QUANTITY-ifnull(l2.delivery_qty,0)) ,0)\n"
			+ "   from  SRM_PO_NOTICE_LINE l2,srm_po_notice n2\n" + "   where l2.PO_LINE_ID = l.po_line_id \n"
			+ "   and  l2.po_notice_id = n2.po_notice_id\n"
			+ "   and  n2.status IN ('CREATED','CONFIRMED','REJECTED'))\n" + ")\n" + "AND l.DELIVERY_TYPE = '2'\n"
			+ "AND l.status = 'APPROVED' ";

	// 查询需求列表
	public static String QUERY_NEED_LIST_SQL = "SELECT\n" + "        sppd.plan_demand_id planDemandId,\n"
			+ "        sppd.plan_demand_num planDemandNum,\n" + "        slv1.tag planType,\n"
			+ "        sppdl.line_id lineId,\n" + "        sppd.supply_inst_id supplyInstId,\n"
			+ "        si2.inst_name supplyInstName,\n" + "        sppd.inst_id instId,\n"
			+ "        si.inst_name instName,\n" + "        sppd.item_id itemId,\n"
			+ "        sppd.demand_classify demandClassify,\n" + "        sppd.need_quantity needQuantity,\n"
			+ "        sppd.need_by_date needByDate,\n" + "        sppd.employee_number employeeNumber,\n"
			+ "        sppd.supplier_number appointSupplierNumber,\n" + "        sppd.special_use_num specialUseNum,\n"
			+ "        sppdl.day_capacity dayCapacity,\n" + "        sppdl.month_proportion monthProportion,\n"
			+ "        sppdl.proportion proportion,\n" + "        get_plan_qty_f(sppdl.line_id) poQuantity,\n"
			+ "        sppdl.distribute_quantity distributeQuantity,\n" + "        sppdl.supplier_id supplierId,\n"
			+ "        sppdl.supplier_id oldSupplierId,\n" + "        sbi.item_code itemCode,\n"
			+ "        sbi.item_name itemName,\n" + "        sbi.unit_of_measure_name unit,\n"
			+ "        slv2.meaning deliveryTypeStr,\n" + "        sbi.delivery_type deliveryType,\n"
			+ "        sbc.category_name categoryName,\n" + "        t.supplier_name supplierName,\n"
			+ "        t.acct_check_type acctCheckType,\n" + "        t.supplier_status supplierStatus,\n"
			+ "        t.meaning1 supplierStatusStr,\n" + "        t.meaning2 acctCheckTypeStr,\n"
			+ "        t.able_check_order_flag ableCheckOrderFlag,\n" + "        spsb.bank_currency currencyCode,\n"
			+ "        spsi1.supplier_name appointSupplierName,\n" + "        se.employee_name employeeName,\n"
			+ "        se.employee_id buyerId,\n" + "        sbi.purchasing_lead_time purchasePeriod,\n"
			+ "        sppd.advise_order_date adviseOrderDate,\n"
			+ "        if (get_profile_value ('ORDER_LEAD_TIME') = 'N','Y',if (curdate() >= date_sub(date_sub(sppd.need_by_date,INTERVAL sbi.purchasing_lead_time DAY),INTERVAL sbi.order_lead_time DAY),'Y','N')) isOrderDateFlag,\n"
			+ "        date_sub(sppd.advise_order_date,interval sbi.order_lead_time day) aheadOrderDate,\n"
			+ "        if(sbi.delivery_type is null,'N',if(sppdl.supplier_id is null or t.supplier_status !='QUALIFIED' or (ifnull((select 1 from srm_po_approved_list a where a.item_id=sppd.item_id and a.supplier_id=sppdl.supplier_id and a.enabled_flag = 'Y'),0)<1),'N',if(get_profile_value('ORDER_LEAD_TIME')='N','Y',if(curdate()>=date_sub(date_sub(sppd.need_by_date, interval sbi.purchasing_lead_time day),interval sbi.order_lead_time day),'Y','N')))) canOrderFlag,\n"
			+ "        if(datediff(sppd.advise_order_date,curdate())<0,datediff(sppd.advise_order_date,curdate()),null) cutDay\n"
			+ "        FROM\n"
			+ "        srm_po_plan_demands sppd left join srm_pos_supplier_info spsi1 on sppd.supplier_number = spsi1.supplier_number\n"
			+ "        left join saaf_employees se on sppd.employee_number = se.employee_number\n"
			+ "        left join saaf_lookup_values slv1 on slv1.lookup_type = 'PO_PLAN_TYPE' AND sppd.plan_type = slv1.lookup_code,\n"
			+ "        srm_po_plan_demand_line sppdl left join srm_pos_supplier_bank spsb on spsb.supplier_id=sppdl.supplier_id\n"
			+ "        left join (SELECT\n" + "\tslv.meaning meaning1,\n" + "\tspsi.supplier_id,\n"
			+ "\tspsi.supplier_name,\n" + "\tspsi.acct_check_type,\n" + "\tspsi.supplier_status,\n"
			+ "\tspsi.able_check_order_flag,\n" + "\tslv1.meaning meaning2\n" + "FROM\n"
			+ "\tsrm_pos_supplier_info spsi,\n" + "\tsaaf_lookup_values slv,\n" + "\tsaaf_lookup_values slv1\n"
			+ "WHERE\n" + "\tslv.lookup_type = 'POS_SUPPLIER_INFO_STATUS'\n"
			+ "AND slv.lookup_code = spsi.supplier_status\n" + "AND slv1.lookup_type = 'POS_ACCT_CHECK_TYPE'\n"
			+ "AND slv1.lookup_code = spsi.acct_check_type) t on sppdl.supplier_id = t.supplier_id,\n"
			+ "        srm_base_items sbi left join saaf_lookup_values slv2 on slv2.lookup_type = 'DELIVERY_TYPE' AND slv2.lookup_code = sbi.delivery_type\n"
			+ "        left join srm_base_categories sbc on sbi.category_code = sbc.category_code,\n"
			+ "        saaf_institution si,\n" + "        saaf_institution si2\n" + "        WHERE\n"
			+ "        sppd.plan_demand_id = sppdl.plan_demand_id\n" + "        AND sppd.item_id = sbi.item_id\n"
			+ "        AND si.inst_id = sppd.inst_id\n" + "        AND si2.inst_id = sppd.supply_inst_id\n"
			+ "        AND sppdl.distribute_quantity > (\n" + "\tSELECT\n" + "\t\tifnull(\n" + "\t\t\tsum(\n" + "\n"
			+ "\t\t\t\tIF (\n" + "\t\t\t\t\tspl. STATUS = 'SHORTAGE_CLOSED'\n"
			+ "\t\t\t\t\tOR spl. STATUS = 'NATURAL_CLOSED',\n" + "\t\t\t\t\tspl.delivery_qty,\n"
			+ "\t\t\t\t\tspl.demand_qty\n" + "\t\t\t\t)\n" + "\t\t\t),0\n" + "\t\t)\n" + "\tFROM\n"
			+ "\t\tsrm_po_lines spl\n" + "\tWHERE\n" + "\t\tspl.status NOT IN (\"REJECT\")\n"
			+ "\tAND spl.plan_demand_line_id = sppdl.line_id)\n";

	// 查询需求总条数
	public static String QUERY_NEED_LIST_COUNT_SQL = "SELECT count(1)\n" + "        FROM\n"
			+ "        srm_po_plan_demands sppd left join srm_pos_supplier_info spsi1 on sppd.supplier_number = spsi1.supplier_number\n"
			+ "        left join saaf_employees se on sppd.employee_number = se.employee_number\n"
			+ "        left join saaf_lookup_values slv1 on slv1.lookup_type = 'PO_PLAN_TYPE' AND sppd.plan_type = slv1.lookup_code,\n"
			+ "        srm_po_plan_demand_line sppdl left join srm_pos_supplier_bank spsb on spsb.supplier_id=sppdl.supplier_id\n"
			+ "        left join (SELECT\n" + "\tslv.meaning meaning1,\n" + "\tspsi.supplier_id,\n"
			+ "\tspsi.supplier_name,\n" + "\tspsi.acct_check_type,\n" + "\tspsi.supplier_status,\n"
			+ "\tspsi.able_check_order_flag,\n" + "\tslv1.meaning meaning2\n" + "FROM\n"
			+ "\tsrm_pos_supplier_info spsi,\n" + "\tsaaf_lookup_values slv,\n" + "\tsaaf_lookup_values slv1\n"
			+ "WHERE\n" + "\tslv.lookup_type = 'POS_SUPPLIER_INFO_STATUS'\n"
			+ "AND slv.lookup_code = spsi.supplier_status\n" + "AND slv1.lookup_type = 'POS_ACCT_CHECK_TYPE'\n"
			+ "AND slv1.lookup_code = spsi.acct_check_type) t on sppdl.supplier_id = t.supplier_id,\n"
			+ "        srm_base_items sbi left join saaf_lookup_values slv2 on slv2.lookup_type = 'DELIVERY_TYPE' AND slv2.lookup_code = sbi.delivery_type\n"
			+ "        left join srm_base_categories sbc on sbi.category_code = sbc.category_code,\n"
			+ "        saaf_institution si,\n" + "        saaf_institution si2\n" + "        WHERE\n"
			+ "        sppd.plan_demand_id = sppdl.plan_demand_id\n" + "        AND sppd.item_id = sbi.item_id\n"
			+ "        AND si.inst_id = sppd.inst_id\n" + "        AND si2.inst_id = sppd.supply_inst_id\n"
			+ "        AND sppdl.distribute_quantity > (\n" + "\tSELECT\n" + "\t\tifnull(\n" + "\t\t\tsum(\n" + "\n"
			+ "\t\t\t\tIF (\n" + "\t\t\t\t\tspl. STATUS = 'SHORTAGE_CLOSED'\n"
			+ "\t\t\t\t\tOR spl. STATUS = 'NATURAL_CLOSED',\n" + "\t\t\t\t\tspl.delivery_qty,\n"
			+ "\t\t\t\t\tspl.demand_qty\n" + "\t\t\t\t)\n" + "\t\t\t),0\n" + "\t\t)\n" + "\tFROM\n"
			+ "\t\tsrm_po_lines spl\n" + "\tWHERE\n" + "\t\tspl.status NOT IN (\"REJECT\")\n"
			+ "\tAND spl.plan_demand_line_id = sppdl.line_id)\n";

	public static String QUERY_MONTH_ORDER_SQL = "SELECT\n"
			+ "\tSUM(spl.demand_qty) - SUM(IFNULL(spl.delivery_qty,0)) demandQty,\n" + "\tspl.demand_date demandDate,\n"
			+ "\tspl.item_id itemId,\n" + "\ta.itemCode,\n" + "\ta.itemName,\n" + "\ta.categoryCode,\n"
			+ "\ta.categoryName,\n" + "\tspl.inst_id instId,\n" + "\tsi.inst_name instName,\n"
			+ "\tsph.supplier_id supplierId,\n" + "\tspsi.supplier_number supplierNumber,\n"
			+ "\tspsi.supplier_name supplierName\n" + "FROM\n" + "\tsrm_po_headers sph\n"
			+ "LEFT JOIN srm_pos_supplier_info spsi ON sph.supplier_id = spsi.supplier_id,\n" + " srm_po_lines spl\n"
			+ "LEFT JOIN saaf_institution si on si.inst_id=spl.inst_id\n" + "LEFT JOIN (\n" + "\tSELECT\n"
			+ "\t\tsbi.item_code itemCode,\n" + "\t\tsbi.item_id itemId,\n" + "\t\tsbi.item_name itemName,\n"
			+ "\t\tsbi.category_code categoryCode,\n" + "\t\tsbc.category_name categoryName\n" + "\tFROM\n"
			+ "\t\tsrm_base_items sbi\n"
			+ "\tLEFT JOIN srm_base_categories sbc ON sbc.category_code = sbi.category_code\n"
			+ ") a ON a.itemId = spl.item_id\n" + "WHERE\n" + "\tsph.po_header_id = spl.po_header_id\n"
			+ "AND sph.status = 'APPROVED'\n" + "AND spl.status != 'SHORTAGE_CLOSED'\n"
			+ "AND (spl.demand_qty - IFNULL(spl.delivery_qty,0))>0 ";

	//采购框架协议查询
	public static String QUERY_PO_FRAMEWORK_AGREEMEN_SQL =
					"SELECT\n" +
					"  ph.po_header_id poHeaderId,\n" +
					"  si1.inst_name orgName,\n" +
					"  ph.po_number poNumber,\n" +
					"  ph.creation_date creationDate,\n" +
					"  ph.po_versions poVersions,\n" +
					"  ph.start_date startDate,\n" +
					"  ph.end_date endDate,\n" +
					"  psi.supplier_name supplierName,\n" +
					"  emp1.employee_name employeeName,\n" +
					"  ph.status status,\n" +
					"  slv1.meaning statusStr,\n" +
					"  s1v2.meaning currencyCode,\n" +
					"  s1v3.meaning taxRateCode,\n" +
					"  ppt.payment_term_name paymentCondition,\n" +
					"  s1v4.meaning settlementWay,\n" +
					"  ph.description description,\n" +
					"  emp2.employee_name approvalUserName,\n" +
					"  ph.approved_date approvedDate,\n" +
					"  pl.status lineStatus\n" +
					"FROM\n" +
					"  srm_po_headers ph\n" +
					"  LEFT JOIN saaf_lookup_values slv1\n" +
					"    ON slv1.lookup_code = ph.status\n" +
					"    AND slv1.lookup_type = 'ISP_PO_STATUS'\n" +
					"  LEFT JOIN saaf_lookup_values s1v2\n" +
					"    ON s1v2.lookup_code = ph.currency_code\n" +
					"    AND s1v2.lookup_type = 'BANK_CURRENCY'\n" +
					"  LEFT JOIN saaf_lookup_values s1v3\n" +
					"    ON s1v3.lookup_code = ph.tax_rate_code\n" +
					"    AND s1v3.lookup_type = 'PON_TAX_LIST'\n" +
					"  LEFT JOIN srm_pon_payment_terms ppt\n" +
					"    ON ppt.payment_term_code = ph.payment_condition\n" +
					"  LEFT JOIN saaf_lookup_values s1v4\n" +
					"    ON s1v4.lookup_code = ph.settlement_way\n" +
					"    AND s1v4.lookup_type = 'PAYMENT_METHOD'\n" +
					"  LEFT JOIN saaf_users su\n" +
					"    ON su.user_id = ph.approval_user_id\n" +
					"  LEFT JOIN saaf_employees emp2\n" +
					"    ON emp2.employee_id = su.employee_id,\n" +
					"  srm_po_lines pl\n" +
					"  LEFT JOIN srm_base_items_b sbi\n" +
					"    ON sbi.item_id = pl.item_id,\n" +
					"  saaf_institution si1,\n" +
					"  srm_pos_supplier_info psi,\n" +
					"  saaf_employees emp1\n" +
					"WHERE ph.po_header_id = pl.po_header_id\n" +
					"  AND ph.org_id = si1.inst_id\n" +
					"  AND si1.inst_type = 'ORG'\n" +
					"  AND ph.supplier_id = psi.supplier_id\n" +
					"  AND ph.buyer_id = emp1.employee_id\n" +
					"  AND ph.po_doc_type = 'AGREEMENT'";

	//采购框架协议查询（历史信息）
	public static String QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_SQL = "SELECT \n" +
			"  ph.po_archive_id poArchiveId,\n" +
			"  ph.po_header_id poHeaderId,\n" +
			"  si.inst_name orgName,\n" +
			"  ph.po_number poNumber,\n" +
			"  ph.creation_date creationDate,\n" +
			"  ph.po_versions poVersions,\n" +
			"  ph.start_date startDate,\n" +
			"  ph.end_date endDate,\n" +
			"  spsi.supplier_name supplierName,\n" +
			"  se.employee_name employeeName,\n" +
			"  paa.receive_to_organization_id receiveToOrganizationId,\n" +
			"  si2.inst_name receiveToOrganizationName,\n" +
			"  paa.bill_to_organization_id billToOrganizationId,\n" +
			"  si1.inst_name billToOrganizationName,\n" +
			"  ph.status status,\n" +
			"  slv.meaning statusStr,\n" +
			"  pl.status lineStatus,\n" +
			"  s1v1.meaning currencyCode,\n" +
			"  s1v2.meaning taxRateCode,\n" +
			"  ppt.payment_term_name paymentCondition,\n" +
			"  s1v4.meaning settlementWay,\n" +
			"  ph.description description,\n" +
			"  se1.employee_name approvalUserName,\n" +
			"  ph.approved_date approvedDate\n" +
			"FROM\n" +
			"  srm_po_header_archives ph \n" +
			"  LEFT JOIN srm_po_lines pl ON ph.po_header_id = pl.po_header_id\n" +
			"  LEFT JOIN srm_po_agreement_assigns paa ON ph.po_header_id = paa.po_header_id\n"+
			"  LEFT JOIN saaf_institution si ON ph.org_id = si.inst_id AND si.inst_type = 'ORG'\n" +
			"  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
			"  LEFT JOIN saaf_employees se ON ph.buyer_id = se.employee_id\n" +
			"  LEFT JOIN saaf_institution si1 ON paa.bill_to_organization_id = si1.inst_id AND si1.inst_type = 'ORG'\n" +
			"  LEFT JOIN saaf_lookup_values slv ON ph.status = slv.lookup_code AND slv.lookup_type = 'ISP_PO_STATUS' \n" +
			"  LEFT JOIN saaf_lookup_values s1v1 ON ph.currency_code = s1v1.lookup_code AND s1v1.lookup_type = 'BANK_CURRENCY'\n" +
			"  LEFT JOIN saaf_lookup_values s1v2 ON ph.tax_rate_code = s1v2.lookup_code AND s1v2.lookup_type = 'PON_TAX_LIST'\n" +
			"  LEFT JOIN srm_pon_payment_terms ppt ON ph.payment_condition = ppt.payment_term_code\n" +
			"  LEFT JOIN saaf_lookup_values s1v4 ON ph.settlement_way = s1v4.lookup_code AND s1v4.lookup_type = 'PAYMENT_METHOD'\n" +
			"  LEFT JOIN saaf_employees se1 ON ph.approval_user_id = se1.employee_id\n" +
			"  LEFT JOIN srm_base_items_b sbi ON pl.item_id = sbi.item_id\n" +
			"  LEFT JOIN saaf_institution si2 ON paa.receive_to_organization_id = si2.inst_id AND si2.inst_type = 'ORGANIZATION'\n" +
			"WHERE ph.po_doc_type = 'AGREEMENT'\r\n";

	//查询采购框架协议头信息
	public static String QUERY_PO_FRAMEWORK_AGREEMEN_HEADER_SQL = "SELECT\n" +
			"  ph.po_header_id poHeaderId,\n" +
			"  ph.po_number poNumber,\n" +
			"  ph.org_id orgId,\n" +
			"  si.inst_name orgName,\n" +
			"  ph.supplier_id supplierId,\n" +
			"  spsi.supplier_name supplierName,\n" +
			"  ph.creation_date creationDate,\n" +
			"  ph.start_date startDate,\n" +
			"  ph.end_date endDate,\n" +
			"  ph.po_versions poVersions,\n" +
			"  ph.buyer_id employeeId,\n" +
			"  se.employee_name employeeName,\n" +
			"  ph.status status,\n" +
			"  slv.meaning statusStr,\n" +
			"  s1v1.meaning currencyCodeStr,\n" +
			"  ph.currency_code currencyCode,\n" +
			"  s1v2.meaning taxRateCodeStr,\n" +
			"  ph.tax_rate_code taxRateCode,\n" +
			"  ppt.payment_term_name paymentTermName,\n" +
			"  ph.payment_condition paymentCondition,\n" +
			"  s1v4.meaning settlementWayStr,\n" +
			"  ph.settlement_way settlementWay,\n" +
			"  ph.approved_date approvedDate,\n" +
			"  se1.employee_name approvalUserName,\n" +
			"  rf.access_Path commonFilePath,\n" +
			"  rf.file_Name commonFileName,\n" +
			"	ph.source_code sourceCode,\n" +
			"	ph.source_id sourceId,\n" +
			"  ph.po_file_id commonFileId,\n" +
			"  ph.description description,\n" +
			"  ph.agreement_clause agreementClause,\n" +
			"  ph.is_global isGlobal\n" +
			"FROM\n" +
			"  srm_po_headers ph \n" +
			"  LEFT JOIN saaf_institution si ON ph.org_id = si.inst_id AND si.inst_type = 'ORG'\n" +
			"  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
			"  LEFT JOIN saaf_employees se ON ph.buyer_id = se.employee_id\n" +
			"  LEFT JOIN saaf_lookup_values slv ON ph.status = slv.lookup_code AND slv.lookup_type = 'ISP_PO_STATUS' \n" +
			"  LEFT JOIN saaf_lookup_values s1v1 ON ph.currency_code = s1v1.lookup_code AND s1v1.lookup_type = 'BANK_CURRENCY'\n" +
			"  LEFT JOIN saaf_lookup_values s1v2 ON ph.tax_rate_code = s1v2.lookup_code AND s1v2.lookup_type = 'PON_TAX_LIST'\n" +
			"  LEFT JOIN srm_pon_payment_terms ppt ON ph.payment_condition = ppt.payment_term_code\n" +
			"  LEFT JOIN saaf_lookup_values s1v4 ON ph.settlement_way = s1v4.lookup_code AND s1v4.lookup_type = 'PAYMENT_METHOD'\n" +
			"  LEFT JOIN saaf_employees se1 ON ph.approval_user_id = se1.employee_id\n" +
			"  LEFT JOIN saaf_base_result_file rf on ph.po_file_id = rf.file_id\n" +
			"WHERE ph.po_doc_type = 'AGREEMENT' AND ph.po_header_id = :poHeaderId\r\n";

		//查询历史的采购框架协议头信息
		public static String QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_HEADER_SQL = "SELECT\n" +
				"  ph.po_header_id poHeaderId,\n" +
				"  ph.po_number poNumber,\n" +
				"  ph.org_id orgId,\n" +
				"  si.inst_name orgName,\n" +
				"  ph.supplier_id supplierId,\n" +
				"  spsi.supplier_name supplierName,\n" +
				"  ph.creation_date creationDate,\n" +
				"  ph.start_date startDate,\n" +
				"  ph.end_date endDate,\n" +
				"  ph.po_versions poVersions,\n" +
				"  ph.buyer_id employeeId,\n" +
				"  se.employee_name employeeName,\n" +
				"  ph.status status,\n" +
				"  slv.meaning statusStr,\n" +
				"  s1v1.meaning currencyCodeStr,\n" +
				"  ph.currency_code currencyCode,\n" +
				"  s1v2.meaning taxRateCodeStr,\n" +
				"  ph.tax_rate_code taxRateCode,\n" +
				"  ppt.payment_term_name paymentTermName,\n" +
				"  ph.payment_condition paymentCondition,\n" +
				"  s1v4.meaning settlementWayStr,\n" +
				"  ph.settlement_way settlementWay,\n" +
				"  ph.approved_date approvedDate,\n" +
				"  se1.employee_name approvalUserName,\n" +
				"  rf.access_Path commonFilePath,\n" +
				"  rf.file_Name commonFileName,\n" +
				"	ph.source_code sourceCode,\n" +
				"	ph.source_id sourceId,\n" +
				"  ph.po_file_id commonFileId,\n" +
				"  ph.description description,\n" +
				"  ph.agreement_clause agreementClause,\n" +
				"  ph.is_global isGlobal\n" +
				"FROM\n" +
				"  srm_po_header_archives ph \n" +
				"  LEFT JOIN saaf_institution si ON ph.org_id = si.inst_id AND si.inst_type = 'ORG'\n" +
				"  LEFT JOIN srm_pos_supplier_info spsi ON ph.supplier_id = spsi.supplier_id\n" +
				"  LEFT JOIN saaf_employees se ON ph.buyer_id = se.employee_id\n" +
				"  LEFT JOIN saaf_lookup_values slv ON ph.status = slv.lookup_code AND slv.lookup_type = 'ISP_PO_STATUS' \n" +
				"  LEFT JOIN saaf_lookup_values s1v1 ON ph.currency_code = s1v1.lookup_code AND s1v1.lookup_type = 'BANK_CURRENCY'\n" +
				"  LEFT JOIN saaf_lookup_values s1v2 ON ph.tax_rate_code = s1v2.lookup_code AND s1v2.lookup_type = 'PON_TAX_LIST'\n" +
				"  LEFT JOIN srm_pon_payment_terms ppt ON ph.payment_condition = ppt.payment_term_code\n" +
				"  LEFT JOIN saaf_lookup_values s1v4 ON ph.settlement_way = s1v4.lookup_code AND s1v4.lookup_type = 'PAYMENT_METHOD'\n" +
				"  LEFT JOIN saaf_employees se1 ON ph.approval_user_id = se1.employee_id\n" +
				"  LEFT JOIN saaf_base_result_file rf on ph.po_file_id = rf.file_id\n" +
				"WHERE ph.po_doc_type = 'AGREEMENT' AND ph.po_header_id = :poHeaderId\r\n";

	//查询采购框架协议行物料信息
	public static String QUERY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL = "SELECT distinct pl.line_number lineNumber,\n" +
			"  pl.po_line_id poLineId,\n" +
			"  pl.po_header_id poHeaderId,\n" +
			/*"  pl.line_number lineNumber,\n" +*/
			"  pl.item_id itemId,\n" +
			"  bi.item_code itemCode,\n" +
			"  pl.item_name itemName,\n" +
			"  pl.item_spec itemSpec,\n" +
			"  pl.category_id categoryId,\n" +
			"  bc.category_code categoryCode,\n" +
			"  bc.category_name categoryName,\n" +
			"  pl.demand_qty demandQty,\n" +
			"  slv.meaning uomCodeDesc,\n" +
			"  pl.min_po_qty minPoQty,\n" +
			"  pl.demand_date demandDate,\n" +
			"  pl.ladder_price_flag ladderPriceFlag,\n" +
			"  pl.ladder_qty ladderQty,\n" +
			"  pl.non_tax_price nonTaxPrice,\n" +
			"  pl.tax_price taxPrice,\n" +
			"  pl.accumulative_flag accumulativeFlag,\n" +
			"  pl.start_date startDate,\n" +
			"  pl.end_date endDate,\n" +
			"  pl.description description,\n" +
			"  pl.source_number sourceNumber,\n" +
			"  pl.source_code sourceCode,\n" +
			"  pl.source_id sourceid,\n" +
			"  pl.status STATUS,\n" +
			"  slv1.meaning lineStatusStr\n" +
			"FROM\n" +
			"  srm_po_lines pl\n" +
			"  LEFT JOIN srm_base_items bi ON pl.item_id = bi.item_id AND EXISTS\n" +
			"  (SELECT\n" +
			"      1\n" +
			"    FROM\n" +
			"      saaf_institution si,\n" +
			"      srm_po_headers ph\n" +
			"    WHERE si.inst_id = bi.organization_id\n" +
			"      AND si.inst_type = 'ORGANIZATION'\n" +
			"      AND si.parent_inst_id = ph.org_id\n" +
			"      AND pl.po_header_id = ph.po_header_id\n" +
			"  )\n" +
			"  LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = bi.uom_code AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
			"  LEFT JOIN srm_base_categories bc ON pl.category_id = bc.category_id\n" +
			"  LEFT JOIN saaf_lookup_values slv1 ON pl.status = slv1.lookup_code AND slv1.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
			" WHERE 1=1 \r\n";

	//查询历史的采购框架协议行物料信息
		public static String QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL = "SELECT distinct pl.line_number lineNumber,\n" +
				"  pl.po_line_id poLineId,\n" +
				"  pl.po_header_id poHeaderId,\n" +
				/*"  pl.line_number lineNumber,\n" +*/
				"  pl.item_id itemId,\n" +
				"  bi.item_code itemCode,\n" +
				"  pl.item_name itemName,\n" +
				"  pl.item_spec itemSpec,\n" +
				"  pl.category_id categoryId,\n" +
				"  bc.category_code categoryCode,\n" +
				"  bc.category_name categoryName,\n" +
				"  pl.demand_qty demandQty,\n" +
				"  slv.meaning uomCodeDesc,\n" +
				"  pl.min_po_qty minPoQty,\n" +
				"  pl.demand_date demandDate,\n" +
				"  pl.ladder_price_flag ladderPriceFlag,\n" +
				"  pl.ladder_qty ladderQty,\n" +
				"  pl.non_tax_price nonTaxPrice,\n" +
				"  pl.tax_price taxPrice,\n" +
				"  pl.accumulative_flag accumulativeFlag,\n" +
				"  pl.start_date startDate,\n" +
				"  pl.end_date endDate,\n" +
				"  pl.description description,\n" +
				"  pl.source_number sourceNumber,\n" +
				"  pl.source_code sourceCode,\n" +
				"  pl.source_id sourceid,\n" +
				"  pl.status STATUS,\n" +
				"  slv1.meaning lineStatusStr\n" +
				"FROM\n" +
				"  srm_po_line_archives pl\n" +
				"  LEFT JOIN srm_base_items bi ON pl.item_id = bi.item_id AND EXISTS\n" +
				"  (SELECT\n" +
				"      1\n" +
				"    FROM\n" +
				"      saaf_institution si,\n" +
				"      srm_po_headers ph\n" +
				"    WHERE si.inst_id = bi.organization_id\n" +
				"      AND si.inst_type = 'ORGANIZATION'\n" +
				"      AND si.parent_inst_id = ph.org_id\n" +
				"      AND pl.po_header_id = ph.po_header_id\n" +
				"  )\n" +
				"  LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = bi.uom_code AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
				"  LEFT JOIN srm_base_categories bc ON pl.category_id = bc.category_id\n" +
				"  LEFT JOIN saaf_lookup_values slv1 ON pl.status = slv1.lookup_code AND slv1.lookup_type = 'ISP_PO_LINE_STATUS'\n" +
				" WHERE 1=1 \r\n";

	public static String QUERY_PO_FRAMEWORK_AGREEMEN_LINE_ITEM_INFO_SQL_OLD = "SELECT\n" +
			"  pl.po_line_id poLineId,\n" +
			"  pl.po_header_id poHeaderId,\n" +
			"  pl.line_number lineNumber,\n" +
			"  pl.item_id itemId,\n" +
			"  tb1.item_code itemCode,\n" +
			"  pl.item_name itemName,\n" +
			"  pl.item_spec itemSpec,\n" +
			"  pl.category_id categoryId,\n" +
			"  bc.category_code categoryCode,\n" +
			"  bc.category_name categoryName,\n" +
			"  pl.demand_qty demandQty,\n" +
			"  tb1.meaning uomCodeDesc,\n" +
			"  pl.min_po_qty minPoQty,\n" +
			"  pl.demand_date demandDate,\n" +
			"  pl.ladder_price_flag ladderPriceFlag,\n" +
			"  pl.ladder_qty ladderQty,\n" +
			"  pl.non_tax_price nonTaxPrice,\n" +
			"  pl.tax_price taxPrice,\n" +
			"  pl.accumulative_flag accumulativeFlag,\n" +
			"  pl.start_date startDate,\n" +
			"  pl.end_date endDate,\n" +
			"  pl.description description,\n" +
			"  pl.source_number sourceNumber,\n" +
			"  pl.status status,\n" +
			"  slv1.meaning lineStatusStr\n" +
			"FROM\n" +
			"  srm_po_lines pl\n" +
			"  LEFT JOIN (SELECT bi.item_id,bi.item_code,bi.item_name,bi.organization_id,slv.meaning FROM srm_base_items bi LEFT JOIN saaf_lookup_values slv ON bi.uom_code = slv.lookup_code AND slv.lookup_type = 'BASE_ITEMS_UNIT') tb1\n" +
			"  ON pl.item_id = tb1.item_id\n" +
			"  LEFT JOIN srm_base_categories bc ON pl.category_id = bc.category_id\n" +
			"  LEFT JOIN saaf_lookup_values slv1 ON pl.status = slv1.lookup_code AND slv1.lookup_type = 'ISP_PO_LINE_STATUS'" +
			"WHERE 1=1 \r\n";

	/**
	 * 查询历史的应用组织信息
	 */
	public static String QUERY_HISTORY_PO_FRAMEWORK_AGREEMEN_LINE_APPLIED_ORGANIZATION_SQL = "SELECT\n" +
			"  pa.agreement_assign_id agreementAssignId,\n" +
			"  pa.po_header_id poHeaderId,\n" +
			"  pa.org_id orgId,\n" +
			"  si.inst_name orgName,\n" +
			"  pa.receive_to_organization_id receiveToOrganizationId,\n"+
			"  si2.inst_name receiveToOrganizationName,\n" +
			"  pa.bill_to_organization_id billToOrganizationId,\n"+
			"  si1.inst_name billToOrganizationName,\n" +
			"  pa.default_flag defaultFlag\n" +
			"FROM\n" +
			"  srm_po_agreement_assign_archives pa\n" +
			"  LEFT JOIN saaf_institution si ON pa.org_id = si.inst_id AND si.inst_type = 'ORG'\n" +
			"  LEFT JOIN saaf_institution si2 ON pa.receive_to_organization_id = si2.inst_id AND si2.inst_type = 'ORGANIZATION'\n" +
			"  LEFT JOIN saaf_institution si1 ON pa.bill_to_organization_id = si1.inst_id AND si1.inst_type = 'ORG'\n" +
			"WHERE 1=1\r\n";
	
	/**
	 * 查询应用组织信息
	 */
	public static String QUERY_PO_FRAMEWORK_AGREEMEN_LINE_APPLIED_ORGANIZATION_SQL = "SELECT\n" +
			"  pa.agreement_assign_id agreementAssignId,\n" +
			"  pa.po_header_id poHeaderId,\n" +
			"  pa.org_id orgId,\n" +
			"  si.inst_name orgName,\n" +
			"  pa.receive_to_organization_id receiveToOrganizationId,\n"+
			"  si2.inst_name receiveToOrganizationName,\n" +
			"  pa.bill_to_organization_id billToOrganizationId,\n"+
			"  si1.inst_name billToOrganizationName,\n" +
			"  pa.default_flag defaultFlag\n" +
			"FROM\n" +
			"  srm_po_agreement_assigns pa\n" +
			"  LEFT JOIN saaf_institution si ON pa.org_id = si.inst_id AND si.inst_type = 'ORG'\n" +
			"  LEFT JOIN saaf_institution si2 ON pa.receive_to_organization_id = si2.inst_id AND si2.inst_type = 'ORGANIZATION'\n" +
			"  LEFT JOIN saaf_institution si1 ON pa.bill_to_organization_id = si1.inst_id AND si1.inst_type = 'ORG'\n" +
			"WHERE 1=1\r\n";
	
	/**
	 * 查询采购订单物料(物料来源于货源表)
	 */
	public static String QUERY_ITEMS_BY_APPROVED_LIST="select bi.item_id itemId\r\n" + 
			",bi.item_code itemCode\r\n" + 
			",bi.item_name itemName\r\n" + 
			",bi.item_status itemStatus\r\n" + 
			",bc.category_id categoryId\r\n" + 
			",bc.category_code categoryCode\r\n" + 
			",bc.category_name categoryName\r\n" + 
			",slv.meaning uomCodeName\r\n" + 
			"from \r\n" + 
			"srm_po_approved_list al \r\n" + 
			"left join srm_base_items bi on al.item_id = bi.item_id\r\n" + 
			"left join srm_base_categories bc on bi.category_id = bc.category_id\r\n" + 
			"left join saaf_lookup_values slv on slv.lookup_type = 'BASE_ITEMS_UNIT'\r\n" + 
			"and slv.lookup_code = bi.uom_code\r\n" + 
			"where 1=1 and al.disabled_flag = 'N'\r\n" + 
			"and al.list_status in('NEW','APPROVED')\r\n";
	
	/**
	 * 查询采购订单物料LOV(物料来源于物料表)
	 */
	public static String QUERY_ITEMS_BY_BASE_ITEMS="select bi.item_id itemId\r\n" + 
			",bi.item_code itemCode\r\n" + 
			",bi.item_name itemName\r\n" + 
			",bi.item_status itemStatus\r\n" + 
			",bc.category_id categoryId\r\n" + 
			",bc.category_code categoryCode\r\n" + 
			",bc.category_name categoryName\r\n" + 
			",slv.meaning uomCodeName\r\n" + 
			"from \r\n" + 
			"srm_base_items bi \r\n" + 
			"left join srm_base_categories bc on bi.category_id = bc.category_id\r\n" + 
			"left join saaf_lookup_values slv on slv.lookup_type = 'BASE_ITEMS_UNIT'\r\n" + 
			"and slv.lookup_code = bi.uom_code\r\n" + 
			"where 1=1 \r\n" + 
			"and bi.item_status='ACT'";

	public static String QUERY_BASE_ASL_ITEM_SQL =
					"SELECT\n" +
					"  t.list_id AS id,\n" +
					"  sbc.category_id AS category_id,\n" +
					"  t.item_id,\n" +
					"  '' AS TYPE,\n" +
					"  t.list_status AS STATUS,\n" +
					"  sbc.category_code AS categoryCode,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  slv.meaning AS itemStatusName,\n" +
					"  slv2.meaning AS uomCodeName,\n" +
					"  sbi.item_name AS itemName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbi.item_description AS itemDescription\n" +
					"FROM\n" +
					"  srm_po_approved_list t,\n" +
					"  srm_base_items_b sbi\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = sbi.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"    AND slv.lookup_code = sbi.item_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv2.lookup_code = sbi.uom_code\n" +
					"WHERE t.item_id = sbi.item_id\n" +
					"  AND t.supplier_id = :varSupplierId\n" +
					"  AND sbi.item_status = 'ACT'";

	public static String QUERY_BASE_ASL_RETURN_ITEM_SQL =
					"SELECT DISTINCT\n" +
					"  a.itemCode,\n" +
					"  a.category_id,\n" +
					"  a.item_id,\n" +
					"  a.uomCodeName,\n" +
					"  a.categoryCode,\n" +
					"  a.categoryName,\n" +
					"  a.fullCategoryCode,\n" +
					"  a.fullCategoryName,\n" +
					"  a.itemStatusName,\n" +
					"  a.itemName,\n" +
					"  a.itemDescription\n" +
					"FROM\n" +
					"  (SELECT\n" +
					"    t.list_id AS id,\n" +
					"    sbc.category_id AS category_id,\n" +
					"    t.item_id,\n" +
					"    '' AS TYPE,\n" +
					"    t.list_status AS STATUS,\n" +
					"    sbc.category_code AS categoryCode,\n" +
					"    sbc.category_name AS categoryName,\n" +
					"    sbc.full_category_code AS fullCategoryCode,\n" +
					"    sbc.full_category_name AS fullCategoryName,\n" +
					"    slv.meaning AS itemStatusName,\n" +
					"    slv2.meaning AS uomCodeName,\n" +
					"    sbi.item_name AS itemName,\n" +
					"    sbi.item_code AS itemCode,\n" +
					"    sbi.item_description AS itemDescription\n" +
					"  FROM\n" +
					"    srm_po_approved_list t,\n" +
					"    srm_base_items_b sbi\n" +
					"    LEFT JOIN srm_base_categories sbc\n" +
					"      ON sbc.category_id = sbi.category_id\n" +
					"    LEFT JOIN saaf_lookup_values slv\n" +
					"      ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"      AND slv.lookup_code = sbi.item_status\n" +
					"    LEFT JOIN saaf_lookup_values slv2\n" +
					"      ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"      AND slv2.lookup_code = sbi.uom_code\n" +
					"  WHERE t.item_id = sbi.item_id\n" +
					"    AND t.supplier_id = :varSupplierId\n" +
					"    AND sbi.item_status = 'ACT'\n" +
					"    AND t.list_status IN ('NEW', 'APPROVED')\n" +
					"    AND t.disabled_flag = 'N'\n" +
					"  UNION\n" +
					"  ALL\n" +
					"  SELECT\n" +
					"    t.item_deliver_id AS id,\n" +
					"    t.category_id,\n" +
					"    t.item_id,\n" +
					"    t.deliver_type AS TYPE,\n" +
					"    t.deliver_status AS STATUS,\n" +
					"    sbc.category_code AS categoryCode,\n" +
					"    sbc.category_name AS categoryName,\n" +
					"    sbc.full_category_code AS fullCategoryCode,\n" +
					"    sbc.full_category_name AS fullCategoryName,\n" +
					"    slv.meaning AS itemStatusName,\n" +
					"    slv2.meaning AS uomCodeName,\n" +
					"    sbi.item_name AS itemName,\n" +
					"    sbi.item_code AS itemCode,\n" +
					"    sbi.item_description AS itemDescription\n" +
					"  FROM\n" +
					"    srm_base_item_delivers t,\n" +
					"    srm_base_items_b sbi\n" +
					"    LEFT JOIN srm_base_categories sbc\n" +
					"      ON sbc.category_id = sbi.category_id\n" +
					"    LEFT JOIN saaf_lookup_values slv\n" +
					"      ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"      AND slv.lookup_code = sbi.item_status\n" +
					"    LEFT JOIN saaf_lookup_values slv2\n" +
					"      ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"      AND slv2.lookup_code = sbi.uom_code\n" +
					"  WHERE t.item_id = sbi.item_id\n" +
					"    AND t.category_id = sbi.category_id\n" +
					"    AND sbi.item_status = 'ACT'\n" +
					"    AND t.deliver_status = 'ACT') a\n" +
					"WHERE 1 = 1";

	public static String QUERY_BASE_RETURN_ITEM_SQL = "SELECT\n" +
					"  t.item_deliver_id,\n" +
					"  t.category_id,\n" +
					"  t.item_id,\n" +
					"  t.deliver_type,\n" +
					"  t.deliver_status,\n" +
					"  sbc.category_code AS categoryCode,\n" +
					"  sbc.category_name AS categoryName,\n" +
					"  sbc.full_category_code AS fullCategoryCode,\n" +
					"  sbc.full_category_name AS fullCategoryName,\n" +
					"  slv.meaning AS itemStatusName,\n" +
					"  slv2.meaning AS uomCodeName,\n" +
					"  sbi.item_name AS itemName,\n" +
					"  sbi.item_code AS itemCode,\n" +
					"  sbi.item_description AS itemDescription\n" +
					"FROM\n" +
					"  srm_base_item_delivers t,\n" +
					"  srm_base_items_b sbi\n" +
					"  LEFT JOIN srm_base_categories sbc\n" +
					"    ON sbc.category_id = sbi.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_type = 'BASE_DATA_STATUS'\n" +
					"    AND slv.lookup_code = sbi.item_status\n" +
					"  LEFT JOIN saaf_lookup_values slv2\n" +
					"    ON slv2.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"    AND slv2.lookup_code = sbi.uom_code\n" +
					"WHERE t.item_id = sbi.item_id\n" +
					"  AND t.category_id = sbi.category_id\n" +
					"  AND sbi.item_status = 'ACT'";

	public static String CONTRACT_PO_REL = "select poh.po_header_id as poHeaderId ,poh.po_number as poNumber ,s1.meaning as statusStr ,poh.creation_date as creationDate,\n"+
			"   (SELECT SUM(w.tax_price) FROM srm_po_lines w WHERE w.po_header_id = poh.po_header_id) AS taxTotalPrice, b.employee_name as createdName,pt.payment_term_name  as paymentCondition from srm_po_headers poh\n"+
			"   LEFT JOIN saaf_employees b ON poh.created_by = b.employee_id\n"+
			"  LEFT JOIN  srm_pon_payment_terms pt on poh.payment_condition = pt.payment_term_code\n"+
			"  LEFT JOIN saaf_lookup_values s1 ON poh.status = s1.lookup_code AND s1.lookup_type = 'ISP_PO_STATUS'\n"+
			"  where poh.contract_id = :contractId";

	public static String QUERY_PO_CONVERTED_QTY =
					"SELECT\n" +
					"  sph.po_header_id\n" +
					"  , sph.po_number\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    nvl(SUM(pnl.notice_delivery_qty), 0)\n" +
					"  FROM\n" +
					"    srm_po_lines spl\n" +
					"    , srm_po_notice_line pnl\n" +
					"    , srm_po_notice pnh\n" +
					"  WHERE spl.po_line_id = pnl.po_line_id\n" +
					"    AND pnl.po_notice_id = pnh.po_notice_id\n" +
					"    AND pnh.po_notice_status <> 'CANCEL'\n" +
					"    AND spl.po_header_id = sph.po_header_id) notifiedQty\n" +
					"  ,\n" +
					"  (SELECT\n" +
					"    nvl(SUM(pdl.delivery_qty), 0)\n" +
					"  FROM\n" +
					"    srm_po_lines spl\n" +
					"    , srm_po_delivery_lines pdl\n" +
					"  WHERE spl.po_line_id = pdl.po_line_id\n" +
					"    AND pdl.delivery_line_status <> 'CANCELLED'\n" +
					"    AND spl.po_header_id = sph.po_header_id) deliveryQty\n" +
					"FROM\n" +
					"  srm_po_headers sph\n" +
					"WHERE sph.po_header_id = :poHeaderId";

	public static String QUERY_AGREEMENT_PRICE_LIBRARY_LIST_SQL =
					"select t.price_library_id,\n" +
							"       t.price_library_number,\n" +
							"       t.org_id,\n" +
							"       si.inst_name AS orgName,\n" +
                            "       t.organization_Id AS organizationId,\n" +
                            "       si1.inst_name AS organizationName,\n" +
							"       t.supplier_id,\n" +
							"       spsi.supplier_number,\n" +
							"       spsi.supplier_name,\n" +
                            "       spsi.supplier_type,\n" +
							"       t.buyer_id,\n" +
							"       se.employee_name        buyer,\n" +
							"       t.item_id,\n" +
							"       t.item_name,\n" +
							"       slv1.meaning            uomCodeName,\n" +
							"       t.item_spec,\n" +
							"       t.category_id,\n" +
							"       sbc.full_category_code  categoryCode,\n" +
							"       sbc.full_category_name  categoryName,\n" +
							"       slv2.meaning            taxRate,\n" +
							"       slv3.meaning            currencyCode,\n" +
							"       sppt.payment_term_name  payment_condition,\n" +
							"       t.tax_price,\n" +
							"       t.non_tax_price,\n" +
							"       slv4.meaning            price_library_status,\n" +
							"       t.price_effective_date  startDate,\n" +
							"       t.price_expiration_date endDate,\n" +
							"       t.remarks,\n" +
							"       t.price_library_version,\n" +
							"       t.source_number         auctionNumber,\n" +
							"       t.source_code,\n" +
							"       t.source_id,\n" +
                            "       sbib.item_code,\n" +
							"       t.creation_date,\n" +
                            "       t.artificial_price,\n" +
                            "       t.materials_price,\n" +
                            "       slv5.meaning   supplierTypeName,\n" +
                            "       sbib.specification   specification,\n" +
							"       t.tax_rate_code   taxRateCode\n" +
							"  from srm_pon_price_library t\n" +
							"  left join srm_base_items_b sbib\n" +
							"    on t.item_id = sbib.item_id\n" +
							"  left join srm_base_categories sbc\n" +
							"    on t.category_id = sbc.category_id\n" +
							"  left join saaf_lookup_values slv1\n" +
							"    on sbib.uom_code = slv1.lookup_code\n" +
							"   and slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
							"  left join saaf_lookup_values slv2\n" +
							"    on t.tax_rate_code = slv2.lookup_code\n" +
							"   and slv2.lookup_type = 'PON_TAX_LIST'\n" +
							"  left join saaf_lookup_values slv3\n" +
							"    on t.currency_code = slv3.lookup_code\n" +
							"   and slv3.lookup_type = 'BANK_CURRENCY'\n" +
							"  left join saaf_lookup_values slv4\n" +
							"    on t.price_library_status = slv4.lookup_code\n" +
							"   and slv4.lookup_type = 'PON_OFFER_STATUS'\n" +
							"  left join srm_pon_payment_terms sppt\n" +
							"    on sppt.payment_term_code = t.payment_condition\n" +
							"  left join saaf_employees se\n" +
							"    on t.buyer_id = se.user_id\n" +
							"  left join srm_pos_supplier_info spsi\n" +
							"    on t.supplier_id = spsi.supplier_id\n" +
                            "  left join saaf_lookup_values slv5\n" +
                            "    on spsi.supplier_type = slv5.lookup_code\n" +
                            "   and slv5.lookup_type = 'POS_SUPPLIER_TYPE'\n" +
							"LEFT JOIN saaf_institution si ON si.inst_id = t.org_id\n" +
                            "LEFT JOIN saaf_institution si1 ON si1.inst_id = t.organization_Id\n" +
							" where 1 = 1\n";


	public static String QUERY_AGREEMENT_PRICE_LIBRARY_LIST_EXTEND =
					" AND Pl.Po_Line_Id IN (SELECT Pl.Po_Line_Id\n" +
					"                        FROM Srm_Po_Headers Ph\n" +
					"                            ,Srm_Po_Lines Pl\n" +
					"                            ,(SELECT MAX(Pl.Start_Date) Start_Date\n" +
					"                                    ,Ph.Org_Id\n" +
					"                                    ,Pl.Item_Id\n" +
					"                                FROM Srm_Po_Headers Ph\n" +
					"                                    ,Srm_Po_Lines   Pl\n" +
					"                               WHERE Ph.Po_Doc_Type = 'AGREEMENT'\n" +
					"                                 AND Ph.Source_Code = 'srm_pon_bid_headers'\n" +
					"                                 AND Ph.Po_Header_Id = Pl.Po_Header_Id\n" +
					"                                 AND (ph.org_id = :orgId OR :orgId IS NULL)\n" +
					"                                 AND (ph.supplier_id = :supplierId OR :supplierId IS NULL)\n" +
					"                                 AND (ph.buyer_id = :buyerId OR :buyerId IS NULL)\n" +
					"                                 AND (ph.item_id = :itemId OR :itemId IS NULL)\n" +
					"                                 AND (ph.category_id = :categoryId OR :categoryId IS NULL)\n" +
					"                               GROUP BY Ph.Org_Id\n" +
					"                                       ,Pl.Item_Id) t\n" +
					"                       WHERE Ph.Po_Doc_Type = 'AGREEMENT'\n" +
					"                         AND Ph.Source_Code = 'srm_pon_bid_headers'\n" +
					"                         AND Ph.Po_Header_Id = Pl.Po_Header_Id\n" +
					"                         AND Ph.Org_Id = t.Org_Id\n" +
					"                         AND Pl.Item_Id = t.Item_Id\n" +
					"                         AND Pl.Start_Date = t.Start_Date\n" +
					"                         AND (ph.org_id = :orgId OR :orgId IS NULL)\n" +
					"                         AND (ph.supplier_id = :supplierId OR :supplierId IS NULL)\n" +
					"                         AND (ph.buyer_id = :buyerId OR :buyerId IS NULL)\n" +
					"                         AND (ph.item_id = :itemId OR :itemId IS NULL)\n" +
					"                         AND (ph.category_id = :categoryId OR :categoryId IS NULL))\n";

	public static String QUERY_AGREEMENT_PRICE_LIBRARY_LIST_EXTEND1 =
					"                              SELECT MAX(Pl.Start_Date) Start_Date\n" +
					"                                    ,Ph.Org_Id\n" +
					"                                    ,Pl.Item_Id\n" +
					"                                FROM Srm_Po_Headers Ph\n" +
					"                                    ,Srm_Po_Lines   Pl\n" +
					"                               WHERE Ph.Po_Doc_Type = 'AGREEMENT'\n" +
					"                                 AND Ph.Source_Code = 'srm_pon_bid_headers'\n" +
					"                                 AND Ph.Po_Header_Id = Pl.Po_Header_Id";

	public static String QUERY_AGREEMENT_PRICE_LIBRARY_LIST_EXTEND2 =
					"                      SELECT Pl.Po_Line_Id\n" +
					"                        FROM Srm_Po_Headers Ph\n" +
					"                            ,Srm_Po_Lines Pl\n" +
					"                            ,(QUERY_AGREEMENT_PRICE_LIBRARY_LIST_EXTEND1) t\n" +
					"                       WHERE Ph.Po_Doc_Type = 'AGREEMENT'\n" +
					"                         AND Ph.Source_Code = 'srm_pon_bid_headers'\n" +
					"                         AND Ph.Po_Header_Id = Pl.Po_Header_Id\n" +
					"                         AND Ph.Org_Id = t.Org_Id\n" +
					"                         AND Pl.Item_Id = t.Item_Id\n" +
					"                         AND Pl.Start_Date = t.Start_Date";

	public static String QUERY_PO_VERSIONS_SQL =
			"SELECT poVersions\n" +
			"      ,origin\n" +
			"  FROM (SELECT Pha.Po_Versions AS poVersions\n" +
			"              ,'ARCHIVES' AS origin\n" +
			"          FROM Srm_Po_Header_Archives Pha\n" +
			"         WHERE Pha.Po_Header_Id = :poHeaderId\n" +
			"        UNION ALL\n" +
			"        SELECT Ph.Po_Versions AS poVersions\n" +
			"              ,'PO' AS origin\n" +
			"          FROM Srm_Po_Headers Ph\n" +
			"         WHERE Ph.Po_Header_Id = :poHeaderId)\n" +
			" ORDER BY poVersions\n";


    public static String QUERY_PRICE_LIBRARY_REPORT ="SELECT t.Category_Id Category_Id\n" +
            "      ,Sbc.Full_Category_Code Categorycode\n" +
            "      ,Sbc.Full_Category_Name Categoryname\n" +
            "      ,AVG(t.Tax_Price) Tax_Price\n" +
            "      ,AVG(t.Non_Tax_Price) Non_Tax_Price\n" +
            "      ,Trunc(t.Creation_Date) Creation_Date\n" +
            "  FROM Srm_Pon_Price_Library t\n" +
            "  LEFT JOIN Srm_Base_Items_b Sbib\n" +
            "    ON t.Item_Id = Sbib.Item_Id\n" +
            "  LEFT JOIN Srm_Base_Categories Sbc\n" +
            "    ON t.Category_Id = Sbc.Category_Id\n" +
            " WHERE  1=1";

    public static String QUERY_COMB_TOEBS ="SELECT Splc.Po_Line_Comb_Id\n" +
            "      ,Inst1.Inst_Code              Org_Code\n" +
            "      ,Inst2.Inst_Code              Organization_Code\n" +
            "      ,Splc.Erp_Po_Number\n" +
            "      ,Splc.Line_Number\n" +
            "      ,Splc.Item_Code\n" +
            "      ,Splc.tax_total_price\n" +
            "      ,Splc.Non_Tax_Act_Total_Price\n" +
            "      ,Sbl.Location_Code            Ship_To_Location_Code\n" +
            "  FROM Srm_Po_Lines_Comb Splc\n" +
            "  LEFT JOIN Srm_Po_Headers Sph\n" +
            "    ON Sph.Po_Header_Id = Splc.Po_Header_Id\n" +
            "  LEFT JOIN Saaf_Institution Inst1\n" +
            "    ON Inst1.Inst_Id = Sph.Org_Id\n" +
            "  LEFT JOIN Saaf_Institution Inst2\n" +
            "    ON Inst2.Inst_Id = Sph.Organization_Id\n" +
            "  LEFT JOIN Srm_Base_Locations Sbl\n" +
            "    ON Sph.Ship_To_Location_Id = Sbl.Location_Id\n" +
            " WHERE 1 = 1 ";

    public static String QUERY_LINE_TOEBS ="SELECT Spl.Po_Line_Id\n" +
            "      ,Spl.Po_Header_Id\n" +
            "      ,Spl.Po_Line_Comb_Id\n" +
            "      ,Spl.Non_Tax_Act_Total_Price\n" +
            "      ,Spl.Tax_Act_Total_Price\n" +
            "      ,Spl.Status\n" +
            "  FROM Srm_Po_Lines Spl\n" +
            " WHERE 1 = 1 ";

    public static String QUERY_RECEIVED_QTY_BY_CONTRACT ="select spl.contract_item_id, SUM(spl.received_qty) sum_received_qty\n" +
            "  from srm_po_lines spl\n" +
            " where spl.contract_id = :contractId\n" +
            " and spl.status <> 'CANCELLED'\n" +
            " group by spl.contract_item_id ";


    public static String QUERY_DEMAND_QTY_BY_CONTRACT ="select spl.contract_item_id, SUM(spl.demand_qty) sum_demand_qty\n" +
            "  from srm_po_lines spl\n" +
            " where spl.contract_id = :contractId\n" +
            " and spl.status  in ('OPEN','CANCELLED') \n" +
            " group by spl.contract_item_id ";

    public static String QUERY_TRANSFERREDPOQUANTITY_BY_CONTRACT ="select t.contract_item_id,sum(t.transferredPoQuantity) transferredPoQuantity from" +
            "(select spl.contract_item_id, SUM(spl.received_qty) transferredPoQuantity" +
            "from srm_po_lines spl " +
            "where spl.contract_id = :contractId" +
            "and spl.status <> 'CANCELLED'" +
            "group by spl.contract_item_id" +
            "union all" +
            "select spl.contract_item_id, SUM(spl.demand_qty) transferredPoQuantity" +
            "from srm_po_lines spl" +
            "where spl.contract_id = :contractId" +
            "and spl.status  in ('OPEN','CANCELLED')" +
            "group by spl.contract_item_id) t group by t.contract_item_id";



    public static String QUERY_NONTAXACTTOTALPRICE_BY_CONTRACT ="SELECT SUM(w.received_qty*w.non_tax_price) Non_Tax_Act_Total_Price  FROM srm_po_lines w WHERE w.contract_id = :contractId AND w.status <> 'CANCELLED'";

	private Integer poHeaderId; //采购订单ID
	private String poNumber; //采购订单号
	private Integer orgId; //业务实体ID
	private Integer billToOrganizationId; //收单组织ID
	private String poDocType; //单据类型，ORDER：订单，AGREEMENT：协议
	private Integer supplierId; //供应商ID，关联表：srm_pos_supplier_info
	private Integer supplierSiteId; //供应商地点ID，关联表：srm_pos_supplier_sites
	private String currencyCode; //币种(BANK_CURRENCY)
	private String taxRateCode; //税率
	private Integer buyerId; //采购员ID，关联表：saaf_employees
	private String returnGoodsType; //回货类型
	private String paymentCondition; //付款条件
	private String settlementWay; //结算方式
	private BigDecimal poVersions; //订单版本
	private String status; //状态
	private Integer approvalUserId; //审批用户ID
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date approvedDate; //批准时间
	@JSONField(format = "yyyy-MM-dd")
	private Date startDate; //有效开始日期
	@JSONField(format = "yyyy-MM-dd")
	private Date endDate; //有效结束日期
	private String description; //说明
	private Integer poFileId; //附件ID
	private String agreementClause; //协议条款
	private String sourceCode; //数据来源
	private String sourceId; //数据来源ID
	private Integer versionNum;
	@JSONField(format = "yyyy-MM-dd")
	private Date creationDate;
	private Integer createdBy;
	private Integer lastUpdatedBy;
	@JSONField(format = "yyyy-MM-dd")
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

	// 自定义///////////////////////////////////////////////////////////
	private String unit;
	private String employeeNumber;
	private String employeeName;
	private String supplierNumber;
	private Integer poLineId;// 行ID
	private Integer lineNumber;// 行号
	private BigDecimal demandQty; // 需求数量

	private String supplierName;
	private String siteName;

	private String billToOrganizationName;// 收单组织
	private String uomCodeDesc;
	private String returnGoodsTypeStr;
	private String statusStr;
	private Integer employeeId;

	private BigDecimal taxTotalPrice;// 订单总价（含税）
	private BigDecimal nonTaxTotalPrice;// 订单总价（不含税）
	private Integer receiveToOrganizationId;
	private String receiveToOrganizationName;// 收货组织
	private Integer itemId;// 物料ID
	private String itemName;// 物料名称
	private String itemCode;// 物料编码

	private String createdName;

	private Integer categoryId; // 采购分类ID
	private String categoryName; // 采购分类名称
	private String categoryCode; // 采购分类编码
	private String lineDescription;// 订单行备注
	private String buyerName;// 采购员姓名
	private String orgName;// 业务实体名称
	private String lineStatus;// 订单行状态
	private String lineStatusStr;// 订单行状态
	private BigDecimal taxPrice; // 含税单价
	private BigDecimal nonTaxPrice; // 不含税单价
	private BigDecimal mayNoticeQty; // 可通知送货数量
	private BigDecimal onWayQty; // 在途数量
	private BigDecimal receivedQty; // 已接收数量
	private BigDecimal returnQty; // 退货数量
	private BigDecimal originalDemandQty; // 原需求数量
	@JSONField(format = "yyyy-MM-dd")
	private Date originalDemandDate; // 原需求日期
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date feedbackAdjustDate; // 反馈调整日期
	private BigDecimal feedbackAdjustQty; // 反馈调整数量
	private String feedbackStatus; // 反馈状态
	private String feedbackStatusStr; // 反馈状态
	private String feedbackResult; // 反馈结果
	private String feedbackResultStr; // 反馈结果
	private String rejectReason; // 供应商拒绝原因

	private String currencyCodeStr;
	private String taxRateCodeStr;
	private String paymentConditionStr;
	private String settlementWayStr;

	private String fullCategoryCode;
	private String fullCategoryName;
	private String expenseItemCode;
	private BigDecimal deliveryQty;//发货数量
	private BigDecimal noticeQty;//已接受数量
	private String approvalUserName;
	private String itemDescription;
	private String ladderPriceFlag;
	private BigDecimal ladderQty;
	private String sourceNumber;
	private String accumulativeFlag;
	private BigDecimal minPoQty;
	private Integer agreementAssignId;
	private String defaultFlag;
	private String itemSpec;
	private Integer paymentTermId;
	private String paymentTermName;
	@JSONField(format = "yyyy-MM-dd")
	private Date demandDate; // 需求日期
	private String receiveToOrganizationCode;
	private String paymentTermCode;
	private Integer commonFileId;
	private String commonFilePath;
	private String commonFileName;
	private String uomCodeName;
	
	private String isGlobal;//是否全局
	private Integer poArchiveId;	//采购订单归档id
	private BigDecimal sumDemandQty; // 采购总数量

	private Integer contractId;
	private String contractCode;
	private BigDecimal notifiedQty;

	private String region;
	private String specification;
	private BigDecimal materialsPrice;
	private BigDecimal artificialPrice;
	private String taxRate;
	private String auctionNumber;
	private String agreementNumber;

	private String erpPoNumber;
	private String context;
	private String projectCategory;
	private String projectType;
	private String technicalTransNumber;
	private String subprojectNumber;
	private String acceptanceProcessNumber;
	private String prNumber;
	private String locationCode;
	private String poType;
	private BigDecimal taxActTotalPrice;
	private BigDecimal nonTaxActTotalPrice;
	private Integer shipToOrganizationId;
	private String shipToOrganizationName;// 收货组织
    private String receiveIsDisabled;

	private String origin;
	private String priceLibraryNumber;//价格库编码
	private String buyer;//采购员
	private String remarks;//备注

	private Integer shipToLocationId;
	private Integer billToLocationId;
	private String shipToLocationCode;
	private String billToLocationCode;
	private Integer organizationId;
	private String organizationName;
	private Integer priceLibraryVersion;
	private String supplierType;
	private String supplierTypeName;
	private String lineTaxRate;
	private Integer poLineCombId;
	private String orgCode;
	private String organizationCode;
	private String logiShipToLocationName;
    private String logiBillToLocationName;
    private String logiShipToLocationCode;
    private String logiBillToLocationCode;
    private Integer contractItemId;
    private String contractName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date partyASignDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date contractDate;


    private BigDecimal cost;

    private BigDecimal sumReceivedQty;
    private BigDecimal transferredPoQuantity;

    public BigDecimal getTransferredPoQuantity() {
        return transferredPoQuantity;
    }

    public void setTransferredPoQuantity(BigDecimal transferredPoQuantity) {
        this.transferredPoQuantity = transferredPoQuantity;
    }

    public BigDecimal getSumReceivedQty() {
        return sumReceivedQty;
    }

    public void setSumReceivedQty(BigDecimal sumReceivedQty) {
        this.sumReceivedQty = sumReceivedQty;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Date getPartyASignDate() {
        return partyASignDate;
    }

    public void setPartyASignDate(Date partyASignDate) {
        this.partyASignDate = partyASignDate;
    }

    public Integer getContractItemId() {
        return contractItemId;
    }

    public void setContractItemId(Integer contractItemId) {
        this.contractItemId = contractItemId;
    }

    public String getLogiShipToLocationName() {
        return logiShipToLocationName;
    }

    public void setLogiShipToLocationName(String logiShipToLocationName) {
        this.logiShipToLocationName = logiShipToLocationName;
    }

    public String getLogiBillToLocationName() {
        return logiBillToLocationName;
    }

    public void setLogiBillToLocationName(String logiBillToLocationName) {
        this.logiBillToLocationName = logiBillToLocationName;
    }

    public String getLogiShipToLocationCode() {
        return logiShipToLocationCode;
    }

    public void setLogiShipToLocationCode(String logiShipToLocationCode) {
        this.logiShipToLocationCode = logiShipToLocationCode;
    }

    public String getLogiBillToLocationCode() {
        return logiBillToLocationCode;
    }

    public void setLogiBillToLocationCode(String logiBillToLocationCode) {
        this.logiBillToLocationCode = logiBillToLocationCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Integer getPoLineCombId() {
        return poLineCombId;
    }

    public void setPoLineCombId(Integer poLineCombId) {
        this.poLineCombId = poLineCombId;
    }

    public String getLineTaxRate() {
        return lineTaxRate;
    }

    public void setLineTaxRate(String lineTaxRate) {
        this.lineTaxRate = lineTaxRate;
    }

    public String getSupplierTypeName() {
        return supplierTypeName;
    }

    public void setSupplierTypeName(String supplierTypeName) {
        this.supplierTypeName = supplierTypeName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getPriceLibraryVersion() {
		return priceLibraryVersion;
	}

	public void setPriceLibraryVersion(Integer priceLibraryVersion) {
		this.priceLibraryVersion = priceLibraryVersion;
	}

	public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getNotifiedQty() {
		return notifiedQty;
	}

	public void setNotifiedQty(BigDecimal notifiedQty) {
		this.notifiedQty = notifiedQty;
	}

	public BigDecimal getSumDemandQty() {
		return sumDemandQty;
	}

	public void setSumDemandQty(BigDecimal sumDemandQty) {
		this.sumDemandQty = sumDemandQty;
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

	public Integer getCommonFileId() {
		return commonFileId;
	}

	public void setCommonFileId(Integer commonFileId) {
		this.commonFileId = commonFileId;
	}

	public String getPaymentTermCode() {
		return paymentTermCode;
	}

	public void setPaymentTermCode(String paymentTermCode) {
		this.paymentTermCode = paymentTermCode;
	}

	public String getReceiveToOrganizationCode() {
		return receiveToOrganizationCode;
	}

	public void setReceiveToOrganizationCode(String receiveToOrganizationCode) {
		this.receiveToOrganizationCode = receiveToOrganizationCode;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public Integer getPaymentTermId() {
		return paymentTermId;
	}

	public void setPaymentTermId(Integer paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	public String getPaymentTermName() {
		return paymentTermName;
	}

	public void setPaymentTermName(String paymentTermName) {
		this.paymentTermName = paymentTermName;
	}

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public Integer getAgreementAssignId() {
		return agreementAssignId;
	}

	public void setAgreementAssignId(Integer agreementAssignId) {
		this.agreementAssignId = agreementAssignId;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
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

	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	public String getAccumulativeFlag() {
		return accumulativeFlag;
	}

	public void setAccumulativeFlag(String accumulativeFlag) {
		this.accumulativeFlag = accumulativeFlag;
	}

	public BigDecimal getMinPoQty() {
		return minPoQty;
	}

	public void setMinPoQty(BigDecimal minPoQty) {
		this.minPoQty = minPoQty;
	}

	public String getAgreementClause() {
		return agreementClause;
	}

	public void setAgreementClause(String agreementClause) {
		this.agreementClause = agreementClause;
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

	public String getApprovalUserName() {
		return approvalUserName;
	}

	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}

	public BigDecimal getNoticeQty() {
		return noticeQty;
	}

	public void setNoticeQty(BigDecimal noticeQty) {
		this.noticeQty = noticeQty;
	}

	public BigDecimal getDeliveryQty() {
		return deliveryQty;
	}

	public void setDeliveryQty(BigDecimal deliveryQty) {
		this.deliveryQty = deliveryQty;
	}

	public void setPoHeaderId(Integer poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Integer getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}

	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setPoDocType(String poDocType) {
		this.poDocType = poDocType;
	}

	public String getPoDocType() {
		return poDocType;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
	}

	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setReturnGoodsType(String returnGoodsType) {
		this.returnGoodsType = returnGoodsType;
	}

	public String getReturnGoodsType() {
		return returnGoodsType;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}

	public String getSettlementWay() {
		return settlementWay;
	}

	public void setPoVersions(BigDecimal poVersions) {
		this.poVersions = poVersions;
	}

	public BigDecimal getPoVersions() {
		return poVersions;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setPoFileId(Integer poFileId) {
		this.poFileId = poFileId;
	}

	public Integer getPoFileId() {
		return poFileId;
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

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}



	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getLineDescription() {
		return lineDescription;
	}

	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getNonTaxPrice() {
		return nonTaxPrice;
	}

	public void setNonTaxPrice(BigDecimal nonTaxPrice) {
		this.nonTaxPrice = nonTaxPrice;
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

	public BigDecimal getOriginalDemandQty() {
		return originalDemandQty;
	}

	public void setOriginalDemandQty(BigDecimal originalDemandQty) {
		this.originalDemandQty = originalDemandQty;
	}

	public Date getOriginalDemandDate() {
		return originalDemandDate;
	}

	public void setOriginalDemandDate(Date originalDemandDate) {
		this.originalDemandDate = originalDemandDate;
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

	public String getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(String feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getBillToOrganizationName() {
		return billToOrganizationName;
	}

	public void setBillToOrganizationName(String billToOrganizationName) {
		this.billToOrganizationName = billToOrganizationName;
	}

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public String getReturnGoodsTypeStr() {
		return returnGoodsTypeStr;
	}

	public void setReturnGoodsTypeStr(String returnGoodsTypeStr) {
		this.returnGoodsTypeStr = returnGoodsTypeStr;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getTaxTotalPrice() {
		return taxTotalPrice;
	}

	public void setTaxTotalPrice(BigDecimal taxTotalPrice) {
		this.taxTotalPrice = taxTotalPrice;
	}

	public BigDecimal getNonTaxTotalPrice() {
		return nonTaxTotalPrice;
	}

	public void setNonTaxTotalPrice(BigDecimal nonTaxTotalPrice) {
		this.nonTaxTotalPrice = nonTaxTotalPrice;
	}

	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}

	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}

	public String getLineStatusStr() {
		return lineStatusStr;
	}

	public void setLineStatusStr(String lineStatusStr) {
		this.lineStatusStr = lineStatusStr;
	}

	public String getFeedbackStatusStr() {
		return feedbackStatusStr;
	}

	public void setFeedbackStatusStr(String feedbackStatusStr) {
		this.feedbackStatusStr = feedbackStatusStr;
	}

	public String getFeedbackResultStr() {
		return feedbackResultStr;
	}

	public void setFeedbackResultStr(String feedbackResultStr) {
		this.feedbackResultStr = feedbackResultStr;
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

	public String getExpenseItemCode() {
		return expenseItemCode;
	}

	public void setExpenseItemCode(String expenseItemCode) {
		this.expenseItemCode = expenseItemCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCurrencyCodeStr() {
		return currencyCodeStr;
	}

	public void setCurrencyCodeStr(String currencyCodeStr) {
		this.currencyCodeStr = currencyCodeStr;
	}

	public String getTaxRateCodeStr() {
		return taxRateCodeStr;
	}

	public void setTaxRateCodeStr(String taxRateCodeStr) {
		this.taxRateCodeStr = taxRateCodeStr;
	}

	public String getPaymentConditionStr() {
		return paymentConditionStr;
	}

	public void setPaymentConditionStr(String paymentConditionStr) {
		this.paymentConditionStr = paymentConditionStr;
	}

	public String getSettlementWayStr() {
		return settlementWayStr;
	}

	public void setSettlementWayStr(String settlementWayStr) {
		this.settlementWayStr = settlementWayStr;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	public String getUomCodeName() {
		return uomCodeName;
	}

	public void setUomCodeName(String uomCodeName) {
		this.uomCodeName = uomCodeName;
	}

	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public Integer getPoArchiveId() {
		return poArchiveId;
	}

	public void setPoArchiveId(Integer poArchiveId) {
		this.poArchiveId = poArchiveId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getMaterialsPrice() {
		return materialsPrice;
	}

	public void setMaterialsPrice(BigDecimal materialsPrice) {
		this.materialsPrice = materialsPrice;
	}

	public BigDecimal getArtificialPrice() {
		return artificialPrice;
	}

	public void setArtificialPrice(BigDecimal artificialPrice) {
		this.artificialPrice = artificialPrice;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getAuctionNumber() {
		return auctionNumber;
	}

	public void setAuctionNumber(String auctionNumber) {
		this.auctionNumber = auctionNumber;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getPrNumber() {
		return prNumber;
	}

	public String getErpPoNumber() {
		return erpPoNumber;
	}

	public void setErpPoNumber(String erpPoNumber) {
		this.erpPoNumber = erpPoNumber;
	}

	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getTaxActTotalPrice() {
		return taxActTotalPrice;
	}

	public void setTaxActTotalPrice(BigDecimal taxActTotalPrice) {
		this.taxActTotalPrice = taxActTotalPrice;
	}

	public BigDecimal getNonTaxActTotalPrice() {
		return nonTaxActTotalPrice;
	}

	public void setNonTaxActTotalPrice(BigDecimal nonTaxActTotalPrice) {
		this.nonTaxActTotalPrice = nonTaxActTotalPrice;
	}

	public Integer getShipToOrganizationId() {
		return shipToOrganizationId;
	}

	public void setShipToOrganizationId(Integer shipToOrganizationId) {
		this.shipToOrganizationId = shipToOrganizationId;
	}

	public String getShipToOrganizationName() {
		return shipToOrganizationName;
	}

	public void setShipToOrganizationName(String shipToOrganizationName) {
		this.shipToOrganizationName = shipToOrganizationName;
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

    public String getReceiveIsDisabled() {
        return receiveIsDisabled;
    }

    public void setReceiveIsDisabled(String receiveIsDisabled) {
        this.receiveIsDisabled = receiveIsDisabled;
    }

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPriceLibraryNumber() {
		return priceLibraryNumber;
	}

	public void setPriceLibraryNumber(String priceLibraryNumber) {
		this.priceLibraryNumber = priceLibraryNumber;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getShipToLocationId() {
		return shipToLocationId;
	}

	public void setShipToLocationId(Integer shipToLocationId) {
		this.shipToLocationId = shipToLocationId;
	}

	public Integer getBillToLocationId() {
		return billToLocationId;
	}

	public void setBillToLocationId(Integer billToLocationId) {
		this.billToLocationId = billToLocationId;
	}

	public String getShipToLocationCode() {
		return shipToLocationCode;
	}

	public void setShipToLocationCode(String shipToLocationCode) {
		this.shipToLocationCode = shipToLocationCode;
	}

	public String getBillToLocationCode() {
		return billToLocationCode;
	}

	public void setBillToLocationCode(String billToLocationCode) {
		this.billToLocationCode = billToLocationCode;
	}
}
