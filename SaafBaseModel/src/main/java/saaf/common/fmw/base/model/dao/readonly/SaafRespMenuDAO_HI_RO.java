package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafRespMenuEntity_HI_RO;


@Component
public class SaafRespMenuDAO_HI_RO extends DynamicViewObjectImpl<SaafRespMenuEntity_HI_RO> {
    public SaafRespMenuDAO_HI_RO() {
        super();
    }
}
