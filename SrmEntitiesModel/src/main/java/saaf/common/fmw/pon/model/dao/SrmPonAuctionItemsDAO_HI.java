package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionItemsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionItemsDAO_HI")
public class SrmPonAuctionItemsDAO_HI extends ViewObjectImpl<SrmPonAuctionItemsEntity_HI>  {
	public SrmPonAuctionItemsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionItemsEntity_HI entity) {
		return super.save(entity);
	}
}
