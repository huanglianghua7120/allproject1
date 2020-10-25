package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.SrmPoAgreementAssignsEntity_HI;

@Component("srmPoAgreementAssignsDAO_HI")
public class SrmPoAgreementAssignsDAO_HI extends ViewObjectImpl<SrmPoAgreementAssignsEntity_HI>  {

	public SrmPoAgreementAssignsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoAgreementAssignsEntity_HI entity) {
		return super.save(entity);
	}
}

