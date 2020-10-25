package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionHeadersDAO_HI")
public class SrmPonAuctionHeadersDAO_HI extends ViewObjectImpl<SrmPonAuctionHeadersEntity_HI>  {
	public SrmPonAuctionHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionHeadersEntity_HI entity) {
		return super.save(entity);
	}
}
