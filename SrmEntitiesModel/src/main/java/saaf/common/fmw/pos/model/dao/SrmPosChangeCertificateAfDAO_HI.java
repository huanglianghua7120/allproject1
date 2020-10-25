package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeCertificateAfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeCertificateAfDAO_HI")
public class SrmPosChangeCertificateAfDAO_HI extends ViewObjectImpl<SrmPosChangeCertificateAfEntity_HI>  {
	public SrmPosChangeCertificateAfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeCertificateAfEntity_HI entity) {
		return super.save(entity);
	}
}
