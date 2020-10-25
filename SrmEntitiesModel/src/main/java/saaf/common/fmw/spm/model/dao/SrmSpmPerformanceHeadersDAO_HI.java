package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmPerformanceHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmPerformanceHeadersDAO_HI")
public class SrmSpmPerformanceHeadersDAO_HI extends ViewObjectImpl<SrmSpmPerformanceHeadersEntity_HI>  {
	public SrmSpmPerformanceHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmPerformanceHeadersEntity_HI entity) {
		return super.save(entity);
	}
}
