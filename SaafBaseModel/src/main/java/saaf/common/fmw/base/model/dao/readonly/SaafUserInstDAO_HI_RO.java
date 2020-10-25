package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafUserInstEntity_HI_RO;


@Component
public class SaafUserInstDAO_HI_RO extends DynamicViewObjectImpl<SaafUserInstEntity_HI_RO> {
    public SaafUserInstDAO_HI_RO() {
        super();
    }
}
