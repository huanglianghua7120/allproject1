package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoForecastInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoForecastInfoDAO_HI")
public class SrmPoForecastInfoDAO_HI extends ViewObjectImpl<SrmPoForecastInfoEntity_HI>  {
	public SrmPoForecastInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoForecastInfoEntity_HI entity) {
		return super.save(entity);
	}
}

