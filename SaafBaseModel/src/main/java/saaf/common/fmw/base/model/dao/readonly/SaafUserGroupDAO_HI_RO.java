package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafUserGroupEntity_HI_RO;


@Component
public class SaafUserGroupDAO_HI_RO extends DynamicViewObjectImpl<SaafUserGroupEntity_HI_RO> {
    public SaafUserGroupDAO_HI_RO() {
        super();
    }
}
