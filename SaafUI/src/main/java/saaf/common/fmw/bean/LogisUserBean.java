package saaf.common.fmw.bean;

import java.io.Serializable;

public class LogisUserBean implements Serializable {
    public static final String LOGIS_SESSION_BEAN_ATTRIBUTE =
        "_LOGIS_SESSION_BEAN_ATTRIBUTE_";
    public static final int DEFAULT_SPACE_HIGHT = 200;
    public static final int DEFAULT_TABLE_ROW_HIGHT = 22;
    private boolean loginSuccessFlag;
    private int logisticUserId;
    private String logisticCode;
    private String logisticName;
    private String logisticType;
    private String contactsName;
    private String contactsPhon;

    public static String getLOGIS_SESSION_BEAN_ATTRIBUTE() {
        return LOGIS_SESSION_BEAN_ATTRIBUTE;
    }

    public static int getDEFAULT_SPACE_HIGHT() {
        return DEFAULT_SPACE_HIGHT;
    }

    public static int getDEFAULT_TABLE_ROW_HIGHT() {
        return DEFAULT_TABLE_ROW_HIGHT;
    }

    public void setLoginSuccessFlag(boolean loginSuccessFlag) {
        this.loginSuccessFlag = loginSuccessFlag;
    }

    public boolean isLoginSuccessFlag() {
        return loginSuccessFlag;
    }

    public void setLogisticUserId(int logisticUserId) {
        this.logisticUserId = logisticUserId;
    }

    public int getLogisticUserId() {
        return logisticUserId;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticName(String logisticName) {
        this.logisticName = logisticName;
    }

    public String getLogisticName() {
        return logisticName;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsPhon(String contactsPhon) {
        this.contactsPhon = contactsPhon;
    }

    public String getContactsPhon() {
        return contactsPhon;
    }
}
