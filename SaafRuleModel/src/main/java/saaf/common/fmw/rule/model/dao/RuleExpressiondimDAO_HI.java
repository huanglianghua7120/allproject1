package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.RuleExpressiondimEntity_HI;

import org.springframework.stereotype.Component;

@Component("ruleExpressiondimDAO_HI")
public class RuleExpressiondimDAO_HI extends ViewObjectImpl<RuleExpressiondimEntity_HI> {
    public RuleExpressiondimDAO_HI() {
        super();
    }

    @Override
    public Object save(RuleExpressiondimEntity_HI entity) {
        return super.save(entity);
    }
}
