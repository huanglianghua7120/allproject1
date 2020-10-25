package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.readonly.SrmPosSampleTrialsEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component
public class SrmPosSampleTrialsDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSampleTrialsEntity_HI_RO>{
	
	public SrmPosSampleTrialsDAO_HI_RO(){
		super();
	}
}
