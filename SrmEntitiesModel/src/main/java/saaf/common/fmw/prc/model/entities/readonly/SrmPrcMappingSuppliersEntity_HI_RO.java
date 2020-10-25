package saaf.common.fmw.prc.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmPrcMappingSuppliersEntity_HI_RO implements Serializable {
    public static final String QUERY_MAPPING_SUPPLIER_BY_ID = "SELECT \n" +
            "ms.MAPPING_HEADER_ID mappingHeaderId,\n" +
            "\tsi.supplier_number supplierNumber,\n" +
            "\tsi.supplier_name supplierName,\n" +
            "\tms.ORIGINAL_PRICE originalPrice\n" +
            "FROM\n" +
            "\tsrm_prc_mapping_suppliers AS ms\n" +
            "INNER JOIN srm_pos_supplier_info AS si ON si.supplier_id = ms.supplier_id\n" +
            "WHERE\n" +
            "\tms.MAPPING_ITEM_ID=:mappingItemId";

    private String supplierNumber;
    private String supplierName;
    private BigDecimal originalPrice;

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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }
}
