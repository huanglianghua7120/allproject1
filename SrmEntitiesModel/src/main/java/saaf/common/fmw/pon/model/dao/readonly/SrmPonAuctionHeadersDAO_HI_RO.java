package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionHeadersEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionHeadersDAO_HI_RO")
public class SrmPonAuctionHeadersDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionHeadersEntity_HI_RO>  {
	public SrmPonAuctionHeadersDAO_HI_RO() {
		super();
	}

}
