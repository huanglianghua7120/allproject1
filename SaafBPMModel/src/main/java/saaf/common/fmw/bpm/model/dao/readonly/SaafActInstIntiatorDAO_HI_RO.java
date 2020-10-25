package saaf.common.fmw.bpm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActInstIntiatorEntity_HI_RO;
@Component("saafActInstIntiatorDAO_HI_RO")
public class SaafActInstIntiatorDAO_HI_RO extends DynamicViewObjectImpl<SaafActInstIntiatorEntity_HI_RO>{
    public SaafActInstIntiatorDAO_HI_RO() {
        super();
    }
}
