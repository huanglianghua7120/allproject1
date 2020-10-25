package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuEventSubscrEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuEventSubscrDAO_HI")
public class ActRuEventSubscrDAO_HI extends ViewObjectImpl<ActRuEventSubscrEntity_HI>  {
	public ActRuEventSubscrDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuEventSubscrEntity_HI entity) {
		return super.save(entity);
	}
}
