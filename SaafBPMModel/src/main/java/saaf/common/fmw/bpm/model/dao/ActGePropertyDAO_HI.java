package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActGePropertyEntity_HI;
import org.springframework.stereotype.Component;

@Component("actGePropertyDAO_HI")
public class ActGePropertyDAO_HI extends ViewObjectImpl<ActGePropertyEntity_HI>  {
	public ActGePropertyDAO_HI() {
		super();
	}

	@Override
	public Object save(ActGePropertyEntity_HI entity) {
		return super.save(entity);
	}
}
