package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmCalculateResultEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmCalculateResultDAO_HI")
public class SrmSpmCalculateResultDAO_HI extends ViewObjectImpl<SrmSpmCalculateResultEntity_HI>  {
	public SrmSpmCalculateResultDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmCalculateResultEntity_HI entity) {
		return super.save(entity);
	}
}
