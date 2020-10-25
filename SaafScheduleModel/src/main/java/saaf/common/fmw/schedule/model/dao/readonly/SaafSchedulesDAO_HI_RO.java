package saaf.common.fmw.schedule.model.dao.readonly;


import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.readonly.SaafSchedulesEntity_HI_RO;

@Component("saafSchedulesDAO_HI_RO")
public class SaafSchedulesDAO_HI_RO extends DynamicViewObjectImpl<SaafSchedulesEntity_HI_RO>{
    public SaafSchedulesDAO_HI_RO() {
        super();
    }
    
}
