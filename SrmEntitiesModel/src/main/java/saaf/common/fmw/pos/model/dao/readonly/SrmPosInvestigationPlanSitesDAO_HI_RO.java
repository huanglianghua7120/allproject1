package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanSitesEntity_HI_RO;

@Component("srmPosInvestigationPlanSitesDAO_HI_RO")
public class SrmPosInvestigationPlanSitesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosInvestigationPlanSitesEntity_HI_RO>  {
	private static final Logger LOGGER = LoggerFactory.getLogger(SrmPosInvestigationPlanSitesDAO_HI_RO.class);
	public SrmPosInvestigationPlanSitesDAO_HI_RO() {
		super();
	}

}
