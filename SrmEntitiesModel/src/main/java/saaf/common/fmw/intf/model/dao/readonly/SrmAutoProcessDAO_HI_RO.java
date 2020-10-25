package saaf.common.fmw.intf.model.dao.readonly;

import org.springframework.stereotype.Component;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.intf.model.entities.readonly.SrmAutoProcessEntity_HI_RO;
import saaf.common.fmw.intf.model.entities.readonly.SrmPlanAnalyzeEntity_HI_RO;

 

@Component("srmAutoProcessDAO_HI_RO")
public class SrmAutoProcessDAO_HI_RO extends DynamicViewObjectImpl<SrmAutoProcessEntity_HI_RO>  {
	public SrmAutoProcessDAO_HI_RO() {
		super();
	}

	 
}