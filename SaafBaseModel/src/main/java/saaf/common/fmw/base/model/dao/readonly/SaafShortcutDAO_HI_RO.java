package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SaafShortcutEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("saafShortcutDAO_HI_RO")
public class SaafShortcutDAO_HI_RO extends DynamicViewObjectImpl<SaafShortcutEntity_HI_RO>  {
	public SaafShortcutDAO_HI_RO() {
		super();
	}

}
