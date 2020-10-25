package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActIdInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("actIdInfoDAO_HI")
public class ActIdInfoDAO_HI extends ViewObjectImpl<ActIdInfoEntity_HI>  {
	public ActIdInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(ActIdInfoEntity_HI entity) {
		return super.save(entity);
	}
}
