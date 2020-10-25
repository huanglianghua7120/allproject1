package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeContactBfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeContactBfDAO_HI")
public class SrmPosChangeContactBfDAO_HI extends ViewObjectImpl<SrmPosChangeContactBfEntity_HI>  {
	public SrmPosChangeContactBfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeContactBfEntity_HI entity) {
		return super.save(entity);
	}
}
