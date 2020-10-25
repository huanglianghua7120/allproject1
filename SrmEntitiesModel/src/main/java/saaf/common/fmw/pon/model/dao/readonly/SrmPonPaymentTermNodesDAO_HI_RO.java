package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermNodesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonPaymentTermNodesDAO_HI_RO")
public class SrmPonPaymentTermNodesDAO_HI_RO extends DynamicViewObjectImpl<SrmPonPaymentTermNodesEntity_HI_RO>  {
	public SrmPonPaymentTermNodesDAO_HI_RO() {
		super();
	}

}
