package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionJuriesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionJuriesDAO_HI_RO")
public class SrmPonAuctionJuriesDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionJuriesEntity_HI_RO>  {
	public SrmPonAuctionJuriesDAO_HI_RO() {
		super();
	}

}
