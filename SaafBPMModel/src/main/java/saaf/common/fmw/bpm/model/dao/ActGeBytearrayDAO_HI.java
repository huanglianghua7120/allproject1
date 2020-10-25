package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActGeBytearrayEntity_HI;
import org.springframework.stereotype.Component;

@Component("actGeBytearrayDAO_HI")
public class ActGeBytearrayDAO_HI extends ViewObjectImpl<ActGeBytearrayEntity_HI>  {
	public ActGeBytearrayDAO_HI() {
		super();
	}

	@Override
	public Object save(ActGeBytearrayEntity_HI entity) {
		return super.save(entity);
	}
}
