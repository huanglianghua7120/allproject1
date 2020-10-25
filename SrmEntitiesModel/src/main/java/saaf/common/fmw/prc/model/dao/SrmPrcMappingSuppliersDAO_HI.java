package saaf.common.fmw.prc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.prc.model.entities.SrmPrcMappingSuppliersEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPrcMappingSuppliersDAO_HI")
public class SrmPrcMappingSuppliersDAO_HI extends ViewObjectImpl<SrmPrcMappingSuppliersEntity_HI>  {
	public SrmPrcMappingSuppliersDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPrcMappingSuppliersEntity_HI entity) {
		return super.save(entity);
	}
}

