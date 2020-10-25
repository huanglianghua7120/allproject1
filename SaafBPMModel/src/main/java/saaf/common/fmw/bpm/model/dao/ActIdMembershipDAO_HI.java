package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActIdMembershipEntity_HI;
import org.springframework.stereotype.Component;

@Component("actIdMembershipDAO_HI")
public class ActIdMembershipDAO_HI extends ViewObjectImpl<ActIdMembershipEntity_HI>  {
	public ActIdMembershipDAO_HI() {
		super();
	}

	@Override
	public Object save(ActIdMembershipEntity_HI entity) {
		return super.save(entity);
	}
}
