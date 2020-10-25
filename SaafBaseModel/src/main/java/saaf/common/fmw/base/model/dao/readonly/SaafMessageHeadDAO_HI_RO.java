package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMessageHeadEntity_HI_RO;


@Component
public class SaafMessageHeadDAO_HI_RO extends DynamicViewObjectImpl<SaafMessageHeadEntity_HI_RO> {
    public SaafMessageHeadDAO_HI_RO() {
        super();
    }
}
