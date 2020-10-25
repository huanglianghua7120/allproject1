package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.RuleMappingBusinessEntity_HI;

import org.springframework.stereotype.Component;

@Component("ruleMappingBusinessDAO_HI")
public class RuleMappingBusinessDAO_HI extends ViewObjectImpl<RuleMappingBusinessEntity_HI> {
    public RuleMappingBusinessDAO_HI() {
        super();
    }

    @Override
    public Object save(RuleMappingBusinessEntity_HI entity) {
        return super.save(entity);
    }
}
