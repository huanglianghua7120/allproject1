package saaf.common.fmw.report.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.report.model.entities.readonly.SaafChartsEntity_HI_RO;

@Component("saafChartsDAO_HI_RO")
public class SaafChartsDAO_HI_RO extends DynamicViewObjectImpl<SaafChartsEntity_HI_RO>{
    public SaafChartsDAO_HI_RO() {
        super();
    }
}
