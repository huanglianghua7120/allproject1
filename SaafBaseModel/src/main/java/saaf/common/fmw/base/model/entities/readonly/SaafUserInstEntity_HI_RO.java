package saaf.common.fmw.base.model.entities.readonly;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.util.Date;


public class SaafUserInstEntity_HI_RO implements Serializable {


    public static String QUERY_USER_INST_SQL = "  SELECT sua.access_org_id \"accessOrgId\"\n" +
        "    		      ,sua.inst_id       \"instId\"\n" +
        "    		      ,sua.user_id       \"userId\"\n" +
        "    		      ,su.user_name      \"userName\"\n" +
        "    		      ,su.user_full_name \"userFullName\"\n" +
        "    		      ,sua.start_date    \"startDate\"\n" +
        "    		      ,sua.end_date      \"endDate\"\n" +
        "    		      ,sua.platform_code \"platformCode\"\n" +
        "    		      ,se.employee_name  \"employeeName\"\n" +
        "    		 FROM saaf_user_access_orgs sua\n" +
        "    		     ,saaf_users            su\n" +
        "    		     ,saaf_employees        se\n" +
        "    		WHERE sua.user_id = su.user_id\n" +
        "    		  AND su.employee_id = se.employee_id";

    public static String QUERY_USER_INST_SQL2 = "SELECT sua.access_org_id \"accessOrgId\"\r\n" +
        "       ,sua.inst_id       \"instId\"\r\n" +
        "       ,sua.user_id       \"userId\"\r\n" +
        "  FROM saaf_user_access_orgs sua\r\n" +
        " WHERE 1 = 1 ";

    //增加查询子节点的组织ID
    public static String QUERY_USER_INST_TREE_SQL = " SELECT sua.access_org_id \"accessOrgId\"\n" +
        "    		      ,sua.inst_id       \"instId\"\n" +
        "    		      ,sua.user_id       \"userId\"\n" +
        "    		      ,si.inst_name      \"instName\"\n" +
        "    		      ,su.user_name      \"userName\"\n" +
        "    		      ,su.user_full_name \"userFullName\"\n" +
        "    		      ,sua.start_date    \"startDate\"\n" +
        "    		      ,sua.end_date      \"endDate\"\n" +
        "    		      ,sua.platform_code \"platformCode\"\n" +
        "    		  FROM saaf_user_access_orgs sua\n" +
        "    		     ,saaf_institution      si\n" +
        "    		     ,saaf_users            su\n" +
        "    		  WHERE sua.user_id = su.user_id\n" +
        "    		  AND sua.inst_id = si.inst_id ";

    //LOV：给用户分配组织{不显示已分配的组织)
    public static String QUERY_ASSIGNED_INST_TO_USER_SQL = " SELECT si.inst_id        \"instId\"\n" +
        "    		      ,si.parent_inst_id \"parentInstId\"\n" +
        "    		      ,si.inst_code      \"instCode\"\n" +
        "    		      ,si.inst_name      \"instName\"\n" +
        "    		 FROM saaf_institution si\n" +
        "    		WHERE (si.start_date IS NULL OR date_format(si.start_date,'%Y-%m-%d') <= CURDATE())\n" +
        "    		  AND (si.end_date IS NULL OR date_format(si.end_date,'%Y-%m-%d') >= CURDATE())\n" +
        "    		  AND si.inst_id NOT IN\n" +
        "    		      (SELECT sua.inst_id instid\n" +
        "    		         FROM saaf_user_access_orgs sua) ";

    //LOV：给组织分配用户
    public static String QUERY_USERS_SQL = " SELECT su.user_id        \"userId\"\n" +
        "    		      ,su.user_name      \"userName\"\n" +
        "    		      ,su.user_full_name \"userFullName\"\n" +
        "    		      ,se.employee_name  \"employeeName\"\n" +
        "    		 FROM saaf_users     su\n" +
        "    		     ,saaf_employees se\n" +
        "    		 WHERE se.employee_id = su.employee_id\n" +
        "    		  AND (su.start_date_active IS NULL OR\n" +
        "    		      date_format(su.start_date_active,'%Y-%m-%d') <= CURDATE())\n" +
        "    		  AND (su.end_date_active IS NULL OR\n" +
        "    		      date_format(su.end_date_active,'%Y-%m-%d') >= CURDATE())\n" +
        "    		  AND su.user_id NOT IN (SELECT sua.user_id\n" +
        "    		                           FROM saaf_user_access_orgs sua\n" +
        "    		                          WHERE sua.inst_id = :instid ) ";

    //查询用户分配的组织区域
    public static String QUERY_USER_INST_REGION_SQL =
                    "SELECT DISTINCT Si.Inst_Region instRegion\n" +
                    "  FROM Saaf_User_Access_Orgs Suao\n" +
                    "      ,Saaf_Institution      Si\n" +
                    " WHERE Suao.Inst_Id = Si.Inst_Id\n" +
                    "   AND Si.Inst_Type = 'ORG'\n" +
                    "   AND Si.Enabled = 'Y'\n" +
                    "   AND Si.Inst_Region IS NOT NULL\n";

    //查询组织区域所有用户
    public static String QUERY_INST_REGION_USER_SQL =
            "SELECT DISTINCT Suao.User_Id userId\n" +
                    "  FROM Saaf_User_Access_Orgs Suao\n" +
                    "      ,Saaf_Institution      Si\n" +
                    " WHERE Suao.Inst_Id = Si.Inst_Id\n" +
                    "   AND Si.Inst_Type = 'ORG'\n" +
                    "   AND Si.Enabled = 'Y'\n" +
                    "   AND Si.Inst_Region IS NOT NULL\n";

    private Integer accessOrgId;
    private Integer parentInstId;
    private Integer instId;
    private String instCode;
    private String instName;
    private Integer userId;
    private String userName;
    private String userFullName;
    private String employeeName;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    private String platformCode;

    private String instRegion;


    public void setAccessOrgId(Integer accessOrgId) {
        this.accessOrgId = accessOrgId;
    }

    public Integer getAccessOrgId() {
        return accessOrgId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
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

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setParentInstId(Integer parentInstId) {
        this.parentInstId = parentInstId;
    }

    public Integer getParentInstId() {
        return parentInstId;
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

    public String getInstRegion() {
        return instRegion;
    }

    public void setInstRegion(String instRegion) {
        this.instRegion = instRegion;
    }
}
