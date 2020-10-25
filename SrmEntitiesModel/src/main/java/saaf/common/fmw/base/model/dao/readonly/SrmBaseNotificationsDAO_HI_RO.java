package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseNotificationsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseNotificationsDAO_HI_RO")
public class SrmBaseNotificationsDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseNotificationsEntity_HI_RO>  {
	public SrmBaseNotificationsDAO_HI_RO() {
		super();
	}

}
