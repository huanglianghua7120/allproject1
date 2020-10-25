package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmShortMaterialWarmEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmShortMaterialWarmDAO_HI")
public class SrmShortMaterialWarmDAO_HI extends ViewObjectImpl<SrmShortMaterialWarmEntity_HI>  {
	public SrmShortMaterialWarmDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmShortMaterialWarmEntity_HI entity) {
		return super.save(entity);
	}
}
