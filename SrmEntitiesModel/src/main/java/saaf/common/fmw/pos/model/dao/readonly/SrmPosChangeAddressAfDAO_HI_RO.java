package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeAddressAfEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosChangeAddressAfDAO_HI_RO")
public class SrmPosChangeAddressAfDAO_HI_RO extends DynamicViewObjectImpl<SrmPosChangeAddressAfEntity_HI_RO>  {
	public SrmPosChangeAddressAfDAO_HI_RO() {
		super();
	}

}
