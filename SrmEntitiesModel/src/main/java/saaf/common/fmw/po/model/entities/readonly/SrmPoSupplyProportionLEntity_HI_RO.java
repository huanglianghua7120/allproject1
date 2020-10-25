package saaf.common.fmw.po.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SrmPoSupplyProportionLEntity_HI_RO implements Serializable {

    public static final String QUERY_SUPPLY_PROPORTION_L_SQL = "SELECT \n" +
            "spspl.SUPPLY_DETAIL_ID supplyDetailId,\n" +
            "\tspspl.SUPPLY_ID supplyId,\n" +
            "\tspspl.SUPPLIER_ID supplierId,\n" +
            "\tsi.supplier_number supplierNmuber,\n" +
            "\tsi.SUPPLIER_NAME supplierName,\n" +
            "\tspspl.PROPORTION proportion,\n" +
            "\tspspl.ENABLE_FLAG enableFlag\n" +
            "FROM\n" +
            "\tsrm_po_supply_proportion_l AS spspl\n" +
            "LEFT JOIN srm_pos_supplier_info AS si ON si.supplier_id = spspl.SUPPLIER_ID\n" +
            "WHERE\n" +
            "\t1 = 1";

    private Integer supplyDetailId;
    private Integer supplyId;
    private Integer supplierId;
    private String supplierNmuber;
    private String supplierName;
    private BigDecimal proportion;
    private String enableFlag;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    private String attributeCategory;

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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierNmuber() {
        return supplierNmuber;
    }

    public void setSupplierNmuber(String supplierNmuber) {
        this.supplierNmuber = supplierNmuber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
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
