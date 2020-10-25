package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseItemDeliversEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseItemDeliversDAO_HI")
public class SrmBaseItemDeliversDAO_HI extends ViewObjectImpl<SrmBaseItemDeliversEntity_HI>  {
	public SrmBaseItemDeliversDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseItemDeliversEntity_HI entity) {
		return super.save(entity);
	}
}
