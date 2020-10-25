package saaf.common.fmw.apimanager.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerLEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafProjectManagerLDAO_HI")
public class SaafProjectManagerLDAO_HI extends ViewObjectImpl<SaafProjectManagerLEntity_HI>  {
	public SaafProjectManagerLDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafProjectManagerLEntity_HI entity) {
		return super.save(entity);
	}
}
