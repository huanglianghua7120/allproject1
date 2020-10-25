package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosFrozenSitesEntity_HI;

@Component("srmPosFrozenSitesDAO_HI")
public class SrmPosFrozenSitesDAO_HI extends ViewObjectImpl<SrmPosFrozenSitesEntity_HI>  {
	public SrmPosFrozenSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosFrozenSitesEntity_HI entity) {
		return super.save(entity);
	}
}
