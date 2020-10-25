package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseNotificationsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseNotificationsDAO_HI")
public class SrmBaseNotificationsDAO_HI extends ViewObjectImpl<SrmBaseNotificationsEntity_HI>  {
	public SrmBaseNotificationsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseNotificationsEntity_HI entity) {
		return super.save(entity);
	}
}
