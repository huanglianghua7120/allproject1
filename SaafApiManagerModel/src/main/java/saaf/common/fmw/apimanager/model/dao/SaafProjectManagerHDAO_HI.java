package saaf.common.fmw.apimanager.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.apimanager.model.entities.SaafProjectManagerHEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafProjectManagerHDAO_HI")
public class SaafProjectManagerHDAO_HI extends ViewObjectImpl<SaafProjectManagerHEntity_HI>  {
	public SaafProjectManagerHDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafProjectManagerHEntity_HI entity) {
		return super.save(entity);
	}
}
