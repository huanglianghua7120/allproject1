package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmPoNoticeLineEntity_HI_RO implements Serializable {

    public static final String QUERY_NOTICE_SQL = "SELECT\n" +
            "\tph.po_number poHeaderNumber,\n" +
            "\tsi.supplier_name supplierName,\n" +
            "\tpl.demand_qty orderQuantity,\n" +
            "\tpl.demand_date demandDate,\n" +
            "\tspnl.MATCH_QUANTITY noticeQty\n" +
            "FROM\n" +
            "\tsrm_po_notice_line AS spnl\n" +
            "LEFT JOIN srm_po_lines AS pl ON pl.po_line_id = spnl.PO_LINE_ID\n" +
            "LEFT JOIN srm_po_headers AS ph ON ph.po_header_id = pl.po_header_id\n" +
            "LEFT JOIN srm_pos_supplier_info AS si ON si.supplier_id = ph.supplier_id\n" +
            "WHERE\n" +
            "\t1 = 1";


    public static final String QUERY_NOTICElINE_SQL = "SELECT \n" +
            "t.PO_HEADER_ID poHeaderId,\n" +
            "\tt.po_notice_id poNoticeId,\n" +
            "\tt.PO_LINE_ID PoLineId,\n" +
            "\tt.demand_date demandDate,\n" +
            "\tt.LINE_ID lineId,\n" +
            "\tget_notice_line_qty_f (\n" +
            "\t\tt.LINE_ID,\n" +
            "\t\t'CAN_CREATE_DELIVERY'\n" +
            "\t) canCreateDelivery\n" +
            "FROM\n" +
            "\tsrm_po_notice_line t\n" +
            "WHERE\n" +
            "\t1 = 1";

    public static final String QUERY_LINE_MATCHNUMBER_SQL="select \tget_po_qty_f (\n" +
            "\t\tl.po_line_id,\n" +
            "\t\t'CAN_CREATE_NOTICE'\n" +
            "\t) matchingNumber\n" +
            "from srm_po_lines l where l.po_line_id=:poLineId";
    
    /**送货通知行查询*/
    public static final String QUERY_SRMPONOTICElINE_SQL1 = "\t select a.line_status lineStatus,a.po_notice_id poNoticeId,b.po_line_id poLineId,b.demand_date demandDate,j.inst_name receiveToOrganizationName,pn.po_notice_status poNoticeStatus,c.item_code itemCode,c.item_name itemName,c.meaning uomCodeDesc,b.demand_qty orderQuantity," +
//			"IFNULL(b.demand_qty,0) mayNoticeQty," +
			"IFNULL(b.may_notice_qty,0) mayNoticeQty," +
			"b.original_demand_qty originalDemandQty,b.original_demand_date originalDemandDate,a.line_id lineId,a.feedback_adjust_date feedbackAdjustDate,a.feedback_adjust_qty feedbackAdjustQty,n.meaning feedbackStatusName,a.feedback_status feedbackStatus,a.reject_reason rejectReason,d.meaning meaning,e.po_number poHeaderNumber,f.category_code categoryCode,f.category_name categoryName,h.inst_name instName,IFNULL(a.notice_delivery_date,b.demand_date) noticeDeliveryDate,a.notice_delivery_qty noticeDeliveryQty,a.line_comments lineComments,a.feedback_result feedbackResult,pn.po_notice_code poNoticeCode,a.creation_date creationDate,\r\n"
    		+ "\t(select sum(s2.received_qty) from srm_po_notice_line n2 LEFT JOIN srm_po_lines s2 ON s2.po_line_id = n2.po_line_id where n2.po_line_id =a.po_line_id)receivedQty,\r\n"
    		+ "\t(select sum(s3.on_way_qty) from srm_po_notice_line n3 LEFT JOIN srm_po_lines s3 ON s3.po_line_id = n3.po_line_id and s3.`status`='APPROVED' where n3.po_line_id =a.po_line_id) onWayQty \r\n"
    		+ "\tfrom srm_po_notice_line a "
    		+ "\tLEFT JOIN saaf_lookup_values d ON a.line_status = d.lookup_code AND d.lookup_type = 'ISP_DELIVERYNOTICE_LINE_STATUS'\r\n"
    		+ "\tLEFT JOIN saaf_lookup_values k ON a.feedback_result = k.lookup_code AND k.lookup_type = 'ISP_PO_SUPPLIER_FEEDBACK'\r\n"
    		+ "\tLEFT JOIN saaf_lookup_values n ON a.feedback_status = n.lookup_code AND n.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\r\n"
    		+ "\tLEFT JOIN srm_po_notice pn ON a.po_notice_id = pn.po_notice_id \r\n"
    		+ "\tLEFT JOIN saaf_institution h ON h.inst_id = pn.bill_to_organization_id\r\n"
    		+ "\tLEFT JOIN srm_po_headers e on e.po_header_id = a.po_header_id,\r\n"
    		+ "\tsrm_po_lines b \r\n"
//    		+ "\tLEFT JOIN srm_base_items c ON c.item_id = b.item_id\r\n "
			+ "\tLEFT JOIN (SELECT w.item_id,w.item_code,w.item_name,w.organization_id,z.meaning FROM srm_base_items w LEFT JOIN saaf_lookup_values z ON w.uom_code = z.lookup_code AND z.lookup_type = 'BASE_ITEMS_UNIT') c ON b.item_id = c.item_id\r\n"
    		+ "\tLEFT JOIN srm_base_categories f on f.category_id = b.category_id\r\n"
    		+ "\tLEFT JOIN saaf_institution j ON j.inst_id = b.receive_to_organization_id\r\n"
//    		+ "\t,(select n2.po_line_id,sum(s2.received_qty) receivedQty from srm_po_notice_line n2,srm_po_lines s2 where s2.po_line_id = n2.po_line_id GROUP BY n2.po_line_id) t1\r\n"
//    		+ "\t,(SELECT n3.po_line_id,sum(s3.delivery_qty) onWayQty FROM srm_po_notice_line n3 LEFT JOIN srm_po_delivery_lines s3 ON s3.po_line_id = n3.po_line_id AND s3.`status` = 'APPROVED' GROUP BY n3.po_line_id) t2\r\n"
    		+ "where a.po_line_id = b.po_line_id \r\n";
//    + "where a.po_line_id = b.po_line_id and t1.po_line_id=b.po_line_id and t2.po_line_id=b.po_line_id \r\n";
    //t1.receivedQty,t2.onWayQty,(a.notice_delivery_qty-IFNULL(t2.onWayQty, 0)-IFNULL(t1.receivedQty, 0)) mayQty,



	public static final String QUERY_SRMPONOTICElINE_SQL = "SELECT\n" +
			"  ph.po_header_id poHeaderId,\n" +
			"  pnl.line_id lineId,\n" +
			"  pnl.po_notice_id poNoticeId,\n" +
			"  pl.line_number lineNumber,\n" +
			"  pl.demand_date demandDate,\n" +
			"  si1.inst_name receiveToOrganizationName,\n" +
			"  pnl.notice_delivery_date noticeDeliveryDate,\n" +
			"  bc.category_name categoryName,\n" +
			"  bc.category_code categoryCode,\n" +
			"  ifnull(bi.item_name ,(SELECT MAX(item_name) FROM srm_base_items b WHERE pl.item_id = b.item_id)) itemName,\n" +
			"  ifnull(bi.item_code ,(SELECT MAX(item_code) FROM srm_base_items b WHERE pl.item_id = b.item_id)) itemCode,\n" +
			"  pl.demand_qty orderQuantity,\n" +
			"  slv3.meaning uomCodeDesc,\n" +
			"-- 可通知送货量\n" +
			"  IFNULL(pl.may_notice_qty, 0) mayNoticeQty, \n" +
			"-- 已通知送货量\n" +
			"-- noticeQty, \n" +
			"-- 可送货数量\n" +
			"  (pnl.notice_delivery_qty-pnl.on_way_qty-pdl.received_qty) mayQty, \n" +
			"-- 在途数量\n" +
			"  pnl.on_way_qty onWayQty, \n" +
			"-- 接收数量\n" +
			"  pdl.received_qty receivedQty, \n" +
			"-- 本次通知数量\n" +
			"  pnl.notice_delivery_qty noticeDeliveryQty, \n" +
			"  pnl.line_status lineStatus,\n" +
			"  slv.meaning lineStatusStr,\n" +
			"  pnl.line_comments lineComments,\n" +
			"  ph.po_number poHeaderNumber,\n" +
			"  pl.po_line_id poLineId,\n" +
			"  pnl.original_delivery_date originalDeliveryDate,\n" +
			"  pnl.original_delivery_qty originalDeliveryQty,\n" +
			"  pnl.feedback_adjust_date feedbackAdjustDate,\n" +
			"  pnl.feedback_adjust_qty feedbackAdjustQty,\n" +
			"  pn.po_notice_status poNoticeStatus,\n" +
			"  slv2.meaning feedbackStatusName,\n" +
			"  pnl.feedback_status feedbackStatus,\n" +
			"  pnl.feedback_result feedbackResult,\n" +
			"  pnl.reject_reason rejectReason,\n" +
			"  pl.original_demand_qty originalDemandQty,\n" +
			"  pl.original_demand_date originalDemandDate,\n" +
			"  si.inst_name instName,\n" +
			"  pn.po_notice_code poNoticeCode,\n" +
			"  pnl.creation_date creationDate\n" +
			"FROM\n" +
			"  srm_po_notice_line pnl\n" +
			"  LEFT JOIN saaf_lookup_values slv ON pnl.line_status = slv.lookup_code AND slv.lookup_type = 'ISP_DELIVERYNOTICE_LINE_STATUS'\n" +
			"  LEFT JOIN saaf_lookup_values slv1 ON pnl.feedback_result = slv1.lookup_code AND slv1.lookup_type = 'ISP_PO_SUPPLIER_FEEDBACK'\n" +
			"  LEFT JOIN saaf_lookup_values slv2 ON pnl.feedback_status = slv2.lookup_code AND slv2.lookup_type = 'ISP_PO_FEEDBACK_STATUS'\n" +
			"  LEFT JOIN srm_po_notice pn ON pnl.po_notice_id = pn.po_notice_id\n" +
			"  LEFT JOIN srm_po_delivery_lines pdl ON pnl.line_id = pdl.notice_line_id\n" +
			"  LEFT JOIN saaf_institution si ON si.inst_id = pn.bill_to_organization_id\n" +
			"  LEFT JOIN srm_po_headers ph ON ph.po_header_id = pnl.po_header_id,\n" +
			"  srm_po_lines pl\n" +
			"  LEFT JOIN srm_po_headers ph1 ON ph1.po_header_id = pl.po_header_id\n" +
			"  LEFT JOIN srm_base_items bi ON pl.item_id = bi.item_id \n" +
			"  AND bi.organization_id = ph1.bill_to_organization_id\n"+	//关联库存组织
//			"  LEFT JOIN srm_base_items bi ON pl.item_id = bi.item_id AND EXISTS\n" +
//			"  (SELECT\n" +
//			"      1\n" +
//			"    FROM\n" +
//			"      saaf_institution si,\n" +
//			"      srm_po_headers ph\n" +
//			"    WHERE si.inst_id = bi.organization_id\n" +
//			"      AND si.inst_type = 'ORGANIZATION'\n" +
//			"      AND si.parent_inst_id = ph.org_id\n" +
//			"      AND pl.po_header_id = ph.po_header_id\n" +
//			"  )\n" +
			"  LEFT JOIN saaf_lookup_values slv3 ON slv3.lookup_code = bi.uom_code AND slv3.lookup_type = 'BASE_ITEMS_UNIT'\n"+
			"  LEFT JOIN srm_base_categories bc ON bc.category_id = pl.category_id\n" +
			"  LEFT JOIN saaf_institution si1 ON si1.inst_id = pl.receive_to_organization_id and si1.inst_type='ORGANIZATION'\n" +
			"WHERE pnl.po_line_id = pl.po_line_id\r\n";

    private BigDecimal matchingNumber;
    private Integer poHeaderId;
    private Integer poNoticeId;
    private Integer poLineId;
    private BigDecimal canCreateDelivery;
    private BigDecimal noticeQty;
    private Integer lineId;
    private String poHeaderNumber;
    private String supplierName;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDate;
    private BigDecimal orderQuantity;
    private BigDecimal deliveryOrderQty;
    private BigDecimal matchQuantity;

    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;
    private String itemName;
    private BigDecimal mayNoticeQty;
    private BigDecimal notifiedQty;
    private BigDecimal onWayQty;
    private BigDecimal receivedQty;
    private String meaning;
    private String categoryCode;
    private String categoryName;
    private BigDecimal originalDemandQty; // 原需求数量
	@JSONField(format = "yyyy-MM-dd")
	private Date originalDemandDate; // 原需求日期
	@JSONField(format = "yyyy-MM-dd")
	private Date noticeDeliveryDate; // 通知送货日期
	private String instName;
	private Integer noticeDeliveryQty;
	private String lineComments;
	@JSONField(format = "yyyy-MM-dd")
	private Date feedbackAdjustDate;
	private BigDecimal feedbackAdjustQty;
	private String feedbackStatus;
	private String rejectReason;
	private String feedbackResult;
	private String poNoticeCode;
	private String poNoticeStatus;
	private String receiveToOrganizationName;
	private BigDecimal mayQty;
	private String uomCodeDesc;
	private String itemCode;
	private String feedbackStatusName;
	private String lineStatus;
	private String lineStatusStr;
	private BigDecimal originalDeliveryQty; // 原通知数量
	@JSONField(format = "yyyy-MM-dd")
	private Date originalDeliveryDate; //原通知日期
	private Integer lineNumber;


	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
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

	public String getLineStatusStr() {
		return lineStatusStr;
	}

	public void setLineStatusStr(String lineStatusStr) {
		this.lineStatusStr = lineStatusStr;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getFeedbackStatusName() {
		return feedbackStatusName;
	}

	public void setFeedbackStatusName(String feedbackStatusName) {
		this.feedbackStatusName = feedbackStatusName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUomCodeDesc() {
		return uomCodeDesc;
	}

	public void setUomCodeDesc(String uomCodeDesc) {
		this.uomCodeDesc = uomCodeDesc;
	}

	public BigDecimal getMayQty() {
		return mayQty;
	}

	public void setMayQty(BigDecimal mayQty) {
		this.mayQty = mayQty;
	}

	public String getReceiveToOrganizationName() {
		return receiveToOrganizationName;
	}

	public void setReceiveToOrganizationName(String receiveToOrganizationName) {
		this.receiveToOrganizationName = receiveToOrganizationName;
	}

	public String getPoNoticeStatus() {
		return poNoticeStatus;
	}

	public void setPoNoticeStatus(String poNoticeStatus) {
		this.poNoticeStatus = poNoticeStatus;
	}

	public String getPoNoticeCode() {
		return poNoticeCode;
	}

	public void setPoNoticeCode(String poNoticeCode) {
		this.poNoticeCode = poNoticeCode;
	}

	public String getFeedbackResult() {
		return feedbackResult;
	}

	public void setFeedbackResult(String feedbackResult) {
		this.feedbackResult = feedbackResult;
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

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Integer getNoticeDeliveryQty() {
		return noticeDeliveryQty;
	}

	public void setNoticeDeliveryQty(Integer noticeDeliveryQty) {
		this.noticeDeliveryQty = noticeDeliveryQty;
	}

	public String getLineComments() {
		return lineComments;
	}

	public void setLineComments(String lineComments) {
		this.lineComments = lineComments;
	}

	public Date getNoticeDeliveryDate() {
		return noticeDeliveryDate;
	}

	public void setNoticeDeliveryDate(Date noticeDeliveryDate) {
		this.noticeDeliveryDate = noticeDeliveryDate;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
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

	public BigDecimal getMayNoticeQty() {
		return mayNoticeQty;
	}

	public void setMayNoticeQty(BigDecimal mayNoticeQty) {
		this.mayNoticeQty = mayNoticeQty;
	}

	public BigDecimal getNotifiedQty() {
		return notifiedQty;
	}

	public void setNotifiedQty(BigDecimal notifiedQty) {
		this.notifiedQty = notifiedQty;
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

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getMatchingNumber() {
        return matchingNumber;
    }

    public void setMatchingNumber(BigDecimal matchingNumber) {
        this.matchingNumber = matchingNumber;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public Integer getPoNoticeId() {
        return poNoticeId;
    }

    public void setPoNoticeId(Integer poNoticeId) {
        this.poNoticeId = poNoticeId;
    }

	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	public BigDecimal getCanCreateDelivery() {
        return canCreateDelivery;
    }

    public void setCanCreateDelivery(BigDecimal canCreateDelivery) {
        this.canCreateDelivery = canCreateDelivery;
    }

    public BigDecimal getNoticeQty() {
        return noticeQty;
    }

    public void setNoticeQty(BigDecimal noticeQty) {
        this.noticeQty = noticeQty;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getPoHeaderNumber() {
        return poHeaderNumber;
    }

    public void setPoHeaderNumber(String poHeaderNumber) {
        this.poHeaderNumber = poHeaderNumber;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getDeliveryOrderQty() {
        return deliveryOrderQty;
    }

    public void setDeliveryOrderQty(BigDecimal deliveryOrderQty) {
        this.deliveryOrderQty = deliveryOrderQty;
    }

    public BigDecimal getMatchQuantity() {
        return matchQuantity;
    }

    public void setMatchQuantity(BigDecimal matchQuantity) {
        this.matchQuantity = matchQuantity;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory(String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }


}
