package saaf.common.fmw.rule.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RuleBusinessLineEntity_HI Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "rule_business_line")
public class RuleBusinessLineEntity_HI {
    private Integer ruleBusinessLineId;
    private String ruleBusinessLineCode;
    private String ruleBusinessLineName;
    private String ruleBusinessLineDesc;
    private String ruleBusinessLineParentCode;
    private String ruleBusinessLineMapptype;

    public void setRuleBusinessLineId(Integer ruleBusinessLineId) {
        this.ruleBusinessLineId = ruleBusinessLineId;
    }

    @Id
    @GeneratedValue
    @Column(name = "rule_business_line_id", nullable = false, length = 11)
    public Integer getRuleBusinessLineId() {
        return ruleBusinessLineId;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    @Column(name = "rule_business_line_code", nullable = true, length = 100)
    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleBusinessLineName(String ruleBusinessLineName) {
        this.ruleBusinessLineName = ruleBusinessLineName;
    }

    @Column(name = "rule_business_line_name", nullable = true, length = 500)
    public String getRuleBusinessLineName() {
        return ruleBusinessLineName;
    }

    public void setRuleBusinessLineDesc(String ruleBusinessLineDesc) {
        this.ruleBusinessLineDesc = ruleBusinessLineDesc;
    }

    @Column(name = "rule_business_line_desc", nullable = true, length = 500)
    public String getRuleBusinessLineDesc() {
        return ruleBusinessLineDesc;
    }

    public void setRuleBusinessLineParentCode(String ruleBusinessLineParentCode) {
        this.ruleBusinessLineParentCode = ruleBusinessLineParentCode;
    }

    @Column(name = "rule_business_line_parent_code", nullable = true, length = 100)
    public String getRuleBusinessLineParentCode() {
        return ruleBusinessLineParentCode;
    }

    public void setRuleBusinessLineMapptype(String ruleBusinessLineMapptype) {
        this.ruleBusinessLineMapptype = ruleBusinessLineMapptype;
    }

    @Column(name = "rule_business_line_mapptype", nullable = true, length = 100)
    public String getRuleBusinessLineMapptype() {
        return ruleBusinessLineMapptype;
    }
}

