package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPoSupplyProportionHEntity_HI_RO implements Serializable {

    public static final String QUERY_SUPPLY_PROPORTION_SQL = "SELECT DISTINCT\n" +
            "\tsph.SUPPLY_ID supplyId,\n" +
            "\tsph.SUPPLY_TYPE supplyType,\n" +
            "\tbc.CATEGORY_CODE smallCategoryCode,\n" +
            "\tbc.CATEGORY_NAME smallCategoryName,\n" +
            "\tbi.ITEM_CODE itemCode,\n" +
            "\tbi.ITEM_NAME itemDescription,\n" +
            "\tsph.INST_ID instId,\n" +
            "\tsph.START_DATE_ACTIVE startDateActive,\n" +
            "\tsph.END_DATE_ACTIVE endDateActive,\n" +
            "\tsph.DESCRIPTION description,\n" +
            "\tsph.`STATUS` STATUS,\n" +
            "\tsi.inst_name instName,\n" +
            "\tslv.meaning meaning\n" +
            "FROM\n" +
            "\tsrm_po_supply_proportion_h AS sph\n" +
            "LEFT JOIN srm_base_items AS bi ON bi.ITEM_ID = sph.ITEM_ID\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = sph.inst_id\n" +
            "LEFT JOIN srm_base_categories bc ON bc.CATEGORY_CODE = sph.SMALL_CATEGORY_CODE\n" +
            "LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = sph.supply_type and slv.lookup_type='SUPPLU_PROPORTION_CLASSIFY'\n" +
            "WHERE\n" +
            "\t1 = 1";

    //public static final String QUERY_DATE_ISREPEAT_SQL = "SELECT count(t.START_DATE_ACTIVE) FROM srm_po_supply_proportion_h AS t WHERE 1=1";

    public static final String QUERY_SUPPLY_PRINT_SQL = "SELECT \n" +
            "\tif(h.SUPPLY_TYPE='CLASSIFY','分类','物料') supplyType,\n" +
            "\th.SMALL_CATEGORY_CODE smallCategoryCode,\n" +
            "  h.SMALL_CATEGORY_NAME smallCategoryName,\n" +
            "  h.ITEM_CODE itemCode,\n" +
            "  h.ITEM_DESCRIPTION itemDescription,\n" +
            "  si.inst_id instId,\n" +
            "  si.inst_name instName,\n" +
            "  h.START_DATE_ACTIVE startDateActive,\n" +
            "  h.END_DATE_ACTIVE endDateActive,\n" +
            "  if(h.status='Y','是','否') status,\n" +
            "\tl.SUPPLIER_NMUBER supplierNmuber,\n" +
            "  l.SUPPLIER_NAME supplierName,\n" +
            "  l.PROPORTION proportion,\n" +
            "  l.ENABLE_FLAG enableFlag\n" +
            "FROM\n" +
            "\tsrm_po_supply_proportion_h AS h\n" +
            "left join srm_po_supply_proportion_l as l on l.supply_id = h.supply_id\n" +
            "LEFT JOIN saaf_institution AS si ON h.inst_id = si.inst_id\n" +
            "WHERE\n" +
            "\t1 = 1\n";
    //根据块码类型跟 说明 返回code
    public static final String QUERY_LOOKUP_BY_TYPE = "SELECT\n" +
            "\tv.lookup_code lookupCode\n" +
            "FROM\n" +
            "\tsaaf_lookup_values v\n" +
            "WHERE\n" +
            "\tv.lookup_type ='SUPPLU_PROPORTION_CLASSIFY'\n" +
            "AND v.meaning =:meaning";
    public static final String QUERY_CATEGORIES_SQL = "SELECT\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\tsrm_base_categories t\n" +
            "WHERE\n" +
            "\tt.category_code = :smallCateGoryCode";

    public static final String QUERY_ITEM_SQL = "SELECT\n" +
            "\tt.item_id itemId\n" +
            "FROM\n" +
            "\tsrm_base_items t\n" +
            "WHERE\n" +
            "\tt.ITEM_CODE = :itemCode";
    public static final String QUERY_ITEMNAME_SQL = "SELECT\n" +
            "\tt.item_id itemId,\n" +
            "\tt.item_name itemDescription\n" +
            "FROM\n" +
            "\tsrm_base_items t\n" +
            "WHERE\n" +
            "\tt.ITEM_CODE = :itemCode";
    public static final String QUERY_SUPPLIER_SQL = "SELECT\n" +
            "\tt.supplier_id supplierId\n" +
            "FROM\n" +
            "\tsrm_pos_supplier_info t\n" +
            "WHERE\n" +
            "\tt.supplier_number = :supplierNumber";

    public static final String QUERY_SUPPLY_PROPORTION_SQL_SUPPLY_RATIO = "SELECT DISTINCT\n" +
            "\tsph.SUPPLY_ID supplyId,\n" +
            "\tsph.SUPPLY_TYPE supplyType,\n" +
            "\tbc.CATEGORY_CODE smallCategoryCode,\n" +
            "\tbc.CATEGORY_NAME smallCategoryName,\n" +
            "\tbi.ITEM_CODE itemCode,\n" +
            "\tbi.ITEM_NAME itemDescription,\n" +
            "\tsph.INST_ID instId,\n" +
            "\tsph.START_DATE_ACTIVE startDateActive,\n" +
            "\tsph.END_DATE_ACTIVE endDateActive,\n" +
            "\tsph.DESCRIPTION description,\n" +
            "\tsph.`STATUS` STATUS,\n" +
            "\tsi.inst_name instName,\n" +
            "\tslv.meaning meaning\n" +
            "FROM\n" +
            "\tsrm_po_supply_proportion_h AS sph\n" +
            "LEFT JOIN srm_base_items AS bi ON bi.ITEM_ID = sph.ITEM_ID\n" +
            "LEFT JOIN saaf_institution AS si ON si.inst_id = sph.inst_id\n" +
            "LEFT JOIN srm_base_categories bc ON bc.CATEGORY_CODE = sph.SMALL_CATEGORY_CODE\n" +
            "LEFT JOIN saaf_lookup_values slv ON slv.lookup_code = sph.supply_type and slv.lookup_type='SUPPLU_PROPORTION_CLASSIFY'\n" +
            "WHERE\n" +
            "\tsph.STATUS='Y'\n";

    //分类code
    private String lookupCode;
    //统计
    private Integer count;

    private Integer itemId;
    private Integer supplierId;
    private BigDecimal proportion;
    private String supplierName;
    private String supplierNmuber;
    private String enableFlag;
    private String ts;
    private String meaning;
    private Integer supplyId;
    private String supplyType;
    private String smallCategoryCode;
    private String smallCategoryName;
    private String itemCode;
    private String itemDescription;
    private Integer instId;
    private String instName;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateActive;
    private String description;
    private String status;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getEnableFlag() {
        if ("Y".equals(this.enableFlag)) return "是";
        if ("N".equals(this.enableFlag)) return "否";
        return this.enableFlag;
    }

    public String getTs() {
        return "Y";
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNmuber() {
        return supplierNmuber;
    }

    public void setSupplierNmuber(String supplierNmuber) {
        this.supplierNmuber = supplierNmuber;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public Integer getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Integer supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public String getSmallCategoryCode() {
        return smallCategoryCode;
    }

    public void setSmallCategoryCode(String smallCategoryCode) {
        this.smallCategoryCode = smallCategoryCode;
    }

    public String getSmallCategoryName() {
        return smallCategoryName;
    }

    public void setSmallCategoryName(String smallCategoryName) {
        this.smallCategoryName = smallCategoryName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


}
