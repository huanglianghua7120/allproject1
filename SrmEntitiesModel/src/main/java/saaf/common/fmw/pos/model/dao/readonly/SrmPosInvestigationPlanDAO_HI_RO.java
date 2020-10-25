package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosInvestigationPlanEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosInvestigationPlanDAO_HI_RO")
public class SrmPosInvestigationPlanDAO_HI_RO extends DynamicViewObjectImpl<SrmPosInvestigationPlanEntity_HI_RO>  {
	public SrmPosInvestigationPlanDAO_HI_RO() {
		super();
	}

}
