package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeBankBfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBankBfDAO_HI")
public class SrmPosChangeBankBfDAO_HI extends ViewObjectImpl<SrmPosChangeBankBfEntity_HI>  {
	public SrmPosChangeBankBfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeBankBfEntity_HI entity) {
		return super.save(entity);
	}
}
