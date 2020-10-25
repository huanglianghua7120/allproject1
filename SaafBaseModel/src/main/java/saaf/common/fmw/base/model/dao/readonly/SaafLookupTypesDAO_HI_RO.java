package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafLookupTypesEntity_HI_RO;


@Component
public class SaafLookupTypesDAO_HI_RO extends DynamicViewObjectImpl<SaafLookupTypesEntity_HI_RO> {
    public SaafLookupTypesDAO_HI_RO() {
        super();
    }
}
