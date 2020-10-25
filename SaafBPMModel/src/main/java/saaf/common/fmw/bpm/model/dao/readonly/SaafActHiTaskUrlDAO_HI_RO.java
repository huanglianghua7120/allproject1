package saaf.common.fmw.bpm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActHiTaskUrlEntity_HI_RO;
@Component("saafActHiTaskUrlDAO_HI_RO")
public class SaafActHiTaskUrlDAO_HI_RO extends DynamicViewObjectImpl<SaafActHiTaskUrlEntity_HI_RO>{
    public SaafActHiTaskUrlDAO_HI_RO() {
        super();
    }
}
