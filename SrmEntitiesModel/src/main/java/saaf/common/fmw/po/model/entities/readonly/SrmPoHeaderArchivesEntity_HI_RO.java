package saaf.common.fmw.po.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import javax.persistence.Version;
import javax.persistence.Transient;

/**
 * SrmPoHeaderArchivesEntity_HI_RO Entity Object
 * Thu Mar 21 09:56:42 CST 2019  Auto Generate
 */

public class SrmPoHeaderArchivesEntity_HI_RO {
	//采购框架协议查询
	public static String QUERY_PO_FRAMEWORK_AGREEMEN_SQL = "SELECT DISTINCT \n" +
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
			//"  LEFT JOIN saaf_lookup_values s1v3 ON ph.payment_condition = s1v3.lookup_code AND s1v3.lookup_type = 'PON_PAY_TYPE'\n" +
			"  LEFT JOIN srm_pon_payment_terms ppt ON ph.payment_condition = ppt.payment_term_code\n" +
			"  LEFT JOIN saaf_lookup_values s1v4 ON ph.settlement_way = s1v4.lookup_code AND s1v4.lookup_type = 'PAYMENT_METHOD'\n" +
			"  LEFT JOIN saaf_employees se1 ON ph.approval_user_id = se1.employee_id\n" +
			"  LEFT JOIN srm_base_items sbi ON pl.item_id = sbi.item_id\n" +
			"  LEFT JOIN saaf_institution si2 ON paa.receive_to_organization_id = si2.inst_id AND si2.inst_type = 'ORGANIZATION'\n" +
			"WHERE ph.po_doc_type = 'AGREEMENT'\r\n";


	public static String QUERY_ORDER_HEADER_ARCHIVES_BY_SUPPLIER_SQL =
			"SELECT  a.po_header_id            AS poHeaderId,\n" +
			"        a.po_number               AS poNumber,\n" +
			"        a.contract_id             AS contractId,\n" +
			"        a.contract_code           AS contractCode,\n" +
			"        a.org_id                  AS orgId,\n" +
			"        c.inst_name               AS orgName,\n" +
			"        a.bill_to_organization_id AS billToOrganizationId,\n" +
			"        m.inst_name               AS billToOrganizationName,\n" +
			"        a.ship_to_organization_id AS shipToOrganizationId,\n" +
			"        m2.inst_name              AS shipToOrganizationName,\n" +
			"        a.creation_date           AS creationDate,\n" +
			"        a.buyer_id                AS employeeId,\n" +
			"        a.description             AS description,\n" +
			"        f.employee_name           AS employeeName,\n" +
			"        f2.employee_name          AS createdName,\n" +
			"        a.return_goods_type       AS returnGoodsType,\n" +
			"        j.meaning                 AS returnGoodsTypeStr,\n" +
			"        a.status                  AS STATUS,\n" +
			"        h.meaning                 AS statusStr,\n" +
			"        a.currency_code           AS currencyCode,\n" +
			"        s11.meaning           AS currencyCodeStr,\n" +
			"        s12.meaning           AS taxRateCodeStr,\n" +
			"        a.payment_condition  AS paymentTermCode,\n" +
			"        ppt.payment_term_id  AS paymentTermId,\n" +
			"        ppt.payment_term_name  AS paymentTermName,\n" +
			"        s14.meaning          AS settlementWayStr,\n" +
			"        a.tax_rate_code           AS taxRateCode,\n" +
			"        (SELECT SUM(w.demand_qty*w.tax_price) FROM srm_po_line_archives w WHERE w.Po_Archive_Id = a.Po_Archive_Id AND w.status <> 'CANCELLED') AS taxTotalPrice,\n" +
			"        (SELECT SUM(w.demand_qty*w.non_tax_price) FROM srm_po_line_archives w WHERE w.Po_Archive_Id = a.Po_Archive_Id AND w.status <> 'CANCELLED') AS NonTaxTotalPrice,\n" +
			"        (SELECT SUM(w.received_qty*w.tax_price) FROM srm_po_line_archives w WHERE w.Po_Archive_Id = a.Po_Archive_Id AND w.status <> 'CANCELLED') AS taxActTotalPrice,\n" +
			"        (SELECT SUM(w.received_qty*w.non_tax_price) FROM srm_po_line_archives w WHERE w.Po_Archive_Id = a.Po_Archive_Id AND w.status <> 'CANCELLED') AS NonTaxActTotalPrice,\n" +
			"        a.payment_condition       AS paymentCondition,\n" +
			"        a.settlement_way          AS settlementWay,\n" +
			"        a.supplier_id             AS supplierId,\n" +
			"        w.supplier_name           AS supplierName,\n" +
			"        a.supplier_site_id        AS supplierSiteId,\n" +
			"        q.site_name               AS siteName,\n" +
			"        a.approved_date           AS approvedDate,\n" +
			"        a.po_versions       AS poVersions,\n" +
			"        a.version_num       AS versionNum,\n" +
			"        a.po_file_id              AS commonFileId,\n" +
			"        a.pr_number                  AS prNumber,\n" +
			"        a.location_code              AS locationCode,\n" +
			"        a.po_type              AS poType,\n" +
			"        rf.access_Path commonFilePath,\n" +
			"        rf.file_Name commonFileName,\n" +
            "        a.Organization_Id organizationId\n" +
            "      ,Si1.Inst_Name organizationName\n" +
            "      ,a.Bill_To_Location_Id AS billToLocationId\n" +
            "      ,Sbl1.Location_Code AS billToLocationCode\n" +
            "      ,a.Ship_To_Location_Id AS shipToLocationId\n" +
            "      ,Sbl2.Location_Code AS shipToLocationCode\n" +
            "  ,a.logi_ship_to_location_code     logiShipToLocationCode\n" +
            "  ,a.logi_bill_to_location_code     logiBillToLocationCode\n" +
            "  ,a.auction_number     auctionNumber\n" +
            "  ,slv1.meaning     logiShipToLocationName\n" +
            "  ,slv2.meaning     logiBillToLocationName\n" +
			"  FROM srm_po_header_archives a\n" +
			"  LEFT JOIN Saaf_Institution c\n" +
			"    ON a.Org_Id = c.Inst_Id\n" +
			"   AND c.Inst_Type = 'ORG'\n" +
            "   LEFT JOIN Saaf_Institution Si1\n" +
            "    ON Si1.Inst_Id = a.Organization_Id\n" +
            "  LEFT JOIN Srm_Base_Locations Sbl1\n" +
            "    ON a.Bill_To_Location_Id = Sbl1.Location_Id\n" +
            "  LEFT JOIN Srm_Base_Locations Sbl2\n" +
            "    ON a.Ship_To_Location_Id = Sbl2.Location_Id\n" +
			"  LEFT JOIN Saaf_Employees f\n" +
			"    ON a.Buyer_Id = f.Employee_Id\n" +
			"  LEFT JOIN Saaf_Employees F2\n" +
			"    ON a.Created_By = F2.User_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values h\n" +
			"    ON h.Lookup_Type = 'ISP_PO_STATUS'\n" +
			"   AND a.Status = h.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values j\n" +
			"    ON j.Lookup_Type = 'ISP_DELIVERY_TYPE'\n" +
			"   AND a.Return_Goods_Type = j.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values S11\n" +
			"    ON S11.Lookup_Type = 'BANK_CURRENCY'\n" +
			"   AND a.Currency_Code = S11.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Lookup_Values S12\n" +
			"    ON S12.Lookup_Type = 'PON_TAX_LIST'\n" +
			"   AND a.Tax_Rate_Code = S12.Lookup_Code\n" +
			"  LEFT JOIN Srm_Pon_Payment_Terms Ppt\n" +
			"    ON a.Payment_Condition = Ppt.Payment_Term_Code\n" +
			"   AND Ppt.Payment_Term_Status = 'ACT'\n" +
			"  LEFT JOIN Saaf_Lookup_Values S14\n" +
			"    ON S14.Lookup_Type = 'PAYMENT_METHOD'\n" +
			"   AND a.Settlement_Way = S14.Lookup_Code\n" +
			"  LEFT JOIN Saaf_Institution m\n" +
			"    ON a.Bill_To_Organization_Id = m.Inst_Id\n" +
			"   AND m.Inst_Type = 'ORGANIZATION'\n" +
			"  LEFT JOIN Saaf_Institution M2\n" +
			"    ON a.Ship_To_Organization_Id = M2.Inst_Id\n" +
			"   AND M2.Inst_Type = 'ORGANIZATION'\n" +
			"  LEFT JOIN Srm_Pos_Supplier_Info w\n" +
			"    ON a.Supplier_Id = w.Supplier_Id\n" +
			"  LEFT JOIN Saaf_Base_Result_File Rf\n" +
			"    ON a.Po_File_Id = Rf.File_Id\n" +
			"  LEFT JOIN Srm_Pos_Supplier_Sites q\n" +
			"    ON a.Supplier_Site_Id = q.Supplier_Site_Id\n" +
            "      LEFT JOIN saaf_lookup_values slv1  ON slv1.lookup_type = 'LOGISTICS_SZ_RECEIVER' AND a.logi_ship_to_location_code = slv1.lookup_code\n" +
            "      LEFT JOIN saaf_lookup_values slv2  ON slv2.lookup_type = 'LOGISTICS_SZ_RECEIVER' AND a.logi_bill_to_location_code = slv2.lookup_code\n" +
            " WHERE a.Po_Header_Id = :poHeaderId\n" +
			"   AND a.po_versions = :poVersions\n";

	public static String QUERY_ORDER_LINE_ARCHIVES_BY_SUPPLIER_SQL =
			"SELECT spl.po_header_id AS poHeaderId,\n" +
			"      spl.po_line_id AS poLineId,\n" +
			"      spl.line_number AS lineNumber,\n" +
			"      spl.item_id AS itemId,\n" +
			"      sbi.item_code AS itemCode,\n" +
			"      spl.item_name AS itemName,\n" +
			"      spl.item_spec AS itemSpec,\n" +
			"      slv1.meaning AS uomCodeName,\n" +
			"      spl.demand_date AS demandDate,\n" +
			"      spl.demand_qty AS demandQty,\n" +
			"      spl.category_id AS categoryId,\n" +
			"      sbc.full_category_code AS categoryCode,\n" +
			"      sbc.full_category_name AS categoryName,\n" +
			"      Spl.expense_item_code AS expenseItemCode,\n" +
			"      spl.description AS lineDescription,\n" +
			"      spl.status AS lineStatus,\n" +
			"      slv2.meaning AS lineStatusStr,\n" +
			"      spl.tax_price AS taxPrice,\n" +
			"      spl.non_tax_price AS nonTaxPrice,\n" +
			"      spl.receive_to_organization_id AS receiveToOrganizationId,\n" +
			"      si.inst_code AS receiveToOrganizationCode,\n" +
			"      si.inst_name AS receiveToOrganizationName,\n" +
			"      spl.may_notice_qty AS mayNoticeQty,\n" +
			"      spl.on_way_qty AS onWayQty,\n" +
			"      spl.received_qty AS receivedQty,\n" +
			"      spl.original_demand_qty AS originalDemandQty,\n" +
			"      spl.original_demand_date AS originalDemandDate,\n" +
			"      spl.feedback_adjust_date AS feedbackAdjustDate,\n" +
			"      spl.feedback_adjust_qty AS feedbackAdjustQty,\n" +
			"      spl.feedback_status AS feedbackStatus,\n" +
			"      slv3.meaning AS feedbackStatusStr,\n" +
			"      spl.feedback_result AS feedbackResult,\n" +
			"      slv4.meaning AS feedbackResultStr,\n" +
			"      spl.reject_reason AS rejectReason,\n" +
			"      spl.source_code AS sourceCode,\n" +
			"      spl.return_qty AS returnQty,\n" +
			"      spl.erp_po_number AS erpPoNumber,\n" +
			"      spl.context AS context,\n" +
			"      spl.project_category AS projectCategory,\n" +
			"      spl.project_type AS projectType,\n" +
			"      spl.technical_trans_number AS technicalTransNumber,\n" +
			"      spl.subproject_number AS subprojectNumber,\n" +
			"      spl.acceptance_process_number AS acceptanceProcessNumber,\n" +
			"      spl.tax_rate_code AS taxRateCode,\n" +
			"      spl.non_tax_total_price AS nonTaxTotalPrice,\n" +
			"      spl.tax_total_price AS taxTotalPrice,\n" +
			"      spl.non_tax_act_total_price AS nonTaxActTotalPrice,\n" +
			"      spl.tax_act_total_price AS taxActTotalPrice,\n" +
            "      spl.po_line_comb_id AS poLineCombId,\n" +
            "      spl.contract_item_id AS contractItemId,\n" +
            "      spl.contract_id AS contractId,\n" +
			"      'Y' AS receiveIsDisabled\n" +
			"  FROM Srm_Po_Header_Archives Sph\n" +
			"      ,Srm_Po_Line_Archives   Spl\n" +
			"  LEFT JOIN Srm_Base_Items_b Sbi\n" +
			"    ON Spl.Item_Id = Sbi.Item_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv1\n" +
			"    ON Slv1.Lookup_Code = Sbi.Uom_Code\n" +
			"   AND Slv1.Lookup_Type = 'BASE_ITEMS_UNIT'\n" +
			"  LEFT JOIN Srm_Base_Categories Sbc\n" +
			"    ON Sbc.Category_Id = Spl.Category_Id\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv2\n" +
			"    ON Slv2.Lookup_Code = Spl.Status\n" +
			"   AND Slv2.Lookup_Type = 'ISP_PO_LINE_STATUS'\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv3\n" +
			"    ON Slv3.Lookup_Code = Spl.Feedback_Status\n" +
			"   AND Slv3.Lookup_Type = 'ISP_PO_FEEDBACK_STATUS'\n" +
			"  LEFT JOIN Saaf_Lookup_Values Slv4\n" +
			"    ON Slv4.Lookup_Code = Spl.Feedback_Result\n" +
			"   AND Slv4.Lookup_Type = 'ISP_PO_SUPPLIER_FEEDBACK'\n" +
			"  LEFT JOIN Saaf_Institution Si\n" +
			"    ON Si.Inst_Id = Spl.Receive_To_Organization_Id\n" +
			" WHERE Sph.Po_Archive_Id = Spl.Po_Archive_Id\n" +
			"   AND Sph.Po_Header_Id = :poHeaderId\n" +
			"   AND Sph.Po_Versions = :poVersions\n";

    private Integer poArchiveId; //采购订单归档ID
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

	private Integer instId;
	private String instName;
	private String instCode;

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
    private Integer shipToLocationId;
    private Integer billToLocationId;
    private String shipToLocationCode;
    private String billToLocationCode;
    private Integer organizationId;
    private String organizationName;
    private String logiShipToLocationCode;
    private String logiBillToLocationCode;
    private String logiShipToLocationName;
    private String logiBillToLocationName;
    private Integer poLineCombId;
    private Integer contractItemId;

    public Integer getPoLineCombId() {
        return poLineCombId;
    }

    public void setPoLineCombId(Integer poLineCombId) {
        this.poLineCombId = poLineCombId;
    }

    public Integer getContractItemId() {
        return contractItemId;
    }

    public void setContractItemId(Integer contractItemId) {
        this.contractItemId = contractItemId;
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

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
	
	
	
}
