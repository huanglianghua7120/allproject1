package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmTplDimensionEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmTplDimensionDAO_HI")
public class SrmSpmTplDimensionDAO_HI extends ViewObjectImpl<SrmSpmTplDimensionEntity_HI>  {
	public SrmSpmTplDimensionDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmTplDimensionEntity_HI entity) {
		return super.save(entity);
	}
}
