package saaf.common.fmw.pos.model.dao.readonly;

import org.springframework.stereotype.Component;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationInfoEntity_HI_RO;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

@Component
public class SrmPosQualificationInfoDAO_HI_RO extends DynamicViewObjectImpl<SrmPosQualificationInfoEntity_HI_RO> {
	
	public SrmPosQualificationInfoDAO_HI_RO(){
		super();
	}
}
