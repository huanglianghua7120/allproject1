package saaf.common.fmw.rule.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * RuleExpressiondimEntity_HI Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "rule_expressiondim")
public class RuleExpressiondimEntity_HI {
    private Integer ruleExpDimId;
    private String ruleBusinessLineCode;
    private String ruleOrderId;
    private String ruleDimCode;
    private String ruleDimName;
    private String ruleDimOperators;
    private String ruleDimValue;
    private String ruleExpCode;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;

    public void setRuleExpDimId(Integer ruleExpDimId) {
        this.ruleExpDimId = ruleExpDimId;
    }

    @Id
    @GeneratedValue
    @Column(name = "rule_exp_dim_id", nullable = false, length = 10)
    public Integer getRuleExpDimId() {
        return ruleExpDimId;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    @Column(name = "rule_business_line_code", nullable = true, length = 100)
    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleDimCode(String ruleDimCode) {
        this.ruleDimCode = ruleDimCode;
    }

    @Column(name = "rule_order_Id", nullable = true, length = 20)
    public String getRuleOrderId() {
        return ruleOrderId;
    }

    public void setRuleOrderId(String ruleOrderId) {
        this.ruleOrderId = ruleOrderId;
    }

    @Column(name = "rule_dim_code", nullable = true, length = 150)
    public String getRuleDimCode() {
        return ruleDimCode;
    }

    public void setRuleDimName(String ruleDimName) {
        this.ruleDimName = ruleDimName;
    }

    @Column(name = "rule_dim_name", nullable = true, length = 150)
    public String getRuleDimName() {
        return ruleDimName;
    }

    public void setRuleDimOperators(String ruleDimOperators) {
        this.ruleDimOperators = ruleDimOperators;
    }

    @Column(name = "rule_dim_operators", nullable = true, length = 15)
    public String getRuleDimOperators() {
        return ruleDimOperators;
    }

    public void setRuleDimValue(String ruleDimValue) {
        this.ruleDimValue = ruleDimValue;
    }

    @Column(name = "rule_dim_value", nullable = true, length = 200)
    public String getRuleDimValue() {
        return ruleDimValue;
    }

    public void setRuleExpCode(String ruleExpCode) {
        this.ruleExpCode = ruleExpCode;
    }

    @Column(name = "rule_exp_code", nullable = true, length = 150)
    public String getRuleExpCode() {
        return ruleExpCode;
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
}

