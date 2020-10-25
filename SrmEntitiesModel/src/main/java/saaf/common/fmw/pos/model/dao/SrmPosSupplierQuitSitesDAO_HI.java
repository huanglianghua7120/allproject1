package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierQuitSitesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierQuitSitesDAO_HI")
public class SrmPosSupplierQuitSitesDAO_HI extends ViewObjectImpl<SrmPosSupplierQuitSitesEntity_HI>  {
	public SrmPosSupplierQuitSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierQuitSitesEntity_HI entity) {
		return super.save(entity);
	}
}
