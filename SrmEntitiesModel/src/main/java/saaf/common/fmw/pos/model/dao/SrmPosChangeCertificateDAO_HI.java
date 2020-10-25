package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeCertificateEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeCertificateDAO_HI")
public class SrmPosChangeCertificateDAO_HI extends ViewObjectImpl<SrmPosChangeCertificateEntity_HI>  {
	public SrmPosChangeCertificateDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeCertificateEntity_HI entity) {
		return super.save(entity);
	}
}

