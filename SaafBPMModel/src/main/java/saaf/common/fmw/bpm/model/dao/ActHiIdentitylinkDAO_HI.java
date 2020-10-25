package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiIdentitylinkEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiIdentitylinkDAO_HI")
public class ActHiIdentitylinkDAO_HI extends ViewObjectImpl<ActHiIdentitylinkEntity_HI>  {
	public ActHiIdentitylinkDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiIdentitylinkEntity_HI entity) {
		return super.save(entity);
	}
}
