package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonAuctionScoreRulesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionScoreRulesDAO_HI")
public class SrmPonAuctionScoreRulesDAO_HI extends ViewObjectImpl<SrmPonAuctionScoreRulesEntity_HI>  {
	public SrmPonAuctionScoreRulesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonAuctionScoreRulesEntity_HI entity) {
		return super.save(entity);
	}
}
