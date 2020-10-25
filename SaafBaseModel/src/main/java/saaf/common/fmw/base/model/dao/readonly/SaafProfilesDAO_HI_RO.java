package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProfilesEntity_HI_RO;


@Component
public class SaafProfilesDAO_HI_RO extends DynamicViewObjectImpl<SaafProfilesEntity_HI_RO> {
    public SaafProfilesDAO_HI_RO() {
        super();
    }
}
