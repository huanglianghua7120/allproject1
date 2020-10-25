package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryDetailsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoDeliveryDetailsDAO_HI")
public class SrmPoDeliveryDetailsDAO_HI extends ViewObjectImpl<SrmPoDeliveryDetailsEntity_HI>  {
	public SrmPoDeliveryDetailsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoDeliveryDetailsEntity_HI entity) {
		return super.save(entity);
	}
}

