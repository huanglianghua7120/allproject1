package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingHeadersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcMappingHeadersDAO_HI")
public class SrmPrcMappingHeadersDAO_HI extends ViewObjectImpl<SrmPrcMappingHeadersEntity_HI>  {
	public SrmPrcMappingHeadersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcMappingHeadersEntity_HI entity) {
		return super.save(entity);
	}
}

