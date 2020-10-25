package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProductVersionEntity_HI_RO;


@Component
public class SaafProductVersionDAO_HI_RO extends DynamicViewObjectImpl<SaafProductVersionEntity_HI_RO> {
    public SaafProductVersionDAO_HI_RO() {
        super();
    }
}
