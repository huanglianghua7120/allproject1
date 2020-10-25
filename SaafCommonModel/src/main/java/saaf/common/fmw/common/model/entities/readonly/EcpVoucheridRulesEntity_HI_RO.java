package saaf.common.fmw.common.model.entities.readonly;

public class EcpVoucheridRulesEntity_HI_RO {
    public static String sql_Rule = "     select rules_id   \"rulesId\"\n" +
        "            ,org_id    \"orgId\"\n" +
        "            ,site_id    \"siteId\"\n" +
        "            ,organization_id   \"organizationId\"\n" +
        "            ,customer_id    \"customerId\"\n" +
        "            ,voucher_table   \"voucherTable\"\n" +
        "            ,voucherid_prefix  \"voucheridPrefix\"\n" +
        "            ,voucher_type     \"voucherType\"\n" +
        "            ,voucherid_length   \"voucheridLength\"\n" +
        "            ,date_format(CURDATE(), voucher_type)  \"currentVoucherType\"\n" +
        "        from ecp_voucherid_rules t\n" +
        "       where 1 = 1";

    private Integer rulesId;
    private Integer orgId;
    private Integer siteId;
    private Integer organizationId;
    private Integer customerId;
    private String voucherTable;
    private String voucheridPrefix;
    private String voucherType;
    private Integer voucheridLength;
    private String currentVoucherType;

    public String getCurrentVoucherType() {
        return currentVoucherType;
    }

    public void setCurrentVoucherType(String currentVoucherType) {
        this.currentVoucherType = currentVoucherType;
    }

    public Integer getRulesId() {
        return rulesId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getVoucherTable() {
        return voucherTable;
    }

    public void setVoucherTable(String voucherTable) {
        this.voucherTable = voucherTable;
    }

    public String getVoucheridPrefix() {
        return voucheridPrefix;
    }

    public void setVoucheridPrefix(String voucheridPrefix) {
        this.voucheridPrefix = voucheridPrefix;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getVoucheridLength() {
        return voucheridLength;
    }

    public void setVoucheridLength(Integer voucheridLength) {
        this.voucheridLength = voucheridLength;
    }


}
