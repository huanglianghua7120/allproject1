package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanSitesEntity_HI;

@Component("srmPosInvestigationPlanSitesDAO_HI")
public class SrmPosInvestigationPlanSitesDAO_HI extends ViewObjectImpl<SrmPosInvestigationPlanSitesEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanSitesDAO_HI.class);

	public SrmPosInvestigationPlanSitesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosInvestigationPlanSitesEntity_HI entity) {
		return super.save(entity);
	}
}
