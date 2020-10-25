package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseCategoriesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseCategoriesDAO_HI")
public class SrmBaseCategoriesDAO_HI extends ViewObjectImpl<SrmBaseCategoriesEntity_HI>  {
	public SrmBaseCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}
