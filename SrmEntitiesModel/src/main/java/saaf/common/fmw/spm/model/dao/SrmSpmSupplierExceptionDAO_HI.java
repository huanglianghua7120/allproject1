package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierExceptionEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmSupplierExceptionDAO_HI")
public class SrmSpmSupplierExceptionDAO_HI extends ViewObjectImpl<SrmSpmSupplierExceptionEntity_HI>  {

	public SrmSpmSupplierExceptionDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmSupplierExceptionEntity_HI entity) {
		return super.save(entity);
	}
}
