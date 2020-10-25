package saaf.common.fmw.bpm.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.bpm.model.entities.readonly.SaafActHiProcinstEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;


@Component("saafActHiProcinstDAO_HI_RO")
public class SaafActHiProcinstDAO_HI_RO  extends DynamicViewObjectImpl<SaafActHiProcinstEntity_HI_RO>{

	public SaafActHiProcinstDAO_HI_RO() {
		super();
	}

}
