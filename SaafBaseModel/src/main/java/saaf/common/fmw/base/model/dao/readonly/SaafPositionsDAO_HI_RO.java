package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafPositionsEntity_HI_RO;


@Component
public class SaafPositionsDAO_HI_RO extends DynamicViewObjectImpl<SaafPositionsEntity_HI_RO> {
    public SaafPositionsDAO_HI_RO() {
        super();
    }
}
