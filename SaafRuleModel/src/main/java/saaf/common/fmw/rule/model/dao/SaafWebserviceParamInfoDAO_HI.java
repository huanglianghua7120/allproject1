package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.SaafWebserviceParamInfoEntity_HI;

import org.springframework.stereotype.Component;

@Component("saafWebserviceParamInfoDAO_HI")
public class SaafWebserviceParamInfoDAO_HI extends ViewObjectImpl<SaafWebserviceParamInfoEntity_HI> {
    public SaafWebserviceParamInfoDAO_HI() {
        super();
    }

    @Override
    public Object save(SaafWebserviceParamInfoEntity_HI entity) {
        return super.save(entity);
    }
}

