package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierContactsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierContactsDAO_HI")
public class SrmPosSupplierContactsDAO_HI extends ViewObjectImpl<SrmPosSupplierContactsEntity_HI>  {
	public SrmPosSupplierContactsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierContactsEntity_HI entity) {
		return super.save(entity);
	}
}

