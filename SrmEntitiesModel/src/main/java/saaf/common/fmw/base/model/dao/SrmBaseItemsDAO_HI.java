package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseItemsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseItemsDAO_HI")
public class SrmBaseItemsDAO_HI extends ViewObjectImpl<SrmBaseItemsEntity_HI>  {
	public SrmBaseItemsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseItemsEntity_HI entity) {
		return super.save(entity);
	}
}
