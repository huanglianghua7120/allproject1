package saaf.common.fmw.common.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.common.model.entities.readonly.EcpVoucheridRulesEntity_HI_RO;

@Component("ecpVoucheridRulesDAO_HI_RO")
public class EcpVoucheridRulesDAO_HI_RO extends DynamicViewObjectImpl<EcpVoucheridRulesEntity_HI_RO> {
    public EcpVoucheridRulesDAO_HI_RO() {
        super();
    }
}
