package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiVarinstEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiVarinstDAO_HI")
public class ActHiVarinstDAO_HI extends ViewObjectImpl<ActHiVarinstEntity_HI>  {
	public ActHiVarinstDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiVarinstEntity_HI entity) {
		return super.save(entity);
	}
}
