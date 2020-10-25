package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * RuleMappingBusinessEntity_HI_RO Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */

public class RuleMappingBusinessEntity_HI_RO {
    public static final String query =
        "SELECT   rmb.RULE_MAPP_BUS_ID AS ruleMappBusId,   rmb.RULE_BUS_dim AS ruleBusDim,   rd.RULE_DIM_NAME AS ruleDimName,   rd.RULE_DIM_DATA_TYPE AS ruleDimDataType,   rmb.rule_bus_dim_operator AS ruleBusDimOperator,   rmb.rule_bus_dim_value AS ruleBusDimValue,   rmb.rule_bus_target_type AS ruleBusTargetType,   rmb.rule_bus_target_source AS ruleBusTargetSource,   rmb.rule_bus_result_dim AS ruleBusResultDim,   rmb.rule_bus_param AS ruleBusParam,   rmb.rule_exc_code AS ruleExcCode,   re.rule_business_line_code AS ruleBusinessLineCode,   rbl.rule_business_line_name AS ruleBusinessLineName,   re.RULE_EXP_NAME AS ruleExpName,   rmb.rule_bus_level AS ruleBusLevel,   rmb.EFFECT_DATE AS effectDate,   rmb.EFFECT_END_DATE AS effectEndDate  FROM rule_mapping_business rmb LEFT JOIN rule_expression re ON re.RULE_EXP_CODE = rmb.rule_exc_code   LEFT JOIN rule_business_line rbl ON rbl.rule_business_line_code = re.rule_business_line_code   LEFT JOIN rule_dim rd ON rmb.RULE_BUS_dim =rd.RULE_DIM_CODE AND rd.rule_business_line_code=re.rule_business_line_code WHERE 1=1";
    private Integer ruleMappBusId;
    private String ruleBusDim;
    private String ruleDimName;
    private String ruleDimDataType;
    private String ruleBusDimOperator;
    private String ruleBusDimValue;
    private String ruleBusTargetType;
    private String ruleBusTargetSource;
    private String ruleBusResultDim;
    private String ruleBusParam;
    private String ruleExcCode;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private String ruleExpName;
    private Integer ruleBusLevel;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;

    public java.lang.Integer getRuleMappBusId() {
        return this.ruleMappBusId;
    }

    public void setRuleMappBusId(java.lang.Integer ruleMappBusId) {
        this.ruleMappBusId = ruleMappBusId;
    }

    public java.lang.String getRuleBusDim() {
        return this.ruleBusDim;
    }

    public void setRuleBusDim(java.lang.String ruleBusDim) {
        this.ruleBusDim = ruleBusDim;
    }

    public java.lang.String getRuleDimName() {
        return this.ruleDimName;
    }

    public void setRuleDimName(java.lang.String ruleDimName) {
        this.ruleDimName = ruleDimName;
    }

    public java.lang.String getRuleDimDataType() {
        return this.ruleDimDataType;
    }

    public void setRuleDimDataType(java.lang.String ruleDimDataType) {
        this.ruleDimDataType = ruleDimDataType;
    }

    public java.lang.String getRuleBusDimOperator() {
        return this.ruleBusDimOperator;
    }

    public void setRuleBusDimOperator(java.lang.String ruleBusDimOperator) {
        this.ruleBusDimOperator = ruleBusDimOperator;
    }

    public java.lang.String getRuleBusDimValue() {
        return this.ruleBusDimValue;
    }

    public void setRuleBusDimValue(java.lang.String ruleBusDimValue) {
        this.ruleBusDimValue = ruleBusDimValue;
    }

    public java.lang.String getRuleBusTargetType() {
        return this.ruleBusTargetType;
    }

    public void setRuleBusTargetType(java.lang.String ruleBusTargetType) {
        this.ruleBusTargetType = ruleBusTargetType;
    }

    public java.lang.String getRuleBusTargetSource() {
        return this.ruleBusTargetSource;
    }

    public void setRuleBusTargetSource(java.lang.String ruleBusTargetSource) {
        this.ruleBusTargetSource = ruleBusTargetSource;
    }

    public java.lang.String getRuleBusResultDim() {
        return this.ruleBusResultDim;
    }

    public void setRuleBusResultDim(java.lang.String ruleBusResultDim) {
        this.ruleBusResultDim = ruleBusResultDim;
    }

    public java.lang.String getRuleBusParam() {
        return this.ruleBusParam;
    }

    public void setRuleBusParam(java.lang.String ruleBusParam) {
        this.ruleBusParam = ruleBusParam;
    }

    public java.lang.String getRuleExcCode() {
        return this.ruleExcCode;
    }

    public void setRuleExcCode(java.lang.String ruleExcCode) {
        this.ruleExcCode = ruleExcCode;
    }

    public java.lang.String getRuleBusinessLineCode() {
        return this.ruleBusinessLineCode;
    }

    public void setRuleBusinessLineCode(java.lang.String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    public java.lang.String getRuleBusinessLineName() {
        return this.ruleBusinessLineName;
    }

    public void setRuleBusinessLineName(java.lang.String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }

    public java.lang.String getRuleExpName() {
        return this.ruleExpName;
    }

    public void setRuleExpName(java.lang.String ruleExpName) {
        this.ruleExpName = ruleExpName;
    }

    public java.lang.Integer getRuleBusLevel() {
        return this.ruleBusLevel;
    }

    public void setRuleBusLevel(java.lang.Integer ruleBusLevel) {
        this.ruleBusLevel = ruleBusLevel;
    }

    public java.util.Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(java.util.Date effectDate) {
        this.effectDate = effectDate;
    }

    public java.util.Date getEffectEndDate() {
        return this.effectEndDate;
    }

    public void setEffectEndDate(java.util.Date effectEndDate) {
        this.effectEndDate = effectEndDate;
    }
}

