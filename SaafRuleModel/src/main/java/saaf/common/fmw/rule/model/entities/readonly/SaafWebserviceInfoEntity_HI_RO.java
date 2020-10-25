package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * Created by Administrator on Thu Jul 06 17:08:13 CST 2017
 */
public class SaafWebserviceInfoEntity_HI_RO {


    public static final String query =
        "SELECT   swi.webservice_id AS webserviceId,   swi.business_line_code AS businessLineCode,   swi.webservice_code AS webserviceCode,   rbl.rule_business_line_name AS ruleBusinessLineName,   swi.webservice_url AS webserviceUrl,   swi.webservice_name AS webserviceName,   swi.webservice_desc AS webserviceDesc,   swi.webserice_agreement AS websericeAgreement,   swi.webservice_type AS webserviceType,   swi.request_param_demo AS requestParamDemo,   swi.response_param_demo AS responseParamDemo,   swi.version_num AS versionNum,   swi.CREATION_DATE AS creationDate  FROM saaf_webservice_info swi LEFT JOIN rule_business_line rbl ON rbl.rule_business_line_code=swi.business_line_code WHERE 1=1";
    private Integer webserviceId;
    private String businessLineCode;
    private String webserviceCode;
    private String ruleBusinessLineName;
    private String webserviceUrl;
    private String webserviceName;
    private String webserviceDesc;
    private String websericeAgreement;
    private String webserviceType;
    private String requestParamDemo;
    private String responseParamDemo;
    private Integer versionNum;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    public java.lang.Integer getWebserviceId() {
        return this.webserviceId;
    }

    public void setWebserviceId(java.lang.Integer webserviceId) {
        this.webserviceId = webserviceId;
    }

    public java.lang.String getBusinessLineCode() {
        return this.businessLineCode;
    }

    public void setBusinessLineCode(java.lang.String businessLineCode) {
        this.businessLineCode = businessLineCode;
    }

    public java.lang.String getWebserviceCode() {
        return this.webserviceCode;
    }

    public void setWebserviceCode(java.lang.String webserviceCode) {
        this.webserviceCode = webserviceCode;
    }

    public java.lang.String getRuleBusinessLineName() {
        return this.ruleBusinessLineName;
    }

    public void setRuleBusinessLineName(java.lang.String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }

    public java.lang.String getWebserviceUrl() {
        return this.webserviceUrl;
    }

    public void setWebserviceUrl(java.lang.String webserviceUrl) {
        this.webserviceUrl = webserviceUrl;
    }

    public java.lang.String getWebserviceName() {
        return this.webserviceName;
    }

    public void setWebserviceName(java.lang.String webserviceName) {
        this.webserviceName = webserviceName;
    }

    public java.lang.String getWebserviceDesc() {
        return this.webserviceDesc;
    }

    public void setWebserviceDesc(java.lang.String webserviceDesc) {
        this.webserviceDesc = webserviceDesc;
    }

    public java.lang.String getWebsericeAgreement() {
        return this.websericeAgreement;
    }

    public void setWebsericeAgreement(java.lang.String websericeAgreement) {
        this.websericeAgreement = websericeAgreement;
    }

    public java.lang.String getWebserviceType() {
        return this.webserviceType;
    }

    public void setWebserviceType(java.lang.String webserviceType) {
        this.webserviceType = webserviceType;
    }

    public java.lang.String getRequestParamDemo() {
        return this.requestParamDemo;
    }

    public void setRequestParamDemo(java.lang.String requestParamDemo) {
        this.requestParamDemo = requestParamDemo;
    }

    public java.lang.String getResponseParamDemo() {
        return this.responseParamDemo;
    }

    public void setResponseParamDemo(java.lang.String responseParamDemo) {
        this.responseParamDemo = responseParamDemo;
    }

    public java.lang.Integer getVersionNum() {
        return this.versionNum;
    }

    public void setVersionNum(java.lang.Integer versionNum) {
        this.versionNum = versionNum;
    }

    public java.util.Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
}
