package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeAddressBfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeAddressBfDAO_HI")
public class SrmPosChangeAddressBfDAO_HI extends ViewObjectImpl<SrmPosChangeAddressBfEntity_HI>  {
	public SrmPosChangeAddressBfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeAddressBfEntity_HI entity) {
		return super.save(entity);
	}
}
