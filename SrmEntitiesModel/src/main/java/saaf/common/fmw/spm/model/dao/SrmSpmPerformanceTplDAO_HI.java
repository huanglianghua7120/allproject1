package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceTplEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceTplDAO_HI")
public class SrmSpmPerformanceTplDAO_HI extends ViewObjectImpl<SrmSpmPerformanceTplEntity_HI>  {
	public SrmSpmPerformanceTplDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceTplEntity_HI entity) {
		return super.save(entity);
	}
}
