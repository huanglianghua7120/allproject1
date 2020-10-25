package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSampleTrialsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSampleTrialsDAO_HI")
public class SrmPosSampleTrialsDAO_HI extends ViewObjectImpl<SrmPosSampleTrialsEntity_HI>  {
	public SrmPosSampleTrialsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSampleTrialsEntity_HI entity) {
		return super.save(entity);
	}
}

