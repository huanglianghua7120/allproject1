package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonPaymentTermsDAO_HI")
public class SrmPonPaymentTermsDAO_HI extends ViewObjectImpl<SrmPonPaymentTermsEntity_HI>  {
	public SrmPonPaymentTermsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonPaymentTermsEntity_HI entity) {
		return super.save(entity);
	}
}
