package saaf.common.fmw.intf.model.entities.readonly;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPlanAnalyzeEntity_HI_RO {
	

	public static final String QUERY_PO_QTY = 
			"SELECT\r\n" + 
			"	ifnull(sum(l.demand_qty),0) sumQuantity\r\n" + 
			"FROM\r\n" + 
			"	srm_po_lines l,\r\n" + 
			"	srm_po_headers h,\r\n" + 
			"	srm_base_items i\r\n" + 
			"WHERE\r\n" + 
			"	l.po_header_id = h.po_header_id\r\n" + 
			" and DATE_FORMAT(h.CREATION_DATE, '%Y-%m') = DATE_FORMAT(CURDATE(), '%Y-%m') \r\n" + 
			" AND l.item_id = i.ITEM_ID\r\n" + 
			" and h.`status` ='APPROVED'" ;
	
	public static final String QUERY_PLAN_QTY = 
			"select ifnull(sum(t.need_quantity),0) sumQuantity from srm_po_plan_demands t\r\n" + 
			" where 1=1 \n" ;

	
	public static final String QUERY_PO_INFO = 
			"SELECT\r\n" + 
			"	h.po_number poNumber,\r\n" + 
			"	H.supplier_id supplierId,\r\n" + 
			"	h.po_header_id poHeaderId,\r\n" + 	
			"	h.ATTRIBUTE10 Attribute10,\r\n" +
			"	l.po_line_id poLineId,\r\n" + 
			"	l.line_number lineNumber,\r\n" + 
			"	l.`status` lineStatus,\r\n" + 
			"	I.ITEM_ID itemId,\r\n" + 
			"	I.CATEGORY_CODE categoryCode\r\n" + 
			"FROM\r\n" + 
			"	srm_po_lines l,\r\n" + 
			"	srm_po_headers h,\r\n" + 
			"	srm_base_items i\r\n" + 
			"WHERE\r\n" + 
			"	l.po_header_id = h.po_header_id\r\n" + 
			"AND l.item_id = i.ITEM_ID" ;
	
	public static final String QUERY_PROPORTION_H  =
			"SELECT\r\n" + 
			"   h.SUPPLY_ID supplyId,\r\n" + 
			"	h.SUPPLY_TYPE supplyType,\r\n" + 
			"	h.SMALL_CATEGORY_CODE categoryCode,\r\n" + 
			"	h.ITEM_CODE itemCode,\r\n" + 
			"   h.ITEM_ID itemId,\r\n" + 
			"	h.INST_ID instId,\r\n" + 
			"	h.`STATUS` status\r\n" + 
			"FROM\r\n" + 
			"	srm_po_supply_proportion_h H   \r\n" + 
			"WHERE 1=1\r\n" + 
			"AND SYSDATE() BETWEEN h.START_DATE_ACTIVE\r\n" + 
			"AND IFNULL(\r\n" + 
			"	h.END_DATE_ACTIVE,\r\n" + 
			"	SYSDATE() + 1\r\n" + 
			")\r\n" + 
			"and h.`STATUS` = 'Y'"  ;
	
	public static final String QUERY_PROPORTION_L = 
			"SELECT\r\n" + 
			"	l.SUPPLY_DETAIL_ID supplyDetailId,\r\n" + 
			"	l.SUPPLY_ID supplyId,\r\n" + 
			"	l.SUPPLIER_ID supplierId,\r\n" + 
			"	si.SUPPLIER_NUMBER supplierNumber,\r\n" + 
			"	l.SUPPLIER_NAME supplierName,\r\n" + 
			"	l.PROPORTION proportion\r\n" + 
			"FROM\r\n" + 
			"	srm_po_supply_proportion_l L ,\n" + 
			"   srm_pos_supplier_info si   \n" +
			"WHERE l.supplier_id = si.supplier_id \n" + 
			"	and L.ENABLE_FLAG = 'Y'\r\n"  ;
	
    public static final String QUERY_PLAN_DEMAND =
    		"SELECT\r\n" + 
    		"	t.plan_demand_id planDemandId,\r\n" + 
    		"	t.plan_date planDate,\r\n" + 
    		"	T.plan_type planType,\r\n" + 
    		"	t.inst_id instId,\r\n" + 
    		"	t.item_id itemId,\r\n" + 
    		"	t.category_code categoryCode,\r\n" + 
    		"	c.retrospect_flag retrospectFlag,\r\n" + 
    		"	t.need_by_date needByDate,\r\n" + 
    		"	t.need_quantity needQuantity,\r\n" + 
    		"	t.SPECIAL_USE_NUM specialUseNum,\r\n" + 
    		"	t.demand_classify demandClassify,\r\n" + 
    		"	t.handle_status handleStatus,\r\n" + 
    		"	t.SUPPLIER_NUMBER supplierNumber,\r\n" + 
    		"   s.supplier_id supplierId\r\n" + 
    		"FROM\r\n" + 
    		"	srm_po_plan_demands t\r\n" + 
    		"LEFT JOIN srm_base_categories c ON t.category_code = c.CATEGORY_CODE\r\n" + 
    		"LEFT JOIN srm_pos_supplier_info s on s.supplier_number = t.SUPPLIER_NUMBER\r\n" + 
    		"WHERE\r\n" + 
    		"	1 = 1\r\n"   ;
    
   public static final String QUERY_INTF_PLAN_DEMAND = 
		   "SELECT\r\n" + 
		   "	t.INTF_ID intfId,\r\n" + 
		   "	i.parent_inst_id parentInstId,\r\n" + 
		   "	i.inst_id instId,\r\n" + 
		   "	b.CATEGORY_CODE categoryCode,\r\n" + 
		   "	b.ITEM_ID itemId,\r\n" + 
		   "	b.AGENT_NUMBER agentNumber,\r\n" + 
		   "	t.plan_date planDate,\r\n" + 
		   "	t.plan_type planType,\r\n" + 
		   "	t.need_by_date needByDate,\r\n" + 
		   "	t.need_quantity needQuantity,\r\n" + 
		   "	t.advise_order_date adviseOrderDate, \r\n" + 
		   "	t.SUPPLIER_NUMBER supplierNumber,\r\n" + 
		   "	t.SPECIAL_USE_NUM specialUseNum,\r\n" + 
		   "	t.U9_HEADER_ID u9HeaderId,\r\n" + 
		   "	t.plan_code planCode,\r\n" + 
		   "	t.demand_classify demandClassify\r\n" + 
		   "FROM\r\n" + 
		   "	srm_intf_plan_demand t\r\n" + 
		   "LEFT JOIN srm_base_items b ON b.ITEM_CODE = t.item_code\r\n" + 
		   "LEFT JOIN saaf_institution i ON i.inst_code = t.inst_code\r\n" + 
		   "WHERE 1=1  ";
   
   public static final String QUERY_PO_CHANGE_INFO = "SELECT\r\n" + 
   		"  t.INTF_ID intfId,\r\n" + 
   		"	t.PLAN_DATE planDate,\r\n" + 
   		"	t.ORDER_QUANTITY orderQuantity,\r\n" + 
   		"	t.NEED_BY_DATE needByDate,\r\n" + 
   		"	t.origin_date originDate,\r\n" + 
   		"	t.origin_quantity originQuantity,\r\n" + 
   		"  i.ITEM_ID itemId,\r\n" + 
   		"	t.ITEM_CODE itemCode,\r\n" + 
   		"	t.CHANGE_TYPE changeType,\r\n" + 
   		"	t.PO_NUMBER poNumber,\r\n" + 
   		"	t.PO_LINE_NUM lineNumber,\r\n" + 
   		"	l.po_header_id poHeaderId,\r\n" + 
   		"	l.Attribute10 Attribute10,\r\n" + 
   		"	l.po_line_id poLineId\r\n" + 
   		"FROM\r\n" + 
   		"	srm_intf_po_changes t\r\n" + 
   		"LEFT JOIN srm_base_items i ON i.ITEM_CODE = t.ITEM_CODE\r\n" + 
   		"LEFT JOIN srm_po_headers h ON h.po_number = t.PO_NUMBER\r\n" + 
   		"LEFT JOIN srm_po_lines l ON l.po_header_id = h.po_header_id\r\n" + 
   		"AND l.line_number = t.PO_LINE_NUM  where 1=1 ";
  
      
	private Integer intfId;
	private Integer parentInstId;
	private String agentNumber;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date adviseOrderDate;

	private Integer supplyDetailId;
	private Integer supplyId;
	private BigDecimal proportion;
	private String supplyType;
	private String status;

	private BigDecimal sumQuantity;

	private Integer poHeaderId;
	private Integer poLineId;
	private String poStatus;
	private Integer lineNumber;
	private String poNumber;

	private Integer planDemandId;
	private Integer instId;
	private String instCode;
	private Integer itemId;
	private String itemCode;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date planDate;
	private String planType;
	private String demandClassify;
	private BigDecimal needQuantity;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date needByDate;
	private String employerNumber;
	private Integer supplierId;
	private String supplierNumber;
	private String supplierName;
	private String specialUseNum;
	private String categoryCode;
	private String retrospectFlag;

	private String Attribute10;

	private String lastDemandClassify;
	private BigDecimal sumSupplierQty; // 供货比例（物料或分类）对应单个供应商当月下单数量
	private BigDecimal sumPlanQty;
	private BigDecimal disQty;
	private BigDecimal canDisQty;

	private BigDecimal sumMonQuantity; // 供货比例（物料或分类）对应所有供应商当月下单数量

	private BigDecimal MonthProportion;

	private String handleStatus;

	private Integer dataCount;
	
	private String u9HeaderId;
	private String planCode;
	
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date originDate;
	private String changeType;
	private BigDecimal originQuantity;
	private BigDecimal orderQuantity;
	 

	public Integer getIntfId() {
		return intfId;
	}

	public void setIntfId(Integer intfId) {
		this.intfId = intfId;
	}

	public Integer getParentInstId() {
		return parentInstId;
	}

	public void setParentInstId(Integer parentInstId) {
		this.parentInstId = parentInstId;
	}

	public String getAgentNumber() {
		return agentNumber;
	}

	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}

	public Date getAdviseOrderDate() {
		return adviseOrderDate;
	}

	public void setAdviseOrderDate(Date adviseOrderDate) {
		this.adviseOrderDate = adviseOrderDate;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public Integer getSupplyDetailId() {
		return supplyDetailId;
	}

	public void setSupplyDetailId(Integer supplyDetailId) {
		this.supplyDetailId = supplyDetailId;
	}

	public Integer getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Integer supplyId) {
		this.supplyId = supplyId;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getPlanDemandId() {
		return planDemandId;
	}

	public void setPlanDemandId(Integer planDemandId) {
		this.planDemandId = planDemandId;
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

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getDemandClassify() {
		return demandClassify;
	}

	public void setDemandClassify(String demandClassify) {
		this.demandClassify = demandClassify;
	}

	public BigDecimal getNeedQuantity() {
		return needQuantity;
	}

	public void setNeedQuantity(BigDecimal needQuantity) {
		this.needQuantity = needQuantity;
	}

	public Date getNeedByDate() {
		return needByDate;
	}

	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}

	public String getEmployerNumber() {
		return employerNumber;
	}

	public void setEmployerNumber(String employerNumber) {
		this.employerNumber = employerNumber;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSpecialUseNum() {
		return specialUseNum;
	}

	public void setSpecialUseNum(String specialUseNum) {
		this.specialUseNum = specialUseNum;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getRetrospectFlag() {
		return retrospectFlag;
	}

	public void setRetrospectFlag(String retrospectFlag) {
		this.retrospectFlag = retrospectFlag;
	}

	public BigDecimal getSumMonQuantity() {
		return sumMonQuantity;
	}

	public void setSumMonQuantity(BigDecimal sumMonQuantity) {
		this.sumMonQuantity = sumMonQuantity;
	}

	public String getLastDemandClassify() {
		return lastDemandClassify;
	}

	public void setLastDemandClassify(String lastDemandClassify) {
		this.lastDemandClassify = lastDemandClassify;
	}

	public BigDecimal getDisQty() {
		return disQty;
	}

	public void setDisQty(BigDecimal disQty) {
		this.disQty = disQty;
	}

	public BigDecimal getCanDisQty() {
		return canDisQty;
	}

	public void setCanDisQty(BigDecimal canDisQty) {
		this.canDisQty = canDisQty;
	}

	public BigDecimal getSumSupplierQty() {
		return sumSupplierQty;
	}

	public void setSumSupplierQty(BigDecimal sumSupplierQty) {
		this.sumSupplierQty = sumSupplierQty;
	}

	public BigDecimal getSumPlanQty() {
		return sumPlanQty;
	}

	public void setSumPlanQty(BigDecimal sumPlanQty) {
		this.sumPlanQty = sumPlanQty;
	}

	public BigDecimal getMonthProportion() {
		return MonthProportion;
	}

	public void setMonthProportion(BigDecimal monthProportion) {
		MonthProportion = monthProportion;
	}

	public BigDecimal getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(BigDecimal sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	public String getAttribute10() {
		return Attribute10;
	}

	public void setAttribute10(String attribute10) {
		Attribute10 = attribute10;
	}

	public Integer getDataCount() {
		return dataCount;
	}

	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}

	public String getU9HeaderId() {
		return u9HeaderId;
	}

	public void setU9HeaderId(String u9HeaderId) {
		this.u9HeaderId = u9HeaderId;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public Date getOriginDate() {
		return originDate;
	}

	public void setOriginDate(Date originDate) {
		this.originDate = originDate;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public BigDecimal getOriginQuantity() {
		return originQuantity;
	}

	public void setOriginQuantity(BigDecimal originQuantity) {
		this.originQuantity = originQuantity;
	}

	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

}
