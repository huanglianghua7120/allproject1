package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosQualificationSitesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosQualificationSitesDAO_HI")
public class SrmPosQualificationSitesDAO_HI extends ViewObjectImpl<SrmPosQualificationSitesEntity_HI>  {
	public SrmPosQualificationSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosQualificationSitesEntity_HI entity) {
		return super.save(entity);
	}
}
