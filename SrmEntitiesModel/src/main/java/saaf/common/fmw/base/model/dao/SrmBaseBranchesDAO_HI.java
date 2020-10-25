package saaf.common.fmw.base.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.base.model.entities.SrmBaseBranchesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmBaseBranchesDAO_HI")
public class SrmBaseBranchesDAO_HI extends ViewObjectImpl<SrmBaseBranchesEntity_HI>  {
	public SrmBaseBranchesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmBaseBranchesEntity_HI entity) {
		return super.save(entity);
	}
}
