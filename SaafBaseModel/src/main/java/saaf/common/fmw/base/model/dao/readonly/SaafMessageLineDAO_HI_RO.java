package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMessageLineEntity_HI_RO;


@Component
public class SaafMessageLineDAO_HI_RO extends DynamicViewObjectImpl<SaafMessageLineEntity_HI_RO> {
    public SaafMessageLineDAO_HI_RO() {
        super();
    }
}
