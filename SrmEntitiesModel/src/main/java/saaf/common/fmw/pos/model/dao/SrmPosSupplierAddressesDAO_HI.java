package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierAddressesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierAddressesDAO_HI")
public class SrmPosSupplierAddressesDAO_HI extends ViewObjectImpl<SrmPosSupplierAddressesEntity_HI>  {
	public SrmPosSupplierAddressesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierAddressesEntity_HI entity) {
		return super.save(entity);
	}
}
