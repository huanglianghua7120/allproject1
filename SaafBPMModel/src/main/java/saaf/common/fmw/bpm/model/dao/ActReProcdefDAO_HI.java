package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActReProcdefEntity_HI;
import org.springframework.stereotype.Component;

@Component("actReProcdefDAO_HI")
public class ActReProcdefDAO_HI extends ViewObjectImpl<ActReProcdefEntity_HI>  {
	public ActReProcdefDAO_HI() {
		super();
	}

	@Override
	public Object save(ActReProcdefEntity_HI entity) {
		return super.save(entity);
	}
}
