package saaf.common.fmw.spm.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.spm.model.entities.readonly.SrmSpmSupplierScoreEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmSpmSupplierScoreDAO_HI_RO")
public class SrmSpmSupplierScoreDAO_HI_RO extends DynamicViewObjectImpl<SrmSpmSupplierScoreEntity_HI_RO>  {
	public SrmSpmSupplierScoreDAO_HI_RO() {
		super();
	}

}
