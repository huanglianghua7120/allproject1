package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCredentialsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierCredentialsDAO_HI")
public class SrmPosSupplierCredentialsDAO_HI extends ViewObjectImpl<SrmPosSupplierCredentialsEntity_HI>  {
	public SrmPosSupplierCredentialsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierCredentialsEntity_HI entity) {
		return super.save(entity);
	}
}

