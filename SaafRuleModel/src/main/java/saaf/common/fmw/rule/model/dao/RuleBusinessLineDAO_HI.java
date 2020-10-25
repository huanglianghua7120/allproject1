package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.RuleBusinessLineEntity_HI;

import org.springframework.stereotype.Component;

@Component("ruleBusinessLineDAO_HI")
public class RuleBusinessLineDAO_HI extends ViewObjectImpl<RuleBusinessLineEntity_HI> {
    public RuleBusinessLineDAO_HI() {
        super();
    }

    @Override
    public Object save(RuleBusinessLineEntity_HI entity) {
        return super.save(entity);
    }
}
