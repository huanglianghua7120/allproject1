package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActIdGroupEntity_HI;
import org.springframework.stereotype.Component;

@Component("actIdGroupDAO_HI")
public class ActIdGroupDAO_HI extends ViewObjectImpl<ActIdGroupEntity_HI>  {
	public ActIdGroupDAO_HI() {
		super();
	}

	@Override
	public Object save(ActIdGroupEntity_HI entity) {
		return super.save(entity);
	}
}
