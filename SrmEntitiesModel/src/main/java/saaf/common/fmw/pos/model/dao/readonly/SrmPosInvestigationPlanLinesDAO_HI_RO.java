package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanLinesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosInvestigationPlanLinesDAO_HI_RO")
public class SrmPosInvestigationPlanLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosInvestigationPlanLinesEntity_HI_RO>  {
	public SrmPosInvestigationPlanLinesDAO_HI_RO() {
		super();
	}

}
