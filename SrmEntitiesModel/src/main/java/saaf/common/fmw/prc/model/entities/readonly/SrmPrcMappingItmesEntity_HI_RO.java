package saaf.common.fmw.prc.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingItemsEntity_HI;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingSuppliersEntity_HI;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class SrmPrcMappingItmesEntity_HI_RO implements Serializable {

    public static final String QUERY_MAPPING_ITEMS_SQL = "SELECT \n" +
            "\tmh.MAPPING_HEADER_ID mappingHeaderId,\n" +
            "\tmi.MAPPING_ITEM_ID mappingItemId,\n" +
            "\tmh.INDICATOR_NUMBER indicatorNumber,\n" +
            "\tih.INDICATOR_NAME indicatorName,\n" +
            "\tih.COMPUTATIONAL_FORMULA computationalFormula,\n" +
            "\tmi.ITEM_NUMBER itemNumber,\n" +
            "\tmi.ITEM_DESCRIPTION itemDescription,\n" +
            "\tlv.meaning mappingStatus,\n" +
            "\tmi.ITEM_PRICE itemPrice,\n" +
            "\tsu.user_full_name createUserName,\n" +
            "\tmh.CREATION_DATE creationDate,\n" +
            "\tsu2.user_full_name lastUpdateUserName,\n" +
            "\tmi.LAST_UPDATE_DATE lastUpdateDate\n" +
            "FROM \n" +
            "\tsrm_prc_mapping_items AS mi\n" +
            "INNER JOIN srm_prc_mapping_headers AS mh on mh.MAPPING_HEADER_ID = mi.MAPPING_HEADER_ID\n" +
            "INNER JOIN (SELECT t.INDICATOR_NUMBER,t.INDICATOR_NAME,t.COMPUTATIONAL_FORMULA FROM srm_prc_indicator_headers AS t where t.INDICATOR_STATUS='ACT') AS ih on ih.INDICATOR_NUMBER = mh.INDICATOR_NUMBER\n" +
            "INNER JOIN saaf_lookup_values AS lv on lv.lookup_type='PRC_MAPPING_STATUS' and lv.lookup_code=mh.MAPPING_STATUS\n" +
            "INNER JOIN saaf_users AS su on su.user_id = mh.CREATED_BY\n" +
            "INNER JOIN saaf_users AS su2 on su2.user_id = mi.LAST_UPDATED_BY\n" +
            "where 1=1 ";
    public static final String QUERY_MAPPING_ITEMS_LOV_SQL = "SELECT \n" +
            "ih.INDICATOR_NUMBER indicatorNumber,\n" +
            "ih.INDICATOR_NAME indicatorName\n" +
            "FROM\n" +
            "srm_prc_indicator_headers AS ih\n" +
            "where ih.INDICATOR_STATUS='ACT' ";

    public static final String QUERY_FIND_BY_MAPPINGING_ITEMS_ID_SQL = "SELECT \n" +
            "mi.MAPPING_ITEM_ID mappingItemId,\n" +
            "  mi.ITEM_NUMBER itemNumber,\n" +
            "  mi.ITEM_ID itemId,\n" +
            "  mi.ITEM_DESCRIPTION itemDescription,\n" +
            "  mi.UNIT_OF_MEASURE unitOfMeasure,\n" +
            "  mi.LENGTH_VALUE lengthValue,\n" +
            "  mi.WIDTH_VALUE widthValue,\n" +
            "  mi.HEIGHT_VALUE heightValue,\n" +
            "  mi.ITEM_PRICE itemPrice,\n" +
            "  mi.VERSION_NUM versionNum\n" +
            "FROM srm_prc_mapping_items AS mi\n" +
            "WHERE mi.MAPPING_HEADER_ID=:mappingHeaderId ";
    public static final String QUERY_FIND_BY_HEADERS_SQL="SELECT \n" +
            "mh.MAPPING_HEADER_ID mappingHeaderId,\n" +
            "ih.INDICATOR_NUMBER indicatorNumber,\n" +
            "  ih.INDICATOR_NAME indicatorName,\n" +
            "  su1.user_full_name createUserName,\n" +
            "  mh.CREATION_DATE creationDate,\n" +
            "  su2.user_full_name lastUpdateUserName,\n" +
            "  mh.LAST_UPDATE_DATE lastUpdateDate,\n" +
            "  slv.meaning mappingStatus,\n" +
            "  mh.MAPPING_STATUS mappingStatusCode,\n" +
            "  mh.VERSION_NUM versionNum\n" +
            "FROM\n" +
            "    srm_prc_mapping_headers AS mh\n" +
            "    INNER JOIN (select t.INDICATOR_NAME,t.INDICATOR_NUMBER from srm_prc_indicator_headers as t where t.INDICATOR_STATUS='ACT') AS ih ON ih.INDICATOR_NUMBER = mh.INDICATOR_NUMBER\n" +
            "    INNER JOIN saaf_users AS su1 ON su1.user_id=mh.CREATED_BY\n" +
            "    INNER JOIN saaf_users AS su2 ON su2.user_id=mh.LAST_UPDATED_BY\n" +
            "    INNER JOIN saaf_lookup_values AS slv ON slv.lookup_type='PRC_MAPPING_STATUS' and slv.lookup_code=mh.MAPPING_STATUS\n" +
            " where mh.MAPPING_HEADER_ID=:mappingHeaderId";
    public static final String COUNT_MAPPINGF_HEADER_SQL="SELECT\n" +
            "\tmi.MAPPING_ITEM_ID mappingItemId\n" +
            "FROM\n" +
            "\tsrm_prc_mapping_items AS mi\n" +
            "INNER JOIN srm_prc_mapping_headers AS mh ON mh.MAPPING_HEADER_ID = mi.MAPPING_HEADER_ID\n" +
            "WHERE\n" +
            "\t mi.ITEM_NUMBER = ? and mh.MAPPING_HEADER_ID=?";

    public static final String QUERY_FIND_MAPPINGF_HEADER_ID_SQL="SELECT\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\tsrm_prc_mapping_items AS mi\n" +
            "INNER JOIN srm_prc_mapping_headers AS mh ON mh.MAPPING_HEADER_ID = mi.MAPPING_HEADER_ID\n" +
            "WHERE\n" +
            "\t mi.ITEM_NUMBER = ? and mh.MAPPING_HEADER_ID=?";


    public static final String QUERY_MAPPING_COUNT_SQL="SELECT\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\tsrm_prc_mapping_items AS mi\n" +
            "INNER JOIN srm_prc_mapping_suppliers AS ms ON mi.MAPPING_ITEM_ID = ms.MAPPING_ITEM_ID\n" +
            "AND mi.MAPPING_HEADER_ID = ms.MAPPING_HEADER_ID\n" +
            "WHERE\n" +
            "\tmi.ITEM_ID = ?\n" +
            "AND ms.SUPPLIER_ID = ?\n" +
            "AND mi.MAPPING_HEADER_ID =?";
    public static final String QUERY_MAPPING_HEADER_ACT_SQL="SELECT \n" +
            "\tcount(*) count\n" +
            "FROM \n" +
            "\tsrm_prc_mapping_items AS mi\n" +
            "INNER JOIN srm_prc_mapping_headers AS mh on mh.MAPPING_HEADER_ID = mi.MAPPING_HEADER_ID\n" +
            "INNER JOIN (SELECT t.INDICATOR_NUMBER FROM srm_prc_indicator_headers AS t where t.INDICATOR_STATUS='ACT') AS ih \n" +
            "on ih.INDICATOR_NUMBER = mh.INDICATOR_NUMBER\n" +
            "where \n" +
            "  mh.MAPPING_STATUS ='ACT' \n" +
            "and mh.INDICATOR_NUMBER = ?";
    private Integer supplierId;
    private String supplierNumber;
    private String supplierName;
    private BigDecimal supplierPrice;
    private Integer itemId;
    private Integer count;
    private String computationalFormula;
    private String mappingStatusCode;
    private Integer mappingItemId;
    private String unitOfMeasure;
    private BigDecimal lengthValue;
    private BigDecimal widthValue;
    private BigDecimal heightValue;
    private Integer versionNum;
    private Integer mappingHeaderId;
    private String indicatorNumber;
    private String indicatorName;
    private String itemNumber;
    private String itemDescription;
    private String createUserName;
    private String lastUpdateUserName;
    private String mappingStatus;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private BigDecimal itemPrice;

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

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getComputationalFormula() {
        return computationalFormula;
    }

    public void setComputationalFormula(String computationalFormula) {
        this.computationalFormula = computationalFormula;
    }

    public String getMappingStatusCode() {
        return mappingStatusCode;
    }

    public void setMappingStatusCode(String mappingStatusCode) {
        this.mappingStatusCode = mappingStatusCode;
    }

    public Integer getMappingItemId() {
        return mappingItemId;
    }

    public void setMappingItemId(Integer mappingItemId) {
        this.mappingItemId = mappingItemId;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public BigDecimal getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(BigDecimal lengthValue) {
        this.lengthValue = lengthValue;
    }

    public BigDecimal getWidthValue() {
        return widthValue;
    }

    public void setWidthValue(BigDecimal widthValue) {
        this.widthValue = widthValue;
    }

    public BigDecimal getHeightValue() {
        return heightValue;
    }

    public void setHeightValue(BigDecimal heightValue) {
        this.heightValue = heightValue;
    }




    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Integer getMappingHeaderId() {
        return mappingHeaderId;
    }

    public void setMappingHeaderId(Integer mappingHeaderId) {
        this.mappingHeaderId = mappingHeaderId;
    }

    public String getIndicatorNumber() {
        return indicatorNumber;
    }

    public void setIndicatorNumber(String indicatorNumber) {
        this.indicatorNumber = indicatorNumber;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getLastUpdateUserName() {
        return lastUpdateUserName;
    }

    public void setLastUpdateUserName(String lastUpdateUserName) {
        this.lastUpdateUserName = lastUpdateUserName;
    }

    public String getMappingStatus() {
        return mappingStatus;
    }

    public void setMappingStatus(String mappingStatus) {
        this.mappingStatus = mappingStatus;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrmPrcMappingItmesEntity_HI_RO that = (SrmPrcMappingItmesEntity_HI_RO) o;

        if (!itemId.equals(that.itemId)) return false;
        return itemNumber.equals(that.itemNumber);
    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + itemNumber.hashCode();
        return result;
    }
}
