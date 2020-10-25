package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeBankBfEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosChangeBankBfDAO_HI_RO")
public class SrmPosChangeBankBfDAO_HI_RO extends DynamicViewObjectImpl<SrmPosChangeBankBfEntity_HI_RO>  {
	public SrmPosChangeBankBfDAO_HI_RO() {
		super();
	}

}
