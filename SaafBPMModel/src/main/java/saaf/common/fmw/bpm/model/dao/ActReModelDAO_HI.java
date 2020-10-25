package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActReModelEntity_HI;
import org.springframework.stereotype.Component;

@Component("actReModelDAO_HI")
public class ActReModelDAO_HI extends ViewObjectImpl<ActReModelEntity_HI>  {
	public ActReModelDAO_HI() {
		super();
	}

	@Override
	public Object save(ActReModelEntity_HI entity) {
		return super.save(entity);
	}
}
