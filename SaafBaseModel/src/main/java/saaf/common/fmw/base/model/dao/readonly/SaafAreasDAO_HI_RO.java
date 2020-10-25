package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafAreasEntity_HI_RO;


@Component
public class SaafAreasDAO_HI_RO extends DynamicViewObjectImpl<SaafAreasEntity_HI_RO> {
    public SaafAreasDAO_HI_RO() {
        super();
    }
}
