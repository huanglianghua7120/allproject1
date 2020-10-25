package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoSupplyProportionHEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoSupplyProportionHDAO_HI")
public class SrmPoSupplyProportionHDAO_HI extends ViewObjectImpl<SrmPoSupplyProportionHEntity_HI>  {
	public SrmPoSupplyProportionHDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoSupplyProportionHEntity_HI entity) {
		return super.save(entity);
	}
}

