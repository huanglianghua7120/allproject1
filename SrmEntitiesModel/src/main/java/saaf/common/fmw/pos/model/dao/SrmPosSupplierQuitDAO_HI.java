package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierQuitEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierQuitDAO_HI")
public class SrmPosSupplierQuitDAO_HI extends ViewObjectImpl<SrmPosSupplierQuitEntity_HI>  {
	public SrmPosSupplierQuitDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierQuitEntity_HI entity) {
		return super.save(entity);
	}
}

