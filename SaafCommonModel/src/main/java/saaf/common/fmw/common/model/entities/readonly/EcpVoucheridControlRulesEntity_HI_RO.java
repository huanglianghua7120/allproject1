package saaf.common.fmw.common.model.entities.readonly;

public class EcpVoucheridControlRulesEntity_HI_RO {
    public static String sql_control = " SELECT vc.current_voucherid  \"currentVoucherid\"\n" +
        "      ,vc.rules_id   \"rulesId\"\n" +
        "      ,vc.customer_id  \"customerId\"\n" +
        "      ,vc.org_id    \"orgId\"\n" +
        "      ,vc.site_id   \"siteId\"\n" +
        "      ,vc.organization_id   \"organizationId\"\n" +
        "      ,vc.voucher_table   \"voucherTable\"\n" +
        "      ,vc.voucherid_prefix   \"voucheridPrefix\"\n" +
        "      ,vc.current_voucher_type  \"currentVoucherType\"\n" +
        "      ,vc.voucherid_length   \"voucheridLength\"\n" +
        "FROM   ecp_voucherid_control vc\n" +
        "      ,ecp_voucherid_rules   vr\n" +
        "WHERE  1 = 1\n" +
        "AND    vc.rules_id = vr.rules_id\n" +
        "AND    (vc.org_id = vr.org_id OR vr.org_id_ctlflag IS NULL OR\n" +
        "      vr.org_id_ctlflag = 'N')\n" +
        "AND    (vc.site_id = vr.site_id OR vr.site_id_ctlflag IS NULL OR\n" +
        "      vr.site_id_ctlflag = 'N')\n" +
        "AND    (vc.organization_id = vr.organization_id OR\n" +
        "      vr.organization_id_ctlflag IS NULL OR\n" +
        "      vr.organization_id_ctlflag = 'N')\n" +
        "AND    (vc.customer_id = vr.customer_id OR vr.customer_id_ctlflag IS NULL OR\n" +
        "      vr.customer_id_ctlflag = 'N')\n" +
        "AND    vc.voucher_table = vr.voucher_table\n" +
        "AND    ((vc.voucherid_prefix IS NULL AND vr.voucherid_prefix IS NULL) OR\n" +
        "      vc.voucherid_prefix = vr.voucherid_prefix)\n" +
        "AND    vc.voucherid_length = vr.voucherid_length\n" +
        "AND    (vc.current_voucher_type =\n" +
        "      date_format(CURDATE(), vr.voucher_type) OR\n" +
        "      (vc.current_voucher_type IS NULL AND vr.voucher_type IS NULL))";

    private Integer currentVoucherid;
    private Integer rulesId;
    private Integer customerId;
    private Integer orgId;
    private Integer siteId;
    private Integer organizationId;
    private String voucherTable;
    private String voucheridPrefix;
    private String currentVoucherType;
    private Integer voucheridLength;


    public Integer getCurrentVoucherid() {
        return currentVoucherid;
    }

    public void setCurrentVoucherid(Integer currentVoucherid) {
        this.currentVoucherid = currentVoucherid;
    }

    public Integer getRulesId() {
        return rulesId;
    }

    public void setRulesId(Integer rulesId) {
        this.rulesId = rulesId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getCurrentVoucherType() {
        return currentVoucherType;
    }

    public void setCurrentVoucherType(String currentVoucherType) {
        this.currentVoucherType = currentVoucherType;
    }

    public Integer getVoucheridLength() {
        return voucheridLength;
    }

    public void setVoucheridLength(Integer voucheridLength) {
        this.voucheridLength = voucheridLength;
    }

}
