package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeBankAfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBankAfDAO_HI")
public class SrmPosChangeBankAfDAO_HI extends ViewObjectImpl<SrmPosChangeBankAfEntity_HI>  {
	public SrmPosChangeBankAfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeBankAfEntity_HI entity) {
		return super.save(entity);
	}
}
