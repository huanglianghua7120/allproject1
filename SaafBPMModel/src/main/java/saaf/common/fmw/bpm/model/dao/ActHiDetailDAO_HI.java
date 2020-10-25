package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiDetailEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiDetailDAO_HI")
public class ActHiDetailDAO_HI extends ViewObjectImpl<ActHiDetailEntity_HI>  {
	public ActHiDetailDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiDetailEntity_HI entity) {
		return super.save(entity);
	}
}
