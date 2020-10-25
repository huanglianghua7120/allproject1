package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosChangeCredentialsEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosChangeCredentialsDAO_HI")
public class SrmPosChangeCredentialsDAO_HI extends ViewObjectImpl<SrmPosChangeCredentialsEntity_HI>  {
	public SrmPosChangeCredentialsDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosChangeCredentialsEntity_HI entity) {
		return super.save(entity);
	}
}

