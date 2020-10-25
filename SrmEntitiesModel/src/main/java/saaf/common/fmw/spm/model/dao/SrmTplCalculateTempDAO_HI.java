package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmTplCalculateTempEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmTplCalculateTempDAO_HI")
public class SrmTplCalculateTempDAO_HI extends ViewObjectImpl<SrmTplCalculateTempEntity_HI>  {
	public SrmTplCalculateTempDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmTplCalculateTempEntity_HI entity) {
		return super.save(entity);
	}
}
