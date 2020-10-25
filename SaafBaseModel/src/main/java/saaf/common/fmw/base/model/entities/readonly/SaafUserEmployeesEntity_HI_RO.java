package saaf.common.fmw.base.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.Date;


public class SaafUserEmployeesEntity_HI_RO implements Serializable {

    public static String QUERY_USERLIST_SQL =
                    "SELECT\n" +
                    "  su.user_id userId,\n" +
                    "  su.user_name userName,\n" +
                    "  su.user_full_name userFullName,\n" +
                    "  su.platform_code platformCode,\n" +
                    "  su.encrypted_password encryptedPassword,\n" +
                    "  slv.meaning platformName,\n" +
                    "  su.isadmin isadmin,\n" +
                    "  su.start_date_active startDateActive,\n" +
                    "  su.end_date_active endDateActive,\n" +
                    "  se.employee_id employeeId,\n" +
                    "  se.employee_number employeeNumber,\n" +
                    "  se.employee_name employeeName,\n" +
                    "  se.sex sex,\n" +
                    "  se.birth_day birthDay,\n" +
                    "  se.enabled_flag enabledFlag,\n" +
                    "  se.position_id positionId,\n" +
                    "  se.position_name positionName,\n" +
                    "  se.postcode postcode,\n" +
                    "  se.card_no cardNo,\n" +
                    "  se.postal_address postalAddress,\n" +
                    "  se.email email,\n" +
                    "  se.mobile_phone mobilePhone,\n" +
                    "  se.tel_phone telPhone,\n" +
                    "  si.inst_id instId,\n" +
                    "  si.inst_code instCode,\n" +
                    "  si.inst_name instName,\n" +
                    "  sp.pos_id posId,\n" +
                    "  sp.pos_number posNumber,\n" +
                    "  sp.pos_name posName,\n" +
                    "  se.quarters_code quartersCode,\n" +
                    "  se.parents_quarters_code parentsQuartersCode,\n" +
                    "  slv1.meaning parentsQuartersName,\n" +
                    "  su.user_file_id userFileId,\n" +
                    "  rf.access_Path userFilePath,\n" +
                    "  rf.file_Name userFileName,\n" +
                    "  (SELECT\n" +
                    "    val.meaning\n" +
                    "  FROM\n" +
                    "    saaf_lookup_values val\n" +
                    "  WHERE val.lookup_code = se.quarters_code\n" +
                    "    AND val.lookup_type = 'EMPLOYEE_POST') quartersName,\n" +
                    "  su.supplier_id supplierId,\n" +
                    "  psi.supplier_number supplierNumber,\n" +
                    "  psi.supplier_name supplierName,\n" +
                    "  su.created_by AS createdBy,\n" +
                    "  su.creation_date AS creationDate,\n" +
                    "  su.last_updated_by AS lastUpdatedBy,\n" +
                    "  su.last_update_date AS lastUpdateDate,\n" +
                    "  su.last_update_login AS lastUpdateLogin\n" +
                    "FROM\n" +
                    "  saaf_users su\n" +
                    "  LEFT JOIN saaf_employees se\n" +
                    "    ON su.employee_id = se.employee_id\n" +
                    "  LEFT JOIN saaf_institution si\n" +
                    "    ON se.inst_id = si.inst_id\n" +
                    "  LEFT JOIN saaf_positions sp\n" +
                    "    ON se.position_id = sp.pos_id\n" +
                    "  LEFT JOIN saaf_base_result_file rf\n" +
                    "    ON su.user_file_id = rf.file_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info psi\n" +
                    "    ON su.supplier_id = psi.supplier_id\n" +
                     "  LEFT JOIN saaf_lookup_values slv1\n" +
                     "    ON se.parents_quarters_code = slv1.lookup_code and slv1.lookup_type = 'EMPLOYEE_POST',\n" +
                    "  saaf_lookup_values slv\n" +
                    "WHERE su.platform_code = slv.lookup_code\n" +
                    "  AND slv.lookup_type = 'PLATFORM_CODE'";

    public static String COUNT_USERLIST_SQL =
                    "SELECT\n" +
                    "  COUNT(1)\n" +
                    "FROM\n" +
                    "  saaf_users su\n" +
                    "  LEFT JOIN saaf_employees se\n" +
                    "    ON su.employee_id = se.employee_id\n" +
                    "  LEFT JOIN saaf_institution si\n" +
                    "    ON se.inst_id = si.inst_id\n" +
                    "  LEFT JOIN saaf_positions sp\n" +
                    "    ON se.position_id = sp.pos_id\n" +
                    "  LEFT JOIN saaf_base_result_file rf\n" +
                    "    ON su.user_file_id = rf.file_id\n" +
                    "  LEFT JOIN srm_pos_supplier_info psi\n" +
                    "    ON su.supplier_id = psi.supplier_id,\n" +
                    "  saaf_lookup_values slv\n" +
                    "WHERE su.platform_code = slv.lookup_code\n" +
                    "  AND slv.lookup_type = 'PLATFORM_CODE'";

    public static String QUERY_USERS_LOV =
                    "SELECT\n" +
                    "  su.user_id userId,\n" +
                    "  su.user_full_name userFullName,\n" +
                    "  su.user_name userName,\n" +
                    "  emp.employee_name employeeName,\n" +
                    "  emp.employee_number employeeNumber,\n" +
                    "  emp.employee_id employeeId\n" +
                    "FROM\n" +
                    "  saaf_users su,\n" +
                    "  saaf_employees emp\n" +
                    "WHERE su.user_id = emp.user_id";

    public static String QUERY_All_USERS =
                    "SELECT\n" +
                    "  user_name AS userName,\n" +
                    "  platform_code AS platformCode,\n" +
                    "  user_full_name AS userFullName,\n" +
                    "  start_date_active AS startDateActive,\n" +
                    "  end_date_active AS endDateActive,\n" +
                    "  user_id AS userId\n" +
                    "FROM\n" +
                    "  saaf_users\n" +
                    "WHERE 1 = 1";

    public static String QUERY_EMP_LOV =
                    "SELECT\n" +
                    "  emp.employee_id employeeId,\n" +
                    "  emp.employee_number employeeNumber,\n" +
                    "  emp.employee_name employeeName,\n" +
                    "  emp.sex sex,\n" +
                    "  su.user_id userId,\n" +
                    "  su.user_name userName,\n" +
                    "  emp.birth_day birthDay,\n" +
                    "  emp.position_id positionId,\n" +
                    "  emp.position_name positionName,\n" +
                    "  (SELECT\n" +
                    "    val.meaning\n" +
                    "  FROM\n" +
                    "    saaf_lookup_values val\n" +
                    "  WHERE val.lookup_code = emp.quarters_code\n" +
                    "    AND val.lookup_type = 'employee_post') quartersName,\n" +
                    "  emp.mobile_phone mobilePhone,\n" +
                    "  emp.tel_phone telPhone,\n" +
                    "  emp.email email,\n" +
                    "  emp.postal_address postalAddress,\n" +
                    "  sp.pos_id posId,\n" +
                    "  sp.pos_number posNumber,\n" +
                    "  sp.pos_name posName\n" +
                    "FROM\n" +
                    "  saaf_employees emp\n" +
                    "  LEFT JOIN saaf_positions sp\n" +
                    "    ON sp.pos_id = emp.position_id,\n" +
                    "  saaf_users su\n" +
                    "WHERE emp.employee_id = su.employee_id";

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
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    private Integer createdBy;
    private Integer lastUpdatedBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    private Integer lastUpdateLogin;

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
    
    private BigInteger supplierId;
    private String supplierNumber;
    private String supplierName;
    private String quartersCode;
    private String quartersName;
    private Integer posId;
    private String posNumber;
    private String posName;
    
    private Integer userFileId;
    private String userFilePath;
    private String userFileName;

    private Integer instId;
    private String instCode;
    private String instName;
    private String parentsQuartersCode;
    private String parentsQuartersName;

    public String getParentsQuartersCode() {
        return parentsQuartersCode;
    }

    public void setParentsQuartersCode(String parentsQuartersCode) {
        this.parentsQuartersCode = parentsQuartersCode;
    }

    public String getParentsQuartersName() {
        return parentsQuartersName;
    }

    public void setParentsQuartersName(String parentsQuartersName) {
        this.parentsQuartersName = parentsQuartersName;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
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

    public BigInteger getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(BigInteger supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

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

    public String getQuartersCode() {
        return quartersCode;
    }

    public void setQuartersCode(String quartersCode) {
        this.quartersCode = quartersCode;
    }

    public String getQuartersName() {
        return quartersName;
    }

    public void setQuartersName(String quartersName) {
        this.quartersName = quartersName;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getPosNumber() {
        return posNumber;
    }

    public void setPosNumber(String posNumber) {
        this.posNumber = posNumber;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

	public Integer getUserFileId() {
		return userFileId;
	}

	public void setUserFileId(Integer userFileId) {
		this.userFileId = userFileId;
	}

	public String getUserFilePath() {
		return userFilePath;
	}

	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}

	public String getUserFileName() {
		return userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }
}
