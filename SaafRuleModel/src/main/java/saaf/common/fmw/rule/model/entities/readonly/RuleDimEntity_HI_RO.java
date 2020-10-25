package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;


/**
 * Created by Administrator on Thu Aug 17 11:46:29 CST 2017
 */
public class RuleDimEntity_HI_RO{


    public static final String query="SELECT   rd.placeholder   AS placeholder,rd.RULE_DIM_ID AS ruleDimId,   rd.rule_business_line_code AS ruleBusinessLineCode,   rbl.rule_business_line_name AS ruleBusinessLineName,   rbl.rule_business_line_Desc AS ruleBusinessLineDesc,   rbl.rule_business_line_type AS ruleBusinessLineType,   slv3.meaning AS ruleBusinessLineTypeMeaning,   rd.RULE_view_TYPE AS ruleViewType,   rd.RULE_DIM_NAME AS ruleDimName,   rd.RULE_DIM_DATA_TYPE AS ruleDimDataType,   rd.RULE_DIM_default_VALUE AS ruleDimDefaultValue,   rd.RULE_DIM_CODE AS ruleDimCode,   rd.RULE_DIM_DESC AS ruleDimDesc,   rd.rule_dim_target_source AS ruleDimTargetSource,   rd.rule_dim_reference_code AS ruleDimReferenceCode,   slv.MEANING AS ruleDimReferenceFromMeaning,   rd.rule_dim_reference_from AS ruleDimReferenceFrom,   slv2.MEANING AS ruleDimReferenceCodeMeaning,   rd.rule_DIM_value_from AS ruleDimValueFrom,   slv4.MEANING AS ruleDimValueFromMeaning,   rd.rule_dim_target_source AS ruleDimTargetSource,   rd.EFFECT_DATE AS effectDate,   rd.EFFECT_END_DATE AS effectEndDate  FROM rule_dim rd LEFT JOIN rule_business_line rbl ON rbl.rule_business_line_code = rd.rule_business_line_code   LEFT JOIN saaf_lookup_values slv     ON slv.LOOKUP_TYPE = 'DIM_VALUE_SOURCES' AND slv.LOOKUP_CODE = rd.rule_dim_reference_from   LEFT JOIN saaf_lookup_values slv2 ON slv2.LOOKUP_TYPE = 'DIM_VALUE' AND slv2.LOOKUP_CODE = rd.rule_dim_reference_code   LEFT JOIN saaf_lookup_values slv3     ON slv3.LOOKUP_TYPE = 'BUSINESS_TYPE' AND slv3.LOOKUP_CODE = rbl.rule_business_line_type   LEFT JOIN saaf_lookup_values slv4 ON slv4 .LOOKUP_TYPE='' AND slv4.LOOKUP_CODE=rd.rule_DIM_value_from WHERE 1=1";
    private Integer ruleDimId;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private String ruleBusinessLineDesc;
    private String ruleBusinessLineType;
    private String ruleBusinessLineTypeMeaning;
    private String ruleViewType;
    private String ruleDimName;
    private String ruleDimDataType;
    private String ruleDimDefaultValue;
    private String ruleDimCode;
    private String ruleDimDesc;
    private String ruleDimTargetSource;
    private String ruleDimReferenceCode;
    private String ruleDimReferenceFromMeaning;
    private String ruleDimReferenceFrom;
    private String ruleDimReferenceCodeMeaning;
    private String ruleDimValueFrom;
    private String ruleDimValueFromMeaning;
    private String rdRuleDimTargetSource;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;
    private String placeholder;

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public java.lang.Integer getRuleDimId() {
        return this.ruleDimId;
    }

    public void setRuleDimId(java.lang.Integer ruleDimId) {
        this.ruleDimId=ruleDimId;
    }

    public java.lang.String getRuleBusinessLineCode() {
        return this.ruleBusinessLineCode;
    }

    public void setRuleBusinessLineCode(java.lang.String ruleBusinessLineCode) {
        this.ruleBusinessLineCode=ruleBusinessLineCode;
    }

    public java.lang.String getRuleBusinessLineName() {
        return this.ruleBusinessLineName;
    }

    public void setRuleBusinessLineName(java.lang.String ruleBusinessLineName) {
        this.ruleBusinessLineName=ruleBusinessLineName;
    }

    public java.lang.String getRuleBusinessLineDesc() {
        return this.ruleBusinessLineDesc;
    }

    public void setRuleBusinessLineDesc(java.lang.String ruleBusinessLineDesc) {
        this.ruleBusinessLineDesc=ruleBusinessLineDesc;
    }

    public java.lang.String getRuleBusinessLineType() {
        return this.ruleBusinessLineType;
    }

    public void setRuleBusinessLineType(java.lang.String ruleBusinessLineType) {
        this.ruleBusinessLineType=ruleBusinessLineType;
    }

    public java.lang.String getRuleBusinessLineTypeMeaning() {
        return this.ruleBusinessLineTypeMeaning;
    }

    public void setRuleBusinessLineTypeMeaning(java.lang.String ruleBusinessLineTypeMeaning) {
        this.ruleBusinessLineTypeMeaning=ruleBusinessLineTypeMeaning;
    }

    public java.lang.String getRuleViewType() {
        return this.ruleViewType;
    }

    public void setRuleViewType(java.lang.String ruleViewType) {
        this.ruleViewType=ruleViewType;
    }

    public java.lang.String getRuleDimName() {
        return this.ruleDimName;
    }

    public void setRuleDimName(java.lang.String ruleDimName) {
        this.ruleDimName=ruleDimName;
    }

    public java.lang.String getRuleDimDataType() {
        return this.ruleDimDataType;
    }

    public void setRuleDimDataType(java.lang.String ruleDimDataType) {
        this.ruleDimDataType=ruleDimDataType;
    }

    public java.lang.String getRuleDimDefaultValue() {
        return this.ruleDimDefaultValue;
    }

    public void setRuleDimDefaultValue(java.lang.String ruleDimDefaultValue) {
        this.ruleDimDefaultValue=ruleDimDefaultValue;
    }

    public java.lang.String getRuleDimCode() {
        return this.ruleDimCode;
    }

    public void setRuleDimCode(java.lang.String ruleDimCode) {
        this.ruleDimCode=ruleDimCode;
    }

    public java.lang.String getRuleDimDesc() {
        return this.ruleDimDesc;
    }

    public void setRuleDimDesc(java.lang.String ruleDimDesc) {
        this.ruleDimDesc=ruleDimDesc;
    }

    public java.lang.String getRuleDimTargetSource() {
        return this.ruleDimTargetSource;
    }

    public void setRuleDimTargetSource(java.lang.String ruleDimTargetSource) {
        this.ruleDimTargetSource=ruleDimTargetSource;
    }

    public java.lang.String getRuleDimReferenceCode() {
        return this.ruleDimReferenceCode;
    }

    public void setRuleDimReferenceCode(java.lang.String ruleDimReferenceCode) {
        this.ruleDimReferenceCode=ruleDimReferenceCode;
    }

    public java.lang.String getRuleDimReferenceFromMeaning() {
        return this.ruleDimReferenceFromMeaning;
    }

    public void setRuleDimReferenceFromMeaning(java.lang.String ruleDimReferenceFromMeaning) {
        this.ruleDimReferenceFromMeaning=ruleDimReferenceFromMeaning;
    }

    public java.lang.String getRuleDimReferenceFrom() {
        return this.ruleDimReferenceFrom;
    }

    public void setRuleDimReferenceFrom(java.lang.String ruleDimReferenceFrom) {
        this.ruleDimReferenceFrom=ruleDimReferenceFrom;
    }

    public java.lang.String getRuleDimReferenceCodeMeaning() {
        return this.ruleDimReferenceCodeMeaning;
    }

    public void setRuleDimReferenceCodeMeaning(java.lang.String ruleDimReferenceCodeMeaning) {
        this.ruleDimReferenceCodeMeaning=ruleDimReferenceCodeMeaning;
    }

    public java.lang.String getRuleDimValueFrom() {
        return this.ruleDimValueFrom;
    }

    public void setRuleDimValueFrom(java.lang.String ruleDimValueFrom) {
        this.ruleDimValueFrom=ruleDimValueFrom;
    }

    public java.lang.String getRuleDimValueFromMeaning() {
        return this.ruleDimValueFromMeaning;
    }

    public void setRuleDimValueFromMeaning(java.lang.String ruleDimValueFromMeaning) {
        this.ruleDimValueFromMeaning=ruleDimValueFromMeaning;
    }

    public java.lang.String getRdRuleDimTargetSource() {
        return this.rdRuleDimTargetSource;
    }

    public void setRdRuleDimTargetSource(java.lang.String rdRuleDimTargetSource) {
        this.rdRuleDimTargetSource=rdRuleDimTargetSource;
    }

    public java.util.Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(java.util.Date effectDate) {
        this.effectDate=effectDate;
    }

    public java.util.Date getEffectEndDate() {
        return this.effectEndDate;
    }

    public void setEffectEndDate(java.util.Date effectEndDate) {
        this.effectEndDate=effectEndDate;
    }
}