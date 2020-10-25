package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosQualificationCatesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosQualificationCatesDAO_HI_RO")
public class SrmPosQualificationCatesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosQualificationCatesEntity_HI_RO>  {
	public SrmPosQualificationCatesDAO_HI_RO() {
		super();
	}

}
