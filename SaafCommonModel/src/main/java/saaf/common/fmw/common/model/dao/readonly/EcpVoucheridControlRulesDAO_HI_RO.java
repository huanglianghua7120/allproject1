package saaf.common.fmw.common.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.readonly.EcpVoucheridControlRulesEntity_HI_RO;
@Component("ecpVoucheridControlRulesDAO_HI_RO")
public class EcpVoucheridControlRulesDAO_HI_RO extends DynamicViewObjectImpl<EcpVoucheridControlRulesEntity_HI_RO> {
    public EcpVoucheridControlRulesDAO_HI_RO() {
        super();
    }
}
