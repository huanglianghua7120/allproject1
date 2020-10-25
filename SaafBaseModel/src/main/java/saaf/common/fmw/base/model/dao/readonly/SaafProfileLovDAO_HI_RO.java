package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProfileLovEntity_HI_RO;


@Component
public class SaafProfileLovDAO_HI_RO extends DynamicViewObjectImpl<SaafProfileLovEntity_HI_RO> {
    public SaafProfileLovDAO_HI_RO() {
        super();
    }
}
