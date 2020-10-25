package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierBankEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierBankDAO_HI")
public class SrmPosSupplierBankDAO_HI extends ViewObjectImpl<SrmPosSupplierBankEntity_HI>  {
	public SrmPosSupplierBankDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierBankEntity_HI entity) {
		return super.save(entity);
	}
}

