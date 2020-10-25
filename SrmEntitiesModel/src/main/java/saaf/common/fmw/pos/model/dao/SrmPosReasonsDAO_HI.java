package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosReasonsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosReasonsDAO_HI")
public class SrmPosReasonsDAO_HI extends ViewObjectImpl<SrmPosReasonsEntity_HI>  {
	public SrmPosReasonsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosReasonsEntity_HI entity) {
		return super.save(entity);
	}
}

