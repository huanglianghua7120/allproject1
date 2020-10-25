package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierSitesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierSitesDAO_HI")
public class SrmPosSupplierSitesDAO_HI extends ViewObjectImpl<SrmPosSupplierSitesEntity_HI>  {
	public SrmPosSupplierSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierSitesEntity_HI entity) {
		return super.save(entity);
	}
}
