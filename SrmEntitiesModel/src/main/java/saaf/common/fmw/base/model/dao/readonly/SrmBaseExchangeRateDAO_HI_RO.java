package saaf.common.fmw.base.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;

import saaf.common.fmw.base.model.entities.readonly.SrmBaseExchangeRateEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmBaseExchangeRateDAO_HI_RO")
public class SrmBaseExchangeRateDAO_HI_RO extends DynamicViewObjectImpl<SrmBaseExchangeRateEntity_HI_RO>  {
	public SrmBaseExchangeRateDAO_HI_RO() {
		super();
	}

}
