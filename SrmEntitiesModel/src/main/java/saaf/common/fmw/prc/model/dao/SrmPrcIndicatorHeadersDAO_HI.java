package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcIndicatorHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcIndicatorHeadersDAO_HI")
public class SrmPrcIndicatorHeadersDAO_HI extends ViewObjectImpl<SrmPrcIndicatorHeadersEntity_HI>  {
	public SrmPrcIndicatorHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcIndicatorHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

