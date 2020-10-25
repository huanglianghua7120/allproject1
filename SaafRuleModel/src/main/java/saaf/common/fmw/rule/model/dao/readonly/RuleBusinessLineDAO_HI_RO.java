package saaf.common.fmw.rule.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.rule.model.entities.readonly.RuleBusinessLineEntity_HI_RO;

import org.springframework.stereotype.Component;

@Component("ruleBusinessLineDAO_HI_RO")
public class RuleBusinessLineDAO_HI_RO extends DynamicViewObjectImpl<RuleBusinessLineEntity_HI_RO> {
    public RuleBusinessLineDAO_HI_RO() {
        super();
    }

}
