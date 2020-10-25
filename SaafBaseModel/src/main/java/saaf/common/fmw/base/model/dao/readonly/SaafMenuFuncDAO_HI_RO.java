package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMenuFuncEntity_HI_RO;


@Component
public class SaafMenuFuncDAO_HI_RO extends DynamicViewObjectImpl<SaafMenuFuncEntity_HI_RO> {
    public SaafMenuFuncDAO_HI_RO() {
        super();
    }
}
