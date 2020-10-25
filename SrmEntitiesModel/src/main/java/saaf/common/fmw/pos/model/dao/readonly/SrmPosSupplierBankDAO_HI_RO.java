package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierBankEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierBankDAO_HI_RO")
public class SrmPosSupplierBankDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierBankEntity_HI_RO>  {
	public SrmPosSupplierBankDAO_HI_RO() {
		super();
	}

}
