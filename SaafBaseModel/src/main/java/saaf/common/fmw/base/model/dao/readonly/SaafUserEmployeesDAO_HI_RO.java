package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafUserEmployeesEntity_HI_RO;


@Component
public class SaafUserEmployeesDAO_HI_RO extends DynamicViewObjectImpl<SaafUserEmployeesEntity_HI_RO> {
    public SaafUserEmployeesDAO_HI_RO() {
        super();
    }
}
