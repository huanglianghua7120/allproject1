package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.base.model.entities.readonly.SrmBaseBanksEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseBanksDAO_HI_RO")
public class SrmBaseBanksDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseBanksEntity_HI_RO>  {
	public SrmBaseBanksDAO_HI_RO() {
		super();
	}

}
