package saaf.common.fmw.bpm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActTaskHistoryEntity_HI_RO;
@Component("saafActTaskHistoryDAO_HI_RO")
public class SaafActTaskHistoryDAO_HI_RO extends DynamicViewObjectImpl<SaafActTaskHistoryEntity_HI_RO>{
    public SaafActTaskHistoryDAO_HI_RO() {
        super();
    }
}
