package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewCatesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosLocaleReviewCatesDAO_HI")
public class SrmPosLocaleReviewCatesDAO_HI extends ViewObjectImpl<SrmPosLocaleReviewCatesEntity_HI>  {
	public SrmPosLocaleReviewCatesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosLocaleReviewCatesEntity_HI entity) {
		return super.save(entity);
	}
}
