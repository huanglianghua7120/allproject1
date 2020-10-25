package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoDeliveryHeadersDAO_HI")
public class SrmPoDeliveryHeadersDAO_HI extends ViewObjectImpl<SrmPoDeliveryHeadersEntity_HI>  {
	public SrmPoDeliveryHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoDeliveryHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

