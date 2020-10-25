package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionSuppliersEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionSuppliersDAO_HI_RO")
public class SrmPonAuctionSuppliersDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionSuppliersEntity_HI_RO>  {
	public SrmPonAuctionSuppliersDAO_HI_RO() {
		super();
	}

}
