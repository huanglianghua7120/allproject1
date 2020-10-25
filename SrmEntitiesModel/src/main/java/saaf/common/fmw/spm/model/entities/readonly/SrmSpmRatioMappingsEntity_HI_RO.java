package saaf.common.fmw.spm.model.entities.readonly;

import java.io.Serializable;
import java.math.BigDecimal;

public class SrmSpmRatioMappingsEntity_HI_RO implements Serializable{


    public static String QUERY_RATIO_MAPPINGS_DATA_LIST =
                    "SELECT rm.mapping_id      mappingId\n" +
                    "      ,rm.org_id          orgId\n" +
                    "      ,ins.inst_name      instName\n" +
                    "      ,rm.vendor_count    vendorCount\n" +
                    "      ,rm.category_id     categoryId\n" +
                    "      ,bc.category_code   categoryCode\n" +
                    "      ,bc.category_name   categoryName\n" +
                    "      ,rm.supply_ratio_1  supplyRatio1\n" +
                    "      ,rm.supply_ratio_2  supplyRatio2\n" +
                    "      ,rm.supply_ratio_3  supplyRatio3\n" +
                    "      ,rm.supply_ratio_4  supplyRatio4\n" +
                    "      ,rm.supply_ratio_5  supplyRatio5\n" +
                    "      ,rm.supply_ratio_6  supplyRatio6\n" +
                    "      ,rm.supply_ratio_7  supplyRatio7\n" +
                    "      ,rm.supply_ratio_8  supplyRatio8\n" +
                    "      ,rm.supply_ratio_9  supplyRatio9\n" +
                    "      ,rm.supply_ratio_10 supplyRatio10\n" +
                    "      ,rm.supply_ratio_11 supplyRatio11\n" +
                    "      ,rm.supply_ratio_12 supplyRatio12\n" +
                    "      ,rm.supply_ratio_13 supplyRatio13\n" +
                    "      ,rm.supply_ratio_14 supplyRatio14\n" +
                    "      ,rm.supply_ratio_15 supplyRatio15\n" +
                    "FROM   srm_spm_ratio_mappings rm\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     rm.vendor_count = slv.lookup_code\n" +
                    "AND    slv.lookup_type = 'SUPPLIERS_QTY'\n" +
                    "LEFT   JOIN saaf_institution ins\n" +
                    "ON     rm.org_id = ins.inst_id, srm_base_categories bc\n" +
                    "WHERE  rm.category_id = bc.category_id\n";

    public static String QUERY_RATIO_MAPPINGS_COUNT =
                    "SELECT COUNT(1) AS count\n" +
                    "FROM   srm_spm_ratio_mappings t\n" +
                    "WHERE  t.org_id = :instid\n" +
                    "AND    t.category_id = :categoryId\n" +
                    "AND    t.vendor_count = :vendorCount\n";

    public static String QUERY_INST_ID = "SELECT s.inst_id instid FROM saaf_institution s WHERE s.inst_name = :instName\n";

    public static String QUERY_CATEGORY_CODE = "SELECT s.category_id categoryid FROM srm_base_categories s WHERE s.category_code = :categoryCode\n";

    private Integer mappingId;
    private Integer orgId;
    private Integer categoryId;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private Integer vendorCount;
    private BigDecimal supplyRatio1;
    private BigDecimal supplyRatio2;
    private BigDecimal supplyRatio3;
    private BigDecimal supplyRatio4;
    private BigDecimal supplyRatio5;
    private BigDecimal supplyRatio6;
    private BigDecimal supplyRatio7;
    private BigDecimal supplyRatio8;
    private BigDecimal supplyRatio9;
    private BigDecimal supplyRatio10;
    private BigDecimal supplyRatio11;
    private BigDecimal supplyRatio12;
    private BigDecimal supplyRatio13;
    private BigDecimal supplyRatio14;
    private BigDecimal supplyRatio15;
    private BigDecimal supplyRatio16;
    private BigDecimal supplyRatio17;
    private BigDecimal supplyRatio18;
    private BigDecimal supplyRatio19;
    private BigDecimal supplyRatio20;
    private String description;
    private String categoryCode;
    private String categoryName;
    private String ts;
    private Integer count;
    private String instName;


    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTs() {
        return "Y";
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

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public Integer getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(Integer vendorCount) {
        this.vendorCount = vendorCount;
    }

    public BigDecimal getSupplyRatio1() {
        return supplyRatio1;
    }

    public void setSupplyRatio1(BigDecimal supplyRatio1) {
        this.supplyRatio1 = supplyRatio1;
    }

    public BigDecimal getSupplyRatio2() {
        return supplyRatio2;
    }

    public void setSupplyRatio2(BigDecimal supplyRatio2) {
        this.supplyRatio2 = supplyRatio2;
    }

    public BigDecimal getSupplyRatio3() {
        return supplyRatio3;
    }

    public void setSupplyRatio3(BigDecimal supplyRatio3) {
        this.supplyRatio3 = supplyRatio3;
    }

    public BigDecimal getSupplyRatio4() {
        return supplyRatio4;
    }

    public void setSupplyRatio4(BigDecimal supplyRatio4) {
        this.supplyRatio4 = supplyRatio4;
    }

    public BigDecimal getSupplyRatio5() {
        return supplyRatio5;
    }

    public void setSupplyRatio5(BigDecimal supplyRatio5) {
        this.supplyRatio5 = supplyRatio5;
    }

    public BigDecimal getSupplyRatio6() {
        return supplyRatio6;
    }

    public void setSupplyRatio6(BigDecimal supplyRatio6) {
        this.supplyRatio6 = supplyRatio6;
    }

    public BigDecimal getSupplyRatio7() {
        return supplyRatio7;
    }

    public void setSupplyRatio7(BigDecimal supplyRatio7) {
        this.supplyRatio7 = supplyRatio7;
    }

    public BigDecimal getSupplyRatio8() {
        return supplyRatio8;
    }

    public void setSupplyRatio8(BigDecimal supplyRatio8) {
        this.supplyRatio8 = supplyRatio8;
    }

    public BigDecimal getSupplyRatio9() {
        return supplyRatio9;
    }

    public void setSupplyRatio9(BigDecimal supplyRatio9) {
        this.supplyRatio9 = supplyRatio9;
    }

    public BigDecimal getSupplyRatio10() {
        return supplyRatio10;
    }

    public void setSupplyRatio10(BigDecimal supplyRatio10) {
        this.supplyRatio10 = supplyRatio10;
    }

    public BigDecimal getSupplyRatio11() {
        return supplyRatio11;
    }

    public void setSupplyRatio11(BigDecimal supplyRatio11) {
        this.supplyRatio11 = supplyRatio11;
    }

    public BigDecimal getSupplyRatio12() {
        return supplyRatio12;
    }

    public void setSupplyRatio12(BigDecimal supplyRatio12) {
        this.supplyRatio12 = supplyRatio12;
    }

    public BigDecimal getSupplyRatio13() {
        return supplyRatio13;
    }

    public void setSupplyRatio13(BigDecimal supplyRatio13) {
        this.supplyRatio13 = supplyRatio13;
    }

    public BigDecimal getSupplyRatio14() {
        return supplyRatio14;
    }

    public void setSupplyRatio14(BigDecimal supplyRatio14) {
        this.supplyRatio14 = supplyRatio14;
    }

    public BigDecimal getSupplyRatio15() {
        return supplyRatio15;
    }

    public void setSupplyRatio15(BigDecimal supplyRatio15) {
        this.supplyRatio15 = supplyRatio15;
    }

    public BigDecimal getSupplyRatio16() {
        return supplyRatio16;
    }

    public void setSupplyRatio16(BigDecimal supplyRatio16) {
        this.supplyRatio16 = supplyRatio16;
    }

    public BigDecimal getSupplyRatio17() {
        return supplyRatio17;
    }

    public void setSupplyRatio17(BigDecimal supplyRatio17) {
        this.supplyRatio17 = supplyRatio17;
    }

    public BigDecimal getSupplyRatio18() {
        return supplyRatio18;
    }

    public void setSupplyRatio18(BigDecimal supplyRatio18) {
        this.supplyRatio18 = supplyRatio18;
    }

    public BigDecimal getSupplyRatio19() {
        return supplyRatio19;
    }

    public void setSupplyRatio19(BigDecimal supplyRatio19) {
        this.supplyRatio19 = supplyRatio19;
    }

    public BigDecimal getSupplyRatio20() {
        return supplyRatio20;
    }

    public void setSupplyRatio20(BigDecimal supplyRatio20) {
        this.supplyRatio20 = supplyRatio20;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
