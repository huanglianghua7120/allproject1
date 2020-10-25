package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseNoticesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseNoticesDAO_HI")
public class SrmBaseNoticesDAO_HI extends ViewObjectImpl<SrmBaseNoticesEntity_HI>  {
	public SrmBaseNoticesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseNoticesEntity_HI entity) {
		return super.save(entity);
	}
}

