package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSampleTrialCatesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSampleTrialCatesDAO_HI")
public class SrmPosSampleTrialCatesDAO_HI extends ViewObjectImpl<SrmPosSampleTrialCatesEntity_HI>  {
	public SrmPosSampleTrialCatesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSampleTrialCatesEntity_HI entity) {
		return super.save(entity);
	}
}

