package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionSuppliersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionSuppliersDAO_HI")
public class SrmPonAuctionSuppliersDAO_HI extends ViewObjectImpl<SrmPonAuctionSuppliersEntity_HI>  {
	public SrmPonAuctionSuppliersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionSuppliersEntity_HI entity) {
		return super.save(entity);
	}
}
