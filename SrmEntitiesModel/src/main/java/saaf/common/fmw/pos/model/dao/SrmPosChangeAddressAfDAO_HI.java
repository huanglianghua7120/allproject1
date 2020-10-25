package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeAddressAfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeAddressAfDAO_HI")
public class SrmPosChangeAddressAfDAO_HI extends ViewObjectImpl<SrmPosChangeAddressAfEntity_HI>  {
	public SrmPosChangeAddressAfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeAddressAfEntity_HI entity) {
		return super.save(entity);
	}
}
