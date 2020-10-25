package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmTplIndicatorsEntity_HI_RO implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final String COUNT_QUERY_FLAG = "SELECT sd.TPL_DIMENSION_ID  tplDimensionId FROM srm_spm_performance_tpl tpl,srm_spm_tpl_dimension sd  WHERE  tpl.TPL_ID=sd.TPL_ID AND tpl.STATUS='ACTIVE' ";
	
	public static final String QUERY_DIMENSION_INFO_LIST ="SELECT \r\n" + 
			"  sti.TPL_INDICATOR_ID tplIndicatorId,\r\n" + 
			"  sti.TPL_DIMENSION_ID tplDimensionId,\r\n" + 
			"  sti.INDICATOR_WEIGHT dimensionWeight,\r\n" + 
			"  sti.INDICATOR_ID indicatorId,\r\n" + 
			"  sti.Target_value targetValue,\r\n" + 
			"  sti.DESCRIPTION description,\r\n" + 
			"  sti.SCORE_DEDUCTING_LIMIT scoreDeductingLimit,\r\n" + 
			"  sp.INDICATOR_CODE indicatorCode,\r\n" + 
			"  (SELECT sl.meaning FROM saaf_lookup_values sl WHERE sl.lookup_code=sp.INDICATOR_CODE AND sl.lookup_type='SPM_INDICATOR_NAME' )indicatorName,\r\n" +
			"  sp.INDICATOR_TYPE indicatorType,\r\n" + 
			"    (SELECT sl.meaning FROM saaf_lookup_values sl WHERE sl.lookup_code=sp.INDICATOR_TYPE AND sl.lookup_type='SPM_INDICATOR_TYPE' )indicatorTypeName,\r\n" +
			"  sp.SCORE_EXPLAIN scoreExplain,\r\n" + 
			"  sp.INDICATOR_VALUE_TYPE indicatorValueType,\r\n" + 
			"  'Y' postCode,\r\n" +
			"  'Z' postName,\r\n" +
			"  sp.SCORE_DEDUCTING_LIMIT scoregLimit \r\n" + 
			"FROM\r\n" + 
			"  srm_spm_tpl_indicators sti \r\n" +
			"  LEFT JOIN srm_spm_indicators sp \r\n" +
			"    ON sti.INDICATOR_ID = sp.INDICATOR_ID WHERE 1=1";
	
	
	public static final String QUERY_DATAS_INFO_LIST="SELECT COUNT(*) numt FROM(SELECT  SUM(v.RECEIPT_QUANTITY), v.RECEIPT_NUMBER,v.ITEM_ID  FROM srm_gl_receipt_headers_v v, srm_base_items items WHERE v.ITEM_ID = items.ITEM_ID AND v.BUSINESS_DATE >=:evaluateIntervalFrom AND v.BUSINESS_DATE <=:evaluateIntervalTo AND v.SUPPLIER_ID = :supplierId  AND v.ATTRIBUTE1<> -1 AND EXISTS  (SELECT  *  FROM srm_base_categories cate  WHERE cate.CATEGORY_ID =:categoryId AND cate.CATEGORY_CODE = items.CATEGORY_CODE) GROUP BY v.RECEIPT_NUMBER)tt";
	public static final String QUERY_REJECT_INFO_LIST="SELECT COUNT(*) numt FROM(SELECT   SUM(v.REJECT_QUANTITY), v.RECEIPT_NUMBER,v.ITEM_ID FROM srm_gl_receipt_headers_v v, srm_base_items items WHERE v.ITEM_ID = items.ITEM_ID AND v.BUSINESS_DATE >=:evaluateIntervalFrom AND v.BUSINESS_DATE <=:evaluateIntervalTo AND v.SUPPLIER_ID = :supplierId AND EXISTS  (SELECT  *  FROM srm_base_categories cate  WHERE cate.CATEGORY_ID =:categoryId AND cate.CATEGORY_CODE = items.CATEGORY_CODE) GROUP BY v.RECEIPT_NUMBER,v.ITEM_ID HAVING   SUM(v.REJECT_QUANTITY)>0)tt";
	public static final String QUERY_CONCESSION_INFO_LIST="SELECT COUNT(*) numt FROM(SELECT  sum(v.CONCESSION_QUANTITY), v.RECEIPT_NUMBER,v.ITEM_ID  FROM srm_gl_receipt_headers_v v, srm_base_items items WHERE v.ITEM_ID = items.ITEM_ID AND v.BUSINESS_DATE >=:evaluateIntervalFrom AND v.BUSINESS_DATE <=:evaluateIntervalTo AND v.SUPPLIER_ID = :supplierId  AND v.ATTRIBUTE1 not in(-1,0) AND EXISTS  (SELECT  *  FROM srm_base_categories cate  WHERE cate.CATEGORY_ID =:categoryId AND cate.CATEGORY_CODE = items.CATEGORY_CODE) GROUP BY v.RECEIPT_NUMBER)tt";

	//public static final String QUERY_RATECOUNT_INFO_LIST="  SELECT SUM((tt.RECEIPT_QUANTITY-tt.RETURN_DEDUCT_QUANITTY-tt.RETURN_FILL_QUANITTY)/tt.demand_qty) numt FROM(SELECT  v.BUSINESS_DATE, v.RECEIPT_QUANTITY,v.RETURN_DEDUCT_QUANITTY,v.RETURN_FILL_QUANITTY,po.demand_qty, po.demand_date  FROM srm_gl_receipt_headers_v v,SRM_PO_LINES po, srm_spm_request_datas datas  WHERE v.SUPPLIER_ID = datas.VENDOR_ID  AND v.PO_HEADER_ID = datas.PO_HEADER_ID  AND v.po_line_number = po.line_number  AND datas.PO_HEADER_ID = po.po_header_id  AND datas.PO_LINE_ID = po.po_line_id  AND v.SUPPLIER_ID =:supplierId AND datas.ORG_ID =:orgId AND datas.TPL_ID =:tplId AND datas.SCHEME_ID =:schemeId  AND datas.CATEGORY_ID=:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom AND datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo AND v.BUSINESS_DATE<= po.demand_date )tt";
	//public static final String QUERY_RATE_INFO_LIST="  SELECT  COUNT(*) numt FROM srm_gl_receipt_headers_v v, SRM_PO_LINES po, SRM_SPM_REQUEST_DATAS datas  WHERE v.SUPPLIER_ID = datas.VENDOR_ID  AND v.PO_HEADER_ID = datas.PO_HEADER_ID  AND v.po_line_number = po.line_number  AND datas.PO_HEADER_ID = po.po_header_id  AND datas.PO_LINE_ID = po.po_line_id  AND v.SUPPLIER_ID =:supplierId  AND datas.ORG_ID =:orgId  AND datas.TPL_ID =:tplId AND datas.SCHEME_ID =:schemeId AND datas.CATEGORY_ID =:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom AND datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo AND v.BUSINESS_DATE <= po.demand_date";
	//public static final String QUERY_RATECOUNT_INFO_LIST="SELECT  SUM(t.numt) numt FROM (SELECT  SUM(( tt.RECEIPT_QUANTITY - tt.RETURN_DEDUCT_QUANITTY - tt.RETURN_FILL_QUANITTY) / tt.demand_qty ) numt FROM (SELECT  v.BUSINESS_DATE, v.RECEIPT_QUANTITY, v.RETURN_DEDUCT_QUANITTY, v.RETURN_FILL_QUANITTY, po.demand_qty, po.demand_date   FROM srm_gl_receipt_headers_v v, SRM_PO_LINES po,SRM_SPM_REQUEST_DATAS datas  WHERE v.SUPPLIER_ID = datas.VENDOR_ID   AND v.PO_HEADER_ID = datas.PO_HEADER_ID  AND v.po_line_number = po.line_number  AND datas.PO_HEADER_ID = po.po_header_id  AND datas.PO_LINE_ID = po.po_line_id AND datas.SEGMENT1 = 1   AND v.SUPPLIER_ID = :supplierId   AND datas.SCHEME_ID =:schemeId AND datas.CATEGORY_ID =:categoryId  AND v.BUSINESS_DATE <= po.demand_date) tt UNION ALL SELECT SUM(l.delivery_qty / n.quantity) numt  FROM srm_po_notice n, srm_po_delivery_lines l, srm_base_items i, SRM_BASE_CATEGORIES cate  WHERE l.po_notice_id = n.po_notice_id  AND n.item_id = i.item_id  AND i.CATEGORY_CODE = cate.CATEGORY_CODE  AND n.status IN ('CONFIRM', 'NATURAL_CLOSED','SHORTAGE_CLOSED')  AND n.demand_date >=:evaluateIntervalFrom AND n.demand_date <=:evaluateIntervalTo  AND n.supplier_id =:supplierId AND cate.CATEGORY_ID =:categoryId AND l.delivery_qty IS NOT NULL  AND l.LAST_UPDATE_DATE <= n.demand_date  AND EXISTS  (SELECT  *  FROM srm_spm_tpl_categories tplcate, SRM_BASE_CATEGORIES cate  WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID  AND tpl_id =:tplId AND i.CATEGORY_CODE = cate.CATEGORY_CODE)) t ";
	public static final String QUERY_RATECOUNT_INFO_LIST_old="SELECT  ROUND(SUM(tt.numt), 3) numt FROM (SELECT  SUM(IF(ty.numt>1,1,ty.numt)) numt  FROM (SELECT  SUM(IF(tt.demand_qty <= 0,0,tt.RECEIPT_QUANTITY / tt.demand_qty)  ) numt,  tt.po_line_id  FROM  (SELECT   SUM(IFNULL(v.RECEIPT_QUANTITY, 0)) RECEIPT_QUANTITY, tt.demand_qty, tt.po_line_id  FROM (SELECT po.demand_qty, po.po_line_id, po.demand_date, datas.VENDOR_ID, datas.PO_HEADER_ID,datas.SCHEME_ID,datas.CATEGORY_ID,datas.line_number   FROM srm_po_lines po, srm_spm_request_datas datas   WHERE datas.PO_HEADER_ID = po.po_header_id   AND datas.PO_LINE_ID = po.po_line_id  AND po.status IN ('APPROVED', 'NATURAL_CLOSED')  AND datas.SEGMENT1 = 1   AND datas.VENDOR_ID =:supplierId AND datas.SCHEME_ID =:schemeId  AND datas.CATEGORY_ID =:categoryId) tt  LEFT JOIN srm_gl_receipt_headers_v v  ON v.SUPPLIER_ID = tt.VENDOR_ID    AND v.po_line_number = tt.line_number   AND v.PO_HEADER_ID = tt.PO_HEADER_ID  AND v.BUSINESS_DATE <= tt.demand_date   GROUP BY po_line_id) tt   GROUP BY tt.po_line_id) ty  UNION ALL  SELECT  SUM(IF(ty.numt>1,1,ty.numt)) numt  FROM (SELECT SUM( IF( vm.quantity <= 0,0,vm.delivery_qty / vm.quantity)) numt, vm.po_notice_code  FROM (SELECT  tt.po_notice_code, tt.demand_date, tt.quantity, tt.ITEM_ID,tt.CATEGORY_CODE,tt.CATEGORY_ID, SUM(IFNULL(l.delivery_qty, 0)) delivery_qty  FROM  (SELECT  n.po_notice_id, n.po_notice_code,n.demand_date,n.quantity,i.ITEM_ID,cate.CATEGORY_CODE, cate.CATEGORY_ID  FROM srm_po_notice n, srm_base_items i,  srm_base_categories cate   WHERE n.item_id = i.item_id  AND i.CATEGORY_CODE = cate.CATEGORY_CODE  AND n.status IN ('CONFIRMED', 'NATURAL_CLOSED')   AND n.demand_date >=:evaluateIntervalFrom AND n.demand_date <=:evaluateIntervalTo  AND n.supplier_id =:supplierId AND cate.CATEGORY_ID =:categoryId) tt LEFT JOIN srm_po_delivery_lines l ON l.po_notice_id = tt.po_notice_id  AND l.delivery_qty IS NOT NULL  AND tt.demand_date >= DATE_FORMAT(l.LAST_UPDATE_DATE, '%Y-%m-%d')  AND EXISTS (SELECT  *  FROM srm_spm_tpl_categories tplcate,srm_base_categories cate  WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID  AND tpl_id =:tplId AND tt.CATEGORY_CODE = cate.CATEGORY_CODE)  GROUP BY tt.po_notice_code) vm  GROUP BY vm.po_notice_code) ty) tt ";
	public static final String QUERY_RATECOUNT_INFO_LIST= "SELECT\n" +
			"  ROUND(SUM(tt.numt), 3) numt\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    SUM(IF(ty.numt > 1, 1, ty.numt)) numt\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      SUM(\n" +
			"        IF(\n" +
			"          tt.demand_qty <= 0,\n" +
			"          0,\n" +
			"          tt.receipt_quantity / tt.demand_qty\n" +
			"        )\n" +
			"      ) numt,\n" +
			"      tt.po_line_id\n" +
			"    FROM\n" +
			"      (SELECT\n" +
			"        SUM(IFNULL(l.received_qty, 0)) receipt_quantity,\n" +
			"        tt.demand_qty,\n" +
			"        tt.po_line_id\n" +
			"      FROM\n" +
			"        (SELECT\n" +
			"          po.demand_qty,\n" +
			"          po.po_line_id,\n" +
			"          po.demand_date,\n" +
			"          datas.vendor_id,\n" +
			"          datas.po_header_id,\n" +
			"          datas.scheme_id,\n" +
			"          datas.category_id,\n" +
			"          datas.line_number\n" +
			"        FROM\n" +
			"          srm_po_lines po,\n" +
			"          srm_spm_request_datas datas\n" +
			"        WHERE datas.po_header_id = po.po_header_id\n" +
			"          AND datas.po_line_id = po.po_line_id\n" +
			"          AND po.status IN ('OPEN', 'CLOSED')\n" +
			"          AND datas.segment1 = 'BASE_ON_PO'\n" +
			"          AND datas.vendor_id = :supplierId\n" +
			"          AND datas.scheme_id = :schemeId\n" +
			"          AND datas.category_id = :categoryId) tt\n" +
			"        LEFT JOIN srm_po_delivery_lines l\n" +
			"          ON l.po_line_id = tt.po_line_id\n" +
			"          AND l.received_qty IS NOT NULL\n" +
			"          AND tt.demand_date >= DATE_FORMAT(l.last_update_date, '%Y-%m-%d')\n" +
			"          AND EXISTS\n" +
			"          (SELECT\n" +
			"            *\n" +
			"          FROM\n" +
			"            srm_spm_tpl_categories tplcate,\n" +
			"            srm_base_categories cate\n" +
			"          WHERE tplcate.category_id = cate.category_id\n" +
			"            AND tpl_id = :tplId\n" +
			"            AND tt.category_id = cate.category_id)\n" +
			"      GROUP BY po_line_id) tt\n" +
			"    GROUP BY tt.po_line_id) ty\n" +
			"  UNION ALL\n" +
			"  SELECT\n" +
			"    SUM(IF(ty.numt > 1, 1, ty.numt)) numt\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      SUM(\n" +
			"        IF(\n" +
			"          vm.demand_qty <= 0,\n" +
			"          0,\n" +
			"          vm.received_qty / vm.demand_qty\n" +
			"        )\n" +
			"      ) numt,\n" +
			"      vm.po_notice_code\n" +
			"    FROM\n" +
			"      (SELECT\n" +
			"        SUM(IFNULL(l.received_qty, 0)) received_qty,\n" +
			"        tt.demand_qty,\n" +
			"        tt.po_notice_code\n" +
			"      FROM\n" +
			"        (SELECT\n" +
			"          nl.line_id,\n" +
			"          n.po_notice_code,\n" +
			"          pl.demand_date,\n" +
			"          pl.demand_qty,\n" +
			"          bi.item_id,\n" +
			"          cate.category_code,\n" +
			"          cate.category_id\n" +
			"        FROM\n" +
			"          srm_po_notice n,\n" +
			"          srm_po_notice_line nl,\n" +
			"          srm_po_lines pl\n" +
			"          LEFT JOIN srm_base_items bi\n" +
			"            ON pl.item_id = bi.item_id\n" +
			"            AND EXISTS\n" +
			"            (SELECT\n" +
			"              1\n" +
			"            FROM\n" +
			"              saaf_institution si,\n" +
			"              srm_po_headers ph\n" +
			"            WHERE si.inst_id = bi.organization_id\n" +
			"              AND si.inst_type = 'ORGANIZATION'\n" +
			"              AND si.parent_inst_id = ph.org_id\n" +
			"              AND pl.po_header_id = ph.po_header_id)\n" +
			"          LEFT JOIN srm_base_categories cate\n" +
			"            ON cate.category_id = pl.category_id\n" +
			"        WHERE n.po_notice_id = nl.po_notice_id\n" +
			"          AND nl.po_line_id = pl.po_line_id\n" +
			"          AND n.po_notice_status IN ('APPROVED', 'CLOSE')\n" +
			"          AND pl.demand_date >= :evaluateIntervalFrom\n" +
			"          AND pl.demand_date <= :evaluateIntervalTo\n" +
			"          AND n.supplier_id = :supplierId\n" +
			"          AND cate.category_id = :categoryId) tt\n" +
			"        LEFT JOIN srm_po_delivery_lines l\n" +
			"          ON l.notice_line_id = tt.line_id\n" +
			"          AND l.received_qty IS NOT NULL\n" +
			"          AND tt.demand_date >= DATE_FORMAT(l.last_update_date, '%Y-%m-%d')\n" +
			"          AND EXISTS\n" +
			"          (SELECT\n" +
			"            *\n" +
			"          FROM\n" +
			"            srm_spm_tpl_categories tplcate,\n" +
			"            srm_base_categories cate\n" +
			"          WHERE tplcate.category_id = cate.category_id\n" +
			"            AND tpl_id = :tplId\n" +
			"            AND tt.category_code = cate.category_code)\n" +
			"      GROUP BY tt.po_notice_code,\n" +
			"        tt.demand_qty) vm\n" +
			"    GROUP BY vm.po_notice_code) ty) tt\r\n";
	public static final String QUERY_RATECOUNT_INFO_LIST11_old="SELECT  ROUND(SUM(tt.numt), 3) numt FROM (SELECT  SUM(IF(ty.numt > 1, 1, ty.numt)) numt  FROM (SELECT  SUM(IF( tt.demand_qty <= 0,0, tt.RECEIPT_QUANTITY / tt.demand_qty)) numt, tt.po_line_id  FROM (SELECT  SUM(IFNULL(v.RECEIPT_QUANTITY, 0)) RECEIPT_QUANTITY, tt.demand_qty,tt.po_line_id  FROM(SELECT  po.demand_qty, po.po_line_id, po.demand_date,datas.VENDOR_ID, datas.PO_HEADER_ID,datas.SCHEME_ID,datas.CATEGORY_ID, datas.line_number   FROM  srm_po_lines po, srm_spm_request_datas datas  WHERE datas.PO_HEADER_ID = po.po_header_id   AND datas.PO_LINE_ID = po.po_line_id  AND datas.SEGMENT1 = 1  AND po.status = 'SHORTAGE_CLOSED'   AND datas.VENDOR_ID =:supplierId  AND datas.SCHEME_ID =:schemeId  AND datas.CATEGORY_ID =:categoryId) tt  LEFT JOIN srm_gl_receipt_headers_v v  ON v.SUPPLIER_ID = tt.VENDOR_ID  AND v.po_line_number = tt.line_number  AND v.PO_HEADER_ID = tt.PO_HEADER_ID   AND v.BUSINESS_DATE <= tt.demand_date   GROUP BY po_line_id) tt   GROUP BY tt.po_line_id) ty  UNION ALL  SELECT  SUM(IF(ty.numt > 1, 1, ty.numt)) numt FROM (SELECT  SUM(IF( vm.quantity <= 0, 0, vm.delivery_qty / vm.delivery_qtyt ) ) numt, vm.po_notice_code  FROM  (SELECT  tt.po_notice_code, tt.demand_date, tt.quantity,tt.ITEM_ID, tt.CATEGORY_CODE, tt.CATEGORY_ID,  SUM(IFNULL(tt.delivery_qty, 0)) delivery_qty, tt.delivery_qtyt delivery_qtyt  FROM (SELECT  n.po_notice_id, n.po_notice_code, n.demand_date, n.quantity,n.delivery_qty delivery_qtyt, i.ITEM_ID, cate.CATEGORY_CODE, cate.CATEGORY_ID, l.delivery_qty  FROM srm_po_notice n, srm_po_delivery_lines l, srm_base_items i, srm_base_categories cate  WHERE n.item_id = i.item_id  AND l.po_notice_id = n.po_notice_id   AND i.CATEGORY_CODE = cate.CATEGORY_CODE  AND l.delivery_qty IS NOT NULL  AND n.status = 'SHORTAGE_CLOSED'  AND n.demand_date >=:evaluateIntervalFrom  AND n.demand_date <=:evaluateIntervalTo   AND n.supplier_id =:supplierId   AND cate.CATEGORY_ID =:categoryId AND n.demand_date >= DATE_FORMAT(l.LAST_UPDATE_DATE, '%Y-%m-%d')   AND EXISTS  (SELECT *  FROM srm_spm_tpl_categories tplcate,  srm_base_categories cat  WHERE tplcate.CATEGORY_ID = cat.CATEGORY_ID   AND tpl_id =:tplId  AND cate.CATEGORY_CODE = cat.CATEGORY_CODE)) tt  GROUP BY tt.po_notice_code) vm  GROUP BY vm.po_notice_code) ty) tt ";

	public static final String QUERY_RATECOUNT_INFO_LIST11 = "SELECT\n" +
			"  ROUND(SUM(tt.numt), 3) numt\n" +
			"FROM\n" +
			"  (\n" +
			"  SELECT\n" +
			"    SUM(IF(ty.numt > 1, 1, ty.numt)) numt\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      SUM(\n" +
			"        IF(\n" +
			"          vm.quantity <= 0,\n" +
			"          0,\n" +
			"          vm.delivery_qty / vm.delivery_qtyt\n" +
			"        )\n" +
			"      ) numt,\n" +
			"      vm.po_notice_code\n" +
			"    FROM\n" +
			"      (SELECT\n" +
			"        tt.po_notice_code,\n" +
			"        tt.demand_date,\n" +
			"        tt.quantity,\n" +
			"        tt.ITEM_ID,\n" +
			"        tt.CATEGORY_CODE,\n" +
			"        tt.CATEGORY_ID,\n" +
			"        SUM(IFNULL(tt.delivery_qty, 0)) delivery_qty,\n" +
			"        tt.delivery_qtyt delivery_qtyt\n" +
			"      FROM\n" +
			"        (SELECT\n" +
			"          n.po_notice_id,\n" +
			"          n.po_notice_code,\n" +
			"          n.demand_date,\n" +
			"          n.quantity,\n" +
			"          n.delivery_qty delivery_qtyt,\n" +
			"          i.ITEM_ID,\n" +
			"          cate.CATEGORY_CODE,\n" +
			"          cate.CATEGORY_ID,\n" +
			"          l.delivery_qty\n" +
			"        FROM\n" +
			"          srm_po_notice n,\n" +
			"          srm_po_delivery_lines l,\n" +
			"          srm_base_items i,\n" +
			"          srm_base_categories cate\n" +
			"        WHERE n.item_id = i.item_id\n" +
			"          AND l.po_notice_id = n.po_notice_id\n" +
			"          AND i.CATEGORY_id = cate.CATEGORY_id\n" +
			"          AND l.delivery_qty IS NOT NULL\n" +
			"          AND n.status = 'SHORTAGE_CLOSED'\n" +
			"          AND n.demand_date >= :evaluateIntervalFrom\n" +
			"          AND n.demand_date <= :evaluateIntervalTo\n" +
			"          AND n.supplier_id = :supplierId\n" +
			"          AND cate.CATEGORY_ID = :categoryId\n" +
			"          AND n.demand_date >= DATE_FORMAT(l.LAST_UPDATE_DATE, '%Y-%m-%d')\n" +
			"          AND EXISTS\n" +
			"          (SELECT\n" +
			"            *\n" +
			"          FROM\n" +
			"            srm_spm_tpl_categories tplcate,\n" +
			"            srm_base_categories cat\n" +
			"          WHERE tplcate.CATEGORY_ID = cat.CATEGORY_ID\n" +
			"            AND tpl_id = :tplId\n" +
			"            AND cate.CATEGORY_CODE = cat.CATEGORY_CODE)) tt\n" +
			"      GROUP BY tt.po_notice_code) vm\n" +
			"    GROUP BY vm.po_notice_code) ty) tt\r\n";

	public static final String QUERY_RATE_INFO_LIST_old="SELECT  SUM(tt.numt) numt FROM (SELECT   COUNT(*) numt FROM srm_po_lines po, srm_spm_request_datas datas  WHERE datas.PO_HEADER_ID = po.po_header_id   AND datas.PO_LINE_ID = po.po_line_id AND po.status IN ('APPROVED','NATURAL_CLOSED') AND datas.SEGMENT1 = 1   AND datas.VENDOR_ID =:supplierId AND datas.SCHEME_ID =:schemeId AND datas.CATEGORY_ID =:categoryId UNION ALL  SELECT  COUNT(*) numt  FROM (SELECT  tt.po_notice_code, SUM(IFNULL(l.delivery_qty, 0))   FROM(SELECT  n.po_notice_code, n.item_id,n.demand_date,n.po_notice_id,cate.CATEGORY_CODE, cate.CATEGORY_ID  FROM srm_po_notice n,srm_base_items i, srm_base_categories cate  WHERE n.item_id = i.item_id  AND i.CATEGORY_CODE = cate.CATEGORY_CODE AND n.status IN ('CONFIRMED','NATURAL_CLOSED')   AND n.demand_date >=:evaluateIntervalFrom AND n.demand_date <=:evaluateIntervalTo AND n.supplier_id =:supplierId  AND cate.CATEGORY_ID =:categoryId) tt   LEFT JOIN srm_po_delivery_lines l  ON l.po_notice_id = tt.po_notice_id  AND l.delivery_qty IS NOT NULL  AND tt.demand_date>= DATE_FORMAT(l.LAST_UPDATE_DATE, '%Y-%m-%d')  AND EXISTS (SELECT  *  FROM srm_spm_tpl_categories tplcate, srm_base_categories cate   WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID  AND tpl_id =:tplId AND tt.CATEGORY_CODE = cate.CATEGORY_CODE) GROUP BY tt.po_notice_code) p) tt ";

	public static final String QUERY_RATE_INFO_LIST = "SELECT\n" +
			"  SUM(tt.numt) numt\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    COUNT(*) numt\n" +
			"  FROM\n" +
			"    srm_po_lines po,\n" +
			"    srm_spm_request_datas datas\n" +
			"  WHERE datas.po_header_id = po.po_header_id\n" +
			"    AND datas.po_line_id = po.po_line_id\n" +
			"    AND po.status IN ('OPEN', 'CLOSED')\n" +
			"    AND datas.segment1 = 'BASE_ON_PO'\n" +
			"    AND datas.vendor_id = :supplierId\n" +
			"    AND datas.scheme_id = :schemeId\n" +
			"    AND datas.category_id = :categoryId\n" +
			"  UNION\n" +
			"  ALL\n" +
			"  SELECT\n" +
			"    COUNT(*) numt\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      tt.po_notice_code,\n" +
			"      SUM(IFNULL(l.received_qty, 0))\n" +
			"    FROM\n" +
			"      (SELECT\n" +
			"        n.po_notice_code,\n" +
			"        bi.item_id,\n" +
			"        pl.demand_date,\n" +
			"        nl.line_id,\n" +
			"        cate.category_code,\n" +
			"        cate.category_id\n" +
			"      FROM\n" +
			"        srm_po_notice n,\n" +
			"        srm_po_notice_line nl,\n" +
			"        srm_po_lines pl\n" +
			"        LEFT JOIN srm_base_items bi\n" +
			"          ON pl.item_id = bi.item_id\n" +
			"          AND EXISTS\n" +
			"          (SELECT\n" +
			"            1\n" +
			"          FROM\n" +
			"            saaf_institution si,\n" +
			"            srm_po_headers ph\n" +
			"          WHERE si.inst_id = bi.organization_id\n" +
			"            AND si.inst_type = 'ORGANIZATION'\n" +
			"            AND si.parent_inst_id = ph.org_id\n" +
			"            AND pl.po_header_id = ph.po_header_id)\n" +
			"        LEFT JOIN srm_base_categories cate\n" +
			"          ON cate.category_id = pl.category_id\n" +
			"      WHERE n.po_notice_id = nl.po_notice_id\n" +
			"        AND nl.po_line_id = pl.po_line_id\n" +
			"        AND n.po_notice_status IN ('APPROVED', 'CLOSE')\n" +
			"        AND pl.demand_date >= :evaluateIntervalFrom\n" +
			"        AND pl.demand_date <= :evaluateIntervalTo\n" +
			"        AND n.supplier_id = :supplierId\n" +
			"        AND cate.category_id = :categoryId) tt\n" +
			"      LEFT JOIN srm_po_delivery_lines l\n" +
			"        ON l.po_notice_id = tt.line_id\n" +
			"        AND l.received_qty IS NOT NULL\n" +
			"        AND tt.demand_date >= DATE_FORMAT(l.LAST_UPDATE_DATE, '%Y-%m-%d')\n" +
			"        AND EXISTS\n" +
			"        (SELECT\n" +
			"          *\n" +
			"        FROM\n" +
			"          srm_spm_tpl_categories tplcate,\n" +
			"          srm_base_categories cate\n" +
			"        WHERE tplcate.category_id = cate.category_id\n" +
			"          AND tpl_id = :tplId\n" +
			"          AND tt.category_code = cate.category_code)\n" +
			"    GROUP BY tt.po_notice_code) p) tt\r\n";

	public static final String QUERY_RATE_INFO_LIST11_old="SELECT  SUM(tt.numt) numt FROM(SELECT  COUNT(*) numt  FROM  srm_po_lines po, srm_spm_request_datas datas  WHERE datas.PO_HEADER_ID = po.po_header_id  AND datas.PO_LINE_ID = po.po_line_id   AND po.status = 'SHORTAGE_CLOSED'  AND datas.SEGMENT1 = 1    AND po.delivery_qty<>0 AND datas.VENDOR_ID = :supplierId  AND datas.SCHEME_ID = :schemeId   AND datas.CATEGORY_ID = :categoryId  UNION ALL SELECT  COUNT(*) numt FROM(SELECT  tt.po_notice_code, SUM(IFNULL(tt.delivery_qty, 0))  FROM (SELECT   n.po_notice_code, n.item_id, n.demand_date,  n.po_notice_id, l.delivery_qty, cate.CATEGORY_CODE,   cate.CATEGORY_ID  FROM srm_po_notice n, srm_base_items i,  srm_po_delivery_lines l,  srm_base_categories cate   WHERE n.item_id = i.item_id   AND l.po_notice_id = n.po_notice_id  AND i.CATEGORY_CODE = cate.CATEGORY_CODE   AND n.status = 'SHORTAGE_CLOSED'   AND n.demand_date >=:evaluateIntervalFrom  AND n.demand_date <=:evaluateIntervalTo  AND n.supplier_id =:supplierId AND l.delivery_qty IS NOT NULL   AND cate.CATEGORY_ID =:categoryId  AND EXISTS  (SELECT  *   FROM srm_spm_tpl_categories tplcate, srm_base_categories cat  WHERE tplcate.CATEGORY_ID = cat.CATEGORY_ID AND tpl_id =:tplId AND cate.CATEGORY_CODE = cat.CATEGORY_CODE)) tt  GROUP BY tt.po_notice_code) p ) tt";

	public static final String QUERY_RATE_INFO_LIST11="SELECT\n" +
			"  SUM(tb3.numt) numt\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    COUNT(*) numt\n" +
			"  FROM\n" +
			"    (SELECT\n" +
			"      tb.po_notice_code,\n" +
			"      SUM(IFNULL(tb.received_qty, 0))\n" +
			"    FROM\n" +
			"      (SELECT\n" +
			"        pn.po_notice_code,\n" +
			"        pdl.item_id,\n" +
			"        po.demand_date,\n" +
			"        pn.po_notice_id,\n" +
			"        pdl.received_qty,\n" +
			"        bc.category_code,\n" +
			"        bc.category_id\n" +
			"      FROM\n" +
			"        srm_po_delivery_lines pdl,\n" +
			"        srm_po_lines po,\n" +
			"        srm_po_notice pn,\n" +
			"        srm_po_notice_line pnl,\n" +
			"        srm_base_categories bc\n" +
			"      WHERE pdl.po_line_id = po.po_line_id\n" +
			"        AND pdl.notice_line_id = line_id\n" +
			"        AND pn.po_notice_id = pnl.po_notice_id\n" +
			"        AND pdl.category_id = bc.category_id\n" +
			"        AND po.demand_date >= :evaluateIntervalFrom\n" +
			"        AND po.demand_date <= :evaluateIntervalTo\n" +
			"        AND pn.supplier_id = :supplierId\n" +
			"        AND pdl.received_qty IS NOT NULL\n" +
			"        AND bc.category_id = :categoryId\n" +
			"        AND EXISTS\n" +
			"        (SELECT\n" +
			"          *\n" +
			"        FROM\n" +
			"          srm_spm_tpl_categories tplcate,\n" +
			"          srm_base_categories cat\n" +
			"        WHERE tplcate.category_id = cat.category_id\n" +
			"          AND tpl_id = :tplId\n" +
			"          AND bc.category_code = cat.category_code)) tb\n" +
			"    GROUP BY tt.po_notice_code) tb2) tb3\r\n";

	public static final String QUERY_TPL_INDICATORS_LIST =
			"SELECT Spt.Tpl_Id             AS tplId           \n" +
			"      ,Std.Tpl_Dimension_Id   AS tplDimensionId  \n" +
			"      ,Std.Evaluate_Dimension AS evaluateDimension\n" +
			"      ,Slv1.Meaning           AS evaluateDimensionName\n" +
			"      ,Std.Dimension_Weight   AS dimensionWeight  \n" +
			"      ,Sti.Tpl_Indicator_Id   AS tplIndicatorId  \n" +
			"      ,Sti.Indicator_Id       AS indicatorId      \n" +
			"      ,Spi.Indicator_Code     AS indicatorCode    \n" +
			"      ,Spi.Score_Explain      AS scoreExplain     \n" +
			"      ,Sti.Indicator_Weight   AS indicatorWeight\n" +
			"  FROM Srm_Spm_Performance_Tpl Spt\n" +
			"      ,Srm_Spm_Tpl_Dimension   Std\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Std.Evaluate_Dimension = Slv1.Lookup_Code\n" +
			"   AND Slv1.Lookup_Type = 'SPM_INDICATOR_DIMENSION',\n" +
			" Srm_Spm_Tpl_Indicators Sti, Srm_Spm_Indicators Spi\n" +
			" WHERE Spt.Tpl_Id = Std.Tpl_Id\n" +
			"   AND Std.Tpl_Dimension_Id = Sti.Tpl_Dimension_Id\n" +
			"   AND Sti.Indicator_Id = Spi.Indicator_Id\n";

	private Integer tplDimensionId; //模板维度ID
    private Integer tplIndicatorId; //绩效模板ID
    private Integer indicatorId;
    private String evaluateDimension; 
    private BigDecimal dimensionWeight; //维度权重
    private BigDecimal scoreDeductingLimit;
    private BigDecimal scoregLimit;
    private String description; //说明、备注
    
    private String indicatorCode;
    private String indicatorName;
    private String indicatorType;
    private String indicatorTypeName;
    private String indicatorValueType;
    private String scoreExplain;
    private BigDecimal targetValue;
    
    private String postCode;
    private String postName;
    
    private BigDecimal numt;

    //add by wujiali
	private Integer tplId;
	private String evaluateDimensionName;
	private BigDecimal indicatorWeight;
    
	public Integer getTplDimensionId() {
		return tplDimensionId;
	}
	public void setTplDimensionId(Integer tplDimensionId) {
		this.tplDimensionId = tplDimensionId;
	}
	public String getEvaluateDimension() {
		return evaluateDimension;
	}
	public void setEvaluateDimension(String evaluateDimension) {
		this.evaluateDimension = evaluateDimension;
	}
	public BigDecimal getDimensionWeight() {
		return dimensionWeight;
	}
	public void setDimensionWeight(BigDecimal dimensionWeight) {
		this.dimensionWeight = dimensionWeight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIndicatorCode() {
		return indicatorCode;
	}
	public void setIndicatorCode(String indicatorCode) {
		this.indicatorCode = indicatorCode;
	}
	public String getIndicatorName() {
		return indicatorName;
	}
	public void setIndicatorName(String indicatorName) {
		this.indicatorName = indicatorName;
	}
	public String getIndicatorType() {
		return indicatorType;
	}
	public void setIndicatorType(String indicatorType) {
		this.indicatorType = indicatorType;
	}
	public String getIndicatorTypeName() {
		return indicatorTypeName;
	}
	public void setIndicatorTypeName(String indicatorTypeName) {
		this.indicatorTypeName = indicatorTypeName;
	}
	public String getIndicatorValueType() {
		return indicatorValueType;
	}
	public void setIndicatorValueType(String indicatorValueType) {
		this.indicatorValueType = indicatorValueType;
	}
	public String getScoreExplain() {
		return scoreExplain;
	}
	public void setScoreExplain(String scoreExplain) {
		this.scoreExplain = scoreExplain;
	}
	public Integer getTplIndicatorId() {
		return tplIndicatorId;
	}
	public void setTplIndicatorId(Integer tplIndicatorId) {
		this.tplIndicatorId = tplIndicatorId;
	}
	public Integer getIndicatorId() {
		return indicatorId;
	}
	public void setIndicatorId(Integer indicatorId) {
		this.indicatorId = indicatorId;
	}
	public BigDecimal getScoreDeductingLimit() {
		return scoreDeductingLimit;
	}
	public void setScoreDeductingLimit(BigDecimal scoreDeductingLimit) {
		this.scoreDeductingLimit = scoreDeductingLimit;
	}
	public BigDecimal getScoregLimit() {
		return scoregLimit;
	}
	public void setScoregLimit(BigDecimal scoregLimit) {
		this.scoregLimit = scoregLimit;
	}
	public BigDecimal getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public BigDecimal getNumt() {
		return numt;
	}
	public void setNumt(BigDecimal numt) {
		this.numt = numt;
	}

	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	public String getEvaluateDimensionName() {
		return evaluateDimensionName;
	}

	public void setEvaluateDimensionName(String evaluateDimensionName) {
		this.evaluateDimensionName = evaluateDimensionName;
	}

	public BigDecimal getIndicatorWeight() {
		return indicatorWeight;
	}

	public void setIndicatorWeight(BigDecimal indicatorWeight) {
		this.indicatorWeight = indicatorWeight;
	}
}
