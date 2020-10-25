package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoLinesCombEntity_HI;

@Component("srmPoLinesCombDAO_HI")
public class SrmPoLinesCombDAO_HI extends ViewObjectImpl<SrmPoLinesCombEntity_HI>  {
	public SrmPoLinesCombDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoLinesCombEntity_HI entity) {
		return super.save(entity);
	}
}
