package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.po.model.entities.SaafDbConfigEntity_HI;

import org.springframework.stereotype.Component;

@Component("saafDbConfigDAO_HI")
public class SaafDbConfigDAO_HI extends ViewObjectImpl<SaafDbConfigEntity_HI>  {
	public SaafDbConfigDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafDbConfigEntity_HI entity) {
		return super.save(entity);
	}
}
