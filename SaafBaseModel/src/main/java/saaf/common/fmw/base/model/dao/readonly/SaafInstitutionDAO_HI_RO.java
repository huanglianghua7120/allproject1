package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafInstitutionEntity_HI_RO;


@Component
public class SaafInstitutionDAO_HI_RO extends DynamicViewObjectImpl<SaafInstitutionEntity_HI_RO> {
    public SaafInstitutionDAO_HI_RO() {
        super();
    }
}
