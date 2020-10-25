package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActEvtLogEntity_HI;
import org.springframework.stereotype.Component;

@Component("actEvtLogDAO_HI")
public class ActEvtLogDAO_HI extends ViewObjectImpl<ActEvtLogEntity_HI>  {
	public ActEvtLogDAO_HI() {
		super();
	}

	@Override
	public Object save(ActEvtLogEntity_HI entity) {
		return super.save(entity);
	}
}
