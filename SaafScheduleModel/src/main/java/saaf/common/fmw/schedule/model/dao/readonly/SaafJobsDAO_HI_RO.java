package saaf.common.fmw.schedule.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.readonly.SaafJobsEntity_HI_RO;

@Component("saafJobsDAO_HI_RO")
public class SaafJobsDAO_HI_RO extends DynamicViewObjectImpl<SaafJobsEntity_HI_RO> {
    public SaafJobsDAO_HI_RO() {
        super();
    }

}
