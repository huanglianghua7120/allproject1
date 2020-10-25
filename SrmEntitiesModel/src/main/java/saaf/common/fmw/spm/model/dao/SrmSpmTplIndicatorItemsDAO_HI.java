package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmTplIndicatorItemsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmTplIndicatorItemsDAO_HI")
public class SrmSpmTplIndicatorItemsDAO_HI extends ViewObjectImpl<SrmSpmTplIndicatorItemsEntity_HI>  {
	public SrmSpmTplIndicatorItemsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmTplIndicatorItemsEntity_HI entity) {
		return super.save(entity);
	}
}
