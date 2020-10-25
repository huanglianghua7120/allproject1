package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceSchemeEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceSchemeDAO_HI")
public class SrmSpmPerformanceSchemeDAO_HI extends ViewObjectImpl<SrmSpmPerformanceSchemeEntity_HI>  {

	public SrmSpmPerformanceSchemeDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceSchemeEntity_HI entity) {
		return super.save(entity);
	}
}
