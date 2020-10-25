package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceIndicatorsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceIndicatorsDAO_HI")
public class SrmSpmPerformanceIndicatorsDAO_HI extends ViewObjectImpl<SrmSpmPerformanceIndicatorsEntity_HI>  {
	public SrmSpmPerformanceIndicatorsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceIndicatorsEntity_HI entity) {
		return super.save(entity);
	}
}
