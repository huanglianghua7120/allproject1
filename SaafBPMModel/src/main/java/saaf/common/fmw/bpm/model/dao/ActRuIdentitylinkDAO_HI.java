package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActRuIdentitylinkEntity_HI;
import org.springframework.stereotype.Component;

@Component("actRuIdentitylinkDAO_HI")
public class ActRuIdentitylinkDAO_HI extends ViewObjectImpl<ActRuIdentitylinkEntity_HI>  {
	public ActRuIdentitylinkDAO_HI() {
		super();
	}

	@Override
	public Object save(ActRuIdentitylinkEntity_HI entity) {
		return super.save(entity);
	}
}
