package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiProcinstEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiProcinstDAO_HI")
public class ActHiProcinstDAO_HI extends ViewObjectImpl<ActHiProcinstEntity_HI>  {
	public ActHiProcinstDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiProcinstEntity_HI entity) {
		return super.save(entity);
	}
}
