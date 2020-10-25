package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiTaskinstEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiTaskinstDAO_HI")
public class ActHiTaskinstDAO_HI extends ViewObjectImpl<ActHiTaskinstEntity_HI>  {
	public ActHiTaskinstDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiTaskinstEntity_HI entity) {
		return super.save(entity);
	}
}
