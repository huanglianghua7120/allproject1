package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeBankAfEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBankAfDAO_HI_RO")
public class SrmPosChangeBankAfDAO_HI_RO extends DynamicViewObjectImpl<SrmPosChangeBankAfEntity_HI_RO>  {
	public SrmPosChangeBankAfDAO_HI_RO() {
		super();
	}

}
