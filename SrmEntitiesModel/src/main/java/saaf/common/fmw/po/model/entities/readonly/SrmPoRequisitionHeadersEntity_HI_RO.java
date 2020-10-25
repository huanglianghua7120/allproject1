package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * SrmPoRequisitionHeadersEntity_HI_RO Entity Object
 * Sat Jun 09 16:36:46 CST 2018  Auto Generate
 */

public class SrmPoRequisitionHeadersEntity_HI_RO  implements Serializable {

	/**
	 * 查询待处理
	 */
	public static final String QUERY_PENDING_SQL =
					"SELECT\n" +
					"  ds.requisition_header_id requisitionHeaderId\n" +
					"  , ds.requisition_line_id requisitionLineId\n" +
					"  , ds.buyer_id buyerId\n" +
					"  , ds.employee_name employeeName\n" +
					"  , ds.requisition_number requisitionNumber\n" +
					"  , ds.org_id orgId\n" +
					"  , ds.inst_name instName\n" +
					"  , ds.organization_id organizationId\n" +
					"  , ds.organization_name organizationName\n" +
					"  , ds.pos_name posName\n" +
					"  , ds.apply_name applyName\n" +
					"  , ds.item_id itemId\n" +
					"  , ds.item_code itemCode\n" +
					"  , ds.item_name itemName\n" +
					"  , ds.item_spec itemDescription\n" +
					"  , ds.meaning uomCode\n" +
					"  , ds.category_id categoryId\n" +
					"  , ds.category_code categoryCode\n" +
					"  , ds.category_name categoryName\n" +
					"  , ds.demand_qty demandQty\n" +
					"  , ds.demand_date demandDate\n" +
					"  , ds.comments comments\n" +
					"  , ds.line_comments lineComments\n" +
					"  , ds.requisition_status requisitionStatus\n" +
					"  , ds.requisition_type requisitionType\n" +
					"  , ds.source_number sourceNumber\n" +
					"  , ds.creation_date creationDate\n" +
					"  , ds.agreement_count agreementCount\n" +
					"  , (\n" +
					"    CASE\n" +
					"      WHEN ds.agreement_count = 0\n" +
					"      THEN '无协议'\n" +
					"      WHEN ds.agreement_count = 1\n" +
					"      THEN '单框架协议'\n" +
					"      ELSE '多框架协议'\n" +
					"    END\n" +
					"  ) isFrameworkAgreemen\n" +
					"FROM\n" +
					"  (SELECT\n" +
					"    prh.requisition_header_id\n" +
					"    , prh.requisition_number\n" +
					"    , prh.requisition_type\n" +
					"    , prh.org_id\n" +
					"    , prh.organization_id\n" +
					"    , prh.department_id\n" +
					"    , prh.requisition_status\n" +
					"    , prh.requisition_emp_id\n" +
					"    , prh.source_number\n" +
					"    , prh.creation_date\n" +
					"    , prl.requisition_line_id\n" +
					"    , prl.buyer_id\n" +
					"    , se2.employee_name\n" +
					"    , si.inst_name\n" +
					"    , si1.inst_name organization_name\n" +
					"    , sp.pos_name\n" +
					"    , se1.employee_name apply_name\n" +
					"    , prl.item_id\n" +
					"    , bi.item_code\n" +
					"    , nvl(prl.item_name, bi.item_name) item_name\n" +
					"    , prl.item_spec\n" +
					"    , slv.meaning\n" +
					"    , prl.category_id\n" +
					"    , bc.category_code\n" +
					"    , bc.category_name\n" +
					"    , prl.demand_qty\n" +
					"    , prl.demand_date\n" +
					"    , prh.comments\n" +
					"    , prl.line_comments\n" +
					"    ,\n" +
					"    (SELECT\n" +
					"      COUNT(1)\n" +
					"    FROM\n" +
					"      srm_po_headers poh\n" +
					"    WHERE poh.po_doc_type = 'AGREEMENT'\n" +
					"      AND poh.status = 'APPROVED'\n" +
					"      AND (\n" +
					"        poh.start_date IS NULL\n" +
					"        OR poh.start_date <= trunc(SYSDATE)\n" +
					"      )\n" +
					"      AND (\n" +
					"        poh.end_date IS NULL\n" +
					"        OR poh.end_date >= trunc(SYSDATE)\n" +
					"      )\n" +
					"      AND EXISTS\n" +
					"      (SELECT\n" +
					"        1\n" +
					"      FROM\n" +
					"        srm_po_lines pol\n" +
					"      WHERE pol.po_header_id = poh.po_header_id\n" +
					"        AND pol.status = 'OPEN'\n" +
					"        AND (\n" +
					"          pol.start_date IS NULL\n" +
					"          OR pol.start_date <= trunc(SYSDATE)\n" +
					"        )\n" +
					"        AND (\n" +
					"          pol.end_date IS NULL\n" +
					"          OR pol.end_date >= trunc(SYSDATE)\n" +
					"        )\n" +
					"        AND pol.non_tax_price > 0\n" +
					"        AND pol.item_id = prl.item_id)\n" +
					"      AND EXISTS\n" +
					"      (SELECT\n" +
					"        1\n" +
					"      FROM\n" +
					"        srm_po_agreement_assigns paa\n" +
					"      WHERE paa.po_header_id = poh.po_header_id\n" +
					"        AND paa.org_id = prh.org_id)) agreement_count\n" +
					"  FROM\n" +
					"    srm_po_requisition_headers prh\n" +
					"    LEFT JOIN saaf_institution si\n" +
					"      ON si.inst_id = prh.org_id\n" +
					"    LEFT JOIN saaf_institution si1\n" +
					"      ON si1.inst_id = prh.organization_id\n" +
					"    LEFT JOIN saaf_positions sp\n" +
					"      ON sp.pos_id = prh.department_id\n" +
					"    LEFT JOIN saaf_employees se1\n" +
					"      ON se1.employee_id = prh.requisition_emp_id\n" +
					"    , srm_po_requisition_lines prl\n" +
					"    LEFT JOIN saaf_employees se2\n" +
					"      ON se2.employee_id = prl.buyer_id\n" +
					"    LEFT JOIN srm_base_items_b bi\n" +
					"      ON bi.item_id = prl.item_id\n" +
					"    LEFT JOIN srm_base_categories bc\n" +
					"      ON bc.category_id = prl.category_id\n" +
					"    LEFT JOIN saaf_lookup_values slv\n" +
					"      ON slv.lookup_code = bi.uom_code\n" +
					"      AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  WHERE prh.requisition_header_id = prl.requisition_header_id\n" +
					"    AND prh.requisition_status = 'APPROVED'\n" +
					"    AND prl.line_status = 'OPEN'\n" +
					"    AND prl.item_id > 0\n" +
					"    AND nvl(prl.handle_way, '1') <> '3'\n" +
					"    AND check_org_f (:userId, prh.org_id) = 'Y') ds\n" +
					"WHERE 1 = 1";

	/**
	 * 查询已处理
	 */
	public static final String QUERY_HANDLED_SQL =
					"SELECT\n" +
					"  prh.requisition_header_id requisitionHeaderId\n" +
					"  , prl.requisition_line_id requisitionLineId\n" +
					"  , prl.buyer_id buyerId\n" +
					"  , se2.employee_name employeeName\n" +
					"  , prh.requisition_number requisitionNumber\n" +
					"  , prh.org_id orgId\n" +
					"  , si.inst_name instName\n" +
					"  , prh.organization_id organizationId\n" +
					"  , si1.inst_name organizationName\n" +
					"  , sp.pos_name posName\n" +
					"  , se1.employee_name applyName\n" +
					"  , prl.item_id itemId\n" +
					"  , bi.item_code itemCode\n" +
					"  , nvl(prl.item_name, bi.item_name) itemName\n" +
					"  , prl.item_spec itemSpec\n" +
					"  , slv.meaning uomCode\n" +
					"  , prl.category_id categoryId\n" +
					"  , bc.full_category_code categoryCode\n" +
					"  , bc.full_category_name categoryName\n" +
					"  , prl.demand_qty demandQty\n" +
					"  , prl.demand_date demandDate\n" +
					"  , prh.comments comments\n" +
					"  , prl.line_comments lineComments\n" +
					"  , prh.requisition_status requisitionStatus\n" +
					"  , prh.requisition_type requisitionType\n" +
					"  , prh.source_number sourceNumber\n" +
					"  , prh.creation_date creationDate\n" +
					"FROM\n" +
					"  srm_po_requisition_headers prh\n" +
					"  LEFT JOIN saaf_institution si\n" +
					"    ON si.inst_id = prh.org_id\n" +
					"  LEFT JOIN saaf_institution si1\n" +
					"    ON si1.inst_id = prh.organization_id\n" +
					"  LEFT JOIN saaf_positions sp\n" +
					"    ON sp.pos_id = prh.department_id\n" +
					"  LEFT JOIN saaf_employees se1\n" +
					"    ON se1.employee_id = prh.requisition_emp_id\n" +
					"  , srm_po_requisition_lines prl\n" +
					"  LEFT JOIN saaf_employees se2\n" +
					"    ON se2.employee_id = prl.buyer_id\n" +
					"  LEFT JOIN srm_base_items_b bi\n" +
					"    ON bi.item_id = prl.item_id\n" +
					"  LEFT JOIN srm_base_categories bc\n" +
					"    ON bc.category_id = prl.category_id\n" +
					"  LEFT JOIN saaf_lookup_values slv\n" +
					"    ON slv.lookup_code = bi.uom_code\n" +
					"    AND slv.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"WHERE prh.requisition_header_id = prl.requisition_header_id\n" +
					"  AND prh.requisition_status = 'APPROVED'\n" +
					"  AND prl.line_status = 'OPEN'\n" +
					"  AND prl.handle_way = '3'";

	public static final String QUERY_FRAMEWORK_AGREEMEN_SQL =
					"SELECT\n" +
					"  ph.po_header_id poHeaderId\n" +
					"  , ph.po_number poNumber\n" +
					"  , ph.start_date phStartDate\n" +
					"  , ph.end_date phEndDate\n" +
					"  , ph.supplier_id supplierId\n" +
					"  , psi.supplier_name supplierName\n" +
					"  , ph.supplier_site_id supplierSiteId\n" +
					"  , ph.currency_code currencyCode\n" +
					"  , ph.tax_rate_code taxRateCode\n" +
					"  , ph.payment_condition paymentCondition\n" +
					"  , ph.settlement_way settlementWay\n" +
					"  , ph.bill_to_organization_id billToOrganizationId\n" +
					"  , ph.return_goods_type returnGoodsType\n" +
					"  , ph.buyer_id buyerId\n" +
					"  , pl.po_line_id poLineId\n" +
					"  , pl.item_id itemId\n" +
					"  , pl.ladder_price_flag ladderPriceFlag\n" +
					"  , pl.ladder_qty ladderQty\n" +
					"  , pl.non_tax_price nonTaxPrice\n" +
					"  , pl.tax_price taxPrice\n" +
					"  , pl.start_date plStartDate\n" +
					"  , pl.end_date plEndDate\n" +
					"FROM\n" +
					"  srm_po_headers ph\n" +
					"  , srm_pos_supplier_info psi\n" +
					"  , srm_po_lines pl\n" +
					"WHERE ph.po_header_id = pl.po_header_id\n" +
					"  AND ph.supplier_id = psi.supplier_id\n" +
					"  AND ph.po_doc_type = 'AGREEMENT'\n" +
					"  AND ph.status = 'APPROVED'\n" +
					"  AND pl.status = 'OPEN'\n" +
					"  AND (\n" +
					"    ph.start_date IS NULL\n" +
					"    OR ph.start_date <= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND (\n" +
					"    ph.end_date IS NULL\n" +
					"    OR ph.end_date >= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND (\n" +
					"    pl.start_date IS NULL\n" +
					"    OR pl.start_date <= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND (\n" +
					"    pl.end_date IS NULL\n" +
					"    OR pl.end_date >= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND pl.item_id = :itemId\n" +
					"  AND EXISTS\n" +
					"  (SELECT\n" +
					"    1\n" +
					"  FROM\n" +
					"    srm_po_agreement_assigns paa\n" +
					"  WHERE paa.po_header_id = ph.po_header_id\n" +
					"    AND paa.org_id = :orgId)";

	public static final String QUERY_FRAMEWORK_AGREEMEN_SQL2 =
					"SELECT\n" +
					"  prh.requisition_header_id requisitionHeaderId\n" +
					"  , prh.requisition_number requisitionNumber\n" +
					"  , prh.org_id orgId\n" +
					"  , si.inst_name orgName\n" +
					"  , prl.requisition_line_id requisitionLineId\n" +
					"  , prl.line_number lineNumber\n" +
					"  , prl.item_id itemId\n" +
					"  , sbi.item_code itemCode\n" +
					"  , nvl(prl.item_name, sbi.item_name) itemName\n" +
					"  , prl.item_spec itemSpec\n" +
					"  , prl.category_id categoryId\n" +
					"  , sbc.full_category_code fullCategoryCode\n" +
					"  , sbc.full_category_name fullCategoryName\n" +
					"  , prl.uom_code uomCode\n" +
					"  , slv1.meaning uomCodeDesc\n" +
					"  , nvl(prl.buyer_id, poh.buyer_id) buyerId\n" +
					"  , emp1.employee_name buyerName\n" +
					"  , prl.demand_qty demandQty\n" +
					"  , prl.demand_date demandDate\n" +
					"  , prl.line_comments lineComments\n" +
					"  , poh.po_header_id poHeaderId\n" +
					"  , poh.po_number poNumber\n" +
					"  , poh.supplier_id supplierId\n" +
					"  , psi.supplier_number supplierNumber\n" +
					"  , psi.supplier_name supplierName\n" +
					"  , poh.supplier_site_id supplierSiteId\n" +
					"  , poh.tax_rate_code taxRateCode\n" +
					"  , poh.currency_code currencyCode\n" +
					"  , slv2.meaning taxRate\n" +
					"  , poh.payment_condition paymentCondition\n" +
					"  , poh.settlement_way settlementWay\n" +
					"  , pol.po_line_id poLineId\n" +
					"  , pol.tax_price taxPrice\n" +
					"  , pol.non_tax_price nonTaxPrice\n" +
					"  , pol.ladder_price_flag ladderPriceFlag\n" +
					"  , pol.ladder_qty ladderQty\n" +
					"  , nvl(sbd.deliver_type, 'BASE_ON_PO') returnGoodsType\n" +
					"  , slv3.meaning returnGoodsTypeDesc\n" +
					"FROM\n" +
					"  srm_po_requisition_headers prh\n" +
					"  , saaf_institution si\n" +
					"  , srm_po_requisition_lines prl\n" +
					"  LEFT JOIN saaf_employees emp1\n" +
					"    ON emp1.employee_id = prl.buyer_id\n" +
					"  , srm_base_items_b sbi\n" +
					"  LEFT JOIN srm_base_item_delivers sbd\n" +
					"    ON sbd.item_id = sbi.item_id\n" +
					"    AND sbd.deliver_status = 'ACT'\n" +
					"  LEFT JOIN saaf_lookup_values slv3\n" +
					"    ON slv3.lookup_code = IFNULL(sbd.deliver_type, 'BASE_ON_PO')\n" +
					"    AND slv3.lookup_type = 'ISP_DELIVERY_TYPE'\n" +
					"  , srm_base_categories sbc\n" +
					"  , saaf_lookup_values slv1\n" +
					"  , srm_po_lines pol\n" +
					"  , srm_po_headers poh\n" +
					"  , saaf_lookup_values slv2\n" +
					"  , srm_pos_supplier_info psi\n" +
					"WHERE prh.org_id = si.inst_id\n" +
					"  AND prh.requisition_header_id = prl.requisition_header_id\n" +
					"  AND prl.item_id = sbi.item_id\n" +
					"  AND prl.category_id = sbc.category_id\n" +
					"  AND sbi.uom_code = slv1.lookup_code\n" +
					"  AND slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"  AND prl.item_id = pol.item_id\n" +
					"  AND pol.status = 'OPEN'\n" +
					"  AND (\n" +
					"    pol.start_date IS NULL\n" +
					"    OR pol.start_date <= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND (\n" +
					"    pol.end_date IS NULL\n" +
					"    OR pol.end_date >= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND nvl(pol.ladder_qty, prl.demand_qty) <= prl.demand_qty\n" +
					"  AND pol.non_tax_price > 0\n" +
					"  AND pol.po_header_id = poh.po_header_id\n" +
					"  AND poh.po_doc_type = 'AGREEMENT'\n" +
					"  AND poh.status = 'APPROVED'\n" +
					"  AND (\n" +
					"    poh.start_date IS NULL\n" +
					"    OR poh.start_date <= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND (\n" +
					"    poh.end_date IS NULL\n" +
					"    OR poh.end_date >= trunc(SYSDATE)\n" +
					"  )\n" +
					"  AND EXISTS\n" +
					"  (SELECT\n" +
					"    1\n" +
					"  FROM\n" +
					"    srm_po_agreement_assigns paa\n" +
					"  WHERE paa.po_header_id = poh.po_header_id\n" +
					"    AND paa.org_id = prh.org_id)\n" +
					"  AND poh.tax_rate_code = slv2.lookup_code\n" +
					"  AND slv2.lookup_type = 'PON_TAX_LIST'\n" +
					"  AND poh.supplier_id = psi.supplier_id\n" +
					"  AND prl.requisition_line_id = :requisitionLineId";

	public static final String QUERY_AGENT_LOV =
					"SELECT se.employee_id employeeId\r\n" +
					"      ,se.user_id userId\r\n" +
					"      ,se.employee_name employeeName\r\n" +
					"      ,se.employee_number employeeNumber\r\n" +
					"FROM saaf_employees se WHERE 1 = 1";

	public static final String QUERY_PO_REQUISITION_SQL =
					"SELECT t.requisitionHeaderId\n" +
					"      ,t.requisitionNumber\n" +
					"      ,t.orgId\n" +
					"      ,t.orgName\n" +
					"      ,t.organizationId\n" +
					"      ,t.organizationName\n" +
					"      ,t.departmentId\n" +
					"      ,t.posName\n" +
					"      ,t.requisitionDate\n" +
					"      ,t.employeeId\n" +
					"      ,t.employeeName\n" +
					"      ,t.requisitionType\n" +
					"      ,t.requisitionTypeDesc\n" +
					"      ,t.sourceNumberh\n" +
					"      ,t.requisitionStatus\n" +
					"      ,t.requisitionStatusDesc\n" +
					"      ,t.comments\n" +
					"      ,t.requisitionLineId\n" +
					"      ,t.itemId\n" +
					"      ,t.itemName\n" +
					"      ,t.itemSpec\n" +
					"      ,t.categoryId\n" +
					"      ,t.fullCategoryCode\n" +
					"      ,t.fullCategoryName\n" +
					"      ,t.buyerId\n" +
					"      ,t.buyerName\n" +
					"      ,t.minPacking\n" +
					"      ,t.uomCode\n" +
					"      ,t.uomCodeDesc\n" +
					"      ,t.sourceNumberi\n" +
					"      ,t.lineComments\n" +
					"      ,t.lineStatus\n" +
					"      ,t.lineStatusDesc\n" +
					"      ,t.handleWay\n" +
					"      ,t.demandDate\n" +
					"      ,t.demandQty\n" +
					"      ,t.itemCode\n" +
					"      ,t.creationDate\n" +
					"      ,t.lineNumber\n" +
					"      ,t.handleWayDesc\n" +
					"      ,t.sourceId\n" +
					"      ,t.lastUpdateDate\n" +
                    "      ,t.poNumber\n" +
                    "      ,t.supplierId\n" +
                    "      ,t.supplierName\n" +
                    "      ,t.poHeaderId\n" +
					"FROM   (SELECT rh.requisition_header_id requisitionHeaderId\n" +
					"              ,rh.requisition_number requisitionNumber\n" +
					"              ,rh.org_id orgId\n" +
					"              ,si.inst_name orgName\n" +
					"              ,rh.organization_id organizationId\n" +
					"              ,si1.inst_name organizationName\n" +
					"              ,rh.department_id departmentId\n" +
					"              ,sp.pos_name posName\n" +
					"              ,rh.requisition_date requisitionDate\n" +
					"              ,rh.requisition_emp_id employeeId\n" +
					"              ,emp.employee_name employeeName\n" +
					"              ,rh.requisition_type requisitionType\n" +
					"              ,val.meaning requisitionTypeDesc\n" +
					"              ,rh.source_number sourceNumberh\n" +
					"              ,rh.requisition_status requisitionStatus\n" +
					"              ,val1.meaning requisitionStatusDesc\n" +
					"              ,rh.comments\n" +
					"              ,rl.requisition_line_id requisitionLineId\n" +
					"              ,rl.item_id itemId\n" +
					"              ,rl.item_name itemName\n" +
					"              ,rl.item_spec itemSpec\n" +
					"              ,rl.category_id categoryId\n" +
					"              ,bc.full_category_code fullCategoryCode\n" +
					"              ,bc.full_category_name fullCategoryName\n" +
					"              ,rh.buyer_id buyerId\n" +
					"              ,emp1.employee_name buyerName\n" +
					"              ,rl.min_packing minPacking\n" +
					"              ,rl.uom_code uomCode\n" +
					"              ,slv1.meaning uomCodeDesc\n" +
					"              ,rl.source_number sourceNumberi\n" +
					"              ,rl.line_comments lineComments\n" +
					"              ,rl.line_status lineStatus\n" +
					"              ,val2.meaning lineStatusDesc\n" +
					"              ,rl.handle_way handleWay\n" +
					"              ,rl.demand_date demandDate\n" +
					"              ,rl.demand_qty demandQty\n" +
					"              ,(SELECT it.item_code\n" +
					"                FROM   srm_base_items_b it\n" +
					"                WHERE  it.item_id = rl.item_id) itemCode\n" +
					"              ,rh.creation_date creationDate\n" +
					"              ,rl.line_number lineNumber\n" +
					"              ,slv2.meaning handleWayDesc\n" +
					"              ,rh.source_id sourceId\n" +
					"              ,rh.last_update_date lastUpdateDate\n" +
                    "              ,sph.PO_NUMBER poNumber\n" +
                    "              ,rl.supplier_id supplierId\n" +
                    "              ,spsi.supplier_name supplierName\n" +
                    "              ,rl.po_header_id poHeaderId\n" +
					"        FROM   srm_po_requisition_headers rh\n" +
					"        LEFT   JOIN srm_po_requisition_lines rl\n" +
					"        ON     rh.requisition_header_id = rl.requisition_header_id\n" +
                    "       LEFT   JOIN srm_pos_supplier_info spsi\n" +
                    "       ON     spsi.supplier_id = rl.supplier_id\n" +
                    "        LEFT   JOIN srm_po_headers sph\n" +
                    "        ON Sph.Po_Header_id = rl.Po_Header_id\n" +
					"        LEFT   JOIN saaf_institution si\n" +
					"        ON     si.inst_id = rh.org_id\n" +
					"        LEFT   JOIN saaf_institution si1\n" +
					"        ON     si1.inst_id = rh.organization_id\n" +
					"        LEFT   JOIN saaf_positions sp\n" +
					"        ON     sp.pos_id = rh.department_id\n" +
					"        LEFT   JOIN saaf_employees emp\n" +
					"        ON     rh.requisition_emp_id = emp.employee_id\n" +
					"        LEFT   JOIN saaf_employees emp1\n" +
					"        ON     rh.buyer_id = emp1.employee_id\n" +
					"        LEFT   JOIN saaf_lookup_values val\n" +
					"        ON     val.lookup_code = rh.requisition_type\n" +
					"        AND    val.lookup_type = 'SER_TENDER_TYPE'\n" +
					"        LEFT   JOIN saaf_lookup_values val1\n" +
					"        ON     val1.lookup_code = rh.requisition_status\n" +
					"        AND    val1.lookup_type = 'ISP_PR_STATUS'\n" +
					"        LEFT   JOIN srm_base_categories bc\n" +
					"        ON     bc.category_id = rl.category_id\n" +
					"        LEFT   JOIN saaf_lookup_values val2\n" +
					"        ON     val2.lookup_code = rl.line_status\n" +
					"        AND    val2.lookup_type = 'ISP_PR_LINE_STATUS'\n" +
					"        LEFT   JOIN saaf_lookup_values slv1\n" +
					"        ON     rl.uom_code = slv1.lookup_code\n" +
					"        AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"        LEFT   JOIN saaf_lookup_values slv2\n" +
					"        ON     slv2.lookup_code = rl.handle_way\n" +
					"        AND    slv2.lookup_type = 'ISP_PROCESS_MODE'\n" +
					"        WHERE  1 = 1) t\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_REQUISITON_HEADER_SQL =
					"SELECT rh.requisition_header_id requisitionHeaderId\n" +
					"      ,rh.requisition_number    requisitionNumber\n" +
					"      ,rh.org_id                orgId\n" +
					"      ,si.inst_name             orgName\n" +
					"      ,rh.organization_id       organizationId\n" +
					"      ,si1.inst_name            organizationName\n" +
					"      ,rh.department_id         departmentId\n" +
					"      ,sp.pos_name              posName\n" +
					"      ,rh.requisition_date      requisitionDate\n" +
					"      ,rh.requisition_emp_id    employeeId\n" +
					"      ,emp.employee_name        employeeName\n" +
					"      ,rh.requisition_type      requisitionType\n" +
					"      ,val.meaning              requisitionTypeDesc\n" +
					"      ,rh.source_number         sourceNumberh\n" +
					"      ,rh.requisition_status    requisitionStatus\n" +
					"      ,val1.meaning             requisitionStatusDesc\n" +
					"      ,rh.comments\n" +
					"      ,rh.buyer_id buyerId\n" +
					"      ,emp1.employee_name buyerName\n" +
					"FROM   srm_po_requisition_headers rh\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = rh.org_id\n" +
					"LEFT   JOIN saaf_institution si1\n" +
					"ON     si1.inst_id = rh.organization_id\n" +
					"LEFT   JOIN saaf_positions sp\n" +
					"ON     sp.pos_id = rh.department_id\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     rh.requisition_emp_id = emp.employee_id\n" +
					"LEFT   JOIN saaf_lookup_values val\n" +
					"ON     val.lookup_code = rh.requisition_type\n" +
					"AND    val.lookup_type = 'SER_TENDER_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values val1\n" +
					"ON     val1.lookup_code = rh.requisition_status\n" +
					"AND    val1.lookup_type = 'ISP_PR_STATUS'\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     rh.buyer_id = emp1.employee_id\n" +
					"WHERE  1 = 1\n";

	public static final String QUERY_REQUISITON_LINES_SQL =
					"SELECT rh.requisition_header_id requisitionHeaderId\n" +
					"      ,rh.requisition_number requisitionNumber\n" +
					"      ,rh.org_id orgId\n" +
					"      ,si.inst_name orgName\n" +
					"      ,rh.organization_id organizationId\n" +
					"      ,si1.inst_name organizationName\n" +
					"      ,rh.department_id departmentId\n" +
					"      ,sp.pos_name posName\n" +
					"      ,rh.requisition_date requisitionDate\n" +
					"      ,rh.requisition_emp_id employeeId\n" +
					"      ,emp.employee_name employeeName\n" +
					"      ,rh.requisition_type requisitionType\n" +
					"      ,val.meaning requisitionTypeDesc\n" +
					"      ,rh.source_number sourceNumberh\n" +
					"      ,rh.requisition_status requisitionStatus\n" +
					"      ,val1.meaning requisitionStatusDesc\n" +
					"      ,rh.comments\n" +
					"      ,rl.requisition_line_id requisitionLineId\n" +
					"      ,rl.item_id itemId\n" +
					"      ,rl.item_name itemName\n" +
					"      ,rl.item_spec itemSpec\n" +
					"      ,rl.category_id categoryId\n" +
					"      ,bc.full_category_code fullCategoryCode\n" +
					"      ,bc.full_category_name fullCategoryName\n" +
					"      ,rl.buyer_id buyerId\n" +
					"      ,emp1.employee_name buyerName\n" +
					"      ,rl.min_packing minPacking\n" +
					"      ,rl.uom_code uomCode\n" +
					"      ,slv1.meaning uomCodeDesc\n" +
					"      ,rl.source_number sourceNumberi\n" +
					"      ,rl.line_comments lineComments\n" +
					"      ,rl.line_status lineStatus\n" +
					"      ,val2.meaning lineStatusDesc\n" +
					"      ,rl.handle_way handleWay\n" +
					"      ,rl.demand_date demandDate\n" +
					"      ,rl.demand_qty demandQty\n" +
					"      ,sbi.item_code itemCode\n" +
					"      ,rh.creation_date creationDate\n" +
					"      ,rl.line_number lineNumber\n" +
					"      ,nvl(slv2.meaning,\n" +
					"           (CASE\n" +
					"             WHEN (SELECT COUNT(1)\n" +
					"                   FROM   srm_pon_auction_items spa\n" +
					"                   WHERE  spa.attribute1 = rl.requisition_header_id\n" +
					"                   AND    spa.attribute2 = rl.requisition_line_id) = 0 THEN\n" +
					"              NULL\n" +
					"             ELSE\n" +
					"              '招标/询价中'\n" +
					"           END)) handleWayDesc\n" +
					"      ,rh.source_id sourceId\n" +
					"      ,rh.last_update_date lastUpdateDate\n" +
                    "      ,rl.supplier_id supplierId\n" +
                    "      ,spsi.supplier_name supplierName\n" +
                    "      ,rl.po_header_id poHeaderId\n" +
					"FROM   srm_po_requisition_lines rl\n" +
					"LEFT   JOIN srm_po_requisition_headers rh\n" +
					"ON     rh.requisition_header_id = rl.requisition_header_id\n" +
					"LEFT   JOIN saaf_institution si\n" +
					"ON     si.inst_id = rh.org_id\n" +
					"LEFT   JOIN saaf_institution si1\n" +
					"ON     si1.inst_id = rh.organization_id\n" +
					"LEFT   JOIN saaf_positions sp\n" +
					"ON     sp.pos_id = rh.department_id\n" +
					"LEFT   JOIN saaf_employees emp\n" +
					"ON     rh.requisition_emp_id = emp.employee_id\n" +
					"LEFT   JOIN saaf_employees emp1\n" +
					"ON     rl.buyer_id = emp1.employee_id\n" +
					"LEFT   JOIN saaf_lookup_values val\n" +
					"ON     val.lookup_code = rh.requisition_type\n" +
					"AND    val.lookup_type = 'ISP_PR_TYPE'\n" +
					"LEFT   JOIN saaf_lookup_values val1\n" +
					"ON     val1.lookup_code = rh.requisition_status\n" +
					"AND    val1.lookup_type = 'ISP_PR_STATUS'\n" +
					"LEFT   JOIN srm_base_categories bc\n" +
					"ON     bc.category_id = rl.category_id\n" +
					"LEFT   JOIN saaf_lookup_values val2\n" +
					"ON     val2.lookup_code = rl.line_status\n" +
					"AND    val2.lookup_type = 'ISP_PR_LINE_STATUS'\n" +
					"LEFT   JOIN saaf_lookup_values slv1\n" +
					"ON     rl.uom_code = slv1.lookup_code\n" +
					"AND    slv1.lookup_type = 'BASE_ITEMS_UNIT'\n" +
					"LEFT   JOIN saaf_lookup_values slv2\n" +
					"ON     slv2.lookup_code = rl.handle_way\n" +
					"AND    slv2.lookup_type = 'ISP_PROCESS_MODE'\n" +
					"LEFT   JOIN srm_base_items_b sbi\n" +
					"ON     sbi.item_id = rl.item_id\n" +
                    "LEFT   JOIN srm_pos_supplier_info spsi\n" +
                    "ON     spsi.supplier_id = rl.supplier_id\n" +
					"WHERE  1 = 1\n";

	private Integer employeeId;		//申请人
	private String orgName;			//业务实体名称
	private String organizationName;//组织名称
	private String posName;			//部门名称
	private String employeeName;		//申请人名称
	private String requisitionTypeDesc;//申请类型名称
	private String lineStatusDesc;	//行状态描述
	private Integer lineNumber; //行号
	private Integer itemId;//物料ID
	private String itemCode;//物料编码
	private String fullCategoryCode;//类别编码
	private String fullCategoryName;//类别名称
	private String uomCodeDesc;//类别名称
	private Integer requisitionLineId; //行id
	private String itemName; //物料名称
	private String itemSpec; //物料规格
	private String uomCode; //计量单位
	private BigDecimal minPacking; //最小包装量
	private Integer buyerId; //采购员ID，关联表：saaf_employees
	private String buyerName; //采购员名称
	private Integer categoryId; //采购分类ID
	private BigDecimal demandQty; //需求数量
	@JSONField(format = "yyyy-MM-dd")
	private Date demandDate; //需求时间
	private Integer agentId; //采购员ID
	private String handleWay; //处理方式
	private String lineStatus; //行状态
	private String lineComments; //行说明
	private String sourceNumberh; //来源单号头
	private String sourceNumberi; //来源单号行
	private String handleWayDesc; //处理方式描述

	private Integer requisitionHeaderId; //采购申请ID
	private String requisitionNumber; //采购申请编号
	private Integer orgId; //业务实体ID
	private Integer organizationId; //库存组织ID
	private Integer departmentId; //部门ID
	private String requisitionType; //申请类型
	private Integer requisitionEmpId; //申请人ID
	@JSONField(format = "yyyy-MM-dd")
	private Date requisitionDate; //申请日期
	private String requisitionStatus; //申请状态
	private String requisitionStatusDesc; //申请状态名称
    private Integer approvalUserId; //审批用户ID
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date approvalDate; //批准时间
    private String sourceNumber; //来源单号
    private String comments; //说明
    private String sourceCode; //数据来源
    private String sourceId; //数据来源ID
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDateFrom; //单据日期从
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDateTo; //单据日期至
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
	private String ts;
	
	private String itemDescription;
    private String categoryName;
    private String instName;
    private String applyName;
    private String employeeNumber;

    private String poNumber;
    private String supplierNumber;
	private String supplierName;
	private Integer supplierId;
	@JSONField(format = "yyyy-MM-dd")
	private Date plStartDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date plEndDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date phStartDate;
	@JSONField(format = "yyyy-MM-dd")
	private Date phEndDate;
	private String isFrameworkAgreemen;
	private List<SrmPoRequisitionHeadersEntity_HI_RO> frameworkAgreemenData;
	private String isPriceEffective;
	private Integer poHeaderId;
	private Integer poLineId;
	private Integer reqDistributionId;
	private BigDecimal distributionProportion;
	private String description;
	private BigDecimal taxPrice; //含税单价
	private BigDecimal nonTaxPrice; //不含税单价
	private String taxRateCode;
	private String taxRate;
	private String currencyCode;
	private String currencyName;
	private String categoryCode;
	private String returnGoodsType;
	private String returnGoodsTypeDesc;
	private String paymentCondition;
	private String settlementWay;
	private Integer billToOrganizationId;
	private Integer receiveToOrganizationId;
	private Integer agreementCount;
	private Integer supplierSiteId;
	private String ladderPriceFlag;
	private BigDecimal ladderQty;

	private List<SrmPoRequisitionHeadersEntity_HI_RO> lineList;

	public List<SrmPoRequisitionHeadersEntity_HI_RO> getLineList() {
		return lineList;
	}

	public void setLineList(List<SrmPoRequisitionHeadersEntity_HI_RO> lineList) {
		this.lineList = lineList;
	}

	public String getReturnGoodsType() {
		return returnGoodsType;
	}

	public void setReturnGoodsType(String returnGoodsType) {
		this.returnGoodsType = returnGoodsType;
	}

	public String getReturnGoodsTypeDesc() {
		return returnGoodsTypeDesc;
	}

	public void setReturnGoodsTypeDesc(String returnGoodsTypeDesc) {
		this.returnGoodsTypeDesc = returnGoodsTypeDesc;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public String getSettlementWay() {
		return settlementWay;
	}

	public void setSettlementWay(String settlementWay) {
		this.settlementWay = settlementWay;
	}

	public Integer getBillToOrganizationId() {
		return billToOrganizationId;
	}

	public void setBillToOrganizationId(Integer billToOrganizationId) {
		this.billToOrganizationId = billToOrganizationId;
	}

	public Integer getReceiveToOrganizationId() {
		return receiveToOrganizationId;
	}

	public void setReceiveToOrganizationId(Integer receiveToOrganizationId) {
		this.receiveToOrganizationId = receiveToOrganizationId;
	}

	public Integer getSupplierId() { return supplierId; }

	public void setSupplierId(Integer supplierId) { this.supplierId = supplierId; }

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public String getTaxRateCode() {
		return taxRateCode;
	}

	public void setTaxRateCode(String taxRateCode) {
		this.taxRateCode = taxRateCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getReqDistributionId() {
		return reqDistributionId;
	}

	public void setReqDistributionId(Integer reqDistributionId) {
		this.reqDistributionId = reqDistributionId;
	}

	public BigDecimal getDistributionProportion() {
		return distributionProportion;
	}

	public void setDistributionProportion(BigDecimal distributionProportion) {
		this.distributionProportion = distributionProportion;
	}

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

	public String getIsPriceEffective() {
		return isPriceEffective;
	}

	public void setIsPriceEffective(String isPriceEffective) {
		this.isPriceEffective = isPriceEffective;
	}

	public List<SrmPoRequisitionHeadersEntity_HI_RO> getFrameworkAgreemenData() {
		return frameworkAgreemenData;
	}

	public void setFrameworkAgreemenData(List<SrmPoRequisitionHeadersEntity_HI_RO> frameworkAgreemenData) {
		this.frameworkAgreemenData = frameworkAgreemenData;
	}

	public String getIsFrameworkAgreemen() {
		return isFrameworkAgreemen;
	}

	public void setIsFrameworkAgreemen(String isFrameworkAgreemen) {
		this.isFrameworkAgreemen = isFrameworkAgreemen;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Date getPlStartDate() {
		return plStartDate;
	}

	public void setPlStartDate(Date plStartDate) {
		this.plStartDate = plStartDate;
	}

	public Date getPlEndDate() {
		return plEndDate;
	}

	public void setPlEndDate(Date plEndDate) {
		this.plEndDate = plEndDate;
	}

	public Date getPhStartDate() {
		return phStartDate;
	}

	public void setPhStartDate(Date phStartDate) {
		this.phStartDate = phStartDate;
	}

	public Date getPhEndDate() {
		return phEndDate;
	}

	public void setPhEndDate(Date phEndDate) {
		this.phEndDate = phEndDate;
	}

	public void setRequisitionHeaderId(Integer requisitionHeaderId) {
		this.requisitionHeaderId = requisitionHeaderId;
	}

	
	public Integer getRequisitionHeaderId() {
		return requisitionHeaderId;
	}

	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}

	
	public String getRequisitionNumber() {
		return requisitionNumber;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}

	
	public String getRequisitionType() {
		return requisitionType;
	}

	public void setRequisitionEmpId(Integer requisitionEmpId) {
		this.requisitionEmpId = requisitionEmpId;
	}

	
	public Integer getRequisitionEmpId() {
		return requisitionEmpId;
	}

	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	
	public Date getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionStatus(String requisitionStatus) {
		this.requisitionStatus = requisitionStatus;
	}

	
	public String getRequisitionStatus() {
		return requisitionStatus;
	}

	public void setApprovalUserId(Integer approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	
	public Integer getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	
	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	
	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	public String getComments() {
		return comments;
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
	public String getTs() {
		return "Y";
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRequisitionTypeDesc() {
		return requisitionTypeDesc;
	}

	public void setRequisitionTypeDesc(String requisitionTypeDesc) {
		this.requisitionTypeDesc = requisitionTypeDesc;
	}

	public String getLineStatusDesc() {
		return lineStatusDesc;
	}

	public void setLineStatusDesc(String lineStatusDesc) {
		this.lineStatusDesc = lineStatusDesc;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
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

	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public BigDecimal getMinPacking() {
		return minPacking;
	}

	public void setMinPacking(BigDecimal minPacking) {
		this.minPacking = minPacking;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(BigDecimal demandQty) {
		this.demandQty = demandQty;
	}

	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getHandleWay() {
		return handleWay;
	}

	public void setHandleWay(String handleWay) {
		this.handleWay = handleWay;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getLineComments() {
		return lineComments;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public Integer getRequisitionLineId() {
		return requisitionLineId;
	}

	public void setRequisitionLineId(Integer requisitionLineId) {
		this.requisitionLineId = requisitionLineId;
	}

    public Date getCreationDateFrom() {
        return creationDateFrom;
    }

    public void setCreationDateFrom(Date creationDateFrom) {
        this.creationDateFrom = creationDateFrom;
    }

    public Date getCreationDateTo() {
        return creationDateTo;
    }

    public void setCreationDateTo(Date creationDateTo) {
        this.creationDateTo = creationDateTo;
    }

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getRequisitionStatusDesc() {
		return requisitionStatusDesc;
	}

	public void setRequisitionStatusDesc(String requisitionStatusDesc) {
		this.requisitionStatusDesc = requisitionStatusDesc;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getInstName() {
		return instName;
	}


	public void setInstName(String instName) {
		this.instName = instName;
	}


	public String getApplyName() {
		return applyName;
	}


	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}


	public String getEmployeeNumber() {
		return employeeNumber;
	}


	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getSourceNumberh() {
		return sourceNumberh;
	}

	public void setSourceNumberh(String sourceNumberh) {
		this.sourceNumberh = sourceNumberh;
	}

	public String getSourceNumberi() {
		return sourceNumberi;
	}

	public void setSourceNumberi(String sourceNumberi) {
		this.sourceNumberi = sourceNumberi;
	}


	public String getHandleWayDesc() {
		return handleWayDesc;
	}

	public void setHandleWayDesc(String handleWayDesc) {
		this.handleWayDesc = handleWayDesc;
	}

	public Integer getAgreementCount() {
		return agreementCount;
	}

	public void setAgreementCount(Integer agreementCount) {
		this.agreementCount = agreementCount;
	}

	public Integer getSupplierSiteId() {
		return supplierSiteId;
	}

	public void setSupplierSiteId(Integer supplierSiteId) {
		this.supplierSiteId = supplierSiteId;
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

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
}
