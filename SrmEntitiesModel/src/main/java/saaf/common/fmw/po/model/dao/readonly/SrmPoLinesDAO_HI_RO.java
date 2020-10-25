package saaf.common.fmw.po.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.po.model.entities.readonly.SrmPoLinesEntity_HI_RO;

@Component("srmPoLinesDAO_HI_RO")
public class SrmPoLinesDAO_HI_RO extends DynamicViewObjectImpl<SrmPoLinesEntity_HI_RO> {
	public SrmPoLinesDAO_HI_RO() {
		super();
	}


}
