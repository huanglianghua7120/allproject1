package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmTplIndicatorsDAO_HI")
public class SrmSpmTplIndicatorsDAO_HI extends ViewObjectImpl<SrmSpmTplIndicatorsEntity_HI>  {
	public SrmSpmTplIndicatorsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmTplIndicatorsEntity_HI entity) {
		return super.save(entity);
	}
}
