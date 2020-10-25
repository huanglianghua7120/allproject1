package saaf.common.fmw.intf.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;

 

@Component("srmPlanAnalyzeDAO_HI_RO")
public class SrmPlanAnalyzeDAO_HI_RO extends DynamicViewObjectImpl<SrmPlanAnalyzeEntity_HI_RO>  {
	public SrmPlanAnalyzeDAO_HI_RO() {
		super();
	}

	 
}