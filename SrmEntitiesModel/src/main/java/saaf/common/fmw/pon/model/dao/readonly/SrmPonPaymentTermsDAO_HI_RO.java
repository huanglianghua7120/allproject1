package saaf.common.fmw.pon.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pon.model.entities.readonly.SrmPonPaymentTermsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPonPaymentTermsDAO_HI_RO")
public class SrmPonPaymentTermsDAO_HI_RO extends DynamicViewObjectImpl<SrmPonPaymentTermsEntity_HI_RO>  {
	public SrmPonPaymentTermsDAO_HI_RO() {
		super();
	}

}
