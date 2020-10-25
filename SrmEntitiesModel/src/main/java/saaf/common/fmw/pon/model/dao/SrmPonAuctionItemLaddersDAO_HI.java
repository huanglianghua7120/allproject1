package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemLaddersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionItemLaddersDAO_HI")
public class SrmPonAuctionItemLaddersDAO_HI extends ViewObjectImpl<SrmPonAuctionItemLaddersEntity_HI>  {
	public SrmPonAuctionItemLaddersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionItemLaddersEntity_HI entity) {
		return super.save(entity);
	}
}
