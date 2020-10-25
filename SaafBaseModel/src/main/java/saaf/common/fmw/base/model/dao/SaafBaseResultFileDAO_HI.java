package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SaafBaseResultFileEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafBaseResultFileDAO_HI")
public class SaafBaseResultFileDAO_HI extends ViewObjectImpl<SaafBaseResultFileEntity_HI>  {
	public SaafBaseResultFileDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafBaseResultFileEntity_HI entity) {
		return super.save(entity);
	}
}
