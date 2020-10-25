package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafUserRespEntity_HI_RO;


@Component
public class SaafUserRespDAO_HI_RO extends DynamicViewObjectImpl<SaafUserRespEntity_HI_RO> {
    public SaafUserRespDAO_HI_RO() {
        super();
    }
}
