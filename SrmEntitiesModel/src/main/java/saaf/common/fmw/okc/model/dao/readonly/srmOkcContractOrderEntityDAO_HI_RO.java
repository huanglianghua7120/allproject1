package saaf.common.fmw.okc.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import org.springframework.stereotype.Component;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTemplatesEntity_HI_RO;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractsOrderEntity_HI_RO;

@Component("srmOkcContractOrderEntityDAO_HI_RO")
public class srmOkcContractOrderEntityDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractsOrderEntity_HI_RO>  {
	public srmOkcContractOrderEntityDAO_HI_RO() {
		super();
	}

}
