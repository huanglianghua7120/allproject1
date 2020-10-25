package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosFrozenInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosFrozenInfoDAO_HI")
public class SrmPosFrozenInfoDAO_HI extends ViewObjectImpl<SrmPosFrozenInfoEntity_HI>  {
	public SrmPosFrozenInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosFrozenInfoEntity_HI entity) {
		return super.save(entity);
	}
}

