package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierContactsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierContactsDAO_HI_RO")
public class SrmPosSupplierContactsDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierContactsEntity_HI_RO>  {
	public SrmPosSupplierContactsDAO_HI_RO() {
		super();
	}

}
