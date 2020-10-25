package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionItemsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionItemsDAO_HI_RO")
public class SrmPonAuctionItemsDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionItemsEntity_HI_RO>  {
	public SrmPonAuctionItemsDAO_HI_RO() {
		super();
	}

}
