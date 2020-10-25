package saaf.common.fmw.bpm.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.ActReProcdefEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component("saafActReProcdefDAO_HI_RO")
public class SaafActReProcdefDAO_HI_RO extends DynamicViewObjectImpl<ActReProcdefEntity_HI_RO> {

	public SaafActReProcdefDAO_HI_RO() {
		super();
	}

}
