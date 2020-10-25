package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosChangeAddressBfEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosChangeAddressBfDAO_HI_RO")
public class SrmPosChangeAddressBfDAO_HI_RO extends DynamicViewObjectImpl<SrmPosChangeAddressBfEntity_HI_RO>  {
	public SrmPosChangeAddressBfDAO_HI_RO() {
		super();
	}

}
