package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeBankEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBankDAO_HI")
public class SrmPosChangeBankDAO_HI extends ViewObjectImpl<SrmPosChangeBankEntity_HI>  {
	public SrmPosChangeBankDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeBankEntity_HI entity) {
		return super.save(entity);
	}
}

