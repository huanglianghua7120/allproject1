package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorFactorsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmIndicatorFactorsDAO_HI")
public class SrmSpmIndicatorFactorsDAO_HI extends ViewObjectImpl<SrmSpmIndicatorFactorsEntity_HI>  {
	public SrmSpmIndicatorFactorsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmIndicatorFactorsEntity_HI entity) {
		return super.save(entity);
	}
}
