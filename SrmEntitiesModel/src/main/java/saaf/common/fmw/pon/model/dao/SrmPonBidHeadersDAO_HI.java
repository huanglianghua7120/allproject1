package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonBidHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonBidHeadersDAO_HI")
public class SrmPonBidHeadersDAO_HI extends ViewObjectImpl<SrmPonBidHeadersEntity_HI>  {
	public SrmPonBidHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonBidHeadersEntity_HI entity) {
		return super.save(entity);
	}
}
