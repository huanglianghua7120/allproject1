package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorScoreEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmIndicatorScoreDAO_HI")
public class SrmSpmIndicatorScoreDAO_HI extends ViewObjectImpl<SrmSpmIndicatorScoreEntity_HI>  {
	public SrmSpmIndicatorScoreDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmIndicatorScoreEntity_HI entity) {
		return super.save(entity);
	}
}
