package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeContactsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeContactsDAO_HI")
public class SrmPosChangeContactsDAO_HI extends ViewObjectImpl<SrmPosChangeContactsEntity_HI>  {
	public SrmPosChangeContactsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeContactsEntity_HI entity) {
		return super.save(entity);
	}
}

