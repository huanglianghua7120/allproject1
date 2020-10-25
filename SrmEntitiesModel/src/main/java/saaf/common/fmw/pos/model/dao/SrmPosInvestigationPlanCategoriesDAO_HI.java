package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.SrmPosInvestigationPlanCategoriesEntity_HI;

@Component("srmPosInvestigationPlanCategoriesDAO_HI")
public class SrmPosInvestigationPlanCategoriesDAO_HI extends ViewObjectImpl<SrmPosInvestigationPlanCategoriesEntity_HI> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanCategoriesDAO_HI.class);

	public SrmPosInvestigationPlanCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosInvestigationPlanCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}
