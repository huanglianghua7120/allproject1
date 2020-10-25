package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeInfoDAO_HI")
public class SrmPosChangeInfoDAO_HI extends ViewObjectImpl<SrmPosChangeInfoEntity_HI>  {
	public SrmPosChangeInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeInfoEntity_HI entity) {
		return super.save(entity);
	}
}

