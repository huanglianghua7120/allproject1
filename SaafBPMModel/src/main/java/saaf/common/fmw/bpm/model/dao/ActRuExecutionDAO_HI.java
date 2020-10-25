package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuExecutionEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuExecutionDAO_HI")
public class ActRuExecutionDAO_HI extends ViewObjectImpl<ActRuExecutionEntity_HI>  {
	public ActRuExecutionDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuExecutionEntity_HI entity) {
		return super.save(entity);
	}
}
