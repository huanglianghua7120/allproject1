package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfForecastsEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfForecastsDAO_HI")
public class SrmIntfForecastsDAO_HI extends ViewObjectImpl<SrmIntfForecastsEntity_HI>  {
	public SrmIntfForecastsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfForecastsEntity_HI entity) {
		return super.save(entity);
	}
}
