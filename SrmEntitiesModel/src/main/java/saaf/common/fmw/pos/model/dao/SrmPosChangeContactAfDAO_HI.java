package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeContactAfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeContactAfDAO_HI")
public class SrmPosChangeContactAfDAO_HI extends ViewObjectImpl<SrmPosChangeContactAfEntity_HI>  {
	public SrmPosChangeContactAfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeContactAfEntity_HI entity) {
		return super.save(entity);
	}
}
