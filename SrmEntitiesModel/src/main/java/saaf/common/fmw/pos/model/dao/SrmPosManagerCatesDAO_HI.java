package saaf.common.fmw.pos.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pos.model.entities.SrmPosManagerCatesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPosManagerCatesDAO_HI")
public class SrmPosManagerCatesDAO_HI extends ViewObjectImpl<SrmPosManagerCatesEntity_HI>  {
	public SrmPosManagerCatesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPosManagerCatesEntity_HI entity) {
		return super.save(entity);
	}
}

