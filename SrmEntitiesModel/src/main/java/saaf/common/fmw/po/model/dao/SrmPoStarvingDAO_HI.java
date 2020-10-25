package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoStarvingEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoStarvingDAO_HI")
public class SrmPoStarvingDAO_HI extends ViewObjectImpl<SrmPoStarvingEntity_HI>  {
	public SrmPoStarvingDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoStarvingEntity_HI entity) {
		return super.save(entity);
	}
}

