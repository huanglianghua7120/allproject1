package saaf.common.fmw.okc.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SrmOkcFreightExpensesEntity_HI_RO implements Serializable {

    public static String SRM_OKC_FREIGHT_EXPENSES_QUERY_SQL =
                    "SELECT\n" +
                    "  ofe.freight_expense_id AS freightExpenseId,\n" +
                    "  ofe.carrier_id AS carrierId,\n" +
                    "  ofe.supplier_id AS supplierId,\n" +
                    "  ofe.supplier_site_id AS supplierSiteId,\n" +
                    "  ofe.organization_id AS organizationId,\n" +
                    "  ofe.transport_method AS transportMethod,\n" +
                    "  ofe.freight_price AS freightPrice,\n" +
                    "  ofe.uom_code AS uomCode,\n" +
                    "  ofe.start_date AS startDate,\n" +
                    "  ofe.end_date AS endDate,\n" +
                    "  ofe.version_num AS versionNum,\n" +
                    "  ofe.creation_date AS creationDate,\n" +
                    "  ofe.created_by AS createdBy,\n" +
                    "  ofe.last_update_date AS lastUpdateDate,\n" +
                    "  ofe.last_updated_by AS lastUpdatedBy,\n" +
                    "  ofe.last_update_login AS lastUpdateLogin,\n" +
                    "  ofe.attribute_category AS attributeCategory,\n" +
                    "  ofe.attribute1 AS attribute1,\n" +
                    "  ofe.attribute2 AS attribute2,\n" +
                    "  ofe.attribute3 AS attribute3,\n" +
                    "  ofe.attribute4 AS attribute4,\n" +
                    "  ofe.attribute5 AS attribute5,\n" +
                    "  ofe.attribute6 AS attribute6,\n" +
                    "  ofe.attribute7 AS attribute7,\n" +
                    "  ofe.attribute8 AS attribute8,\n" +
                    "  ofe.attribute9 AS attribute9,\n" +
                    "  ofe.attribute10 AS attribute10,\n" +
                    "  ofe.attribute11 AS attribute11,\n" +
                    "  ofe.attribute12 AS attribute12,\n" +
                    "  ofe.attribute13 AS attribute13,\n" +
                    "  ofe.attribute14 AS attribute14,\n" +
                    "  ofe.attribute15 AS attribute15,\n" +
                    "  ofe.attribute16 AS attribute16,\n" +
                    "  ofe.attribute17 AS attribute17,\n" +
                    "  ofe.attribute18 AS attribute18,\n" +
                    "  ofe.attribute19 AS attribute19,\n" +
                    "  ofe.attribute20 AS attribute20,\n" +
                    "  ofe.attribute21 AS attribute21,\n" +
                    "  ofe.attribute22 AS attribute22,\n" +
                    "  ofe.attribute23 AS attribute23,\n" +
                    "  ofe.attribute24 AS attribute24,\n" +
                    "  ofe.attribute25 AS attribute25,\n" +
                    "  ofe.attribute26 AS attribute26,\n" +
                    "  ofe.attribute27 AS attribute27,\n" +
                    "  ofe.attribute28 AS attribute28,\n" +
                    "  ofe.attribute29 AS attribute29,\n" +
                    "  ofe.attribute30 AS attribute30,\n" +
                    "  se1.employee_name AS createdByName,\n" +
                    "  se2.employee_name AS lastUpdatedByName,\n" +
                    "  pss.site_name AS siteName,\n" +
                    "  si.inst_name AS instName,\n" +
                    "  si.inst_code AS instCode,\n" +
                    "  psi1.supplier_name AS carrierName,\n" +
                    "  psi1.supplier_number AS carrierNumber,\n" +
                    "  psi2.supplier_name AS supplierName,\n" +
                    "  psi2.supplier_number AS supplierNumber\n" +
                    "FROM\n" +
                    "  srm_okc_freight_expenses ofe\n" +
                    "  INNER JOIN srm_pos_supplier_info psi1\n" +
                    "    ON ofe.carrier_id = psi1.supplier_id\n" +
                    "  INNER JOIN srm_pos_supplier_info psi2\n" +
                    "    ON ofe.supplier_id = psi2.supplier_id\n" +
                    "  INNER JOIN srm_pos_supplier_sites pss\n" +
                    "    ON ofe.supplier_site_id = pss.supplier_site_id\n" +
                    "  INNER JOIN saaf_institution si\n" +
                    "    ON ofe.organization_id = si.inst_id\n" +
                    "  LEFT JOIN saaf_users su1\n" +
                    "    ON su1.user_id = ofe.created_by\n" +
                    "  LEFT JOIN saaf_employees se1\n" +
                    "    ON se1.employee_id = su1.employee_id\n" +
                    "  LEFT JOIN saaf_users su2\n" +
                    "    ON su2.user_id = ofe.last_updated_by\n" +
                    "  LEFT JOIN saaf_employees se2\n" +
                    "    ON se2.employee_id = su2.employee_id\n" +
                    "WHERE 1 = 1";

    public static String QUERY_FREIGHT_PRICE_SQL =
                    "SELECT\n" +
                    "  ofe.freight_expense_id AS freightExpenseId,\n" +
                    "  ofe.carrier_id AS carrierId,\n" +
                    "  ofe.supplier_id AS supplierId,\n" +
                    "  ofe.supplier_site_id AS supplierSiteId,\n" +
                    "  ofe.organization_id AS organizationId,\n" +
                    "  ofe.transport_method AS transportMethod,\n" +
                    "  ofe.freight_price AS freightPrice,\n" +
                    "  ofe.uom_code AS uomCode,\n" +
                    "  ofe.start_date AS startDate,\n" +
                    "  ofe.end_date AS endDate,\n" +
                    "  ofe.version_num AS versionNum,\n" +
                    "  ofe.creation_date AS creationDate,\n" +
                    "  ofe.created_by AS createdBy,\n" +
                    "  ofe.last_update_date AS lastUpdateDate,\n" +
                    "  ofe.last_updated_by AS lastUpdatedBy,\n" +
                    "  ofe.last_update_login AS lastUpdateLogin,\n" +
                    "  ofe.attribute_category AS attributeCategory,\n" +
                    "  ofe.attribute1 AS attribute1,\n" +
                    "  ofe.attribute2 AS attribute2,\n" +
                    "  ofe.attribute3 AS attribute3,\n" +
                    "  ofe.attribute4 AS attribute4,\n" +
                    "  ofe.attribute5 AS attribute5,\n" +
                    "  ofe.attribute6 AS attribute6,\n" +
                    "  ofe.attribute7 AS attribute7,\n" +
                    "  ofe.attribute8 AS attribute8,\n" +
                    "  ofe.attribute9 AS attribute9,\n" +
                    "  ofe.attribute10 AS attribute10,\n" +
                    "  ofe.attribute11 AS attribute11,\n" +
                    "  ofe.attribute12 AS attribute12,\n" +
                    "  ofe.attribute13 AS attribute13,\n" +
                    "  ofe.attribute14 AS attribute14,\n" +
                    "  ofe.attribute15 AS attribute15,\n" +
                    "  ofe.attribute16 AS attribute16,\n" +
                    "  ofe.attribute17 AS attribute17,\n" +
                    "  ofe.attribute18 AS attribute18,\n" +
                    "  ofe.attribute19 AS attribute19,\n" +
                    "  ofe.attribute20 AS attribute20,\n" +
                    "  ofe.attribute21 AS attribute21,\n" +
                    "  ofe.attribute22 AS attribute22,\n" +
                    "  ofe.attribute23 AS attribute23,\n" +
                    "  ofe.attribute24 AS attribute24,\n" +
                    "  ofe.attribute25 AS attribute25,\n" +
                    "  ofe.attribute26 AS attribute26,\n" +
                    "  ofe.attribute27 AS attribute27,\n" +
                    "  ofe.attribute28 AS attribute28,\n" +
                    "  ofe.attribute29 AS attribute29,\n" +
                    "  ofe.attribute30 AS attribute30\n" +
                    "FROM\n" +
                    "  srm_okc_freight_expenses ofe\n" +
                    "WHERE ofe.carrier_id = :carrierId\n" +
                    "  AND ofe.supplier_id = :supplierId\n" +
                    "  AND ofe.supplier_site_id = :supplierSiteId\n" +
                    "  AND ofe.organization_id = :organizationId\n" +
                    "  AND ofe.transport_method = :transportMethod\n" +
                    "  AND ofe.uom_code = :uomCode\n" +
                    "  AND SYSDATE() >= IFNULL(ofe.start_date, SYSDATE() - 1)\n" +
                    "  AND SYSDATE() < IFNULL(ofe.end_date, SYSDATE() + 1)";

    private Integer freightExpenseId;

	private Integer carrierId;

	private Integer supplierId;

	private Integer supplierSiteId;

	private Integer organizationId;

	private String transportMethod;

	private BigDecimal freightPrice;

	private String uomCode;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	private Integer versionNum;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	private Integer createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateDate;

	private Integer lastUpdatedBy;

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

	private String attribute11;

	private String attribute12;

	private String attribute13;

	private String attribute14;

	private String attribute15;

	private Integer attribute16;

	private Integer attribute17;

	private Integer attribute18;

	private Integer attribute19;

	private Integer attribute20;

	private BigDecimal attribute21;

	private BigDecimal attribute22;

	private BigDecimal attribute23;

	private BigDecimal attribute24;

	private BigDecimal attribute25;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute26;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute27;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute28;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute29;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date attribute30;


    private String carrierName;
    private String carrierNumber;
    private String supplierName;
    private String supplierNumber;
    private String siteName;
    private String instName;
    private String instCode;
    private String createdByName;
    private String lastUpdatedByName;

    public SrmOkcFreightExpensesEntity_HI_RO(){
        super();
    }

    public Integer  getFreightExpenseId() {
        return freightExpenseId;
    }

    public void setFreightExpenseId( Integer freightExpenseId) {
        this.freightExpenseId = freightExpenseId;
    }

    public Integer  getCarrierId() {
        return carrierId;
    }

    public void setCarrierId( Integer carrierId) {
        this.carrierId = carrierId;
    }

    public Integer  getSupplierId() {
        return supplierId;
    }

    public void setSupplierId( Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer  getSupplierSiteId() {
        return supplierSiteId;
    }

    public void setSupplierSiteId( Integer supplierSiteId) {
        this.supplierSiteId = supplierSiteId;
    }

    public Integer  getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId( Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String  getTransportMethod() {
        return transportMethod;
    }

    public void setTransportMethod( String transportMethod) {
        this.transportMethod = transportMethod;
    }

    public BigDecimal  getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice( BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String  getUomCode() {
        return uomCode;
    }

    public void setUomCode( String uomCode) {
        this.uomCode = uomCode;
    }

    public Date  getStartDate() {
        return startDate;
    }

    public void setStartDate( Date startDate) {
        this.startDate = startDate;
    }

    public Date  getEndDate() {
        return endDate;
    }

    public void setEndDate( Date endDate) {
        this.endDate = endDate;
    }

    public Integer  getVersionNum() {
        return versionNum;
    }

    public void setVersionNum( Integer versionNum) {
        this.versionNum = versionNum;
    }

    public Date  getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy( Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date  getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate( Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer  getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy( Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer  getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin( Integer lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public String  getAttributeCategory() {
        return attributeCategory;
    }

    public void setAttributeCategory( String attributeCategory) {
        this.attributeCategory = attributeCategory;
    }

    public String  getAttribute1() {
        return attribute1;
    }

    public void setAttribute1( String attribute1) {
        this.attribute1 = attribute1;
    }

    public String  getAttribute2() {
        return attribute2;
    }

    public void setAttribute2( String attribute2) {
        this.attribute2 = attribute2;
    }

    public String  getAttribute3() {
        return attribute3;
    }

    public void setAttribute3( String attribute3) {
        this.attribute3 = attribute3;
    }

    public String  getAttribute4() {
        return attribute4;
    }

    public void setAttribute4( String attribute4) {
        this.attribute4 = attribute4;
    }

    public String  getAttribute5() {
        return attribute5;
    }

    public void setAttribute5( String attribute5) {
        this.attribute5 = attribute5;
    }

    public String  getAttribute6() {
        return attribute6;
    }

    public void setAttribute6( String attribute6) {
        this.attribute6 = attribute6;
    }

    public String  getAttribute7() {
        return attribute7;
    }

    public void setAttribute7( String attribute7) {
        this.attribute7 = attribute7;
    }

    public String  getAttribute8() {
        return attribute8;
    }

    public void setAttribute8( String attribute8) {
        this.attribute8 = attribute8;
    }

    public String  getAttribute9() {
        return attribute9;
    }

    public void setAttribute9( String attribute9) {
        this.attribute9 = attribute9;
    }

    public String  getAttribute10() {
        return attribute10;
    }

    public void setAttribute10( String attribute10) {
        this.attribute10 = attribute10;
    }

    public String  getAttribute11() {
        return attribute11;
    }

    public void setAttribute11( String attribute11) {
        this.attribute11 = attribute11;
    }

    public String  getAttribute12() {
        return attribute12;
    }

    public void setAttribute12( String attribute12) {
        this.attribute12 = attribute12;
    }

    public String  getAttribute13() {
        return attribute13;
    }

    public void setAttribute13( String attribute13) {
        this.attribute13 = attribute13;
    }

    public String  getAttribute14() {
        return attribute14;
    }

    public void setAttribute14( String attribute14) {
        this.attribute14 = attribute14;
    }

    public String  getAttribute15() {
        return attribute15;
    }

    public void setAttribute15( String attribute15) {
        this.attribute15 = attribute15;
    }

    public Integer  getAttribute16() {
        return attribute16;
    }

    public void setAttribute16( Integer attribute16) {
        this.attribute16 = attribute16;
    }

    public Integer  getAttribute17() {
        return attribute17;
    }

    public void setAttribute17( Integer attribute17) {
        this.attribute17 = attribute17;
    }

    public Integer  getAttribute18() {
        return attribute18;
    }

    public void setAttribute18( Integer attribute18) {
        this.attribute18 = attribute18;
    }

    public Integer  getAttribute19() {
        return attribute19;
    }

    public void setAttribute19( Integer attribute19) {
        this.attribute19 = attribute19;
    }

    public Integer  getAttribute20() {
        return attribute20;
    }

    public void setAttribute20( Integer attribute20) {
        this.attribute20 = attribute20;
    }

    public BigDecimal  getAttribute21() {
        return attribute21;
    }

    public void setAttribute21( BigDecimal attribute21) {
        this.attribute21 = attribute21;
    }

    public BigDecimal  getAttribute22() {
        return attribute22;
    }

    public void setAttribute22( BigDecimal attribute22) {
        this.attribute22 = attribute22;
    }

    public BigDecimal  getAttribute23() {
        return attribute23;
    }

    public void setAttribute23( BigDecimal attribute23) {
        this.attribute23 = attribute23;
    }

    public BigDecimal  getAttribute24() {
        return attribute24;
    }

    public void setAttribute24( BigDecimal attribute24) {
        this.attribute24 = attribute24;
    }

    public BigDecimal  getAttribute25() {
        return attribute25;
    }

    public void setAttribute25( BigDecimal attribute25) {
        this.attribute25 = attribute25;
    }

    public Date  getAttribute26() {
        return attribute26;
    }

    public void setAttribute26( Date attribute26) {
        this.attribute26 = attribute26;
    }

    public Date  getAttribute27() {
        return attribute27;
    }

    public void setAttribute27( Date attribute27) {
        this.attribute27 = attribute27;
    }

    public Date  getAttribute28() {
        return attribute28;
    }

    public void setAttribute28( Date attribute28) {
        this.attribute28 = attribute28;
    }

    public Date  getAttribute29() {
        return attribute29;
    }

    public void setAttribute29( Date attribute29) {
        this.attribute29 = attribute29;
    }

    public Date  getAttribute30() {
        return attribute30;
    }

    public void setAttribute30( Date attribute30) {
        this.attribute30 = attribute30;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCarrierNumber() {
        return carrierNumber;
    }

    public void setCarrierNumber(String carrierNumber) {
        this.carrierNumber = carrierNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        this.supplierNumber = supplierNumber;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastUpdatedByName() {
        return lastUpdatedByName;
    }

    public void setLastUpdatedByName(String lastUpdatedByName) {
        this.lastUpdatedByName = lastUpdatedByName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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
}
