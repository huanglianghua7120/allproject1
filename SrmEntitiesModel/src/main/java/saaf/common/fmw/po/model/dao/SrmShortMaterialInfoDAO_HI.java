package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmShortMaterialInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmShortMaterialInfoDAO_HI")
public class SrmShortMaterialInfoDAO_HI extends ViewObjectImpl<SrmShortMaterialInfoEntity_HI>  {
	public SrmShortMaterialInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmShortMaterialInfoEntity_HI entity) {
		return super.save(entity);
	}
}
