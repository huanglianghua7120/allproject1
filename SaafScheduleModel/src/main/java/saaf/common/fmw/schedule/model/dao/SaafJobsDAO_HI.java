package saaf.common.fmw.schedule.model.dao;


import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.SaafJobsEntity_HI;

@Component("saafJobsDAO_HI")
public class SaafJobsDAO_HI extends ViewObjectImpl<SaafJobsEntity_HI> {
    public SaafJobsDAO_HI() {
        super();
    }
}
