package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.RuleDimEntity_HI;

import org.springframework.stereotype.Component;

@Component("ruleDimDAO_HI")
public class RuleDimDAO_HI extends ViewObjectImpl<RuleDimEntity_HI> {
    public RuleDimDAO_HI() {
        super();
    }

    @Override
    public Object save(RuleDimEntity_HI entity) {
        return super.save(entity);
    }
}

