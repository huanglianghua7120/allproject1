package saaf.common.fmw.pos.model.dao.readonly;

import com.yhg.hibernate.core.dao.DynamicViewObjectImpl;
import saaf.common.fmw.pos.model.entities.readonly.SrmPosSupplierCredentialsEntity_HI_RO;
import org.springframework.stereotype.Component;

@Component("srmPosSupplierCredentialsDAO_HI_RO")
public class SrmPosSupplierCredentialsDAO_HI_RO extends DynamicViewObjectImpl<SrmPosSupplierCredentialsEntity_HI_RO>  {
	public SrmPosSupplierCredentialsDAO_HI_RO() {
		super();
	}

}
