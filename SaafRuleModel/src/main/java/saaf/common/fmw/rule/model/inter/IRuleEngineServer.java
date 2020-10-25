package saaf.common.fmw.rule.model.inter;

import saaf.common.fmw.rule.model.beans.RuleMappingResult;

import java.util.Map;

/**
 * Created by Admin on 2017/8/8.
 */
public interface IRuleEngineServer {
    RuleMappingResult execute(String ruleBusinessLineCode, String ruleExpCode, Map<String, Object> dimParams);
}
