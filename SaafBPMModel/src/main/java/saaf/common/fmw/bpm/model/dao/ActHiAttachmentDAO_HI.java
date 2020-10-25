package saaf.common.fmw.bpm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.bpm.model.entities.ActHiAttachmentEntity_HI;
import org.springframework.stereotype.Component;

@Component("actHiAttachmentDAO_HI")
public class ActHiAttachmentDAO_HI extends ViewObjectImpl<ActHiAttachmentEntity_HI>  {
	public ActHiAttachmentDAO_HI() {
		super();
	}

	@Override
	public Object save(ActHiAttachmentEntity_HI entity) {
		return super.save(entity);
	}
}
