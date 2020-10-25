package saaf.common.fmw.prc.model.dao.readonly;

import org.springframework.stereotype.Component;

import saaf.common.fmw.prc.model.entities.readonly.SrmPrcIndicatorHeadersEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component
public class SrmPrcIndicatorHeadersDAO_HI_RO extends DynamicViewObjectImpl<SrmPrcIndicatorHeadersEntity_HI_RO>{
	
	public SrmPrcIndicatorHeadersDAO_HI_RO(){
		super();
	}
}
