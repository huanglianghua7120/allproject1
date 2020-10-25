package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.readonly.SrmPoAgreementAssignsEntity_HI_RO;

@Component("srmPoAgreementAssignsDAO_HI_RO")
public class SrmPoAgreementAssignsDAO_HI_RO extends DynamicViewObjectImpl<SrmPoAgreementAssignsEntity_HI_RO> {

    public SrmPoAgreementAssignsDAO_HI_RO() {
        super();

    }
}
