package saaf.common.fmw.okc.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTemplatesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmOkcContractTemplatesDAO_HI_RO")
public class SrmOkcContractTemplatesDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractTemplatesEntity_HI_RO>  {
	public SrmOkcContractTemplatesDAO_HI_RO() {
		super();
	}

}
