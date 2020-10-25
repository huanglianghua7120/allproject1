package saaf.common.fmw.base.model.entities.readonly;

/**
 * SaafUserGroupVEntity_HI_RO Entity Object add by luofenwu
 */
public class SaafUserGroupEntity_HI_RO {
    public static String QUERY_SQL = //
        "SELECT sug.user_group_id \"userGroupId\"\r\n" +
        "       ,sug.type_detail   \"typeDetail\"\r\n" +
        "       ,sug.enabled_flag  \"enabledFlag\"\r\n" +
        "       ,sug.group_code    \"groupCode\"\r\n" +
        "       ,sug.group_desc    \"groupDesc\"\r\n" +
        "       ,sug.group_type    \"groupType\"\r\n" +
        "       ,slv1.meaning      \"groupTypeMeaning\"\r\n" +
        "       ,sug.user_type     \"userType\"\r\n" +
        "       ,slv.meaning       \"userTypeMeaning\"\r\n" +
        "       ,sug.group_sql     \"groupSql\"\r\n" +
        "       ,sug.select_result \"selectResult\"\r\n" +
        "  FROM saaf_user_group    sug\r\n" +
        "      ,saaf_lookup_values slv\r\n" +
        "      ,saaf_lookup_values slv1\r\n" +
        " WHERE slv.lookup_type = 'USER_TYPE4GROUP'\r\n" +
        "   AND slv.lookup_code = sug.user_type\r\n" +
        "   AND slv1.lookup_type = 'USERGROUP_TYPE'\r\n" +
        "   AND slv1.lookup_code = sug.group_type";

    public static String QUERY_SQL_ORDERBY = " order by sug.last_update_date desc";

    private Integer userGroupId;
    private String groupCode;
    private String groupDesc;
    private String groupType;
    private String groupTypeMeaning;
    private String typeDetail;
    private String groupSql;
    private String userType;
    private String userTypeMeaning;
    private String enabledFlag;
    private String selectResult;

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    public String getGroupSql() {
        return groupSql;
    }

    public void setGroupSql(String groupSql) {
        this.groupSql = groupSql;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGroupTypeMeaning() {
        return groupTypeMeaning;
    }

    public void setGroupTypeMeaning(String groupTypeMeaning) {
        this.groupTypeMeaning = groupTypeMeaning;
    }

    public String getUserTypeMeaning() {
        return userTypeMeaning;
    }

    public void setUserTypeMeaning(String userTypeMeaning) {
        this.userTypeMeaning = userTypeMeaning;
    }

    public String getSelectResult() {
        return selectResult;
    }

    public void setSelectResult(String selectResult) {
        this.selectResult = selectResult;
    }

    //仅用于 【用户组多选中心】
    public static String MUL_LOV_INST_QUERY_SQL = //
        "select si.inst_id mulId," + //
        "	si.inst_code mulCode," + //
        "	si.inst_name mulName," + //
        " if((select count(1) from saaf_user_group sug " + //
        "    where instr(sug.type_detail,CONCAT(',',si.inst_id,','))>0" + //
        "		and sug.group_code=:groupCode" + //
        "      )>0,'Y','N') checkFlag" + //
        " from saaf_institution si  " + //
        " where sysdate between si.start_date " + //
        " and ifnull(si.end_date, sysdate) ";

    //仅用于 【用户组多选职责】
    public static String MUL_LOV_RESP_QUERY_SQL = //
        "select sr.responsibility_id mulId," + //
        "	slv.meaning mulCode," + //
        "	sr.responsibility_name mulName, " + //
        " if((select count(1) from saaf_user_group sug " + //
        "    where instr(sug.type_detail,CONCAT(',',sr.responsibility_id,','))>0" + //
        "		and sug.group_code=:groupCode" + //
        "      )>0,'Y','N') checkFlag" + //
        " from saaf_responsibilitys sr,saaf_lookup_values slv" + //
        " where slv.lookup_type='RESPONSIBILITY_TYPE'" + //
        " and sr.platform_code=slv.lookup_code" + //
        " and sysdate between sr.start_date_active " + //
        " and ifnull(sr.end_date_active, sysdate) ";

    //仅用于 【用户组多选代理商/分销商/直营商/卖场】
    public static String MUL_LOV_CUSTOMER_QUERY_SQL = //
        "select ci.customer_id as mulId ,  " + //
        "		ci.customer_code as mulCode,  " + //
        "		ci.customer_name  as mulName, " + //
        " if((select count(1) from saaf_user_group sug " + //
        "    where instr(sug.type_detail,CONCAT(',',ci.customer_id,','))>0" + //
        "		and sug.group_code=:groupCode" + //
        "      )>0,'Y','N') checkFlag" + //
        " from customer_info ci " + //
        " where ci.enabled_flag ='Y'" + //
        " and ci.customer_type=:customerType ";


    //仅用于 【用户组多选用户】
    public static String MUL_LOV_USER_QUERY_SQL = //
        "select mt.mulId,mt.mulCode,mt.mulName,mt.custType,mt.checkFlag from (" + //
        " select su.user_id   mulId," + //
        "		su.user_name  mulCode," + //
        " ifnull(ci.customer_name,nvl((select sem.EMPLOYEE_NAME from saaf_employees sem where sem.employee_id=su.employee_id),su.user_full_name)) mulName," +
        " ifnull(slv.meaning,'中心用户') custType," + //
        " if((select count(1) from saaf_user_group sug " + //
        "    where instr(sug.type_detail,CONCAT(',',su.user_id,','))>0" + //
        "		and sug.group_code=:groupCode" + //
        "      )>0,'Y','N') checkFlag" + //
        " from saaf_users su left join customer_info ci on su.member_id=ci.customer_id " + " left join saaf_lookup_values slv " + " on slv.lookup_code= ci.customer_type " +
        " and slv.lookup_type='CUSTOMER_TYPE' " + " where sysdate BETWEEN su.start_date_active and ifnull(su.end_date_active,sysdate)" + ") mt where 1=1 ";
    private Integer mulId;
    private String mulCode;
    private String mulName;
    private String custType;

    public Integer getMulId() {
        return mulId;
    }

    public void setMulId(Integer mulId) {
        this.mulId = mulId;
    }

    public String getMulCode() {
        return mulCode;
    }

    public void setMulCode(String mulCode) {
        this.mulCode = mulCode;
    }

    public String getMulName() {
        return mulName;
    }

    public void setMulName(String mulName) {
        this.mulName = mulName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }
}
