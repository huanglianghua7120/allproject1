package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuVariableEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuVariableDAO_HI")
public class ActRuVariableDAO_HI extends ViewObjectImpl<ActRuVariableEntity_HI>  {
	public ActRuVariableDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuVariableEntity_HI entity) {
		return super.save(entity);
	}
}
