package saaf.common.fmw.po.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

public class SrmPoStarvingEntity_HI_RO implements Serializable,Comparator {

    public SrmPoStarvingEntity_HI_RO() {

    }

    public SrmPoStarvingEntity_HI_RO(String inst_name, Integer inst_id, BigDecimal needQuantity) {
        this.setInstName(inst_name);
        this.setInstId(inst_id);
        this.setNeedQuantity(needQuantity);
    }

    public static final String QUERY_STARVING_SQL =
            "SELECT\n" +
                    "\tsps.po_starving_id poStarvingId,\n" +
                    "  sps.inst_id instId,\n" +
                    "  sps.delivery_site_id deliverySiteId,\n" +
                    "\tsi.inst_name instName,\n" +
                    "\tsi2.inst_name deliverySiteName,\n" +
                    "  sps.category_code categoryCode,\n" +
                    "\tsbc.category_name categoryName,\n" +
                    "  sbi.ITEM_ID  itemId,\n" +
                    "\tsbi.item_code itemCode,\n" +
                    "\tsbi.item_name itemName,\n" +
                    "\tsps.demand_classify demandClassify,\n" +
                    "\tsps.need_quantity needQuantity,\n" +
                    "  (\r\n" + 
                    "		SELECT\r\n" + 
                    "			ifnull(SUM(n.QUANTITY), 0)\r\n" + 
                    "		FROM\r\n" + 
                    "			srm_po_notice n\r\n" + 
                    "		WHERE\r\n" + 
                    "			n.po_starving_id = sps.po_starving_id\r\n" + 
                    "		AND n.`status` IN ('CREATED', 'CONFIRMED','REJECTED')\r\n" + 
                    "	) + (\r\n" + 
                    "		SELECT\r\n" + 
                    "			ifnull(sum(n.delivery_qty), 0)\r\n" + 
                    "		FROM\r\n" + 
                    "			srm_po_notice n\r\n" + 
                    "		WHERE\r\n" + 
                    "			n.po_starving_id = sps.po_starving_id\r\n" + 
                    "		AND n.`status` IN (\r\n" + 
                    "			'SHORTAGE_CLOSED',\r\n" + 
                    "			'NATURAL_CLOSED'\r\n" + 
                    "		)\r\n" + 
                    "	)  noticeQty,\n" +
                    "\tsps.special_use_num specialUseNum,\n" +
                    "\tsps.need_by_date needByDate,\n" +
                    "  s2.SUPPLIER_ID  supplierId,\n" +
                    "  se.employee_Name  employeeName,\n" +
                    "  se.employee_number  employeeNumber,\n" +
                    "  sps.SUPPLIER_NUMBER  supplierNumber\n" +
                    "FROM\n" +
                    "\tsrm_po_starving AS sps\n" +
                    "LEFT JOIN saaf_institution AS si ON si.inst_id = sps.inst_id\n" +
                    "LEFT JOIN saaf_institution AS si2 ON si2.inst_id = sps.delivery_site_id\n" +
                    "LEFT JOIN srm_base_items AS sbi ON sbi.item_id = sps.item_id\n" +
                    "LEFT JOIN srm_base_categories AS sbc ON sbc.category_code = sbi.category_code\n" +
                    "LEFT JOIN srm_pos_supplier_info AS s2 ON s2.supplier_number = sps.supplier_number\n" +
                    "LEFT JOIN saaf_employees AS se ON se.employee_id = sps.employee_id\n" +
                    "\n" +
                    "WHERE\n" +
                    "\t1 = 1 \n" +
                    "and \n" +
                    "\t 0<sps.need_quantity-(select  ifnull( SUM(n.QUANTITY) ,0)\n" +
                    "   from   srm_po_notice n\n" +
                    "   where n.po_starving_id = sps.po_starving_id \n" +
                    "   and  n.`status` IN( 'CREATED','CONFIRMED' )) \n" +
                    "-\n" +
                    "  (select  ifnull( sum(n.delivery_qty) ,0)\n" +
                    "   from  srm_po_notice n\n" +
                    "   where n.po_starving_id = sps.po_starving_id \n" +
                    "   and  n.`status` IN( 'SHORTAGE_CLOSED','NATURAL_CLOSED' ))\n";

    public static final String QUERY_MATCH_PO_INFO =
            "SELECT\r\n" +
                    " h.po_header_Id poHeaderId,\r\n" +
                    "	h.po_number poNumber,\r\n" +
                    "  si.supplier_number supplierNumber,\r\n" +
                    "  si.supplier_name supplierName,\r\n" +
                    "  si.supplier_Id supplierId,\r\n" +
                    "  l.po_line_id poLineId,\r\n" +
                    "  l.line_number lineNumber,\r\n" +
                    "  l.demand_date demandDate,\r\n" +
                    "	h. PO_DOC_TYPE poDocType,\r\n" +
                    "  l.demand_qty poDemandQty,\r\n" +
                    "  l.delivery_qty deliveryQty,\r\n" +
                    " l.demand_qty - ifnull(l.delivery_qty, 0) - (\r\n" + 
                    "		SELECT\r\n" + 
                    "			ifnull(\r\n" + 
                    "				SUM(\r\n" + 
                    "					zL.MATCH_QUANTITY - ifnull(zl.delivery_qty, 0)\r\n" + 
                    "				),\r\n" + 
                    "				0\r\n" + 
                    "			)\r\n" + 
                    "		FROM\r\n" + 
                    "			SRM_PO_NOTICE_LINE zl,\r\n" + 
                    "			srm_po_notice zn\r\n" + 
                    "		WHERE\r\n" + 
                    "			zL.PO_LINE_ID = l.po_line_id\r\n" + 
                    "		AND zl.po_notice_id = zn.po_notice_id\r\n" + 
                    "		AND zn.`status` IN (\r\n" + 
                    "			'CREATED',\r\n" + 
                    "			'CONFIRMED',\r\n" + 
                    "			'REJECTED'\r\n" + 
                    "		)\r\n" + 
                    "	)  canMatchQty     " +
                   // "  get_po_qty_f(l.po_line_id,'CAN_CREATE_NOTICE')  canMatchQty\r\n" +
                    "FROM\r\n" +
                    "	srm_po_headers h,\r\n" +
                    "	srm_po_lines l,\r\n" +
                    "  srm_pos_supplier_info si\r\n" +
                    "WHERE\r\n" +
                    "	h.po_header_id = l.po_header_id\r\n" +
                    "and h.supplier_id = si.supplier_id\r\n" +
                    "and si.srm_delivery = 'Y'\r\n" +
                    "AND l.DELIVERY_TYPE = '2'\r\n" +
                    "and l.`status` ='APPROVED'\r\n";


    public static final String QUERY_SUM_PO_INFO =
            " SELECT\r\n" + 
            "	h.supplier_id supplierId,\r\n" + 
            "	si.supplier_number supplierNumber,\r\n" + 
            "	si.supplier_name supplierName,\r\n" + 
            "	sum(  ifnull(l.demand_qty, 0) - ifnull(l.delivery_qty, 0)\r\n" +  
            "	) - (\r\n" + 
            "		SELECT\r\n" + 
            "			ifnull(\r\n" + 
            "				SUM(\r\n" + 
            "					n.quantity - ifnull(n.delivery_qty, 0)\r\n" + 
            "				),\r\n" + 
            "				0\r\n" + 
            "			)\r\n" + 
            "		FROM\r\n" + 
            "			srm_po_notice n\r\n" + 
            "		WHERE\r\n" + 
            "		    n.supplier_id = h.supplier_id\r\n" + 
            "		AND n.inst_id = l.inst_id\r\n" + 
            "		AND	n.item_id = l.item_id\r\n" + 
            "		AND ifnull(n.SPECIAL_USE_NUM, '') = IFNULL(l.SPECIAL_USE_NUM, '')\r\n" + 
            "		AND n.`status` IN (\r\n" + 
            "			'CREATED',\r\n" + 
            "			'CONFIRMED',\r\n" + 
            "			'REJECTED'\r\n" + 
            "		)\r\n" + 
            "	) canMatchQty\r\n" + 
            "FROM\r\n" + 
            "	srm_po_headers h,\r\n" + 
            "	srm_po_lines l,\r\n" + 
            "	srm_pos_supplier_info si\r\n" + 
            "WHERE\r\n" + 
            "	h.po_header_id = l.po_header_id\r\n" + 
            "AND h.supplier_id = si.supplier_id\r\n" + 
            "and si.srm_delivery = 'Y'\r\n" +
            "AND l.DELIVERY_TYPE = '2'\r\n" + 
            "AND l.`status` = 'APPROVED'\r\n"  ;
    private Integer deliverySiteId;
    private String deliverySiteName;
    private String poDocType;
    private String employeeNumber;
    private Integer starvingNum;
    private String isMatchPo;
    private String poNumber;
    private Integer lineNumber;
    private Integer poHeaderId;
    private String appointSupplierName;
    private String supplierName;
    private String employeeName;
    private BigDecimal needQuantity;
    private BigDecimal canMatchQty;
    private BigDecimal matchQty;
    private BigDecimal markMatchQty;
    private BigDecimal poDemandQty;
    private BigDecimal deliveryQty;
    private Integer poLineId;
    private String instName;
    private String categoryName;
    private String itemCode;
    private String itemName;
    private Integer poStarvingId;
    @JSONField(format = "yyyy-MM-dd")
    private Date shortCheckDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date demandDate;
    private String wipEntityNumber;
    private Integer instId;
    private Integer itemId;
    private Integer supplierId;
    private String categoryCode;
    @JSONField(format = "yyyy-MM-dd")
    private Date needByDate;
    private String supplierNumber;
    private String specialUseNum;
    private String demandClassify;
    private Integer employeeId;
    private BigDecimal noticeQty;

    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    public Integer getDeliverySiteId() {
        return deliverySiteId;
    }

    public void setDeliverySiteId(Integer deliverySiteId) {
        this.deliverySiteId = deliverySiteId;
    }

    public String getDeliverySiteName() {
        return deliverySiteName;
    }

    public void setDeliverySiteName(String deliverySiteName) {
        this.deliverySiteName = deliverySiteName;
    }

    public String getPoDocType() {
        return poDocType;
    }

    public void setPoDocType(String poDocType) {
        this.poDocType = poDocType;
    }

    public BigDecimal getMarkMatchQty() {
        return markMatchQty;
    }

    public void setMarkMatchQty(BigDecimal markMatchQty) {
        this.markMatchQty = markMatchQty;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Integer getStarvingNum() {
        return starvingNum;
    }

    public void setStarvingNum(Integer starvingNum) {
        this.starvingNum = starvingNum;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public BigDecimal getMatchQty() {

        return matchQty;
    }

    public void setMatchQty(BigDecimal matchQty) {
        this.matchQty = matchQty;
    }

    public Integer getPoHeaderId() {
        return poHeaderId;
    }

    public void setPoHeaderId(Integer poHeaderId) {
        this.poHeaderId = poHeaderId;
    }

    public String getAppointSupplierName() {
        return appointSupplierName;
    }

    public void setAppointSupplierName(String appointSupplierName) {
        this.appointSupplierName = appointSupplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getNeedQuantity() {

        return needQuantity;
    }

    public void setNeedQuantity(BigDecimal needQuantity) {

        this.needQuantity = needQuantity;
    }

    public BigDecimal getCanMatchQty() {

        return canMatchQty;
    }

    public void setCanMatchQty(BigDecimal canMatchQty) {
        this.canMatchQty = canMatchQty;
    }

    public Integer getPoLineId() {
        return poLineId;
    }

    public void setPoLineId(Integer poLineId) {
        this.poLineId = poLineId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPoStarvingId() {
        return poStarvingId;
    }

    public void setPoStarvingId(Integer poStarvingId) {
        this.poStarvingId = poStarvingId;
    }

    public Date getShortCheckDate() {
        return shortCheckDate;
    }

    public void setShortCheckDate(Date shortCheckDate) {
        this.shortCheckDate = shortCheckDate;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public String getWipEntityNumber() {
        return wipEntityNumber;
    }

    public void setWipEntityNumber(String wipEntityNumber) {
        this.wipEntityNumber = wipEntityNumber;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Date getNeedByDate() {
        return needByDate;
    }

    public void setNeedByDate(Date needByDate) {
        this.needByDate = needByDate;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getNoticeQty() {

        return noticeQty;
    }

    public void setNoticeQty(BigDecimal noticeQty) {
        this.noticeQty = noticeQty;
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getIsMatchPo() {
        return isMatchPo;
    }

    public void setIsMatchPo(String isMatchPo) {
        this.isMatchPo = isMatchPo;
    }

    public BigDecimal getPoDemandQty() {
        return poDemandQty;
    }

    public void setPoDemandQty(BigDecimal poDemandQty) {
        this.poDemandQty = poDemandQty;
    }

    public BigDecimal getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(BigDecimal deliveryQty) {
        this.deliveryQty = deliveryQty;
    }


    @Override
    public int compare(Object o1, Object o2) {
        SrmPoStarvingEntity_HI_RO s1 = (SrmPoStarvingEntity_HI_RO) o1;
        SrmPoStarvingEntity_HI_RO s2 = (SrmPoStarvingEntity_HI_RO) o2;
        return s1.getStarvingNum().compareTo(s2.getStarvingNum());
    }
}
