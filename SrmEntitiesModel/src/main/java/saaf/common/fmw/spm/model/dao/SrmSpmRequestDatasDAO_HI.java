package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmRequestDatasEntity_HI;
import org.springframework.stereotype.Component;

@Component
public class SrmSpmRequestDatasDAO_HI extends ViewObjectImpl<SrmSpmRequestDatasEntity_HI>  {
	public SrmSpmRequestDatasDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmRequestDatasEntity_HI entity) {
		return super.save(entity);
	}
}
