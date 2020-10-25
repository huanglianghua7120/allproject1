package saaf.common.fmw.okc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTermsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmOkcContractTermsDAO_HI")
public class SrmOkcContractTermsDAO_HI extends ViewObjectImpl<SrmOkcContractTermsEntity_HI>  {
	public SrmOkcContractTermsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmOkcContractTermsEntity_HI entity) {
		return super.save(entity);
	}
}
