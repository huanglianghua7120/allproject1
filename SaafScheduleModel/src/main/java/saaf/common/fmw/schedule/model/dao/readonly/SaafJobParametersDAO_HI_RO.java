package saaf.common.fmw.schedule.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.schedule.model.entities.readonly.SaafJobParametersEntity_HI_RO;

@Component("saafJobParametersDAO_HI_RO")
public class SaafJobParametersDAO_HI_RO extends DynamicViewObjectImpl<SaafJobParametersEntity_HI_RO> {
    public SaafJobParametersDAO_HI_RO() {
        super();
    }
}
