package saaf.common.fmw.bpm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuTaskUrlEntity_HI_RO;
@Component("saafActRuTaskUrlDAO_HI_RO")
public class SaafActRuTaskUrlDAO_HI_RO extends DynamicViewObjectImpl<SaafActRuTaskUrlEntity_HI_RO>{
    public SaafActRuTaskUrlDAO_HI_RO() {
        super();
    }
}
