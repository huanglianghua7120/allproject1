package saaf.common.fmw.bpm.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuExecutionEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("saafActRuExecutionDAO_HI_RO")
public class SaafActRuExecutionDAO_HI_RO extends DynamicViewObjectImpl<SaafActRuExecutionEntity_HI_RO>{

	public SaafActRuExecutionDAO_HI_RO() {
		super();
	}

}
