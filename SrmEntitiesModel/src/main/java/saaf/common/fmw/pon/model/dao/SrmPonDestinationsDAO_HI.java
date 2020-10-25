package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonDestinationsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonDestinationsDAO_HI")
public class SrmPonDestinationsDAO_HI extends ViewObjectImpl<SrmPonDestinationsEntity_HI>  {
	public SrmPonDestinationsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonDestinationsEntity_HI entity) {
		return super.save(entity);
	}
}
