package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmOnlyShortMaterialInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmOnlyShortMaterialInfoDAO_HI")
public class SrmOnlyShortMaterialInfoDAO_HI extends ViewObjectImpl<SrmOnlyShortMaterialInfoEntity_HI>  {
	public SrmOnlyShortMaterialInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmOnlyShortMaterialInfoEntity_HI entity) {
		return super.save(entity);
	}
}
