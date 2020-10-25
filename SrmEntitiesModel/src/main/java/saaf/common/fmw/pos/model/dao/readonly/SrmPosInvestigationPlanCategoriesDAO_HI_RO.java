package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanCategoriesEntity_HI_RO;

@Component("srmPosInvestigationPlanCategoriesDAO_HI_RO")
public class SrmPosInvestigationPlanCategoriesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosInvestigationPlanCategoriesEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanCategoriesDAO_HI_RO.class);
	public SrmPosInvestigationPlanCategoriesDAO_HI_RO() {
		super();
	}

}
