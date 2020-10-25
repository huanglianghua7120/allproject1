package saaf.common.fmw.spm.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.spm.model.entities.readonly.SrmSpmIndicatorsEntity_HI_RO;

@Component
public class SrmSpmIndicatorsDAO_HI_RO  extends DynamicViewObjectImpl<SrmSpmIndicatorsEntity_HI_RO>{
	
	public SrmSpmIndicatorsDAO_HI_RO(){
		super();
	}
}
