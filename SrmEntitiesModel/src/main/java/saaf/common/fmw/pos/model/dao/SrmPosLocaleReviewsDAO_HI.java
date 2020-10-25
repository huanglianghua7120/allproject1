package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosLocaleReviewsDAO_HI")
public class SrmPosLocaleReviewsDAO_HI extends ViewObjectImpl<SrmPosLocaleReviewsEntity_HI>  {
	public SrmPosLocaleReviewsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosLocaleReviewsEntity_HI entity) {
		return super.save(entity);
	}
}
