package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierFeedbacksEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmSupplierFeedbacksDAO_HI")
public class SrmSpmSupplierFeedbacksDAO_HI extends ViewObjectImpl<SrmSpmSupplierFeedbacksEntity_HI>  {
	public SrmSpmSupplierFeedbacksDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmSupplierFeedbacksEntity_HI entity) {
		return super.save(entity);
	}
}
