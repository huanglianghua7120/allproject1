package saaf.common.fmw.okc.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.okc.model.entities.readonly.SrmOkcContractTermsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmOkcContractTermsDAO_HI_RO")
public class SrmOkcContractTermsDAO_HI_RO extends DynamicViewObjectImpl<SrmOkcContractTermsEntity_HI_RO>  {
	public SrmOkcContractTermsDAO_HI_RO() {
		super();
	}

}
