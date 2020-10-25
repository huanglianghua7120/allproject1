package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeBaseEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBaseDAO_HI")
public class SrmPosChangeBaseDAO_HI extends ViewObjectImpl<SrmPosChangeBaseEntity_HI>  {
	public SrmPosChangeBaseDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeBaseEntity_HI entity) {
		return super.save(entity);
	}
}

