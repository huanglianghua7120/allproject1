package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseItemDeliversEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseItemDeliversDAO_HI_RO")
public class SrmBaseItemDeliversDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseItemDeliversEntity_HI_RO>  {
	public SrmBaseItemDeliversDAO_HI_RO() {
		super();
	}
}
