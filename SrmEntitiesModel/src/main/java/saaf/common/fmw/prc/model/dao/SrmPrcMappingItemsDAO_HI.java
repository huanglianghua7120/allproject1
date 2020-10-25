package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingItemsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcMappingItemsDAO_HI")
public class SrmPrcMappingItemsDAO_HI extends ViewObjectImpl<SrmPrcMappingItemsEntity_HI>  {
	public SrmPrcMappingItemsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcMappingItemsEntity_HI entity) {
		return super.save(entity);
	}
}

