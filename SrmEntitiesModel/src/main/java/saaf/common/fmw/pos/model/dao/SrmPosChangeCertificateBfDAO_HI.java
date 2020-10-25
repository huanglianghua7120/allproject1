package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeCertificateBfEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeCertificateBfDAO_HI")
public class SrmPosChangeCertificateBfDAO_HI extends ViewObjectImpl<SrmPosChangeCertificateBfEntity_HI>  {
	public SrmPosChangeCertificateBfDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeCertificateBfEntity_HI entity) {
		return super.save(entity);
	}
}
