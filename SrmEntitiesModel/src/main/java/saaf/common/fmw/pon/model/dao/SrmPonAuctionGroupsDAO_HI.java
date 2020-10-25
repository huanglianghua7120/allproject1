package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionGroupsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionGroupsDAO_HI")
public class SrmPonAuctionGroupsDAO_HI extends ViewObjectImpl<SrmPonAuctionGroupsEntity_HI>  {
	public SrmPonAuctionGroupsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionGroupsEntity_HI entity) {
		return super.save(entity);
	}
}
