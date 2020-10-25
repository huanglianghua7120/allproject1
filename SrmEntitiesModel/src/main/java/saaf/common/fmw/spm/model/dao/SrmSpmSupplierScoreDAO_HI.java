package saaf.common.fmw.spm.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.spm.model.entities.SrmSpmSupplierScoreEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmSpmSupplierScoreDAO_HI")
public class SrmSpmSupplierScoreDAO_HI extends ViewObjectImpl<SrmSpmSupplierScoreEntity_HI>  {
	public SrmSpmSupplierScoreDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmSpmSupplierScoreEntity_HI entity) {
		return super.save(entity);
	}
}
