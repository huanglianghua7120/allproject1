package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemLaddersEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionItemLaddersDAO_HI_RO")
public class SrmPonAuctionItemLaddersDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionItemLaddersEntity_HI_RO>  {
	public SrmPonAuctionItemLaddersDAO_HI_RO() {
		super();
	}

}
