package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.RuleExpressionEntity_HI;

import org.springframework.stereotype.Component;

@Component("ruleExpressionDAO_HI")
public class RuleExpressionDAO_HI extends ViewObjectImpl<RuleExpressionEntity_HI> {
    public RuleExpressionDAO_HI() {
        super();
    }

    @Override
    public Object save(RuleExpressionEntity_HI entity) {
        return super.save(entity);
    }
}
