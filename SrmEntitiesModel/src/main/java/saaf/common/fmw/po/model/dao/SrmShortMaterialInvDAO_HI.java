package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInvEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmShortMaterialInvDAO_HI")
public class SrmShortMaterialInvDAO_HI extends ViewObjectImpl<SrmShortMaterialInvEntity_HI>  {
	public SrmShortMaterialInvDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmShortMaterialInvEntity_HI entity) {
		return super.save(entity);
	}
}
