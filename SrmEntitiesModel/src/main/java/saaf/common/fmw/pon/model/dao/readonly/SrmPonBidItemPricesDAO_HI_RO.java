package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidItemPricesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonBidItemPricesDAO_HI_RO")
public class SrmPonBidItemPricesDAO_HI_RO extends DynamicViewObjectImpl<SrmPonBidItemPricesEntity_HI_RO>  {
	public SrmPonBidItemPricesDAO_HI_RO() {
		super();
	}

}
