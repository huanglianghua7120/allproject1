package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionJuriesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionJuriesDAO_HI")
public class SrmPonAuctionJuriesDAO_HI extends ViewObjectImpl<SrmPonAuctionJuriesEntity_HI>  {
	public SrmPonAuctionJuriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionJuriesEntity_HI entity) {
		return super.save(entity);
	}
}
