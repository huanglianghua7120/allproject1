package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SaafBaseResultFileEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("saafBaseResultFileDAO_HI_RO")
public class SaafBaseResultFileDAO_HI_RO extends DynamicViewObjectImpl<SaafBaseResultFileEntity_HI_RO>  {
	public SaafBaseResultFileDAO_HI_RO() {
		super();
	}

}
