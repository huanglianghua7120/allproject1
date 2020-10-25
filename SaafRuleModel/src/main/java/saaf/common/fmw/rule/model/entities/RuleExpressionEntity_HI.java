package saaf.common.fmw.rule.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * RuleExpressionEntity_HI Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "rule_expression")
public class RuleExpressionEntity_HI {
    private Integer ruleExpId;
    private String ruleBusinessLineCode;
    private String ruleExpName;
    private String ruleExpCode;
    private String ruleExpDesc;
    private String ruleSimpleExp;
    private Integer ruleExpWeight;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;

    public void setRuleExpId(Integer ruleExpId) {
        this.ruleExpId = ruleExpId;
    }

    @Id
    @GeneratedValue
    @Column(name = "rule_exp_id", nullable = false, length = 10)
    public Integer getRuleExpId() {
        return ruleExpId;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    @Column(name = "rule_business_line_code", nullable = true, length = 100)
    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleExpCode(String ruleExpCode) {
        this.ruleExpCode = ruleExpCode;
    }

    @Column(name = "rule_exp_code", nullable = true, length = 150)
    public String getRuleExpCode() {
        return ruleExpCode;
    }

    public void setRuleExpDesc(String ruleExpDesc) {
        this.ruleExpDesc = ruleExpDesc;
    }

    @Column(name = "rule_exp_desc", nullable = true, length = 500)
    public String getRuleExpDesc() {
        return ruleExpDesc;
    }

    public void setRuleSimpleExp(String ruleSimpleExp) {
        this.ruleSimpleExp = ruleSimpleExp;
    }

    @Column(name = "rule_simple_exp", nullable = true, length = 3900)
    public String getRuleSimpleExp() {
        return ruleSimpleExp;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Column(name = "effect_date", nullable = true)
    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectEndDate(Date effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    @Column(name = "effect_end_date", nullable = true)
    public Date getEffectEndDate() {
        return effectEndDate;
    }

    public void setRuleExpName(String ruleExpName) {
        this.ruleExpName = ruleExpName;
    }

    @Column(name = "rule_exp_name", nullable = true, length = 500)
    public String getRuleExpName() {
        return ruleExpName;
    }

    public void setRuleExpWeight(Integer ruleExpWeight) {
        this.ruleExpWeight = ruleExpWeight;
    }

    @Column(name = "rule_exp_weight", nullable = true, length = 500)
    public Integer getRuleExpWeight() {
        return ruleExpWeight;
    }
}

