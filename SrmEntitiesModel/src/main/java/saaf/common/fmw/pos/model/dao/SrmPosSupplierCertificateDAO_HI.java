package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosSupplierCertificateEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierCertificateDAO_HI")
public class SrmPosSupplierCertificateDAO_HI extends ViewObjectImpl<SrmPosSupplierCertificateEntity_HI>  {
	public SrmPosSupplierCertificateDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosSupplierCertificateEntity_HI entity) {
		return super.save(entity);
	}
}

