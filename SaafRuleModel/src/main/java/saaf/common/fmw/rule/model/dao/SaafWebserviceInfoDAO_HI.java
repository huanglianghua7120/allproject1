package saaf.common.fmw.rule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.rule.model.entities.SaafWebserviceInfoEntity_HI;

import org.springframework.stereotype.Component;

@Component("saafWebserviceInfoDAO_HI")
public class SaafWebserviceInfoDAO_HI extends ViewObjectImpl<SaafWebserviceInfoEntity_HI> {
    public SaafWebserviceInfoDAO_HI() {
        super();
    }

    @Override
    public Object save(SaafWebserviceInfoEntity_HI entity) {
        return super.save(entity);
    }
}

