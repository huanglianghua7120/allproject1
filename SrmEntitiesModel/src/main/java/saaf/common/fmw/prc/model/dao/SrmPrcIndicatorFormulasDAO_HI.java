package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcIndicatorFormulasEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcIndicatorFormulasDAO_HI")
public class SrmPrcIndicatorFormulasDAO_HI extends ViewObjectImpl<SrmPrcIndicatorFormulasEntity_HI>  {
	public SrmPrcIndicatorFormulasDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcIndicatorFormulasEntity_HI entity) {
		return super.save(entity);
	}
}

