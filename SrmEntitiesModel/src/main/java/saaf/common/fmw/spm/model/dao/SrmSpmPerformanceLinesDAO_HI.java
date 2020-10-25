package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceLinesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceLinesDAO_HI")
public class SrmSpmPerformanceLinesDAO_HI extends ViewObjectImpl<SrmSpmPerformanceLinesEntity_HI>  {
	public SrmSpmPerformanceLinesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceLinesEntity_HI entity) {
		return super.save(entity);
	}
}
