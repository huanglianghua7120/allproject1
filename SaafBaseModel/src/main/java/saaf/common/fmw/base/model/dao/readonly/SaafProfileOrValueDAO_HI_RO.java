package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafProfileOrValueEntity_HI_RO;


@Component
public class SaafProfileOrValueDAO_HI_RO extends DynamicViewObjectImpl<SaafProfileOrValueEntity_HI_RO> {
    public SaafProfileOrValueDAO_HI_RO() {
        super();
    }
}
