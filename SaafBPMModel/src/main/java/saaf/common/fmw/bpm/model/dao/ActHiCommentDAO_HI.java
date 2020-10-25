package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiCommentEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiCommentDAO_HI")
public class ActHiCommentDAO_HI extends ViewObjectImpl<ActHiCommentEntity_HI>  {
	public ActHiCommentDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiCommentEntity_HI entity) {
		return super.save(entity);
	}
}
