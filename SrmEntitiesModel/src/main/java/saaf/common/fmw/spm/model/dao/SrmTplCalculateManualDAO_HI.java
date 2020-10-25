package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateManualEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmTplCalculateManualDAO_HI")
public class SrmTplCalculateManualDAO_HI extends ViewObjectImpl<SrmTplCalculateManualEntity_HI>  {
	public SrmTplCalculateManualDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmTplCalculateManualEntity_HI entity) {
		return super.save(entity);
	}
}
