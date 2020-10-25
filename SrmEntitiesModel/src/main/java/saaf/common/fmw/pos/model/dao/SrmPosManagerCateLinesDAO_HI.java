package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCateLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosManagerCateLinesDAO_HI")
public class SrmPosManagerCateLinesDAO_HI extends ViewObjectImpl<SrmPosManagerCateLinesEntity_HI>  {
	public SrmPosManagerCateLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosManagerCateLinesEntity_HI entity) {
		return super.save(entity);
	}
}

