package saaf.common.fmw.rule.model.entities.readonly;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * RuleExpressionEntity_HI_RO Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */

public class RuleExpressionEntity_HI_RO {
    public static final String query =
        "SELECT   re.RULE_EXP_ID AS ruleExpId,   rbl.rule_business_line_code AS ruleBusinessLineCode,   rbl.rule_business_line_name AS ruleBusinessLineName,   rbl.rule_business_line_mappType AS ruleBusinessLineMapptype,   re.RULE_EXP_NAME AS ruleExpName,   re.RULE_EXP_CODE AS ruleExpCode,   re.RULE_EXP_DESC AS ruleExpDesc,   re.RULE_SIMPLE_EXP AS ruleSimpleExp,   re.RULE_EXP_WEIGHT AS ruleExpWeight,   re.EFFECT_DATE AS effectDate,   re.EFFECT_END_DATE AS effectEndDate  FROM rule_expression re LEFT  JOIN rule_business_line rbl ON rbl.rule_business_line_code = re.rule_business_line_code WHERE 1=1";
    private Integer ruleExpId;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private String ruleBusinessLineMapptype;
    private String ruleExpName;
    private String ruleExpCode;
    private String ruleExpDesc;
    private String ruleSimpleExp;
    private Integer ruleExpWeight;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;

    public Integer getRuleExpId() {
        return ruleExpId;
    }

    public void setRuleExpId(Integer ruleExpId) {
        this.ruleExpId = ruleExpId;
    }

    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    public String getRuleBusinessLineName() {
        return ruleBusinessLineName;
    }

    public void setRuleBusinessLineName(String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }

    public String getRuleBusinessLineMapptype() {
        return ruleBusinessLineMapptype;
    }

    public void setRuleBusinessLineMapptype(String ruleBusinessLineMapptype) {
        this.ruleBusinessLineMapptype = ruleBusinessLineMapptype;
    }

    public String getRuleExpName() {
        return ruleExpName;
    }

    public void setRuleExpName(String ruleExpName) {
        this.ruleExpName = ruleExpName;
    }

    public String getRuleExpCode() {
        return ruleExpCode;
    }

    public void setRuleExpCode(String ruleExpCode) {
        this.ruleExpCode = ruleExpCode;
    }

    public String getRuleExpDesc() {
        return ruleExpDesc;
    }

    public void setRuleExpDesc(String ruleExpDesc) {
        this.ruleExpDesc = ruleExpDesc;
    }

    public String getRuleSimpleExp() {
        return ruleSimpleExp;
    }

    public void setRuleSimpleExp(String ruleSimpleExp) {
        this.ruleSimpleExp = ruleSimpleExp;
    }

    public Integer getRuleExpWeight() {
        return ruleExpWeight;
    }

    public void setRuleExpWeight(Integer ruleExpWeight) {
        this.ruleExpWeight = ruleExpWeight;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(Date effectEndDate) {
        this.effectEndDate = effectEndDate;
    }
}

