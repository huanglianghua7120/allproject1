package saaf.common.fmw.schedule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.SaafJobParametersEntity_HI;

@Component("saafJobParametersDAO_HI")
public class SaafJobParametersDAO_HI extends ViewObjectImpl<SaafJobParametersEntity_HI> {
    public SaafJobParametersDAO_HI() {
        super();
    }
}
