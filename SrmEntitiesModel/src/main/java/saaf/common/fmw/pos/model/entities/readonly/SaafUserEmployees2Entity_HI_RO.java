package saaf.common.fmw.pos.model.entities.readonly;



import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;


public class SaafUserEmployees2Entity_HI_RO implements Serializable {


    public static String QUERY_USERLIST_SQL = "   SELECT su.user_id            \"userId\"\n" +
        "    		      ,su.user_name          \"userName\"\n" +
        "    		      ,su.user_full_name     \"userFullName\"\n" +
        "    		      ,su.platform_code      \"platformCode\"\n" +
        "    		      ,su.encrypted_password \"encryptedPassword\"\n" +
        "    		      ,slv.meaning           \"platformName\"\n" +
        "    		      ,su.isadmin            \"isadmin\"\n" +
        "    		      ,su.start_date_active \"startDateActive\"\n" +
        "    		      ,su.end_date_active \"endDateActive\"\n" +
        "    		      ,se.employee_id        \"employeeId\"\n" +
        "    		      ,se.employee_number    \"employeeNumber\"\n" +
        "    		      ,se.employee_name      \"employeeName\"\n" +
        "    		      ,se.sex                \"sex\"\n" +
        "    		      ,se.birth_day \"birthDay\"\n" +
        "    		      ,se.enabled_flag       \"enabledFlag\"\n" +
        "    		      ,se.position_id        \"positionId\"\n" +
        "    		      ,se.position_name      \"positionName\"\n" +
        "    		      ,se.postcode           \"postcode\"\n" +
        "    		      ,se.card_no            \"cardNo\"\n" +
        "    		      ,se.postal_address     \"postalAddress\"\n" +
        "    		      ,se.email              \"email\"\n" +
        "    		      ,se.mobile_phone       \"mobilePhone\"\n" +
        "    		      ,se.tel_phone          \"telPhone\"\n" +
        "    		      ,si.inst_id            \"instId\"\n" +
        "    		      ,si.inst_code          \"instCode\"\n" +
        "    		      ,si.inst_name          \"instName\"\n" +
        "    		      ,sp.pos_id             \"posId\"\n" +
        "    		      ,sp.pos_number         \"posNumber\"\n" +
        "    		      ,sp.pos_name           \"posName\"\n" +
        "    		 FROM saaf_users su\n" +
        "    		 LEFT JOIN saaf_employees se\n" +
        "    		   ON su.user_id = se.user_id\n" +
        "    		 LEFT JOIN saaf_institution si\n" +
        "    		   ON se.inst_id = si.inst_id\n" +
        "    		 LEFT JOIN saaf_positions sp\n" +
        "    		   ON se.position_id = sp.pos_id, saaf_lookup_values slv\n" +
        "    		WHERE su.platform_code = slv.lookup_code\n" +
        "    		  AND slv.lookup_type = 'PLATFORM_CODE'";

    public static String QUERY_USERS_LOV = " SELECT su.user_id          \"userId\"\r\n" +
        "       ,su.user_full_name   \"userFullName\"\r\n" +
        "       ,su.user_name        \"userName\"\r\n" +
        "       ,emp.employee_name   \"employeeName\"\r\n" +
        "       ,emp.employee_number \"employeeNumber\"\r\n" +
        "  FROM saaf_users     su\r\n" +
        "      ,saaf_employees emp\r\n" +
        " WHERE su.user_id = emp.user_id ";

    public static String QUERY_All_USERS = " SELECT\r\n" +
        "saaf_users.USER_NAME AS \"userName\",\r\n" +
        "saaf_users.PLATFORM_CODE AS \"platformCode\",\r\n" +
        "saaf_users.USER_FULL_NAME AS \"userFullName\",\r\n" +
        "saaf_users.START_DATE_ACTIVE AS \"startDateActive\",\r\n" +
        "saaf_users.END_DATE_ACTIVE AS \"endDateActive\",\r\n" +
        "saaf_users.USER_ID AS \"userId\"\r\n" +
        "FROM\r\n" +
        "saaf_users\r\n" +
        "where 1=1  ";

    private Integer userId;
    private String userName;
    private String userFullName;
    private String platformCode;
    private String platformName;
    private String isadmin;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDateActive;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDateActive;

    private BigInteger employeeId;
    private String employeeNumber;
    private String employeeName;
    private String sex;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date birthDay;
    private String enabledFlag;
    private String postcode;
    private BigInteger positionId;
    private String positionName;
    private String cardNo;
    private String postalAddress;
    private String mobilePhone;
    private String telPhone;
    private String email;

    private String encryptedPassword;

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setStartDateActive(Date startDateActive) {
        this.startDateActive = startDateActive;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getStartDateActive() {
        return startDateActive;
    }

    public void setEndDateActive(Date endDateActive) {
        this.endDateActive = endDateActive;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getEndDateActive() {
        return endDateActive;
    }

    public void setEmployeeId(BigInteger employeeId) {
        this.employeeId = employeeId;
    }

    public BigInteger getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @JSONField(format = "yyyy-MM-dd")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getPostalAddress() {
        return postalAddress;
    }


    public void setPositionId(BigInteger positionId) {
        this.positionId = positionId;
    }

    public BigInteger getPositionId() {
        return positionId;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getTelPhone() {
        return telPhone;
    }
}
