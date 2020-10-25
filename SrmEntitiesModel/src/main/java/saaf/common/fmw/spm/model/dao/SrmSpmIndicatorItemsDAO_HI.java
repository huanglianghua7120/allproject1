package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmIndicatorItemsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmIndicatorItemsDAO_HI")
public class SrmSpmIndicatorItemsDAO_HI extends ViewObjectImpl<SrmSpmIndicatorItemsEntity_HI>  {
	public SrmSpmIndicatorItemsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmIndicatorItemsEntity_HI entity) {
		return super.save(entity);
	}
}
