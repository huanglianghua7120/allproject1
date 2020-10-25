package saaf.common.fmw.pon.model.dao;

import com.yhg.hibernate.core.dao.ViewObjectImpl;
import saaf.common.fmw.pon.model.entities.SrmPonPaymentTermNodesEntity_HI;
import org.springframework.stereotype.Component;

@Component("srmPonPaymentTermNodesDAO_HI")
public class SrmPonPaymentTermNodesDAO_HI extends ViewObjectImpl<SrmPonPaymentTermNodesEntity_HI>  {
	public SrmPonPaymentTermNodesDAO_HI() {
		super();
	}

	@Override
	public Object save(SrmPonPaymentTermNodesEntity_HI entity) {
		return super.save(entity);
	}
}
