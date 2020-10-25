package saaf.common.fmw.rule.model.entities;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * RuleMappingBusinessEntity_HI Entity Object
 * Mon May 08 23:15:35 CST 2017  Auto Generate
 */
@Entity
@Table(name = "rule_mapping_business")
public class RuleMappingBusinessEntity_HI {
    private Integer ruleMappBusId;
    private String ruleBusDim;
    private String ruleBusDimOperator;
    private String ruleBusDimValue;
    private String ruleBusTargetType;
    private String ruleBusTargetSource;
    private String ruleBusResultDim;
    private String ruleBusParam;
    private String ruleExcCode;
    private Integer ruleBusLevel;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date effectEndDate;

    public void setRuleMappBusId(Integer ruleMappBusId) {
        this.ruleMappBusId = ruleMappBusId;
    }

    @Id
    @GeneratedValue
    @Column(name = "rule_mapp_bus_id", nullable = false, length = 10)
    public Integer getRuleMappBusId() {
        return ruleMappBusId;
    }

    public void setRuleBusDim(String ruleBusDim) {
        this.ruleBusDim = ruleBusDim;
    }

    @Column(name = "rule_bus_dim", nullable = true, length = 150)
    public String getRuleBusDim() {
        return ruleBusDim;
    }

    public void setRuleBusDimValue(String ruleBusDimValue) {
        this.ruleBusDimValue = ruleBusDimValue;
    }

    @Column(name = "rule_bus_dim_value", nullable = true, length = 15)
    public String getRuleBusDimValue() {
        return ruleBusDimValue;
    }

    public void setRuleBusTargetType(String ruleBusTargetType) {
        this.ruleBusTargetType = ruleBusTargetType;
    }

    @Column(name = "rule_bus_target_type", nullable = true, length = 150)
    public String getRuleBusTargetType() {
        return ruleBusTargetType;
    }

    public void setRuleBusTargetSource(String ruleBusTargetSource) {
        this.ruleBusTargetSource = ruleBusTargetSource;
    }

    @Column(name = "rule_bus_target_source", nullable = true, length = 150)
    public String getRuleBusTargetSource() {
        return ruleBusTargetSource;
    }

    public void setRuleBusResultDim(String ruleBusResultDim) {
        this.ruleBusResultDim = ruleBusResultDim;
    }

    @Column(name = "rule_bus_result_dim", nullable = true, length = 3000)
    public String getRuleBusResultDim() {
        return ruleBusResultDim;
    }

    public void setRuleBusParam(String ruleBusParam) {
        this.ruleBusParam = ruleBusParam;
    }

    @Column(name = "rule_bus_param", nullable = true, length = 3000)
    public String getRuleBusParam() {
        return ruleBusParam;
    }

    public void setRuleExcCode(String ruleExcCode) {
        this.ruleExcCode = ruleExcCode;
    }

    @Column(name = "rule_exc_code", nullable = true, length = 100)
    public String getRuleExcCode() {
        return ruleExcCode;
    }

    public void setRuleBusLevel(Integer ruleBusLevel) {
        this.ruleBusLevel = ruleBusLevel;
    }

    @Column(name = "rule_bus_level", nullable = true, length = 11)
    public Integer getRuleBusLevel() {
        return ruleBusLevel;
    }

    public void setRuleBusDimOperator(String ruleBusDimOperator) {
        this.ruleBusDimOperator = ruleBusDimOperator;
    }

    @Column(name = "rule_bus_dim_operator", nullable = true, length = 15)
    public String getRuleBusDimOperator() {
        return ruleBusDimOperator;
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

