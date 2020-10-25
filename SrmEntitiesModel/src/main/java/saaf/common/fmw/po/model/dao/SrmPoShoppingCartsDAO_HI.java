package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoShoppingCartsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoShoppingCartsDAO_HI")
public class SrmPoShoppingCartsDAO_HI extends ViewObjectImpl<SrmPoShoppingCartsEntity_HI>  {
	public SrmPoShoppingCartsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoShoppingCartsEntity_HI entity) {
		return super.save(entity);
	}
}
