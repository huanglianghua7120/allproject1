package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonJuryCountersignsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonJuryCountersignsDAO_HI")
public class SrmPonJuryCountersignsDAO_HI extends ViewObjectImpl<SrmPonJuryCountersignsEntity_HI>  {
	public SrmPonJuryCountersignsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonJuryCountersignsEntity_HI entity) {
		return super.save(entity);
	}
}
