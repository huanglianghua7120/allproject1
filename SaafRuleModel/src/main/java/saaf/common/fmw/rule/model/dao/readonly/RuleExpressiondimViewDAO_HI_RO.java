package saaf.common.fmw.rule.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.rule.model.entities.readonly.RuleExpressiondimViewEntity_HI_RO;

import org.springframework.stereotype.Component;

@Component("ruleExpressiondimViewDAO_HI_RO")
public class RuleExpressiondimViewDAO_HI_RO extends DynamicViewObjectImpl<RuleExpressiondimViewEntity_HI_RO> {
    public RuleExpressiondimViewDAO_HI_RO() {
        super();
    }

}
