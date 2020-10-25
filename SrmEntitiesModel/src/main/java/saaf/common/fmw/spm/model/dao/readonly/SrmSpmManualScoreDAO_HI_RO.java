package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmManualScoreEntity_HI_RO;

@Component
public class SrmSpmManualScoreDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmManualScoreEntity_HI_RO>{
	public SrmSpmManualScoreDAO_HI_RO(){
		super();
	}
}
