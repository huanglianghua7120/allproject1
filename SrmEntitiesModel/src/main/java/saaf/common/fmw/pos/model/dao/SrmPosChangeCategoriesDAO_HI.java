package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeCategoriesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeCategoriesDAO_HI")
public class SrmPosChangeCategoriesDAO_HI extends ViewObjectImpl<SrmPosChangeCategoriesEntity_HI>  {
	public SrmPosChangeCategoriesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeCategoriesEntity_HI entity) {
		return super.save(entity);
	}
}

