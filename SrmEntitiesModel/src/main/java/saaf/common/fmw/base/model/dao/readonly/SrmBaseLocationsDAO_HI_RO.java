package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseLocationsEntity_HI_RO;

@Component("srmBaseLocationsDAO_HI_RO")
public class SrmBaseLocationsDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseLocationsEntity_HI_RO> {
	public SrmBaseLocationsDAO_HI_RO() {
		super();
	}
}
