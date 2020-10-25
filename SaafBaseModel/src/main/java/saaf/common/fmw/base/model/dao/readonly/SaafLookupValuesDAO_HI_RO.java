package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupValuesEntity_HI_RO;


@Component
public class SaafLookupValuesDAO_HI_RO extends DynamicViewObjectImpl<SaafLookupValuesEntity_HI_RO> {
    public SaafLookupValuesDAO_HI_RO() {
        super();
    }
}
