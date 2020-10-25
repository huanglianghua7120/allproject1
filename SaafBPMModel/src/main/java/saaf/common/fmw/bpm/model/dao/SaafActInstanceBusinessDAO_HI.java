package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.SaafActInstanceBusinessEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafActInstanceBusinessDAO_HI")
public class SaafActInstanceBusinessDAO_HI extends ViewObjectImpl<SaafActInstanceBusinessEntity_HI>  {
	public SaafActInstanceBusinessDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafActInstanceBusinessEntity_HI entity) {
		return super.save(entity);
	}
}
