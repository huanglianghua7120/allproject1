package saaf.common.fmw.spm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmPerformanceIndicatorsEntity_HI_RO;

@Component
public class SrmSpmPerformanceIndicatorsDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmPerformanceIndicatorsEntity_HI_RO>{

	public SrmSpmPerformanceIndicatorsDAO_HI_RO(){
		super();
	}
}
