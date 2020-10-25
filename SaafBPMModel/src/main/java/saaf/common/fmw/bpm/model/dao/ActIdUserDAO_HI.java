package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActIdUserEntity_HI;
import org.springframework.stereotype.Component;

@Component("actIdUserDAO_HI")
public class ActIdUserDAO_HI extends ViewObjectImpl<ActIdUserEntity_HI>  {
	public ActIdUserDAO_HI() {
		super();
	}

	@Override
	public Object save(ActIdUserEntity_HI entity) {
		return super.save(entity);
	}
}
