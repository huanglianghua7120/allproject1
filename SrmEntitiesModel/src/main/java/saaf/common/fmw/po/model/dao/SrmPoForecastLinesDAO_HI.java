package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoForecastLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoForecastLinesDAO_HI")
public class SrmPoForecastLinesDAO_HI extends ViewObjectImpl<SrmPoForecastLinesEntity_HI>  {
	public SrmPoForecastLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoForecastLinesEntity_HI entity) {
		return super.save(entity);
	}
}
