package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonBidHeadersEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonBidHeadersDAO_HI_RO")
public class SrmPonBidHeadersDAO_HI_RO extends DynamicViewObjectImpl<SrmPonBidHeadersEntity_HI_RO>  {
	public SrmPonBidHeadersDAO_HI_RO() {
		super();
	}

}
