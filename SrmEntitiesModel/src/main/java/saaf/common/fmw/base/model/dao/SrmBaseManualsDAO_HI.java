package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseManualsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseManualsDAO_HI")
public class SrmBaseManualsDAO_HI extends ViewObjectImpl<SrmBaseManualsEntity_HI>  {
	public SrmBaseManualsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseManualsEntity_HI entity) {
		return super.save(entity);
	}
}

