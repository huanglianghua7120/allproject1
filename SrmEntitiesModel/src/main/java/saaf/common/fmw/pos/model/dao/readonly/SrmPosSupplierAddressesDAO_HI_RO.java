package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierAddressesEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierAddressesDAO_HI_RO")
public class SrmPosSupplierAddressesDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierAddressesEntity_HI_RO>  {
	public SrmPosSupplierAddressesDAO_HI_RO() {
		super();
	}

}
