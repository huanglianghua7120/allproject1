package saaf.common.fmw.rule.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.rule.model.entities.readonly.RuleMappingBusinessEntity_HI_RO;

import org.springframework.stereotype.Component;

@Component("ruleMappingBusinessDAO_HI_RO")
public class RuleMappingBusinessDAO_HI_RO extends DynamicViewObjectImpl<RuleMappingBusinessEntity_HI_RO> {
    public RuleMappingBusinessDAO_HI_RO() {
        super();
    }

}
