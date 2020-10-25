package saaf.common.fmw.apimanager.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.apimanager.model.entities.SaafProjectApiMamagerEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafProjectApiMamagerDAO_HI")
public class SaafProjectApiMamagerDAO_HI extends ViewObjectImpl<SaafProjectApiMamagerEntity_HI>  {
	public SaafProjectApiMamagerDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafProjectApiMamagerEntity_HI entity) {
		return super.save(entity);
	}
}
