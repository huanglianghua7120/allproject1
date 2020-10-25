package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafInstitutionEntity_HI_RO implements Serializable {

    public SaafInstitutionEntity_HI_RO() {
        super();
    }

    public static String QUERY_ORG_NAME_BY_ID = "select t.INST_NAME instName from SAAF_INSTITUTION t where t.INST_TYPE = 'ORG' \n";

    public static String QUERY_SQL =
                    "SELECT\n" +
                    "  siv.inst_id AS instId,\n" +
                    "  siv.inst_code AS instCode,\n" +
                    "  siv.inst_name AS instName,\n" +
                    "  siv.inst_type AS instType,\n" +
                    "  val.meaning AS instTypeDesc,\n" +
                    "  sie.inst_name AS parentInstName,\n" +
                    "  siv.parent_inst_id AS parentInstId,\n" +
                    "  siv.inst_level AS intLevel,\n" +
                    "  siv.start_date AS startDate,\n" +
                    "  siv.end_date AS endDate,\n" +
                    "  siv.remark AS remark\n" +
                    "FROM\n" +
                    "  saaf_institution siv\n" +
                    "  LEFT JOIN saaf_institution sie\n" +
                    "    ON (siv.parent_inst_id = sie.inst_id)\n" +
                    "  LEFT JOIN saaf_lookup_values val\n" +
                    "    ON siv.inst_type = val.lookup_code\n" +
                    "    AND val.lookup_type = 'BASE_INST_TYPE'\n" +
                    "WHERE (siv.inst_type = 'ORG' OR siv.inst_type = 'ORGANIZATION')";

    public static String LOV_INST_QUERY_SQL =
                    "SELECT\n" +
                    "  si.inst_id instId,\n" +
                    "  si.inst_code instCode,\n" +
                    "  si.inst_name instName\n" +
                    "FROM\n" +
                    "  saaf_institution si\n" +
                    "WHERE TO_CHAR(si.start_date,'yyyy-MM-dd') <= TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
                    "  AND (\n" +
                    "    si.end_date IS NULL\n" +
                    "    OR TO_CHAR(si.end_date,'yyyy-MM-dd') >= TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
                    "  )";

    public static String INST_QUERY_SQL_BY_USERID =
                    "SELECT\n" +
                    "  si.inst_id instId,\n" +
                    "  si.parent_inst_id parentInstId,\n" +
                    "  si.inst_code instCode,\n" +
                    "  si.inst_name instName\n" +
                    "FROM\n" +
                    "  saaf_institution si,\n" +
                    "  saaf_user_access_orgs suao\n" +
                    "WHERE suao.inst_id = si.inst_id\n" +
                    "  AND CURDATE() BETWEEN si.start_date\n" +
                    "  AND IFNULL(si.end_date, CURDATE())\n" +
                    "  AND suao.user_id = :userId";

    /**
     * 查询有效的库存组织
     */
    public static String QUERY_INSITITUTION_SQL =
                    "SELECT\n" +
                    "  si.inst_code instCode,\n" +
                    "  si.inst_id instId,\n" +
                    "  si.inst_name instName,\n" +
                    "  org.inst_id AS parentInstId,\n" +
                    "  org.inst_name AS parentInstName\n" +
                    "FROM\n" +
                    "  saaf_institution si\n" +
                    "  LEFT JOIN saaf_institution org\n" +
                    "    ON org.inst_id = si.parent_inst_id\n" +
                    "WHERE si.inst_type = 'ORGANIZATION'\n" +
                    "  AND si.enabled = 'Y'";

    /**
     * 查询有效的业务实体
     */
    public static String QUERY_INSITITUTION_SQL_LOV =
                    "SELECT\n" +
                    "  si.inst_id AS instId,\n" +
                    "  si.inst_code AS instCode,\n" +
                    "  si.inst_name AS instName,\n" +
                    "  si.parent_inst_id AS parentInstId,\n" +
                    "  si1.inst_name AS parentInstName\n" +
                    "FROM\n" +
                    "  saaf_institution si\n" +
                    "  LEFT JOIN saaf_institution si1\n" +
                    "    ON si1.inst_id = si.parent_inst_id\n" +
                    "WHERE 1 = 1\n" +
                    "  AND si.inst_type = 'ORG'\n" +
                    "  AND si.enabled = 'Y'";

    /**
     * 查物料组织
     */
    public static String QUERY_ITEM_INSITITUTION_SQL =
                    "SELECT\n" +
                    "  t.inst_id instId,\n" +
                    "  t.inst_name instName,\n" +
                    "  t.inst_code instCode\n" +
                    "FROM\n" +
                    "  saaf_institution t,\n" +
                    "  srm_base_items it\n" +
                    "WHERE t.inst_type = 'ORGANIZATION'\n" +
                    "  AND it.organization_id = t.inst_id\n" +
                    "  AND (it.invalid_date IS NULL\n" +
                    "    OR '')";

    //查询用户拥有的组织权限
    public static final String QUERY_LIST_SQL =
                    "SELECT si.inst_id        AS instid\n" +
                    "      ,si.inst_code      AS instcode\n" +
                    "      ,si.inst_name      AS instname\n" +
                    "      ,si.inst_type      AS insttype\n" +
                    "      ,si.parent_inst_id AS parentinstid\n" +
                    "      ,si.inst_level     AS instlevel\n" +
                    "      ,si.if_leaf        AS ifleaf\n" +
                    "      ,si.platform_code  AS platformcode\n" +
                    "      ,si.start_date     AS startdate\n" +
                    "      ,si.end_date       AS enddate\n" +
                    "      ,si.enabled        AS enabled\n" +
                    "      ,si.remark         AS remark\n" +
                    "      ,slv1.meaning      AS insttypedesc\n" +
                    "FROM   saaf_institution      si\n" +
                    "      ,saaf_user_access_orgs uao\n" +
                    "      ,saaf_lookup_values    slv1\n" +
                    "WHERE  si.inst_id = uao.inst_id\n" +
                    "AND    si.inst_type = slv1.lookup_code\n" +
                    "AND    slv1.lookup_type = 'BASE_INST_TYPE'\n" +
                    /*"AND    si.enabled = 'Y'\n" +*/
                    " AND TO_CHAR(si.start_date,'yyyy-MM-dd') <= TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
                    "  AND (\n" +
                    "    si.end_date IS NULL\n" +
                    "    OR TO_CHAR(si.end_date,'yyyy-MM-dd') >= TO_CHAR(SYSDATE,'yyyy-MM-dd')\n" +
                    "  )"+
                    "AND    uao.user_id = :userId";

    public static final String QUERY_INST_ID_BY_INST_CODE = "SELECT t.inst_id instId FROM saaf_institution t WHERE t.inst_name= :instName";

    /**
     * 查询有效的部门信息
     */
    public static String QUERY_INSITITUTION_BY_DEPT =
                    "SELECT\n" +
                    "  si.inst_id instId,\n" +
                    "  si.inst_code instCode,\n" +
                    "  si.inst_name instName,\n" +
                    "  org.inst_id AS parentInstId,\n" +
                    "  org.inst_name AS parentInstName\n" +
                    "FROM\n" +
                    "  saaf_institution si\n" +
                    "  LEFT JOIN saaf_institution org\n" +
                    "    ON org.inst_id = si.parent_inst_id\n" +
                    "WHERE si.inst_type = 'DEPT'\n" +
                    "  AND si.enabled = 'Y'";

    private Integer instId;
    private Integer parentInstId;
    private String instCode;
    private String instName;
    private String instType;
    private Integer instLevel;
    private Integer ifLeaf;
    private String platformCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private String enabled;
    private String remark;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;
    //自定义
    private String parentInstName;
    private Integer intLevel;
    private String area;
    private String instTypeDesc;

    public Integer getInstLevel() {
        return instLevel;
    }

    public void setInstLevel(Integer instLevel) {
        this.instLevel = instLevel;
    }

    public Integer getIfLeaf() {
        return ifLeaf;
    }

    public void setIfLeaf(Integer ifLeaf) {
        this.ifLeaf = ifLeaf;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getInstType() {
        return instType;
    }

    public void setParentInstId(Integer parentInstId) {
        this.parentInstId = parentInstId;
    }

    public Integer getParentInstId() {
        return parentInstId;
    }

    public void setParentInstName(String parentInstName) {
        this.parentInstName = parentInstName;
    }

    public String getParentInstName() {
        return parentInstName;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setIntLevel(Integer intLevel) {
        this.intLevel = intLevel;
    }

    public Integer getIntLevel() {
        return intLevel;
    }

    public String getInstTypeDesc() {
        return instTypeDesc;
    }

    public void setInstTypeDesc(String instTypeDesc) {
        this.instTypeDesc = instTypeDesc;
    }
}
