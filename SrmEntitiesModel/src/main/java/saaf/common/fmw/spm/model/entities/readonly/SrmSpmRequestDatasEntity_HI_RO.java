package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmSpmRequestDatasEntity_HI_RO implements Serializable{

	private static final long serialVersionUID = 1L;

	public final static String QUERY_SUPPLIER_INFO_LIST = "SELECT\n" +
			"  tb.po_header_id poHeaderId,\n" +
			"  tb.po_number poNumber,\n" +
			"  tb.org_id orgId,\n" +
			"  tb.supplier_id vendorId,\n" +
			"  tb.return_goods_type deliveryType,\n" +
			"  tb.po_line_id poLineId,\n" +
			"  tb.demand_date demanDate,\n" +
			"  tb.item_id itemId,\n" +
			"  tb.line_number lineNumber,\n" +
			"  tb.category_id categoryId,\n" +
			"  tb.category_code categoryCode\n" +
			"FROM\n" +
			"  (SELECT\n" +
			"    header.po_header_id,\n" +
			"    header.po_number,\n" +
			"    header.org_id,\n" +
			"    header.supplier_id,\n" +
			"    header.return_goods_type,\n" +
			"    line.po_line_id,\n" +
			"    line.demand_date,\n" +
			"    line.item_id,\n" +
			"    line.line_number,\n" +
			"    line.category_id,\n" +
			"    bc.category_code\n" +
			"  FROM\n" +
			"    srm_po_headers header,\n" +
			"    srm_po_lines line,\n" +
			"    srm_base_items bi,\n" +
			"    srm_base_categories bc,\n" +
			"    srm_spm_tpl_categories tplcate\n" +
			"  WHERE header.po_header_id = line.po_header_id\n" +
			"    AND line.item_id = bi.item_id\n" +
			"    AND EXISTS\n" +
			"    (SELECT\n" +
			"      1\n" +
			"    FROM\n" +
			"      saaf_institution si,\n" +
			"      srm_po_headers ph\n" +
			"    WHERE si.inst_id = bi.organization_id\n" +
			"      AND si.inst_type = 'ORGANIZATION'\n" +
			"      AND si.parent_inst_id = ph.org_id\n" +
			"      AND ph.po_header_id = line.po_header_id)\n" +
			"    AND line.category_id = bc.category_id\n" +
			"    AND header.return_goods_type = 'BASE_ON_PO' -- 采购订单回货\n" +
			"    AND header.status IN ('APPROVED', 'CLOSED')\n" +
			"    AND line.status IN ('OPEN', 'CLOSED')\n" +
			"    AND header.po_doc_type = 'ORDER'\n" +
			"    AND line.category_id = tplcate.category_id\n" +
			"    AND tplcate.tpl_id = :tplId\n" +
			"    AND line.demand_date >= :evaluateIntervalFrom\n" +
			"    AND line.demand_date <= :evaluateIntervalTo\n" +
			"    AND header.org_id = :orgId\n" +
			"  UNION\n" +
			"  ALL\n" +
			"  SELECT\n" +
			"    header.po_header_id,\n" +
			"    header.po_number,\n" +
			"    header.org_id,\n" +
			"    header.supplier_id,\n" +
			"    header.return_goods_type,\n" +
			"    line.po_line_id,\n" +
			"    line.demand_date,\n" +
			"    line.item_id,\n" +
			"    line.line_number,\n" +
			"    line.category_id,\n" +
			"    bc.category_code\n" +
			"  FROM\n" +
			"    srm_po_headers header,\n" +
			"    srm_po_lines line,\n" +
			"    srm_po_notice_line nline,\n" +
			"    srm_po_notice nheader,\n" +
			"    srm_base_items bi,\n" +
			"    srm_base_categories bc,\n" +
			"    srm_spm_tpl_categories tplcate\n" +
			"  WHERE header.po_header_id = line.po_header_id\n" +
			"    AND nline.po_header_id = header.po_header_id\n" +
			"    AND nline.po_line_id = line.po_line_id\n" +
			"    AND nline.po_notice_id = nheader.po_notice_id\n" +
			"    AND line.item_id = bi.item_id\n" +
			"    AND EXISTS\n" +
			"    (SELECT\n" +
			"      1\n" +
			"    FROM\n" +
			"      saaf_institution si,\n" +
			"      srm_po_headers ph\n" +
			"    WHERE si.inst_id = bi.organization_id\n" +
			"      AND si.inst_type = 'ORGANIZATION'\n" +
			"      AND si.parent_inst_id = ph.org_id\n" +
			"      AND ph.po_header_id = line.po_header_id)\n" +
			"    AND line.category_id = bc.category_id\n" +
			"    AND header.return_goods_type = 'BASE_ON_NOTICE' -- 送货通知回货\n" +
			"    AND nheader.po_notice_status IN ('APPROVED', 'CLOSE')\n" +
			"    AND nline.line_status IN ('OPEN', 'CLOSE')\n" +
			"    AND nline.feedback_status = 'CONFIRMED'\n" +
			"    AND header.po_doc_type = 'ORDER'\n" +
			"    AND line.category_id = tplcate.category_id\n" +
			"    AND tplcate.tpl_id = :tplId\n" +
			"    AND line.demand_date >= :evaluateIntervalFrom\n" +
			"    AND line.demand_date <= :evaluateIntervalTo\n" +
			"    AND header.org_id = :orgId) tb\n" +
			"WHERE 1 = 1\r\n";

	public final static String QUERY_SUPPLIER_INFO_LIST_old ="SELECT \r\n" +
			"  tt.po_header_id poHeaderId,\r\n" + 
			"  tt.po_number poNumber,\r\n" + 
			"  tt.supply_inst_id orgId,\r\n" + 
			"  tt.po_line_id poLineId,\r\n" + 
			"  tt.demand_date demanDate,\r\n" + 
			"  tt.DELIVERY_TYPE deliveryType,\r\n" + 
			"  tt.supplier_id vendorId,\r\n" + 
			"  tt.item_id itemId,\r\n" + 
			"  tt.line_number lineNumber,\r\n" + 
			"  tt.CATEGORY_ID categoryId \r\n" + 
			"FROM\r\n" + 
			"  (SELECT \r\n" + 
			"    header.`po_header_id`,\r\n" + 
			"    header.`po_number`,\r\n" + 
			"    header.`supply_inst_id`,\r\n" + 
			"    line.`po_line_id`,\r\n" + 
			"    line.`demand_date`,\r\n" + 
			"    line.`DELIVERY_TYPE`,\r\n" + 
			"    line.`item_id`,\r\n" + 
			"    line.`line_number`,\r\n" + 
			"    header.`supplier_id`,\r\n" + 
			"    items.`CATEGORY_CODE`,\r\n" + 
			"    cate.`CATEGORY_ID` \r\n" + 
			"  FROM\r\n" + 
			"    srm_po_headers header,\r\n" +
			"    srm_po_lines line,\r\n" +
			"    srm_base_items items,\r\n" +
			"    srm_base_categories cate \r\n" +
			"  WHERE header.`po_header_id` = line.`po_header_id` \r\n" + 
			"    AND items.`ITEM_ID` = line.`item_id` \r\n" + 
			"    AND items.`CATEGORY_CODE` = cate.`CATEGORY_CODE` \r\n" + 
			"    AND line.`DELIVERY_TYPE` = 1  and line.status in('APPROVED','NATURAL_CLOSED','SHORTAGE_CLOSED')\r\n" + 
			"    AND line.demand_date >= :evaluateIntervalFrom \r\n" + 
			"    AND line.demand_date <= :evaluateIntervalTo \r\n" + 
			"    AND EXISTS \r\n" + 
			"    (SELECT \r\n" + 
			"      * \r\n" + 
			"    FROM\r\n" + 
			"      srm_spm_tpl_categories tplcate,\r\n" +
			"      srm_base_categories cate \r\n" +
			"    WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID \r\n" + 
			"      AND tpl_id = :tplId \r\n" + 
			"      AND items.CATEGORY_CODE = cate.CATEGORY_CODE) \r\n" + 
			"    UNION\r\n" + 
			"    ALL \r\n" + 
			"    SELECT \r\n" + 
			"      header.`po_header_id`,\r\n" + 
			"      header.`po_number`,\r\n" + 
			"      header.`supply_inst_id`,\r\n" + 
			"      line.`po_line_id`,\r\n" + 
			"      nheader.`demand_date`,\r\n" + 
			"      line.`DELIVERY_TYPE`,\r\n" + 
			"      line.`item_id`,\r\n" + 
			"      line.`line_number`,\r\n" + 
			"      header.`supplier_id`,\r\n" + 
			"      items.`CATEGORY_CODE`,\r\n" + 
			"      cate.`CATEGORY_ID` \r\n" + 
			"    FROM\r\n" + 
			"      srm_po_headers header,\r\n" +
			"      srm_po_lines line,\r\n" +
			"      srm_po_notice_line nline,srm_po_notice nheader,\r\n" + 
			"      srm_base_items items,\r\n" +
			"      srm_base_categories cate \r\n" +
			"    WHERE header.`po_header_id` = line.`po_header_id` \r\n" + 
			"      AND nline.po_header_id = header.`po_header_id` \r\n" + 
			"      AND nline.po_line_id = line.`po_line_id` and nline.po_notice_id =nheader.po_notice_id \r\n" + 
			"      AND items.`ITEM_ID` = line.`item_id` \r\n" + 
			"      AND items.`CATEGORY_CODE` = cate.`CATEGORY_CODE` \r\n" + 
			"      AND line.`DELIVERY_TYPE` = 2 and nheader.status in('APPROVED','NATURAL_CLOSED','SHORTAGE_CLOSED')\r\n" + 
			"      AND nheader.demand_date >= :evaluateIntervalFrom \r\n" + 
			"      AND nheader.demand_date <= :evaluateIntervalTo \r\n" + 
			"      AND EXISTS \r\n" + 
			"      (SELECT \r\n" + 
			"        * \r\n" + 
			"      FROM\r\n" + 
			"        srm_spm_tpl_categories tplcate,\r\n" +
			"        srm_base_categories cate \r\n" +
			"      WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID \r\n" + 
			"        AND tpl_id = :tplId \r\n" + 
			"        AND items.CATEGORY_CODE = cate.CATEGORY_CODE)) tt  where 1=1";
	
	public final static String QUERY_COUNT_INFO_LIST="SELECT  COUNT(*) nunm, datas.VENDOR_ID vendorId, datas.CATEGORY_ID categoryId FROM srm_spm_request_datas datas WHERE datas.scheme_id =:schemeId AND datas.ORG_ID=:orgId AND datas.TPL_ID=:tplId AND datas.CATEGORY_ID=:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom and datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo  GROUP BY datas.VENDOR_ID,datas.CATEGORY_ID ";
	public final static String QUERY_COUNT_ITEM_LIST="SELECT  COUNT(datas.`ITEM_ID`),datas.`ITEM_ID` itemId,datas.`VENDOR_ID` vendorId FROM srm_spm_request_datas datas WHERE datas.scheme_id =:schemeId AND datas.ORG_ID=:orgId AND datas.TPL_ID=:tplId AND datas.CATEGORY_ID=:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom and datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo  GROUP BY datas.`VENDOR_ID`,datas.`ITEM_ID`";
	public final static String QUERY_COUNT_SUP_INFO="SELECT  datas.`VENDOR_ID` vendorId,SUM(line.`RECEIPT_QUANTITY`)/SUM(line.`RECEIPT_QUANTITY`*line.`UNIT_PRICE`)*100 score FROM srm_spm_receipt_headers header  LEFT JOIN srm_spm_receipt_lines line   ON header.`RECEIPT_ID` = line.`RECEIPT_ID`  LEFT JOIN SRM_SPM_REQUEST_DATAS datas  ON datas.`PO_HEADER_ID` = line.`PO_HEADER_ID` AND line.`po_line_number` =datas.`line_number`   WHERE datas.scheme_id =:schemeId AND datas.ORG_ID=:orgId AND datas.TPL_ID=:tplId AND datas.CATEGORY_ID=:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom and datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo AND  datas.`ITEM_ID`=:itemId  GROUP BY  datas.`VENDOR_ID`";
	public final static String QUERY_COUNT_ITEM_INFO="SELECT tt.item_id itemId,tt.VENDOR_ID vendorId,AVG(tt.UNIT_PRICE) score FROM(SELECT  datas.`ITEM_ID`,datas.`VENDOR_ID`,datas.`CATEGORY_ID`,line.`UNIT_PRICE` FROM srm_spm_receipt_headers header  LEFT JOIN srm_spm_receipt_lines line  ON header.`RECEIPT_ID` = line.`RECEIPT_ID`   LEFT JOIN SRM_SPM_REQUEST_DATAS datas  ON datas.`PO_HEADER_ID` = line.`PO_HEADER_ID` AND line.`po_line_number` =datas.`line_number`   WHERE datas.scheme_id =:schemeId AND datas.ORG_ID=:orgId AND datas.TPL_ID=:tplId AND datas.CATEGORY_ID=:categoryId AND datas.EVALUATE_INTERVAL_FROM >=:evaluateIntervalFrom and datas.EVALUATE_INTERVAL_TO <=:evaluateIntervalTo AND datas.`ITEM_ID`=:itemId)tt GROUP BY tt.item_id,tt.VENDOR_ID ";
	
	
	//public final static String  QUERY_COUNT_ITEM_INFO1="SELECT  SUM(ROUND(t.n/r.m*100,3)) score,t.ITEM_ID itemId FROM (SELECT v.`BUSINESS_DATE`,SUM( v.`RECEIPT_QUANTITY` * pr.`UNIT_PRICE`) n, datas.`VENDOR_ID`, datas.`ITEM_ID`  FROM  srm_gl_receipt_headers_v v, SRM_SPM_REQUEST_DATAS datas, srm_gl_price_range pr  WHERE v.`po_line_number` = datas.`line_number`  AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`  AND datas.`VENDOR_ID` = pr.`SUPPLIER_ID`  AND datas.`ITEM_ID` = pr.`ITEM_ID`   AND datas.`SEGMENT1`=1 AND v.`BUSINESS_DATE` BETWEEN pr.`START_DATE`   AND pr.`END_DATE`   AND pr.`STATUS` = 1  AND datas.`SCHEME_ID` =:schemeId AND datas.`CATEGORY_ID` =:categoryId  GROUP BY datas.`VENDOR_ID`,datas.`ITEM_ID`) t,(SELECT  SUM(v.`RECEIPT_QUANTITY`* pr.`UNIT_PRICE`) m FROM srm_gl_receipt_headers_v v,SRM_SPM_REQUEST_DATAS datas, srm_gl_price_range pr  WHERE v.`po_line_number` = datas.`line_number`  AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`  AND datas.`VENDOR_ID` = pr.`SUPPLIER_ID`  AND datas.`ITEM_ID` = pr.`ITEM_ID` AND datas.`SEGMENT1`=1  AND v.`BUSINESS_DATE` BETWEEN pr.`START_DATE`  AND pr.`END_DATE`  AND pr.`STATUS` = 1  AND datas.`SCHEME_ID` =:schemeId AND datas.`CATEGORY_ID` =:categoryId) r  GROUP BY t.ITEM_ID";
	
	//public final static String QUERY_COUNT_SUP_INFO1="SELECT   ROUND(t.n/r.m,3)score, t.VENDOR_ID vendorId, t.ITEM_ID itemId FROM (SELECT  v.`BUSINESS_DATE`, SUM( v.`RECEIPT_QUANTITY` * pr.`UNIT_PRICE`) n, datas.`VENDOR_ID`, datas.`ITEM_ID`  FROM srm_gl_receipt_headers_v v, SRM_SPM_REQUEST_DATAS datas, srm_gl_price_range pr  WHERE v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`   AND datas.`VENDOR_ID` = pr.`SUPPLIER_ID`   AND datas.`ITEM_ID` = pr.`ITEM_ID`  AND datas.`SEGMENT1`=1 AND v.`BUSINESS_DATE` BETWEEN pr.`START_DATE`  AND pr.`END_DATE`   AND pr.`STATUS` = 1   AND datas.`SCHEME_ID` =:schemeId  AND datas.`CATEGORY_ID` =:categoryId GROUP BY datas.`VENDOR_ID`, datas.`ITEM_ID`) t, (SELECT   v.`BUSINESS_DATE`, SUM(v.`RECEIPT_QUANTITY`) m, datas.`VENDOR_ID`, datas.`ITEM_ID`  FROM  srm_gl_receipt_headers_v v, SRM_SPM_REQUEST_DATAS datas, srm_gl_price_range pr  WHERE v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`  AND datas.`VENDOR_ID` = pr.`SUPPLIER_ID`   AND datas.`ITEM_ID` = pr.`ITEM_ID`   AND datas.`SEGMENT1` = 1  AND v.`BUSINESS_DATE` BETWEEN pr.`START_DATE`  AND pr.`END_DATE`  AND pr.`STATUS` = 1   AND datas.`SCHEME_ID` =:schemeId AND datas.`CATEGORY_ID` =:categoryId GROUP BY datas.`VENDOR_ID`, datas.`ITEM_ID`) r WHERE r.VENDOR_ID = t.VENDOR_ID  AND r.ITEM_ID = t.ITEM_ID ";
	
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public final static String QUERY_COUNT_DATAS="SELECT datas.VENDOR_ID vendorId,datas.`ORG_ID`orgId FROM SRM_SPM_REQUEST_DATAS datas WHERE 1=1";
	private Integer schemeId; //方案ID
    private Integer orgId; //组织ID，关联表：SAAF_INSTITUTION
    private Integer tplId; //绩效模板ID，关联表：SRM_SPM_PERFORMANCE_TPL
    private String evaluateIntervalFrom; //评价区间从
    private String evaluateIntervalTo; //评价区间至
    private Integer tplCategoryId; //模板分类ID，关联表：SRM_SPM_TPL_CATEGORIES
    private Integer categoryId; //分类ID（备用），关联表：SRM_BASE_CATEGORIES
    private String segment1; //一级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_BIG_CATEGORY）
    private String segment2; //二级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_MIDDLE_CATEGORY）
    private String segment3; //三级分类编码，关联表：SAAF_LOOKUP_VALUES（BASE_SMALL_CATEGORY）
    private String segment4; //四级分类编码（备用）
    private Integer vendorId; //供应商ID，关联表：SRM_POS_SUPPLIER_INFO
    private Integer poHeaderId; //订单头ID
    private Integer poLineId; //订单行ID
    private Integer deliveryHeaderId; //送货单头ID
    private Integer deliveryLineId; //送货单行ID
    private Integer detailId; //送货单明细ID
    private Integer itemId; //物料ID
    private Integer lineNumber;
    private String description; //说明、备注
    @JSONField(format = "yyyy-MM-dd")
    private Date demanDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date pdemanDate;
    
    private String poNumber;
    private String deliveryType;
    
    public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	private BigDecimal score;
    
    private String categoryCode;
    
	public Integer getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Integer schemeId) {
		this.schemeId = schemeId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getTplId() {
		return tplId;
	}
	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}
	public String getEvaluateIntervalFrom() {
		return evaluateIntervalFrom;
	}
	public void setEvaluateIntervalFrom(String evaluateIntervalFrom) {
		this.evaluateIntervalFrom = evaluateIntervalFrom;
	}
	public String getEvaluateIntervalTo() {
		return evaluateIntervalTo;
	}
	public void setEvaluateIntervalTo(String evaluateIntervalTo) {
		this.evaluateIntervalTo = evaluateIntervalTo;
	}
	public Integer getTplCategoryId() {
		return tplCategoryId;
	}
	public void setTplCategoryId(Integer tplCategoryId) {
		this.tplCategoryId = tplCategoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getSegment3() {
		return segment3;
	}
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}
	public String getSegment4() {
		return segment4;
	}
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
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
	public Integer getDeliveryHeaderId() {
		return deliveryHeaderId;
	}
	public void setDeliveryHeaderId(Integer deliveryHeaderId) {
		this.deliveryHeaderId = deliveryHeaderId;
	}
	public Integer getDeliveryLineId() {
		return deliveryLineId;
	}
	public void setDeliveryLineId(Integer deliveryLineId) {
		this.deliveryLineId = deliveryLineId;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDemanDate() {
		return demanDate;
	}
	public void setDemanDate(Date demanDate) {
		this.demanDate = demanDate;
	}
	public Date getPdemanDate() {
		return pdemanDate;
	}
	public void setPdemanDate(Date pdemanDate) {
		this.pdemanDate = pdemanDate;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public final static String  QUERY_COUNT_ITEM_INFO11="SELECT  IFNULL(ROUND(SUM(r.n / u.m * 100), 3),0) score, r.ITEM_ID itemId FROM ( (SELECT   SUM(tt.n) n,tt.VENDOR_ID, tt.ITEM_ID  FROM (SELECT   SUM( t.RECEIPT_QUANTITY * IFNULL(pr.`UNIT_PRICE`, t.UNIT_PRICE)) n, t.VENDOR_ID,t.ITEM_ID FROM (SELECT  v.`BUSINESS_DATE`,v.`RECEIPT_QUANTITY`,datas.`VENDOR_ID`,v.`UNIT_PRICE`, datas.`ITEM_ID`  FROM SRM_SPM_REQUEST_DATAS datas LEFT JOIN  srm_gl_receipt_headers_v v ON v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`  WHERE datas.`SEGMENT1` = 1  AND datas.`SCHEME_ID` =:schemeId AND datas.`CATEGORY_ID` =:categoryId) t  LEFT JOIN srm_gl_price_range pr  ON t.`VENDOR_ID` = pr.`SUPPLIER_ID`  AND t.`ITEM_ID` = pr.`ITEM_ID`  AND t.`BUSINESS_DATE` BETWEEN pr.`START_DATE`   AND pr.`END_DATE`  AND pr.`STATUS` = 1  GROUP BY t.`VENDOR_ID`, t.`ITEM_ID`  UNION ALL SELECT SUM(t.delivery_qty * pr.`UNIT_PRICE`) n,t.supplier_id VENDOR_ID,t.ITEM_ID  FROM(SELECT  n.`po_notice_code`,n.`demand_date`, n.`quantity`,i.`ITEM_ID`, SUM(l.`delivery_qty`) delivery_qty,l.`LAST_UPDATE_DATE`, n.`supplier_id`   FROM srm_po_notice n,srm_po_delivery_lines l,  srm_base_items i,  srm_base_categories cate   WHERE l.po_notice_id = n.po_notice_id AND n.item_id = i.item_id  AND i.`CATEGORY_CODE` = cate.`CATEGORY_CODE` AND n.status IN ('APPROVED', 'NATURAL_CLOSED','SHORTAGE_CLOSED' )   AND n.`demand_date` >=:evaluateIntervalFrom  AND n.`demand_date` <=:evaluateIntervalTo AND cate.`CATEGORY_ID` =:categoryId AND l.delivery_qty IS NOT NULL  AND EXISTS  (SELECT  *   FROM  SRM_SPM_TPL_CATEGORIES tplcate, SRM_BASE_CATEGORIES cate  WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID   AND tpl_id =:tplId AND i.CATEGORY_CODE = cate.CATEGORY_CODE)  GROUP BY n.`po_notice_code`) t, srm_gl_price_range pr  WHERE t.supplier_id = pr.`SUPPLIER_ID`  AND t.ITEM_ID = pr.`ITEM_ID`  AND t.LAST_UPDATE_DATE BETWEEN pr.`START_DATE` AND pr.`END_DATE`  AND pr.`STATUS` = 1  GROUP BY t.supplier_id, t.ITEM_ID) tt GROUP BY tt.VENDOR_ID, tt.ITEM_ID) r,(SELECT SUM(tt.m) m  FROM (SELECT  SUM( t.RECEIPT_QUANTITY * IFNULL(pr.UNIT_PRICE, t.UNIT_PRICE)) m  FROM (SELECT  v.`RECEIPT_QUANTITY`, v.`UNIT_PRICE`,datas.`VENDOR_ID`,datas.`ITEM_ID`, v.`BUSINESS_DATE`   FROM SRM_SPM_REQUEST_DATAS datas LEFT JOIN  srm_gl_receipt_headers_v v ON v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`   WHERE datas.`SEGMENT1` = 1  AND datas.`SCHEME_ID` =:schemeId  AND datas.`CATEGORY_ID` =:categoryId) t  LEFT JOIN srm_gl_price_range pr  ON t.`VENDOR_ID` = pr.`SUPPLIER_ID`   AND t.`ITEM_ID` = pr.`ITEM_ID`  AND t.`BUSINESS_DATE` BETWEEN pr.`START_DATE`   AND pr.`END_DATE`  AND pr.`STATUS` = 1   UNION ALL  SELECT  SUM(t.delivery_qty * pr.`UNIT_PRICE`) m  FROM (SELECT n.`po_notice_code`,n.`demand_date`,n.`quantity`,i.`ITEM_ID`, SUM(l.`delivery_qty`) delivery_qty,l.`LAST_UPDATE_DATE`, n.`supplier_id`  FROM srm_po_notice n,srm_po_delivery_lines l,srm_base_items i,SRM_BASE_CATEGORIES cate   WHERE l.po_notice_id = n.po_notice_id   AND n.item_id = i.item_id  AND i.`CATEGORY_CODE` = cate.`CATEGORY_CODE`  AND n.status IN ('APPROVED','NATURAL_CLOSED','SHORTAGE_CLOSED') AND n.`demand_date` >=:evaluateIntervalFrom AND n.`demand_date` <=:evaluateIntervalTo AND cate.`CATEGORY_ID` =:categoryId  AND l.delivery_qty IS NOT NULL   AND EXISTS (SELECT  *  FROM  SRM_SPM_TPL_CATEGORIES tplcate, SRM_BASE_CATEGORIES cate  WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID  AND tpl_id =:tplId AND i.CATEGORY_CODE = cate.CATEGORY_CODE)  GROUP BY n.`po_notice_code`) t, srm_gl_price_range pr  WHERE t.supplier_id = pr.`SUPPLIER_ID`  AND t.ITEM_ID = pr.`ITEM_ID`  AND t.LAST_UPDATE_DATE BETWEEN pr.`START_DATE`  AND pr.`END_DATE`  AND pr.`STATUS` = 1) tt) u) GROUP BY r.ITEM_ID";
	public final static String QUERY_COUNT_SUP_INFO11="SELECT  IF(r.m<=0,0, ROUND(t.n / r.m, 3))score, t.VENDOR_ID vendorId,t.ITEM_ID itemId FROM (SELECT  SUM(tt.n) n, tt.VENDOR_ID, tt.ITEM_ID FROM (SELECT  SUM( IFNULL(t.RECEIPT_QUANTITY,0) * IFNULL(pr.`UNIT_PRICE`, t.UNIT_PRICE)) n,t.VENDOR_ID,t.ITEM_ID FROM(SELECT  v.`BUSINESS_DATE`,v.`RECEIPT_QUANTITY`,datas.`VENDOR_ID`, v.`UNIT_PRICE`, datas.`ITEM_ID`   FROM   SRM_SPM_REQUEST_DATAS datas LEFT JOIN srm_gl_receipt_headers_v v ON v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`  WHERE datas.`SEGMENT1` = 1  AND datas.`SCHEME_ID` = :schemeId   AND datas.`CATEGORY_ID` = :categoryId) t  LEFT JOIN srm_gl_price_range pr   ON t.`VENDOR_ID` = pr.`SUPPLIER_ID`   AND t.`ITEM_ID` = pr.`ITEM_ID`   AND t.`BUSINESS_DATE` BETWEEN pr.`START_DATE`  AND pr.`END_DATE`  AND pr.`STATUS` = 1  GROUP BY t.`VENDOR_ID`, t.`ITEM_ID`  UNION ALL  SELECT   SUM(t.delivery_qty * pr.`UNIT_PRICE`) n, t.supplier_id VENDOR_ID, t.ITEM_ID  FROM (SELECT  n.`po_notice_code`,n.`demand_date`, n.`quantity`, i.`ITEM_ID`,SUM(l.`delivery_qty`) delivery_qty, l.`LAST_UPDATE_DATE`,  n.`supplier_id`  FROM  srm_po_notice n, srm_po_delivery_lines l,   srm_base_items i,  SRM_BASE_CATEGORIES cate   WHERE l.po_notice_id = n.po_notice_id  AND n.item_id = i.item_id   AND i.`CATEGORY_CODE` = cate.`CATEGORY_CODE`  AND n.status IN ( 'APPROVED','NATURAL_CLOSED', 'SHORTAGE_CLOSED')  AND n.`demand_date` >= :evaluateIntervalFrom  AND n.`demand_date` <= :evaluateIntervalTo  AND cate.`CATEGORY_ID` = :categoryId  AND l.delivery_qty IS NOT NULL  AND EXISTS  (SELECT   *   FROM   SRM_SPM_TPL_CATEGORIES tplcate,  SRM_BASE_CATEGORIES cate   WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID   AND tpl_id = :tplId  AND i.CATEGORY_CODE = cate.CATEGORY_CODE)   GROUP BY n.`po_notice_code`) t,  srm_gl_price_range pr   WHERE t.supplier_id = pr.`SUPPLIER_ID`  AND t.ITEM_ID = pr.`ITEM_ID`   AND t.LAST_UPDATE_DATE BETWEEN pr.`START_DATE`  AND pr.`END_DATE`   AND pr.`STATUS` = 1  GROUP BY t.ITEM_ID, t.supplier_id) tt  GROUP BY tt.VENDOR_ID,  tt.ITEM_ID) t, (SELECT  SUM(IFNULL(tt.m,0)) m, tt.VENDOR_ID, tt.ITEM_ID  FROM (SELECT  SUM(IFNULL(v.`RECEIPT_QUANTITY`,0)) m,datas.`VENDOR_ID`, datas.`ITEM_ID`  FROM  SRM_SPM_REQUEST_DATAS datas LEFT JOIN srm_gl_receipt_headers_v v  ON v.`po_line_number` = datas.`line_number`   AND v.`PO_HEADER_ID` = datas.`PO_HEADER_ID`   WHERE datas.`SEGMENT1` = 1  AND datas.`SCHEME_ID` = :schemeId AND datas.`CATEGORY_ID` = :categoryId  GROUP BY datas.`VENDOR_ID`,   datas.`ITEM_ID`   UNION  ALL  SELECT  SUM(t.delivery_qty) m,  t.supplier_id VENDOR_ID, t.ITEM_ID  FROM  (SELECT  n.`po_notice_code`,n.`demand_date`,n.`quantity`, i.`ITEM_ID`, SUM(l.`delivery_qty`) delivery_qty, l.`LAST_UPDATE_DATE`, n.`supplier_id`  FROM srm_po_notice n,srm_po_delivery_lines l, srm_base_items i, SRM_BASE_CATEGORIES cate  WHERE l.po_notice_id = n.po_notice_id  AND n.item_id = i.item_id  AND i.`CATEGORY_CODE` = cate.`CATEGORY_CODE`  AND n.status IN ( 'APPROVED','NATURAL_CLOSED','SHORTAGE_CLOSED' )   AND n.`demand_date` >= :evaluateIntervalFrom  AND n.`demand_date` <= :evaluateIntervalTo  AND cate.`CATEGORY_ID` = :categoryId  AND l.delivery_qty IS NOT NULL  AND EXISTS (SELECT *  FROM SRM_SPM_TPL_CATEGORIES tplcate, SRM_BASE_CATEGORIES cate  WHERE tplcate.CATEGORY_ID = cate.CATEGORY_ID AND tpl_id = :tplId  AND i.CATEGORY_CODE = cate.CATEGORY_CODE) GROUP BY n.`po_notice_code`) t, srm_gl_price_range pr  WHERE t.supplier_id = pr.`SUPPLIER_ID`  AND t.ITEM_ID = pr.`ITEM_ID`  AND t.LAST_UPDATE_DATE BETWEEN pr.`START_DATE`  AND pr.`END_DATE` AND pr.`STATUS` = 1  GROUP BY t.ITEM_ID, t.supplier_id) tt  GROUP BY tt.VENDOR_ID, tt.ITEM_ID) r WHERE t.VENDOR_ID = r.VENDOR_ID  AND t.ITEM_ID = r.ITEM_ID ";
}
