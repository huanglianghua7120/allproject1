package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmIndicatorsDAO_HI")
public class SrmSpmIndicatorsDAO_HI extends ViewObjectImpl<SrmSpmIndicatorsEntity_HI>  {
	public SrmSpmIndicatorsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmIndicatorsEntity_HI entity) {
		return super.save(entity);
	}
}
