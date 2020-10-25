package saaf.common.fmw.spm.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class SrmSpmSupplierExceptionEntity_HI_RO implements Serializable{

    public static String QUERY_EXCEPTION_INFO_LIST =
                    "SELECT se.exception_id     exceptionId\n" +
                    "      ,se.org_id           orgId\n" +
                    "      ,ins.inst_name       instName\n" +
                    "      ,slv.meaning         exceptionObject\n" +
                    "      ,si.supplier_name    supplierName\n" +
                    "      ,si.supplier_number  supplierNumber\n" +
                    "      ,bc.category_code    categoryCode\n" +
                    "      ,bc.category_name    categoryName\n" +
                    "      ,slv1.meaning        exceptionType\n" +
                    "      ,se.start_date       startDate\n" +
                    "      ,se.end_date         endDate\n" +
                    "      ,se.description      description\n" +
                    "      ,slv2.meaning        exceptionStatus\n" +
                    "      ,se.exception_status en_exceptionStatus\n" +
                    "      ,su.user_full_name   creationName\n" +
                    "      ,se.creation_date    creationDate\n" +
                    "      ,u.user_full_name    updatedName\n" +
                    "      ,se.last_update_date updateDate\n" +
                    "FROM   srm_spm_supplier_exception se\n" +
                    "LEFT   JOIN srm_pos_supplier_info si\n" +
                    "ON     se.supplier_id = si.supplier_id\n" +
                    "LEFT   JOIN srm_base_categories bc\n" +
                    "ON     se.category_id = bc.category_id\n" +
                    "LEFT   JOIN saaf_institution ins\n" +
                    "ON     se.org_id = ins.inst_id\n" +
                    "LEFT   JOIN saaf_users su\n" +
                    "ON     se.created_by = su.user_id\n" +
                    "LEFT   JOIN saaf_users u\n" +
                    "ON     se.last_updated_by = u.user_id\n" +
                    "LEFT   JOIN saaf_lookup_values slv\n" +
                    "ON     slv.lookup_code = se.exception_object\n" +
                    "AND    slv.lookup_type = 'SPM_EXCEPTION_OBJECT'\n" +
                    "LEFT   JOIN saaf_lookup_values slv1\n" +
                    "ON     slv1.lookup_code = se.exception_type\n" +
                    "AND    slv1.lookup_type = 'SPM_EXCEPTION_TYPE'\n" +
                    "LEFT   JOIN saaf_lookup_values slv2\n" +
                    "ON     slv2.lookup_code = se.exception_status\n" +
                    "AND    slv2.lookup_type = 'SPM_EXCEPTION_STATUS'\n" +
                    "WHERE  1 = 1\n";

    public static String QUERY_SUPPLIER_ID = "SELECT s.supplier_id supplierId FROM srm_pos_supplier_info s WHERE s.supplier_name = :supplierName";

    public static String QUERY_LOOKUP_CODE = "SELECT s.lookup_code lookupCode FROM saaf_lookup_values s WHERE s.meaning = :meaning AND s.lookup_type = :lookupType";

    public static String QUERY_CATEGORY_CODE = "SELECT s.category_id categoryId FROM srm_base_categories s WHERE s.category_code = :categoryCode";

    public static String QUERY_INST_ID = "SELECT s.inst_id instId FROM saaf_institution s WHERE s.inst_name = :orgName";

    public static String QUERY_COUNT =
                    "SELECT COUNT(1)\n" +
                    "FROM   srm_spm_supplier_exception t\n" +
                    "WHERE  (t.org_id = :orgId AND t.supplier_id = :supplierId AND\n" +
                    "       t.exception_type = :exceptionType)\n" +
                    "OR     (t.org_id = :orgId AND t.category_id = :categoryId AND\n" +
                    "      t.exception_type = :exceptionType)\n";

    public static String QUERY_SUPPLIER_CATEGORY="SELECT supplier_id, category_id FROM srm_spm_supplier_exception WHERE exception_id = :exceptionId ";

    public static String QUERY_COUNT1 =
                    "SELECT COUNT(1) AS count1\n" +
                    "FROM   srm_spm_supplier_exception sse\n" +
                    "WHERE  sse.exception_id = :exceptionId\n" +
                    "AND    sse.exception_status = 'ACTIVE'\n";

    public static String QUERY_COUNT3 =
                    "SELECT COUNT(1) AS count3\n" +
                    "FROM   srm_spm_supplier_exception\n" +
                    "WHERE  exception_status = 'ACTIVE'\n" +
                    "AND    org_id = :instId\n" +
                    "AND    (category_id = :categoryId OR supplier_id = :supplierId)\n" +
                    "AND    exception_type = :exceptionType\n";

    private Integer exceptionId;
    private Integer orgId;
    private String exceptionObject;
    private Integer supplierId;
    private Integer categoryId;
    private String segment1;
    private String segment2;
    private String segment3;
    private String segment4;
    private String exceptionType;
    private String exceptionStatus;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    private String instName;
    private String supplierName;
    private String supplierNumber;
    private String categoryCode;
    private String categoryName;
    private String creationName;
    private String updatedName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
    private String description;
    private String ts;
    private Integer count;
    private Integer count1;
    private Integer count2;
    private Integer count3;
    private Integer count4;
    private String en_exceptionStatus;


    public String getEn_exceptionStatus() {
        return en_exceptionStatus;
    }

    public void setEn_exceptionStatus(String en_exceptionStatus) {
        this.en_exceptionStatus = en_exceptionStatus;
    }

    public Integer getCount3() {
        return count3;
    }

    public void setCount3(Integer count3) {
        this.count3 = count3;
    }

    public Integer getCount4() {
        return count4;
    }

    public void setCount4(Integer count4) {
        this.count4 = count4;
    }

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
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
    public Integer getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(Integer exceptionId) {
        this.exceptionId = exceptionId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getExceptionObject() {
        return exceptionObject;
    }

    public void setExceptionObject(String exceptionObject) {
        this.exceptionObject = exceptionObject;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionStatus() {
        return exceptionStatus;
    }

    public void setExceptionStatus(String exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
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

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
