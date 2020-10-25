package saaf.common.fmw.okc.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.okc.model.entities.SrmOkcContractTemplatesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmOkcContractTemplatesDAO_HI")
public class SrmOkcContractTemplatesDAO_HI extends ViewObjectImpl<SrmOkcContractTemplatesEntity_HI>  {
	public SrmOkcContractTemplatesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmOkcContractTemplatesEntity_HI entity) {
		return super.save(entity);
	}
}
