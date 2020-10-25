package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmShortMaterialConfigEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmShortMaterialConfigDAO_HI")
public class SrmShortMaterialConfigDAO_HI extends ViewObjectImpl<SrmShortMaterialConfigEntity_HI>  {
	public SrmShortMaterialConfigDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmShortMaterialConfigEntity_HI entity) {
		return super.save(entity);
	}
}
