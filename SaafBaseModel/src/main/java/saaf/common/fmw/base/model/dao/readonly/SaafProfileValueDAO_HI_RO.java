package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProfileValueEntity_HI_RO;


@Component
public class SaafProfileValueDAO_HI_RO extends DynamicViewObjectImpl<SaafProfileValueEntity_HI_RO> {
    public SaafProfileValueDAO_HI_RO() {
        super();
    }
}
