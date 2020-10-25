package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonBidItemPricesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonBidItemPricesDAO_HI")
public class SrmPonBidItemPricesDAO_HI extends ViewObjectImpl<SrmPonBidItemPricesEntity_HI>  {
	public SrmPonBidItemPricesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonBidItemPricesEntity_HI entity) {
		return super.save(entity);
	}
}
