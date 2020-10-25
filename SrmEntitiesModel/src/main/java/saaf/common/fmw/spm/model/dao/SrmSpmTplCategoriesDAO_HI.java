package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmTplCategoriesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmTplCategoriesDAO_HI")
public class SrmSpmTplCategoriesDAO_HI extends ViewObjectImpl<SrmSpmTplCategoriesEntity_HI>  {
	public SrmSpmTplCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmTplCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}
