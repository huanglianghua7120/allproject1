package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCategoriesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierCategoriesDAO_HI")
public class SrmPosSupplierCategoriesDAO_HI extends ViewObjectImpl<SrmPosSupplierCategoriesEntity_HI>  {
	public SrmPosSupplierCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}

