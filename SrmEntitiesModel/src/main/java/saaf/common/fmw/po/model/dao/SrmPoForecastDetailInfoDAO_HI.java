package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoForecastDetailInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoForecastDetailInfoDAO_HI")
public class SrmPoForecastDetailInfoDAO_HI extends ViewObjectImpl<SrmPoForecastDetailInfoEntity_HI>  {
	public SrmPoForecastDetailInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoForecastDetailInfoEntity_HI entity) {
		return super.save(entity);
	}
	
	
	
	
}

