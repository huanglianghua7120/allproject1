package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBranchesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseBranchesDAO_HI_RO")
public class SrmBaseBranchesDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseBranchesEntity_HI_RO>  {
	public SrmBaseBranchesDAO_HI_RO() {
		super();
	}

}
