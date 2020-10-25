package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonAuctionGroupsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonAuctionGroupsDAO_HI_RO")
public class SrmPonAuctionGroupsDAO_HI_RO extends DynamicViewObjectImpl<SrmPonAuctionGroupsEntity_HI_RO>  {
	public SrmPonAuctionGroupsDAO_HI_RO() {
		super();
	}

}
