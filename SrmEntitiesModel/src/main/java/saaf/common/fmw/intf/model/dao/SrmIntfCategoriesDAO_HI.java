package saaf.common.fmw.intf.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;

import saaf.common.fmw.intf.model.entities.SrmIntfCategoriesEntity_HI;

import org.springframework.stereotype.Component;

@Component("srmIntfCategoriesDAO_HI")
public class SrmIntfCategoriesDAO_HI extends ViewObjectImpl<SrmIntfCategoriesEntity_HI>  {
	public SrmIntfCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmIntfCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}
