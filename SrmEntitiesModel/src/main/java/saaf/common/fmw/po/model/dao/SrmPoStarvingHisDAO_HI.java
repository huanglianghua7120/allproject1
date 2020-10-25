package saaf.common.fmw.po.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.po.model.entities.SrmPoStarvingHisEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPoStarvingHisDAO_HI")
public class SrmPoStarvingHisDAO_HI extends ViewObjectImpl<SrmPoStarvingHisEntity_HI>  {
	public SrmPoStarvingHisDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPoStarvingHisEntity_HI entity) {
		return super.save(entity);
	}
}
