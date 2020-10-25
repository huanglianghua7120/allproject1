package saaf.common.fmw.bpm.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActRuTaskEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;


@Component("saafActRuTaskDAO_HI_RO")
public class SaafActRuTaskDAO_HI_RO  extends DynamicViewObjectImpl<SaafActRuTaskEntity_HI_RO>{

	public SaafActRuTaskDAO_HI_RO() {
		super();
	}

}
