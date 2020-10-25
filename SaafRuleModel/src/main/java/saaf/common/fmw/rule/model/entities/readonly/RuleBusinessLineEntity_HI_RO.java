package saaf.common.fmw.rule.model.entities.readonly;


import org.springframework.stereotype.Component;


/**
 * RuleBusinessLineEntity_HI_RO Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */
@Component("ruleBusinessLineEntity_HI_RO")
public class RuleBusinessLineEntity_HI_RO {


    public static final String query =
        "SELECT     rbl.rule_business_line_id AS ruleBusinessLineId,    rbl.rule_business_line_code AS ruleBusinessLineCode,    rbl.rule_business_line_name AS ruleBusinessLineName,    rbl.rule_business_line_Desc AS ruleBusinessLineDesc,    rbl.rule_business_line_parent_code AS ruleBusinessLineParentCode,    rbl.rule_business_line_mappType AS ruleBusinessLineMapptype FROM    rule_business_line rbl WHERE 1 = 1";
    private Integer ruleBusinessLineId;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private String ruleBusinessLineDesc;
    private String ruleBusinessLineParentCode;
    private String ruleBusinessLineMapptype;

    public void setRuleBusinessLineId(Integer ruleBusinessLineId) {
        this.ruleBusinessLineId = ruleBusinessLineId;
    }


    public Integer getRuleBusinessLineId() {
        return ruleBusinessLineId;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }


    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleBusinessLineName(String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }


    public String getRuleBusinessLineName() {
        return ruleBusinessLineName;
    }

    public void setRuleBusinessLineDesc(String ruleBusinessLineDesc) {
        this.ruleBusinessLineDesc = ruleBusinessLineDesc;
    }


    public String getRuleBusinessLineDesc() {
        return ruleBusinessLineDesc;
    }

    public void setRuleBusinessLineParentCode(String ruleBusinessLineParentCode) {
        this.ruleBusinessLineParentCode = ruleBusinessLineParentCode;
    }


    public String getRuleBusinessLineParentCode() {
        return ruleBusinessLineParentCode;
    }

    public void setRuleBusinessLineMapptype(String ruleBusinessLineMapptype) {
        this.ruleBusinessLineMapptype = ruleBusinessLineMapptype;
    }


    public String getRuleBusinessLineMapptype() {
        return ruleBusinessLineMapptype;
    }
}

