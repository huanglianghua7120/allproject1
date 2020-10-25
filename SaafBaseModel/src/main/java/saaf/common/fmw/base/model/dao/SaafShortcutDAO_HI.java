package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SaafShortcutEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafShortcutDAO_HI")
public class SaafShortcutDAO_HI extends ViewObjectImpl<SaafShortcutEntity_HI>  {
	public SaafShortcutDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafShortcutEntity_HI entity) {
		return super.save(entity);
	}
}
