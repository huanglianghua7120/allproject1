package saaf.common.fmw.base.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.base.model.entities.readonly.SaafMenuFuncEntity_HI_RO;


@Component("saafRespMenuFuncDAO_HI_RO")
public class saafRespMenuFuncDAO_HI_RO extends DynamicViewObjectImpl<SaafMenuFuncEntity_HI_RO> {
    public saafRespMenuFuncDAO_HI_RO() {
        super();
    }
}
