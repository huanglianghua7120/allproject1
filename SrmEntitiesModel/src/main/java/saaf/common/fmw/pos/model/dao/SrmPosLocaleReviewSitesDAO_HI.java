package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosLocaleReviewSitesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosLocaleReviewSitesDAO_HI")
public class SrmPosLocaleReviewSitesDAO_HI extends ViewObjectImpl<SrmPosLocaleReviewSitesEntity_HI>  {
	public SrmPosLocaleReviewSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosLocaleReviewSitesEntity_HI entity) {
		return super.save(entity);
	}
}
