package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuJobEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuJobDAO_HI")
public class ActRuJobDAO_HI extends ViewObjectImpl<ActRuJobEntity_HI>  {
	public ActRuJobDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuJobEntity_HI entity) {
		return super.save(entity);
	}
}
