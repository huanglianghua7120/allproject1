package saaf.common.fmw.rule.model.entities;

import com.alibaba.fastjson.annotation.JSONField;
import saaf.common.fmw.rule.model.beans.OperatorBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * RuleDimEntity_HI Entity Object
 * Thu Jul 06 10:50:38 CST 2017  Auto Generate
 */
@Entity
@Table(name = "rule_dim")
public class RuleDimEntity_HI {
    private Integer ruleDimId;
    private String ruleBusinessLineCode;
    private String ruleViewType;
    private String ruleDimName;
    private String ruleDimDataType;
    private String ruleDimDefaultValue;
    private String ruleDimCode;
    private String ruleDimDesc;
    private String ruleDimValueFrom;
    private String ruleDimTargetSource;
    private List<OperatorBean> operatorBeans = operatorBeans_();
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;
    private String ruleDimReferenceFrom;
    private String ruleDimReferenceCode;
    private String placeholder;

    private static List<OperatorBean> operatorBeans_ (){
        List<OperatorBean> operatorBeans = new ArrayList<OperatorBean>();
        OperatorBean in = new OperatorBean("in", "包含");
        OperatorBean notIn = new OperatorBean("not in", "不包含");
        OperatorBean eqal = new OperatorBean("=", "等于");
        OperatorBean notEqal = new OperatorBean("<>", "不等于");
        OperatorBean betweenAnd = new OperatorBean("between##and", "在两者之间");
        OperatorBean gt = new OperatorBean(">", "大于");
        OperatorBean notLt = new OperatorBean(">=", "大于等于");
        OperatorBean lt = new OperatorBean("<", "小于");
        OperatorBean notGt = new OperatorBean("<=", "小于等于");
        operatorBeans.add(in);
        operatorBeans.add(notIn);
        operatorBeans.add(eqal);
        operatorBeans.add(notEqal);
        operatorBeans.add(betweenAnd);
        operatorBeans.add(gt);
        operatorBeans.add(notLt);
        operatorBeans.add(lt);
        operatorBeans.add(notGt);
        return operatorBeans;
    }

    public void setRuleDimId(Integer ruleDimId) {
        this.ruleDimId = ruleDimId;
    }

    @Id
    @GeneratedValue
    @Column(name = "rule_dim_id", nullable = false, length = 10)
    public Integer getRuleDimId() {
        return ruleDimId;
    }

    public void setRuleBusinessLineCode(String ruleBusinessLineCode) {
        this.ruleBusinessLineCode = ruleBusinessLineCode;
    }

    @Column(name = "rule_business_line_code", nullable = true, length = 100)
    public String getRuleBusinessLineCode() {
        return ruleBusinessLineCode;
    }

    public void setRuleViewType(String ruleViewType) {
        this.ruleViewType = ruleViewType;
    }

    @Column(name = "rule_view_type", nullable = true, length = 30)
    public String getRuleViewType() {
        return ruleViewType;
    }

    public void setRuleDimName(String ruleDimName) {
        this.ruleDimName = ruleDimName;
    }

    @Column(name = "rule_dim_name", nullable = true, length = 150)
    public String getRuleDimName() {
        return ruleDimName;
    }

    public void setRuleDimDataType(String ruleDimDataType) {
        this.ruleDimDataType = ruleDimDataType;
    }

    @Column(name = "rule_dim_data_type", nullable = true, length = 15)
    public String getRuleDimDataType() {
        return ruleDimDataType;
    }

    public void setRuleDimDefaultValue(String ruleDimDefaultValue) {
        this.ruleDimDefaultValue = ruleDimDefaultValue;
    }

    @Column(name = "rule_dim_default_value", nullable = true, length = 150)
    public String getRuleDimDefaultValue() {
        return ruleDimDefaultValue;
    }

    public void setRuleDimCode(String ruleDimCode) {
        this.ruleDimCode = ruleDimCode;
    }

    @Column(name = "rule_dim_code", nullable = true, length = 150)
    public String getRuleDimCode() {
        return ruleDimCode;
    }

    public void setRuleDimDesc(String ruleDimDesc) {
        this.ruleDimDesc = ruleDimDesc;
    }

    @Column(name = "rule_dim_desc", nullable = true, length = 500)
    public String getRuleDimDesc() {
        return ruleDimDesc;
    }

    public void setRuleDimValueFrom(String ruleDimValueFrom) {
        this.ruleDimValueFrom = ruleDimValueFrom;
    }

    @Column(name = "rule_dim_value_from", nullable = true, length = 100)
    public String getRuleDimValueFrom() {
        return ruleDimValueFrom;
    }

    public void setRuleDimTargetSource(String ruleDimTargetSource) {
        this.ruleDimTargetSource = ruleDimTargetSource;
    }

    @Column(name = "rule_dim_target_source", nullable = true, length = 500)
    public String getRuleDimTargetSource() {
        return ruleDimTargetSource;
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

    public void setRuleDimReferenceFrom(String ruleDimReferenceFrom) {
        this.ruleDimReferenceFrom = ruleDimReferenceFrom;
    }

    @Column(name = "rule_dim_reference_from", nullable = true, length = 100)
    public String getRuleDimReferenceFrom() {
        return ruleDimReferenceFrom;
    }

    public void setRuleDimReferenceCode(String ruleDimReferenceCode) {
        this.ruleDimReferenceCode = ruleDimReferenceCode;
    }

    @Column(name = "rule_dim_reference_code", nullable = true, length = 100)
    public String getRuleDimReferenceCode() {
        return ruleDimReferenceCode;
    }

    @Column(name = "placeholder", nullable = true, length = 45)
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Transient
    public List<OperatorBean> getOperatorBeans() {
        return operatorBeans;
    }
}

