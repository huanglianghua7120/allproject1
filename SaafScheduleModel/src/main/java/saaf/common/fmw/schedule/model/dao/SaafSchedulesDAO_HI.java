package saaf.common.fmw.schedule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.SaafSchedulesEntity_HI;

@Component("saafSchedulesDAO_HI")
public class SaafSchedulesDAO_HI extends ViewObjectImpl<SaafSchedulesEntity_HI> {
    public SaafSchedulesDAO_HI() {
        super();
    }
}
