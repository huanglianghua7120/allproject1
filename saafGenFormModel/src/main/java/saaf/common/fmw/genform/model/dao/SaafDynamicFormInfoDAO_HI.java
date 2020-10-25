package saaf.common.fmw.genform.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.genform.model.entities.SaafDynamicFormInfoEntity_HI;
import org.springframework.stereotype.Component;

@Component("saafDynamicFormInfoDAO_HI")
public class SaafDynamicFormInfoDAO_HI extends ViewObjectImpl<SaafDynamicFormInfoEntity_HI>  {
	public SaafDynamicFormInfoDAO_HI() {
		super();
	}

	@Override
	public Object save(SaafDynamicFormInfoEntity_HI entity) {
		return super.save(entity);
	}
}
