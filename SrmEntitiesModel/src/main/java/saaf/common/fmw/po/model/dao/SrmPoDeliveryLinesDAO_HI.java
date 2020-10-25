package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoDeliveryLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoDeliveryLinesDAO_HI")
public class SrmPoDeliveryLinesDAO_HI extends ViewObjectImpl<SrmPoDeliveryLinesEntity_HI>  {
	public SrmPoDeliveryLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoDeliveryLinesEntity_HI entity) {
		return super.save(entity);
	}
}

