package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmManualScoreEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmManualScoreDAO_HI")
public class SrmSpmManualScoreDAO_HI extends ViewObjectImpl<SrmSpmManualScoreEntity_HI>  {
	public SrmSpmManualScoreDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmManualScoreEntity_HI entity) {
		return super.save(entity);
	}
}
