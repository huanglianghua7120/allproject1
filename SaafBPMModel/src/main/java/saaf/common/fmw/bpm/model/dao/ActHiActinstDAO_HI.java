package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiActinstEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiActinstDAO_HI")
public class ActHiActinstDAO_HI extends ViewObjectImpl<ActHiActinstEntity_HI>  {
	public ActHiActinstDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiActinstEntity_HI entity) {
		return super.save(entity);
	}
}
