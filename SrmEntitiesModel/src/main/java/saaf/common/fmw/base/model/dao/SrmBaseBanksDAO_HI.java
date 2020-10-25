package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseBanksEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseBanksDAO_HI")
public class SrmBaseBanksDAO_HI extends ViewObjectImpl<SrmBaseBanksEntity_HI>  {
	public SrmBaseBanksDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseBanksEntity_HI entity) {
		return super.save(entity);
	}
}
