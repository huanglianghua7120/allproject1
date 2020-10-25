package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempItemEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmTplCalculateTempItemDAO_HI")
public class SrmTplCalculateTempItemDAO_HI extends ViewObjectImpl<SrmTplCalculateTempItemEntity_HI>  {
	public SrmTplCalculateTempItemDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmTplCalculateTempItemEntity_HI entity) {
		return super.save(entity);
	}
}
