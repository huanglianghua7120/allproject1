package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuTaskEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuTaskDAO_HI")
public class ActRuTaskDAO_HI extends ViewObjectImpl<ActRuTaskEntity_HI>  {
	public ActRuTaskDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuTaskEntity_HI entity) {
		return super.save(entity);
	}
}
