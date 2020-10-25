package saaf.common.fmw.base.model.entities.readonly;


import java.io.Serializable;


public class SaafUserRespEntity_HI_RO implements Serializable {

    public SaafUserRespEntity_HI_RO() {
        super();
    }
    private Integer employeeId;
    private Integer memberId;
    private String isadmin;
    private String startDateActive;
    private String endDateActive;

//    private static String QUERY_USERS_RESP = "SELECT SUSER.USER_ID \"userId\"\r\n" +
//        "      ,SUSER.EMPLOYEE_ID \"employeeId\"\r\n" +
//        "      ,SUSER.MEMBER_ID \"memberId\"\r\n" +
//        "      ,SUSER.ISADMIN \"isadmin\"\r\n" +
//        "      ,SUSER.USER_NAME \"userName\"\r\n" +
//        "      ,SUSER.USER_FULL_NAME \"userFullName\"\r\n" +
//        "      ,SUSER.PLATFORM_CODE \"platformCode\"\r\n" +
//        "      ,SUSER.START_DATE_ACTIVE \"startDateActive\"\r\n" +
//        "      ,SUSER.END_DATE_ACTIVE \"endDateActive\"\r\n" +
//        "      ,RESP.USER_RESP_ID \"userRespId\"\r\n" +
//        "      ,RESP.USER_RESP_NAME \"userRespName\"\r\n" +
//        "      ,RESP.RESPONSIBILITY_ID \"responsibilityId\"\r\n" +
//        "  FROM SAAF_USERS SUSER\r\n" +
//        "  LEFT JOIN SAAF_USER_RESP RESP\r\n" +
//        "    ON SUSER.USER_ID = RESP.USER_ID\r\n" +
//        " WHERE 1 = 1\r\n";

    public static String QUERY_SQL =
                    "SELECT er.responsibility_name responsibilityName\n" +
                    "      ,er.responsibility_id responsibilityId\n" +
                    "      ,eur.user_resp_id userRespId\n" +
                    "      ,to_char(er.end_date_active, 'yyyy-mm-dd') endDateActive\n" +
                    "      ,to_char(er.start_date_active, 'yyyy-mm-dd') startDateActive\n" +
                    "FROM   saaf_responsibilitys er\n" +
                    "      ,saaf_user_resp       eur\n" +
                    "WHERE  er.responsibility_id = eur.responsibility_id\n" +
                    "AND    trunc(nvl(er.start_date_active, SYSDATE)) <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(er.end_date_active, SYSDATE)) >= trunc(SYSDATE)\n" +
                    "AND    eur.user_id = ?\n";

    public static String QUERY_SQL2 =
                    "SELECT er.responsibility_name responsibilityName\n" +
                    "      ,er.responsibility_id responsibilityId\n" +
                    "      ,eur.user_resp_id userRespId\n" +
                    "      ,to_char(er.end_date_active, 'yyyy-mm-dd') endDateActive\n" +
                    "      ,to_char(er.start_date_active, 'yyyy-mm-dd') startDateActive\n" +
                    "FROM   saaf_responsibilitys er\n" +
                    "      ,saaf_user_resp       eur\n" +
                    "WHERE  er.responsibility_id = eur.responsibility_id\n" +
                    "AND    trunc(nvl(er.start_date_active, SYSDATE)) <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(er.end_date_active, SYSDATE)) >= trunc(SYSDATE)\n" +
                    "AND    eur.user_id = :userId\n" +
                    "AND    eur.platform_code = :platformCode\n";

    public static String QUERY_USERS_SQL =
                    "SELECT sur.user_resp_id  userRespId\n" +
                    "      ,sur.user_id       userId\n" +
                    "      ,su.user_name      userName\n" +
                    "      ,su.user_full_name userFullName\n" +
                    "      ,se.employee_name  employeeName\n" +
                    "FROM   saaf_user_resp sur\n" +
                    "      ,saaf_users     su\n" +
                    "      ,saaf_employees se\n" +
                    "WHERE  sur.user_id = su.user_id\n" +
                    "AND    se.employee_id = su.employee_id\n" +
                    "AND    sur.responsibility_id = :responsibilityId\n";

    public static String QUERY_NOT_EXISTS_USERS_SQL =
                    "SELECT su.user_id        userId\n" +
                    "      ,su.user_name      userName\n" +
                    "      ,su.user_full_name userFullName\n" +
                    "      ,se.employee_name  employeeName\n" +
                    "FROM   saaf_users     su\n" +
                    "      ,saaf_employees se\n" +
                    "WHERE  se.employee_id = su.employee_id\n" +
                    "AND    NOT EXISTS\n" +
                    " (SELECT 1\n" +
                    "        FROM   saaf_user_resp sur\n" +
                    "        WHERE  sur.user_id = su.user_id\n" +
                    "        AND    sur.responsibility_id = :responsibilityId)\n";

    //LOV:查询未分配给用户的所以职责
    public static String QUERY_ASSIGNED_RESP_TO_USER_SQL =
                    "SELECT sr.responsibility_id   responsibilityId\n" +
                    "      ,sr.responsibility_name responsibilityName\n" +
                    "      ,sr.responsibility_key  responsibilityKey\n" +
                    "      ,sr.platform_code       platformCode\n" +
                    "FROM   saaf_responsibilitys sr\n" +
                    "WHERE  trunc(sr.start_date_active) <= trunc(SYSDATE)\n" +
                    "AND    trunc(nvl(sr.end_date_active, SYSDATE)) >= trunc(SYSDATE)\n" +
                    "AND    sr.responsibility_id NOT IN\n" +
                    "       (SELECT sur.responsibility_id\n" +
                    "         FROM   saaf_user_resp sur\n" +
                    "         WHERE  sur.user_id = :userId)\n";

    private String responsibilityName;
    private Integer responsibilityId;
    private Integer userRespId;
    private Integer userId;
    private String userName;
    private String userFullName;
    private String employeeName;
    private String platformCode;
    private String responsibilityKey;

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityId(Integer responsibilityId) {
        this.responsibilityId = responsibilityId;
    }

    public Integer getResponsibilityId() {
        return responsibilityId;
    }

    public static void setQUERY_USERS_SQL(String QUERY_USERS_SQL) {
        SaafUserRespEntity_HI_RO.QUERY_USERS_SQL = QUERY_USERS_SQL;
    }

    public static String getQUERY_USERS_SQL() {
        return QUERY_USERS_SQL;
    }

    public void setUserRespId(Integer userRespId) {
        this.userRespId = userRespId;
    }

    public Integer getUserRespId() {
        return userRespId;
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

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setResponsibilityKey(String responsibilityKey) {
        this.responsibilityKey = responsibilityKey;
    }

    public String getResponsibilityKey() {
        return responsibilityKey;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setStartDateActive(String startDateActive) {
        this.startDateActive = startDateActive;
    }

    public String getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(String endDateActive) {
        this.endDateActive = endDateActive;
    }

    public String getEndDateActive() {
        return endDateActive;
    }
}
