package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceEvaluateEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceEvaluateDAO_HI")
public class SrmSpmPerformanceEvaluateDAO_HI extends ViewObjectImpl<SrmSpmPerformanceEvaluateEntity_HI>  {
	public SrmSpmPerformanceEvaluateDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceEvaluateEntity_HI entity) {
		return super.save(entity);
	}
}
