package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionScoreRulesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionScoreRulesDAO_HI_RO")
public class SrmPonAuctionScoreRulesDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionScoreRulesEntity_HI_RO>  {
	public SrmPonAuctionScoreRulesDAO_HI_RO() {
		super();
	}

}
