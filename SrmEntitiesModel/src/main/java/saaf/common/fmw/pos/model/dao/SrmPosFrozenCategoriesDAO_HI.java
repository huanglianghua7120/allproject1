package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import org.springframework.stereotype.Component;

import saaf.common.fmw.pos.model.entities.SrmPosFrozenCategoriesEntity_HI;

@Component("srmPosFrozenCategoriesDAO_HI")
public class SrmPosFrozenCategoriesDAO_HI extends ViewObjectImpl<SrmPosFrozenCategoriesEntity_HI>  {
	public SrmPosFrozenCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosFrozenCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}
