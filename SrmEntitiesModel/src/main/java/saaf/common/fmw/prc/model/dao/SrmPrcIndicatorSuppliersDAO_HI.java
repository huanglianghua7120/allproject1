package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcIndicatorSuppliersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcIndicatorSuppliersDAO_HI")
public class SrmPrcIndicatorSuppliersDAO_HI extends ViewObjectImpl<SrmPrcIndicatorSuppliersEntity_HI>  {
	public SrmPrcIndicatorSuppliersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcIndicatorSuppliersEntity_HI entity) {
		return super.save(entity);
	}
}

