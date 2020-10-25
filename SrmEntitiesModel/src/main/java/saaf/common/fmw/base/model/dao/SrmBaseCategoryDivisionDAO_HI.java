package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import saaf.common.fmw.base.model.entities.SrmBaseCategoryDivisionEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseCategoryDivisionDAO_HI")
@Transactional(readOnly=false)
public class SrmBaseCategoryDivisionDAO_HI extends ViewObjectImpl<SrmBaseCategoryDivisionEntity_HI>  {
	public SrmBaseCategoryDivisionDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseCategoryDivisionEntity_HI entity) {
		return super.save(entity);
	}
}
