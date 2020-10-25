package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionLEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoSupplyProportionLDAO_HI")
public class SrmPoSupplyProportionLDAO_HI extends ViewObjectImpl<SrmPoSupplyProportionLEntity_HI>  {
	public SrmPoSupplyProportionLDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoSupplyProportionLEntity_HI entity) {
		return super.save(entity);
	}
}

