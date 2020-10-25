package saaf.common.fmw.bean;

import java.io.Serializable;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserInfoSessionBean implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoSessionBean.class);
    public static final String SAAF_SESSION_BEAN_ATTRIBUTE = "_SAAF_SESSION_BEAN_ATTRIBUTE_";
    public static final int DEFAULT_SPACE_HIGHT = 200;
    public static final int DEFAULT_TABLE_ROW_HIGHT = 22;
    private boolean loginSuccessFlag;
    private Integer userId;
    private String userName;
    private String userFullName;
    private String employeeName;
    private String loginId;
    private Integer employeeId;
    private String sessionId;
    private String responsibilitiesId;
    private String emailAddress;
    private String language;
    private String userIpAddress;
    private int screenWidth;
    private int screenHeight;
    private int tableDefaultRows;
    private int tablesDefaultRows;
    private int topSpageHeight;
    private boolean executePageLoadFlag = false;
    private List userRespList;
    private String platformCode;
    private String instIdData;
    private String isAdmin;
    private String tokenCode;
    private Integer memberId;
    private Integer supplierId;
    private String employeeNumber;//员工编号

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFullName() {
        return this.userFullName;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginId() {
        return this.loginId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeId() {
        return this.employeeId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return this.sessionId;
    }


    public void setResponsibilitiesId(String responsibilitiesid) {
        this.responsibilitiesId = responsibilitiesid;
    }

    public String getResponsibilitiesId() {
        return this.responsibilitiesId;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setUserIpAddress(String userIpAddress) {
        this.userIpAddress = userIpAddress;
    }

    public String getUserIpAddress() {
        return this.userIpAddress;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void setExecutePageLoadFlag(boolean executePageLoadFlag) {
        this.executePageLoadFlag = executePageLoadFlag;
    }

    public boolean isExecutePageLoadFlag() {
        return this.executePageLoadFlag;
    }

    public void setTableDefaultRows(int tableDefaultRows) {
        this.tableDefaultRows = tableDefaultRows;
    }

    public int getTableDefaultRows() {
        return this.tableDefaultRows;
    }

    public void setTablesDefaultRows(int tablesDefaultRows) {
        this.tablesDefaultRows = tablesDefaultRows;
    }

    public int getTablesDefaultRows() {
        return this.tablesDefaultRows;
    }

    public void setTopSpageHeight(int topSpageHeight) {
        this.topSpageHeight = topSpageHeight;
    }

    public int getTopSpageHeight() {
        return this.topSpageHeight;
    }

    public void setLoginSuccessFlag(boolean loginSuccessFlag) {
        this.loginSuccessFlag = loginSuccessFlag;
    }

    public boolean isLoginSuccessFlag() {
        return this.loginSuccessFlag;
    }


    public void setUserRespList(List userRespList) {
        this.userRespList = userRespList;
    }

    public List getUserRespList() {
        return userRespList;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setInstIdData(String instIdData) {
        this.instIdData = instIdData;
    }

    public String getInstIdData() {
        return instIdData;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
