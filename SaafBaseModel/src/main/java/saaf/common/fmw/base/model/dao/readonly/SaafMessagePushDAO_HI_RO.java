package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMessagePushEntity_HI_RO;


@Component
public class SaafMessagePushDAO_HI_RO extends DynamicViewObjectImpl<SaafMessagePushEntity_HI_RO> {
    public SaafMessagePushDAO_HI_RO() {
        super();
    }
}
